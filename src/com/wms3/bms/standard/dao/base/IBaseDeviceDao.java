package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseDevice;

/**
 * ����: �豸DAO��ӿ�
 * @author fangjie
 * 
 */
public interface IBaseDeviceDao {
	
	/**
	 * ����:����������ѯ�豸
	 * @param warehouseid	�ֿ�ID
	 * @param whAreaId 		����ID
	 * @param devicetype 	�豸����
	 * @return
	 * @throws Exception
	 */
	public List getDeviceQuery(String warehouseid, String whAreaId, String devicetype) throws Exception;

	/**
	 * ����:�����豸ID����豸
	 * @param id	�豸ID
	 * @return
	 * @throws Exception
	 */
	public BaseDevice getDeviceById(String id) throws Exception;
	
	/**
	 * ����:��ȡ�����豸�б�
	 * @return
	 * @throws Exception
	 */
	public List getDeviceList() throws Exception;

	/**
	 * ����:�����豸
	 * @param device	�豸
	 * @throws Exception
	 */
	public void addDevice(BaseDevice device) throws Exception;

	/**
	 * ����:�޸��豸
	 * @param device	�豸
	 * @throws Exception
	 */
	public void updateDevice(BaseDevice device) throws Exception;

	/**
	 * ����:ɾ���豸
	 * @param id	�豸ID
	 * @throws Exception
	 */
	public void deleteDevice(String id) throws Exception;

}
