package com.wms3.bms.standard.business.base;

import java.util.HashMap;
import java.util.List;

import com.wms3.bms.standard.entity.base.BaseLotSet;

/**
 * 
 * 描述: 批次属性设置管理业务接口
 * @author hug 2012-3-12
 * @since WMS 3.0
 */
public interface ILotSetBus {
	
	/**
	 * 功能:查询批次属性设置不同类型
	 * @return 
	 * @throws Exception
	 */
	List getLotsetType() throws Exception;

	/**
	 * 功能:增加批次属性设置
	 * @param lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, 
	 * @param lotset7, lotset8, lotset9, lotset10, lotset11, lotset12
	 * @throws Exception
	 */
	void addLotset(BaseLotSet lotset1, BaseLotSet lotset2, BaseLotSet lotset3, BaseLotSet lotset4, BaseLotSet lotset5, BaseLotSet lotset6, 
			BaseLotSet lotset7, BaseLotSet lotset8, BaseLotSet lotset9, BaseLotSet lotset10, BaseLotSet lotset11, BaseLotSet lotset12) throws Exception;

	/**
	 * 功能:根据类型查询批次属性设置的列表
	 * @param lottype	类型
	 * @return 
	 * @throws Exception
	 */
	List getLotsetByType(String lottype) throws Exception;
	
	/**
	 * 功能:删除批次属性设置
	 * @param lottype	类型
	 * @return 
	 * @throws Exception
	 */
	void deleteLotset(String lottype) throws Exception;

	/**
	 * 功能:根据Id查询批次属性设置
	 * @param lotattid	Id
	 * @return 
	 * @throws Exception
	 */
	BaseLotSet getLotsetById(String lotattid) throws Exception;

	/**
	 * 功能:修改批次属性设置
	 * @param lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, 
	 * @param lotset7, lotset8, lotset9, lotset10, lotset11, lotset12
	 * @throws Exception
	 */
	void updateLotset(BaseLotSet lotset1, BaseLotSet lotset2, BaseLotSet lotset3, BaseLotSet lotset4, BaseLotSet lotset5, BaseLotSet lotset6, 
			BaseLotSet lotset7, BaseLotSet lotset8, BaseLotSet lotset9, BaseLotSet lotset10, BaseLotSet lotset11, BaseLotSet lotset12) throws Exception;

	/**
	 * 功能:判断该类型是否存在
	 * @param lottype	类型
	 * @throws Exception
	 */
	boolean isLotsetExist(String lottype) throws Exception;
    
    /**
     * 根据批次类型获得可显示的批次属性，并按设置的顺序排序
     * @author hug 2012-8-22 
     * @param strLotType  显示的批次类型
     * @return
     * @throws Exception
     */
    public List<BaseLotSet> getViewLot(String strLotType) throws Exception;
    
    /**
     * 功能：获取所有可显示的批次信息(按设置的顺序排序)
     * @author hug 2012-8-21 
     * @return
     * @throws Exception
     */
    public HashMap<String, List> getViewLot() throws Exception; 
	
}
