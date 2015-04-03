package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseType;

/**
 * 
 * 描述: 库区DAO类接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseTypeDao {

	/**
	 * 功能：根据条件查询类型的列表
	 * @param typevalue		类型值
	 * @param typename		类型名
	 * @return 
	 */
	List getType(String typename, String typevalue) throws Exception;
	
	/**
	 * 功能：根据类型代码查询类型内容的列表
	 * @param typevalue		类型值
	 * @param typename		类型名
	 * @return
	 */
	List getTypeList(String typevalue) throws Exception;
	
	/**
	 * 功能:增加类型
	 * @param type	类型
	 * @throws Exception
	 */
	void addType(BaseType type) throws Exception;
	
	/**
	 * 功能:获得父类型值
	 * @return 
	 * @throws Exception
	 */
	List getTypeParentList() throws Exception;
	
	/**
	 * 功能:根据类型ID获得类型
	 * @param typeId	类型ID
	 * @return 
	 * @throws Exception
	 */
	BaseType getTypeById(String id) throws Exception;
	
	/**
	 * 功能：根据条件查询类型
	 * @param typevalue		类型值
	 * @param selectvalue	下拉列表值
	 * @return
	 */
	List getTypeList(String typevalue, String selectvalue) throws Exception;
	
	/**
	 * 功能:根据类型值修改类型
	 * @param typevalue		类型值
	 * @param typename		类型名
	 * @throws Exception
	 */
	void updateType(String typevalue, String typename) throws Exception;
	
	/**
	 * 功能:将类型设为系统级别
	 * @param typevalue		类型值
	 * @throws Exception
	 */
	void setTypeLevel(String typevalue) throws Exception;
	
	/**
	 * 功能:根据类型值删除类型
	 * @param typevalue		类型值
	 * @throws Exception
	 */
	void deleteType(String typevalue) throws Exception;
	
	/**
	 * 功能:修改类型
	 * @param type	类型
	 * @throws Exception
	 */
	void updateType(BaseType type) throws Exception;
	
	/**
	 * 功能:删除类型
	 * @param type	类型
	 * @throws Exception
	 */
	void deleteType(BaseType type) throws Exception;
    /**
     * 功能：获取字典中心类型
     * @throws Exception
     */
	List<BaseType> getType()throws Exception;
}
