package com.wms3.bms.standard.dao.inbound.impl;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.inbound.IPutawayDao;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BasePackageMastermesg;
import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * ����: �ϼ����ݿ����DAO��
 * @author hug 2012-8-30
 * @since WMS 3.0
 */
public class PutawayDaoImpl extends AbstractDao implements IPutawayDao{
    public PutawayDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    /**
     * �����ջ���¼ID����ջ���¼
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IPutawayDao#getTransReceiptById(java.lang.String)
     */
    public InboundReceiptTransaction getTransReceiptById(String strTransId) throws Exception {
        InboundReceiptTransaction trans = null;
        try{
            if(strTransId != null){
                String strSql = "from InboundReceiptTransaction as retrans where retrans.transreceiptid='" + strTransId + "'";
                List ls = m_dao.searchEntities(strSql);
                if(ls != null && ls.size() > 0){
                    trans = (InboundReceiptTransaction)ls.get(0);
                }
            }
        }catch(Exception er){
            throw new Exception("�����ջ���¼ID����ջ���¼����" + er.getMessage());
        }
        return trans;
    }
 
    /**
     * ���ӿ��
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IPutawayDao#addStorage(java.util.List, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void addStorage(List<InventoryStorage> lsStorage, String strJobId, String strJobStatus, String strSpaceId, String strSpaceStatus) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //���ӿ��
            if(lsStorage != null){
                InventoryStorage storage = null;//���
                for(int i = 0; i < lsStorage.size(); i++){
                    storage = lsStorage.get(i);
                    session.save(storage);
                }
            }
            //�޸���ҵ״̬
            String strJobSql = "update InoutJob as injob set injob.status='" + strJobStatus + "' where injob.jobid='" + strJobId + "'";
            session.createQuery(strJobSql).executeUpdate(); 
            //�޸Ļ�λ״̬
            if(strSpaceId != null && strSpaceId.trim().length() > 0){
                String strSpaceSql = "update BaseCargospace as spc set spc.csstatus='" + strSpaceStatus + "' where spc.cargoSpaceId='" + strSpaceId + "'";
                session.createQuery(strSpaceSql).executeUpdate(); 
            }
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�ϼܵ�������"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
    }
    public void addStorage(BaseCargospace space, List<InventoryStorage> lsStorage, String strJobId, String strJobStatus, String strSpaceId, String strSpaceStatus, String strOldSpaceId, String strOldSpaceStatus) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();

            //���ӿ��
            if(lsStorage != null){
                InventoryStorage storage = null;//���
                for(int i = 0; i < lsStorage.size(); i++){
                    storage = lsStorage.get(i);
                    storage.setCargoSpaceId(space.getCargoSpaceId()); //��λID
                    storage.setCargoAlleyId(space.getCargoAlleyId()); //���ID
                    //storage(space.getCargoAreaId());//����ID
                    storage.setWhAreaId(space.getWhAreaId());         //����
                    storage.setWarehouseid(space.getWarehouseid());   //�ֿ�ID
                    session.save(storage);
                }
            }
            //�޸���ҵ״̬
            String strJobSql = "update InoutJob as injob set injob.status='" + strJobStatus + "'," +
                    " injob.tcargoWhareaId='"+ (space.getWhAreaId() == null ? "" : space.getWhAreaId()) +"',"+
                    "injob.tcargoSpaceId='"+ (space.getCargoSpaceId() == null ? "" : space.getCargoSpaceId()) +"'," +
                    "injob.tcargoAlleyId='"+ (space.getCargoAlleyId() == null ? "" : space.getCargoAlleyId()) +"'  where injob.jobid='" + strJobId + "'";
            session.createQuery(strJobSql).executeUpdate(); 
            //�޸Ļ�λ״̬
            if(strSpaceId != null && strSpaceId.trim().length() > 0){
                String strSpaceSql = "update BaseCargospace as spc set spc.csstatus='" + strSpaceStatus + "' where spc.cargoSpaceId='" + strSpaceId + "'";
                session.createQuery(strSpaceSql).executeUpdate(); 
            }
            //�޸�ԭ��λ״̬
            if(strOldSpaceId != null && strOldSpaceId.trim().length() > 0){
                String strOldSpaceSql = "update BaseCargospace as spc set spc.csstatus='" + strOldSpaceStatus + "', spc.haspalletnum=spc.haspalletnum-1  where spc.cargoSpaceId='" + strOldSpaceId + "'";
                session.createQuery(strOldSpaceSql).executeUpdate(); 
            }
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�ϼܵ�������"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
    }
    /**
     * �����ջ���ID����ϼܵ���ҵ��Ϣ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IPutawayDao#getPutawayJob(java.lang.String)
     */
    public List getPutawayJob(String strInvoiceId) throws Exception {
        List lsResult = null;
        try{
            if(strInvoiceId != null){
                String strSql = "from InoutJob as injob, InoutJobdetail as detail  where injob.jobid=detail.jobid and injob.status!='5' and injob.inOutType='1' and  detail.sinvoiceid='" + strInvoiceId + "'";     
                lsResult = m_dao.searchEntities(strSql);
            }
        }catch(Exception er){
            throw new Exception("�����ջ���ID����ϼܵ���ҵ��Ϣ����" + er.getMessage());
        }
        return lsResult;
    }
    
