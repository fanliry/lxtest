package com.wms3.bms.standard.business.inventory;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckRequest;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask;

/**
 * 描述: 库存盘点业务接口
 * @author fangjie
 * 
 */
public interface IInventoryCheckBus {

	/**
	 * 功能:根据条件查询盘点申请单
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param type			类型
	 * @param status		状态
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	PagingTool getCheckRequests(String warehouseid, String whAreaId, String type, String status,String lotnumber,String productid, String strUrl, int maxLine,String fmDate,String toDate) throws Exception;

	/**
	 * 功能:添加盘点申请单
	 * @param checkReq	盘点申请单
	 * @return 
	 * @throws Exception
	 */
	void addCheckReq(InventoryCheckRequest checkReq) throws Exception;

	/**
	 * 功能:根据盘点申请单查询盘点任务信息
	 * @param requestid		盘点申请ID
	 * @param tray		          托盘条码
	 * @return 
	 * @throws Exception
	 */
	List<InventoryCheckTask> getCheckTasks(String requestid,String tray) throws Exception;

	/**
	 * 功能:根据盘点申请ID查询盘点申请单
	 * @param requestid		盘点申请ID
	 * @return 
	 * @throws Exception
	 */
	InventoryCheckRequest getCheckReqById(String requestid) throws Exception;

	/**
	 * 功能:修改盘点申请单
	 * @param checkReq	盘点申请单
	 * @return 
	 * @throws Exception
	 */
	void updateCheckReq(InventoryCheckRequest checkReq) throws Exception;

	/**
	 * 功能:删除盘点申请单
	 * @param requestid		盘点申请ID
	 * @return 
	 * @throws Exception
	 */
	void deleteCheckReq(String requestid) throws Exception;
	/**
	 * 功能:完成盘点申请单
	 * @param requestid		盘点申请ID
	 * @return 
	 * @throws Exception
	 */
	void finishCheckReq(String requestid) throws Exception;

	/**
	 * 功能:根据盘点申请查询库存
	 * @param requestid		盘点申请ID
	 * @return 
	 * @throws Exception
	 */
	List queryStorage(String requestid) throws Exception;

	/**
	 * 功能:生成盘点任务
	 * @param requestid		盘点申请ID
	 * @param ids			库存ID
	 * @return 
	 * @throws Exception
	 */
	String addCheckTasks(String requestid, String ids) throws Exception;

	/**
	 * 功能:根据盘点任务查询盘点结果
	 * @param taskid		盘点任务ID
	 * @return 
	 * @throws Exception
	 */
	List getCheckResults(String taskid) throws Exception;
	/**
	 * 功能:根据盘点任务查询盘点结果
	 * @param taskid		盘点任务ID
	 * @return 
	 * @throws Exception
	 */
	List getCheckResultsByRequestid(String requestid) throws Exception;

	/**
	 * 功能:生成盘点结果
	 * @param taskid			盘点任务ID
	 * @param checknum			盘点数量
	 * @param checknetweight	盘点重量
	 * @param strUserCode 		用户ID
	 * @return 
	 * @throws Exception
	 */
	String addCheckResult(String taskid, String checknum, String checknetweight, String strUserCode) throws Exception;
	/**
	 * 功能:生成盘点结果（富阳）
	 * @param taskid			盘点任务ID
	 * @param checknum			盘点数量
	 * @param strUserCode 		用户ID
	 * @return 
	 * @throws Exception
	 */
	CommonReturn addCheckResult(String taskid, String checknum, String strUserCode) throws Exception;

	/**
	 * 功能:根据盘点结果ID查询盘点结果
	 * @param checkid		盘点结果ID
	 * @return 
	 * @throws Exception
	 */
	InventoryCheckResult getCheckResultById(String checkid) throws Exception;
    /**
     * 功能：根据盘盈盘亏和时间获得盘点结果
     * @param type  0.盘盈（盘点结果大于库存）1.盘亏（盘点结果小于库存）
     * @param fmtime 开始时间
     * @param totime 结束时间
     * @return
     * @throws Exception
     */
	CommonReturn getCheckResultsByTimeAndType(String type,String fmtime,String totime) throws Exception;
	/**
	 * 功能:修改盘点结果
	 * @param result		盘点结果
	 * @return 
	 * @throws Exception
	 */
	String updateCheckResult(InventoryCheckResult result) throws Exception;

	/**
	 * 功能:关闭盘点申请
	 * @param requestid		盘点申请ID
	 * @return 
	 * @throws Exception
	 */
	String closeCheckTasks(String requestid) throws Exception;
    /**
     * 查询盘点任务
     * @param requestid
     * @param traycode
     * @param platoon
     * @param column
     * @param floor
     * @return
     */
	List getCheckTasksbyTj(String requestid, String traycode, String platoon,
			String column, String floor) throws Exception;
    
	CommonReturn getCheckResultsByTimeAndType1(String type,String fmtime,String totime) throws Exception;

}
