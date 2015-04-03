package com.wms3.bms.standard.action.inbound;

import java.net.URLDecoder;
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
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.inbound.IInBoundBus;
import com.wms3.bms.standard.business.inbound.impl.InBoundBusImpl;
import com.wms3.bms.standard.business.inbound.impl.InboundPoBusImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.inbound.InboundInvoiceDetail;
import com.wms3.bms.standard.entity.inbound.InboundInvoiceHeader;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;

/**
 * 描述:入库单管理
 * @author gui
 *
 */
public class InBoundAction extends AbstractAction
{
	protected IInBoundBus inBoundBus;
	protected List<BaseLotSet> lsLot;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区
		String indate = CommonTools.getStrToGbk(request.getParameter("indate"));			//日期
		String shiftid = CommonTools.getStrToGb2312(request.getParameter("shift_id"));		//班次 
		String owner_id = CommonTools.getStrToGbk(request.getParameter("owner_id"));		//货主
		String isinvoice = CommonTools.getStrToGbk(request.getParameter("isinvoice"));		//是否开单
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		inBoundBus = new InBoundBusImpl(dao);
		try{
		
			lsLot = getLotset(hsSysParam);
			
			if(flag != null && flag.equals("1")){ //新建入库单 查询列表
			
				strUrl = "/standard/jsp/inbound/newin/inbound_newin_list.jsp";
				
				List ls = inBoundBus.getInboundJobs(warehouseid, whAreaId, indate, shiftid, owner_id, isinvoice, lsLot);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2")){//新建入库单 查询列表 查询明细
			

				String strKey = URLDecoder.decode(CommonTools.getStrToGb2312(request.getParameter("key")), "UTF-8");	//明细(有中文和特殊字符)
				strUrl = "/standard/jsp/inbound/newin/inbound_newin_list_detail.jsp";
				
				List ls = inBoundBus.getInboundJobDetails(warehouseid, whAreaId, indate, shiftid, isinvoice, strKey, lsLot);
				request.setAttribute("exResultList", ls);
				request.setAttribute("lsLot", lsLot);
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("入库管理==>新建入库单==>作业查询失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	
	/**
	 * 功能:生成入库单
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecCreate(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String searchCondition = CommonTools.getStrToGbk(request.getParameter("searchCondition"));	//查询条件
		String strKey = CommonTools.getStrToGb2312(request.getParameter("key"));	//明细
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		inBoundBus = new InBoundBusImpl(dao);
		
		try{
			String strBackMsg = "Y";
			lsLot = getLotset(hsSysParam);
			
			strUrl = "/standard/jsp/inbound/newin/inbound_newin_list.jsp";
			String shiftid = "";		//班次 (预留)
			
			String[] bem = searchCondition.split("\\|");
			String warehouseid = bem[0];	//仓库
			String whAreaId = bem[1];		//库区
			String indate = bem[2];			//日期
			String owner_id = bem[3];		//货主
			String isinvoice = bem[4];		//是否开单
			
			strBackMsg = inBoundBus.addInboundInvoice(warehouseid, whAreaId, indate, shiftid, strKey, lsLot, strUserCode);
			
			List ls = inBoundBus.getInboundJobs(warehouseid, whAreaId, indate, shiftid, owner_id, isinvoice, lsLot);
			request.setAttribute("exResultList", ls);
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
					
		}catch(Exception er){
		
			Logger.error("用户["+strUserCode+"]，入库管理==>生成入库单据==>生成入库单失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:入库单管理->查询功能
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecSearchRkd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//日期开始
		String indate_to = CommonTools.getStrToGb2312(request.getParameter("indate_to"));	//日期结束
		String shiftid = CommonTools.getStrToGb2312(request.getParameter("shift_id"));		//班次 
		String owner_id = CommonTools.getStrToGbk(request.getParameter("owner_id"));		//货主
		String instatus = CommonTools.getStrToGbk(request.getParameter("instatus"));		//单据状态
		String intype = CommonTools.getStrToGbk(request.getParameter("intype"));			//入库类型
		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));		//入库单据编号
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
		int maxLine = 6;
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		
		inBoundBus = new InBoundBusImpl(dao);
		try{
		
			if(flag != null && flag.equals("1")){ 		//入库单管理 查询入库单列表
			
				strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_list.jsp";
				
				PagingTool pt = inBoundBus.getInbounds(warehouseid, whAreaId, indate_from, indate_to, shiftid, owner_id, instatus, intype, strUrl, maxLine);
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2")){	//入库单管理 查询入库单明细列表
			

				strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_detail.jsp";
				
				List ls = inBoundBus.getInboundDetails(instockid);
				request.setAttribute("exResultList", ls);
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("入库管理==>入库单管理==>入库单查询失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
/*	*//**
	 * 功能:入库单管理->单据审核
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 *//*
	public String ricoExecAudit(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//日期开始
		String indate_to = CommonTools.getStrToGb2312(request.getParameter("indate_to"));	//日期结束
		String shiftid = CommonTools.getStrToGb2312(request.getParameter("shift_id"));		//班次 
		String owner_id = CommonTools.getStrToGbk(request.getParameter("owner_id"));		//货主
		String instatus = CommonTools.getStrToGbk(request.getParameter("instatus"));		//单据状态
		String intype = CommonTools.getStrToGbk(request.getParameter("intype"));			//入库类型
		String instockids = CommonTools.getStrToGbk(request.getParameter("instockids"));	//入库单据编号
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
		int maxLine = 6;
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strCurrDate = StrTools.getCurrDateTime(8);
		
		inBoundBus = new InBoundBusImpl(dao);
		
		try{
			String strBackMsg = "Y";
			
			String ids[] = instockids.split(",");
			InboundHeader inbound = null;
			for(int i=0 ; i < ids.length; i++){
			
				inbound = inBoundBus.getInboundInvoice(ids[i]);
				if(inbound!=null && inbound.getInstatus().equals("0")){	//单据状态 0-新建，1-审核，2-确认 5-作废 
				
					boolean bFlag = true; //验证成功标志
					List lsDetail = inBoundBus.getInboundDetails(ids[i]);	//入库单详细
					if(lsDetail != null){
					
						InboundInvoiceDetail inDetail = null;
						for(int j = 0; j < lsDetail.size(); j++){
						
							inDetail = (InboundInvoiceDetail)lsDetail.get(j);
							
							//验证单据详细的数量与作业数量是否相等
							int iNum = inBoundBus.getJobNumSum(inDetail.getInstockdetailid());
							if(iNum != (int)inDetail.getInvoicenum()){
								
								bFlag = false;
								break;
							}		
						}
					}
					if(bFlag){
						
						inbound.setInstatus("1");				//单据状态 0-新建，1-审核，2-确认 5-作废 
						inbound.setAuditmanid(strUserCode);		//审核人Id
						inbound.setAuditdate(strCurrDate);		//审核日期
						inBoundBus.updateInboundInvoice(inbound);
						Logger.error("用户["+strUserCode+"]进行了入库单审核操作，单据id：" + ids[i]);
						
					}else{
						
						if(strBackMsg.equals("Y")){
							strBackMsg = "单据【" + ids[i] + "】验证错误，单据产品数量与明细总数不等，无法审核！";
						}else{
							strBackMsg += "\\r单据【" + ids[i] + "】验证错误，单据产品数量与明细总数不等，无法审核！";
						}
					}
				}else{
				
					if(strBackMsg.equals("Y")){
						strBackMsg = "单据【" + ids[i] + "】的状态为非开单状态，无法审核！";
					}else{
						strBackMsg += "\\r单据【" + ids[i] + "】的状态为非开单状态，无法审核！";
					}
				}
			}
			
			strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_list.jsp";
			PagingTool pt = inBoundBus.getInbounds(warehouseid, whAreaId, indate_from, indate_to, shiftid, owner_id, instatus, intype, strUrl, maxLine);
			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
					
		}catch(Exception er){
		
			Logger.error("用户["+strUserCode+"]，入库管理==>单据审核失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}*/
	
	/**
	 * 功能:入库单管理->单据单据确认
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecConfirm(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//日期开始
		String indate_to = CommonTools.getStrToGb2312(request.getParameter("indate_to"));	//日期结束
		String shiftid = CommonTools.getStrToGb2312(request.getParameter("shift_id"));		//班次 
		String owner_id = CommonTools.getStrToGbk(request.getParameter("owner_id"));		//货主
		String instatus = CommonTools.getStrToGbk(request.getParameter("instatus"));		//单据状态
		String intype = CommonTools.getStrToGbk(request.getParameter("intype"));			//入库类型
		String instockids = CommonTools.getStrToGbk(request.getParameter("instockids"));	//入库单据编号
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
		int maxLine = 6;
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strCurrDate = StrTools.getCurrDateTime(8);
		
		inBoundBus = new InBoundBusImpl(dao);
		
		try{
			String strBackMsg = "Y";
			
			String ids[] = instockids.split(",");
			InboundHeader inbound = null;
			for(int i=0 ; i < ids.length; i++){
			
				inbound = inBoundBus.getInboundInvoice(ids[i]);
				if(inbound!=null && inbound.getInstatus().equals("1")){	//单据状态 1-新建，3-确认  5-作废 
				
					boolean bFlag = true; //验证成功标志
					
					List lsDetail = inBoundBus.getInboundDetails(ids[i]);	//入库单详细
					if(lsDetail != null){
					
						InboundDetail inDetail = null;
						for(int j = 0; j < lsDetail.size(); j++){
						
							inDetail = (InboundDetail)lsDetail.get(j);
							
							//验证单据详细的数量与作业数量是否相等
							double iNum = inBoundBus.getJobNumSum(inDetail.getInstockdetailid());
							if(iNum != inDetail.getInnum()){
								bFlag = false;
								break;
							}		
						}
					}
					if(bFlag){
						inbound.setInstatus("3");				//单据状态 1-新建，3-确认 5-作废 
						inbound.setCreatemanid(strUserCode);	//确认人Id
						inbound.setCreatetime(strCurrDate);		//确认日期
						inBoundBus.updateInboundInvoice(inbound);
						Logger.error("用户["+strUserCode+"]进行了入库单确认操作，单据id：" + ids[i]);
						
					}else{
						
						if(strBackMsg.equals("Y")){
							strBackMsg = "单据【" + ids[i] + "】验证错误，单据产品数量与明细总数不等，无法确认！";
						}else{
							strBackMsg += "\\r单据【" + ids[i] + "】验证错误，单据产品数量与明细总数不等，无法确认！";
						}
					}
				}else{
				
					if(strBackMsg.equals("Y")){
						strBackMsg = "单据【" + ids[i] + "】的状态为非开单状态，无法确认！";
					}else{
						strBackMsg += "\\r单据【" + ids[i] + "】的状态为非开单状态，无法确认！";
					}
				}
			}
			
			strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_list.jsp";
			PagingTool pt = inBoundBus.getInbounds(warehouseid, whAreaId, indate_from, indate_to, shiftid, owner_id, instatus, intype, strUrl, maxLine);
			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
					
		}catch(Exception er){
		
			Logger.error("用户["+strUserCode+"]，入库管理==>单据确认失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:入库单管理->单据作废
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecCancel(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//日期开始
		String indate_to = CommonTools.getStrToGb2312(request.getParameter("indate_to"));	//日期结束
		String shiftid = CommonTools.getStrToGb2312(request.getParameter("shift_id"));		//班次 
		String owner_id = CommonTools.getStrToGbk(request.getParameter("owner_id"));		//货主
		String instatus = CommonTools.getStrToGbk(request.getParameter("instatus"));		//单据状态
		String intype = CommonTools.getStrToGbk(request.getParameter("intype"));			//入库类型
		String instockids = CommonTools.getStrToGbk(request.getParameter("instockids"));	//入库单据编号
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
		int maxLine = 6;
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		inBoundBus = new InBoundBusImpl(dao);
		
		try{
			String strBackMsg = "Y";
			
			String ids[] = instockids.split(",");
			InboundHeader inbound = null;
			for(int i=0 ; i < ids.length; i++){
				
				inbound = inBoundBus.getInboundInvoice(ids[i]);
				if(inbound!=null && inbound.getInstatus().equals("1")){	//单据状态 1-新建，2-确认 5-作废 
				
					//**根据单据来源区分********************************************
					//1：直接采集生成的作业，2：按收货单来生成的作业，3：按入库单来生成的作业 4:按ERP其它单来生成的作业
					//if(inbound.getInvoicesource().equals("1") || inbound.getInvoicesource().equals("2") ){ 	
					
						inBoundBus.cancelInboundInvoice(ids[i]);
						Logger.error("用户["+strUserCode+"]进行了入库单作废操作，单据id：" + ids[i]);
					//}
					
				}else{
				
					if(strBackMsg.equals("Y")){
						strBackMsg = "单据【" + ids[i] + "】的状态为非开单状态，无法作废！";
					}else{
						strBackMsg += "\\r单据【" + ids[i] + "】的状态为非开单状态，无法作废！";
					}
				}
			}
			
			strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_list.jsp";
			PagingTool pt = inBoundBus.getInbounds(warehouseid, whAreaId, indate_from, indate_to, shiftid, owner_id, instatus, intype, strUrl, maxLine);
			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
					
		}catch(Exception er){
		
			Logger.error("用户["+strUserCode+"]，入库管理==>单据作废失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * po作业信息显示
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
     public String ricoExecJobDdis(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String instockdetailid = CommonTools.getStrToGbk(request.getParameter("instockdetailid"));	//单据详细编号
		inBoundBus = new InBoundBusImpl(dao);
		try{

			List<Object[]> ls=inBoundBus.getJobInfoByPodetailid(instockdetailid);
            strUrl = "/standard/jsp/inbound/job/inbound_jobinfo_display.jsp";
            request.setAttribute("jobinfo", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("出库管理==>外来单管理==>入库单打印失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
/*	*//**
	 * 功能:入库单管理->明细单据作废
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 *//*
	public String ricoExecCancelDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String instockdetailids = CommonTools.getStrToGbk(request.getParameter("instockdetailids"));	//入库单据编号
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		inBoundBus = new InBoundBusImpl(dao);
		
		try{
			String strBackMsg = "Y";
			
			String ids[] = instockdetailids.split(",");
			String instockid = ids[0].substring(0, ids[0].length()-2);
			InboundHeader inbound = inBoundBus.getInboundInvoice(instockid);
				
			if(inbound!=null && inbound.getInstatus().equals("0")){	//单据状态 0-新建，1-审核，2-确认 5-作废 
			
				//**根据单据来源区分********************************************
				//1：直接采集生成的作业，2：按收货单来生成的作业，3：按入库单来生成的作业 4:按ERP其它单来生成的作业
				if(inbound.getInvoicesource().equals("1") || inbound.getInvoicesource().equals("2") ){

					for(int i=0 ; i < ids.length; i++){
						inBoundBus.cancelInboundDetail(ids[i]);
						Logger.error("用户["+strUserCode+"]进行了入库单明细作废操作，单据id：" + instockid + "，单据明细id：" + ids[i]);
					}
				}
				
			}else{
			
				if(strBackMsg.equals("Y")){
					strBackMsg = "单据【" + instockid + "】的状态为非开单状态，无法作废！";
				}else{
					strBackMsg += "\\r单据【" + instockid + "】的状态为非开单状态，无法作废！";
				}
			}
			
			strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_detail.jsp";
			List ls = inBoundBus.getInboundDetails(instockid);
			request.setAttribute("exResultList", ls);
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
					
		}catch(Exception er){
		
			Logger.error("用户["+strUserCode+"]，入库管理==>明细单据作废失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	*/
	/**
	 * 功能:入库单管理->单据打印
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecReportRKD(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));		//入库单据编号
		
		inBoundBus = new InBoundBusImpl(dao);
		try{
			
			lsLot = getLotset(hsSysParam);	//批次属性集
			
			InboundHeader inbound = inBoundBus.getInboundInvoice(instockid);	//入库单
			List lsDetail = inBoundBus.getInboundDetails(instockid);	//入库单详细
            
            strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_print.jsp";
            request.setAttribute("lsLot", lsLot);
            request.setAttribute("invoice", inbound);
            request.setAttribute("invoicedetail", lsDetail); 
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("入库管理==>入库单管理==>入库单打印失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:返回批次属性列表
	 * @param hsSysParam
	 * @return
	 * @throws Exception
	 */
	private List getLotset(Hashtable hsSysParam) {
		
		HashMap<String, List> hsLot = (HashMap<String, List>)hsSysParam.get("viewLot");	//所有要显示的批次
		List list = null;  	//显示的批次属性列表
		if(hsLot != null){
			list = hsLot.get("newin");		//新建入库单->入库单统计的项目
		}
		return list;
	}
	
	
}
