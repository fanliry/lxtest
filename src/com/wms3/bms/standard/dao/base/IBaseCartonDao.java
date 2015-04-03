package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseCarton;

/**
 * 
 * ����: ��ת��DAO��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseCartonDao {

	/**
	 * ����:������ת��ID�����ת��
	 * @param id	��ת��ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCarton getCartonById(String id) throws Exception;

	/**
	 * ����:�������һ����ת�����
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public String getMaxCartonNo() throws Exception;

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
