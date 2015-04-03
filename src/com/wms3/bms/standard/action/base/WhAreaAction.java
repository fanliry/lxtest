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
 * ����:��������
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����ID
		String whAreaName = CommonTools.getStrToGbk(request.getParameter("whAreaName"));	//������
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		whAreaBus = new WhAreaBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //�������� ��ѯ�б�
			{
				strUrl = "/standard/jsp/base/wharea/base_wharea_list.jsp";
				List ls = whAreaBus.getWhAreaQuery(warehouseid, whAreaName);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//�������� �޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/base/wharea/base_wharea_update.jsp";
				BaseWharea wharea = whAreaBus.getWhareaById(whAreaId);
				request.setAttribute("whArea", wharea); 
			}else if(flag != null && flag.equals("3"))//���ÿ����������ĸ����� �޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/base/wharea/base_wharea_belongto.jsp";
				BaseWharea wharea = whAreaBus.getWhareaById(whAreaId);
				request.setAttribute("whArea", wharea); 
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
	 * ����:���ӿ���
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
		String whAreaType = CommonTools.getStrToGbk(request.getParameter("whAreaType"));	//��������
		String environment = CommonTools.getStrToGbk(request.getParameter("environment"));	//�洢����id
		String istask = CommonTools.getStrToGbk(request.getParameter("istask"));			//�Ƿ���Ҫ���ɵ�������
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		whAreaBus = new WhAreaBusImpl(dao);
		try
		{
        	//����������Ϣ
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
			
			Logger.info("�û�" + strUserName + "����˿���" + whAreaName);

	        strUrl = "/standard/jsp/base/wharea/base_wharea_list.jsp";
	        List ls = whAreaBus.getWhAreaList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��������==>���ӿ���ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸Ŀ���
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
		String whAreaType = CommonTools.getStrToGbk(request.getParameter("whAreaType"));	//��������
		String environment = CommonTools.getStrToGbk(request.getParameter("environment"));	//�洢����id
		String istask = CommonTools.getStrToGbk(request.getParameter("istask"));			//�Ƿ���Ҫ���ɵ�������

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
				wharea.setwhAreaName(whAreaName);	// ������
				wharea.setwhAreaType(whAreaType);	// ��������
				wharea.setStorageEnvironment(environment);
				wharea.setIstask(istask);			// �Ƿ���Ҫ���ɵ�������
				wharea.setUpdatetime(currentTime);	// ����޸�ʱ��
				wharea.setUpdatemanid(strUserCode);	// ����޸���
				
				whAreaBus.updateWhArea(wharea);
				Logger.info("�û�" + strUserName + "�޸��˿���" + whAreaId);
			}
			
			strUrl = "/standard/jsp/base/wharea/base_wharea_list.jsp";
			List ls = whAreaBus.getWhAreaQuery(wharea.getWarehouseid(), null);
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��������==>�޸Ŀ���ʧ��:" + er.getMessage());
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
		
		whAreaBus = new WhAreaBusImpl(dao);
		try
		{
			//ɾ��
			whAreaBus.deleteWhArea(id);	
			Logger.info("�û�" + strUserName + "ɾ���˿���" + id);
			
			strUrl = "/standard/jsp/base/wharea/base_wharea_list.jsp";
			List ls = whAreaBus.getWhAreaList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��������==>����ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:����ΪĬ���ջ���
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
				
				//��øòֿ�����п���, ����òֿ��Ѿ�������Ĭ���ջ����� ��ȡ��
				List ls = whAreaBus.getWhAreaQuery(wharea.getWarehouseid(), "");
				if(ls != null){
					BaseWharea wharea1 = null;
					for(int i=0; i<ls.size(); i++){
						wharea1 = (BaseWharea)ls.get(i);

						if(wharea1.getIsdefault().equalsIgnoreCase("Y")){
							wharea1.setIsdefault("N");	//�Ƿ���Ĭ���ջ���  �ǣ�Y  ��N
							whAreaBus.updateWhArea(wharea1);
							Logger.info("�û�" + strUserName + "ȡ���˲ֿ⣺" + wharea1.getWarehousename() + "��Ĭ���ջ�����"+ wharea1.getwhAreaId());
							break;
						}
					}
				}

				wharea.setIsdefault("Y");	//�Ƿ���Ĭ���ջ���  �ǣ�Y  ��N
				whAreaBus.updateWhArea(wharea);
				Logger.info("�û�" + strUserName + "�����˲ֿ⣺" + wharea.getWarehousename() + "��Ĭ���ջ�����"+ whAreaId);
			}
			
			strUrl = "/standard/jsp/base/wharea/base_wharea_list.jsp";
			List ls = whAreaBus.getWhAreaList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��������==>����ɾ��ʧ��:" + er.getMessage());
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
					msg ="�ݴ��������������ݴ���";
				}
				whAreaBus.updateWhArea(wharea);
				Logger.info("�û�" + strUserName + "�޸��˿���" + whAreaId);
			}
			strUrl = "/standard/jsp/base/wharea/base_wharea_list.jsp";
			List ls = whAreaBus.getWhAreaQuery(wharea.getWarehouseid(), null);
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
