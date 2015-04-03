package com.wms3.bms.standard.dao.outbound.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.outbound.ISendDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * ����: ����ȷ��DAO��
 * @author hug 2012-10-9
 * @since WMS 3.0
 */
public class SendDaoImpl extends AbstractDao implements ISendDao {
    
    public SendDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    /**
     * A�ͻ��Ƶ��ݴ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ISendDao#sendAmoveZ(com.wms3.bms.standard.entity.inventory.InventoryStorage, java.lang.String, java.lang.String)
     */
    public void sendAmoveZ(InventoryStorage inventoryStorage, String strJobSQL, String strAInvoiceSQL) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //�����ݴ�
            if(inventoryStorage != null){
                session.save(inventoryStorage);          
             }
            //�޸���ҵ��ϸ������
            session.createQuery(strJobSQL).executeUpdate();
            //�޸�A������ϸ�ķ�������
            session.createQuery(strAInvoiceSQL).executeUpdate();
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("A�ͻ����ݴ����������"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }             
    }
    /**
     * ���ܣ�A�ͻ��ƶ����ݴ���
     * @author hug 2012-5-21 
     * @param inventoryStorage  �ݴ���  
     * @param strJobSQL         ɾ����ҵ����ҵ��ϸ
     * @param strAInvoiceSQL    �޸�A������ϸ�ķ�������
     * @throws Exception
     */
    public void sendAmoveZD(InventoryStorage inventoryStorage, InoutJob job,InoutJobdetail detail, String strAInvoiceSQL) throws Exception{
    	Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //�����ݴ�
            if(inventoryStorage != null){
                session.save(inventoryStorage);          
            }
            //ɾ����ҵ��ϸ������
            session.delete(job);
            session.delete(detail);
            
            //�޸�A������ϸ�ķ�������
            session.createQuery(strAInvoiceSQL).executeUpdate();
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("A�ͻ����ݴ����������"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
    }
    /**
     * A�ͻ��Ƶ��ݴ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ISendDao#sendAmoveZ(com.wms3.bms.standard.entity.inventory.InventoryStorage, java.lang.String, java.lang.String)
     */
    public void sendAmoveZ(InventoryStorage inventoryStorage, String strJobSQL, String strAInvoiceSQL,String strCrossSQL) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //�����ݴ�
            if(inventoryStorage != null){
                session.save(inventoryStorage);          
             }
            //�޸���ҵ��ϸ������
            session.createQuery(strJobSQL).executeUpdate();
            //�޸�A������ϸ�ķ�������
            session.createQuery(strAInvoiceSQL).executeUpdate();
            //���ĸ�����Ϣ
            if(strCrossSQL!=null && !strCrossSQL.equals("")){
            	session.createQuery(strCrossSQL).executeUpdate();
            }
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("A�ͻ����ݴ����������"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }             
    }
    /**
     * A�ͻ��ƶ���B�ͻ�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ISendDao#sendAmoveB(java.lang.String, com.wms3.bms.standard.entity.job.InoutJob, com.wms3.bms.standard.entity.job.InoutJobdetail, java.lang.String, java.lang.String)
     */
    public void sendAmoveB(String ajobDetail, InoutJob bjob, InoutJobdetail bjobDetail, String strAInvoiceSQL, String strBInvoiceSQL) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //������ҵ����ҵ��ϸ
            if(bjob != null && bjobDetail != null){
                session.save("InoutJob", bjob);  
                session.save("InoutJobdetail", bjobDetail);
             } 
            //�޸���ҵ��ϸ������
            session.createQuery(ajobDetail).executeUpdate();
            //�޸�A������ϸ�ķ�������
            session.createQuery(strAInvoiceSQL).executeUpdate();
            //�޸�B������ϸ�ķ�������
            session.createQuery(strBInvoiceSQL).executeUpdate();
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("A�ͻ��ƶ���B�ͻ��������"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }             
    }
    /**
     * �ݴ����ƶ���A�ͻ�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ISendDao#sendZmoveA(java.lang.String, com.wms3.bms.standard.entity.job.InoutJob, com.wms3.bms.standard.entity.job.InoutJobdetail, java.lang.String)
     */
    public void sendZmoveA(String strInventorySql, InoutJob ajob, InoutJobdetail ajobDetail, String strInvoiceSQL) throws Exception {

        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            if(strInventorySql != null){
                session.createQuery(strInventorySql).executeUpdate();
            }
            if(ajob != null && ajobDetail != null){
                session.save("InoutJob", ajob);  
                session.save("InoutJobdetail", ajobDetail);
             }
            if(strInvoiceSQL != null){
                session.createQuery(strInvoiceSQL).executeUpdate();
            }
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�ݴ����ƶ���A�ͻ��������"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }         
    }
    /**
     * ���ⵥ����ȷ��
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ISendDao#outInvoiceFHQR(java.lang.String, java.util.List)
     */
    public void outInvoiceFHQR(String strInvoiceSQL, List<String> lsInvoiceDetailSQL) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            if(strInvoiceSQL != null){
                session.createQuery(strInvoiceSQL).executeUpdate();
            }
            if(lsInvoiceDetailSQL != null && lsInvoiceDetailSQL.size() > 0){
                for(int i = 0; i < lsInvoiceDetailSQL.size(); i++){
                    session.createQuery(lsInvoiceDetailSQL.get(i)).executeUpdate();    
                }
            }
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("���ⵥ����ȷ�ϲ�������"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }             
    }
    /**
     * ���ⵥ��ϸ����ȷ�ϲ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ISendDao#outInvoiceDetailFHQR(java.lang.String)
     */
    public void outInvoiceDetailFHQR(String strSQL) throws Exception {       
        m_dao.updateSql(strSQL);
    }
    /**
     * ���ݳ��ⵥ��ѯû�з���ȷ�ϵĳ��ⵥ����ϸ��Ϣ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ISendDao#getNoFHQRDetail(java.lang.String)
     */
    public List getNoFHQRDetail(String strOutId) throws Exception
    {
        if(strOutId != null)
        {
            try
            {
                String strSql = "select inDetail.outstockdetailid from OutboundInvoiceDetail as inDetail where inDetail.outstockid='" + strOutId + "' and inDetail.linestatus not in('7','8')";
                List ls = m_dao.searchEntities(strSql);
                return ls;
            }catch(Exception er) 
            {
                throw new Exception("���ݳ��ⵥ��ѯû����ȷ�ϵĵ�����ϸ��Ϣ����" + er.getMessage());
            }
        }
        return null;
    }
    /**
     * ���ݲֿ�ID����ݴ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ISendDao#getZCInventory(java.lang.String)
     */
    public List getZCInventory(String warehouseid) throws Exception {
        if(warehouseid != null)
        {
            try
            {
                String strSql = "from InventoryStorage as storage where storage.whAreaId in (select bw.whAreaId from BaseWharea as bw where bw.warehouseid='" + warehouseid + "' and bw.whAreaType='9')  and storage.warehouseid='" + warehouseid + "'";
                List ls = m_dao.searchEntities(strSql);
                return ls;
            }catch(Exception er) 
            {
                throw new Exception("���ݲֿ�ID����ݴ�������" + er.getMessage());
            }
        }
        return null;
    }
    /**
     * ���ݲֿ�ID����ݴ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ISendDao#getZCInventory(java.lang.String)
     */
    public List getZCInventorybyTray(String warehouseid,String zcwhareaid,String traycode) throws Exception {
        if(warehouseid != null)
        {
            try
            {
                String strSql = "from InventoryStorage as storage where storage.whAreaId='"+zcwhareaid+"'  and storage.warehouseid='" + warehouseid + "'";
                if(traycode!=null && !traycode.equals("")){
                	strSql += " and storage.traycode='" + traycode + "'";
                }
                List ls = m_dao.searchEntities(strSql);
                return ls;
            }catch(Exception er) 
            {
                throw new Exception("���ݲֿ�ID����ݴ�������" + er.getMessage());
            }
        }
        return null;
    }
    /**
     * ��������������������õ����ѳ������ҵ����ҵ��ϸ(�������ҵ)
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ISendDao#getJobAndJobDetailByCode(java.lang.String, java.lang.String, java.lang.String)
     */
    public List getJobAndJobDetailByCode(String invoiceid, String traycode, String boxcode) throws Exception {
        if(invoiceid != null){
            try{
                String strSQL = "from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.status='4' and job.boundstockid='" + invoiceid + "'";
                if(traycode != null && traycode.trim().length() > 0){
                    strSQL = strSQL + " and job.traycode='" + traycode + "'";
                }
                if(boxcode != null && boxcode.trim().length() > 0){
                    strSQL = strSQL + " and jobdetail.boxcode='" + boxcode + "'";
                }
                List ls = m_dao.searchEntities(strSQL);
                return ls;
            }catch(Exception er) 
            {
                throw new Exception("��������������������õ����ѳ������ҵ����ҵ��ϸ(�������ҵ)����" + er.getMessage());
            }
        }
        return null;
    }

}
