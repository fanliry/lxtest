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
import com.wms3.bms.standard.business.base.IProducttypeBus;
import com.wms3.bms.standard.business.base.impl.ProducttypeBusImpl;
import com.wms3.bms.standard.entity.base.BaseProducttype;

/**
 * ����:��Ʒ������
 * @author gui
 *
 */
public class ProducttypeAction extends AbstractAction
{
	protected IProducttypeBus producttypeBus;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ptname = CommonTools.getStrToGbk(request.getParameter("ptname"));	//��Ʒ�����
		String id = CommonTools.getStrToGbk(request.getParameter("id"));			//��Ʒ���Id
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		producttypeBus = new ProducttypeBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //��Ʒ������ ��ѯ�б�
			{
				strUrl = "/standard/jsp/base/producttype/base_producttype_list.jsp";
				
				List ls = producttypeBus.getProducttypeQuery(ptname);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//��Ʒ������ �޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/base/producttype/base_producttype_update.jsp";
				
				BaseProducttype producttype = producttypeBus.getProducttypeById(id);
				request.setAttribute("producttype", producttype); 
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��Ʒ������==>��Ʒ����ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:������Ʒ���
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
		
		String ptname = CommonTools.getStrToGbk(request.getParameter("ptname"));		//�����
		String parentid = CommonTools.getStrToGbk(request.getParameter("parentid"));	//�����ID
		String ptorder = CommonTools.getStrToGbk(request.getParameter("ptorder"));	//˳��
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		producttypeBus = new ProducttypeBusImpl(dao);
		try
		{
        	//������Ʒ�����Ϣ
			BaseProducttype producttype = new BaseProducttype(parentid, new Integer(ptorder), null, ptname);		 		
			producttypeBus.addProducttype(producttype);
			
			Logger.info("�û�" + strUserName + "�������Ʒ���:" + ptname);

			strUrl = "/standard/jsp/base/producttype/base_producttype_list.jsp";
			
			List ls = producttypeBus.getProducttypeList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��Ʒ������==>������Ʒ���ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸���Ʒ���
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
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));				//��Ʒ���Id
		String ptname = CommonTools.getStrToGbk(request.getParameter("ptname"));		//�����
		String parentid = CommonTools.getStrToGbk(request.getParameter("parentid"));	//�����ID
		String ptorder = CommonTools.getStrToGbk(request.getParameter("ptorder"));	//˳��
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		producttypeBus = new ProducttypeBusImpl(dao);
		try
		{
			if(id != null && id.length()>0)
			{
				BaseProducttype producttype = producttypeBus.getProducttypeById(id);
				producttype.setPtname(ptname);
				producttype.setParentid(parentid);
				producttype.setPtorder(new Integer(ptorder));
				
				producttypeBus.updateProducttype(producttype);
				Logger.info("�û�" + strUserName + "�޸�����Ʒ���" + id);
			}
			
			strUrl = "/standard/jsp/base/producttype/base_producttype_list.jsp";
			
			List ls = producttypeBus.getProducttypeList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��Ʒ������==>�޸���Ʒ���ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ����Ʒ���
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
		
		producttypeBus = new ProducttypeBusImpl(dao);
		try
		{
			//ɾ��
			producttypeBus.deleteProducttype(id);	
			Logger.info("�û�" + strUserName + "ɾ������Ʒ���" + id);
			
			strUrl = "/standard/jsp/base/producttype/base_producttype_list.jsp";
			
			List ls = producttypeBus.getProducttypeList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��Ʒ������==>��Ʒ���ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}