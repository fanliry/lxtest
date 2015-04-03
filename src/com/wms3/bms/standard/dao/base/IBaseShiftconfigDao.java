package com.wms3.bms.standard.dao.base;

import com.wms3.bms.standard.entity.base.BaseShiftconfig;

/**
 * 
 * 描述: 班次设置DAO类接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseShiftconfigDao {

	/**
	 * 功能:获得班次设置
	 * @return 
	 * @throws Exception
	 */
	public BaseShiftconfig getShiftconfig();

	/**
	 * 功能:修改班次设置
	 * @param shiftconfig	班次设置
	 * @throws Exception
	 */
	public void updateShiftconfig(BaseShiftconfig shiftconfig) throws Exception;

	/**
	 * 功能:增加班次设置
	 * @param shiftconfig	班次设置
	 * @throws Exception
	 */
	public void addShiftconfig(BaseShiftconfig shiftconfig) throws Exception;
}
