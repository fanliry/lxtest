package com.wms3.bms.standard.dao.job;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * ����������DAO������ӿ�
 * @author liuxh2014
 * @since wmsBMS3.0
 */
public interface ITaskDao extends IDao{
    /**
     * ���ܣ����������߻��Ϊִ�е�����
     * @author liuxh 2014-1-7
     * @param snumber
     * @return
     * @throws Exception
     */
	public int getTasksBySnumber(String snumber) throws Exception;
	/**
     * ���ܣ�������������鿴�Ѿ���ɵ�����
	 * @author liuxh 2014-1-7
	 * @param traycode
	 * @return
	 * @throws Exception
	 */
	public int getTasksByTraycode(String traycode) throws Exception;
    /**
     * ���ܣ�������������ɾ������ɵĵ�������
     * @author fanll 2014-8-13
     * @param traycode
     * @return
     * @throws Exception
     */
	public boolean delTasksByTraycode(String traycode) throws Exception;
	/**
	 * ����:���ݵ���ָ��id��ѯ����
	 * @param taskid			
	 * @return 
	 * @throws Exception
	 */
	public ScheduleTask getScheduleTaskByTaskid(String taskid) throws Exception;
}
