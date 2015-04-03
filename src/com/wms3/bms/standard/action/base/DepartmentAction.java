package com.wms3.bms.standard.action.base;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.base.IDepartmentBus;
import com.wms3.bms.standard.business.base.impl.DepartmentBusImpl;
import com.wms3.bms.standard.entity.base.BaseDepartment;

/**
 * 描述:部门管理
 * @author gui
 *
 */
public class DepartmentAction extends AbstractAction
{
	protected IDepartmentBus departmentBus;
	protected int maxLine = 25;		//分页显示的行数；
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String departmentname = CommonTools.getStrToGbk(request.getParameter("departmentname"));	//部门名称
		String departmenttype = CommonTools.getStrToGbk(request.getParameter("departmenttype"));	//部门分类
		String id = CommonTools.getStrToGbk(request.getParameter("id"));							//部门Id
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		departmentBus = new DepartmentBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //部门管理 查询列表
			{
				strUrl = "/standard/jsp/base/department/base_department_list.jsp";
				
				List ls = departmentBus.getDepartmentQuery(departmentname, departmenttype);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//部门管理 修改时获取信息
			{
				strUrl = "/standard/jsp/base/department/base_department_update.jsp";
				
				BaseDepartment department = departmentBus.getDepartmentById(id);
				request.setAttribute("department", department); 
				
			}else if(flag != null && flag.equals("3")) //部门物品 查询列表
			{
				strUrl = "/standard/jsp/common/common_department_list.jsp";
				
				PagingTool pt = departmentBus.getDepartmentQuery(departmentname, departmenttype, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				request.setAttribute("pagingTool", pt);
				httpsession.setAttribute("commpaging", pt);
					
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>部门管理==>部门查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加部门
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
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String shortname = CommonTools.getStrToGbk(request.getParameter("shortname"));			//部门简称
		String departmentname = CommonTools.getStrToGbk(request.getParameter("departmentname"));	//部门全称
		String departmenttype = CommonTools.getStrToGbk(request.getParameter("departmenttype"));	//部门分类
		String contact = CommonTools.getStrToGbk(request.getParameter("contact"));				//联系人
		String phone = CommonTools.getStrToGbk(request.getParameter("phone"));					//联系电话
		String fax = CommonTools.getStrToGbk(request.getParameter("fax"));						//传真
		String address = CommonTools.getStrToGbk(request.getParameter("address"));				//地址
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		departmentBus = new DepartmentBusImpl(dao);
		try
		{
        	//新增部门信息
			BaseDepartment department = new BaseDepartment(shortname, departmentname, departmenttype, 
					contact, phone, fax, address, currentTime, strUserCode, currentTime, strUserCode, "Y");		 		
			departmentBus.addDepartment(department);
			
			Logger.info("用户" + strUserName + "添加了部门" + departmentname);

	        strUrl = "/standard/jsp/base/department/base_department_list.jsp";
	        List ls = departmentBus.getDepartmentList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>部门管理==>增加部门失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改部门
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
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));							//部门Id
		String shortname = CommonTools.getStrToGbk(request.getParameter("shortname"));			//部门简称
		String departmentname = CommonTools.getStrToGbk(request.getParameter("departmentname"));	//部门全称
		String departmenttype = CommonTools.getStrToGbk(request.getParameter("departmenttype"));	//部门分类
		String contact = CommonTools.getStrToGbk(request.getParameter("contact"));				//联系人
		String phone = CommonTools.getStrToGbk(request.getParameter("phone"));					//联系电话
		String fax = CommonTools.getStrToGbk(request.getParameter("fax"));						//传真
		String address = CommonTools.getStrToGbk(request.getParameter("address"));				//地址

		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		departmentBus = new DepartmentBusImpl(dao);
		try
		{
			if(id != null && id.length()>0)
			{
				BaseDepartment department = departmentBus.getDepartmentById(id);
				department.setShortname(shortname);				//部门简称
				department.setDepartmentname(departmentname);	//部门全称
				department.setDepartmenttype(departmenttype);	//部门分类
				department.setContact(contact);					//联系人
				department.setPhone(phone);						//联系电话
				department.setFax(fax);							//传真
				department.setAddress(address);					//地址
				department.setUpdatetime(currentTime);			//最后修改时间
				department.setUpdatemanid(strUserCode);			//最后修改人
				
				departmentBus.updateDepartment(department);
				Logger.info("用户" + strUserName + "修改了部门" + id);
			}
			
			strUrl = "/standard/jsp/base/department/base_department_list.jsp";
	        List ls = departmentBus.getDepartmentList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>部门管理==>修改部门失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除部门
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
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));
		String strUserName = (String)httpsession.getAttribute("userName");
		
		departmentBus = new DepartmentBusImpl(dao);
		try
		{
			//删除
			departmentBus.deleteDepartment(id);	
			Logger.info("用户" + strUserName + "删除了部门" + id);
			
			strUrl = "/standard/jsp/base/department/base_department_list.jsp";
	        List ls = departmentBus.getDepartmentList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>部门管理==>部门删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}