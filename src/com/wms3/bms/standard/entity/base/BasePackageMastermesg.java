package com.wms3.bms.standard.entity.base;



/**
 * 描述：包装主信息表
 * @author hug
 */
public class BasePackageMastermesg  implements java.io.Serializable {

     /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -476604141423683384L;
	
	
    // Fields    
	private String packmasterid;	// 包装主信息ID
	private String packid;			// 包装ID
    private String pkgunit;			// 包装单位(EA-主单位,IP-内包装,CS-箱,PL-托盘,OT-其它)
    private Integer qty;			// 最小单位数量
    private String pkgunitdesc;		// 包装单位描述
    private String inused;			// 入库使用 Y/N
    private String outused;			// 出库使用 Y/N
    private String csused;			// 库位使用
    private String remark;			// 备注
    
    private double length;          // 长
    private double width;           // 宽
    private double height;          // 高
    private double weight;          // 重量
    private double volume;          // 体积
    private double palletlength;    // 托盘长
    private double palletwidth;     // 托盘宽
    private double palletheight;    // 托盘高
    private double palletti;        // 托盘TI
    private double pallethi;        // 托盘HI

    //派生属性
    private String csusedname;		// 库位使用名称
    
    // Constructors

    /** default constructor */
    public BasePackageMastermesg() {
    }

    
    /** full constructor */
    public BasePackageMastermesg(String packid, String pkgunit, Integer qty, String pkgunitdesc, 
    		String inused, String outused, String csused, String remark) {
    	
        this.packid = packid;
        this.pkgunit = pkgunit;
        this.qty = qty;
        this.pkgunitdesc = pkgunitdesc;
        this.inused = inused;
        this.outused = outused;
        this.csused = csused;
        this.remark = remark;
    }

   
    // Property accessors
    /**
     * 功能：获得包装主信息ID
     */
    public String getPackmasterid() {
        return this.packmasterid;
    }
    
    /**
     * 功能：设置包装主信息ID
     * @param packmasterid
     */ 
    public void setPackmasterid(String packmasterid) {
        this.packmasterid = packmasterid;
    }
    
    /**
     * 功能：获得包装ID
     */
    public String getPackid() {
        return this.packid;
    }
    
    /**
     * 功能：设置包装ID
     * @param packid
     */ 
    public void setPackid(String packid) {
        this.packid = packid;
    }

    /**
     * 功能：获得包装单位
     */
    public String getPkgunit() {
        return this.pkgunit;
    }
    
    /**
     * 功能：设置包装单位
     * @param pkgunit
     */ 
    public void setPkgunit(String pkgunit) {
        this.pkgunit = pkgunit;
    }

    /**
     * 功能：获得最小单位数量
     */
    public Integer getQty() {
        return this.qty;
    }
    
    /**
     * 功能：设置最小单位数量
     * @param qty
     */ 
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    /**
     * 功能：获得包装单位描述
     */
    public String getPkgunitdesc() {
        return this.pkgunitdesc;
    }
    
    /**
     * 功能：设置包装单位描述
     * @param pkgunitdesc
     */ 
    public void setPkgunitdesc(String pkgunitdesc) {
        this.pkgunitdesc = pkgunitdesc;
    }

    /**
     * 功能：获得入库使用
     */
    public String getInused() {
        return this.inused;
    }
    
    /**
     * 功能：设置入库使用
     * @param inused
     */ 
    public void setInused(String inused) {
        this.inused = inused;
    }

    /**
     * 功能：获得出库使用
     */
    public String getOutused() {
        return this.outused;
    }
    
    /**
     * 功能：设置出库使用
     * @param outused
     */ 
    public void setOutused(String outused) {
        this.outused = outused;
    }

    /**
     * 功能：获得库位使用
     */
    public String getCsused() {
        return this.csused;
    }
    
    /**
     * 功能：设置库位使用
     * @param csused
     */ 
    public void setCsused(String csused) {
        this.csused = csused;
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
     * 功能：获得库位使用名称
     */
    public String getCsusedname() {
        return this.csusedname;
    }
    
    /**
     * 功能：设置库位使用名称
     * @param csusedname
     */ 
    public void setCsusedname(String csusedname) {
        this.csusedname = csusedname;
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
     * 功能：获得重量
     */
    public double getWeight() {
        return this.weight;
    }
    
    /**
     * 功能：设置重量
     * @param weight
     */ 
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * 功能：获得体积
     */
    public double getVolume() {
        return this.volume;
    }
    
    /**
     * 功能：设置体积
     * @param volume
     */ 
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * 功能：获得托盘长
     */
    public double getPalletlength() {
        return this.palletlength;
    }
    
    /**
     * 功能：设置托盘长
     * @param palletlength
     */ 
    public void setPalletlength(double palletlength) {
        this.palletlength = palletlength;
    }

    /**
     * 功能：获得托盘宽
     */
    public double getPalletwidth() {
        return this.palletwidth;
    }
    
    /**
     * 功能：设置托盘宽
     * @param palletwidth
     */ 
    public void setPalletwidth(double palletwidth) {
        this.palletwidth = palletwidth;
    }

    /**
     * 功能：获得托盘高
     */
    public double getPalletheight() {
        return this.palletheight;
    }
    
    /**
     * 功能：设置托盘高
     * @param palletheight
     */ 
    public void setPalletheight(double palletheight) {
        this.palletheight = palletheight;
    }

    /**
     * 功能：获得托盘TI
     */
    public double getPalletti() {
        return this.palletti;
    }
    
    /**
     * 功能：设置托盘TI
     * @param palletti
     */ 
    public void setPalletti(double palletti) {
        this.palletti = palletti;
    }

    /**
     * 功能：获得托盘HI
     */
    public double getPallethi() {
        return this.pallethi;
    }
    
    /**
     * 功能：设置托盘HI
     * @param pallethi
     */ 
    public void setPallethi(double pallethi) {
        this.pallethi = pallethi;
    }
    
    public void setBasePackageExtramesg(double length, double width, double height, double volume, double weight,  
            double palletlength, double palletwidth, double palletheight, double palletti, double pallethi) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.volume = volume;
        this.weight = weight;
        this.palletlength = palletlength;
        this.palletwidth = palletwidth;
        this.palletheight = palletheight;
        this.palletti = palletti;
        this.pallethi = pallethi;
    }
}