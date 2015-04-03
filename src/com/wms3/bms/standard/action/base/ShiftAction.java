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
import com.wms3.bms.standard.business.base.IShiftBus;
import com.wms3.bms.standard.business.base.IShiftconfigBus;
import com.wms3.bms.standard.business.base.impl.ShiftBusImpl;
import com.wms3.bms.standard.business.base.impl.ShiftconfigBusImpl;
import com.wms3.bms.standard.entity.base.BaseShift;
import com.wms3.bms.standard.entity.base.BaseShiftconfig;

/**
 * 描述:班次管理
 * @author gui
 *
 */
public class ShiftAction extends AbstractAction
{
	protected IShiftBus shiftBus;
	protected IShiftconfigBus shiftconfigBus;
	protected int maxLine = 20;		//分页显示的行数；
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String op_man_id = CommonTools.getStrToGbk(request.getParameter("op_man_id"));		//负责人
		String in_out_type = CommonTools.getStrToGbk(request.getParameter("in_out_type"));	//入出类型
		String id = CommonTools.getStrToGbk(request.getParameter("id"));					//班次Id
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		shiftBus = new ShiftBusImpl(dao);
		shiftconfigBus = new ShiftconfigBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //班次管理 查询列表
			{
				strUrl = "/standard/jsp/base/shift/base_shift_list.jsp";
				
				PagingTool pt = shiftBus.getShiftQuery(op_man_id, in_out_type, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2"))//班次管理 添加时获取信息
			{
				//取得班次设置信息
				BaseShiftconfig shiftconfig = shiftconfigBus.getShiftconfig();
				request.setAttribute("shiftconfig", shiftconfig); 
				
				//取得最后班次
				BaseShift lastshift = shiftBus.getLastShift("0");	//不区分入出库
				BaseShift lastshiftin = shiftBus.getLastShift("1");	//入库
				BaseShift lastshiftout = shiftBus.getLastShift("2");//出库
				
				strUrl = "/standard/jsp/base/shift/base_shift_add.jsp";
				//strUrl = "/standard/jsp/base/shift/base_shift_add_editable.jsp";
				request.setAttribute("lastshift", lastshift); 
				request.setAttribute("lastshiftin", lastshiftin); 
				request.setAttribute("lastshiftout", lastshiftout); 

			}else if(flag != null && flag.equals("3"))//班次管理 修改时获取信息
			{
				strUrl = "/standard/jsp/base/shift/base_shift_update.jsp";
				
				BaseShift shift = shiftBus.getShiftById(id);
				request.setAttribute("shift", shift); 
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>班次管理==>班次查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加班次
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
		
		String daynight = CommonTools.getStrToGbk(request.getParameter("daynight"));		//白班、夜班
		String indate = CommonTools.getStrToGbk(request.getParameter("indate"));			//班次时间
		String shiftturn = CommonTools.getStrToGbk(request.getParameter("shift"));			//甲、乙、丙、丁
		String op_man_name = CommonTools.getStrToGbk(request.getParameter("op_man_name"));	//负责人
		String onduty = CommonTools.getStrToGbk(request.getParameter("onduty"));			//当班人员
		String lastShiftId = CommonTools.getStrToGbk(request.getParameter("lastShiftId"));	//上班次ID
		String shiftname = CommonTools.getStrToGbk(request.getParameter("shiftname"));		//上班名称
		String inouttype = CommonTools.getStrToGbk(request.getParameter("inouttype"));		//入出类型
		
		String strUserName = (String)httpsession.getAttribute("userName");
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		shiftBus = new ShiftBusImpl(dao);
		try
		{
        	//新增班次信息
			shiftname = shiftname.replaceAll("-", "");
			BaseShift shift = new BaseShift(indate, daynight, shiftturn, shiftname, inouttype, op_man_name, 
				"N", "N", "", "N", "", "N", "", "Y", lastShiftId, onduty);
			String back_msg = shiftBus.addShift(shift);
			if(back_msg.equals("")){
				Logger.info("用户" + strUserName + "添加了班次" + shiftname);
			}
			
	        strUrl = "/standard/jsp/base/shift/base_shift_list.jsp";
	        PagingTool pt = shiftBus.getShiftQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("back_msg", back_msg);
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>班次管理==>增加班次失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改班次
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
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));					//班次Id
		String op_man_name = CommonTools.getStrToGbk(request.getParameter("op_man_name"));	//负责人
		String onduty = CommonTools.getStrToGbk(request.getParameter("onduty"));			//当班人员
		
		String strUserName = (String)httpsession.getAttribute("userName");
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		shiftBus = new ShiftBusImpl(dao);
		try
		{
			if(id != null && id.length()>0)
			{
				BaseShift shift = shiftBus.getShiftById(id);
				shift.setM_strOp_Man_Id(op_man_name);
				shift.setM_strOndutyMen(onduty);	
				shiftBus.updateShift(shift);
				Logger.info("用户：" + strUserName + "修改了班次名：" + shift.getM_strShiftName() + "，入出库类型：" + shift.getM_strIn_Out_Type());
			}
			
	        strUrl = "/standard/jsp/base/shift/base_shift_list.jsp";
	        PagingTool pt = shiftBus.getShiftQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>班次管理==>修改班次失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除班次
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
		shiftBus = new ShiftBusImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					//删除
					shiftBus.deleteShift(id[i]);	
					Logger.info("用户" + strUserName + "删除了班次" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/base/shift/base_shift_list.jsp";
			PagingTool pt = shiftBus.getShiftQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>班次管理==>班次删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改班次设定信息
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEditSet(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String shift_timespace = CommonTools.getStrToGbk(request.getParameter("shift_timespace"));	//班次的间隔时间
		String shift_type = CommonTools.getStrToGbk(request.getParameter("shift_type"));			//班次区分
		String shift_inout = CommonTools.getStrToGbk(request.getParameter("shift_inout"));			//班次的入出类型
		String op_man1 = CommonTools.getStrToGbk(request.getParameter("op_man1"));					//甲班负责人
		String onduty1 = CommonTools.getStrToGbk(request.getParameter("onduty1"));					//甲班当班人员
		String op_man2 = CommonTools.getStrToGbk(request.getParameter("op_man2"));					//乙班负责人
		String onduty2 = CommonTools.getStrToGbk(request.getParameter("onduty2"));					//乙班当班人员
		String op_man3 = CommonTools.getStrToGbk(request.getParameter("op_man3"));					//丙班负责人
		String onduty3 = CommonTools.getStrToGbk(request.getParameter("onduty3"));					//丙班当班人员
		String op_man4 = CommonTools.getStrToGbk(request.getParameter("op_man4"));					//丁班负责人
		String onduty4 = CommonTools.getStrToGbk(request.getParameter("onduty4"));					//丁班当班人员
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		shiftconfigBus = new ShiftconfigBusImpl(dao);
		try
		{
			BaseShiftconfig shiftconfig = shiftconfigBus.getShiftconfig();
			if(shiftconfig == null){
				
				shiftconfig = new BaseShiftconfig(shift_timespace, shift_type, shift_inout, 
						op_man1, onduty1, op_man2, onduty2, op_man3, onduty3, op_man4, onduty4, "", "");
				shiftconfigBus.addShiftconfig(shiftconfig);
				
				Logger.info("用户" + strUserName + "增加了班次设置，班次间隔时间：" + shift_timespace + "，班次区分：" + shift_type 
						 + "，班次的入出类型：" + shift_inout + "，甲班负责人：" + op_man1 + "，甲班当班人员：" + onduty1
						 + "，乙班负责人：" + op_man2 + "，乙班当班人员：" + onduty2 + "，丙班负责人：" + op_man3 + "，丙班当班人员：" + onduty3
						 + "，丁班负责人：" + op_man4 + "，丁班当班人员：" + onduty4);
				
			}else{
				shiftconfig.setTimespace(shift_timespace);
				shiftconfig.setType(shift_type);
				shiftconfig.setInout(shift_inout);
				shiftconfig.setOpman1(op_man1);
				shiftconfig.setOnduty1(onduty1);
				shiftconfig.setOpman2(op_man2);
				shiftconfig.setOnduty2(onduty2);
				shiftconfig.setOpman3(op_man3);
				shiftconfig.setOnduty3(onduty3);
				shiftconfig.setOpman4(op_man4);
				shiftconfig.setOnduty4(onduty4);
				shiftconfigBus.updateShiftconfig(shiftconfig);
				
				Logger.info("用户" + strUserName + "修改了班次设置，班次间隔时间：" + shift_timespace + "，班次区分：" + shift_type 
						 + "，班次的入出类型：" + shift_inout + "，甲班负责人：" + op_man1 + "，甲班当班人员：" + onduty1
						 + "，乙班负责人：" + op_man2 + "，乙班当班人员：" + onduty2 + "，丙班负责人：" + op_man3 + "，丙班当班人员：" + onduty3
						 + "，丁班负责人：" + op_man4 + "，丁班当班人员：" + onduty4);
			}
			
			
			strUrl = "/standard/jsp/base/shift/base_shiftconfig.jsp";
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>班次管理==>修改班次设置失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}