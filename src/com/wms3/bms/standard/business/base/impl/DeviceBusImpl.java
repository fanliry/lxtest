package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.IDeviceBus;
import com.wms3.bms.standard.dao.base.IBaseDeviceDao;
import com.wms3.bms.standard.dao.base.impl.BaseDeviceDaoImpl;
import com.wms3.bms.standard.entity.base.BaseDevice;

/**
 * 描述: 设备管理业务类
 * @author fangjie
 * 
 */
public class DeviceBusImpl implements IDeviceBus {
	
	protected IBaseDeviceDao baseDeviceDAO = null;

	public DeviceBusImpl(EntityDAO dao) {
		this.baseDeviceDAO = new BaseDeviceDaoImpl(dao);
	}
	
	/**
	 * 功能:根据条件查询设备
	 * @param warehouseid	仓库ID
	 * @param whAreaId 		库区ID
	 * @param devicetype 	设备类型
	 * @return
	 * @throws Exception
	 */
	public List getDeviceQuery(String warehouseid, String whAreaId, String devicetype) throws Exception {
		
		return baseDeviceDAO.getDeviceQuery(warehouseid, whAreaId, devicetype);
	}

	/**
	 * 功能:根据设备ID获得设备
	 * @param id	设备ID
	 * @return
	 * @throws Exception
	 */
	public BaseDevice getDeviceById(String id) throws Exception {
		
		return baseDeviceDAO.getDeviceById(id);
	}

	/**
	 * 功能:增加设备
	 * @param device	设备
	 * @throws Exception
	 */
	public void addDevice(BaseDevice device) throws Exception {
		
		baseDeviceDAO.addDevice(device);
		
	}

	/**
	 * 功能:获取所有设备列表
	 * @return
	 * @throws Exception
	 */
	public List getDeviceList() throws Exception {

		return baseDeviceDAO.getDeviceList();
	}

	/**
	 * 功能:修改设备
	 * @param device	设备
	 * @throws Exception
	 */
	public void updateDevice(BaseDevice device) throws Exception {
		
		baseDeviceDAO.updateDevice(device);
	}

	/**
	 * 功能:删除设备
	 * @param id	设备ID
	 * @throws Exception
	 */
	public void deleteDevice(String id) throws Exception {
		
		baseDeviceDAO.deleteDevice(id);
	}
}