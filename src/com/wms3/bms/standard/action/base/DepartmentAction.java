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
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.base.IDepartmentBus;
import com.wms3.bms.standard.business.base.impl.DepartmentBusImpl;
import com.wms3.bms.standard.entity.base.BaseDepartment;

/**
 * ����:���Ź���
 * @author gui
 *
 */
public class DepartmentAction extends AbstractAction
{
	protected IDepartmentBus departmentBus;
	protected int maxLine = 25;		//��ҳ��ʾ��������
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String departmentname = CommonTools.getStrToGbk(request.getParameter("departmentname"));	//��������
		String departmenttype = CommonTools.getStrToGbk(request.getParameter("departmenttype"));	//���ŷ���
		String id = CommonTools.getStrToGbk(request.getParameter("id"));							//����Id
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		departmentBus = new DepartmentBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //���Ź��� ��ѯ�б�
			{
				strUrl = "/standard/jsp/base/department/base_department_list.jsp";
				
				List ls = departmentBus.getDepartmentQuery(departmentname, departmenttype);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//���Ź��� �޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/base/department/base_department_update.jsp";
				
				BaseDepartment department = departmentBus.getDepartmentById(id);
				request.setAttribute("department", department); 
				
			}else if(flag != null && flag.equals("3")) //������Ʒ ��ѯ�б�
			{
				strUrl = "/standard/jsp/common/common_department_list.jsp";
				
				PagingTool pt = departmentBus.getDepartmentQuery(departmentname, departmenttype, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				request.setAttribute("pagingTool", pt);
				httpsession.setAttribute("commpaging", pt);
					
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���Ź���==>���Ų�ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:���Ӳ���
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
		
		String shortname = CommonTools.getStrToGbk(request.getParameter("shortname"));			//���ż��
		String departmentname = CommonTools.getStrToGbk(request.getParameter("departmentname"));	//����ȫ��
		String departmenttype = CommonTools.getStrToGbk(request.getParameter("departmenttype"));	//���ŷ���
		String contact = CommonTools.getStrToGbk(request.getParameter("contact"));				//��ϵ��
		String phone = CommonTools.getStrToGbk(request.getParameter("phone"));					//��ϵ�绰
		String fax = CommonTools.getStrToGbk(request.getParameter("fax"));						//����
		String address = CommonTools.getStrToGbk(request.getParameter("address"));				//��ַ
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		departmentBus = new DepartmentBusImpl(dao);
		try
		{
        	//����������Ϣ
			BaseDepartment department = new BaseDepartment(shortname, departmentname, departmenttype, 
					contact, phone, fax, address, currentTime, strUserCode, currentTime, strUserCode, "Y");		 		
			departmentBus.addDepartment(department);
			
			Logger.info("�û�" + strUserName + "����˲���" + departmentname);

	        strUrl = "/standard/jsp/base/department/base_department_list.jsp";
	        List ls = departmentBus.getDepartmentList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���Ź���==>���Ӳ���ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸Ĳ���
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
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));							//����Id
		String shortname = CommonTools.getStrToGbk(request.getParameter("shortname"));			//���ż��
		String departmentname = CommonTools.getStrToGbk(request.getParameter("departmentname"));	//����ȫ��
		String departmenttype = CommonTools.getStrToGbk(request.getParameter("departmenttype"));	//���ŷ���
		String contact = CommonTools.getStrToGbk(request.getParameter("contact"));				//��ϵ��
		String phone = CommonTools.getStrToGbk(request.getParameter("phone"));					//��ϵ�绰
		String fax = CommonTools.getStrToGbk(request.getParameter("fax"));						//����
		String address = CommonTools.getStrToGbk(request.getParameter("address"));				//��ַ

		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		departmentBus = new DepartmentBusImpl(dao);
		try
		{
			if(id != null && id.length()>0)
			{
				BaseDepartment department = departmentBus.getDepartmentById(id);
				department.setShortname(shortname);				//���ż��
				department.setDepartmentname(departmentname);	//����ȫ��
				department.setDepartmenttype(departmenttype);	//���ŷ���
				department.setContact(contact);					//��ϵ��
				department.setPhone(phone);						//��ϵ�绰
				department.setFax(fax);							//����
				department.setAddress(address);					//��ַ
				department.setUpdatetime(currentTime);			//����޸�ʱ��
				department.setUpdatemanid(strUserCode);			//����޸���
				
				departmentBus.updateDepartment(department);
				Logger.info("�û�" + strUserName + "�޸��˲���" + id);
			}
			
			strUrl = "/standard/jsp/base/department/base_department_list.jsp";
	        List ls = departmentBus.getDepartmentList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���Ź���==>�޸Ĳ���ʧ��:" + er.getMessage());
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
		
		departmentBus = new DepartmentBusImpl(dao);
		try
		{
			//ɾ��
			departmentBus.deleteDepartment(id);	
			Logger.info("�û�" + strUserName + "ɾ���˲���" + id);
			
			strUrl = "/standard/jsp/base/department/base_department_list.jsp";
	        List ls = departmentBus.getDepartmentList();
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���Ź���==>����ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}