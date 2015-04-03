package com.wms3.bms.standard.dao.outbound.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.outbound.IPickDao;

/**
 * ����: ���DAO
 * @author hug 2012-10-24
 * @since WMS 3.0
 */
public class PickDaoImpl extends AbstractDao implements IPickDao{
    
    public PickDaoImpl(EntityDAO dao){
        m_dao = dao;
    }
    /**
     * ִ�м����SQL
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IPickDao#executePick(java.lang.String, java.lang.String, java.lang.String)
     */
    public void executePick(String strJobDSQL, String strInvoiceDSQL, String strStorageSQL) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{ 
            session = m_dao.getSession();
            tx = session.beginTransaction(); 
            session.createQuery(strJobDSQL).executeUpdate();
            session.createQuery(strInvoiceDSQL).executeUpdate();
            if(strStorageSQL != null && strStorageSQL.trim().length() > 0){
                session.createQuery(strStorageSQL).executeUpdate(); 
            }
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("ִ�м���޸����ݵ�ʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
    }
    /**
     *  �����ҵ�Ƿ�����ȫ������       true:��ȫ��� false:û�м�����
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IPickDao#isPickJobFinish(java.lang.String)
     */
    public boolean isPickJobFinish(String strJobId) throws Exception {
        try{
            if(strJobId != null){
                String strSql = "select detail.jobdetailid from InoutJobdetail as detail where detail.jobid='" + strJobId + "' and detail.linestatus not in('4','5')";
                List lsResult = m_dao.searchEntities(strSql);
                if(lsResult != null && lsResult.size()>0){
                    return false; //δ������
                }else{
                    return true; //ȫ��������
                }
            }  
        }catch(Exception er){
            throw new Exception("�����ҵ�Ƿ�����ȫ�����ɳ���" + er.getMessage());
        }
        return false;
    }
    /**
     * �����ⵥ��ϸ�Ƿ�����ȫ������ true:��ȫ��� false:û�м�����
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IPickDao#isPickInvoiceDetailFinish(java.lang.String)
     */
    public boolean isPickInvoiceDetailFinish(String strInvoiceDetailId) throws Exception {
        try{
            if(strInvoiceDetailId != null){
                String strSql = "select detail.jobdetailid from InoutJobdetail as detail where detail.invoicedetailid='" + strInvoiceDetailId + "' and detail.linestatus not in('4','5')";
                List lsResult = m_dao.searchEntities(strSql);
                if(lsResult != null && lsResult.size()>0){
                    return false; //δ������
                }else{
                    return true; //ȫ��������
                }
            }  
        }catch(Exception er){
            throw new Exception("�����ⵥ��ϸ�Ƿ�����ȫ�����ɳ���" + er.getMessage());
        }
        return false;
    }
    /**
     * �����ⵥ�Ƿ�����ȫ�ܻ����    true:��ȫ��� false:û�м�����
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IPickDao#isPickInvoiceFinish(java.lang.String)
     */
    public boolean isPickInvoiceFinish(String strInvoiceId) throws Exception {
        try{
            if(strInvoiceId != null){
                String strSql = "select detail.outstockdetailid from OutboundInvoiceDetail as detail where detail.outstockid='" + strInvoiceId + "' and detail.linestatus!='4'";
                List lsResult = m_dao.searchEntities(strSql);
                if(lsResult != null && lsResult.size()>0){
                    return false; //δ������
                }else{
                    return true; //ȫ��������
                }
            }  
        }catch(Exception er){
            throw new Exception("�����ⵥ�Ƿ�����ȫ�ܻ���ɳ���" + er.getMessage());
        }
        return false;
    }

}
