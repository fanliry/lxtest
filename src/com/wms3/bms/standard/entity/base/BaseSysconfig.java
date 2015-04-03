package com.wms3.bms.standard.entity.base;



/**
 * ������ϵͳ���ñ�
 * @author hug
 */
public class BaseSysconfig  implements java.io.Serializable {

     /**
	 * 
	 */
	private static final long serialVersionUID = -8754766350695980425L;

    // Fields    
	private String sysconfigid;		// ϵͳ���ñ��
    private String sysconfigtype;	// ���÷���
    private String cfgvalue;		// ��ϵ��
    private String remark;			// ��ע
    private String createtime;		// ����ʱ��
    private String createmanid;		// ������
    private String updatetime;		// ����޸�ʱ��
    private String updatemanid;		// ����޸���
    private String useflag;			// �Ƿ�����


    // Constructors

    /** default constructor */
    public BaseSysconfig() {
    }

    
    /** full constructor */
    public BaseSysconfig(String sysconfigtype, String cfgvalue, String remark, String createtime, String createmanid, String updatetime, String updatemanid, String useflag) {
        this.sysconfigtype = sysconfigtype;
        this.cfgvalue = cfgvalue;
        this.remark = remark;
        this.createtime = createtime;
        this.createmanid = createmanid;
        this.updatetime = updatetime;
        this.updatemanid = updatemanid;
        this.useflag = useflag;
    }

   
    // Property accessors
    /**
     * ���ܣ����ϵͳ���ñ��
     */
    public String getSysconfigid() {
        return this.sysconfigid;
    }
    
    /**
     * ���ܣ�����ϵͳ���ñ��
     * @param sysconfigid
     */ 
    public void setSysconfigid(String sysconfigid) {
        this.sysconfigid = sysconfigid;
    }

    /**
     * ���ܣ�������÷���
     */
    public String getSysconfigtype() {
        return this.sysconfigtype;
    }
    
    /**
     * ���ܣ��������÷���
     * @param sysconfigtype
     */ 
    public void setSysconfigtype(String sysconfigtype) {
        this.sysconfigtype = sysconfigtype;
    }

    /**
     * ���ܣ������ϵ��
     */
    public String getCfgvalue() {
        return this.cfgvalue;
    }
    
    /**
     * ���ܣ�������ϵ��
     * @param cfgvalue
     */ 
    public void setCfgvalue(String cfgvalue) {
        this.cfgvalue = cfgvalue;
    }

    /**
     * ���ܣ���ñ�ע
     */
    public String getRemark() {
        return this.remark;
    }
    
    /**
     * ���ܣ����ñ�ע
     * @param remark
     */ 
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * ���ܣ���ô���ʱ��
     */
    public String getCreatetime() {
        return this.createtime;
    }
    
    /**
     * ���ܣ����ô���ʱ��
     * @param createtime
     */ 
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * ���ܣ���ô�����
     */
    public String getCreatemanid() {
        return this.createmanid;
    }
    
    /**
     * ���ܣ����ô�����
     * @param createmanid
     */ 
    public void setCreatemanid(String createmanid) {
        this.createmanid = createmanid;
    }

    /**
     * ���ܣ��������޸�ʱ��
     */
    public String getUpdatetime() {
        return this.updatetime;
    }
    
    /**
     * ���ܣ���������޸�ʱ��
     * @param updatetime
     */ 
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * ���ܣ��������޸���
     */
    public String getUpdatemanid() {
        return this.updatemanid;
    }
    
    /**
     * ���ܣ���������޸���
     * @param updatemanid
     */ 
    public void setUpdatemanid(String updatemanid) {
        this.updatemanid = updatemanid;
    }

    /**
     * ���ܣ�����Ƿ�����
     */
    public String getUseflag() {
        return this.useflag;
    }
    
    /**
     * ���ܣ������Ƿ�����
     * @param useflag
     */ 
    public void setUseflag(String useflag) {
        this.useflag = useflag;
    }
}