package com.wms3.bms.standard.business.inventory;

/**
 * ����:��Ʒ��������
 * @author yao
 *
 */
public interface ProductAdjustBus 
{
	/**
	 * ����:��û�Ʒ��������ѯ��SQL���
	 * @param status	״̬
	 * @param customer	�ͻ�
	 * @return
	 */
	public String getProductAdjustHeaderSQL(String status, String customer) throws Exception;
	/**
	 * ����:��û�Ʒ�������ܼ�¼��ѯ��SQL���
	 * @param status	״̬
	 * @param customer	�ͻ�
	 * @return
	 */
	public String getProductAdjustHeaderCountSQL(String status, String customer) throws Exception;
}
