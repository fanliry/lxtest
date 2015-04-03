package com.wms3.bms.standard.entity.base;



/**
 * 描述：货位表
 * @author hug
 */
public class BaseCargospace implements java.io.Serializable { 

     /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3967806561646973624L;
	
	private String cargoSpaceId;		// 货位ID
	private String cargoSpaceName;		// 货位名称
	private String csstatus;			// 货位状态 1：空货位；2：满货位；3：入库分配；4：出库分配；5：入库需盘点；6：出库需盘点；7：出库取货；8：已出货
	private Integer csplatoon;			// 货位排
	private Integer cscolumn;			// 货位列
	private Integer csfloor;			// 货位层
	private String cstype;				// 货位类型
	private String inlock;				// 入库锁 Y.是 N.否
	private String outlock;				// 出库锁 Y.是 N.否
	private String cargoAlleyId;		// 巷道ID
	private String cargoAreaId;			// 货区ID
	private String warehouseid;			// 仓库ID
	private String whAreaId;			// 库区ID
	private String storagetype;			// 存储类型(存储单位：托,箱,件,箱/件,其他)
	private String puttype;				// 上架类型(入库类型：1：只能分配一次2：可多次分配)
	private String picktype;			// 拣选类型(出库类型：1：只能分配一次；2：可多次分配)
	private double length;				// 长
	private double width;				// 宽
	private double height;				// 高
	private double volume;				// 体积
	private double xcoordinate;			// x坐标
	private double ycoordinate;			// y坐标
	private double zcoordinate;			// z坐标
	private String environment;			// 环境
	private Integer layernum;			// 层数
	private double loadweight;			// 承重
	private Integer palletnum;			// 可放托盘数	-1-没有限制 0-不能放 n-具体数字表示可放数量
    private Integer haspalletnum;       // 已放托盘数
	private String istwin;				// 是否双升货位 Y.是 N.否
	private String location;			// 位置：1：里面；2：外面
	private String twincsid;			// 相邻双升货位ID
	private String usagetype;   		// 库位使用
	private String attributetype;   	// 库位属性
	private String demandtype;   		// 周转需求
	private String createtime;			// 创建时间
	private String createmanid;			// 创建人
	private String updatetime;			// 最后修改时间
	private String updatemanid;			// 最后修改人
	
	//派生属性
	private String cargoAlleyName;		// 巷道名称
	private String cargoAreaName;		// 货区名称
	private String warehousename;		// 仓库名称
	private String whAreaName;			// 库区名称
	private String csstatusname;		// 货位状态名称
	private String cstypename;			// 货位类型名称
	private String storagetypename;		// 存储类型名称
	private String puttypename;			// 上架类型名称
	private String picktypename;		// 拣选类型名称
	private String twincsname;			// 相邻双升货位名称
	private String usagetypename;   	// 库位使用名称
	private String attributetypename;   // 库位属性名称
	private String demandtypename;   	// 周转需求名称
	
    // Constructors

    /** default constructor */
    public BaseCargospace() {
    }

   
    // Property accessors
    /**
     * 功能：获得货位ID
     */
    public String getCargoSpaceId() {
        return this.cargoSpaceId;
    }
    
    /**
     * 功能：设置货位ID
     * @param cargoSpaceId
     */
    public void setCargoSpaceId(String cargoSpaceId) {
        this.cargoSpaceId = cargoSpaceId;
    }
    
    /**
     * 功能：获得货位名称
     */
    public String getCargoSpaceName() {
        return this.cargoSpaceName;
    }
    
    /**
     * 功能：设置货位名称
     * @param cargoSpaceName
     */
    public void setCargoSpaceName(String cargoSpaceName) {
        this.cargoSpaceName = cargoSpaceName;
    }

    /**
     * 功能：获得货位状态
     */
    public String getCsstatus() {
        return this.csstatus;
    }
    
