package com.wms3.bms.standard.entity.inbound;

/**
 * 
 * ����: �ջ���
 * @author hug 2012-6-26
 * @since WMS 3.0
 */
public class InboundReceiptHeader implements java.io.Serializable{

    /**  */
    private static final long serialVersionUID = -6358945903492412835L;
    
    private String reinvoiceid; /*���뵥�ݱ��*/
    private String warehouseid; /*�ֿ���*/
    private String invoicedate; /*Ĭ��Ϊ����ʱ��*/
    private String createtime ; /*��������ʱ��*/
    private String createmanid; /*����������Ա���*/
    private String updatetime ; /*��������޸�ʱ��*/
    private String updatemanid; /*��������޸���Ա���*/
    private String instatus ;   /*����״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�*/
    private String intype ;     /*����*/
    private String ownerid ;    /*����*/
    private String arrivestarttime; /*Ԥ�ڵ���ʱ��(from)*/
    private String arriveendtime;   /*Ԥ�ڵ���ʱ��(to)*/
    private String shipperid;   /*��������Ϣ*/
    private String supplierid ; /*��Ӧ����Ϣ*/
    private String receiveloc;  /*�ջ���̨*/
    private String customsno ;  /*���ر�����*/
    private String reserve1;    /*Ԥ���ֶ�1*/
    private String reserve2;    /*Ԥ���ֶ�2*/
    private String reserve3;    /*Ԥ���ֶ�3*/
    private String reserve4;    /*Ԥ���ֶ�4*/
    private String remark;      /*��ע*/
    
    //��������
    private String m_strStatusName; // ״̬��
    private String m_strTypeName;   // ������
    private String createman;       // ����������Ա
    private String ownername;       //����
    private String shippername;     //��������Ϣ
    private String suppliername ;   //��Ӧ����Ϣ
    
    
    public InboundReceiptHeader()
    {}
    
    /**
     * ���ܣ����Ԥ�ڵ���ʱ��(to)
     * @author hug 2012-6-26 
     * @return
     */
    public String getArriveendtime() {
        return arriveendtime;
    }
    /**
     * ���ܣ�����Ԥ�ڵ���ʱ��(to)
     * @author hug 2012-6-26 
     * @param arriveendtime
     */
    public void setArriveendtime(String arriveendtime) {
        this.arriveendtime = arriveendtime;
    }
    /**
     * ���ܣ����Ԥ�ڵ���ʱ��(from)
     * @author hug 2012-6-26 
     * @return
     */
    public String getArrivestarttime() {
        return arrivestarttime;
    }
    /**
     * ���ܣ�����Ԥ�ڵ���ʱ��(from)
     * @author hug 2012-6-26 
     * @param arrivestarttime
     */
    public void setArrivestarttime(String arrivestarttime) {
        this.arrivestarttime = arrivestarttime;
    }
   
