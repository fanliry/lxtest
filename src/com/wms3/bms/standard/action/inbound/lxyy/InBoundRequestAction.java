package com.wms3.bms.standard.action.inbound.lxyy;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.inbound.IInBoundBus;
import com.wms3.bms.standard.business.inbound.impl.InBoundBusImpl;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inbound.InboundInvoiceDetail;
import com.wms3.bms.standard.entity.inbound.InboundInvoiceHeader;
import com.wms3.bms.standard.entity.inbound.lxyy.BindingRecord;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceHeader;

/**
 * 描述:入库申请单管理
 * @author yao
 *
 */
public class InBoundRequestAction extends AbstractAction
{
	protected IInBoundBus inBoundBus;
	protected List<BaseLotSet> lsLot;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库ID
		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));		//单据号
		String invoicedate = CommonTools.getStrToGbk(request.getParameter("invoicedate"));			//生产日期
		String instatus = CommonTools.getStrToGb2312(request.getParameter("instatus"));		//单据状态
		String productName = CommonTools.getStrToGbk(request.getParameter("productName"));		//产品名称
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));		//批号
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));//托盘条码
		String num = CommonTools.getStrToGbk(request.getParameter("num"));//数量
		String requestvoicedetailid = CommonTools.getStrToGbk(request.getParameter("requestvoicedetailid"));//详单id
		String bandrecordid = CommonTools.getStrToGbk(request.getParameter("bandrecordid"));//绑定记录号
		
		String starttime = CommonTools.getStrToGbk(request.getParameter("starttime"));			//开始日期
		String endtime = CommonTools.getStrToGbk(request.getParameter("endtime"));			//结束日期
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype"));		//类型（成品入库，销售退库）
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String errortextString ="";
		String back_msg = "Y";
		try{
			//每页显示行数
            int iline = 6;
            String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
            if(linerow != null && linerow.length()>0){
                iline = Integer.parseInt(linerow);
            }
			if(flag != null && flag.equals("1")){ //入库申请单管理 查询入库申请单
				
				back_msg = "";
				errortextString = "入库管理==>生产入库管理==>入库申请单管理==>入库申请单失败:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_scnewsqdmgr_list.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				/*查询*/
				String strQueryHQL = invoiceHeader.getQueryHQL(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = invoiceHeader.getQueryHQLCount(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
                
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("用户["+strUserCode+"]，入库管理==>生产入库管理==>入库申请单管理==>查询");
					
			}else if(flag != null && flag.equals("2")){//入库申请单管理 查询明细
			
				back_msg = "";
				errortextString = "入库管理==>生产入库管理==>入库申请单管理==>查询明细失败:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_scnewsqdmgrde_list.jsp";
				InboundRequestInvoiceDetail invoicedetail = new InboundRequestInvoiceDetail();
				/*查询*/
				List ls = dao.searchEntities(invoicedetail.getQueryHQLByid(instockid));
				request.setAttribute("invoicedetail", ls);
				Logger.info("用户["+strUserCode+"]，入库管理==>生产入库管理==>入库申请单管理==>入库申请单明细查询");
			}else if(flag != null && flag.equals("3")){//入库申请单管理 审核
			
				errortextString = "入库管理==>生产入库管理==>入库申请单管理==>审核失败:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_scnewsqdmgr_list.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				/*查询*/
				dao.updateSql(invoiceHeader.getUpdateAuditsql(instockid,strUserCode));
				
				String strQueryHQL = invoiceHeader.getQueryHQL(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = invoiceHeader.getQueryHQLCount(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
                
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("用户["+strUserCode+"]，入库管理==>生产入库管理==>入库申请单管理==>审核单"+instockid);
			}else if(flag != null && flag.equals("4")){//入库申请单管理 取消审核
			

				errortextString = "入库管理==>生产入库管理==>入库申请单管理==>取消审核失败:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_scnewsqdmgr_list.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				/*查询*/
				dao.updateSql(invoiceHeader.getUpdateCancelAuditsql(instockid));
				String strQueryHQL = invoiceHeader.getQueryHQL(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = invoiceHeader.getQueryHQLCount(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
                
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("用户["+strUserCode+"]，入库管理==>生产入库管理==>入库申请单管理==>取消审核单"+instockid);
			}else if(flag != null && flag.equals("5")){//入库申请单管理 作废单据
			

				errortextString = "入库管理==>生产入库管理==>入库申请单管理==>作废单据失败:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_scnewsqdmgr_list.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				/*查询*/
				dao.updateSql(invoiceHeader.getUpdateDeleteAuditsql(instockid));
				String strQueryHQL = invoiceHeader.getQueryHQL(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = invoiceHeader.getQueryHQLCount(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
                
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("用户["+strUserCode+"]，入库管理==>生产入库管理==>入库申请单管理==>作废单"+instockid);
			}else if(flag != null && flag.equals("6")){//入库申请单管理 关闭申请单
			
				
				errortextString = "入库管理==>生产入库管理==>入库申请单管理==>关闭申请单失败:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_scnewsqdmgr_list.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				/*查询*/
				dao.updateSql(invoiceHeader.getUpdateCloseAuditsql(instockid));
				String strQueryHQL = invoiceHeader.getQueryHQL(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = invoiceHeader.getQueryHQLCount(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
                
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("用户["+strUserCode+"]，入库管理==>生产入库管理==>入库申请单管理==>关闭申请单"+instockid);
			}else if(flag != null && flag.equals("7")){//入库申请单管理 打印申请单
				back_msg = "";

				errortextString = "入库管理==>生产入库管理==>入库申请单管理==>打印申请单失败:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_rkd_print.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				InboundRequestInvoiceDetail invoicedetail = new InboundRequestInvoiceDetail();
				/*查询*/
				InboundRequestInvoiceHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				List lsdetail = dao.searchEntities(invoicedetail.getQueryHQLByid(instockid));
				
				request.setAttribute("instockid", instockid);
				request.setAttribute("invoice", invoice);
				request.setAttribute("invoicedetail", lsdetail);
				Logger.info("用户["+strUserCode+"]，入库管理==>生产入库管理==>入库申请单管理==>打印申请单"+instockid);
			}else if(flag != null && flag.equals("8")){//入库申请单管理 打印绑定记录
				
				back_msg = "";
				errortextString = "入库管理==>生产入库管理==>入库申请单管理==>打印申请单绑定记录失败:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_rkdbr_print.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				BindingRecord record = new BindingRecord();
				/*查询*/
				InboundRequestInvoiceHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				List lsRecord = dao.searchEntities(record.getQueryHQLByid(instockid));
				
				request.setAttribute("instockid", instockid);
				request.setAttribute("invoice", invoice);
				request.setAttribute("lsrecord", lsRecord);
				Logger.info("用户["+strUserCode+"]，入库管理==>生产入库管理==>入库申请单管理==>打印申请单"+instockid+"绑定记录");
			}else if(flag != null && flag.equals("9")){//入库申请单管理 入库申请单绑定信息显示
				back_msg = "";

				errortextString = "入库管理==>生产入库管理==>入库申请单管理==>入库申请单绑定信息显示失败:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_bindrecord_list.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				BindingRecord record = new BindingRecord();
				/*查询*/
				InboundRequestInvoiceHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				List lsRecord = dao.searchEntities(record.getQueryHQLByid(instockid));
				
				request.setAttribute("invoice", invoice);
				request.setAttribute("lsrecord", lsRecord);
				Logger.info("用户["+strUserCode+"]，入库管理==>生产入库管理==>入库申请单管理==>入库申请单"+instockid+"绑定信息显示");
			}else if(flag != null && flag.equals("10")){//入库申请单管理 入库申请单绑定信息显示 取消绑定记录

				errortextString = "入库管理==>生产入库管理==>入库申请单管理==>入库申请单绑定信息显示==>取消绑定记录失败:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_bindrecord_list.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				BindingRecord record = new BindingRecord();
				List records = dao.searchEntities(record.getQueryHQLByRecordid(bandrecordid));
				if(records!=null && records.size()==1){
					record = (BindingRecord)records.get(0);
					InboundRequestInvoiceDetail detail = new InboundRequestInvoiceDetail();
					detail = detail.getDetailByid(instockid,record.getInstockdetailid(),dao);
					if(detail!=null){
						double numx = detail.getBindingnum() - record.getNum();
						if(numx<=0){
							numx = 0;
						}
						detail.setBindingnum(numx);
						record.UpdateRequestDInBound(detail,record,dao);
					}else{
						back_msg = "没能找到相应的单据明细";
					}
					
				}
				/*查询*/
				InboundRequestInvoiceHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				List lsRecord = dao.searchEntities(record.getQueryHQLByid(instockid));
				
				request.setAttribute("invoice", invoice);
				request.setAttribute("lsrecord", lsRecord);
				Logger.info("用户["+strUserCode+"]，入库管理==>生产入库管理==>入库申请单管理==>入库申请单"+instockid+"绑定信息显示==>取消绑定记录");
			}else if(flag != null && flag.equals("RF1")){//RF绑定托盘 保存
			
				errortextString = "RF绑定托盘=>绑定失败:";
				strUrl = "/rf/lxyy/bindtray.jsp";
				
				/*查询*/
				InboundRequestInvoiceHeader invoice = new InboundRequestInvoiceHeader().getDaoQueryHql(instockid,dao);
				InboundRequestInvoiceDetail invoicedetail = new InboundRequestInvoiceDetail().getDetailByid(instockid, requestvoicedetailid, dao);
				if(invoice!=null){
					//绑定
		            BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
		            BaseSeq  seqEn = seqDao.getSeqByType("BDD");
		            String strRecordNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),dao); 
		            
					BindingRecord record = new BindingRecord();
					double num1 = invoicedetail.getBindingnum();
					double num2 = Double.parseDouble(num);
					
					record.setBandrecordid(strRecordNo);
					record.setInstockid(instockid);
					record.setInstockdetailid(requestvoicedetailid);
					record.setProductid(invoicedetail.getProductid());
					record.setTraycode(traycode);
					record.setBandrecordtype(invoice.getInvoicetypeid());
					record.setPrintdate(invoicedetail.getPrintdate());
					record.setProductStatus(invoicedetail.getProductStatus());
					record.setLotid(invoicedetail.getLotid());
					record.setLotinfo(invoicedetail.getLotinfo());
					record.setNum(num2);
					record.setStatus("1");
					record.setPunit(invoicedetail.getPunit());
					record.setBandmanid(strUserCode);
					record.setCreatetime(StrTools.getCurrDateTime(2));
					
					invoicedetail.setBindingnum(num1+num2);
					invoice.setInstatus("3");
					
					dao.save(record);
					dao.update(invoicedetail);
					dao.update(invoice);
				}
				request.setAttribute("invoice", invoice);
				request.setAttribute("invoicedetail", invoicedetail);
				Logger.info("用户["+strUserCode+"]，RF绑定托盘=> 申请单号为："+instockid+"，托盘条码为："+traycode);
			}
			request.setAttribute("back_msg", back_msg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			Logger.error(errortextString + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	
	/**
	 * 功能:生成入库申请单
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
		String warehouseid = CommonTools.getStrToGb2312(request.getParameter("warehouseid"));	//仓库id
		String invoicetype = CommonTools.getStrToGb2312(request.getParameter("invoicetype"));	//入库类型
		String invoicedate = CommonTools.getStrToGb2312(request.getParameter("invoicedate"));	//打印日期
		String department = CommonTools.getStrToGb2312(request.getParameter("department"));	    //生产部门
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));	        //备注
		String detail = CommonTools.getStrToGb2312(request.getParameter("detail"));	//明细
		String strUserCode = (String)httpsession.getAttribute("userCode");
		InboundRequestInvoiceHeader RequestHeader=null;
		InboundRequestInvoiceDetail  RequestDetail=null;
		
		try{
			String strBackMsg = "Y";
			strUrl = "/standard/jsp/inbound/scnew/inbound_scnewsqd_list.jsp";
			int rowLength = StrTools.StrTimes(detail,"|");
			 //入库申请单号
            BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
            BaseSeq  seqEn = seqDao.getSeqByType("SQD");
            //String strRequestNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),dao);
            String strRequestNo = SeqTool.getID(seqEn.getSeqType(), dao);
            
            List<InboundRequestInvoiceDetail> lsRequestDetail = new ArrayList<InboundRequestInvoiceDetail>();
			for(int i=0; i< rowLength; i++)
			{
				
				//产品id   产品名     单位     生产日期    计划数量   批号类型  批号信息
				String colums[] = StrTools.GetStrFlag(detail,"|",i).split(",");
				RequestDetail = new InboundRequestInvoiceDetail();
				RequestDetail.setInstockid(strRequestNo);  		//申请单id
				RequestDetail.setProductid(colums[0]);  		//品名id
				RequestDetail.setPrintdate(colums[3]);  	    //生产日期
				RequestDetail.setPlannum(Double.parseDouble(colums[4]));  	//计划数量
				RequestDetail.setLotid(colums[5]);  		   //批号类型
				RequestDetail.setLotinfo(colums[6]);  		   //具体批号
				RequestDetail.setProductStatus(colums[7]);		//物品状态
				RequestDetail.setRemark("");  		           //备注
				RequestDetail.setPunit(colums[2]); //单位
				lsRequestDetail.add(RequestDetail);
				
			}
			if(lsRequestDetail!=null && lsRequestDetail.size()>0){
				//入库单
				RequestHeader = new InboundRequestInvoiceHeader();
	            RequestHeader.setInstockid(strRequestNo);  		//申请单id
	            RequestHeader.setInvoicetypeid(invoicetype);	//申请单类型id(成品入库，销售退库)
	            RequestHeader.setInvoicedate(invoicedate);  	    //建单时间
	            RequestHeader.setCreatetime(StrTools.getCurrDateTime(2));		//单据生成时间
	            RequestHeader.setInstatus("1");  		//申请单据状态id 1-新建，2-审核，3-部分绑定，4-关闭 5-作废 
	            RequestHeader.setCreatemanid(strUserCode);		//单据生成人员编号
	            RequestHeader.setDepartid(department);  		//生产车间
	            RequestHeader.setAuditmanid("");		//审核人Id
	            RequestHeader.setWarehouseid(warehouseid);  	//收货仓库
	            RequestHeader.setAuditdate("");		    //审核时间
	            RequestHeader.setConfirmdate("");  		//关闭确认时间
	            RequestHeader.setRemark(remark);		//备注
	            RequestHeader.addRequestInBound(lsRequestDetail, RequestHeader, dao);
			}else{
				strBackMsg ="没有申请单明细 没有建入库申请单";
			}
            
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
					
		}catch(Exception er){
		
			Logger.error("用户["+strUserCode+"]，入库管理==>生成入库单据==>生成入库单失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