    /**
     * �����ϼ���ҵ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IPutawayDao#addPutawayJob(java.util.List)
     */
    public void addPutawayJob(List<Object[]> lsObj) throws Exception {
        StatelessSession session = null; //����������ʱʹ��
        Transaction tx = null;
        Connection conn = null;
        try{
            conn = m_dao.getConnection();//�����ӳػ������
            session = m_dao.getSessionFactory().openStatelessSession(conn);
            tx = session.beginTransaction();
            //������ҵ
            Object[] obj = null;
            for(int i = 0; i < lsObj.size(); i++){
                obj = lsObj.get(i);
                
                //�����ϼ���ҵ
                InoutJob job = (InoutJob)obj[0]; //��ҵ
                session.insert("InoutJob", job);
                
                //���ӵ�������
                ScheduleTask task = (ScheduleTask)obj[1];       //��������
                if(task != null){
                    session.insert(task);
                }
                
                //������ҵ��ϸ
                List<InoutJobdetail> lsJobDetail = (List<InoutJobdetail>)obj[2];//��ҵ��ϸ
                InoutJobdetail jobDetail = null;//�ϼ���ҵ��ϸ
                for(int k = 0; k < lsJobDetail.size(); k++){
                    jobDetail = lsJobDetail.get(k);
                    session.insert("InoutJobdetail", jobDetail);
                }
                
                //����Ҫ�޸ĵ��ջ���¼
                List<String> lsTrans = (List<String>)obj[3];    //Ҫ�޸ĵ��ջ���¼
                String strTransSql = null;
                for(int k = 0; k < lsTrans.size(); k++){
                    strTransSql = lsTrans.get(k);
                    session.createQuery(strTransSql).executeUpdate();
                }       
            } 
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�����ϼ���ҵ����"+er.getMessage());
        }finally{
            if(session != null)
            {
                session.close();
            }
            if(conn != null){
                conn.close(); //�ر�����
            }
        }    
    }
    public void addPutawayJob(List<Object[]> lsObj, String strSql) throws Exception {
        StatelessSession session = null; //����������ʱʹ��
        Transaction tx = null;
        Connection conn = null;
        try{
            conn = m_dao.getConnection();//�����ӳػ������
            session = m_dao.getSessionFactory().openStatelessSession(conn);
            tx = session.beginTransaction();
            //������ҵ
            Object[] obj = null;
            for(int i = 0; i < lsObj.size(); i++){
                obj = lsObj.get(i);
                
                //�����ϼ���ҵ
                InoutJob job = (InoutJob)obj[0]; //��ҵ
                session.insert("InoutJob", job);
                
                //���ӵ�������
                ScheduleTask task = (ScheduleTask)obj[1];       //��������
                if(task != null){
                    session.insert(task);
                }
                
                //������ҵ��ϸ
                List<InoutJobdetail> lsJobDetail = (List<InoutJobdetail>)obj[2];//��ҵ��ϸ
                InoutJobdetail jobDetail = null;//�ϼ���ҵ��ϸ
                for(int k = 0; k < lsJobDetail.size(); k++){
                    jobDetail = lsJobDetail.get(k);
                    session.insert("InoutJobdetail", jobDetail);
                }    
            } 
            //�޸��ջ���¼
            session.createQuery(strSql).executeUpdate();
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�����ϼ���ҵ����"+er.getMessage());
        }finally{
            if(session != null)
            {
                session.close();
            }
            if(conn != null){
                conn.close(); //�ر�����
            }
        }    
    }
    
