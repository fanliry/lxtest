package com.wms3.bms.standard.business.inventory;

/**
 * 描述:货品调整管理
 * @author yao
 *
 */
public interface ProductAdjustBus 
{
	/**
	 * 功能:获得货品调整单查询的SQL语句
	 * @param status	状态
	 * @param customer	客户
	 * @return
	 */
	public String getProductAdjustHeaderSQL(String status, String customer) throws Exception;
	/**
	 * 功能:获得货品调整单总记录查询的SQL语句
	 * @param status	状态
	 * @param customer	客户
	 * @return
	 */
	public String getProductAdjustHeaderCountSQL(String status, String customer) throws Exception;
}
