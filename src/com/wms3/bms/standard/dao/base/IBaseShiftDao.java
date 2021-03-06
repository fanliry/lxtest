package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseShift;

/**
 * 
 * 描述: 班次DAO类接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseShiftDao {

	/**
	 * 功能:根据班次ID获得班次
	 * @param id	班次ID
	 * @return 
	 * @throws Exception
	 */
	public BaseShift getShiftById(String id) throws Exception;

	/**
	 * 功能:增加班次
	 * @param shift	班次
	 * @param lastshift 最后班次（出入库类型相同）
	 * @throws Exception
	 */
	public void addShift(BaseShift shift, BaseShift lastshift) throws Exception;

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

	/**
	 * 功能:根据班次ID获得班次
	 * @param strIn_Out_Type	入出类型
	 * @return 
	 * @throws Exception
	 */
	public BaseShift getLastShift(String strIn_Out_Type) throws Exception;

	/**
	 * 功能:根据班次名查询班次是否存在
	 * @param shiftname	班次名
	 * @param inouttype 入出类型
	 * @return 
	 */
	public boolean isShiftExist(String shiftname, String inouttype);

}
