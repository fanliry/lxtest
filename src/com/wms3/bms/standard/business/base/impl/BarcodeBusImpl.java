package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.base.IBarcodeBus;
import com.wms3.bms.standard.dao.base.IBaseBarcodeDao;
import com.wms3.bms.standard.dao.base.impl.BaseBarcodeDaoImpl;
import com.wms3.bms.standard.entity.base.BaseBarcode;

/**
 * ����: ���������ҵ����
 * @author fangjie
 * 
 */
public class BarcodeBusImpl implements IBarcodeBus {
	
	protected IBaseBarcodeDao baseBarcodeDAO = null;
	protected EntityDAO m_dao = null;

	public BarcodeBusImpl(EntityDAO dao) {
		this.baseBarcodeDAO = new BaseBarcodeDaoImpl(dao);
		this.m_dao = dao;
	}
	
	/**
	 * ����:����������ѯ������
	 * @param isused	�Ƿ�ʹ��
	 * @param maxLine 	��ҳ��ʾ����
	 * @param strUrl 	��ַ
	 * @return
	 * @throws Exception
	 */
	public PagingTool getBarcodeQuery(String isused, String strUrl, int maxLine) throws Exception {
		
		PagingTool pt = null;
		try
		{
			String sql = "From BaseBarcode as t where 1=1" ;
			
			if(isused != null && isused.trim().length() > 0){
				sql += " and t.isused = '" + isused + "'";
			}

			String strHQL = sql + " order by t.barcode";
			String strCountHQL = "select count(*)" + sql ;
			
			pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);

				
		}catch(Exception er)
		{
			throw new Exception("����������ѯ���������:" + er.getMessage());
		}
		return pt;
	}

	/**
	 * ����:����������ID���������
	 * @param id	������ID
	 * @return
	 * @throws Exception
	 */
	public BaseBarcode getBarcodeById(String id) throws Exception {
		
		return baseBarcodeDAO.getBarcodeById(id);
	}

	/**
	 * ����:����������
	 * @param barcode	������
	 * @throws Exception
	 */
	public void addBarcode(BaseBarcode barcode) throws Exception {
		
		baseBarcodeDAO.addBarcode(barcode);
		
	}

	/**
	 * ����:��ȡ�����������б�
	 * @return
	 * @throws Exception
	 */
	public List getBarcodeList() throws Exception {

		return baseBarcodeDAO.getBarcodeList();
	}

	/**
	 * ����:�޸�������
	 * @param barcode	������
	 * @throws Exception
	 */
	public void updateBarcode(BaseBarcode barcode) throws Exception {
		
		baseBarcodeDAO.updateBarcode(barcode);
	}

	/**
	 * ����:ɾ��������
	 * @param id	������ID
	 * @throws Exception
	 */
	public void deleteBarcode(String id) throws Exception {
		
		baseBarcodeDAO.deleteBarcode(id);
	}
	
	/**
	 * ��ȡ������
	 * @param i
	 * @return
	 */
	public String getBarCode(int i) {
		
		String code = null;
		if (i < 10) {
		  code = "00000" + i;
		}
		else if (i < 100) {
		  code = "0000" + i;
		}
		else if (i < 1000) {
		  code = "000" + i;
		}
		else if (i < 10000) {
		  code = "00" + i;
		}
		else if (i < 100000) {
		  code = "0" + i;
		}
		else
		  code = i+"";
		
		return code;
	}

}