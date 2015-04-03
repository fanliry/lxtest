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
 * ˵�������Ա��ѯ
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
		
		
		
		//�����û���ѯ���� ���
		String strNumber = CommonTools.getStrToGb2312(request.getParameter("number"));
		//�����û���ѯ���� ����
		String strName = CommonTools.getStrToGb2312(request.getParameter("name"));
		
		try
		{
			//��ѯ��SQL���
			String strQuerySQL = Extractor.getExtSQL(strNumber, strName);
			//��ѯ�ܼ�¼����SQL���
			String strCountSQL = Extractor.getExtCountSQL(strNumber, strName);
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, PageConstant.m_strExtUrl ,1, 10);
			//��ѯ���
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(PageConstant.m_strExtUrl).forward(request, response);
				
		}catch(Exception er)
		{
			Logger.error("ģ���ѯʧ��"+er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}

}
