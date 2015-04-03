package com.wms3.bms.standard.entity.inventory.lxyy;

/**
 * 
 * 描述: 库存调整单
 * @since WMS 3.0
 */
public class InventoryAdjustHeader  implements java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6038598254721034915L;
	
	private String adjustid;		//调整单号
	private String status;			//状态 0：创建状态 1：审核状态 3：完成状态
	private String auditmanid;     	//审核人Id
    private String auditdate;      	//审核时间
    private String adjusttype;      //调整类型（1：出库异常调整，2：入库异常调整:3：盘点调整，4：库存信息调整）
	private String reasoncode;		//调整原因
	private String reasondiscr;		//原因描述
	private String warehouseid;    	//仓库id 
	private String createtime;		//创建时间
	private String adjusttime;		//调整时间
	private String createmanid;		//创建人
	private String remark;			//备注

     
	 //派生属性
	 private String warehousename;	//仓库名
	 private String auditmanname;	//审核人名
	 private String createmanname;	//制单人名
	 private String adjusttypename;	//调整类型名
	 private String adjustreasonname;	//调整原因名称
	 private String adjuststatusname;	//调整原因名称
	 
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