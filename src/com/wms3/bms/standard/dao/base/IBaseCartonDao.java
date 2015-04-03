package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseCarton;

/**
 * 
 * 描述: 周转箱DAO类接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseCartonDao {

	/**
	 * 功能:根据周转箱ID获得周转箱
	 * @param id	周转箱ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCarton getCartonById(String id) throws Exception;

	/**
	 * 功能:获得最大的一个周转箱编码
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public String getMaxCartonNo() throws Exception;

	/**
	 * 功能:增加周转箱
	 * @param carton	周转箱
	 * @throws Exception
	 */
	public void addCarton(BaseCarton carton) throws Exception;

	/**
	 * 功能:获取所有周转箱列表
	 * @return 
	 * @throws Exception
	 */
	public List getCartonList() throws Exception;

	/**
	 * 功能:修改周转箱
	 * @param carton	周转箱
	 * @throws Exception
	 */
	public void updateCarton(BaseCarton carton) throws Exception;

	/**
	 * 功能:删除周转箱
	 * @param id	周转箱ID
	 * @throws Exception
	 */
	public void deleteCarton(String id) throws Exception;

}
