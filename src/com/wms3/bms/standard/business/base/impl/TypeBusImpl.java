package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.ITypeBus;
import com.wms3.bms.standard.dao.base.IBaseTypeDao;
import com.wms3.bms.standard.dao.base.impl.BaseTypeDaoImpl;
import com.wms3.bms.standard.entity.base.BaseType;

/**
 * 
 * 描述: 类型管理业务类
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
	 * 功能：根据条件查询类型的列表
	 * @param typevalue		类型值
	 * @param typename		类型名
	 * @return
	 */
	public List getType(String typename, String typevalue) throws Exception {
		
		return basetypeDAO.getType(typename, typevalue);
	}

	/**
	 * 功能：根据类型代码查询类型内容的列表
	 * @param typevalue		类型值
	 * @param typename		类型名
	 * @return
	 */
	public List getTypeList(String typevalue) throws Exception {
		
		return basetypeDAO.getTypeList(typevalue);
	}

	/**
	 * 功能:增加类型
	 * @param type	类型
	 * @throws Exception
	 */
	public void addType(BaseType type) throws Exception {
		
		basetypeDAO.addType(type);
	}

	/**
	 * 功能:获得父类型值
	 * @return 
	 * @throws Exception
	 */
	public List getTypeParentList() throws Exception {
		
		return basetypeDAO.getTypeParentList();
	}

	/**
	 * 功能:根据类型ID获得类型
	 * @param typeId	类型ID
	 * @return 
	 * @throws Exception
	 */
	public BaseType getTypeById(String id) throws Exception {
		
		return basetypeDAO.getTypeById(id);
	}
	
	/**
	 * 功能：根据条件查询类型
	 * @param typevalue		类型值
	 * @param selectvalue	下拉列表值
	 * @return
	 */
	public List getTypeList(String typevalue, String selectvalue) throws Exception {
		
		return basetypeDAO.getTypeList(typevalue, selectvalue);
	}

	/**
	 * 功能:根据类型值修改类型
	 * @param typevalue		类型值
	 * @param typename		类型名
	 * @throws Exception
	 */
	public void updateType(String typevalue, String typename) throws Exception {
		
		basetypeDAO.updateType(typevalue, typename);
	}

	/**
	 * 功能:将类型设为系统级别
	 * @param typevalue		类型值
	 * @throws Exception
	 */
	public void setTypeLevel(String typevalue) throws Exception {
		
		basetypeDAO.setTypeLevel(typevalue);
	}

	/**
	 * 功能:根据类型值删除类型
	 * @param typevalue		类型值
	 * @throws Exception
	 */
	public void deleteType(String typevalue) throws Exception {
		
		basetypeDAO.deleteType(typevalue);
	}

	/**
	 * 功能:修改类型
	 * @param type	类型
	 * @throws Exception
	 */
	public void updateType(BaseType type) throws Exception {
		
		basetypeDAO.updateType(type);
	}

	/**
	 * 功能:删除类型
	 * @param type	类型
	 * @throws Exception
	 */
	public void deleteType(BaseType type) throws Exception {
		
		basetypeDAO.deleteType(type);
	}
	/**
	 * 功能：获取字典中心类型
	 * @return
	 * @throws Exception
	 */
	public List<BaseType> getBaseType() throws Exception {	    
		return basetypeDAO.getType();
	}

}