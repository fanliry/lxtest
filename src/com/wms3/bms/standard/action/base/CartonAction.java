package com.wms3.bms.standard.action.base;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.base.ICartonBus;
import com.wms3.bms.standard.business.base.impl.CartonBusImpl;
import com.wms3.bms.standard.entity.base.BaseCarton;

/**
 * 描述:周转箱
 * @author gui
 *
 */
public class CartonAction extends AbstractAction
{
	protected ICartonBus cartonBus;
	protected int maxLine = 20;		//分页显示的行数；
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String cartonid = CommonTools.getStrToGbk(request.getParameter("cartonid"));	//装箱代码
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        cartonBus = new CartonBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //查询列表
			{
				strUrl = "/standard/jsp/base/carton/base_carton_list.jsp";
				
				PagingTool pt = cartonBus.getCartonQuery(cartonid, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2"))//修改时获取信息
			{
				strUrl = "/standard/jsp/base/carton/base_carton_update.jsp";
				
				BaseCarton carton = cartonBus.getCartonById(cartonid);
				request.setAttribute("carton", carton); 
				
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>周转箱==>周转箱查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加周转箱
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
		
		String cartontype = CommonTools.getStrToGbk(request.getParameter("cartontype"));		//装箱类型
		String descr = CommonTools.getStrToGbk(request.getParameter("descr"));					//装箱描述
		String boxleng = CommonTools.getStrToGbk(request.getParameter("boxleng"));				//长
		String boxwidth = CommonTools.getStrToGbk(request.getParameter("boxwidth"));			//宽
		String boxheight = CommonTools.getStrToGbk(request.getParameter("boxheight"));			//高
		String maxcube = CommonTools.getStrToGbk(request.getParameter("maxcube"));				//最大体积
		String maxweight = CommonTools.getStrToGbk(request.getParameter("maxweight"));			//最大重量
		String maxcount = CommonTools.getStrToGbk(request.getParameter("maxcount"));			//最大数量
		String selfweight = CommonTools.getStrToGbk(request.getParameter("selfweight"));		//自重
		String cartonpercent = CommonTools.getStrToGbk(request.getParameter("cartonpercent"));	//装箱百分比
		String remark = CommonTools.getStrToGbk(request.getParameter("remark"));				//备注
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		cartonBus = new CartonBusImpl(dao);
		try
		{
        	//新增周转箱信息
			BaseCarton carton = new BaseCarton();
			carton.setCartontype(cartontype);	// 装箱类型
			carton.setDescr(descr);				// 装箱描述
			carton.setBoxleng(Double.parseDouble(boxleng));		// 长
			carton.setBoxwidth(Double.parseDouble(boxwidth));	// 宽
			carton.setBoxheight(Double.parseDouble(boxheight));	// 高
			carton.setMaxcube(Double.parseDouble(maxcube));		// 最大体积
			carton.setMaxweight(Double.parseDouble(maxweight));	// 最大重量
			carton.setMaxcount(Double.parseDouble(maxcount));	// 最大数量
			carton.setSelfweight(Double.parseDouble(selfweight));		// 自重
			carton.setCartonpercent(Double.parseDouble(cartonpercent));	// 装箱百分比
			carton.setRemark(remark);			// 备注
			cartonBus.addCarton(carton);
			
			Logger.info("用户" + strUserName + "添加了周转箱" + descr);

	        strUrl = "/standard/jsp/base/carton/base_carton_list.jsp";
	        PagingTool pt = cartonBus.getCartonQuery("", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>周转箱管理==>增加周转箱失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改周转箱
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
		
		String cartonid = CommonTools.getStrToGbk(request.getParameter("cartonid"));						//周转箱Id
		String cartontype = CommonTools.getStrToGbk(request.getParameter("cartontype"));		//装箱类型
		String descr = CommonTools.getStrToGbk(request.getParameter("descr"));					//装箱描述
		String boxleng = CommonTools.getStrToGbk(request.getParameter("boxleng"));				//长
		String boxwidth = CommonTools.getStrToGbk(request.getParameter("boxwidth"));			//宽
		String boxheight = CommonTools.getStrToGbk(request.getParameter("boxheight"));			//高
		String maxcube = CommonTools.getStrToGbk(request.getParameter("maxcube"));				//最大体积
		String maxweight = CommonTools.getStrToGbk(request.getParameter("maxweight"));			//最大重量
		String maxcount = CommonTools.getStrToGbk(request.getParameter("maxcount"));			//最大数量
		String selfweight = CommonTools.getStrToGbk(request.getParameter("selfweight"));		//自重
		String cartonpercent = CommonTools.getStrToGbk(request.getParameter("cartonpercent"));	//装箱百分比
		String remark = CommonTools.getStrToGbk(request.getParameter("remark"));				//备注
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		cartonBus = new CartonBusImpl(dao);
		try
		{
			if(cartonid != null && cartonid.length()>0)
			{
				BaseCarton carton = cartonBus.getCartonById(cartonid);
				carton.setCartontype(cartontype);	// 装箱类型
				carton.setDescr(descr);				// 装箱描述
				carton.setBoxleng(Double.parseDouble(boxleng));		// 长
				carton.setBoxwidth(Double.parseDouble(boxwidth));	// 宽
				carton.setBoxheight(Double.parseDouble(boxheight));	// 高
				carton.setMaxcube(Double.parseDouble(maxcube));		// 最大体积
				carton.setMaxweight(Double.parseDouble(maxweight));	// 最大重量
				carton.setMaxcount(Double.parseDouble(maxcount));	// 最大数量
				carton.setSelfweight(Double.parseDouble(selfweight));		// 自重
				carton.setCartonpercent(Double.parseDouble(cartonpercent));	// 装箱百分比
				carton.setRemark(remark);			// 备注
				cartonBus.updateCarton(carton);
				Logger.info("用户" + strUserName + "修改了周转箱" + cartonid);
			}
			
			strUrl = "/standard/jsp/base/carton/base_carton_list.jsp";
			PagingTool pt = cartonBus.getCartonQuery("", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>周转箱管理==>修改周转箱失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除周转箱
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
		
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));
		String strUserName = (String)httpsession.getAttribute("userName");
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		cartonBus = new CartonBusImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					//删除
					cartonBus.deleteCarton(id[i]);	
					Logger.info("用户" + strUserName + "删除了周转箱" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/base/carton/base_carton_list.jsp";
			PagingTool pt = cartonBus.getCartonQuery("", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>周转箱管理==>周转箱删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}