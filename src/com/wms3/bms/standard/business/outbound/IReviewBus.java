package com.wms3.bms.standard.business.outbound;

/**
 * 描述: 复核业务类
 * @author hug 2012-10-19
 * @since WMS 3.0
 */
public interface IReviewBus {
    /** 
     * 功能: 按单据复核
     * @author hug 2012-10-19 
     * @param invoiceid         出库单ID
     * @param invoicedetailid   出库单详细ID
     * @param renum             复核数量
     * @param usercode          操作人
     * @return
     * @throws Exception
     */
    public String invoiceReview(String invoiceid, String invoicedetailid, double renum, String usercode) throws Exception;
    /**
     * 功能: 取消单据复核
     * @author hug 2012-10-19 
     * @param invoicedetailid     出库单详细ID
     * @param usercode
     * @return
     * @throws Exception
     */
    public String deleteInvoiceReview(String invoicedetailid, String usercode) throws Exception;
    /**
     * 功能: 取消明细复核
     * @author hug 2012-10-19 
     * @param strId     复核ID
     * @param usercode
     * @return
     * @throws Exception
     */
    public String deleteDetailReview(String strId, String usercode) throws Exception;
    /**
     * 功能: 按明细复核
     * @author hug 2012-10-19 
     * @param invoiceid         出库单ID
     * @param invoicedetailid   出库单详细ID
     * @param jobid             作业ID
     * @param jobdetailid       作业详细ID
     * @param renum             复核数量
     * @param usercode          操作人
     * @return
     * @throws Exception
     */
    public String detailReview(String invoiceid, String invoicedetailid, String jobid, String jobdetailid, double renum, String usercode) throws Exception;
    /**
     * 功能: 根据出库单ID获得作业详细的查询的SQL
     * @author hug 2012-10-22 
     * @param strInvoiceId   出库单ID
     * @return
     */
    public String getJobDetailQuerySQL(String strInvoiceId);
    /**
     * 功能:根据出库单ID获得作业详细的查询总记录的SQL
     * @author hug 2012-10-22 
     * @param strInvoiceId    出库单ID
     * @return
     */
    public String getJobDetailCountSQL(String strInvoiceId);
    
    public String getReviewQuerySQL(String strInvoiceId);
    public String getReviewCountSQL(String strInvoiceId);
    /**
     * 功能：根据出库单详细ID判断复核信息是否存在
     * @param strId 出库单详细ID
     */
    public boolean getOutBoundCroByOutBoundDelId(String strId) throws Exception;
    /**
     * 功能：判断订单是否复核（1:按单据复核; 2:按分配的作业复核）
     * @param orderId
     * @param flag
     * @return
     * @throws Exception
     */
    public boolean isCheckedByHeader(String orderId, String flag) throws Exception;
}
