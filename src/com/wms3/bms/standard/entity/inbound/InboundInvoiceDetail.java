package com.wms3.bms.standard.entity.inbound;



/**
 * ��ⵥ��ϸ
 */
public class InboundInvoiceDetail  implements java.io.Serializable {

	private static final long serialVersionUID = 92095308929677463L;
	
	 private String instockdetailid;	//��ⵥ��ϸID
     private String instockid;			//��ⵥ�ݱ��
     private String productid;			//Ʒ�����
     private String packid;				//��װ
     private String eunit;				//��λ
     private Integer boxnum;			//������������
     private Integer plattenum;			//��������������
     private double invoicenum;			//������������
     private Integer puboxnum;			//�������ϼܣ�
     private Integer puplattenum;		//���������ϼܣ�
     private double pucnum;				//�������ϼܣ�
     private String lotid;				//��������ID
     private double volume;				//���
     private double weight;				//����
     private double netweight;			//����
     private double puvolume;			//�ϼ����
     private double puweight;			//�ϼ�����
     private double punetweight;		//�ϼܾ���
     private double price;				//����
     private String reserve1;			//Ԥ���ֶ�1
     private String reserve2;			//Ԥ���ֶ�2
     private String reserve3;			//Ԥ���ֶ�3
     private String reserve4;			//Ԥ���ֶ�4
     private String providerid;			//��Ӧ�̱��
     private String linestatus;         //������״̬0-�½� 5-����
     private String reincoiceid;        //�ջ�����
     private String reinvoicedetailid;  //�ջ�����ϸID
     public String lotatt1;  			//��������1
     public String lotatt2;  			//��������2
     public String lotatt3;  			//��������3
     public String lotatt4;  			//��������4
     public String lotatt5;  			//��������5
     public String lotatt6;  			//��������6
     public String lotatt7;  			//��������7
     public String lotatt8;  			//��������8
     public String lotatt9;  			//��������9
     public String lotatt10;  			//��������10
     public String lotatt11;  			//��������11
     public String lotatt12;  			//��������12
     
     //��������
     protected String m_strProviderName;	// ��Ӧ��
     protected String m_strStatusName;		// ״̬��
     protected String m_strProductName;		// ��Ʒ
     protected String pkgdesc;  			// ��װ����
     

    // Constructors

    /** default constructor */
    public InboundInvoiceDetail() {
    }

