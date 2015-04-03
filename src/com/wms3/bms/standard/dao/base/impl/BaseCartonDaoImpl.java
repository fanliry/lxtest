package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseCartonDao;
import com.wms3.bms.standard.entity.base.BaseCarton;

/**
 * 
 * ����: ��ת��DAO��
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseCartonDaoImpl implements IBaseCartonDao {
	
	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;

	public BaseCartonDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * ����:������ת��ID�����ת��
	 * @param id	��ת��ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCarton getCartonById(String id) throws Exception 
	{
		if(id!=null && !id.equals(""))
		{
			String strSql = " from BaseCarton as t where t.cartonid='" + id + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseCarton)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * ����:�������һ����ת�����
	 * @return
	 * @throws Exception
	 */
	public String getMaxCartonNo() throws Exception 
	{
		String maxNo = "0000";
		try
		{
			String strSql = "from BaseCarton as t where 1=1 order by t.cartonid desc";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && !ls.isEmpty())
			{
				BaseCarton carton = (BaseCarton)ls.get(0);
				maxNo = carton.getCartonid();
			}
		}catch(Exception er)
		{
			throw new Exception("�������һ����ת�����ʧ��:"+er.getMessage());
		}
		return maxNo;
	}

	/**
	 * ����:������ת��
	 * @param carton	��ת��
	 * @throws Exception
	 */
	public void addCarton(BaseCarton carton) throws Exception 
	{
		m_dao.save(carton);
	}

	/**
	 * ����:��ȡ������ת���б�
	 * @return 
	 * @throws Exception
	 */
	public List getCartonList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseCarton as t where 1=1 order by t.cartonid";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("��ѯ��ת���б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:�޸���ת��
	 * @param carton	��ת��
	 * @throws Exception
	 */
	public void updateCarton(BaseCarton carton) throws Exception 
	{
		m_dao.update(carton);
	}

	/**
	 * ����:ɾ����ת��
	 * @param id	��ת��ID
	 * @throws Exception
	 */
	public void deleteCarton(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete BaseCarton as t where t.cartonid='" + id + "'";
			m_dao.deleteSql(strSql);
		}	
	}
}