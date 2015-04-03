package com.wms3.bms.standard.business.scheduletasl.impl;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.scheduletasl.IIdxAsrsSend;
import com.wms3.bms.standard.entity.schedule.IdxAsrsSend;
public class IdxAsrsSendImpl implements IIdxAsrsSend {
	protected EntityDAO m_dao;
	public IdxAsrsSendImpl(EntityDAO dao) {
		m_dao = dao;
	}
	@Override
	public List getIdxAsrsSend(String taskId, String asrsid, String palletId, String messageCode,String taskStatus, String location, String fromdate, String todate) throws Exception {
		List list = null;
		
		try {
			String sql = "From IdxAsrsSend st where 1=1 ";
			if(taskId != null && taskId.length() > 0)
			{
				sql += " and st.taskId='" + taskId + "'";
			}
			if(asrsid != null && asrsid.length() > 0)
			{
				sql += " and st.asrsid='" + asrsid + "'";
			}
			if(palletId != null && palletId.length() > 0)
			{
				sql += " and st.palletId='" + palletId + "'";
			}
			if(messageCode != null && messageCode.length() > 0)
			{
				sql += " and st.messageCode='" + messageCode + "'";
			}
			if(taskStatus != null && taskStatus.length() > 0)
			{
				sql += " and st.taskStatus='" + taskStatus + "'";
			}
			if(location != null && location.length() > 0)
			{
				sql += " and st.location='" + location + "'";
			}
			if(fromdate != null && fromdate.length() > 0)
			{
				sql += " and st.creationTime>='" + fromdate + "'";
			}
			if(todate != null && todate.length() > 0)
			{
				sql += " and st.creationTime<='" + todate + "' 99";
			}
			String strHQL = sql + " order by st.taskId,st.creationTime";
			list=m_dao.searchEntities(strHQL);
		} catch (Exception e) {
			Logger.error("查询异常表出现问题："+e.getMessage());
		}
		
		return list;
	}
	@Override
	public List getIdxAsrsSends(String taskId, String asrsid, String palletId, String messageCodes,String taskStatus, String location, String fromdate, String todate) throws Exception {
		List list = null;
		
		try {
			String sql = "From IdxAsrsSend st where 1=1 ";
			if(taskId != null && taskId.length() > 0)
			{
				sql += " and st.taskId='" + taskId + "'";
			}
			if(asrsid != null && asrsid.length() > 0)
			{
				sql += " and st.asrsid='" + asrsid + "'";
			}
			if(palletId != null && palletId.length() > 0)
			{
				sql += " and st.palletId='" + palletId + "'";
			}
			if(messageCodes != null && messageCodes.length() > 0)
			{
				sql += " and st.messageCode in(" + messageCodes + ")";
			}
			if(taskStatus != null && taskStatus.length() > 0)
			{
				sql += " and st.taskStatus='" + taskStatus + "'";
			}
			if(location != null && location.length() > 0)
			{
				sql += " and st.location='" + location + "'";
			}
			if(fromdate != null && fromdate.length() > 0)
			{
				sql += " and st.creationTime>='" + fromdate + "'";
			}
			if(todate != null && todate.length() > 0)
			{
				sql += " and st.creationTime<='" + todate + "' 99";
			}
			String strHQL = sql + " order by st.taskId,st.creationTime";
			list=m_dao.searchEntities(strHQL);
		} catch (Exception e) {
			Logger.error("查询异常表出现问题："+e.getMessage());
		}
		
		return list;
	}
	@Override
	/**
	 * 查询
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
			throws Exception{
		PagingTool pt = null;
		try {
			String sql = "From IdxAsrsSend st where 1=1 ";
			if(taskId != null && taskId.length() > 0)
			{
				sql += " and st.taskId='" + taskId + "'";
			}
			if(asrsid != null && asrsid.length() > 0)
			{
				sql += " and st.asrsid='" + asrsid + "'";
			}
			if(palletId != null && palletId.length() > 0)
			{
				sql += " and st.palletId='" + palletId + "'";
			}
			if(messageCode != null && messageCode.length() > 0)
			{
				sql += " and st.messageCode='" + messageCode + "'";
			}
			if(taskStatus != null && taskStatus.length() > 0)
			{
				sql += " and st.taskStatus='" + taskStatus + "'";
			}
			if(location != null && location.length() > 0)
			{
				sql += " and st.location='" + location + "'";
			}
			if(fromdate != null && fromdate.length() > 0)
			{
				sql += " and st.creationTime>='" + fromdate + "'";
			}
			if(todate != null && todate.length() > 0)
			{
				sql += " and st.creationTime<='" + todate + "' 99";
			}
			String strHQL = sql + " order by st.taskId,st.creationTime";
			String strCountHQL = "select count(*) " + sql;
			pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);
		} catch (Exception e) {
			Logger.error("查询异常表出现问题："+e.getMessage());
		}
		return pt;
	}
	@Override
	public void addIdxAsrsSend(List<IdxAsrsSend> scheduleLs) throws Exception {
		synchronized(schedule_lock)
		{
			Session session = null;
			Transaction tx = null;
			try {
				session = m_dao.getSession();
				tx = session.beginTransaction();
				if(scheduleLs != null && scheduleLs.size() > 0)
				{
					IdxAsrsSend st = null;
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
	public void deleteIdxAsrsSend(List<IdxAsrsSend> scheduleLs) throws Exception {
		synchronized(schedule_lock)
		{
			Session session = null;
			Transaction tx = null;
			try {
				session = m_dao.getSession();
				tx = session.beginTransaction();
				if(scheduleLs != null && scheduleLs.size() > 0)
				{
					IdxAsrsSend st= null;
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
	public void updateIdxAsrsSend(List<IdxAsrsSend> scheduleLs) throws Exception {
		synchronized(schedule_lock)
		{
			Session session = null;
			Transaction tx = null;
			try {
				session = m_dao.getSession();
				tx = session.beginTransaction();
				if(scheduleLs != null && scheduleLs.size() > 0)
				{
					IdxAsrsSend st = null;
					for (int i = 0; i < scheduleLs.size(); i++) {
						st = scheduleLs.get(i);
						session.update(st);
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
