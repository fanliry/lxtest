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
 * 描述: 发货确认DAO类
 * @author hug 2012-10-9
 * @since WMS 3.0
 */
public class SendDaoImpl extends AbstractDao implements ISendDao {
    
    public SendDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    /**
     * A客户移到暂存区
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ISendDao#sendAmoveZ(com.wms3.bms.standard.entity.inventory.InventoryStorage, java.lang.String, java.lang.String)
     */
    public void sendAmoveZ(InventoryStorage inventoryStorage, String strJobSQL, String strAInvoiceSQL) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //增加暂存
            if(inventoryStorage != null){
                session.save(inventoryStorage);          
             }
            //修改作业详细的数量
            session.createQuery(strJobSQL).executeUpdate();
            //修改A单据详细的分配数量
            session.createQuery(strAInvoiceSQL).executeUpdate();
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("A客户到暂存区保存出错！"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }             
    }
    /**
     * 功能：A客户移动到暂存区
     * @author hug 2012-5-21 
     * @param inventoryStorage  暂存区  
     * @param strJobSQL         删除作业及作业明细
     * @param strAInvoiceSQL    修改A单据详细的分配数量
     * @throws Exception
     */
    public void sendAmoveZD(InventoryStorage inventoryStorage, InoutJob job,InoutJobdetail detail, String strAInvoiceSQL) throws Exception{
    	Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //增加暂存
            if(inventoryStorage != null){
                session.save(inventoryStorage);          
            }
            //删除作业详细的数量
            session.delete(job);
            session.delete(detail);
            
            //修改A单据详细的分配数量
            session.createQuery(strAInvoiceSQL).executeUpdate();
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("A客户到暂存区保存出错！"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
    }
    /**
     * A客户移到暂存区
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ISendDao#sendAmoveZ(com.wms3.bms.standard.entity.inventory.InventoryStorage, java.lang.String, java.lang.String)
     */
    public void sendAmoveZ(InventoryStorage inventoryStorage, String strJobSQL, String strAInvoiceSQL,String strCrossSQL) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //增加暂存
            if(inventoryStorage != null){
                session.save(inventoryStorage);          
             }
            //修改作业详细的数量
            session.createQuery(strJobSQL).executeUpdate();
            //修改A单据详细的分配数量
            session.createQuery(strAInvoiceSQL).executeUpdate();
            //更改复核信息
            if(strCrossSQL!=null && !strCrossSQL.equals("")){
            	session.createQuery(strCrossSQL).executeUpdate();
            }
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("A客户到暂存区保存出错！"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }             
    }
    /**
     * A客户移动到B客户
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ISendDao#sendAmoveB(java.lang.String, com.wms3.bms.standard.entity.job.InoutJob, com.wms3.bms.standard.entity.job.InoutJobdetail, java.lang.String, java.lang.String)
     */
    public void sendAmoveB(String ajobDetail, InoutJob bjob, InoutJobdetail bjobDetail, String strAInvoiceSQL, String strBInvoiceSQL) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //增加作业及作业详细
            if(bjob != null && bjobDetail != null){
                session.save("InoutJob", bjob);  
                session.save("InoutJobdetail", bjobDetail);
             } 
            //修改作业详细的数量
            session.createQuery(ajobDetail).executeUpdate();
            //修改A单据详细的分配数量
            session.createQuery(strAInvoiceSQL).executeUpdate();
            //修改B单据详细的分配数量
            session.createQuery(strBInvoiceSQL).executeUpdate();
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("A客户移动到B客户保存出错！"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }             
    }
    /**
     * 暂存区移动到A客户
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
            throw new  Exception("暂存区移动到A客户保存出错！"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }         
    }
    /**
     * 出库单发货确认
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
            throw new  Exception("出库单发货确认操作出错！"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }             
    }
    /**
     * 出库单详细发货确认操作
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ISendDao#outInvoiceDetailFHQR(java.lang.String)
     */
    public void outInvoiceDetailFHQR(String strSQL) throws Exception {       
        m_dao.updateSql(strSQL);
    }
    /**
     * 根据出库单查询没有发货确认的出库单据详细信息
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
                throw new Exception("根据出库单查询没发货确认的单据详细信息出错：" + er.getMessage());
            }
        }
        return null;
    }
    /**
     * 根据仓库ID获得暂存区
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
                throw new Exception("根据仓库ID获得暂存区出错：" + er.getMessage());
            }
        }
        return null;
    }
    /**
     * 根据仓库ID获得暂存区
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
                throw new Exception("根据仓库ID获得暂存区出错：" + er.getMessage());
            }
        }
        return null;
    }
    /**
     * 根据托盘条码或箱条码获得单据已出库的作业及作业详细(已完成作业)
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
                throw new Exception("根据托盘条码或箱条码获得单据已出库的作业及作业详细(已完成作业)出错：" + er.getMessage());
            }
        }
        return null;
    }

}
