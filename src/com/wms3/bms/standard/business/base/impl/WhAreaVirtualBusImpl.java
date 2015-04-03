package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.IWhAreaVirtualBus;
import com.wms3.bms.standard.dao.base.IBaseWhAreaVirtualDao;
import com.wms3.bms.standard.dao.base.impl.BaseWhAreaVirtualDaoImpl;
import com.wms3.bms.standard.entity.base.BaseWhareaVirtual;

/**
 * 
 * 描述: 库区管理业务类
 * @author fanll
 * @since WMS 3.0
 */
public class WhAreaVirtualBusImpl implements IWhAreaVirtualBus {
	
	protected IBaseWhAreaVirtualDao baseWhAreaVirtualDAO = null;

	/**
	 * @param dao
	 */
	public WhAreaVirtualBusImpl(EntityDAO dao) {
		this.baseWhAreaVirtualDAO = new BaseWhAreaVirtualDaoImpl(dao);
	}


	/**
	 * 功能:根据条件查询库区
	 * @param warehouseid		仓库ID
	 * @param whAreaName		库区名
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaQuery(String warehouseid, String whAreaName) throws Exception {
		
		return baseWhAreaVirtualDAO.getWhAreaQuery(warehouseid, whAreaName);
	}

	/**
	 * 功能:根据库区ID获得库区
	 * @param whAreaId	库区ID
	 * @return 
	 * @throws Exception
	 */
	public BaseWhareaVirtual getWhareaById(String whAreaId) throws Exception {
		
		return baseWhAreaVirtualDAO.getWhareaById(whAreaId);
	}

	/**
	 * 功能:增加库区
	 * @param wharea	库区
	 * @throws Exception
	 */
	public void addWhArea(BaseWhareaVirtual wharea) throws Exception {
		
		// 获得该仓库的现有的最大库区编码
		String whAreaId = baseWhAreaVirtualDAO.getMaxWhareaNo(wharea.getWarehouseid());
		
		whAreaId = wharea.getWarehouseid() + SeqTool.getCode(Integer.parseInt(whAreaId.substring(3,7)) + 1, 4);
		wharea.setwhAreaId(whAreaId);
		
		baseWhAreaVirtualDAO.addWhArea(wharea);
		
	}
	
	/**
	 * 功能:获取所有库区列表
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaList() throws Exception {
		
		return baseWhAreaVirtualDAO.getWhAreaList();
	}

	/**
	 * 功能:修改库区
	 * @param wharea	库区
	 * @throws Exception
	 */
	public void updateWhArea(BaseWhareaVirtual wharea) throws Exception {
		
		baseWhAreaVirtualDAO.updateWhArea(wharea);
		
	}

	/**
	 * 功能:删除库区
	 * @param whAreaId	库区ID
	 * @throws Exception
	 */
	public void deleteWhArea(String whAreaId) throws Exception {
		
		baseWhAreaVirtualDAO.deleteWhArea(whAreaId);
	}

	/**
	 * 功能:判断仓库下是否有库区
	 * @param id	仓库ID
	 * @throws Exception
	 */
	public boolean isWhHasChildNode(String id) throws Exception {
		
		return baseWhAreaVirtualDAO.isWhHasChildNode(id);
	}

	/**
	 * 功能:取得指定仓库下的库区
	 * @param warehouseid	仓库ID
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaByWhid(String warehouseid) throws Exception {
		
		return baseWhAreaVirtualDAO.getWhAreaByWhid(warehouseid);
	}
	/**
	 * 功能:取得指定仓库下的库区(立体库)
	 * @param warehouseid	仓库ID
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaNotTemByWhid(String warehouseid) throws Exception {
		
		return baseWhAreaVirtualDAO.getWhAreaByWhidNotTem(warehouseid);
	}
    /**
     * 根据仓库获得暂存区
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IWhAreaBus#getZCgetWhAreaByWhid(java.lang.String)
     */
    public BaseWhareaVirtual getZCgetWhAreaByWhid(String warehouseid) throws Exception {
        return baseWhAreaVirtualDAO.getZCgetWhAreaByWhid(warehouseid);
    }
}