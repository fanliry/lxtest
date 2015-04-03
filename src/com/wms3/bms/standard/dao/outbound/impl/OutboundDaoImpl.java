package com.wms3.bms.standard.dao.outbound.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryDaoImpl;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.dao.job.impl.TaskDaoImpl;
import com.wms3.bms.standard.dao.outbound.IOutboundDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
import com.wms3.bms.standard.entity.outerInterface.SaleFormDetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * 描述: 出库管理DAO类
 * @author hug 2012-9-13
 * @since WMS 3.0
 */
public class OutboundDaoImpl extends AbstractDao implements IOutboundDao{
    
    public OutboundDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }  
    /**
     * 增加出库单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#addOutBound(com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader)
     */
    public void addOutBound(OutboundInvoiceHeader invoice) throws Exception {
        m_dao.save("OutboundInvoiceHeader", invoice);  
    }
    /**
     * 更新出库单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#updateOutBound(com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader)
     */
    public void updateOutBound(OutboundInvoiceHeader invoice) throws Exception {
        m_dao.update("OutboundInvoiceHeader", invoice);
    }
    /**
     * 增加出库单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#addOutBoundDetail(com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail)
     */
    public void addOutBoundDetail(OutboundInvoiceDetail outBoundDetail) throws Exception {
        m_dao.save("OutboundInvoiceDetail", outBoundDetail);
    }
    /**
     * 修改出库单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#updateOutBoundDetail(com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail)
     */
    public void updateOutBoundDetail(OutboundInvoiceDetail outBoundDetail) throws Exception {
        m_dao.update("OutboundInvoiceDetail", outBoundDetail);  
    }
    /**
     * 删除出库单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#deleteOutBound(java.lang.String)
     */
    public void deleteOutBound(String strOutBoundId) throws Exception {
        try{
            if(strOutBoundId != null){
                List<String> lsSQL = new ArrayList<String>();
                String strSql = "delete from OutboundInvoiceHeader as outvo where outvo.outstockid='" + strOutBoundId + "'";
                lsSQL.add(strSql);
                String strSql1 = "delete from OutboundInvoiceDetail as outde where outde.outstockid='" + strOutBoundId + "'";
                lsSQL.add(strSql1);
                m_dao.deleteSqlList(lsSQL);
            }
        }catch(Exception er){
            throw new Exception("删除出库单出错：" + er.getMessage());
        }   
    }
    /**
     * 删除出库单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#deleteOutBoundDetail(java.lang.String)
     */
    public void deleteOutBoundDetail(String strDetailId) throws Exception {
        try{
            if(strDetailId != null){
                String strSql = "delete from OutboundInvoiceDetail as outde where outde.outstockdetailid='" + strDetailId + "'";
                m_dao.deleteSql(strSql);
            }
        }catch(Exception er){
            throw new Exception("删除出库单详细出错：" + er.getMessage());
        } 
    }
    /**
     * 根据出库单ID获得出库单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#getOutBoundById(java.lang.String)
     */
    public OutboundInvoiceHeader getOutBoundById(String strOutBoundId) throws Exception {
        try{
            if(strOutBoundId != null){
                String strSql = "from OutboundInvoiceHeader as invo where invo.outstockid='" + strOutBoundId + "'";
                List lsOutBound = m_dao.searchEntities(strSql, 0, 1);
                if(lsOutBound != null && lsOutBound.size() > 0){
                    return (OutboundInvoiceHeader)lsOutBound.get(0);
                }
            }
        }catch(Exception er){
            throw new Exception("根据出库单ID["+strOutBoundId+"]获得出库单出错：" + er.getMessage());
        }
        return null;
    }
    /**
     * 功能:获取没有完全分配的出库产品信息
     * @author yao 2015-3-11
     * @return
     * @throws Exception
     */
    public List getOutBoundProudctInfo() throws Exception{
    	List lsList = null; 
    	try{
    		
             String strSql = "select ta.saleno,tb.outstockdetailid,ta.customername,tb.lotinfo,tb.m_strProductCode ,(tb.invoicenum- ISNULL(tb.assignnum,0)),tb.printdate,tb.productid,tb.Virtualwhid from OutboundInvoiceHeader ta ,OutboundInvoiceDetail tb where ta.outstockid=tb.outstockid and ta.outstatus not in('7','8') and tb.invoicenum>tb.assignnum order by tb.lotinfo,ta.saleno";
             lsList = m_dao.searchEntities(strSql);
        }catch(Exception er){
            throw new Exception("获取没有完全分配的出库产品信息出错：" + er.getMessage());
        }
        return lsList;
    }
    /**
     * 根据出库单详细ID获得出库单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#getOutBoundDetailById(java.lang.String)
     */
    public OutboundInvoiceDetail getOutBoundDetailById(String strInvoiceDetailId) throws Exception {
        try{
            if(strInvoiceDetailId != null){
                String strSql = "from OutboundInvoiceDetail as invo where invo.outstockdetailid='" + strInvoiceDetailId + "'";
                List lsOutBound = m_dao.searchEntities(strSql, 0, 1);
                if(lsOutBound != null && lsOutBound.size() > 0){
                    return (OutboundInvoiceDetail)lsOutBound.get(0);
                }
            }
        }catch(Exception er){
            throw new Exception("根据出库单详细ID["+strInvoiceDetailId+"]获得出库单详细出错：" + er.getMessage());
        }
        return null;
    }
    /**
     * 根据出库单ID获得出库单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#getOutBoundDetailsById(java.lang.String)
     */
    public List<OutboundInvoiceDetail> getOutBoundDetailsById(String strInvoiceId) throws Exception {
        try{
            if(strInvoiceId != null){
                String strSql = "from OutboundInvoiceDetail as invo where invo.outstockid='" + strInvoiceId + "'";
                List<OutboundInvoiceDetail> lsOutBound = m_dao.searchEntities(strSql);
                return lsOutBound;
            }
        }catch(Exception er){
            throw new Exception("根据出库单ID["+strInvoiceId+"]获得出库单详细出错：" + er.getMessage());
        }
        return null;
    }
    /**
     * 根据仓库ID获得需要发货确认的出库单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#getSendOutInvoice(java.lang.String)
     */
    public List<OutboundInvoiceHeader> getSendOutInvoice(String warehouseid) throws Exception {
        List<OutboundInvoiceHeader> lsResult = null;
        try{
            if(warehouseid != null){//出库单状态 0:开单；1-预配 2-分配 4-完全拣货   7:发货确认 8:作废
                String strSql = "from OutboundInvoiceHeader as outinv  where outinv.outstatus not in('1','7','8') and outinv.warehouseid='" + warehouseid + "'";     
                lsResult = m_dao.searchEntities(strSql);
            }
        }catch(Exception er){
            throw new Exception("根据仓库ID获得需要发货确认的出库单出错：" + er.getMessage());
        }
        return lsResult;
    }
    /**
     * 根据仓库ID获得需要发货确认的B客户的出库单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#getSendOutInvoice(java.lang.String, java.lang.String)
     */
    public List<OutboundInvoiceHeader> getSendOutInvoice(String warehouseid, String aoutid) throws Exception {
        List<OutboundInvoiceHeader> lsResult = null;
        try{
            if(warehouseid != null){//出库单状态 0:开单；1-预配 2-分配 4-完全拣货   7:发货确认 8:作废
                String strSql = "from OutboundInvoiceHeader as outinv  where outinv.outstatus not in('1','7','8') and outinv.warehouseid='" + warehouseid + "' and outinv.outstockid!='" + aoutid + "'";     
                lsResult = m_dao.searchEntities(strSql);
            }
        }catch(Exception er){
            throw new Exception("根据仓库ID获得需要发货确认的B客户的出库单出错：" + er.getMessage());
        }
        return lsResult;
    }
    /**
     * 功能：根据出库单据id客户id获得出库已经完成作业
     * @return List<InoutJobdetail>
     * @throws Exception
     */
	public List<InoutJobdetail> getFinishJobByOutIdCid(String outId) throws Exception {
		List<InoutJobdetail> lsDel = null;
		try {
			String strHql = "from InoutJobdetail as o where o.invoiceid='"+outId+"'";
			lsDel = m_dao.searchEntities(strHql);
		} catch (Exception er) {
			throw new Exception("根据出库单据id客户id获得出库已经完成作业出错：" + er.getMessage());
		}
		return lsDel;
	}
	/**
     * 功能：根据出库单据id客户id获得出库已经完成作业
     * @return List<InoutJobdetail>
     * @throws Exception
     */
	public List<InoutJob> getFinishJobByIdCid(String outId) throws Exception {
		List<InoutJob> lsDel = null;
		try {
			String strHql = "from InoutJob as o where o.boundstockid='"+outId+"'";
			lsDel = m_dao.searchEntities(strHql);
		} catch (Exception er) {
			throw new Exception("根据出库单据id客户id获得出库已经完成作业出错：" + er.getMessage());
		}
		return lsDel;
	}
	 /**
	 * 功能：暂存直接出库时，修改的内容
	 * @param job
	 * @param jobDetail
	 * @param inventory
	 * @throws Exception
	 */
    public String updateInventoryAndJob(String jobID, String jobDetailID,String inventoryID) throws Exception{
    	String strBackMsg = "Y";
    	IJobDao jobDao = new JobDaoImpl(m_dao);
    	IInventoryDao inventoryDao = new InventoryDaoImpl(m_dao);
    	InoutJob job = null;
    	InoutJobdetail jobDetail = null;
    	InventoryStorage inventory = null;
    	if(jobID!=null){
			job = jobDao.getJobById(jobID);
		}
		if(jobDetailID!=null){
			jobDetail = jobDao.getJobDetailByDetailId(jobDetailID);
		}
		if(inventoryID!=null){
			inventory = inventoryDao.getInventoryById(inventoryID); 
		}
    	Session session = m_dao.getSession();
    	Transaction tx = session.beginTransaction();
    	try{
    		if (job != null) {
    			String jobidsString = job.getJobid().intern();
    			synchronized (jobidsString) {
    				if (job.getJobcategory() != null && job.getJobcategory().equals("2")) { // 暂存区作业
        				// 判断作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
        				if (job.getStatus().equals("4") || job.getStatus().equals("5")) {
        					if (strBackMsg.equals("Y")) {
        						strBackMsg = "不能操作已经复合或者已经作废掉的作业！";
        					} else {
        						strBackMsg += "\r不能操作已经复合或者已经作废掉的作业！";
        					}
        				} else {
        	        		job.setStatus("4");
        	        		job.setFinishtime(StrTools.getCurrDateTime(2));
        	        		jobDetail.setIsreview("Y");
        	        		jobDetail.setJobnum(jobDetail.getAssignnum());    //复核成功。修改 作业数量为分配数量
        	        		jobDetail.setReviewTime(StrTools.getCurrDateTime(2));
        	        		session.update(job);
        	        		session.update(jobDetail);
        	        		double pnumResult = inventory.getPnum()-jobDetail.getAssignnum();
        	        		double assignnumResult = inventory.getAssignnum() - jobDetail.getAssignnum();
        	        		if(pnumResult == 0){
        	        			session.delete(inventory);
        	        		}else if(pnumResult>0){
        	        			inventory.setPnum(pnumResult);
        	        			if(assignnumResult<=0){
        	        				inventory.setAssignnum(0);
        	        				inventory.setStatus("0"); //分配数量为0时，设置库存为未分配状态
        	        			}else{
        	        				inventory.setAssignnum(assignnumResult);
        	        			}
        	        			session.update(inventory);
        	        		}else if(pnumResult<0){
        	        			strBackMsg = "库存数量小于作业分配数量！";
        	        		}
        	        		tx.commit();
        				}
        			} else {
        				strBackMsg = "不是暂存区作业！";
        			}
    			}
    		} else {
    			if (strBackMsg.equals("Y")) {
    				strBackMsg = "作业不存在！";

    			} else {
    				strBackMsg += "\r作业不存在！";
    			}
    		}
    		

    	}catch(Exception he){
    		Logger.error("直接出库出错！"+he.getMessage());
    		strBackMsg = "直接出库出错！";
    	}finally
		{
    		m_dao.closeSession(session);
    	} 
    	return strBackMsg; //"1"表示成功
    }
    
    /**
     * 功能：增加出库单
     * @author fanll 2015-3-8
     * @param invoice
     * @param outboundInvoiceDetails
     * @return
     * @throws Exception
     */
    public String addOutBound(OutboundInvoiceHeader outInvoice,List<OutboundInvoiceDetail> outboundInvoiceDetails,List<SaleFormDetail> SaleFormDetaills) throws Exception{
    	String msg = "操作成功";
    	Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //增加出库单
            if(outInvoice!=null){
            	session.save("OutboundInvoiceHeader", outInvoice);	
            }
            //增加出库单详细
            if(outboundInvoiceDetails != null){
            	OutboundInvoiceDetail outDetail = null;	
                for(int i = 0; i < outboundInvoiceDetails.size(); i++){
                	outDetail = outboundInvoiceDetails.get(i);
                    session.save("OutboundInvoiceDetail", outDetail);
                }
            }
            //更改外来出库订单的数量
            if(SaleFormDetaills != null){
            	SaleFormDetail sodetail = null;	
                for(int i = 0; i < SaleFormDetaills.size(); i++){
                	sodetail = SaleFormDetaills.get(i);
                    session.update(sodetail);
                }
            }
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("根据外来出库订单生成出库单据明细出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
        return msg;
    }
    
    /**
     * 功能：取消出库单
     * @author fanll 2015-3-8
     * @param outInvoice
     * @param SaleFormDetaills
     * @return
     * @throws Exception
     */
    public String CancelInvoice(OutboundInvoiceHeader outInvoice,List<SaleFormDetail> SaleFormDetaills) throws Exception{
    	String msg = "操作成功";
    	Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //增加出库单
            if(outInvoice!=null){
            	session.update("OutboundInvoiceHeader", outInvoice);	
            }

            //更改外来出库订单的数量
            if(SaleFormDetaills != null){
            	SaleFormDetail sodetail = null;	
                for(int i = 0; i < SaleFormDetaills.size(); i++){
                	sodetail = SaleFormDetaills.get(i);
                    session.update(sodetail);
                }
            }
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("根据外来出库订单生成出库单据明细出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
        return msg;
    }
    
}
