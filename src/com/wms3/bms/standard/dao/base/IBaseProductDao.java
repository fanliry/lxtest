package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseProduct;

/**
 * 描述: 产品DAO类接口
 * @author fangjie
 * 
 */
public interface IBaseProductDao {

	/**
	 * 功能:根据产品ID获得产品
	 * @param productid	产品ID
	 * @return
	 * @throws Exception
	 */
	public BaseProduct getProductById(String productid) throws Exception;

	/**
	 * 功能:增加产品
	 * @param product	产品
	 * @throws Exception
	 */
	public void addProduct(BaseProduct product) throws Exception;

	/**
	 * 功能:修改产品
	 * @param product	产品
	 * @throws Exception
	 */
	public void updateProduct(BaseProduct product) throws Exception;

	/**
	 * 功能:删除产品
	 * @param id	产品ID
	 * @throws Exception
	 */
	public void deleteProduct(String id) throws Exception;
    
    /**
     * 功能:根据物品条码获得物品（可能有多个）
     * @author hug 2012-3-14 
     * @param strCode
     * @return
     * @throws Exception
     */
    public List getProductByCode(String strCode) throws Exception;
    
    /**
     * 功能:根据产品编码获得物品
     * @author hug 2012-3-14 
     * @param strCode
     * @return
     * @throws Exception
     */
    public List getProductByCodeInfo(String strCode) throws Exception;
    
    /**
     * 功能:根据物品编码获得物品（可能有多个）
     * @author hug 2012-3-14 
     * @param strCode
     * @return
     * @throws Exception
     */
    public List getProductByProductCode(String strCode) throws Exception;

	public String getMaxProductId(String typeid)throws Exception;

	public String getMaxProductIdz()throws Exception;

}
