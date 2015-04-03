package com.wms3.bms.standard.entity.outbound;

/**
 * 描述: 出库复核
 * @author hug 2012-10-11
 * @since WMS 3.0
 */
public class OutboundCrosscheck implements java.io.Serializable{

    /**  */
    private static final long serialVersionUID = -7990874026639354903L;
    
    protected String crosscheckid;  //ID
    protected String crosstype;     //复核类型 1:按单据复核; 2:按分配的作业复核
    protected String outstockid;    //出库单ID
    protected String outstockdetailid;//出库单详细ID
    protected String jobid;         //出库作业ID
    protected String jobdetailid;   //出库作业详细ID
    protected String customerid;    //客户ID
    protected String productid;     //产品ID
    protected double qtyscan;       //扫描数量
    protected String checktime;     //复核时间
    protected String checkwho;      //复核人
    protected String remark;        //备注
    
    //派生属性
    private String m_strProductName;    //品名
    
    public OutboundCrosscheck() {
        
    }

    /**
     * 功能:复核时间
     * @author hug 2012-10-11 
     * @return
     */
    public String getChecktime() {
        return checktime;
    }

    /**
     * 功能: 设置复核时间
     * @author hug 2012-10-11 
     * @param checktime
     */
    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    /**
     * 功能: 获得复核人
     * @author hug 2012-10-11 
     * @return
     */
    public String getCheckwho() {
        return checkwho;
    }

    /** 
     * 功能: 设置复核人
     * @author hug 2012-10-11 
     * @param checkwho
     */
    public void setCheckwho(String checkwho) {
        this.checkwho = checkwho;
    }

    /**
     * 功能: 获得ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getCrosscheckid() {
        return crosscheckid;
    }

    /**
     * 功能: 设置ID
     * @author hug 2012-10-11 
     * @param crosscheckid
     */
    public void setCrosscheckid(String crosscheckid) {
        this.crosscheckid = crosscheckid;
    }

    /**
     * 功能: 获得复核类型 1:按单据复核; 2:按分配的作业复核
     * @author hug 2012-10-11 
     * @return
     */
    public String getCrosstype() {
        return crosstype;
    }

    /** 
     * 功能:  设置复核类型 1:按单据复核; 2:按分配的作业复核
     * @author hug 2012-10-11 
     * @param crosstype
     */
    public void setCrosstype(String crosstype) {
        this.crosstype = crosstype;
    }

    /** 
     * 功能: 获得客户ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getCustomerid() {
        return customerid;
    }

    /**
     * 功能:  设置客户ID
     * @author hug 2012-10-11 
     * @param customerid
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    /**
     * 功能: 获得出库作业ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getJobid() {
        return jobid;
    }

    /**
     * 功能:  设置出库作业ID
     * @author hug 2012-10-11 
     * @param jobid
     */
    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    /**
     * 功能: 获得出库单详细ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getOutstockdetailid() {
        return outstockdetailid;
    }

    /**
     * 功能:  设置出库单详细ID
     * @author hug 2012-10-11 
     * @param outstockdetailid
     */
    public void setOutstockdetailid(String outstockdetailid) {
        this.outstockdetailid = outstockdetailid;
    }

    /**
     * 功能: 获得出库单ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getOutstockid() {
        return outstockid;
    }

    /**
     * 功能: 设置出库单ID
     * @author hug 2012-10-11 
     * @param outstockid
     */
    public void setOutstockid(String outstockid) {
        this.outstockid = outstockid;
    }

    /**
     * 功能: 获得产品ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getProductid() {
        return productid;
    }

    /**
     * 功能: 设置产品ID
     * @author hug 2012-10-11 
     * @param productid
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }

    /**
     * 功能: 获得扫描数量
     * @author hug 2012-10-11 
     * @return
     */
    public double getQtyscan() {
        return qtyscan;
    }

    /**
     * 功能: 设置扫描数量
     * @author hug 2012-10-11 
     * @param qtyscan
     */
    public void setQtyscan(double qtyscan) {
        this.qtyscan = qtyscan;
    }

    /**
     * 功能: 获得备注
     * @author hug 2012-10-11 
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 功能: 设置备注
     * @author hug 2012-10-11 
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 
     * 功能:获得出库作业详细ID
     * @author hug 2012-10-18 
     * @return
     */
    public String getJobdetailid() {
        return jobdetailid;
    }

    /**
     * 
     * 功能:设置出库作业详细ID
     * @author hug 2012-10-18 
     * @param jobdetailid
     */
    public void setJobdetailid(String jobdetailid) {
        this.jobdetailid = jobdetailid;
    }
    
    public String getM_strProductName() {
        return m_strProductName;
    }

    
    public void setM_strProductName(String productName) {
        m_strProductName = productName;
    }
    

}
