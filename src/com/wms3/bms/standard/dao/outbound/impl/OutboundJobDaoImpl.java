package com.wms3.bms.standard.dao.outbound.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.job.ITaskDao;
import com.wms3.bms.standard.dao.job.impl.TaskDaoImpl;
import com.wms3.bms.standard.dao.outbound.IOutboundJobDao;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * 
 * 描述: 出库单数据库操作DAO类
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class OutboundJobDaoImpl extends AbstractDao implements IOutboundJobDao{
    
    /** 数据库基本的DAO操作 */
    public OutboundJobDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    
	/**
	 * 功能:根据作业id查询作业
	 * @param jobid			作业号
	 * @return 
	 * @throws Exception
	 */
	public InoutJob getJobByJobid(String jobid) throws Exception {
		
		if(jobid != null){
		
			String strSql = " from InoutJob as t where t.jobid='" + jobid + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (InoutJob)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 功能:保存到数据库,作废作业，恢复库存分配状态,修改货位状态为空货位
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsStorage		库存列表
	 * @return 
	 * @throws Exception
	 */
	public void cancelJob(InoutJob job, BaseCargospace cargospace, List lsStorage) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //修改作业
            session.update("InoutJob", job);
            
            //修改货位状态
            session.update(cargospace);
            
            /*//修改库存分配状态
            if(lsStorage != null){
            	
                InventoryStorage storage = null;	//库存
                for(int i = 0; i < lsStorage.size(); i++){
                	
                    storage = (InventoryStorage)lsStorage.get(i);
                    storage.setStatus("0");	//状态  0:未分配，1：已分配 3:调整 4:货品调整；5：盘点
                    
                    session.update(storage);
                }
            }*/
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("保存到数据库,作废作业，恢复库存分配状态,修改货位状态为空货位时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
	/**
	 * 功能:作废出库作业
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsStorage		库存列表
	 * @return 
	 * @throws Exception
	 */
	public void ZFJob(InoutJob job, BaseCargospace cargospace, List lsStorage,StringBuilder detailBuilder) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //修改作业
            session.update("InoutJob", job);
            
            //修改货位状态
            session.update(cargospace);
            
            //修改库存
            if(lsStorage!=null){
            	InventoryStorage storage = null;	//库存
                for(int i = 0; i < lsStorage.size(); i++){
                    storage = (InventoryStorage)lsStorage.get(i);
                    storage.setStatus("0");	//状态  0:未分配，1：已分配 3:调整 4:货品调整；5：盘点
                    session.update(storage);
                }
            }
            
            //修改单据明细分配数量
            if(!detailBuilder.toString().equals("")){
            	session.createQuery(detailBuilder.toString()).executeUpdate();
            }
            
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("保存到数据库,作废作业，恢复库存分配状态,修改货位状态为空货位时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
	/**
	 * 功能:作废暂存出库作业
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsStorage		库存列表
	 * @return 
	 * @throws Exception
	 */
	public void ZCJob(InoutJob job, BaseCargospace cargospace, InventoryStorage lsStorage,StringBuilder detailBuilder) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //修改作业
            session.update("InoutJob", job);
            
            /*//修改货位状态
             if(cargospace!=null){
                 session.update(cargospace);
             }*/
            
            //修改库存
            if(lsStorage!=null){
            	session.update(lsStorage);
            }
            //修改单据明细分配数量
            session.createQuery(detailBuilder.toString()).executeUpdate();
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("保存到数据库,作废作业，恢复库存分配状态,修改货位状态为空货位时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}

	/**
	 * 功能:手动完成作业，删除库存,修改货位状态为空货位
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsJobDetail	作业详细的列表
	 * @param lsStorage		库存的列表
	 * @return 
	 * @throws Exception
	 */
	public void finishJob(InoutJob job,ScheduleTask task, BaseCargospace cargospace, List lsjobdetail, List lsStorage,List<OutboundInvoiceDetail> outBoundls) throws Exception {
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //删除库存
            if(lsStorage != null){
                InventoryStorage storage = null;	//库存
                for(int i = 0; i < lsStorage.size(); i++){
                    storage = (InventoryStorage)lsStorage.get(i);
                    session.delete(storage);
                }
            }
            //修改作业详细
            if(lsjobdetail != null){
                InoutJobdetail jobdetail = null;	//作业详细
                for(int i = 0; i < lsjobdetail.size(); i++){
                    jobdetail = (InoutJobdetail)lsjobdetail.get(i);               
                    jobdetail.setLinestatus("4");		//状态0:新建 4:完成   	                                  	
                    session.update("InoutJobdetail", jobdetail);	
                }
            }
            //修改作业
            session.update("InoutJob", job);	
            
            //修改调度任务
            if(task!=null){
            	session.update("ScheduleTask", task);
            }
            //修改货位状态
            session.update(cargospace);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("手动完成作业，删除库存,修改货位状态为空货位时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
	/**
	 * 功能:手动完成作业，更新库存,修改货位状态为空货位
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsJobDetail	作业详细的列表
	 * @param lsStorage		库存的列表
	 * @return 
	 * @throws Exception
	 */
	public void finishJobupdate(InoutJob job, BaseCargospace cargospace, List lsjobdetail, List lsStorage,List<OutboundInvoiceDetail> outBoundls) throws Exception {
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //删除库存
            if(lsStorage != null){
                InventoryStorage storage = null;	//库存
                
                for(int i = 0; i < lsStorage.size(); i++){
                	
                    storage = (InventoryStorage)lsStorage.get(i);
                    
                    session.update(storage);
                }
            }
            
            //修改作业详细
            if(lsjobdetail != null){
                InoutJobdetail jobdetail = null;	//作业详细
                
                for(int i = 0; i < lsjobdetail.size(); i++){
                    
                    jobdetail = (InoutJobdetail)lsjobdetail.get(i);               
                    //jobdetail.setInventoryid("");		//库存ID
                    jobdetail.setLinestatus("4");		//状态0:新建 4:完成   	                                  	
                    session.update("InoutJobdetail", jobdetail);	
                }
            }
            //修改出库单据明细
           /* if (outBoundls!=null) {
				OutboundInvoiceDetail outBoundDel = null; //出库单详细
				for (int i = 0; i < outBoundls.size(); i++) {
					outBoundDel = outBoundls.get(i);
					outBoundDel.setPicknum(outBoundDel.getAssignnum());
					session.update("OutboundInvoiceDetail",outBoundDel);
				}	
			}*/
            //修改作业
            session.update("InoutJob", job);			
            
            //修改货位状态
            session.update(cargospace);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("手动完成作业，删除库存,修改货位状态为空货位时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
	/**
	 * 功能:手动完成作业，添加库存,修改货位状态满空货位
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsJobDetail	作业详细的列表
	 * @param lsStorage		库存的列表
	 * @return 
	 * @throws Exception
	 */
	public void finishJobZC(InoutJob job, BaseCargospace cargospace, List lsjobdetail, List  lsStorage1,List lsStorage2,List<OutboundInvoiceDetail> outBoundls) throws Exception{
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //删除库存
            if(lsStorage1 != null){
                InventoryStorage storage = null;	//库存
                
                for(int i = 0; i < lsStorage1.size(); i++){
                	
                    storage = (InventoryStorage)lsStorage1.get(i);
                    
                    session.delete(storage);
                }
            }
            
            //修改作业详细
            if(lsjobdetail != null){
                InoutJobdetail jobdetail = null;	//作业详细
                
                for(int i = 0; i < lsjobdetail.size(); i++){
                    
                    jobdetail = (InoutJobdetail)lsjobdetail.get(i);               
                    //jobdetail.setInventoryid("");		//库存ID
                    jobdetail.setLinestatus("4");		//状态0:新建 4:完成   	                                  	
                    session.update("InoutJobdetail", jobdetail);	
                }
            }
            //修改出库单据明细
           /* if (outBoundls!=null) {
				OutboundInvoiceDetail outBoundDel = null; //出库单详细
				for (int i = 0; i < outBoundls.size(); i++) {
					outBoundDel = outBoundls.get(i);
					outBoundDel.setPicknum(outBoundDel.getAssignnum());
					session.update("OutboundInvoiceDetail",outBoundDel);
				}	
			}*/
            //修改作业
            session.update("InoutJob", job);			
            
            //修改货位状态
            session.update(cargospace);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("手动完成作业，删除库存,修改货位状态为空货位时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
	public static void main(String arg[]){
		StringBuilder invoiceBudSQL= new StringBuilder();
		System.out.println(!invoiceBudSQL.toString().equals(""));
	}
	
	/**
	 * 功能:手动完成作业，更新库存,修改货位状态为空货位,修改调度状态
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsJobDetail	作业详细的列表
	 * @param lsStorage		库存的列表
	 * @param lstask		调度的列表
	 * @return 
	 * @throws Exception
	 */
	public void finishJobupdatez(InoutJob job, BaseCargospace cargospace, List lsjobdetail,ScheduleTask task, List lsStorage,List<OutboundInvoiceDetail> outBoundls) throws Exception {
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //删除库存
            if(lsStorage != null){
                InventoryStorage storage = null;	//库存
                
                for(int i = 0; i < lsStorage.size(); i++){
                	
                    storage = (InventoryStorage)lsStorage.get(i);
                    
                    session.update(storage);
                }
            }
            
            //修改作业详细
            if(lsjobdetail != null){
                InoutJobdetail jobdetail = null;	//作业详细
                
                for(int i = 0; i < lsjobdetail.size(); i++){
                    
                    jobdetail = (InoutJobdetail)lsjobdetail.get(i);               
                    //jobdetail.setInventoryid("");		//库存ID
                    jobdetail.setLinestatus("4");		//状态0:新建 4:完成   	                                  	
                    session.update("InoutJobdetail", jobdetail);	
                }
            }
            //修改出库单据明细
           /* if (outBoundls!=null) {
				OutboundInvoiceDetail outBoundDel = null; //出库单详细
				for (int i = 0; i < outBoundls.size(); i++) {
					outBoundDel = outBoundls.get(i);
					outBoundDel.setPicknum(outBoundDel.getAssignnum());
					session.update("OutboundInvoiceDetail",outBoundDel);
				}	
			}*/
            //修改作业
            session.update("InoutJob", job);			
            
            //修改货位状态
            session.update(cargospace);
            
            //修改调度状态

            session.update(task);	

            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("手动完成作业，删除库存,修改调度状态,修改货位状态为空货位时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
}
