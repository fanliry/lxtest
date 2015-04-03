package com.wms3.bms.standard.action.outbound;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.outbound.IAssginBus;
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.impl.AssginBusImpl;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.base.HisProduct;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;

/**
 * 描述: 出库分配
 * @author hug 2012-9-20
 * @since WMS 3.0
 */
public class AllocationAction extends AbstractAction{
    protected IAssginBus assginBus;
    protected IOutBoundBus outboundBus;
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        //HttpSession httpsession = request.getSession(false);
        
        AssginBusImpl assginBus1 = new AssginBusImpl(dao);
        try
        {
            String flag = CommonTools.getStrToGbk(request.getParameter("flag"));        
            //出库单ID
            String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
            //出库单详细ID
            String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));     
            //String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));     //客户
            String ownerid = CommonTools.getStrToGbk(request.getParameter("ownerid"));           //货主   
            String warehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));   //仓库ID
            String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));    //品名ID
            String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));      //库区
            String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyid"));        //巷道
            String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));        //批号
            String printdate = CommonTools.getStrToGbk(request.getParameter("printdate"));    //生产日期
            String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));      //托盘条码
            
            String unit = CommonTools.getStrToGbk(request.getParameter("unit"));      //单位

            HisProduct tg = new HisProduct();
            if(flag != null && flag.trim().equals("1")){//出库分配->手工分配查询
                //跳转页面
                strUrl = "/standard/jsp/outbound/assgin/outbound_assgin_list.jsp";
                outboundBus = new OutBoundBusImpl(dao);
                OutboundInvoiceHeader outBound = outboundBus.getOutBoundById(invoiceid);
                OutboundInvoiceDetail outInvoiceDetail = outboundBus.getOutBoundDetailById(invoicedetailid);
                String out_type = outBound.getOuttype();
                String ss = tg.getCAdd(dao, out_type, "wpzt");
                List lsStorage = assginBus1.getAssginQueryStorage(warehouseId,whAreaId, alleyId, strProductId,traycode,ss);

                request.setAttribute("exResultList", lsStorage);
                request.setAttribute("outInvoiceDetail", outInvoiceDetail);
            }
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("出库管理==>分配==>出库分配查询失败:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能: 人工出库分配
     * @author hug 2012-9-26 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String allotRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        //HttpSession httpsession = request.getSession(false);
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
 
        //当前用户
        String strUserCode = (String)request.getSession().getAttribute("userCode");
        
        //出库单ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("outboundid"));
        //出库单详细ID
        String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("detaiid"));
        
        //分配的库存信息 格式:    货位:托盘条码c[库存ID1，数量;库存ID2，数量,分配数量] | 货位3:托盘条码d[库存ID1，数量;库存ID2，数量,分配数量] |
        String strStoMsg = CommonTools.getStrToGbk(request.getParameter("result"));
        
        String floor = CommonTools.getStrToGbk(request.getParameter("floor"));
        
        String tsjh = CommonTools.getStrToGbk(request.getParameter("tsjh"));
        
        Logger.info(strUserCode+"接收人工分配的信息:" + strStoMsg);
        
        assginBus = new AssginBusImpl(dao);
        outboundBus = new OutBoundBusImpl(dao);
        try
        {   
        	OutboundInvoiceHeader outBound = null;
             
			String strBackMsg = assginBus.assginStorage(invoiceid, invoicedetailid, strStoMsg, floor, tsjh, strUserCode);
            OutboundInvoiceDetail invoiceDetail = outboundBus.getOutBoundDetailById(invoicedetailid);
            
            if(invoiceDetail != null){ 
            	outBound = outboundBus.getOutBoundById(invoiceDetail.getOutstockid());
            }
            //跳转页面
            strUrl = "/standard/jsp/outbound/assgin/outbound_assgin_list.jsp";
            //返回值
            request.setAttribute("back_msg", strBackMsg);   
            request.setAttribute("invoicedetail", invoiceDetail);
            request.setAttribute("invoice", outBound);
            request.getRequestDispatcher(strUrl).forward(request, response);   
            
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库管理==>人工出库分配出错:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]：错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }       
        return null;
    }
    /**
     * 功能: 自动分配
     * @author hug 2012-9-26 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String outoAllotRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        //HttpSession httpsession = request.getSession(false);
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
 
        //当前用户
        String strUserCode = (String)request.getSession().getAttribute("userCode");
        
        //出库单ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //出库单详细ID
        String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));
        String warehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));   //仓库ID
        String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));     //客户
        String ownerid = CommonTools.getStrToGbk(request.getParameter("ownerid"));           //货主

        String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));    //库区ID
        
        assginBus = new AssginBusImpl(dao);
        try
        {
            String strBackMsg = assginBus.autoAssginStorage(invoiceid, invoicedetailid, ownerid, customerid, warehouseId, whAreaId, strUserCode);
            
            //跳转页面
            //strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_list.jsp";
            //返回值
            request.setAttribute("back_msg", strBackMsg);                
            request.getRequestDispatcher(strUrl).forward(request, response);   
            
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库管理==>出库自动分配出错:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]：错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }       
        return null;
    }
    
}
