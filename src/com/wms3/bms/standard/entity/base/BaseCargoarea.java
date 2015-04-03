package com.wms3.bms.standard.entity.base;


/**
 * 描述：货区表
 * @author hug
 *
 */
public class BaseCargoarea  implements java.io.Serializable {

	private static final long serialVersionUID = 4222340800660371699L;
	private String cargoAreaId;		//货区ID
    private String cargoAreaName;	//货区名
    private String warehouseid;		//仓库ID
    private String createtime;		//创建时间
    private String createmanid;		//创建人
    private String updatetime;		//最后修改时间
    private String updatemanid;		//最后修改人

    private String warehousename;	//仓库名
    
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
     * 功能：获得货区ID
     */
    public String getCargoAreaId() {
        return this.cargoAreaId;
    }
    /**
     * 功能：设置货区ID
     * @param cargoAreaId
     */
    public void setCargoAreaId(String cargoAreaId) {
        this.cargoAreaId = cargoAreaId;
    }
    /**
     * 功能：获得货区名
     * @return
     */
    public String getCargoAreaName() {
        return this.cargoAreaName;
    }
    /**
     * 功能：设置货区名
     * @param cargoAreaName
     */
    public void setCargoAreaName(String cargoAreaName) {
        this.cargoAreaName = cargoAreaName;
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
     * 功能：获得仓库ID
     * @return
     */
	public String getWarehouseid() {
		return warehouseid;
	}
	/**
	 * 功能：设置仓库ID
	 * @param warehouseid
	 */
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
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
}