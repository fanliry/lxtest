package com.wms3.bms.standard.entity.outbound;

/**
 * ����: ���⸴��
 * @author hug 2012-10-11
 * @since WMS 3.0
 */
public class OutboundCrosscheck implements java.io.Serializable{

    /**  */
    private static final long serialVersionUID = -7990874026639354903L;
    
    protected String crosscheckid;  //ID
    protected String crosstype;     //�������� 1:�����ݸ���; 2:���������ҵ����
    protected String outstockid;    //���ⵥID
    protected String outstockdetailid;//���ⵥ��ϸID
    protected String jobid;         //������ҵID
    protected String jobdetailid;   //������ҵ��ϸID
    protected String customerid;    //�ͻ�ID
    protected String productid;     //��ƷID
    protected double qtyscan;       //ɨ������
    protected String checktime;     //����ʱ��
    protected String checkwho;      //������
    protected String remark;        //��ע
    
    //��������
    private String m_strProductName;    //Ʒ��
    
    public OutboundCrosscheck() {
        
    }

    /**
     * ����:����ʱ��
     * @author hug 2012-10-11 
     * @return
     */
    public String getChecktime() {
        return checktime;
    }

    /**
     * ����: ���ø���ʱ��
     * @author hug 2012-10-11 
     * @param checktime
     */
    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    /**
     * ����: ��ø�����
     * @author hug 2012-10-11 
     * @return
     */
    public String getCheckwho() {
        return checkwho;
    }

    /** 
     * ����: ���ø�����
     * @author hug 2012-10-11 
     * @param checkwho
     */
    public void setCheckwho(String checkwho) {
        this.checkwho = checkwho;
    }

    /**
     * ����: ���ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getCrosscheckid() {
        return crosscheckid;
    }

    /**
     * ����: ����ID
     * @author hug 2012-10-11 
     * @param crosscheckid
     */
    public void setCrosscheckid(String crosscheckid) {
        this.crosscheckid = crosscheckid;
    }

    /**
     * ����: ��ø������� 1:�����ݸ���; 2:���������ҵ����
     * @author hug 2012-10-11 
     * @return
     */
    public String getCrosstype() {
        return crosstype;
    }

    /** 
     * ����:  ���ø������� 1:�����ݸ���; 2:���������ҵ����
     * @author hug 2012-10-11 
     * @param crosstype
     */
    public void setCrosstype(String crosstype) {
        this.crosstype = crosstype;
    }

    /** 
     * ����: ��ÿͻ�ID
     * @author hug 2012-10-11 
     * @return
     */
    public String getCustomerid() {
        return customerid;
    }

    /**
     * ����:  ���ÿͻ�ID
     * @author hug 2012-10-11 
     * @param customerid
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    /**
     * ����: ��ó�����ҵID
     * @author hug 2012-10-11 
     * @return
     */
    public String getJobid() {
        return jobid;
    }

    /**
     * ����:  ���ó�����ҵID
     * @author hug 2012-10-11 
     * @param jobid
     */
    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    /**
     * ����: ��ó��ⵥ��ϸID
     * @author hug 2012-10-11 
     * @return
     */
    public String getOutstockdetailid() {
        return outstockdetailid;
    }

    /**
     * ����:  ���ó��ⵥ��ϸID
     * @author hug 2012-10-11 
     * @param outstockdetailid
     */
    public void setOutstockdetailid(String outstockdetailid) {
        this.outstockdetailid = outstockdetailid;
    }

    /**
     * ����: ��ó��ⵥID
     * @author hug 2012-10-11 
     * @return
     */
    public String getOutstockid() {
        return outstockid;
    }

    /**
     * ����: ���ó��ⵥID
     * @author hug 2012-10-11 
     * @param outstockid
     */
    public void setOutstockid(String outstockid) {
        this.outstockid = outstockid;
    }

    /**
     * ����: ��ò�ƷID
     * @author hug 2012-10-11 
     * @return
     */
    public String getProductid() {
        return productid;
    }

    /**
     * ����: ���ò�ƷID
     * @author hug 2012-10-11 
     * @param productid
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }

    /**
     * ����: ���ɨ������
     * @author hug 2012-10-11 
     * @return
     */
    public double getQtyscan() {
        return qtyscan;
    }

    /**
     * ����: ����ɨ������
     * @author hug 2012-10-11 
     * @param qtyscan
     */
    public void setQtyscan(double qtyscan) {
        this.qtyscan = qtyscan;
    }

    /**
     * ����: ��ñ�ע
     * @author hug 2012-10-11 
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * ����: ���ñ�ע
     * @author hug 2012-10-11 
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 
     * ����:��ó�����ҵ��ϸID
     * @author hug 2012-10-18 
     * @return
     */
    public String getJobdetailid() {
        return jobdetailid;
    }

    /**
     * 
     * ����:���ó�����ҵ��ϸID
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
