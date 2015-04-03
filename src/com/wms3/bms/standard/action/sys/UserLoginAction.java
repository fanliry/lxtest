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
 * 描述:用户登录
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
			httpsession.invalidate(); //清除旧的session
			httpsession = null;
		}
		httpsession = request.getSession(true); //创建新的session
		httpsession.setMaxInactiveInterval(3600);//设置session失效时间(秒) 1小时
		
		
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
			
			//用户登录验证需带上仓库代码
			UserInfo user = userMgr.getUserInfoFromCode(strUserCode);
			//boolean bflag = userMgr.userExist(strUserCode, strPassword);
			if(user == null)
			{
				Logger.error("不存在的用户（" + strUserCode + "）！");
				if (strFlag.equals("rf")) {
					response.sendRedirect(request.getContextPath() + "/rf/login.jsp");
				} else {
					response.sendRedirect(request.getContextPath() + "/login.jsp");
				}
			}else if(!user.getM_strPassword().equals(strPassword)) //不相等
			{
				Logger.error("用户（" + strUserCode + "）密码错误！");
				if (strFlag.equals("rf"))
				{
					response.sendRedirect(request.getContextPath() + "/rf/login.jsp");
				}else{
					response.sendRedirect(request.getContextPath() + "/login.jsp");
				}
			}else if(user.getM_strUsableness() != null && !user.getM_strUsableness().equals("Y"))
			{
				Logger.error("用户（" + strUserCode + "）已不再启用！");
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
				
				//当前仓库ID
				String strWarehouseName = "";
				//String strWarehouseId = (String)hsSysParam.get("c_warhouse_id");
				hsSysParam.put("c_warhouse_id", strWarehouseId);
				
				hsSysParam.put("userCode", strUserCode);
				hsSysParam.put("userName", strUserName);
                
                HashMap<String, List> hsLot = lotSetBus.getViewLot();//显示的批次设置  
                hsSysParam.put("viewLot", hsLot);
                httpsession.setAttribute("viewLot",hsLot );     //显示的批次
				 
                //仓库名
				//strWarehouseName = mnw.getM_strWarehouseName();
				
				httpsession.setAttribute( "hsPopedom",hsPopedom );  //设置用户权限
				httpsession.setAttribute("userId", user.getM_strUserId());
				httpsession.setAttribute("userCode", strUserCode);
				httpsession.setAttribute("userName", strUserName);
				httpsession.setAttribute("password", strPassword);
				
				httpsession.setAttribute("warehouseName", strWarehouseName);	//仓库名称
				httpsession.setAttribute("warehouseid", strWarehouseId);	//仓库ID
				
				//httpsession.setAttribute("forwardurl", constant);	//路径跳转
				
				//日志
				SystemLogInfo sysLog = new SystemLogInfo();
				SysLogMgr logMgr = new SysLogMgr(dao);
				sysLog.setM_strLogCode(strUserCode);
				sysLog.setM_strLogName(strUserName);
				sysLog.setM_strModuleName("用户登录");
				sysLog.setM_strContent("用户成功登录");
				sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
				logMgr.addLog(sysLog);
				
				if(strFlag.equals("rf"))
				{
					System.out.println("RF用户("+ strUserName +")已登录!");
                    request.setAttribute("warehouseid", strWarehouseId);
					//标准的
					request.getRequestDispatcher("/rf/main.jsp").forward(request,response);	
				}
				else
				{
					System.out.println("用户("+ strUserName +")已登录!");
					request.getRequestDispatcher("/standard/include/index.jsp").forward(request,response);
				}
				
			}
		}catch(Exception er)
		{
			if(strFlag.equals("rf"))
			{
				response.sendRedirect(request.getContextPath() + "/rf/login.jsp");
				Logger.error("RF登陆失败:" + er.getMessage());
			}
			else
			{
				response.sendRedirect(request.getContextPath() + "/login.jsp");
				Logger.error("登陆失败:" + er.getMessage());
			}
		}
		return null;
	}
	/**
	 * 功能：修改密码
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
			//当前仓库
			String strWarehouseId = (String)hsSysParam.get("c_warhouse_id");
			//当前用户
			String strUserCode = (String)hsSysParam.get("userCode");
			
			String strMsg = "Y";
			
			UserMgr userMgr = new UserMgr(dao);
			
			//用户登录验证需带上仓库代码
			UserInfo user = userMgr.getUserInfoFromCode(strUserCode);
			//boolean bflag = userMgr.userExist(strUserCode, strPassword);
			if(user == null)
			{
				strMsg = "不存在的用户（" + strUserCode + "）！";
	
			}else if(!user.getM_strPassword().equals(strOldPassword)) //不相等
			{
				strMsg = "用户（" + strUserCode + "）密码错误！";
	
			}else if(user.getM_strUsableness() != null && !user.getM_strUsableness().equals("Y"))
			{
				strMsg ="用户（" + strUserCode + "）已不再启用！";
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
			Logger.error("修改密码失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
}
