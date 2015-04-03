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
 * 描述:查询统计--出入库流水查询
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId"));			//巷道

		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//作业日期
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//作业日期
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype"));	//作业来源
		
		String productid = CommonTools.getStrToGbk(request.getParameter("package_id"));		//品名
		String ownerid = CommonTools.getStrToGbk(request.getParameter("owner_id"));		    //货主
		String customer_id = CommonTools.getStrToGbk(request.getParameter("customer_id"));		//客户
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//托盘条码
		
		String boundstockid = CommonTools.getStrToGbk(request.getParameter("boundstockid"));		//单据号
		String intype = CommonTools.getStrToGbk(request.getParameter("intype"));		//入库单据类型
		String outtype = CommonTools.getStrToGbk(request.getParameter("outtype"));		//出库单据类型
		
		String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));              //批次ID
		
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));//标识
		String groupinfo = CommonTools.getStrToGbk(request.getParameter("groupinfo"));//分组信息
		
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
			Logger.error("查询统计==出入库流水查询失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 功能:分组条件显示
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
				HashMap<String, List> hsLot = (HashMap<String, List>)hsSysParam.get("viewLot");	//所有要显示的批次
				List lsLot = hsLot.get("cxinoutjob");//查询统计作业流水时显示批次	
				request.setAttribute("lsLot", lsLot);
				request.getRequestDispatcher(strUrl).forward(request, response);
			}else if(flag!=null&&flag.equals("2")){
				String strUrl = "/standard/jsp/report/outjobselectgroup.jsp";
				HashMap<String, List> hsLot = (HashMap<String, List>)hsSysParam.get("viewLot");	//所有要显示的批次
				List lsLot = hsLot.get("cxinoutjob");//查询统计作业流水时显示批次	
				request.setAttribute("lsLot", lsLot);
				request.getRequestDispatcher(strUrl).forward(request, response);
			}
			
			
		}catch(Exception er)
		{
			Logger.error("查询统计==>出入库流水分组条件获取失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
