package com.wms3.bms.standard.entity.outbound;


/**
 * ���������ⵥ��ϸ
 * @author hug
 *
 */
public class OutboundInvoiceDetail  implements java.io.Serializable {
     
   
	private static final long serialVersionUID = 4012223973119209218L;
	
	private String outstockdetailid;	//���ⵥ��ϸID
	private String outstockid;			//���ⵥ�ݺ�       
	private String linestatus;          //������״̬ 0-����;1-Ԥ�� 2-���� 3-���ּ�� 4-��ȫ���   7-����
	private String productid;			//��ƷID
	private String lotid;				//��������ID
	private String cargoSpaceId;		//��λId
	private String cargoAlleyId;		//���Id
	private String whAreaId;			//����ID
	private String packid;				//��װID
	private String pkgunit;				//��װ��λ
    private double invoicenum;          //��������
    private double preassignnum;        //Ԥ������
    private double picknum;             //������� ����ҵ����ʱ ���˳ɹ� ���� ͬʱ�޸���������
    private double sendnum;             //��������
    private String customid;            //�ͻ�id
    private String fbFlag;              //��Դ��ʶ 1-ָ����Ʒ 2-ָ����λ 3-���۶��� 4-�ⲿ������
    private String traycode;            //��������
    private double netweight;           //����
    private double weight;              //����
    private double volume;              //���
    private double price;               /* ���� */
    private double snetweight;          //��������
    private double sweight;             //��������
    private double svolume;             //�������
    private double preassignnetweight;  //Ԥ�侻��
    private double preassignweight;     //Ԥ������
    private double preassignvolume;     //Ԥ�����
    private double assignnetweight;     //���侻��
    private double assignweight;        //��������
    private double assignvolume;        //�������
    private double picknetweight;       //�������
    private double pickweight;          //�������
    private double pickvolume;          //������
    private String confirmanid;         //ȷ����Id
    private String confirmdate;         //ȷ��ʱ��
    private String reserve1;            //Ԥ���ֶ�1
    private String reserve2;            //Ԥ���ֶ�2
    private String reserve3;            //Ԥ���ֶ�3
    private String reserve4;            //Ԥ���ֶ�4
    public String lotatt1;             //��������1
    public String lotatt2;             //��������2
    public String lotatt3;             //��������3
    public String lotatt4;             //��������4
    public String lotatt5;             //��������5
    public String lotatt6;             //��������6
    public String lotatt7;             //��������7
    public String lotatt8;             //��������8
    public String lotatt9;             //��������9
    public String lotatt10;            //��������10
    public String lotatt11;            //��������11
    public String lotatt12;            //��������12
    
    public String lotinfo;             //������Ϣ
    public String printdate;          	//��������
    private String Virtualwhid;      //�߼�����id

    
	//��������
    private double assignnum;          //��������
    private String Virtualwhname;     //�߼���������
    private String m_strProductName;    //Ʒ��
    protected String m_strCustomerName; // �ͻ�	
    private String m_strProductCode;//��Ʒ����
    private String m_strSpec;//��Ʒ���

	private String m_strStatusName;     // ״̬��
    private String m_strPackName;       //��װ����
    private String m_strUnitName;       //��λ����

    //��������
    private String soid;             /* ����SO�� */
    private String sodetailid;       /* ����SO�к� */
    
    // Constructors

    /** default constructor */
    public OutboundInvoiceDetail() {
    }

	/** minimal constructor */
    public OutboundInvoiceDetail(String productid) {
        this.productid = productid;
    }
    public String getVirtualwhid() {
		return Virtualwhid;
	}

	public void setVirtualwhid(String virtualwhid) {
		Virtualwhid = virtualwhid;
	}

	public String getVirtualwhname() {
		return Virtualwhname;
	}

	public void setVirtualwhname(String virtualwhname) {
		Virtualwhname = virtualwhname;
	}
   
    // Property accessors
    /**
     * ���ܣ���ó��ⵥ��ϸID
     */
    public String getOutstockdetailid() {
        return this.outstockdetailid;
    }
    /**
     * ���ܣ����ó��ⵥ��ϸID
     * @param outstockdetailid
     */
    public void setOutstockdetailid(String outstockdetailid) {
        this.outstockdetailid = outstockdetailid;
    }
    /**
     * ���ܣ���ȡ��Ʒ����
     * @return
     */
    public String getM_strProductCode() {
		return m_strProductCode;
	}
    /**
     * ���ܣ����ò�Ʒ����
     * @param mStrProductCode
     */
	public void setM_strProductCode(String mStrProductCode) {
		m_strProductCode = mStrProductCode;
	}
	
	public String getM_strSpec() {
		return m_strSpec;
	}

