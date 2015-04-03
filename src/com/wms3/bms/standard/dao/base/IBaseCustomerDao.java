package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseCustomer;

/**
 * 
 * 描述: 客户DAO类接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseCustomerDao {

	/**
	 * 功能:根据客户ID获得客户
	 * @param id	客户ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCustomer getCustomerById(String id) throws Exception;

	/**
	 * 功能:获得最大的一个客户编码
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public String getMaxCustomerNo() throws Exception;

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

}