    /**
     * 功能：设置货位状态
     * @param csstatus
     */
    public void setCsstatus(String csstatus) {
        this.csstatus = csstatus;
    }

    /**
     * 功能：获得货位排
     */
    public Integer getCsplatoon() {
        return this.csplatoon;
    }
    
    /**
     * 功能：设置货位排
     * @param csplatoon
     */
    public void setCsplatoon(Integer csplatoon) {
        this.csplatoon = csplatoon;
    }
    
    /**
     * 功能：获得货位列
     */
    public Integer getCscolumn() {
        return this.cscolumn;
    }
    
    /**
     * 功能：设置货位列
     * @param cscolumn
     */
    public void setCscolumn(Integer cscolumn) {
        this.cscolumn = cscolumn;
    }

    /**
     * 功能：获得货位层
     */
    public Integer getCsfloor() {
        return this.csfloor;
    }
    
    /**
     * 功能：设置货位层
     * @param csfloor
     */
    public void setCsfloor(Integer csfloor) {
        this.csfloor = csfloor;
    }
    
    /**
     * 功能：获得货位类型
     */
    public String getCstype() {
        return this.cstype;
    }
    
    /**
     * 功能：设置货位类型
     * @param cstype
     */
    public void setCstype(String cstype) {
        this.cstype = cstype;
    }

    /**
     * 功能：获得入库锁
     */
    public String getInlock() {
        return this.inlock;
    }
    
    /**
     * 功能：设置入库锁
     * @param inlock
     */
    public void setInlock(String inlock) {
        this.inlock = inlock;
    }

    /**
     * 功能：获得出库锁
     */
    public String getOutlock() {
        return this.outlock;
    }
    
    /**
     * 功能：设置出库锁
     * @param outlock
     */    
    public void setOutlock(String outlock) {
        this.outlock = outlock;
    }

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
     * 功能：获得仓库ID
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
     * 功能：获得存储类型
     */
    public String getStoragetype() {
        return this.storagetype;
    }
    
    /**
     * 功能：设置存储类型
     * @param storagetype
     */     
    public void setStoragetype(String storagetype) {
        this.storagetype = storagetype;
    }

    /**
     * 功能：获得上架类型
     */
    public String getPuttype() {
        return this.puttype;
    }
    
    /**
     * 功能：设置上架类型
     * @param puttype
     */     
    public void setPuttype(String puttype) {
        this.puttype = puttype;
    }

    /**
     * 功能：获得拣选类型
     */
    public String getPicktype() {
        return this.picktype;
    }
    
    /**
     * 功能：设置拣选类型
     * @param picktype
     */     
    public void setPicktype(String picktype) {
        this.picktype = picktype;
    }

    /**
     * 功能：获得长
     */
    public double getLength() {
        return this.length;
    }
    
    /**
     * 功能：设置长
     * @param length
     */     
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * 功能：获得宽
     */
    public double getWidth() {
        return this.width;
    }
    
    /**
     * 功能：设置宽
     * @param width
     */     
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * 功能：获得高
     */
    public double getHeight() {
        return this.height;
    }
    
    /**
     * 功能：设置高
     * @param height
     */     
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * 功能：获得体积
     */
    public double getVolume() {
        return this.volume;
    }
    
    /**
     * 功能：设置体积
     * @param 体积
     */     
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * 功能：获得x坐标
     */
    public double getXcoordinate() {
        return this.xcoordinate;
    }
    
    /**
     * 功能：设置x坐标
     * @param x坐标
     */     
    public void setXcoordinate(double xcoordinate) {
        this.xcoordinate = xcoordinate;
    }

    /**
     * 功能：获得y坐标
     */
    public double getYcoordinate() {
        return this.ycoordinate;
    }
    
    /**
     * 功能：设置y坐标
     * @param ycoordinate
     */     
    public void setYcoordinate(double ycoordinate) {
        this.ycoordinate = ycoordinate;
    }

    /**
     * 功能：获得z坐标
     */
    public double getZcoordinate() {
        return this.zcoordinate;
    }
    
