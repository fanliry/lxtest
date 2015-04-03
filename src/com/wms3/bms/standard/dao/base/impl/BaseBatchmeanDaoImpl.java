package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseBatchmeanDao;
import com.wms3.bms.standard.entity.base.BaseBatchmean;

/**
 * 
 * ����: ��������DAO��
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseBatchmeanDaoImpl implements IBaseBatchmeanDao {
	
	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;

	public BaseBatchmeanDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * ����:��ȡ�������������б�
	 * @return
	 * @throws Exception
	 */
	public List getBatchmeanList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseBatchmean as t where 1=1 order by t.batchmeanid";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("��ѯ���������б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:������������ID�����������
	 * @param id	��������ID
	 * @return 
	 * @throws Exception
	 */
	public BaseBatchmean getBatchmeanById(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql = " from BaseBatchmean as t where t.batchmeanid='" + id+"'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseBatchmean)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * ����:��������ID�����������
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public List getBatchmeanByBatchId(String id) throws Exception 
	{
		List ls = null;
		try
		{
			if(id != null && !id.equals(""))
			{
				String strSql = " from BaseBatchmean as t where t.batchid='" + id+"'";
				ls = m_dao.searchEntities(strSql);
			}
		}catch(Exception er)
		{
			throw new Exception("��������ID������������б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:������������
	 * @param batchmean	��������
	 * @throws Exception
	 */
	public void addBatchmean(BaseBatchmean batchmean) throws Exception 
	{
		m_dao.save(batchmean);
	}

	/**
	 * ����:�޸���������
	 * @param batchmean	��������
	 * @throws Exception
	 */
	public void updateBatchmean(BaseBatchmean batchmean) throws Exception 
	{
		m_dao.update(batchmean);
	}

	/**
	 * ����:ɾ����������
	 * @param id	��������ID
	 * @throws Exception
	 */
	public void deleteBatchmean(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete BaseBatchmean as t where t.batchmeanid='" + id+"'";

			m_dao.deleteSql(strSql);
		}	
	}

}