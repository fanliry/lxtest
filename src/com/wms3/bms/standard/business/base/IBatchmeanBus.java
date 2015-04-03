package com.wms3.bms.standard.business.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseBatchmean;

/**
 * 
 * ����: �����������ҵ��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBatchmeanBus {
	
	/**
	 * ����:��ȡ�������������б�
	 * @return 
	 * @throws Exception
	 */
	public List getBatchmeanList() throws Exception;

	/**
	 * ����:����ID�����������
	 * @param id	
	 * @return 
	 * @throws Exception
	 */
	public BaseBatchmean getBatchmeanById(String id) throws Exception;
	
	/**
	 * ����:��������ID�����������
	 * @param batchid	����ID
	 * @return 
	 * @throws Exception
	 */
	public List getBatchmeanByBatchId(String batchid) throws Exception;

	/**
	 * ����:������������
	 * @param batchhmean	��������
	 * @throws Exception
	 */
	public void addBatchmean(BaseBatchmean batchhmean) throws Exception;

	/**
	 * ����:�޸���������
	 * @param batchhmean	��������
	 * @throws Exception
	 */
	public void updateBatchmean(BaseBatchmean batchhmean) throws Exception;

	/**
	 * ����:ɾ����������
	 * @param id	ID
	 * @throws Exception
	 */
	public void deleteBatchmean(String id) throws Exception;
	
}
