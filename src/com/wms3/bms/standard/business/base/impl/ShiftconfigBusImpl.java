package com.wms3.bms.standard.business.base.impl;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.IShiftconfigBus;
import com.wms3.bms.standard.dao.base.IBaseShiftconfigDao;
import com.wms3.bms.standard.dao.base.impl.BaseShiftconfigDaoImpl;
import com.wms3.bms.standard.entity.base.BaseShiftconfig;

/**
 * 
 * 描述: 班次设置管理业务类
 * @author fangjie
 * @since WMS 3.0
 */
public class ShiftconfigBusImpl implements IShiftconfigBus {
	
	protected IBaseShiftconfigDao baseShiftconfigDAO = null;

	/**
	 * @param dao
	 */
	public ShiftconfigBusImpl(EntityDAO dao) {
		this.baseShiftconfigDAO = new BaseShiftconfigDaoImpl(dao);
	}

	/**
	 * 功能:获得班次设置
	 * @return 
	 */
	public BaseShiftconfig getShiftconfig(){
		
		return baseShiftconfigDAO.getShiftconfig();
	}
	
	/**
	 * 功能:修改班次设置
	 * @param 
	 * @throws Exception
	 */
	public void updateShiftconfig(BaseShiftconfig shiftconfig) throws Exception {
		baseShiftconfigDAO.updateShiftconfig(shiftconfig);
	}

	/**
	 * 功能:增加班次设置
	 * @param shiftconfig	班次设置
	 * @throws Exception
	 */
	public void addShiftconfig(BaseShiftconfig shiftconfig) throws Exception {
		baseShiftconfigDAO.addShiftconfig(shiftconfig);
	}
}