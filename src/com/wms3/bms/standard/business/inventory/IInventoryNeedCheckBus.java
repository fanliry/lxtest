package com.wms3.bms.standard.business.inventory;

import com.wms3.bms.standard.entity.inventory.InventoryNeedcheck;

/**
 * 描述：需盘点管理的业务接口
 * @author liuxh
 * @since 2012-11-23
 */
public interface IInventoryNeedCheckBus {
 /**
  * 功能：查询需盘点信息
  * @param needcheckid      需盘点id
  * @param jobid            作业号
  * @param inouttype        入出类型
  * @param cargoSpaceId     作业货位
  * @param createtimeform   生成时间from
  * @param createtimeto     生成时间to
  * @param handleflag       处理标识
  * @param traycode         托盘条码
  * @param taskno           任务号
  * @return
  * @throws Exception
  */
 public String[] getInventNeedCheck(String needcheckid,String jobid,String inouttype,
			  String cargoSpaceId,String createtimeform,String createtimeto,String handleflag,String traycode,String taskno)throws Exception;
 /**
  * 功能：更新库存，货位状体，需盘点状态			                                                
  * @param ids
  * @param user
  * @param flag 1.保存2.作废
  * @return
  * @throws Exception
  */
 public String updateInventAndCargoSpace(String ids,String flag,String user)throws Exception;
 /**
  * 功能：根据ID获取异常单
  * @param needCheckId
  * @return
  * @throws Exception
  */
 public InventoryNeedcheck getINeedcheckInfo(String needCheckId)
	throws Exception;
 
 /**
  * 功能：根据库位ID获取异常单
  * @param needCheckId
  * @return
  * @throws Exception
  */
 public InventoryNeedcheck getINeedcheckInfoByCargospaceId(String cargospaceId)
	throws Exception;
 
}
