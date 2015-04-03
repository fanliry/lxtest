package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseBatchDao;
import com.wms3.bms.standard.entity.base.BaseBatch;

/**
 * 
 * ����: ����DAO��
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseBatchDaoImpl implements IBaseBatchDao {
	
	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;

	public BaseBatchDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * ����:��ȡ���������б�
	 * @return
	 * @throws Exception
	 */
	public List getBatchList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseBatch as t where 1=1 order by t.batchid";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("��ѯ�����б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:��������ID�������
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public BaseBatch getBatchById(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql = " from BaseBatch as t where t.batchid='" + id+"'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseBatch)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * ����:�������һ�����α���
	 * @return
	 * @throws Exception
	 */
	public String getMaxBatchNo() throws Exception 
	{
		String maxBatchNo = "000";
		try
		{
			String strSql = "from BaseBatch as t where 1=1 order by t.batchid desc";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && !ls.isEmpty())
			{
				BaseBatch batch = (BaseBatch)ls.get(0);
				maxBatchNo = batch.getBatchid();
			}
		}catch(Exception er)
		{
			throw new Exception("�������һ�����α���ʧ��:"+er.getMessage());
		}
		return maxBatchNo;
	}

	/**
	 * ����:��������
	 * @param batch	����
	 * @throws Exception
	 */
	public void addBatch(BaseBatch batch) throws Exception 
	{
		m_dao.save(batch);
	}

	/**
	 * ����:�޸�����
	 * @param batch	����
	 * @throws Exception
	 */
	public void updateBatch(BaseBatch batch) throws Exception 
	{
		m_dao.update(batch);
	}

	/**
	 * ����:ɾ������
	 * @param id	����ID
	 * @throws Exception
	 */
	public void deleteBatch(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete BaseBatch as t where t.batchid='" + id+"'";

			m_dao.deleteSql(strSql);
		}	
	}

}