package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseProducttype;

/**
 * 
 * ����: ��Ʒ���DAO��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseProducttypeDao {

	/**
	 * ����:����������ѯ����
	 * @param ptname	�����
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeQuery(String ptname) throws Exception;

	/**
	 * ����:���ݲ�Ʒ���ID��ò�Ʒ���
	 * @param id	��Ʒ���ID
	 * @return 
	 * @throws Exception
	 */
	public BaseProducttype getProducttypeById(String id) throws Exception;

	/**
	 * ����:���ͬһ�������������һ����Ʒ������
	 * @param parentid	�����ID
	 * @return
	 * @throws Exception
	 */
	public String getMaxProducttypeNo(String parentid) throws Exception;

	/**
	 * ����:���Ӳ�Ʒ���
	 * @param producttype	��Ʒ���
	 * @throws Exception
	 */
	public void addProducttype(BaseProducttype producttype) throws Exception;

	/**
	 * ����:��ȡ���в�Ʒ����б�
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeList() throws Exception;

	/**
	 * ����:�޸Ĳ�Ʒ���
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
