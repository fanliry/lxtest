package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseBatchDao;
import com.wms3.bms.standard.entity.base.BaseBatch;

/**
 * 
 * 描述: 批次DAO类
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseBatchDaoImpl implements IBaseBatchDao {
	
	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;

	public BaseBatchDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * 功能:获取所有批次列表
	 * @return
	 * @throws Exception
	 */
	public List getBatchList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseBatch as t where 1=1 order by t.batchid";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("查询批次列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:根据批次ID获得批次
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public BaseBatch getBatchById(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql = " from BaseBatch as t where t.batchid='" + id+"'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseBatch)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 功能:获得最大的一个批次编码
	 * @return
	 * @throws Exception
	 */
	public String getMaxBatchNo() throws Exception 
	{
		String maxBatchNo = "000";
		try
		{
			String strSql = "from BaseBatch as t where 1=1 order by t.batchid desc";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && !ls.isEmpty())
			{
				BaseBatch batch = (BaseBatch)ls.get(0);
				maxBatchNo = batch.getBatchid();
			}
		}catch(Exception er)
		{
			throw new Exception("获得最大的一个批次编码失败:"+er.getMessage());
		}
		return maxBatchNo;
	}

	/**
	 * 功能:增加批次
	 * @param batch	批次
	 * @throws Exception
	 */
	public void addBatch(BaseBatch batch) throws Exception 
	{
		m_dao.save(batch);
	}

	/**
	 * 功能:修改批次
	 * @param batch	批次
	 * @throws Exception
	 */
	public void updateBatch(BaseBatch batch) throws Exception 
	{
		m_dao.update(batch);
	}

	/**
	 * 功能:删除批次
	 * @param id	批次ID
	 * @throws Exception
	 */
	public void deleteBatch(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete BaseBatch as t where t.batchid='" + id+"'";

			m_dao.deleteSql(strSql);
		}	
	}

}