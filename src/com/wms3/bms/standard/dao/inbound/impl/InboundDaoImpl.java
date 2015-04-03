package com.wms3.bms.standard.dao.inbound.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.inbound.IInboundDao;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inbound.InboundInvoiceDetail;
import com.wms3.bms.standard.entity.inbound.InboundInvoiceHeader;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * 
 * 描述: 入库单数据库操作DAO类
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class InboundDaoImpl extends AbstractDao implements IInboundDao{
    
    public InboundDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
	/**
	 * 功能:生成入库单据明细
	 * @param inInvoice		入库单
	 * @param lsinDetail	入库单明细的集合
	 * @param ids			对应作业明细的id
	 * @return 
	 * @throws Exception
	 */
	public void createInvoice(InboundHeader inInvoice, List<InboundDetail> lsinDetail, String ids) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //增加入库单
            session.save("InboundHeader", inInvoice);	
            
            //增加入库单详细
            if(lsinDetail != null){
            	InboundDetail inDetail = null;	
            	
            	String strSql = "";
                String[] aem = ids.split("\\|");
                
                for(int i = 0; i < lsinDetail.size(); i++){
                	inDetail = lsinDetail.get(i);
                    session.save("InboundDetail", inDetail);
                    
                    //修改对应明细作业的生成单据状态
                    strSql = "update InoutJobdetail as jobdetail set jobdetail.isinvoice='Y', "
                		+ " jobdetail.invoiceid='" + inInvoice.getInstockid() + "',"
                		+ " jobdetail.invoicedetailid='" + inDetail.getInstockdetailid() + "'"
                		+ " where jobdetail.jobdetailid in (" + aem[i].substring(0, aem[i].length()-1) + ")";
                    
                    session.createQuery(strSql).executeUpdate();
                }
            }
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("生成入库单据明细出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
		
	}

	/**
	 * 功能:根据入库单据编号查询入库单
	 * @param instockid	入库单据编号
	 * @return 
	 * @throws Exception
	 */
	public InboundHeader getInboundHeader(String instockid) throws Exception {
		
		if(instockid != null){
		
			String strSql = " from InboundHeader as t where t.instockid='" + instockid + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (InboundHeader)ls.get(0);
			}
		}
		return null;
	}

	/**
	 * 功能:更新入库单
	 * @param instockid	入库单据编号
	 * @return 
	 * @throws Exception
	 */
	public void updateInboundInvoice(InboundHeader inbound) throws Exception {
		m_dao.update(inbound);
	}

	/**
	 * 功能:删除入库单
	 * @param instockid	入库单据编号
	 * @return 
	 * @throws Exception
	 */
	public void deleteInboundInvoice(String instockid) throws Exception {
		
		try{
            if(instockid != null){
                List<String> lsSQL = new ArrayList<String>();
                
                String strSql = "delete from InboundInvoiceHeader as t where t.instockid='" + instockid + "'";
                lsSQL.add(strSql);
                
                String strSql1 = "delete from InboundInvoiceDetail as t where t.instockid='" + instockid + "'";
                lsSQL.add(strSql1);
                
                m_dao.deleteSqlList(lsSQL);
            }
        }catch(Exception er){
            throw new Exception("删除入库单出错：" + er.getMessage());
        }  
	}

	/**
	 * 功能:根据入库单据编号查询作业的总数量
	 * @param instockdetailid	入库单据详细编号
	 * @return 
	 * @throws Exception
	 */
	public int getJobNumSum(String instockdetailid) throws Exception {
		
		int iNum = 0;
		
		try {
			String strSql = "select sum(jobdetail.jobnum) from InoutJob job,InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.boundstockdetailid='" + instockdetailid + "'";
			iNum = m_dao.searchEntitieCount(strSql);
			
		} catch(Exception er) {
			er.printStackTrace();
			throw new Exception("根据入库单据详细编号获得作业总数量出错:" + er.getMessage());
		}
		return iNum;
	}

	/**
	 * 功能:作废单据
	 * @param instockid	入库单据编号
	 * @return 
	 * @throws Exception
	 */
	public void cancelInboundInvoice(String instockid) throws Exception {
		
		try{
            if(instockid != null){
            	
                List<String> lsSQL = new ArrayList<String>();
                
                String strSql = "update InboundHeader as invoice set invoice.instatus='5' where invoice.instockid='" + instockid + "'";
                lsSQL.add(strSql);
                
                //修改作业里的单据信息
                String strSql1 = "update InoutJob as job " +
                		" set job.isinvoice='N', job.boundstockid='', job.boundstockdetailid='' where job.boundstockid='" + instockid + "'";
                lsSQL.add(strSql1);
                
                m_dao.deleteSqlList(lsSQL);
            }
        }catch(Exception er){
            throw new Exception("作废入库单出错：" + er.getMessage());
        }  
	}

	/**
	 * 功能:作废详细单据
	 * @param instockdetailid	入库单据详细编号
	 * @return 
	 * @throws Exception
	 */
	public void cancelInboundDetail(String instockdetailid) throws Exception {
		
		try{
            if(instockdetailid != null){
            	
                List<String> lsSQL = new ArrayList<String>();
                
                String strSql = "update InboundInvoiceDetail as inboundDetail set inboundDetail.linestatus='5' " +
                		" where inboundDetail.instockdetailid='" + instockdetailid + "'";
                lsSQL.add(strSql);
                
                //修改作业里的单据信息
                String strSql1 = "update InoutJobdetail as jobdetail " +
                		" set jobdetail.isinvoice='N', jobdetail.invoiceid='', jobdetail.invoicedetailid='' " +
                		" where jobdetail.invoicedetailid='" + instockdetailid + "'";
                lsSQL.add(strSql1);
                
                m_dao.deleteSqlList(lsSQL);
            }
        }catch(Exception er){
            throw new Exception("作废入库单明细出错：" + er.getMessage());
        }  
	}
	/**
	 * rF回流入库
	 * 
	 */
	public String addHLRKJob(String inventoryId,String strUserCode,String getnum) throws Exception{

		String strBackMsg = "Y";
		
		String hql = "from InventoryStorage as inventory where inventory.inventoryid='"+inventoryId+"'";
		Session session = null;
		try{
			List ls = m_dao.searchEntities(hql);
			
			if(ls!=null&&ls.size()>0){
				for(int i = 0; i<ls.size();i++){
					InventoryStorage inventoryS = (InventoryStorage)ls.get(i);
					InoutJob job = new InoutJob();
					InoutJobdetail jobdetail = new InoutJobdetail();
					//入库作业号
		            BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(m_dao);
		            BaseSeq  seqEn = seqDao.getSeqByType("RKZY");
		            String strNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),m_dao);
		            job.setJobid(strNo);
					job.setJobtype("hl");
					job.setOnLineType("1");//联机操作
					job.setIsaccount("N");//记账
					job.setStatus("1"); //未执行状态
					job.setPriority(1);//优先级
					job.setCreatetime(StrTools.getCurrDateTime(2));
					job.setInOutType("1");//入库类型
					job.setTraycode(inventoryS.getTraycode());
					job.setRoute("2");//路线 直入
					job.setOpManId(strUserCode);
					job.setInvoicetype("3");//1：按入库收货单来生成的作业，2：按出库单生成的作业，3： 回流直接生成的作业（从暂存到立体库）
					job.setBoundrequeststockid(inventoryS.getRequestid());
					job.setBoundstockid(inventoryS.getInstockid());
					
					job.setWarehouseid(inventoryS.getWarehouseid());
					job.setScargoAlleyId(inventoryS.getCargoAlleyId());
					job.setScargoSpaceId(inventoryS.getCargoSpaceId());
					job.setScargoWhareaId(inventoryS.getWhAreaId());
				
					
					jobdetail.setJobid(strNo);
					jobdetail.setProductid(inventoryS.getProductid());
					jobdetail.setLotid(inventoryS.getLotid());
					jobdetail.setLotinfo(inventoryS.getLotinfo());
					jobdetail.setPunit(inventoryS.getPunit());
					
					jobdetail.setJobnum(Double.parseDouble(getnum));
					jobdetail.setAssignnum(Double.parseDouble(getnum));
					jobdetail.setPrintdate(inventoryS.getProductdate());
					jobdetail.setProductStatus(inventoryS.getProductstatus());
					jobdetail.setIsplit("2");//整托
					jobdetail.setIsinvoice("N");//设置是否已生成单据 Y是  N否
					jobdetail.setInventoryid(inventoryS.getInventoryid());
					
					session = m_dao.getSession();
					Transaction tx = session.beginTransaction();
					session.save(job);
					session.save(jobdetail);
					double num = inventoryS.getPnum() - Double.parseDouble(getnum);
					if(num==0){
						session.delete(inventoryS);
					}else if(num>0){
						inventoryS.setPnum(num);		
						session.update(inventoryS);
					}else if(num<0){
						strBackMsg = "库存数量小于实收数量！";
					}
					tx.commit();
					
				}
				
			}
		}catch (HibernateException he) {
			Logger.error("回流入库生成作业出错！保存失败"+he.getMessage());
			strBackMsg = "回流入库生成作业出错！";
		}
		finally
		{
    		m_dao.closeSession(session);
    	}   
		return strBackMsg;
	}

	/**
	 * rF回流入库(有指定货位)
	 * 
	 */
	public String addHLRKJobplus(String inventoryId,String Virtualwhid,String sjtraycode,String strUserCode,String getnum,String platoon,String column,String floor) throws Exception{

		String strBackMsg = "Y";
		
		String hql = "from InventoryStorage as inventory where inventory.inventoryid='"+inventoryId+"'";
		Session session = null;
		try{
			List ls = m_dao.searchEntities(hql);
			if(ls!=null&&ls.size()>0){
				for(int i = 0; i<ls.size();i++){
					InventoryStorage inventoryS = (InventoryStorage)ls.get(i);
					InoutJob job = new InoutJob();
					InoutJobdetail jobdetail = new InoutJobdetail();
					//入库作业号
		            BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(m_dao);
		            BaseSeq  seqEn = seqDao.getSeqByType("RKZY");
		            String strNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),m_dao);
		            job.setJobid(strNo);
		            job.setVirtualwhid(Virtualwhid);//逻辑库区id
					job.setJobtype("hl");
					job.setOnLineType("1");//联机操作
					job.setIsaccount("N");//记账
					job.setStatus("1"); //未执行状态
					job.setPriority(1);//优先级
					job.setCreatetime(StrTools.getCurrDateTime(2));
					job.setInOutType("1");//入库类型
					job.setTraycode(sjtraycode);//托盘条码
					job.setRoute("2");//路线 直入
					job.setOpManId(strUserCode);
					job.setInvoicetype("3");//1：按入库收货单来生成的作业，2：按出库单生成的作业，3： 回流直接生成的作业（从暂存到立体库）
					job.setBoundrequeststockid(inventoryS.getRequestid());
					job.setBoundstockid(inventoryS.getInstockid());
					
					IBaseCargoSpaceDao bspacedao=new BaseCargoSpaceDaoImpl(m_dao);
					ScheduleTask task = null;
					if(platoon!=null&&platoon.length()>0&&column!=null&&column.length()>0&&floor!=null&&floor.length()>0){
						
						BaseCargospace space = bspacedao.GetCargospace(platoon, column, floor,inventoryS.getWarehouseid());
						job.setTcargoSpaceId(space.getCargoSpaceId());
						job.setTcargoWhareaId(space.getWhAreaId());
						
						job.setTcargoAlleyId(space.getCargoAlleyId());
						space.setCsstatus("3");
						job.setWarehouseid(space.getWarehouseid());
						
	                    task = new ScheduleTask();
	                    
	                    String strTaskId= SeqTool.getID("RW",m_dao); //作业ID  
	                    task.setTaskid(strTaskId);    //taskid;          调度任务编号
	                    job.setTaskid(strTaskId); //调度任务ID 
	                    task.setTasktype("2");          //调度类型 1-入库 2-出库 3-移库
	                    task.setSplatoon(space.getCsplatoon());       //源货位排
	                    task.setScolumn(space.getCscolumn());         //源货位列
	                    task.setSfloor(space.getCsfloor());           //源货位层   
	                    task.setCargoSpaceId(space.getCargoSpaceId());// 货位ID
	                    task.setCargoAlleyId(space.getCargoAlleyId());// 巷道ID
	                    task.setWarehouseid(space.getWarehouseid());  // 仓库ID
	                    task.setWhAreaId(space.getWhAreaId());        // 库区ID  
	                    task.setStatus("1");          //任务状态
	                    task.setPriority(10);         //优先级	                                
	                    task.setCreatetime(StrTools.getCurrDateTime(2));      //时间 时间格式：yyyy-MM-dd hh:mm:ss
	                    task.setTraycode(inventoryS.getTraycode()); //托盘条码
	                    task.setTaskpos("2");         //任务方向 1.入库端 2.出库端
	                    task.setRoute("1");            //执行路线 1-直入/直出 2-回流                              
	                    
	                    bspacedao.updateCargospace(space);
					}
					
					
					job.setWarehouseid(inventoryS.getWarehouseid());
					job.setScargoAlleyId(inventoryS.getCargoAlleyId());
					job.setScargoSpaceId(inventoryS.getCargoSpaceId());
					job.setScargoWhareaId(inventoryS.getWhAreaId());
				
					
					jobdetail.setJobid(strNo);
					jobdetail.setProductid(inventoryS.getProductid());
					jobdetail.setLotid(inventoryS.getLotid());
					jobdetail.setLotinfo(inventoryS.getLotinfo());
					jobdetail.setLotinfo2(inventoryS.getLotinfo());
					jobdetail.setPunit(inventoryS.getPunit());
					
					jobdetail.setJobnum(Double.parseDouble(getnum));
					jobdetail.setAssignnum(Double.parseDouble(getnum));
					jobdetail.setPrintdate(inventoryS.getProductdate());
					jobdetail.setProductStatus(inventoryS.getProductstatus());
					jobdetail.setIsinvoice("N");//设置是否已生成单据 Y是  N否
					jobdetail.setInventoryid(inventoryS.getInventoryid());
					
					session = m_dao.getSession();
					Transaction tx = session.beginTransaction();
					session.save(job);
					session.save(jobdetail);
					session.save("ScheduleTask",task);
					double num = inventoryS.getPnum() - Double.parseDouble(getnum);
					if(num==0){
						session.delete(inventoryS);
					}else if(num>0){
						inventoryS.setPnum(num);		
						session.update(inventoryS);
					}else if(num<0){
						strBackMsg = "库存数量小于实收数量！";
					}
					tx.commit();
					
				}
				
			}
		}catch (HibernateException he) {
			Logger.error("回流入库生成作业出错！保存失败"+he.getMessage());
			strBackMsg = "回流入库生成作业出错！";
		}
		finally
		{
    		m_dao.closeSession(session);
    	}   
		return strBackMsg;
	}
	
}
