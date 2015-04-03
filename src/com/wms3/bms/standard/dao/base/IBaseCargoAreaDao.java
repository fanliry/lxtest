package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseCargoarea;

/**
 * ����: ����DAO��ӿ�
 * @author fangjie
 * 
 */
public interface IBaseCargoAreaDao {
	
	/**
	 * ����:����������ѯ����
	 * @param warehouseid		�ֿ�ID
	 * @param cargoAreaName		������	
	 * @return
	 * @throws Exception
	 */
	public List getCargoAreaQuery(String warehouseid, String cargoAreaName) throws Exception;

	/**
	 * ����:���ݻ���ID��û���
	 * @param cargoAreaId	����ID
	 * @return
	 * @throws Exception
	 */
	public BaseCargoarea getCargoareaById(String cargoAreaId) throws Exception;

	/**
	 * ����:��øòֿ������һ����������
	 * @param warehouseid 
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public String getMaxCargoAreaId(String warehouseid) throws Exception;

	/**
	 * ����:���ӻ���
	 * @param cargoArea	����
	 * @throws Exception
	 */
	public void addCargoArea(BaseCargoarea cargoarea) throws Exception;

	/**
	 * ����:��ȡ���л����б�
	 * @return
	 * @throws Exception
	 */
	public List getCargoAreaList() throws Exception;

	/**
	 * ����:�޸Ļ���
	 * @param cargoArea	����
	 * @throws Exception
	 */
	public void updateCargoArea(BaseCargoarea cargoarea) throws Exception;

	/**
	 * ����:ɾ������
	 * @param cargoAreaId	����ID
	 * @throws Exception
	 */
	public void deleteCargoArea(String id) throws Exception;

}