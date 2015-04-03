package com.wms3.bms.standard.entity.base;



/**
 * ��������װ��
 * @author hug
 */
public class BasePackage  implements java.io.Serializable {

     /**
	 * 
	 */
	private static final long serialVersionUID = -476604141423683384L;
	
    // Fields    

	private String packageid;		// ��װID
    private String pkgdesc;			// ��װ����
    private String remark;			// ��ע
    
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
     * ���ܣ���ð�װID
     */
    public String getPackageid() {
        return this.packageid;
    }
    
    /**
     * ���ܣ����ð�װID
     * @param packageid
     */ 
    public void setPackageid(String packageid) {
        this.packageid = packageid;
    }

    /**
     * ���ܣ���ð�װ����
     */
    public String getPkgdesc() {
        return this.pkgdesc;
    }
    
    /**
     * ���ܣ����ð�װ����
     * @param pkgdesc
     */ 
    public void setPkgdesc(String pkgdesc) {
        this.pkgdesc = pkgdesc;
    }

    /**
     * ���ܣ���ñ�ע
     */
    public String getRemark() {
        return this.remark;
    }
    
    /**
     * ���ܣ����ñ�ע
     * @param remark
     */ 
    public void setRemark(String remark) {
        this.remark = remark;
    }
}