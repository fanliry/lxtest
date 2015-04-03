package com.wms3.bms.standard.action.sys;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.business.base.impl.BatchBusImpl;
import com.wms3.bms.standard.business.inventory.impl.InventoryBusImpl;
import com.wms3.bms.standard.business.job.IJobBus;
import com.wms3.bms.standard.business.job.impl.JobBusImpl;
import com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao;
import com.wms3.bms.standard.dao.base.IBaseProductDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseProductDaoImpl;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.dao.outbound.IOutboundDao;
import com.wms3.bms.standard.dao.outbound.IReviewDao;
import com.wms3.bms.standard.dao.outbound.impl.OutboundDaoImpl;
import com.wms3.bms.standard.dao.outbound.impl.ReviewDaoImpl;
import com.wms3.bms.standard.entity.base.BaseBatch;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseProduct;
import com.wms3.bms.standard.entity.inbound.lxyy.BindingRecord;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.service.BMSService;

/**
 * 
 * 描述: DWR验证判断
 * @author yao 2015-5-15
 * @since WMS 3.0
 */
public class JudgmentTool {
    
    /**
     * 功能：托盘条码验证
     * @param strTrayCode   托盘条码
     * @param session
     * @return
     */
    public String isTrayCodeUse(String strTrayCode, HttpSession session)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
            String strSql = "select count(job.jobid) from InoutJob as job where job.traycode='" + strTrayCode + "' and job.status in('1','2','3') and job.inOutType in('0','1')";
            int iCount = dao.searchEntitieCount(strSql);
            if(iCount > 0)
            {
                strMsg = "未完成作业正在使用该托盘条码，无法绑定！";
                return strMsg;
            }
            
