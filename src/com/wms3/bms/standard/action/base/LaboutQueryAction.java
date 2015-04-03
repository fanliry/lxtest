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
 * ˵������ѯж�ع�����
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
		
		//�����û���ѯ���� ж�ع���
		String strNumber = CommonTools.getStrToGb2312(request.getParameter("number"));
		//�����û���ѯ���� ����
		String strName = CommonTools.getStrToGb2312(request.getParameter("name"));
		//�����û���ѯ���� ��ʶ
		String strMarker = CommonTools.getStrToGb2312(request.getParameter("marker"));
		//�����û���ѯ���� ���
		String strDuty   = CommonTools.getStrToGb2312(request.getParameter("duty"));
		//���ҳ����ת
		String strMark   = CommonTools.getStrToGb2312(request.getParameter("mark"));
		try
		{
			//��ѯ��SQL���
			String strQuerySQL = Labout.getLabSQL(strNumber, strName,strMarker,strDuty);
			//��ѯ�ܼ�¼����SQL���
			String strCountSQL = Labout.getLabCountSQL(strNumber, strName,strMarker,strDuty);
			
			if(strMark=="")
			{
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, PageConstant. m_strLabUrl ,1, 10);
				//��ѯ���
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				request.setAttribute("pagingTool", pt);
				httpsession.setAttribute("paging", pt);
				
				request.getRequestDispatcher(PageConstant. m_strLabUrl).forward(request, response);
			}
			if(strMark.equals("A")){//����ȷ�ϣ�װж��
				
//				��ѯ��SQL���
				String strSQL = Labout.getSQL();
				List ls = dao.searchEntities(strSQL);
				request.setAttribute("list", ls);
				request.getRequestDispatcher(PageConstant. m_strSureLabUrl).forward(request, response);
			}
		}catch(Exception er)
		{
			Logger.error("ģ���ѯʧ��"+er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}

}
