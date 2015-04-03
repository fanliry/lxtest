package com.wms3.bms.standard.business.inventory.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.inventory.IInventoryCheckResultBus;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;

public class InventoryCheckResultBusImpl implements IInventoryCheckResultBus {
	
	protected EntityDAO m_dao = null;
	
	public InventoryCheckResultBusImpl(EntityDAO dao)
	{
		m_dao = dao;
	}

	@Override
	public InventoryCheckResult getCheckResultById(String checkid)
			throws Exception {
		InventoryCheckResult checkResult = null;
		try
		{
			if(checkid != null)
			{
				String strSql = "from InventoryCheckResult as icr where icr.checkid='" + checkid + "'";
				List ls = m_dao.searchEntities(strSql);
				if(ls != null && ls.size() > 0)
				{
					checkResult = (InventoryCheckResult)ls.get(0);
				}
			}
		}catch(Exception er)
		{
			throw new Exception("根据盘点单结果ID获得盘点单结果出错(InventoryCheckResultBusImpl.getCheckResultById):" + er.getMessage());
		}
		return checkResult;
	}
	@Override
	public List<InventoryCheckResult> getCheckResultByRequestId(String requestid)
			throws Exception {
		List<InventoryCheckResult> checkResults = null;
		try
		{
			if(requestid != null)
			{
				String strSql = "from InventoryCheckResult as icr where icr.requestid='" + requestid + "'";
				checkResults = m_dao.searchEntities(strSql);
			}
		}catch(Exception er)
		{
			throw new Exception("根据盘点申请ID获得盘点单结果出错(InventoryCheckResultBusImpl.getCheckResultByRequestId):" + er.getMessage());
		}
		return checkResults;
	}
	@Override
	public InventoryCheckResult getCheckResultByInventoryId(String inventoryid) throws Exception{
		InventoryCheckResult checkResult = null;
		try
		{
			if(inventoryid != null)
			{
				String strSql = "from InventoryCheckResult as icr where icr.inventoryid='" + inventoryid + "'";
				List ls = m_dao.searchEntities(strSql);
				if(ls != null && ls.size() > 0)
				{
					checkResult = (InventoryCheckResult)ls.get(0);
				}
			}
		}catch(Exception er)
		{
			throw new Exception("根据库存ID获得盘点单结果出错(InventoryCheckResultBusImpl.getCheckResultByInventoryId):" + er.getMessage());
		}
		return checkResult;
	}
	

}
