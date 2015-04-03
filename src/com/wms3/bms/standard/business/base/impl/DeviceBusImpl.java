package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.IDeviceBus;
import com.wms3.bms.standard.dao.base.IBaseDeviceDao;
import com.wms3.bms.standard.dao.base.impl.BaseDeviceDaoImpl;
import com.wms3.bms.standard.entity.base.BaseDevice;

/**
 * ����: �豸����ҵ����
 * @author fangjie
 * 
 */
public class DeviceBusImpl implements IDeviceBus {
	
	protected IBaseDeviceDao baseDeviceDAO = null;

	public DeviceBusImpl(EntityDAO dao) {
		this.baseDeviceDAO = new BaseDeviceDaoImpl(dao);
	}
	
	/**
	 * ����:����������ѯ�豸
	 * @param warehouseid	�ֿ�ID
	 * @param whAreaId 		����ID
	 * @param devicetype 	�豸����
	 * @return
	 * @throws Exception
	 */
	public List getDeviceQuery(String warehouseid, String whAreaId, String devicetype) throws Exception {
		
		return baseDeviceDAO.getDeviceQuery(warehouseid, whAreaId, devicetype);
	}

	/**
	 * ����:�����豸ID����豸
	 * @param id	�豸ID
	 * @return
	 * @throws Exception
	 */
	public BaseDevice getDeviceById(String id) throws Exception {
		
		return baseDeviceDAO.getDeviceById(id);
	}

	/**
	 * ����:�����豸
	 * @param device	�豸
	 * @throws Exception
	 */
	public void addDevice(BaseDevice device) throws Exception {
		
		baseDeviceDAO.addDevice(device);
		
	}

	/**
	 * ����:��ȡ�����豸�б�
	 * @return
	 * @throws Exception
	 */
	public List getDeviceList() throws Exception {

		return baseDeviceDAO.getDeviceList();
	}

	/**
	 * ����:�޸��豸
	 * @param device	�豸
	 * @throws Exception
	 */
	public void updateDevice(BaseDevice device) throws Exception {
		
		baseDeviceDAO.updateDevice(device);
	}

	/**
	 * ����:ɾ���豸
	 * @param id	�豸ID
	 * @throws Exception
	 */
	public void deleteDevice(String id) throws Exception {
		
		baseDeviceDAO.deleteDevice(id);
	}
}