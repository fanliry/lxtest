package com.wms3.bms.standard.action.base;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.common.tools.PageConstant;


public class MapQueryAction extends AbstractAction {

	
	@SuppressWarnings("unchecked")
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		try
		{
			//获得map表里的数据
			List ls =dao.searchEntities("from Map m order by m.m_mapNumber");
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(PageConstant.m_strMapUrl).forward(request, response);
		
				
		}catch(Exception er)
		{
			Logger.error("查询字典数据失败"+er.toString());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

}
