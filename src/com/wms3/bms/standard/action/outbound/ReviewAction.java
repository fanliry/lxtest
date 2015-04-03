package com.wms3.bms.standard.action.outbound;

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
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.IReviewBus;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;
import com.wms3.bms.standard.business.outbound.impl.ReviewBusImpl;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;

/**
 * 描述: 复核操作
 * @author hug 2012-9-18
 * @since WMS 3.0
 */
public class ReviewAction extends AbstractAction{
    protected IOutBoundBus outboundBus;
    protected IReviewBus reviewBus;
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        reviewBus = new ReviewBusImpl(dao);
        outboundBus = new OutBoundBusImpl(dao);
        try
        {
            String flag = CommonTools.getStrToGb2312(request.getParameter("flag"));
            
            String outstatus = CommonTools.getStrToGbk(request.getParameter("outstatus"));      //单据状态
            String outtype = CommonTools.getStrToGbk(request.getParameter("outtype"));          //出库类型
            String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));  //仓库ID
            String shiftid = CommonTools.getStrToGbk(request.getParameter("shiftid"));          //作业班次
            String screatedate = CommonTools.getStrToGbk(request.getParameter("start_time"));   //单据开始生成时间
            String ecreatedate = CommonTools.getStrToGbk(request.getParameter("end_time"));     //单据结束生成时间
            String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));    //客户
            String outno = CommonTools.getStrToGbk(request.getParameter("outno"));              //出库单号
            
            //每页显示行数
            int iline = 5;
            String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
            if(linerow != null && linerow.length()>0){
                iline = Integer.parseInt(linerow);
            }
            
            //出库单ID
            String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
            //出库单详细ID
            String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));
            
            if(flag != null && flag.trim().equals("1")){//复核->出库单查询
                
                String strQueryHQL = outboundBus.getOutBoundQuerySQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                String strCountHQL = outboundBus.getOutBoundCountSQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                
                //跳转页面
                strUrl = "/standard/jsp/outbound/review/outbound_review_list.jsp";    
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("paging", pt);
                
            }else if(flag != null && flag.trim().equals("2")){ //复核->根据出库单ID获得出库单据详细列表
                //出库单详细
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
                
                strUrl = "/standard/jsp/outbound/review/outbound_review_dlist.jsp";
                request.setAttribute("invoicedetail", lsDetail);    
            }else if(flag != null && flag.trim().equals("3")){//复核->按单据复核->根据出库单ID获得出库单详细
                //出库单详细
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
                
                strUrl = "/standard/jsp/outbound/review/outbound_review_invoice_list.jsp";
                request.setAttribute("invoicedetail", lsDetail);        
            }else if(flag != null && flag.trim().equals("4")){//复核->按明细复核->查询已完成的作业
                
                String strQueryHQL = reviewBus.getJobDetailQuerySQL(invoiceid);
                String strCountHQL = reviewBus.getJobDetailCountSQL(invoiceid);
                
                //跳转页面
                strUrl = "/standard/jsp/outbound/review/outbound_review_detail_job.jsp";    
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 10);
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("outboundPagejob", pt);
            }else if(flag != null && flag.trim().equals("5"))//复核->按明细复核->查询已的记录
            {
                String strQueryHQL = reviewBus.getReviewQuerySQL(invoiceid);
                String strCountHQL = reviewBus.getReviewCountSQL(invoiceid);
                
                //跳转页面
                strUrl = "/standard/jsp/outbound/review/outbound_review_detail_crosscheck.jsp";
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 8);
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("outboundPagereview", pt);
            }
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("出库管理==>复核==>出库查询失败:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /** 
     * 功能: 按单据复核
     * @author hug 2012-10-19 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String invoiceReview(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        HttpSession httpsession = request.getSession(false);
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //标识 1:确认复核 2：取消复核
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        //当前用户
        String strUserCode = (String)httpsession.getAttribute("userCode");
        
        //出库单ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //出库单明细ID
        String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));
        //复核的数量
        String renum = CommonTools.getStrToGbk(request.getParameter("renum"));
        
        String strBackMsg = "Y";
        reviewBus = new ReviewBusImpl(dao);
        outboundBus = new OutBoundBusImpl(dao);
        try{
            if(flag != null && flag.trim().equals("1")){//确认复核(按单据复核)
                strBackMsg = reviewBus.invoiceReview(invoiceid, invoicedetailid, Double.parseDouble(renum), strUserCode);     
            }else if(flag != null && flag.trim().equals("2")){//取消复核
                strBackMsg = reviewBus.deleteInvoiceReview(invoicedetailid, strUserCode);
            }
            //跳转页面
            strUrl = "/standard/jsp/outbound/review/outbound_review_invoice_list.jsp";
            List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
            request.setAttribute("invoicedetail", lsDetail);    
            //返回值
            request.setAttribute("back_msg", strBackMsg); 
            
            request.getRequestDispatcher(strUrl).forward(request, response);

        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库管理==>按单据复核失败:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]：错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /** 
     * 功能: 按明细复核
     * @author hug 2012-10-19 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String detailReview(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        HttpSession httpsession = request.getSession(false);
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //标识 1:确认复核 2：取消复核
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        //当前用户
        String strUserCode = (String)httpsession.getAttribute("userCode");
        //出库单ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //出库单明细ID
        String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));
        //作业ID
        String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));
        //作业详细ID
        String jobdetailid = CommonTools.getStrToGbk(request.getParameter("jobdetailid"));
        //复核的数量
        String renum = CommonTools.getStrToGbk(request.getParameter("renum"));
        
        //复核ID
        String strCrosscheckID = CommonTools.getStrToGbk(request.getParameter("crossid"));
        String strBackMsg = "Y";
        reviewBus = new ReviewBusImpl(dao);
        try{
            if(flag != null && flag.trim().equals("1")){//确认复核(按明细复核)
                strBackMsg = reviewBus.detailReview(invoiceid, invoicedetailid, jobid, jobdetailid, Double.parseDouble(renum), strUserCode); 
            }else if(flag != null && flag.trim().equals("2")){//取消复核
                strBackMsg = reviewBus.deleteDetailReview(strCrosscheckID, strUserCode);
            }

            String strQueryHQL = reviewBus.getJobDetailQuerySQL(invoiceid);
            String strCountHQL = reviewBus.getJobDetailCountSQL(invoiceid);
            
            //跳转页面
            strUrl = "/standard/jsp/outbound/review/outbound_review_detail_job.jsp";    
            PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 10);
            List ls = pt.getLsResult();//查询结果
            //返回值
            request.setAttribute("back_msg", strBackMsg);
            request.setAttribute("exResultList", ls);
            request.setAttribute("pagingTool", pt);
            httpsession.setAttribute("outboundPagejob", pt);
            
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库管理==>按明细复核失败:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    
  
 
}
