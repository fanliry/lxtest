package com.wms3.bms.standard.entity.base;



/**
 * 描述：包装表
 * @author hug
 */
public class BasePackage  implements java.io.Serializable {

     /**
	 * 
	 */
	private static final long serialVersionUID = -476604141423683384L;
	
    // Fields    

	private String packageid;		// 包装ID
    private String pkgdesc;			// 包装描述
    private String remark;			// 备注
    
    // Constructors

    /** default constructor */
    public BasePackage() {
    }

    
    /** full constructor */
    public BasePackage(String pkgdesc, String remark) {
    	
        this.pkgdesc = pkgdesc;
        this.remark = remark;
    }

   
    // Property accessors
    /**
     * 功能：获得包装ID
     */
    public String getPackageid() {
        return this.packageid;
    }
    
    /**
     * 功能：设置包装ID
     * @param packageid
     */ 
    public void setPackageid(String packageid) {
        this.packageid = packageid;
    }

    /**
     * 功能：获得包装描述
     */
    public String getPkgdesc() {
        return this.pkgdesc;
    }
    
    /**
     * 功能：设置包装描述
     * @param pkgdesc
     */ 
    public void setPkgdesc(String pkgdesc) {
        this.pkgdesc = pkgdesc;
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
}