package com.wms3.bms.standard.entity.inventory;

/**
 * 
 * ����: ���ת�Ƶ���ϸ
 * @since WMS 3.0
 */
public class InventoryTransferDetail  implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5523017946735375971L;
	
	private String transferdetailid;//���ת�Ƶ���ϸID
	private String transferid;		//���ת�Ƶ����
	private String status;			//״̬ 0:���� 1:���
	private String cargo_space_id;	//��λID
	private String wh_area_id;		//����ID
	private String fmcustomerid;	//FM�ͻ�ID
	private String fmpackid;		//FM��װID
	private String fmpunit;			//FM������λ
	private String fmproductid;		//FM��ƷID
	private String fmlotid;			//FM����ID
	private String fmlotatt1;		//FM��������ֵ1
	private String fmlotatt2;		//FM��������ֵ2
	private String fmlotatt3;		//FM��������ֵ3
	private String fmlotatt4;		//FM��������ֵ4
	private String fmlotatt5;		//FM��������ֵ5
	private String fmlotatt6;		//FM��������ֵ6
	private String fmlotatt7;		//FM��������ֵ7
	private String fmlotatt8;		//FM��������ֵ8
	private String fmlotatt9;		//FM��������ֵ9
	private String fmlotatt10;		//FM��������ֵ10
	private String fmlotatt11;		//FM��������ֵ11
	private String fmlotatt12;		//FM��������ֵ12
	private String tocustomerid;	//TO�ͻ�ID
	private String topackid;		//TO��װID
	private String topunit;			//TO������λ
	private String toproductid;		//TO��ƷID
	private String tolotid;			//TO����ID
	private String tolotatt1;		//TO��������ֵ1
	private String tolotatt2;		//TO��������ֵ2
	private String tolotatt3;		//TO��������ֵ3
	private String tolotatt4;		//TO��������ֵ4
	private String tolotatt5;		//TO��������ֵ5
	private String tolotatt6;		//TO��������ֵ6
	private String tolotatt7;		//TO��������ֵ7
	private String tolotatt8;		//TO��������ֵ8
	private String tolotatt9;		//TO��������ֵ9
	private String tolotatt10;		//TO��������ֵ10
	private String tolotatt11;		//TO��������ֵ11
	private String tolotatt12;		//TO��������ֵ12
	private String totraycode;		//TO��������
	private String fmtraycode;		//FM��������
	private String injobid;			//��ҵID
	private String injobetailid;	//��ҵ��ϸID
	private double pnum;			//�������
	private double pvolume;			//������
	private double pweight;			//�������
	private double pnetweight;		//��澻��
	private String createtime;		//����ʱ��
	private String transfertime;	//ת��ʱ��
	private String createmanid;		//������
	private String inventoryid;		//���ID
	private String warehouseid;		//�ֿ�ID
	
	//��������
	private String fmcustomername;	//FM�ͻ�����
	private String tocustomername;	//TO�ͻ�����
	
	private String fmlotname;	//FM��������
	private String tolotname;	//TO��������
	
	private String fmproductname;	//FM��Ʒ����
	private String toproductname;	//TO��Ʒ����
	
	//�����ֶ�
    private String boxcode;            //������
    private String productcode;        //��Ʒ����

	// Constructors
    /** default constructor */
    public InventoryTransferDetail() {
    }

    
    //Property accessors

	/**
	 * ���ܣ���ò�����
	 */
	public String getCreatemanid() {
		return createmanid;
	}


	/**
	 * ���ܣ����ò�����
	 * @param createmanid
	 */
	public void setCreatemanid(String createmanid) {
		this.createmanid = createmanid;
	}


	/**
	 * ���ܣ����FM�ͻ�ID
	 */
	public String getFmcustomerid() {
		return fmcustomerid;
	}


	/**
	 * ���ܣ�����FM�ͻ�ID
	 * @param fmcustomerid
	 */
	public void setFmcustomerid(String fmcustomerid) {
		this.fmcustomerid = fmcustomerid;
	}


	/**
	 * ���ܣ����FM��������ֵ1
	 */
	public String getFmlotatt1() {
		return fmlotatt1;
	}


	/**
	 * ���ܣ�����FM��������ֵ1
	 * @param fmlotatt1
	 */
	public void setFmlotatt1(String fmlotatt1) {
		this.fmlotatt1 = fmlotatt1;
	}


	/**
	 * ���ܣ����FM��������ֵ10
	 */
	public String getFmlotatt10() {
		return fmlotatt10;
	}


	/**
	 * ���ܣ�����FM��������ֵ10
	 * @param fmlotatt10
	 */
	public void setFmlotatt10(String fmlotatt10) {
		this.fmlotatt10 = fmlotatt10;
	}


	/**
	 * ���ܣ����FM��������ֵ11
	 */
	public String getFmlotatt11() {
		return fmlotatt11;
	}


	/**
	 * ���ܣ�����FM��������ֵ11
	 * @param fmlotatt11
	 */
	public void setFmlotatt11(String fmlotatt11) {
		this.fmlotatt11 = fmlotatt11;
	}


	/**
	 * ���ܣ����FM��������ֵ12
	 */
	public String getFmlotatt12() {
		return fmlotatt12;
	}


	/**
	 * ���ܣ�����FM��������ֵ12
	 * @param fmlotatt12
	 */
	public void setFmlotatt12(String fmlotatt12) {
		this.fmlotatt12 = fmlotatt12;
	}


	/**
	 * ���ܣ����FM��������ֵ2
	 */
	public String getFmlotatt2() {
		return fmlotatt2;
	}


	/**
	 * ���ܣ�����FM��������ֵ2
	 * @param fmlotatt2
	 */
	public void setFmlotatt2(String fmlotatt2) {
		this.fmlotatt2 = fmlotatt2;
	}


	/**
	 * ���ܣ����FM��������ֵ3
	 */
	public String getFmlotatt3() {
		return fmlotatt3;
	}


	/**
	 * ���ܣ�����FM��������ֵ3
	 * @param fmlotatt3
	 */
	public void setFmlotatt3(String fmlotatt3) {
		this.fmlotatt3 = fmlotatt3;
	}


	/**
	 * ���ܣ����FM��������ֵ4
	 */
	public String getFmlotatt4() {
		return fmlotatt4;
	}


	/**
	 * ���ܣ�����FM��������ֵ4
	 * @param fmlotatt4
	 */
	public void setFmlotatt4(String fmlotatt4) {
		this.fmlotatt4 = fmlotatt4;
	}


	/**
	 * ���ܣ����FM��������ֵ5
	 */
	public String getFmlotatt5() {
		return fmlotatt5;
	}


	/**
	 * ���ܣ�����FM��������ֵ5
	 * @param fmlotatt5
	 */
	public void setFmlotatt5(String fmlotatt5) {
		this.fmlotatt5 = fmlotatt5;
	}


	/**
	 * ���ܣ����FM��������ֵ6
	 */
	public String getFmlotatt6() {
		return fmlotatt6;
	}


	/**
	 * ���ܣ�����FM��������ֵ6
	 * @param fmlotatt6
	 */
	public void setFmlotatt6(String fmlotatt6) {
		this.fmlotatt6 = fmlotatt6;
	}


	/**
	 * ���ܣ����FM��������ֵ7
	 */
	public String getFmlotatt7() {
		return fmlotatt7;
	}


	/**
	 * ���ܣ�����FM��������ֵ7
	 * @param fmlotatt7
	 */
	public void setFmlotatt7(String fmlotatt7) {
		this.fmlotatt7 = fmlotatt7;
	}


	/**
	 * ���ܣ����FM��������ֵ8
	 */
	public String getFmlotatt8() {
		return fmlotatt8;
	}


	/**
	 * ���ܣ�����FM��������ֵ8
	 * @param fmlotatt8
	 */
	public void setFmlotatt8(String fmlotatt8) {
		this.fmlotatt8 = fmlotatt8;
	}


	/**
	 * ���ܣ����FM��������ֵ9
	 */
	public String getFmlotatt9() {
		return fmlotatt9;
	}


	/**
	 * ���ܣ�����FM��������ֵ9
	 * @param fmlotatt9
	 */
	public void setFmlotatt9(String fmlotatt9) {
		this.fmlotatt9 = fmlotatt9;
	}


	/**
	 * ���ܣ����FM����ID
	 */
	public String getFmlotid() {
		return fmlotid;
	}


	/**
	 * ���ܣ�����FM����ID
	 * @param fmlotid
	 */
	public void setFmlotid(String fmlotid) {
		this.fmlotid = fmlotid;
	}


	/**
	 * ���ܣ����FM��װID
	 */
	public String getFmpackid() {
		return fmpackid;
	}


	/**
	 * ���ܣ�����FM��װID
	 * @param fmpackid
	 */
	public void setFmpackid(String fmpackid) {
		this.fmpackid = fmpackid;
	}


	/**
	 * ���ܣ����FM��ƷID
	 */
	public String getFmproductid() {
		return fmproductid;
	}


	/**
	 * ���ܣ�����FM��ƷID
	 * @param fmproductid
	 */
	public void setFmproductid(String fmproductid) {
		this.fmproductid = fmproductid;
	}


	/**
	 * ���ܣ����FM������λ
	 */
	public String getFmpunit() {
		return fmpunit;
	}


	/**
	 * ���ܣ�����FM������λ
	 * @param fmpunit
	 */
	public void setFmpunit(String fmpunit) {
		this.fmpunit = fmpunit;
	}


	/**
	 * ���ܣ����FM��������
	 */
	public String getFmtraycode() {
		return fmtraycode;
	}


	/**
	 * ���ܣ�����FM��������
	 * @param fmtraycode
	 */
	public void setFmtraycode(String fmtraycode) {
		this.fmtraycode = fmtraycode;
	}


	/**
	 * ���ܣ������ҵ��ϸID
	 */
	public String getInjobetailid() {
		return injobetailid;
	}


	/**
	 * ���ܣ�������ҵ��ϸID
	 * @param injobetailid
	 */
	public void setInjobetailid(String injobetailid) {
		this.injobetailid = injobetailid;
	}


	/**
	 * ���ܣ������ҵID
	 */
	public String getInjobid() {
		return injobid;
	}


	/**
	 * ���ܣ�������ҵID
	 * @param injobid
	 */
	public void setInjobid(String injobid) {
		this.injobid = injobid;
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
	 * ���ܣ����TO�ͻ�ID
	 */
	public String getTocustomerid() {
		return tocustomerid;
	}


	/**
	 * ���ܣ�����TO�ͻ�ID
	 * @param tocustomerid
	 */
	public void setTocustomerid(String tocustomerid) {
		this.tocustomerid = tocustomerid;
	}


	/**
	 * ���ܣ����TO��������ֵ1
	 */
	public String getTolotatt1() {
		return tolotatt1;
	}


	/**
	 * ���ܣ�����TO��������ֵ1
	 * @param tolotatt1
	 */
	public void setTolotatt1(String tolotatt1) {
		this.tolotatt1 = tolotatt1;
	}


	/**
	 * ���ܣ����TO��������ֵ10
	 */
	public String getTolotatt10() {
		return tolotatt10;
	}


	/**
	 * ���ܣ�����TO��������ֵ10
	 * @param tolotatt10
	 */
	public void setTolotatt10(String tolotatt10) {
		this.tolotatt10 = tolotatt10;
	}


	/**
	 * ���ܣ����TO��������ֵ11
	 */
	public String getTolotatt11() {
		return tolotatt11;
	}


	/**
	 * ���ܣ�����TO��������ֵ11
	 * @param tolotatt11
	 */
	public void setTolotatt11(String tolotatt11) {
		this.tolotatt11 = tolotatt11;
	}


	/**
	 * ���ܣ����TO��������ֵ12
	 */
	public String getTolotatt12() {
		return tolotatt12;
	}


	/**
	 * ���ܣ�����TO��������ֵ12
	 * @param tolotatt12
	 */
	public void setTolotatt12(String tolotatt12) {
		this.tolotatt12 = tolotatt12;
	}


	/**
	 * ���ܣ����TO��������ֵ2
	 */
	public String getTolotatt2() {
		return tolotatt2;
	}


	/**
	 * ���ܣ�����TO��������ֵ2
	 * @param tolotatt2
	 */
	public void setTolotatt2(String tolotatt2) {
		this.tolotatt2 = tolotatt2;
	}


	/**
	 * ���ܣ����TO��������ֵ3
	 */
	public String getTolotatt3() {
		return tolotatt3;
	}


	/**
	 * ���ܣ�����TO��������ֵ3
	 * @param tolotatt3
	 */
	public void setTolotatt3(String tolotatt3) {
		this.tolotatt3 = tolotatt3;
	}


	/**
	 * ���ܣ����TO��������ֵ4
	 */
	public String getTolotatt4() {
		return tolotatt4;
	}


	/**
	 * ���ܣ�����TO��������ֵ4
	 * @param tolotatt4
	 */
	public void setTolotatt4(String tolotatt4) {
		this.tolotatt4 = tolotatt4;
	}


	/**
	 * ���ܣ����TO��������ֵ5
	 */
	public String getTolotatt5() {
		return tolotatt5;
	}


	/**
	 * ���ܣ�����TO��������ֵ5
	 * @param tolotatt5
	 */
	public void setTolotatt5(String tolotatt5) {
		this.tolotatt5 = tolotatt5;
	}


	/**
	 * ���ܣ����TO��������ֵ6
	 */
	public String getTolotatt6() {
		return tolotatt6;
	}


	/**
	 * ���ܣ�����TO��������ֵ6
	 * @param tolotatt6
	 */
	public void setTolotatt6(String tolotatt6) {
		this.tolotatt6 = tolotatt6;
	}


	/**
	 * ���ܣ����TO��������ֵ7
	 */
	public String getTolotatt7() {
		return tolotatt7;
	}


	/**
	 * ���ܣ�����TO��������ֵ7
	 * @param tolotatt7
	 */
	public void setTolotatt7(String tolotatt7) {
		this.tolotatt7 = tolotatt7;
	}


	/**
	 * ���ܣ����TO��������ֵ8
	 */
	public String getTolotatt8() {
		return tolotatt8;
	}


	/**
	 * ���ܣ�����TO��������ֵ8
	 * @param tolotatt8
	 */
	public void setTolotatt8(String tolotatt8) {
		this.tolotatt8 = tolotatt8;
	}


	/**
	 * ���ܣ����TO��������ֵ9
	 */
	public String getTolotatt9() {
		return tolotatt9;
	}


	/**
	 * ���ܣ�����TO��������ֵ9
	 * @param tolotatt9
	 */
	public void setTolotatt9(String tolotatt9) {
		this.tolotatt9 = tolotatt9;
	}


	/**
	 * ���ܣ����TO����ID
	 */
	public String getTolotid() {
		return tolotid;
	}


	/**
	 * ���ܣ�����TO����ID
	 * @param tolotid
	 */
	public void setTolotid(String tolotid) {
		this.tolotid = tolotid;
	}


	/**
	 * ���ܣ����TO��װID
	 */
	public String getTopackid() {
		return topackid;
	}


	/**
	 * ���ܣ�����TO��װID
	 * @param topackid
	 */
	public void setTopackid(String topackid) {
		this.topackid = topackid;
	}


	/**
	 * ���ܣ����TO��ƷID
	 */
	public String getToproductid() {
		return toproductid;
	}


	/**
	 * ���ܣ�����TO��ƷID
	 * @param toproductid
	 */
	public void setToproductid(String toproductid) {
		this.toproductid = toproductid;
	}


	/**
	 * ���ܣ����TO������λ
	 */
	public String getTopunit() {
		return topunit;
	}


	/**
	 * ���ܣ�����TO������λ
	 * @param topunit
	 */
	public void setTopunit(String topunit) {
		this.topunit = topunit;
	}


	/**
	 * ���ܣ����TO��������
	 */
	public String getTotraycode() {
		return totraycode;
	}


	/**
	 * ���ܣ�����TO��������
	 * @param totraycode
	 */
	public void setTotraycode(String totraycode) {
		this.totraycode = totraycode;
	}


	/**
	 * ���ܣ���òֿ�ID
	 */
	public String getWarehouseid() {
		return warehouseid;
	}


	/**
	 * ���ܣ����òֿ�ID
	 * @param warehouseid
	 */
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}


	/**
	 * ���ܣ���û�λID
	 */
	public String getCargo_space_id() {
		return cargo_space_id;
	}


	/**
	 * ���ܣ����û�λID
	 * @param cargo_space_id
	 */
	public void setCargo_space_id(String cargo_space_id) {
		this.cargo_space_id = cargo_space_id;
	}


	/**
	 * ���ܣ���ô���ʱ��
	 */
	public String getCreatetime() {
		return createtime;
	}


	/**
	 * ���ܣ����ô���ʱ��
	 * @param createtime
	 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	/**
	 * ���ܣ���ÿ�澻��
	 */
	public double getPnetweight() {
		return pnetweight;
	}


	/**
	 * ���ܣ����ÿ�澻��
	 * @param pnetweight
	 */
	public void setPnetweight(double pnetweight) {
		this.pnetweight = pnetweight;
	}


	/**
	 * ���ܣ���ÿ������
	 */
	public double getPnum() {
		return pnum;
	}


	/**
	 * ���ܣ����ÿ������
	 * @param pnum
	 */
	public void setPnum(double pnum) {
		this.pnum = pnum;
	}


	/**
	 * ���ܣ���ÿ�����
	 */
	public double getPvolume() {
		return pvolume;
	}


	/**
	 * ���ܣ����ÿ�����
	 * @param pvolume
	 */
	public void setPvolume(double pvolume) {
		this.pvolume = pvolume;
	}


	/**
	 * ���ܣ���ÿ������
	 */
	public double getPweight() {
		return pweight;
	}


	/**
	 * ���ܣ����ÿ������
	 * @param pweight
	 */
	public void setPweight(double pweight) {
		this.pweight = pweight;
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
	 * ���ܣ���ÿ��ת�Ƶ���ϸID
	 */
	public String getTransferdetailid() {
		return transferdetailid;
	}


	/**
	 * ���ܣ����ÿ��ת�Ƶ���ϸID
	 * @param transferdetailid
	 */
	public void setTransferdetailid(String transferdetailid) {
		this.transferdetailid = transferdetailid;
	}


	/**
	 * ���ܣ���ÿ��ת�Ƶ����
	 */
	public String getTransferid() {
		return transferid;
	}


	/**
	 * ���ܣ����ÿ��ת�Ƶ����
	 * @param transferid
	 */
	public void setTransferid(String transferid) {
		this.transferid = transferid;
	}


	/**
	 * ���ܣ����ת��ʱ��
	 */
	public String getTransfertime() {
		return transfertime;
	}


	/**
	 * ���ܣ�����ת��ʱ��
	 * @param transfertime
	 */
	public void setTransfertime(String transfertime) {
		this.transfertime = transfertime;
	}


	/**
	 * ���ܣ���ÿ���ID
	 */
	public String getWh_area_id() {
		return wh_area_id;
	}


	/**
	 * ���ܣ����ÿ���ID
	 * @param wh_area_id
	 */
	public void setWh_area_id(String wh_area_id) {
		this.wh_area_id = wh_area_id;
	}


	public String getFmcustomername() {
		return fmcustomername;
	}


	public void setFmcustomername(String fmcustomername) {
		this.fmcustomername = fmcustomername;
	}


	public String getTocustomername() {
		return tocustomername;
	}


	public void setTocustomername(String tocustomername) {
		this.tocustomername = tocustomername;
	}


	public String getFmlotname() {
		return fmlotname;
	}


	public void setFmlotname(String fmlotname) {
		this.fmlotname = fmlotname;
	}


	public String getFmproductname() {
		return fmproductname;
	}


	public void setFmproductname(String fmproductname) {
		this.fmproductname = fmproductname;
	}


	public String getTolotname() {
		return tolotname;
	}


	public void setTolotname(String tolotname) {
		this.tolotname = tolotname;
	}


	public String getToproductname() {
		return toproductname;
	}


	public void setToproductname(String toproductname) {
		this.toproductname = toproductname;
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