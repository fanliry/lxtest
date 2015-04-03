package com.wms3.bms.standard.entity.inbound;



/**
 * �ջ�����ϸ
 */
public class InboundReceiptDetail  implements java.io.Serializable {
	 /**  */
    private static final long serialVersionUID = -1294713723821283384L;
    
    private String reincoicedetailid;  /* �ջ�����ϸID */
    private String linestatus;          /* ������״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ� */
    private String reinvoiceid;         /* �ջ����ݱ�� */
    private String productid;           /* Ʒ�� */
    private String packid;              /* ��װ */
    private String eunit;               /* ��λ */
    private Integer boxnum;             /* ���� */
    private String rejectcode;          /* ����ԭ����� */
    private String rejectreason;        /* ����ԭ������ */
    private String holdcode;            /* ����ԭ����� */
    private String holdreason;          /* ����ԭ������ */
    private String skustatus;           /* ��Ʒ״̬���� */
    private String skustatus_descr;     /* ��Ʒ״̬���� */
    private Integer plattenum;          /* �������� */
    private double invoicenum;          /* Ԥ�������������� */
    private double recnum;              /* �ջ��������ջ��� */
    private double rejectednum;         /* �������� */
    private double holdnum;             /* �������� */
    private String reclocation;         /* �ջ����� */
    private String receivedtime;        /* �ջ�ʱ�� */
    private String lotid;            	/* ��������ID */
    private double volume;              /* ��� */
    private double weight;              /* ���� */
    private double netweight;           /* ���� */
    private double reweight;            /* �ջ����� */
    private double renetweight;         /* �ջ����� */
    private double revolume;            /* �ջ���� */
    private double price;               /* ���� */
    private String reserve1;            /* Ԥ���ֶ�1*/
    private String reserve2;            /* Ԥ���ֶ�2 */
    private String reserve3;            /* Ԥ���ֶ�3 */
    private String reserve4;            /* Ԥ���ֶ�4 */
    public String lotatt1;  			// ��������1
    public String lotatt2;  			// ��������2
    public String lotatt3;  			// ��������3
    public String lotatt4;  			// ��������4
    public String lotatt5;  			// ��������5
    public String lotatt6;  			// ��������6
    public String lotatt7;  			// ��������7
    public String lotatt8;  			// ��������8
    public String lotatt9;  			// ��������9
    public String lotatt10;  			// ��������10
    public String lotatt11;  			// ��������11
    public String lotatt12;  			// ��������12
     
     
     //��������
     private String m_strStatusName;           // ״̬��
     private String m_strProductName;          // ��Ʒ
     private String m_strPackName;       //��װ����
     private String m_strUnitName;       //��λ����
     private String m_strRejectCodeText; //���ձ�����ʾ����
     private String m_strHoldCodeText;   //���������ʾ����
     private String m_strOverRcvFlag;    //SKU�Ƿ��������ջ�



    // Constructors

    /** default constructor */
    public InboundReceiptDetail() {
    }
   
    // Property accessors


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
     * ���ܣ����Ʒ�����
     * @return
     */
    public String getProductid() {
        return this.productid;
    }
    /**
     * ���ܣ�����Ʒ�����
     * @param productid
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }
    /**
     * ���ܣ������С��λ
     * @return
     */
    public String getEunit() {
        return this.eunit;
    }
    /**
     * ���ܣ�������С��λ
     * @param eunit
     */
    public void setEunit(String eunit) {
        this.eunit = eunit;
    }
    /**
     * ���ܣ����������������
     * @return
     */
    public Integer getBoxnum() {
        return this.boxnum;
    }
    /**
     * ���ܣ�����������������
     * @param boxnum
     */
    public void setBoxnum(Integer boxnum) {
        this.boxnum = boxnum;
    }
    /**
     * ���ܣ������������������
     * @return
     */
    public Integer getPlattenum() {
        return this.plattenum;
    }
    /**
     * ���ܣ�������������������
     * @param plattenum
     */
    public void setPlattenum(Integer plattenum) {
        this.plattenum = plattenum;
    }
    /**
     * ���ܣ������С��λ������������
     * @return
     */
    public double getInvoicenum() {
        return this.invoicenum;
    }
    /**
     * ���ܣ�������С��λ������������
     * @param invoicenum
     */
    public void setInvoicenum(double invoicenum) {
        this.invoicenum = invoicenum;
    }

