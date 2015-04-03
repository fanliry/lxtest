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
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceHeader;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 描述:入库单管理
 * @author yao
 *
 */
public class OtherInBoundAction extends AbstractAction
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
		String inrequeststockid = CommonTools.getStrToGbk(request.getParameter("inrequeststockid"));		//申请单号
		String invoicedate = CommonTools.getStrToGbk(request.getParameter("invoicedate"));	//生产日期
		String instatus = CommonTools.getStrToGb2312(request.getParameter("instatus"));		//单据状态
		String productName = CommonTools.getStrToGbk(request.getParameter("productName"));		//产品名称
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));		//批号
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));//托盘条码
		String num = CommonTools.getStrToGbk(request.getParameter("num"));//数量
		String requestvoicedetailid = CommonTools.getStrToGbk(request.getParameter("requestvoicedetailid"));//详单id
		String receiverecordid = CommonTools.getStrToGbk(request.getParameter("receiverecordid"));//收货记录号
		
		String starttime = CommonTools.getStrToGbk(request.getParameter("starttime"));			//开始日期
		String endtime = CommonTools.getStrToGbk(request.getParameter("endtime"));			//结束日期
		
		String isform = CommonTools.getStrToGbk(request.getParameter("isform"));			//是否开单
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype"));		//类型（成品入库，销售退库）
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String errortextString ="";
		try{
			//每页显示行数
            int iline = 6;
            String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
            if(linerow != null && linerow.length()>0){
                iline = Integer.parseInt(linerow);
            }
			if(flag != null && flag.equals("1")){ //入库管理 新建入库单 查询
			
				errortextString = "入库管理==>退货入库管理==>新建入库单==>查询绑定记录失败:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/inbound_newin_list.jsp";
				
	            
				BindingRecord record = new BindingRecord();
				/*查询*/
				String strQueryHQL = record.getQueryHQLOther(warehouseid,instockid,isform,invoicetype,lotinfo,starttime,endtime);
                String strCountHQL = record.getQueryHQLCountOther(warehouseid,instockid,isform,invoicetype,lotinfo,starttime,endtime);
                
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List lsRecord = pt.getLsResult();//查询结果
                
				request.setAttribute("exResultList", lsRecord);
				httpsession.setAttribute("paging", pt);
				Logger.info("用户["+strUserCode+"]，入库管理==>退货入库管理==>新建入库单==>查询");
					
			}else if(flag != null && flag.equals("2")){ //入库单管理 查询入库单
			
				errortextString = "入库管理==>退货入库管理==>入库单管理==>查询入库单单失败:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/inbound_scnewrkdmgr_list.jsp";
				InboundHeader inHeader = new InboundHeader();
				/*查询*/
				String strQueryHQL = inHeader.getQueryHQLOther(warehouseid,inrequeststockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = inHeader.getQueryHQLCountOther(warehouseid,inrequeststockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("用户["+strUserCode+"]，入库管理==>退货入库管理==>入库单管理==>查询");
					
			}else if(flag != null && flag.equals("3")){//入库单管理 查询明细
			

				errortextString = "入库管理==>退货入库管理==>入库单管理==>查询明细失败:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/inbound_scnewrkdmgrde_list.jsp";
				InboundDetail detail = new InboundDetail(); 
				/*查询*/
				List ls = dao.searchEntities(detail.getQueryHQLByid(instockid));
				request.setAttribute("invoicedetail", ls);
				Logger.info("用户["+strUserCode+"]，入库管理==>退货入库管理==>入库单管理==>入库单明细查询");
			}else if(flag != null && flag.equals("4")){//入库单管理 作废单据
			

				errortextString = "入库管理==>退货入库管理==>入库单管理==>作废单据失败:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/inbound_scnewrkdmgr_list.jsp";
				
				InboundHeader inHeader = new InboundHeader();
				BindingRecord detail = new BindingRecord();
				List lsBindingRecord = detail.getQueryHQLByid(instockid,dao);
				List<BindingRecord> lsListrecord =new ArrayList<BindingRecord>();
				/*查询*/
				if(lsBindingRecord!=null && lsBindingRecord.size()>0){
					for(int i=0;i<lsBindingRecord.size();i++){
						BindingRecord record =new BindingRecord();
						record = (BindingRecord)lsBindingRecord.get(i);
						record.setStatus("1");
						lsListrecord.add(record);
					}
				}
				inHeader = inHeader.getDaoQueryHql(instockid,dao);
				if(inHeader!=null){
					inHeader.setInstatus("4");
					inHeader.updateInboundABR(inHeader,lsBindingRecord,dao);
				}
				String strQueryHQL = inHeader.getQueryHQLOther(warehouseid,inrequeststockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = inHeader.getQueryHQLCountOther(warehouseid,inrequeststockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("用户["+strUserCode+"]，入库管理==>退货入库管理==>入库单管理==>作废单"+instockid);
			}else if(flag != null && flag.equals("5")){//入库单管理 打印入库单
			

				errortextString = "入库管理==>退货入库管理==>入库单管理==>打印入库单失败:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/in_rkd_print.jsp";
				InboundHeader invoiceHeader = new InboundHeader();
				InboundDetail invoicedetail = new InboundDetail();
				/*查询*/
				InboundHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				List lsdetail = dao.searchEntities(invoicedetail.getQueryHQLByid(instockid));
				
				request.setAttribute("instockid", instockid);
				request.setAttribute("invoice", invoice);
				request.setAttribute("invoicedetail", lsdetail);
				Logger.info("用户["+strUserCode+"]，入库管理==>退货入库管理==>入库单管理==>打印入库单"+instockid);
			}else if(flag != null && flag.equals("6")){//入库申请单管理 打印收货单
				

				errortextString = "入库管理==>退货入库管理==>入库单管理==>打印收货失败:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/in_rkdbr_print.jsp";
				InboundHeader invoiceHeader = new InboundHeader();
				InboundDetail invoicedetail = new InboundDetail();
				/*查询*/
				InboundHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				List lsinvoicedetail = dao.searchEntities(invoicedetail.getQuerySHByid(instockid));
				
				request.setAttribute("instockid", instockid);
				request.setAttribute("invoice", invoice);
				request.setAttribute("lsinvoicedetail", lsinvoicedetail);
				Logger.info("用户["+strUserCode+"]，入库管理==>退货入库管理==>入库单管理==>打印入库单"+instockid+"收货记录");
			}else if(flag != null && flag.equals("7")){//入库单管理 入库单收货信息显示
				

				errortextString = "入库管理==>退货入库管理==>入库单管理==>入库单收货信息显示失败:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/inbound_receive_record_list.jsp";
				InboundHeader invoiceHeader = new InboundHeader();
				InboundDetail invoicedetail = new InboundDetail();
				/*查询*/
				InboundHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				List lsinvoicedetail = dao.searchEntities(invoicedetail.getQuerySHByid(instockid));
				
				request.setAttribute("invoice", invoice);
				request.setAttribute("lsinvoicedetail", lsinvoicedetail);
				Logger.info("用户["+strUserCode+"]，入库管理==>退货入库管理==>入库单管理==>入库单"+instockid+"收货信息显示");
			}else if(flag != null && flag.equals("8")){//入库单管理 入库单收货信息显示 取消收货
				

				errortextString = "入库管理==>退货入库管理==>入库单管理==>入库单收货信息显示==>取消收货失败:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/inbound_receive_record_list.jsp";
				InboundHeader invoiceHeader = new InboundHeader();
				InboundDetail invoicedetail = new InboundDetail();
				InoutJob job = new InoutJob();
				List records = dao.searchEntities(invoicedetail.getQueryHQLByRecordid(receiverecordid));
				List lsinvoicedetailx = dao.searchEntities(invoicedetail.getQuerySHByid(instockid));
				List jobs = dao.searchEntities(job.getQueryHQLByboundid(instockid,receiverecordid));
				InboundHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				if(records!=null && records.size()==1 && jobs!=null && jobs.size()==1){
					//更新作业详细
					InboundDetail invoicedetail1 = (InboundDetail)records.get(0);
					invoicedetail1.setStatus("1");
					invoicedetail1.setGetnum(0.0);//实收数量
					invoicedetail1.setObtainmanid("");//收货人
					invoicedetail1.setObtaintime("");//收货时间
					
					//作废作业
					InoutJob jobx = (InoutJob)jobs.get(0);
					jobx.setStatus("5");
					
					dao.update(invoicedetail1);
					dao.update(jobx);
					if(lsinvoicedetailx!=null && lsinvoicedetailx.size()==0){
						//修改单据状态
						invoice.setInstatus("1"); //修改为新建状态
						dao.update(invoice);
					}
				}
				/*查询*/
				List lsinvoicedetail = dao.searchEntities(invoicedetail.getQuerySHByid(instockid));
				
				request.setAttribute("invoice", invoice);
				request.setAttribute("lsrecord", lsinvoicedetail);
				Logger.info("用户["+strUserCode+"]，入库管理==>退货入库管理==>入库申请单管理==>入库单"+instockid+"收货信息显示==>取消收货");
			}else if(flag != null && flag.equals("9")){//入库单管理 入库单确认
				

				errortextString = "入库管理==>退货入库管理==>入库单管理==>入库单确认:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/inbound_scnewrkdmgr_list.jsp";
				InboundHeader inHeader = new InboundHeader();
				InboundHeader invoice = inHeader.getDaoQueryHql(instockid,dao);
				if(invoice!=null){
					invoice.setInstatus("3");
					dao.update(invoice);
				}
				/*查询*/
				
				String strQueryHQL = inHeader.getQueryHQLOther(warehouseid,inrequeststockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = inHeader.getQueryHQLCountOther(warehouseid,inrequeststockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				
				Logger.info("用户["+strUserCode+"]，入库管理==>退货入库管理==>入库单"+instockid+"确认");
			}else if(flag != null && flag.equals("RF1")){//RF收货 保存
				errortextString = "RF==>收货==>收货失败:";
				strUrl = "/rf/fyhz/receipt.jsp";
				InboundDetail invoicedetail = new InboundDetail();
				InboundHeader invoice = new InboundHeader().getDaoQueryHql(instockid,dao);
				invoicedetail = invoicedetail.getDetailByidtray(instockid,traycode,dao);
				invoicedetail.setGetnum(Double.parseDouble(num));//实收数量
				invoicedetail.setStatus("2");
				invoice.setInstatus("2");
				
				//入库作业号
	            BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
	            BaseSeq  seqEn = seqDao.getSeqByType("RKZY");
	            String strNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),dao); 
				InoutJob job = new InoutJob();
				InoutJobdetail jobdetail = new InoutJobdetail();
				job.setJobid(strNo);
				job.setJobtype(invoice.getInvoicetypeid()); 
				job.setOnLineType("1");//联机操作
				job.setIsaccount("Y");//记账
				job.setStatus("2"); //待机状态
				job.setPriority(1);//优先级
				job.setCreatetime(StrTools.getCurrDateTime(2));
				job.setInOutType("1");//入库类型
				job.setTraycode(invoicedetail.getTraycode());
				job.setRoute("1");//路线 直入
				job.setOpManId(strUserCode);
				job.setInvoicetype("1");//按入库收货单来生成的作业
				job.setWarehouseid(invoice.getWarehouseid());
				job.setBoundstockid(invoice.getInstockid());
				job.setBoundstockdetailid(invoicedetail.getInstockdetailid());
				job.setBoundrequeststockid(invoice.getInrequestid());
				jobdetail.setJobid(strNo);
				jobdetail.setProductid(invoicedetail.getProductid());
				jobdetail.setLotid(invoicedetail.getLotid());
				jobdetail.setLotinfo(invoicedetail.getLotinfo());
				jobdetail.setPunit(invoicedetail.getPunit());
				jobdetail.setJobnum(invoicedetail.getGetnum());
				jobdetail.setAssignnum(invoicedetail.getGetnum());
				jobdetail.setPrintdate(invoicedetail.getPrintdate());
				jobdetail.setIsplit("1");//整托
				jobdetail.setIsinvoice("Y");//设置是否已生成单据 Y是  N否
				
				invoice.addInBoundJob(invoice,invoicedetail ,job,jobdetail,dao);
				request.setAttribute("instockid", instockid);
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			Logger.error(errortextString + er.getMessage());
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库ID
		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));		//单据号
		String invoicedate = CommonTools.getStrToGbk(request.getParameter("invoicedate"));	//生产日期
		String instatus = CommonTools.getStrToGb2312(request.getParameter("instatus"));		//单据状态
		String productName = CommonTools.getStrToGbk(request.getParameter("productName"));		//产品名称
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));		//批号
		String starttime = CommonTools.getStrToGbk(request.getParameter("starttime"));			//开始日期
		String endtime = CommonTools.getStrToGbk(request.getParameter("endtime"));			//结束日期
		String isform = CommonTools.getStrToGbk(request.getParameter("isform"));			//是否开单
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype"));		//类型（成品入库，销售退库）
		String condition = CommonTools.getStrToGbk(request.getParameter("condition"));		   //绑定记录id系列
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		InboundHeader Header=null;
		InboundDetail  Detail=null;
		
		try{
			String strBackMsg = "Y";
			strUrl = "/standard/jsp/inbound/othernew/inbound_scnewsqd_list.jsp";
			int rowLength = StrTools.StrTimes(condition,",");
			 //入库申请单号
            BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
            BaseSeq  seqEn = seqDao.getSeqByType("RKD");
            String strNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),dao); 
            String inrequestid = "";
            int x=0,y=0;
            List<InboundDetail> lsInboundDetail = new ArrayList<InboundDetail>();
			List<BindingRecord> lsBindingRecord = new ArrayList<BindingRecord>();
			for(int i=0; i< rowLength; i++)
			{
				
				//绑定记录id
				String bandrecordid = StrTools.GetStrFlag(condition,",",i);
				BindingRecord record = new BindingRecord();
				/*查询*/
				List lsRecord = dao.searchEntities(record.getQueryHQLByRecordid(bandrecordid));
				if(lsRecord!=null&&lsRecord.size()==1){
					record = (BindingRecord)lsRecord.get(0);
					if(record!=null && record.getStatus().equals("1")){ //只有未建单的绑定记录才可应生成入库单
						record.setStatus("2");
						inrequestid = record.getInstockid();
						Detail = new InboundDetail();
						Detail.setInstockid(strNo);
						Detail.setBandrecordid(record.getBandrecordid());
						Detail.setInnum(record.getNum());
						Detail.setTraycode(record.getTraycode());
						Detail.setLotid(record.getLotid());
						Detail.setLotinfo(record.getLotinfo());
						Detail.setProductStatus(record.getProductStatus());
						Detail.setStatus("1");
						Detail.setPunit(record.getPunit());
						Detail.setPrintdate(record.getPrintdate());
						Detail.setProductid(record.getProductid());
						lsInboundDetail.add(Detail);
						lsBindingRecord.add(record);
						y++;
					}else{
						x++;
					}
				}
			}
			if(x!=0){
				strBackMsg = "有"+x+"个绑定记录没有生成相应的入库单";
			}
			if(y!=0 && lsInboundDetail!=null && lsInboundDetail.size()>0  && lsBindingRecord!=null && lsBindingRecord.size()>0 ){ //如果所选的绑定记录有未生成单据 则需要建立入库 否则不建立入库单
				//入库单
				Header = new InboundHeader();
				Header.setInstockid(strNo);  		    //入库单id
				Header.setInrequestid(inrequestid);       //申请单号
				Header.setInvoicetypeid(invoicetype);	//申请单类型id(成品入库，销售退库)
				Header.setInvoicedate(StrTools.getCurrDateTime(8));  	//建单时间
				Header.setCreatetime(StrTools.getCurrDateTime(2));	//单据生成时间
				Header.setInstatus("1");  		//入库单据状态id 1-新建，2-确定， 3-作废 
				Header.setCreatemanid(strUserCode);		//单据生成人员编号
				Header.setWarehouseid(warehouseid);  	//收货仓库
	            Header.addInBound(lsInboundDetail, lsBindingRecord, Header, dao);
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
