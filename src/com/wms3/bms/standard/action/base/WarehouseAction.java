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
import com.wms3.bms.standard.business.base.IWarehouseBus;
import com.wms3.bms.standard.business.base.impl.WarehouseBusImpl;
import com.wms3.bms.standard.entity.base.BaseWarehouse;

/**
 * 描述:仓库管理
 * @author gui
 *
 */
public class WarehouseAction extends AbstractAction
{
    protected IWarehouseBus warehouseBus; 
    
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		// 仓库ID
		String warehousename = CommonTools.getStrToGbk(request.getParameter("warehousename"));	// 仓库名
		String warehousetype = CommonTools.getStrToGbk(request.getParameter("warehousetype"));	// 仓库类型
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
        warehouseBus = new WarehouseBusImpl(dao);
        
		try
		{
			if(flag != null && flag.equals("1")) //仓库管理 查询列表
			{
				strUrl = "/standard/jsp/base/warehouse/base_warehouse_list.jsp";
				List ls = warehouseBus.getWarehouseQuery(warehousename, warehousetype);
					
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//仓库管理 修改时获取信息
			{
				strUrl = "/standard/jsp/base/warehouse/base_warehouse_update.jsp";
				BaseWarehouse wh = warehouseBus.getWarehouseById(warehouseid);
				
				request.setAttribute("Warehouse", wh); 
				
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>仓库管理==>仓库查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	/**
	 * 功能:增加仓库
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehousename = CommonTools.getStrToGbk(request.getParameter("warehousename"));	// 仓库名
		String whaddress = CommonTools.getStrToGbk(request.getParameter("whaddress"));			// 仓库地址
		String whmgrman = CommonTools.getStrToGbk(request.getParameter("whmgrman"));			// 仓库管理员
		String whlinkman = CommonTools.getStrToGbk(request.getParameter("whlinkman"));			// 联系人
		String whlinktel = CommonTools.getStrToGbk(request.getParameter("whlinktel"));			// 联系电话
		String iscurrent = CommonTools.getStrToGbk(request.getParameter("iscurrent"));			// 是否当前仓库
		String erpcode = CommonTools.getStrToGbk(request.getParameter("erpcode"));				// 对应ERP的代码
		String mark = CommonTools.getStrToGb2312(request.getParameter("mark"));
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
        warehouseBus = new WarehouseBusImpl(dao);
		try{
			
        	if(mark.equals("1")){
        		//设置仓库不是当前仓库
        		warehouseBus.updateIsCurrent();
        	}
        	
        	//新增仓库信息
			BaseWarehouse warehouse = new BaseWarehouse();
			warehouse.setWarehousename(warehousename);		// 仓库名
			warehouse.setWhaddress(whaddress);				// 仓库地址
			warehouse.setWhmgrman(whmgrman);				// 仓库管理员
			warehouse.setWhlinkman(whlinkman);				// 联系人
			warehouse.setWhlinktel(whlinktel);				// 联系电话
			warehouse.setIscurrent(iscurrent);				// 是否当前仓库
			warehouse.setErpcode(erpcode);					// 对应ERP的代码
			warehouse.setUpdatetime(currentTime);			// 最后修改时间
			warehouse.setUpdatemanid(strUserCode);			// 最后修改人
            warehouseBus.addWarehouse(warehouse);
			
			Logger.info("用户" + strUserName + "添加了仓库" + warehousename);
			
			//重新获取仓库列表
	        strUrl = "/standard/jsp/base/warehouse/base_warehouse_list.jsp";
	        List ls = warehouseBus.getWarehouseList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>仓库管理==>增加仓库失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改仓库
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		// 仓库ID
		String warehousename = CommonTools.getStrToGbk(request.getParameter("warehousename"));	// 仓库名
		String whaddress = CommonTools.getStrToGbk(request.getParameter("whaddress"));			// 仓库地址
		String whmgrman = CommonTools.getStrToGbk(request.getParameter("whmgrman"));			// 仓库管理员
		String whlinkman = CommonTools.getStrToGbk(request.getParameter("whlinkman"));			// 联系人
		String whlinktel = CommonTools.getStrToGbk(request.getParameter("whlinktel"));			// 联系电话
		String iscurrent = CommonTools.getStrToGbk(request.getParameter("iscurrent"));			// 是否当前仓库
		String erpcode = CommonTools.getStrToGbk(request.getParameter("erpcode"));				// 对应ERP的代码
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);

        warehouseBus = new WarehouseBusImpl(dao);
		try
		{
			if(warehouseid != null && !warehouseid.equals(""))
			{
				if(iscurrent.equals("Y")){
	        		//设置仓库不是当前仓库
	        		warehouseBus.updateIsCurrent();
	        	}
				
				BaseWarehouse wh = warehouseBus.getWarehouseById(warehouseid);
				wh.setWarehousename(warehousename);		// 仓库名
				wh.setWhaddress(whaddress);				// 仓库地址
				wh.setWhmgrman(whmgrman);				// 仓库管理员
				wh.setWhlinkman(whlinkman);				// 联系人
				wh.setWhlinktel(whlinktel);				// 联系电话
				wh.setIscurrent(iscurrent);				// 是否当前仓库
				wh.setErpcode(erpcode);					// 对应ERP的代码
				wh.setUpdatetime(currentTime);			// 最后修改时间
				wh.setUpdatemanid(strUserCode);			// 最后修改人
				
                warehouseBus.updateWarehouse(wh);
				Logger.info("用户" + strUserName + "修改了仓库" + warehouseid);
			}
			
			//重新获取仓库列表
	        strUrl = "/standard/jsp/base/warehouse/base_warehouse_list.jsp";
	        List ls = warehouseBus.getWarehouseList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>仓库管理==>修改仓库失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除仓库
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

        warehouseBus = new WarehouseBusImpl(dao);
		try
		{
			//删除
            warehouseBus.deleteWarehouse(id);	
			Logger.info("用户" + strUserName + "删除了仓库" + id);
			
			//重新获取仓库列表
	        strUrl = "/standard/jsp/base/warehouse/base_warehouse_list.jsp";
	        List ls = warehouseBus.getWarehouseList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>仓库管理==>仓库删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
