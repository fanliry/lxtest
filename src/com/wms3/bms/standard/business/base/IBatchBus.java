package com.wms3.bms.standard.business.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseBatch;

/**
 * 
 * 描述: 批次管理业务接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBatchBus {
	
	/**
	 * 功能:获取所有批次列表
	 * @return 
	 * @throws Exception
	 */
	public List getBatchList() throws Exception;

	/**
	 * 功能:根据批次ID获得批次
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public BaseBatch getBatchById(String id) throws Exception;

	/**
	 * 功能:增加批次
	 * @param batch	批次
	 * @throws Exception
	 */
	public void addBatch(BaseBatch batch) throws Exception;

	/**
	 * 功能:修改批次
	 * @param batch	批次
	 * @throws Exception
	 */
	public void updateBatch(BaseBatch batch) throws Exception;

	/**
	 * 功能:删除批次
	 * @param id	批次ID
	 * @throws Exception
	 */
	public void deleteBatch(String id) throws Exception;
	
}
