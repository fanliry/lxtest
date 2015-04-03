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
 * ����: ���Ź���ҵ����
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
	 * ����:����������ѯ����
	 * @param departmentname	��������
	 * @param departmenttype	���ŷ���
	 * @return 
	 * @throws Exception
	 */
	public List getDepartmentQuery(String departmentname, String departmenttype) throws Exception {
		
		return baseDepartmentDAO.getDepartmentQuery(departmentname, departmenttype);
	}

	/**
	 * ����:���ݲ���ID��ÿ���
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public BaseDepartment getDepartmentById(String id) throws Exception {
		
		return baseDepartmentDAO.getDepartmentById(id);
	}

	/**
	 * ����:���Ӳ���
	 * @param department	����
	 * @throws Exception
	 */
	public void addDepartment(BaseDepartment department) throws Exception {
		
		//��ñ���
        String id = baseDepartmentDAO.getMaxDepartmentNo();
        id = SeqTool.getCode(Integer.parseInt(id) + 1, 3);
        department.setDepartmentid(id);
        baseDepartmentDAO.addDepartment(department);
	}
	
	/**
	 * ����:��ȡ���в����б�
	 * @return 
	 * @throws Exception
	 */
	public List getDepartmentList() throws Exception {
		
		return baseDepartmentDAO.getDepartmentList();
	}

	/**
	 * ����:�޸Ĳ���
	 * @param department	����
	 * @throws Exception
	 */
	public void updateDepartment(BaseDepartment department) throws Exception {
		
		baseDepartmentDAO.updateDepartment(department);
		
	}

	/**
	 * ����:ɾ������
	 * @param id	����ID
	 * @throws Exception
	 */
	public void deleteDepartment(String id) throws Exception {
		
		baseDepartmentDAO.deleteDepartment(id);
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
		
		return baseDepartmentDAO.getDepartmentQuery(departmentname, departmenttype, strUrl, maxLine);
	}
}