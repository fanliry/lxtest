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
 * ������RF�̵���¼��action
 * @author liuxh
 *
 */
public class RFCheckAction extends AbstractAction {
	
	IInventoryCheckBus iCheckBus = null;
	@Override//�����̵㵥�����������ȡ������ϸ
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
	    HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
	    EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
	    HttpSession httpsession = request.getSession(false);
	    String checkid =CommonTools.getStrToGbk(request.getParameter("checkid"));
	    String traycode =CommonTools.getStrToGbk(request.getParameter("traycode"));
	    
	    String strMsg = "";
	    String submitFlag = (String)httpsession.getAttribute("submitFlag"); //�ж��Ƿ�Ϊ�ظ��ύ�ı�־����
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
						//strMsg = "��ȡ��Ϣʧ��!\n��ȷ��������Ϣ�Ƿ���ȷ!";
						strMsg = "��ȡ��Ϣʧ��!��ȷ��������Ϣ�Ƿ���ȷ!";
					}
					String strQueryResults=" from InventoryCheckResult result where result.traycode='"+traycode +"'";
					lsResult = dao.searchEntities(strQueryResults);
					if (lsResult!=null && lsResult.size()>0) {
						checkResult = lsResult.get(0);
					}
					
					httpsession.setAttribute("submitFlag", "finish");
			}else {
					strMsg = "���ύ�ˣ������ظ��ύ��";
			}
			strUrl = "/rf/lxyy/checkResult_task.jsp";
			request.setAttribute("checkTask", checkTask);
			request.setAttribute("checkResult", checkResult);
			request.setAttribute("back_msg", strMsg); 
			request.getRequestDispatcher(strUrl).forward(request, response);
	    }catch (Exception er) {
	    	 Logger.error("RF==>��ȡ�̵��������:" + er.getMessage());
	         request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
	         request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * ���ܣ�RF�����̵���
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

		String taskid = CommonTools.getStrToGbk(request.getParameter("taskid"));		//�̵�����ID
		String checknum = CommonTools.getStrToGbk(request.getParameter("checknum"));	//�̵�����

		String strUserCode = (String)httpsession.getAttribute("userCode");
		iCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
				
			CommonReturn cReturn =  iCheckBus.addCheckResult(taskid, checknum,strUserCode);		
			String strBackMsg = cReturn.getRetInfo();
			List lsResult =  cReturn.getLsReturn();
			if(strBackMsg.equals("Y")){
				Logger.info("�û�["+strUserCode+"]�����̵�����:" + taskid + "�������̵������̵�������" + checknum );
			}
	    	
	    	strUrl = "/rf/lxyy/checkResult_task.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.setAttribute("exResultList", lsResult);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("�û�["+strUserCode+"]��RF==>�̵���¼��==>�����̵���:" + taskid + "�����̵���ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;	
	}
	/**
	 * ���ܣ�RF�����̵���
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

		String taskid = CommonTools.getStrToGbk(request.getParameter("taskid"));		//�̵�����ID
		String checknum = CommonTools.getStrToGbk(request.getParameter("checknum"));	//�̵�����
		String resultid = CommonTools.getStrToGbk(request.getParameter("resultid"));	//�̵�����
		String strUserCode = (String)httpsession.getAttribute("userCode");
		iCheckBus = new InventoryCheckBusImpl(dao);
		
		try{		
			InventoryCheckResult checkResult = iCheckBus.getCheckResultById(resultid);
			checkResult.setChecknum(Double.valueOf(checknum));	
			String strBackMsg = iCheckBus.updateCheckResult(checkResult);
			if(strBackMsg.equals("Y")){
				Logger.info("�û�["+strUserCode+"]�����̵�����:" + taskid + "�������̵������̵�������" + checknum );
			}
	    	
	    	strUrl = "/rf/lxyy/checkResult_task.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("�û�["+strUserCode+"]��RF==>�̵���¼��==>�޸��̵���:" + taskid + "�����̵���ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;	
	}
}
