package com.wms3.bms.standard.business.outbound.impl;

import java.util.List;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.outbound.IReviewBus;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.dao.outbound.IOutboundDao;
import com.wms3.bms.standard.dao.outbound.IReviewDao;
import com.wms3.bms.standard.dao.outbound.impl.OutboundDaoImpl;
import com.wms3.bms.standard.dao.outbound.impl.ReviewDaoImpl;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundCrosscheck;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;

/**
 * 描述: 复核业务类
 * @author hug 2012-10-19
 * @since WMS 3.0
 */
public class ReviewBusImpl implements IReviewBus {
    /** 出库单DAO类  */
    protected IOutboundDao outBoundDAO;
    /** 复核DAO类 */
    protected IReviewDao reviewDAO;
    /** 作业DAO类 */
    protected IJobDao jobDAO;
    public ReviewBusImpl(EntityDAO dao){
        outBoundDAO = new OutboundDaoImpl(dao);
        reviewDAO = new ReviewDaoImpl(dao);
        jobDAO = new JobDaoImpl(dao);
    }
    /**
     * 按单据复核
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IReviewBus#invoiceReview(java.lang.String, java.lang.String, double, java.lang.String)
     */
    public String invoiceReview(String invoiceid, String invoicedetailid, double renum, String usercode) throws Exception {
        String strMsg = "操作成功!";
        //同步 
        synchronized (invoicedetailid) {
            OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(invoicedetailid);
            if(outDetail != null){
                double qtyUnChecked = 0;//outDetail.getAssignnum() - Double.parseDouble(outDetail.getReviewnum() == null ? "0":outDetail.getReviewnum());         //未复核数量
                if(renum <= qtyUnChecked){//<=未复核数量
                    //复核
                    OutboundCrosscheck check = new OutboundCrosscheck();
                    check.setCrosstype("1");            //复核类型 1:按单据复核; 2:按分配的作业复核
                    check.setOutstockid(invoiceid);     //出库单ID
                    check.setOutstockdetailid(invoicedetailid);//出库单详细ID
                    //出库作业ID
                    //出库作业详细ID
                    //客户ID
                    //产品ID
                    check.setQtyscan(renum);       //扫描数量
                    check.setChecktime(StrTools.getCurrDateTime(2));//复核时间
                    check.setCheckwho(usercode);   //复核人
                    
                    reviewDAO.addReview(check);
                }else{
                    strMsg = "【复核数量(" + renum + ")】不能大于未复核数量【(" + qtyUnChecked + ")】！";
                }
            }else{
                strMsg = "单据[" + invoiceid + "]的单据详细[" + invoicedetailid + "]不存在！";
            }    
        }
        return strMsg;
    }
    /**
     * 按明细复核
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IReviewBus#detailReview(java.lang.String, java.lang.String, java.lang.String, java.lang.String, double, java.lang.String)
     */
    public String detailReview(String invoiceid, String invoicedetailid, String jobid, String jobdetailid, double renum, String usercode) throws Exception {
        String strMsg = "操作成功!";
        synchronized (jobdetailid) {
            InoutJobdetail jobDetail = jobDAO.getJobDetailByDetailId(jobdetailid);
            if(jobDetail != null){
                //已复核的数量
                OutboundCrosscheck check = reviewDAO.getOutboundCrosscheck(jobid, jobdetailid);
                if(check!= null){
                    double qtyUnChecked = jobDetail.getJobnum() - check.getQtyscan();
                    if( renum <= qtyUnChecked){
                        check.setQtyscan(check.getQtyscan()+renum);//数量
                        check.setCheckwho(usercode);//复核人
                        check.setChecktime(StrTools.getCurrDateTime(2));//复核时间
                        reviewDAO.updateReview(check);//修改复核数量
                    }else{
                        strMsg = "作业详细【"+jobdetailid+"】的【复核数量(" + renum + ")】不能大于未复核数量【(" + qtyUnChecked + ")】！";
                    }
                }else{
                    if(renum <= jobDetail.getJobnum()){
                        //复核
                        check = new OutboundCrosscheck();
                        check.setCrosstype("2");            //复核类型 1:按单据复核; 2:按分配的作业复核
                        check.setOutstockid(invoiceid);     //出库单ID
                        check.setOutstockdetailid(invoicedetailid);//出库单详细ID
                        check.setJobid(jobid);              //出库作业ID
                        check.setJobdetailid(jobdetailid);  //出库作业详细ID
                        //客户ID
                        check.setProductid(jobDetail.getProductid());//产品ID
                        check.setQtyscan(renum);       //扫描数量
                        check.setChecktime(StrTools.getCurrDateTime(2));//复核时间
                        check.setCheckwho(usercode);   //复核人
                        
                        reviewDAO.addReview(check);      
                    }else{
                        strMsg = "作业详细【"+jobdetailid+"】的【复核数量(" + renum + ")】不能大于未复核数量【(" + jobDetail.getJobnum() + ")】！";
                    }    
                }
            }else{
                strMsg = "作业[" + jobid + "]的作业详细[" + jobdetailid + "]不存在！";
            } 
        }
        return strMsg;
    }
    /**
     * 取消单据复核
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IReviewBus#deleteInvoiceReview(java.lang.String, java.lang.String)
     */
    public String deleteInvoiceReview(String invoicedetailid, String usercode) throws Exception {
        String strMsg = "操作成功!";
        //删掉复核的
        reviewDAO.deleteInvoiceReview(invoicedetailid);
        return strMsg;
    }
    /**
     * 取消明细复核
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IReviewBus#deleteDetailReview(java.lang.String, java.lang.String)
     */
    public String deleteDetailReview(String strId, String usercode) throws Exception {
        String strMsg = "操作成功!";
        //删掉复核的
        reviewDAO.deleteReview(strId, "2");
        return strMsg;
    }
    /**
     * 根据出库单ID获得作业详细的查询的SQL
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IReviewBus#getJobDetailQuerySQL(java.lang.String)
     */
    public String getJobDetailQuerySQL(String strInvoiceId) {
        StringBuilder strBud = new StringBuilder();
        strBud.append("select detail, (select chec.qtyscan from OutboundCrosscheck chec where chec.crosstype='2' and chec.jobdetailid=detail.jobdetailid and chec.jobid=detail.jobid) as checknum from InoutJobdetail as detail where detail.linestatus='4' and detail.invoiceid='")
        .append(strInvoiceId).append("' and (detail.assignnum - detail.reviewnum)!=0");
        //未复核数量=作业详细的分配数量-已复核的数量  未复核数量不能为0
        return strBud.toString();
    }
    /**
     * 根据出库单ID获得作业详细的查询总记录的SQL
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IReviewBus#getJobDetailCountSQL(java.lang.String)
     */
    public String getJobDetailCountSQL(String strInvoiceId) {
        StringBuilder strBud = new StringBuilder();
        strBud.append("select count(detail.jobdetailid) from InoutJobdetail as detail where detail.linestatus='4' and detail.invoiceid='")
        .append(strInvoiceId).append("' and (detail.assignnum - detail.reviewnum)!=0");
        return strBud.toString();
    }
    
    public String getReviewQuerySQL(String strInvoiceId) {
        StringBuilder strBud = new StringBuilder();
        strBud.append("from OutboundCrosscheck chec where chec.crosstype='2' and chec.outstockid='")
        .append(strInvoiceId).append("'");
        return strBud.toString();
    }
    
    public String getReviewCountSQL(String strInvoiceId) {
        StringBuilder strBud = new StringBuilder();
        strBud.append("select count(chec.crosscheckid) from OutboundCrosscheck chec where chec.crosstype='2' and chec.outstockid='")
        .append(strInvoiceId).append("'");
        return strBud.toString();
    }
    /**
     * 功能：根据出库单详细ID判断复核信息是否存在
     * @param strId 出库单详细ID
     */
	public boolean getOutBoundCroByOutBoundDelId(String strId)
			throws Exception {
		List<OutboundCrosscheck> ls = null;
		ls = reviewDAO.getOutboundCrossById(strId);
		if (ls!=null && ls.size()>0) {
             return true;				
		}
		return false;
	}
	public boolean isCheckedByHeader(String orderId, String flag) throws Exception{
		return reviewDAO.isCheckedByHeader(orderId, flag);
	}
}
