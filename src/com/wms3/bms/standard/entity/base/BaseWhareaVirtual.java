package com.wms3.bms.standard.entity.base;


/**
 * 描述：虚拟库区表
 * @author hug
 *
 */
public class BaseWhareaVirtual  implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6074534054844940198L;
	
	private String whAreaId;		//库区ID
    private String whAreaName;		//库区名
    private String warehouseid;		//仓库ID
    private String createtime;		//创建时间
    private String createmanid;		//创建人
    private String ERPCode;			//ERP代码
    private String updatetime;		//最后修改时间
    private String updatemanid;		//最后修改人
    
    private String belongto;	    // 隶属于 哪个物理库区（一般用在 哪个暂存隶属于哪个非暂存类型的库区）

    private String remarks1;	//预留字段
    private String remarks2;	//预留字段
    
    private String ERPAccount;	//ERP账套
	//派生属性
    private String warehousename;	//仓库名
    private String belongTowhAreaName;	//隶属于哪个物理库区

    // Constructors

	/** default constructor */
    public BaseWhareaVirtual() {
    }

	/** minimal constructor */
    public BaseWhareaVirtual(String whAreaName) {
        this.whAreaName = whAreaName;
    }

   
    // Property accessors
    /**
     * 功能：获得库区ID
     */
    public String getwhAreaId() {
        return this.whAreaId;
    }
    /**
     * 功能：设置库区ID
     * @param whAreaId
     */
    public void setwhAreaId(String whAreaId) {
        this.whAreaId = whAreaId;
    }
    /**
     * 功能：获得库区名
     * @return
     */
    public String getwhAreaName() {
        return this.whAreaName;
    }
    /**
     * 功能：设置库区名
     * @param whAreaName
     */
    public void setwhAreaName(String whAreaName) {
        this.whAreaName = whAreaName;
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
	
    /**
     * 功能：获得隶属物理库区
     * @return
     */
    public String getBelongto() {
		return belongto;
	}
    
	/**
	 * 功能：设置隶属物理库区
	 * @param belongto
	 */
	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

    /**
     * 功能：获得隶属物理库区
     * @return
     */
    public String getBelongTowhAreaName() {
		return belongTowhAreaName;
	}

	/**
	 * 功能：设置隶属物理库区
	 * @param belongTowhAreaName
	 */
	public void setBelongTowhAreaName(String belongTowhAreaName) {
		this.belongTowhAreaName = belongTowhAreaName;
	}
	
	public String getERPCode() {
		return ERPCode;
	}

	public void setERPCode(String eRPCode) {
		ERPCode = eRPCode;
	}

	public String getERPAccount() {
		return ERPAccount;
	}

	public void setERPAccount(String eRPAccount) {
		ERPAccount = eRPAccount;
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}

	public String getRemarks2() {
		return remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}
	
	
}