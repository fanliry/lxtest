package com.wms3.bms.standard.business.base;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseDepartment;

/**
 * 
 * 描述: 部门管理业务接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IDepartmentBus {
	
	/**
	 * 功能:根据条件查询部门
	 * @param departmentname	部门名称
	 * @param departmenttype	部门分类
	 * @return 
	 * @throws Exception
	 */
	public List getDepartmentQuery(String departmentname, String departmenttype) throws Exception;

	/**
	 * 功能:根据部门ID获得库区
	 * @param id	部门ID
	 * @return 
	 * @throws Exception
	 */
	public BaseDepartment getDepartmentById(String id) throws Exception;

	/**
	 * 功能:增加部门
	 * @param department	部门
	 * @throws Exception
	 */
	public void addDepartment(BaseDepartment department) throws Exception;

	/**
	 * 功能:获取所有部门列表
	 * @return 
	 * @throws Exception
	 */
	public List getDepartmentList() throws Exception;

	/**
	 * 功能:修改部门
	 * @param department	部门
	 * @throws Exception
	 */
	public void updateDepartment(BaseDepartment department) throws Exception;

	/**
	 * 功能:删除部门
	 * @param id	部门ID
	 * @throws Exception
	 */
	public void deleteDepartment(String id) throws Exception;

	/**
	 * 功能:根据条件查询部门
	 * @param departmentname	部门名称
	 * @param departmenttype	部门分类
	 * @param strUrl
	 * @param maxLine
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getDepartmentQuery(String departmentname, String departmenttype, String strUrl, int maxLine) throws Exception;
	
}