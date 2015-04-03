package com.wms3.bms.standard.action.base;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.base.IProducttypeBus;
import com.wms3.bms.standard.business.base.impl.ProducttypeBusImpl;
import com.wms3.bms.standard.entity.base.BaseProducttype;

/**
 * 描述:物品类别管理
 * @author gui
 *
 */
public class ProducttypeAction extends AbstractAction
{
	protected IProducttypeBus producttypeBus;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ptname = CommonTools.getStrToGbk(request.getParameter("ptname"));	//物品类别名
		String id = CommonTools.getStrToGbk(request.getParameter("id"));			//物品类别Id
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		producttypeBus = new ProducttypeBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //物品类别管理 查询列表
			{
				strUrl = "/standard/jsp/base/producttype/base_producttype_list.jsp";
				
				List ls = producttypeBus.getProducttypeQuery(ptname);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//物品类别管理 修改时获取信息
			{
				strUrl = "/standard/jsp/base/producttype/base_producttype_update.jsp";
				
				BaseProducttype producttype = producttypeBus.getProducttypeById(id);
				request.setAttribute("producttype", producttype); 
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>物品类别管理==>物品类别查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加物品类别
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ptname = CommonTools.getStrToGbk(request.getParameter("ptname"));		//类别名
		String parentid = CommonTools.getStrToGbk(request.getParameter("parentid"));	//父类别ID
		String ptorder = CommonTools.getStrToGbk(request.getParameter("ptorder"));	//顺序
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		producttypeBus = new ProducttypeBusImpl(dao);
		try
		{
        	//新增物品类别信息
			BaseProducttype producttype = new BaseProducttype(parentid, new Integer(ptorder), null, ptname);		 		
			producttypeBus.addProducttype(producttype);
			
			Logger.info("用户" + strUserName + "添加了物品类别:" + ptname);

			strUrl = "/standard/jsp/base/producttype/base_producttype_list.jsp";
			
			List ls = producttypeBus.getProducttypeList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>物品类别管理==>增加物品类别失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改物品类别
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEdit(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));				//物品类别Id
		String ptname = CommonTools.getStrToGbk(request.getParameter("ptname"));		//类别名
		String parentid = CommonTools.getStrToGbk(request.getParameter("parentid"));	//父类别ID
		String ptorder = CommonTools.getStrToGbk(request.getParameter("ptorder"));	//顺序
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		producttypeBus = new ProducttypeBusImpl(dao);
		try
		{
			if(id != null && id.length()>0)
			{
				BaseProducttype producttype = producttypeBus.getProducttypeById(id);
				producttype.setPtname(ptname);
				producttype.setParentid(parentid);
				producttype.setPtorder(new Integer(ptorder));
				
				producttypeBus.updateProducttype(producttype);
				Logger.info("用户" + strUserName + "修改了物品类别" + id);
			}
			
			strUrl = "/standard/jsp/base/producttype/base_producttype_list.jsp";
			
			List ls = producttypeBus.getProducttypeList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>物品类别管理==>修改物品类别失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除物品类别
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDelete(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));
		String strUserName = (String)httpsession.getAttribute("userName");
		
		producttypeBus = new ProducttypeBusImpl(dao);
		try
		{
			//删除
			producttypeBus.deleteProducttype(id);	
			Logger.info("用户" + strUserName + "删除了物品类别" + id);
			
			strUrl = "/standard/jsp/base/producttype/base_producttype_list.jsp";
			
			List ls = producttypeBus.getProducttypeList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>物品类别管理==>物品类别删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}