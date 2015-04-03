package com.wms3.bms.standard.entity.base;



/**
 * 描述：部门表
 * @author hug
 */
public class BaseDepartment implements java.io.Serializable {

     /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1575475036346770416L;	

    // Fields    
	
	private String departmentid;	// 部门编号
    private String shortname;		// 部门简称
    private String departmentname;	// 部门全称
    private String departmenttype;	// 部门分类
    private String contact;			// 联系人
    private String phone;			// 联系电话
    private String fax;				// 传真
    private String address;			// 地址
    private String createtime;		// 创建时间
    private String createmanid;		// 创建人
    private String updatetime;		// 最后修改时间
    private String updatemanid;		// 最后修改人
    private String useflag;			// 是否启用 Y/N
    
    //派生属性
    private String departmenttypename;	// 部门分类名

    // Constructors

    /** default constructor */
    public BaseDepartment() {
    }

	/** minimal constructor */
    public BaseDepartment(String departmentname) {
        this.departmentname = departmentname;
    }
    
    /** full constructor */
    public BaseDepartment(String shortname, String departmentname, String departmenttype, String contact, String phone, 
    		String fax, String address, String createtime, String createmanid, String updatetime, String updatemanid, String useflag) {
    	
        this.shortname = shortname;
        this.departmentname = departmentname;
        this.departmenttype = departmenttype;
        this.contact = contact;
        this.phone = phone;
        this.fax = fax;
        this.address = address;
        this.createtime = createtime;
        this.createmanid = createmanid;
        this.updatetime = updatetime;
        this.updatemanid = updatemanid;
        this.useflag = useflag;
    }

   
    // Property accessors
    /**
     * 功能：获得部门编号
     */
    public String getDepartmentid() {
        return this.departmentid;
    }
    
    /**
     * 功能：设置部门编号
     * @param departmentid
     */
    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * 功能：获得部门简称
     */
    public String getShortname() {
        return this.shortname;
    }
    
    /**
     * 功能：设置部门简称
     * @param shortname
     */
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    /**
     * 功能：获得部门全称
     */
    public String getDepartmentname() {
        return this.departmentname;
    }
    
    /**
     * 功能：设置部门全称
     * @param departmentname
     */
    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    /**
     * 功能：获得部门分类
     */
    public String getDepartmenttype() {
        return this.departmenttype;
    }
    
    /**
     * 功能：设置部门分类
     * @param departmenttype
     */
    public void setDepartmenttype(String departmenttype) {
        this.departmenttype = departmenttype;
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
     * 功能：获得部门分类名
     */
    public String getDepartmenttypename() {
        return this.departmenttypename;
    }
    
    /**
     * 功能：设置部门分类名
     * @param departmenttypename
     */
    public void setDepartmenttypename(String departmenttypename) {
        this.departmenttypename = departmenttypename;
    }
}