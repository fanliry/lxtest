package com.wms3.bms.standard.entity.base;



/**
 * �������豸��
 * @author hug
 */
public class BaseDevice  implements java.io.Serializable {

     /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7444250448445113370L;

    // Fields   
	
	private String deviceid;		// �豸ID
    private String deviceno;		// �豸���
    private String devicename;		// �豸����
    private String devicetype;		// �豸����
    private String belongto;		// �������
    private String warehouseid;		// �����ֿ�ID
    private String whAreaId;		// ��������ID

    //��������
    private String warehousename;	// �����ֿ���
    private String whAreaName;		// ����������
    private String alleyName;		// ���������
    private String devicetypename;	// �豸������
    
    // Constructors

    /** default constructor */
    public BaseDevice() {
    }

	/** minimal constructor */
    public BaseDevice(String deviceno, String devicename, String devicetype, String warehouseid, String whAreaId) {
        this.deviceno = deviceno;
        this.devicename = devicename;
        this.devicetype = devicetype;
        this.warehouseid = warehouseid;
        this.whAreaId = whAreaId;
    }
    
    /** full constructor */
    public BaseDevice(String deviceno, String devicename, String devicetype, String belongto, String warehouseid, String whAreaId) {
        this.deviceno = deviceno;
        this.devicename = devicename;
        this.devicetype = devicetype;
        this.belongto = belongto;
        this.warehouseid = warehouseid;
        this.whAreaId = whAreaId;
    }

   
    // Property accessors
    /**
     * ���ܣ�����豸ID
     */
    public String getDeviceid() {
        return this.deviceid;
    }
    
    /**
     * ���ܣ������豸ID
     * @param deviceid
     */
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    /**
     * ���ܣ�����豸���
     */
    public String getDeviceno() {
        return this.deviceno;
    }
    
    /**
     * ���ܣ������豸���
     * @param deviceno
     */
    public void setDeviceno(String deviceno) {
        this.deviceno = deviceno;
    }

    /**
     * ���ܣ�����豸����
     */
    public String getDevicename() {
        return this.devicename;
    }
    
    /**
     * ���ܣ������豸����
     * @param devicename
     */
    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    /**
     * ���ܣ�����豸����
     */
    public String getDevicetype() {
        return this.devicetype;
    }
    
    /**
     * ���ܣ������豸����
     * @param devicetype
     */
    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    /**
     * ���ܣ�����������
     */
    public String getBelongto() {
        return this.belongto;
    }
    
    /**
     * ���ܣ������������
     * @param belongto
     */
    public void setBelongto(String belongto) {
        this.belongto = belongto;
    }
    /**
     * ���ܣ���òֿ�ID
     * @return
     */
    public String getWarehouseid() {
        return this.warehouseid;
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
    /**
     * ���ܣ���ÿ�����
     * @return
     */
    public String getWhAreaName() {
        return this.whAreaName;
    }
    /**
     * ���ܣ����ÿ�����
     * @param whAreaName
     */
    public void setWhAreaName(String whAreaName) {
        this.whAreaName = whAreaName;
    }
    
    /**
     * ���ܣ�������������
     * @return
     */
    public String getAlleyName() {
        return this.alleyName;
    }
    /**
     * ���ܣ��������������
     * @param alleyName
     */
    public void setAlleyName(String alleyName) {
        this.alleyName = alleyName;
    }
    
    /**
     * ���ܣ�����豸������
     */
    public String getDevicetypename() {
        return this.devicetypename;
    }
    
    /**
     * ���ܣ������豸������
     * @param devicetypename
     */
    public void setDevicetypename(String devicetypename) {
        this.devicetypename = devicetypename;
    }
}