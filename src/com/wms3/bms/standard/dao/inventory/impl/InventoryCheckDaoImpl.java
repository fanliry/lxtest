package com.wms3.bms.standard.dao.inventory.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.inventory.IInventoryCheckDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckRequest;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;


/**
 * 
 * 描述: 库存DAO类
 * @author hug 2012-4-25
 * @since WMS 3.0
 */
public class InventoryCheckDaoImpl extends AbstractDao implements IInventoryCheckDao {

    public InventoryCheckDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }

    /**
	 * 功能:添加盘点申请单
	 * @param checkReq	盘点申请单
	 * @return 
	 * @throws Exception
	 */
	public void addCheckReq(InventoryCheckRequest checkReq) throws Exception {
		
		m_dao.save(checkReq);
	}

	/**
	 * 功能:修改盘点申请单
	 * @param checkReq	盘点申请单
	 * @return 
	 * @throws Exception
	 */
	public void updateCheckReq(InventoryCheckRequest checkReq) throws Exception{
		
		m_dao.update(checkReq);
	}

	/**
	 * 功能:生成盘点任务，修改库存状态，修改盘点申请状态
	 * @param lstask	任务列表
	 * @param lsstorage	库存列表
	 * @param checkReq  盘点申请单
	 * @return 
	 * @throws Exception
	 */
	public void addCheckTasks(List<InventoryCheckTask> lstask, List<InoutJob> lsJobs,List<InoutJobdetail> lsjobdels,List<InventoryStorage> lsstorage, InventoryCheckRequest checkReq) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
        	InventoryStorage storage = null;
			InventoryCheckTask task = null;
            InoutJob inoutJob = null;
            InoutJobdetail jobdetail = null;
            for(int i = 0; i < lstask.size(); i++){
            	
                storage = (InventoryStorage)lsstorage.get(i);
                task = (InventoryCheckTask)lstask.get(i);
                //暂时不添加盘点作业
                /*inoutJob = lsJobs.get(i);
                jobdetail = lsjobdels.get(i);
                 //增加作业及明细
                session.save(inoutJob);
                session.save(jobdetail);*/
                //增加盘点任务
                session.save(task);
                //修改库存状态
                session.update(storage);	
            }
            
            //修改盘点申请状态
            session.update(checkReq);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("生成盘点任务，修改库存状态，修改盘点申请状态时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
		
	}

	/**
	 * 功能:生成盘点结果，修改盘点任务和盘点申请以及库存的状态
	 * @param result	盘点结果
	 * @param task		盘点任务
	 * @param req  		盘点申请单
	 * @param storage 	库存
	 * @return 
	 * @throws Exception
	 */
	public void addCheckResult(InventoryCheckResult result, InventoryCheckTask task, InventoryCheckRequest req, InventoryStorage storage) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
                
            //增加盘点结果
            session.save(result);

            //修改盘点任务状态
            session.update(task);	
            
            //修改盘点申请状态
            session.update(req);
            
            //恢复库存状态为未分配
            session.update(storage);
            
            tx.commit(); 
            
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("生成盘点结果，修改盘点任务和盘点申请以及库存的状态时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}

	/**
	 * 功能:关闭盘点申请,恢复库存状态为未分配
	 * @param req  			盘点申请单
	 * @param lsStorages  	库存列表
	 * @return 
	 * @throws Exception
	 */
	public void closeReq(InventoryCheckRequest req, List lsStorages) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
        	InventoryStorage storage = null;
            
            for(int i = 0; i < lsStorages.size(); i++){
            	
                storage = (InventoryStorage)lsStorages.get(i);
                //修改库存状态
                session.update(storage);	
            }
            
            //修改盘点申请状态
            session.update(req);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("关闭盘点申请,恢复库存状态为未分配时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}

	@Override
	public void addCheckResult(InventoryCheckResult result,
			InventoryCheckTask task, InventoryCheckRequest req)throws Exception{
		Session session = null;
	    Transaction tx = null;
	    
	    try{
	        session = m_dao.getSession();
	        tx = session.beginTransaction();
	            
	        //增加盘点结果
	        session.save(result);
	
	        //修改盘点任务状态
	        session.update(task);	
	        
	        //修改盘点申请状态
	        session.update(req);
	        
	        tx.commit(); 
	        
	    }catch(Exception er){
	        if(tx != null){
	            tx.rollback();
	        }
	        throw new  Exception("生成盘点结果，修改盘点任务和盘点申请以及库存的状态时候出错："+er.getMessage());
	    }finally{
	        m_dao.closeSession(session);
	    }
		
	}
}
