package com.wms3.bms.standard.entity.rule;



/**
 * �������������ñ�
 * @author hug
 */
public class RuleConfig implements java.io.Serializable {

     /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8064683696195202923L;
	

    // Fields    
	private String ruleconfigid;	// ��������ID
    private String descr;			// ��������
    private String isused;			// �Ƿ�����
    private String ruletype;        // �������� 1���ϼܣ�2�����䣻3������
    private String ruleclass;       // �����Ӧ�ĵ�����
    private String rulemethod;      // �����Ӧ�ĵ��÷���


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
     * ���ܣ���ù�������ID
     */
    public String getRuleconfigid() {
        return this.ruleconfigid;
    }
    
    /**
     * ���ܣ����ù�������ID
     * @param ruleconfigid
     */ 
    public void setRuleconfigid(String ruleconfigid) {
        this.ruleconfigid = ruleconfigid;
    }

    /**
     * ���ܣ���ù�������
     */
    public String getDescr() {
        return this.descr;
    }
    
    /**
     * ���ܣ����ù�������
     * @param descr
     */ 
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * ���ܣ�����Ƿ�����
     */
    public String getIsused() {
        return this.isused;
    }
    
    /**
     * ���ܣ������Ƿ�����
     * @param isused
     */ 
    public void setIsused(String isused) {
        this.isused = isused;
    }


    /**
     * ����:��ù������� 
     * @author hug 2012-5-7 
     * @return
     */
    public String getRuletype() {
        return ruletype;
    }


    /**
     * ����:���ù������� 
     * @author hug 2012-5-7 
     * @param ruletype
     */
    public void setRuletype(String ruletype) {
        this.ruletype = ruletype;
    }
    
    /**
     * ���ܣ���ù����Ӧ�ĵ�����
     */
    public String getRuleclass() {
        return this.ruleclass;
    }
    
    /**
     * ���ܣ����ù����Ӧ�ĵ�����
     * @param ruleclass
     */ 
    public void setRuleclass(String ruleclass) {
        this.ruleclass = ruleclass;
    }
    
    /**
     * ���ܣ���ù����Ӧ�ĵ��÷���
     */
    public String getRulemethod() {
        return this.rulemethod;
    }
    
    /**
     * ���ܣ����ù����Ӧ�ĵ��÷���
     * @param rulemethod
     */ 
    public void setRulemethod(String rulemethod) {
        this.rulemethod = rulemethod;
    }
    
}