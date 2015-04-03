package com.wms3.bms.standard.entity.rule;

/**
 * 描述：上架规则详细表
 * @author hug
 */
public class RulePutawayDetail implements java.io.Serializable{

    /**
	 * serialVersionUID
	 */
    private static final long serialVersionUID = 6677430147370893672L;
    
    //Fields    
    private String putawaydetailid;		//上架规则详细ID
    private String putawayid;  			//上架规则ID
	private	int	sort;					//优先顺位
	private String ruleconfigid;		//规则配置ID
    private String enableflag;			//是否生效
    
    private String tozone;				//目标库区
    private String tolocationid;		//目标库位
    
    private String loc_restrict;    	//库位限制
    
    private String loc_usage1;   		//库位使用1
    private String loc_usage2;   		//库位使用2
    private String loc_usage3;   		//库位使用3
    private String loc_usage4;   		//库位使用4
    private String loc_usage5;   		//库位使用5
    
    private String loc_handle1;   		//存储类型1
    private String loc_handle2;   		//存储类型2
    private String loc_handle3;   		//存储类型3
    private String loc_handle4;   		//存储类型4
    private String loc_handle5;   		//存储类型5
    
    private String lotid;    			//批次ID
	private String lotatt1;  			//批次属性1
	private String lotatt2;  			//批次属性2
	private String lotatt3;  			//批次属性3
	private String lotatt4;  			//批次属性4
 	private String lotatt5;  			//批次属性5
	private String lotatt6;  			//批次属性6
 	private String lotatt7;  			//批次属性7
	private String lotatt8;  			//批次属性8
 	private String lotatt9;  			//批次属性9
 	private String lotatt10;  			//批次属性10
 	private String lotatt11;  			//批次属性11
 	private String lotatt12;  			//批次属性12
 	
    private String remark;    			//备注
    
    // 派生属性
    private String ruleconfigname;		//规则配置名称
    private String tozonename;			//目标库位名称
    private String tolocationname;		//目标库位名称
    private String loc_restrictname;	//库位限制名称
    private String loc_usagename1;		//库位使用1名称
    private String loc_handlename1;		//存储类型1名称
    

	//Constructors
    public RulePutawayDetail(){}


    //Property accessors

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
	 * 功能：获得存储类型1
	 */
	public String getLoc_handle1() {
		return loc_handle1;
	}


	/**
	 * 功能：设置存储类型1
	 * @param loc_handle1
	 */
	public void setLoc_handle1(String loc_handle1) {
		this.loc_handle1 = loc_handle1;
	}


	/**
	 * 功能：获得存储类型2
	 */
	public String getLoc_handle2() {
		return loc_handle2;
	}


	/**
	 * 功能：设置存储类型2
	 * @param loc_handle2
	 */
	public void setLoc_handle2(String loc_handle2) {
		this.loc_handle2 = loc_handle2;
	}


	/**
	 * 功能：获得存储类型3
	 */
	public String getLoc_handle3() {
		return loc_handle3;
	}


	/**
	 * 功能：设置存储类型3
	 * @param loc_handle3
	 */
	public void setLoc_handle3(String loc_handle3) {
		this.loc_handle3 = loc_handle3;
	}


	/**
	 * 功能：获得存储类型4
	 */
	public String getLoc_handle4() {
		return loc_handle4;
	}


	/**
	 * 功能：设置存储类型4
	 * @param loc_handle4
	 */
	public void setLoc_handle4(String loc_handle4) {
		this.loc_handle4 = loc_handle4;
	}


	/**
	 * 功能：获得存储类型5
	 */
	public String getLoc_handle5() {
		return loc_handle5;
	}


	/**
	 * 功能：设置存储类型5
	 * @param loc_handle5
	 */
	public void setLoc_handle5(String loc_handle5) {
		this.loc_handle5 = loc_handle5;
	}


