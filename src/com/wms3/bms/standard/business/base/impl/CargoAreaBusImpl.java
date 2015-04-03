package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.ICargoAreaBus;
import com.wms3.bms.standard.dao.base.IBaseCargoAreaDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoAreaDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargoarea;

/**
 * ����: ��������ҵ����
 * @author fangjie
 * 
 */
public class CargoAreaBusImpl implements ICargoAreaBus {
	
	protected IBaseCargoAreaDao baseCargoAreaDAO = null;

	public CargoAreaBusImpl(EntityDAO dao) {
		this.baseCargoAreaDAO = new BaseCargoAreaDaoImpl(dao);
	}
	
	/**
	 * ����:����������ѯ����
	 * @param warehouseid		�ֿ�ID
	 * @param cargoAreaName		������	
	 * @return
	 * @throws Exception
	 */
	public List getCargoAreaQuery(String warehouseid, String cargoAreaName) throws Exception {
		
		return baseCargoAreaDAO.getCargoAreaQuery(warehouseid, cargoAreaName);
	}

	/**
	 * ����:���ݻ���ID��û���
	 * @param cargoAreaId	����ID
	 * @return
	 * @throws Exception
	 */
	public BaseCargoarea getCargoareaById(String cargoAreaId) throws Exception {
		
		return baseCargoAreaDAO.getCargoareaById(cargoAreaId);
	}

	/**
	 * ����:���ӻ���
	 * @param cargoArea	����
	 * @throws Exception
	 */
	public void addCargoArea(BaseCargoarea cargoarea) throws Exception {
		
		// ��øòֿ�����е�����������
		String cargoAreaId = baseCargoAreaDAO.getMaxCargoAreaId(cargoarea.getWarehouseid());
		
		cargoAreaId = cargoarea.getWarehouseid() + SeqTool.getCode(Integer.parseInt(cargoAreaId.substring(3,6)) + 1, 3);
		cargoarea.setCargoAreaId(cargoAreaId);
		
		baseCargoAreaDAO.addCargoArea(cargoarea);
		
	}

	/**
	 * ����:��ȡ���л����б�
	 * @return
	 * @throws Exception
	 */
	public List getCargoAreaList() throws Exception {

		return baseCargoAreaDAO.getCargoAreaList();
	}

	/**
	 * ����:�޸Ļ���
	 * @param cargoArea	����
	 * @throws Exception
	 */
	public void updateCargoArea(BaseCargoarea cargoarea) throws Exception {
		
		baseCargoAreaDAO.updateCargoArea(cargoarea);
	}

	/**
	 * ����:ɾ������
	 * @param cargoAreaId	����ID
	 * @throws Exception
	 */
	public void deleteCargoArea(String id) throws Exception {
		
		baseCargoAreaDAO.deleteCargoArea(id);
	}

}
