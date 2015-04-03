package com.wms3.bms.standard.business.scheduletasl;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

public interface IScheduleTask {
	
	public static byte[] schedule_lock = new byte[0]; 
	
	/**
	 * 功能：通过条件查询调度指令（单个状态值）
	 * @param scheduleTaskId
	 * @param scheduleStatus
	 * @param Fplatoon
	 * @param Fcolumn
	 * @param Ffloor
	 * @param Tplatoon
	 * @param Tcolumn
	 * @param Tfloor
	 * @param whAreaId
	 * @param alleyId
	 * @param status
	 * @param traycode
	 * @return
	 * @throws Exception
	 */
	public List getScheduleTask(String scheduleTaskId, String scheduleType, String Fplatoon, String Fcolumn, 
			String Ffloor, String Tplatoon, String Tcolumn, String Tfloor, String whAreaId, String alleyId, String status, String traycode)
			throws Exception;
	/**
	 * 功能：通过条件查询调度指令（状态值系列）
	 * @param scheduleTaskId
	 * @param scheduleType
	 * @param Fplatoon
	 * @param Fcolumn
	 * @param Ffloor
	 * @param Tplatoon
	 * @param Tcolumn
	 * @param Tfloor
	 * @param whAreaId
	 * @param alleyId
	 * @param statuss
	 * @param traycode
	 * @return
	 * @throws Exception
	 */
	public List getScheduleTaskByStatus(String scheduleTaskId, String scheduleType,
			String Fplatoon, String Fcolumn, String Ffloor, String Tplatoon,
			String Tcolumn, String Tfloor, String whAreaId, String alleyId,
			String statuss, String traycode) throws Exception;
	/**
	 * 功能：通过条件查询调度指令（分页）
	 * @param scheduleTaskId
	 * @param scheduleStatus
	 * @param Fplatoon
	 * @param Fcolumn
	 * @param Ffloor
	 * @param Tplatoon
	 * @param Tcolumn
	 * @param Tfloor
	 * @param whAreaId
	 * @param alleyId
	 * @param status
	 * @param traycode
	 * @return
	 * @throws Exception
	 */
	public PagingTool getScheduleTaskPt(String scheduleTaskId, String scheduleType, String Fplatoon, String Fcolumn, 
			String Ffloor, String Tplatoon, String Tcolumn, String Tfloor, String whAreaId, String alleyId, String status, String createtime, String traycode, String strUrl, int maxLine)
			throws Exception;
	/**
	 * 功能：批量增加任务指令
	 * @param scheduleLs
	 * @throws Exception
	 */
	public void addSchedule(List<ScheduleTask> scheduleLs) throws Exception;
	
	/**
	 * 功能：批量删除任务指令
	 * @param scheduleLs
	 * @throws Exception
	 */
	public void deleteSchedule(List<ScheduleTask> scheduleLs) throws Exception;
	/**
	 * 功能：批量更新任务指令
	 * @param scheduleLs
	 * @throws Exception
	 */
	public void updateSchedule(List<ScheduleTask> scheduleLs) throws Exception;
	
	
	

}
