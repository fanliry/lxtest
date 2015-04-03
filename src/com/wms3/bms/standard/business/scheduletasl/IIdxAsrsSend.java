package com.wms3.bms.standard.business.scheduletasl;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.schedule.IdxAsrsSend;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

public interface IIdxAsrsSend {
	
	public static byte[] schedule_lock = new byte[0]; 
	
	/**
	 * ��ѯ
	 * @param taskId
	 * @param asrsid
	 * @param palletId
	 * @param messageCode
	 * @param taskStatus
	 * @param location
	 * @param fromdate
	 * @param todate
	 * @return
	 * @throws Exception
	 */
	public List getIdxAsrsSend(String taskId, String asrsid, String palletId, String messageCode,String taskStatus, String location, String fromdate, String todate)
			throws Exception;
	/**
	 * ��ѯ
	 * @param taskId
	 * @param asrsid
	 * @param palletId
	 * @param messageCode
	 * @param taskStatus
	 * @param location
	 * @param fromdate
	 * @param todate
	 * @return
	 * @throws Exception
	 */
	public List getIdxAsrsSends(String taskId, String asrsid, String palletId, String messageCodes,String taskStatus, String location, String fromdate, String todate)
			throws Exception;
	/**
	 * ��ѯ
	 * @param taskId
	 * @param asrsid
	 * @param palletId
	 * @param messageCode
	 * @param taskStatus
	 * @param location
	 * @param fromdate
	 * @param todate
	 * @param strUrl
	 * @param maxLine
	 * @return
	 * @throws Exception
	 */
	public PagingTool getIdxAsrsSendPt(String taskId, String asrsid, String palletId, String messageCode,String taskStatus, String location, String fromdate, String todate, String strUrl, int maxLine)
			throws Exception;
	/**
	 * ���ܣ�������������ָ��
	 * @param scheduleLs
	 * @throws Exception
	 */
	public void addIdxAsrsSend(List<IdxAsrsSend> scheduleLs) throws Exception;
	
	/**
	 * ���ܣ�����ɾ������ָ��
	 * @param scheduleLs
	 * @throws Exception
	 */
	public void deleteIdxAsrsSend(List<IdxAsrsSend> scheduleLs) throws Exception;
	/**
	 * ���ܣ�������������ָ��
	 * @param scheduleLs
	 * @throws Exception
	 */
	public void updateIdxAsrsSend(List<IdxAsrsSend> scheduleLs) throws Exception;
	
	
	

}
