package com.wms3.bms.standard.business.base;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseCarton;

/**
 * 
 * 描述:周转箱业务接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface ICartonBus {
	
	/**
	 * 功能:根据条件查询周转箱
	 * @param cartonid	装箱代码
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getCartonQuery(String cartonid, String strUrl, int maxLine) throws Exception;

	/**
	 * 功能:根据周转箱ID获得周转箱
	 * @param id	周转箱ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCarton getCartonById(String id) throws Exception;

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