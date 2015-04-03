package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseBarcode;

/**
 * ����: ������DAO��ӿ�
 * @author fangjie
 * 
 */
public interface IBaseBarcodeDao {

	/**
	 * ����:����������ID���������
	 * @param id	������ID
	 * @return
	 * @throws Exception
	 */
	public BaseBarcode getBarcodeById(String id) throws Exception;
	
	/**
	 * ����:��ȡ�����������б�
	 * @return
	 * @throws Exception
	 */
	public List getBarcodeList() throws Exception;

	/**
	 * ����:����������
	 * @param barcode	������
	 * @throws Exception
	 */
	public void addBarcode(BaseBarcode barcode) throws Exception;

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

}
