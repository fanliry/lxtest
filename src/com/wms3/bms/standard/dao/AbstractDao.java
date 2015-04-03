package com.wms3.bms.standard.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * 描述: DAO父抽象类
 * @author hug 2012-9-21
 * @since WMS 3.0
 */
public abstract class AbstractDao{
    /** 数据库基本的DAO操作 */
    public EntityDAO m_dao;
    /**
     * 功能:返回DAO
     * @author hug 2012-9-21 
     * @return
     */
    public EntityDAO getEntityDAO() {
        return m_dao;
    }
    /** 
     * 功能: 执行查询语句返回结果
     * @author hug 2012-9-21 
     * @return
     * @throws Exception
     */
    public List<?> querySQL(String strSQL) throws Exception {
        return m_dao.searchEntities(strSQL);
    }

    /**
     * 功能:执行HQL语句（修改、删除）
     * @author hug 2012-9-21 
     * @param strSQL
     * @throws Exception
     */
    public void excuteSQL(String strSQL) throws Exception {
        m_dao.updateSql(strSQL);     
    }
    /**
     * 功能: 批量执行HQL语句（修改、删除）
     * @author hug 2012-9-27 
     * @param lsSQL
     * @throws Exception
     */
    public void excuteBatchSQL(List lsSQL) throws Exception{
        m_dao.deleteSqlList(lsSQL);
    }
    
    /**
     * 功能:根据库区判断是否生成调度任务
     * @author hug 2012-9-26 
     * @param whAreaId  库区ID
     * @return
     * @throws Exception
     */
    public boolean isCreateTask(String whAreaId) throws Exception {
        try{
            if(whAreaId != null){
                String strSql = "select wh.whAreaId from BaseWharea as wh where wh.whAreaId='" + whAreaId + "' and wh.istask='Y'";
                List lsResult = m_dao.searchEntities(strSql);
                if(lsResult != null && lsResult.size()>0){
                    return true; //生成调度任务
                }
            }  
        }catch(Exception er){
            throw new Exception("根据库区判断是否生成调度任务出错：" + er.getMessage());
        }
        return false;
    }
    /**
     * 功能:批量修改货位状态
     * @author hug 2012-9-27 
     * @param lsSQL
     * @throws Exception
     */
    public void updateSpaceStatus(List<String> lsSQL) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //修改货位状态
            if(lsSQL != null){
                String strSql = null;//修改货位状态的SQL语句
                for(int i = 0; i < lsSQL.size(); i++){
                    strSql = lsSQL.get(i);
                    session.createQuery(strSql).executeUpdate(); 
                }
            }
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("修改货位状态出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
    }

}
