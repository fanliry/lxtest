package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.ICargoAreaBus;
import com.wms3.bms.standard.dao.base.IBaseCargoAreaDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoAreaDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargoarea;

/**
 * 描述: 货区管理业务类
 * @author fangjie
 * 
 */
public class CargoAreaBusImpl implements ICargoAreaBus {
	
	protected IBaseCargoAreaDao baseCargoAreaDAO = null;

	public CargoAreaBusImpl(EntityDAO dao) {
		this.baseCargoAreaDAO = new BaseCargoAreaDaoImpl(dao);
	}
	
	/**
	 * 功能:根据条件查询库区
	 * @param warehouseid		仓库ID
	 * @param cargoAreaName		货区名	
	 * @return
	 * @throws Exception
	 */
	public List getCargoAreaQuery(String warehouseid, String cargoAreaName) throws Exception {
		
		return baseCargoAreaDAO.getCargoAreaQuery(warehouseid, cargoAreaName);
	}

	/**
	 * 功能:根据货区ID获得货区
	 * @param cargoAreaId	货区ID
	 * @return
	 * @throws Exception
	 */
	public BaseCargoarea getCargoareaById(String cargoAreaId) throws Exception {
		
		return baseCargoAreaDAO.getCargoareaById(cargoAreaId);
	}

	/**
	 * 功能:增加货区
	 * @param cargoArea	货区
	 * @throws Exception
	 */
	public void addCargoArea(BaseCargoarea cargoarea) throws Exception {
		
		// 获得该仓库的现有的最大货区编码
		String cargoAreaId = baseCargoAreaDAO.getMaxCargoAreaId(cargoarea.getWarehouseid());
		
		cargoAreaId = cargoarea.getWarehouseid() + SeqTool.getCode(Integer.parseInt(cargoAreaId.substring(3,6)) + 1, 3);
		cargoarea.setCargoAreaId(cargoAreaId);
		
		baseCargoAreaDAO.addCargoArea(cargoarea);
		
	}

	/**
	 * 功能:获取所有货区列表
	 * @return
	 * @throws Exception
	 */
	public List getCargoAreaList() throws Exception {

		return baseCargoAreaDAO.getCargoAreaList();
	}

	/**
	 * 功能:修改货区
	 * @param cargoArea	货区
	 * @throws Exception
	 */
	public void updateCargoArea(BaseCargoarea cargoarea) throws Exception {
		
		baseCargoAreaDAO.updateCargoArea(cargoarea);
	}

	/**
	 * 功能:删除货区
	 * @param cargoAreaId	货区ID
	 * @throws Exception
	 */
	public void deleteCargoArea(String id) throws Exception {
		
		baseCargoAreaDAO.deleteCargoArea(id);
	}

}
