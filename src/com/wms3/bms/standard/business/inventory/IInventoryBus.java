package com.wms3.bms.standard.business.inventory;

import java.util.List;

import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * 描述: 库存查询业务接口
 * @author fangjie
 * 
 */
public interface IInventoryBus {
    
    /**
     * 功能:库存管理->库存移动的SQL语句
     * @param warehouseid       仓库ID
     * @param whAreaId          库区ID
     * @param cargoAlleyId      巷道ID
     * @param platoon           排
     * @param column            列
     * @param floor             层
     * @param customer_id       货主
     * @param package_id        品名规格
     * @param tray_code         托盘条码
     * @param lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, lotatt6      批次属性
     * @param lotatt7, lotatt8, lotatt9, lotatt10, lotatt11, lotatt12   批次属性 
     * @param sttLotId          批次ID
     * @param tray_code         托盘条码
     * @param indate_from       入库日期from
     * @param indate_to         入库日期to
     * @return
     * @throws Exception
     */
    public String getInventoryHQL(String warehouseid, String whAreaId, String cargoAlleyId, String platoon, String column, String floor, 
            String package_id, String tray_code,String indate_from ,String indate_to,String productcode) throws Exception;
/*	 *//**
	  * 功能:质检管理->状态转换的SQL语句
	  *@author liuxh 2013-3-8
	  *@param warehouseid 仓库id
	  *@param whareaid    库区
	  *@param lotnumber   批号
	  *@param requestid   申请单
	  *@param productid   产品id
	  *@param productstatus 产品状态
	  *@return
	  *@throws Exception
	  *//*
    public String getInventoryStatusHQL(String warehouseid,String whareaid,String lotnumber,String requestid,String productid,String productstatus) throws Exception;*/
    /**
     * 功能:库存管理->库存统计的SQL语句
     * @param warehouseid       仓库ID
     * @param whAreaId          库区ID
     * @param customer_id       货主
     * @param package_id        品名
     * @param indate_from, indate_to      入库日期
     * @param lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, lotatt6      批次属性
     * @param lotatt7, lotatt8, lotatt9, lotatt10, lotatt11, lotatt12   批次属性 
     * @param sttLotId          批次ID
     * @param total_items       统计项目
     * @return
     * @throws Exception
	 */
	public String[] getInventoryTotalQuery(String warehouseid, String whAreaId, String customer_id, String package_id, String indate_from, String indate_to, 
			String total_items) throws Exception;
    
    /**
     * 功能: 出库分配->统计库存的数量
     * @author hug 2012-9-21 
     * @param warehouseid   仓库ID
     * @param whAreaId      库区ID
     * @param ownerid   货主ID
     * @param productid 品名ID
     * @param packid    包装ID
     * @param lotatt1   批次属性1
     * @param lotatt2   批次属性2
     * @param lotatt3   批次属性3
     * @param lotatt4   批次属性4
     * @param lotatt5   批次属性5
     * @param lotatt6   批次属性6
     * @param lotatt7   批次属性7
     * @param lotatt8   批次属性8
     * @param lotatt9   批次属性9
     * @param lotatt10  批次属性10
     * @param lotatt11  批次属性11
     * @param lotatt12  批次属性12
     * @return
     * @throws Exception
     */
    public Object[] getGroupInventoryNum(String warehouseid, String whAreaId, String ownerid, String productid, String packid) throws Exception;
    /**
     * 功能：库存查询的sql
     *@author liuxh 2013-3-12
     *@param warehouseid 仓库
     *@param whAreaId    库区
     *@param cargoAlleyId 巷道
     *@param platoon      排
     *@param column       列
     *@param floor        层
     *@param lotnumber    批号
     *@param productid  产品id
     *@param indate_from 开始时间
     *@param indate_to   结束时间
     *@param tray_code   托盘条码
     *@param status      状态
     *@param requestid   申请单
     *@param instockid   入库单
     *@param productstatus   产品状态
     *@param productcode   产品编码
     *@param kcstatus   库存状态
     *@return
     *@throws Exception
     */
	public String[] getInventoryQuerynew(String warehouseid, String whAreaId,
			String cargoAlleyId, String platoon, String column, String floor,
			String lotnumber, String productid, String indateFrom,
			String indateTo, String trayCode, String status, String requestid,
			String instockid, String productstatus, String productcode,
			String kcstatus) throws Exception;
	/**
	 * 出库备货->按批号拣货出库->暂存列表
	 * @param warehouseid
	 * @param whAreaId
	 * @param Virtualwhid
	 * @param productid
	 * @param lotinfo
	 * @param printdate
	 * @param trayCode
	 * @return
	 * @throws Exception
	 */
	public List<InventoryStorage> getZCInventoryByCKInfo(String warehouseid, String whAreaId,String Virtualwhid,String productid,String lotinfo,String printdate, String trayCode) throws Exception;
    /**
     * 功能：库存查询的sql
     *@author liuxh 2013-3-12
     *@param warehouseid 仓库
     *@param whAreaId    库区
     *@param cargoAlleyId 巷道
     *@param platoon      排
     *@param column       列
     *@param floor        层
     *@param lotnumber    批号
     *@param productid  产品id
     *@param indate_from 开始时间
     *@param indate_to   结束时间
     *@param tray_code   托盘条码
     *@param status      状态
     *@param requestid   申请单
     *@param instockid   入库单
     *@param productstatus   产品状态
     *@param productcode   产品编码
     *@param kcstatus   库存状态
     *@return
     *@throws Exception
     */
	public String[] getInventoryQuerynew(String warehouseid, String whAreaId,
			String Virtualwhid, String lotinfo, String producttype,
			String cargoAlleyId, String platoon, String column, String floor,
			String lotnumber, String productid, String indateFrom,
			String indateTo, String trayCode, String status, String requestid,
			String instockid, String productstatus, String productcode) throws Exception;
	
	/**
	 * 功能：(库存管理)物品查询
	 * @param warehouseid
	 * @param whAreaId
	 * @param Virtualwhid
	 * @param lotinfo
	 * @param productid
	 * @param indateFrom
	 * @param indateTo
	 * @param productstatus
	 * @return
	 * @throws Exception
	 */
	public String getInventoryWpQuerynew(String warehouseid, String whAreaId, String Virtualwhid, String lotinfo, String productid, String indateFrom,String indateTo,String productstatus) throws Exception;
	
	/**
	 * 功能：(库存管理)物品查询
	 * @param warehouseid
	 * @param whAreaId
	 * @param Virtualwhid
	 * @param lotinfo
	 * @param productid
	 * @param indateFrom
	 * @param indateTo
	 * @param productstatus
	 * @return
	 * @throws Exception
	 */
	public List<InventoryStorage> getInventoryWpQuery(String warehouseid, String whAreaId, String Virtualwhid, String lotinfo, String productid, String indateFrom,String indateTo,String productstatus) throws Exception;
	
}
