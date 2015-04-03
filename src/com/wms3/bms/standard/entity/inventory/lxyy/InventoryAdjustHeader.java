package com.wms3.bms.standard.entity.inventory.lxyy;

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
	private String status;			//״̬ 0������״̬ 1�����״̬ 3�����״̬
	private String auditmanid;     	//�����Id
    private String auditdate;      	//���ʱ��
    private String adjusttype;      //�������ͣ�1�������쳣������2������쳣����:3���̵������4�������Ϣ������
	private String reasoncode;		//����ԭ��
	private String reasondiscr;		//ԭ������
	private String warehouseid;    	//�ֿ�id 
	private String createtime;		//����ʱ��
	private String adjusttime;		//����ʱ��
	private String createmanid;		//������
	private String remark;			//��ע

     
	 //��������
	 private String warehousename;	//�ֿ���
	 private String auditmanname;	//�������
	 private String createmanname;	//�Ƶ�����
	 private String adjusttypename;	//����������
	 private String adjustreasonname;	//����ԭ������
	 private String adjuststatusname;	//����ԭ������
	 
	 public String getAdjuststatusname() {
		return adjuststatusname;
	}
	public void setAdjuststatusname(String adjuststatusname) {
		this.adjuststatusname = adjuststatusname;
	}
	public String getAdjusttypename() {
		return adjusttypename;
	}
	public void setAdjusttypename(String adjusttypename) {
		this.adjusttypename = adjusttypename;
	}
	public String getAdjustreasonname() {
		return adjustreasonname;
	}
	public void setAdjustreasonname(String adjustreasonname) {
		this.adjustreasonname = adjustreasonname;
	}
	public String getAdjustid() {
		return adjustid;
	}
	public void setAdjustid(String adjustid) {
		this.adjustid = adjustid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuditmanid() {
		return auditmanid;
	}
	public void setAuditmanid(String auditmanid) {
		this.auditmanid = auditmanid;
	}
	public String getAuditdate() {
		return auditdate;
	}
	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}
	public String getAdjusttype() {
		return adjusttype;
	}
	public void setAdjusttype(String adjusttype) {
		this.adjusttype = adjusttype;
	}
	public String getReasoncode() {
		return reasoncode;
	}
	public void setReasoncode(String reasoncode) {
		this.reasoncode = reasoncode;
	}
	public String getReasondiscr() {
		return reasondiscr;
	}
	public void setReasondiscr(String reasondiscr) {
		this.reasondiscr = reasondiscr;
	}
	public String getWarehouseid() {
		return warehouseid;
	}
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getAdjusttime() {
		return adjusttime;
	}
	public void setAdjusttime(String adjusttime) {
		this.adjusttime = adjusttime;
	}
	public String getCreatemanid() {
		return createmanid;
	}
	public void setCreatemanid(String createmanid) {
		this.createmanid = createmanid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWarehousename() {
		return warehousename;
	}
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	public String getAuditmanname() {
		return auditmanname;
	}
	public void setAuditmanname(String auditmanname) {
		this.auditmanname = auditmanname;
	}
	public String getCreatemanname() {
		return createmanname;
	}
	public void setCreatemanname(String createmanname) {
		this.createmanname = createmanname;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
    
}