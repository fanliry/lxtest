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
import com.wms3.bms.standard.business.base.ITypeBus;
import com.wms3.bms.standard.business.base.impl.TypeBusImpl;
import com.wms3.bms.standard.entity.base.BaseType;

/**
 * ����:���͹���
 * @author gui
 *
 */
public class TypeAction extends AbstractAction
{
	protected ITypeBus typeBus;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		
		String typeid = CommonTools.getStrToGbk(request.getParameter("typeid"));			//����ID
		String typename = CommonTools.getStrToGbk(request.getParameter("typename"));		//������
		String typevalue = CommonTools.getStrToGbk(request.getParameter("typevalue"));		//����ֵ
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		typeBus = new TypeBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")){ 			//���͹��� ��ѯ�����б�
			
				strUrl = "/standard/jsp/base/type/base_type_list_left.jsp";	
				List ls = typeBus.getType(typename, typevalue);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("2")){		//���͹��� ��ѯ��������
				
				strUrl = "/standard/jsp/base/type/base_type_list_right.jsp";	
				List ls = typeBus.getTypeList(typevalue);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("3"))		//���͹��� �޸������б�
			{
				strUrl = "/standard/jsp/base/type/base_type_updateList.jsp";
				BaseType type = typeBus.getTypeById(typeid);
				request.setAttribute("type", type); 
			}
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���͹���==>���Ͳ�ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:��������
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
		
		String typeid = CommonTools.getStrToGbk(request.getParameter("typeid"));			//����ID
		String typename = CommonTools.getStrToGbk(request.getParameter("typename"));		//������
		String typevalue = CommonTools.getStrToGbk(request.getParameter("typevalue"));		//����ֵ
		String selecttext = CommonTools.getStrToGbk(request.getParameter("selecttext"));	//�����б���ʾ����
	    String selectvalue = CommonTools.getStrToGbk(request.getParameter("selectvalue"));	//�����б�ֵ
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String strUserName = (String)httpsession.getAttribute("userName");
		
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		typeBus = new TypeBusImpl(dao);
		
		try
		{	
			if(flag != null && flag.equals("1")){ 			//��������
				
				BaseType type = new BaseType();	
				type.setTypeid(typevalue);
				type.setTypevalue(typevalue);
				type.setTypename(typename);
				type.setTypelevel("");	//"Y"��ʾϵͳ
				
				typeBus.addType(type);
				Logger.info("�û�" + strUserName + "���������" + typename);
				
				strUrl = "/standard/jsp/base/type/base_type_list_left.jsp";	
				List ls = typeBus.getType("", "");
				request.setAttribute("exResultList", ls);
				
			}if(flag != null && flag.equals("2")){ 			//���������б�
				
				BaseType type = new BaseType();	
				type.setTypeid(typeid);
				type.setTypevalue(typevalue);
				type.setTypename(typename);
				type.setSelecttext(selecttext);
				type.setSelectvalue(selectvalue);
				type.setTypelevel("");	//"Y"��ʾϵͳ
				
				typeBus.addType(type);
				Logger.info("�û�" + strUserName + "���������:" + typename + "�������б�" + selecttext);
				
				strUrl = "/standard/jsp/base/type/base_type_list_right.jsp";	
				List ls = typeBus.getTypeList(typevalue);
				request.setAttribute("exResultList", ls);
				
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
	        
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���͹���==>��������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸�����
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
		
		String typeid = CommonTools.getStrToGbk(request.getParameter("typeid"));			//����ID
		String typename = CommonTools.getStrToGbk(request.getParameter("typename"));		//������
		String typevalue = CommonTools.getStrToGbk(request.getParameter("typevalue"));		//����ֵ
		String selecttext = CommonTools.getStrToGbk(request.getParameter("selecttext"));	//�����б���ʾ����
	    String selectvalue = CommonTools.getStrToGbk(request.getParameter("selectvalue"));	//�����б�ֵ
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String strUserName = (String)httpsession.getAttribute("userName");

		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		typeBus = new TypeBusImpl(dao);
		
		try
		{

			if(flag != null && flag.equals("1")){ 			//�޸�����
				
				typeBus.updateType(typevalue, typename);
				Logger.info("�û�" + strUserName + "�޸�������, ����ֵ:" + typevalue + "��������:" + typename);
				
				strUrl = "/standard/jsp/base/type/base_type_list_left.jsp";	
				List ls = typeBus.getType("", "");
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("2")){		//�޸����������б�
				
				BaseType type = typeBus.getTypeById(typeid);
				type.setSelecttext(selecttext);
				type.setSelectvalue(selectvalue);
				
				typeBus.updateType(type);
				Logger.info("�û�" + strUserName + "�޸������͵������б�:" + typeid);
				
				strUrl = "/standard/jsp/base/type/base_type_list_right.jsp";	
				List ls = typeBus.getTypeList(typevalue);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("3")){		//��������Ϊϵͳ����
				
				typeBus.setTypeLevel(typevalue);
				Logger.info("�û�" + strUserName + "��������Ϊϵͳ����, ����ֵ:" + typevalue);
				
				strUrl = "/standard/jsp/base/type/base_type_list_left.jsp";	
				List ls = typeBus.getType("", "");
				request.setAttribute("exResultList", ls);
			}
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���͹���==>�޸�����ʧ��:" + er.getMessage());
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
		
		String typeid = CommonTools.getStrToGbk(request.getParameter("typeid"));			//����ID
		String typevalue = CommonTools.getStrToGbk(request.getParameter("typevalue"));		//����ֵ
		String strUserName = (String)httpsession.getAttribute("userName");
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		typeBus = new TypeBusImpl(dao);
		
		try
		{
			if(flag != null && flag.equals("1")){ 			//ɾ������
				
				typeBus.deleteType(typevalue);
				Logger.info("�û�" + strUserName + "ɾ��������, ����ֵ:" + typevalue);
				
				strUrl = "/standard/jsp/base/type/base_type_list_left.jsp";	
				List ls = typeBus.getType("", "");
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("2")){ 	//ɾ�����������б�
				BaseType type = typeBus.getTypeById(typeid);
				typeBus.deleteType(type);
				Logger.info("�û�" + strUserName + "ɾ�������͵������б�:" + typeid);
				
				strUrl = "/standard/jsp/base/type/base_type_list_right.jsp";	
				String[] temp = typeid.split("_");
				List ls = typeBus.getTypeList(temp[0]);
				request.setAttribute("exResultList", ls);
				
			}
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���͹���==>ɾ������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
}
