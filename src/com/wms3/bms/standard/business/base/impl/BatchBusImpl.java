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
 * 描述: 批次管理业务类
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
	 * 功能:获取所有批次列表
	 * @return 
	 * @throws Exception
	 */
	public List getBatchList() throws Exception {
		
		return baseBatchDAO.getBatchList();
	}

	/**
	 * 功能:根据批次ID获得批次
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public BaseBatch getBatchById(String id) throws Exception {
		
		return baseBatchDAO.getBatchById(id);
	}

	/**
	 * 功能:增加批次
	 * @param batch	批次
	 * @throws Exception
	 */
	public void addBatch(BaseBatch batch) throws Exception {
		
		// 获得现有的最大批次编码
		String batchId = baseBatchDAO.getMaxBatchNo();
		
		batchId = SeqTool.getCode(Integer.parseInt(batchId) + 1, 3);
		batch.setBatchid(batchId);
		
		baseBatchDAO.addBatch(batch);
		
	}

	/**
	 * 功能:修改批次
	 * @param batch	批次
	 * @throws Exception
	 */
	public void updateBatch(BaseBatch batch) throws Exception {
		
		baseBatchDAO.updateBatch(batch);
		
	}

	/**
	 * 功能:删除批次
	 * @param id	批次ID
	 * @throws Exception
	 */
	public void deleteBatch(String id) throws Exception {
		
		baseBatchDAO.deleteBatch(id);
	}

}