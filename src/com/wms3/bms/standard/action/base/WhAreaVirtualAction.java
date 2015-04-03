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
 * ����:�����������
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����ID
		String whAreaName = CommonTools.getStrToGbk(request.getParameter("whAreaName"));	//������
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		whAreaBusVirtual = new WhAreaVirtualBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //����������� ��ѯ�б�
			{
				strUrl = "/standard/jsp/base/whareaVirtual/base_whareavirtual_list.jsp";
				List ls = whAreaBusVirtual.getWhAreaQuery(warehouseid, whAreaName);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//����������� �޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/base/whareaVirtual/base_whareavirtual_update.jsp";
				BaseWhareaVirtual wharea = whAreaBusVirtual.getWhareaById(whAreaId);
				request.setAttribute("whArea", wharea); 
			}else if(flag != null && flag.equals("3"))//������������������ĸ�������� �޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/base/whareaVirtual/base_whareavirtual_belongto.jsp";
				BaseWhareaVirtual wharea = whAreaBusVirtual.getWhareaById(whAreaId);
				request.setAttribute("whArea", wharea); 
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�����������==>���������ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�����������
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�ID
		String whAreaName = CommonTools.getStrToGbk(request.getParameter("whAreaName"));	//������
		String ERPCode = CommonTools.getStrToGbk(request.getParameter("ERPCode"));			//ERP����
		String ERPAccount = CommonTools.getStrToGbk(request.getParameter("ERPAccount"));	//ERP����
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		whAreaBusVirtual = new WhAreaVirtualBusImpl(dao);
		try
		{
        	//����������Ϣ
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
			
			Logger.info("�û�" + strUserName + "������������" + whAreaName);

	        strUrl = "/standard/jsp/base/whareaVirtual/base_whareavirtual_list.jsp";
	        List ls = whAreaBusVirtual.getWhAreaList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�����������==>�����������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸��������
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
		
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����ID
		String whAreaName = CommonTools.getStrToGbk(request.getParameter("whAreaName"));	//������
		String ERPCode = CommonTools.getStrToGbk(request.getParameter("ERPCode"));			//ERP����
		String ERPAccount = CommonTools.getStrToGbk(request.getParameter("ERPAccount"));	//ERP����

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
				wharea.setwhAreaName(whAreaName);	// ������
				wharea.setERPAccount(ERPAccount);
				wharea.setERPCode(ERPCode);
				wharea.setUpdatetime(currentTime);	// ����޸�ʱ��
				wharea.setUpdatemanid(strUserCode);	// ����޸���
				
				whAreaBusVirtual.updateWhArea(wharea);
				Logger.info("�û�" + strUserName + "�޸����������" + whAreaId);
			}
			
			strUrl = "/standard/jsp/base/whareaVirtual/base_whareavirtual_list.jsp";
			List ls = whAreaBusVirtual.getWhAreaQuery(wharea.getWarehouseid(), null);
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�����������==>�޸��������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ���������
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
			//ɾ��
			whAreaBusVirtual.deleteWhArea(id);	
			Logger.info("�û�" + strUserName + "ɾ�����������" + id);
			
			strUrl = "/standard/jsp/base/whareaVirtual/base_whareavirtual_list.jsp";
			List ls = whAreaBusVirtual.getWhAreaList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�����������==>�������ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
		
	/**
	 * ����:�����ݴ����������ĸ�����
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
		
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����ID
		String whAreaIdBelongTo = CommonTools.getStrToGbk(request.getParameter("whAreaIdBelongTo"));	//�������ĸ�����
		
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
				Logger.info("�û�" + strUserName + "�޸��˿���" + whAreaId);
			}
			strUrl = "/standard/jsp/base/whareaVirtual/base_whareavirtual_list.jsp";
			List ls = whAreaBusVirtual.getWhAreaQuery(wharea.getWarehouseid(), null);
			request.setAttribute("exResultList", ls);
			request.setAttribute("back_msg", msg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��������==>�޸Ŀ���ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}
