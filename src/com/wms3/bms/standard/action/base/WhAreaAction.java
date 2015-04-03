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
import com.wms3.bms.standard.business.base.IWhAreaBus;
import com.wms3.bms.standard.business.base.impl.WhAreaBusImpl;
import com.wms3.bms.standard.entity.base.BaseWharea;

/**
 * 描述:库区管理
 * @author gui
 *
 */
public class WhAreaAction extends AbstractAction
{
	protected IWhAreaBus whAreaBus;
	
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
		
		whAreaBus = new WhAreaBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //库区管理 查询列表
			{
				strUrl = "/standard/jsp/base/wharea/base_wharea_list.jsp";
				List ls = whAreaBus.getWhAreaQuery(warehouseid, whAreaName);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//库区管理 修改时获取信息
			{
				strUrl = "/standard/jsp/base/wharea/base_wharea_update.jsp";
				BaseWharea wharea = whAreaBus.getWhareaById(whAreaId);
				request.setAttribute("whArea", wharea); 
			}else if(flag != null && flag.equals("3"))//设置库区隶属于哪个库区 修改时获取信息
			{
				strUrl = "/standard/jsp/base/wharea/base_wharea_belongto.jsp";
				BaseWharea wharea = whAreaBus.getWhareaById(whAreaId);
				request.setAttribute("whArea", wharea); 
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>库区管理==>库区查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加库区
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
		String whAreaType = CommonTools.getStrToGbk(request.getParameter("whAreaType"));	//库区类型
		String environment = CommonTools.getStrToGbk(request.getParameter("environment"));	//存储环境id
		String istask = CommonTools.getStrToGbk(request.getParameter("istask"));			//是否需要生成调度任务
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		whAreaBus = new WhAreaBusImpl(dao);
		try
		{
        	//新增库区信息
			BaseWharea wharea = new BaseWharea();
			wharea.setWarehouseid(warehouseid);
			wharea.setwhAreaName(whAreaName);
			wharea.setwhAreaType(whAreaType);
			wharea.setStorageEnvironment(environment);
			
			wharea.setIsdefault("N");
			wharea.setIstask(istask);
			wharea.setCreatetime(currentTime);
			wharea.setCreatemanid(strUserCode);
			wharea.setUpdatetime(currentTime);
			wharea.setUpdatemanid(strUserCode);
			
			whAreaBus.addWhArea(wharea);
			
			Logger.info("用户" + strUserName + "添加了库区" + whAreaName);

	        strUrl = "/standard/jsp/base/wharea/base_wharea_list.jsp";
	        List ls = whAreaBus.getWhAreaList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>库区管理==>增加库区失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改库区
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
		String whAreaType = CommonTools.getStrToGbk(request.getParameter("whAreaType"));	//库区类型
		String environment = CommonTools.getStrToGbk(request.getParameter("environment"));	//存储环境id
		String istask = CommonTools.getStrToGbk(request.getParameter("istask"));			//是否需要生成调度任务

		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		whAreaBus = new WhAreaBusImpl(dao);
		BaseWharea wharea = null;
		try
		{
			if(whAreaId != null)
			{
				wharea = whAreaBus.getWhareaById(whAreaId);
				wharea.setwhAreaName(whAreaName);	// 库区名
				wharea.setwhAreaType(whAreaType);	// 库区类型
				wharea.setStorageEnvironment(environment);
				wharea.setIstask(istask);			// 是否需要生成调度任务
				wharea.setUpdatetime(currentTime);	// 最后修改时间
				wharea.setUpdatemanid(strUserCode);	// 最后修改人
				
				whAreaBus.updateWhArea(wharea);
				Logger.info("用户" + strUserName + "修改了库区" + whAreaId);
			}
			
			strUrl = "/standard/jsp/base/wharea/base_wharea_list.jsp";
			List ls = whAreaBus.getWhAreaQuery(wharea.getWarehouseid(), null);
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>库区管理==>修改库区失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除库区
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
		
		whAreaBus = new WhAreaBusImpl(dao);
		try
		{
			//删除
			whAreaBus.deleteWhArea(id);	
			Logger.info("用户" + strUserName + "删除了库区" + id);
			
			strUrl = "/standard/jsp/base/wharea/base_wharea_list.jsp";
			List ls = whAreaBus.getWhAreaList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>库区管理==>库区删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:设置为默认收货区
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecSetDefault(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));
		String strUserName = (String)httpsession.getAttribute("userName");
		
		whAreaBus = new WhAreaBusImpl(dao);
		try
		{
			if(whAreaId != null)
			{
				BaseWharea wharea = whAreaBus.getWhareaById(whAreaId);
				
				//获得该仓库的所有库区, 如果该仓库已经设置了默认收货区， 先取消
				List ls = whAreaBus.getWhAreaQuery(wharea.getWarehouseid(), "");
				if(ls != null){
					BaseWharea wharea1 = null;
					for(int i=0; i<ls.size(); i++){
						wharea1 = (BaseWharea)ls.get(i);

						if(wharea1.getIsdefault().equalsIgnoreCase("Y")){
							wharea1.setIsdefault("N");	//是否是默认收货区  是：Y  否：N
							whAreaBus.updateWhArea(wharea1);
							Logger.info("用户" + strUserName + "取消了仓库：" + wharea1.getWarehousename() + "的默认收货区："+ wharea1.getwhAreaId());
							break;
						}
					}
				}

				wharea.setIsdefault("Y");	//是否是默认收货区  是：Y  否：N
				whAreaBus.updateWhArea(wharea);
				Logger.info("用户" + strUserName + "设置了仓库：" + wharea.getWarehousename() + "的默认收货区："+ whAreaId);
			}
			
			strUrl = "/standard/jsp/base/wharea/base_wharea_list.jsp";
			List ls = whAreaBus.getWhAreaList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>库区管理==>库区删除失败:" + er.getMessage());
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
		
		whAreaBus = new WhAreaBusImpl(dao);
		try
		{
			BaseWharea wharea = null;
			if(whAreaId != null)
			{
				wharea = whAreaBus.getWhareaById(whAreaId);
				BaseWharea whareaBe = whAreaBus.getWhareaById(whAreaIdBelongTo);
				if(!whareaBe.getwhAreaType().equals("9")){
					wharea.setBelongto(whAreaIdBelongTo);
				}else{
					msg ="暂存区不能隶属于暂存区";
				}
				whAreaBus.updateWhArea(wharea);
				Logger.info("用户" + strUserName + "修改了库区" + whAreaId);
			}
			strUrl = "/standard/jsp/base/wharea/base_wharea_list.jsp";
			List ls = whAreaBus.getWhAreaQuery(wharea.getWarehouseid(), null);
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
