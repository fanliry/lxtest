package com.wms3.bms.standard.action.rf;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.job.IJobBus;
import com.wms3.bms.standard.business.job.impl.JobBusImpl;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 描述: RF采集生成作业
 * @author hug 2012-9-4
 * @since WMS 3.0
 */
public class RFJobAction extends AbstractAction{
    protected IJobBus jobBus;   //作业业务类
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        //收货单ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        


        try{

            
            
            request.getRequestDispatcher(strUrl).forward(request, response);   
        }catch(Exception er)
        {
            Logger.error("RF==>RF查询出错:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能:RF生成作业
     * @author hug 2012-9-10 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String addricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");     //当前用户 
        
        String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));// 仓库ID 
        String strTrayCode = CommonTools.getStrToGbk(request.getParameter("traycode"));  //托盘条码
        //库区ID
        String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));
        
        String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));//品名规格
        String strPackId = CommonTools.getStrToGbk(request.getParameter("packid"));      //包装   
        String strEunit = CommonTools.getStrToGbk(request.getParameter("eunit"));        //单位
        
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
        
        String num = CommonTools.getStrToGbk(request.getParameter("num"));              //作业数量
        String weight = CommonTools.getStrToGbk(request.getParameter("weight"));        //作业毛重
        String netweight = CommonTools.getStrToGbk(request.getParameter("netweight"));  //作业净重
        String volume = CommonTools.getStrToGbk(request.getParameter("volume"));        //作业体积
        
        String ownerId = CommonTools.getStrToGbk(request.getParameter("ownerid"));        //货主
        
        jobBus = new JobBusImpl(dao);
        String strMsg = "";
        try{
            
            String submitFlag = (String)httpsession.getAttribute("submitFlag"); //判断是否为重复提交的标志变量
            if(submitFlag == "begin")
            {
                InoutJob job = new InoutJob();
                job.setJobtype("1");        //业类型1-入库2-出库3-移库
                job.setOnLineType("1");     //联机模式 1.联机 2.脱机
                job.setIsaccount("Y");      //是否记账 Y.是 N.否
                job.setStatus("1");         //作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
                job.setPriority(10);        //优先级
                job.setCreatetime(StrTools.getCurrDateTime(2));//生成时间
                job.setJobpos("1");             //作业方向 1.入库端 2.出库端
                //job.setShiftid(strShiftId);   //作业班次
                job.setTraycode(strTrayCode);   //托盘条码
                job.setWarehouseid(warehouseid);//仓库ID
                job.setTcargoWhareaId(whAreaId);//库区ID
                job.setOpManId(strUserCode);    //操作人
                job.setInOutType("1");          //入出类型 1上架入库 2.出库
                job.setInvoicetype("1");        //1:先有作业后生成单据;2:先有单据后生成作业
                //获得作业ID   收货作业SJZY 的前缀 SJ
                String strID = SeqTool.getID("RKZY", "RK", dao); //作业ID
                job.setJobid(strID);            //设置作业ID
                
                InoutJobdetail jobDetail = new InoutJobdetail();
                //作业详细ID
                String strJobDetailId = SeqTool.getDetailId(strID, "1");
                jobDetail.setJobdetailid(strJobDetailId);   //作业详细ID
                jobDetail.setJobid(strID);                  //作业ID
                jobDetail.setLinestatus("0");               //状态0:新建2完成
                jobDetail.setProductid(strProductId);//品名
                jobDetail.setPackid(strPackId); //包装ID
                jobDetail.setPunit(strEunit);   //单位
                jobDetail.setLotid(strLotid);   //批次类型ID
                jobDetail.setJobnum(Double.parseDouble(num));           //数量
                jobDetail.setNetweight(Double.parseDouble(netweight));  //净重
                jobDetail.setWeight(Double.parseDouble(weight));        //毛量
                jobDetail.setVolume(Double.parseDouble(volume));        //体积   
                jobDetail.setAssignnum(Double.parseDouble(num));             //分配数量
                jobDetail.setAssignnetweight(Double.parseDouble(netweight)); //分配净重
                jobDetail.setAssignweight(Double.parseDouble(weight));       //分配毛量
                jobDetail.setAssignvolume(Double.parseDouble(volume));       //分配体积
                //jobDetail.setOwnerId(trans.getOwnerid());                  //货主
                jobDetail.setIsinvoice("N");                //是否已生成单据
                //jobDetail.setTransreceiptid(strTransId);    //收货记录ID
    /*            jobDetail.setLotatt1(strLotatt1);   //批次属性1
                jobDetail.setLotatt2(strLotatt2);   //批次属性2
                jobDetail.setLotatt3(strLotatt3);   //批次属性3
                jobDetail.setLotatt4(strLotatt4);   //批次属性4
                jobDetail.setLotatt5(strLotatt5);   //批次属性5
                jobDetail.setLotatt6(strLotatt6);   //批次属性6
                jobDetail.setLotatt7(strLotatt7);   //批次属性7
                jobDetail.setLotatt8(strLotatt8);   //批次属性8
                jobDetail.setLotatt9(strLotatt9);   //批次属性9
                jobDetail.setLotatt10(strLotatt10); //批次属性10
                jobDetail.setLotatt11(strLotatt11); //批次属性11
                jobDetail.setLotatt12(strLotatt12); //批次属性12
*/                jobDetail.setOwnerId(ownerId);      //货主
                strMsg = jobBus.addOneJob(job, jobDetail);
                
                // 设置标志变量SubmitFlag值为“finish”，表示已经提交
                httpsession.setAttribute("submitFlag", "finish");
  
            }else{
                strMsg = "已提交了，不能重复提交！";
            }    
            //跳转页面
            strUrl = "/rf/sc_inbound.jsp";   

            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);   
        }catch(Exception er)
        {
            Logger.error("RF==>增加作业出错:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }

}
