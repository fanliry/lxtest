package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseDepartment;

/**
 * 
 * ����: ����DAO��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseDepartmentDao {

	/**
	 * ����:����������ѯ����
	 * @param departmentname	��������
	 * @param departmenttype	���ŷ���
	 * @return 
	 * @throws Exception
	 */
	public List getDepartmentQuery(String departmentname, String departmenttype) throws Exception;

	/**
	 * ����:���ݲ���ID��ò���
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public BaseDepartment getDepartmentById(String id) throws Exception;

	/**
	 * ����:�������һ�����ű���
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public String getMaxDepartmentNo() throws Exception;

	/**
	 * ����:���Ӳ���
	 * @param department	����
	 * @throws Exception
	 */
	public void addDepartment(BaseDepartment department) throws Exception;

	/**
	 * ����:��ȡ���в����б�
	 * @return 
	 * @throws Exception
	 */
	public List getDepartmentList() throws Exception;

	/**
	 * ����:�޸Ĳ���
	 * @param department	����
	 * @throws Exception
	 */
	public void updateDepartment(BaseDepartment department) throws Exception;

	/**
	 * ����:ɾ������
	 * @param id	����ID
	 * @throws Exception
	 */
	public void deleteDepartment(String id) throws Exception;

	/**
	 * ����:����������ѯ����
	 * @param departmentname	��������
	 * @param departmenttype	���ŷ���
	 * @param strUrl
	 * @param maxLine
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getDepartmentQuery(String departmentname, String departmenttype, String strUrl, int maxLine) throws Exception;

}
