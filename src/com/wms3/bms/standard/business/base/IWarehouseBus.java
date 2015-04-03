package com.wms3.bms.standard.business.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseWarehouse;

/**
 * 
 * ����: �ֿ����ҵ��ӿ�
 * @author hug 2012-3-12
 * @since WMS 3.0
 */
public interface IWarehouseBus {
    /**
     * ���ܣ����Ӳֿ�
     * @author hug 2012-3-12 
     * @param warehouse
     * @throws Exception
     */
    public void addWarehouse(BaseWarehouse warehouse) throws Exception;
    
	/**
	 * ����:����������ѯ�ֿ���Ϣ
	 * @param warehousename		�ֿ���
	 * @param warehousetype		�ֿ�����
	 * @return 
	 * @throws Exception
	 */
	public List getWarehouseQuery(String warehousename, String warehousetype) throws Exception;
	
    /**
     * ����:������вֿ���Ϣ
     * @return
     * @throws Exception
     */
    public List getWarehouseList() throws Exception;
    
    /**
     * ����:���ݲֿ�ID��òֿ�
     * @param strWarehouseId    �ֿ�ID
     * @return
     * @throws Exception
     */
    public BaseWarehouse getWarehouseById(String strWarehouseId) throws Exception;
    
    /**
     * ����:ɾ���ֿ�
     * @param strWarehouseId    �ֿ�ID
     * @throws Exception
     */
    public void deleteWarehouse(String strWarehouseId) throws Exception;
    
    /**
     * ���ܣ��޸Ĳֿ�
     * @author hug 2012-3-12 
     * @param warehouse �ֿ�
     * @throws Exception
     */
    public void updateWarehouse(BaseWarehouse warehouse) throws Exception;

    /**
     * ����:��ȡ���ݱ����Ƿ���ڵ�ǰ���ݿ�
     * @return
     * @throws Exception
     */
	public List getHaveIscurrent() throws Exception;

	/**
	 * ����:���òֿⲻ�ǵ�ǰ�ֿ�
	 * @throws Exception
	 */
	public void updateIsCurrent() throws Exception;
}
