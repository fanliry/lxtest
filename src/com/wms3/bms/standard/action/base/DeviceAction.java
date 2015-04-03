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
 * ����:�豸����
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));			//����ID
		String devicetype = CommonTools.getStrToGbk(request.getParameter("devicetype"));		//�豸����
		String id = CommonTools.getStrToGbk(request.getParameter("id"));						//�豸ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		deviceBus = new DeviceBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //�豸���� ��ѯ�б�
			{
				strUrl = "/standard/jsp/base/device/base_device_list.jsp";
				List ls = deviceBus.getDeviceQuery(warehouseid, whAreaId, devicetype);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//�豸���� �޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/base/device/base_device_update.jsp";
				BaseDevice device = deviceBus.getDeviceById(id);
				request.setAttribute("device", device); 
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�豸����==>�豸��ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�����豸
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
		
		String deviceno = CommonTools.getStrToGbk(request.getParameter("deviceno"));			//�豸���
		String devicename = CommonTools.getStrToGbk(request.getParameter("devicename"));		//�豸��
		String devicetype = CommonTools.getStrToGbk(request.getParameter("devicetype"));		//�豸����
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));			//����ID		
		String belongto = CommonTools.getStrToGbk(request.getParameter("belongto"));			//�������
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		deviceBus = new DeviceBusImpl(dao);
		try
		{
			strUrl = "/standard/jsp/base/device/base_device_list.jsp";
			
        	//�����豸��Ϣ
			BaseDevice device = new BaseDevice(deviceno, devicename, devicetype, belongto, warehouseid, whAreaId);		 		
			deviceBus.addDevice(device);
			
			Logger.info("�û�" + strUserName + "������豸:" + devicename);
			
	        List ls = deviceBus.getDeviceList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�豸����==>�����豸ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸Ļ���
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
		
		String deviceid = CommonTools.getStrToGbk(request.getParameter("deviceid"));			//�豸ID
		String deviceno = CommonTools.getStrToGbk(request.getParameter("deviceno"));			//�豸���
		String devicename = CommonTools.getStrToGbk(request.getParameter("devicename"));		//�豸��
		String devicetype = CommonTools.getStrToGbk(request.getParameter("devicetype"));		//�豸����
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));			//����ID		
		String belongto = CommonTools.getStrToGbk(request.getParameter("belongto"));			//�������

		String strUserName = (String)httpsession.getAttribute("userName");
		
		deviceBus = new DeviceBusImpl(dao);
		try
		{
			if(deviceid != null && !deviceid.equals(""))
			{
				BaseDevice device = deviceBus.getDeviceById(deviceid);
				device.setDeviceno(deviceno);			// �豸���
				device.setDevicename(devicename);		// �豸��
				device.setDevicetype(devicetype);		// �豸����
				device.setWarehouseid(warehouseid);		// �ֿ�ID
				device.setWhAreaId(whAreaId);			// ����ID		
				device.setBelongto(belongto);			// �������
				
				deviceBus.updateDevice(device);
				Logger.info("�û�" + strUserName + "�޸����豸" + deviceid);
			}
			
			strUrl = "/standard/jsp/base/device/base_device_list.jsp";
			List ls = deviceBus.getDeviceList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�豸����==>�޸��豸ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ���豸
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
			//ɾ��
			deviceBus.deleteDevice(id);	
			Logger.info("�û�" + strUserName + "ɾ�����豸" + id);
			
			strUrl = "/standard/jsp/base/device/base_device_list.jsp";
			List ls = deviceBus.getDeviceList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�豸����==>�豸ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
