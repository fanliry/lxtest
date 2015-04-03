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
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.inbound.IInBoundReceiptBus;
import com.wms3.bms.standard.business.inbound.impl.InBoundReceiptBusImpl;
import com.wms3.bms.standard.entity.inbound.InboundReceiptDetail;
import com.wms3.bms.standard.entity.inbound.InboundReceiptHeader;


/**
 * 
 * 描述: 收货单管理
 * @author hug 2012-3-8
 * @since WMS 3.0
 */
public class InBoundReceiptAction extends AbstractAction{
    
    private IInBoundReceiptBus inReceiptBus;

    @Override
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        
        String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));// 仓库ID
        String ownerid = CommonTools.getStrToGbk(request.getParameter("ownerid"));        //货主
        String instatus = CommonTools.getStrToGbk(request.getParameter("instatus"));      //单据状态
        String intype = CommonTools.getStrToGbk(request.getParameter("intype"));          //入库类型
        String screatedate = CommonTools.getStrToGbk(request.getParameter("start_time")); //单据开始生成时间
        String ecreatedate = CommonTools.getStrToGbk(request.getParameter("end_time"));   //单据结束生成时间

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
        inReceiptBus = new InBoundReceiptBusImpl(dao);
        try
        {
             if(flag != null && flag.trim().equals("1")){//新建收货单->收货单查询
                 
                 String strQueryHQL = inReceiptBus.getInboundReceiptQuerySQL(warehouseid, ownerid, instatus, intype, screatedate, ecreatedate);
                 String strCountHQL = inReceiptBus.getInboundReceiptCountSQL(warehouseid, ownerid, instatus, intype, screatedate, ecreatedate);   
                 
                 //跳转页面
                 strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_list.jsp";    
                 PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                 List ls = pt.getLsResult();//查询结果
                 request.setAttribute("exResultList", ls);
                 request.setAttribute("pagingTool", pt);
                 httpsession.setAttribute("paging", pt);

             }else if(flag != null && flag.trim().equals("re1")){//收货->收货单查询
                 
                 String strQueryHQL = inReceiptBus.getInboundReceiptQuerySQL(warehouseid, ownerid, instatus, intype, screatedate, ecreatedate);
                 String strCountHQL = inReceiptBus.getInboundReceiptCountSQL(warehouseid, ownerid, instatus, intype, screatedate, ecreatedate);   
                 
                 //跳转页面
                 strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_list.jsp";    
                 PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                 List ls = pt.getLsResult();//查询结果
                 request.setAttribute("exResultList", ls);
                 request.setAttribute("pagingTool", pt);
                 httpsession.setAttribute("paging", pt);

             }else if(flag != null && flag.trim().equals("put1")){//上架->收货单查询
                 
                 String strQueryHQL = inReceiptBus.getInboundReceiptQuerySQL(warehouseid, ownerid, instatus, intype, screatedate, ecreatedate);
                 String strCountHQL = inReceiptBus.getInboundReceiptCountSQL(warehouseid, ownerid, instatus, intype, screatedate, ecreatedate);   
                 
                 //跳转页面
                 strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_list.jsp";    
                 PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                 List ls = pt.getLsResult();//查询结果
                 request.setAttribute("exResultList", ls);
                 request.setAttribute("pagingTool", pt);
                 httpsession.setAttribute("paging", pt);

             }else if(flag != null && flag.trim().equals("2")){//收货单打印
                 //收货单
                 InboundReceiptHeader inBound = inReceiptBus.getInBoundReceiptInvoiceById(invoiceid);
                 //收货单详细
                 List<InboundReceiptDetail> lsDetail = inReceiptBus.getInBoundReceiptDetailsById(invoiceid);
                 
                 strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_print.jsp";
                 
                 request.setAttribute("invoice", inBound);
                 request.setAttribute("invoicedetail", lsDetail); 
                 
             }else if(flag != null && flag.trim().equals("3")){ //新建收货单->根据收货单ID获得收货单详细列表
                 //收货单详细
                 List<InboundReceiptDetail> lsDetail = inReceiptBus.getInBoundReceiptDetailsById(invoiceid);
                 
                 strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_dlist.jsp";
                 request.setAttribute("invoicedetail", lsDetail);
                 
             }else if(flag != null && flag.trim().equals("re3")){ //收货->根据收货单ID获得收货单详细列表
                 //收货单详细
                 List<InboundReceiptDetail> lsDetail = inReceiptBus.getInBoundReceiptDetailsById(invoiceid);
                 
                 strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_dlist.jsp";
                 request.setAttribute("invoicedetail", lsDetail);
                 
             }else if(flag != null && flag.trim().equals("4")){//根据收货单据ID获得收货单据
                 //收货单
                 InboundReceiptHeader inBound = inReceiptBus.getInBoundReceiptInvoiceById(invoiceid);
                 strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_update.jsp";
                 request.setAttribute("invoice", inBound);     
             }else if(flag != null && flag.trim().equals("6")){//新建收货单->明细->查询收货单详细
                 //收货单详细
                 List<InboundReceiptDetail> lsDetail = inReceiptBus.getInBoundReceiptDetailsById(invoiceid);
                 strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_detail_list.jsp";
                 request.setAttribute("invoicedetail", lsDetail);
             }   
             
             request.getRequestDispatcher(strUrl).forward(request, response);
        }catch(Exception er)
        {
            Logger.error("入库管理==>入库收货单出错:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能：增加入库收货单
     * @author hug 2012-3-8 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String addRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        String warehouseid = CommonTools.getStrToGb2312(request.getParameter("warehouseid")); // 仓库ID
        String strUserCode = (String)request.getSession().getAttribute("userCode");           //当前用户
        
        String strInType = CommonTools.getStrToGbk(request.getParameter("intype"));             //入库类型
        
        String strCustomerId = CommonTools.getStrToGbk(request.getParameter("customer_id"));    //货主 
        String strArrivestarttime = CommonTools.getStrToGbk(request.getParameter("arrivestarttime"));//预期到货时间从
        String strArriveendtime = CommonTools.getStrToGbk(request.getParameter("arriveendtime"));    //预期到货时间到
        
        String strShipperid = CommonTools.getStrToGbk(request.getParameter("shipperid"));        //承运人   
        String strSupplierid = CommonTools.getStrToGbk(request.getParameter("supplierid"));      //供应商
        String strReceiveloc = CommonTools.getStrToGbk(request.getParameter("receiveloc"));      //收货月台
        
        String strCustomsno = CommonTools.getStrToGbk(request.getParameter("customsno"));       //海关备案号
        String strReserve1 = CommonTools.getStrToGbk(request.getParameter("reserve1"));         //用户自定义1
        String strReserve2 = CommonTools.getStrToGbk(request.getParameter("reserve2"));         //用户自定义2
        
        String strRemark = CommonTools.getStrToGbk(request.getParameter("remark"));        //备注
          
        inReceiptBus = new InBoundReceiptBusImpl(dao);
        String strMsg = "Y";
        try
        {
            //入库收货单
            InboundReceiptHeader inBoundReceipt = new InboundReceiptHeader();
            inBoundReceipt.setWarehouseid(warehouseid);    //仓库编号
            inBoundReceipt.setInvoicedate(StrTools.getCurrDateTime(8));//默认为生成日期
            inBoundReceipt.setCreatetime(StrTools.getCurrDateTime(2));//单据生成时间
            inBoundReceipt.setCreatemanid(strUserCode);//单据生成人员编号
            inBoundReceipt.setInstatus("0");//单据状态
            inBoundReceipt.setIntype(strInType);//入库类型
            inBoundReceipt.setOwnerid(strCustomerId);//货主
            
            inBoundReceipt.setArrivestarttime(strArrivestarttime);//预期到货时间从
            inBoundReceipt.setArriveendtime(strArriveendtime);//预期到货时间到
            inBoundReceipt.setShipperid(strShipperid);//承运人
            inBoundReceipt.setSupplierid(strSupplierid);//供应商
            inBoundReceipt.setReceiveloc(strReceiveloc);//收货月台
            
            inBoundReceipt.setCustomsno(strCustomsno);//海关备案号
            inBoundReceipt.setReserve1(strReserve1);//用户自定义1
            inBoundReceipt.setReserve2(strReserve2);//用户自定义2
            inBoundReceipt.setRemark(strRemark);     //备注
            String strID = inReceiptBus.addInBoundReceipt(inBoundReceipt);
               
   
            //跳转页面
            strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_list.jsp";
            //返回值
            request.setAttribute("back_msg", strMsg);
            request.setAttribute("InvoicedId", strID);
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("入库管理==>入库收货单据出错:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    
    /**
     * 功能：修改入库收货单
     * @author hug 2012-3-13 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String updateRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // 仓库ID
        String strUserCode = (String)request.getSession().getAttribute("userCode");           //当前用户
        
        //收货单ID
        String strInvoiceId = CommonTools.getStrToGbk(request.getParameter("invoiceid")); 
        
        String strInType = CommonTools.getStrToGbk(request.getParameter("intype"));             //入库类型
        String strCustomerId = CommonTools.getStrToGbk(request.getParameter("customer_id"));    //货主 
        String strArrivestarttime = CommonTools.getStrToGbk(request.getParameter("arrivestarttime"));//预期到货时间从
        String strArriveendtime = CommonTools.getStrToGbk(request.getParameter("arriveendtime"));    //预期到货时间到
        
        String strShipperid = CommonTools.getStrToGbk(request.getParameter("shipperid"));        //承运人   
        String strSupplierid = CommonTools.getStrToGbk(request.getParameter("supplierid"));      //供应商
        String strReceiveloc = CommonTools.getStrToGbk(request.getParameter("receiveloc"));      //收货月台
        
        String strCustomsno = CommonTools.getStrToGbk(request.getParameter("customsno"));       //海关备案号
        String strReserve1 = CommonTools.getStrToGbk(request.getParameter("reserve1"));         //用户自定义1
        String strReserve2 = CommonTools.getStrToGbk(request.getParameter("reserve2"));         //用户自定义2
        
        String strRemark = CommonTools.getStrToGbk(request.getParameter("remark"));        //备注
        inReceiptBus = new InBoundReceiptBusImpl(dao);
        String strMsg = "";
        try
        {
            //要修改入库收货单的内容
            InboundReceiptHeader inBoundReceipt = inReceiptBus.getInBoundReceiptInvoiceById(strInvoiceId);
            inBoundReceipt.setWarehouseid(warehouseid);    //仓库编号
            inBoundReceipt.setUpdatemanid(strUserCode);    //单据修改人员编号
            inBoundReceipt.setIntype(strInType);//入库类型
            inBoundReceipt.setOwnerid(strCustomerId);//货主         
            inBoundReceipt.setArrivestarttime(strArrivestarttime);//预期到货时间从
            inBoundReceipt.setArriveendtime(strArriveendtime);//预期到货时间到
            inBoundReceipt.setShipperid(strShipperid);//承运人
            inBoundReceipt.setSupplierid(strSupplierid);//供应商
            inBoundReceipt.setReceiveloc(strReceiveloc);//收货月台
            
            inBoundReceipt.setCustomsno(strCustomsno);//海关备案号
            inBoundReceipt.setReserve1(strReserve1);//用户自定义1
            inBoundReceipt.setReserve2(strReserve2);//用户自定义2
            inBoundReceipt.setRemark(strRemark);     //备注
            strMsg = inReceiptBus.updateInBoundReceipt(inBoundReceipt);
            
            //刷新 
            PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
            List ls = null;
            String strUrl = null;
            if(pt != null)
            {
                ls = CommonPagination.getPagingList(dao, pt);
                
                strUrl = pt.getM_strUrl();
            }
            if(strUrl == null)
            {
                //跳转页面
                strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_list.jsp";
            }
            request.setAttribute("exResultList", ls);
            request.setAttribute("pagingTool", pt);
            httpsession.setAttribute("paging", pt);
           
            //返回值
            request.setAttribute("back_msg", strMsg);//返回操作结果
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("入库管理==>修改入库收货单据出错:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能：增加入库收货单详细
     * @author hug 2012-3-13 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String addDetailRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // 仓库ID
        //String strUserCode = (String)request.getSession().getAttribute("userCode");           //当前用户
        //收货单据ID
        String strInvoiceId = CommonTools.getStrToGbk(request.getParameter("invoiceid")); 
        
        String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));    //品名规格
        String strPackId = CommonTools.getStrToGbk(request.getParameter("packid"));          //包装   
        String strEunit = CommonTools.getStrToGbk(request.getParameter("eunit"));            //单位
        String strInvoiceNum = CommonTools.getStrToGbk(request.getParameter("invoicenum"));  //最小单位数量
        String strVolume = CommonTools.getStrToGbk(request.getParameter("volume"));          //体积
        String strWeight = CommonTools.getStrToGbk(request.getParameter("weight"));          //重量
        String strNetweight = CommonTools.getStrToGbk(request.getParameter("netweight"));    //净重
        String strPrice = CommonTools.getStrToGbk(request.getParameter("price"));            //价格
        String strReclocation = CommonTools.getStrToGbk(request.getParameter("reclocation"));//收货库位
        String strSkustatus = CommonTools.getStrToGbk(request.getParameter("skustatus"));  //产品状态代码
        String strSkustatus_descr = CommonTools.getStrToGbk(request.getParameter("skustatus_descr"));//产品状态描述

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
        
        inReceiptBus = new InBoundReceiptBusImpl(dao);
        String strMsg = "";
        try
        {
            //入库收货单详细
            InboundReceiptDetail receiptDetail = new InboundReceiptDetail();
            receiptDetail.setReinvoiceid(strInvoiceId); //收货单编号
            receiptDetail.setProductid(strProductId);   //品名
            receiptDetail.setPackid(strPackId);         //包装
            receiptDetail.setEunit(strEunit);
            receiptDetail.setInvoicenum(Double.parseDouble(strInvoiceNum)); //最小单位数量
            receiptDetail.setVolume(Double.parseDouble(strVolume));         //体积
            receiptDetail.setWeight(Double.parseDouble(strWeight));         //重量
            receiptDetail.setNetweight(Double.parseDouble(strNetweight));   //净重
            receiptDetail.setPrice(Double.parseDouble(strPrice));//单价
            receiptDetail.setReclocation(strReclocation);        //收货库区
            receiptDetail.setSkustatus(strSkustatus);            //产品状态代码
            receiptDetail.setSkustatus_descr(strSkustatus_descr);//产品状态描述
            receiptDetail.setLinestatus("0");   //单据行状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
            
            receiptDetail.setLotid(strLotid);    //批次类型ID
            receiptDetail.setLotatt1(strLotatt1);//批次属性1
            receiptDetail.setLotatt2(strLotatt2);//批次属性2
            receiptDetail.setLotatt3(strLotatt3);//批次属性3
            receiptDetail.setLotatt4(strLotatt4);//批次属性4
            receiptDetail.setLotatt5(strLotatt5);//批次属性5
            receiptDetail.setLotatt6(strLotatt6);//批次属性6
            receiptDetail.setLotatt7(strLotatt7);//批次属性7
            receiptDetail.setLotatt8(strLotatt8);//批次属性8
            receiptDetail.setLotatt9(strLotatt9);//批次属性9
            receiptDetail.setLotatt10(strLotatt10);//批次属性10
            receiptDetail.setLotatt11(strLotatt11);//批次属性11
            receiptDetail.setLotatt12(strLotatt12);//批次属性12
            
            
            strMsg = inReceiptBus.addInBoundReceiptDetail(receiptDetail, strInvoiceId);
            
            List<InboundReceiptDetail> lsDetail = inReceiptBus.getInBoundReceiptDetailsById(strInvoiceId);
            
            //跳转页面
            strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_detail_list.jsp";
            //返回值
            request.setAttribute("back_msg", strMsg);//返回操作结果
            request.setAttribute("invoicedetail", lsDetail);
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("入库管理==>新增入库收货单详细出错:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能：修改入库收货单详细
     * @author hug 2012-3-13 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String updateDetailRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // 仓库ID
        //String strUserCode = (String)request.getSession().getAttribute("userCode");           //当前用户
        
        //收货单详细ID
        String strInvoiceDetailId = CommonTools.getStrToGbk(request.getParameter("detailid")); 
        
        String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));    //品名规格
        String strPackId = CommonTools.getStrToGbk(request.getParameter("packid"));          //包装
        String strEunit = CommonTools.getStrToGbk(request.getParameter("eunit"));           //单位
        String strInvoiceNum = CommonTools.getStrToGbk(request.getParameter("invoicenum"));  //最小单位数量
        String strVolume = CommonTools.getStrToGbk(request.getParameter("volume"));          //体积
        String strWeight = CommonTools.getStrToGbk(request.getParameter("weight"));          //重量
        String strNetweight = CommonTools.getStrToGbk(request.getParameter("netweight"));    //净重
        String strPrice = CommonTools.getStrToGbk(request.getParameter("price"));            //价格
        String strReclocation = CommonTools.getStrToGbk(request.getParameter("reclocation"));  //收货库位
        String strSkustatus = CommonTools.getStrToGbk(request.getParameter("skustatus"));  //产品状态代码
        String strSkustatus_descr = CommonTools.getStrToGbk(request.getParameter("skustatus_descr"));//产品状态描述

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
            
        inReceiptBus = new InBoundReceiptBusImpl(dao);
        String strMsg = "";
        try
        {
            //收货单详细
            InboundReceiptDetail receiptDetail = inReceiptBus.getInBoundReceiptDetailByDetailId(strInvoiceDetailId); 
            receiptDetail.setProductid(strProductId);//品名规格
            receiptDetail.setPackid(strPackId);      //包装
            receiptDetail.setEunit(strEunit);
            receiptDetail.setInvoicenum(Double.parseDouble(strInvoiceNum)); //最小单位数量
            receiptDetail.setVolume(Double.parseDouble(strVolume));         //体积
            receiptDetail.setWeight(Double.parseDouble(strWeight));         //重量
            receiptDetail.setNetweight(Double.parseDouble(strNetweight));   //净重
            receiptDetail.setPrice(Double.parseDouble(strPrice));//单价
            receiptDetail.setReclocation(strReclocation);//收货库位
            receiptDetail.setSkustatus(strSkustatus);            //产品状态代码
            receiptDetail.setSkustatus_descr(strSkustatus_descr);//产品状态描述
            
            receiptDetail.setLotid(strLotid);    //批次类型ID
            receiptDetail.setLotatt1(strLotatt1);//批次属性1
            receiptDetail.setLotatt2(strLotatt2);//批次属性2
            receiptDetail.setLotatt3(strLotatt3);//批次属性3
            receiptDetail.setLotatt4(strLotatt4);//批次属性4
            receiptDetail.setLotatt5(strLotatt5);//批次属性5
            receiptDetail.setLotatt6(strLotatt6);//批次属性6
            receiptDetail.setLotatt7(strLotatt7);//批次属性7
            receiptDetail.setLotatt8(strLotatt8);//批次属性8
            receiptDetail.setLotatt9(strLotatt9);//批次属性9
            receiptDetail.setLotatt10(strLotatt10);//批次属性10
            receiptDetail.setLotatt11(strLotatt11);//批次属性11
            receiptDetail.setLotatt12(strLotatt12);//批次属性12
            
            strMsg = inReceiptBus.updateInBoundReceiptDetail(receiptDetail);
            
            List<InboundReceiptDetail> lsDetail = inReceiptBus.getInBoundReceiptDetailsById(receiptDetail.getReinvoiceid());
            
            //跳转页面
            strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_detail_list.jsp";
            //返回值
            request.setAttribute("back_msg", strMsg);//返回操作结果
            request.setAttribute("invoicedetail", lsDetail);
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("入库管理==>修改入库收货单详细出错:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能：删除单据或单据详细
     * @author hug 2012-3-13 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String deleteRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //String strUserCode = (String)request.getSession().getAttribute("userCode"); //当前用户
        //操作类型 1：删除单据 2：删除单据详细
        String strFlag = CommonTools.getStrToGbk(request.getParameter("flag"));
        //单据ID列表
        String strInvoiceIds = CommonTools.getStrToGbk(request.getParameter("invoiceids")); 
        
        //单据ID
        String strInvoiceId = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //单据详细ID (删除单据时不需要)列表
        String strInvoiceDetailIds = CommonTools.getStrToGbk(request.getParameter("detailids"));

        inReceiptBus = new InBoundReceiptBusImpl(dao);
        String strMsg = "";
        try
        {
            List<InboundReceiptDetail> lsDetail = null;
            if(strFlag != null && strFlag.trim().equals("1")){ //1：删除单据)
                if(strInvoiceIds != null)
                {   
                    String strIds[] = strInvoiceIds.split(",");    
                    for(int i = 0; i < strIds.length; i++)
                    {
                        //单据ID
                        String strInvoice = strIds[i];
                        strMsg = strMsg + inReceiptBus.deleteReceiptInvoice(strInvoice, null, strFlag);
                    }
                }
                //跳转页面
                strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_list.jsp";
                
            }else if(strFlag != null && strFlag.trim().equals("2")){ //2：删除单据详细
                if(strInvoiceDetailIds != null)
                {   
                    String strIds[] = strInvoiceDetailIds.split(",");    
                    for(int i = 0; i < strIds.length; i++)
                    {
                        //单据详细
                        String strInvoiceDetailId = strIds[i];
                        strMsg = strMsg + inReceiptBus.deleteReceiptInvoice(strInvoiceId, strInvoiceDetailId, strFlag);
                    }
                }
                lsDetail = inReceiptBus.getInBoundReceiptDetailsById(strInvoiceId);
                //跳转页面
                strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_detail_list.jsp";
            }

            //返回值
            request.setAttribute("back_msg", strMsg);//返回操作结果
            request.setAttribute("invoicedetail", lsDetail);
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("入库管理==>删除入库收货单或收货单详细(" + strFlag + ")出错:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }

    
    

}
