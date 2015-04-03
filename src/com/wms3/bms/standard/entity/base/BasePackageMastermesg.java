package com.wms3.bms.standard.entity.base;



/**
 * ��������װ����Ϣ��
 * @author hug
 */
public class BasePackageMastermesg  implements java.io.Serializable {

     /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -476604141423683384L;
	
	
    // Fields    
	private String packmasterid;	// ��װ����ϢID
	private String packid;			// ��װID
    private String pkgunit;			// ��װ��λ(EA-����λ,IP-�ڰ�װ,CS-��,PL-����,OT-����)
    private Integer qty;			// ��С��λ����
    private String pkgunitdesc;		// ��װ��λ����
    private String inused;			// ���ʹ�� Y/N
    private String outused;			// ����ʹ�� Y/N
    private String csused;			// ��λʹ��
    private String remark;			// ��ע
    
    private double length;          // ��
    private double width;           // ��
    private double height;          // ��
    private double weight;          // ����
    private double volume;          // ���
    private double palletlength;    // ���̳�
    private double palletwidth;     // ���̿�
    private double palletheight;    // ���̸�
    private double palletti;        // ����TI
    private double pallethi;        // ����HI

    //��������
    private String csusedname;		// ��λʹ������
    
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
     * ���ܣ���ð�װ����ϢID
     */
    public String getPackmasterid() {
        return this.packmasterid;
    }
    
    /**
     * ���ܣ����ð�װ����ϢID
     * @param packmasterid
     */ 
    public void setPackmasterid(String packmasterid) {
        this.packmasterid = packmasterid;
    }
    
    /**
     * ���ܣ���ð�װID
     */
    public String getPackid() {
        return this.packid;
    }
    
    /**
     * ���ܣ����ð�װID
     * @param packid
     */ 
    public void setPackid(String packid) {
        this.packid = packid;
    }

    /**
     * ���ܣ���ð�װ��λ
     */
    public String getPkgunit() {
        return this.pkgunit;
    }
    
    /**
     * ���ܣ����ð�װ��λ
     * @param pkgunit
     */ 
    public void setPkgunit(String pkgunit) {
        this.pkgunit = pkgunit;
    }

    /**
     * ���ܣ������С��λ����
     */
    public Integer getQty() {
        return this.qty;
    }
    
    /**
     * ���ܣ�������С��λ����
     * @param qty
     */ 
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    /**
     * ���ܣ���ð�װ��λ����
     */
    public String getPkgunitdesc() {
        return this.pkgunitdesc;
    }
    
    /**
     * ���ܣ����ð�װ��λ����
     * @param pkgunitdesc
     */ 
    public void setPkgunitdesc(String pkgunitdesc) {
        this.pkgunitdesc = pkgunitdesc;
    }

    /**
     * ���ܣ�������ʹ��
     */
    public String getInused() {
        return this.inused;
    }
    
    /**
     * ���ܣ��������ʹ��
     * @param inused
     */ 
    public void setInused(String inused) {
        this.inused = inused;
    }

    /**
     * ���ܣ���ó���ʹ��
     */
    public String getOutused() {
        return this.outused;
    }
    
    /**
     * ���ܣ����ó���ʹ��
     * @param outused
     */ 
    public void setOutused(String outused) {
        this.outused = outused;
    }

    /**
     * ���ܣ���ÿ�λʹ��
     */
    public String getCsused() {
        return this.csused;
    }
    
    /**
     * ���ܣ����ÿ�λʹ��
     * @param csused
     */ 
    public void setCsused(String csused) {
        this.csused = csused;
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
    

    /**
     * ���ܣ���ÿ�λʹ������
     */
    public String getCsusedname() {
        return this.csusedname;
    }
    
    /**
     * ���ܣ����ÿ�λʹ������
     * @param csusedname
     */ 
    public void setCsusedname(String csusedname) {
        this.csusedname = csusedname;
    }
    
    /**
     * ���ܣ���ó�
     */
    public double getLength() {
        return this.length;
    }
    
    /**
     * ���ܣ����ó�
     * @param length
     */ 
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * ���ܣ���ÿ�
     */
    public double getWidth() {
        return this.width;
    }
    
    /**
     * ���ܣ����ÿ�
     * @param width
     */ 
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * ���ܣ���ø�
     */
    public double getHeight() {
        return this.height;
    }
    
    /**
     * ���ܣ����ø�
     * @param height
     */ 
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * ���ܣ��������
     */
    public double getWeight() {
        return this.weight;
    }
    
    /**
     * ���ܣ���������
     * @param weight
     */ 
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * ���ܣ�������
     */
    public double getVolume() {
        return this.volume;
    }
    
    /**
     * ���ܣ��������
     * @param volume
     */ 
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * ���ܣ�������̳�
     */
    public double getPalletlength() {
        return this.palletlength;
    }
    
    /**
     * ���ܣ��������̳�
     * @param palletlength
     */ 
    public void setPalletlength(double palletlength) {
        this.palletlength = palletlength;
    }

    /**
     * ���ܣ�������̿�
     */
    public double getPalletwidth() {
        return this.palletwidth;
    }
    
    /**
     * ���ܣ��������̿�
     * @param palletwidth
     */ 
    public void setPalletwidth(double palletwidth) {
        this.palletwidth = palletwidth;
    }

    /**
     * ���ܣ�������̸�
     */
    public double getPalletheight() {
        return this.palletheight;
    }
    
    /**
     * ���ܣ��������̸�
     * @param palletheight
     */ 
    public void setPalletheight(double palletheight) {
        this.palletheight = palletheight;
    }

    /**
     * ���ܣ��������TI
     */
    public double getPalletti() {
        return this.palletti;
    }
    
    /**
     * ���ܣ���������TI
     * @param palletti
     */ 
    public void setPalletti(double palletti) {
        this.palletti = palletti;
    }

    /**
     * ���ܣ��������HI
     */
    public double getPallethi() {
        return this.pallethi;
    }
    
    /**
     * ���ܣ���������HI
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