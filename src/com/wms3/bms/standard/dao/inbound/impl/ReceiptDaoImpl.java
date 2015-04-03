package com.wms3.bms.standard.dao.inbound.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.inbound.IReceiptDao;
import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;

/**
 * 
 * ����: �ջ����ݿ����DAO��
 * @author hug 2012-8-27
 * @since WMS 3.0
 */
public class ReceiptDaoImpl extends AbstractDao implements IReceiptDao{

    public ReceiptDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    } 
    /**
     * �����ջ����׼�¼
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IReceiptDao#addReceiptTrans(com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction, java.lang.String)
     */
    public void addReceiptTrans(InboundReceiptTransaction trans, String strSql) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //�����ջ����׼�¼
            if(trans != null){
                session.save("InboundReceiptTransaction", trans);           
            }
            session.createQuery(strSql).executeUpdate(); 
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�����ջ����׼�¼����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        } 
    }
    
    /**
     * �����ջ���ID����ջ������б�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IReceiptDao#getReceiptTransactionToIvoiceId(java.lang.String)
     */
    public List<InboundReceiptTransaction> getReceiptTransactionToIvoiceId(String strInReceiptId) throws Exception {
        try{
            if(strInReceiptId != null){
                String strSql = "from InboundReceiptTransaction as rets where rets.reinvoiceid='" + strInReceiptId + "' order by rets.transreceiptid";
                List<InboundReceiptTransaction> lsDetail = m_dao.searchEntities(strSql); 
                return lsDetail;
            }
        }catch(Exception er){
            throw new Exception("�����ջ���ID["+strInReceiptId+"]����ջ������б����" + er.getMessage());
        }
        return null;
    }
    /**
     * ����������
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IReceiptDao#bindTray(java.lang.String, java.lang.String)
     */
    public void bindTray(String jobid, String traycode) throws Exception {
        String strSQL = "update InoutJob as injob set injob.traycode='" + traycode + "' where injob.jobid='" + jobid + "'";
        m_dao.updateSql(strSQL);
    }
    /**
     *  ȡ���ջ���SQL
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IReceiptDao#cancelReceiptSQL(java.lang.String, java.lang.String, java.lang.String)
     */
    public void cancelReceiptSQL(String strDetailSql, String strInvoiceSql, String strTransSql) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //1:�޸��ջ�����ϸ���ջ�������״̬����Ϊ�����ջ�״̬
            session.createQuery(strDetailSql).executeUpdate();
            //2:�޸��ջ�����״̬ ,��Ϊ�����ջ�״̬1
            session.createQuery(strInvoiceSql).executeUpdate();
            //3:�޸��ջ���¼��״̬����Ϊȡ���ջ�״̬5
            session.createQuery(strTransSql).executeUpdate(); 
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("ִ��ȡ���ջ���SQL����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        } 
    }

}
