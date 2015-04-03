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
 * ����:�������
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		//�ֿ�ID
		String cargoAlleyId = CommonTools.getStrToGbk(request.getParameter("cargoAlleyId"));		//���ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		alleyBus = new AlleyBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //������� ��ѯ�б�
			{
				strUrl = "/standard/jsp/base/alley/base_alley_list.jsp";
				List ls = alleyBus.getAlleyQuery(warehouseid, cargoAlleyId, "");
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//������� �޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/base/alley/base_alley_update.jsp";
				BaseAlley alley = alleyBus.getAlleyById(cargoAlleyId);
				request.setAttribute("alley", alley); 
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�������==>�����ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�������
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
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));			//����ID
		String cargoAlleyName = CommonTools.getStrToGbk(request.getParameter("cargoAlleyName"));//�����
		String istwin = CommonTools.getStrToGbk(request.getParameter("istwin"));				//�Ƿ�˫����λ���
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		alleyBus = new AlleyBusImpl(dao);
		try
		{
			strUrl = "/standard/jsp/base/alley/base_alley_list.jsp";
			
        	//���������Ϣ
			BaseAlley alley = new BaseAlley(cargoAlleyName, warehouseid, whAreaId, "N", "N", istwin, currentTime, strUserCode, currentTime, strUserCode);		 		
			alleyBus.addAlley(alley);
			
			Logger.info("�û�" + strUserName + "��������" + cargoAlleyName);
			
	        List ls = alleyBus.getAlleyList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�������==>�������ʧ��:" + er.getMessage());
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
		
		String cargoAlleyId = CommonTools.getStrToGbk(request.getParameter("cargoAlleyId"));	//���ID
		String cargoAlleyName = CommonTools.getStrToGbk(request.getParameter("cargoAlleyName"));//�����
		String inlock = CommonTools.getStrToGbk(request.getParameter("inlock"));				//�����
		String outlock = CommonTools.getStrToGbk(request.getParameter("outlock"));				//������
		String istwin = CommonTools.getStrToGbk(request.getParameter("istwin"));				//�Ƿ�˫����λ���
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		alleyBus = new AlleyBusImpl(dao);
		try
		{
			if(cargoAlleyId != null)
			{
				BaseAlley alley = alleyBus.getAlleyById(cargoAlleyId);
				alley.setCargoAlleyName(cargoAlleyName);	// �����
				alley.setInlock(inlock);					// �����
				alley.setOutlock(outlock);					// ������
				alley.setIstwin(istwin);					// �Ƿ�˫����λ���
				alley.setUpdatetime(currentTime);			// ����޸�ʱ��
				alley.setUpdatemanid(strUserCode);			// ����޸���
				
				alleyBus.updateAlley(alley);
				Logger.info("�û�" + strUserName + "�޸������" + cargoAlleyId);
			}
			
			strUrl = "/standard/jsp/base/alley/base_alley_list.jsp";
			List ls = alleyBus.getAlleyList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�������==>�޸����ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ�����
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
			//ɾ��
			alleyBus.deleteAlley(id);	
			Logger.info("�û�" + strUserName + "ɾ�������" + id);
			
			strUrl = "/standard/jsp/base/alley/base_alley_list.jsp";
			List ls = alleyBus.getAlleyList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�������==>���ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
