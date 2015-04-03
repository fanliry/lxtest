package com.wms3.bms.standard.action.sys;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.business.competenceMgr.PopedomMgr;
import com.ricosoft.business.competenceMgr.SysLogMgr;
import com.ricosoft.business.competenceMgr.UserMgr;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.common.tools.StrTools;
import com.ricosoft.entity.competenceMgr.SystemLogInfo;
import com.ricosoft.entity.competenceMgr.UserInfo;
import com.wms3.bms.standard.business.base.ILotSetBus;
import com.wms3.bms.standard.business.base.impl.LotSetBusImpl;


/**
 * ����:�û���¼
 * @author yao
 *
 */
public class UserLoginAction extends AbstractAction
{
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		if(httpsession != null)
		{
			httpsession.invalidate(); //����ɵ�session
			httpsession = null;
		}
		httpsession = request.getSession(true); //�����µ�session
		httpsession.setMaxInactiveInterval(3600);//����sessionʧЧʱ��(��) 1Сʱ
		
		
		String strUserCode = CommonTools.getStrToGb2312(request.getParameter("username"));
		String strPassword = CommonTools.getStrToGb2312(request.getParameter("password"));
		String strFlag    = CommonTools.getStrToGb2312(request.getParameter("flag"));
		
		String strWarehouseId = CommonTools.getStrToGb2312(request.getParameter("warehouseid"));
        ILotSetBus lotSetBus = new LotSetBusImpl(dao);
		try
		{
			String strUserName = "";
			String strUserId = "";
			PopedomMgr popedomMgr = new PopedomMgr(dao);
			UserMgr userMgr = new UserMgr(dao);
			
			//�û���¼��֤����ϲֿ����
			UserInfo user = userMgr.getUserInfoFromCode(strUserCode);
			//boolean bflag = userMgr.userExist(strUserCode, strPassword);
			if(user == null)
			{
				Logger.error("�����ڵ��û���" + strUserCode + "����");
				if (strFlag.equals("rf")) {
					response.sendRedirect(request.getContextPath() + "/rf/login.jsp");
				} else {
					response.sendRedirect(request.getContextPath() + "/login.jsp");
				}
			}else if(!user.getM_strPassword().equals(strPassword)) //�����
			{
				Logger.error("�û���" + strUserCode + "���������");
				if (strFlag.equals("rf"))
				{
					response.sendRedirect(request.getContextPath() + "/rf/login.jsp");
				}else{
					response.sendRedirect(request.getContextPath() + "/login.jsp");
				}
			}else if(user.getM_strUsableness() != null && !user.getM_strUsableness().equals("Y"))
			{
				Logger.error("�û���" + strUserCode + "���Ѳ������ã�");
				if (strFlag.equals("rf")) 
				{
					response.sendRedirect(request.getContextPath() + "/rf/login.jsp");
				} else {
					response.sendRedirect(request.getContextPath() + "/login.jsp");
				}
			}else
			{
				//UserInfo user = userMgr.getUserInfoFromCode(strUserCode);
				strUserName = user.getM_strUsername();
				strUserId = user.getM_strUserId();
				
				HashMap  hsPopedom = null;
				List     lsPopedom = null;
				
				if(strUserId.equals("root"))
				{
					lsPopedom = popedomMgr.getLsAllPopedom();
			    	hsPopedom = popedomMgr.getHsAllPopedom(lsPopedom);
				}else
				{
					lsPopedom = popedomMgr.getLsPopedomFromUserCode(strUserCode);
			    	hsPopedom = popedomMgr.getHsAllPopedom(lsPopedom);
				}
				
				//��ǰ�ֿ�ID
				String strWarehouseName = "";
				//String strWarehouseId = (String)hsSysParam.get("c_warhouse_id");
				hsSysParam.put("c_warhouse_id", strWarehouseId);
				
				hsSysParam.put("userCode", strUserCode);
				hsSysParam.put("userName", strUserName);
                
                HashMap<String, List> hsLot = lotSetBus.getViewLot();//��ʾ����������  
                hsSysParam.put("viewLot", hsLot);
                httpsession.setAttribute("viewLot",hsLot );     //��ʾ������
				 
                //�ֿ���
				//strWarehouseName = mnw.getM_strWarehouseName();
				
				httpsession.setAttribute( "hsPopedom",hsPopedom );  //�����û�Ȩ��
				httpsession.setAttribute("userId", user.getM_strUserId());
				httpsession.setAttribute("userCode", strUserCode);
				httpsession.setAttribute("userName", strUserName);
				httpsession.setAttribute("password", strPassword);
				
				httpsession.setAttribute("warehouseName", strWarehouseName);	//�ֿ�����
				httpsession.setAttribute("warehouseid", strWarehouseId);	//�ֿ�ID
				
				//httpsession.setAttribute("forwardurl", constant);	//·����ת
				
				//��־
				SystemLogInfo sysLog = new SystemLogInfo();
				SysLogMgr logMgr = new SysLogMgr(dao);
				sysLog.setM_strLogCode(strUserCode);
				sysLog.setM_strLogName(strUserName);
				sysLog.setM_strModuleName("�û���¼");
				sysLog.setM_strContent("�û��ɹ���¼");
				sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
				logMgr.addLog(sysLog);
				
				if(strFlag.equals("rf"))
				{
					System.out.println("RF�û�("+ strUserName +")�ѵ�¼!");
                    request.setAttribute("warehouseid", strWarehouseId);
					//��׼��
					request.getRequestDispatcher("/rf/main.jsp").forward(request,response);	
				}
				else
				{
					System.out.println("�û�("+ strUserName +")�ѵ�¼!");
					request.getRequestDispatcher("/standard/include/index.jsp").forward(request,response);
				}
				
			}
		}catch(Exception er)
		{
			if(strFlag.equals("rf"))
			{
				response.sendRedirect(request.getContextPath() + "/rf/login.jsp");
				Logger.error("RF��½ʧ��:" + er.getMessage());
			}
			else
			{
				response.sendRedirect(request.getContextPath() + "/login.jsp");
				Logger.error("��½ʧ��:" + er.getMessage());
			}
		}
		return null;
	}
	/**
	 * ���ܣ��޸�����
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecUpdatePassword(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		//HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String strOldPassword = CommonTools.getStrToGb2312(request.getParameter("oldpassword"));
		String strNewPassword = CommonTools.getStrToGb2312(request.getParameter("password1"));
		
		try
		{
			//��ǰ�ֿ�
			String strWarehouseId = (String)hsSysParam.get("c_warhouse_id");
			//��ǰ�û�
			String strUserCode = (String)hsSysParam.get("userCode");
			
			String strMsg = "Y";
			
			UserMgr userMgr = new UserMgr(dao);
			
			//�û���¼��֤����ϲֿ����
			UserInfo user = userMgr.getUserInfoFromCode(strUserCode);
			//boolean bflag = userMgr.userExist(strUserCode, strPassword);
			if(user == null)
			{
				strMsg = "�����ڵ��û���" + strUserCode + "����";
	
			}else if(!user.getM_strPassword().equals(strOldPassword)) //�����
			{
				strMsg = "�û���" + strUserCode + "���������";
	
			}else if(user.getM_strUsableness() != null && !user.getM_strUsableness().equals("Y"))
			{
				strMsg ="�û���" + strUserCode + "���Ѳ������ã�";
			}else
			{
				user.setM_strPassword(strNewPassword);
				userMgr.updateUserInfo(user);
			}
			
			strUrl = "/jsp/competenceMgr/password_update.jsp";
			
			request.setAttribute("backMsg", strMsg);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
		
			
		}catch(Exception er)
		{
			Logger.error("�޸�����ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
}
