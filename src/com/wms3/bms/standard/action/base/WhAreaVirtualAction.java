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
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.base.IWhAreaVirtualBus;
import com.wms3.bms.standard.business.base.impl.WhAreaVirtualBusImpl;
import com.wms3.bms.standard.entity.base.BaseWhareaVirtual;

/**
 * 描述:虚拟库区管理
 * @author fanll
 *
 */
public class WhAreaVirtualAction extends AbstractAction
{
	protected IWhAreaVirtualBus whAreaBusVirtual;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区ID
		String whAreaName = CommonTools.getStrToGbk(request.getParameter("whAreaName"));	//库区名
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		whAreaBusVirtual = new WhAreaVirtualBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //虚拟库区管理 查询列表
			{
				strUrl = "/standard/jsp/base/whareaVirtual/base_whareavirtual_list.jsp";
				List ls = whAreaBusVirtual.getWhAreaQuery(warehouseid, whAreaName);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//虚拟库区管理 修改时获取信息
			{
				strUrl = "/standard/jsp/base/whareaVirtual/base_whareavirtual_update.jsp";
				BaseWhareaVirtual wharea = whAreaBusVirtual.getWhareaById(whAreaId);
				request.setAttribute("whArea", wharea); 
			}else if(flag != null && flag.equals("3"))//设置虚拟库区隶属于哪个物理库区 修改时获取信息
			{
				strUrl = "/standard/jsp/base/whareaVirtual/base_whareavirtual_belongto.jsp";
				BaseWhareaVirtual wharea = whAreaBusVirtual.getWhareaById(whAreaId);
				request.setAttribute("whArea", wharea); 
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>虚拟库区管理==>虚拟库区查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加虚拟库区
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库ID
		String whAreaName = CommonTools.getStrToGbk(request.getParameter("whAreaName"));	//库区名
		String ERPCode = CommonTools.getStrToGbk(request.getParameter("ERPCode"));			//ERP代码
		String ERPAccount = CommonTools.getStrToGbk(request.getParameter("ERPAccount"));	//ERP账套
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		whAreaBusVirtual = new WhAreaVirtualBusImpl(dao);
		try
		{
        	//新增库区信息
			BaseWhareaVirtual wharea = new BaseWhareaVirtual();
			wharea.setWarehouseid(warehouseid);
			wharea.setwhAreaName(whAreaName);
			wharea.setERPAccount(ERPAccount);
			wharea.setERPCode(ERPCode);
			wharea.setCreatetime(currentTime);
			wharea.setCreatemanid(strUserCode);
			wharea.setUpdatetime(currentTime);
			wharea.setUpdatemanid(strUserCode);
			
			whAreaBusVirtual.addWhArea(wharea);
			
			Logger.info("用户" + strUserName + "添加了虚拟库区" + whAreaName);

	        strUrl = "/standard/jsp/base/whareaVirtual/base_whareavirtual_list.jsp";
	        List ls = whAreaBusVirtual.getWhAreaList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>虚拟库区管理==>增加虚拟库区失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改虚拟库区
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
		
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区ID
		String whAreaName = CommonTools.getStrToGbk(request.getParameter("whAreaName"));	//库区名
		String ERPCode = CommonTools.getStrToGbk(request.getParameter("ERPCode"));			//ERP代码
		String ERPAccount = CommonTools.getStrToGbk(request.getParameter("ERPAccount"));	//ERP账套

		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		whAreaBusVirtual = new WhAreaVirtualBusImpl(dao);
		BaseWhareaVirtual wharea = null;
		try
		{
			if(whAreaId != null)
			{
				wharea = whAreaBusVirtual.getWhareaById(whAreaId);
				wharea.setwhAreaName(whAreaName);	// 库区名
				wharea.setERPAccount(ERPAccount);
				wharea.setERPCode(ERPCode);
				wharea.setUpdatetime(currentTime);	// 最后修改时间
				wharea.setUpdatemanid(strUserCode);	// 最后修改人
				
				whAreaBusVirtual.updateWhArea(wharea);
				Logger.info("用户" + strUserName + "修改了虚拟库区" + whAreaId);
			}
			
			strUrl = "/standard/jsp/base/whareaVirtual/base_whareavirtual_list.jsp";
			List ls = whAreaBusVirtual.getWhAreaQuery(wharea.getWarehouseid(), null);
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>虚拟库区管理==>修改虚拟库区失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除虚拟库区
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
		
		whAreaBusVirtual = new WhAreaVirtualBusImpl(dao);
		try
		{
			//删除
			whAreaBusVirtual.deleteWhArea(id);	
			Logger.info("用户" + strUserName + "删除了虚拟库区" + id);
			
			strUrl = "/standard/jsp/base/whareaVirtual/base_whareavirtual_list.jsp";
			List ls = whAreaBusVirtual.getWhAreaList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>虚拟库区管理==>虚拟库区删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
		
	/**
	 * 功能:设置暂存区隶属于哪个库区
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecSetBelongTo(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区ID
		String whAreaIdBelongTo = CommonTools.getStrToGbk(request.getParameter("whAreaIdBelongTo"));	//隶属于哪个库区
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		String msg="";
		
		whAreaBusVirtual = new WhAreaVirtualBusImpl(dao);
		try
		{
			BaseWhareaVirtual wharea = null;
			if(whAreaId != null)
			{
				wharea = whAreaBusVirtual.getWhareaById(whAreaId);
				BaseWhareaVirtual whareaBe = whAreaBusVirtual.getWhareaById(whAreaIdBelongTo);
				wharea.setBelongto(whAreaIdBelongTo);
				whAreaBusVirtual.updateWhArea(wharea);
				Logger.info("用户" + strUserName + "修改了库区" + whAreaId);
			}
			strUrl = "/standard/jsp/base/whareaVirtual/base_whareavirtual_list.jsp";
			List ls = whAreaBusVirtual.getWhAreaQuery(wharea.getWarehouseid(), null);
			request.setAttribute("exResultList", ls);
			request.setAttribute("back_msg", msg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>库区管理==>修改库区失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}
