package com.wms3.bms.standard.entity.base;


/**
 * ������������
 * @author hug
 *
 */
public class BaseCargoarea  implements java.io.Serializable {

	private static final long serialVersionUID = 4222340800660371699L;
	private String cargoAreaId;		//����ID
    private String cargoAreaName;	//������
    private String warehouseid;		//�ֿ�ID
    private String createtime;		//����ʱ��
    private String createmanid;		//������
    private String updatetime;		//����޸�ʱ��
    private String updatemanid;		//����޸���

    private String warehousename;	//�ֿ���
    
    // Constructors

    /** default constructor */
    public BaseCargoarea() {
    }

	/** minimal constructor */
    public BaseCargoarea(String cargoAreaName) {
        this.cargoAreaName = cargoAreaName;
    }
    
    /** full constructor */
    public BaseCargoarea(String warehouseid, String cargoAreaName, String createtime, String createmanid, String updatetime, String updatemanid) {
        this.warehouseid = warehouseid;
    	this.cargoAreaName = cargoAreaName;
        this.createtime = createtime;
        this.createmanid = createmanid;
        this.updatetime = updatetime;
        this.updatemanid = updatemanid;
    }

   
    // Property accessors
    /**
     * ���ܣ���û���ID
     */
    public String getCargoAreaId() {
        return this.cargoAreaId;
    }
    /**
     * ���ܣ����û���ID
     * @param cargoAreaId
     */
    public void setCargoAreaId(String cargoAreaId) {
        this.cargoAreaId = cargoAreaId;
    }
    /**
     * ���ܣ���û�����
     * @return
     */
    public String getCargoAreaName() {
        return this.cargoAreaName;
    }
    /**
     * ���ܣ����û�����
     * @param cargoAreaName
     */
    public void setCargoAreaName(String cargoAreaName) {
        this.cargoAreaName = cargoAreaName;
    }
    /**
     * ���ܣ���ô���ʱ��
     * @return
     */
    public String getCreatetime() {
        return this.createtime;
    }
    /**
     * ���ܣ����ô���ʱ��
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**
     * ���ܣ���ô�����
     * @return
     */
    public String getCreatemanid() {
        return this.createmanid;
    }
    /**
     * ���ܣ����ô�����
     * @param createmanid
     */
    public void setCreatemanid(String createmanid) {
        this.createmanid = createmanid;
    }
    /**
     * ���ܣ��������޸�ʱ��
     * @return
     */
    public String getUpdatetime() {
        return this.updatetime;
    }
    /**
     * ���ܣ���������޸�ʱ��
     * @param updatetime
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    /**
     * ���ܣ��������޸���
     * @return
     */
    public String getUpdatemanid() {
        return this.updatemanid;
    }
    /**
     * ���ܣ���������޸���
     * @param updatemanid
     */
    public void setUpdatemanid(String updatemanid) {
        this.updatemanid = updatemanid;
    }
    /**
     * ���ܣ���òֿ�ID
     * @return
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
     * ���ܣ���òֿ���
     * @return
     */
	public String getWarehousename() {
		return warehousename;
	}
	/**
	 * ���ܣ����òֿ���
	 * @param warehousename
	 */
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
}