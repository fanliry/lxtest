package com.wms3.bms.standard.entity.outbound;

/**
 * ����: ����װ��
 * @author hug 2012-10-11
 * @since WMS 3.0
 */
public class OutboundPacking  implements java.io.Serializable{
    /**  */
    private static final long serialVersionUID = -2930055020058789184L;
    
    protected String packingid;     //ID
    protected String packingno;     //���
    protected String outstockid;    //���ⵥID
    protected String outstockdetailid;//���ⵥ��ϸID
    protected String jobid;         //������ҵID
    protected String customerid;    //�ͻ�ID
    protected String productid;     //��ƷID
    protected double qty;           //����
    
    public OutboundPacking() {

    }
    /** 
     * ����:�ͻ�ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getCustomerid() {
        return customerid;
    }
    /**
     * ����:�ͻ�ID
     * @author hug 2012-10-11 
     * @param customerid
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
    /**
     * ����:������ҵID
     * @author hug 2012-10-11 
     * @return
     */
    public String getJobid() {
        return jobid;
    }
    /**
     * ����:������ҵID
     * @author hug 2012-10-11 
     * @param jobid
     */
    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
    /**
     * ����:���ⵥ��ϸID
     * @author hug 2012-10-11 
     * @return
     */
    public String getOutstockdetailid() {
        return outstockdetailid;
    }
    /**
     * ����:���ⵥ��ϸID
     * @author hug 2012-10-11 
     * @param outstockdetailid
     */
    public void setOutstockdetailid(String outstockdetailid) {
        this.outstockdetailid = outstockdetailid;
    }
    /**
     * ����:���ⵥID
     * @author hug 2012-10-11 
     * @return
     */
    public String getOutstockid() {
        return outstockid;
    }
    /**
     * ����:���ⵥID
     * @author hug 2012-10-11 
     * @param outstockid
     */
    public void setOutstockid(String outstockid) {
        this.outstockid = outstockid;
    }
    /**
     * ����:ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getPackingid() {
        return packingid;
    }
    /**
     * ����:ID
     * @author hug 2012-10-11 
     * @param packingid
     */
    public void setPackingid(String packingid) {
        this.packingid = packingid;
    }
    /**
     * ����:���
     * @author hug 2012-10-11 
     * @return
     */
    public String getPackingno() {
        return packingno;
    }
    /** 
     * ����:���
     * @author hug 2012-10-11 
     * @param packingno
     */
    public void setPackingno(String packingno) {
        this.packingno = packingno;
    }
    /**
     * ����:��ƷID
     * @author hug 2012-10-11 
     * @return
     */
    public String getProductid() {
        return productid;
    }
    /**
     * ����:��ƷID
     * @author hug 2012-10-11 
     * @param productid
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }
    /** 
     * ����:����
     * @author hug 2012-10-11 
     * @return
     */
    public double getQty() {
        return qty;
    }
    /**
     * ����:����
     * @author hug 2012-10-11 
     * @param qty
     */
    public void setQty(double qty) {
        this.qty = qty;
    }
    
    

}
