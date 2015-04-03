package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseLotDetail;

/**
 * 
 * 描述: 批次属性详细信息DAO类接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseLotDetailDao {

	/**
	 * 功能:根据批次ID获得批次详细信息列表
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	List getListByLotId(String lotid) throws Exception;

	/**
	 * 功能:根据批次明细ID获得批次明细
	 * @param id	批次明细ID
	 * @return 
	 * @throws Exception
	 */
	BaseLotDetail getLotDetailByDetailId(String lotdetailid) throws Exception;
    
}
