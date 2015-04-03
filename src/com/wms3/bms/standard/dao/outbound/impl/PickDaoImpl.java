package com.wms3.bms.standard.dao.outbound.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.outbound.IPickDao;

/**
 * 描述: 拣货DAO
 * @author hug 2012-10-24
 * @since WMS 3.0
 */
public class PickDaoImpl extends AbstractDao implements IPickDao{
    
    public PickDaoImpl(EntityDAO dao){
        m_dao = dao;
    }
    /**
     * 执行拣货的SQL
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
            throw new  Exception("执行拣货修改数据的时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
    }
    /**
     *  检测作业是否已完全拣货完成       true:完全拣货 false:没有拣货完成
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IPickDao#isPickJobFinish(java.lang.String)
     */
    public boolean isPickJobFinish(String strJobId) throws Exception {
        try{
            if(strJobId != null){
                String strSql = "select detail.jobdetailid from InoutJobdetail as detail where detail.jobid='" + strJobId + "' and detail.linestatus not in('4','5')";
                List lsResult = m_dao.searchEntities(strSql);
                if(lsResult != null && lsResult.size()>0){
                    return false; //未拣货完成
                }else{
                    return true; //全部拣货完成
                }
            }  
        }catch(Exception er){
            throw new Exception("检测作业是否已完全拣货完成出错：" + er.getMessage());
        }
        return false;
    }
    /**
     * 检测出库单详细是否已完全拣货完成 true:完全拣货 false:没有拣货完成
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IPickDao#isPickInvoiceDetailFinish(java.lang.String)
     */
    public boolean isPickInvoiceDetailFinish(String strInvoiceDetailId) throws Exception {
        try{
            if(strInvoiceDetailId != null){
                String strSql = "select detail.jobdetailid from InoutJobdetail as detail where detail.invoicedetailid='" + strInvoiceDetailId + "' and detail.linestatus not in('4','5')";
                List lsResult = m_dao.searchEntities(strSql);
                if(lsResult != null && lsResult.size()>0){
                    return false; //未拣货完成
                }else{
                    return true; //全部拣货完成
                }
            }  
        }catch(Exception er){
            throw new Exception("检测出库单详细是否已完全拣货完成出错：" + er.getMessage());
        }
        return false;
    }
    /**
     * 检测出库单是否已完全拒货完成    true:完全拣货 false:没有拣货完成
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IPickDao#isPickInvoiceFinish(java.lang.String)
     */
    public boolean isPickInvoiceFinish(String strInvoiceId) throws Exception {
        try{
            if(strInvoiceId != null){
                String strSql = "select detail.outstockdetailid from OutboundInvoiceDetail as detail where detail.outstockid='" + strInvoiceId + "' and detail.linestatus!='4'";
                List lsResult = m_dao.searchEntities(strSql);
                if(lsResult != null && lsResult.size()>0){
                    return false; //未拣货完成
                }else{
                    return true; //全部拣货完成
                }
            }  
        }catch(Exception er){
            throw new Exception("检测出库单是否已完全拒货完成出错：" + er.getMessage());
        }
        return false;
    }

}
