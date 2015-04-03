package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseCargoAreaDao;
import com.wms3.bms.standard.entity.base.BaseCargoarea;

/**
 * ����: ����DAO��
 * @author fangjie 
 * 
 */
public class BaseCargoAreaDaoImpl implements IBaseCargoAreaDao {

	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    
	public BaseCargoAreaDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * ����:����������ѯ����
	 * @param warehouseid		�ֿ�ID
	 * @param cargoAreaName		������	
	 * @return
	 * @throws Exception
	 */
	public List getCargoAreaQuery(String warehouseid, String cargoAreaName) throws Exception
	{
		List ls = null;
		try
		{
			String strHql = "From BaseCargoarea as t where 1=1" ;
			
			if(warehouseid != null && warehouseid.trim().length() > 0){
				strHql += " and t.warehouseid='" + warehouseid + "'";
			}
			if(cargoAreaName != null && cargoAreaName.trim().length() > 0){
				strHql += " and t.cargoAreaName like '%" + cargoAreaName + "%'";
			}
			strHql = strHql + " order by t.cargoAreaId";
			ls = m_dao.searchEntities(strHql);		
				
		}catch(Exception er)
		{
			throw new Exception("����������ѯ��������:" + er.getMessage());
		}
		return ls;
	}
	
	/**
	 * ����:���ݻ���ID��û���
	 * @param cargoAreaId	����ID
	 * @return
	 * @throws Exception
	 */
	public BaseCargoarea getCargoareaById(String cargoAreaId) throws Exception
	{
		if(cargoAreaId != null)
		{
			String strSql = " from BaseCargoarea as t where t.cargoAreaId='" + cargoAreaId + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseCargoarea)ls.get(0);
			}
		}
		return null;
	}

	/**
	 * ����:��øòֿ������һ����������
	 * @param warehouseid 
	 * @return
	 * @throws Exception
	 */
	public String getMaxCargoAreaId(String warehouseid) throws Exception
	{
		String maxCargoareaNo = warehouseid + "000";
		try
		{
			String strSql = "from BaseCargoarea as t where t.warehouseid='" + warehouseid + "' order by t.cargoAreaId desc";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && !ls.isEmpty())
			{
				BaseCargoarea cargoArea = (BaseCargoarea)ls.get(0);
				maxCargoareaNo = cargoArea.getCargoAreaId();
			}
		}catch(Exception er)
		{
			throw new Exception("�������һ����������ʧ��:"+er.getMessage());
		}
		return maxCargoareaNo;
	}

	/**
	 * ����:���ӻ���
	 * @param cargoArea	����
	 * @throws Exception
	 */
	public void addCargoArea(BaseCargoarea cargoarea) throws Exception{
		
		m_dao.save(cargoarea);
	}

	/**
	 * ����:��ȡ���л����б�
	 * @return
	 * @throws Exception
	 */
	public List getCargoAreaList() throws Exception
	{
		List ls = null;
		try
		{
			String strHql = "From BaseCargoarea as t where 1=1 order by t.cargoAreaId";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("��ѯ�����б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:�޸Ļ���
	 * @param cargoArea	����
	 * @throws Exception
	 */
	public void updateCargoArea(BaseCargoarea cargoarea) throws Exception{
		
		m_dao.update(cargoarea);
	}

	/**
	 * ����:ɾ������
	 * @param cargoAreaId	����ID
	 * @throws Exception
	 */
	public void deleteCargoArea(String id) throws Exception
	{
		if(id != null)
		{
			String strSql  = " delete BaseCargoarea as t where t.cargoAreaId='" + id + "'";

			m_dao.deleteSql(strSql);
		}	
	}
}
