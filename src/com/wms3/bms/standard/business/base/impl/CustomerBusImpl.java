package com.wms3.bms.standard.business.base.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.ICustomerBus;
import com.wms3.bms.standard.dao.base.IBaseCustomerDao;
import com.wms3.bms.standard.dao.base.impl.BaseCustomerDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCustomer;

/**
 * 
 * 描述: 客户管理业务类
 * @author fangjie
 * @since WMS 3.0
 */
public class CustomerBusImpl implements ICustomerBus {
	
	protected IBaseCustomerDao baseCustomerDAO = null;
	protected EntityDAO m_dao;

	/**
	 * @param dao
	 */
	public CustomerBusImpl(EntityDAO dao) {
		this.baseCustomerDAO = new BaseCustomerDaoImpl(dao);
		this.m_dao = dao;
	}


	/**
	 * 功能:根据条件查询客户
	 * @param customername	客户名称
	 * @param customertype	客户分类
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getCustomerQuery(String customername, String customertype, String strUrl, int maxLine) throws Exception {
		
		PagingTool pt = null;
		
		try {
			String sql = "From BaseCustomer as t where 1=1" ;
			
			if(customertype != null && customertype.trim().length() > 0){
				sql += " and t.customertype='" + customertype + "'";
			}
			if(customername != null && customername.trim().length() > 0){
				sql += " and t.customername like '%" + customername + "%'";
			}
			
			String strHQL = sql + " order by t.customerid";
			String strCountHQL = "select count(*)" + sql;
			
			pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("根据条件查询客户出错:" + er.getMessage());
		}
				
		return pt;
	}

	/**
	 * 功能:根据条件查询客户
	 * @param customername	客户名称
	 * @param customertype	客户分类
	 * @return 
	 * @throws Exception
	 */
	public List<BaseCustomer> getCustomerQueryRF(String customername, String customertype) throws Exception {
		
		List<BaseCustomer> list = new ArrayList<BaseCustomer>();
		
		try {
			String sql = "From BaseCustomer as t where 1=1" ;
			
			if(customertype != null && customertype.trim().length() > 0){
				sql += " and t.customertype='" + customertype + "'";
			}
			if(customername != null && customername.trim().length() > 0){
				sql += " and t.customername like '%" + customername + "%'";
			}
			
			String strHQL = sql + " order by t.customerid";
			
			list = m_dao.searchEntities(strHQL);
			
		} catch (Exception er) {
			throw new Exception("根据条件查询客户出错:" + er.getMessage());
		}
				
		return list;
	}
	
	
	/**
	 * 功能:根据客户ID获得库区
	 * @param id	客户ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCustomer getCustomerById(String id) throws Exception {
		
		return baseCustomerDAO.getCustomerById(id);
	}

	/**
	 * 功能:增加客户
	 * @param customer	客户
	 * @throws Exception
	 */
	public void addCustomer(BaseCustomer customer) throws Exception {
		
		//获得编码
        String id = baseCustomerDAO.getMaxCustomerNo();
        id = SeqTool.getCode(Integer.parseInt(id) + 1, 3);
        customer.setCustomerid(id);
        baseCustomerDAO.addCustomer(customer);
	}
	
	/**
	 * 功能:获取所有客户列表
	 * @return 
	 * @throws Exception
	 */
	public List getCustomerList() throws Exception {
		
		return baseCustomerDAO.getCustomerList();
	}

	/**
	 * 功能:修改客户
	 * @param customer	客户
	 * @throws Exception
	 */
	public void updateCustomer(BaseCustomer customer) throws Exception {
		
		baseCustomerDAO.updateCustomer(customer);
		
	}

	/**
	 * 功能:删除客户
	 * @param id	客户ID
	 * @throws Exception
	 */
	public void deleteCustomer(String id) throws Exception {
		
		baseCustomerDAO.deleteCustomer(id);
	}
	
}