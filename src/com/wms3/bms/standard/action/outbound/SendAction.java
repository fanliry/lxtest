package com.wms3.bms.standard.action.outbound;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.outbound.ISendBus;
import com.wms3.bms.standard.business.outbound.impl.SendBusImpl;
import com.wms3.bms.standard.dao.outbound.IAssginDao;
import com.wms3.bms.standard.dao.outbound.impl.AssginDaoImpl;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 描述: 发货确认
 * @author hug 2012-10-9
 * @since WMS 3.0
 */
public class SendAction extends AbstractAction{
    protected ISendBus sendBus;
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));
        String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));//仓库ID
        String zcwhareaid = CommonTools.getStrToGbk(request.getParameter("zcwhareaid"));//暂存ID
        sendBus = new SendBusImpl(dao); 
        try
        {     
            //出库单ID
            String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
            
            //每页显示行数
            int iline = 5;
            String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
            if(linerow != null && linerow.length()>0){
                iline = Integer.parseInt(linerow);
            }
            if(flag != null && flag.trim().equals("1")){//发货确认 -> 调整 -> 根据仓库获得暂存
            	
                List ls = sendBus.getZCInventorybyTray(warehouseid,zcwhareaid,traycode);
                //跳转页面
                strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_zc_list.jsp";   
                request.setAttribute("exResultList", ls);
                 
             }else if(flag != null && flag.trim().equals("2")){//发货确认 ->查询作业
                 //出库单详细ID
                 String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));
                 List ls = sendBus.getJobAndJobDetail(invoiceid, invoicedetailid);
                 
                 //跳转页面
                 strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_job_list.jsp";   
                 request.setAttribute("exResultList", ls);
                 
             }else if(flag != null && flag.trim().equals("3")){//发货确认 ->调整 ->根据托盘条码和箱条码查询已出库的作业
                 List ls = sendBus.getJobAndJobDetailByCode(invoiceid, traycode, "");    
                 //跳转页面
                 strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_jobdetail.jsp";   
                 request.setAttribute("exResultList", ls);
             }else if(flag != null && flag.trim().equals("4")){//发货确认 ->调整 ->下移 ->查询作业详细
                 //作业详细ID
                String strJobDetailId = CommonTools.getStrToGbk(request.getParameter("jobdetailid")); 
                strUrl ="/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_move.jsp";
                //作业详细
                InoutJobdetail jobDetail = sendBus.getJobDetailByDetailId(strJobDetailId);
                //作业
                InoutJob job = sendBus.getJobById(jobDetail.getJobid());
                request.setAttribute("job", job);
                request.setAttribute("jobDetail", jobDetail);
             }else if(flag != null && flag.trim().equals("5")){//发货确认 ->调整 ->上移 ->查询暂存
                 //库存ID
                 String strStoreid = CommonTools.getStrToGbk(request.getParameter("Storeid")); 
                 strUrl ="/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_movezc.jsp";
                 IAssginDao assginDao = new AssginDaoImpl(dao);
                 InventoryStorage storage = assginDao.getInventoryStorageById(strStoreid);
                 request.setAttribute("storage", strStoreid);
             }
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("出库管理==>发货查询出错:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息"+er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能:发货调整 A客户->B客户 A客户->暂存
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String ricoExecMove(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        HttpSession httpsession = request.getSession(false);
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //标识  A客户->暂存区   A客户->B客户
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        
        //调整的出入库作业详细
        String strDetailId = CommonTools.getStrToGbk(request.getParameter("detailid"));
        //调整的数量
        String strNum = CommonTools.getStrToGbk(request.getParameter("num"));
        //调整的毛重
        String strWeight = CommonTools.getStrToGbk(request.getParameter("weight"));
        //调整的净重
        String strNetweight = CommonTools.getStrToGbk(request.getParameter("netweight"));
        //调整的体积
        String strVolume = CommonTools.getStrToGbk(request.getParameter("volume"));
        
        //A客户 -> 暂存区****************************************************************
        //出库单ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //仓库ID
        String strWarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));
        //库区ID（暂存区ID）
        String whAreaId =  CommonTools.getStrToGbk(request.getParameter("whAreaId")); 
        // 暂存区的货位ID
       // String whAreaSpaceId = strWarehouseId + whAreaId + "001";   //暂存区货位   
        String whAreaSpaceId = whAreaId + "010101";   //暂存区货位 
        //托盘条码
        String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));
        //箱条码
        String boxcode = CommonTools.getStrToGbk(request.getParameter("boxcode")); 
        //当前仓库
        String strUserCode = (String)httpsession.getAttribute("userCode");            //当前用户
        String strShiftid =  CommonTools.getStrToGbk(request.getParameter("shiftid"));//班次
        sendBus = new SendBusImpl(dao);
        String strMsg = "Y";
        try
        {
            double num = Double.parseDouble(strNum);       //数量
            double volume = 0;    //体积
            double weight = 0;    //重量
            double netweight = 0; //净重
           
            List ls = null;
            if(flag != null && flag.equals("1"))    //A客户->暂存区
            {
            	strMsg = sendBus.getAtoZ(strDetailId, num, weight, netweight, volume, strUserCode, strShiftid, strWarehouseId, whAreaId, whAreaSpaceId);
            }
            ls = sendBus.getJobAndJobDetailByCode(invoiceid, traycode, boxcode);    
            //跳转页面
            strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_jobdetail.jsp";   
            request.setAttribute("exResultList", ls);
            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库管理==>发货确认调整失败:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]：错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能: 批量调整
     * @author hug 2012-10-31 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String ricoExecBatchMove(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        HttpSession httpsession = request.getSession(false);
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //标识  A客户->暂存区   A客户->B客户
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));    
        //出入库作业详细列表  用逗号分隔
        String strDetailIds = CommonTools.getStrToGbk(request.getParameter("detailids"));
        
        //A客户 -> B客户****************************************************************
        //B客户单据ID
        String strBInvoiceId = CommonTools.getStrToGbk(request.getParameter("binvoiceid"));
        //B客户单据详细ID
        String strBInvoiceDetailId = CommonTools.getStrToGbk(request.getParameter("binvoicedetailid"));
        
        //A客户 -> 暂存区****************************************************************
        //仓库ID
        String strWarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));
        // 库区ID（暂存区ID）
        String whAreaId =  CommonTools.getStrToGbk(request.getParameter("whAreaId")); //一个仓库只能有一个暂存区
        // 暂存区的货位ID
        String whAreaSpaceId  =  strWarehouseId + whAreaId + "001";   //暂存区货位
        
        //当前用户
        String strUserCode = (String)httpsession.getAttribute("userCode");
        //班次
        String strShiftid =  CommonTools.getStrToGbk(request.getParameter("shiftid")); 
        
        sendBus = new SendBusImpl(dao);
        String strMsg = "Y";
        try
        {
            List ls = null;
            
            if(flag != null && flag.equals("1"))    //A客户->暂存区
            {
                sendBus.getBatchAtoZ(strDetailIds, strUserCode, strShiftid, strWarehouseId, whAreaId, whAreaSpaceId);
            }else if(flag != null && flag.equals("2")) //A客户 -> B客户
            {
                sendBus.getBatchAtoB(strBInvoiceId, strBInvoiceDetailId, strDetailIds, strUserCode, strShiftid);
            }

            request.setAttribute("back_msg", strMsg);
            request.setAttribute("exResultList", ls);
            request.getRequestDispatcher(strUrl).forward(request, response);
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库管理==>发货确认批量调整失败:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]：错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    
    /**
     * 功能：发货确认
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String ricoExecOkFHQR(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
         
        //出库单据号
        String strOutBoundId = CommonTools.getStrToGbk(request.getParameter("out_id"));
        //班次
        String strShiftid =  CommonTools.getStrToGbk(request.getParameter("shiftid"));
        sendBus = new SendBusImpl(dao);
        //当前用户
        String strUserCode = (String)httpsession.getAttribute("userCode");
        try
        {
            String strMsg = "Y";
            //按单据发货确认
            strMsg = sendBus.outInvoiceFHQR(strOutBoundId, strUserCode, strShiftid);
            
           // strUrl = "/standard/jsp/outbound/outbound_fhqr.jsp";
            strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_list.jsp";
            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库管理==>发货确认失败:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        
        return null;
    }
    /**
     * 功能：单据详细进行发货确认
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String ricoExecOneOkFHQR(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        //标识 
        //String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        
        //出库单据号
        //String invoiceid = CommonTools.getStrToGbk(request.getParameter("out_id"));
        //出库单详细
        String strOutDetailId = CommonTools.getStrToGbk(request.getParameter("out_detail_id"));
        //班次
        String strShiftid =  CommonTools.getStrToGbk(request.getParameter("shiftid"));
        sendBus = new SendBusImpl(dao);
        //当前用户
        String strUserCode = (String)httpsession.getAttribute("userCode");
        try
        {
            String strMsg = "Y";
            //按单据发货确认
            strMsg = sendBus.outInvoiceDetailFHQR(strOutDetailId, strUserCode, strShiftid);

            //跳转页面
            strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_list.jsp";
           // strUrl = "/standard/jsp/outbound/outbound_fhqr.jsp";
   
            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库管理==>按单据详细发货确认失败:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }

}
