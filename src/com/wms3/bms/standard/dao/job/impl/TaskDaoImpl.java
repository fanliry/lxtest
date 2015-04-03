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

	/** DAO�� */


	/**
     * ���ܣ����������߻��Ϊִ�е�����
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
            throw new Exception("[JobDaoImpl.getSnumberTaskCount]����������["+ snumber +"]����м����½�������������" + er.getMessage());
        }
        return iCount;
	}
    /**
     * ���ܣ�������������鿴�Ѿ���ɵ�����
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
     * ���ܣ�������������ɾ������ɵĵ�������
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
			throw new Exception("������������[" + traycode + "]ɾ������ɵĵ����������"
					+ er.getMessage());
		}
		return flag;
	}

	
    /**
     * ���ܣ���ӵ�����ҵ(��������)
     * @param cargoSpaceId  List<ScheduleTask> 
     * @param cargoSpaceId  EntityDAO dao
     */
    public void addScheduleTask(List<ScheduleTask> lsTask,EntityDAO dao) throws Exception {

        StatelessSession session = null; //����������ʱʹ��
        Transaction tx = null;
        Connection conn = null;
        try{
            conn = dao.getConnection();//�����ӳػ������
            session = dao.getSessionFactory().openStatelessSession(conn);
            tx = session.beginTransaction();

            //���ӵ���
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
            throw new  Exception("��ӵ�����ҵ��"+er.getMessage());
        }finally{
            if(session != null)
            {
                session.close();
            }
            if(conn != null){
                conn.close(); //�ر�����
            }
        }       
    }
    
    
    /**
     * ���ܣ�������������ɾ������ɵĵ�������
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
			throw new Exception("������������[" + traycode + "]ɾ������ɵĵ����������"
					+ er.getMessage());
		}
		return flag;
	}
    
	
	/**
	 * ����:�ֶ������ҵ���޸ĵ�������״̬Ϊ���
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
	
            
            //�޸Ļ�λ״̬
            session.update(task);
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�ֶ������ҵ���������������޸ĵ�������״̬Ϊ���ʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}
	
	/**
	 * ����:�������������ѯ����
	 * 
	 * @param jobid
	 *            ��ҵ��
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
			throw new Exception("�������->��ѯ��ҵ��ϸ�б�ʱ�����" + er.getMessage());
		}

		return list;
	}
	/**
	 * ����:�������������ѯ����
	 * @param traycode			��������
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
	 * ����:���ݵ���ָ��id��ѯ����
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
