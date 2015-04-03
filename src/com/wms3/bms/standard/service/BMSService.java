package com.wms3.bms.standard.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.ActionService;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.service.RicoService;
import com.wms3.bms.standard.config.ConfigFactory;
import com.wms3.bms.standard.config.WMSBMSConfig;
import com.wms3.bms.standard.constant.BMSConstant;
import com.wms3.bms.standard.thread.JobTaskSynThread;

/**
 * 描述:物流管理系统
 * @author hug
 *
 */
public class BMSService extends RicoService
{
	private static final long serialVersionUID = 6490823857046232618L;
	protected  WMSBMSConfig m_dbConfig = null;
	protected  static  String m_strDriverName = null;
	protected  static  String m_strServerPath = null;
	protected  static  EntityDAO m_dabaoDao = null;
	private  static  HashMap m_hsUrl = null;
	
	public void init(ServletConfig config) throws ServletException 
	{
		//super.init(config);
		//初始化配置参数
		m_dbConfig = (WMSBMSConfig)ConfigFactory.makeConfig(WMSBMSConfig.class,config);
		m_hsSysParam.put("config", m_dbConfig); 
		
		//初始化数据库操作对象
		m_entityDao = m_dbConfig.getDaoFactory().makeEntityDAO(EntityDAO.class);
		m_hsSysParam.put("dao", m_entityDao);
		
		//跳转类路径地址
		String strClassUrl = m_dbConfig.getM_strForwardUrl();
		if(strClassUrl == null)
		{
			strClassUrl = "com.wms3.bms.standard.constant.StandardConstant";
		}
		BMSConstant constant = null;
		try {
			//.newInstance 类似new 可以避开编译时的异常检测
			constant = (BMSConstant)Class.forName(strClassUrl).newInstance();
		} catch (Exception e) {
			Logger.error("初始化失败！");	
			e.printStackTrace();
		} 
		m_hsSysParam.put("ClassURL", constant);
		
		//******************************
		m_hsUrl = m_dbConfig.getM_hsUrl();
		
		System.out.println("监控系统地址:" + m_hsUrl.get("dmmsurl"));
		
		m_strDriverName = (String)((WMSBMSConfig)m_hsSysParam.get("config")).getm_hsCurrDBInfo().get("DriverClassName");
		
		m_strServerPath = config.getServletContext().getServletContextName();
		
		JobTaskSynThread jobtaskThread = new JobTaskSynThread(); //启动调度-作业 同步线程
		jobtaskThread.start();
		

	    
		
		
	}
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String  strActionCode = null;           //action标识  
		String strMethod = null;//方法名
		Hashtable hsCurrentParam = new Hashtable(); //创建当前交易参数列表
		String rfmainView = request.getParameter("rfmainView");//rf的action
		m_strServerPath = request.getContextPath();
		try{
				Logger.info("接收到客户端的请求" + request.getRemoteAddr());
			
			if(request.getParameter("actionCode") != null)
			{							
				strActionCode = (String)request.getParameter("actionCode");
				strMethod = (String)request.getParameter("method");
				if(strMethod == null)
				{
					strMethod = "";
				}
				if(!strActionCode.trim().equals("UserLoginAction") && !strActionCode.trim().equals("ValidationAction") && !strActionCode.trim().equals("imageAction"))
				{
					HttpSession httpsession = request.getSession(false);
					if(httpsession != null) //session超时
					{
						String strUserCode = (String)httpsession.getAttribute("userCode");
						if(strUserCode == null && rfmainView!=null && rfmainView.equals("rf"))
						{
							response.sendRedirect(request.getContextPath() + "/rf/mesg.jsp");
							return;
						}else if(strUserCode == null){
							response.sendRedirect(request.getContextPath() + "/mesg.jsp");
							return;
						}
					}else
					{
						/* ---------外部系统入口（系统接口） ---------------*/
						//当且仅当请求参数为 "outuser=outinterface" 时可处理请求
						if(request.getParameter("outuser")!=null)
						{
							String outuser = (String)request.getParameter("outuser");
							if(!outuser.equals("outinterface")){
								response.sendRedirect(request.getContextPath() + "/mesg.jsp");
								return;
							}
						}else
						{
							response.sendRedirect(request.getContextPath() + "/mesg.jsp");
							return;
						}
					}
				}				
				hsCurrentParam.put("actionCode", strActionCode);
				hsCurrentParam.put("method", strMethod);
				hsCurrentParam.put("request",request);
				hsCurrentParam.put("response",response);
				ActionService.performAction(m_hsSysParam, hsCurrentParam);
				return;
			}else
			{
				Logger.error("客户端请求中没有actionCode！");	
			}
			
		}catch(Exception er)
		{
			Logger.error("服务器处理失败!" + er.getMessage());
		}
		finally{			
				
		}

	}
	public void destroy() 
	{
		super.destroy();
	}
	/**
	 * 功能:获得EntityDAO
	 * @return
	 */
	public static EntityDAO getm_EntityDAO()
	{
		return m_entityDao;
	}
	/**
	 * 功能:获得数据库驱动名
	 * @return
	 */
	public static String getm_strDriverName()
	{
		return m_strDriverName;
	}
	/**
	 * 功能:获得路径
	 * @return
	 */
	public static String getm_strServerPath()
	{
		return m_strServerPath;
	}
	public static HashMap getHsUrl()
	{
		return m_hsUrl;
	}
}
