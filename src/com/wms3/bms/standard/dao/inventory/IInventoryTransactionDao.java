package com.wms3.bms.standard.dao.inventory;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.InventoryTransaction;
/**
 * 描述：库存交易dao
 * @author liuxh
 *
 */
public interface IInventoryTransactionDao extends IDao {
	/**
	 * 功能：获得库存交易的list
	 * @param HQL
	 * @return
	 * @throws Exception
	 */
	List<InventoryTransaction> getInventoryTransactions(String HQL)throws Exception;
}
