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
 * ����:��������
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		//�ֿ�ID
		String cargoAreaId = CommonTools.getStrToGbk(request.getParameter("cargoAreaId"));		//����ID
		String cargoAreaName = CommonTools.getStrToGbk(request.getParameter("cargoAreaName"));	//������
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		cargoAreaBus = new CargoAreaBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //�������� ��ѯ�б�
			{
				strUrl = "/standard/jsp/base/cargoarea/base_cargoarea_list.jsp";
				List ls = cargoAreaBus.getCargoAreaQuery(warehouseid, cargoAreaName);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//�������� �޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/base/cargoarea/base_cargoarea_update.jsp";
				BaseCargoarea cargoarea = cargoAreaBus.getCargoareaById(cargoAreaId);
				request.setAttribute("cargoArea", cargoarea); 
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��������==>������ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:���ӻ���
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		//�ֿ�ID
		String cargoAreaName = CommonTools.getStrToGbk(request.getParameter("cargoAreaName"));	//������
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		cargoAreaBus = new CargoAreaBusImpl(dao);
		try
		{
        	//����������Ϣ
			BaseCargoarea cargoarea = new BaseCargoarea(warehouseid, cargoAreaName, currentTime, strUserCode, currentTime, strUserCode);		 		
			cargoAreaBus.addCargoArea(cargoarea);
			
			Logger.info("�û�" + strUserName + "����˻���" + cargoAreaName);

	        strUrl = "/standard/jsp/base/cargoarea/base_cargoarea_list.jsp";
	        List ls = cargoAreaBus.getCargoAreaList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��������==>���ӻ���ʧ��:" + er.getMessage());
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
		
		String cargoAreaId = CommonTools.getStrToGbk(request.getParameter("cargoAreaId"));		//����ID
		String cargoAreaName = CommonTools.getStrToGbk(request.getParameter("cargoAreaName"));	//������

		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		cargoAreaBus = new CargoAreaBusImpl(dao);
		try
		{
			if(cargoAreaId != null)
			{
				BaseCargoarea cargoarea = cargoAreaBus.getCargoareaById(cargoAreaId);
				cargoarea.setCargoAreaName(cargoAreaName);	// ������
				cargoarea.setUpdatetime(currentTime);		// ����޸�ʱ��
				cargoarea.setUpdatemanid(strUserCode);		// ����޸���
				
				cargoAreaBus.updateCargoArea(cargoarea);
				Logger.info("�û�" + strUserName + "�޸��˻���" + cargoAreaId);
			}
			
			strUrl = "/standard/jsp/base/cargoarea/base_cargoarea_list.jsp";
			List ls = cargoAreaBus.getCargoAreaList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��������==>�޸Ļ���ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ������
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
			//ɾ��
			cargoAreaBus.deleteCargoArea(id);	
			Logger.info("�û�" + strUserName + "ɾ���˻���" + id);
			
			strUrl = "/standard/jsp/base/cargoarea/base_cargoarea_list.jsp";
			List ls = cargoAreaBus.getCargoAreaList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��������==>����ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
