package com.wms3.bms.standard.dao.inbound.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.inbound.IInboundJobDao;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 
 * 描述: 入库单数据库操作DAO类
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class InboundJobDaoImpl extends AbstractDao implements IInboundJobDao{
    
    /** 数据库基本的DAO操作 */
    public InboundJobDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    /**
	 * 功能:新增作业和作业详细信息，新增库存,并且修改货位状态为满货位2
	 * @param cargospace	货位
	 * @param job			作业
	 * @param lsJobDetail	作业详细的列表
	 * @param lsStorage		库存的列表
	 * @return 
	 * @throws Exception
	 */
	public void addRKWHJob(BaseCargospace cargospace, InoutJob job, List lsJobDetail, List lsStorage) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            if(lsStorage != null){
                InventoryStorage storage = null;	//库存
                InoutJobdetail jobdetail = null;	//作业详细
                
                for(int i = 0; i < lsStorage.size(); i++){
                	
                    storage = (InventoryStorage)lsStorage.get(i);
                    //增加库存
                    session.save(storage);
                    
                    jobdetail = (InoutJobdetail)lsJobDetail.get(i);
                    jobdetail.setInventoryid(storage.getInventoryid());	//库存ID
                    //增加作业详细
                    session.save("InoutJobdetail", jobdetail);	
                }
            }
            
            //增加作业
            session.save("InoutJob", job);			
            
            //修改货位状态
            session.update(cargospace);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("新增作业和作业详细信息，新增库存,并且修改货位状态为满货位2时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
		
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
	 * 功能:修改作业
	 * @param job			作业
	 * @return 
	 * @throws Exception
	 */
	public void updateJob(InoutJob job) throws Exception {
		
		m_dao.update("InoutJob", job);
	}

	/**
	 * 功能:根据作业明细号查询作业明细
	 * @param jobdetailid			作业明细号
	 * @return 
	 * @throws Exception
	 */
	public InoutJobdetail getJobDetailBydetailid(String jobdetailid) throws Exception {
		
		if(jobdetailid != null){
			
			String strSql = " from InoutJobdetail as t where t.jobdetailid='" + jobdetailid + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (InoutJobdetail)ls.get(0);
			}
		}
		return null;
	}

	/**
	 * 功能:修改作业明细
	 * @param jobdetail			作业明细
	 * @return 
	 * @throws Exception
	 */
	public void updateJobDetail(InoutJobdetail jobdetail) throws Exception {
		
		m_dao.update("InoutJobdetail", jobdetail);
	}

	/**
	 * 功能:作废作业,删除库存,修改货位状态
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
            
            if(lsStorage != null){
            	
                InventoryStorage storage = null;	//库存
                for(int i = 0; i < lsStorage.size(); i++){
                	
                    storage = (InventoryStorage)lsStorage.get(i);
                    //增加库存
                    session.save(storage);
                }
            }
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("作废作业,删除库存,修改货位状态时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}

	/**
	 * 功能:手动完成作业，添加库存,修改货位状态为满货位
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsJobDetail	作业详细的列表
	 * @param lsStorage		库存的列表
	 * @return 
	 * @throws Exception
	 */
	public void finishJob(InoutJob job, BaseCargospace cargospace, List lsjobdetail, List lsStorage) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            if(lsStorage != null){
                InventoryStorage storage = null;	//库存
                InoutJobdetail jobdetail = null;	//作业详细
                
                for(int i = 0; i < lsStorage.size(); i++){
                	
                    storage = (InventoryStorage)lsStorage.get(i);
                    //增加库存
                    session.save(storage);
                    
                    jobdetail = (InoutJobdetail)lsjobdetail.get(i);
                    jobdetail.setInventoryid(storage.getInventoryid());	//库存ID
                    jobdetail.setLinestatus("2");		//状态0:新建 2:完成
                    //修改作业详细
                    session.update("InoutJobdetail", jobdetail);	
                }
            }
            
            //修改作业
            session.update("InoutJob", job);			
            
            //修改货位状态
            session.update(cargospace);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("手动完成作业，添加库存,修改货位状态为满货位时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
    

}
