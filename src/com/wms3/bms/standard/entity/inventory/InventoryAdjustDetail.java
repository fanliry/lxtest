package com.wms3.bms.standard.entity.inventory;

/**
 * 
 * ����: ����������ϸ
 * @since WMS 3.0
 */
public class InventoryAdjustDetail  implements java.io.Serializable {
    
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6038598254721034915L;
	
	private String adjustdetailid;	//��������ϸID
	private String adjustid;		//������ID
	private String status;			//״̬
	private String lotinfo;		   //����
	private String cargo_space_id;	//��λID
	private String wh_area_id;		//����ID
	private String packid;			//��װID
	private String punit;			//������λ
	private String productid;		//��ƷID
	private String lotid;			//����ID
	private String lotatt1;			//��������ֵ1
	private String lotatt2;			//��������ֵ2
	private String lotatt3;			//��������ֵ3
	private String lotatt4;			//��������ֵ4
	private String lotatt5;			//��������ֵ5
	private String lotatt6;			//��������ֵ6
	private String lotatt7;			//��������ֵ7
	private String lotatt8;			//��������ֵ8
	private String lotatt9;			//��������ֵ9
	private String lotatt10;		//��������ֵ10
	private String lotatt11;		//��������ֵ11
	private String lotatt12;		//��������ֵ12
	private String traycode;		//��������
	private String injobid;			//��ҵID
	private String injobetailid;	//��ҵ��ϸID
	private double pnum;			//�������
	private String producttime;		//��������
	private double pweight;			//�������
	private double pnetweight;		//��澻��
	private double tovolume;		//Ŀ�������
	private double tonum;			//Ŀ��������
	private double toweight;		//Ŀ��������
	private double tonetweight;		//Ŀ���澻��
	private String createtime;		//����ʱ��
	private String adjusttime;		//����ʱ��
	private String createmanid;		//������
	private String inventoryid;		//���ID
	private String warehouseid;		//�ֿ�ID
	
	//��������
	private String customername;	//�ͻ�����
	private String productname;	//�ͻ�����
	
	//�����ֶ�
    private String boxcode;            //������
    private String productcode;        //��Ʒ����

	// Constructors
    /** default constructor */
    public InventoryAdjustDetail() {
    }

    
    // Property accessors
	/**
	 * ���ܣ���õ�������ϸID
	 */
	public String getAdjustdetailid() {
		return adjustdetailid;
	}

	/**
	 * ���ܣ����õ�������ϸID
	 * @param adjustdetailid
	 */
	public void setAdjustdetailid(String adjustdetailid) {
		this.adjustdetailid = adjustdetailid;
	}

	/**
	 * ���ܣ���õ�����ID
	 */
	public String getAdjustid() {
		return adjustid;
	}

	/**
	 * ���ܣ����õ�����ID
	 * @param adjustid
	 */
	public void setAdjustid(String adjustid) {
		this.adjustid = adjustid;
	}

	/**
	 * ���ܣ���õ���ʱ��
	 */
	public String getAdjusttime() {
		return adjusttime;
	}

