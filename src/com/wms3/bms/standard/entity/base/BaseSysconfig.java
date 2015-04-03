package com.wms3.bms.standard.entity.base;



/**
 * 描述：系统配置表
 * @author hug
 */
public class BaseSysconfig  implements java.io.Serializable {

     /**
	 * 
	 */
	private static final long serialVersionUID = -8754766350695980425L;

    // Fields    
	private String sysconfigid;		// 系统配置编号
    private String sysconfigtype;	// 配置分类
    private String cfgvalue;		// 联系人
    private String remark;			// 备注
    private String createtime;		// 创建时间
    private String createmanid;		// 创建人
    private String updatetime;		// 最后修改时间
    private String updatemanid;		// 最后修改人
    private String useflag;			// 是否启用


    // Constructors

    /** default constructor */
    public BaseSysconfig() {
    }

    
    /** full constructor */
    public BaseSysconfig(String sysconfigtype, String cfgvalue, String remark, String createtime, String createmanid, String updatetime, String updatemanid, String useflag) {
        this.sysconfigtype = sysconfigtype;
        this.cfgvalue = cfgvalue;
        this.remark = remark;
        this.createtime = createtime;
        this.createmanid = createmanid;
        this.updatetime = updatetime;
        this.updatemanid = updatemanid;
        this.useflag = useflag;
    }

   
    // Property accessors
    /**
     * 功能：获得系统配置编号
     */
    public String getSysconfigid() {
        return this.sysconfigid;
    }
    
    /**
     * 功能：设置系统配置编号
     * @param sysconfigid
     */ 
    public void setSysconfigid(String sysconfigid) {
        this.sysconfigid = sysconfigid;
    }

    /**
     * 功能：获得配置分类
     */
    public String getSysconfigtype() {
        return this.sysconfigtype;
    }
    
    /**
     * 功能：设置配置分类
     * @param sysconfigtype
     */ 
    public void setSysconfigtype(String sysconfigtype) {
        this.sysconfigtype = sysconfigtype;
    }

    /**
     * 功能：获得联系人
     */
    public String getCfgvalue() {
        return this.cfgvalue;
    }
    
    /**
     * 功能：设置联系人
     * @param cfgvalue
     */ 
    public void setCfgvalue(String cfgvalue) {
        this.cfgvalue = cfgvalue;
    }

    /**
     * 功能：获得备注
     */
    public String getRemark() {
        return this.remark;
    }
    
    /**
     * 功能：设置备注
     * @param remark
     */ 
    public void setRemark(String remark) {
        this.remark = remark;
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
}