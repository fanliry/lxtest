package com.wms3.bms.standard.action.rf;

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
import com.wms3.bms.standard.business.inbound.IPutawayBus;
import com.wms3.bms.standard.business.inbound.IReceiptBus;
//import com.wms3.bms.standard.business.inbound.impl.PutawayBusImpl;
import com.wms3.bms.standard.business.inbound.impl.ReceiptBusImpl;

/**
 * 描述: RF收货
 * @author hug 2012-9-3
 * @since WMS 3.0
 */
public class RFReceiptAction extends AbstractAction{
    protected IPutawayBus putawayBus;
    protected IReceiptBus receiptBus;       //收货业务
    @Override
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        //收货单ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        
        //每页显示行数
        int iline = 5;
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
        if(linerow != null && linerow.length()>0){
            iline = Integer.parseInt(linerow);
        }
        putawayBus = null;//new PutawayBusImpl(dao);
        try{
            if(flag != null && flag.trim().equals("1")){ //RF收货->查询收货单详细
                //(状态0-开单1-部分收货)
                String strQueryHQL = putawayBus.getReceiptDetailQuerySQL(invoiceid);
                String strCountHQL = putawayBus.getReceiptDetailCountSQL(invoiceid);    
                //跳转页面
                strUrl = "/rf/receipt_list.jsp";   
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 1);
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPage5", pt);       
            }else if(flag != null && flag.trim().equals("2")){ //RF上架->查询上架记录
                //(状态不为 4：完全上架 5：取消收货)
                String strQueryHQL = putawayBus.getNeedReceiptTransQuerySQL(invoiceid);
                String strCountHQL = putawayBus.getNeedReceiptTransCountSQL(invoiceid);    
                //跳转页面
                strUrl = "/rf/putaway_list.jsp";   
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 1);
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPage6", pt);       
            }
            
            
            request.getRequestDispatcher(strUrl).forward(request, response);   
        }catch(Exception er)
        {
            Logger.error("RF==>RF收货查询出错:" + er.getMessage());
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
        
        receiptBus = new ReceiptBusImpl(dao);
        String strMsg = "";
        try
        {     
            //执行收货
           strMsg = receiptBus.excuteRFReceipt(invoiceid, invoicedetailid, strLotid, 
                                strLotatt1, strLotatt2, strLotatt3, strLotatt4, strLotatt5, strLotatt6, 
                                strLotatt7, strLotatt8, strLotatt9, strLotatt10, strLotatt11, strLotatt12, 
                                Double.parseDouble(num), Double.parseDouble(weight), Double.parseDouble(netweight), Double.parseDouble(volume),
                                strUserCode);
                
         
          //跳转页面
          strUrl = "/rf/receipt.jsp";   

          request.setAttribute("back_msg", strMsg);
          request.getRequestDispatcher(strUrl).forward(request, response);      
        }catch(Exception er)
        {
            Logger.error("RF收货管理==>执行RF收货出错:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能:绑定托盘
     * @author hug 2012-9-4 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String bindTray(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        //HttpSession httpsession = request.getSession(false);
        
        //作业号
        String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));  //作业号 
        //托盘条码
        String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));
        receiptBus = new ReceiptBusImpl(dao);
        String strMsg = "";
        try
        {    
            strMsg = receiptBus.bindTray(jobid, traycode);
            
            //跳转页面
            strUrl = "/rf/bindtray.jsp";   

            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);      
          }catch(Exception er)
          {
              Logger.error("RF==>绑定托盘出错:" + er.getMessage());
              request.setAttribute("errormsg", "错误信息:" + er.getMessage());
              request.getRequestDispatcher("/error.jsp").forward(request, response);
          }
        return null;
    }


}
