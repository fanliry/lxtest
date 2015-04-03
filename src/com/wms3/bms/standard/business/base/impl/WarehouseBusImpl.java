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
 * ����: ����: �ֿ����ҵ����
 * @author hug 2012-3-12
 * @since WMS 3.0
 */
public class WarehouseBusImpl implements IWarehouseBus{
    protected IBaseWarehouseDao baseWarehouseDAO = null;
    
    public WarehouseBusImpl(EntityDAO dao){
        this.baseWarehouseDAO = new BaseWarehouseDaoImpl(dao);
    }
    /**
     * ���ܣ����ܣ����Ӳֿ�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IWarehouseBus#addWarehouse(com.ricosoft.common.dao.dataSource.EntityDAO, com.wms3.bms.standard.entity.base.BaseWarehouse)
     */
    public void addWarehouse(BaseWarehouse warehouse) throws Exception {
        
        //��òֿ����
        String warehouseId = baseWarehouseDAO.getMaxWarehouseNo();
        warehouseId = SeqTool.getCode(Integer.parseInt(warehouseId) + 1, 3);
        warehouse.setWarehouseid(warehouseId);
        baseWarehouseDAO.addWarehouse(warehouse);
    }
    
    /**
     * ����:������вֿ���Ϣ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IWarehouseBus#getWarehouseList()
     */
    public List getWarehouseList() throws Exception {
        return baseWarehouseDAO.getWarehouseList();
    }
    
    /**
     * ���ݲֿ�ID��òֿ�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IWarehouseBus#getWarehouseById(java.lang.String)
     */
    public BaseWarehouse getWarehouseById(String strWarehouseId) throws Exception {
        
        return baseWarehouseDAO.getWarehouseById(strWarehouseId);
    }
    /**
     * ���ܣ�ɾ���ֿ�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IWarehouseBus#deleteWarehouse(java.lang.String)
     */
    public void deleteWarehouse(String strWarehouseId) throws Exception {
        
        baseWarehouseDAO.deleteWarehouse(strWarehouseId);
    }
    
    /**
     * ���ܣ��޸Ĳֿ�
     * @author hug 2012-3-12 
     * @param arg0
     * @throws Exception
     */
    public void updateWarehouse(BaseWarehouse warehouse) throws Exception {
       
        baseWarehouseDAO.updateWarehouse(warehouse);
    }
    
	/**
	 * ����:����������ѯ�ֿ���Ϣ
	 * @param warehousename		�ֿ���
	 * @param warehousetype		�ֿ�����
	 * @return 
	 * @throws Exception
	 */
	public List getWarehouseQuery(String warehousename, String warehousetype) throws Exception {
		
		return baseWarehouseDAO.getWarehouseQuery(warehousename, warehousetype);
	}
	
	/**
     * ����:��ȡ���ݱ����Ƿ���ڵ�ǰ���ݿ�
     * @return
     * @throws Exception
     */
	public List getHaveIscurrent() throws Exception {
		
		return baseWarehouseDAO.getWarehouseCurrent();
	}
	
	/**
	 * ����:���òֿⲻ�ǵ�ǰ�ֿ�
	 * @throws Exception
	 */
	public void updateIsCurrent() throws Exception {
		
		baseWarehouseDAO.updateIsCurrentWh();
		
	}

}
