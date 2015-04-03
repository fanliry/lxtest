package com.wms3.bms.standard.entity.inventory;

/**
 * 
 * ����: ���ת�Ƶ�
 * @since WMS 3.0
 */
public class InventoryTransferHeader  implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5523017946735375971L;
	
	private String transferid;		//���ת�Ƶ����
	private String status;			//״̬ 0������״̬ 1�����״̬
	private String doctype;			//���ת�Ƶ�����
	private String reasoncode;		//ת��ԭ��
	private String reasondiscr;		//ԭ������
	private String customerid;		//�ͻ�ID
	private String createtime;		//����ʱ��
	private String transfertime;	//ת��ʱ��
	private String createmanid;		//������
	private String remark;			//��ע
	


     
	 //��������
	 private String customername;	//�ͻ�����
	 private String doctypeName;			//���ת�Ƶ�����
    

	// Constructors
    /** default constructor */
    public InventoryTransferHeader() {
    }

    
    // Property accessors
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
	 * ���ܣ���ÿͻ�ID
	 */
	public String getCustomerid() {
		return customerid;
	}


	/**
	 * ���ܣ����ÿͻ�ID
	 * @param customerid
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}


	/**
	 * ���ܣ���ÿͻ�����
	 */
	public String getCustomername() {
		return customername;
	}


	/**
	 * ���ܣ����ÿͻ�����
	 * @param customername
	 */
	public void setCustomername(String customername) {
		this.customername = customername;
	}


	/**
	 * ���ܣ����ת��ԭ��
	 */
	public String getReasoncode() {
		return reasoncode;
	}


	/**
	 * ���ܣ�����ת��ԭ��
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
	 * ���ܣ����״̬
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * ���ܣ�����״̬
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * ���ܣ���ÿ��ת�Ƶ�����
	 */
	public String getDoctype() {
		return doctype;
	}


	/**
	 * ���ܣ����ÿ��ת�Ƶ�����
	 * @param doctype
	 */
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}


	/**
	 * ���ܣ���ÿ��ת�Ƶ����
	 */
	public String getTransferid() {
		return transferid;
	}


	/**
	 * ���ܣ����ÿ��ת�Ƶ����
	 * @param transferid
	 */
	public void setTransferid(String transferid) {
		this.transferid = transferid;
	}


	/**
	 * ���ܣ����ת��ʱ��
	 */
	public String getTransfertime() {
		return transfertime;
	}


	/**
	 * ���ܣ�����ת��ʱ��
	 * @param transfertime
	 */
	public void setTransfertime(String transfertime) {
		this.transfertime = transfertime;
	}


	public String getDoctypeName() {
		return doctypeName;
	}


	public void setDoctypeName(String doctypeName) {
		this.doctypeName = doctypeName;
	}
    
}