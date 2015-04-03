package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseShift;

/**
 * 
 * ����: ���DAO��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseShiftDao {

	/**
	 * ����:���ݰ��ID��ð��
	 * @param id	���ID
	 * @return 
	 * @throws Exception
	 */
	public BaseShift getShiftById(String id) throws Exception;

	/**
	 * ����:���Ӱ��
	 * @param shift	���
	 * @param lastshift ����Σ������������ͬ��
	 * @throws Exception
	 */
	public void addShift(BaseShift shift, BaseShift lastshift) throws Exception;

	/**
	 * ����:��ȡ���а���б�
	 * @return 
	 * @throws Exception
	 */
	public List getShiftList() throws Exception;

	/**
	 * ����:�޸İ��
	 * @param shift	���
	 * @throws Exception
	 */
	public void updateShift(BaseShift shift) throws Exception;

	/**
	 * ����:ɾ�����
	 * @param id	���ID
	 * @throws Exception
	 */
	public void deleteShift(String id) throws Exception;

	/**
	 * ����:���ݰ��ID��ð��
	 * @param strIn_Out_Type	�������
	 * @return 
	 * @throws Exception
	 */
	public BaseShift getLastShift(String strIn_Out_Type) throws Exception;

	/**
	 * ����:���ݰ������ѯ����Ƿ����
	 * @param shiftname	�����
	 * @param inouttype �������
	 * @return 
	 */
	public boolean isShiftExist(String shiftname, String inouttype);

}
