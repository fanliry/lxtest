package com.wms3.bms.standard.entity.rule.copy;



/**
 * 描述：补货规则表
 * @author hug
 */
public class RuleReplenish implements java.io.Serializable {


	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 123041264676420237L;

    // Fields   
	private String replenishid;			//补货规则代码
	private String warehouseid;			//所属仓库ID
	private String descr;				//描述
	private String ruleconfigid;		//规则配置ID
	private double ea_lowerlimit;		//件拣下限
	private double ea_uplimit;			//件拣上限
	private double ea_minreplenishqty;	//件拣最小补货数
	private double cs_lowerlimit;		//箱拣下限
	private double cs_uplimit;			//箱拣上限
	private double cs_minreplenishqty;	//箱拣最小补货数
	private double ot_lowerlimit;		//箱件拣下限
	private double ot_uplimit;			//箱件拣上限
	private double ot_minreplenishqty;	//箱件拣最小补货数
    private String remark;				//备注
    
    // 派生属性
    private String warehousename;		//所属仓库名
    private String ruleconfigname;		//规则配置名称

    // Constructors

    /** default constructor */
    public RuleReplenish() {
    }

   
    // Property accessors

	/**
	 * 功能：获得箱拣下限
	 */
	public double getCs_lowerlimit() {
		return cs_lowerlimit;
	}


	/**
	 * 功能：设置箱拣下限
	 * @param cs_lowerlimit
	 */
	public void setCs_lowerlimit(double cs_lowerlimit) {
		this.cs_lowerlimit = cs_lowerlimit;
	}


	/**
	 * 功能：获得箱拣最小补货数
	 */
	public double getCs_minreplenishqty() {
		return cs_minreplenishqty;
	}


	/**
	 * 功能：设置箱拣最小补货数
	 * @param cs_minreplenishqty
	 */
	public void setCs_minreplenishqty(double cs_minreplenishqty) {
		this.cs_minreplenishqty = cs_minreplenishqty;
	}


	/**
	 * 功能：获得箱拣上限
	 */
	public double getCs_uplimit() {
		return cs_uplimit;
	}


	/**
	 * 功能：设置箱拣上限
	 * @param cs_uplimit
	 */
	public void setCs_uplimit(double cs_uplimit) {
		this.cs_uplimit = cs_uplimit;
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
	 * 功能：获得件拣下限
	 */
	public double getEa_lowerlimit() {
		return ea_lowerlimit;
	}


	/**
	 * 功能：设置件拣下限
	 * @param ea_lowerlimit
	 */
	public void setEa_lowerlimit(double ea_lowerlimit) {
		this.ea_lowerlimit = ea_lowerlimit;
	}


	/**
	 * 功能：获得件拣最小补货数
	 */
	public double getEa_minreplenishqty() {
		return ea_minreplenishqty;
	}


	/**
	 * 功能：设置件拣最小补货数
	 * @param ea_minreplenishqty
	 */
	public void setEa_minreplenishqty(double ea_minreplenishqty) {
		this.ea_minreplenishqty = ea_minreplenishqty;
	}


	/**
	 * 功能：获得件拣上限
	 */
	public double getEa_uplimit() {
		return ea_uplimit;
	}


	/**
	 * 功能：设置件拣上限
	 * @param ea_uplimit
	 */
	public void setEa_uplimit(double ea_uplimit) {
		this.ea_uplimit = ea_uplimit;
	}


	/**
	 * 功能：获得箱件拣下限
	 */
	public double getOt_lowerlimit() {
		return ot_lowerlimit;
	}


	/**
	 * 功能：设置箱件拣下限
	 * @param ot_lowerlimit
	 */
	public void setOt_lowerlimit(double ot_lowerlimit) {
		this.ot_lowerlimit = ot_lowerlimit;
	}


	/**
	 * 功能：获得箱件拣最小补货数
	 */
	public double getOt_minreplenishqty() {
		return ot_minreplenishqty;
	}


	/**
	 * 功能：设置箱件拣最小补货数
	 * @param ot_minreplenishqty
	 */
	public void setOt_minreplenishqty(double ot_minreplenishqty) {
		this.ot_minreplenishqty = ot_minreplenishqty;
	}


	/**
	 * 功能：获得箱件拣上限
	 */
	public double getOt_uplimit() {
		return ot_uplimit;
	}


	/**
	 * 功能：设置箱件拣上限
	 * @param ot_uplimit
	 */
	public void setOt_uplimit(double ot_uplimit) {
		this.ot_uplimit = ot_uplimit;
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
	 * 功能：获得补货规则代码
	 */
	public String getReplenishid() {
		return replenishid;
	}


	/**
	 * 功能：设置补货规则代码
	 * @param replenishid
	 */
	public void setReplenishid(String replenishid) {
		this.replenishid = replenishid;
	}


	/**
	 * 功能：获得规则配置ID
	 */
	public String getRuleconfigid() {
		return ruleconfigid;
	}


	/**
	 * 功能：设置规则配置ID
	 * @param ruleconfigid
	 */
	public void setRuleconfigid(String ruleconfigid) {
		this.ruleconfigid = ruleconfigid;
	}


	/**
	 * 功能：获得规则配置名称
	 */
	public String getRuleconfigname() {
		return ruleconfigname;
	}


	/**
	 * 功能：设置规则配置名称
	 * @param ruleconfigname
	 */
	public void setRuleconfigname(String ruleconfigname) {
		this.ruleconfigname = ruleconfigname;
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