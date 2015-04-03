package com.wms3.bms.standard.dao.base;

import com.wms3.bms.standard.entity.base.BaseShiftconfig;

/**
 * 
 * ����: �������DAO��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseShiftconfigDao {

	/**
	 * ����:��ð������
	 * @return 
	 * @throws Exception
	 */
	public BaseShiftconfig getShiftconfig();

	/**
	 * ����:�޸İ������
	 * @param shiftconfig	�������
	 * @throws Exception
	 */
	public void updateShiftconfig(BaseShiftconfig shiftconfig) throws Exception;

	/**
	 * ����:���Ӱ������
	 * @param shiftconfig	�������
	 * @throws Exception
	 */
	public void addShiftconfig(BaseShiftconfig shiftconfig) throws Exception;
}
