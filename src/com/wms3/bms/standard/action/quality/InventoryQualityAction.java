package com.wms3.bms.standard.action.quality;

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
import com.wms3.bms.standard.business.quality.IQualityBus;
import com.wms3.bms.standard.business.quality.InventoryQualityBus;
import com.wms3.bms.standard.business.quality.impl.InventoryQualityBusImpl;
import com.wms3.bms.standard.business.quality.impl.QualityBusImpl;
import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inventory.InventoryQualityResult;
/**
 * 描述：状态转换
 * @author yao
 *
 */
public class InventoryQualityAction extends AbstractAction 
{
	
	protected IQualityBus qualityBus;
	protected int maxLine = 20;			//分页显示的行数；
	
	@Override
	public String  ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam)throws Exception{
		
		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String strWarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));//仓库ID	
		String strAreaId = CommonTools.getStrToGbk(request.getParameter("whareaid"));//货区
		String strLotNumber = CommonTools.getStrToGbk(request.getParameter("lotnumber"));//批号
		String strRequestId = CommonTools.getStrToGbk(request.getParameter("requestid"));//申请单id
		String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));//产品id
		String strProductStatus = CommonTools.getStrToGbk(request.getParameter("productstatus"));//产品状态
		
		String productCode = CommonTools.getStrToGbk(request.getParameter("productCode"));//产品编码
		String strLineRow = CommonTools.getStrToGbk(request.getParameter("linerow"));//显示行数
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));//标示
		
		if (strLineRow!=null && strLineRow.length()>0) {
			maxLine = Integer.parseInt(strLineRow);
		}
		qualityBus = new QualityBusImpl(dao);
		List ls = null;
		try {
		if (flag!=null ) {
			if (flag.equals("1")) {//质检管理=》状态转换->查询库存
				strUrl = "standard/jsp/quality/changestatus/changestatus_list.jsp";
				String strQueryHql = qualityBus.searchInventoryForLotnumber(strWarehouseId, strAreaId, strLotNumber, strRequestId, strProductId, strProductStatus,productCode);	
				int count = dao.searchEntities(strQueryHql).size();
				PagingTool pt = CommonPagination.getPagingTool(dao, count, strQueryHql, strUrl, 1, maxLine);
				ls = pt.getLsResult();				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
			}else if(flag.equals("2")){//质检管理=》状态转换->查询库存（入库单）
                String strHql = qualityBus.searchInventroyForInstock(strWarehouseId, strLotNumber,strRequestId, strProductId,strProductStatus,strAreaId);
				ls = dao.searchEntities(strHql);
                strUrl = "standard/jsp/quality/changestatus/changestatus_list_instock.jsp";		
				request.setAttribute("exResultList", ls);			
			}else if(flag.equals("3")){//质检管理=》状态转换->查询库存（入库单明细）
				String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));
                List<InboundDetail> lsdel = qualityBus.searchInStockDel(instockid, strProductId);
                strUrl = "standard/jsp/quality/changestatus/changestatus_list_instockdel.jsp";		
				request.setAttribute("exResultList", lsdel);			
			}else if(flag.equals("4")){//质检管理=》放行记录查询->查询放行记录
				String strFormDate = CommonTools.getStrToGbk(request.getParameter("fromdate"));//开始时间
				String strToDate = CommonTools.getStrToGbk(request.getParameter("todate"));//结束时间
				
				InventoryQualityBus iBus = new InventoryQualityBusImpl(dao);
				//查询放行记录的hql
				String strHql = iBus.getQualityHeaderSQL(strLotNumber, strFormDate, strToDate);
				String strCountHql = iBus.getQualityHeaderCountSQL(strLotNumber, strFormDate, strToDate);
				
				strUrl = "standard/jsp/quality/changestatus/changestatusqueryhead.jsp";	
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHql, strHql, strUrl, 1, maxLine);
				
				ls = pt.getLsResult();				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);			
			}
		}
		 request.getRequestDispatcher(strUrl).forward(request, response);
		} catch (Exception er) {
			Logger.error("质检管理==>状态转换==>查询库存:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 功能：更新物品状态增加放行记录
	 *@author liuxh 2013-3-8
	 *@param hsSysParam
	 *@param hsCurrentParam
	 *@return
	 *@throws Exception
	 */
	public String ricoExecUpdateStatusAndAddRecord(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");  
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		String strUserCode = request.getSession().getAttribute("userCode").toString();
		//String strUserName = request.getSession().getAttribute("userName").toString();
		
		
		String strWarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));//仓库ID	
		String strAreaId = CommonTools.getStrToGbk(request.getParameter("whareaid"));//货区
		String strLotNumber = CommonTools.getStrToGbk(request.getParameter("lotnumber"));//批号
		String strRequestId = CommonTools.getStrToGbk(request.getParameter("requestid"));//申请单id
		String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));//产品id
		String strProductStatus = CommonTools.getStrToGbk(request.getParameter("productstatus"));//产品状态
		String strwpzt = CommonTools.getStrToGbk(request.getParameter("wpzt"));//新的物品状态
		qualityBus = new QualityBusImpl(dao);
		try {
			CommonReturn cReturn = qualityBus.updateStatusAddRecord(strUserCode, strWarehouseId, strAreaId, strLotNumber, strRequestId, strProductId, strProductStatus, strwpzt);
			strUrl = "/standard/jsp/quality/changestatus/changestatus_list.jsp";
			String strQueryHql = qualityBus.searchInventoryForLotnumber(strWarehouseId, strAreaId, strLotNumber, strRequestId, strProductId, strwpzt,null);	
			int count = dao.searchEntities(strQueryHql).size();
			PagingTool pt = CommonPagination.getPagingTool(dao, count, strQueryHql, strUrl, 1, maxLine);
			List ls = pt.getLsResult();				
			request.setAttribute("exResultList", ls);
			request.setAttribute("strMeg", cReturn.getRetInfo());
			httpsession.setAttribute("paging", pt);	
		}catch (Exception er) {
			Logger.error("质检管理==>状态转换:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		request.getRequestDispatcher(strUrl).forward(request, response);
		return null;
	}
	/**
	 * 状态转换单明细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");   
	
		String strUserCode = request.getSession().getAttribute("userCode").toString();
		String strUserName = request.getSession().getAttribute("userName").toString();
		String id = CommonTools.getStrToGbk(request.getParameter("id"));//状态转换单号
		strUrl = "/standard/jsp/quality/changestatus/changestatusquerydetail.jsp";
		try{
			if(id!=null && !id.equals("")){
				InventoryQualityBusImpl qualityBusImpl= new InventoryQualityBusImpl(dao);
				//InventoryQualityResultDetail ss=null;
				List ls= qualityBusImpl.getAdjustDetailListToId(id);
				request.setAttribute("exResultList", ls);
			}
		}catch (Exception er){
			Logger.error("质检管理==>状态转换:查询状态转换单明细时出现 " + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		request.getRequestDispatcher(strUrl).forward(request, response);
		return null;
	}
	
	/**
	 * 打印
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecPrint(Hashtable hsSysParam, Hashtable hsCurrentParam)
			throws Exception {
		HttpServletRequest request = (HttpServletRequest) hsCurrentParam
				.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam
				.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		
		
		String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));		//放行单ID
		String linerow = CommonTools.getStrToGbk(request
				.getParameter("linerow")); // 每页显示行数
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		}
		try {
			strUrl = "/standard/jsp/quality/changestatus/changesatus_print.jsp";
			
			InventoryQualityBusImpl qualityBusImpl= new InventoryQualityBusImpl(dao);
			
			InventoryQualityResult result= qualityBusImpl.getAdjustListToId(invoiceid);
			List ls = qualityBusImpl.getAdjustDetailListToId(invoiceid);
			
			request.setAttribute("result", result);
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {
			Logger.error("质量管理==>放行记录查询==>报表:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}

		return null;
	}

}
