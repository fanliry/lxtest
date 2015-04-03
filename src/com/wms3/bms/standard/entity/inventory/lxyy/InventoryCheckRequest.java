package com.wms3.bms.standard.entity.inventory.lxyy;

/**
 * 
 * ����: �̵�����
 * @since WMS 3.0
 */
public class InventoryCheckRequest  implements java.io.Serializable {

    
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4378900787650441282L;
	
	private String requestid;		//ID
	private String counttype;		//����
	private String status;			//״̬	1:�½���2�������̵�����3:�����̵㣻4�����
	private int priority;			//���ȼ�	1:�ߣ�2���ϸߣ�3:�У�4���ϵͣ�5����
	private String cargo_space_id;	//��λID
	private String wh_area_id;		//����ID
	private String lotinfo;		//����
	private String productid;		//��ƷID
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
	private String traycode;		//��������
	private String requesttime;		//����ʱ��
	private String starttime;		//��ʼʱ��
	private String endtime;			//����ʱ��
	private String createmanid;		//������
	private String warehouseid;		//�ֿ�ID

	//�����ֶ�
    private String boxcode;            //������
    private String productcode;        //��Ʒ����
	
	//��������
    private String whAreaName;			//��������
	private String cargoSpaceName;		//��λ����
	private String productName;			//��Ʒ����
	private String typeName;			//��������
	private String statusName;			//״̬����
	private String createman;			//������
	private String lotName;				//��������

	// Constructors
    /** default constructor */
    public InventoryCheckRequest() {
    }


    
    // Property accessors
    
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
	 * ���ܣ��������
	 */
	public String getCounttype() {
		return counttype;
	}



	/**
	 * ���ܣ���������
	 * @param counttype
	 */
	public void setCounttype(String counttype) {
		this.counttype = counttype;
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
	 * ���ܣ���ý���ʱ��
	 */
	public String getEndtime() {
		return endtime;
	}



	/**
	 * ���ܣ����ý���ʱ��
	 * @param endtime
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
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
	 * ���ܣ�������ȼ�
	 */
	public int getPriority() {
		return priority;
	}



	/**
	 * ���ܣ��������ȼ�
	 * @param priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
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
	 * ���ܣ����ID
	 */
	public String getRequestid() {
		return requestid;
	}



	/**
	 * ���ܣ�����ID
	 * @param requestid
	 */
	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}



	/**
	 * ���ܣ��������ʱ��
	 */
	public String getRequesttime() {
		return requesttime;
	}



	/**
	 * ���ܣ���������ʱ��
	 * @param requesttime
	 */
	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}



	/**
	 * ���ܣ���ÿ�ʼʱ��
	 */
	public String getStarttime() {
		return starttime;
	}



	/**
	 * ���ܣ����ÿ�ʼʱ��
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
	 * ���ܣ���ô�����
	 */
	public String getCreateman() {
		return createman;
	}



	/**
	 * ���ܣ����ô�����
	 * @param createman
	 */
	public void setCreateman(String createman) {
		this.createman = createman;
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
	 * ���ܣ������������
	 */
	public String getTypeName() {
		return typeName;
	}



	/**
	 * ���ܣ�������������
	 * @param typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	 * ���ܣ���������
	 *@author liuxh 2013-3-11
	 *@param lotnumber
	 */
	public void setLotinfo(String lotnumber) {
		this.lotinfo = lotnumber;
	}


	/**
	 * ���ܣ���ȡ����
	 *@author liuxh 2013-3-11
	 *@param lotnumber
	 */
	public String getLotinfo() {
		return lotinfo;
	}
    
    
}