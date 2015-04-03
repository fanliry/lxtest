package com.wms3.bms.standard.business.base;

import java.util.HashMap;
import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseLot;
import com.wms3.bms.standard.entity.base.BaseLotDetail;

/**
 * 
 * 描述: 批次属性管理业务接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface ILotBus {
	
	/**
	 * 功能:根据条件查询批次属性
	 * @param descr	描述
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getLotQuery(String descr, String strUrl, int maxLine) throws Exception;

	/**
	 * 功能:增加批次，批次详细信息
	 * @param lot	批次
	 * @param lotDetail1, lotDetail2, lotDetail3, lotDetail4, lotDetail5, lotDetail6, 
	 * @param lotDetail7, lotDetail8, lotDetail9, lotDetail10, lotDetail11, lotDetail12 批次详细信息
	 * @throws Exception
	 */
	public void addLot(BaseLot lot, BaseLotDetail lotDetail1, BaseLotDetail lotDetail2, BaseLotDetail lotDetail3, BaseLotDetail lotDetail4, 
			BaseLotDetail lotDetail5, BaseLotDetail lotDetail6, BaseLotDetail lotDetail7, BaseLotDetail lotDetail8, BaseLotDetail lotDetail9, 
			BaseLotDetail lotDetail10, BaseLotDetail lotDetail11, BaseLotDetail lotDetail12) throws Exception;

	/**
	 * 功能:根据批次ID获得批次
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public BaseLot getLotById(String lotid) throws Exception;

	/**
	 * 功能:根据批次ID获得批次明细信息列表
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public List getListByLotId(String lotid) throws Exception;
    /**
     * 
     * 功能: 功能:根据批次ID获得批次明细信息列表(HashMap)
     * @author hug 2012-8-30 
     * @param lotid   批次ID
     * @return
     * @throws Exception
     */
    public HashMap<String, BaseLotDetail> getHashMapByLotId(String lotid) throws Exception;

	/**
	 * 功能:根据批次明细ID获得批次明细
	 * @param id	批次明细ID
	 * @return 
	 * @throws Exception
	 */
	public BaseLotDetail getLotDetailByDetailId(String lotdetailid) throws Exception;

	/**
	 * 功能:修改批次，批次明细信息
	 * @param lot	批次
	 * @param lotDetail1, lotDetail2, lotDetail3, lotDetail4, lotDetail5, lotDetail6, 
	 * @param lotDetail7, lotDetail8, lotDetail9, lotDetail10, lotDetail11, lotDetail12 批次明细信息
	 * @throws Exception
	 */
	public void updateLot(BaseLot lot, BaseLotDetail lotDetail1, BaseLotDetail lotDetail2, BaseLotDetail lotDetail3, BaseLotDetail lotDetail4, 
			BaseLotDetail lotDetail5, BaseLotDetail lotDetail6, BaseLotDetail lotDetail7, BaseLotDetail lotDetail8, BaseLotDetail lotDetail9, 
			BaseLotDetail lotDetail10, BaseLotDetail lotDetail11, BaseLotDetail lotDetail12) throws Exception;

	/**
	 * 功能:删除批次，批次明细信息
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public void deleteLot(String id) throws Exception;

	/**
	 * 功能:获得所有批次属性
	 * @return 
	 * @throws Exception
	 */
	public List getAllLots() throws Exception;


}