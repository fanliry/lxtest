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
import com.wms3.bms.standard.business.base.IAlleyBus;
import com.wms3.bms.standard.business.base.impl.AlleyBusImpl;
import com.wms3.bms.standard.entity.base.BaseAlley;
/**
 * 描述:巷道管理
 * @author gui
 *
 */
public class AlleyAction extends AbstractAction
{
	protected IAlleyBus alleyBus;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		//仓库ID
		String cargoAlleyId = CommonTools.getStrToGbk(request.getParameter("cargoAlleyId"));		//巷道ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		alleyBus = new AlleyBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //巷道管理 查询列表
			{
				strUrl = "/standard/jsp/base/alley/base_alley_list.jsp";
				List ls = alleyBus.getAlleyQuery(warehouseid, cargoAlleyId, "");
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//巷道管理 修改时获取信息
			{
				strUrl = "/standard/jsp/base/alley/base_alley_update.jsp";
				BaseAlley alley = alleyBus.getAlleyById(cargoAlleyId);
				request.setAttribute("alley", alley); 
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>巷道管理==>巷道查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加巷道
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
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));			//库区ID
		String cargoAlleyName = CommonTools.getStrToGbk(request.getParameter("cargoAlleyName"));//巷道名
		String istwin = CommonTools.getStrToGbk(request.getParameter("istwin"));				//是否双升货位巷道
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		alleyBus = new AlleyBusImpl(dao);
		try
		{
			strUrl = "/standard/jsp/base/alley/base_alley_list.jsp";
			
        	//新增巷道信息
			BaseAlley alley = new BaseAlley(cargoAlleyName, warehouseid, whAreaId, "N", "N", istwin, currentTime, strUserCode, currentTime, strUserCode);		 		
			alleyBus.addAlley(alley);
			
			Logger.info("用户" + strUserName + "添加了巷道" + cargoAlleyName);
			
	        List ls = alleyBus.getAlleyList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>巷道管理==>增加巷道失败:" + er.getMessage());
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
		
		String cargoAlleyId = CommonTools.getStrToGbk(request.getParameter("cargoAlleyId"));	//巷道ID
		String cargoAlleyName = CommonTools.getStrToGbk(request.getParameter("cargoAlleyName"));//巷道名
		String inlock = CommonTools.getStrToGbk(request.getParameter("inlock"));				//入库锁
		String outlock = CommonTools.getStrToGbk(request.getParameter("outlock"));				//出库锁
		String istwin = CommonTools.getStrToGbk(request.getParameter("istwin"));				//是否双升货位巷道
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		alleyBus = new AlleyBusImpl(dao);
		try
		{
			if(cargoAlleyId != null)
			{
				BaseAlley alley = alleyBus.getAlleyById(cargoAlleyId);
				alley.setCargoAlleyName(cargoAlleyName);	// 巷道名
				alley.setInlock(inlock);					// 入库锁
				alley.setOutlock(outlock);					// 出库锁
				alley.setIstwin(istwin);					// 是否双升货位巷道
				alley.setUpdatetime(currentTime);			// 最后修改时间
				alley.setUpdatemanid(strUserCode);			// 最后修改人
				
				alleyBus.updateAlley(alley);
				Logger.info("用户" + strUserName + "修改了巷道" + cargoAlleyId);
			}
			
			strUrl = "/standard/jsp/base/alley/base_alley_list.jsp";
			List ls = alleyBus.getAlleyList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>巷道管理==>修改巷道失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除巷道
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
		
		alleyBus = new AlleyBusImpl(dao);
		try
		{
			//删除
			alleyBus.deleteAlley(id);	
			Logger.info("用户" + strUserName + "删除了巷道" + id);
			
			strUrl = "/standard/jsp/base/alley/base_alley_list.jsp";
			List ls = alleyBus.getAlleyList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>巷道管理==>巷道删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
