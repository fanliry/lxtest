package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.dao.base.IBaseDepartmentDao;
import com.wms3.bms.standard.entity.base.BaseDepartment;

/**
 * 
 * 描述: 部门DAO类
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseDepartmentDaoImpl implements IBaseDepartmentDao {
	
	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;

	public BaseDepartmentDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * 功能:根据条件查询库区
	 * @param departmentname	部门名称
	 * @param departmenttype	部门分类
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
	 * 功能:根据部门ID获得部门
	 * @param id	部门ID
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
	 * 功能:获得最大的一个部门编码
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
			throw new Exception("获得最大的一个部门编码失败:"+er.getMessage());
		}
		return maxNo;
	}

	/**
	 * 功能:增加部门
	 * @param department	部门
	 * @throws Exception
	 */
	public void addDepartment(BaseDepartment department) throws Exception 
	{
		m_dao.save(department);
	}

	/**
	 * 功能:获取所有部门列表
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
			throw new Exception("查询部门列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:修改部门
	 * @param department	部门
	 * @throws Exception
	 */
	public void updateDepartment(BaseDepartment department) throws Exception 
	{
		m_dao.update(department);
	}

	/**
	 * 功能:删除部门
	 * @param id	部门ID
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
	 * 功能:根据条件查询部门
	 * @param departmentname	部门名称
	 * @param departmenttype	部门分类
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
			throw new Exception("根据条件查询部门出错:" + er.getMessage());
		}
				
		return pt;
	}
}