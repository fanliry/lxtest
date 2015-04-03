package com.wms3.bms.standard.entity.inbound;



/**
 * �ջ���¼��
 */
public class InboundReceiptTransaction  implements java.io.Serializable {
	 /**  */
    private static final long serialVersionUID = -1294713723821283384L;
    
    private String transreceiptid;		//�ջ���¼ID
    private String transstatus;			//״̬  (transstatus)    1:�ջ���ɣ�2:������; 3:�����ϼ�; 4����ȫ�ϼ� 5��ȡ���ջ�
    private String reinvoiceid;			//�ջ����ݱ��
    private String reinvoicedetailid;	//�ջ�����ϸID
    private String warehouseid;			//�ֿ���
    private String receipttime;			//�ջ�ʱ��
    private String receiptmanid;		//�ջ���
    private String receiptrf;			//�ջ��豸RF
    private String traycode;			//��������
    private String ownerid;				//����
    private String productid;			//��Ʒ
    private String packid;				//��װ
    private String eunit;				//��λ
    private double recnum;				//�ջ�����
    private double reweight;			//�ջ�����
    private double renetweight;			//�ջ�����
    private double revolume;			//�ջ����
    private double pucnum;				//�ϼ�����
    private double puweight;			//�ϼ�����
    private double punetweight;			//�ϼܾ���
    private double puvolume;			//�ϼ����
    private String lotid;				//��������ID
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
     private String m_strProductName;    // ��Ʒ
     private String m_strPackName;       //��װ����
     private String m_strUnitName;       //��λ����
     private String ownername;           //����
     private String receiptmanname;      //�ջ���
     
     private String strStatusName;      // ״̬��

     //�����ֶ�
     private String boxcode;            //������
     private String productcode;        //��Ʒ����

    // Constructors

    /** default constructor */
    public InboundReceiptTransaction() {
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
	 * ���ܣ���õ�λ
	 */
	public String getEunit() {
		return eunit;
	}

	/**
	 * ���ܣ����õ�λ
	 * @param eunit
	 */
	public void setEunit(String eunit) {
		this.eunit = eunit;
	}

	/**
	 * ���ܣ���ò�Ʒ
	 */
	public String getM_strProductName() {
		return m_strProductName;
	}

	/**
	 * ���ܣ����ò�Ʒ
	 * @param productName
	 */
	public void setM_strProductName(String productName) {
		m_strProductName = productName;
	}

	/**
	 * ���ܣ���û���
	 */
	public String getOwnerid() {
		return ownerid;
	}

	/**
	 * ���ܣ����û���
	 * @param ownerid
	 */
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
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
	 * ���ܣ���ò�Ʒ
	 */
	public String getProductid() {
		return productid;
	}

	/**
	 * ���ܣ����ò�Ʒ
	 * @param productid
	 */
	public void setProductid(String productid) {
		this.productid = productid;
	}

	/**
	 * ���ܣ�����ϼ�����
	 */
	public double getPucnum() {
		return pucnum;
	}

	/**
	 * ���ܣ������ϼ�����
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
	 * ���ܣ�����ջ���
	 */
	public String getReceiptmanid() {
		return receiptmanid;
	}

	/**
	 * ���ܣ������ջ���
	 * @param receiptmanid
	 */
	public void setReceiptmanid(String receiptmanid) {
		this.receiptmanid = receiptmanid;
	}

	/**
	 * ���ܣ�����ջ��豸RF
	 */
	public String getReceiptrf() {
		return receiptrf;
	}

	/**
	 * ���ܣ������ջ��豸RF
	 * @param receiptrf
	 */
	public void setReceiptrf(String receiptrf) {
		this.receiptrf = receiptrf;
	}

	/**
	 * ���ܣ�����ջ�ʱ��
	 */
	public String getReceipttime() {
		return receipttime;
	}

	/**
	 * ���ܣ������ջ�ʱ��
	 * @param receipttime
	 */
	public void setReceipttime(String receipttime) {
		this.receipttime = receipttime;
	}

	/**
	 * ���ܣ�����ջ�����
	 */
	public double getRecnum() {
		return recnum;
	}

	/**
	 * ���ܣ������ջ�����
	 * @param recnum
	 */
	public void setRecnum(double recnum) {
		this.recnum = recnum;
	}

	/**
	 * ���ܣ�����ջ�����ϸID
	 */
	public String getReinvoicedetailid() {
		return reinvoicedetailid;
	}

	/**
	 * ���ܣ������ջ�����ϸID
	 * @param reincoicedetailid
	 */
	public void setReinvoicedetailid(String reinvoicedetailid) {
		this.reinvoicedetailid = reinvoicedetailid;
	}

	/**
	 * ���ܣ�����ջ����ݱ��
	 */
	public String getReinvoiceid() {
		return reinvoiceid;
	}

	/**
	 * ���ܣ������ջ����ݱ��
	 * @param reinvoiceid
	 */
	public void setReinvoiceid(String reinvoiceid) {
		this.reinvoiceid = reinvoiceid;
	}

	/**
	 * ���ܣ�����ջ�����
	 */
	public double getRenetweight() {
		return renetweight;
	}

	/**
	 * ���ܣ������ջ�����
	 * @param renetweight
	 */
	public void setRenetweight(double renetweight) {
		this.renetweight = renetweight;
	}

	/**
	 * ���ܣ�����ջ����
	 */
	public double getRevolume() {
		return revolume;
	}

	/**
	 * ���ܣ������ջ����
	 * @param revolume
	 */
	public void setRevolume(double revolume) {
		this.revolume = revolume;
	}

	/**
	 * ���ܣ�����ջ�����
	 */
	public double getReweight() {
		return reweight;
	}

	/**
	 * ���ܣ������ջ�����
	 * @param reweight
	 */
	public void setReweight(double reweight) {
		this.reweight = reweight;
	}

	/**
	 * ���ܣ�����ջ���¼ID
	 */
	public String getTransreceiptid() {
		return transreceiptid;
	}

	/**
	 * ���ܣ������ջ���¼ID
	 * @param transreceiptid
	 */
	public void setTransreceiptid(String transreceiptid) {
		this.transreceiptid = transreceiptid;
	}

	/**
	 * ���ܣ����״̬(transstatus)1:�ջ���ɣ�2:������; 3:�����ϼ�; 4����ȫ�ϼ� 5��ȡ���ջ�
	 */
	public String getTransstatus() {
		return transstatus;
	}

	/**
	 * ���ܣ�����״̬(transstatus)1:�ջ���ɣ�2:������; 3:�����ϼ�; 4����ȫ�ϼ� 5��ȡ���ջ�
	 * @param transstatus
	 */
	public void setTransstatus(String transstatus) {
		this.transstatus = transstatus;
	}

	/**
	 * ���ܣ������������
	 */
	public String getTraycode() {
		return traycode;
	}

	/**
	 * ���ܣ�������������
	 * @param traycode
	 */
	public void setTraycode(String traycode) {
		this.traycode = traycode;
	}

	/**
	 * ���ܣ���òֿ���
	 */
	public String getWarehouseid() {
		return warehouseid;
	}

	/**
	 * ���ܣ����òֿ���
	 * @param warehouseid
	 */
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}

