package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseBatchruleDao;
import com.wms3.bms.standard.entity.base.BaseBatchrule;

/**
 * 
 * 描述: 批次规则DAO类
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseBatchruleDaoImpl implements IBaseBatchruleDao {
	
	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;

	public BaseBatchruleDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * 功能:获取所有批次规则列表
	 * @return
	 * @throws Exception
	 */
	public List getBatchruleList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseBatchrule as t where 1=1 order by t.batchruleid";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("查询批次规则列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:根据批次规则ID获得批次规则
	 * @param id	批次规则ID
	 * @return 
	 * @throws Exception
	 */
	public BaseBatchrule getBatchruleById(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql = " from BaseBatchrule as t where t.batchruleid='" + id+"'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseBatchrule)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 功能:根据批次ID获得批次规则
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public List getBatchruleByBatchId(String id) throws Exception 
	{
		List ls = null;
		try
		{
			if(id != null && !id.equals(""))
			{
				String strSql = " from BaseBatchrule as t where t.batchid='" + id+"'";
				ls = m_dao.searchEntities(strSql);
			}
		}catch(Exception er)
		{
			throw new Exception("根据批次ID获得批次规则列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:增加批次规则
	 * @param batchrule	批次规则
	 * @throws Exception
	 */
	public void addBatchrule(BaseBatchrule batchrule) throws Exception 
	{
		m_dao.save(batchrule);
	}

	/**
	 * 功能:修改批次规则
	 * @param batchrule	批次规则
	 * @throws Exception
	 */
	public void updateBatchrule(BaseBatchrule batchrule) throws Exception 
	{
		m_dao.update(batchrule);
	}

	/**
	 * 功能:删除批次规则
	 * @param id	批次规则ID
	 * @throws Exception
	 */
	public void deleteBatchrule(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete BaseBatchrule as t where t.batchruleid='" + id+"'";

			m_dao.deleteSql(strSql);
		}	
	}

}