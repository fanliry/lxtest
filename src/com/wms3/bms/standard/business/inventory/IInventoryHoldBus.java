package com.wms3.bms.standard.business.inventory;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;

/**
 * ����: ���
 * ����/�ͷ�ҵ��ӿ�
 * @author fangjie
 * 
 */
public interface IInventoryHoldBus {

	/**
	 * ����:����������ѯ�����¼���б�
	 * @param cargospaceid	��λ
	 * @param lotid			��������
	 * @param ownerid		����
	 * @param productid		Ʒ��
	 * @param holdcode		����ԭ��
	 * @param holdby		���᷽��
	 * @param dateon_from	��������
	 * @param dateon_to		��������
	 * @param dateoff_from	�ͷ�����
	 * @param dateoff_to	�ͷ�����
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getHoldList(String cargospaceid, String productid, String holdcode, String holdby, 
			String dateon_from, String dateon_to, String dateoff_from, String dateoff_to, String strUrl, int maxLine) throws Exception;
	
	/**
	 * ����:����������ѯ����¼���б�
	 * @param ownerid		����
	 * @param productid		Ʒ��
	 * @param cargospaceid	��λ
	 * @param lotid			��������
	 * @param traycode		��������
	 * @param boxcode		������
	 * @param productcode	��Ʒ����
	 * @return 
	 * @throws Exception
	 */
	public List getStorageList( String productid, String cargospaceid, 
			String traycode, String boxcode, String productcode) throws Exception;

	/**
	 * ����:���ɶ����¼
	 * @param ids			���ids
	 * @param holdcode		����ԭ��
	 * @param holdby		���᷽��
	 * @param dateoff		�ͷ�����
	 * @param holdreason 	ԭ������
	 * @param strUserCode 	�û�����
	 * @return 
	 * @throws Exception
	 */
	public String addHold(String ids, String holdcode, String holdby, String dateoff, String holdreason, String strUserCode) throws Exception;

	/**
	 * ����:�ͷŶ����¼
	 * @param ids			ids(����ID+���ID)
	 * @param strUserCode 	�û�����
	 * @return 
	 * @throws Exception
	 */
	public String updateHold(String ids, String strUserCode) throws Exception;

	
    
    

}