	/** minimal constructor */
    public InboundInvoiceDetail(String productid, String eunit, double invoicenum) {
        this.productid = productid;
        this.eunit = eunit;
        this.invoicenum = invoicenum;
    }

   
    // Property accessors
    /**
     * ���ܣ������ⵥ��ϸID
     */
    public String getInstockdetailid() {
        return this.instockdetailid;
    }
    /**
     * ���ܣ�������ⵥ��ϸID
     * @param instockdetailid
     */
    public void setInstockdetailid(String instockdetailid) {
        this.instockdetailid = instockdetailid;
    }
    /**
     * ���ܣ������ⵥ�ݱ��
     * @return
     */
    public String getInstockid() {
        return this.instockid;
    }
    /**
     * ���ܣ�������ⵥ�ݱ��
     * @param instockid
     */
    public void setInstockid(String instockid) {
        this.instockid = instockid;
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
     * ���ܣ����������������
     * @return
     */
    public double getInvoicenum() {
        return this.invoicenum;
    }
    /**
     * ���ܣ�����������������
     * @param invoicenum
     */
    public void setInvoicenum(double invoicenum) {
        this.invoicenum = invoicenum;
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
     * ���ܣ���ù�Ӧ�̱��
     * @author hug 2012-3-22 
     * @return
     */
    public String getProviderid() {
        return providerid;
    }

    /**
     * ���ܣ����ù�Ӧ�̱��
     * @author hug 2012-3-22 
     * @param providerid
     */
    public void setProviderid(String providerid) {
        this.providerid = providerid;
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
     * ���ܣ���ù�Ӧ��
     * @author hug 2012-6-25 
     * @return
     */
    public String getM_strProviderName() {
        return m_strProviderName;
    }

    /**
     * ���ܣ����ù�Ӧ��
     * @author hug 2012-6-25 
     * @param providerName
     */
    public void setM_strProviderName(String providerName) {
        m_strProviderName = providerName;
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
	 * ���ܣ���ð�װ
	 */
	public String getPackid() {
		return packid;
	}

	/**
	 * ���ܣ����ð�װ
	 * @param packid
	 */
	public void setPackid(String packid) {
		this.packid = packid;
	}

	/**
	 * ���ܣ�����������ϼܣ�
	 */
	public Integer getPuboxnum() {
		return puboxnum;
	}

	/**
	 * ���ܣ������������ϼܣ�
	 * @param puboxnum
	 */
	public void setPuboxnum(Integer puboxnum) {
		this.puboxnum = puboxnum;
	}

	/**
	 * ���ܣ�����������ϼܣ�
	 */
	public double getPucnum() {
		return pucnum;
	}

	/**
	 * ���ܣ������������ϼܣ�
	 * @param pucnum
	 */
	public void setPucnum(double pucnum) {
		this.pucnum = pucnum;
	}

	/**
	 * ���ܣ�����ϼܾ���
	 */
	public double getPunetweight() {
		return punetweight;
	}

	/**
	 * ���ܣ������ϼܾ���
	 * @param punetweight
	 */
	public void setPunetweight(double punetweight) {
		this.punetweight = punetweight;
	}

	/**
	 * ���ܣ�������������ϼܣ�
	 */
	public Integer getPuplattenum() {
		return puplattenum;
	}

	/**
	 * ���ܣ��������������ϼܣ�
	 * @param puplattenum
	 */
	public void setPuplattenum(Integer puplattenum) {
		this.puplattenum = puplattenum;
	}

	/**
	 * ���ܣ�����ϼ����
	 */
	public double getPuvolume() {
		return puvolume;
	}

	/**
	 * ���ܣ������ϼ����
	 * @param puvolume
	 */
	public void setPuvolume(double puvolume) {
		this.puvolume = puvolume;
	}

	/**
	 * ���ܣ�����ϼ�����
	 */
	public double getPuweight() {
		return puweight;
	}

	/**
	 * ���ܣ������ϼ�����
	 * @param puweight
	 */
	public void setPuweight(double puweight) {
		this.puweight = puweight;
	}

	/**
	 * ���ܣ�����ջ�����
	 */
	public String getReincoiceid() {
		return reincoiceid;
	}

	/**
	 * ���ܣ������ջ�����
	 * @param reincoiceid
	 */
	public void setReincoiceid(String reincoiceid) {
		this.reincoiceid = reincoiceid;
	}

	/**
	 * ���ܣ�����ջ�����ϸID
	 */
	public String getReinvoicedetailid() {
		return reinvoicedetailid;
	}

	/**
	 * ���ܣ������ջ�����ϸID
	 * @param reinvoicedetailid
	 */
	public void setReinvoicedetailid(String reinvoicedetailid) {
		this.reinvoicedetailid = reinvoicedetailid;
	}

	/**
	 * ���ܣ����Ԥ���ֶ�1
	 */
	public String getReserve1() {
		return reserve1;
	}

	/**
	 * ���ܣ�����Ԥ���ֶ�1
	 * @param reserve1
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	/**
	 * ���ܣ����Ԥ���ֶ�2
	 */
	public String getReserve2() {
		return reserve2;
	}

	/**
	 * ���ܣ�����Ԥ���ֶ�2
	 * @param reserve2
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	/**
	 * ���ܣ����Ԥ���ֶ�3
	 */
	public String getReserve3() {
		return reserve3;
	}

	/**
	 * ���ܣ�����Ԥ���ֶ�3
	 * @param reserve3
	 */
	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	/**
	 * ���ܣ����Ԥ���ֶ�4
	 */
	public String getReserve4() {
		return reserve4;
	}

	/**
	 * ���ܣ�����Ԥ���ֶ�4
	 * @param reserve4
	 */
	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	/**
	 * ���ܣ���ð�װ����
	 */
	public String getPkgdesc() {
		return pkgdesc;
	}

	/**
	 * ���ܣ����ð�װ����
	 * @param pkgdesc
	 */
	public void setPkgdesc(String pkgdesc) {
		this.pkgdesc = pkgdesc;
	}
    
}