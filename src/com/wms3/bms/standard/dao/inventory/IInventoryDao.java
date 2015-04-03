package com.wms3.bms.standard.dao.inventory;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * 
 * 描述: 库存DAO类接口
 * @author hug 2012-4-25
 * @since WMS 3.0
 */
public interface IInventoryDao  extends IDao{
    /**
     * 功能：根据库存ID获得库存
     * @author hug 2012-4-25 
     * @param strDetailId
     * @return
     * @throws Exception
     */
    public InventoryStorage getInventoryById(String strDetailId) throws Exception;
    
    /**
     * 功能:判断货位和该货位的巷道是否出库锁了 true：锁定；false：没锁
     * @author hug 2012-5-18 
     * @param strSpace
     * @return
     * @throws Exception
     */
    public boolean isSpaceAlleyOutLock(String strSpace) throws Exception;
    
    /**
     * 功能：获得作业详细行数
     * @author hug 2012-5-18 
     * @param strJobId  作业ID
     * @return
     * @throws Exception
     */
    public int getCountJobDetailRow(String strJobId) throws Exception;
    
    public List<InventoryStorage> searchInventory(String strProductId, String strBatch, String strPrintdate, String strStype, String strMstatus, String strCustomerId) throws Exception;
    
    public List<InventoryStorage> queryInventory(String warehouseid, String whareaid, String strProductId, String strBatchId, String strBatch, String strPrintdate, String strStype, String strMstatus, String ownerid) throws Exception;
    public List<InventoryStorage> groupInventory(String warehouseid, String whareaid, String strProductId, String strBatchId, String strBatch, String strPrintdate, String strStype, String strMstatus, String ownerid) throws Exception;

	/**
	 * 功能:根据货位ID获得库存列表
	 * @param strCargoSpaceId	货位ID
	 * @return
	 * @throws Exception
	 */
	public List<InventoryStorage> getInventoryByCsId(String strCargoSpaceId) throws Exception;
	/**
	 * 功能：根据作业id，托盘条码获得库存
	 * @param jobid 作业ID
	 * @param trayCode 托盘条码
	 * @return
	 * @throws Exception
	 */
	public List<InventoryStorage> getInventoryByJobIdAndTrayCode(String jobid,String trayCode)throws Exception;
	/**
	 * 功能：根据作业产品Id，托盘条码，货位货物库存信息
	 * @param proId 产品ID
	 * @param trayCode 托盘条码
	 * @param cargoSpaceId 货位
	 * @return
	 * @throws Exception
	 */
	public InventoryStorage getInventoryByProIdAndTrayCodeAndCSId(String proId,String trayCode,String cargoSpaceId)throws Exception;
	
	public InventoryStorage getInventoryByIdAndInventoryType(String inventoryId) throws Exception;
  
}
