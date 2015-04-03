package com.wms3.bms.standard.action.inbound.lxyy;

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
import com.wms3.bms.standard.business.inbound.IInBoundPoBus;
import com.wms3.bms.standard.business.inbound.impl.InBoundBusImpl;
import com.wms3.bms.standard.business.inbound.impl.InboundPoBusImpl;
import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundPoHeader;

/**
 * 描述:
 * @author yaohb 2014-3-2
 * @since wmsBMS3.0
 */
public class InBoundPoAction extends AbstractAction {
	
	protected IInBoundPoBus inBoundPoBus;
	static final String V_RKDDETAIL = "V-rkddetail";
	protected int maxLine = 6;
	/* (non-Javadoc)
	 * @see com.ricosoft.action.AbstractAction#ricoExec(java.util.Hashtable, java.util.Hashtable)
	 */
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		String warehouseid = (String) hsSysParam.get("c_warhouse_id");	//仓库
		String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));	//客户id
		String createdate = CommonTools.getStrToGbk(request.getParameter("createdate"));	//创建日期
		String poid = CommonTools.getStrToGbk(request.getParameter("poid"));	//采购单id
		String potype = CommonTools.getStrToGbk(request.getParameter("potype"));
		
		String start_time = CommonTools.getStrToGbk(request.getParameter("start_time"));	
		String end_time = CommonTools.getStrToGbk(request.getParameter("end_time"));
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));	
		String RFflag = CommonTools.getStrToGbk(request.getParameter("RFflag"));	
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		inBoundPoBus = new InboundPoBusImpl(dao);
		try {
			if(flag!=null){
				if(flag.equals("1")){//获得订单信息	
					//跳转页面
					//strUrl = "/standard/jsp/inbound/po/inbound_po_list.jsp";
					strUrl = "/standard/jsp/inbound/po/inbound_rkmgr_po_list.jsp";
					PagingTool pt = inBoundPoBus.getInboundPoHeaders("", customerid, createdate,"1",null, null, strUrl,maxLine);
					List ls = pt!=null?pt.getLsResult():null;
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
				}else if(flag.equals("2")){
					//跳转页面
					strUrl = "/standard/jsp/inbound/po/inbound_po_detail.jsp";
					maxLine = 50;
					PagingTool pt = inBoundPoBus.getInboundPoDelsByPoId(poid, strUrl, maxLine);
					List ls = pt.getLsResult();
					request.setAttribute("exResultList", ls);
					
				}else if(flag.equals("3")){//获取走入库流程的订单
					//跳转页面
					strUrl = "/standard/jsp/inbound/po/inbound_rkmgr_po_list.jsp";
					PagingTool pt = inBoundPoBus.getInboundPoHeaders("", customerid, createdate,"2",null, null, strUrl,maxLine);
					List ls = pt!=null?pt.getLsResult():null;
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
				}else if(flag.equals("3newin")){//获取走入库流程的订单
					//跳转页面
					strUrl = "/standard/jsp/inbound/po/inbound_rkmgr_po_rk_list.jsp";
					PagingTool pt = inBoundPoBus.getInboundPoHeaders("", customerid, createdate,"2",null, null, strUrl,maxLine);
					List ls = pt!=null?pt.getLsResult():null;
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
				}else if(flag.equals("4")){//获取走入库流程的订单明细
					//跳转页面
					strUrl = "/standard/jsp/inbound/po/inbound_rkmgr_po_detail.jsp";
					maxLine = 50;
					PagingTool pt = inBoundPoBus.getInboundPoDelsByPoId(poid, strUrl, maxLine);
					List ls = pt.getLsResult();
					request.setAttribute("exResultList", ls);
				}else if(flag.equals("5")){//获取走入库流程的订单明细
					maxLine = 50;
					//跳转页面
					strUrl = "/customs/yld/jsp/rf/inbound_po_detail.jsp";
					PagingTool pt = inBoundPoBus.getInboundPoDelsByPoId(poid, strUrl, maxLine);
					List ls=null;
					if(pt!=null){
						ls= pt.getLsResult();
					}
					request.setAttribute("exResultList", ls);
				}else if(flag.equals("6")){//获得订单信息	
					//跳转页面
					//strUrl = "/standard/jsp/inbound/po/inbound_po_list.jsp";
					strUrl = "/standard/jsp/inbound/inothermgr/inbound_othermgr_list.jsp";
					if("rf".equals(RFflag)){
						strUrl = "/rf/lxyy/inother/inbound_othermgr_list.jsp";
						end_time = start_time;
					}
					PagingTool pt = inBoundPoBus.getInboundPoHeader("", customerid,  start_time,
							 end_time, null, poid, potype, strUrl, maxLine);
					List ls = pt!=null?pt.getLsResult():null;
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
				}else if(flag.equals("7")){
					//跳转页面
					strUrl = "/standard/jsp/inbound/inothermgr/inbound_othermgr_detail.jsp";
					if("rf".equals(RFflag)){
						strUrl = "/rf/lxyy/inother/inbound_othermgr_detail.jsp";
					}
					maxLine = 50;
					PagingTool pt = inBoundPoBus.getInboundPoDelsByPoId(poid, strUrl, maxLine);
					List ls = pt.getLsResult();
					request.setAttribute("exResultList", ls);
					
				}
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
		} catch (Exception er) {
			Logger.error("到货单管理==>采购订单查询出错:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 功能：根据订购单创建预期到货通知单及其详细
	 * @author liuxh 2014-1-21
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddAsn(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
			
			HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
			HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
			EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
			HttpSession httpsession = request.getSession(false);
			
			String poid = CommonTools.getStrToGbk(request.getParameter("poid"));	//采购单id
			String ids = CommonTools.getStrToGbk(request.getParameter("ids"));	    //订单明细|数量
			String asntype = CommonTools.getStrToGbk(request.getParameter("asntype"));	    //订单明细|数量
			String warehouseid = (String) hsSysParam.get("c_warhouse_id");	//仓库
			String strUserCode = (String)request.getSession().getAttribute("userCode");           //当前用户
			String flag = CommonTools.getStrToGbk(request.getParameter("flag"));	//采购单id
			String isReceipt = CommonTools.getStrToGbk(request.getParameter("isReceipt"));//是否直接收货
			inBoundPoBus = new InboundPoBusImpl(dao);
			//IInBoundAsnBus asnBus = new InBoundAsnBusImpl(dao);
			try {
				String 	strBackMsg="Y";	
				CommonReturn cReturn = inBoundPoBus.createAsnByPoId(poid,ids,asntype,warehouseid, strUserCode);
				strBackMsg = cReturn.getRetInfo();
				//跳转页面
				if("rf".equals(flag)){
				strUrl = "/customs/yld/jsp/rf/RF_receipt_add.jsp";
				if(strBackMsg==null){
					strBackMsg="Y";
				}
				}else{
				strUrl = "/standard/jsp/inbound/asnmgr/inbound_newasn_list.jsp";
				}
				String invoiceid = cReturn.getStrCountHQL();//单据id
				/*if("Y".equals(strBackMsg) && "1".equals(isReceipt))
				{
					List detail = asnBus.getInboundAsnDetailsByAsnId(invoiceid);
					for(int i = 0; i < detail.size(); i++)
					{
						IReceiptBus receiptBus = new ReceiptBusImpl(dao);
						String detailid = ((InboundAsnDetail)detail.get(i)).getAsndetailid();
						double num = ((InboundAsnDetail)detail.get(i)).getInvoicenum();
						String strMsg = "";
						strMsg = receiptBus.excuteReceipt(warehouseid, null, null, null, invoiceid, detailid, null,null, null, null, null, null, null, 
							null, null, null, null, null, null,num, 0, 0, 0, 0,  null, null, 0, null, null, strUserCode, null, null);	
						if(!"Y".equals(strMsg))
						{
							strBackMsg = strMsg;
							break;
						}
					  
					}
				}
				IInBoundAsnBus inBoundAsnBus = new InBoundAsnBusImpl(dao);*/
				/*PagingTool pt = inBoundAsnBus.getInboundAsnHeaders(warehouseid, "0", null, StrTools.getCurrDateTime(8) ,StrTools.getCurrDateTime(8), asntype, strUrl, maxLine);				
				List ls = pt!=null?pt.getLsResult():null; */
				//request.setAttribute("exResultList", ls);	
				request.setAttribute("InvoicedId", invoiceid);	
                //httpsession.setAttribute( "paging", pt);
				request.setAttribute("back_msg", strBackMsg);
				request.getRequestDispatcher(strUrl).forward(request, response);
			} catch (Exception er) {
				Logger.error("到货单管理==>采购订单查询出错:" + er.getMessage());
	            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
	            request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
			return null;
		}
	/**
	 * 功能：根据订单创建入库单及明细
	 * @author yao 2014-2-28
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddInbound(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
			
			HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
			HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
			EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
			HttpSession httpsession = request.getSession(false);
			
			String warehouseid = (String) hsSysParam.get("c_warhouse_id");	//仓库
			String poid = CommonTools.getStrToGbk(request.getParameter("poid"));	//入库订单id
			String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));	//入库单
			String ids = CommonTools.getStrToGbk(request.getParameter("ids"));	//入库订单明细集合
			String intype = CommonTools.getStrToGbk(request.getParameter("intype"));	//入库类型
			String departid = CommonTools.getStrToGbk(request.getParameter("departid"));  //部门
			String strUserCode = (String)request.getSession().getAttribute("userCode");           //当前用户
	
			inBoundPoBus = new InboundPoBusImpl(dao);
			InBoundBusImpl inboundbusimpl = new InBoundBusImpl(dao);
			
			try {/*
				String 	strBackMsg="Y";	
				//跳转页面
				strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_addbyorder_detail.jsp";
				List ls=null;
				if(ids!=null && ids.length()>0){
					String info[] = ids.split(",");
					if(info!=null){
						InboundPoHeader header = inBoundPoBus.getInboundPoHeaders(poid);
						if(header!=null ){
							synchronized (inboundbusimpl.newin_lock) {
								if(instockid!=null && !instockid.equals("")){//向已存在的入库单添加入库信息
									//InboundInvoiceHeader head = inboundbusimpl.getInboundInvoice(instockid);
									if(head!=null&&head.getInstatus()!=null&&head.getInstatus().equals("0")&&head.getInvoicesource()!=null&&head.getInvoicesource().equals("4")){
										List lsList = new ArrayList<InboundInvoiceDetail>();
						                List lsList2 = new ArrayList<InboundPoDetail>(); 
						                String sqlMax = "select max(detail.instockdetailid) from InboundInvoiceDetail as detail where detail.instockid='" + instockid + "'";
						                int sValue = SeqTool.getDetailMaxId(sqlMax, instockid.length(),dao);
										for(int i=0;i<info.length;i++){
											String msg[]=info[i].split("\\|"); // 订单明细id|数量值|库区id
											InboundPoDetail podetail = inBoundPoBus.getInboundPoHeaders(poid,msg[0]);
											if(podetail!=null){
												InboundInvoiceDetail inDetail = new InboundInvoiceDetail();
								                inDetail.setInstockdetailid(instockid + SeqTool.getCode((sValue+i+1), StandardConstant.D_LEN));	//入库单详细ID
									            inDetail.setInstockid(instockid);	//入库单据编号
									            inDetail.setProductid(podetail.getProductid());		//品名规格
									            inDetail.setPackid(podetail.getPackid());				//包装
									            inDetail.setEunit(podetail.getEunit());				//单位
									            inDetail.setInvoicenum(Double.parseDouble(msg[1]));		//数量（开单）
									            inDetail.setWhAreaId(msg[2]);
									            inDetail.setLinestatus("0");        	//单据行状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
									            inDetail.setReincoiceid(podetail.getPoid());//来自订单id
									            inDetail.setReinvoicedetailid(podetail.getPodetailid());//来自订单详细id
									            inDetail.setLotid(podetail.getLotid());
									            inDetail.setLotatt1(podetail.getLotatt1());
									            inDetail.setLotatt2(podetail.getLotatt2());
									            inDetail.setLotatt3(podetail.getLotatt3());
									            inDetail.setLotatt4(podetail.getLotatt4());
									            inDetail.setLotatt5(podetail.getLotatt5());
									            inDetail.setLotatt6(podetail.getLotatt6());
									            inDetail.setLotatt7(podetail.getLotatt7());
									            inDetail.setLotatt8(podetail.getLotatt8());
									            inDetail.setLotatt9(podetail.getLotatt9());
									            inDetail.setLotatt10(podetail.getLotatt10());
									            inDetail.setLotatt11(podetail.getLotatt11());
									            inDetail.setLotatt12(podetail.getLotatt12());
									            podetail.setUseponum(podetail.getUseponum()+Double.parseDouble(msg[1]));
									            
									            lsList.add(inDetail);
									            lsList2.add(podetail);
											} 
										}
										strBackMsg = inboundbusimpl.addInboundInvoice(null, lsList,lsList2);
									}else{
										strBackMsg = "该入库单不是开单状态，不能再添加入库单明细";
									}
								}else{
									BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
					                BaseSeq  seqEn = seqDao.getSeqByType("RKD");
					                String strInvoiceNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), dao);
					                InboundInvoiceHeader inInvoice = new InboundInvoiceHeader();
									inInvoice.setInstockid(strInvoiceNo);  		//入库单编号
					                inInvoice.setWarehouseid(warehouseid);		//仓库编号
					                inInvoice.setInvoicedate(StrTools.getCurrDateTime(8));    //单据日期
					                inInvoice.setCreatetime(StrTools.getCurrDateTime(2));    //单据生成时间
					                inInvoice.setCreatemanid(strUserCode);    	//单据生成人员编号
					                inInvoice.setInstatus("0");       	//单据状态 0-新建，1-审核，2-确认 5-作废 
					                inInvoice.setIntype(intype);           //入库类型
					                inInvoice.setDepartid(departid);
					                inInvoice.setOpmode("1");         	//单据处理方式1-正常单据（默认）2-账目单据
					                inInvoice.setUploadflag("1");     	//上传标志0-已上传（默认）1-未上传
					                inInvoice.setShiftid("");      //作业班次
					                inInvoice.setOnLineType("1");     	//联机模式 1.联机（默认）2.脱机
					                inInvoice.setInpos("1");          	//入库点
					                inInvoice.setInvoicesource("4"); //单据来源
					                inInvoice.setCustomerid(header.getCustomerid());  	//客户
					                List lsList = new ArrayList<InboundInvoiceDetail>();
					                List lsList2 = new ArrayList<InboundPoDetail>(); 
					                String sqlMax = "select max(detail.instockdetailid) from InboundInvoiceDetail as detail where detail.instockid='" + strInvoiceNo + "'";
					                int sValue = SeqTool.getDetailMaxId(sqlMax, strInvoiceNo.length(),dao);
									for(int i=0;i<info.length;i++){
										String msg[]=info[i].split("\\|"); // 订单明细id|数量值|库区id
										InboundPoDetail podetail = inBoundPoBus.getInboundPoHeaders(poid,msg[0]);
										if(podetail!=null){
											InboundInvoiceDetail inDetail = new InboundInvoiceDetail();
											inDetail.setInstockdetailid(SeqTool.getDetailId(strInvoiceNo,(sValue+i+1)+"",StandardConstant.D_LEN));	//入库单详细ID
							                //inDetail.setInstockdetailid(strInvoiceNo + SeqTool.getCode((sValue+i+1), StandardConstant.D_LEN));	//入库单详细ID
								            inDetail.setInstockid(strInvoiceNo);	//入库单据编号
								            inDetail.setProductid(podetail.getProductid());		//品名规格
								            inDetail.setPackid(podetail.getPackid());				//包装
								            inDetail.setEunit(podetail.getEunit());				//单位
								            inDetail.setInvoicenum(Double.parseDouble(msg[1]));		//数量（开单）
								            inDetail.setWhAreaId(msg[2]);
								            inDetail.setLinestatus("0");        	//单据行状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
								            inDetail.setReincoiceid(podetail.getPoid());//来自订单id
								            inDetail.setReinvoicedetailid(podetail.getPodetailid());//来自订单详细id
								            inDetail.setLotid(podetail.getLotid());
								            inDetail.setLotatt1(podetail.getLotatt1());
								            inDetail.setLotatt2(podetail.getLotatt2());
								            inDetail.setLotatt3(podetail.getLotatt3());
								            inDetail.setLotatt4(podetail.getLotatt4());
								            inDetail.setLotatt5(podetail.getLotatt5());
								            inDetail.setLotatt6(podetail.getLotatt6());
								            inDetail.setLotatt7(podetail.getLotatt7());
								            inDetail.setLotatt8(podetail.getLotatt8());
								            inDetail.setLotatt9(podetail.getLotatt9());
								            inDetail.setLotatt10(podetail.getLotatt10());
								            inDetail.setLotatt11(podetail.getLotatt11());
								            inDetail.setLotatt12(podetail.getLotatt12());
								            podetail.setUseponum(podetail.getUseponum()+Double.parseDouble(msg[1]));
								            lsList.add(inDetail);
								            lsList2.add(podetail);
										} 
									}
									inboundbusimpl.addInboundInvoice(inInvoice, lsList,lsList2);
									instockid=strInvoiceNo;
								}
							}	
						}else{
							strBackMsg = "添加失败";
						}
					}
				}
				List<BaseLotViewsearch> lsLot = AbstractBus.getLotset(hsSysParam, V_RKDDETAIL);
				if(instockid!=null && instockid.length()>1){
					ls = inboundbusimpl.getInboundDetails(instockid,lsLot);
				}
				request.setAttribute("lsLot", lsLot);
				request.setAttribute("exResultList", ls);	
				request.setAttribute("instockid", instockid);
				request.setAttribute("back_msg", strBackMsg);
				request.getRequestDispatcher(strUrl).forward(request, response);*/
			} catch (Exception er) {
				Logger.error("入库单管理==>按订单添加==>选择订单:" + er.getMessage());
	            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
	            request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
			return null;
		}
	public String ricoExecPrint(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String poid = CommonTools.getStrToGbk(request.getParameter("poid"));		//单据编号
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
		if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		maxLine = 50;
		inBoundPoBus = new InboundPoBusImpl(dao);
		try{

			InboundPoHeader inboundPoHeader=inBoundPoBus.getInboundPoHeaders(poid);
			PagingTool pl = inBoundPoBus.getInboundPoDelsByPoId(poid, strUrl, maxLine);
			List lsDetail =pl.getLsResult();
            strUrl = "/standard/jsp/inbound/inothermgr/inbound_othermgr_print.jsp";
            request.setAttribute("po", inboundPoHeader);
            request.setAttribute("podetail", lsDetail); 
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("出库管理==>外来单管理==>入库单打印失败:" + er.getMessage());
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
		
		String podetailid = CommonTools.getStrToGbk(request.getParameter("podetailid"));	//单据详细编号
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
		if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		maxLine = 50;
		inBoundPoBus = new InboundPoBusImpl(dao);
		try{

			List<Object[]> ls=inBoundPoBus.getJobInfoByPodetailid(podetailid);
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

}
