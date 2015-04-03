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
import com.wms3.bms.standard.entity.base.Extractor;

/**
 * 说明：抽检员查询
 * @author xiaotao
 *
 */

public class ExtQueryAction extends AbstractAction{
	
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		
		
		//接收用户查询条件 编号
		String strNumber = CommonTools.getStrToGb2312(request.getParameter("number"));
		//接收用户查询条件 名称
		String strName = CommonTools.getStrToGb2312(request.getParameter("name"));
		
		try
		{
			//查询的SQL语句
			String strQuerySQL = Extractor.getExtSQL(strNumber, strName);
			//查询总记录数的SQL语句
			String strCountSQL = Extractor.getExtCountSQL(strNumber, strName);
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, PageConstant.m_strExtUrl ,1, 10);
			//查询结果
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(PageConstant.m_strExtUrl).forward(request, response);
				
		}catch(Exception er)
		{
			Logger.error("模块查询失败"+er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}

}
