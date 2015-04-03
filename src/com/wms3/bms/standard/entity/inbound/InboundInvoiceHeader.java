package com.wms3.bms.standard.entity.inbound;

/**
 * 
 * ����: ��ⵥ
 * @author hug 2012-3-9
 * @since WMS 3.0
 */
public class InboundInvoiceHeader implements java.io.Serializable {

    private static final long serialVersionUID = -747802160323265188L;
    
     private String instockid;      	//��ⵥ�ݱ��
     private String warehouseid;    	//�ֿ���
     private String invoicedate;    	//��������
     private String createtime;     	//��������ʱ��
     private String createmanid;    	//����������Ա���
     private String instatus;       	//����״̬ 0-�½���1-��ˣ�2-ȷ�� 5-���� 
     private String intype;         	//�������
     private String opmode;         	//���ݴ���ʽ1-�������ݣ�Ĭ�ϣ�2-��Ŀ����
     private String uploadflag;     	//�ϴ���־0-���ϴ���Ĭ�ϣ�1-δ�ϴ�
     private String auditmanid;     	//�����Id
     private String auditdate;      	//�������
     private String confirmanid;    	//ȷ����Id
     private String confirmdate;    	//ȷ������
     private String departid;       	//���ű��
     private String shiftid;        	//��ҵ���
     private String onLineType;     	//����ģʽ 1.������Ĭ�ϣ�2.�ѻ�
     private String inpos;          	//����
     private String invoicesource;  	//������Դ(��ҵ��Դ 1��ֱ�Ӳɼ����ɵ���ҵ��2�����ջ��������ɵ���ҵ��3������ⵥ�����ɵ���ҵ 4:��ERP�����������ɵ���ҵ)
     private String customerid;  		//�ͻ�
     
     //��������
     protected String m_strStatusName;	// ״̬��
     protected String m_strTypeName;	// ������
     protected String createman;		// ����������Ա
     protected String customername;		// �ͻ���



    // Constructors

    /** default constructor */
    public InboundInvoiceHeader() {
    }

	/** minimal constructor */
    public InboundInvoiceHeader(String warehouseid, String createtime, String createmanid, String opmode) {
        this.warehouseid = warehouseid;
        this.createtime = createtime;
        this.createmanid = createmanid;
        this.opmode = opmode;
    }

   
    // Property accessors
    /**
     * ���ܣ������ⵥ�ݱ��
     */
    public String getInstockid() {
        return this.instockid;
    }
    /**
     * ���ܣ�������ⵥ�ݱ��
     * @author hug 2012-3-9 
     * @param instockid
     */
    public void setInstockid(String instockid) {
        this.instockid = instockid;
    }
    /**
     * ���ܣ���òֿ���
     * @author hug 2012-3-9 
     * @return
     */
    public String getWarehouseid() {
        return this.warehouseid;
    }
    /**
     * ���ܣ����òֿ���
     * @author hug 2012-3-9 
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }

    /**
     * ���ܣ����Ĭ��Ϊ����ʱ��
     * @author hug 2012-3-9 
     * @return
     */
    public String getInvoicedate() {
        return this.invoicedate;
    }
    /**
     * ���ܣ�����Ĭ��Ϊ����ʱ��
     * @author hug 2012-3-9 
     * @param invoicedate
     */
    public void setInvoicedate(String invoicedate) {
        this.invoicedate = invoicedate;
    }
    /**
     * ���ܣ���õ�������ʱ��
     * @author hug 2012-3-9 
     * @return
     */
    public String getCreatetime() {
        return this.createtime;
    }
    /**
     * ���ܣ����õ�������ʱ��
     * @author hug 2012-3-9 
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**
     * ���ܣ���õ���������Ա���
     * @author hug 2012-3-9 
     * @return
     */
    public String getCreatemanid() {
        return this.createmanid;
    }
    /**
     * ���ܣ����õ���������Ա���
     * @author hug 2012-3-9 
     * @param createmanid
     */
    public void setCreatemanid(String createmanid) {
        this.createmanid = createmanid;
    }
    
    
    /**
     * ���ܣ���õ���״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
     * @author hug 2012-3-9 
     * @return
     */
    public String getInstatus() {
        return this.instatus;
    }
    /**
     * ���ܣ����õ���״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
     * @author hug 2012-3-9 
     * @param instatus
     */
    public void setInstatus(String instatus) {
        this.instatus = instatus;
    }
    /**
     * ���ܣ�����������
     * @author hug 2012-3-9 
     * @return
     */
    public String getIntype() {
        return this.intype;
    }
    /**
     * ���ܣ������������
     * @author hug 2012-3-9 
     * @param intype
     */
    public void setIntype(String intype) {
        this.intype = intype;
    }
    /**
     * ���ܣ���õ�����Դ
     * @author hug 2012-3-9 
     * @return
     */
    public String getInvoicesource() {
        return this.invoicesource;
    }
    /**
     * ���ܣ����õ�����Դ
     * @author hug 2012-3-9 
     * @param invoicesource
     */
    public void setInvoicesource(String invoicesource) {
        this.invoicesource = invoicesource;
    }
    /**
     * ���ܣ���õ��ݴ���ʽ1-�������ݣ�Ĭ�ϣ�2-��Ŀ����
     * @author hug 2012-3-9 
     * @return
     */
    public String getOpmode() {
        return this.opmode;
    }
    /**
     * ���ܣ����õ��ݴ���ʽ1-�������ݣ�Ĭ�ϣ�2-��Ŀ����
     * @author hug 2012-3-9 
     * @param opmode
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode;
    }
    /**
     * ���ܣ�����ϴ���־0-���ϴ���Ĭ�ϣ�1-δ�ϴ�
     * @author hug 2012-3-9 
     * @return
     */
    public String getUploadflag() {
        return this.uploadflag;
    }
    /**
     * ���ܣ������ϴ���־0-���ϴ���Ĭ�ϣ�1-δ�ϴ�
     * @author hug 2012-3-9 
     * @param uploadflag
     */
    public void setUploadflag(String uploadflag) {
        this.uploadflag = uploadflag;
    }

