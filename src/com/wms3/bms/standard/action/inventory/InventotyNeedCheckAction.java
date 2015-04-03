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
import com.wms3.bms.standard.business.inventory.IInventoryNeedCheckBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryNeedCheckBusImp;
/**
 * 描述：需盘点管理的action
 * @author liuxh
 * @since 2012-11-22
 *
 */
public class InventotyNeedCheckAction extends AbstractAction{
	protected int maxLine = 6;
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String strNeedCheckId = CommonTools.getStrToGbk(request.getParameter("needcheckid"));//需盘点id	
		String strJobId = CommonTools.getStrToGbk(request.getParameter("jobid"));//作业号
		String strinouttype = CommonTools.getStrToGbk(request.getParameter("inouttype"));//入出类型
		String strcargoSpaceId = CommonTools.getStrToGbk(request.getParameter("cargoSpaceId"));//作业货位
		String strTimeForm = CommonTools.getStrToGbk(request.getParameter("createtimeform"));//生成时间from
		String strTimeTo = CommonTools.getStrToGbk(request.getParameter("createtimeto"));//生成时间to
		String strHandleFlag = CommonTools.getStrToGbk(request.getParameter("handleflag"));//处理标识
		String strTrayCode = CommonTools.getStrToGbk(request.getParameter("traycode"));//托盘条码
		String strTaskNo = CommonTools.getStrToGbk(request.getParameter("taskno"));//任务号
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
		if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		try {
			IInventoryNeedCheckBus iNeedCheckBus = new InventoryNeedCheckBusImp(dao);
			PagingTool pt = null;
			String[] sql = new String[2];
			sql = iNeedCheckBus.getInventNeedCheck(strNeedCheckId, strJobId, strinouttype, strcargoSpaceId, strTimeForm, strTimeTo, strHandleFlag, strTrayCode, strTaskNo);
			pt = CommonPagination.getPagingTool(dao, sql[1] ,sql[0], strUrl, 1, maxLine);
			strUrl = "/standard/jsp/inventory/needcheckmgr/kc_need_check_list.jsp";
			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
		    httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
		} catch (Exception er) {
			Logger.error("库存管理==>需盘点管理==>需盘点查询失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}		
		return null;
	}
	/**
	 * 功能：处理需盘点：保存、作废
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecUpdate(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");	
		String strUserCode = httpsession.getAttribute("userName").toString();//操作员
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));//需盘点
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));//1.保存2作废
		IInventoryNeedCheckBus needCheckBus = new InventoryNeedCheckBusImp(dao);
		try {			
			strUrl = "/standard/jsp/inventory/needcheckmgr/kc_need_check_list.jsp";
			String meg = needCheckBus.updateInventAndCargoSpace(ids, flag, strUserCode );
			request.setAttribute("back_msg", meg);
			request.getRequestDispatcher(strUrl).forward(request, response);
		} catch (Exception er) {
			Logger.error("库存管理==>需盘点管理==>需盘点处理失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}		
		return null;
	}

/**
 * 功能:需盘点管理->单据打印
 * @param hsSysParam
 * @param hsCurrentParam
 * @return
 * @throws Exception
 */
public String ricoExecPrint(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

	HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
	HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
	HttpSession httpsession = request.getSession(false);
	EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
	
	String strNeedCheckId = CommonTools.getStrToGbk(request.getParameter("needcheckid"));//需盘点id	
	String strJobId = CommonTools.getStrToGbk(request.getParameter("jobid"));//作业号
	String strinouttype = CommonTools.getStrToGbk(request.getParameter("inouttype"));//入出类型
	String strcargoSpaceId = CommonTools.getStrToGbk(request.getParameter("cargoSpaceId"));//作业货位
	String strTimeForm = CommonTools.getStrToGbk(request.getParameter("createtimeform"));//生成时间from
	String strTimeTo = CommonTools.getStrToGbk(request.getParameter("createtimeto"));//生成时间to
	String strHandleFlag = CommonTools.getStrToGbk(request.getParameter("handleflag"));//处理标识
	String strTrayCode = CommonTools.getStrToGbk(request.getParameter("traycode"));//托盘条码
	String strTaskNo = CommonTools.getStrToGbk(request.getParameter("taskno"));//任务号
	String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
	if(linerow != null && linerow.length()>0){
    	maxLine = Integer.parseInt(linerow);
    }
	try {
		IInventoryNeedCheckBus iNeedCheckBus = new InventoryNeedCheckBusImp(dao);
		PagingTool pt = null;
		String[] sql = new String[2];
		sql = iNeedCheckBus.getInventNeedCheck(strNeedCheckId, strJobId, strinouttype, strcargoSpaceId, strTimeForm, strTimeTo, strHandleFlag, strTrayCode, strTaskNo);
		pt = CommonPagination.getPagingTool(dao, sql[1] ,sql[0], strUrl, 1, maxLine);
		strUrl = "/standard/jsp/inventory/needcheckmgr/kc_need_check_print.jsp";
		List ls = pt.getLsResult();
		request.setAttribute("exResultList", ls);
		request.getRequestDispatcher(strUrl).forward(request, response);
	} catch (Exception er) {
		Logger.error("库存管理==>需盘点管理==>需盘点查询失败:" + er.getMessage());
        request.setAttribute("errormsg", "错误信息:" + er.getMessage());
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}		
	return null;
}
}
