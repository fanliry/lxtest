package com.wms3.bms.standard.dao.job;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * 描述：任务DAO类操作接口
 * @author liuxh2014
 * @since wmsBMS3.0
 */
public interface ITaskDao extends IDao{
    /**
     * 功能：根据码盘线获得为执行的任务
     * @author liuxh 2014-1-7
     * @param snumber
     * @return
     * @throws Exception
     */
	public int getTasksBySnumber(String snumber) throws Exception;
	/**
     * 功能：根据托盘条码查看已经完成的任务
	 * @author liuxh 2014-1-7
	 * @param traycode
	 * @return
	 * @throws Exception
	 */
	public int getTasksByTraycode(String traycode) throws Exception;
    /**
     * 功能：根据托盘条码删除以完成的调度任务
     * @author fanll 2014-8-13
     * @param traycode
     * @return
     * @throws Exception
     */
	public boolean delTasksByTraycode(String traycode) throws Exception;
	/**
	 * 功能:根据调度指令id查询调度
	 * @param taskid			
	 * @return 
	 * @throws Exception
	 */
	public ScheduleTask getScheduleTaskByTaskid(String taskid) throws Exception;
}
