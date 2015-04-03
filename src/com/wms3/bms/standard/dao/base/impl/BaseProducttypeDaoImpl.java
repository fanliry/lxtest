package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseProducttypeDao;
import com.wms3.bms.standard.entity.base.BaseProducttype;

/**
 * 
 * ����: ��Ʒ���DAO��
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseProducttypeDaoImpl implements IBaseProducttypeDao {
	
	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;

	public BaseProducttypeDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * ����:����������ѯ����
	 * @param ptname	�����
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeQuery(String ptname) throws Exception 
	{
		List ls = null;
		
		String strHql = "From BaseProducttype as t where 1=1" ;
		
		if(ptname != null && ptname.trim().length() > 0){
			strHql += " and t.ptname like '%" + ptname + "%'";
		}
		strHql = strHql + " order by t.ptorder";
		ls = m_dao.searchEntities(strHql);		
				
		return ls;
	}

	/**
	 * ����:������Ʒ���ID�����Ʒ���
	 * @param id	��Ʒ���ID
	 * @return 
	 * @throws Exception
	 */
	public BaseProducttype getProducttypeById(String id) throws Exception 
	{
		if(id!=null && !id.equals(""))
		{
			String strSql = " from BaseProducttype as t where t.ptid='" + id + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseProducttype)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * ����:�������һ����Ʒ������
	 * @param parentid	�����ID
	 * @return
	 * @throws Exception
	 */
	public String getMaxProducttypeNo(String parentid) throws Exception 
	{
		String maxNo = "000";
		try
		{
			String strSql = "from BaseProducttype as t where t.parentid='" + parentid +"' order by t.ptid desc";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && !ls.isEmpty())
			{
				BaseProducttype producttype = (BaseProducttype)ls.get(0);
				maxNo = producttype.getPtid();
			}
		}catch(Exception er)
		{
			throw new Exception("�������һ����Ʒ������ʧ��:"+er.getMessage());
		}
		return maxNo;
	}

	/**
	 * ����:������Ʒ���
	 * @param producttype	��Ʒ���
	 * @throws Exception
	 */
	public void addProducttype(BaseProducttype producttype) throws Exception 
	{
		m_dao.save(producttype);
	}

	/**
	 * ����:��ȡ������Ʒ����б�
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseProducttype as t where 1=1 order by t.ptorder";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("��ѯ��Ʒ����б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:�޸���Ʒ���
	 * @param producttype	��Ʒ���
	 * @throws Exception
	 */
	public void updateProducttype(BaseProducttype producttype) throws Exception 
	{
		m_dao.update(producttype);
	}

	/**
	 * ����:ɾ����Ʒ���
	 * @param id	��Ʒ���ID
	 * @throws Exception
	 */
	public void deleteProducttype(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete BaseProducttype as t where t.ptid='" + id + "'";
			m_dao.deleteSql(strSql);
		}	
	}
}