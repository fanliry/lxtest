package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseCargospace;

/**
 * 描述: 货位DAO类接口
 * @author fangjie
 * 
 */
public interface IBaseCargoSpaceDao {

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
	 * 功能:删除巷道下所有货位
	 * @param id	巷道ID
	 * @throws Exception
	 */
	public void deletCargospaceByAlleyId(String id) throws Exception;
	
    /**
     * 功能：修改货位状态
     * @author hug 2012-4-27 
     * @param strSpaceId    货位ID
     * @param strStatus     货位状态
     * @throws Exception
     */
    public void updateCargospaceStatus(String strSpaceId, String strStatus) throws Exception;

	/**
	 * 功能:清空一个货位的库存
	 * @param strCargoSpaceId 货位ID
	 * @throws Exception
	 */
	public void clearOneCS(String cargoSpaceId) throws Exception;

	/**
	 * 功能:清空所有货位的库存
	 * @throws Exception
	 */
	public void clearAllCS() throws Exception;

	/**
	 * 功能:修改货位的入出库锁
	 * @param strIds		货位ID
	 * @param strFlag		1:入库加锁;2:出库加锁;3:入库解锁;4:出库解锁
	 * @param strFlagValue
	 * @throws Exception
	 */
	public void lockCS(String strIds, String flag, String strFlag) throws Exception;

	/**
	 * 功能:清空货位,保存货位
	 * @param strIds		货位ID
	 * @param strFlag		1:清空货位;2:保存货位
	 * @throws Exception
	 */
	public void saveCS(String ids, String flag) throws Exception;
	
	/**
	 * 功能:获得指定货位相邻的货位
	 * @param cargoSpaceId	货位ID
	 * @param 1-上 2-下 3-左 4-右 
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getNearCargospace(String cargoSpaceId, String position) throws Exception;


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
     * 功能：根据货位获取货位信息
     * @return
     * @throws Exception 
     */
    public BaseCargospace GetCargospace(String platoon, String column,String floor,String warehouseid) throws Exception;
    
	/**
	 * 功能：获取一个空货位
	 * @param warehouseid
	 * @param WhAreaId
	 * @return
	 */
    public BaseCargospace getNullSpaceId(String warehouseid, String WhAreaId);
    
    /**
     * 功能：根据货位获取货位信息
     * @return
     * @throws Exception 
     */
    public BaseCargospace GetWhCargospace(String platoon, String column,String floor,String warehouseid,String whAreaId);
    
	/**
	 * 功能:根据whAreaId获得货位
	 * @param whAreaId
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getCargoSpaceBywhAreaId(String whAreaId) throws Exception;
}
