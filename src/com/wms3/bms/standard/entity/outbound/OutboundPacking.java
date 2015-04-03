package com.wms3.bms.standard.entity.outbound;

/**
 * 描述: 复核装箱
 * @author hug 2012-10-11
 * @since WMS 3.0
 */
public class OutboundPacking  implements java.io.Serializable{
    /**  */
    private static final long serialVersionUID = -2930055020058789184L;
    
    protected String packingid;     //ID
    protected String packingno;     //箱号
    protected String outstockid;    //出库单ID
    protected String outstockdetailid;//出库单详细ID
    protected String jobid;         //出库作业ID
    protected String customerid;    //客户ID
    protected String productid;     //产品ID
    protected double qty;           //数量
    
    public OutboundPacking() {

    }
    /** 
     * 功能:客户ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getCustomerid() {
        return customerid;
    }
    /**
     * 功能:客户ID
     * @author hug 2012-10-11 
     * @param customerid
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
    /**
     * 功能:出库作业ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getJobid() {
        return jobid;
    }
    /**
     * 功能:出库作业ID
     * @author hug 2012-10-11 
     * @param jobid
     */
    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
    /**
     * 功能:出库单详细ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getOutstockdetailid() {
        return outstockdetailid;
    }
    /**
     * 功能:出库单详细ID
     * @author hug 2012-10-11 
     * @param outstockdetailid
     */
    public void setOutstockdetailid(String outstockdetailid) {
        this.outstockdetailid = outstockdetailid;
    }
    /**
     * 功能:出库单ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getOutstockid() {
        return outstockid;
    }
    /**
     * 功能:出库单ID
     * @author hug 2012-10-11 
     * @param outstockid
     */
    public void setOutstockid(String outstockid) {
        this.outstockid = outstockid;
    }
    /**
     * 功能:ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getPackingid() {
        return packingid;
    }
    /**
     * 功能:ID
     * @author hug 2012-10-11 
     * @param packingid
     */
    public void setPackingid(String packingid) {
        this.packingid = packingid;
    }
    /**
     * 功能:箱号
     * @author hug 2012-10-11 
     * @return
     */
    public String getPackingno() {
        return packingno;
    }
    /** 
     * 功能:箱号
     * @author hug 2012-10-11 
     * @param packingno
     */
    public void setPackingno(String packingno) {
        this.packingno = packingno;
    }
    /**
     * 功能:产品ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getProductid() {
        return productid;
    }
    /**
     * 功能:产品ID
     * @author hug 2012-10-11 
     * @param productid
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }
    /** 
     * 功能:数量
     * @author hug 2012-10-11 
     * @return
     */
    public double getQty() {
        return qty;
    }
    /**
     * 功能:数量
     * @author hug 2012-10-11 
     * @param qty
     */
    public void setQty(double qty) {
        this.qty = qty;
    }
    
    

}
