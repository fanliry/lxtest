package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseBatchmeanDao;
import com.wms3.bms.standard.entity.base.BaseBatchmean;

/**
 * 
 * 描述: 批次意义DAO类
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseBatchmeanDaoImpl implements IBaseBatchmeanDao {
	
	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;

	public BaseBatchmeanDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * 功能:获取所有批次意义列表
	 * @return
	 * @throws Exception
	 */
	public List getBatchmeanList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseBatchmean as t where 1=1 order by t.batchmeanid";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("查询批次意义列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:根据批次意义ID获得批次意义
	 * @param id	批次意义ID
	 * @return 
	 * @throws Exception
	 */
	public BaseBatchmean getBatchmeanById(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql = " from BaseBatchmean as t where t.batchmeanid='" + id+"'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseBatchmean)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 功能:根据批次ID获得批次意义
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public List getBatchmeanByBatchId(String id) throws Exception 
	{
		List ls = null;
		try
		{
			if(id != null && !id.equals(""))
			{
				String strSql = " from BaseBatchmean as t where t.batchid='" + id+"'";
				ls = m_dao.searchEntities(strSql);
			}
		}catch(Exception er)
		{
			throw new Exception("根据批次ID获得批次意义列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:增加批次意义
	 * @param batchmean	批次意义
	 * @throws Exception
	 */
	public void addBatchmean(BaseBatchmean batchmean) throws Exception 
	{
		m_dao.save(batchmean);
	}

	/**
	 * 功能:修改批次意义
	 * @param batchmean	批次意义
	 * @throws Exception
	 */
	public void updateBatchmean(BaseBatchmean batchmean) throws Exception 
	{
		m_dao.update(batchmean);
	}

	/**
	 * 功能:删除批次意义
	 * @param id	批次意义ID
	 * @throws Exception
	 */
	public void deleteBatchmean(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete BaseBatchmean as t where t.batchmeanid='" + id+"'";

			m_dao.deleteSql(strSql);
		}	
	}

}