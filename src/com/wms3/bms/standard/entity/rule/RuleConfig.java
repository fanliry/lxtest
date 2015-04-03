package com.wms3.bms.standard.entity.rule;



/**
 * 描述：规则配置表
 * @author hug
 */
public class RuleConfig implements java.io.Serializable {

     /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8064683696195202923L;
	

    // Fields    
	private String ruleconfigid;	// 规则配置ID
    private String descr;			// 规则描述
    private String isused;			// 是否启用
    private String ruletype;        // 规则类型 1：上架；2：分配；3：补货
    private String ruleclass;       // 规则对应的调用类
    private String rulemethod;      // 规则对应的调用方法


    // Constructors

    /** default constructor */
    public RuleConfig() {
    }

    
    /** full constructor */
    public RuleConfig(String descr, String isused, String ruletype, String ruleclass, String rulemethod) {
        this.descr = descr;
        this.isused = isused;
        this.ruletype = ruletype;
        this.ruleclass = ruleclass;
        this.rulemethod = rulemethod;
    }

   
    // Property accessors

    /**
     * 功能：获得规则配置ID
     */
    public String getRuleconfigid() {
        return this.ruleconfigid;
    }
    
    /**
     * 功能：设置规则配置ID
     * @param ruleconfigid
     */ 
    public void setRuleconfigid(String ruleconfigid) {
        this.ruleconfigid = ruleconfigid;
    }

    /**
     * 功能：获得规则描述
     */
    public String getDescr() {
        return this.descr;
    }
    
    /**
     * 功能：设置规则描述
     * @param descr
     */ 
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * 功能：获得是否启用
     */
    public String getIsused() {
        return this.isused;
    }
    
    /**
     * 功能：设置是否启用
     * @param isused
     */ 
    public void setIsused(String isused) {
        this.isused = isused;
    }


    /**
     * 功能:获得规则类型 
     * @author hug 2012-5-7 
     * @return
     */
    public String getRuletype() {
        return ruletype;
    }


    /**
     * 功能:设置规则类型 
     * @author hug 2012-5-7 
     * @param ruletype
     */
    public void setRuletype(String ruletype) {
        this.ruletype = ruletype;
    }
    
    /**
     * 功能：获得规则对应的调用类
     */
    public String getRuleclass() {
        return this.ruleclass;
    }
    
    /**
     * 功能：设置规则对应的调用类
     * @param ruleclass
     */ 
    public void setRuleclass(String ruleclass) {
        this.ruleclass = ruleclass;
    }
    
    /**
     * 功能：获得规则对应的调用方法
     */
    public String getRulemethod() {
        return this.rulemethod;
    }
    
    /**
     * 功能：设置规则对应的调用方法
     * @param rulemethod
     */ 
    public void setRulemethod(String rulemethod) {
        this.rulemethod = rulemethod;
    }
    
}