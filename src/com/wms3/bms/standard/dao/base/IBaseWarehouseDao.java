package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseWarehouse;

/**
 * 
 * 描述: 仓库DAO类接口
 * @author hug 2012-3-12
 * @since WMS 3.0
 */
public interface IBaseWarehouseDao {
    /**
     * 功能:增加仓库
     * @author hug 2012-3-12 
     * @param warehouse
     * @throws Exception
     */
    public void addWarehouse(BaseWarehouse warehouse) throws Exception;
    /**
     * 功能:获得最大的一个仓库编码
     * @author hug 2012-3-12 
     * @return
     * @throws Exception
     */
    public String getMaxWarehouseNo() throws Exception;
    
    /**
     * 功能:获得所有仓库信息
     * @return
     * @throws Exception
     */
    public List getWarehouseList() throws Exception;
    
    /**
     * 功能:根据仓库ID获得仓库
     * @param strWarehouseId    仓库ID
     * @return
     * @throws Exception
     */
    public BaseWarehouse getWarehouseById(String strWarehouseId) throws Exception;
    
    /**
     * 功能:删除仓库
     * @param strWarehouseId    仓库ID
     * @throws Exception
     */
    public void deleteWarehouse(String strWarehouseId) throws Exception;
    
    /**
     * 功能：修改仓库
     * @author hug 2012-3-12 
     * @param warehouse 仓库
     * @throws Exception
     */
    public void updateWarehouse(BaseWarehouse warehouse) throws Exception;
    
	/**
	 * 功能:根据条件查询仓库信息
	 * @param warehousename		仓库名
	 * @param warehousetype		仓库类型
	 * @return 
	 * @throws Exception
	 */
	public List getWarehouseQuery(String warehousename, String warehousetype) throws Exception;
	
	/**
     * 功能:获取数据表中是否存在当前数据库
     * @return
     * @throws Exception
     */
	public List getWarehouseCurrent() throws Exception;
	
	/**
	 * 功能:设置仓库不是当前仓库
	 * @throws Exception
	 */
	public void updateIsCurrentWh() throws Exception;

}
