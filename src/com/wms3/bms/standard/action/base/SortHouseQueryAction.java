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
import com.wms3.bms.standard.entity.base.StoreHouseAdminster;

/**
 * 说明：查询仓库管理员信息
 * @author xiaotao
 *
 */
public class SortHouseQueryAction extends AbstractAction {


	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession();
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		

		//接收用户查询条件 部门编号
		String storeHouseNumber = CommonTools.getStrToGb2312(request.getParameter("number"));
		//接收用户查询条件 部门名称
		String storeHouseName = CommonTools.getStrToGb2312(request.getParameter("name"));
		try
		{
			//查询的SQL语句
			String strQuerySQL = StoreHouseAdminster.getSortHouseSQL(storeHouseNumber ,storeHouseName);
			//查询角色总记录数的SQL语句
			String strCountSQL = StoreHouseAdminster.getSortHouseCountSQL(storeHouseNumber, storeHouseName);
			
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, PageConstant.m_strSortHouseUrl ,1, 10);
			//查询结果
			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(PageConstant.m_strSortHouseUrl).forward(request, response);
				
		}catch(Exception er)
		{
			Logger.error("模块查询失败"+er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}


}