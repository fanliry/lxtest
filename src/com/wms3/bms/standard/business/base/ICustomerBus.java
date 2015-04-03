package com.wms3.bms.standard.business.base;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseCustomer;

/**
 * 
 * 描述: 客户管理业务接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface ICustomerBus {
	
	/**
	 * 功能:根据条件查询客户
	 * @param customername	客户名称
	 * @param customertype	客户分类
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getCustomerQuery(String customername, String customertype, String strUrl, int maxLine) throws Exception;

	/**
	 * 功能:根据客户ID获得客户
	 * @param id	客户ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCustomer getCustomerById(String id) throws Exception;

	/**
	 * 功能:增加客户
	 * @param customer	客户
	 * @throws Exception
	 */
	public void addCustomer(BaseCustomer customer) throws Exception;

	/**
	 * 功能:获取所有客户列表
	 * @return 
	 * @throws Exception
	 */
	public List getCustomerList() throws Exception;

	/**
	 * 功能:修改客户
	 * @param customer	客户
	 * @throws Exception
	 */
	public void updateCustomer(BaseCustomer customer) throws Exception;

	/**
	 * 功能:删除客户
	 * @param id	客户ID
	 * @throws Exception
	 */
	public void deleteCustomer(String id) throws Exception;
	
	/**
	 * 功能:根据条件查询客户
	 * @param customername	客户名称
	 * @param customertype	客户分类
	 * @return 
	 * @throws Exception
	 */
	public List<BaseCustomer> getCustomerQueryRF(String customername, String customertype) throws Exception;
		
}