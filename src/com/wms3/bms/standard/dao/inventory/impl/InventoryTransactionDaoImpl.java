package com.wms3.bms.standard.dao.inventory.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.inventory.IInventoryTransactionDao;
import com.wms3.bms.standard.entity.inventory.InventoryTransaction;

public class InventoryTransactionDaoImpl extends AbstractDao implements IInventoryTransactionDao {

	public InventoryTransactionDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	
	public List<InventoryTransaction> getInventoryTransactions(String HQL)
			throws Exception {
		List<InventoryTransaction> ls = null;
		try {
			ls = m_dao.searchEntities(HQL);
		} catch (Exception er) {
			throw new  Exception("查询库存交易出错，InventoryTransactionDaoImpl.getInventoryTransactions："+er.getMessage());
		}	
		return ls;
	}
}
