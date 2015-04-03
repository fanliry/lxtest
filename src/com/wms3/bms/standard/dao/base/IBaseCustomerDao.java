package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseCustomer;

/**
 * 
 * ����: �ͻ�DAO��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseCustomerDao {

	/**
	 * ����:���ݿͻ�ID��ÿͻ�
	 * @param id	�ͻ�ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCustomer getCustomerById(String id) throws Exception;

	/**
	 * ����:�������һ���ͻ�����
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public String getMaxCustomerNo() throws Exception;

	/**
	 * ����:���ӿͻ�
	 * @param customer	�ͻ�
	 * @throws Exception
	 */
	public void addCustomer(BaseCustomer customer) throws Exception;

	/**
	 * ����:��ȡ���пͻ��б�
	 * @return 
	 * @throws Exception
	 */
	public List getCustomerList() throws Exception;

	/**
	 * ����:�޸Ŀͻ�
	 * @param customer	�ͻ�
	 * @throws Exception
	 */
	public void updateCustomer(BaseCustomer customer) throws Exception;

	/**
	 * ����:ɾ���ͻ�
	 * @param id	�ͻ�ID
	 * @throws Exception
	 */
	public void deleteCustomer(String id) throws Exception;

}
