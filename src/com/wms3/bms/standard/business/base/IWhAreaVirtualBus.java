package com.wms3.bms.standard.business.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseWhareaVirtual;

/**
 * 
 * 描述: 库区管理业务接口
 * @author fanll
 * @since WMS 3.0
 */
public interface IWhAreaVirtualBus {
	
	/**
	 * 功能:根据条件查询库区
	 * @param warehouseid		仓库ID
	 * @param whAreaName		库区名
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaQuery(String warehouseid, String whAreaName) throws Exception;

	/**
	 * 功能:根据库区ID获得库区
	 * @param whAreaId	库区ID
	 * @return 
	 * @throws Exception
	 */
	public BaseWhareaVirtual getWhareaById(String whAreaId) throws Exception;

	/**
	 * 功能:增加库区
	 * @param wharea	库区
	 * @throws Exception
	 */
	public void addWhArea(BaseWhareaVirtual wharea) throws Exception;

	/**
	 * 功能:获取所有库区列表
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaList() throws Exception;

	/**
	 * 功能:修改库区
	 * @param wharea	库区
	 * @throws Exception
	 */
	public void updateWhArea(BaseWhareaVirtual wharea) throws Exception;

	/**
	 * 功能:删除库区
	 * @param whAreaId	库区ID
	 * @throws Exception
	 */
	public void deleteWhArea(String whAreaId) throws Exception;
	
	/**
	 * 功能:判断仓库下是否有库区
	 * @param id	仓库ID
	 * @throws Exception
	 */
	public boolean isWhHasChildNode(String id) throws Exception;
	/**
	 * 功能:取得指定仓库下的库区(立体库)
	 * @param warehouseid	仓库ID
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaNotTemByWhid(String warehouseid) throws Exception;
	/**
	 * 功能:取得指定仓库下的库区
	 * @param warehouseid	仓库ID
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaByWhid(String warehouseid) throws Exception;
    /**
     * 功能:根据仓库获得暂存区
     * @param warehouseid
     * @return
     * @throws Exception
     */
    public BaseWhareaVirtual getZCgetWhAreaByWhid(String warehouseid)throws Exception;
    
 
}
