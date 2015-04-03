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
 * ����: ��ⵥ���ݿ����DAO��
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class InboundJobDaoImpl extends AbstractDao implements IInboundJobDao{
    
    /** ���ݿ������DAO���� */
    public InboundJobDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    /**
	 * ����:������ҵ����ҵ��ϸ��Ϣ���������,�����޸Ļ�λ״̬Ϊ����λ2
	 * @param cargospace	��λ
	 * @param job			��ҵ
	 * @param lsJobDetail	��ҵ��ϸ���б�
	 * @param lsStorage		�����б�
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
                InventoryStorage storage = null;	//���
                InoutJobdetail jobdetail = null;	//��ҵ��ϸ
                
                for(int i = 0; i < lsStorage.size(); i++){
                	
                    storage = (InventoryStorage)lsStorage.get(i);
                    //���ӿ��
                    session.save(storage);
                    
                    jobdetail = (InoutJobdetail)lsJobDetail.get(i);
                    jobdetail.setInventoryid(storage.getInventoryid());	//���ID
                    //������ҵ��ϸ
                    session.save("InoutJobdetail", jobdetail);	
                }
            }
            
            //������ҵ
            session.save("InoutJob", job);			
            
            //�޸Ļ�λ״̬
            session.update(cargospace);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("������ҵ����ҵ��ϸ��Ϣ���������,�����޸Ļ�λ״̬Ϊ����λ2ʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
		
	}

	/**
	 * ����:������ҵid��ѯ��ҵ
	 * @param jobid			��ҵ��
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
	 * ����:�޸���ҵ
	 * @param job			��ҵ
	 * @return 
	 * @throws Exception
	 */
	public void updateJob(InoutJob job) throws Exception {
		
		m_dao.update("InoutJob", job);
	}

	/**
	 * ����:������ҵ��ϸ�Ų�ѯ��ҵ��ϸ
	 * @param jobdetailid			��ҵ��ϸ��
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
	 * ����:�޸���ҵ��ϸ
	 * @param jobdetail			��ҵ��ϸ
	 * @return 
	 * @throws Exception
	 */
	public void updateJobDetail(InoutJobdetail jobdetail) throws Exception {
		
		m_dao.update("InoutJobdetail", jobdetail);
	}

	/**
	 * ����:������ҵ,ɾ�����,�޸Ļ�λ״̬
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsStorage		����б�
	 * @return 
	 * @throws Exception
	 */
	public void cancelJob(InoutJob job, BaseCargospace cargospace, List lsStorage) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //�޸���ҵ
            session.update("InoutJob", job);
            
            //�޸Ļ�λ״̬
            session.update(cargospace);
            
            if(lsStorage != null){
            	
                InventoryStorage storage = null;	//���
                for(int i = 0; i < lsStorage.size(); i++){
                	
                    storage = (InventoryStorage)lsStorage.get(i);
                    //���ӿ��
                    session.save(storage);
                }
            }
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("������ҵ,ɾ�����,�޸Ļ�λ״̬ʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}

	/**
	 * ����:�ֶ������ҵ����ӿ��,�޸Ļ�λ״̬Ϊ����λ
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsJobDetail	��ҵ��ϸ���б�
	 * @param lsStorage		�����б�
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
                InventoryStorage storage = null;	//���
                InoutJobdetail jobdetail = null;	//��ҵ��ϸ
                
                for(int i = 0; i < lsStorage.size(); i++){
                	
                    storage = (InventoryStorage)lsStorage.get(i);
                    //���ӿ��
                    session.save(storage);
                    
                    jobdetail = (InoutJobdetail)lsjobdetail.get(i);
                    jobdetail.setInventoryid(storage.getInventoryid());	//���ID
                    jobdetail.setLinestatus("2");		//״̬0:�½� 2:���
                    //�޸���ҵ��ϸ
                    session.update("InoutJobdetail", jobdetail);	
                }
            }
            
            //�޸���ҵ
            session.update("InoutJob", job);			
            
            //�޸Ļ�λ״̬
            session.update(cargospace);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�ֶ������ҵ����ӿ��,�޸Ļ�λ״̬Ϊ����λʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
    

}
