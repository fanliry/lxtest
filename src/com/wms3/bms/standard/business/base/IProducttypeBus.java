package com.wms3.bms.standard.business.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseProducttype;

/**
 * 
 * 描述: 物品类别管理业务接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IProducttypeBus {
	
	/**
	 * 功能:根据条件查询库区
	 * @param ptname	类别名
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeQuery(String ptname) throws Exception;

	/**
	 * 功能:根据物品类别ID获得库区
	 * @param id	物品类别ID
	 * @return 
	 * @throws Exception
	 */
	public BaseProducttype getProducttypeById(String id) throws Exception;

	/**
	 * 功能:增加物品类别
	 * @param producttype	物品类别
	 * @throws Exception
	 */
	public void addProducttype(BaseProducttype producttype) throws Exception;

	/**
	 * 功能:获取所有物品类别列表
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeList() throws Exception;

	/**
	 * 功能:修改物品类别
	 * @param producttype	物品类别
	 * @throws Exception
	 */
	public void updateProducttype(BaseProducttype producttype) throws Exception;

	/**
	 * 功能:删除物品类别
	 * @param id	物品类别ID
	 * @throws Exception
	 */
	public void deleteProducttype(String id) throws Exception;
	
}