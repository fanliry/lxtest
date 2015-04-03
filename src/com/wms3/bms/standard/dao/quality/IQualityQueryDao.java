package com.wms3.bms.standard.dao.quality;

/**
 * 描述：质检管理放行查询的dao
 * @author liuxh 2013-3-7
 *
 */
public interface IQualityQueryDao {
	/**
	 * 功能：质检管理放行（分组）
	 *@author liuxh 2013-3-7
	 *@param warehouseid 仓库
	 *@param whareaid  库区
	 *@param lotnumber 批号
	 *@param requestid 申请单号
	 *@param productid 产品id
	 *@param productstatus 产品状态
	 *@param isgroup 是否按批次分组
	 *@return
	 *@throws Exception
	 */
	public String searchInventoryForLotnumber(String warehouseid,String whareaid,String lotnumber,String requestid,String productid,String productstatus)throws Exception;
	/**
	 * 功能：查询某个批号下的入库单
	 *@author liuxh 2013-3-7
	 *@param warehouseid  仓库id
	 *@param lotnumber    批号
	 *@param requestid    申请单id
	 *@param productid    产品id
	 *@param inbound      入库单 
	 *@return
	 *@throws Exception
	 */
	public String searchInventroyForInstock(String warehouseid,String lotnumber,String requestid,String productid,String inbound)throws Exception;
}
