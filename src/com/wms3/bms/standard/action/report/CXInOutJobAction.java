package com.wms3.bms.standard.action.report;



import java.util.HashMap;
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
import com.wms3.bms.standard.business.inbound.impl.InBoundJobBusImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;


/**
 * ����:��ѯͳ��--�������ˮ��ѯ
 * @author yao
 *
 */
public class CXInOutJobAction extends AbstractAction
{
	protected List<BaseLotSet> lsLot;
	protected int maxLine = 6;
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId"));			//���

		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//��ҵ����
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//��ҵ����
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype"));	//��ҵ��Դ
		
		String productid = CommonTools.getStrToGbk(request.getParameter("package_id"));		//Ʒ��
		String ownerid = CommonTools.getStrToGbk(request.getParameter("owner_id"));		    //����
		String customer_id = CommonTools.getStrToGbk(request.getParameter("customer_id"));		//�ͻ�
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//��������
		
		String boundstockid = CommonTools.getStrToGbk(request.getParameter("boundstockid"));		//���ݺ�
		String intype = CommonTools.getStrToGbk(request.getParameter("intype"));		//��ⵥ������
		String outtype = CommonTools.getStrToGbk(request.getParameter("outtype"));		//���ⵥ������
		
		String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));              //����ID
		
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));//��ʶ
		String groupinfo = CommonTools.getStrToGbk(request.getParameter("groupinfo"));//������Ϣ
		
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		
        InBoundJobBusImpl inBoundJobBus = new InBoundJobBusImpl(dao);
		try{
			if(flag!=null && flag.equals("1")){
				strUrl = "/standard/jsp/report/cx_in_query_list.jsp";
				
				PagingTool pt = inBoundJobBus.getInboundJobDetailsGroupByIn(warehouseid, whAreaId, alleyId,  
					    indate_from, indate_to, invoicetype, productid, ownerid, traycode, 
						lotid, strUrl, maxLine,boundstockid,groupinfo,customer_id,intype);
				
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				request.setAttribute("groupinfo", groupinfo);
				httpsession.setAttribute("paging", pt);
			}else if(flag!=null && flag.equals("2")){
				strUrl = "/standard/jsp/report/cx_out_query_list.jsp";
				
				PagingTool pt = inBoundJobBus.getInboundJobDetailsGroupByOut(warehouseid, whAreaId, alleyId,  
					    indate_from, indate_to, invoicetype, productid, ownerid, traycode, 
						lotid, strUrl, maxLine,boundstockid,groupinfo,customer_id,outtype);
				
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				request.setAttribute("groupinfo", groupinfo);
				httpsession.setAttribute("paging", pt);
			}else if(flag!=null&&flag.equals("3")){
				strUrl = "/standard/jsp/report/cx_in_query_report.jsp";
				
				List ls = inBoundJobBus.getInboundJobDetailsGroupListByIn(warehouseid, whAreaId, alleyId,  
					    indate_from, indate_to, invoicetype, productid, ownerid, traycode, 
						lotid, strUrl,boundstockid,groupinfo,customer_id,outtype);
				
				request.setAttribute("exResultList", ls);
				request.setAttribute("groupinfo", groupinfo);
			}else if(flag!=null&&flag.equals("4")){
				strUrl = "/standard/jsp/report/cx_out_query_report.jsp";
				List ls = inBoundJobBus.getInboundJobDetailsGroupListByOut(warehouseid, whAreaId, alleyId,  
					    indate_from, indate_to, invoicetype, productid, ownerid, traycode, 
						lotid, strUrl,boundstockid,groupinfo,customer_id,outtype);
				
				request.setAttribute("exResultList", ls);
				request.setAttribute("groupinfo", groupinfo);
				
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			Logger.error("��ѯͳ��==�������ˮ��ѯʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * ����:����������ʾ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecGroup(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		String flag = (String)request.getParameter("flag");

		try
		{	
			if(flag!=null&&flag.equals("1")){
				String strUrl = "/standard/jsp/report/jobselectgroup.jsp";
				HashMap<String, List> hsLot = (HashMap<String, List>)hsSysParam.get("viewLot");	//����Ҫ��ʾ������
				List lsLot = hsLot.get("cxinoutjob");//��ѯͳ����ҵ��ˮʱ��ʾ����	
				request.setAttribute("lsLot", lsLot);
				request.getRequestDispatcher(strUrl).forward(request, response);
			}else if(flag!=null&&flag.equals("2")){
				String strUrl = "/standard/jsp/report/outjobselectgroup.jsp";
				HashMap<String, List> hsLot = (HashMap<String, List>)hsSysParam.get("viewLot");	//����Ҫ��ʾ������
				List lsLot = hsLot.get("cxinoutjob");//��ѯͳ����ҵ��ˮʱ��ʾ����	
				request.setAttribute("lsLot", lsLot);
				request.getRequestDispatcher(strUrl).forward(request, response);
			}
			
			
		}catch(Exception er)
		{
			Logger.error("��ѯͳ��==>�������ˮ����������ȡʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
