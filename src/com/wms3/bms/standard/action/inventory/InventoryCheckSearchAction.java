package com.wms3.bms.standard.action.inventory;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.inbound.IInBoundBus;
import com.wms3.bms.standard.business.inbound.impl.InBoundBusImpl;
import com.wms3.bms.standard.business.inventory.IInventoryCheckBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryCheckBusImpl;
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;
import com.wms3.bms.standard.constant.CommonReturn;
/**
 * 盘点查询的action
 * @author liuxh
 *
 */
public class InventoryCheckSearchAction extends AbstractAction {
    protected 	IInBoundBus inBoundBus;
    protected IOutBoundBus outBoundBus;
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		String checkType = CommonTools.getStrToGbk(request.getParameter("checkType"));	//盘点类型
		String fmDate = CommonTools.getStrToGbk(request.getParameter("fmDate"));	//from时间
		String toDate = CommonTools.getStrToGbk(request.getParameter("toDate"));	//to时间
		//inBoundBus = new InBoundBusImpl(dao);
		//outBoundBus = new OutBoundBusImpl(dao);
		IInventoryCheckBus iCheckBus = new InventoryCheckBusImpl(dao);
		try {
			
			strUrl = "/standard/jsp/inventory/checksearch/kc_check_search_list.jsp";
			CommonReturn cReturn = iCheckBus.getCheckResultsByTimeAndType(checkType, fmDate, toDate);
			PagingTool pt =  CommonPagination.getPagingTool(dao, cReturn.getStrCountHQL(), cReturn.getStrQueryHQL(), strUrl, 1, 20);
	     /*List inls = null;
	     List outls = null;
	     PagingTool pt = null;
	     strUrl = "/standard/jsp/inventory/checksearch/kc_check_search_list.jsp";
		if (checkType!=null&&checkType.equals("1")) {
			
			pt = inBoundBus.getInbounds(null, null, fmDate, toDate, null, null, null, "6", strUrl, 6);
			inls = pt.getLsResult();
		}
		else if (checkType!=null&&checkType.equals("2")) {
		
			String strQueryHQL = outBoundBus.getOutBoundQuerySQL(null, null, "6", null, fmDate, toDate, null, null);
            String strCountHQL = outBoundBus.getOutBoundCountSQL(null, null, "6", null, fmDate, toDate, null, null);

            pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 6);
            outls = pt.getLsResult();//查询结果
		}else {
			pt = inBoundBus.getInbounds(null, null, fmDate, toDate, null, null, null, "6", strUrl, 6);
			String strQueryHQL = outBoundBus.getOutBoundQuerySQL(null, null, "6", null, fmDate, toDate, null, null);
			inls = pt.getLsResult();	
			outls = dao.searchEntities(strQueryHQL,0, 6);
		}
		request.setAttribute("outls", outls);*/
		httpsession.setAttribute("paging", pt);
		request.setAttribute("objLs", cReturn.getLsReturn());	
		request.getRequestDispatcher(strUrl).forward(request, response);
		} catch (Exception er) {
			Logger.error("库存管理==>盘点查询==>盘点查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 功能：查询盘点单据明细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecSearchDel(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		String invoiceid = CommonTools.getStrToGbk(request.getParameter("instockid"));	//单据id
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));	//单据id
	    
	    try {
	
	    if (flag!=null && flag.equals("1")) {//入库盘点单明细
	    	strUrl = "/standard/jsp/inventory/checksearch/kc_check_search_indetail.jsp";
	    	inBoundBus = new InBoundBusImpl(dao);
	    	List ls = inBoundBus.getInboundDetails(invoiceid);
			request.setAttribute("exResultList", ls);
		}
	    if (flag!=null && flag.equals("2")) { //出库盘点单详细
	    	strUrl = "/standard/jsp/inventory/checksearch/kc_check_search_outdetail.jsp";
	    	outBoundBus = new OutBoundBusImpl(dao);
	        List lsDetail = outBoundBus.getOutBoundDetailsById(invoiceid);        
	        request.setAttribute("exResultList", lsDetail); 
		}
	    request.getRequestDispatcher(strUrl).forward(request, response);
		} catch (Exception er) {
			Logger.error("库存管理==>盘点查询==>查询盘点单据明细失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 功能：调整查询
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdjust(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception{

		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		String checkType = CommonTools.getStrToGbk(request.getParameter("checkType"));	//盘点类型
		String fmDate = CommonTools.getStrToGbk(request.getParameter("fmDate"));	//from时间
		String toDate = CommonTools.getStrToGbk(request.getParameter("toDate"));	//to时间
		String kccheckid = CommonTools.getStrToGbk(request.getParameter("kccheckid"));	//盘点单ID
		//inBoundBus = new InBoundBusImpl(dao);
		//outBoundBus = new OutBoundBusImpl(dao);
		IInventoryCheckBus iCheckBus = new InventoryCheckBusImpl(dao);
		try {
			
			strUrl = "/standard/jsp/inventory/adjust/kc_adjust_check_search_list.jsp";
			CommonReturn cReturn = iCheckBus.getCheckResultsByTimeAndType1(checkType, fmDate, toDate);
			PagingTool pt =  CommonPagination.getPagingTool(dao, cReturn.getStrCountHQL(), cReturn.getStrQueryHQL(), strUrl, 1, 20);
	     
		httpsession.setAttribute("paging", pt);
		request.setAttribute("objLs", cReturn.getLsReturn());	
		request.getRequestDispatcher(strUrl).forward(request, response);
		} catch (Exception er) {
			Logger.error("库存管理==>盘点调整查询==>查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	
	}
	public String ricoExecReport(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		String checkType = CommonTools.getStrToGbk(request.getParameter("checkType"));	//盘点类型
		String fmDate = CommonTools.getStrToGbk(request.getParameter("fmDate"));	//from时间
		String toDate = CommonTools.getStrToGbk(request.getParameter("toDate"));	//to时间
		IInventoryCheckBus iCheckBus = new InventoryCheckBusImpl(dao);
		try {
			strUrl = "/standard/jsp/inventory/checksearch/kc_checksearch_report.jsp";
			CommonReturn cReturn = iCheckBus.getCheckResultsByTimeAndType(checkType, fmDate, toDate);
			PagingTool pt =  CommonPagination.getPagingTool(dao, cReturn.getStrCountHQL(), cReturn.getStrQueryHQL(), strUrl, 1, 20);
		httpsession.setAttribute("paging", pt);
		request.setAttribute("objLs", cReturn.getLsReturn());	
		request.getRequestDispatcher(strUrl).forward(request, response);
		} catch (Exception er) {
			Logger.error("库存管理==>盘点查询==>盘点查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

}
