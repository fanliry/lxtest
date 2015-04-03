package com.wms3.bms.standard.entity.outbound;


/**
 * ���ⵥ
 * @author hug
 *
 */
public class OutboundInvoiceHeader  implements java.io.Serializable { 
	
	private static final long serialVersionUID = 5578611054539531472L;
	
	private String outstockid;			//���ⵥ�ݺ�
	private String outstatus;			//���ⵥ״̬ 0:������1-Ԥ�� 2-���� 4-��ȫ���   7:����ȷ�� 8:����
	private String outtype;				//���ⵥ������
	private String departid;			//����id
  	private String warehouseid;			//�ֿ�id
   	private String warehousemanid;		//�ֿ����Աid
  	private String createtime;			//���ⵥ����ʱ��
  	private String createmanid;			//������Id
  	private String formdate;			//��������
	private String outpos;				//�����
	private String vehicleno;			//����
  	private String vehiclepos;			//��λ
  	private String auditmanid;			//�����Id
 	private String auditdate;			//�������
 	private String confirmanid;			//ȷ����Id
 	private String confirmdate;			//ȷ������
 	private String opmanid;				//����Ա
 	private String shiftid;				//��ҵ���
 	private String saleno;				//�ⲿ����id
 	private String onLineType;			//����ģʽ 1.���� 2.�ѻ�
 	private String isupload;			//�Ƿ��ϴ��ɹ� Y.�� N.�� Ĭ��Ϊ��
 	private String ownerid;			    //����
 	private String sendplatform;		//������̨
 	private String setposition;			//����λ
 	private String shipmentstarttime;	//Ԥ�ڷ���ʱ��(from)
 	private String shipmentendtime;		//Ԥ�ڷ���ʱ��(to)
 	private String customerid;			//�ͻ�ID(�ջ���)
 	private String address;			    //�ͻ�ID(�ջ���)��ַ

 	//��������
 	private String m_strAffirmManName;		// ȷ����
 	private String m_strOpManName;      	// �������� 
 	private String m_strOutStatusName;   	// ����״̬��
 	private String m_strOutTypeName;     	// ����������
    private String customername;            // �ͻ�
    private String ownername;               // ����
    private String departName;			    //����
    private String warehousename;			//�ֿ�


    // Constructors
    /** default constructor */
    public OutboundInvoiceHeader() {
    }

	/** minimal constructor */
    public OutboundInvoiceHeader(String outstockid, String outtype, String createtime) {
        this.outstockid = outstockid;
        this.outtype = outtype;
        this.createtime = createtime;
    }
   
