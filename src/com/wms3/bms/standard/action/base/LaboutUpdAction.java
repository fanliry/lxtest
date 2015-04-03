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
 * 说明：修改卸载工资料
 * @author xiaotao
 */
public class LaboutUpdAction extends AbstractAction {


	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		try{
			//接收用户信息
			String strId=CommonTools.getStrToGb2312(request.getParameter("id"));
			String strNumber = CommonTools.getStrToGb2312(request.getParameter("number"));			
			String strName = CommonTools.getStrToGb2312(request.getParameter("name"));	
			String strMarker=CommonTools.getStrToGb2312(request.getParameter("remark"));
			String strDuty=CommonTools.getStrToGb2312(request.getParameter("duty"));
			String strUse =CommonTools.getStrToGb2312(request.getParameter("play"));

			//根据Id修改信息
			Labout lab= new Labout().getLabInfoFromId(strId,dao);
			lab.setM_laboutNumber(strNumber);
			lab.setM_laboutName(strName);
			lab.setM_duty(strDuty);
			lab.setM_marker(strUse);
			lab.setM_describe(strMarker);
			lab.updateLabInfo(dao);

			//刷新
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			if(pt != null)
			{
				ls = CommonPagination.getPagingList(dao, pt);
				strUrl = pt.getM_strUrl();
			}
			if(strUrl == null)
			{
				strUrl = PageConstant.m_strLabUrl;
			}
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);

		}
		catch(Exception ex)
		{
			Logger.error("修改模块失败:"+ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

}
