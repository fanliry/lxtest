package com.wms3.bms.standard.entity.inventory.lxyy;


import java.io.Serializable;

/**
 * ����: ���ᡢ�ͷ�
 * @author hug 2012-10-15
 * @since WMS 3.0
 */
public class InventoryHold implements Serializable{
    
    /** serialVersionUID */
    private static final long serialVersionUID = 4775618188329586131L;
    
    private String inventoryholdid; //ID
    private String status;          //״̬(Ĭ��1,ֱ�Ӷ���)
    private String holdflag;        //�Ƿ񶳽� Y-��,N-��
    private String holdby;          //���᷽�� 1.ֱ�Ӷ��� 2.�������ϸ�Ʒ��
    private String holdcode;        //����ԭ�� 1.��Ʒ���� 2.��Ʒ��
    private String holdreason;      //ԭ������
    private String inventoryid;     //���ID
    private String ownerid;         //����ID
    private String productid ;      //��ƷID
    private String lotid;           //���κ�
    private String spaceid;         //��λ
    private double qtyonhold;       //��������
    private double netweightonhold; //��������
    private String dateon;          //����ʱ��
    private String whoon;           //���������
    private String dateoff;         //�ͷ�ʱ��
    private String whooff;          //�ͷŲ�����
    private String traycode;        //��������
    
    //�����ֶ�
    private String boxcode;            //������
    private String productcode;        //��Ʒ����
    
