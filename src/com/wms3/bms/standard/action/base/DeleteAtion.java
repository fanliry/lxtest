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
import com.wms3.bms.standard.entity.base.Delete;
/**
 * 描述:删除信息
 * @author xiaotao
 *
 */
public class DeleteAtion extends AbstractAction
{
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{		
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		
		HttpSession httpsession = request.getSession();
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		Delete del = new Delete();
		//获取用户选择信息的ID
		String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
		String Object=CommonTools.getStrToGb2312(request.getParameter("object"));
		String id=CommonTools.getStrToGb2312(request.getParameter("id"));
		String warehouseoutno=CommonTools.getStrToGb2312(request.getParameter("warehouseoutno"));
		String strMark=CommonTools.getStrToGb2312(request.getParameter("mark"));
		
			try
			{
				List ls = null;
				String strUrl=null;
				//删除
				PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
				if(strDeleteStr != null && strDeleteStr.length() > 0)
				{
					String [] roleIds = strDeleteStr.split(",");
					for(int i=0; i<roleIds.length; i++)
					{
						//删除
						String strTemp =  roleIds[i];
						del.deleteToId(Object,strTemp,dao,id);
						Logger.info("删除客户！");
					}
					
					if(pt != null)
					{
						//更新总记录数  
						int rows = pt.getM_iCountRow()-roleIds.length;
						pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
						ls = CommonPagination.getPagingList(dao, pt);
						strUrl = pt.getM_strUrl();
						request.setAttribute("exResultList", ls);
						request.setAttribute("pagingTool", pt);
						httpsession.setAttribute("paging", pt);
						
					}if(strMark.equals("A"))
					{
							strUrl="/rmsService?actionCode=clientBil";  //特例：删除表单标准客户列表
					}
					if(strMark.equals("B"))
					{
						strUrl = "/rmsService?actionCode=mnjob&inoutno="+warehouseoutno;  //发货确认 A客户单据——》暂存区
					}
				}
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("信息删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);	
			}
		return null;
	}
	
}
