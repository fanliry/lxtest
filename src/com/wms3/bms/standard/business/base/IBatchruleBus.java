package com.wms3.bms.standard.business.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseBatchrule;

/**
 * 
 * ����: ���ι������ҵ��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBatchruleBus {
	
	/**
	 * ����:��ȡ�������ι����б�
	 * @return 
	 * @throws Exception
	 */
	public List getBatchruleList() throws Exception;

	/**
	 * ����:����ID������ι���
	 * @param id	
	 * @return 
	 * @throws Exception
	 */
	public BaseBatchrule getBatchruleById(String id) throws Exception;
	
	/**
	 * ����:��������ID������ι���
	 * @param batchid	����ID
	 * @return 
	 * @throws Exception
	 */
	public List getBatchruleByBatchId(String batchid) throws Exception;

	/**
	 * ����:�������ι���
	 * @param batchhrule	���ι���
	 * @throws Exception
	 */
	public void addBatchrule(BaseBatchrule batchhrule) throws Exception;

	/**
	 * ����:�޸����ι���
	 * @param batchhrule	���ι���
	 * @throws Exception
	 */
	public void updateBatchrule(BaseBatchrule batchhrule) throws Exception;

	/**
	 * ����:ɾ�����ι���
	 * @param id	ID
	 * @throws Exception
	 */
	public void deleteBatchrule(String id) throws Exception;
	
}
