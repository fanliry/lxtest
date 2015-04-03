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
 * 描述: 收货数据库操作DAO类
 * @author hug 2012-8-27
 * @since WMS 3.0
 */
public class ReceiptDaoImpl extends AbstractDao implements IReceiptDao{

    public ReceiptDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    } 
    /**
     * 增加收货交易记录
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IReceiptDao#addReceiptTrans(com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction, java.lang.String)
     */
    public void addReceiptTrans(InboundReceiptTransaction trans, String strSql) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //增加收货交易记录
            if(trans != null){
                session.save("InboundReceiptTransaction", trans);           
            }
            session.createQuery(strSql).executeUpdate(); 
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("增加收货交易记录出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        } 
    }
    
    /**
     * 根据收货单ID获得收货任务列表
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
            throw new Exception("根据收货单ID["+strInReceiptId+"]获得收货任务列表出错：" + er.getMessage());
        }
        return null;
    }
    /**
     * 绑定托盘条码
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IReceiptDao#bindTray(java.lang.String, java.lang.String)
     */
    public void bindTray(String jobid, String traycode) throws Exception {
        String strSQL = "update InoutJob as injob set injob.traycode='" + traycode + "' where injob.jobid='" + jobid + "'";
        m_dao.updateSql(strSQL);
    }
    /**
     *  取消收货的SQL
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IReceiptDao#cancelReceiptSQL(java.lang.String, java.lang.String, java.lang.String)
     */
    public void cancelReceiptSQL(String strDetailSql, String strInvoiceSql, String strTransSql) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //1:修改收货单详细的收货数量，状态，改为部分收货状态
            session.createQuery(strDetailSql).executeUpdate();
            //2:修改收货单的状态 ,改为部分收货状态1
            session.createQuery(strInvoiceSql).executeUpdate();
            //3:修改收货记录的状态，改为取消收货状态5
            session.createQuery(strTransSql).executeUpdate(); 
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("执行取消收货的SQL出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        } 
    }

}
