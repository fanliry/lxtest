package com.wms3.bms.standard.entity.base;


/**
 * 描述：库区表
 * @author hug
 *
 */
public class BaseWharea  implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3212529052570509895L;
	
	private String whAreaId;		//库区ID
    private String whAreaName;		//库区名
    private String whAreaType;		//库区类型  2：立体库区  否则不为立体库
    private String warehouseid;		//仓库ID
    private String createtime;		//创建时间
    private String createmanid;		//创建人
    private String updatetime;		//最后修改时间
    private String updatemanid;		//最后修改人
    private String isdefault;		//是否是默认收货区  是：Y  否：N
    private String istask;			//是否需要生成调度任务 是：Y 否：N
    private String storageEnvironment;//存储环境代码
    
    private String belongto;	    // 隶属于 哪个库区（一般用在 哪个暂存隶属于哪个非暂存类型的库区）

	//派生属性
    private String warehousename;	//仓库名
    private String whAreaTypeName;	//库区类型名
    private String storageEnvironmentName;//存储环境名
    private String belongTowhAreaName;	//隶属于哪个库区

    // Constructors

	public String getStorageEnvironment() {
		return storageEnvironment;
	}

	public void setStorageEnvironment(String storageEnvironment) {
		this.storageEnvironment = storageEnvironment;
	}

	public String getStorageEnvironmentName() {
		return storageEnvironmentName;
	}

	public void setStorageEnvironmentName(String storageEnvironmentName) {
		this.storageEnvironmentName = storageEnvironmentName;
	}

	/** default constructor */
    public BaseWharea() {
    }

	/** minimal constructor */
    public BaseWharea(String whAreaName) {
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
     * 功能：获得库区类型
     * @return
     */
    public String getwhAreaType() {
        return this.whAreaType;
    }
    /**
     * 功能：设置库区类型
     * @param whAreaType
     */
    public void setwhAreaType(String whAreaType) {
        this.whAreaType = whAreaType;
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
     * 功能：获得是否是默认收货区
     * @return
     */
	public String getIsdefault() {
		return isdefault;
	}
	/**
	 * 功能：设置是否是默认收货区
	 * @param isdefault
	 */
	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
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
     * 功能：获得库区类型名
     * @return
     */
    public String getwhAreaTypeName() {
        return this.whAreaTypeName;
    }
    
    /**
     * 功能：设置库区类型名
     * @param whAreaTypeName
     */
    public void setwhAreaTypeName(String whAreaTypeName) {
        this.whAreaTypeName = whAreaTypeName;
    }

	/**
	 * 功能：获得是否需要生成调度任务 是：Y 否：N
	 */
	public String getIstask() {
		return istask;
	}

	/**
	 * 功能：设置是否需要生成调度任务 是：Y 否：N
	 * @param istask
	 */
	public void setIstask(String istask) {
		this.istask = istask;
	}
	
    /**
     * 功能：获得隶属库区
     * @return
     */
    public String getBelongto() {
		return belongto;
	}
    
	/**
	 * 功能：设置隶属库区
	 * @param belongto
	 */
	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

    /**
     * 功能：获得隶属库区
     * @return
     */
    public String getBelongTowhAreaName() {
		return belongTowhAreaName;
	}

	/**
	 * 功能：设置隶属库区
	 * @param belongTowhAreaName
	 */
	public void setBelongTowhAreaName(String belongTowhAreaName) {
		this.belongTowhAreaName = belongTowhAreaName;
	}
	
}