package com.wms3.bms.standard.action.rf;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.inventory.IInventoryCheckBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryCheckBusImpl;
import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask;
/**
 * 描述：RF盘点结果录入action
 * @author liuxh
 *
 */
public class RFCheckAction extends AbstractAction {
	
	IInventoryCheckBus iCheckBus = null;
	@Override//根据盘点单和托盘条码获取任务明细
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
	    HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
	    EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
	    HttpSession httpsession = request.getSession(false);
	    String checkid =CommonTools.getStrToGbk(request.getParameter("checkid"));
	    String traycode =CommonTools.getStrToGbk(request.getParameter("traycode"));
	    
	    String strMsg = "";
	    String submitFlag = (String)httpsession.getAttribute("submitFlag"); //判断是否为重复提交的标志变量
	    try {
	    	iCheckBus = new InventoryCheckBusImpl(dao);
	    	InventoryCheckTask  checkTask = null;
	    	List<InventoryCheckTask> lsTasks = null; 
	    	List<InventoryCheckResult> lsResult = null; 
	    	InventoryCheckResult checkResult = null;
			if (submitFlag == "begin") {
				    String strQuerytasks = " from InventoryCheckTask as checktask where checktask.requestid='" + checkid 
				    		+ "' and checktask.traycode='"+ traycode + "'";
					lsTasks = dao.searchEntities(strQuerytasks);
					if (lsTasks!=null && lsTasks.size()>0) {
						checkTask = lsTasks.get(0);
					}else {
						//strMsg = "获取信息失败!\n你确定输入信息是否正确!";
						strMsg = "获取信息失败!请确定输入信息是否正确!";
					}
					String strQueryResults=" from InventoryCheckResult result where result.traycode='"+traycode +"'";
					lsResult = dao.searchEntities(strQueryResults);
					if (lsResult!=null && lsResult.size()>0) {
						checkResult = lsResult.get(0);
					}
					
					httpsession.setAttribute("submitFlag", "finish");
			}else {
					strMsg = "已提交了，不能重复提交！";
			}
			strUrl = "/rf/lxyy/checkResult_task.jsp";
			request.setAttribute("checkTask", checkTask);
			request.setAttribute("checkResult", checkResult);
			request.setAttribute("back_msg", strMsg); 
			request.getRequestDispatcher(strUrl).forward(request, response);
	    }catch (Exception er) {
	    	 Logger.error("RF==>获取盘点任务出错:" + er.getMessage());
	         request.setAttribute("errormsg", "错误信息:" + er.getMessage());
	         request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 功能：RF增加盘点结果
	 *@author liuxh 2013-3-18
	 *@param hsSysParam
	 *@param hsCurrentParam
	 *@return
	 *@throws Exception
	 */
	public String ricoExecAddCheckResult(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String taskid = CommonTools.getStrToGbk(request.getParameter("taskid"));		//盘点任务ID
		String checknum = CommonTools.getStrToGbk(request.getParameter("checknum"));	//盘点数量

		String strUserCode = (String)httpsession.getAttribute("userCode");
		iCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
				
			CommonReturn cReturn =  iCheckBus.addCheckResult(taskid, checknum,strUserCode);		
			String strBackMsg = cReturn.getRetInfo();
			List lsResult =  cReturn.getLsReturn();
			if(strBackMsg.equals("Y")){
				Logger.info("用户["+strUserCode+"]，对盘点任务:" + taskid + "新增了盘点结果，盘点数量：" + checknum );
			}
	    	
	    	strUrl = "/rf/lxyy/checkResult_task.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.setAttribute("exResultList", lsResult);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("用户["+strUserCode+"]，RF==>盘点结果录入==>新增盘点结果:" + taskid + "新增盘点结果失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;	
	}
	/**
	 * 功能：RF增加盘点结果
	 *@author liuxh 2013-3-18
	 *@param hsSysParam
	 *@param hsCurrentParam
	 *@return
	 *@throws Exception
	 */
	public String ricoExecUpdateCheckResult(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String taskid = CommonTools.getStrToGbk(request.getParameter("taskid"));		//盘点任务ID
		String checknum = CommonTools.getStrToGbk(request.getParameter("checknum"));	//盘点数量
		String resultid = CommonTools.getStrToGbk(request.getParameter("resultid"));	//盘点数量
		String strUserCode = (String)httpsession.getAttribute("userCode");
		iCheckBus = new InventoryCheckBusImpl(dao);
		
		try{		
			InventoryCheckResult checkResult = iCheckBus.getCheckResultById(resultid);
			checkResult.setChecknum(Double.valueOf(checknum));	
			String strBackMsg = iCheckBus.updateCheckResult(checkResult);
			if(strBackMsg.equals("Y")){
				Logger.info("用户["+strUserCode+"]，对盘点任务:" + taskid + "新增了盘点结果，盘点数量：" + checknum );
			}
	    	
	    	strUrl = "/rf/lxyy/checkResult_task.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("用户["+strUserCode+"]，RF==>盘点结果录入==>修改盘点结果:" + taskid + "新增盘点结果失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;	
	}
}
