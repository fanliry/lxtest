package com.wms3.bms.standard.entity.inventory.lxyy;

/**
 * �������̵�����
 *
 */
public class InventoryCheckTask  implements java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7881611685702005475L;
	
	private String taskid;			//ID
	private String requestid;		//�̵������ID
	private String status;			//״̬ 	1:�½���2�������̵����� 3:�����̵� 4�������̵��� 5�����
	private String cargo_space_id;	//��λID
	private String wh_area_id;		//����ID
	private String lotinfo;		    //����
	private String productid;		//��ƷID
	private String traycode;		//��������
	private String lotid;			//����ID
	public String lotatt1;			//��������ֵ1
	public String lotatt2;			//��������ֵ2
	public String lotatt3;			//��������ֵ3
	public String lotatt4;			//��������ֵ4
	public String lotatt5;			//��������ֵ5
	public String lotatt6;			//��������ֵ6
	public String lotatt7;			//��������ֵ7
	public String lotatt8;			//��������ֵ8
	public String lotatt9;			//��������ֵ9
	public String lotatt10;			//��������ֵ10
	public String lotatt11;			//��������ֵ11
	public String lotatt12;			//��������ֵ12
	private String createtime;		//����ʱ��
	private double num;				//�������
	private double netweight;		//�������
	private String inventoryid;		//���ID
	private String warehouseid;		//�ֿ�ID

	//	�����ֶ�
    private String boxcode;            //������
    private String productcode;        //��Ʒ����
    
	//��������
    private String whAreaName;			//��������
	private String cargoSpaceName;		//��λ����
	private String productName;			//��Ʒ����
	private String ownerName;			//��������
	private String statusName;			//״̬����
	
	private String punit;			    //��λ
	private String printdate;           //��������
	
	public String getPunit() {
		return punit;
	}


	public void setPunit(String punit) {
		this.punit = punit;
	}


	public String getPrintdate() {
		return printdate;
	}


	public void setPrintdate(String printdate) {
		this.printdate = printdate;
	}

	/** default constructor */
    public InventoryCheckTask() {
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
	 * ���ܣ���ÿ��ID
	 */
	public String getInventoryid() {
		return inventoryid;
	}

	/**
	 * ���ܣ��������
	 */
	public String getLotinfo() {
		return lotinfo;
	}

	/**
	 * ���ܣ���������
	 */
	public void setLotinfo(String lotinfo) {
		this.lotinfo = lotinfo;
	}


	/**
	 * ���ܣ����ÿ��ID
	 * @param inventoryid
	 */
	public void setInventoryid(String inventoryid) {
		this.inventoryid = inventoryid;
	}


	/**
	 * ���ܣ���ÿ������
	 */
	public double getNum() {
		return num;
	}


	/**
	 * ���ܣ����ÿ������
	 * @param num
	 */
	public void setNum(double num) {
		this.num = num;
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
	 * ���ܣ�����̵������ID
	 */
	public String getRequestid() {
		return requestid;
	}


	/**
	 * ���ܣ������̵������ID
	 * @param requestid
	 */
	public void setRequestid(String requestid) {
		this.requestid = requestid;
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
	 * ���ܣ����ID
	 */
	public String getTaskid() {
		return taskid;
	}


	/**
	 * ���ܣ�����ID
	 * @param taskid
	 */
	public void setTaskid(String taskid) {
		this.taskid = taskid;
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
	 * ���ܣ���ÿ������
	 */
	public double getNetweight() {
		return netweight;
	}


	/**
	 * ���ܣ����ÿ������
	 * @param netweight
	 */
	public void setNetweight(double netweight) {
		this.netweight = netweight;
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
	 * ���ܣ����״̬����
	 */
	public String getStatusName() {
		return statusName;
	}


	/**
	 * ���ܣ�����״̬����
	 * @param statusName
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}


	/**
	 * ���ܣ���ÿ�������
	 */
	public String getWhAreaName() {
		return whAreaName;
	}


	/**
	 * ���ܣ����ÿ�������
	 * @param whAreaName
	 */
	public void setWhAreaName(String whAreaName) {
		this.whAreaName = whAreaName;
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

}