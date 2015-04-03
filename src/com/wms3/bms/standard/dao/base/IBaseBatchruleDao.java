package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseBatchrule;

/**
 * 
 * ����: ���ι���DAO��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseBatchruleDao {

	/**
	 * ����:��ȡ�������ι����б�
	 * @return 
	 * @throws Exception
	 */
	public List getBatchruleList() throws Exception;

	/**
	 * ����:�������ι���ID������ι���
	 * @param id	���ι���ID
	 * @return 
	 * @throws Exception
	 */
	public BaseBatchrule getBatchruleById(String id) throws Exception;
	
	/**
	 * ����:��������ID������ι���
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public List getBatchruleByBatchId(String id) throws Exception;

	/**
	 * ����:�������ι���
	 * @param batchrule	���ι���
	 * @throws Exception
	 */
	public void addBatchrule(BaseBatchrule batchrule) throws Exception;

	/**
	 * ����:�޸����ι���
	 * @param batchrule	���ι���
	 * @throws Exception
	 */
	public void updateBatchrule(BaseBatchrule batchrule) throws Exception;

	/**
	 * ����:ɾ�����ι���
	 * @param id	���ι���ID
	 * @throws Exception
	 */
	public void deleteBatchrule(String id) throws Exception;

}
