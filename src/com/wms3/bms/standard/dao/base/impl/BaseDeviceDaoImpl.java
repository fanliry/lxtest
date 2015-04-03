package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseDeviceDao;
import com.wms3.bms.standard.entity.base.BaseDevice;

/**
 * ����: �豸DAO��
 * @author fangjie
 * 
 */
public class BaseDeviceDaoImpl implements IBaseDeviceDao {

	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    
	public BaseDeviceDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * ����:����������ѯ�豸
	 * @param warehouseid	�ֿ�ID
	 * @param whAreaId 		����ID
	 * @param devicetype 	�豸����
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
			throw new Exception("����������ѯ�豸����:" + er.getMessage());
		}
		return ls;
	}
	
	/**
	 * ����:�����豸ID����豸
	 * @param id	�豸ID
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
	 * ����:�����豸
	 * @param device	�豸
	 * @throws Exception
	 */
	public void addDevice(BaseDevice device) throws Exception{
		
		m_dao.save(device);
	}

	/**
	 * ����:��ȡ�����豸�б�
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
			throw new Exception("��ѯ�豸�б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:�޸��豸
	 * @param device	�豸
	 * @throws Exception
	 */
	public void updateDevice(BaseDevice device) throws Exception{
		
		m_dao.update(device);
	}

	/**
	 * ����:ɾ���豸
	 * @param id	�豸ID
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
