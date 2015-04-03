package com.wms3.bms.standard.business.quality;

import java.util.List;

import com.wms3.bms.standard.entity.inventory.InventoryQualityResult;
import com.wms3.bms.standard.entity.inventory.InventoryQualityResultDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
/**
 * 描述:库存调整管理
 * @author yao
 *
 */
public interface InventoryQualityBus 
{
	/**
	 * 功能:获得状态转换单查询的SQL语句
	 * @param strLotNumber	批号
	 * @param strFormDate	日期
	 * @param strToDate 	日期
	 * @return
	 */
	public String getQualityHeaderSQL(String strLotNumber, String strFormDate, String strToDate)throws Exception;
	/**
	 * @param strLotNumber	批号
	 * @param strFormDate	日期
	 * @param strToDate 	日期
	 * @return
	 */
	public String getQualityHeaderCountSQL(String strLotNumber, String strFormDate, String strToDate) throws Exception;
	/**
	 * 功能：更新库存，创建放行记录单及明细
	 *@author liuxh 2013-3-8
	 *@param lsajustDetail 放行明细集合
	 *@param lsinventory   库存集合
	 *@param adjust        放行单
	 *@throws Exception
	 */
	public void createqualityInvoice(List<InventoryQualityResultDetail> lsajustDetail, List<InventoryStorage> lsinventory, InventoryQualityResult adjust) throws Exception;

	/**
	 * 功能：根据id获取放行单及详细的信息
	 * @param strId
	 * @return
	 * @throws Exception
	 */
	public InventoryQualityResult getAdjustListToId(String strId) throws Exception;
}
