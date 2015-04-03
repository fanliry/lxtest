package com.wms3.bms.standard.dao.inventory;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryHold;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * 
 * 描述: 库存冻结/释放DAO类接口
 * @author hug 2012-4-25
 * @since WMS 3.0
 */
public interface IInventoryHoldDao  extends IDao{

	/**
	 * 功能:生成冻结记录，修改库存冻结数量
	 * @param lshold		冻结列表
	 * @param lsstorage		库存列表
	 * @return 
	 * @throws Exception
	 */
	void addHold(List<InventoryHold> lshold, List<InventoryStorage> lsstorage) throws Exception;

	/**
	 * 功能:根据id获得冻结记录
	 * @param id		冻结id
	 * @return 
	 * @throws Exception
	 */
	InventoryHold getHoldById(String id) throws Exception;

	/**
	 * 功能:释放冻结记录，清空库存冻结数量
	 * @param lshold		冻结列表
	 * @param lsstorage		库存列表
	 * @return 
	 * @throws Exception
	 */
	void updateHold(List<InventoryHold> lshold, List<InventoryStorage> lsstorage) throws Exception;

	
  
}
