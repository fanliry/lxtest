package com.wms3.bms.standard.business.base;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseProduct;

/**
 * 描述: 物品管理业务接口
 * @author fangjie
 * 
 */
public interface IProductBus {

	/**
	 * 功能:根据物品ID获得物品
	 * @param productid	物品ID
	 * @return
	 * @throws Exception
	 */
	public BaseProduct getProductById(String productid) throws Exception;
	
	/**
	 * 功能:增加物品
	 * @param product	物品
	 * @throws Exception
	 */
	public void addProduct(BaseProduct product) throws Exception;

	/**
	 * 功能:修改物品
	 * @param product	物品
	 * @throws Exception
	 */
	public void updateProduct(BaseProduct product) throws Exception;

	/**
	 * 功能:删除物品
	 * @param id	物品ID
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
    
	/**
	 * 功能:根据条件查询物品
	 * @param productname	物品名称
     * @param productcode   产品编码
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getProductQuery(String productname, String productcode, String producttype,String strUrl, int maxLine) throws Exception;
    /**
     * 功能: 查询所有物品     
	 * @return 
	 * @throws Exception
	 */
    public List searchEntitieAll() throws Exception;
}