    /**
     * ���ܣ���ò��ű��
     * @author hug 2012-3-9 
     * @return
     */
    public String getDepartid() {
        return this.departid;
    }
    /**
     * ���ܣ����ò��ű��
     * @author hug 2012-3-9 
     * @param departid
     */
    public void setDepartid(String departid) {
        this.departid = departid;
    }
    /**
     * ���ܣ������ҵ���
     * @author hug 2012-3-9 
     * @return
     */
    public String getShiftid() {
        return this.shiftid;
    }
    /**
     * ���ܣ�������ҵ���
     * @author hug 2012-3-9 
     * @param shiftid
     */
    public void setShiftid(String shiftid) {
        this.shiftid = shiftid;
    }
    /**
     * ���ܣ��������ģʽ 1.������Ĭ�ϣ�2.�ѻ�
     * @author hug 2012-3-9 
     * @return
     */
    public String getOnLineType() {
        return this.onLineType;
    }
    /**
     * ���ܣ���������ģʽ 1.������Ĭ�ϣ�2.�ѻ�
     * @author hug 2012-3-9 
     * @param onLineType
     */
    public void setOnLineType(String onLineType) {
        this.onLineType = onLineType;
    }
    /**
     * ���ܣ��������
     * @author hug 2012-3-9 
     * @return
     */
    public String getInpos() {
        return this.inpos;
    }
    /**
     * ���ܣ���������
     * @author hug 2012-3-9 
     * @param inpos
     */
    public void setInpos(String inpos) {
        this.inpos = inpos;
    }
    
    /**
     * ����:����������
     * @author hug 2012-3-22 
     * @return
     */
    public String getAuditdate() {
        return auditdate;
    }

    /**
     * ���ܣ������������
     * @author hug 2012-3-22 
     * @param auditdate
     */
    public void setAuditdate(String auditdate) {
        this.auditdate = auditdate;
    }

    /**
     * ����:��������Id
     * @author hug 2012-3-22 
     * @return
     */
    public String getAuditmanid() {
        return auditmanid;
    }

    /**
     * ����:���������Id
     * @author hug 2012-3-22 
     * @param auditmanid
     */
    public void setAuditmanid(String auditmanid) {
        this.auditmanid = auditmanid;
    }

    /**
     * ���ܣ����ȷ����Id
     * @author hug 2012-3-22 
     * @return
     */
    public String getConfirmanid() {
        return confirmanid;
    }

    /**
     * ����:����ȷ����Id
     * @author hug 2012-3-22 
     * @param confirmanid
     */
    public void setConfirmanid(String confirmanid) {
        this.confirmanid = confirmanid;
    }

    /**
     * ���ܣ����ȷ������
     * @author hug 2012-3-22 
     * @return
     */
    public String getConfirmdate() {
        return confirmdate;
    }

    /**
     * ����:����ȷ������
     * @author hug 2012-3-22 
     * @param confirmdate
     */
    public void setConfirmdate(String confirmdate) {
        this.confirmdate = confirmdate;
    }


    /**
     * ���ܣ���õ���������Ա
     * @author hug 2012-6-25 
     * @return
     */
    public String getCreateman() {
        return createman;
    }

    /**
     * ���ܣ����õ���������Ա
     * @author hug 2012-6-25 
     * @param createman
     */
    public void setCreateman(String createman) {
        this.createman = createman;
    }

    /**
     * ���ܣ����״̬��
     * @author hug 2012-6-25 
     * @return
     */
    public String getM_strStatusName() {
        return m_strStatusName;
    }

    /**
     * ���ܣ�����״̬��
     * @author hug 2012-6-25 
     * @param statusName
     */
    public void setM_strStatusName(String statusName) {
        m_strStatusName = statusName;
    }

    /**
     * ���ܣ����������
     * @author hug 2012-6-25 
     * @return
     */
    public String getM_strTypeName() {
        return m_strTypeName;
    }

    /**
     * ���ܣ�����������
     * @author hug 2012-6-25 
     * @param typeName
     */
    public void setM_strTypeName(String typeName) {
        m_strTypeName = typeName;
    }

	/**
	 * ���ܣ���ÿͻ�
	 */
	public String getCustomerid() {
		return customerid;
	}

	/**
	 * ���ܣ����ÿͻ�
	 * @param customerid
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	/**
	 * ���ܣ���ÿͻ���
	 */
	public String getCustomername() {
		return customername;
	}

	/**
	 * ���ܣ����ÿͻ���
	 * @param customername
	 */
	public void setCustomername(String customername) {
		this.customername = customername;
	}
    
    

}