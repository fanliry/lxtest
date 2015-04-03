package com.wms3.bms.standard.business.inventory;

import com.ricosoft.entity.competenceMgr.SystemLogInfo;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 描述:库存调整管理
 * @author yao
 *
 */
public interface InventoryAdjustBus 
{
	/**
	 * 功能:获得库存调整单查询的SQL语句
	 * @param status	状态
	 * @param customer	客户
	 * @return
	 */
	public String getAdjustHeaderSQL(String warehouseid, String status,String adjusttype,String reasoncode) throws Exception;
	/**
	 * 功能:获得库存调整单总记录查询的SQL语句
	 * @param sql	查询语句
	 * @return
	 */
	public String getAdjustHeaderCountSQL(String sql) throws Exception;
    /**
	 * 功能：添加库存信息，更新货位状态，增加调整明细,保存日志(库存调整，添加库存信息)
	 * @param inven
	 * @param cs
	 * @param adjustDetail
	 * @throws Exception
	 */
	public void addinvenUpdatecarsoAddAdjustDel(InventoryStorage iStorage,BaseCargospace cs,InventoryAdjustDetail adjustDetail,InventoryCheckResult checkResult,SystemLogInfo sysLog) throws Exception;
    /**
     * 功能：库存调整，执行调整（增加已完成作业，库存，更新货位状态）
     * @param adjustDetail
     * @return
     * @throws Exception
     */
	public Object[] addinvenForAdjust(InventoryAdjustDetail adjustDetail) throws Exception;
	public Object[] updateinvenForAdjust(InventoryAdjustDetail adjustDetail) throws Exception;
}