    /**
     * 功能：设置z坐标
     * @param zcoordinate
     */     
    public void setZcoordinate(double zcoordinate) {
        this.zcoordinate = zcoordinate;
    }

    /**
     * 功能：获得环境
     */
    public String getEnvironment() {
        return this.environment;
    }
    
    /**
     * 功能：设置环境
     * @param environment
     */     
    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    /**
     * 功能：获得层数
     */
    public Integer getLayernum() {
        return this.layernum;
    }
    
    /**
     * 功能：设置层数
     * @param layernum
     */     
    public void setLayernum(Integer layernum) {
        this.layernum = layernum;
    }

    /**
     * 功能：获得承重
     */
    public double getLoadweight() {
        return this.loadweight;
    }
    
    /**
     * 功能：设置承重
     * @param loadweight
     */     
    public void setLoadweight(double loadweight) {
        this.loadweight = loadweight;
    }

    /**
     * 功能：获得可放托盘数
     */
    public Integer getPalletnum() {
        return this.palletnum;
    }
    
    /**
     * 功能：设置可放托盘数
     * @param palletnum
     */     
    public void setPalletnum(Integer palletnum) {
        this.palletnum = palletnum;
    }

    /**
     * 功能：获得创建时间
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
     * 功能：获得巷道名称
     */
    public String getCargoAlleyName() {
        return this.cargoAlleyName;
    }
    
    /**
     * 功能：设置巷道名称
     * @param cargoAlleyName
     */     
    public void setCargoAlleyName(String cargoAlleyName) {
        this.cargoAlleyName = cargoAlleyName;
    }
    
    /**
     * 功能：获得货区名称
     */
    public String getCargoAreaName() {
        return this.cargoAreaName;
    }
    
    /**
     * 功能：设置货区名称
     * @param cargoAlleyName
     */     
    public void setCargoAreaName(String cargoAreaName) {
        this.cargoAreaName = cargoAreaName;
    }
    
    /**
     * 功能：获得仓库名称
     */
    public String getWarehousename() {
        return this.warehousename;
    }
    
    /**
     * 功能：设置仓库名称
     * @param warehousename
     */     
    public void setWarehousename(String warehousename) {
        this.warehousename = warehousename;
    }

    /**
     * 功能：获得库区名称
     */
    public String getWhAreaName() {
        return this.whAreaName;
    }

    /**
     * 功能：设置库区名称
     * @param warehousename
     */     
    public void setWhAreaName(String whAreaName) {
        this.whAreaName = whAreaName;
    }
    
    /**
     * 功能：获得货位状态名称
     */
    public String getCsstatusname() {
        return this.csstatusname;
    }
    
    /**
     * 功能：设置货位状态名称
     * @param csstatusname
     */     
    public void setCsstatusname(String csstatusname) {
        this.csstatusname = csstatusname;
    }
    
    /**
     * 功能：获得货位类型名称
     */
    public String getCstypename() {
        return this.cstypename;
    }
    
    /**
     * 功能：设置货位类型名称
     * @param cstypename
     */     
    public void setCstypename(String cstypename) {
        this.cstypename = cstypename;
    }
    
    /**
     * 功能：获得存储类型名称
     */
    public String getStoragetypename() {
        return this.storagetypename;
    }
    
    /**
     * 功能：设置存储类型名称
     * @param storagetypename
     */     
    public void setStoragetypename(String storagetypename) {
        this.storagetypename = storagetypename;
    }
    
    /**
     * 功能：获得上架类型名称
     */
    public String getPuttypename() {
        return this.puttypename;
    }
    
    /**
     * 功能：设置上架类型名称
     * @param puttypename
     */     
    public void setPuttypename(String puttypename) {
        this.puttypename = puttypename;
    }
    
    /**
     * 功能：获得拣选类型名称
     */
    public String getPicktypename() {
        return this.picktypename;
    }
    
