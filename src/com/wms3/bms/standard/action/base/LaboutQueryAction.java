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
import com.wms3.bms.standard.entity.base.Labout;

/**
 * 说明：查询卸载工资料
 * @author xiaotao
 *
 */
public class LaboutQueryAction extends AbstractAction{
	
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession();
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		//接收用户查询条件 卸载工号
		String strNumber = CommonTools.getStrToGb2312(request.getParameter("number"));
		//接收用户查询条件 姓名
		String strName = CommonTools.getStrToGb2312(request.getParameter("name"));
		//接收用户查询条件 标识
		String strMarker = CommonTools.getStrToGb2312(request.getParameter("marker"));
		//接收用户查询条件 班次
		String strDuty   = CommonTools.getStrToGb2312(request.getParameter("duty"));
		//标记页面跳转
		String strMark   = CommonTools.getStrToGb2312(request.getParameter("mark"));
		try
		{
			//查询的SQL语句
			String strQuerySQL = Labout.getLabSQL(strNumber, strName,strMarker,strDuty);
			//查询总记录数的SQL语句
			String strCountSQL = Labout.getLabCountSQL(strNumber, strName,strMarker,strDuty);
			
			if(strMark=="")
			{
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, PageConstant. m_strLabUrl ,1, 10);
				//查询结果
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				request.setAttribute("pagingTool", pt);
				httpsession.setAttribute("paging", pt);
				
				request.getRequestDispatcher(PageConstant. m_strLabUrl).forward(request, response);
			}
			if(strMark.equals("A")){//发货确认，装卸工
				
//				查询的SQL语句
				String strSQL = Labout.getSQL();
				List ls = dao.searchEntities(strSQL);
				request.setAttribute("list", ls);
				request.getRequestDispatcher(PageConstant. m_strSureLabUrl).forward(request, response);
			}
		}catch(Exception er)
		{
			Logger.error("模块查询失败"+er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}

}
