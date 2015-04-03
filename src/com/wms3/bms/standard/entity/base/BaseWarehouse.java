package com.wms3.bms.standard.entity.base;



/**
 * 描述：仓库表
 * @author hug
 */
public class BaseWarehouse  implements java.io.Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8039395923359479863L;

    // Fields    
	private String warehouseid;		// 仓库ID
    private String warehousename;	// 仓库名
    private String whaddress;		// 仓库地址
    private String whmgrman;		// 仓库管理员
    private String whlinkman;		// 联系人
    private String whlinktel;		// 联系电话
    private String iscurrent;		// 是否当前仓库 Y：是；N：不是
    private String erpcode;			// 对应ERP的代码
    private String createtime;		// 创建时间
    private String createmanid;		// 创建人
    private String updatetime;		// 最后修改时间
    private String updatemanid;		// 最后修改人
    
    //派生属性
    private String warehousetypename;	// 仓库类型名称

    // Constructors

    /** default constructor */
    public BaseWarehouse() {
    }

    
    // Property accessors
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
     * 功能：获得仓库名
     */
    public String getWarehousename() {
        return this.warehousename;
    }
    
    /**
     * 功能：设置仓库名
     * @param warehousename
     */
    public void setWarehousename(String warehousename) {
        this.warehousename = warehousename;
    }

    /**
     * 功能：获得仓库地址
     */
    public String getWhaddress() {
        return this.whaddress;
    }
    
    /**
     * 功能：设置仓库地址
     * @param whaddress
     */
    public void setWhaddress(String whaddress) {
        this.whaddress = whaddress;
    }

    /**
     * 功能：获得仓库管理员
     */
    public String getWhmgrman() {
        return this.whmgrman;
    }
    
    /**
     * 功能：设置仓库管理员
     * @param whmgrman
     */
    public void setWhmgrman(String whmgrman) {
        this.whmgrman = whmgrman;
    }

    /**
     * 功能：获得联系人
     */
    public String getWhlinkman() {
        return this.whlinkman;
    }
    
    /**
     * 功能：设置联系人
     * @param whlinkman
     */
    public void setWhlinkman(String whlinkman) {
        this.whlinkman = whlinkman;
    }

    /**
     * 功能：获得联系电话
     */
    public String getWhlinktel() {
        return this.whlinktel;
    }
    
    /**
     * 功能：设置联系电话
     * @param whlinktel
     */
    public void setWhlinktel(String whlinktel) {
        this.whlinktel = whlinktel;
    }
    
    /**
     * 功能：获得是否当前仓库
     */
    public String getIscurrent() {
        return this.iscurrent;
    }
    
    /**
     * 功能：设置是否当前仓库
     * @param iscurrent
     */
    public void setIscurrent(String iscurrent) {
        this.iscurrent = iscurrent;
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
     * 功能：获得仓库类型名称
     */
    public String getWarehousetypename() {
        return this.warehousetypename;
    }
    
    /**
     * 功能：设置仓库类型名称
     * @param warehousetypename
     */
    public void setWarehousetypename(String warehousetypename) {
        this.warehousetypename = warehousetypename;
    }


	/**
	 * 功能：获得对应ERP的代码
	 */
	public String getErpcode() {
		return erpcode;
	}


	/**
	 * 功能：设置对应ERP的代码
	 * @param erpcode
	 */
	public void setErpcode(String erpcode) {
		this.erpcode = erpcode;
	}
}