	/**
	 * ���ܣ����õ���ʱ��
	 * @param adjusttime
	 */
	public void setAdjusttime(String adjusttime) {
		this.adjusttime = adjusttime;
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
	 * ���ܣ���ô�����
	 */
	public String getCreatemanid() {
		return createmanid;
	}

	/**
	 * ���ܣ����ô�����
	 * @param createmanid
	 */
	public void setCreatemanid(String createmanid) {
		this.createmanid = createmanid;
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
	 * ���ܣ���ȡ����
	 * @return
	 */
	public String getLotinfo() {
		return lotinfo;
	}
	/**
	 * ���ܣ���������
	 * @return
	 */
	public void setLotinfo(String lotinfo) {
		this.lotinfo = lotinfo;
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
	 * ���ܣ������������ֵ1
	 */
	public String getLotatt1() {
		return lotatt1;
	}

	/**
	 * ���ܣ�������������ֵ1
	 * @param lotatt1
	 */
	public void setLotatt1(String lotatt1) {
		this.lotatt1 = lotatt1;
	}

	/**
	 * ���ܣ������������ֵ10
	 */
	public String getLotatt10() {
		return lotatt10;
	}

	/**
	 * ���ܣ�������������ֵ10
	 * @param lotatt10
	 */
	public void setLotatt10(String lotatt10) {
		this.lotatt10 = lotatt10;
	}

	/**
	 * ���ܣ������������ֵ11
	 */
	public String getLotatt11() {
		return lotatt11;
	}

	/**
	 * ���ܣ�������������ֵ11
	 * @param lotatt11
	 */
	public void setLotatt11(String lotatt11) {
		this.lotatt11 = lotatt11;
	}

	/**
	 * ���ܣ������������ֵ12
	 */
	public String getLotatt12() {
		return lotatt12;
	}

	/**
	 * ���ܣ�������������ֵ12
	 * @param lotatt12
	 */
	public void setLotatt12(String lotatt12) {
		this.lotatt12 = lotatt12;
	}

	/**
	 * ���ܣ������������ֵ2
	 */
	public String getLotatt2() {
		return lotatt2;
	}

	/**
	 * ���ܣ�������������ֵ2
	 * @param lotatt2
	 */
	public void setLotatt2(String lotatt2) {
		this.lotatt2 = lotatt2;
	}

	/**
	 * ���ܣ������������ֵ3
	 */
	public String getLotatt3() {
		return lotatt3;
	}

	/**
	 * ���ܣ�������������ֵ3
	 * @param lotatt3
	 */
	public void setLotatt3(String lotatt3) {
		this.lotatt3 = lotatt3;
	}

	/**
	 * ���ܣ������������ֵ4
	 */
	public String getLotatt4() {
		return lotatt4;
	}

	/**
	 * ���ܣ�������������ֵ4
	 * @param lotatt4
	 */
	public void setLotatt4(String lotatt4) {
		this.lotatt4 = lotatt4;
	}

	/**
	 * ���ܣ������������ֵ5
	 */
	public String getLotatt5() {
		return lotatt5;
	}

	/**
	 * ���ܣ�������������ֵ5
	 * @param lotatt5
	 */
	public void setLotatt5(String lotatt5) {
		this.lotatt5 = lotatt5;
	}

	/**
	 * ���ܣ������������ֵ6
	 */
	public String getLotatt6() {
		return lotatt6;
	}

	/**
	 * ���ܣ�������������ֵ6
	 * @param lotatt6
	 */
	public void setLotatt6(String lotatt6) {
		this.lotatt6 = lotatt6;
	}

	/**
	 * ���ܣ������������ֵ7
	 */
	public String getLotatt7() {
		return lotatt7;
	}

	/**
	 * ���ܣ�������������ֵ7
	 * @param lotatt7
	 */
	public void setLotatt7(String lotatt7) {
		this.lotatt7 = lotatt7;
	}

	/**
	 * ���ܣ������������ֵ8
	 */
	public String getLotatt8() {
		return lotatt8;
	}

	/**
	 * ���ܣ�������������ֵ8
	 * @param lotatt8
	 */
	public void setLotatt8(String lotatt8) {
		this.lotatt8 = lotatt8;
	}

	/**
	 * ���ܣ������������ֵ9
	 */
	public String getLotatt9() {
		return lotatt9;
	}

	/**
	 * ���ܣ�������������ֵ9
	 * @param lotatt9
	 */
	public void setLotatt9(String lotatt9) {
		this.lotatt9 = lotatt9;
	}

	/**
	 * ���ܣ��������ID
	 */
	public String getLotid() {
		return lotid;
	}

	/**
	 * ���ܣ���������ID
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
	 * ���ܣ���ü�����λ
	 */
	public String getPunit() {
		return punit;
	}

	/**
	 * ���ܣ����ü�����λ
	 * @param punit
	 */
	public void setPunit(String punit) {
		this.punit = punit;
	}

   //
	public String getProducttime() {
		return producttime;
	}


	public void setProducttime(String producttime) {
		this.producttime = producttime;
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
	 * ���ܣ����Ŀ���澻��
	 */
	public double getTonetweight() {
		return tonetweight;
	}

	/**
	 * ���ܣ�����Ŀ���澻��
	 * @param tonetweight
	 */
	public void setTonetweight(double tonetweight) {
		this.tonetweight = tonetweight;
	}

	/**
	 * ���ܣ����Ŀ��������
	 */
	public double getTonum() {
		return tonum;
	}

	/**
	 * ���ܣ�����Ŀ��������
	 * @param tonum
	 */
	public void setTonum(double tonum) {
		this.tonum = tonum;
	}

	/**
	 * ���ܣ����Ŀ�������
	 */
	public double getTovolume() {
		return tovolume;
	}

	/**
	 * ���ܣ�����Ŀ�������
	 * @param tovolume
	 */
	public void setTovolume(double tovolume) {
		this.tovolume = tovolume;
	}

	/**
	 * ���ܣ����Ŀ��������
	 */
	public double getToweight() {
		return toweight;
	}

	/**
	 * ���ܣ�����Ŀ��������
	 * @param toweight
	 */
	public void setToweight(double toweight) {
		this.toweight = toweight;
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

	/**
	 * ���ܣ���ÿͻ�����
	 */
	public String getCustomername() {
		return customername;
	}


	/**
	 * ���ܣ����ÿͻ�����
	 * @param customername
	 */
	public void setCustomername(String customername) {
		this.customername = customername;
	}


	public String getProductname() {
		return productname;
	}


	public void setProductname(String productname) {
		this.productname = productname;
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