    /**
     * ���ܣ���õ���������Ա���
     * @author hug 2012-6-26 
     * @return
     */
    public String getCreatemanid() {
        return createmanid;
    }
    /**
     * ���ܣ����õ���������Ա���
     * @author hug 2012-6-26 
     * @param createmanid
     */
    public void setCreatemanid(String createmanid) {
        this.createmanid = createmanid;
    }
    /**
     * ���ܣ���õ�������ʱ��
     * @author hug 2012-6-26 
     * @return
     */
    public String getCreatetime() {
        return createtime;
    }
    /**
     * ���ܣ����õ�������ʱ��
     * @author hug 2012-6-26 
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**
     * ���ܣ���ú��ر�����
     * @author hug 2012-6-26 
     * @return
     */
    public String getCustomsno() {
        return customsno;
    }
    /**
     * ���ܣ����ú��ر�����
     * @author hug 2012-6-26 
     * @param customsno
     */
    public void setCustomsno(String customsno) {
        this.customsno = customsno;
    }
    /**
     * ���ܣ���õ���״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
     * @author hug 2012-6-26 
     * @return
     */
    public String getInstatus() {
        return instatus;
    }
    /**
     * ���ܣ����õ���״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
     * @author hug 2012-6-26 
     * @param instatus
     */
    public void setInstatus(String instatus) {
        this.instatus = instatus;
    }
    /**
     * ���ܣ��������
     * @author hug 2012-6-26 
     * @return
     */
    public String getIntype() {
        return intype;
    }
    /**
     * ���ܣ���������
     * @author hug 2012-6-26 
     * @param intype
     */
    public void setIntype(String intype) {
        this.intype = intype;
    }
    /**
     * ���ܣ����Ĭ��Ϊ����ʱ��
     * @author hug 2012-6-26 
     * @return
     */
    public String getInvoicedate() {
        return invoicedate;
    }
    /**
     * ���ܣ�����Ĭ��Ϊ����ʱ��
     * @author hug 2012-6-26 
     * @param invoicedate
     */
    public void setInvoicedate(String invoicedate) {
        this.invoicedate = invoicedate;
    }
    
    
    /**
     * ���ܣ���û���
     * @author hug 2012-6-26 
     * @return
     */
    public String getOwnerid() {
        return ownerid;
    }
    /**
     * ���ܣ����û���
     * @author hug 2012-6-26 
     * @param ownerid
     */
    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }
    
    
    /**
     * ���ܣ�����ջ���̨
     * @author hug 2012-6-26 
     * @return
     */
    public String getReceiveloc() {
        return receiveloc;
    }
    /**
     * ���ܣ������ջ���̨
     * @author hug 2012-6-26 
     * @param receiveloc
     */
    public void setReceiveloc(String receiveloc) {
        this.receiveloc = receiveloc;
    }
    /**
     * ���ܣ�����ջ����ݱ��
     * @author hug 2012-6-26 
     * @return
     */
    public String getReinvoiceid() {
        return reinvoiceid;
    }
    /**
     * ���ܣ������ջ����ݱ��
     * @author hug 2012-6-26 
     * @param reinvoiceid
     */
    public void setReinvoiceid(String reinvoiceid) {
        this.reinvoiceid = reinvoiceid;
    }
    /**
     * ���ܣ���ñ�ע
     * @author hug 2012-6-26 
     * @return
     */
    public String getRemark() {
        return remark;
    }
    /**
     * ���ܣ����ñ�ע
     * @author hug 2012-6-26 
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * ���ܣ����Ԥ���ֶ�1
     * @author hug 2012-6-26 
     * @return
     */
    public String getReserve1() {
        return reserve1;
    }
    /**
     * ���ܣ�����Ԥ���ֶ�1
     * @author hug 2012-6-26 
     * @param reserve1
     */
    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }
    /**
     * ���ܣ����Ԥ���ֶ�2
     * @author hug 2012-6-26 
     * @return
     */
    public String getReserve2() {
        return reserve2;
    }
    /**
     * ���ܣ�����Ԥ���ֶ�2
     * @author hug 2012-6-26 
     * @param reserve2
     */
    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }
    /**
     * ���ܣ����Ԥ���ֶ�3
     * @author hug 2012-6-26 
     * @return
     */
    public String getReserve3() {
        return reserve3;
    }
    /**
     * ���ܣ�����Ԥ���ֶ�3
     * @author hug 2012-6-26 
     * @param reserve3
     */
    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }
    /**
     * ���ܣ����Ԥ���ֶ�4
     * @author hug 2012-6-26 
     * @return
     */
    public String getReserve4() {
        return reserve4;
    }
    /**
     * ���ܣ�����Ԥ���ֶ�4
     * @author hug 2012-6-26 
     * @param reserve4
     */
    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }
    /**
     * ���ܣ���ó�������Ϣ
     * @author hug 2012-6-26 
     * @return
     */
    public String getShipperid() {
        return shipperid;
    }
    /**
     * ���ܣ����ó�������Ϣ
     * @author hug 2012-6-26 
     * @param shipperid
     */
    public void setShipperid(String shipperid) {
        this.shipperid = shipperid;
    }
    
    
    /**
     * ���ܣ���ù�Ӧ����Ϣ
     * @author hug 2012-6-26 
     * @return
     */
    public String getSupplierid() {
        return supplierid;
    }
    /**
     * ���ܣ����ù�Ӧ����Ϣ
     * @author hug 2012-6-26 
     * @param supplierid
     */
    public void setSupplierid(String supplierid) {
        this.supplierid = supplierid;
    }
    
    
    /**
     * ���ܣ���õ�������޸���Ա���
     * @author hug 2012-6-26 
     * @return
     */
    public String getUpdatemanid() {
        return updatemanid;
    }
    /**
     * ���ܣ����õ�������޸���Ա���
     * @author hug 2012-6-26 
     * @param updatemanid
     */
    public void setUpdatemanid(String updatemanid) {
        this.updatemanid = updatemanid;
    }
    /**
     * ���ܣ���õ�������޸�ʱ��
     * @author hug 2012-6-26 
     * @return
     */
    public String getUpdatetime() {
        return updatetime;
    }
    /**
     * ���ܣ����õ�������޸�ʱ��
     * @author hug 2012-6-26 
     * @param updatetime
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    /**
     * ���ܣ���òֿ���
     * @author hug 2012-6-26 
     * @return
     */
    public String getWarehouseid() {
        return warehouseid;
    }
    /**
     * ���ܣ����òֿ���
     * @author hug 2012-6-26 
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }

    
    
    
    /**
     * ���ܣ���õ���������Ա
     * @author hug 2012-6-26 
     * @return
     */
    public String getCreateman() {
        return createman;
    }

    /**
     * ���ܣ����õ���������Ա
     * @author hug 2012-6-26 
     * @param createman
     */
    public void setCreateman(String createman) {
        this.createman = createman;
    }

    /**
     * ���ܣ����״̬��
     * @author hug 2012-6-26 
     * @return
     */
    public String getM_strStatusName() {
        return m_strStatusName;
    }

    /**
     * ���ܣ�����״̬��
     * @author hug 2012-6-26 
     * @param statusName
     */
    public void setM_strStatusName(String statusName) {
        m_strStatusName = statusName;
    }

    /**
     * ���ܣ����������
     * @author hug 2012-6-26 
     * @return
     */
    public String getM_strTypeName() {
        return m_strTypeName;
    }

    /**
     * ���ܣ�����������
     * @author hug 2012-6-26 
     * @param typeName
     */
    public void setM_strTypeName(String typeName) {
        m_strTypeName = typeName;
    }

    /**
     * ���ܣ���û���
     * @author hug 2012-6-26 
     * @return
     */
    public String getOwnername() {
        return ownername;
    }

    /**
     * ���ܣ����û���
     * @author hug 2012-6-26 
     * @param ownername
     */
    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    /**
     * ���ܣ���ó�������Ϣ
     * @author hug 2012-6-26 
     * @return
     */
    public String getShippername() {
        return shippername;
    }

    /**
     * ���ܣ����ó�������Ϣ
     * @author hug 2012-6-26 
     * @param shippername
     */
    public void setShippername(String shippername) {
        this.shippername = shippername;
    }

    /**
     * ���ܣ���ù�Ӧ����Ϣ
     * @author hug 2012-6-26 
     * @return
     */
    public String getSuppliername() {
        return suppliername;
    }

    /**
     * ���ܣ����ù�Ӧ����Ϣ
     * @author hug 2012-6-26 
     * @param suppliername
     */
    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }
    
    
    
    
    
    

}
