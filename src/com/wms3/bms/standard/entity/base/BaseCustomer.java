package com.wms3.bms.standard.entity.base;



/**
 * �������ͻ���
 * @author hug
 */
public class BaseCustomer  implements java.io.Serializable {

     /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8723672586391308558L;
	
    // Fields    
	private String customerid;		// �ͻ����
    private String shortname;		// �ͻ����
    private String customername;	// �ͻ�ȫ��
    private String customertype;	// �ͻ�����
    private String contact;			// ��ϵ��
    private String phone;			// ��ϵ�绰
    private String fax;				// ����
    private String address;			// ��ַ
    private String bank;			// ��������
    private String putawayid;		// �ϼܹ���ID
    private String allocationid;	// �������ID
    private String replenishid;		// ��������ID
    private String linenumber;		// ��·��
    private String pakageid;		// ��װID 
    private String useflag;			// �Ƿ�����
    private String createtime;		// ����ʱ��
    private String createmanid;		// ������
    private String updatetime;		// ����޸�ʱ��
    private String updatemanid;		// ����޸���
    
    //��������
    private String customertypename;	// �ͻ�������
    private String putawayname;			// �ϼܹ�������
    private String allocationname;		// �����������
    private String replenishname;		// ������������
    private String pkgdesc;				// ��װ����

    // Constructors

    /** default constructor */
    public BaseCustomer() {
    }

	/** minimal constructor */
    public BaseCustomer(String customername) {
        this.customername = customername;
    }

   
    // Property accessors
    /**
     * ���ܣ���ÿͻ����
     */
    public String getCustomerid() {
        return this.customerid;
    }
    
    /**
     * ���ܣ����ÿͻ����
     * @param customerid
     */    
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
    
    /**
     * ���ܣ���ÿͻ����
     */
    public String getShortname() {
        return this.shortname;
    }
    
    /**
     * ���ܣ����ÿͻ����
     * @param shortname
     */ 
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
    
    /**
     * ���ܣ���ÿͻ�ȫ��
     */
    public String getCustomername() {
        return this.customername;
    }
    
    /**
     * ���ܣ����ÿͻ�ȫ��
     * @param customername
     */ 
    public void setCustomername(String customername) {
        this.customername = customername;
    }
    
    /**
     * ���ܣ���ÿͻ�����
     */
    public String getCustomertype() {
        return this.customertype;
    }
    
    /**
     * ���ܣ����ÿͻ�����
     * @param customertype
     */ 
    public void setCustomertype(String customertype) {
        this.customertype = customertype;
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
     * ���ܣ���ÿ�������
     */
    public String getBank() {
        return this.bank;
    }
    
    /**
     * ���ܣ����ÿ�������
     * @param bank
     */ 
    public void setBank(String bank) {
        this.bank = bank;
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
     * ���ܣ���ÿͻ�������
     */
    public String getCustomertypename() {
        return this.customertypename;
    }
    
    /**
     * ���ܣ����ÿͻ�������
     * @param customertypename
     */ 
    public void setCustomertypename(String customertypename) {
        this.customertypename = customertypename;
    }

	/**
	 * ���ܣ������·��
	 */
	public String getLinenumber() {
		return linenumber;
	}

	/**
	 * ���ܣ�������·��
	 * @param linenumber
	 */
	public void setLinenumber(String linenumber) {
		this.linenumber = linenumber;
	}

	/**
	 * ���ܣ���÷������ID
	 */
	public String getAllocationid() {
		return allocationid;
	}

	/**
	 * ���ܣ����÷������ID
	 * @param allocationid
	 */
	public void setAllocationid(String allocationid) {
		this.allocationid = allocationid;
	}

	/**
	 * ���ܣ���÷����������
	 */
	public String getAllocationname() {
		return allocationname;
	}

	/**
	 * ���ܣ����÷����������
	 * @param allocationname
	 */
	public void setAllocationname(String allocationname) {
		this.allocationname = allocationname;
	}

	/**
	 * ���ܣ�����ϼܹ���ID
	 */
	public String getPutawayid() {
		return putawayid;
	}

	/**
	 * ���ܣ������ϼܹ���ID
	 * @param putawayid
	 */
	public void setPutawayid(String putawayid) {
		this.putawayid = putawayid;
	}

	/**
	 * ���ܣ�����ϼܹ�������
	 */
	public String getPutawayname() {
		return putawayname;
	}

	/**
	 * ���ܣ������ϼܹ�������
	 * @param putawayname
	 */
	public void setPutawayname(String putawayname) {
		this.putawayname = putawayname;
	}

	/**
	 * ���ܣ���ò�������ID
	 */
	public String getReplenishid() {
		return replenishid;
	}

	/**
	 * ���ܣ����ò�������ID
	 * @param replenishid
	 */
	public void setReplenishid(String replenishid) {
		this.replenishid = replenishid;
	}

	/**
	 * ���ܣ���ò�����������
	 */
	public String getReplenishname() {
		return replenishname;
	}

	/**
	 * ���ܣ����ò�����������
	 * @param replenishname
	 */
	public void setReplenishname(String replenishname) {
		this.replenishname = replenishname;
	}

	/**
	 * ���ܣ���ð�װID 
	 */
	public String getPakageid() {
		return pakageid;
	}

	/**
	 * ���ܣ����ð�װID 
	 * @param pakageid
	 */
	public void setPakageid(String pakageid) {
		this.pakageid = pakageid;
	}

	/**
	 * ���ܣ���ð�װ����
	 */
	public String getPkgdesc() {
		return pkgdesc;
	}

	/**
	 * ���ܣ����ð�װ����
	 * @param pkgdesc
	 */
	public void setPkgdesc(String pkgdesc) {
		this.pkgdesc = pkgdesc;
	}
    
    
}