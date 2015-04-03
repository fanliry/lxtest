package com.wms3.bms.standard.dao.job.impl;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.job.ITaskDao;
import com.wms3.bms.standard.dao.outbound.IAssginDao;
import com.wms3.bms.standard.dao.outbound.IOutboundJobDao;
import com.wms3.bms.standard.dao.outbound.impl.AssginDaoImpl;
import com.wms3.bms.standard.dao.outbound.impl.OutboundJobDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

public class TaskDaoImpl extends AbstractDao implements ITaskDao {
	
	protected IOutboundJobDao outboundJobDao;
	
	public TaskDaoImpl(EntityDAO dao){
        this.m_dao = dao;
        outboundJobDao = new OutboundJobDaoImpl(dao);
    }

	/** DAO类 */


	/**
     * 功能：根据码盘线获得为执行的任务
     * @author liuxh 2014-1-7
     * @param snumber
     * @return
     * @throws Exception
     */
	public int getTasksBySnumber(String snumber) throws Exception {
        int iCount = 0;
        try{
            String strSQL = "select count(sed.taskid) from ScheduleTask as sed where sed.status='2' and sed.taskpos='1' and sed.tasktype='1' and sed.snumber='" + snumber + "'";
            iCount = m_dao.searchEntitieCount(strSQL);
        }catch(Exception er){
            throw new Exception("[JobDaoImpl.getSnumberTaskCount]根据码盘线["+ snumber +"]获得有几条新建的入库任务出错：" + er.getMessage());
        }
        return iCount;
	}
    /**
     * 功能：根据托盘条码查看已经完成的任务
     * @author liuxh 2014-1-7
     * @param traycode
     * @return
     * @throws Exception
     */
	public int getTasksByTraycode(String traycode) throws Exception {
		int iCount = 0;
		if (traycode!=null) {
            String strSQL = "select count(sed.taskid) from ScheduleTask as sed where sed.status='4' and sed.taskpos='1' and sed.tasktype='1' and sed.traycode='" + traycode + "'";
            iCount = m_dao.searchEntitieCount(strSQL);
		}
		return iCount;
	}

    /**
     * 功能：根据托盘条码删除以完成的调度任务
     * @author fanll 2014-8-13
     * @param traycode
     * @return
     * @throws Exception
     */
	public boolean delTasksByTraycode(String traycode) throws Exception {
		Boolean flag = true;
		try {
			if (traycode != null) {
				String strSQL = "delete ScheduleTask where traycode='" + traycode + "'";
				m_dao.deleteSql(strSQL);
			}
		} catch (Exception er) {
			flag = false;
			throw new Exception("根据托盘条码[" + traycode + "]删除以完成的调度任务出错："
					+ er.getMessage());
		}
		return flag;
	}

	
    /**
     * 功能：添加调度作业(已事务处理)
     * @param cargoSpaceId  List<ScheduleTask> 
     * @param cargoSpaceId  EntityDAO dao
     */
    public void addScheduleTask(List<ScheduleTask> lsTask,EntityDAO dao) throws Exception {

        StatelessSession session = null; //大批量操作时使用
        Transaction tx = null;
        Connection conn = null;
        try{
            conn = dao.getConnection();//从连接池获得连接
            session = dao.getSessionFactory().openStatelessSession(conn);
            tx = session.beginTransaction();

            //增加调度
            if(lsTask != null){
                ScheduleTask task = null;
                for(int i = 0; i < lsTask.size(); i++){
                    task = lsTask.get(i);
                    session.insert(task);
                }
            }
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("添加调度作业："+er.getMessage());
        }finally{
            if(session != null)
            {
                session.close();
            }
            if(conn != null){
                conn.close(); //关闭连接
            }
        }       
    }
    
    
    /**
     * 功能：根据托盘条码删除以完成的调度任务
     * @author fanll 2014-8-13
     * @param traycode
     * @return
     * @throws Exception
     */
	public boolean delTasksByTraycode(String traycode,EntityDAO dao) throws Exception {
		Boolean flag = true;
		try {
			if (traycode != null) {
				String strSQL = "delete ScheduleTask where traycode='" + traycode + "'";
				dao.deleteSql(strSQL);
			}
		} catch (Exception er) {
			flag = false;
			throw new Exception("根据托盘条码[" + traycode + "]删除以完成的调度任务出错："
					+ er.getMessage());
		}
		return flag;
	}
    
	
	/**
	 * 功能:手动完成作业，修改调度任务状态为完成
	 * @param  ScheduleTask
	 * @return 
	 * @throws Exception
	 */
	public void finishJobupdate(ScheduleTask task) throws Exception {
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = m_dao.getSession();
            tx = session.beginTransaction();
	
            
            //修改货位状态
            session.update(task);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("手动完成作业，根据托盘条码修改调度任务状态为完成时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
	
	/**
	 * 功能:根据托盘条码查询调度
	 * 
	 * @param jobid
	 *            作业号
	 * @return
	 * @throws Exception
	 */
	public List getScheduleTaskls(String traycode) throws Exception {

		String strSql = "";
		List list = null;

		try {
			strSql = " from ScheduleTask as tk where tk.traycode='" + traycode + "'";

			list = outboundJobDao.querySQL(strSql);

		} catch (Exception er) {
			er.printStackTrace();
			throw new Exception("出库管理->查询作业详细列表时候出错：" + er.getMessage());
		}

		return list;
	}
	/**
	 * 功能:根据托盘条码查询调度
	 * @param traycode			托盘条码
	 * @return 
	 * @throws Exception
	 */
	public ScheduleTask getScheduleTaskBytraycode(String traycode,EntityDAO dao) throws Exception {
		
		if(traycode != null){
		
			String strSql = " from ScheduleTask as t where t.traycode='" + traycode + "'";
			List ls = dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (ScheduleTask)ls.get(0);
			}
		}
		return null;
	}
	/**
	 * 功能:根据调度指令id查询调度
	 * @param taskid			
	 * @return 
	 * @throws Exception
	 */
	public ScheduleTask getScheduleTaskByTaskid(String taskid) throws Exception{
		if(taskid != null){
			String strSql = " from ScheduleTask as t where t.taskid='" + taskid + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (ScheduleTask)ls.get(0);
			}
		}
		return null;
	}
}
