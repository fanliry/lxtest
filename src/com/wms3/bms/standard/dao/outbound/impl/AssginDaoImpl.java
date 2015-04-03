package com.wms3.bms.standard.dao.outbound.impl;

import java.sql.Connection;
import java.util.List;

import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.outbound.IAssginDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * ����: �������DAO��
 * @author hug 2012-9-21
 * @since WMS 3.0
 */
public class AssginDaoImpl extends AbstractDao implements IAssginDao{
    
    public AssginDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    /**
     * ���ݿ��ID��ÿ��
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IAssginDao#getInventoryStorageById(java.lang.String)
     */
    public InventoryStorage getInventoryStorageById(String strInventoryId) throws Exception {
        try{
            if(strInventoryId != null){
                String strSQL = "from InventoryStorage as sto where sto.inventoryid='" + strInventoryId + "'";
                List ls = m_dao.searchEntities(strSQL);
                if(ls != null && ls.size() > 0){
                    return (InventoryStorage)ls.get(0);
                }
            }
        }catch(Exception er){
            throw new Exception("���ݿ��ID��ÿ�����" + er.getMessage());
        }
        return null;
    }
    /**
     *  ���ݻ�λID�����������ѯ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IAssginDao#getInventoryStorageBySpaceId(java.lang.String, java.lang.String)
     */
    public List<InventoryStorage> getInventoryStorageBySpaceId(String strSpaceId, String strTrayCode) throws Exception {
        try{
            if(strSpaceId != null){
                StringBuilder strSQL = new StringBuilder();
                strSQL.append("select sto from InventoryStorage as sto, BaseCargospace as space where sto.cargoSpaceId='" + strSpaceId + "' and sto.cargoSpaceId=space.cargoSpaceId and space.csstatus='2' ");
                if(strTrayCode != null && strTrayCode.trim().length() > 0){
                    strSQL.append(" and sto.traycode='").append(strTrayCode).append("'");
                }
                List ls = m_dao.searchEntities(strSQL.toString());
                return ls;
            }
        }catch(Exception er){
            throw new Exception("���ݻ�λID��ÿ�����:" + er.getMessage());
        }
        return null;
    }
    
    public void addAssginJob(List<InoutJob> lsJob, List<ScheduleTask> lsTask, List<InoutJobdetail> lsJobDetail, List<String> lsStorageSQL, List<String> lsInvoiceSQL, String strSpaceSQL,OutboundInvoiceHeader outboundInvoice) throws Exception {

        StatelessSession session = null; //����������ʱʹ��
        Transaction tx = null;
        Connection conn = null;
        try{
            conn = m_dao.getConnection();//�����ӳػ������
            session = m_dao.getSessionFactory().openStatelessSession(conn);
            tx = session.beginTransaction();
            //������ҵ
            InoutJob job = null;
            for(int i = 0; i < lsJob.size(); i++){
                job = lsJob.get(i);
                session.insert(job);
            } 
            
            //���ӵ���
            if(lsTask != null){
                ScheduleTask task = null;
                for(int i = 0; i < lsTask.size(); i++){
                    task = lsTask.get(i);
                    session.insert("ScheduleTask",task);
                }
            }
            
            //������ҵ��ϸ
            InoutJobdetail jobDetail = null;
            for(int i = 0; i < lsJobDetail.size(); i++){
                jobDetail = lsJobDetail.get(i);
                session.insert(jobDetail);
            } 
            
            //�޸Ŀ���SQL
            String strStorageSQL = null;
            for(int i = 0; i < lsStorageSQL.size(); i++){
                strStorageSQL = lsStorageSQL.get(i);
                session.createQuery(strStorageSQL).executeUpdate();
            } 
            
            //�޸ĳ��ⵥ��ϸ��SQL
            String strInvoiceSQL = null;
            for(int i = 0; i < lsInvoiceSQL.size(); i++){
                strInvoiceSQL = lsInvoiceSQL.get(i);
                if(strInvoiceSQL!=null && strInvoiceSQL.length()>0){
                	session.createQuery(strInvoiceSQL).executeUpdate();
                }
            } 
            
            //�޸ĳ��ⵥ��״̬
            if(outboundInvoice!=null){
            	session.update("OutboundInvoiceHeader",outboundInvoice);
            }
            //�޸Ļ�λ״̬
            if(strSpaceSQL != null){
                session.createQuery(strSpaceSQL).executeUpdate();
            }
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("���ɳ�����ҵ����"+er.getMessage());
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
    

}