    //��������
	private String cargoSpaceName;		//��λ����
	private String productName;			//��Ʒ����
	private String ownerName;			//��������
	private String holdbyName;			//���᷽������
	private String holdcodeName;		//����ԭ������
	private String lotName;				//��������
	private String whoonName;			//�������������
	
    
    /** default constructor */
    public InventoryHold() {
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
	/**
	 * ���ܣ�����ͷ�ʱ��
	 */
	public String getDateoff() {
		return dateoff;
	}
	/**
	 * ���ܣ������ͷ�ʱ��
	 * @param dateoff
	 */
	public void setDateoff(String dateoff) {
		this.dateoff = dateoff;
	}
	/**
	 * ���ܣ���ö���ʱ��
	 */
	public String getDateon() {
		return dateon;
	}
	/**
	 * ���ܣ����ö���ʱ��
	 * @param dateon
	 */
	public void setDateon(String dateon) {
		this.dateon = dateon;
	}
	/**
	 * ���ܣ���ö��᷽��
	 */
	public String getHoldby() {
		return holdby;
	}
	/**
	 * ���ܣ����ö��᷽��
	 * @param holdby
	 */
	public void setHoldby(String holdby) {
		this.holdby = holdby;
	}
	/**
	 * ���ܣ���ö���ԭ��
	 */
	public String getHoldcode() {
		return holdcode;
	}
	/**
	 * ���ܣ����ö���ԭ��
	 * @param holdcode
	 */
	public void setHoldcode(String holdcode) {
		this.holdcode = holdcode;
	}
	/**
	 * ���ܣ�����Ƿ񶳽� Y-��,N-��
	 */
	public String getHoldflag() {
		return holdflag;
	}
	/**
	 * ���ܣ������Ƿ񶳽� Y-��,N-��
	 * @param holdflag
	 */
	public void setHoldflag(String holdflag) {
		this.holdflag = holdflag;
	}
	/**
	 * ���ܣ����ԭ������
	 */
	public String getHoldreason() {
		return holdreason;
	}
	/**
	 * ���ܣ�����ԭ������
	 * @param holdreason
	 */
	public void setHoldreason(String holdreason) {
		this.holdreason = holdreason;
	}
	/**
	 * ���ܣ����ID
	 */
	public String getInventoryholdid() {
		return inventoryholdid;
	}
	/**
	 * ���ܣ�����ID
	 * @param inventoryholdid
	 */
	public void setInventoryholdid(String inventoryholdid) {
		this.inventoryholdid = inventoryholdid;
	}
	/**
	 * ���ܣ���ÿ��ID
	 */
	public String getInventoryid() {
		return inventoryid;
	}
	/**
	 * ���ܣ����ÿ��ID
	 * @param inventoryid
	 */
	public void setInventoryid(String inventoryid) {
		this.inventoryid = inventoryid;
	}
	/**
	 * ���ܣ�������κ�
	 */
	public String getLotid() {
		return lotid;
	}
	/**
	 * ���ܣ��������κ�
	 * @param lotid
	 */
	public void setLotid(String lotid) {
		this.lotid = lotid;
	}
	/**
	 * ���ܣ���û���ID
	 */
	public String getOwnerid() {
		return ownerid;
	}
	/**
	 * ���ܣ����û���ID
	 * @param ownerid
	 */
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
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
	 * ���ܣ���ö�������
	 */
	public double getQtyonhold() {
		return qtyonhold;
	}
	/**
	 * ���ܣ����ö�������
	 * @param qtyonhold
	 */
	public void setQtyonhold(double qtyonhold) {
		this.qtyonhold = qtyonhold;
	}
	/**
	 * ���ܣ���û�λ
	 */
	public String getSpaceid() {
		return spaceid;
	}
	/**
	 * ���ܣ����û�λ
	 * @param spaceid
	 */
	public void setSpaceid(String spaceid) {
		this.spaceid = spaceid;
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
	 * ���ܣ�����ͷŲ�����
	 */
	public String getWhooff() {
		return whooff;
	}
	/**
	 * ���ܣ������ͷŲ�����
	 * @param whooff
	 */
	public void setWhooff(String whooff) {
		this.whooff = whooff;
	}
	/**
	 * ���ܣ���ö��������
	 */
	public String getWhoon() {
		return whoon;
	}
	/**
	 * ���ܣ����ö��������
	 * @param whoon
	 */
	public void setWhoon(String whoon) {
		this.whoon = whoon;
	}

	/**
	 * ���ܣ���ö�������
	 */
	public double getNetweightonhold() {
		return netweightonhold;
	}

	/**
	 * ���ܣ����ö�������
	 * @param netweightonhold
	 */
	public void setNetweightonhold(double netweightonhold) {
		this.netweightonhold = netweightonhold;
	}

	/**
	 * ���ܣ���û�λ����
	 */
	public String getCargoSpaceName() {
		return cargoSpaceName;
	}

	/**
	 * ���ܣ����û�λ����
	 * @param cargoSpaceName
	 */
	public void setCargoSpaceName(String cargoSpaceName) {
		this.cargoSpaceName = cargoSpaceName;
	}

	/**
	 * ���ܣ���ö��᷽������
	 */
	public String getHoldbyName() {
		return holdbyName;
	}

	/**
	 * ���ܣ����ö��᷽������
	 * @param holdbyName
	 */
	public void setHoldbyName(String holdbyName) {
		this.holdbyName = holdbyName;
	}

	/**
	 * ���ܣ���ö���ԭ������
	 */
	public String getHoldcodeName() {
		return holdcodeName;
	}

	/**
	 * ���ܣ����ö���ԭ������
	 * @param holdcodeName
	 */
	public void setHoldcodeName(String holdcodeName) {
		this.holdcodeName = holdcodeName;
	}

	/**
	 * ���ܣ���û�������
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * ���ܣ����û�������
	 * @param ownerName
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * ���ܣ������Ʒ����
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * ���ܣ�������Ʒ����
	 * @param productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * ���ܣ������������
	 */
	public String getLotName() {
		return lotName;
	}

	/**
	 * ���ܣ�������������
	 * @param lotName
	 */
	public void setLotName(String lotName) {
		this.lotName = lotName;
	}

	/**
	 * ���ܣ���ö������������
	 */
	public String getWhoonName() {
		return whoonName;
	}

	/**
	 * ���ܣ����ö������������
	 * @param whoonName
	 */
	public void setWhoonName(String whoonName) {
		this.whoonName = whoonName;
	}


}
