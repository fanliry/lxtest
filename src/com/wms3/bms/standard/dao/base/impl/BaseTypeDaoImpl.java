package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseTypeDao;
import com.wms3.bms.standard.entity.base.BaseType;

/**
 * 
 * ����: ����DAO��
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseTypeDaoImpl implements IBaseTypeDao {
	
	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;

	public BaseTypeDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * ���ܣ�����������ѯ���͵��б�
	 * @param typevalue		����ֵ
	 * @param typename		������
	 * @return
	 */
	public List getType(String typename, String typevalue) throws Exception {
		
		String strSql = " From BaseType as t where 1=1 and (t.selecttext is null or t.selecttext='')" ;
		
		if(typename != null && typename.trim().length() > 0){
			strSql += " and t.typename like '%" + typename + "%'";
		}
		if(typevalue != null && typevalue.trim().length() > 0){
			strSql += " and t.typevalue = '" + typevalue + "'";
		}
		
		strSql = strSql + " order by t.typeid";
		List ls = m_dao.searchEntities(strSql);
		
		return ls;
	}

	/**
	 * ���ܣ��������ʹ����ѯ�������ݵ��б�
	 * @param typevalue		����ֵ
	 * @param typename		������
	 * @return
	 */
	public List getTypeList(String typevalue) throws Exception {
		
		String strSql = " From BaseType as t where 1=1 and t.selecttext!='' and t.typevalue = '" + typevalue + "'";
		strSql = strSql + " order by t.typeid";
		List ls = m_dao.searchEntities(strSql);
		
		return ls;
	}
	
	/**
	 * ����:��������
	 * @param type	����
	 * @throws Exception
	 */
	public void addType(BaseType type) throws Exception {
		
		m_dao.save(type);
	}
	
	/**
	 * ����:��ø�����ֵ
	 * @return 
	 * @throws Exception
	 */
	public List getTypeParentList() throws Exception {
		
		String strSql = " from BaseType as t where t.typeid=t.typevalue";
		List ls = m_dao.searchEntities(strSql);
		
		return ls;
	}
	
	/**
	 * ����:��������ID�������
	 * @param typeId	����ID
	 * @return 
	 * @throws Exception
	 */
	public BaseType getTypeById(String typeId) throws Exception {
		
		if(typeId != null && !typeId.equals(""))
		{
			String strSql = " from BaseType as t where t.typeid='" + typeId+"'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseType)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * ���ܣ�����������ѯ����
	 * @param typevalue		����ֵ
	 * @param selectvalue	�����б�ֵ
	 * @return
	 */
	public List getTypeList(String typevalue, String selectvalue) throws Exception {
		
		if(typevalue != null && typevalue.trim().length() > 0){
			
			String strSql = " From BaseType as t where 1=1" 
				+ " and t.typevalue = '" + typevalue + "'";
		
			if(selectvalue != null && selectvalue.trim().length() > 0){
				strSql += " and t.selectvalue = '" + selectvalue + "'";
			}
			
			List ls = m_dao.searchEntities(strSql);
			return ls;	
		}
		return null;
			
	}

	/**
	 * ����:��������ֵ�޸�����
	 * @param typevalue		����ֵ
	 * @param typename		������
	 * @throws Exception
	 */
	public void updateType(String typevalue, String typename) throws Exception {
		
		String strSql="update BaseType as t set t.typename='" + typename + "' where t.typevalue='" + typevalue + "'";
		m_dao.updateSql(strSql);
		
	}

	/**
	 * ����:��������Ϊϵͳ����
	 * @param typevalue		����ֵ
	 * @throws Exception
	 */
	public void setTypeLevel(String typevalue) throws Exception {
		
		try{
		
			String strSql="update BaseType as t set t.typelevel='Y' where t.typevalue='" + typevalue + "'";
			m_dao.updateSql(strSql);
			
		}catch(Exception er){
		
			throw new Exception("������(" + typevalue + ")��Ϊϵͳ����ʧ��:"+er.getMessage());
		}	
		
	}

	/**
	 * ����:��������ֵɾ������
	 * @param typevalue		����ֵ
	 * @throws Exception
	 */
	public void deleteType(String typevalue) throws Exception {
		
		String strSql="delete BaseType as t where t.typevalue='" + typevalue + "'";
		m_dao.deleteSql(strSql);
	}

	/**
	 * ����:�޸�����
	 * @param type	����
	 * @throws Exception
	 */
	public void updateType(BaseType type) throws Exception {
		
		m_dao.update(type);
	}

	/**
	 * ����:ɾ������
	 * @param type	����
	 * @throws Exception
	 */
	public void deleteType(BaseType type) throws Exception {
		
		m_dao.delete(type);
	}
	 /**
     * ���ܣ���ȡ�ֵ���������
     * @throws Exception
     */
	public List<BaseType> getType() throws Exception {
		String strHql = "from BaseType as t where t.typelevel!='Y' and t.selecttext is null or t.selecttext=''";
		List<BaseType> ls = null;
		try {
			ls = m_dao.searchEntities(strHql);	
		} catch (Exception er) {
			throw new Exception("��ȡ�ֵ��������ͳ���BaseTypeDaoImpl.getType()��:" + er.getMessage());
		}
		
		return ls;
	}
}