package com.wms3.bms.standard.entity.base;



/**
 * ���������ι���
 * @author hug
 *
 */
public class BaseBatchrule  implements java.io.Serializable {

	private static final long serialVersionUID = -5794216354387712279L;
	private String batchruleid;		//���ι���ID
    private String batchid;			//����ID
    private String rulename;		//��������
    private String ruleinfo;		//����
    private Integer startpos;		//��ʼλ��
    private Integer endpos;			//����λ��


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
     * ���ܣ�������ι���ID
     */
    public String getBatchruleid() {
        return this.batchruleid;
    }
    /**
     * ���ܣ��������ι���ID
     * @param batchruleid
     */
    public void setBatchruleid(String batchruleid) {
        this.batchruleid = batchruleid;
    }
    /**
     * ���ܣ���ù�������
     * @return
     */
    public String getRulename() {
        return this.rulename;
    }
    /**
     * ���ܣ����ù�������
     * @param rulename
     */
    public void setRulename(String rulename) {
        this.rulename = rulename;
    }
    /**
     * ���ܣ���ù���
     * @return
     */
    public String getRuleinfo() {
        return this.ruleinfo;
    }
    /**
     * ���ܣ����ù���
     * @param ruleinfo
     */
    public void setRuleinfo(String ruleinfo) {
        this.ruleinfo = ruleinfo;
    }
    /**
     * ���ܣ���ÿ�ʼλ��
     * @return
     */
    public Integer getStartpos() {
        return this.startpos;
    }
    /**
     * ���ܣ����ÿ�ʼλ��
     * @param startpos
     */
    public void setStartpos(Integer startpos) {
        this.startpos = startpos;
    }
    /**
     * ���ܣ���ý���λ��
     * @return
     */
    public Integer getEndpos() {
        return this.endpos;
    }
    /**
     * ���ܣ����ý���λ��
     * @param endpos
     */
    public void setEndpos(Integer endpos) {
        this.endpos = endpos;
    }
    /**
     * ���ܣ��������ID
     * @return
     */
	public String getBatchid() {
		return batchid;
	}

	/**
	 * ���ܣ���������ID
	 * @param batchid
	 */
	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}

}