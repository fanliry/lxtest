package com.wms3.bms.standard.entity.base;


/**
 * ������������ά��
 * @author hug
 *
 */
public class BaseBarcode  implements java.io.Serializable {

	private static final long serialVersionUID = 4663743488580123532L;
	private String barcode;	//����
     private String isused;	//�Ƿ�ʹ��


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
     * ���ܣ��������
     */
    public String getBarcode() {
        return this.barcode;
    }
    /**
     * ���ܣ���������
     * @param barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    /**
     * ���ܣ�����Ƿ�ʹ��
     * @return
     */
    public String getIsused() {
        return this.isused;
    }
    /**
     * ���ܣ������Ƿ�ʹ��
     * @param isused
     */
    public void setIsused(String isused) {
        this.isused = isused;
    }
 
}