package com.wms3.bms.standard.dao.inventory;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.InventoryTransaction;
/**
 * ��������潻��dao
 * @author liuxh
 *
 */
public interface IInventoryTransactionDao extends IDao {
	/**
	 * ���ܣ���ÿ�潻�׵�list
	 * @param HQL
	 * @return
	 * @throws Exception
	 */
	List<InventoryTransaction> getInventoryTransactions(String HQL)throws Exception;
}
