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
import com.wms3.bms.standard.business.inbound.IPutawayBus;
//import com.wms3.bms.standard.business.inbound.impl.PutawayBusImpl;
import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;


/**
 * 描述: 上架
 * @author hug 2012-8-28
 * @since WMS 3.0
 */
public class PutawayAction  extends AbstractAction{
    protected IPutawayBus putawayBus;
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
            //收货交易记录ID
            String transid = CommonTools.getStrToGbk(request.getParameter("transid"));
            if(flag != null && flag.trim().equals("1")){ //上架->按单据上架->查询收货记录
                String strQueryHQL = putawayBus.getReceiptTransQuerySQL(invoiceid);
                String strCountHQL = putawayBus.getReceiptTransCountSQL(invoiceid);    
                //跳转页面
                strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_mgr_trans.jsp";   
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 1);
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPage4", pt); 
                
            }else if(flag != null && flag.trim().equals("2")){ //上架->码盘
                //跳转页面
                strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_tray.jsp";
                InboundReceiptTransaction trans = putawayBus.getTransReceiptById(transid);
                //计算该包装一托盘可放多少数量
                int iOneTray = putawayBus.getOneTrayNumber(trans.getPackid());
                //码盘的托盘总数
                int iTrayNum = putawayBus.getTrayNum((trans.getRecnum()-trans.getPucnum()), trans.getPackid());
                request.setAttribute("trans", trans);  
                request.setAttribute("onetray", iOneTray);
                request.setAttribute("traynum", iTrayNum);
            }else if(flag != null && flag.trim().equals("3")){ //上架->执行上架->查询作业
                String strQueryHQL = putawayBus.getPutawayJobQuerySQL(invoiceid);
                String strCountHQL = putawayBus.getPutawayJobCountSQL(invoiceid);    
                //跳转页面
                strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_execute_job.jsp";   
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPagejob", pt); 
                
            }else if(flag != null && flag.trim().equals("4")){ //上架->执行上架->查询作业详细
                //跳转页面
                strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_execute_jobdetail.jsp";  
                //作业ID
                String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));
    //            List<InoutJobdetail> lsJobDetail = putawayBus.getJobDetailsById(jobid);
     //           request.setAttribute("exResultList", lsJobDetail);
            }else if(flag != null && flag.trim().equals("label")){ //上架->打印收货的标签
                //跳转页面
                strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_label.jsp";
                InboundReceiptTransaction trans = putawayBus.getTransReceiptById(transid);
                request.setAttribute("trans", trans);  
            }else if(flag != null && flag.equals("print")){//上架->打印上架任务
                //跳转页面
                strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_printtask.jsp";
                List ls = putawayBus.getPutawayJob(invoiceid);

                request.setAttribute("invoiceid", invoiceid);
                request.setAttribute("exResultList", ls); 
            }
            
            request.getRequestDispatcher(strUrl).forward(request, response);   
        }catch(Exception er)
        {
            Logger.error("入库管理==>上架查询出错:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能:生成上架任务
     * @author hug 2012-9-4 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String createPutaway(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        //HttpSession httpsession = request.getSession(false);
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");     //当前用户  
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));  //收货单ID 
        
        putawayBus = null;// new PutawayBusImpl(dao);
        String strMsg = "";
        
        //收货记录的行数
        String strLineRows = CommonTools.getStrToGbk(request.getParameter("transrows"));
        //托盘条码
        String strTrayCode = CommonTools.getStrToGbk(request.getParameter("traycode"));
        //仓库ID
        String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));
        //库区ID
        String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));
        //上架方式
        String putmode = CommonTools.getStrToGbk(request.getParameter("putmode"));
        
        try
        {
            //创建上架任务
            strMsg = putawayBus.createPutawayTask(invoiceid, strLineRows, strUserCode, strTrayCode, warehouseid, whAreaId, putmode, request);   
                
            //跳转页面
            strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_mgr_put.jsp";   
            request.setAttribute("invoiceid", invoiceid);
            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);      
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]，入库管理==>生成上架任务出错:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]，错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能:码盘
     * @author hug 2012-9-4 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String codeTray(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        //HttpSession httpsession = request.getSession(false);
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");     //当前用户  

        putawayBus = null;//new PutawayBusImpl(dao);
        String strMsg = "";
        
        //收货记录Id
        String strTransId = CommonTools.getStrToGbk(request.getParameter("transid"));
        //上架库区ID
        String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));
        //String strLotid = CommonTools.getStrToGbk(request.getParameter("lotid"));       //批次类型ID
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
        
        try
        {
            strMsg = putawayBus.excuteCodeTray(strTransId, whAreaId, strLotatt1,strLotatt2,strLotatt3, strLotatt4, strLotatt5, strLotatt6, strLotatt7, strLotatt8, strLotatt9, strLotatt10, strLotatt11, strLotatt12, strUserCode);
            //跳转页面
            strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_tasklist.jsp";   
            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);      
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]，入库管理==>码盘出错:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]，错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能: 上架
     * @author hug 2012-9-4 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String putaway(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");     //当前用户  
        putawayBus = null;//new PutawayBusImpl(dao);
        String strMsg = "";  
        //上架作业ID
        String strJobId = CommonTools.getStrToGbk(request.getParameter("jobid"));
        //新货位ID
        String strNewSpaceId = CommonTools.getStrToGbk(request.getParameter("newspaceid"));
        //收货单ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));

        try
        { 
            //执行上架
            strMsg = putawayBus.excutePutaway(strJobId, strUserCode, strNewSpaceId); 
            
            String strQueryHQL = putawayBus.getPutawayJobQuerySQL(invoiceid);
            String strCountHQL = putawayBus.getPutawayJobCountSQL(invoiceid);    
            //跳转页面
            strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_execute_job.jsp";   
            PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 5);
            List ls = pt.getLsResult();//查询结果
            request.setAttribute("exResultList", ls);
            request.setAttribute("pagingTool", pt);
            httpsession.setAttribute("inboundPagejob", pt); 
            
            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);      
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]，入库管理==>执行上架出错:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]，错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能:取消上架
     * @author hug 2012-9-12 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String cancelPutaway(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");     //当前用户  

        putawayBus = null;//new PutawayBusImpl(dao);
        String strMsg = "";
        
        //上架作业ID
        String strJobId = CommonTools.getStrToGbk(request.getParameter("jobid"));
        //收货单ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));

        try
        { 
            //取消上架
            strMsg = putawayBus.cancelPutaway(strJobId);
   
            String strQueryHQL = putawayBus.getPutawayJobQuerySQL(invoiceid);
            String strCountHQL = putawayBus.getPutawayJobCountSQL(invoiceid);    
            //跳转页面
            strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_execute_job.jsp";   
            PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 5);
            List ls = pt.getLsResult();//查询结果
            request.setAttribute("exResultList", ls);
            request.setAttribute("pagingTool", pt);
            httpsession.setAttribute("inboundPagejob", pt);
            
            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);      
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]，入库管理==>取消上架出错:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]，错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }

}
