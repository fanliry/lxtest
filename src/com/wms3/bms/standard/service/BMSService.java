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
 * ����:��������ϵͳ
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
		//��ʼ�����ò���
		m_dbConfig = (WMSBMSConfig)ConfigFactory.makeConfig(WMSBMSConfig.class,config);
		m_hsSysParam.put("config", m_dbConfig); 
		
		//��ʼ�����ݿ��������
		m_entityDao = m_dbConfig.getDaoFactory().makeEntityDAO(EntityDAO.class);
		m_hsSysParam.put("dao", m_entityDao);
		
		//��ת��·����ַ
		String strClassUrl = m_dbConfig.getM_strForwardUrl();
		if(strClassUrl == null)
		{
			strClassUrl = "com.wms3.bms.standard.constant.StandardConstant";
		}
		BMSConstant constant = null;
		try {
			//.newInstance ����new ���Աܿ�����ʱ���쳣���
			constant = (BMSConstant)Class.forName(strClassUrl).newInstance();
		} catch (Exception e) {
			Logger.error("��ʼ��ʧ�ܣ�");	
			e.printStackTrace();
		} 
		m_hsSysParam.put("ClassURL", constant);
		
		//******************************
		m_hsUrl = m_dbConfig.getM_hsUrl();
		
		System.out.println("���ϵͳ��ַ:" + m_hsUrl.get("dmmsurl"));
		
		m_strDriverName = (String)((WMSBMSConfig)m_hsSysParam.get("config")).getm_hsCurrDBInfo().get("DriverClassName");
		
		m_strServerPath = config.getServletContext().getServletContextName();
		
		JobTaskSynThread jobtaskThread = new JobTaskSynThread(); //��������-��ҵ ͬ���߳�
		jobtaskThread.start();
		

	    
		
		
	}
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String  strActionCode = null;           //action��ʶ  
		String strMethod = null;//������
		Hashtable hsCurrentParam = new Hashtable(); //������ǰ���ײ����б�
		String rfmainView = request.getParameter("rfmainView");//rf��action
		m_strServerPath = request.getContextPath();
		try{
				Logger.info("���յ��ͻ��˵�����" + request.getRemoteAddr());
			
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
					if(httpsession != null) //session��ʱ
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
						/* ---------�ⲿϵͳ��ڣ�ϵͳ�ӿڣ� ---------------*/
						//���ҽ����������Ϊ "outuser=outinterface" ʱ�ɴ�������
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
				Logger.error("�ͻ���������û��actionCode��");	
			}
			
		}catch(Exception er)
		{
			Logger.error("����������ʧ��!" + er.getMessage());
		}
		finally{			
				
		}

	}
	public void destroy() 
	{
		super.destroy();
	}
	/**
	 * ����:���EntityDAO
	 * @return
	 */
	public static EntityDAO getm_EntityDAO()
	{
		return m_entityDao;
	}
	/**
	 * ����:������ݿ�������
	 * @return
	 */
	public static String getm_strDriverName()
	{
		return m_strDriverName;
	}
	/**
	 * ����:���·��
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
