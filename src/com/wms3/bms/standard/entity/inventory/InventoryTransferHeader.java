package com.wms3.bms.standard.entity.inventory;

/**
 * 
 * 描述: 库存转移单
 * @since WMS 3.0
 */
public class InventoryTransferHeader  implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5523017946735375971L;
	
	private String transferid;		//库存转移单编号
	private String status;			//状态 0：创建状态 1：完成状态
	private String doctype;			//库存转移单类型
	private String reasoncode;		//转移原因
	private String reasondiscr;		//原因描述
	private String customerid;		//客户ID
	private String createtime;		//创建时间
	private String transfertime;	//转移时间
	private String createmanid;		//创建人
	private String remark;			//备注
	


     
	 //派生属性
	 private String customername;	//客户名称
	 private String doctypeName;			//库存转移单类型
    

	// Constructors
    /** default constructor */
    public InventoryTransferHeader() {
    }

    
    // Property accessors
	/**
	 * 功能：获得创建人
	 */
	public String getCreatemanid() {
		return createmanid;
	}


	/**
	 * 功能：设置创建人
	 * @param createmanid
	 */
	public void setCreatemanid(String createmanid) {
		this.createmanid = createmanid;
	}


	/**
	 * 功能：获得创建时间
	 */
	public String getCreatetime() {
		return createtime;
	}


	/**
	 * 功能：设置创建时间
	 * @param createtime
	 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}


	/**
	 * 功能：获得客户ID
	 */
	public String getCustomerid() {
		return customerid;
	}


	/**
	 * 功能：设置客户ID
	 * @param customerid
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}


	/**
	 * 功能：获得客户名称
	 */
	public String getCustomername() {
		return customername;
	}


	/**
	 * 功能：设置客户名称
	 * @param customername
	 */
	public void setCustomername(String customername) {
		this.customername = customername;
	}


	/**
	 * 功能：获得转移原因
	 */
	public String getReasoncode() {
		return reasoncode;
	}


	/**
	 * 功能：设置转移原因
	 * @param reasoncode
	 */
	public void setReasoncode(String reasoncode) {
		this.reasoncode = reasoncode;
	}


	/**
	 * 功能：获得原因描述
	 */
	public String getReasondiscr() {
		return reasondiscr;
	}


	/**
	 * 功能：设置原因描述
	 * @param reasondiscr
	 */
	public void setReasondiscr(String reasondiscr) {
		this.reasondiscr = reasondiscr;
	}


	/**
	 * 功能：获得备注
	 */
	public String getRemark() {
		return remark;
	}


	/**
	 * 功能：设置备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
	 * 功能：获得状态
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * 功能：设置状态
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * 功能：获得库存转移单类型
	 */
	public String getDoctype() {
		return doctype;
	}


	/**
	 * 功能：设置库存转移单类型
	 * @param doctype
	 */
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}


	/**
	 * 功能：获得库存转移单编号
	 */
	public String getTransferid() {
		return transferid;
	}


	/**
	 * 功能：设置库存转移单编号
	 * @param transferid
	 */
	public void setTransferid(String transferid) {
		this.transferid = transferid;
	}


	/**
	 * 功能：获得转移时间
	 */
	public String getTransfertime() {
		return transfertime;
	}


	/**
	 * 功能：设置转移时间
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