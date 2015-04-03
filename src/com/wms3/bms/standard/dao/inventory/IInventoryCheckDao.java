package com.wms3.bms.standard.dao.inventory;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckRequest;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
/**
 * 
 * 描述: 库存DAO类接口
 * @author hug 2012-4-25
 * @since WMS 3.0
 */
public interface IInventoryCheckDao  extends IDao{

	/**
	 * 功能:添加盘点申请单
	 * @param checkReq	盘点申请单
	 * @return 
	 * @throws Exception
	 */
	void addCheckReq(InventoryCheckRequest checkReq) throws Exception;

	/**
	 * 功能:修改盘点申请单
	 * @param checkReq	盘点申请单
	 * @return 
	 * @throws Exception
	 */
	void updateCheckReq(InventoryCheckRequest checkReq) throws Exception;

	/**
	 * 功能:生成盘点任务，修改库存状态
	 * @param lstask	任务列表
	 * @param lsstorage	库存列表
	 * @param checkReq  盘点申请单
	 * @return 
	 * @throws Exception
	 */
	void addCheckTasks(List<InventoryCheckTask> lstask, List<InoutJob> lsJobs,List<InoutJobdetail> lsjobdels,List<InventoryStorage> lsstorage, InventoryCheckRequest checkReq) throws Exception;

	/**
	 * 功能:生成盘点结果，修改盘点任务和盘点申请的状态
	 * @param result	盘点结果
	 * @param task		盘点任务
	 * @param req  		盘点申请单
	 * @param storage 	库存
	 * @return 
	 * @throws Exception
	 */
	void addCheckResult(InventoryCheckResult result, InventoryCheckTask task, InventoryCheckRequest req, InventoryStorage storage) throws Exception;
	/**
	 * 功能:生成盘点结果，修改盘点任务和盘点申请的状态（富阳）
	 * @param result	盘点结果
	 * @param task		盘点任务
	 * @param req  		盘点申请单
	 * @return 
	 * @throws Exception
	 */
	void addCheckResult(InventoryCheckResult result, InventoryCheckTask task, InventoryCheckRequest req) throws Exception;
	/**
	 * 功能:关闭盘点申请,恢复库存状态为未分配
	 * @param req  			盘点申请单
	 * @param lsStorages  	库存列表
	 * @return 
	 * @throws Exception
	 */
	void closeReq(InventoryCheckRequest req, List lsStorages) throws Exception;
  
}