	public void setM_strSpec(String mStrSpec) {
		m_strSpec = mStrSpec;
	}
    /**
     * ���ܣ���ó��ⵥ�ݺ�
     * @return
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
     * ���ܣ������Ʒ���
     * @return
     */
    public String getProductid() {
        return this.productid;
    }
    /**
     * ���ܣ�������Ʒ���
     * @param productid
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }
    /**
     * ���ܣ���û�λId
     * @return
     */
    public String getCargoSpaceId() {
        return this.cargoSpaceId;
    }
    /**
     * ���ܣ����û�λId
     * @param cargoSpaceId
     */
    public void setCargoSpaceId(String cargoSpaceId) {
        this.cargoSpaceId = cargoSpaceId;
    }
    /**
     * ���ܣ�������Id
     * @return
     */
    public String getCargoAlleyId() {
        return this.cargoAlleyId;
    }
    /**
     * ���ܣ��������Id
     * @param cargoAlleyId
     */
    public void setCargoAlleyId(String cargoAlleyId) {
        this.cargoAlleyId = cargoAlleyId;
    }
    /**
     * ���ܣ���ÿ���ID
     * @return
     */
    public String getWhAreaId() {
        return this.whAreaId;
    }
    /**
     * ���ܣ����ÿ���ID
     * @param whAreaId
     */
    public void setWhAreaId(String whAreaId) {
        this.whAreaId = whAreaId;
    }
    /**
     * ���ܣ���ð�װ��λ
     * @return
     */
    public String getPkgunit() {
        return this.pkgunit;
    }
    /**
     * ���ܣ����ð�װ��λ
     * @param pkgunit
     */
    public void setPkgunit(String pkgunit) {
        this.pkgunit = pkgunit;
    }
    /**
     * ���ܣ���ÿ�������
     * @return
     */
    public double getInvoicenum() {
        return this.invoicenum;
    }
    /**
     * ���ܣ����ÿ�������
     * @param invoicenum
     */
    public void setInvoicenum(double invoicenum) {
        this.invoicenum = invoicenum;
    }
    /**
     * ���ܣ����Ԥ������
     * @return
     */
    public double getPreassignnum() {
        return this.preassignnum;
    }
    /**
     * ���ܣ�����Ԥ������
     * @param preassignnum
     */
    public void setPreassignnum(double preassignnum) {
        this.preassignnum = preassignnum;
    }
    /**
     * ���ܣ���÷�������
     * @return
     */
    public double getAssignnum() {
        return this.assignnum;
    }
    /**
     * ���ܣ����÷�������
     * @param assignnum
     */
    public void setAssignnum(double assignnum) {
        this.assignnum = assignnum;
    }
    /**
     * ���ܣ���ü������
     * @return
     */
    public double getPicknum() {
        return this.picknum;
    }
    /**
     * ���ܣ����ü������
     * @param picknum
     */
    public void setPicknum(double picknum) {
        this.picknum = picknum;
    }
    /**
     * ���ܣ���÷�������
     * @return
     */
    public double getSendnum() {
        return this.sendnum;
    }
    /**
     * ���ܣ����÷�������
     * @param sendnum
     */
    public void setSendnum(double sendnum) {
        this.sendnum = sendnum;
    }
    /**
     * ���ܣ���ÿͻ�id
     * @return
     */
    public String getCustomid() {
        return this.customid;
    }
    /**
     * ���ܣ����ÿͻ�id
     * @param customid
     */
    public void setCustomid(String customid) {
        this.customid = customid;
    }
    /**
     * ���ܣ������Դ��ʶ 1-ָ����Ʒ 2-ָ����λ 3-���۶��� 4-�ⲿ������
     * @return
     */
    public String getFbFlag() {
        return this.fbFlag;
    }
    /**
     * ���ܣ�������Դ��ʶ 1-ָ����Ʒ 2-ָ����λ 3-���۶��� 4-�ⲿ������
     * @param fbFlag
     */
    public void setFbFlag(String fbFlag) {
        this.fbFlag = fbFlag;
    }
    /**
     * ���ܣ������������
     * @return
     */
    public String getTraycode() {
        return this.traycode;
    }
    /**
     * ���ܣ�������������
     * @param traycode
     */
    public void setTraycode(String traycode) {
        this.traycode = traycode;
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
     * ���ܣ���þ���
     * @return
     */
    public double getSnetweight() {
        return this.snetweight;
    }
    /**
     * ���ܣ����þ���
     * @param snetweight
     */
    public void setSnetweight(double snetweight) {
        this.snetweight = snetweight;
    }
    /**
     * ���ܣ��������
     * @return
     */
    public double getSweight() {
        return this.sweight;
    }
    /**
     * ���ܣ���������
     * @param sweight
     */
    public void setSweight(double sweight) {
        this.sweight = sweight;
    }
    /**
     * ���ܣ�������
     * @return
     */
    public double getSvolume() {
        return this.svolume;
    }
    /**
     * ���ܣ��������
     * @param svolume
     */
    public void setSvolume(double svolume) {
        this.svolume = svolume;
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
     * ����:��÷��侻��
     * @author hug 2012-5-21 
     * @return
     */
    public double getAssignnetweight() {
        return assignnetweight;
    }

    /**
     * ���ܣ����÷��侻��
     * @author hug 2012-5-21 
     * @param assignnetweight
     */
    public void setAssignnetweight(double assignnetweight) {
        this.assignnetweight = assignnetweight;
    }

    /**
     * ����:��÷������
     * @author hug 2012-5-21 
     * @return
     */
    public double getAssignvolume() {
        return assignvolume;
    }

    /**
     * ���ܣ����÷������
     * @author hug 2012-5-21 
     * @param assignvolume
     */
    public void setAssignvolume(double assignvolume) {
        this.assignvolume = assignvolume;
    }

    /**
     * ����:��÷�������
     * @author hug 2012-5-21 
     * @return
     */
    public double getAssignweight() {
        return assignweight;
    }

    /**
     * ���ܣ����÷�������
     * @author hug 2012-5-21 
     * @param assignweight
     */
    public void setAssignweight(double assignweight) {
        this.assignweight = assignweight;
    }

    /**
     * ����:��ü������
     * @author hug 2012-5-21 
     * @return
     */
    public double getPicknetweight() {
        return picknetweight;
    }

    /**
     * ���ܣ����ü������
     * @author hug 2012-5-21 
     * @param picknetweight
     */
    public void setPicknetweight(double picknetweight) {
        this.picknetweight = picknetweight;
    }

    /**
     * ����:��ü�����
     * @author hug 2012-5-21 
     * @return
     */
    public double getPickvolume() {
        return pickvolume;
    }

    /**
     * ���ܣ����ü�����
     * @author hug 2012-5-21 
     * @param pickvolume
     */
    public void setPickvolume(double pickvolume) {
        this.pickvolume = pickvolume;
    }

    /**
     * ����:��ü������
     * @author hug 2012-5-21 
     * @return
     */
    public double getPickweight() {
        return pickweight;
    }

    /**
     * ���ܣ����ü������
     * @author hug 2012-5-21 
     * @param pickweight
     */
    public void setPickweight(double pickweight) {
        this.pickweight = pickweight;
    }

    /**
     * ����:���Ԥ�侻��
     * @author hug 2012-5-21 
     * @return
     */
    public double getPreassignnetweight() {
        return preassignnetweight;
    }

    /**
     * ���ܣ�����Ԥ�侻��
     * @author hug 2012-5-21 
     * @param preassignnetweight
     */
    public void setPreassignnetweight(double preassignnetweight) {
        this.preassignnetweight = preassignnetweight;
    }

    /**
     * ����:���Ԥ�����
     * @author hug 2012-5-21 
     * @return
     */
    public double getPreassignvolume() {
        return preassignvolume;
    }

    /**
     * ���ܣ�����Ԥ�����
     * @author hug 2012-5-21 
     * @param preassignvolume
     */
    public void setPreassignvolume(double preassignvolume) {
        this.preassignvolume = preassignvolume;
    }

    /**
     * ����:���Ԥ������
     * @author hug 2012-5-21 
     * @return
     */
    public double getPreassignweight() {
        return preassignweight;
    }

    /**
     * ���ܣ�����Ԥ������
     * @author hug 2012-5-21 
     * @param preassignweight
     */
    public void setPreassignweight(double preassignweight) {
        this.preassignweight = preassignweight;
    }

    
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
	 * ���ܣ�����
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
	 * ���ܣ���õ�����״̬ 0-����;1-Ԥ�� 2-���� 3-���ּ�� 4-��ȫ���   7-����
	 */
	public String getLinestatus() {
		return linestatus;
	}

	/**
	 * ���ܣ����õ�����״̬ 0-����;1-Ԥ�� 2-���� 3-���ּ�� 4-��ȫ���   7-����
	 * @param linestatus
	 */
	public void setLinestatus(String linestatus) {
		this.linestatus = linestatus;
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

	public String getLotinfo() {
		return lotinfo;
	}

	public void setLotinfo(String lotinfo) {
		this.lotinfo = lotinfo;
	}

	public String getPrintdate() {
		return printdate;
	}

	public void setPrintdate(String printdate) {
		this.printdate = printdate;
	}

	public String getSoid() {
		return soid;
	}

	public void setSoid(String soid) {
		this.soid = soid;
	}

	public String getSodetailid() {
		return sodetailid;
	}

	public void setSodetailid(String sodetailid) {
		this.sodetailid = sodetailid;
	}
    
    

}