    /**
     * �����ϼ���ҵ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IPutawayDao#addPutawayJob(com.wms3.bms.standard.entity.job.InoutJob, java.util.List, java.util.List, com.wms3.bms.standard.entity.schedule.ScheduleTask)
     */
    public void addPutawayJob(InoutJob job, List<InoutJobdetail> lsJobDetail, List<String> lsTransSql, ScheduleTask task) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //�����ϼ���ҵ
            session.save("InoutJob", job);
            //�����ϼ���ҵ��ϸ
            if(lsJobDetail != null){
                InoutJobdetail jobDetail = null;//�ϼ���ҵ��ϸ
                for(int i = 0; i < lsJobDetail.size(); i++){
                    jobDetail = lsJobDetail.get(i);
                    session.save("InoutJobdetail", jobDetail);
                }
            }
            //�޸��ջ���¼���ϼ�����
            if(lsTransSql != null){
                String strSql = null;//�޸��ջ���¼��SQL���
                for(int i = 0; i < lsTransSql.size(); i++){
                    strSql = lsTransSql.get(i);
                    session.createQuery(strSql).executeUpdate(); 
                }
            }
            //���ӵ�������
            if(task != null){
                session.save(task);           
            }
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�����ϼ���ҵ����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
    }
    /**
     * ��øð�װһ�е�����
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IPutawayDao#getPackageTrayNum(java.lang.String)
     */
    public int getPackageTrayNum(String packid) throws Exception {
        int iPL = 0;
        try{
            String strSql = "from BasePackageMastermesg as pm where pm.packid='" + packid + "' and pm.pkgunit='PL' and pm.inused='Y'";
            List ls = m_dao.searchEntities(strSql);
            if(ls != null && ls.size()>0){
                BasePackageMastermesg packMaster = (BasePackageMastermesg)ls.get(0);
                iPL = packMaster.getQty();
            }
        }catch(Exception er){
            throw new Exception("��øð�װһ�е���������" + er.getMessage());
        }
        return iPL;
    }
    /**
     * ȡ���ϼ�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IPutawayDao#cancelPutaway(java.lang.String, java.util.List, java.util.List, java.util.List, java.lang.String)
     */
    public void cancelPutaway(String strJobSql, List<String> lsTrasnsSQL, List<String> lsInvoiceSQL, List<String> lsDetailSQL, String strSpaceSQL) throws Exception 
    {   StatelessSession session = null; //����������ʱʹ��
        Transaction tx = null;
        Connection conn = null;
        try{
            conn = m_dao.getConnection();//�����ӳػ������
            session = m_dao.getSessionFactory().openStatelessSession(conn);
            tx = session.beginTransaction();
            //1: �޸���ҵ״̬
            session.createQuery(strJobSql).executeUpdate();
            
            //2���޸��ջ���¼��״̬�����ϼ�����
            for(int i = 0; i < lsTrasnsSQL.size(); i++){
                String strTransSql = lsTrasnsSQL.get(i);   
                session.createQuery(strTransSql).executeUpdate();
            }
            //3���޸��ջ�����״̬
            for(int i = 0; i < lsInvoiceSQL.size(); i++){
                String strInvoiceSql = lsInvoiceSQL.get(i); 
                session.createQuery(strInvoiceSql).executeUpdate();
            }
            //4���޸��ջ�����ϸ��״̬
            for(int i = 0; i < lsDetailSQL.size(); i++){
                String strDetailSql = lsDetailSQL.get(i); 
                session.createQuery(strDetailSql).executeUpdate();
            }        
            //5: �޸��ջ���¼
            session.createQuery(strSpaceSQL).executeUpdate();
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�����ϼ���ҵ����"+er.getMessage());
        }finally{
            if(session != null)
            {
                session.close();
            }
            if(conn != null){
                conn.close(); //�ر�����
            }
        }    
    }
    /**
     * ����:���ݿ����ж��Ƿ����ɵ�������
     * @author yao 2012-11-22
     * @param whAreaId  ����ID
     * @return
     * @throws Exception
     */
    public boolean isCreateTask(String whAreaId) throws Exception{
    	
    	List ls = null;
    	Boolean flag = false;
		try
		{
			if(whAreaId!=null && whAreaId.length()>0){
				String strHql = "From BaseWharea as t where t.istask='Y' and t.whAreaId='"+whAreaId+"'";
				ls = m_dao.searchEntities(strHql);
			}
			if(ls!=null&&ls.size()>=1){
				flag = true;
			}
		}catch(Exception er)
		{
			throw new Exception("��ѯ�����б����:" + er.getMessage());
		}
		return flag;
    }

}
