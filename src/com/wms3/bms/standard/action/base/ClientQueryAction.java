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
import com.wms3.bms.standard.entity.base.ClientFile;

/**
 * 说明：查询客户信息失败
 * @author xiaotao
 *
 */
public class ClientQueryAction extends AbstractAction {


	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession();
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String strNumber = CommonTools.getStrToGb2312(request.getParameter("number"));
		//接收用户查询条件  用友编号
		String strUserName = CommonTools.getStrToGb2312(request.getParameter("userNumber"));
		// 客户简称
		String strSort = CommonTools.getStrToGb2312(request.getParameter("forShort"));
		// 标准分类
		String strAssort = CommonTools.getStrToGb2312(request.getParameter("assort")); 
		//
		String strFilag=CommonTools.getStrToGb2312(request.getParameter("flag"));
		
		if(strFilag.equals("query"))
		{
		
			try
			{
				//查询的SQL语句
				String strQuerySQL = ClientFile.getClientSQL(strNumber,strUserName,strSort,strAssort);
				//查询总记录数的SQL语句
				String strCountSQL = ClientFile.getClientCountSQL(strNumber,strUserName,strSort,strAssort);
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, PageConstant.m_strClient_mainUrl,1, 10);
				//查询结果
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				request.setAttribute("pagingTool", pt);
				httpsession.setAttribute("paging", pt);
				
				request.getRequestDispatcher(PageConstant.m_strClient_mainUrl).forward(request, response);
					
			}catch(Exception er)
			{
				Logger.error("查询客户档案模块失败"+er.getMessage());
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		}
		else
		{
			request.getRequestDispatcher(PageConstant.m_strClientUrl).forward(request, response);
		}
		return null;
	}

}