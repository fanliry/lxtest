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
 * ����: ��ⵥ���ݿ����DAO��
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class InboundDaoImpl extends AbstractDao implements IInboundDao{
    
    public InboundDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
	/**
	 * ����:������ⵥ����ϸ
	 * @param inInvoice		��ⵥ
	 * @param lsinDetail	��ⵥ��ϸ�ļ���
	 * @param ids			��Ӧ��ҵ��ϸ��id
	 * @return 
	 * @throws Exception
	 */
	public void createInvoice(InboundHeader inInvoice, List<InboundDetail> lsinDetail, String ids) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //������ⵥ
            session.save("InboundHeader", inInvoice);	
            
            //������ⵥ��ϸ
            if(lsinDetail != null){
            	InboundDetail inDetail = null;	
            	
            	String strSql = "";
                String[] aem = ids.split("\\|");
                
                for(int i = 0; i < lsinDetail.size(); i++){
                	inDetail = lsinDetail.get(i);
                    session.save("InboundDetail", inDetail);
                    
                    //�޸Ķ�Ӧ��ϸ��ҵ�����ɵ���״̬
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
            throw new  Exception("������ⵥ����ϸ����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
		
	}

	/**
	 * ����:������ⵥ�ݱ�Ų�ѯ��ⵥ
	 * @param instockid	��ⵥ�ݱ��
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
	 * ����:������ⵥ
	 * @param instockid	��ⵥ�ݱ��
	 * @return 
	 * @throws Exception
	 */
	public void updateInboundInvoice(InboundHeader inbound) throws Exception {
		m_dao.update(inbound);
	}

	/**
	 * ����:ɾ����ⵥ
	 * @param instockid	��ⵥ�ݱ��
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
            throw new Exception("ɾ����ⵥ����" + er.getMessage());
        }  
	}

	/**
	 * ����:������ⵥ�ݱ�Ų�ѯ��ҵ��������
	 * @param instockdetailid	��ⵥ����ϸ���
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
			throw new Exception("������ⵥ����ϸ��Ż����ҵ����������:" + er.getMessage());
		}
		return iNum;
	}

	/**
	 * ����:���ϵ���
	 * @param instockid	��ⵥ�ݱ��
	 * @return 
	 * @throws Exception
	 */
	public void cancelInboundInvoice(String instockid) throws Exception {
		
		try{
            if(instockid != null){
            	
                List<String> lsSQL = new ArrayList<String>();
                
                String strSql = "update InboundHeader as invoice set invoice.instatus='5' where invoice.instockid='" + instockid + "'";
                lsSQL.add(strSql);
                
                //�޸���ҵ��ĵ�����Ϣ
                String strSql1 = "update InoutJob as job " +
                		" set job.isinvoice='N', job.boundstockid='', job.boundstockdetailid='' where job.boundstockid='" + instockid + "'";
                lsSQL.add(strSql1);
                
                m_dao.deleteSqlList(lsSQL);
            }
        }catch(Exception er){
            throw new Exception("������ⵥ����" + er.getMessage());
        }  
	}

	/**
	 * ����:������ϸ����
	 * @param instockdetailid	��ⵥ����ϸ���
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
                
                //�޸���ҵ��ĵ�����Ϣ
                String strSql1 = "update InoutJobdetail as jobdetail " +
                		" set jobdetail.isinvoice='N', jobdetail.invoiceid='', jobdetail.invoicedetailid='' " +
                		" where jobdetail.invoicedetailid='" + instockdetailid + "'";
                lsSQL.add(strSql1);
                
                m_dao.deleteSqlList(lsSQL);
            }
        }catch(Exception er){
            throw new Exception("������ⵥ��ϸ����" + er.getMessage());
        }  
	}
	/**
	 * rF�������
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
					//�����ҵ��
		            BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(m_dao);
		            BaseSeq  seqEn = seqDao.getSeqByType("RKZY");
		            String strNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),m_dao);
		            job.setJobid(strNo);
					job.setJobtype("hl");
					job.setOnLineType("1");//��������
					job.setIsaccount("N");//����
					job.setStatus("1"); //δִ��״̬
					job.setPriority(1);//���ȼ�
					job.setCreatetime(StrTools.getCurrDateTime(2));
					job.setInOutType("1");//�������
					job.setTraycode(inventoryS.getTraycode());
					job.setRoute("2");//·�� ֱ��
					job.setOpManId(strUserCode);
					job.setInvoicetype("3");//1��������ջ��������ɵ���ҵ��2�������ⵥ���ɵ���ҵ��3�� ����ֱ�����ɵ���ҵ�����ݴ浽����⣩
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
					jobdetail.setIsplit("2");//����
					jobdetail.setIsinvoice("N");//�����Ƿ������ɵ��� Y��  N��
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
						strBackMsg = "�������С��ʵ��������";
					}
					tx.commit();
					
				}
				
			}
		}catch (HibernateException he) {
			Logger.error("�������������ҵ��������ʧ��"+he.getMessage());
			strBackMsg = "�������������ҵ����";
		}
		finally
		{
    		m_dao.closeSession(session);
    	}   
		return strBackMsg;
	}

	/**
	 * rF�������(��ָ����λ)
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
					//�����ҵ��
		            BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(m_dao);
		            BaseSeq  seqEn = seqDao.getSeqByType("RKZY");
		            String strNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),m_dao);
		            job.setJobid(strNo);
		            job.setVirtualwhid(Virtualwhid);//�߼�����id
					job.setJobtype("hl");
					job.setOnLineType("1");//��������
					job.setIsaccount("N");//����
					job.setStatus("1"); //δִ��״̬
					job.setPriority(1);//���ȼ�
					job.setCreatetime(StrTools.getCurrDateTime(2));
					job.setInOutType("1");//�������
					job.setTraycode(sjtraycode);//��������
					job.setRoute("2");//·�� ֱ��
					job.setOpManId(strUserCode);
					job.setInvoicetype("3");//1��������ջ��������ɵ���ҵ��2�������ⵥ���ɵ���ҵ��3�� ����ֱ�����ɵ���ҵ�����ݴ浽����⣩
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
	                    
	                    String strTaskId= SeqTool.getID("RW",m_dao); //��ҵID  
	                    task.setTaskid(strTaskId);    //taskid;          ����������
	                    job.setTaskid(strTaskId); //��������ID 
	                    task.setTasktype("2");          //�������� 1-��� 2-���� 3-�ƿ�
	                    task.setSplatoon(space.getCsplatoon());       //Դ��λ��
	                    task.setScolumn(space.getCscolumn());         //Դ��λ��
	                    task.setSfloor(space.getCsfloor());           //Դ��λ��   
	                    task.setCargoSpaceId(space.getCargoSpaceId());// ��λID
	                    task.setCargoAlleyId(space.getCargoAlleyId());// ���ID
	                    task.setWarehouseid(space.getWarehouseid());  // �ֿ�ID
	                    task.setWhAreaId(space.getWhAreaId());        // ����ID  
	                    task.setStatus("1");          //����״̬
	                    task.setPriority(10);         //���ȼ�	                                
	                    task.setCreatetime(StrTools.getCurrDateTime(2));      //ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
	                    task.setTraycode(inventoryS.getTraycode()); //��������
	                    task.setTaskpos("2");         //������ 1.���� 2.�����
	                    task.setRoute("1");            //ִ��·�� 1-ֱ��/ֱ�� 2-����                              
	                    
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
					jobdetail.setIsinvoice("N");//�����Ƿ������ɵ��� Y��  N��
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
						strBackMsg = "�������С��ʵ��������";
					}
					tx.commit();
					
				}
				
			}
		}catch (HibernateException he) {
			Logger.error("�������������ҵ��������ʧ��"+he.getMessage());
			strBackMsg = "�������������ҵ����";
		}
		finally
		{
    		m_dao.closeSession(session);
    	}   
		return strBackMsg;
	}
	
}