    // Property accessors
    /**
     * ���ܣ���ó��ⵥ�ݺ�
     */
    public String getOutstockid() {
        return this.outstockid;
    }
    /**
     * ���ܣ����ó��ⵥ�ݺ�
     * @param outstockid
     */
    public void setOutstockid(String outstockid) {
        this.outstockid = outstockid;
    }
    /**
     * ���ܣ���ó��ⵥ״̬ 0:������1-Ԥ�� 2-����  5-���ָ��� 6-���� 7:����ȷ�� 8:����
     * @return
     */
    public String getOutstatus() {
        return this.outstatus;
    }
    /**
     * ���ܣ����ó��ⵥ״̬ 0:������1-Ԥ�� 2-����  5-���ָ��� 6-���� 7:����ȷ�� 8:����
     * @param outstatus
     */
    public void setOutstatus(String outstatus) {
        this.outstatus = outstatus;
    }
    /**
     * ���ܣ���ó��ⵥ������
     * @return
     */
    public String getOuttype() {
        return this.outtype;
    }
    /**
     * ���ܣ����ó��ⵥ������
     * @param outtype
     */
    public void setOuttype(String outtype) {
        this.outtype = outtype;
    }
    /**
     * ���ܣ���ò���id
     * @return
     */
    public String getDepartid() {
        return this.departid;
    }
    /**
     * ���ܣ����ò���id
     * @param departid
     */
    public void setDepartid(String departid) {
        this.departid = departid;
    }
    /**
     * ���ܣ���òֿ�id
     * @return
     */
    public String getWarehouseid() {
        return this.warehouseid;
    }
    /**
     * ���ܣ����òֿ�id
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }
    /**
     * ���ܣ���òֿ����Աid
     * @return
     */
    public String getWarehousemanid() {
        return this.warehousemanid;
    }
    /**
     * ���ܣ����òֿ����Աid
     * @param warehousemanid
     */
    public void setWarehousemanid(String warehousemanid) {
        this.warehousemanid = warehousemanid;
    }
    /**
     * ���ܣ���ó��ⵥ����ʱ��
     * @return
     */
    public String getCreatetime() {
        return this.createtime;
    }
    /**
     * ���ܣ����ó��ⵥ����ʱ��
     * @param createdate
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**
     * ���ܣ���ô�����Id
     * @return
     */
    public String getCreatemanid() {
        return this.createmanid;
    }
    /**
     * ���ܣ����ô�����Id
     * @param createmanid
     */
    public void setCreatemanid(String createmanid) {
        this.createmanid = createmanid;
    }
    /**
     * ���ܣ���õ�������
     * @return
     */
    public String getFormdate() {
        return this.formdate;
    }
    /**
     * ���ܣ����õ�������
     * @param formdate
     */
    public void setFormdate(String formdate) {
        this.formdate = formdate;
    }
    /**
     * ���ܣ���ó����
     * @return
     */
    public String getOutpos() {
        return this.outpos;
    }
    /**
     * ���ܣ����ó����
     * @param outpos
     */
    public void setOutpos(String outpos) {
        this.outpos = outpos;
    }
    /**
     * ���ܣ���ó���
     * @return
     */
    public String getVehicleno() {
        return this.vehicleno;
    }
    /**
     * ���ܣ����ó���
     * @param vehicleno
     */
    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }
    /**
     * ���ܣ���ó�λ
     * @return
     */
    public String getVehiclepos() {
        return this.vehiclepos;
    }
    /**
     * ���ܣ����ó�λ
     * @param vehiclepos
     */
    public void setVehiclepos(String vehiclepos) {
        this.vehiclepos = vehiclepos;
    }
    /**
     * ���ܣ���������Id
     * @return
     */
    public String getAuditmanid() {
        return this.auditmanid;
    }
    /**
     * ���ܣ����������Id
     * @param auditmanid
     */
    public void setAuditmanid(String auditmanid) {
        this.auditmanid = auditmanid;
    }
    /**
     * ���ܣ�����������
     * @return
     */
    public String getAuditdate() {
        return this.auditdate;
    }
    /**
     * ���ܣ������������
     * @param auditdate
     */
    public void setAuditdate(String auditdate) {
        this.auditdate = auditdate;
    }
    /**
     * ���ܣ����ȷ����Id
     * @return
     */
    public String getConfirmanid() {
        return this.confirmanid;
    }
    /**
     * ���ܣ�����ȷ����Id
     * @param confirmanid
     */
    public void setConfirmanid(String confirmanid) {
        this.confirmanid = confirmanid;
    }
    /**
     * ���ܣ����ȷ������
     * @return
     */
    public String getConfirmdate() {
        return this.confirmdate;
    }
    /**
     * ���ܣ�����ȷ������
     * @param confirmdate
     */
    public void setConfirmdate(String confirmdate) {
        this.confirmdate = confirmdate;
    }
    /**
     * ���ܣ���ò���Ա
     * @return
     */
    public String getOpmanid() {
        return this.opmanid;
    }
    /**
     * ���ܣ����ò���Ա
     * @param opmanid
     */
    public void setOpmanid(String opmanid) {
        this.opmanid = opmanid;
    }
    /**
     * ���ܣ������ҵ���
     * @return
     */
    public String getShiftid() {
        return this.shiftid;
    }
    /**
     * ���ܣ�������ҵ���
     * @param shiftid
     */
    public void setShiftid(String shiftid) {
        this.shiftid = shiftid;
    }
    /**
     * ���ܣ�����ⲿ�ݺ�
     * @return
     */
    public String getSaleno() {
        return this.saleno;
    }
    /**
     * ���ܣ������ⲿ�ݺ�
     * @param saleno
     */
    public void setSaleno(String saleno) {
        this.saleno = saleno;
    }
    /**
     * ���ܣ��������ģʽ 1.���� 2.�ѻ�
     * @return
     */
    public String getOnLineType() {
        return this.onLineType;
    }
    /**
     * ���ܣ���������ģʽ 1.���� 2.�ѻ�
     * @param onLineType
     */
    public void setOnLineType(String onLineType) {
        this.onLineType = onLineType;
    }
    /**
     * ���ܣ�����Ƿ��ϴ��ɹ� Y.�� N.�� Ĭ��Ϊ��
     * @return
     */
    public String getIsupload() {
        return this.isupload;
    }
    /**
     * ���ܣ������Ƿ��ϴ��ɹ� Y.�� N.�� Ĭ��Ϊ��
     * @param isupload
     */
    public void setIsupload(String isupload) {
        this.isupload = isupload;
    }

    /**
     * ���ܣ���÷�����̨
     * @return
     */
    public String getSendplatform() {
        return this.sendplatform;
    }
    /**
     * ���ܣ����÷�����̨
     * @param sendplatform
     */
    public void setSendplatform(String sendplatform) {
        this.sendplatform = sendplatform;
    }
    /**
     * ���ܣ���ü���λ
     * @return
     */
    public String getSetposition() {
        return this.setposition;
    }
    /**
     * ���ܣ����ü���λ
     * @param setposition
     */
    public void setSetposition(String setposition) {
        this.setposition = setposition;
    }

    /**
     * ���ܣ����ȷ����
     * @author hug 2012-6-21 
     * @return
     */
    public String getM_strAffirmManName() {
        return m_strAffirmManName;
    }

    /**
     * ���ܣ�����ȷ����
     * @author hug 2012-6-21 
     * @param affirmManName
     */
    public void setM_strAffirmManName(String affirmManName) {
        m_strAffirmManName = affirmManName;
    }

    /**
     * ���ܣ���ò���Ա��
     * @author hug 2012-6-21 
     * @return
     */
    public String getM_strOpManName() {
        return m_strOpManName;
    }

    /**
     * ���ܣ����ò���Ա��
     * @author hug 2012-6-21 
     * @param opManName
     */
    public void setM_strOpManName(String opManName) {
        m_strOpManName = opManName;
    }

    /**
     * ���ܣ���õ���״̬��
     * @author hug 2012-6-21 
     * @return
     */
    public String getM_strOutStatusName() {
        return m_strOutStatusName;
    }

    /**
     * ���ܣ����õ���״̬��
     * @author hug 2012-6-21 
     * @param outStatusName
     */
    public void setM_strOutStatusName(String outStatusName) {
        m_strOutStatusName = outStatusName;
    }

    /**
     * ���ܣ���ó���������
     * @author hug 2012-6-21 
     * @return
     */
    public String getM_strOutTypeName() {
        return m_strOutTypeName;
    }

    /**
     * ���ܣ����ó���������
     * @author hug 2012-6-21 
     * @param outTypeName
     */
    public void setM_strOutTypeName(String outTypeName) {
        m_strOutTypeName = outTypeName;
    }

	/**
	 * ���ܣ���ÿͻ�ID
	 */
	public String getCustomerid() {
		return customerid;
	}

	/**
	 * ���ܣ����ÿͻ�ID
	 * @param customerid
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	/**
	 * ���ܣ����Ԥ�ڷ���ʱ��(to)
	 */
	public String getShipmentendtime() {
		return shipmentendtime;
	}

	/**
	 * ���ܣ�����Ԥ�ڷ���ʱ��(to)
	 * @param shipmentendtime
	 */
	public void setShipmentendtime(String shipmentendtime) {
		this.shipmentendtime = shipmentendtime;
	}

	/**
	 * ���ܣ����Ԥ�ڷ���ʱ��(from)
	 */
	public String getShipmentstarttime() {
		return shipmentstarttime;
	}

	/**
	 * ���ܣ�����Ԥ�ڷ���ʱ��(from)
	 * @param shipmentstarttime
	 */
	public void setShipmentstarttime(String shipmentstarttime) {
		this.shipmentstarttime = shipmentstarttime;
	}

    /**
     * ����:��ÿͻ���
     * @author hug 2012-9-14 
     * @return
     */
    public String getCustomername() {
        return customername;
    }

    /**
     * ����:���ÿͻ���
     * @author hug 2012-9-14 
     * @param customername
     */
    public void setCustomername(String customername) {
        this.customername = customername;
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

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
    
}