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
import com.wms3.bms.standard.business.base.IDeviceBus;
import com.wms3.bms.standard.business.base.impl.DeviceBusImpl;
import com.wms3.bms.standard.entity.base.BaseDevice;

/**
 * 描述:设备管理
 * @author gui
 *
 */
public class DeviceAction extends AbstractAction
{
	protected IDeviceBus deviceBus;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));			//库区ID
		String devicetype = CommonTools.getStrToGbk(request.getParameter("devicetype"));		//设备类型
		String id = CommonTools.getStrToGbk(request.getParameter("id"));						//设备ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		deviceBus = new DeviceBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //设备管理 查询列表
			{
				strUrl = "/standard/jsp/base/device/base_device_list.jsp";
				List ls = deviceBus.getDeviceQuery(warehouseid, whAreaId, devicetype);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//设备管理 修改时获取信息
			{
				strUrl = "/standard/jsp/base/device/base_device_update.jsp";
				BaseDevice device = deviceBus.getDeviceById(id);
				request.setAttribute("device", device); 
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>设备管理==>设备查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加设备
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
		
		String deviceno = CommonTools.getStrToGbk(request.getParameter("deviceno"));			//设备编号
		String devicename = CommonTools.getStrToGbk(request.getParameter("devicename"));		//设备名
		String devicetype = CommonTools.getStrToGbk(request.getParameter("devicetype"));		//设备类型
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));			//库区ID		
		String belongto = CommonTools.getStrToGbk(request.getParameter("belongto"));			//所属巷道
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		deviceBus = new DeviceBusImpl(dao);
		try
		{
			strUrl = "/standard/jsp/base/device/base_device_list.jsp";
			
        	//新增设备信息
			BaseDevice device = new BaseDevice(deviceno, devicename, devicetype, belongto, warehouseid, whAreaId);		 		
			deviceBus.addDevice(device);
			
			Logger.info("用户" + strUserName + "添加了设备:" + devicename);
			
	        List ls = deviceBus.getDeviceList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>设备管理==>增加设备失败:" + er.getMessage());
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
		
		String deviceid = CommonTools.getStrToGbk(request.getParameter("deviceid"));			//设备ID
		String deviceno = CommonTools.getStrToGbk(request.getParameter("deviceno"));			//设备编号
		String devicename = CommonTools.getStrToGbk(request.getParameter("devicename"));		//设备名
		String devicetype = CommonTools.getStrToGbk(request.getParameter("devicetype"));		//设备类型
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));			//库区ID		
		String belongto = CommonTools.getStrToGbk(request.getParameter("belongto"));			//所属巷道

		String strUserName = (String)httpsession.getAttribute("userName");
		
		deviceBus = new DeviceBusImpl(dao);
		try
		{
			if(deviceid != null && !deviceid.equals(""))
			{
				BaseDevice device = deviceBus.getDeviceById(deviceid);
				device.setDeviceno(deviceno);			// 设备编号
				device.setDevicename(devicename);		// 设备名
				device.setDevicetype(devicetype);		// 设备类型
				device.setWarehouseid(warehouseid);		// 仓库ID
				device.setWhAreaId(whAreaId);			// 库区ID		
				device.setBelongto(belongto);			// 所属巷道
				
				deviceBus.updateDevice(device);
				Logger.info("用户" + strUserName + "修改了设备" + deviceid);
			}
			
			strUrl = "/standard/jsp/base/device/base_device_list.jsp";
			List ls = deviceBus.getDeviceList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>设备管理==>修改设备失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除设备
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
		
		deviceBus = new DeviceBusImpl(dao);
		try
		{
			//删除
			deviceBus.deleteDevice(id);	
			Logger.info("用户" + strUserName + "删除了设备" + id);
			
			strUrl = "/standard/jsp/base/device/base_device_list.jsp";
			List ls = deviceBus.getDeviceList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>设备管理==>设备删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
