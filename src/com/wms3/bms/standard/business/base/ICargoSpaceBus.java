package com.wms3.bms.standard.business.base;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseWharea;

/**
 * 描述: 货位管理业务接口
 * @author fangjie
 * 
 */
public interface ICargoSpaceBus {


	/**
	 * 功能:根据货位ID获得货位
	 * @param cargoSpaceId	货位ID
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getCargoSpaceById(String cargoSpaceId) throws Exception;
	
	/**
	 * 功能:增加货位
	 * @param cargoSpace	货位
	 * @throws Exception
	 */
	public void addCargospace(BaseCargospace cargoSpace) throws Exception;

	/**
	 * 功能:修改货位
	 * @param cargoSpace	货位
	 * @throws Exception
	 */
	public void updateCargospace(BaseCargospace cargoSpace) throws Exception;

	/**
	 * 功能:删除货位
	 * @param id	货位ID
	 * @throws Exception
	 */
	public void deletCargospaceById(String id) throws Exception;
	
	/**
	 * 功能:根据条件查询货位
	 * @param warehouseid		仓库ID
	 * @param cargoAreaId		货区ID
	 * @param cargoAlleyId		巷道ID	
	 * @param whAreaId			库区ID
	 * @return
	 * @throws Exception
	 */
	public List getCargoSpaceQuery(String warehouseid, String cargoAreaId, String cargoAlleyId, String whAreaId) throws Exception;
	
	/**
	 * 功能:判断巷道下是否有货位
	 * @param id	巷道ID
	 * @throws Exception
	 */
	public boolean isAlleyHasChildNode(String id) throws Exception;
	
	/**
	 * 功能:判断库区下是否有货位
	 * @param id	库区ID
	 * @throws Exception
	 */
	public boolean isWhAreaHasChildNode(String id) throws Exception;

	/**
	 * 功能:删除巷道下所有货位
	 * @param id	巷道ID
	 * @throws Exception
	 */
	public void deletCargospaceByAlleyId(String strId) throws Exception;

	/**
	 * 功能:根据条件查询货位
	 * @param warehouseid	仓库ID
	 * @param cargoAreaId	货区ID
	 * @param whAreaId		库区ID
	 * @param platoon		排
	 * @param column		列
	 * @param floor			层
	 * @param in_lock		入库锁
	 * @param out_lock		出库锁
	 * @param csstatus		货位状态
	 * @param usage			库位使用
	 * @param handling		存储类型
	 * @param strUrl 		地址
	 * @param maxLine 		分页显示行数
	 * @return
	 * @throws Exception
	 */
	public PagingTool getCsQuery(String warehouseId, String whAreaId, String cargoAreaId, String platoon, String column, String floor, 
			String in_lock, String out_lock, String csstatus, String usage, String handling, String strUrl, int maxLine) throws Exception;

	/**
	 * 功能:根据货位ID获得库存
	 * @param cargoSpaceId	货位ID
	 * @return
	 * @throws Exception
	 */
	public List getStorageBySpaceId(String cargoSpaceId) throws Exception;
	
	/**
	 * 功能:清空一个货位的库存
	 * @param strCargoSpaceId 货位ID
	 * @throws Exception
	 */
	public void clearOneCargoSpace(String cargoSpaceId) throws Exception;
	
	/**
	 * 功能:清空所有货位的库存
	 * @throws Exception
	 */
	public void clearAllCargoSpace() throws Exception;
	
	
	/**
	 * 功能:修改货位状态
	 * @param strSpaceId
	 * @param strStatus
	 * @throws Exception
	 */
	public void updateCargospaceStatus(String strSpaceId, String strStatus) throws Exception;

	/**
	 * 功能:修改货位的入出库锁
	 * @param strIds		货位ID
	 * @param strFlag		1:入库加锁;2:出库加锁;3:入库解锁;4:出库解锁
	 * @param strFlagValue
	 * @throws Exception
	 */
	public void updateCargoSpaceLock(String strIds, String flag, String strFlag) throws Exception;

	/**
	 * 功能:清空货位,保存货位
	 * @param strIds		货位ID
	 * @param strFlag		1:清空货位;2:保存货位
	 * @throws Exception
	 */
	public void saveCargoSpace(String ids, String string) throws Exception;
	
	/**
     * 功能：获取货位已使用的重量
     * @author hug 2012-6-26 
     * @param strSpaceId
     * @return
     * @throws Exception
     */
    public double getSpaceUseWeight(String strSpaceId) throws Exception;
    /**
     * 功能：获取货位已使用的体积
     * @author hug 2012-6-26 
     * @param strSpaceId
     * @return
     * @throws Exception
     */
    public double getSpaceUseVolume(String strSpaceId) throws Exception;


    /**
     * 功能:根据仓库id和库位id获得唯一的库区id
     * @param warehouseid
     * @return
     * @throws Exception
     */
    public String getWhAreaIdByWarehouseidAndCargospaceId(String warehouseid,String cargospaceId)throws Exception;
    
    
    /**
     * 功能:根据仓库id和库位id获得唯一的巷道id
     * @param warehouseid
     * @return
     * @throws Exception
     */
    public String getAlleyIdByWarehouseidAndCargospaceId(String warehouseid,String cargospaceId)throws Exception;
    
	/**
	 * 功能:根据whAreaId获得货位
	 * @param whAreaId
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getCargoSpaceBywhAreaId(String whAreaId) throws Exception;
}
