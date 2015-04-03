package com.wms3.bms.standard.business.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseBatchmean;

/**
 * 
 * 描述: 批次意义管理业务接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBatchmeanBus {
	
	/**
	 * 功能:获取所有批次意义列表
	 * @return 
	 * @throws Exception
	 */
	public List getBatchmeanList() throws Exception;

	/**
	 * 功能:根据ID获得批次意义
	 * @param id	
	 * @return 
	 * @throws Exception
	 */
	public BaseBatchmean getBatchmeanById(String id) throws Exception;
	
	/**
	 * 功能:根据批次ID获得批次意义
	 * @param batchid	批次ID
	 * @return 
	 * @throws Exception
	 */
	public List getBatchmeanByBatchId(String batchid) throws Exception;

	/**
	 * 功能:增加批次意义
	 * @param batchhmean	批次意义
	 * @throws Exception
	 */
	public void addBatchmean(BaseBatchmean batchhmean) throws Exception;

	/**
	 * 功能:修改批次意义
	 * @param batchhmean	批次意义
	 * @throws Exception
	 */
	public void updateBatchmean(BaseBatchmean batchhmean) throws Exception;

	/**
	 * 功能:删除批次意义
	 * @param id	ID
	 * @throws Exception
	 */
	public void deleteBatchmean(String id) throws Exception;
	
}
