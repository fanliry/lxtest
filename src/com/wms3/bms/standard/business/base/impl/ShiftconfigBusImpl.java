package com.wms3.bms.standard.business.base.impl;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.IShiftconfigBus;
import com.wms3.bms.standard.dao.base.IBaseShiftconfigDao;
import com.wms3.bms.standard.dao.base.impl.BaseShiftconfigDaoImpl;
import com.wms3.bms.standard.entity.base.BaseShiftconfig;

/**
 * 
 * ����: ������ù���ҵ����
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
	 * ����:��ð������
	 * @return 
	 */
	public BaseShiftconfig getShiftconfig(){
		
		return baseShiftconfigDAO.getShiftconfig();
	}
	
	/**
	 * ����:�޸İ������
	 * @param 
	 * @throws Exception
	 */
	public void updateShiftconfig(BaseShiftconfig shiftconfig) throws Exception {
		baseShiftconfigDAO.updateShiftconfig(shiftconfig);
	}

	/**
	 * ����:���Ӱ������
	 * @param shiftconfig	�������
	 * @throws Exception
	 */
	public void addShiftconfig(BaseShiftconfig shiftconfig) throws Exception {
		baseShiftconfigDAO.addShiftconfig(shiftconfig);
	}
}