package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseBatchruleDao;
import com.wms3.bms.standard.entity.base.BaseBatchrule;

/**
 * 
 * ����: ���ι���DAO��
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseBatchruleDaoImpl implements IBaseBatchruleDao {
	
	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;

	public BaseBatchruleDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * ����:��ȡ�������ι����б�
	 * @return
	 * @throws Exception
	 */
	public List getBatchruleList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseBatchrule as t where 1=1 order by t.batchruleid";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("��ѯ���ι����б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:�������ι���ID������ι���
	 * @param id	���ι���ID
	 * @return 
	 * @throws Exception
	 */
	public BaseBatchrule getBatchruleById(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql = " from BaseBatchrule as t where t.batchruleid='" + id+"'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseBatchrule)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * ����:��������ID������ι���
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public List getBatchruleByBatchId(String id) throws Exception 
	{
		List ls = null;
		try
		{
			if(id != null && !id.equals(""))
			{
				String strSql = " from BaseBatchrule as t where t.batchid='" + id+"'";
				ls = m_dao.searchEntities(strSql);
			}
		}catch(Exception er)
		{
			throw new Exception("��������ID������ι����б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:�������ι���
	 * @param batchrule	���ι���
	 * @throws Exception
	 */
	public void addBatchrule(BaseBatchrule batchrule) throws Exception 
	{
		m_dao.save(batchrule);
	}

	/**
	 * ����:�޸����ι���
	 * @param batchrule	���ι���
	 * @throws Exception
	 */
	public void updateBatchrule(BaseBatchrule batchrule) throws Exception 
	{
		m_dao.update(batchrule);
	}

	/**
	 * ����:ɾ�����ι���
	 * @param id	���ι���ID
	 * @throws Exception
	 */
	public void deleteBatchrule(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete BaseBatchrule as t where t.batchruleid='" + id+"'";

			m_dao.deleteSql(strSql);
		}	
	}

}