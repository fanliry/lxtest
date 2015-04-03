package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseTypeDao;
import com.wms3.bms.standard.entity.base.BaseType;

/**
 * 
 * 描述: 库区DAO类
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseTypeDaoImpl implements IBaseTypeDao {
	
	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;

	public BaseTypeDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * 功能：根据条件查询类型的列表
	 * @param typevalue		类型值
	 * @param typename		类型名
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
	 * 功能：根据类型代码查询类型内容的列表
	 * @param typevalue		类型值
	 * @param typename		类型名
	 * @return
	 */
	public List getTypeList(String typevalue) throws Exception {
		
		String strSql = " From BaseType as t where 1=1 and t.selecttext!='' and t.typevalue = '" + typevalue + "'";
		strSql = strSql + " order by t.typeid";
		List ls = m_dao.searchEntities(strSql);
		
		return ls;
	}
	
	/**
	 * 功能:增加类型
	 * @param type	类型
	 * @throws Exception
	 */
	public void addType(BaseType type) throws Exception {
		
		m_dao.save(type);
	}
	
	/**
	 * 功能:获得父类型值
	 * @return 
	 * @throws Exception
	 */
	public List getTypeParentList() throws Exception {
		
		String strSql = " from BaseType as t where t.typeid=t.typevalue";
		List ls = m_dao.searchEntities(strSql);
		
		return ls;
	}
	
	/**
	 * 功能:根据类型ID获得类型
	 * @param typeId	类型ID
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
	 * 功能：根据条件查询类型
	 * @param typevalue		类型值
	 * @param selectvalue	下拉列表值
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
	 * 功能:根据类型值修改类型
	 * @param typevalue		类型值
	 * @param typename		类型名
	 * @throws Exception
	 */
	public void updateType(String typevalue, String typename) throws Exception {
		
		String strSql="update BaseType as t set t.typename='" + typename + "' where t.typevalue='" + typevalue + "'";
		m_dao.updateSql(strSql);
		
	}

	/**
	 * 功能:将类型设为系统级别
	 * @param typevalue		类型值
	 * @throws Exception
	 */
	public void setTypeLevel(String typevalue) throws Exception {
		
		try{
		
			String strSql="update BaseType as t set t.typelevel='Y' where t.typevalue='" + typevalue + "'";
			m_dao.updateSql(strSql);
			
		}catch(Exception er){
		
			throw new Exception("将类型(" + typevalue + ")设为系统级别失败:"+er.getMessage());
		}	
		
	}

	/**
	 * 功能:根据类型值删除类型
	 * @param typevalue		类型值
	 * @throws Exception
	 */
	public void deleteType(String typevalue) throws Exception {
		
		String strSql="delete BaseType as t where t.typevalue='" + typevalue + "'";
		m_dao.deleteSql(strSql);
	}

	/**
	 * 功能:修改类型
	 * @param type	类型
	 * @throws Exception
	 */
	public void updateType(BaseType type) throws Exception {
		
		m_dao.update(type);
	}

	/**
	 * 功能:删除类型
	 * @param type	类型
	 * @throws Exception
	 */
	public void deleteType(BaseType type) throws Exception {
		
		m_dao.delete(type);
	}
	 /**
     * 功能：获取字典中心类型
     * @throws Exception
     */
	public List<BaseType> getType() throws Exception {
		String strHql = "from BaseType as t where t.typelevel!='Y' and t.selecttext is null or t.selecttext=''";
		List<BaseType> ls = null;
		try {
			ls = m_dao.searchEntities(strHql);	
		} catch (Exception er) {
			throw new Exception("获取字典中心类型出错（BaseTypeDaoImpl.getType()）:" + er.getMessage());
		}
		
		return ls;
	}
}