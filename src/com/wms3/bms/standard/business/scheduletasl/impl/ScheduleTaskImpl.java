package com.wms3.bms.standard.business.scheduletasl.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.scheduletasl.IScheduleTask;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

public class ScheduleTaskImpl implements IScheduleTask {
	
	protected EntityDAO m_dao;
	public ScheduleTaskImpl(EntityDAO dao) {
		m_dao = dao;
	}

	@Override
	public List getScheduleTask(String scheduleTaskId, String scheduleType,
			String Fplatoon, String Fcolumn, String Ffloor, String Tplatoon,
			String Tcolumn, String Tfloor, String whAreaId, String alleyId,
			String status, String traycode) throws Exception {
		List list = null;
		
		try {
			String sql = "From ScheduleTask st where 1=1 ";
			if(scheduleTaskId != null && scheduleTaskId.length() > 0)
			{
				sql += " and st.taskid='" + scheduleTaskId + "'";
			}
			if(scheduleType != null && scheduleType.length() > 0)
			{
				sql += " and st.tasktype='" + scheduleType + "'";
			}
			if(Fplatoon != null && Fplatoon.length() > 0)
			{
				sql += " and st.splatoon='" + Fplatoon + "'";
			}
			if(Fcolumn != null && Fcolumn.length() > 0)
			{
				sql += " and st.scolumn='" + Fcolumn + "'";
			}
			if(Ffloor != null && Ffloor.length() > 0)
			{
				sql += " and st.sfloor='" + Ffloor + "'";
			}
			if(Tplatoon != null && Tplatoon.length() > 0)
			{
				sql += " and st.tplatoon='" + Tplatoon + "'";
			}
			if(Tcolumn != null && Tcolumn.length() > 0)
			{
				sql += " and st.tcolumn='" + Tcolumn + "'";
			}
			if(Tfloor != null && Tfloor.length() > 0)
			{
				sql += " and st.tfloor='" + Tfloor + "'";
			}
			if(whAreaId != null && whAreaId.length() > 0)
			{
				sql += " and st.whAreaId='" + whAreaId + "'";
			}
			if(alleyId != null && alleyId.length() > 0)
			{
				sql += " and st.cargoAlleyId='" + alleyId + "'";
			}
			if(status != null && status.length() > 0)
			{
				String[] statusStr = status.split(",");
				sql += " and st.status in (";
				for(int i = 0; i < statusStr.length; i++)
				{
					sql += "'" + statusStr[i] + "'";
					if(i != statusStr.length - 1)
					{
						sql += ",";
					}
				}
				sql += ")";
			}
			if(traycode != null && traycode.length() > 0)
			{
				sql += " and st.traycode='" + traycode + "'";
			}
			String strHQL = sql + " order by st.taskid";
			list=m_dao.searchEntities(strHQL);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	public List getScheduleTaskByStatus(String scheduleTaskId, String scheduleType,
			String Fplatoon, String Fcolumn, String Ffloor, String Tplatoon,
			String Tcolumn, String Tfloor, String whAreaId, String alleyId,
			String statuss, String traycode) throws Exception {
		List list = null;
		
		try {
			String sql = "From ScheduleTask st where 1=1 ";
			if(scheduleTaskId != null && scheduleTaskId.length() > 0)
			{
				sql += " and st.taskid='" + scheduleTaskId + "'";
			}
			if(scheduleType != null && scheduleType.length() > 0)
			{
				sql += " and st.tasktype='" + scheduleType + "'";
			}
			if(Fplatoon != null && Fplatoon.length() > 0)
			{
				sql += " and st.splatoon='" + Fplatoon + "'";
			}
			if(Fcolumn != null && Fcolumn.length() > 0)
			{
				sql += " and st.scolumn='" + Fcolumn + "'";
			}
			if(Ffloor != null && Ffloor.length() > 0)
			{
				sql += " and st.sfloor='" + Ffloor + "'";
			}
			if(Tplatoon != null && Tplatoon.length() > 0)
			{
				sql += " and st.tplatoon='" + Tplatoon + "'";
			}
			if(Tcolumn != null && Tcolumn.length() > 0)
			{
				sql += " and st.tcolumn='" + Tcolumn + "'";
			}
			if(Tfloor != null && Tfloor.length() > 0)
			{
				sql += " and st.tfloor='" + Tfloor + "'";
			}
			if(whAreaId != null && whAreaId.length() > 0)
			{
				sql += " and st.whAreaId='" + whAreaId + "'";
			}
			if(alleyId != null && alleyId.length() > 0)
			{
				sql += " and st.cargoAlleyId='" + alleyId + "'";
			}
			if(statuss != null && statuss.length() > 0)
			{
				sql += " and st.status in(" + statuss + ")";
			}
			if(traycode != null && traycode.length() > 0)
			{
				sql += " and st.traycode='" + traycode + "'";
			}
			String strHQL = sql + " order by st.taskid";
			list=m_dao.searchEntities(strHQL);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}

	@Override
	public PagingTool getScheduleTaskPt(String scheduleTaskId,
			String scheduleType, String Fplatoon, String Fcolumn,
			String Ffloor, String Tplatoon, String Tcolumn, String Tfloor,
			String whAreaId, String alleyId, String status, String createtime, String traycode, String strUrl, int maxLine)
			throws Exception {
		PagingTool pt = null;
		
		try {
			String sql = "From ScheduleTask st where 1=1 ";
			if(scheduleTaskId != null && scheduleTaskId.length() > 0)
			{
				sql += " and st.taskid='" + scheduleTaskId + "'";
			}
			if(scheduleType != null && scheduleType.length() > 0)
			{
				sql += " and st.tasktype='" + scheduleType + "'";
			}
			if(Fplatoon != null && Fplatoon.length() > 0)
			{
				sql += " and st.splatoon='" + Fplatoon + "'";
			}
			if(Fcolumn != null && Fcolumn.length() > 0)
			{
				sql += " and st.scolumn='" + Fcolumn + "'";
			}
			if(Ffloor != null && Ffloor.length() > 0)
			{
				sql += " and st.sfloor='" + Ffloor + "'";
			}
			if(Tplatoon != null && Tplatoon.length() > 0)
			{
				sql += " and st.tplatoon='" + Tplatoon + "'";
			}
			if(Tcolumn != null && Tcolumn.length() > 0)
			{
				sql += " and st.tcolumn='" + Tcolumn + "'";
			}
			if(Tfloor != null && Tfloor.length() > 0)
			{
				sql += " and st.tfloor='" + Tfloor + "'";
			}
			if(whAreaId != null && whAreaId.length() > 0)
			{
				sql += " and st.whAreaId='" + whAreaId + "'";
			}
			if(alleyId != null && alleyId.length() > 0)
			{
				sql += " and st.cargoAlleyId='" + alleyId + "'";
			}
			if(status != null && status.length() > 0)
			{
				sql += " and st.status='" + status + "'";
			}
			if(createtime != null && createtime.trim().length() > 0){
				sql += " and st.createtime like '%"+createtime+"%'";
			}
			if(traycode != null && traycode.length() > 0)
			{
				sql += " and st.traycode='" + traycode + "'";
			}
			String strHQL = sql + " order by st.taskid";
			String strCountHQL = "select count(*) " + sql;
			
			pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pt;
	}

	@Override
	public void addSchedule(List<ScheduleTask> scheduleLs) throws Exception {
		synchronized(schedule_lock)
		{
			Session session = null;
			Transaction tx = null;
			try {
				session = m_dao.getSession();
				tx = session.beginTransaction();
				if(scheduleLs != null && scheduleLs.size() > 0)
				{
					ScheduleTask st = null;
					for (int i = 0; i < scheduleLs.size(); i++) {
						st = scheduleLs.get(i);
						session.save(st);
					}
				}
				tx.commit();
			} catch (Exception er) {
				if(tx != null){
	                tx.rollback();
	            }
				throw new  Exception("批量增加指令信息出错！"+er.getMessage());
			}finally
			{
				m_dao.closeSession(session);
			}
		}
	}

	@Override
	public void deleteSchedule(List<ScheduleTask> scheduleLs) throws Exception {
		synchronized(schedule_lock)
		{
			Session session = null;
			Transaction tx = null;
			try {
				session = m_dao.getSession();
				tx = session.beginTransaction();
				if(scheduleLs != null && scheduleLs.size() > 0)
				{
					ScheduleTask st= null;
					for (int i = 0; i < scheduleLs.size(); i++) {
						st = scheduleLs.get(i);
						session.delete(st);
					}
				}
				tx.commit();
			} catch (Exception er) {
				if(tx != null)
				{
					tx.rollback();
				}
				throw new Exception("批量删除指令错误：" + er.getMessage());
			}finally
			{
				m_dao.closeSession(session);
			}
			
		}
	}

	@Override
	public void updateSchedule(List<ScheduleTask> scheduleLs) throws Exception {
		synchronized(schedule_lock)
		{
			Session session = null;
			Transaction tx = null;
			try {
				session = m_dao.getSession();
				tx = session.beginTransaction();
				if(scheduleLs != null && scheduleLs.size() > 0)
				{
					ScheduleTask st = null;
					for (int i = 0; i < scheduleLs.size(); i++) {
						st = scheduleLs.get(i);
						session.update("ScheduleTask",st);
					}
				}
				tx.commit();
			} catch (Exception er) {
				if(tx != null)
				{
					tx.rollback();
				}
				throw new Exception("批量更新指令错误：" + er.getMessage());
			}finally
			{
				m_dao.closeSession(session);
			}
		}

	}

}
