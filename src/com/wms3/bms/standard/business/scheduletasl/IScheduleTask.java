package com.wms3.bms.standard.business.scheduletasl;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

public interface IScheduleTask {
	
	public static byte[] schedule_lock = new byte[0]; 
	
	/**
	 * ���ܣ�ͨ��������ѯ����ָ�����״ֵ̬��
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
	 * ���ܣ�ͨ��������ѯ����ָ�״ֵ̬ϵ�У�
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
	 * ���ܣ�ͨ��������ѯ����ָ���ҳ��
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
	 * ���ܣ�������������ָ��
	 * @param scheduleLs
	 * @throws Exception
	 */
	public void addSchedule(List<ScheduleTask> scheduleLs) throws Exception;
	
	/**
	 * ���ܣ�����ɾ������ָ��
	 * @param scheduleLs
	 * @throws Exception
	 */
	public void deleteSchedule(List<ScheduleTask> scheduleLs) throws Exception;
	/**
	 * ���ܣ�������������ָ��
	 * @param scheduleLs
	 * @throws Exception
	 */
	public void updateSchedule(List<ScheduleTask> scheduleLs) throws Exception;
	
	
	

}
