package com.wms3.bms.standard.dao.inventory;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.InventoryNeedcheck;

public interface IInventoryNeedCheckDao extends IDao {
	/**
	 * 功能：根据id获得需盘点记录
	 * @param needCheckId 需盘点ID
	 * @return
	 * @throws Exception
	 */
public InventoryNeedcheck getINeedcheckInfo(String needCheckId)throws Exception;

/**
 * 功能：根据库位ID获取异常单
 * @param needCheckId
 * @return
 * @throws Exception
 */
public InventoryNeedcheck getINeedcheckInfoByCargospaceId(String cargospaceId)
	throws Exception;
}
