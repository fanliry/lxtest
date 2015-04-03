package com.wms3.bms.standard.entity.base;


/**
 * 描述：巷道
 * @author hug
 *
 */
public class BaseAlley implements java.io.Serializable {

	private static final long serialVersionUID = 4863429125914809083L;
	
	 private String cargoAlleyId;	//巷道ID
     private String cargoAlleyName;	//巷道名
     private String warehouseid;	//仓库ID
     private String whAreaId;		//库区ID
     private String inlock;			//入库锁 Y.是 N.否
     private String outlock;		//出库锁 Y.是 N.否
     private String istwin;			//是否双升货位巷道 Y.是 N.否
     private String createtime;		//创建时间
     private String createmanid;	//创建人
     private String updatetime;		//最后修改时间
     private String updatemanid;	//最后修改人

     private String warehousename;	//仓库名
     private String whAreaName;		//库区名
     
    // Constructors

    /** default constructor */
    public BaseAlley() {
    }

	/** minimal constructor */
    public BaseAlley(String warehouseid, String inlock, String outlock) {
        this.warehouseid = warehouseid;
        this.inlock = inlock;
        this.outlock = outlock;
    }
    
    /** full constructor */
    public BaseAlley(String cargoAlleyName, String warehouseid, String whAreaId, String inlock, String outlock, String istwin, 
    		String createtime, String createmanid, String updatetime, String updatemanid) {
        this.cargoAlleyName = cargoAlleyName;
        this.warehouseid = warehouseid;
        this.whAreaId = whAreaId;
        this.inlock = inlock;
        this.outlock = outlock;
        this.istwin = istwin;
        this.createtime = createtime;
        this.createmanid = createmanid;
        this.updatetime = updatetime;
        this.updatemanid = updatemanid;
    }

   
    // Property accessors
    /**
     * 功能：获得巷道ID
     */
    public String getCargoAlleyId() {
        return this.cargoAlleyId;
    }
    /**
     * 功能：设置巷道ID
     * @param cargoAlleyId
     */
    public void setCargoAlleyId(String cargoAlleyId) {
        this.cargoAlleyId = cargoAlleyId;
    }
    /**
     * 功能：获得巷道名
     * @return
     */
    public String getCargoAlleyName() {
        return this.cargoAlleyName;
    }
    /**
     * 功能：设置巷道名
     * @param cargoAlleyName
     */
    public void setCargoAlleyName(String cargoAlleyName) {
        this.cargoAlleyName = cargoAlleyName;
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
     * 功能：获得入库锁 Y.是 N.否
     * @return
     */
    public String getInlock() {
        return this.inlock;
    }
    /**
     * 功能：设置入库锁 Y.是 N.否
     * @param inlock
     */
    public void setInlock(String inlock) {
        this.inlock = inlock;
    }
    /**
     * 功能：获得出库锁 Y.是 N.否
     * @return
     */
    public String getOutlock() {
        return this.outlock;
    }
    /**
     * 功能：设置出库锁 Y.是 N.否
     * @param outlock
     */
    public void setOutlock(String outlock) {
        this.outlock = outlock;
    }
    /**
     * 功能：获得创建时间
     * @return
     */
    public String getCreatetime() {
        return this.createtime;
    }
    /**
     * 功能：设置创建时间
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**
     * 功能：获得创建人
     * @return
     */
    public String getCreatemanid() {
        return this.createmanid;
    }
    /**
     * 功能：设置创建人
     * @param createmanid
     */
    public void setCreatemanid(String createmanid) {
        this.createmanid = createmanid;
    }
    /**
     * 功能：获得最后修改时间
     * @return
     */
    public String getUpdatetime() {
        return this.updatetime;
    }
    /**
     * 功能：设置最后修改时间
     * @param updatetime
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    /**
     * 功能：获得最后修改人
     * @return
     */
    public String getUpdatemanid() {
        return this.updatemanid;
    }
    /**
     * 功能：设置最后修改人
     * @param updatemanid
     */
    public void setUpdatemanid(String updatemanid) {
        this.updatemanid = updatemanid;
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
	 * 功能：获得是否双升货位巷道
	 */
	public String getIstwin() {
		return istwin;
	}

	/**
	 * 功能：设置是否双升货位巷道
	 * @param istwin
	 */
	public void setIstwin(String istwin) {
		this.istwin = istwin;
	}
}