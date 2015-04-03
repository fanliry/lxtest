package com.wms3.bms.standard.entity.base;



/**
 * 描述：批次规则
 * @author hug
 *
 */
public class BaseBatchrule  implements java.io.Serializable {

	private static final long serialVersionUID = -5794216354387712279L;
	private String batchruleid;		//批次规则ID
    private String batchid;			//批次ID
    private String rulename;		//规则名称
    private String ruleinfo;		//规则
    private Integer startpos;		//开始位置
    private Integer endpos;			//结束位置


    // Constructors

    /** default constructor */
    public BaseBatchrule() {
    }

    
    /** full constructor */
    public BaseBatchrule(String batchid, String rulename, String ruleinfo, Integer startpos, Integer endpos) {
    	this.batchid = batchid;
        this.rulename = rulename;
        this.ruleinfo = ruleinfo;
        this.startpos = startpos;
        this.endpos = endpos;
    }

   
    // Property accessors
    /**
     * 功能：获得批次规则ID
     */
    public String getBatchruleid() {
        return this.batchruleid;
    }
    /**
     * 功能：设置批次规则ID
     * @param batchruleid
     */
    public void setBatchruleid(String batchruleid) {
        this.batchruleid = batchruleid;
    }
    /**
     * 功能：获得规则名称
     * @return
     */
    public String getRulename() {
        return this.rulename;
    }
    /**
     * 功能：设置规则名称
     * @param rulename
     */
    public void setRulename(String rulename) {
        this.rulename = rulename;
    }
    /**
     * 功能：获得规则
     * @return
     */
    public String getRuleinfo() {
        return this.ruleinfo;
    }
    /**
     * 功能：设置规则
     * @param ruleinfo
     */
    public void setRuleinfo(String ruleinfo) {
        this.ruleinfo = ruleinfo;
    }
    /**
     * 功能：获得开始位置
     * @return
     */
    public Integer getStartpos() {
        return this.startpos;
    }
    /**
     * 功能：设置开始位置
     * @param startpos
     */
    public void setStartpos(Integer startpos) {
        this.startpos = startpos;
    }
    /**
     * 功能：获得结束位置
     * @return
     */
    public Integer getEndpos() {
        return this.endpos;
    }
    /**
     * 功能：设置结束位置
     * @param endpos
     */
    public void setEndpos(Integer endpos) {
        this.endpos = endpos;
    }
    /**
     * 功能：获得批次ID
     * @return
     */
	public String getBatchid() {
		return batchid;
	}

	/**
	 * 功能：设置批次ID
	 * @param batchid
	 */
	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}

}