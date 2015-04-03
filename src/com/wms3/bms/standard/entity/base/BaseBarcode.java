package com.wms3.bms.standard.entity.base;


/**
 * 描述：条形码维护
 * @author hug
 *
 */
public class BaseBarcode  implements java.io.Serializable {

	private static final long serialVersionUID = 4663743488580123532L;
	private String barcode;	//条码
     private String isused;	//是否使用


    // Constructors

    /** default constructor */
    public BaseBarcode() {
    }

    
    /** full constructor */
    public BaseBarcode(String barcode, String isused) {
    	this.barcode = barcode;
        this.isused = isused;
    }

   
    // Property accessors
    /**
     * 功能：获得条码
     */
    public String getBarcode() {
        return this.barcode;
    }
    /**
     * 功能：设置条码
     * @param barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    /**
     * 功能：获得是否使用
     * @return
     */
    public String getIsused() {
        return this.isused;
    }
    /**
     * 功能：设置是否使用
     * @param isused
     */
    public void setIsused(String isused) {
        this.isused = isused;
    }
 
}