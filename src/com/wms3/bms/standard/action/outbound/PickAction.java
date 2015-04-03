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
import com.wms3.bms.standard.business.outbound.IPickBus;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;
import com.wms3.bms.standard.business.outbound.impl.PickBusImpl;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;

/**
 * 描述: 拣货操作
 * @author hug 2012-9-18
 * @since WMS 3.0
 */
public class PickAction extends AbstractAction{
    protected IOutBoundBus outboundBus;
    protected IPickBus pickBus;
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        outboundBus = new OutBoundBusImpl(dao);
        pickBus = new PickBusImpl(dao);
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
            
            if(flag != null && flag.trim().equals("1")){//拣货->出库单查询
                
                String strQueryHQL = outboundBus.getOutBoundQuerySQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                String strCountHQL = outboundBus.getOutBoundCountSQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                
                //跳转页面
                strUrl = "/standard/jsp/outbound/pick/outbound_pick_list.jsp";    
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("paging", pt);
                
            }else if(flag != null && flag.trim().equals("2")){ //拣货->根据出库单ID获得出库单据详细列表
                //出库单详细
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
                
                strUrl = "/standard/jsp/outbound/pick/outbound_pick_dlist.jsp";
                request.setAttribute("invoicedetail", lsDetail);    
            }else if(flag != null && flag.trim().equals("3")){//拣货->执行拣货->根据出库单ID获得拣货作业列表
                String strQueryHQL = pickBus.getPickJobQuerySQL(invoiceid);
                String strCountHQL = pickBus.getPickJobCountSQL(invoiceid);    
                //跳转页面
                strUrl = "/standard/jsp/outbound/pick/outbound_pick_execute_job.jsp";   
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 15);
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPagejob", pt); 
                
            }else if(flag != null && flag.trim().equals("4")){ 
                
                
            }else if(flag != null && flag.trim().equals("5")){  //拣货->打印拣货任务  
                //跳转页面
                strUrl = "/standard/jsp/outbound/pick/outbound_pick_print.jsp"; 
                List lsJob = pickBus.getPickJob(invoiceid, invoicedetailid);
                request.setAttribute("exResultList", lsJob);
            }else if(flag != null && flag.trim().equals("6")){  //拣货->打印拣货任务详细
                //跳转页面
                strUrl = "/standard/jsp/outbound/pick/outbound_pick_print_detail.jsp"; 
                List lsJobDetail = pickBus.getPickJobDetail(invoiceid, invoicedetailid);
                request.setAttribute("exResultList", lsJobDetail);
            }
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("出库管理==>拣货==>出库查询失败:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能: 执行拣货
     * @author hug 2012-10-23 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String executePick(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        HttpSession httpsession = request.getSession(false);
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //当前用户
        String strUserCode = (String)httpsession.getAttribute("userCode");
        //出库单ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //作业详细ID
        String jobdetailid = CommonTools.getStrToGbk(request.getParameter("jobdetailid"));

        String strBackMsg = "Y";
        outboundBus = new OutBoundBusImpl(dao);
        pickBus = new PickBusImpl(dao);
        try{
            
            strBackMsg = pickBus.executePick(invoiceid, jobdetailid, strUserCode);
            
            String strQueryHQL = pickBus.getPickJobQuerySQL(invoiceid);
            String strCountHQL = pickBus.getPickJobCountSQL(invoiceid);    
            //跳转页面
            strUrl = "/standard/jsp/outbound/pick/outbound_pick_execute_job.jsp";   
            PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 15);
            List ls = pt.getLsResult();//查询结果
            request.setAttribute("exResultList", ls);
            request.setAttribute("pagingTool", pt);
            httpsession.setAttribute("inboundPagejob", pt);   
            //返回值
            request.setAttribute("back_msg", strBackMsg);   
            request.getRequestDispatcher(strUrl).forward(request, response);

        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库管理==>拣货失败:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]：错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    
  
 
}
