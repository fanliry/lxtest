package com.wms3.bms.standard.business.base;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseShift;

/**
 * 
 * ����: ��ι���ҵ��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IShiftBus {
	
	/**
	 * ����:����������ѯ���
	 * @param strOp_Man_Id		������
	 * @param strIn_Out_Type	�������
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getShiftQuery(String strOp_Man_Id, String strIn_Out_Type, String strUrl, int maxLine) throws Exception;

	/**
	 * ����:���ݰ��ID��ð��
	 * @param strIn_Out_Type	�������
	 * @return 
	 * @throws Exception
	 */
	public BaseShift getLastShift(String strIn_Out_Type) throws Exception;
	
	/**
	 * ����:������һ�����
	 * @param id	���ID
	 * @return 
	 * @throws Exception
	 */
	public BaseShift getShiftById(String id) throws Exception;

	/**
	 * ����:���Ӱ��
	 * @param shift	���
	 * @throws Exception
	 */
	public String addShift(BaseShift shift) throws Exception;

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
	
}