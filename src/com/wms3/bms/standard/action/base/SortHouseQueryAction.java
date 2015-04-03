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
import com.wms3.bms.standard.entity.base.StoreHouseAdminster;

/**
 * ˵������ѯ�ֿ����Ա��Ϣ
 * @author xiaotao
 *
 */
public class SortHouseQueryAction extends AbstractAction {


	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession();
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		

		//�����û���ѯ���� ���ű��
		String storeHouseNumber = CommonTools.getStrToGb2312(request.getParameter("number"));
		//�����û���ѯ���� ��������
		String storeHouseName = CommonTools.getStrToGb2312(request.getParameter("name"));
		try
		{
			//��ѯ��SQL���
			String strQuerySQL = StoreHouseAdminster.getSortHouseSQL(storeHouseNumber ,storeHouseName);
			//��ѯ��ɫ�ܼ�¼����SQL���
			String strCountSQL = StoreHouseAdminster.getSortHouseCountSQL(storeHouseNumber, storeHouseName);
			
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, PageConstant.m_strSortHouseUrl ,1, 10);
			//��ѯ���
			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(PageConstant.m_strSortHouseUrl).forward(request, response);
				
		}catch(Exception er)
		{
			Logger.error("ģ���ѯʧ��"+er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}


}