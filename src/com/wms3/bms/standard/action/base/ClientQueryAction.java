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
 * ˵������ѯ�ͻ���Ϣʧ��
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
		//�����û���ѯ����  ���ѱ��
		String strUserName = CommonTools.getStrToGb2312(request.getParameter("userNumber"));
		// �ͻ����
		String strSort = CommonTools.getStrToGb2312(request.getParameter("forShort"));
		// ��׼����
		String strAssort = CommonTools.getStrToGb2312(request.getParameter("assort")); 
		//
		String strFilag=CommonTools.getStrToGb2312(request.getParameter("flag"));
		
		if(strFilag.equals("query"))
		{
		
			try
			{
				//��ѯ��SQL���
				String strQuerySQL = ClientFile.getClientSQL(strNumber,strUserName,strSort,strAssort);
				//��ѯ�ܼ�¼����SQL���
				String strCountSQL = ClientFile.getClientCountSQL(strNumber,strUserName,strSort,strAssort);
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, PageConstant.m_strClient_mainUrl,1, 10);
				//��ѯ���
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				request.setAttribute("pagingTool", pt);
				httpsession.setAttribute("paging", pt);
				
				request.getRequestDispatcher(PageConstant.m_strClient_mainUrl).forward(request, response);
					
			}catch(Exception er)
			{
				Logger.error("��ѯ�ͻ�����ģ��ʧ��"+er.getMessage());
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