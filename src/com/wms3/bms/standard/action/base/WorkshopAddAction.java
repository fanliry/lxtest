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
 * 说明：车位信息添加
 * @author xiaotao
 *
 */
public class WorkshopAddAction extends AbstractAction {


	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		try{
			//车间编号
			String strWorkshopCode = CommonTools.getStrToGb2312(request.getParameter("number"));
			//车间名称
			String strWorkshopName = CommonTools.getStrToGb2312(request.getParameter("name"));		
			String remark =CommonTools.getStrToGb2312(request.getParameter("remark"));
			String submitFlag = (String)httpsession.getAttribute("submitFlag"); //判断是否为重复提交的标志变量
	        if(submitFlag == "begin")
	        {
	        	
	        //设置标志变量SubmitFlag值为“finish”，表示已经提交
	        httpsession.setAttribute("submitFlag", "finish");
			//添加添加车位信息
	        Workshop workshop= new Workshop();
	        workshop.setM_workshopCode(strWorkshopCode);
	        workshop.setM_workshopName(strWorkshopName);
	        workshop.setM_remark(remark);
			workshop.addWorkshopInfo(dao);
	        }
			//刷新
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			if(pt != null)
			{
				//更新总记录数
				int rows = pt.getM_iCountRow();
				if(submitFlag == "begin")
		        {
					rows = pt.getM_iCountRow()+1;
		        }
				pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
				ls = CommonPagination.getPagingList(dao, pt);
				strUrl = pt.getM_strUrl();
			}
			if(strUrl == null)
			{
				strUrl = PageConstant.m_strWorkshopUrl;
			}
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);

		}
		catch(Exception ex)
		{
			Logger.error("增加车位模块失败:"+ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

}
