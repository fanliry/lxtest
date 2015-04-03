package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseCustomerDao;
import com.wms3.bms.standard.entity.base.BaseCustomer;

/**
 * 
 * ����: �ͻ�DAO��
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseCustomerDaoImpl implements IBaseCustomerDao {
	
	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;

	public BaseCustomerDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * ����:���ݿͻ�ID��ÿͻ�
	 * @param id	�ͻ�ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCustomer getCustomerById(String id) throws Exception 
	{
		if(id!=null && !id.equals(""))
		{
			String strSql = " from BaseCustomer as t where t.customerid='" + id + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseCustomer)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * ����:�������һ���ͻ�����
	 * @return
	 * @throws Exception
	 */
	public String getMaxCustomerNo() throws Exception 
	{
		String maxNo = "000";
		try
		{
			String strSql = "from BaseCustomer as t where 1=1 order by t.customerid desc";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && !ls.isEmpty())
			{
				BaseCustomer customer = (BaseCustomer)ls.get(0);
				maxNo = customer.getCustomerid();
			}
		}catch(Exception er)
		{
			throw new Exception("�������һ���ͻ�����ʧ��:"+er.getMessage());
		}
		return maxNo;
	}

	/**
	 * ����:���ӿͻ�
	 * @param customer	�ͻ�
	 * @throws Exception
	 */
	public void addCustomer(BaseCustomer customer) throws Exception 
	{
		m_dao.save(customer);
	}

	/**
	 * ����:��ȡ���пͻ��б�
	 * @return 
	 * @throws Exception
	 */
	public List getCustomerList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseCustomer as t where 1=1 order by t.customerid";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("��ѯ�ͻ��б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:�޸Ŀͻ�
	 * @param customer	�ͻ�
	 * @throws Exception
	 */
	public void updateCustomer(BaseCustomer customer) throws Exception 
	{
		m_dao.update(customer);
	}

	/**
	 * ����:ɾ���ͻ�
	 * @param id	�ͻ�ID
	 * @throws Exception
	 */
	public void deleteCustomer(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete BaseCustomer as t where t.customerid='" + id + "'";
			m_dao.deleteSql(strSql);
		}	
	}
}