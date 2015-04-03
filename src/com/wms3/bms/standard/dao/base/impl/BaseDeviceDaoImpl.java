package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseDeviceDao;
import com.wms3.bms.standard.entity.base.BaseDevice;

/**
 * 描述: 设备DAO类
 * @author fangjie
 * 
 */
public class BaseDeviceDaoImpl implements IBaseDeviceDao {

	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;
    
	public BaseDeviceDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * 功能:根据条件查询设备
	 * @param warehouseid	仓库ID
	 * @param whAreaId 		库区ID
	 * @param devicetype 	设备类型
	 * @return
	 * @throws Exception
	 */
	public List getDeviceQuery(String warehouseid, String whAreaId, String devicetype) throws Exception
	{
		List ls = null;
		try
		{
			String strHql = "From BaseDevice as t where 1=1" ;
			
			if(warehouseid != null && warehouseid.trim().length() > 0){
				strHql += " and t.warehouseid = '" + warehouseid + "'";
			}
			if(whAreaId != null && whAreaId.trim().length() > 0){
				strHql += " and t.whAreaId = '" + whAreaId + "'";
			}
			if(devicetype != null && devicetype.trim().length() > 0){
				strHql += " and t.devicetype = '" + devicetype + "'";
			}
			strHql = strHql + " order by t.deviceid";
			ls = m_dao.searchEntities(strHql);		
				
		}catch(Exception er)
		{
			throw new Exception("根据条件查询设备出错:" + er.getMessage());
		}
		return ls;
	}
	
	/**
	 * 功能:根据设备ID获得设备
	 * @param id	设备ID
	 * @return
	 * @throws Exception
	 */
	public BaseDevice getDeviceById(String id) throws Exception
	{
		if(id != null || !id.equals(""))
		{
			String strSql = " from BaseDevice as t where t.deviceid='" + id + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseDevice)ls.get(0);
			}
		}
		return null;
	}

	/**
	 * 功能:增加设备
	 * @param device	设备
	 * @throws Exception
	 */
	public void addDevice(BaseDevice device) throws Exception{
		
		m_dao.save(device);
	}

	/**
	 * 功能:获取所有设备列表
	 * @return
	 * @throws Exception
	 */
	public List getDeviceList() throws Exception
	{
		List ls = null;
		try
		{
			String strHql = "From BaseDevice as t where 1=1 order by t.deviceid";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("查询设备列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:修改设备
	 * @param device	设备
	 * @throws Exception
	 */
	public void updateDevice(BaseDevice device) throws Exception{
		
		m_dao.update(device);
	}

	/**
	 * 功能:删除设备
	 * @param id	设备ID
	 * @throws Exception
	 */
	public void deleteDevice(String id) throws Exception
	{
		if(id != null)
		{
			String strSql  = " delete BaseDevice as t where t.deviceid='" + id + "'";

			m_dao.deleteSql(strSql);
		}	
	}
	
}
