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
 * 描述: 出库分配DAO类
 * @author hug 2012-9-21
 * @since WMS 3.0
 */
public class AssginDaoImpl extends AbstractDao implements IAssginDao{
    
    public AssginDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    /**
     * 根据库存ID获得库存
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
            throw new Exception("根据库存ID获得库存出错：" + er.getMessage());
        }
        return null;
    }
    /**
     *  根据货位ID和托盘条码查询库存
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
            throw new Exception("根据货位ID获得库存出错:" + er.getMessage());
        }
        return null;
    }
    
    public void addAssginJob(List<InoutJob> lsJob, List<ScheduleTask> lsTask, List<InoutJobdetail> lsJobDetail, List<String> lsStorageSQL, List<String> lsInvoiceSQL, String strSpaceSQL,OutboundInvoiceHeader outboundInvoice) throws Exception {

        StatelessSession session = null; //大批量操作时使用
        Transaction tx = null;
        Connection conn = null;
        try{
            conn = m_dao.getConnection();//从连接池获得连接
            session = m_dao.getSessionFactory().openStatelessSession(conn);
            tx = session.beginTransaction();
            //增加作业
            InoutJob job = null;
            for(int i = 0; i < lsJob.size(); i++){
                job = lsJob.get(i);
                session.insert(job);
            } 
            
            //增加调度
            if(lsTask != null){
                ScheduleTask task = null;
                for(int i = 0; i < lsTask.size(); i++){
                    task = lsTask.get(i);
                    session.insert("ScheduleTask",task);
                }
            }
            
            //增加作业详细
            InoutJobdetail jobDetail = null;
            for(int i = 0; i < lsJobDetail.size(); i++){
                jobDetail = lsJobDetail.get(i);
                session.insert(jobDetail);
            } 
            
            //修改库存的SQL
            String strStorageSQL = null;
            for(int i = 0; i < lsStorageSQL.size(); i++){
                strStorageSQL = lsStorageSQL.get(i);
                session.createQuery(strStorageSQL).executeUpdate();
            } 
            
            //修改出库单详细的SQL
            String strInvoiceSQL = null;
            for(int i = 0; i < lsInvoiceSQL.size(); i++){
                strInvoiceSQL = lsInvoiceSQL.get(i);
                if(strInvoiceSQL!=null && strInvoiceSQL.length()>0){
                	session.createQuery(strInvoiceSQL).executeUpdate();
                }
            } 
            
            //修改出库单据状态
            if(outboundInvoice!=null){
            	session.update("OutboundInvoiceHeader",outboundInvoice);
            }
            //修改货位状态
            if(strSpaceSQL != null){
                session.createQuery(strSpaceSQL).executeUpdate();
            }
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("生成出库作业出错："+er.getMessage());
        }finally{
            if(session != null)
            {
                session.close();
            }
            if(conn != null){
                conn.close(); //关闭连接
            }
        }       
    }
    

}
