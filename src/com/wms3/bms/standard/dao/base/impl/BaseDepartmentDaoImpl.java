package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.dao.base.IBaseDepartmentDao;
import com.wms3.bms.standard.entity.base.BaseDepartment;

/**
 * 
 * ����: ����DAO��
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseDepartmentDaoImpl implements IBaseDepartmentDao {
	
	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;

	public BaseDepartmentDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * ����:����������ѯ����
	 * @param departmentname	��������
	 * @param departmenttype	���ŷ���
	 * @return 
	 * @throws Exception
	 */
	public List getDepartmentQuery(String departmentname, String departmenttype) throws Exception 
	{
		List ls = null;
		
		String strHql = "From BaseDepartment as t where 1=1" ;
		
		if(departmenttype != null && departmenttype.trim().length() > 0){
			strHql += " and t.departmenttype='" + departmenttype + "'";
		}
		if(departmentname != null && departmentname.trim().length() > 0){
			strHql += " and t.departmentname like '%" + departmentname + "%'";
		}
		strHql = strHql + " order by t.departmentid";
		ls = m_dao.searchEntities(strHql);		
				
		return ls;
	}

	/**
	 * ����:���ݲ���ID��ò���
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public BaseDepartment getDepartmentById(String id) throws Exception 
	{
		if(id!=null && !id.equals(""))
		{
			String strSql = " from BaseDepartment as t where t.departmentid='" + id + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseDepartment)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * ����:�������һ�����ű���
	 * @return
	 * @throws Exception
	 */
	public String getMaxDepartmentNo() throws Exception 
	{
		String maxNo = "000";
		try
		{
			String strSql = "from BaseDepartment as t where 1=1 order by t.departmentid desc";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && !ls.isEmpty())
			{
				BaseDepartment department = (BaseDepartment)ls.get(0);
				maxNo = department.getDepartmentid();
			}
		}catch(Exception er)
		{
			throw new Exception("�������һ�����ű���ʧ��:"+er.getMessage());
		}
		return maxNo;
	}

	/**
	 * ����:���Ӳ���
	 * @param department	����
	 * @throws Exception
	 */
	public void addDepartment(BaseDepartment department) throws Exception 
	{
		m_dao.save(department);
	}

	/**
	 * ����:��ȡ���в����б�
	 * @return 
	 * @throws Exception
	 */
	public List getDepartmentList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseDepartment as t where 1=1 order by t.departmentid";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("��ѯ�����б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:�޸Ĳ���
	 * @param department	����
	 * @throws Exception
	 */
	public void updateDepartment(BaseDepartment department) throws Exception 
	{
		m_dao.update(department);
	}

	/**
	 * ����:ɾ������
	 * @param id	����ID
	 * @throws Exception
	 */
	public void deleteDepartment(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete BaseDepartment as t where t.departmentid='" + id + "'";
			m_dao.deleteSql(strSql);
		}	
	}

	/**
	 * ����:����������ѯ����
	 * @param departmentname	��������
	 * @param departmenttype	���ŷ���
	 * @param strUrl
	 * @param maxLine
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getDepartmentQuery(String departmentname, String departmenttype, String strUrl, int maxLine) throws Exception {
		
PagingTool pt = null;
		
		try {
			String sql = "From BaseDepartment as t where 1=1" ;
			
			if(departmenttype != null && departmenttype.trim().length() > 0){
				sql += " and t.departmenttype='" + departmenttype + "'";
			}
			if(departmentname != null && departmentname.trim().length() > 0){
				sql += " and t.departmentname like '%" + departmentname + "%'";
			}
			
			String strHQL = sql + " order by t.departmentid";
			String strCountHQL = "select count(*)" + sql ;
			
			pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("����������ѯ���ų���:" + er.getMessage());
		}
				
		return pt;
	}
}