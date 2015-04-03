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
import com.wms3.bms.standard.business.base.ICustomerBus;
import com.wms3.bms.standard.business.base.impl.CustomerBusImpl;
import com.wms3.bms.standard.entity.base.BaseCustomer;

/**
 * ����:�ͻ�����
 * @author gui
 *
 */
public class CustomerAction extends AbstractAction
{
	protected ICustomerBus customerBus;
	protected int maxLine = 20;		//��ҳ��ʾ��������
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String customername = CommonTools.getStrToGbk(request.getParameter("customername"));	//�ͻ�����
		String customertype = CommonTools.getStrToGbk(request.getParameter("customertype"));	//�ͻ�����
		String id = CommonTools.getStrToGbk(request.getParameter("id"));						//�ͻ�Id
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
			
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));				//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		customerBus = new CustomerBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //�ͻ����� ��ѯ�б�
			{
				strUrl = "/standard/jsp/base/customer/base_customer_list.jsp";
				
				PagingTool pt = customerBus.getCustomerQuery(customername, customertype, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2"))//�ͻ����� �޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/base/customer/base_customer_update.jsp";
				
				BaseCustomer customer = customerBus.getCustomerById(id);
				request.setAttribute("customer", customer); 
				
			}else if(flag != null && flag.equals("3")) //ѡ��ͻ� ��ѯ�б�
			{
				strUrl = "/standard/jsp/common/common_customer_list.jsp";
				
				PagingTool pt = customerBus.getCustomerQuery(customername, customertype, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				request.setAttribute("pagingTool", pt);
				httpsession.setAttribute("commpaging", pt);
					
			}else if(flag != null && flag.equals("4")) //ѡ��rf��Ӧ�̲�ѯ�б�
			{
				strUrl = "/standard/jsp/common/common_customer_rf_list.jsp";
				List<BaseCustomer> ls = customerBus.getCustomerQueryRF(customername, customertype);
				request.setAttribute("exResultList", ls);
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�ͻ�����==>�ͻ���ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:���ӿͻ�
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
		
		String shortname = CommonTools.getStrToGbk(request.getParameter("shortname"));		//�ͻ����
		String customername = CommonTools.getStrToGbk(request.getParameter("customername"));//�ͻ�ȫ��
		String customertype = CommonTools.getStrToGbk(request.getParameter("customertype"));//�ͻ�����
		String contact = CommonTools.getStrToGbk(request.getParameter("contact"));			//��ϵ��
		String phone = CommonTools.getStrToGbk(request.getParameter("phone"));				//��ϵ�绰
		String fax = CommonTools.getStrToGbk(request.getParameter("fax"));					//����
		String address = CommonTools.getStrToGbk(request.getParameter("address"));			//��ַ
		String bank = CommonTools.getStrToGbk(request.getParameter("bank"));				//��������
	    String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));		//�ϼܹ���ID
	    String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));//�������ID
	    String replenishid = CommonTools.getStrToGbk(request.getParameter("replenishid"));	//��������ID
		String linenumber = CommonTools.getStrToGbk(request.getParameter("linenumber"));	//��·��
		String pakageid = CommonTools.getStrToGbk(request.getParameter("pakageid"));		//��װID
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		customerBus = new CustomerBusImpl(dao);
		try
		{
        	//�����ͻ���Ϣ
			BaseCustomer customer = new BaseCustomer();
			customer.setShortname(shortname);		// �ͻ����
			customer.setCustomername(customername);	// �ͻ�ȫ��
			customer.setCustomertype(customertype);	// �ͻ�����
			customer.setContact(contact);			// ��ϵ��
			customer.setPhone(phone);				// ��ϵ�绰
			customer.setFax(fax);					// ����
			customer.setAddress(address);			// ��ַ
			customer.setBank(bank);					// ��������
			customer.setCreatetime(currentTime);	// ����ʱ��
			customer.setCreatemanid(strUserCode);	// ������
			customer.setUpdatetime(currentTime);	// ����޸�ʱ��
			customer.setUpdatemanid(strUserCode);	// ����޸���
			customer.setUseflag("Y");				// �Ƿ�����
			customer.setPutawayid(putawayid);		// �ϼܹ���ID
			customer.setAllocationid(allocationid);	// �������ID
			customer.setReplenishid(replenishid);	// ��������ID
			customer.setLinenumber(linenumber);		// ��·��
			customer.setPakageid(pakageid);			// ��װID
			customerBus.addCustomer(customer);
			
			Logger.info("�û�" + strUserName + "����˿ͻ�" + customername);

	        strUrl = "/standard/jsp/base/customer/base_customer_list.jsp";
	        PagingTool pt = customerBus.getCustomerQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�ͻ�����==>���ӿͻ�ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸Ŀͻ�
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
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));					//�ͻ�Id
		String shortname = CommonTools.getStrToGbk(request.getParameter("shortname"));		//�ͻ����
		String customername = CommonTools.getStrToGbk(request.getParameter("customername"));//�ͻ�ȫ��
		String customertype = CommonTools.getStrToGbk(request.getParameter("customertype"));//�ͻ�����
		String contact = CommonTools.getStrToGbk(request.getParameter("contact"));			//��ϵ��
		String phone = CommonTools.getStrToGbk(request.getParameter("phone"));				//��ϵ�绰
		String fax = CommonTools.getStrToGbk(request.getParameter("fax"));					//����
		String address = CommonTools.getStrToGbk(request.getParameter("address"));			//��ַ
		String bank = CommonTools.getStrToGbk(request.getParameter("bank"));				//��������
	    String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));		//�ϼܹ���ID
	    String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));//�������ID
	    String replenishid = CommonTools.getStrToGbk(request.getParameter("replenishid"));	//��������ID
		String linenumber = CommonTools.getStrToGbk(request.getParameter("linenumber"));	//��·��
		String pakageid = CommonTools.getStrToGbk(request.getParameter("pakageid"));		//��װID
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		customerBus = new CustomerBusImpl(dao);
		try
		{
			if(id != null && id.length()>0)
			{
				BaseCustomer customer = customerBus.getCustomerById(id);
				customer.setShortname(shortname);		//�ͻ����
				customer.setCustomername(customername);	//�ͻ�ȫ��
				customer.setCustomertype(customertype);	//�ͻ�����
				customer.setContact(contact);			//��ϵ��
				customer.setPhone(phone);				//��ϵ�绰
				customer.setFax(fax);					//����
				customer.setAddress(address);			//��ַ
				customer.setBank(bank);					//��������
				customer.setPutawayid(putawayid);		// �ϼܹ���ID
				customer.setAllocationid(allocationid);	// �������ID
				customer.setReplenishid(replenishid);	// ��������ID
				customer.setUpdatetime(currentTime);	//����޸�ʱ��
				customer.setUpdatemanid(strUserCode);	//����޸���
				customer.setLinenumber(linenumber);		//��·��
				customer.setPakageid(pakageid);			// ��װID
				customerBus.updateCustomer(customer);
				Logger.info("�û�" + strUserName + "�޸��˿ͻ�" + id);
			}
			
			strUrl = "/standard/jsp/base/customer/base_customer_list.jsp";
			PagingTool pt = customerBus.getCustomerQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�ͻ�����==>�޸Ŀͻ�ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ���ͻ�
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
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		customerBus = new CustomerBusImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					//ɾ��
					customerBus.deleteCustomer(id[i]);	
					Logger.info("�û�" + strUserName + "ɾ���˿ͻ�" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/base/customer/base_customer_list.jsp";
			PagingTool pt = customerBus.getCustomerQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�ͻ�����==>�ͻ�ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}