package com.wms3.bms.standard.action.base;


import java.util.HashMap;
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
 * 说明：修改仓库管理员信息
 * @author xiaoguizi
 *
 */
public class SortUpdAction extends AbstractAction {


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
			String strUse  = CommonTools.getStrToGb2312(request.getParameter("use"));
			
			//根据Id修改信息
			StoreHouseAdminster store= new StoreHouseAdminster().getSortInfoFromId(strId,dao);
			store.setM_storeHouseNumber(strNumber);
			store.setM_storeHouseName(strName);
			store.setM_describe(strUse);
			store.updateStoreInfo(dao);

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
				strUrl = PageConstant.m_strSortHouseUrl;
			}
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);

		}
		catch(Exception ex)
		{
			Logger.error("修改信息失败:"+ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

}
