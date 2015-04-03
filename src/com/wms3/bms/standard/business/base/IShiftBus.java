package com.wms3.bms.standard.business.base;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseShift;

/**
 * 
 * 描述: 班次管理业务接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IShiftBus {
	
	/**
	 * 功能:根据条件查询班次
	 * @param strOp_Man_Id		负责人
	 * @param strIn_Out_Type	入出类型
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getShiftQuery(String strOp_Man_Id, String strIn_Out_Type, String strUrl, int maxLine) throws Exception;

	/**
	 * 功能:根据班次ID获得班次
	 * @param strIn_Out_Type	入出类型
	 * @return 
	 * @throws Exception
	 */
	public BaseShift getLastShift(String strIn_Out_Type) throws Exception;
	
	/**
	 * 功能:获的最后一个班次
	 * @param id	班次ID
	 * @return 
	 * @throws Exception
	 */
	public BaseShift getShiftById(String id) throws Exception;

	/**
	 * 功能:增加班次
	 * @param shift	班次
	 * @throws Exception
	 */
	public String addShift(BaseShift shift) throws Exception;

	/**
	 * 功能:获取所有班次列表
	 * @return 
	 * @throws Exception
	 */
	public List getShiftList() throws Exception;

	/**
	 * 功能:修改班次
	 * @param shift	班次
	 * @throws Exception
	 */
	public void updateShift(BaseShift shift) throws Exception;

	/**
	 * 功能:删除班次
	 * @param id	班次ID
	 * @throws Exception
	 */
	public void deleteShift(String id) throws Exception;
	
}