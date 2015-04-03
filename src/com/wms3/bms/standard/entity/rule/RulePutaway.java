package com.wms3.bms.standard.entity.rule;



/**
 * 描述：上架规则表
 * @author hug
 */
public class RulePutaway  implements java.io.Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5104908860464197383L;
	
	
	// Fields    
	private String putawayid;			// 上架规则ID
    private String warehouseid;			// 所属仓库ID
    private String descr;				// 描述
    private String remark;				// 备注
    
    //派生属性
    private String warehousename;		// 所属仓库名

    // Constructors

    /** default constructor */
    public RulePutaway() {
    }

    
    // Property accessors
    
	/**
	 * 功能：获得上架规则ID
	 */
	public String getPutawayid() {
		return putawayid;
	}

	/**
	 * 功能：设置上架规则ID
	 * @param allocationid
	 */
	public void setPutawayid(String putawayid) {
		this.putawayid = putawayid;
	}

	/**
	 * 功能：获得描述
	 */
	public String getDescr() {
		return descr;
	}

	/**
	 * 功能：设置描述
	 * @param descr
	 */
	public void setDescr(String descr) {
		this.descr = descr;
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
	 * 功能：获得所属仓库ID
	 */
	public String getWarehouseid() {
		return warehouseid;
	}

	/**
	 * 功能：设置所属仓库ID
	 * @param warehouseid
	 */
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	
	/**
     * 功能：获得所属仓库名
     * @return
     */
	public String getWarehousename() {
		return warehousename;
	}
	
	/**
	 * 功能：设置所属仓库名
	 * @param warehousename
	 */
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
}