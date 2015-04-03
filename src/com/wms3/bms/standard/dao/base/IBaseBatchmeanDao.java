package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseBatchmean;

/**
 * 
 * ����: ��������DAO��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseBatchmeanDao {

	/**
	 * ����:��ȡ�������������б�
	 * @return 
	 * @throws Exception
	 */
	public List getBatchmeanList() throws Exception;

	/**
	 * ����:������������ID�����������
	 * @param id	��������ID
	 * @return 
	 * @throws Exception
	 */
	public BaseBatchmean getBatchmeanById(String id) throws Exception;
	
	/**
	 * ����:��������ID�����������
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public List getBatchmeanByBatchId(String id) throws Exception;

	/**
	 * ����:������������
	 * @param batchmean	��������
	 * @throws Exception
	 */
	public void addBatchmean(BaseBatchmean batchmean) throws Exception;

	/**
	 * ����:�޸���������
	 * @param batchmean	��������
	 * @throws Exception
	 */
	public void updateBatchmean(BaseBatchmean batchmean) throws Exception;

	/**
	 * ����:ɾ����������
	 * @param id	��������ID
	 * @throws Exception
	 */
	public void deleteBatchmean(String id) throws Exception;

}
