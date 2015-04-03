package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseBarcodeDao;
import com.wms3.bms.standard.entity.base.BaseBarcode;

/**
 * ����: ������DAO��
 * @author fangjie
 * 
 */
public class BaseBarcodeDaoImpl implements IBaseBarcodeDao {

	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    
	public BaseBarcodeDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * ����:����������ID���������
	 * @param id	������ID
	 * @return
	 * @throws Exception
	 */
	public BaseBarcode getBarcodeById(String id) throws Exception
	{
		if(id != null || !id.equals(""))
		{
			String strSql = " from BaseBarcode as t where t.barcode='" + id + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseBarcode)ls.get(0);
			}
		}
		return null;
	}

	/**
	 * ����:����������
	 * @param barcode	������
	 * @throws Exception
	 */
	public void addBarcode(BaseBarcode barcode) throws Exception{
		
		m_dao.save(barcode);
	}

	/**
	 * ����:��ȡ�����������б�
	 * @return
	 * @throws Exception
	 */
	public List getBarcodeList() throws Exception
	{
		List ls = null;
		try
		{
			String strHql = "From BaseBarcode as t where 1=1 order by t.barcode";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("��ѯ�������б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:�޸�������
	 * @param barcode	������
	 * @throws Exception
	 */
	public void updateBarcode(BaseBarcode barcode) throws Exception{
		
		m_dao.update(barcode);
	}

	/**
	 * ����:ɾ��������
	 * @param id	������ID
	 * @throws Exception
	 */
	public void deleteBarcode(String id) throws Exception
	{
		if(id != null)
		{
			String strSql  = " delete BaseBarcode as t where t.barcode='" + id + "'";

			m_dao.deleteSql(strSql);
		}	
	}
	
}
