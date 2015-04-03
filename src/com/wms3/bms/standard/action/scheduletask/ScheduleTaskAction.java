package com.wms3.bms.standard.action.scheduletask;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.scheduletasl.IScheduleTask;
import com.wms3.bms.standard.business.scheduletasl.impl.ScheduleTaskImpl;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

public class ScheduleTaskAction extends AbstractAction {
	IScheduleTask schBus;

	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        schBus = new ScheduleTaskImpl(dao);

        String scheduleTaskId = CommonTools.getStrToGbk(request.getParameter("scheduleTaskId"));
        String scheduleType = CommonTools.getStrToGbk(request.getParameter("scheduleType"));
        String Fplatoon = CommonTools.getStrToGbk(request.getParameter("Fplatoon"));
        String Fcolumn = CommonTools.getStrToGbk(request.getParameter("Fcolumn"));
        String Ffloor = CommonTools.getStrToGbk(request.getParameter("Ffloor"));
        String Tplatoon = CommonTools.getStrToGbk(request.getParameter("Tplatoon"));
        String Tcolumn = CommonTools.getStrToGbk(request.getParameter("Tcolumn"));
        String Tfloor = CommonTools.getStrToGbk(request.getParameter("Tfloor"));
        String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));
        String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId"));
        String status = CommonTools.getStrToGbk(request.getParameter("status"));
        String createtime = CommonTools.getStrToGbk(request.getParameter("createtime"));
        String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));
        int maxLine = Integer.parseInt(CommonTools.getStrToGbk(request.getParameter("maxLine")));
        strUrl = "standard/jsp/scheduleMgr/instructionMgr_list.jsp";
        try {
        	PagingTool pt = schBus.getScheduleTaskPt(scheduleTaskId, scheduleType, Fplatoon, Fcolumn, Ffloor, Tplatoon, Tcolumn, Tfloor, whAreaId, alleyId, status,createtime, traycode, strUrl, maxLine);
            List ls = pt.getLsResult();
    		request.setAttribute("exResultList", ls);
    		request.setAttribute("pagingTool", pt);
    		httpsession.setAttribute("paging", pt);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		request.getRequestDispatcher(strUrl).forward(request, response);
        
		return null;
	}
	
	public String ricoFinashExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        schBus = new ScheduleTaskImpl(dao);

        String scheduleTaskIds = CommonTools.getStrToGbk(request.getParameter("scheduleTaskIds"));
        int num = 0;
        String flag = "Y";
        String[] id = scheduleTaskIds.split(",");
        List resultLs = new ArrayList();
        try {
        	for (int i = 0; i < id.length; i++) {
        		List ls = schBus.getScheduleTask(id[i], null, null, null, null, null, null, null, null, null, "1,2,3", null);
        		if(ls != null && ls.size() > 0)
        		{
        			ScheduleTask st = (ScheduleTask)ls.get(0);
        			st.setStatus("4");
        			resultLs.add(st);
        		}else
        		{
        			num++;
        		}
        	}
        	schBus.updateSchedule(resultLs);
        	if(num != 0)
        	{
        		flag = num + "个命令未执行或已作废或已完成，无法完成作业";
        	}
    		request.setAttribute("back_msg", flag);
        	ricoExec(hsSysParam, hsCurrentParam);
		} catch (Exception er) {
			throw new Exception("完成调度命令出错：" + er.getMessage());
		}
        
		return null;
	}
	/**
	 * 调度任务初始化  
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoIniExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        schBus = new ScheduleTaskImpl(dao);

        String scheduleTaskIds = CommonTools.getStrToGbk(request.getParameter("scheduleTaskIds"));
        int num = 0;
        String flag = "Y";
        String[] id = scheduleTaskIds.split(",");
        List resultLs = new ArrayList();
        try {
        	for (int i = 0; i < id.length; i++) {
        		//正执行作业改成待执行作业
        		List ls = schBus.getScheduleTask(id[i], null, null, null, null, null, null, null, null, null, "3", null);
        		if(ls != null && ls.size() > 0)
        		{
        			ScheduleTask st = (ScheduleTask)ls.get(0);
        			st.setStatus("2");
        			resultLs.add(st);
        		}else
        		{
        			num++;
        		}
        	}
        	schBus.updateSchedule(resultLs);
        	if(num != 0)
        	{
        		flag = num + "个命令未完成，无法初始化";
        	}
    		request.setAttribute("back_msg", flag);
        	ricoExec(hsSysParam, hsCurrentParam);
		} catch (Exception er) {
			throw new Exception("完成调度命令出错：" + er.getMessage());
		}
        
		return null;
	}
	
	public String ricoCancelExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        schBus = new ScheduleTaskImpl(dao);

        String scheduleTaskIds = CommonTools.getStrToGbk(request.getParameter("scheduleTaskIds"));
        int num = 0;
        String flag = "Y";
        String[] id = scheduleTaskIds.split(",");
        List resultLs = new ArrayList();
        try {
        	for (int i = 0; i < id.length; i++) {
        		List ls = schBus.getScheduleTask(id[i], null, null, null, null, null, null, null, null, null, "2", null);
        		if(ls != null && ls.size() > 0)
        		{
        			ScheduleTask st = (ScheduleTask)ls.get(0);
        			st.setStatus("5");
        			resultLs.add(st);
        		}else
        		{
        			num++;
        		}
        	}
        	schBus.updateSchedule(resultLs);
        	if(num != 0)
        	{
        		flag = num + "个命令无法作废,只有待执行作业才可以作废";
        	}
    		request.setAttribute("back_msg", flag);
        	ricoExec(hsSysParam, hsCurrentParam);
		} catch (Exception er) {
			throw new Exception("作废调度命令出错：" + er.getMessage());
		}
        
		return null;
	}

}
