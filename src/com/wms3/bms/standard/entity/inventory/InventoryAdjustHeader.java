package com.wms3.bms.standard.entity.inventory;

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
	private String status;			//状态 0：创建状态 1：完成状态
	private String reasoncode;		//调整原因
	private String reasondiscr;		//原因描述
	private String warehouseid;		//仓库ID
	private String createtime;		//创建时间
	private String adjusttime;		//调整时间
	private String createmanid;		//创建人
	private String remark;			//备注

     
	 //派生属性
	 private String warehousename;	//仓库名称
    
    /**
     * 功能：获得仓库名称
     * @return
     */
	public String getWarehousename() {
		return warehousename;
	}
	 /**
     * 功能：设置仓库名称
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
	 * 功能：获得调整单号
	 */
	public String getAdjustid() {
		return adjustid;
	}


	/**
	 * 功能：设置调整单号
	 * @param adjustid
	 */
	public void setAdjustid(String adjustid) {
		this.adjustid = adjustid;
	}


	/**
	 * 功能：获得调整时间
	 */
	public String getAdjusttime() {
		return adjusttime;
	}


	/**
	 * 功能：设置调整时间
	 * @param adjusttime
	 */
	public void setAdjusttime(String adjusttime) {
		this.adjusttime = adjusttime;
	}


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
	 * 功能：获得客户ID(改为库存id)
	 */
	public String getWarehouseid() {
		return warehouseid;
	}


	/**
	 * 功能：设置客户ID
	 * @param customerid
	 */
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}


	/**
	 * 功能：获得调整原因
	 */
	public String getReasoncode() {
		return reasoncode;
	}


	/**
	 * 功能：设置调整原因
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
	 * 功能：获得
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * 功能：设置
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

    
    
    
}