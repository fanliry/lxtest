package com.wms3.bms.standard.business.inventory;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;

/**
 * 描述: 库存
 * 冻结/释放业务接口
 * @author fangjie
 * 
 */
public interface IInventoryHoldBus {

	/**
	 * 功能:根据条件查询冻结记录的列表
	 * @param cargospaceid	货位
	 * @param lotid			批次属性
	 * @param ownerid		货主
	 * @param productid		品名
	 * @param holdcode		冻结原因
	 * @param holdby		冻结方法
	 * @param dateon_from	冻结日期
	 * @param dateon_to		冻结日期
	 * @param dateoff_from	释放日期
	 * @param dateoff_to	释放日期
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getHoldList(String cargospaceid, String productid, String holdcode, String holdby, 
			String dateon_from, String dateon_to, String dateoff_from, String dateoff_to, String strUrl, int maxLine) throws Exception;
	
	/**
	 * 功能:根据条件查询库存记录的列表
	 * @param ownerid		货主
	 * @param productid		品名
	 * @param cargospaceid	货位
	 * @param lotid			批次属性
	 * @param traycode		托盘条码
	 * @param boxcode		箱条码
	 * @param productcode	产品条码
	 * @return 
	 * @throws Exception
	 */
	public List getStorageList( String productid, String cargospaceid, 
			String traycode, String boxcode, String productcode) throws Exception;

	/**
	 * 功能:生成冻结记录
	 * @param ids			库存ids
	 * @param holdcode		冻结原因
	 * @param holdby		冻结方法
	 * @param dateoff		释放日期
	 * @param holdreason 	原因描述
	 * @param strUserCode 	用户代码
	 * @return 
	 * @throws Exception
	 */
	public String addHold(String ids, String holdcode, String holdby, String dateoff, String holdreason, String strUserCode) throws Exception;

	/**
	 * 功能:释放冻结记录
	 * @param ids			ids(冻结ID+库存ID)
	 * @param strUserCode 	用户代码
	 * @return 
	 * @throws Exception
	 */
	public String updateHold(String ids, String strUserCode) throws Exception;

	
    
    

}
