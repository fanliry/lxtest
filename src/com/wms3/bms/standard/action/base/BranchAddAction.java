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
import com.wms3.bms.standard.entity.base.BranchMeans;
/**
 * ˵������Ӳ�����Ϣ
 * @author xiaotao
 *
 */
public class BranchAddAction extends AbstractAction {


	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		try{
			//�����û���Ϣ  ���ű��
			String strNumber = CommonTools.getStrToGb2312(request.getParameter("number"));
            //�����û���Ϣ  ��������
			String strName = CommonTools.getStrToGb2312(request.getParameter("name"));	
			String submitFlag = (String)httpsession.getAttribute("submitFlag"); //�ж��Ƿ�Ϊ�ظ��ύ�ı�־����
	        if(submitFlag == "begin")
	        {
	        	//���ñ�־����SubmitFlagֵΪ��finish������ʾ�Ѿ��ύ
	        	httpsession.setAttribute("submitFlag", "finish");
	        	//��Ӳ�����Ϣ
				BranchMeans branch= new BranchMeans();
				branch.setM_branchNumber(strNumber);
				branch.setM_branchName(strName);
				branch.addBranchInfo(dao);
				
	        }
	        
			//ˢ��
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			if(pt != null)
			{
				//�����ܼ�¼��
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
				
				strUrl = PageConstant.m_strBatchUrl;
			}
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);

		}
		catch(Exception ex)
		{
			Logger.error("��������ģ��ʧ��:"+ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

}
