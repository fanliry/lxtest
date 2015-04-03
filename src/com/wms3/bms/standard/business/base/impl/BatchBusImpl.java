package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.IBatchBus;
import com.wms3.bms.standard.dao.base.IBaseBatchDao;
import com.wms3.bms.standard.dao.base.impl.BaseBatchDaoImpl;
import com.wms3.bms.standard.entity.base.BaseBatch;

/**
 * 
 * ����: ���ι���ҵ����
 * @author fangjie
 * @since WMS 3.0
 */
public class BatchBusImpl implements IBatchBus {
	
	protected IBaseBatchDao baseBatchDAO = null;

	/**
	 * @param dao
	 */
	public BatchBusImpl(EntityDAO dao) {
		this.baseBatchDAO = new BaseBatchDaoImpl(dao);
	}

	/**
	 * ����:��ȡ���������б�
	 * @return 
	 * @throws Exception
	 */
	public List getBatchList() throws Exception {
		
		return baseBatchDAO.getBatchList();
	}

	/**
	 * ����:��������ID�������
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public BaseBatch getBatchById(String id) throws Exception {
		
		return baseBatchDAO.getBatchById(id);
	}

	/**
	 * ����:��������
	 * @param batch	����
	 * @throws Exception
	 */
	public void addBatch(BaseBatch batch) throws Exception {
		
		// ������е�������α���
		String batchId = baseBatchDAO.getMaxBatchNo();
		
		batchId = SeqTool.getCode(Integer.parseInt(batchId) + 1, 3);
		batch.setBatchid(batchId);
		
		baseBatchDAO.addBatch(batch);
		
	}

	/**
	 * ����:�޸�����
	 * @param batch	����
	 * @throws Exception
	 */
	public void updateBatch(BaseBatch batch) throws Exception {
		
		baseBatchDAO.updateBatch(batch);
		
	}

	/**
	 * ����:ɾ������
	 * @param id	����ID
	 * @throws Exception
	 */
	public void deleteBatch(String id) throws Exception {
		
		baseBatchDAO.deleteBatch(id);
	}

}