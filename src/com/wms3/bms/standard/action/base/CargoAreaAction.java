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
import com.wms3.bms.standard.business.base.ICargoAreaBus;
import com.wms3.bms.standard.business.base.impl.CargoAreaBusImpl;
import com.wms3.bms.standard.entity.base.BaseCargoarea;

/**
 * 描述:货区管理
 * @author gui
 *
 */
public class CargoAreaAction extends AbstractAction
{
	protected ICargoAreaBus cargoAreaBus;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		//仓库ID
		String cargoAreaId = CommonTools.getStrToGbk(request.getParameter("cargoAreaId"));		//货区ID
		String cargoAreaName = CommonTools.getStrToGbk(request.getParameter("cargoAreaName"));	//货区名
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		cargoAreaBus = new CargoAreaBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //货区管理 查询列表
			{
				strUrl = "/standard/jsp/base/cargoarea/base_cargoarea_list.jsp";
				List ls = cargoAreaBus.getCargoAreaQuery(warehouseid, cargoAreaName);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//货区管理 修改时获取信息
			{
				strUrl = "/standard/jsp/base/cargoarea/base_cargoarea_update.jsp";
				BaseCargoarea cargoarea = cargoAreaBus.getCargoareaById(cargoAreaId);
				request.setAttribute("cargoArea", cargoarea); 
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>货区管理==>货区查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加货区
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		//仓库ID
		String cargoAreaName = CommonTools.getStrToGbk(request.getParameter("cargoAreaName"));	//货区名
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		cargoAreaBus = new CargoAreaBusImpl(dao);
		try
		{
        	//新增货区信息
			BaseCargoarea cargoarea = new BaseCargoarea(warehouseid, cargoAreaName, currentTime, strUserCode, currentTime, strUserCode);		 		
			cargoAreaBus.addCargoArea(cargoarea);
			
			Logger.info("用户" + strUserName + "添加了货区" + cargoAreaName);

	        strUrl = "/standard/jsp/base/cargoarea/base_cargoarea_list.jsp";
	        List ls = cargoAreaBus.getCargoAreaList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>货区管理==>增加货区失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改货区
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
		
		String cargoAreaId = CommonTools.getStrToGbk(request.getParameter("cargoAreaId"));		//货区ID
		String cargoAreaName = CommonTools.getStrToGbk(request.getParameter("cargoAreaName"));	//货区名

		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		cargoAreaBus = new CargoAreaBusImpl(dao);
		try
		{
			if(cargoAreaId != null)
			{
				BaseCargoarea cargoarea = cargoAreaBus.getCargoareaById(cargoAreaId);
				cargoarea.setCargoAreaName(cargoAreaName);	// 货区名
				cargoarea.setUpdatetime(currentTime);		// 最后修改时间
				cargoarea.setUpdatemanid(strUserCode);		// 最后修改人
				
				cargoAreaBus.updateCargoArea(cargoarea);
				Logger.info("用户" + strUserName + "修改了货区" + cargoAreaId);
			}
			
			strUrl = "/standard/jsp/base/cargoarea/base_cargoarea_list.jsp";
			List ls = cargoAreaBus.getCargoAreaList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>货区管理==>修改货区失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除货区
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
		
		cargoAreaBus = new CargoAreaBusImpl(dao);
		try
		{
			//删除
			cargoAreaBus.deleteCargoArea(id);	
			Logger.info("用户" + strUserName + "删除了货区" + id);
			
			strUrl = "/standard/jsp/base/cargoarea/base_cargoarea_list.jsp";
			List ls = cargoAreaBus.getCargoAreaList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>货区管理==>货区删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
