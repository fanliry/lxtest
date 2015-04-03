package com.wms3.bms.standard.business.quality;

import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * 描述：质检管理的业务接口类
 * @author liuxh
 * @since 2012-11-18
 *
 */
public interface IQualityMgrBus {
 /**
  * 功能：更新库存抽检单状态和库存
  * @param ids
  * @param userCode
  * @return
  * @throws Exception
  */
 public String updateCheckBoundUpdateInvent(String ids,String userCode)throws Exception;
 /**
  * 功能：根据库存ID获得库存
  * @param id 库存ID
  * @return
  * @throws Exception
  */
 public InventoryStorage getInventById(String id)throws Exception;
 /**
  * 功能：作废抽检单
  * @param ids
  * @param userCode
  * @return
  * @throws Exception
  */
public String deleteCheckBoundUpdateInvent(String ids, String strUserCode)throws Exception;
}
