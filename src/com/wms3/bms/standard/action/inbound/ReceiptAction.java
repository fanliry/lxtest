package com.wms3.bms.standard.action.inbound;

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
import com.wms3.bms.standard.business.inbound.IInBoundReceiptBus;
import com.wms3.bms.standard.business.inbound.IReceiptBus;
import com.wms3.bms.standard.business.inbound.impl.InBoundReceiptBusImpl;
import com.wms3.bms.standard.business.inbound.impl.ReceiptBusImpl;
import com.wms3.bms.standard.entity.inbound.InboundReceiptHeader;
import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;

/**
 * 
 * 描述: 收货
 * @author hug 2012-8-23
 * @since WMS 3.0
 */
public class ReceiptAction  extends AbstractAction{
    protected IReceiptBus receiptBus;       //收货业务
    private IInBoundReceiptBus inReceiptBus;//收货单业务
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String flag = CommonTools.getStrToGb2312(request.getParameter("flag"));
        
        //收货单ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //收货单详细ID
        String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));
        
        //每页显示行数
        int iline = 5;
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
        if(linerow != null && linerow.length()>0){
            iline = Integer.parseInt(linerow);
        }
        receiptBus = new ReceiptBusImpl(dao);
        inReceiptBus = new InBoundReceiptBusImpl(dao);
        try
        {
            if(flag != null && flag.trim().equals("1")){//收货->查询单据详细
                
                String strQueryHQL = receiptBus.getInReceiptDetailQuerySQL(invoiceid);
                String strCountHQL = receiptBus.getInReceiptDetailCountSQL(invoiceid);   
                
                //跳转页面
                strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_mgr_detail.jsp";   
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPage1", pt);
                    
            }else if(flag != null && flag.trim().equals("2")){//收货->根据单据详细查询收货交易信息
                
                String strQueryHQL = receiptBus.getReceiptTaskQuerySQL(invoicedetailid);
                String strCountHQL = receiptBus.getReceiptTaskCountSQL(invoicedetailid);   
                
                //跳转页面
                strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_mgr_task.jsp";   
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 5);
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPage2", pt); 
            }else if(flag != null && flag.trim().equals("3"))//收货->打印收货任务
            {
                //跳转页面
                strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_task_print.jsp";
                //收货单
                InboundReceiptHeader inBound = inReceiptBus.getInBoundReceiptInvoiceById(invoiceid);
                //收货任务
                List<InboundReceiptTransaction> lsTask = receiptBus.getReceiptTransactionToIvoiceId(invoiceid);
                
                request.setAttribute("invoice", inBound);
                request.setAttribute("tasks", lsTask);
            }else if(flag != null && flag.trim().equals("4"))//上架->根据收货单查询收货记录
            {
                //跳转页面
                strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_tasklist.jsp";
                //收货任务列表
                List<InboundReceiptTransaction> lsTask = receiptBus.getReceiptTransactionToIvoiceId(invoiceid);
                request.setAttribute("tasks", lsTask);
            }else if(flag != null && flag.trim().equals("5"))//收货->取消收货->根据收货单查询收货记录
            {
                //跳转页面
                strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_cancel_list.jsp";
                
                String strQueryHQL = receiptBus.getReceiptTransQuerySQL(invoiceid);
                String strCountHQL = receiptBus.getReceiptTransCountSQL(invoiceid); 
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 10);
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPageCancel", pt);      
            }
            
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("入库管理==>收货查询出错:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    
    }
    /**
     * 功能:收货
     * @author hug 2012-8-28 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String receipt(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");           //当前用户  
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));  //收货单ID   
        String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));//收货单详细ID
        String strLotid = CommonTools.getStrToGbk(request.getParameter("lotid"));       //批次类型ID
        String strLotatt1 = CommonTools.getStrToGbk(request.getParameter("lotatt1"));   //批次属性1
        String strLotatt2 = CommonTools.getStrToGbk(request.getParameter("lotatt2"));   //批次属性2
        String strLotatt3 = CommonTools.getStrToGbk(request.getParameter("lotatt3"));   //批次属性3
        String strLotatt4 = CommonTools.getStrToGbk(request.getParameter("lotatt4"));   //批次属性4
        String strLotatt5 = CommonTools.getStrToGbk(request.getParameter("lotatt5"));   //批次属性5
        String strLotatt6 = CommonTools.getStrToGbk(request.getParameter("lotatt6"));   //批次属性6
        String strLotatt7 = CommonTools.getStrToGbk(request.getParameter("lotatt7"));   //批次属性7
        String strLotatt8 = CommonTools.getStrToGbk(request.getParameter("lotatt8"));   //批次属性8
        String strLotatt9 = CommonTools.getStrToGbk(request.getParameter("lotatt9"));   //批次属性9
        String strLotatt10 = CommonTools.getStrToGbk(request.getParameter("lotatt10")); //批次属性10
        String strLotatt11 = CommonTools.getStrToGbk(request.getParameter("lotatt11")); //批次属性11
        String strLotatt12 = CommonTools.getStrToGbk(request.getParameter("lotatt12")); //批次属性12
        
        String num = CommonTools.getStrToGbk(request.getParameter("num"));              //收货数量
        String weight = CommonTools.getStrToGbk(request.getParameter("weight"));        //收货毛重
        String netweight = CommonTools.getStrToGbk(request.getParameter("netweight"));  //收货净重
        String volume = CommonTools.getStrToGbk(request.getParameter("volume"));        //收货体积
        
        String rejectednum = CommonTools.getStrToGbk(request.getParameter("rejectednum"));  //拒收数量
        String rejectcode = CommonTools.getStrToGbk(request.getParameter("rejectcode"));    //拒收代码
        String rejectreason = CommonTools.getStrToGbk(request.getParameter("rejectreason"));//拒收原因
        
        String holdnum = CommonTools.getStrToGbk(request.getParameter("holdnum"));      //冻结数量
        String holdcode = CommonTools.getStrToGbk(request.getParameter("holdcode"));    //冻结代码
        String holdreason = CommonTools.getStrToGbk(request.getParameter("holdreason"));//冻结原因
        
        //每页显示行数
        int iline = 5;
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
        if(linerow != null && linerow.length()>0){
            iline = Integer.parseInt(linerow);
        }
        receiptBus = new ReceiptBusImpl(dao);
        String strMsg = "";
        try
        {
                
            //执行收货
           strMsg = receiptBus.excuteReceipt(invoiceid, invoicedetailid, strLotid, 
                                strLotatt1, strLotatt2, strLotatt3, strLotatt4, strLotatt5, strLotatt6, 
                                strLotatt7, strLotatt8, strLotatt9, strLotatt10, strLotatt11, strLotatt12, 
                                Double.parseDouble(num), Double.parseDouble(weight), Double.parseDouble(netweight), Double.parseDouble(volume),
                                Double.parseDouble(rejectednum), rejectcode, rejectreason,
                                Double.parseDouble(holdnum), holdcode, holdreason, strUserCode);
                
          String strQueryHQL = receiptBus.getInReceiptDetailQuerySQL(invoiceid);
          String strCountHQL = receiptBus.getInReceiptDetailCountSQL(invoiceid);   
                
          //跳转页面
          strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_mgr_detail.jsp";   
          PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
          List ls = pt.getLsResult();//查询结果
          request.setAttribute("exResultList", ls);
          request.setAttribute("pagingTool", pt);
          httpsession.setAttribute("inboundPage2", pt);
          request.setAttribute("back_msg", strMsg);
          request.getRequestDispatcher(strUrl).forward(request, response);      
        }catch(Exception er)
        {
            Logger.error("入库管理==>执行收货出错:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能:取消收货
     * @author hug 2012-8-28 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String cancelreceipt(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
           
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));  //收货单ID   
        //收货记录
        String transid = CommonTools.getStrToGbk(request.getParameter("transid"));//收货记录ID
       
        receiptBus = new ReceiptBusImpl(dao);
        String strMsg = "";
        try
        {             
            //执行收货
           strMsg = receiptBus.cancelReceipt(invoiceid, transid);             
          //跳转页面
          strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_cancel_list.jsp";
          
          String strQueryHQL = receiptBus.getReceiptTransQuerySQL(invoiceid);
          String strCountHQL = receiptBus.getReceiptTransCountSQL(invoiceid); 
          PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 10);
          List ls = pt.getLsResult();//查询结果
          request.setAttribute("exResultList", ls);
          request.setAttribute("pagingTool", pt);
          httpsession.setAttribute("inboundPageCancel", pt); 
          
          request.setAttribute("back_msg", strMsg);
          request.getRequestDispatcher(strUrl).forward(request, response);      
        }catch(Exception er)
        {
            Logger.error("入库管理==>取消收货出错:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }

}
