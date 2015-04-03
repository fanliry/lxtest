package com.wms3.bms.standard.entity.rule;

/**
 * 描述：分配规则详细表
 * @author hug
 */
public class RuleAllocationDetail implements java.io.Serializable{

    /**
	 * serialVersionUID
	 */
    private static final long serialVersionUID = 6677430147370893672L;
    
    //Fields    
    private String allocationdetailid;	//分配规则详细ID
    private String allocationid;  		//分配规则ID
	private	int	sort;					//优先顺位
	private String ruleconfigid;		//规则配置ID
    private String enableflag;			//是否生效
    private String clearloc_flag;      	//是否清仓 N-否,Y-是
    private String apart_flag;			//是否拆零 N-否,Y-是
    
    protected String over_flag;    		//是否拣货位超量分配 N-否,Y-是
    protected String auto_flag;    		//是否自动产生补货任务 N-否,Y-是
    protected String bulkpick_flag;    	//是否存储位拣选 N-否,Y-是
    protected String part_flag;    		//是否拆包 N-否,Y-是
    protected String remark;    		//备注
    
    private String tozone;				//目标库区
    private String tolocationid;		//目标库位
    private String toalleys;			//目标巷道，可多条
    private String part_allocation_flag;//是否允许部分分配 N-否,Y-是
    private String unit_flag;			//计量单位 1.数量 2.毛重 3.净重 4.体积
    
    //派生属性
    private String ruleconfigname;		//规则配置名称
    private String tozonename;			//目标库区名称
    private String tolocationname;		//目标库位名称
    private String unitflagname;		//计量单位 1.数量 2.毛重 3.净重 4.体积
    
    //Constructors
    public RuleAllocationDetail(){}


    //Property accessors
    
	/**
	 * 功能：获得分配规则详细ID
	 */
	public String getAllocationdetailid() {
		return allocationdetailid;
	}


	/**
	 * 功能：设置分配规则详细ID
	 * @param allocationdetailid
	 */
	public void setAllocationdetailid(String allocationdetailid) {
		this.allocationdetailid = allocationdetailid;
	}


	/**
	 * 功能：获得分配规则ID
	 */
	public String getAllocationid() {
		return allocationid;
	}


	/**
	 * 功能：设置分配规则ID
	 * @param allocationid
	 */
	public void setAllocationid(String allocationid) {
		this.allocationid = allocationid;
	}


	/**
	 * 功能：获得是否拆零
	 */
	public String getApart_flag() {
		return apart_flag;
	}


	/**
	 * 功能：设置是否拆零
	 * @param apart_flag
	 */
	public void setApart_flag(String apart_flag) {
		this.apart_flag = apart_flag;
	}


	/**
	 * 功能：获得是否自动产生补货任务
	 */
	public String getAuto_flag() {
		return auto_flag;
	}


	/**
	 * 功能：设置是否自动产生补货任务
	 * @param auto_flag
	 */
	public void setAuto_flag(String auto_flag) {
		this.auto_flag = auto_flag;
	}


	/**
	 * 功能：获得是否存储位拣选
	 */
	public String getBulkpick_flag() {
		return bulkpick_flag;
	}


	/**
	 * 功能：设置是否存储位拣选
	 * @param bulkpick_flag
	 */
	public void setBulkpick_flag(String bulkpick_flag) {
		this.bulkpick_flag = bulkpick_flag;
	}


	/**
	 * 功能：获得是否清仓
	 */
	public String getClearloc_flag() {
		return clearloc_flag;
	}


	/**
	 * 功能：设置是否清仓
	 * @param clearloc_flag
	 */
	public void setClearloc_flag(String clearloc_flag) {
		this.clearloc_flag = clearloc_flag;
	}


	/**
	 * 功能：获得是否生效
	 */
	public String getEnableflag() {
		return enableflag;
	}


	/**
	 * 功能：设置是否生效
	 * @param enableflag
	 */
	public void setEnableflag(String enableflag) {
		this.enableflag = enableflag;
	}


	/**
	 * 功能：获得是否拣货位超量分配
	 */
	public String getOver_flag() {
		return over_flag;
	}


	/**
	 * 功能：设置是否拣货位超量分配
	 * @param over_flag
	 */
	public void setOver_flag(String over_flag) {
		this.over_flag = over_flag;
	}


	/**
	 * 功能：获得是否拆包
	 */
	public String getPart_flag() {
		return part_flag;
	}


	/**
	 * 功能：设置是否拆包
	 * @param part_flag
	 */
	public void setPart_flag(String part_flag) {
		this.part_flag = part_flag;
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
	 * 功能：获得优先顺位
	 */
	public int getSort() {
		return sort;
	}


	/**
	 * 功能：设置优先顺位
	 * @param sort
	 */
	public void setSort(int sort) {
		this.sort = sort;
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
	 * 功能：获得是否允许部分分配 N-否,Y-是
	 */
	public String getPart_allocation_flag() {
		return part_allocation_flag;
	}


	/**
	 * 功能：设置是否允许部分分配 N-否,Y-是
	 * @param part_allocation_flag
	 */
	public void setPart_allocation_flag(String part_allocation_flag) {
		this.part_allocation_flag = part_allocation_flag;
	}


	/**
	 * 功能：获得目标巷道，可多条
	 */
	public String getToalleys() {
		return toalleys;
	}


	/**
	 * 功能：设置目标巷道，可多条
	 * @param toalleys
	 */
	public void setToalleys(String toalleys) {
		this.toalleys = toalleys;
	}


	/**
	 * 功能：获得目标库位
	 */
	public String getTolocationid() {
		return tolocationid;
	}


	/**
	 * 功能：设置目标库位
	 * @param tolocationid
	 */
	public void setTolocationid(String tolocationid) {
		this.tolocationid = tolocationid;
	}


	/**
	 * 功能：获得目标库位名称
	 */
	public String getTolocationname() {
		return tolocationname;
	}


	/**
	 * 功能：设置目标库位名称
	 * @param tolocationname
	 */
	public void setTolocationname(String tolocationname) {
		this.tolocationname = tolocationname;
	}


	/**
	 * 功能：获得目标库区
	 */
	public String getTozone() {
		return tozone;
	}


	/**
	 * 功能：设置目标库区
	 * @param tozone
	 */
	public void setTozone(String tozone) {
		this.tozone = tozone;
	}


	/**
	 * 功能：获得目标库区名称
	 */
	public String getTozonename() {
		return tozonename;
	}


	/**
	 * 功能：设置目标库区名称
	 * @param tozonename
	 */
	public void setTozonename(String tozonename) {
		this.tozonename = tozonename;
	}


	/**
	 * 功能：获得计量单位 1.数量 2.毛重 3.净重 4.体积
	 */
	public String getUnit_flag() {
		return unit_flag;
	}


	/**
	 * 功能：设置计量单位 1.数量 2.毛重 3.净重 4.体积
	 * @param unit_flag
	 */
	public void setUnit_flag(String unit_flag) {
		this.unit_flag = unit_flag;
	}


	/**
	 * 功能：获得计量单位 1.数量 2.毛重 3.净重 4.体积
	 */
	public String getUnitflagname() {
		return unitflagname;
	}


	/**
	 * 功能：设置计量单位 1.数量 2.毛重 3.净重 4.体积
	 * @param unitflagname
	 */
	public void setUnitflagname(String unitflagname) {
		this.unitflagname = unitflagname;
	}
}
