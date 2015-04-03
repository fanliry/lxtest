package com.wms3.bms.standard.business.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseBatchrule;

/**
 * 
 * 描述: 批次规则管理业务接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBatchruleBus {
	
	/**
	 * 功能:获取所有批次规则列表
	 * @return 
	 * @throws Exception
	 */
	public List getBatchruleList() throws Exception;

	/**
	 * 功能:根据ID获得批次规则
	 * @param id	
	 * @return 
	 * @throws Exception
	 */
	public BaseBatchrule getBatchruleById(String id) throws Exception;
	
	/**
	 * 功能:根据批次ID获得批次规则
	 * @param batchid	批次ID
	 * @return 
	 * @throws Exception
	 */
	public List getBatchruleByBatchId(String batchid) throws Exception;

	/**
	 * 功能:增加批次规则
	 * @param batchhrule	批次规则
	 * @throws Exception
	 */
	public void addBatchrule(BaseBatchrule batchhrule) throws Exception;

	/**
	 * 功能:修改批次规则
	 * @param batchhrule	批次规则
	 * @throws Exception
	 */
	public void updateBatchrule(BaseBatchrule batchhrule) throws Exception;

	/**
	 * 功能:删除批次规则
	 * @param id	ID
	 * @throws Exception
	 */
	public void deleteBatchrule(String id) throws Exception;
	
}
