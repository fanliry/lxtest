package com.wms3.bms.standard.action.base;


import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.common.tools.PageConstant;
import com.wms3.bms.standard.entity.base.Workshop;

/**
 * 说明：车位信息查询
 * @author xiaotao
 *
 */
public class WorkshopQueryAction extends AbstractAction{
	
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession();
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		//接收用户查询条件 车位编号
		String workshopCode = CommonTools.getStrToGb2312(request.getParameter("number"));
		//接收用户查询条件 车位名称
		String workshopName = CommonTools.getStrToGb2312(request.getParameter("name"));
		
		
		try
		{
			//查询的SQL语句
			String strQuerySQL = Workshop.getWorkshopSQL(workshopCode, workshopName);
			
			//查询角色总记录数的SQL语句
			String strCountSQL = Workshop.getWorkshopCountSQL(workshopCode, workshopName);
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, PageConstant.m_strWorkshopUrl ,1, 10);
			//查询结果
			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(PageConstant.m_strWorkshopUrl).forward(request, response);
				
		}catch(Exception er)
		{
			Logger.error("车位模块查询失败"+er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}

}
