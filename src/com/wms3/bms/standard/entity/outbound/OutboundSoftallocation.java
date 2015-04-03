package com.wms3.bms.standard.entity.outbound;


/**
 * ������Ԥ��
 * @author hug
 *
 */
public class OutboundSoftallocation  implements java.io.Serializable {
     
    
	private static final long serialVersionUID = 4012223973119209218L;
	
	private String softallocationid;	//Ԥ����ID
	private String status;				//״̬ 0:�½� 1:��� 2������
	private String invoiceid;			//���ݱ��
	private String invoicedetailid;		//������ϸID
	private String edittime;			//Ԥ��ʱ��
	private String editwho;				//Ԥ����
	private String starttime;			//Ԥ�俪ʼʱ��
	private String endtime;				//Ԥ�����ʱ��
    private String warehouseid;         //�ֿ�ID
    private String whAreaId;            //����ID
	private String customerid;			//�ͻ�ID
    private String ownerId;            //����ID
	private String productid;			//��ƷID
	private String lotid;				//����ID
	private String packid;				//��װ����
	private String pkgunit;				//��λ
	private double assignnum;			//����
	private double netweight;			//����
	private double weight;				//����
	private double volume;				//���
	public String lotatt1;				//��������1
    public String lotatt2;				//��������2
    public String lotatt3;				//��������3
    public String lotatt4;				//��������4
    public String lotatt5;				//��������5
    public String lotatt6;				//��������6
    public String lotatt7;				//��������7
    public String lotatt8;				//��������8
    public String lotatt9;				//��������9
    public String lotatt10;			//��������10
    public String lotatt11;			//��������11
    public String lotatt12;			//��������12

    //��������
    private String m_strProductName;    //Ʒ��
    protected String m_strCustomerName; //�ͻ�	

    //�����ֶ�
    private String boxcode;            //������
    private String productcode;        //��Ʒ����

    // Constructors

    /** default constructor */
    public OutboundSoftallocation() {
    }

	/** minimal constructor */
    public OutboundSoftallocation(String productid) {
        this.productid = productid;
    }
    
   
    // Property accessors

    public String getM_strProductName() {
        return m_strProductName;
    }
    public void setM_strProductName(String productName) {
        m_strProductName = productName;
    }
    /**
     * ���ܣ���ÿͻ�
     * @author hug 2012-6-20 
     * @return
     */
    public String getM_strCustomerName() {
        return m_strCustomerName;
    }
    /**
	 * ���ܣ��������
	 */
	public double getAssignnum() {
		return assignnum;
	}

	/**
	 * ���ܣ���������
	 * @param assignnum
	 */
	public void setAssignnum(double assignnum) {
		this.assignnum = assignnum;
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
	 * ���ܣ����Ԥ��ʱ��
	 */
	public String getEdittime() {
		return edittime;
	}

	/**
	 * ���ܣ�����Ԥ��ʱ��
	 * @param edittime
	 */
	public void setEdittime(String edittime) {
		this.edittime = edittime;
	}

	/**
	 * ���ܣ����Ԥ����
	 */
	public String getEditwho() {
		return editwho;
	}

	/**
	 * ���ܣ�����Ԥ����
	 * @param editwho
	 */
	public void setEditwho(String editwho) {
		this.editwho = editwho;
	}

	/**
	 * ���ܣ����Ԥ�����ʱ��
	 */
	public String getEndtime() {
		return endtime;
	}

	/**
	 * ���ܣ�����Ԥ�����ʱ��
	 * @param endtime
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/**
	 * ���ܣ���õ�����ϸID
	 */
	public String getInvoicedetailid() {
		return invoicedetailid;
	}

	/**
	 * ���ܣ����õ�����ϸID
	 * @param invoicedetailid
	 */
	public void setInvoicedetailid(String invoicedetailid) {
		this.invoicedetailid = invoicedetailid;
	}

	/**
	 * ���ܣ���õ��ݱ��
	 */
	public String getInvoiceid() {
		return invoiceid;
	}

	/**
	 * ���ܣ����õ��ݱ��
	 * @param invoiceid
	 */
	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}

	/**
	 * ���ܣ���þ���
	 */
	public double getNetweight() {
		return netweight;
	}

	/**
	 * ���ܣ����þ���
	 * @param netweight
	 */
	public void setNetweight(double netweight) {
		this.netweight = netweight;
	}

	/**
	 * ���ܣ���õ�λ
	 */
	public String getPkgunit() {
		return pkgunit;
	}

	/**
	 * ���ܣ����õ�λ
	 * @param pkgunit
	 */
	public void setPkgunit(String pkgunit) {
		this.pkgunit = pkgunit;
	}

	/**
	 * ���ܣ���ò�ƷID
	 */
	public String getProductid() {
		return productid;
	}

	/**
	 * ���ܣ����ò�ƷID
	 * @param productid
	 */
	public void setProductid(String productid) {
		this.productid = productid;
	}

	/**
	 * ���ܣ����Ԥ����ID
	 */
	public String getSoftallocationid() {
		return softallocationid;
	}

	/**
	 * ���ܣ�����Ԥ����ID
	 * @param softallocationid
	 */
	public void setSoftallocationid(String softallocationid) {
		this.softallocationid = softallocationid;
	}

	/**
	 * ���ܣ����Ԥ�俪ʼʱ��
	 */
	public String getStarttime() {
		return starttime;
	}

	/**
	 * ���ܣ�����Ԥ�俪ʼʱ��
	 * @param starttime
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/**
	 * ���ܣ����״̬
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * ���ܣ�����״̬
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * ���ܣ�������
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * ���ܣ��������
	 * @param volume
	 */
	public void setVolume(double volume) {
		this.volume = volume;
	}

