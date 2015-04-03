package com.wms3.bms.standard.entity.base;



/**
 * ���������ű�
 * @author hug
 */
public class BaseDepartment implements java.io.Serializable {

     /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1575475036346770416L;	

    // Fields    
	
	private String departmentid;	// ���ű��
    private String shortname;		// ���ż��
    private String departmentname;	// ����ȫ��
    private String departmenttype;	// ���ŷ���
    private String contact;			// ��ϵ��
    private String phone;			// ��ϵ�绰
    private String fax;				// ����
    private String address;			// ��ַ
    private String createtime;		// ����ʱ��
    private String createmanid;		// ������
    private String updatetime;		// ����޸�ʱ��
    private String updatemanid;		// ����޸���
    private String useflag;			// �Ƿ����� Y/N
    
    //��������
    private String departmenttypename;	// ���ŷ�����

    // Constructors

    /** default constructor */
    public BaseDepartment() {
    }

	/** minimal constructor */
    public BaseDepartment(String departmentname) {
        this.departmentname = departmentname;
    }
    
    /** full constructor */
    public BaseDepartment(String shortname, String departmentname, String departmenttype, String contact, String phone, 
    		String fax, String address, String createtime, String createmanid, String updatetime, String updatemanid, String useflag) {
    	
        this.shortname = shortname;
        this.departmentname = departmentname;
        this.departmenttype = departmenttype;
        this.contact = contact;
        this.phone = phone;
        this.fax = fax;
        this.address = address;
        this.createtime = createtime;
        this.createmanid = createmanid;
        this.updatetime = updatetime;
        this.updatemanid = updatemanid;
        this.useflag = useflag;
    }

   
    // Property accessors
    /**
     * ���ܣ���ò��ű��
     */
    public String getDepartmentid() {
        return this.departmentid;
    }
    
    /**
     * ���ܣ����ò��ű��
     * @param departmentid
     */
    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    /**
     * ���ܣ���ò��ż��
     */
    public String getShortname() {
        return this.shortname;
    }
    
    /**
     * ���ܣ����ò��ż��
     * @param shortname
     */
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    /**
     * ���ܣ���ò���ȫ��
     */
    public String getDepartmentname() {
        return this.departmentname;
    }
    
    /**
     * ���ܣ����ò���ȫ��
     * @param departmentname
     */
    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    /**
     * ���ܣ���ò��ŷ���
     */
    public String getDepartmenttype() {
        return this.departmenttype;
    }
    
    /**
     * ���ܣ����ò��ŷ���
     * @param departmenttype
     */
    public void setDepartmenttype(String departmenttype) {
        this.departmenttype = departmenttype;
    }

    /**
     * ���ܣ������ϵ��
     */
    public String getContact() {
        return this.contact;
    }
    
    /**
     * ���ܣ�������ϵ��
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * ���ܣ������ϵ�绰
     */
    public String getPhone() {
        return this.phone;
    }
    
    /**
     * ���ܣ�������ϵ�绰
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * ���ܣ���ô���
     */
    public String getFax() {
        return this.fax;
    }
    
    /**
     * ���ܣ����ô���
     * @param fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * ���ܣ���õ�ַ
     */
    public String getAddress() {
        return this.address;
    }
    
    /**
     * ���ܣ����õ�ַ
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
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
    
    /**
     * ���ܣ���ò��ŷ�����
     */
    public String getDepartmenttypename() {
        return this.departmenttypename;
    }
    
    /**
     * ���ܣ����ò��ŷ�����
     * @param departmenttypename
     */
    public void setDepartmenttypename(String departmenttypename) {
        this.departmenttypename = departmenttypename;
    }
}