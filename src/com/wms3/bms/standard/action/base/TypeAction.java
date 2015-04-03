package com.wms3.bms.standard.action.base;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.base.ITypeBus;
import com.wms3.bms.standard.business.base.impl.TypeBusImpl;
import com.wms3.bms.standard.entity.base.BaseType;

/**
 * 描述:类型管理
 * @author gui
 *
 */
public class TypeAction extends AbstractAction
{
	protected ITypeBus typeBus;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		
		String typeid = CommonTools.getStrToGbk(request.getParameter("typeid"));			//类型ID
		String typename = CommonTools.getStrToGbk(request.getParameter("typename"));		//类型名
		String typevalue = CommonTools.getStrToGbk(request.getParameter("typevalue"));		//类型值
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		typeBus = new TypeBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")){ 			//类型管理 查询类型列表
			
				strUrl = "/standard/jsp/base/type/base_type_list_left.jsp";	
				List ls = typeBus.getType(typename, typevalue);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("2")){		//类型管理 查询类型内容
				
				strUrl = "/standard/jsp/base/type/base_type_list_right.jsp";	
				List ls = typeBus.getTypeList(typevalue);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("3"))		//类型管理 修改类型列表
			{
				strUrl = "/standard/jsp/base/type/base_type_updateList.jsp";
				BaseType type = typeBus.getTypeById(typeid);
				request.setAttribute("type", type); 
			}
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>类型管理==>类型查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加类型
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		
		String typeid = CommonTools.getStrToGbk(request.getParameter("typeid"));			//类型ID
		String typename = CommonTools.getStrToGbk(request.getParameter("typename"));		//类型名
		String typevalue = CommonTools.getStrToGbk(request.getParameter("typevalue"));		//类型值
		String selecttext = CommonTools.getStrToGbk(request.getParameter("selecttext"));	//下拉列表显示内容
	    String selectvalue = CommonTools.getStrToGbk(request.getParameter("selectvalue"));	//下拉列表值
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String strUserName = (String)httpsession.getAttribute("userName");
		
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		typeBus = new TypeBusImpl(dao);
		
		try
		{	
			if(flag != null && flag.equals("1")){ 			//增加类型
				
				BaseType type = new BaseType();	
				type.setTypeid(typevalue);
				type.setTypevalue(typevalue);
				type.setTypename(typename);
				type.setTypelevel("");	//"Y"表示系统
				
				typeBus.addType(type);
				Logger.info("用户" + strUserName + "添加了类型" + typename);
				
				strUrl = "/standard/jsp/base/type/base_type_list_left.jsp";	
				List ls = typeBus.getType("", "");
				request.setAttribute("exResultList", ls);
				
			}if(flag != null && flag.equals("2")){ 			//增加类型列表
				
				BaseType type = new BaseType();	
				type.setTypeid(typeid);
				type.setTypevalue(typevalue);
				type.setTypename(typename);
				type.setSelecttext(selecttext);
				type.setSelectvalue(selectvalue);
				type.setTypelevel("");	//"Y"表示系统
				
				typeBus.addType(type);
				Logger.info("用户" + strUserName + "添加了类型:" + typename + "的下拉列表：" + selecttext);
				
				strUrl = "/standard/jsp/base/type/base_type_list_right.jsp";	
				List ls = typeBus.getTypeList(typevalue);
				request.setAttribute("exResultList", ls);
				
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
	        
		}catch(Exception er)
		{
			Logger.error("基本信息==>类型管理==>增加类型失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改类型
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	
	public String ricoExecEdit(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		
		String typeid = CommonTools.getStrToGbk(request.getParameter("typeid"));			//类型ID
		String typename = CommonTools.getStrToGbk(request.getParameter("typename"));		//类型名
		String typevalue = CommonTools.getStrToGbk(request.getParameter("typevalue"));		//类型值
		String selecttext = CommonTools.getStrToGbk(request.getParameter("selecttext"));	//下拉列表显示内容
	    String selectvalue = CommonTools.getStrToGbk(request.getParameter("selectvalue"));	//下拉列表值
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String strUserName = (String)httpsession.getAttribute("userName");

		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		typeBus = new TypeBusImpl(dao);
		
		try
		{

			if(flag != null && flag.equals("1")){ 			//修改类型
				
				typeBus.updateType(typevalue, typename);
				Logger.info("用户" + strUserName + "修改了类型, 类型值:" + typevalue + "，类型名:" + typename);
				
				strUrl = "/standard/jsp/base/type/base_type_list_left.jsp";	
				List ls = typeBus.getType("", "");
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("2")){		//修改类型下拉列表
				
				BaseType type = typeBus.getTypeById(typeid);
				type.setSelecttext(selecttext);
				type.setSelectvalue(selectvalue);
				
				typeBus.updateType(type);
				Logger.info("用户" + strUserName + "修改了类型的下拉列表:" + typeid);
				
				strUrl = "/standard/jsp/base/type/base_type_list_right.jsp";	
				List ls = typeBus.getTypeList(typevalue);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("3")){		//将类型设为系统级别
				
				typeBus.setTypeLevel(typevalue);
				Logger.info("用户" + strUserName + "将类型设为系统级别, 类型值:" + typevalue);
				
				strUrl = "/standard/jsp/base/type/base_type_list_left.jsp";	
				List ls = typeBus.getType("", "");
				request.setAttribute("exResultList", ls);
			}
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基体信息==>类型管理==>修改类型失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
	
	/**
	 * 功能:删除类型
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	
	public String ricoExecDelete(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		
		String typeid = CommonTools.getStrToGbk(request.getParameter("typeid"));			//类型ID
		String typevalue = CommonTools.getStrToGbk(request.getParameter("typevalue"));		//类型值
		String strUserName = (String)httpsession.getAttribute("userName");
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		typeBus = new TypeBusImpl(dao);
		
		try
		{
			if(flag != null && flag.equals("1")){ 			//删除类型
				
				typeBus.deleteType(typevalue);
				Logger.info("用户" + strUserName + "删除了类型, 类型值:" + typevalue);
				
				strUrl = "/standard/jsp/base/type/base_type_list_left.jsp";	
				List ls = typeBus.getType("", "");
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("2")){ 	//删除类型下拉列表
				BaseType type = typeBus.getTypeById(typeid);
				typeBus.deleteType(type);
				Logger.info("用户" + strUserName + "删除了类型的下拉列表:" + typeid);
				
				strUrl = "/standard/jsp/base/type/base_type_list_right.jsp";	
				String[] temp = typeid.split("_");
				List ls = typeBus.getTypeList(temp[0]);
				request.setAttribute("exResultList", ls);
				
			}
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基体信息==>类型管理==>删除类型失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
}
