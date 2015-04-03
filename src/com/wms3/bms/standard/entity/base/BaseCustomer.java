package com.wms3.bms.standard.entity.base;



/**
 * 描述：客户表
 * @author hug
 */
public class BaseCustomer  implements java.io.Serializable {

     /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8723672586391308558L;
	
    // Fields    
	private String customerid;		// 客户编号
    private String shortname;		// 客户简称
    private String customername;	// 客户全称
    private String customertype;	// 客户分类
    private String contact;			// 联系人
    private String phone;			// 联系电话
    private String fax;				// 传真
    private String address;			// 地址
    private String bank;			// 开户银行
    private String putawayid;		// 上架规则ID
    private String allocationid;	// 分配规则ID
    private String replenishid;		// 补货规则ID
    private String linenumber;		// 线路号
    private String pakageid;		// 包装ID 
    private String useflag;			// 是否启用
    private String createtime;		// 创建时间
    private String createmanid;		// 创建人
    private String updatetime;		// 最后修改时间
    private String updatemanid;		// 最后修改人
    
    //派生属性
    private String customertypename;	// 客户分类名
    private String putawayname;			// 上架规则名称
    private String allocationname;		// 分配规则名称
    private String replenishname;		// 补货规则名称
    private String pkgdesc;				// 包装描述

    // Constructors

    /** default constructor */
    public BaseCustomer() {
    }

	/** minimal constructor */
    public BaseCustomer(String customername) {
        this.customername = customername;
    }

   
    // Property accessors
    /**
     * 功能：获得客户编号
     */
    public String getCustomerid() {
        return this.customerid;
    }
    
    /**
     * 功能：设置客户编号
     * @param customerid
     */    
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
    
    /**
     * 功能：获得客户简称
     */
    public String getShortname() {
        return this.shortname;
    }
    
    /**
     * 功能：设置客户简称
     * @param shortname
     */ 
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
    
    /**
     * 功能：获得客户全称
     */
    public String getCustomername() {
        return this.customername;
    }
    
    /**
     * 功能：设置客户全称
     * @param customername
     */ 
    public void setCustomername(String customername) {
        this.customername = customername;
    }
    
    /**
     * 功能：获得客户分类
     */
    public String getCustomertype() {
        return this.customertype;
    }
    
    /**
     * 功能：设置客户分类
     * @param customertype
     */ 
    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }
    
    /**
     * 功能：获得联系人
     */
    public String getContact() {
        return this.contact;
    }
    
    /**
     * 功能：设置联系人
     * @param contact
     */ 
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    /**
     * 功能：获得联系电话
     */
    public String getPhone() {
        return this.phone;
    }
    
    /**
     * 功能：设置联系电话
     * @param phone
     */ 
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * 功能：获得传真
     */
    public String getFax() {
        return this.fax;
    }
    
    /**
     * 功能：设置传真
     * @param fax
     */ 
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    /**
     * 功能：获得地址
     */
    public String getAddress() {
        return this.address;
    }
    
    /**
     * 功能：设置地址
     * @param address
     */ 
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * 功能：获得开户银行
     */
    public String getBank() {
        return this.bank;
    }
    
    /**
     * 功能：设置开户银行
     * @param bank
     */ 
    public void setBank(String bank) {
        this.bank = bank;
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
     * 功能：获得是否启用
     */
    public String getUseflag() {
        return this.useflag;
    }
    
    /**
     * 功能：设置是否启用
     * @param useflag
     */ 
    public void setUseflag(String useflag) {
        this.useflag = useflag;
    }
    
    /**
     * 功能：获得客户分类名
     */
    public String getCustomertypename() {
        return this.customertypename;
    }
    
    /**
     * 功能：设置客户分类名
     * @param customertypename
     */ 
    public void setCustomertypename(String customertypename) {
        this.customertypename = customertypename;
    }

	/**
	 * 功能：获得线路号
	 */
	public String getLinenumber() {
		return linenumber;
	}

	/**
	 * 功能：设置线路号
	 * @param linenumber
	 */
	public void setLinenumber(String linenumber) {
		this.linenumber = linenumber;
	}

	/**
	 * 功能：获得分配规则ID
	 */
	public String getAllocationid() {
		return allocationid;
	}

	/**
	 * 功能：设置分配规则ID
	 * @param allocationid
	 */
	public void setAllocationid(String allocationid) {
		this.allocationid = allocationid;
	}

	/**
	 * 功能：获得分配规则名称
	 */
	public String getAllocationname() {
		return allocationname;
	}

	/**
	 * 功能：设置分配规则名称
	 * @param allocationname
	 */
	public void setAllocationname(String allocationname) {
		this.allocationname = allocationname;
	}

	/**
	 * 功能：获得上架规则ID
	 */
	public String getPutawayid() {
		return putawayid;
	}

	/**
	 * 功能：设置上架规则ID
	 * @param putawayid
	 */
	public void setPutawayid(String putawayid) {
		this.putawayid = putawayid;
	}

	/**
	 * 功能：获得上架规则名称
	 */
	public String getPutawayname() {
		return putawayname;
	}

	/**
	 * 功能：设置上架规则名称
	 * @param putawayname
	 */
	public void setPutawayname(String putawayname) {
		this.putawayname = putawayname;
	}

	/**
	 * 功能：获得补货规则ID
	 */
	public String getReplenishid() {
		return replenishid;
	}

	/**
	 * 功能：设置补货规则ID
	 * @param replenishid
	 */
	public void setReplenishid(String replenishid) {
		this.replenishid = replenishid;
	}

	/**
	 * 功能：获得补货规则名称
	 */
	public String getReplenishname() {
		return replenishname;
	}

	/**
	 * 功能：设置补货规则名称
	 * @param replenishname
	 */
	public void setReplenishname(String replenishname) {
		this.replenishname = replenishname;
	}

	/**
	 * 功能：获得包装ID 
	 */
	public String getPakageid() {
		return pakageid;
	}

	/**
	 * 功能：设置包装ID 
	 * @param pakageid
	 */
	public void setPakageid(String pakageid) {
		this.pakageid = pakageid;
	}

	/**
	 * 功能：获得包装描述
	 */
	public String getPkgdesc() {
		return pkgdesc;
	}

	/**
	 * 功能：设置包装描述
	 * @param pkgdesc
	 */
	public void setPkgdesc(String pkgdesc) {
		this.pkgdesc = pkgdesc;
	}
    
    
}