	/**
	 * ���ܣ��������
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * ���ܣ���������
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
     * ���ܣ����ÿͻ�
     * @author hug 2012-6-20 
     * @param customerName
     */
    public void setM_strCustomerName(String customerName) {
        m_strCustomerName = customerName;
    }

	/**
	 * ���ܣ������������1
	 */
	public String getLotatt1() {
		return lotatt1;
	}

	/**
	 * ���ܣ�������������1
	 * @param lotatt1
	 */
	public void setLotatt1(String lotatt1) {
		this.lotatt1 = lotatt1;
	}

	/**
	 * ���ܣ������������10
	 */
	public String getLotatt10() {
		return lotatt10;
	}

	/**
	 * ���ܣ�������������10
	 * @param lotatt10
	 */
	public void setLotatt10(String lotatt10) {
		this.lotatt10 = lotatt10;
	}

	/**
	 * ���ܣ������������11
	 */
	public String getLotatt11() {
		return lotatt11;
	}

	/**
	 * ���ܣ�������������11
	 * @param lotatt11
	 */
	public void setLotatt11(String lotatt11) {
		this.lotatt11 = lotatt11;
	}

	/**
	 * ���ܣ������������12
	 */
	public String getLotatt12() {
		return lotatt12;
	}

	/**
	 * ���ܣ�������������12
	 * @param lotatt12
	 */
	public void setLotatt12(String lotatt12) {
		this.lotatt12 = lotatt12;
	}

	/**
	 * ���ܣ������������2
	 */
	public String getLotatt2() {
		return lotatt2;
	}

	/**
	 * ���ܣ�������������2
	 * @param lotatt2
	 */
	public void setLotatt2(String lotatt2) {
		this.lotatt2 = lotatt2;
	}

	/**
	 * ���ܣ������������3
	 */
	public String getLotatt3() {
		return lotatt3;
	}

	/**
	 * ���ܣ�������������3
	 * @param lotatt3
	 */
	public void setLotatt3(String lotatt3) {
		this.lotatt3 = lotatt3;
	}

	/**
	 * ���ܣ������������4
	 */
	public String getLotatt4() {
		return lotatt4;
	}

	/**
	 * ���ܣ�������������4
	 * @param lotatt4
	 */
	public void setLotatt4(String lotatt4) {
		this.lotatt4 = lotatt4;
	}

	/**
	 * ���ܣ������������5
	 */
	public String getLotatt5() {
		return lotatt5;
	}

	/**
	 * ���ܣ�������������5
	 * @param lotatt5
	 */
	public void setLotatt5(String lotatt5) {
		this.lotatt5 = lotatt5;
	}

	/**
	 * ���ܣ������������6
	 */
	public String getLotatt6() {
		return lotatt6;
	}

	/**
	 * ���ܣ�������������6
	 * @param lotatt6
	 */
	public void setLotatt6(String lotatt6) {
		this.lotatt6 = lotatt6;
	}

	/**
	 * ���ܣ������������7
	 */
	public String getLotatt7() {
		return lotatt7;
	}

	/**
	 * ���ܣ�������������7
	 * @param lotatt7
	 */
	public void setLotatt7(String lotatt7) {
		this.lotatt7 = lotatt7;
	}

	/**
	 * ���ܣ������������8
	 */
	public String getLotatt8() {
		return lotatt8;
	}

	/**
	 * ���ܣ�������������8
	 * @param lotatt8
	 */
	public void setLotatt8(String lotatt8) {
		this.lotatt8 = lotatt8;
	}

	/**
	 * ���ܣ������������9
	 */
	public String getLotatt9() {
		return lotatt9;
	}

	/**
	 * ���ܣ�������������9
	 * @param lotatt9
	 */
	public void setLotatt9(String lotatt9) {
		this.lotatt9 = lotatt9;
	}

	/**
	 * ���ܣ������������ID
	 */
	public String getLotid() {
		return lotid;
	}

	/**
	 * ���ܣ�������������ID
	 * @param lotid
	 */
	public void setLotid(String lotid) {
		this.lotid = lotid;
	}

	/**
	 * ���ܣ���ð�װID
	 */
	public String getPackid() {
		return packid;
	}

	/**
	 * ���ܣ����ð�װID
	 * @param packid
	 */
	public void setPackid(String packid) {
		this.packid = packid;
	}
    /**
     * ���ܣ���ÿ���ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getWhAreaId() {
        return this.whAreaId;
    }
    /**
     * ���ܣ����ÿ���ID
     * @author hug 2012-3-6 
     * @param whAreaId
     */
    public void setWhAreaId(String whAreaId) {
        this.whAreaId = whAreaId;
    }
    /**
     * ���ܣ���òֿ�ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getWarehouseid() {
        return this.warehouseid;
    }
    /**
     * ���ܣ����òֿ�ID
     * @author hug 2012-3-6 
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }
    /**
     * ���ܣ���û���ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getOwnerId() {
        return this.ownerId;
    }
    /**
     * ���ܣ����û���ID
     * @author hug 2012-3-6 
     * @param ownerId
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    
    /**
     * ���ܣ����������
     * @author hug 2012-4-16 
     * @return
     */
    public String getBoxcode() {
        return boxcode;
    }
    /**
     * ���ܣ�����������
     * @author hug 2012-4-16 
     * @param boxcode
     */
    public void setBoxcode(String boxcode) {
        this.boxcode = boxcode;
    }

    /**
     * ���ܣ���ò�Ʒ����
     * @author hug 2012-10-18 
     * @return
     */
    public String getProductcode() {
        return productcode;
    }

    /**
     * ���ܣ����ò�Ʒ����
     * @author hug 2012-10-18 
     * @param productcode
     */
    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

}