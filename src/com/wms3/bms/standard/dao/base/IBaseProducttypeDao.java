package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseProducttype;

/**
 * 
 * 描述: 产品类别DAO类接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseProducttypeDao {

	/**
	 * 功能:根据条件查询库区
	 * @param ptname	类别名
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeQuery(String ptname) throws Exception;

	/**
	 * 功能:根据产品类别ID获得产品类别
	 * @param id	产品类别ID
	 * @return 
	 * @throws Exception
	 */
	public BaseProducttype getProducttypeById(String id) throws Exception;

	/**
	 * 功能:获得同一个父类别下最大的一个产品类别编码
	 * @param parentid	父类别ID
	 * @return
	 * @throws Exception
	 */
	public String getMaxProducttypeNo(String parentid) throws Exception;

	/**
	 * 功能:增加产品类别
	 * @param producttype	产品类别
	 * @throws Exception
	 */
	public void addProducttype(BaseProducttype producttype) throws Exception;

	/**
	 * 功能:获取所有产品类别列表
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeList() throws Exception;

	/**
	 * 功能:修改产品类别
	 * @param producttype	产品类别
	 * @throws Exception
	 */
	public void updateProducttype(BaseProducttype producttype) throws Exception;

	/**
	 * 功能:删除产品类别
	 * @param id	产品类别ID
	 * @throws Exception
	 */
	public void deleteProducttype(String id) throws Exception;

}
