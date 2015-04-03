package com.wms3.bms.standard.business.inventory;

import java.util.List;

import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;

public interface IInventoryCheckResultBus {
	/**
	 * 功能：根据盘点结果单ID获取盘点结果单
	 * @param checkid
	 * @return
	 */
	public InventoryCheckResult getCheckResultById(String checkid) throws Exception;
	public List<InventoryCheckResult> getCheckResultByRequestId(String requestid) throws Exception;
	/**
	 * 功能：根据库存id获取盘点结果单
	 * @param inventoryid
	 * @return
	 * @throws Exception
	 */
	public InventoryCheckResult getCheckResultByInventoryId(String inventoryid) throws Exception;
	
	

}
