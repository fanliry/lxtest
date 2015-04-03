package com.wms3.bms.standard.entity.base;


/**
 * ���������������
 * @author hug
 *
 */
public class BaseWhareaVirtual  implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6074534054844940198L;
	
	private String whAreaId;		//����ID
    private String whAreaName;		//������
    private String warehouseid;		//�ֿ�ID
    private String createtime;		//����ʱ��
    private String createmanid;		//������
    private String ERPCode;			//ERP����
    private String updatetime;		//����޸�ʱ��
    private String updatemanid;		//����޸���
    
    private String belongto;	    // ������ �ĸ����������һ������ �ĸ��ݴ��������ĸ����ݴ����͵Ŀ�����

    private String remarks1;	//Ԥ���ֶ�
    private String remarks2;	//Ԥ���ֶ�
    
    private String ERPAccount;	//ERP����
	//��������
    private String warehousename;	//�ֿ���
    private String belongTowhAreaName;	//�������ĸ��������

    // Constructors

	/** default constructor */
    public BaseWhareaVirtual() {
    }

	/** minimal constructor */
    public BaseWhareaVirtual(String whAreaName) {
        this.whAreaName = whAreaName;
    }

   
    // Property accessors
    /**
     * ���ܣ���ÿ���ID
     */
    public String getwhAreaId() {
        return this.whAreaId;
    }
    /**
     * ���ܣ����ÿ���ID
     * @param whAreaId
     */
    public void setwhAreaId(String whAreaId) {
        this.whAreaId = whAreaId;
    }
    /**
     * ���ܣ���ÿ�����
     * @return
     */
    public String getwhAreaName() {
        return this.whAreaName;
    }
    /**
     * ���ܣ����ÿ�����
     * @param whAreaName
     */
    public void setwhAreaName(String whAreaName) {
        this.whAreaName = whAreaName;
    }
    /**
     * ���ܣ���ô���ʱ��
     * @return
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
     * @return
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
     * @return
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
     * @return
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
     * ���ܣ���òֿ�ID
     * @return
     */
	public String getWarehouseid() {
		return warehouseid;
	}
	/**
	 * ���ܣ����òֿ�ID
	 * @param warehouseid
	 */
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	/**
     * ���ܣ���òֿ���
     * @return
     */
	public String getWarehousename() {
		return warehousename;
	}
	/**
	 * ���ܣ����òֿ���
	 * @param warehousename
	 */
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	
    /**
     * ���ܣ���������������
     * @return
     */
    public String getBelongto() {
		return belongto;
	}
    
	/**
	 * ���ܣ����������������
	 * @param belongto
	 */
	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

    /**
     * ���ܣ���������������
     * @return
     */
    public String getBelongTowhAreaName() {
		return belongTowhAreaName;
	}

	/**
	 * ���ܣ����������������
	 * @param belongTowhAreaName
	 */
	public void setBelongTowhAreaName(String belongTowhAreaName) {
		this.belongTowhAreaName = belongTowhAreaName;
	}
	
	public String getERPCode() {
		return ERPCode;
	}

	public void setERPCode(String eRPCode) {
		ERPCode = eRPCode;
	}

	public String getERPAccount() {
		return ERPAccount;
	}

	public void setERPAccount(String eRPAccount) {
		ERPAccount = eRPAccount;
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}

	public String getRemarks2() {
		return remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}
	
	
}