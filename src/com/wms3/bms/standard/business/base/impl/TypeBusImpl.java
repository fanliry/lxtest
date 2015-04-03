package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.ITypeBus;
import com.wms3.bms.standard.dao.base.IBaseTypeDao;
import com.wms3.bms.standard.dao.base.impl.BaseTypeDaoImpl;
import com.wms3.bms.standard.entity.base.BaseType;

/**
 * 
 * ����: ���͹���ҵ����
 * @author fangjie
 * @since WMS 3.0
 */
public class TypeBusImpl implements ITypeBus {
	
	protected IBaseTypeDao basetypeDAO = null;

	/**
	 * @param dao
	 */
	public TypeBusImpl(EntityDAO dao) {
		this.basetypeDAO = new BaseTypeDaoImpl(dao);
	}

	/**
	 * ���ܣ�����������ѯ���͵��б�
	 * @param typevalue		����ֵ
	 * @param typename		������
	 * @return
	 */
	public List getType(String typename, String typevalue) throws Exception {
		
		return basetypeDAO.getType(typename, typevalue);
	}

	/**
	 * ���ܣ��������ʹ����ѯ�������ݵ��б�
	 * @param typevalue		����ֵ
	 * @param typename		������
	 * @return
	 */
	public List getTypeList(String typevalue) throws Exception {
		
		return basetypeDAO.getTypeList(typevalue);
	}

	/**
	 * ����:��������
	 * @param type	����
	 * @throws Exception
	 */
	public void addType(BaseType type) throws Exception {
		
		basetypeDAO.addType(type);
	}

	/**
	 * ����:��ø�����ֵ
	 * @return 
	 * @throws Exception
	 */
	public List getTypeParentList() throws Exception {
		
		return basetypeDAO.getTypeParentList();
	}

	/**
	 * ����:��������ID�������
	 * @param typeId	����ID
	 * @return 
	 * @throws Exception
	 */
	public BaseType getTypeById(String id) throws Exception {
		
		return basetypeDAO.getTypeById(id);
	}
	
	/**
	 * ���ܣ�����������ѯ����
	 * @param typevalue		����ֵ
	 * @param selectvalue	�����б�ֵ
	 * @return
	 */
	public List getTypeList(String typevalue, String selectvalue) throws Exception {
		
		return basetypeDAO.getTypeList(typevalue, selectvalue);
	}

	/**
	 * ����:��������ֵ�޸�����
	 * @param typevalue		����ֵ
	 * @param typename		������
	 * @throws Exception
	 */
	public void updateType(String typevalue, String typename) throws Exception {
		
		basetypeDAO.updateType(typevalue, typename);
	}

	/**
	 * ����:��������Ϊϵͳ����
	 * @param typevalue		����ֵ
	 * @throws Exception
	 */
	public void setTypeLevel(String typevalue) throws Exception {
		
		basetypeDAO.setTypeLevel(typevalue);
	}

	/**
	 * ����:��������ֵɾ������
	 * @param typevalue		����ֵ
	 * @throws Exception
	 */
	public void deleteType(String typevalue) throws Exception {
		
		basetypeDAO.deleteType(typevalue);
	}

	/**
	 * ����:�޸�����
	 * @param type	����
	 * @throws Exception
	 */
	public void updateType(BaseType type) throws Exception {
		
		basetypeDAO.updateType(type);
	}

	/**
	 * ����:ɾ������
	 * @param type	����
	 * @throws Exception
	 */
	public void deleteType(BaseType type) throws Exception {
		
		basetypeDAO.deleteType(type);
	}
	/**
	 * ���ܣ���ȡ�ֵ���������
	 * @return
	 * @throws Exception
	 */
	public List<BaseType> getBaseType() throws Exception {	    
		return basetypeDAO.getType();
	}

}