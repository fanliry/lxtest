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
 * ����: ���DAO��
 * @author hug 2012-4-25
 * @since WMS 3.0
 */
public class InventoryCheckDaoImpl extends AbstractDao implements IInventoryCheckDao {

    public InventoryCheckDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }

    /**
	 * ����:����̵����뵥
	 * @param checkReq	�̵����뵥
	 * @return 
	 * @throws Exception
	 */
	public void addCheckReq(InventoryCheckRequest checkReq) throws Exception {
		
		m_dao.save(checkReq);
	}

	/**
	 * ����:�޸��̵����뵥
	 * @param checkReq	�̵����뵥
	 * @return 
	 * @throws Exception
	 */
	public void updateCheckReq(InventoryCheckRequest checkReq) throws Exception{
		
		m_dao.update(checkReq);
	}

	/**
	 * ����:�����̵������޸Ŀ��״̬���޸��̵�����״̬
	 * @param lstask	�����б�
	 * @param lsstorage	����б�
	 * @param checkReq  �̵����뵥
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
                //��ʱ������̵���ҵ
                /*inoutJob = lsJobs.get(i);
                jobdetail = lsjobdels.get(i);
                 //������ҵ����ϸ
                session.save(inoutJob);
                session.save(jobdetail);*/
                //�����̵�����
                session.save(task);
                //�޸Ŀ��״̬
                session.update(storage);	
            }
            
            //�޸��̵�����״̬
            session.update(checkReq);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�����̵������޸Ŀ��״̬���޸��̵�����״̬ʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
		
	}

	/**
	 * ����:�����̵������޸��̵�������̵������Լ�����״̬
	 * @param result	�̵���
	 * @param task		�̵�����
	 * @param req  		�̵����뵥
	 * @param storage 	���
	 * @return 
	 * @throws Exception
	 */
	public void addCheckResult(InventoryCheckResult result, InventoryCheckTask task, InventoryCheckRequest req, InventoryStorage storage) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
                
            //�����̵���
            session.save(result);

            //�޸��̵�����״̬
            session.update(task);	
            
            //�޸��̵�����״̬
            session.update(req);
            
            //�ָ����״̬Ϊδ����
            session.update(storage);
            
            tx.commit(); 
            
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�����̵������޸��̵�������̵������Լ�����״̬ʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}

	/**
	 * ����:�ر��̵�����,�ָ����״̬Ϊδ����
	 * @param req  			�̵����뵥
	 * @param lsStorages  	����б�
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
                //�޸Ŀ��״̬
                session.update(storage);	
            }
            
            //�޸��̵�����״̬
            session.update(req);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�ر��̵�����,�ָ����״̬Ϊδ����ʱ�����"+er.getMessage());
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
	            
	        //�����̵���
	        session.save(result);
	
	        //�޸��̵�����״̬
	        session.update(task);	
	        
	        //�޸��̵�����״̬
	        session.update(req);
	        
	        tx.commit(); 
	        
	    }catch(Exception er){
	        if(tx != null){
	            tx.rollback();
	        }
	        throw new  Exception("�����̵������޸��̵�������̵������Լ�����״̬ʱ�����"+er.getMessage());
	    }finally{
	        m_dao.closeSession(session);
	    }
		
	}
}
