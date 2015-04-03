package com.wms3.bms.standard.business.base;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseCarton;

/**
 * 
 * ����:��ת��ҵ��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface ICartonBus {
	
	/**
	 * ����:����������ѯ��ת��
	 * @param cartonid	װ�����
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getCartonQuery(String cartonid, String strUrl, int maxLine) throws Exception;

	/**
	 * ����:������ת��ID�����ת��
	 * @param id	��ת��ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCarton getCartonById(String id) throws Exception;

	/**
	 * ����:������ת��
	 * @param carton	��ת��
	 * @throws Exception
	 */
	public void addCarton(BaseCarton carton) throws Exception;

	/**
	 * ����:��ȡ������ת���б�
	 * @return 
	 * @throws Exception
	 */
	public List getCartonList() throws Exception;

	/**
	 * ����:�޸���ת��
	 * @param carton	��ת��
	 * @throws Exception
	 */
	public void updateCarton(BaseCarton carton) throws Exception;

	/**
	 * ����:ɾ����ת��
	 * @param id	��ת��ID
	 * @throws Exception
	 */
	public void deleteCarton(String id) throws Exception;
	
}