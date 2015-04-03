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
 * ˵������λ��Ϣ��ѯ
 * @author xiaotao
 *
 */
public class WorkshopQueryAction extends AbstractAction{
	
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession();
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		//�����û���ѯ���� ��λ���
		String workshopCode = CommonTools.getStrToGb2312(request.getParameter("number"));
		//�����û���ѯ���� ��λ����
		String workshopName = CommonTools.getStrToGb2312(request.getParameter("name"));
		
		
		try
		{
			//��ѯ��SQL���
			String strQuerySQL = Workshop.getWorkshopSQL(workshopCode, workshopName);
			
			//��ѯ��ɫ�ܼ�¼����SQL���
			String strCountSQL = Workshop.getWorkshopCountSQL(workshopCode, workshopName);
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, PageConstant.m_strWorkshopUrl ,1, 10);
			//��ѯ���
			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(PageConstant.m_strWorkshopUrl).forward(request, response);
				
		}catch(Exception er)
		{
			Logger.error("��λģ���ѯʧ��"+er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}

}
