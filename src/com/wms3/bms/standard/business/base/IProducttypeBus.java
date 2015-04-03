package com.wms3.bms.standard.business.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseProducttype;

/**
 * 
 * ����: ��Ʒ������ҵ��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IProducttypeBus {
	
	/**
	 * ����:����������ѯ����
	 * @param ptname	�����
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeQuery(String ptname) throws Exception;

	/**
	 * ����:������Ʒ���ID��ÿ���
	 * @param id	��Ʒ���ID
	 * @return 
	 * @throws Exception
	 */
	public BaseProducttype getProducttypeById(String id) throws Exception;

	/**
	 * ����:������Ʒ���
	 * @param producttype	��Ʒ���
	 * @throws Exception
	 */
	public void addProducttype(BaseProducttype producttype) throws Exception;

	/**
	 * ����:��ȡ������Ʒ����б�
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeList() throws Exception;

	/**
	 * ����:�޸���Ʒ���
	 * @param producttype	��Ʒ���
	 * @throws Exception
	 */
	public void updateProducttype(BaseProducttype producttype) throws Exception;

	/**
	 * ����:ɾ����Ʒ���
	 * @param id	��Ʒ���ID
	 * @throws Exception
	 */
	public void deleteProducttype(String id) throws Exception;
	
}