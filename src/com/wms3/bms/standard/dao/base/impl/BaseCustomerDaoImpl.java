package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseCustomerDao;
import com.wms3.bms.standard.entity.base.BaseCustomer;

/**
 * 
 * 描述: 客户DAO类
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseCustomerDaoImpl implements IBaseCustomerDao {
	
	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;

	public BaseCustomerDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * 功能:根据客户ID获得客户
	 * @param id	客户ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCustomer getCustomerById(String id) throws Exception 
	{
		if(id!=null && !id.equals(""))
		{
			String strSql = " from BaseCustomer as t where t.customerid='" + id + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseCustomer)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 功能:获得最大的一个客户编码
	 * @return
	 * @throws Exception
	 */
	public String getMaxCustomerNo() throws Exception 
	{
		String maxNo = "000";
		try
		{
			String strSql = "from BaseCustomer as t where 1=1 order by t.customerid desc";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && !ls.isEmpty())
			{
				BaseCustomer customer = (BaseCustomer)ls.get(0);
				maxNo = customer.getCustomerid();
			}
		}catch(Exception er)
		{
			throw new Exception("获得最大的一个客户编码失败:"+er.getMessage());
		}
		return maxNo;
	}

	/**
	 * 功能:增加客户
	 * @param customer	客户
	 * @throws Exception
	 */
	public void addCustomer(BaseCustomer customer) throws Exception 
	{
		m_dao.save(customer);
	}

	/**
	 * 功能:获取所有客户列表
	 * @return 
	 * @throws Exception
	 */
	public List getCustomerList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseCustomer as t where 1=1 order by t.customerid";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("查询客户列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:修改客户
	 * @param customer	客户
	 * @throws Exception
	 */
	public void updateCustomer(BaseCustomer customer) throws Exception 
	{
		m_dao.update(customer);
	}

	/**
	 * 功能:删除客户
	 * @param id	客户ID
	 * @throws Exception
	 */
	public void deleteCustomer(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete BaseCustomer as t where t.customerid='" + id + "'";
			m_dao.deleteSql(strSql);
		}	
	}
}