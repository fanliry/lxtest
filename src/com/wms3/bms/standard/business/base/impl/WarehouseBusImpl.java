package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.IWarehouseBus;
import com.wms3.bms.standard.dao.base.IBaseWarehouseDao;
import com.wms3.bms.standard.dao.base.impl.BaseWarehouseDaoImpl;
import com.wms3.bms.standard.entity.base.BaseWarehouse;

/**
 * 
 * 描述: 描述: 仓库管理业务类
 * @author hug 2012-3-12
 * @since WMS 3.0
 */
public class WarehouseBusImpl implements IWarehouseBus{
    protected IBaseWarehouseDao baseWarehouseDAO = null;
    
    public WarehouseBusImpl(EntityDAO dao){
        this.baseWarehouseDAO = new BaseWarehouseDaoImpl(dao);
    }
    /**
     * 功能：功能：增加仓库
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IWarehouseBus#addWarehouse(com.ricosoft.common.dao.dataSource.EntityDAO, com.wms3.bms.standard.entity.base.BaseWarehouse)
     */
    public void addWarehouse(BaseWarehouse warehouse) throws Exception {
        
        //获得仓库编码
        String warehouseId = baseWarehouseDAO.getMaxWarehouseNo();
        warehouseId = SeqTool.getCode(Integer.parseInt(warehouseId) + 1, 3);
        warehouse.setWarehouseid(warehouseId);
        baseWarehouseDAO.addWarehouse(warehouse);
    }
    
    /**
     * 功能:获得所有仓库信息
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IWarehouseBus#getWarehouseList()
     */
    public List getWarehouseList() throws Exception {
        return baseWarehouseDAO.getWarehouseList();
    }
    
    /**
     * 根据仓库ID获得仓库
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IWarehouseBus#getWarehouseById(java.lang.String)
     */
    public BaseWarehouse getWarehouseById(String strWarehouseId) throws Exception {
        
        return baseWarehouseDAO.getWarehouseById(strWarehouseId);
    }
    /**
     * 功能：删除仓库
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IWarehouseBus#deleteWarehouse(java.lang.String)
     */
    public void deleteWarehouse(String strWarehouseId) throws Exception {
        
        baseWarehouseDAO.deleteWarehouse(strWarehouseId);
    }
    
    /**
     * 功能：修改仓库
     * @author hug 2012-3-12 
     * @param arg0
     * @throws Exception
     */
    public void updateWarehouse(BaseWarehouse warehouse) throws Exception {
       
        baseWarehouseDAO.updateWarehouse(warehouse);
    }
    
	/**
	 * 功能:根据条件查询仓库信息
	 * @param warehousename		仓库名
	 * @param warehousetype		仓库类型
	 * @return 
	 * @throws Exception
	 */
	public List getWarehouseQuery(String warehousename, String warehousetype) throws Exception {
		
		return baseWarehouseDAO.getWarehouseQuery(warehousename, warehousetype);
	}
	
	/**
     * 功能:获取数据表中是否存在当前数据库
     * @return
     * @throws Exception
     */
	public List getHaveIscurrent() throws Exception {
		
		return baseWarehouseDAO.getWarehouseCurrent();
	}
	
	/**
	 * 功能:设置仓库不是当前仓库
	 * @throws Exception
	 */
	public void updateIsCurrent() throws Exception {
		
		baseWarehouseDAO.updateIsCurrentWh();
		
	}

}
