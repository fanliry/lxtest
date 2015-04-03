package com.wms3.bms.standard.business.base;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseCustomer;

/**
 * 
 * ����: �ͻ�����ҵ��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface ICustomerBus {
	
	/**
	 * ����:����������ѯ�ͻ�
	 * @param customername	�ͻ�����
	 * @param customertype	�ͻ�����
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getCustomerQuery(String customername, String customertype, String strUrl, int maxLine) throws Exception;

	/**
	 * ����:���ݿͻ�ID��ÿͻ�
	 * @param id	�ͻ�ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCustomer getCustomerById(String id) throws Exception;

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
	
	/**
	 * ����:����������ѯ�ͻ�
	 * @param customername	�ͻ�����
	 * @param customertype	�ͻ�����
	 * @return 
	 * @throws Exception
	 */
	public List<BaseCustomer> getCustomerQueryRF(String customername, String customertype) throws Exception;
		
}