    /**
     * ���ܣ������С��λ�������ջ���
     * @return
     */
    public double getRecnum() {
        return this.recnum;
    }
    /**
     * ���ܣ�������С��λ�������ջ���
     * @param recnum
     */
    public void setRecnum(double recnum) {
        this.recnum = recnum;
    }
    /**
     * ���ܣ�������
     * @return
     */
    public double getVolume() {
        return this.volume;
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
     * @return
     */
    public double getWeight() {
        return this.weight;
    }
    /**
     * ���ܣ���������
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
    /**
     * ���ܣ���þ���
     * @return
     */
    public double getNetweight() {
        return this.netweight;
    }
    /**
     * ���ܣ����þ���
     * @param netweight
     */
    public void setNetweight(double netweight) {
        this.netweight = netweight;
    }
    /**
     * ���ܣ�����ջ�����
     * @return
     */
    public double getReweight() {
        return this.reweight;
    }
    /**
     * ���ܣ������ջ�����
     * @param reweight
     */
    public void setReweight(double reweight) {
        this.reweight = reweight;
    }
    /**
     * ���ܣ�����ջ�����
     * @return
     */
    public double getRenetweight() {
        return this.renetweight;
    }
    /**
     * ���ܣ������ջ�����
     * @param renetweight
     */
    public void setRenetweight(double renetweight) {
        this.renetweight = renetweight;
    }
    /**
     * ���ܣ�����ջ�����
     * @return
     */
    public String getReclocation() {
        return this.reclocation;
    }
    /**
     * ���ܣ������ջ�����
     * @param reclocation
     */
    public void setReclocation(String reclocation) {
        this.reclocation = reclocation;
    }
    /**
     * ���ܣ�����ջ����
     * @return
     */
    public double getRevolume() {
        return this.revolume;
    }
    /**
     * ���ܣ������ջ����
     * @param revolume
     */
    public void setRevolume(double revolume) {
        this.revolume = revolume;
    }
    /**
     * ���ܣ���õ���
     * @return
     */
    public double getPrice() {
        return this.price;
    }
    /**
     * ���ܣ����õ���
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * ���ܣ���õ�����״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
     * @author hug 2012-3-22 
     * @return
     */
    public String getLinestatus() {
        return linestatus;
    }

    /**
     * ���ܣ����õ�����״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
     * @author hug 2012-3-22 
     * @param linestatus
     */
    public void setLinestatus(String linestatus) {
        this.linestatus = linestatus;
    }

    /**
     * ���ܣ���ö���ԭ�����
     * @author hug 2012-6-26 
     * @return
     */
    public String getHoldcode() {
        return holdcode;
    }
    /**
     * ���ܣ����ö���ԭ�����
     * @author hug 2012-6-26 
     * @param holdcode
     */
    public void setHoldcode(String holdcode) {
        this.holdcode = holdcode;
    }
    /**
     * ���ܣ���ö�������
     * @author hug 2012-6-26 
     * @return
     */
    public double getHoldnum() {
        return holdnum;
    }


    /**
     * ���ܣ����ö�������
     * @author hug 2012-6-26 
     * @param holdnum
     */
    public void setHoldnum(double holdnum) {
        this.holdnum = holdnum;
    }

    /**
     * ���ܣ���ö���ԭ������
     * @author hug 2012-6-26 
     * @return
     */
    public String getHoldreason() {
        return holdreason;
    }
    /**
     * ���ܣ����ö���ԭ������
     * @author hug 2012-6-26 
     * @param holdreason
     */
    public void setHoldreason(String holdreason) {
        this.holdreason = holdreason;
    }


    /**
     * ���ܣ���ð�װ
     * @author hug 2012-6-26 
     * @return
     */
    public String getPackid() {
        return packid;
    }


    /**
     * ���ܣ����ð�װ
     * @author hug 2012-6-26 
     * @param packid
     */
    public void setPackid(String packid) {
        this.packid = packid;
    }
    /**
     * ���ܣ�����ջ�ʱ��
     * @author hug 2012-6-26 
     * @return
     */
    public String getReceivedtime() {
        return receivedtime;
    }

    /**
     * ���ܣ������ջ�ʱ��
     * @author hug 2012-6-26 
     * @param receivedtime
     */
    public void setReceivedtime(String receivedtime) {
        this.receivedtime = receivedtime;
    }

    /**
     * ���ܣ�����ջ�����ϸID
     * @author hug 2012-6-26 
     * @return
     */
    public String getReincoicedetailid() {
        return reincoicedetailid;
    }

    /**
     * ���ܣ������ջ�����ϸID
     * @author hug 2012-6-26 
     * @param reincoicedetailid
     */
    public void setReincoicedetailid(String reincoicedetailid) {
        this.reincoicedetailid = reincoicedetailid;
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
     * ���ܣ���þ���ԭ�����
     * @author hug 2012-6-26 
     * @return
     */
    public String getRejectcode() {
        return rejectcode;
    }
    /**
     * ���ܣ����þ���ԭ�����
     * @author hug 2012-6-26 
     * @param rejectcode
     */
    public void setRejectcode(String rejectcode) {
        this.rejectcode = rejectcode;
    }
    /**
     * ���ܣ���þ�������
     * @author hug 2012-6-26 
     * @return
     */
    public double getRejectednum() {
        return rejectednum;
    }
    /**
     * ���ܣ����þ�������
     * @author hug 2012-6-26 
     * @param rejectednum
     */
    public void setRejectednum(double rejectednum) {
        this.rejectednum = rejectednum;
    }
    /**
     * ���ܣ���þ���ԭ������
     * @author hug 2012-6-26 
     * @return
     */
    public String getRejectreason() {
        return rejectreason;
    }
    /**
     * ���ܣ����þ���ԭ������
     * @author hug 2012-6-26 
     * @param rejectreason
     */
    public void setRejectreason(String rejectreason) {
        this.rejectreason = rejectreason;
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
     * ���ܣ���ò�Ʒ״̬����
     * @author hug 2012-6-26 
     * @return
     */
    public String getSkustatus() {
        return skustatus;
    }
    /**
     * ���ܣ����ò�Ʒ״̬����
     * @author hug 2012-6-26 
     * @param skustatus
     */
    public void setSkustatus(String skustatus) {
        this.skustatus = skustatus;
    }
    /**
     * ���ܣ���ò�Ʒ״̬����
     * @author hug 2012-6-26 
     * @return
     */
    public String getSkustatus_descr() {
        return skustatus_descr;
    }
    /**
     * ���ܣ����ò�Ʒ״̬����
     * @author hug 2012-6-26 
     * @param skustatus_descr
     */
    public void setSkustatus_descr(String skustatus_descr) {
        this.skustatus_descr = skustatus_descr;
    }

    /**
     * ���ܣ���ò�Ʒ
     * @author hug 2012-6-20 
     * @return
     */
    public String getM_strProductName() {
        return m_strProductName;
    }


    /**
     * ���ܣ����ò�Ʒ
     * @author hug 2012-6-20 
     * @param productName
     */
    public void setM_strProductName(String productName) {
        m_strProductName = productName;
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
     * ���ܣ���ö��������ʾ����
     * @author hug 2012-6-26 
     * @return
     */
    public String getM_strHoldCodeText() {
        return m_strHoldCodeText;
    }
    /**
     * ���ܣ����ö��������ʾ����
     * @author hug 2012-6-26 
     * @param holdCodeText
     */
    public void setM_strHoldCodeText(String holdCodeText) {
        m_strHoldCodeText = holdCodeText;
    }
    /**
     * ���ܣ����SKU�Ƿ��������ջ�
     * @author hug 2012-6-26 
     * @return
     */
    public String getM_strOverRcvFlag() {
        return m_strOverRcvFlag;
    }
    /**
     * ���ܣ�����SKU�Ƿ��������ջ�
     * @author hug 2012-6-26 
     * @param overRcvFlag
     */
    public void setM_strOverRcvFlag(String overRcvFlag) {
        m_strOverRcvFlag = overRcvFlag;
    }

    /**
     * ���ܣ���þ��ձ�����ʾ����
     * @author hug 2012-6-26 
     * @return
     */
    public String getM_strRejectCodeText() {
        return m_strRejectCodeText;
    }
    /**
     * ���ܣ����þ��ձ�����ʾ����
     * @author hug 2012-6-26 
     * @param rejectCodeText
     */
    public void setM_strRejectCodeText(String rejectCodeText) {
        m_strRejectCodeText = rejectCodeText;
    }

    /**
     * ���ܣ���ð�װ����
     * @author hug 2012-8-22 
     * @return
     */
    public String getM_strPackName() {
        return m_strPackName;
    }

    /**
     * ���ܣ����ð�װ����
     * @author hug 2012-8-22 
     * @param packName
     */
    public void setM_strPackName(String packName) {
        m_strPackName = packName;
    }

    /**
     * ���ܣ���õ�λ����
     * @author hug 2012-8-22 
     * @return
     */
    public String getM_strUnitName() {
        return m_strUnitName;
    }

    /**
     * ���ܣ����õ�λ����
     * @author hug 2012-8-22 
     * @param unitName
     */
    public void setM_strUnitName(String unitName) {
        m_strUnitName = unitName;
    }
    
    
    
}