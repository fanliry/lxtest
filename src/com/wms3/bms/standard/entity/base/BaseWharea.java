package com.wms3.bms.standard.entity.base;


/**
 * ������������
 * @author hug
 *
 */
public class BaseWharea  implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3212529052570509895L;
	
	private String whAreaId;		//����ID
    private String whAreaName;		//������
    private String whAreaType;		//��������  2���������  ����Ϊ�����
    private String warehouseid;		//�ֿ�ID
    private String createtime;		//����ʱ��
    private String createmanid;		//������
    private String updatetime;		//����޸�ʱ��
    private String updatemanid;		//����޸���
    private String isdefault;		//�Ƿ���Ĭ���ջ���  �ǣ�Y  ��N
    private String istask;			//�Ƿ���Ҫ���ɵ������� �ǣ�Y ��N
    private String storageEnvironment;//�洢��������
    
    private String belongto;	    // ������ �ĸ�������һ������ �ĸ��ݴ��������ĸ����ݴ����͵Ŀ�����

	//��������
    private String warehousename;	//�ֿ���
    private String whAreaTypeName;	//����������
    private String storageEnvironmentName;//�洢������
    private String belongTowhAreaName;	//�������ĸ�����

    // Constructors

	public String getStorageEnvironment() {
		return storageEnvironment;
	}

	public void setStorageEnvironment(String storageEnvironment) {
		this.storageEnvironment = storageEnvironment;
	}

	public String getStorageEnvironmentName() {
		return storageEnvironmentName;
	}

	public void setStorageEnvironmentName(String storageEnvironmentName) {
		this.storageEnvironmentName = storageEnvironmentName;
	}

	/** default constructor */
    public BaseWharea() {
    }

	/** minimal constructor */
    public BaseWharea(String whAreaName) {
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
     * ���ܣ���ÿ�������
     * @return
     */
    public String getwhAreaType() {
        return this.whAreaType;
    }
    /**
     * ���ܣ����ÿ�������
     * @param whAreaType
     */
    public void setwhAreaType(String whAreaType) {
        this.whAreaType = whAreaType;
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
     * ���ܣ�����Ƿ���Ĭ���ջ���
     * @return
     */
	public String getIsdefault() {
		return isdefault;
	}
	/**
	 * ���ܣ������Ƿ���Ĭ���ջ���
	 * @param isdefault
	 */
	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
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
     * ���ܣ���ÿ���������
     * @return
     */
    public String getwhAreaTypeName() {
        return this.whAreaTypeName;
    }
    
    /**
     * ���ܣ����ÿ���������
     * @param whAreaTypeName
     */
    public void setwhAreaTypeName(String whAreaTypeName) {
        this.whAreaTypeName = whAreaTypeName;
    }

	/**
	 * ���ܣ�����Ƿ���Ҫ���ɵ������� �ǣ�Y ��N
	 */
	public String getIstask() {
		return istask;
	}

	/**
	 * ���ܣ������Ƿ���Ҫ���ɵ������� �ǣ�Y ��N
	 * @param istask
	 */
	public void setIstask(String istask) {
		this.istask = istask;
	}
	
    /**
     * ���ܣ������������
     * @return
     */
    public String getBelongto() {
		return belongto;
	}
    
	/**
	 * ���ܣ�������������
	 * @param belongto
	 */
	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

    /**
     * ���ܣ������������
     * @return
     */
    public String getBelongTowhAreaName() {
		return belongTowhAreaName;
	}

	/**
	 * ���ܣ�������������
	 * @param belongTowhAreaName
	 */
	public void setBelongTowhAreaName(String belongTowhAreaName) {
		this.belongTowhAreaName = belongTowhAreaName;
	}
	
}