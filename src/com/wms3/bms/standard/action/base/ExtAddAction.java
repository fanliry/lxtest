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
 * 说明：抽检员质料
 * @author xiaotao
 *
 */
public class ExtAddAction extends AbstractAction {


	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		try{
			//接收用户信息
			String strNumber = CommonTools.getStrToGb2312(request.getParameter("number"));			
			String strName = CommonTools.getStrToGb2312(request.getParameter("name"));		

			String submitFlag = (String)httpsession.getAttribute("submitFlag"); //判断是否为重复提交的标志变量
	        if(submitFlag == "begin")
	        {
	        //设置标志变量SubmitFlag值为“finish”，表示已经提交
	        httpsession.setAttribute("submitFlag", "finish");
			//添加抽检员信息
			Extractor ext= new Extractor();
			ext.setM_extractorNumber(strNumber);
			ext.setM_extractorName(strName);
			ext.addExtInfo(dao);
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
				strUrl = PageConstant.m_strExtUrl;
			}
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);

		}
		catch(Exception ex)
		{
			Logger.error("增加模块失败:"+ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

}
