package com.wms3.bms.standard.dao;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * 描述: DAO父接口
 * @author hug 2012-9-21
 * @since WMS 3.0
 */
public interface IDao {
    /**
     * 功能：获得数据库操作DAO类
     * @author hug 2012-3-13 
     * @return
     */
    public EntityDAO getEntityDAO();
    
    /** 
     * 功能: 执行查询语句并返回结果
     * @author hug 2012-9-21 
     * @return
     * @throws Exception
     */
    public List<?> querySQL(String strSQL) throws Exception;
    
    /**
     * 
     * 功能:执行HQL语句（修改、删除）
     * @author hug 2012-8-28 
     * @param strSQL
     * @throws Exception
     */
    public void excuteSQL(String strSQL) throws Exception;
    /**
     * 功能:批量执行HQL语句（修改、删除）
     * @author hug 2012-9-27 
     * @param lsSQL
     * @throws Exception
     */
    public void excuteBatchSQL(List lsSQL) throws Exception;
    
    /**
     * 功能:根据库区判断是否生成调度任务
     * @author hug 2012-9-6 
     * @param whAreaId  库区ID
     * @return
     * @throws Exception
     */
    public boolean isCreateTask(String whAreaId) throws Exception;
    
    /**
     * 功能:批量修改货位状态
     * @author hug 2012-9-6 
     * @param lsSQL
     * @throws Exception
     */
    public void updateSpaceStatus(List<String> lsSQL) throws Exception;
}
