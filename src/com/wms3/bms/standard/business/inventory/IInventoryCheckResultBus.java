package com.wms3.bms.standard.business.inventory;

import java.util.List;

import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;

public interface IInventoryCheckResultBus {
	/**
	 * ���ܣ������̵�����ID��ȡ�̵�����
	 * @param checkid
	 * @return
	 */
	public InventoryCheckResult getCheckResultById(String checkid) throws Exception;
	public List<InventoryCheckResult> getCheckResultByRequestId(String requestid) throws Exception;
	/**
	 * ���ܣ����ݿ��id��ȡ�̵�����
	 * @param inventoryid
	 * @return
	 * @throws Exception
	 */
	public InventoryCheckResult getCheckResultByInventoryId(String inventoryid) throws Exception;
	
	

}