    /**
     * 
     * ����:��ð�װ����
     * @author hug 2012-8-23 
     * @return
     */
    public String getM_strPackName() {
        return m_strPackName;
    }

    /**
     * 
     * ����:���ð�װ����
     * @author hug 2012-8-23 
     * @param packName
     */
    public void setM_strPackName(String packName) {
        m_strPackName = packName;
    }

    /**
     * 
     * ����:��õ�λ����
     * @author hug 2012-8-23 
     * @return
     */
    public String getM_strUnitName() {
        return m_strUnitName;
    }

    /**
     * 
     * ����:���õ�λ����
     * @author hug 2012-8-23 
     * @param unitName
     */
    public void setM_strUnitName(String unitName) {
        m_strUnitName = unitName;
    }

    /**
     * 
     * ����:��û���
     * @author hug 2012-8-23 
     * @return
     */
    public String getOwnername() {
        return ownername;
    }

    /**
     * 
     * ����:���û���
     * @author hug 2012-8-23 
     * @param ownername
     */
    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    /**
     * 
     * ����:����ջ���
     * @author hug 2012-8-23 
     * @return
     */
    public String getReceiptmanname() {
        return receiptmanname;
    }

    /**
     * ����:�����ջ���
     * @author hug 2012-8-23 
     * @param receiptmanname
     */
    public void setReceiptmanname(String receiptmanname) {
        this.receiptmanname = receiptmanname;
    }

    /**
     * ����:���״̬��
     * @author hug 2012-8-31 
     * @return
     */
    public String getStrStatusName() {
        return strStatusName;
    }

    /**
     * ����:����״̬��
     * @author hug 2012-8-31 
     * @param strStatusName
     */
    public void setStrStatusName(String strStatusName) {
        this.strStatusName = strStatusName;
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