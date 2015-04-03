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
import com.wms3.bms.standard.business.base.IBaseInoutControlBus;
import com.wms3.bms.standard.business.base.impl.BaseInoutControlBusImpl;
import com.wms3.bms.standard.entity.base.BaseInoutControl;
import com.wms3.bms.standard.entity.base.HisProduct;

/**
 * 描述：出入库卡控 管理
 * @author fanll
 *
 */
public class InoutControlAction extends AbstractAction
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
				if(flag.equals("3"))
				{
					IBaseInoutControlBus ControlBus = new BaseInoutControlBusImpl(dao);
					String currentTime = StrTools.getCurrDateTime(2);
					//清空表数据
					List<BaseInoutControl> ls = null;
					ls = ControlBus.getlsInoutControl();
					
					//新插入一条
					BaseInoutControl Control = new BaseInoutControl();
					HttpSession httpsession = request.getSession(false);
					String strUserCode = (String)httpsession.getAttribute("userCode");
					
					Control.setCreatetime(currentTime);
					Control.setCreatemanid(strUserCode);
					if("1".equals(ids)){
						Control.setCon_type(ids);
						Control.setNote("只能入库");
					}else if("2".equals(ids)){
						Control.setCon_type(ids);
						Control.setNote("只能出库");
					}else if("3".equals(ids)){
						Control.setCon_type(ids);
						Control.setNote("可出入库");
					}
										
					ControlBus.DelInoutControlAdd(ls, Control);
					
					BaseInoutControl InoutControl = ControlBus.getInoutControl();
					request.setAttribute("InoutControl", InoutControl);
					request.setAttribute("back_msg", "Y");
					
					strUrl = "/standard/jsp/base/control_inout/control_inout_set_left.jsp";
				}
			}
			else if(method.equals("search"))
			{

				if(flag.equals("4"))
				{
					String hql = "from BaseType where typevalue='kclx' and (selectvalue<>null and selectvalue<>'')";
					List ls = dao.searchEntities(hql);
					request.setAttribute("list", ls);
					strUrl = "/standard/jsp/base/control_inout/control_inout_set_left.jsp";
				}else if(flag.equals("5")){
					IBaseInoutControlBus ControlBus = new BaseInoutControlBusImpl(dao);
					BaseInoutControl InoutControl = ControlBus.getInoutControl();
					request.setAttribute("InoutControl", InoutControl);
					strUrl = "/standard/jsp/base/control_inout/control_inout_set_left.jsp";
				}
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
		}
		catch(Exception er)
		{
			Logger.error("基本信息=>出入库卡控 管理失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}