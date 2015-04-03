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
 * ����: ���ⵥ���ݿ����DAO��
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class OutboundJobDaoImpl extends AbstractDao implements IOutboundJobDao{
    
    /** ���ݿ������DAO���� */
    public OutboundJobDaoImpl(EntityDAO dao){
        this.m_dao = dao;
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
	 * ����:���浽���ݿ�,������ҵ���ָ�������״̬,�޸Ļ�λ״̬Ϊ�ջ�λ
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
            
            /*//�޸Ŀ�����״̬
            if(lsStorage != null){
            	
                InventoryStorage storage = null;	//���
                for(int i = 0; i < lsStorage.size(); i++){
                	
                    storage = (InventoryStorage)lsStorage.get(i);
                    storage.setStatus("0");	//״̬  0:δ���䣬1���ѷ��� 3:���� 4:��Ʒ������5���̵�
                    
                    session.update(storage);
                }
            }*/
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("���浽���ݿ�,������ҵ���ָ�������״̬,�޸Ļ�λ״̬Ϊ�ջ�λʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
	/**
	 * ����:���ϳ�����ҵ
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsStorage		����б�
	 * @return 
	 * @throws Exception
	 */
	public void ZFJob(InoutJob job, BaseCargospace cargospace, List lsStorage,StringBuilder detailBuilder) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //�޸���ҵ
            session.update("InoutJob", job);
            
            //�޸Ļ�λ״̬
            session.update(cargospace);
            
            //�޸Ŀ��
            if(lsStorage!=null){
            	InventoryStorage storage = null;	//���
                for(int i = 0; i < lsStorage.size(); i++){
                    storage = (InventoryStorage)lsStorage.get(i);
                    storage.setStatus("0");	//״̬  0:δ���䣬1���ѷ��� 3:���� 4:��Ʒ������5���̵�
                    session.update(storage);
                }
            }
            
            //�޸ĵ�����ϸ��������
            if(!detailBuilder.toString().equals("")){
            	session.createQuery(detailBuilder.toString()).executeUpdate();
            }
            
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("���浽���ݿ�,������ҵ���ָ�������״̬,�޸Ļ�λ״̬Ϊ�ջ�λʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
	/**
	 * ����:�����ݴ������ҵ
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsStorage		����б�
	 * @return 
	 * @throws Exception
	 */
	public void ZCJob(InoutJob job, BaseCargospace cargospace, InventoryStorage lsStorage,StringBuilder detailBuilder) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //�޸���ҵ
            session.update("InoutJob", job);
            
            /*//�޸Ļ�λ״̬
             if(cargospace!=null){
                 session.update(cargospace);
             }*/
            
            //�޸Ŀ��
            if(lsStorage!=null){
            	session.update(lsStorage);
            }
            //�޸ĵ�����ϸ��������
            session.createQuery(detailBuilder.toString()).executeUpdate();
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("���浽���ݿ�,������ҵ���ָ�������״̬,�޸Ļ�λ״̬Ϊ�ջ�λʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}

	/**
	 * ����:�ֶ������ҵ��ɾ�����,�޸Ļ�λ״̬Ϊ�ջ�λ
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsJobDetail	��ҵ��ϸ���б�
	 * @param lsStorage		�����б�
	 * @return 
	 * @throws Exception
	 */
	public void finishJob(InoutJob job,ScheduleTask task, BaseCargospace cargospace, List lsjobdetail, List lsStorage,List<OutboundInvoiceDetail> outBoundls) throws Exception {
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //ɾ�����
            if(lsStorage != null){
                InventoryStorage storage = null;	//���
                for(int i = 0; i < lsStorage.size(); i++){
                    storage = (InventoryStorage)lsStorage.get(i);
                    session.delete(storage);
                }
            }
            //�޸���ҵ��ϸ
            if(lsjobdetail != null){
                InoutJobdetail jobdetail = null;	//��ҵ��ϸ
                for(int i = 0; i < lsjobdetail.size(); i++){
                    jobdetail = (InoutJobdetail)lsjobdetail.get(i);               
                    jobdetail.setLinestatus("4");		//״̬0:�½� 4:���   	                                  	
                    session.update("InoutJobdetail", jobdetail);	
                }
            }
            //�޸���ҵ
            session.update("InoutJob", job);	
            
            //�޸ĵ�������
            if(task!=null){
            	session.update("ScheduleTask", task);
            }
            //�޸Ļ�λ״̬
            session.update(cargospace);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�ֶ������ҵ��ɾ�����,�޸Ļ�λ״̬Ϊ�ջ�λʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
	/**
	 * ����:�ֶ������ҵ�����¿��,�޸Ļ�λ״̬Ϊ�ջ�λ
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsJobDetail	��ҵ��ϸ���б�
	 * @param lsStorage		�����б�
	 * @return 
	 * @throws Exception
	 */
	public void finishJobupdate(InoutJob job, BaseCargospace cargospace, List lsjobdetail, List lsStorage,List<OutboundInvoiceDetail> outBoundls) throws Exception {
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //ɾ�����
            if(lsStorage != null){
                InventoryStorage storage = null;	//���
                
                for(int i = 0; i < lsStorage.size(); i++){
                	
                    storage = (InventoryStorage)lsStorage.get(i);
                    
                    session.update(storage);
                }
            }
            
            //�޸���ҵ��ϸ
            if(lsjobdetail != null){
                InoutJobdetail jobdetail = null;	//��ҵ��ϸ
                
                for(int i = 0; i < lsjobdetail.size(); i++){
                    
                    jobdetail = (InoutJobdetail)lsjobdetail.get(i);               
                    //jobdetail.setInventoryid("");		//���ID
                    jobdetail.setLinestatus("4");		//״̬0:�½� 4:���   	                                  	
                    session.update("InoutJobdetail", jobdetail);	
                }
            }
            //�޸ĳ��ⵥ����ϸ
           /* if (outBoundls!=null) {
				OutboundInvoiceDetail outBoundDel = null; //���ⵥ��ϸ
				for (int i = 0; i < outBoundls.size(); i++) {
					outBoundDel = outBoundls.get(i);
					outBoundDel.setPicknum(outBoundDel.getAssignnum());
					session.update("OutboundInvoiceDetail",outBoundDel);
				}	
			}*/
            //�޸���ҵ
            session.update("InoutJob", job);			
            
            //�޸Ļ�λ״̬
            session.update(cargospace);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�ֶ������ҵ��ɾ�����,�޸Ļ�λ״̬Ϊ�ջ�λʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
	/**
	 * ����:�ֶ������ҵ����ӿ��,�޸Ļ�λ״̬���ջ�λ
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsJobDetail	��ҵ��ϸ���б�
	 * @param lsStorage		�����б�
	 * @return 
	 * @throws Exception
	 */
	public void finishJobZC(InoutJob job, BaseCargospace cargospace, List lsjobdetail, List  lsStorage1,List lsStorage2,List<OutboundInvoiceDetail> outBoundls) throws Exception{
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //ɾ�����
            if(lsStorage1 != null){
                InventoryStorage storage = null;	//���
                
                for(int i = 0; i < lsStorage1.size(); i++){
                	
                    storage = (InventoryStorage)lsStorage1.get(i);
                    
                    session.delete(storage);
                }
            }
            
            //�޸���ҵ��ϸ
            if(lsjobdetail != null){
                InoutJobdetail jobdetail = null;	//��ҵ��ϸ
                
                for(int i = 0; i < lsjobdetail.size(); i++){
                    
                    jobdetail = (InoutJobdetail)lsjobdetail.get(i);               
                    //jobdetail.setInventoryid("");		//���ID
                    jobdetail.setLinestatus("4");		//״̬0:�½� 4:���   	                                  	
                    session.update("InoutJobdetail", jobdetail);	
                }
            }
            //�޸ĳ��ⵥ����ϸ
           /* if (outBoundls!=null) {
				OutboundInvoiceDetail outBoundDel = null; //���ⵥ��ϸ
				for (int i = 0; i < outBoundls.size(); i++) {
					outBoundDel = outBoundls.get(i);
					outBoundDel.setPicknum(outBoundDel.getAssignnum());
					session.update("OutboundInvoiceDetail",outBoundDel);
				}	
			}*/
            //�޸���ҵ
            session.update("InoutJob", job);			
            
            //�޸Ļ�λ״̬
            session.update(cargospace);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�ֶ������ҵ��ɾ�����,�޸Ļ�λ״̬Ϊ�ջ�λʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
	public static void main(String arg[]){
		StringBuilder invoiceBudSQL= new StringBuilder();
		System.out.println(!invoiceBudSQL.toString().equals(""));
	}
	
	/**
	 * ����:�ֶ������ҵ�����¿��,�޸Ļ�λ״̬Ϊ�ջ�λ,�޸ĵ���״̬
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsJobDetail	��ҵ��ϸ���б�
	 * @param lsStorage		�����б�
	 * @param lstask		���ȵ��б�
	 * @return 
	 * @throws Exception
	 */
	public void finishJobupdatez(InoutJob job, BaseCargospace cargospace, List lsjobdetail,ScheduleTask task, List lsStorage,List<OutboundInvoiceDetail> outBoundls) throws Exception {
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //ɾ�����
            if(lsStorage != null){
                InventoryStorage storage = null;	//���
                
                for(int i = 0; i < lsStorage.size(); i++){
                	
                    storage = (InventoryStorage)lsStorage.get(i);
                    
                    session.update(storage);
                }
            }
            
            //�޸���ҵ��ϸ
            if(lsjobdetail != null){
                InoutJobdetail jobdetail = null;	//��ҵ��ϸ
                
                for(int i = 0; i < lsjobdetail.size(); i++){
                    
                    jobdetail = (InoutJobdetail)lsjobdetail.get(i);               
                    //jobdetail.setInventoryid("");		//���ID
                    jobdetail.setLinestatus("4");		//״̬0:�½� 4:���   	                                  	
                    session.update("InoutJobdetail", jobdetail);	
                }
            }
            //�޸ĳ��ⵥ����ϸ
           /* if (outBoundls!=null) {
				OutboundInvoiceDetail outBoundDel = null; //���ⵥ��ϸ
				for (int i = 0; i < outBoundls.size(); i++) {
					outBoundDel = outBoundls.get(i);
					outBoundDel.setPicknum(outBoundDel.getAssignnum());
					session.update("OutboundInvoiceDetail",outBoundDel);
				}	
			}*/
            //�޸���ҵ
            session.update("InoutJob", job);			
            
            //�޸Ļ�λ״̬
            session.update(cargospace);
            
            //�޸ĵ���״̬

            session.update(task);	

            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�ֶ������ҵ��ɾ�����,�޸ĵ���״̬,�޸Ļ�λ״̬Ϊ�ջ�λʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
}
