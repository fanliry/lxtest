package com.wms3.bms.standard.business.base;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseBarcode;

/**
 * ����: ���������ҵ��ӿ�
 * @author fangjie
 * 
 */
public interface IBarcodeBus {

	/**
	 * ����:����������ѯ������
	 * @param isused	�Ƿ�ʹ��
	 * @param maxLine 	��ҳ��ʾ����
	 * @param strUrl 	��ַ
	 * @return
	 * @throws Exception
	 */
	public PagingTool getBarcodeQuery(String isused, String strUrl, int maxLine) throws Exception;

	/**
	 * ����:����������ID���������
	 * @param id	������ID
	 * @return
	 * @throws Exception
	 */
	public BaseBarcode getBarcodeById(String id) throws Exception;
	
	/**
	 * ����:����������
	 * @param barcode	������
	 * @throws Exception
	 */
	public void addBarcode(BaseBarcode barcode)  throws Exception;

	/**
	 * ����:��ȡ�����������б�
	 * @return
	 * @throws Exception
	 */
	public List getBarcodeList() throws Exception;

	/**
	 * ����:�޸�������
	 * @param barcode	������
	 * @throws Exception
	 */
	public void updateBarcode(BaseBarcode barcode) throws Exception;

	/**
	 * ����:ɾ��������
	 * @param id	������ID
	 * @throws Exception
	 */
	public void deleteBarcode(String id) throws Exception;

	/**
	 * ��ȡ������
	 * @param i
	 * @returns
	 */
	public String getBarCode(int i);

}