	/**
	 * 功能：获得库位限制
	 */
	public String getLoc_restrict() {
		return loc_restrict;
	}


	/**
	 * 功能：设置库位限制
	 * @param loc_restrict
	 */
	public void setLoc_restrict(String loc_restrict) {
		this.loc_restrict = loc_restrict;
	}


	/**
	 * 功能：获得库位使用1
	 */
	public String getLoc_usage1() {
		return loc_usage1;
	}


	/**
	 * 功能：设置库位使用1
	 * @param loc_usage1
	 */
	public void setLoc_usage1(String loc_usage1) {
		this.loc_usage1 = loc_usage1;
	}


	/**
	 * 功能：获得库位使用2
	 */
	public String getLoc_usage2() {
		return loc_usage2;
	}


	/**
	 * 功能：设置库位使用2
	 * @param loc_usage2
	 */
	public void setLoc_usage2(String loc_usage2) {
		this.loc_usage2 = loc_usage2;
	}


	/**
	 * 功能：获得库位使用3
	 */
	public String getLoc_usage3() {
		return loc_usage3;
	}


	/**
	 * 功能：设置库位使用3
	 * @param loc_usage3
	 */
	public void setLoc_usage3(String loc_usage3) {
		this.loc_usage3 = loc_usage3;
	}


	/**
	 * 功能：获得库位使用4
	 */
	public String getLoc_usage4() {
		return loc_usage4;
	}


	/**
	 * 功能：设置库位使用4
	 * @param loc_usage4
	 */
	public void setLoc_usage4(String loc_usage4) {
		this.loc_usage4 = loc_usage4;
	}


	/**
	 * 功能：获得库位使用5
	 */
	public String getLoc_usage5() {
		return loc_usage5;
	}


	/**
	 * 功能：设置库位使用5
	 * @param loc_usage5
	 */
	public void setLoc_usage5(String loc_usage5) {
		this.loc_usage5 = loc_usage5;
	}


	/**
	 * 功能：获得批次ID
	 */
	public String getLotid() {
		return lotid;
	}


	/**
	 * 功能：设置批次ID
	 * @param lotid
	 */
	public void setLotid(String lotid) {
		this.lotid = lotid;
	}


	/**
	 * 功能：获得上架规则详细ID
	 */
	public String getPutawaydetailid() {
		return putawaydetailid;
	}


	/**
	 * 功能：设置上架规则详细ID
	 * @param putawaydetailid
	 */
	public void setPutawaydetailid(String putawaydetailid) {
		this.putawaydetailid = putawaydetailid;
	}


	/**
	 * 功能：获得上架规则ID
	 */
	public String getPutawayid() {
		return putawayid;
	}


