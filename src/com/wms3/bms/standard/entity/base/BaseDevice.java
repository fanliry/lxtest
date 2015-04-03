package com.wms3.bms.standard.entity.base;



/**
 * 描述：设备表
 * @author hug
 */
public class BaseDevice  implements java.io.Serializable {

     /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7444250448445113370L;

    // Fields   
	
	private String deviceid;		// 设备ID
    private String deviceno;		// 设备编号
    private String devicename;		// 设备名称
    private String devicetype;		// 设备类型
    private String belongto;		// 所属巷道
    private String warehouseid;		// 所属仓库ID
    private String whAreaId;		// 所属库区ID

    //派生属性
    private String warehousename;	// 所属仓库名
    private String whAreaName;		// 所属库区名
    private String alleyName;		// 所属巷道名
    private String devicetypename;	// 设备类型名
    
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
     * 功能：获得设备ID
     */
    public String getDeviceid() {
        return this.deviceid;
    }
    
    /**
     * 功能：设置设备ID
     * @param deviceid
     */
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    /**
     * 功能：获得设备编号
     */
    public String getDeviceno() {
        return this.deviceno;
    }
    
    /**
     * 功能：设置设备编号
     * @param deviceno
     */
    public void setDeviceno(String deviceno) {
        this.deviceno = deviceno;
    }

    /**
     * 功能：获得设备名称
     */
    public String getDevicename() {
        return this.devicename;
    }
    
    /**
     * 功能：设置设备名称
     * @param devicename
     */
    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    /**
     * 功能：获得设备类型
     */
    public String getDevicetype() {
        return this.devicetype;
    }
    
    /**
     * 功能：设置设备类型
     * @param devicetype
     */
    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    /**
     * 功能：获得所属巷道
     */
    public String getBelongto() {
        return this.belongto;
    }
    
    /**
     * 功能：设置所属巷道
     * @param belongto
     */
    public void setBelongto(String belongto) {
        this.belongto = belongto;
    }
    /**
     * 功能：获得仓库ID
     * @return
     */
    public String getWarehouseid() {
        return this.warehouseid;
    }
    /**
     * 功能：设置仓库ID
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }
    /**
     * 功能：获得库区ID
     * @return
     */
    public String getWhAreaId() {
        return this.whAreaId;
    }
    /**
     * 功能：设置库区ID
     * @param whAreaId
     */
    public void setWhAreaId(String whAreaId) {
        this.whAreaId = whAreaId;
    }
    /**
     * 功能：获得仓库名
     * @return
     */
	public String getWarehousename() {
		return warehousename;
	}
	/**
	 * 功能：设置仓库名
	 * @param warehousename
	 */
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}   
    /**
     * 功能：获得库区名
     * @return
     */
    public String getWhAreaName() {
        return this.whAreaName;
    }
    /**
     * 功能：设置库区名
     * @param whAreaName
     */
    public void setWhAreaName(String whAreaName) {
        this.whAreaName = whAreaName;
    }
    
    /**
     * 功能：获得所属巷道名
     * @return
     */
    public String getAlleyName() {
        return this.alleyName;
    }
    /**
     * 功能：设置所属巷道名
     * @param alleyName
     */
    public void setAlleyName(String alleyName) {
        this.alleyName = alleyName;
    }
    
    /**
     * 功能：获得设备类型名
     */
    public String getDevicetypename() {
        return this.devicetypename;
    }
    
    /**
     * 功能：设置设备类型名
     * @param devicetypename
     */
    public void setDevicetypename(String devicetypename) {
        this.devicetypename = devicetypename;
    }
}