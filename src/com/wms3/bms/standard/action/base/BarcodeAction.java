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
import com.wms3.bms.standard.business.base.IBarcodeBus;
import com.wms3.bms.standard.business.base.impl.BarcodeBusImpl;
import com.wms3.bms.standard.entity.base.BaseBarcode;

/**
 * ���������������
 * ���ߣ�fangjie
 */
public class BarcodeAction extends AbstractAction
{
	protected IBarcodeBus barcodeBus;
	protected int maxLine = 20;		//��ҳ��ʾ��������
	
	@Override
	/**
	 * ����:��ѯ������
	 */
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		String is_used = CommonTools.getStrToGbk(request.getParameter("is_used"));
		
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));	//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		barcodeBus = new BarcodeBusImpl(dao);
		try
		{
			strUrl = "/standard/jsp/base/barcode/base_barcode_list.jsp";	
			
			PagingTool pt = barcodeBus.getBarcodeQuery(is_used, strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>������ά��==>�������ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:����������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return String
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String head = CommonTools.getStrToGbk(request.getParameter("head"));
	    String start = CommonTools.getStrToGbk(request.getParameter("start"));
	    String amount = CommonTools.getStrToGbk(request.getParameter("amount"));
	    
	    barcodeBus = new BarcodeBusImpl(dao);
	    String strUserName = (String)httpsession.getAttribute("userName");
	    String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));	//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		try
		{
			strUrl = "/standard/jsp/base/barcode/base_barcode_list.jsp";	
			
			BaseBarcode barcode = null;
			for (int i = Integer.parseInt(start); i < Integer.parseInt(start) + Integer.parseInt(amount); ++i) {
				barcode = new BaseBarcode(head + barcodeBus.getBarCode(i), "N");
				barcodeBus.addBarcode(barcode);
				Logger.info("�û�" + strUserName + "�����������" + head + barcodeBus.getBarCode(i));
	        }
		     
		    // ��ȡ��ѯ���
			PagingTool pt = barcodeBus.getBarcodeQuery("", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>����������==>�����������ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
		
	/**
	 * ����:�޸�������
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
		
		//����idϵ��
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));
		
		barcodeBus = new BarcodeBusImpl(dao);
	    String strUserName = (String)httpsession.getAttribute("userName");
	    String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));	//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		try
		{
			strUrl = "/standard/jsp/base/barcode/base_barcode_list.jsp";	
			
			BaseBarcode barcode = null;

			String[] id = ids.split(",");

			for (int i = 0; i < id.length; ++i) {
				barcode = barcodeBus.getBarcodeById(id[i]);
				barcode.setIsused("Y");
				barcodeBus.updateBarcode(barcode);
				Logger.info("�û�" + strUserName + "�������룺" + id[i] + "�����ʹ��");
			}
	       
		    // ��ȡ��ѯ���
			PagingTool pt = barcodeBus.getBarcodeQuery("", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>������ά��==>��������ʹ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

}