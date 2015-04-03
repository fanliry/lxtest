package com.wms3.bms.standard.action.base;


import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.entity.base.HisProduct;

/**
 * 描述：出库设置
 * @author zhi
 *
 */
public class OutSetAction extends AbstractAction
{
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");	
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		try
		{
			String method = CommonTools.getStrToGb2312(request.getParameter("method1"));
			String flag = CommonTools.getStrToGb2312(request.getParameter("flag"));
			
			String ids = CommonTools.getStrToGb2312(request.getParameter("ids"));
			
			String out_type = CommonTools.getStrToGb2312(request.getParameter("out_type"));
			

			HisProduct ta = new HisProduct();
			
			if(method.equals("update"))
			{
				if(flag.equals("1"))
				{
					List ls = ta.getListByRId(dao, out_type, "yplb");
					for(int i=0; i<ls.size(); i++)
					{
						ta = (HisProduct)ls.get(i);
						dao.delete(ta);
					}
					String[] id = ids.split("\\|");
					for(int i=0; i<id.length; i++)
					{
						ta = new HisProduct();
						ta.setM_strOutType(out_type);
						ta.setM_strFlag("yplb");
						ta.setM_strTypeVale(id[i]);
						dao.save(ta);
					}
					ls = ta.getListByRId(dao, out_type, "yplb");
					request.setAttribute("list", ls);
					strUrl = "/standard/jsp/base/outset/out_set_sample.jsp";
				}
				else if(flag.equals("2"))
				{
					
					List ls = ta.getListByRId(dao, out_type, "wpzt");
					for(int i=0; i<ls.size(); i++)
					{
						ta = (HisProduct)ls.get(i);
						dao.delete(ta);
					}
					String[] id = ids.split("\\|");
					for(int i=0; i<id.length; i++)
					{
						ta = new HisProduct();
						ta.setM_strOutType(out_type);
						ta.setM_strFlag("wpzt");
						ta.setM_strTypeVale(id[i]);
						dao.save(ta);
					}
					ls = ta.getListByRId(dao, out_type, "wpzt");
					request.setAttribute("list", ls);
					strUrl = "/standard/jsp/base/outset/out_set_product.jsp";
				}
			}
			else if(method.equals("search"))
			{
				if(flag.equals("1"))
				{
					List ls = ta.getListByRId(dao, out_type, "yplb");
					
					request.setAttribute("list", ls);
					strUrl = "/standard/jsp/base/outset/out_set_sample.jsp";
				}
				else if(flag.equals("2"))
				{
					List ls = ta.getListByRId(dao, out_type, "wpzt");
					
					request.setAttribute("list", ls);
					strUrl = "/standard/jsp/base/outset/out_set_product.jsp";
				}
				else if(flag.equals("3"))
				{
					String hql = "from BaseType where typevalue='cklx' and (selectvalue<>null and selectvalue<>'')";
					List ls = dao.searchEntities(hql);
					request.setAttribute("list", ls);
					strUrl = "/standard/jsp/base/outset/out_set_left.jsp";
				}
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
		}
		catch(Exception er)
		{
			Logger.error("基本信息=>出库设置 管理失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}