    /**
     * 功能：设置拣选类型名称
     * @param picktypename
     */     
    public void setPicktypename(String picktypename) {
        this.picktypename = picktypename;
    }



	/**
	 * 功能：获得是否双升货位 Y.是 N.否
	 */
	public String getIstwin() {
		return istwin;
	}



	/**
	 * 功能：设置是否双升货位 Y.是 N.否
	 * @param istwin
	 */
	public void setIstwin(String istwin) {
		this.istwin = istwin;
	}



	/**
	 * 功能：获得位置：1：里面；2：外面
	 */
	public String getLocation() {
		return location;
	}



	/**
	 * 功能：设置位置：1：里面；2：外面
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}



	/**
	 * 功能：获得相邻双升货位ID
	 */
	public String getTwincsid() {
		return twincsid;
	}



	/**
	 * 功能：设置相邻双升货位ID
	 * @param twincsid
	 */
	public void setTwincsid(String twincsid) {
		this.twincsid = twincsid;
	}


	/**
	 * 功能：获得相邻双升货位名称
	 */
	public String getTwincsname() {
		return twincsname;
	}


	/**
	 * 功能：设置相邻双升货位名称
	 * @param twincsname
	 */
	public void setTwincsname(String twincsname) {
		this.twincsname = twincsname;
	}


	/**
	 * 功能：获得库位属性
	 */
	public String getAttributetype() {
		return attributetype;
	}


	/**
	 * 功能：设置库位属性
	 * @param attributetype
	 */
	public void setAttributetype(String attributetype) {
		this.attributetype = attributetype;
	}


	/**
	 * 功能：获得周转需求
	 */
	public String getDemandtype() {
		return demandtype;
	}


	/**
	 * 功能：设置周转需求
	 * @param demandtype
	 */
	public void setDemandtype(String demandtype) {
		this.demandtype = demandtype;
	}


	/**
	 * 功能：获得库位使用
	 */
	public String getUsagetype() {
		return usagetype;
	}


	/**
	 * 功能：设置库位使用
	 * @param usagetype
	 */
	public void setUsagetype(String usagetype) {
		this.usagetype = usagetype;
	}


	/**
	 * 功能：获得库位属性名称
	 */
	public String getAttributetypename() {
		return attributetypename;
	}


	/**
	 * 功能：设置库位属性名称
	 * @param attributetypename
	 */
	public void setAttributetypename(String attributetypename) {
		this.attributetypename = attributetypename;
	}


	/**
	 * 功能：获得周转需求名称
	 */
	public String getDemandtypename() {
		return demandtypename;
	}


	/**
	 * 功能：设置周转需求名称
	 * @param demandtypename
	 */
	public void setDemandtypename(String demandtypename) {
		this.demandtypename = demandtypename;
	}


	/**
	 * 功能：获得库位使用名称
	 */
	public String getUsagetypename() {
		return usagetypename;
	}


	/**
	 * 功能：设置库位使用名称
	 * @param usagetypename
	 */
	public void setUsagetypename(String usagetypename) {
		this.usagetypename = usagetypename;
	}


    /**
     * 功能:获得已放托盘数
     * @author hug 2012-9-6 
     * @return
     */
    public Integer getHaspalletnum() {
        return haspalletnum;
    }


    /**
     * 功能:设置已放托盘数
     * @author hug 2012-9-6 
     * @param haspalletnum
     */
    public void setHaspalletnum(Integer haspalletnum) {
        this.haspalletnum = haspalletnum;
    }
	/**
	 * 根据货位查询货位
	 * @param cargoSpaceId
	 * @return
	 */
	public String getQueryHQLByspaceid(String cargoSpaceId)
	{
		String hql = "from BaseCargospace where 1=1 ";
		if(cargoSpaceId != null && cargoSpaceId.trim().length() > 0){
			hql += " and cargoSpaceId='"+cargoSpaceId+"'";
		}
		return hql;
	}
    
}