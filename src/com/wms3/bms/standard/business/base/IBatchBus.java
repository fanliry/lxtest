package com.wms3.bms.standard.business.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseBatch;

/**
 * 
 * ����: ���ι���ҵ��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBatchBus {
	
	/**
	 * ����:��ȡ���������б�
	 * @return 
	 * @throws Exception
	 */
	public List getBatchList() throws Exception;

	/**
	 * ����:��������ID�������
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public BaseBatch getBatchById(String id) throws Exception;

	/**
	 * ����:��������
	 * @param batch	����
	 * @throws Exception
	 */
	public void addBatch(BaseBatch batch) throws Exception;

	/**
	 * ����:�޸�����
	 * @param batch	����
	 * @throws Exception
	 */
	public void updateBatch(BaseBatch batch) throws Exception;

	/**
	 * ����:ɾ������
	 * @param id	����ID
	 * @throws Exception
	 */
	public void deleteBatch(String id) throws Exception;
	
}