	/**
	 * 功能：设置上架规则ID
	 * @param putawayid
	 */
	public void setPutawayid(String putawayid) {
		this.putawayid = putawayid;
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
	 * 功能：获得库位限制名称
	 */
	public String getLoc_restrictname() {
		return loc_restrictname;
	}


	/**
	 * 功能：设置库位限制名称
	 * @param loc_restrictname
	 */
	public void setLoc_restrictname(String loc_restrictname) {
		this.loc_restrictname = loc_restrictname;
	}


	/**
	 * 功能：获得目标库位名称
	 */
	public String getTozonename() {
		return tozonename;
	}


	/**
	 * 功能：设置目标库位名称
	 * @param tozonename
	 */
	public void setTozonename(String tozonename) {
		this.tozonename = tozonename;
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
	 * 功能：获得批次属性1
	 */
	public String getLotatt1() {
		return lotatt1;
	}


	/**
	 * 功能：设置批次属性1
	 * @param lotatt1
	 */
	public void setLotatt1(String lotatt1) {
		this.lotatt1 = lotatt1;
	}


	/**
	 * 功能：获得批次属性10
	 */
	public String getLotatt10() {
		return lotatt10;
	}


	/**
	 * 功能：设置批次属性10
	 * @param lotatt10
	 */
	public void setLotatt10(String lotatt10) {
		this.lotatt10 = lotatt10;
	}


	/**
	 * 功能：获得批次属性11
	 */
	public String getLotatt11() {
		return lotatt11;
	}


	/**
	 * 功能：设置批次属性11
	 * @param lotatt11
	 */
	public void setLotatt11(String lotatt11) {
		this.lotatt11 = lotatt11;
	}


	/**
	 * 功能：获得批次属性12
	 */
	public String getLotatt12() {
		return lotatt12;
	}


	/**
	 * 功能：设置批次属性12
	 * @param lotatt12
	 */
	public void setLotatt12(String lotatt12) {
		this.lotatt12 = lotatt12;
	}


	/**
	 * 功能：获得批次属性2
	 */
	public String getLotatt2() {
		return lotatt2;
	}


	/**
	 * 功能：设置批次属性2
	 * @param lotatt2
	 */
	public void setLotatt2(String lotatt2) {
		this.lotatt2 = lotatt2;
	}


	/**
	 * 功能：获得批次属性3
	 */
	public String getLotatt3() {
		return lotatt3;
	}


	/**
	 * 功能：设置批次属性3
	 * @param lotatt3
	 */
	public void setLotatt3(String lotatt3) {
		this.lotatt3 = lotatt3;
	}


	/**
	 * 功能：获得批次属性4
	 */
	public String getLotatt4() {
		return lotatt4;
	}


	/**
	 * 功能：设置批次属性4
	 * @param lotatt4
	 */
	public void setLotatt4(String lotatt4) {
		this.lotatt4 = lotatt4;
	}


	/**
	 * 功能：获得批次属性5
	 */
	public String getLotatt5() {
		return lotatt5;
	}


	/**
	 * 功能：设置批次属性5
	 * @param lotatt5
	 */
	public void setLotatt5(String lotatt5) {
		this.lotatt5 = lotatt5;
	}


	/**
	 * 功能：获得批次属性6
	 */
	public String getLotatt6() {
		return lotatt6;
	}


	/**
	 * 功能：设置批次属性6
	 * @param lotatt6
	 */
	public void setLotatt6(String lotatt6) {
		this.lotatt6 = lotatt6;
	}


	/**
	 * 功能：获得批次属性7
	 */
	public String getLotatt7() {
		return lotatt7;
	}


	/**
	 * 功能：设置批次属性7
	 * @param lotatt7
	 */
	public void setLotatt7(String lotatt7) {
		this.lotatt7 = lotatt7;
	}


	/**
	 * 功能：获得批次属性8
	 */
	public String getLotatt8() {
		return lotatt8;
	}


	/**
	 * 功能：设置批次属性8
	 * @param lotatt8
	 */
	public void setLotatt8(String lotatt8) {
		this.lotatt8 = lotatt8;
	}


	/**
	 * 功能：获得批次属性9
	 */
	public String getLotatt9() {
		return lotatt9;
	}


	/**
	 * 功能：设置批次属性9
	 * @param lotatt9
	 */
	public void setLotatt9(String lotatt9) {
		this.lotatt9 = lotatt9;
	}


	/**
	 * 功能：获得存储类型1名称
	 */
	public String getLoc_handlename1() {
		return loc_handlename1;
	}


	/**
	 * 功能：设置存储类型1名称
	 * @param loc_handlename1
	 */
	public void setLoc_handlename1(String loc_handlename1) {
		this.loc_handlename1 = loc_handlename1;
	}


	/**
	 * 功能：获得库位使用1名称
	 */
	public String getLoc_usagename1() {
		return loc_usagename1;
	}


	/**
	 * 功能：设置库位使用1名称
	 * @param loc_usagename1
	 */
	public void setLoc_usagename1(String loc_usagename1) {
		this.loc_usagename1 = loc_usagename1;
	}

}
