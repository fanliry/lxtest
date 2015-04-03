package com.wms3.bms.standard.business.base;

import com.wms3.bms.standard.entity.base.BaseShiftconfig;


/**
 * 
 * ����: �������ҵ��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IShiftconfigBus {
	
	/**
	 * ����:��ð������
	 * @return 
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