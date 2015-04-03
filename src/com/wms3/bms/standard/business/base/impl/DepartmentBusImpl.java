package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.IDepartmentBus;
import com.wms3.bms.standard.dao.base.IBaseDepartmentDao;
import com.wms3.bms.standard.dao.base.impl.BaseDepartmentDaoImpl;
import com.wms3.bms.standard.entity.base.BaseDepartment;

/**
 * 
 * 描述: 部门管理业务类
 * @author fangjie
 * @since WMS 3.0
 */
public class DepartmentBusImpl implements IDepartmentBus {
	
	protected IBaseDepartmentDao baseDepartmentDAO = null;

	/**
	 * @param dao
	 */
	public DepartmentBusImpl(EntityDAO dao) {
		this.baseDepartmentDAO = new BaseDepartmentDaoImpl(dao);
	}


	/**
	 * 功能:根据条件查询库区
	 * @param departmentname	部门名称
	 * @param departmenttype	部门分类
	 * @return 
	 * @throws Exception
	 */
	public List getDepartmentQuery(String departmentname, String departmenttype) throws Exception {
		
		return baseDepartmentDAO.getDepartmentQuery(departmentname, departmenttype);
	}

	/**
	 * 功能:根据部门ID获得库区
	 * @param id	部门ID
	 * @return 
	 * @throws Exception
	 */
	public BaseDepartment getDepartmentById(String id) throws Exception {
		
		return baseDepartmentDAO.getDepartmentById(id);
	}

	/**
	 * 功能:增加部门
	 * @param department	部门
	 * @throws Exception
	 */
	public void addDepartment(BaseDepartment department) throws Exception {
		
		//获得编码
        String id = baseDepartmentDAO.getMaxDepartmentNo();
        id = SeqTool.getCode(Integer.parseInt(id) + 1, 3);
        department.setDepartmentid(id);
        baseDepartmentDAO.addDepartment(department);
	}
	
	/**
	 * 功能:获取所有部门列表
	 * @return 
	 * @throws Exception
	 */
	public List getDepartmentList() throws Exception {
		
		return baseDepartmentDAO.getDepartmentList();
	}

	/**
	 * 功能:修改部门
	 * @param department	部门
	 * @throws Exception
	 */
	public void updateDepartment(BaseDepartment department) throws Exception {
		
		baseDepartmentDAO.updateDepartment(department);
		
	}

	/**
	 * 功能:删除部门
	 * @param id	部门ID
	 * @throws Exception
	 */
	public void deleteDepartment(String id) throws Exception {
		
		baseDepartmentDAO.deleteDepartment(id);
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
		
		return baseDepartmentDAO.getDepartmentQuery(departmentname, departmenttype, strUrl, maxLine);
	}
}