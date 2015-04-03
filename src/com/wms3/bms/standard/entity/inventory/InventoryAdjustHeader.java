package com.wms3.bms.standard.entity.inventory;

/**
 * 
 * ����: ��������
 * @since WMS 3.0
 */
public class InventoryAdjustHeader  implements java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6038598254721034915L;
	
	private String adjustid;		//��������
	private String status;			//״̬ 0������״̬ 1�����״̬
	private String reasoncode;		//����ԭ��
	private String reasondiscr;		//ԭ������
	private String warehouseid;		//�ֿ�ID
	private String createtime;		//����ʱ��
	private String adjusttime;		//����ʱ��
	private String createmanid;		//������
	private String remark;			//��ע

     
	 //��������
	 private String warehousename;	//�ֿ�����
    
    /**
     * ���ܣ���òֿ�����
     * @return
     */
	public String getWarehousename() {
		return warehousename;
	}
	 /**
     * ���ܣ����òֿ�����
     * @return
     */

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}


	// Constructors
    /** default constructor */
    public InventoryAdjustHeader() {
    }

    
    // Property accessors
	/**
	 * ���ܣ���õ�������
	 */
	public String getAdjustid() {
		return adjustid;
	}


	/**
	 * ���ܣ����õ�������
	 * @param adjustid
	 */
	public void setAdjustid(String adjustid) {
		this.adjustid = adjustid;
	}


	/**
	 * ���ܣ���õ���ʱ��
	 */
	public String getAdjusttime() {
		return adjusttime;
	}


	/**
	 * ���ܣ����õ���ʱ��
	 * @param adjusttime
	 */
	public void setAdjusttime(String adjusttime) {
		this.adjusttime = adjusttime;
	}


	/**
	 * ���ܣ���ô�����
	 */
	public String getCreatemanid() {
		return createmanid;
	}


	/**
	 * ���ܣ����ô�����
	 * @param createmanid
	 */
	public void setCreatemanid(String createmanid) {
		this.createmanid = createmanid;
	}


	/**
	 * ���ܣ���ô���ʱ��
	 */
	public String getCreatetime() {
		return createtime;
	}


	/**
	 * ���ܣ����ô���ʱ��
	 * @param createtime
	 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}


	/**
	 * ���ܣ���ÿͻ�ID(��Ϊ���id)
	 */
	public String getWarehouseid() {
		return warehouseid;
	}


	/**
	 * ���ܣ����ÿͻ�ID
	 * @param customerid
	 */
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}


	/**
	 * ���ܣ���õ���ԭ��
	 */
	public String getReasoncode() {
		return reasoncode;
	}


	/**
	 * ���ܣ����õ���ԭ��
	 * @param reasoncode
	 */
	public void setReasoncode(String reasoncode) {
		this.reasoncode = reasoncode;
	}


	/**
	 * ���ܣ����ԭ������
	 */
	public String getReasondiscr() {
		return reasondiscr;
	}


	/**
	 * ���ܣ�����ԭ������
	 * @param reasondiscr
	 */
	public void setReasondiscr(String reasondiscr) {
		this.reasondiscr = reasondiscr;
	}


	/**
	 * ���ܣ���ñ�ע
	 */
	public String getRemark() {
		return remark;
	}


	/**
	 * ���ܣ����ñ�ע
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
	 * ���ܣ����
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * ���ܣ�����
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

    
    
    
}