            //验证仓库中是否有该托盘条码
            String strSql1 = "select count(st.inventoryid) from InventoryStorage as st where st.traycode='" + strTrayCode + "'";
            iCount = dao.searchEntitieCount(strSql1);
            if(iCount > 0)
            {
            	strMsg = "库存中正在使用该托盘条码，无法绑定！";
                return strMsg;
            }
            
        }catch(Exception er)
        {
            strMsg = "托盘条码验证失败!请检验托盘条码是否可用!";
            Logger.error("托盘条码验证失败:" + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 功能：根据托盘条码收货
     * @param strTrayCode   托盘条码
     * @param invoiceid     入库单号
     * @return
     */
    public String isRecebyTrayCode(String invoiceid,String strTrayCode)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	//防止同一托盘重复收货
            //String strSql0 = "select ta.instockid from InboundHeader as ta,InboundDetail as tb where tb.traycode='" + strTrayCode + "' and tb.status in('1') and ta.instockid=tb.instockid and ta.instockid='"+invoiceid+"' order by ta.instockid desc";
        	String strSql0 = "select ta.instockid from InboundHeader as ta,InboundDetail as tb where tb.traycode='" + strTrayCode + "' and tb.status in('1') and ta.instockid=tb.instockid and ta.instockid='"+invoiceid+"' order by ta.instockid desc";
        	List lsList = dao.searchEntities(strSql0);
            if(lsList!=null && lsList.size()==1){	
            }else{
            	 strMsg = "不能重复收货!";
            }
            
        }catch(Exception er)
        {
            strMsg = "根据托盘条码收货失败!请检验托盘条码!";
            Logger.error("托盘条码验证失败:" + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 功能：判断该单据是否属于该仓库
     * @param invoiceid     入库单号
     * @return
     */
    public String isToWarehouseid(String invoiceid,String warehouseid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
            String strSql0 = "select * from OutboundInvoiceHeader as ta where ta.outstockid='" + invoiceid + "' and ta.warehouseid='"+warehouseid+"'  and ta.outstatus !='8' ";
            List lsList = dao.searchEntities(strSql0);
            if(lsList!=null && lsList.size()==1){	
            	
            }else{
            	 strMsg = "该单据不属于该仓库 或 单据已经作废!";
            }
            
        }catch(Exception er)
        {
            strMsg = "判断该单据是否属于该仓库!";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 功能：判断批次信息是否与批次类型中所设置的批次长度一样
     * @param invoiceid     入库单号
     * @return
     */
    public String isLotInfoLength(String lotid,String lotinfo)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        BatchBusImpl batchBus = new BatchBusImpl(dao);
        try
        {
        	BaseBatch batch = batchBus.getBatchById(lotid);
        	if(batch!=null && batch.getBatchlength()!=null){
        		if(batch.getBatchlength().intValue() == lotinfo.length()){
        			
        		}else{
        			strMsg = "输入批号信息长度应该为"+batch.getBatchlength().intValue()+"!";
        		}
        	}
            
        }catch(Exception er)
        {
            strMsg = "判断该单据是否属于该仓库!";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
	/**
	 * 作废作业
	 * @param jobids
	 * @return
	 */
    public String cancelJobs(String jobid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        { 
        	InoutJob job = new InoutJob().getJobByJobid(jobid,dao);
        	if(job!=null){
        		 if(job.getStatus().equals("1") || job.getStatus().equals("2")){
        		 }else{
        			 strMsg = "只有未执行或者待执行作业才可以作废";
        		 }
        	}else{
        		strMsg = "没有找到该作业";
        	}
            
        }catch(Exception er)
        {
            strMsg = "作废作业"+jobid+"验证失败!";
            Logger.error(strMsg+ er.getMessage());
        }
        return strMsg;
    }
	/**
	 * 复核作业验证
	 * @param jobids
	 * @return
	 */
    public String CheckJobs(String jobid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        { 
        	InoutJob job = new InoutJob().getJobByJobid(jobid,dao);
        	InoutJobdetail detail = new InoutJobdetail().getJobDetailByJobid(jobid,dao);
        	if(job!=null){
        		 if(!job.getStatus().equals("4")){
        			 strMsg = "只有完成作业才可以复核";
        		 }else if(job.getStatus().equals("4")){
        			 if(detail!=null && detail.getIsreview()!=null && detail.getIsreview().equals("Y")){
        				 strMsg = "该作业已经成功复核 无需再次复核";
        			 } 
        		 }
        	}else{
        		strMsg = "没有找到该作业";
        	}
        	
        }catch(Exception er)
        {
            strMsg = "复核作业"+jobid+"验证失败!";
            Logger.error(strMsg+ er.getMessage());
        }
        return strMsg;
    }
    /**
	 * 初始化入库作业
	 * @param jobids
	 * @return
	 */
    public String initializeJobs(String jobid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        { 
        	InoutJob job = new InoutJob().getJobByJobid(jobid,dao);
        	if(job!=null){
        		 if(!job.getStatus().equals("3")){
        			 strMsg = "只有正执行入库作业才可以初始化";
        		 }
        	}else{
        		strMsg = "没有找到该作业";
        	}
            
        }catch(Exception er)
        {
            strMsg = "初始化入库作业"+jobid+"验证失败!";
            Logger.error(strMsg+ er.getMessage());
        }
        return strMsg;
    }
	/**
	 * 移库到暂存验证
	 * @param jobids
	 * @return
	 */
    public String WaitJobs(String jobid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        { 
        	InoutJob job = new InoutJob().getJobByJobid(jobid,dao);
        	if(job!=null){
        		 if(job.getStatus().equals("1") && job.getInvoicetype().equals("1")){
        		 }else{
        			 strMsg = "非未执行入库作业不能移库到暂存";
        		 }
        	}else{
        		strMsg = "没有找到该作业";
        	}
            
        }catch(Exception er)
        {
            strMsg = "作业号为"+jobid+"移库到暂存验证失败!";
            Logger.error(strMsg+ er.getMessage());
        }
        return strMsg;
    }
	/**
	 * 完成作业
	 * @param jobids
	 * @return
	 */
    public String finishJobs(String jobid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        { 
        	InoutJob job = new InoutJob().getJobByJobid(jobid,dao);
        	if(job!=null){
//        		 if(!job.getStatus().equals("3")){
//        			 strMsg = "只有正执行的作业才能完成";
//        		 }
	       		 if(job.getStatus().equals("4")){
	   			 strMsg = "您选择的是已完成的作业！";
	       		 }
	       		 if(job.getStatus().equals("5")){
	   			 strMsg = "您选择的是作废的作业！";
	       		 }
	       		 if(job.getStatus().equals("6")){
	   			 strMsg = "您选择的是异常作业!!";
	       		 }
        	}else{
        		strMsg = "没有找到该作业";
        	}
            
        }catch(Exception er)
        {
            strMsg = "完成作业"+jobid+"验证失败!";
            Logger.error(strMsg+ er.getMessage());
        }
        return strMsg;
    }
    
    /**
     * 功能:发货确认，发货检测
     * @param strOutId      出库单据号
     * @return
     */
    public String sendCheck(String strOutId)
    {
        String strMsg = "";
        EntityDAO dao = BMSService.getm_EntityDAO();
        IJobDao jobDAO = new JobDaoImpl(dao);
        IOutboundDao outBoundDAO = new OutboundDaoImpl(dao);
        try
        {
            //检测该单据未完成的作业数(不为4:完成和5:作废)
            int iJob = jobDAO.getUnfinishedJob(strOutId, null);
            int iJobFH = jobDAO.getUncheckUpJob(strOutId, null);
            if(iJob != 0)
            {
                strMsg += "该单据有【" + iJob + "】条作业未完成！\r\r";
            }
            if(iJobFH != 0)
            {
                strMsg += "该单据有【" + iJobFH + "】条作业未复核！\r\r";
            }
            //检测单据详细的开单数量是否完成
            List lsDetail = outBoundDAO.getOutBoundDetailsById(strOutId);
            if(lsDetail != null && lsDetail.size()>0)
            {
                OutboundInvoiceDetail outDetail = null;
                for(int i = 0; i < lsDetail.size(); i++)
                {
                    outDetail = (OutboundInvoiceDetail)lsDetail.get(i);
                    if(!outDetail.getLinestatus().trim().equals("7"))//不为确认状态
                    {
                        //单据数量 - 拣货数量
                        //double iNum = outDetail.getInvoicenum() - outDetail.getPicknum();
                    	double iNum = outDetail.getInvoicenum() - outDetail.getAssignnum();
                        //开单数量减去分配的数量
                        if(iNum > 0)
                        {
                            strMsg += "产品【" + outDetail.getM_strProductName() + "】比开单数量少【" + iNum + "】！\r";
                        }else if(iNum < 0)
                        {
                            strMsg += "产品【" + outDetail.getM_strProductName() + "】比开单数量多【" + (-iNum) + "】！\r";
                        }   
                        
                    }
                }
            }
            
            if(strMsg == "")
            {
                strMsg = "Y";
            }
            else
            {
                strMsg = strMsg.substring(0, strMsg.length()-1);
            }
            
        }catch(Exception er)
        {
            Logger.error("发货检测失败:" + er.getMessage());
        }
        
        return strMsg;
    }
    /**
     * 功能:发货确认，发货检测
     * @param strOutId      出库单据号
     * @return
     */
    public String sendCheck1(String strOutId)
    {
        String strMsg = "";
        EntityDAO dao = BMSService.getm_EntityDAO();
        IJobDao jobDAO = new JobDaoImpl(dao);
        IOutboundDao outBoundDAO = new OutboundDaoImpl(dao);
        try
        {
            //检测该单据未完成的作业数(不为4:完成和5:作废)
            int iJob = jobDAO.getUnfinishedJob(strOutId, null);
            int iJobFH = jobDAO.getUncheckUpJob(strOutId, null);
            if(iJob != 0)
            {
                strMsg += "该单据有【" + iJob + "】条作业未完成！\r\r";
            }
            if(iJobFH != 0)
            {
                strMsg += "该单据有【" + iJobFH + "】条作业未复核！\r\r";
            }
            if(strMsg == "")
            {
                strMsg = "Y";
            }
            else
            {
                strMsg = strMsg.substring(0, strMsg.length()-1);
            }
            
        }catch(Exception er)
        {
            Logger.error("发货检测失败:" + er.getMessage());
        }
        
        return strMsg;
    }
    /**
     * 功能:发货确认，发货检测
     * @param strOutId      出库单据号
     * @return
     */
    public String sendCheck2(String strOutId)
    {
        String strMsg = "";
        EntityDAO dao = BMSService.getm_EntityDAO();
        IJobDao jobDAO = new JobDaoImpl(dao);
        IOutboundDao outBoundDAO = new OutboundDaoImpl(dao);
        try
        {
            //检测单据详细的开单数量是否完成
            List lsDetail = outBoundDAO.getOutBoundDetailsById(strOutId);
            if(lsDetail != null && lsDetail.size()>0)
            {
                OutboundInvoiceDetail outDetail = null;
                for(int i = 0; i < lsDetail.size(); i++)
                {
                    outDetail = (OutboundInvoiceDetail)lsDetail.get(i);
                    if(!outDetail.getLinestatus().trim().equals("7"))//不为确认状态
                    {
                        //单据数量 - 拣货数量
                        //double iNum = outDetail.getInvoicenum() - outDetail.getPicknum();
                    	double iNum = outDetail.getInvoicenum() - outDetail.getAssignnum();
                        //开单数量减去分配的数量
                        if(iNum > 0)
                        {
                            strMsg += "产品【" + outDetail.getM_strProductName() + "】比开单数量少【" + iNum + "】！\r";
                        }else if(iNum < 0)
                        {
                            strMsg += "产品【" + outDetail.getM_strProductName() + "】比开单数量多【" + (-iNum) + "】！\r";
                        }   
                        
                    }
                }
            }
            
            if(strMsg == "")
            {
                strMsg = "Y";
            }
            else
            {
                strMsg = strMsg.substring(0, strMsg.length()-1);
            }
            
        }catch(Exception er)
        {
            Logger.error("发货检测失败:" + er.getMessage());
        }
        
        return strMsg;
    }
    /**
     * 功能:发货确认，发货检测
     * @param strOutId      出库单据号
     * @param strOutDetailId    出库单据详细ID
     * @return
     */
    public String sendOneCheck(String strOutId, String strOutDetailId)
    {
        String strMsg = "";
        EntityDAO dao = BMSService.getm_EntityDAO();
        IJobDao jobDAO = new JobDaoImpl(dao);
        IOutboundDao outBoundDAO = new OutboundDaoImpl(dao);
        try
        {
            //检测单据详细的开单数量是否完成
            OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(strOutDetailId);
            if(outDetail != null && !outDetail.getLinestatus().trim().equals("7"))//不为确认状态
            {
                //检测该单据未完成的作业数(不为4:完成和5:作废)
                int iJob = jobDAO.getUnfinishedJob(strOutId, strOutDetailId);
                int iJobFH = jobDAO.getUncheckUpJob(strOutId, null);
                if(iJob != 0)
                {
                    strMsg += "该单据有【" + iJob + "】条作业未完成！\r\r";
                }
                if(iJobFH != 0)
                {
                    strMsg += "该单据有【" + iJob + "】条作业未复核！\r\r";
                }
                //单据数量 - 拣货数量
               // double iNum = outDetail.getInvoicenum() - outDetail.getPicknum();
                //单据数量 - 分配数量
                double iNum = outDetail.getInvoicenum() - outDetail.getAssignnum();
                 //开单数量减去分配的数量
                 if(iNum > 0)
                 {
                        strMsg += "产品【" + outDetail.getM_strProductName() + "】比开单数量少【" + iNum + "】！\r";
                 }else if(iNum < 0)
                 {
                         strMsg += "产品【" + outDetail.getM_strProductName() + "】比开单数量多【" + (-iNum) + "】！\r";
                 }   
                        
            }

            if(strMsg == "")
            {
                strMsg = "Y";
            }
            else
            {
                strMsg = strMsg.substring(0, strMsg.length()-1);
            }
            
        }catch(Exception er)
        {
            Logger.error("发货检测失败:" + er.getMessage());
        }
        
        return strMsg;
    }
    /**
     * 功能:发货确认，发货检测
     * @param strOutId      出库单据号
     * @param strOutDetailId    出库单据详细ID
     * @return
     */
    public String sendOneCheck1(String strOutId, String strOutDetailId)
    {
        String strMsg = "";
        EntityDAO dao = BMSService.getm_EntityDAO();
        IJobDao jobDAO = new JobDaoImpl(dao);
        IOutboundDao outBoundDAO = new OutboundDaoImpl(dao);
        try
        {
            //检测单据详细的开单数量是否完成
            OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(strOutDetailId);
            if(outDetail != null && !outDetail.getLinestatus().trim().equals("7"))//不为确认状态
            {
                //检测该单据未完成的作业数(不为4:完成和5:作废)
                int iJob = jobDAO.getUnfinishedJob(strOutId, strOutDetailId);
                int iJobFH = jobDAO.getUncheckUpJob(strOutId, null);
                if(iJob != 0)
                {
                    strMsg += "该单据有【" + iJob + "】条作业未完成！\r\r";
                }
                if(iJobFH != 0)
                {
                    strMsg += "该单据有【" + iJob + "】条作业未复核！\r\r";
                }             
            }

            if(strMsg == "")
            {
                strMsg = "Y";
            }
            else
            {
                strMsg = strMsg.substring(0, strMsg.length()-1);
            }  
        }catch(Exception er)
        {
            Logger.error("发货检测失败:" + er.getMessage());
        }
        
        return strMsg;
    }
    /**
     * 功能:发货确认，发货检测
     * @param strOutId      出库单据号
     * @param strOutDetailId    出库单据详细ID
     * @return
     */
    public String sendOneCheck2(String strOutId, String strOutDetailId)
    {
        String strMsg = "";
        EntityDAO dao = BMSService.getm_EntityDAO();
        IJobDao jobDAO = new JobDaoImpl(dao);
        IOutboundDao outBoundDAO = new OutboundDaoImpl(dao);
        try
        {
            //检测单据详细的开单数量是否完成
            OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(strOutDetailId);
            if(outDetail != null && !outDetail.getLinestatus().trim().equals("7"))//不为确认状态
            {
                //单据数量 - 分配数量
                double iNum = outDetail.getInvoicenum() - outDetail.getAssignnum();
                 //开单数量减去分配的数量
                 if(iNum > 0)
                 {
                        strMsg += "产品【" + outDetail.getM_strProductName() + "】比开单数量少【" + iNum + "】！\r";
                 }else if(iNum < 0)
                 {
                         strMsg += "产品【" + outDetail.getM_strProductName() + "】比开单数量多【" + (-iNum) + "】！\r";
                 }   
                        
            }

            if(strMsg == "")
            {
                strMsg = "Y";
            }
            else
            {
                strMsg = strMsg.substring(0, strMsg.length()-1);
            }
            
        }catch(Exception er)
        {
            Logger.error("发货检测失败:" + er.getMessage());
        }
        
        return strMsg;
    }
    /**
     * 功能：判断订单是否复核（1:按单据复核; 2:按分配的作业复核）
     * @author hug 2012-10-12 
     * @param orderId
     * @param flag
     * @return
     */
    public int isOrderChecked(String orderId, String flag)
    {
        EntityDAO dao = BMSService.getm_EntityDAO();
        IReviewDao reviewDao = new ReviewDaoImpl(dao);
        boolean result = false;
        try{
            if(flag!=null && flag.equals("header"))
            {
                result = reviewDao.isCheckedByHeader(orderId, "1");
            }
            if(flag!=null && flag.equals("detail"))
            {
                result = reviewDao.isCheckedByHeader(orderId, "2");
            }
        }catch(Exception er)
        {
            Logger.error("判断订单是否复核（1:按单据复核; 2:按分配的作业复核）失败：" + er.getMessage());
        }
        if(result)
        {
            return 1;
        }
        else
        {
            return 0;
        }   
    }
    /**
     * 功能：入库管理==>生产入库管理==>入库申请单管理==>审核验证（只有新建状态才可以审核）
     * @param instockid   申请单号
     * @param strUserCode   审核人
     * @return
     */
    public String isAudit(String instockid,String strUserCode)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundRequestInvoiceHeader header = new InboundRequestInvoiceHeader();
        	header = header.getDaoQueryHql(instockid,dao);
        	if(header!=null && header.getInstatus()!=null && header.getInstatus().equals("1")){ //新建
        		strMsg = "Y";
        	}else{
        		strMsg = "只有新建状态才可以审核";
        	}
        	if(strUserCode==null || strUserCode.equals("")){
        		strMsg = "登陆已过时 请重新登陆用户";
        	}else{
        		if(header!=null && header.getCreatemanid()!=null){
            		if(header.getCreatemanid().equals(strUserCode)&&!strUserCode.equals("root")){
            			strMsg = "制单人与审核人不能为同一人";
            		}
            	}
        	}
        	
            
        }catch(Exception er)
        {
            strMsg = "入库管理==>生产入库管理==>入库申请单管理==>审核验证失败";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 功能：RF-绑定（成品入库只有审核及绑定状态的单据才可以再次绑定）（非成品入库只有新建及绑定状态的单据才可以再次绑定）
     * @param instockid   申请单号
     * @return
     */
    public String isAuditAbind(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundRequestInvoiceHeader header = new InboundRequestInvoiceHeader();
        	header = header.getDaoQueryHql(instockid,dao);
        	if(header!=null && header.getInstatus()!=null && (((header.getInvoicetypeid().equals("9") && header.getInstatus().equals("2")||header.getInstatus().equals("3")))||(!header.getInvoicetypeid().equals("9") && (header.getInstatus().equals("1")||header.getInstatus().equals("3"))))){ //（成品入库只有审核及绑定状态的单据才可以再次绑定）（非成品入库只有新建及绑定状态的单据才可以再次绑定）
        		strMsg = "Y";
        	}else{
        		strMsg = "只有审核及绑定状态的单据才可以再次绑定";
        	}
            
        }catch(Exception er)
        {
            strMsg = "RF-绑定失败";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 功能 取消绑定验证
     * @param bangrecordid   绑定记录id
     * @return
     */
    public String isCancelbind(String bangrecordid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	BindingRecord record = new BindingRecord();
        	record = record.getQueryHQLByRecordid(bangrecordid,dao);
        	if(record!=null && record.getStatus()!=null && record.getStatus().equals("1")){ // 1,未生成单据
        		strMsg = "Y";
        	}else{
        		strMsg = "只有未生成单据的绑定记录才可以取消绑定";
        	}
            
        }catch(Exception er)
        {
            strMsg = "取消绑定失败";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 功能 验证是否为调度任务的作业
     * @param jobid   作业id
     * @return
     */
    public String isTaskJob(String jobid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	if(jobid != null && !jobid.equals("")){
        			String strSql = " from InoutJob as t where t.jobid='" + jobid + "' and t.jobcategory='1'"; // 1代表 立体库作业
        			List ls = dao.searchEntities(strSql);
        			if(ls != null && ls.size() > 0){
        				InoutJob job = (InoutJob)ls.get(0);
        				/*if(job.getInvoicetype().equals("4")){ //如果为移库作业 判断 暂存区是否有该同样的托盘条码，如果有 则提示 且不成功，否则 成功	
        				}*/
        			}else{
        				strMsg = "该作业不是立体库作业";
        			}
    			
    		}else{
    			strMsg = "该作业号为空";
    		}
            
        }catch(Exception er)
        {
            strMsg = "验证是否为调度任务的作业失败";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 功能 验证是否为非调度任务的作业（例如 暂存区作业）
     * @param jobid   作业id
     * @return
     */
    public String isNotTaskJob(String jobid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	if(jobid != null && !jobid.equals("")){
    			String strSql = " from InoutJob as t where t.jobid='" + jobid + "' and t.jobcategory='2'"; // 2代表 暂存区作业
    			List ls = dao.searchEntities(strSql);
    			if(ls != null && ls.size() > 0){	
    			}else{
    				strMsg = "该作业不是暂存区作业";
    			}
    		}else{
    			strMsg = "该作业号为空";
    		}
            
        }catch(Exception er)
        {
            strMsg = "验证是否为非调度任务的作业失败";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 功能：入库管理==>生产入库管理==>入库申请单管理==>取消审核验证（只有审核状态才可以取消审核）
     * @param instockid   申请单号
     * @return
     */
    public String isCancelAudit(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundRequestInvoiceHeader header = new InboundRequestInvoiceHeader();
        	header = header.getDaoQueryHql(instockid,dao);
        	if(header!=null && header.getInstatus()!=null && header.getInstatus().equals("2")){ //审核
        		strMsg = "Y";
        	}else{
        		strMsg = "只有审核状态才可以取消审核";
        	}
            
        }catch(Exception er)
        {
            strMsg = "入库管理==>生产入库管理==>入库申请单管理==>取消审核验证失败";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 功能：入库管理==>生产入库管理==>入库申请单管理==>取消审核验证（只有审核状态才可以取消审核）
     * @param instockid   申请单号
     * @return
     */
    public String isCancelReceive(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundRequestInvoiceHeader header = new InboundRequestInvoiceHeader();
        	header = header.getDaoQueryHql(instockid,dao);
        	if(header!=null && header.getInstatus()!=null && header.getInstatus().equals("2")){ //审核
        		strMsg = "Y";
        	}else{
        		strMsg = "只有审核状态才可以取消审核";
        	}
            
        }catch(Exception er)
        {
            strMsg = "入库管理==>生产入库管理==>入库申请单管理==>取消审核验证失败";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 功能：入库管理==>生产入库管理==>入库申请单管理==>作废验证（只有新建状态才可以作废）
     * @param instockid   申请单号
     * @return
     */
    public String isDeleteAudit(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundRequestInvoiceHeader header = new InboundRequestInvoiceHeader();
        	header = header.getDaoQueryHql(instockid,dao);
        	if(header!=null && header.getInstatus()!=null && header.getInstatus().equals("1")){ //新建
        		strMsg = "Y";
        	}else{
        		strMsg = "只有新建状态才可以作废";
        	}
            
        }catch(Exception er)
        {
            strMsg = "入库管理==>生产入库管理==>入库申请单管理==>作废验证失败";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 功能：入库管理==>生产入库管理==>入库单管理==>作废验证（只有新建状态才可以作废）
     * @param instockid   申请单号
     * @return
     */
    public String isDelete(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundHeader header = new InboundHeader();//
        	header = header.getDaoQueryHql(instockid,dao);
        	if(header!=null && header.getInstatus()!=null && header.getInstatus().equals("1")){ //新建
        		strMsg = "Y";
        	}else{
        		strMsg = "只有新建状态才可以作废";
        	}
            
        }catch(Exception er)
        {
            strMsg = "入库管理==>生产入库管理==>入库单管理==>作废验证失败";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 功能：入库管理==>生产入库管理==>入库申请单管理==>关闭申请单验证（只有绑定状态才可以关闭确认）
     * @param instockid   申请单号
     * @return
     */
    public String isColseAudit(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundRequestInvoiceHeader header = new InboundRequestInvoiceHeader();
        	header = header.getDaoQueryHql(instockid,dao);
        	if(header!=null && header.getInstatus()!=null && header.getInstatus().equals("3")){ //绑定
        		strMsg = "Y";
        	}else{
        		strMsg = "只有绑定状态才可以关闭确认";
        	}
            
        }catch(Exception er)
        {
            strMsg = "入库管理==>生产入库管理==>入库申请单管理==>关闭申请单验证失败";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 功能：入库管理==>生产入库管理==>入库单管理==>确认验证（给出相应的提示）
     * @param instockid   单据号
     * @return
     */
    public String isRKDYZ(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundHeader header = new InboundHeader();
        	header = header.getDaoQueryHql(instockid,dao);
        	
        	if(header!=null&&header.getInstatus()!=null){
        		if(header.getInstatus().equals("3")){
        			strMsg = "该单据已经确认 不能在进行确认";
        			return strMsg;
        		}else if(header.getInstatus().equals("4")){
        			strMsg = "该单据已经作废 不能再进行确认";
        			return strMsg;
        		}	
        	}
        	List ls = new InboundDetail().getQueryNotSJHQLByid(instockid, dao);
    		if(ls!=null && ls.size()>0){
    			strMsg = strMsg+" 还有"+ls.size()+"条作业没有上架完成 不能进行确认";
    			return strMsg;
    		}	
            
        }catch(Exception er)
        {
            strMsg = "入库管理==>生产入库管理==>入库单管理==>确认验证（给出相应的提示）";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 功能：入库管理==>生产入库管理==>入库单管理==>确认验证（给出相应的提示）
     * @param instockid   单据号
     * @return
     */
    public String isRKDconfirm(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundDetail detail = new InboundDetail();
        	List lsList = detail.getQueryHQLByid(instockid, dao);
        	int j=0;
        	if(lsList!=null && lsList.size()>0){
        		for(int i=0;i<lsList.size();i++){
        			detail = (InboundDetail)lsList.get(0);
        			if(detail!=null && detail.getInnum()!=detail.getGetnum() && detail.getStatus().equals("4")){ //上架完成 入库数量与实收数量不一致 显示出来
        				strMsg = strMsg+"绑定id为"+detail.getBandrecordid()+"上架完成作业，产品： "+detail.getProductName()+" 绑定数量为"+detail.getInnum()+" 上架数量为"+detail.getGetnum()+"\r\r";
        				j++;
        			}
        		}
        		
        		if(j>0){
        			strMsg = strMsg+" 是否确认该单据";
        			strMsg = strMsg.substring(1, strMsg.length());
        		}
        	}  
        }catch(Exception er)
        {
            strMsg = "入库管理==>生产入库管理==>入库单管理==>确认验证（给出相应的提示）";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 取消收货验证
     * @param instockdetailid
     * @return
     */
    public String isReceipt(String instockdetailid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundDetail detail = new InboundDetail();
        	detail = detail.getQueryHQLByRecordid(instockdetailid,dao);
        	if(detail!=null && detail.getStatus()!=null && detail.getStatus().equals("2")){ //收货状态
        	}else{
        		strMsg = "只有收货状态才可以取消收货";
        	}
            
        }catch(Exception er)
        {
            strMsg = "取消收货验证失败";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * 验证绑定数量是否复合限定数
     * @param invoiceid
     * @param requestvoicedetailid
     * @param num
     * @return
     */
    public String isNum(String invoiceid ,String requestvoicedetailid,String num){
    	String strBackMsg = "Y";
    	EntityDAO dao = BMSService.getm_EntityDAO();
    	try{
    		//InboundRequestInvoiceHeader inReqInvH = new InboundRequestInvoiceHeader();
    		InboundRequestInvoiceDetail inReqInvD = new InboundRequestInvoiceDetail();
    		IBaseProductDao baseProductDao = new BaseProductDaoImpl(dao);
    		InboundRequestInvoiceDetail inReqInvDt = inReqInvD.getDetailByid(invoiceid, requestvoicedetailid, dao);
    		String productId = inReqInvDt.getProductid();
    		BaseProduct baseProduct =baseProductDao.getProductById(productId);
    		double upperlimit = baseProduct.getUpperlimit();
    		double lowerlimit = baseProduct.getLowerlimit();
    		double num1 = 0;
    		if(num!=null){
    			num1 = Double.valueOf(num);
    		}
    		if(num1<lowerlimit){
    			strBackMsg ="数量低于托盘最小限定数！";
    		}else if(num1>upperlimit){
    			strBackMsg ="数量高于托盘最大限定数！";
    		}else if((num1==lowerlimit&&lowerlimit==0)||(num1==upperlimit&&upperlimit==0)){
    			strBackMsg ="绑定数量不能为0！";
    		}
    	}catch(Exception er)
        {
    		strBackMsg = "数量验证失败！";
            Logger.error(strBackMsg + er.getMessage());
        }
        
    	return strBackMsg;
    }
    
    /**
     * 验证货位是否使用
     * @param invoiceid
     * @param requestvoicedetailid
     * @param num
     * @return
     */
    public String isCarspace(String warehouseid,String platoon, String column,String floor)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
            String strSql0 = "select bc.csstatus from BaseCargospace bc where bc.csplatoon='"+platoon+"' and bc.cscolumn='"+column+"' and bc.csfloor='"+floor+"'"+" and bc.warehouseid='"+warehouseid+"'";
            List lsList = dao.searchEntities(strSql0);
            if(lsList!=null && lsList.size()>0){
            	if(lsList.get(0)!=null){
            		String  status=  (String)lsList.get(0);
            		if(status!=null&&!status.equals("1")){//空货位
            			strMsg = "货位【"+platoon+"排"+column+"列"+floor+"层】上已有货或已经被分配！";
            		}
            	}
            }
        }catch(Exception er)
        {
            strMsg = "货位验证失败!";
            Logger.error("货位验证失败:" + er.getMessage());
        }
        return strMsg;
    }
    
    /**
     * 功能：该输送机是否正在作业
     * @param snumber   输送号
     * @param session
     * @return
     */
    public String isSnumberUse(String snumber, HttpSession session)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
            String strSql = "select count(job.jobid) from InoutJob as job where job.snumber='" + snumber + "' and job.status in('2') and job.inOutType in('1')";
            int iCount = dao.searchEntitieCount(strSql);
            if(iCount > 0)
            {
                strMsg = "该输送机存在待执行作业！";
                return strMsg;
            }
            
        }catch(Exception er)
        {
            strMsg = "输送机验证失败!请检验输送机是否可用!";
            Logger.error("输送机验证失败:" + er.getMessage());
        }
        return strMsg;
    }
    
	/**
	 * 功能：查询排列层是否是所选库区
	 * @param warehouseid
	 * @param whAreaId
	 * @param platoon
	 * @param column
	 * @param floor
	 * @return
	 */
    public String iswhAreaId(String warehouseid, String whAreaId, String platoon, String column, String floor)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	IBaseCargoSpaceDao idao = new BaseCargoSpaceDaoImpl(dao);
        	BaseCargospace space = idao.GetWhCargospace(platoon, column, floor, warehouseid,whAreaId);
            if(!space.getWhAreaId().equals(whAreaId))
            {
                strMsg = "排列层不属于所选库区！";
                return strMsg;
            }
            
        }catch(Exception er)
        {
            strMsg = "查询排列层是否是所选库区失败!";
            Logger.error("查询排列层是否是所选库区失败:" + er.getMessage());
        }
        return strMsg;
    }
    
}
