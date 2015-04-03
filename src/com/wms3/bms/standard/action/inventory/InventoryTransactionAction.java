package com.wms3.bms.standard.action.inventory;

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
import com.wms3.bms.standard.business.inventory.IInventoryTransactionBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryTransactionBusImpl;

/**
 * 描述:库存交易
 * @author liuxh
 *
 */
public class InventoryTransactionAction extends AbstractAction
{
	protected IInventoryTransactionBus iTransactionBus;
	protected int maxLine = 20;			//分页显示的行数；
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		//from
		String fmwarehouseid = CommonTools.getStrToGbk(request.getParameter("fmwarehouseid"));	//仓库ID
		String fmwhAreaId = CommonTools.getStrToGbk(request.getParameter("fmwhAreaid"));		//库区ID
		String fmplatoon = CommonTools.getStrToGbk(request.getParameter("fmplatoon"));			//排
		String fmcolumn = CommonTools.getStrToGbk(request.getParameter("fmcolumn"));			//列
		String fmfloor = CommonTools.getStrToGbk(request.getParameter("fmfloor"));				//层
		
		String fmCustomerId = CommonTools.getStrToGbk(request.getParameter("fmcustomerid"));	//客户ID
		String fmPackageId = CommonTools.getStrToGbk(request.getParameter("fmpackageId"));	//品名ID
		String fmTrayCode = CommonTools.getStrToGbk(request.getParameter("fmtrayCode"));		//托盘条码
		//to
		String towhAreaId = CommonTools.getStrToGbk(request.getParameter("towhAreaid"));		//库区ID
		String toplatoon = CommonTools.getStrToGbk(request.getParameter("toplatoon"));			//排
		String tocolumn = CommonTools.getStrToGbk(request.getParameter("tocolumn"));			//列
		String tofloor = CommonTools.getStrToGbk(request.getParameter("tofloor"));				//层
		
		String toCustomerId = CommonTools.getStrToGbk(request.getParameter("tocustomerId"));	//客户ID
		String toPackageId = CommonTools.getStrToGbk(request.getParameter("topackageId"));	//品名ID
		String toTrayCode = CommonTools.getStrToGbk(request.getParameter("totrayCode"));		//托盘条码
		
		String date_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//日期from
		String date_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//日期to
        
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数 
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        iTransactionBus = new InventoryTransactionBusImpl(dao);
        List lsTransactions = null;
        PagingTool pt = null;
		try
		{
			strUrl = "/standard/jsp/inventory/ykquery/kc_query_list.jsp";
			pt = iTransactionBus.getTransactionsLs(fmwarehouseid, fmwhAreaId, fmplatoon, fmcolumn, fmfloor,
						fmCustomerId, fmPackageId, fmTrayCode, towhAreaId, toplatoon, tocolumn, tofloor, toCustomerId, 
						toPackageId, toTrayCode, date_from, date_to,strUrl,1,maxLine);		
			lsTransactions = pt.getLsResult();
			request.setAttribute("exResultList", lsTransactions);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存交易==>查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 功能：打印报表
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecReport(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		//from
		String fmwarehouseid = CommonTools.getStrToGbk(request.getParameter("fmwarehouseid"));	//仓库ID
		String fmwhAreaId = CommonTools.getStrToGbk(request.getParameter("fmwhAreaid"));		//库区ID
		String fmplatoon = CommonTools.getStrToGbk(request.getParameter("fmplatoon"));			//排
		String fmcolumn = CommonTools.getStrToGbk(request.getParameter("fmcolumn"));			//列
		String fmfloor = CommonTools.getStrToGbk(request.getParameter("fmfloor"));				//层
		
		String fmCustomerId = CommonTools.getStrToGbk(request.getParameter("fmcustomerid"));	//客户ID
		String fmPackageId = CommonTools.getStrToGbk(request.getParameter("fmpackageId"));	//品名ID
		String fmTrayCode = CommonTools.getStrToGbk(request.getParameter("fmtrayCode"));		//托盘条码
		//to
		String towhAreaId = CommonTools.getStrToGbk(request.getParameter("towhAreaid"));		//库区ID
		String toplatoon = CommonTools.getStrToGbk(request.getParameter("toplatoon"));			//排
		String tocolumn = CommonTools.getStrToGbk(request.getParameter("tocolumn"));			//列
		String tofloor = CommonTools.getStrToGbk(request.getParameter("tofloor"));				//层
		
		String toCustomerId = CommonTools.getStrToGbk(request.getParameter("tocustomerId"));	//客户ID
		String toPackageId = CommonTools.getStrToGbk(request.getParameter("topackageId"));	//品名ID
		String toTrayCode = CommonTools.getStrToGbk(request.getParameter("totrayCode"));		//托盘条码
		
		String date_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//日期from
		String date_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//日期to
        
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数 
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        iTransactionBus = new InventoryTransactionBusImpl(dao);
        List lsTransactions = null;
        PagingTool pt = null;
		try
		{
			strUrl = "/standard/jsp/inventory/transaction/kc_transaction_report.jsp";
			pt = iTransactionBus.getTransactionsLs(fmwarehouseid, fmwhAreaId, fmplatoon, fmcolumn, fmfloor,
						fmCustomerId, fmPackageId, fmTrayCode, towhAreaId, toplatoon, tocolumn, tofloor, toCustomerId, 
						toPackageId, toTrayCode, date_from, date_to,strUrl,1,maxLine);		
			lsTransactions = pt.getLsResult();
			request.setAttribute("exResultList", lsTransactions);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存交易==>打印报表:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
