package com.wms3.bms.standard.entity.base;


/**
 * ���������
 * @author hug
 *
 */
public class BaseAlley implements java.io.Serializable {

	private static final long serialVersionUID = 4863429125914809083L;
	
	 private String cargoAlleyId;	//���ID
     private String cargoAlleyName;	//�����
     private String warehouseid;	//�ֿ�ID
     private String whAreaId;		//����ID
     private String inlock;			//����� Y.�� N.��
     private String outlock;		//������ Y.�� N.��
     private String istwin;			//�Ƿ�˫����λ��� Y.�� N.��
     private String createtime;		//����ʱ��
     private String createmanid;	//������
     private String updatetime;		//����޸�ʱ��
     private String updatemanid;	//����޸���

     private String warehousename;	//�ֿ���
     private String whAreaName;		//������
     
    // Constructors

    /** default constructor */
    public BaseAlley() {
    }

	/** minimal constructor */
    public BaseAlley(String warehouseid, String inlock, String outlock) {
        this.warehouseid = warehouseid;
        this.inlock = inlock;
        this.outlock = outlock;
    }
    
    /** full constructor */
    public BaseAlley(String cargoAlleyName, String warehouseid, String whAreaId, String inlock, String outlock, String istwin, 
    		String createtime, String createmanid, String updatetime, String updatemanid) {
        this.cargoAlleyName = cargoAlleyName;
        this.warehouseid = warehouseid;
        this.whAreaId = whAreaId;
        this.inlock = inlock;
        this.outlock = outlock;
        this.istwin = istwin;
        this.createtime = createtime;
        this.createmanid = createmanid;
        this.updatetime = updatetime;
        this.updatemanid = updatemanid;
    }

   
    // Property accessors
    /**
     * ���ܣ�������ID
     */
    public String getCargoAlleyId() {
        return this.cargoAlleyId;
    }
    /**
     * ���ܣ��������ID
     * @param cargoAlleyId
     */
    public void setCargoAlleyId(String cargoAlleyId) {
        this.cargoAlleyId = cargoAlleyId;
    }
    /**
     * ���ܣ���������
     * @return
     */
    public String getCargoAlleyName() {
        return this.cargoAlleyName;
    }
    /**
     * ���ܣ����������
     * @param cargoAlleyName
     */
    public void setCargoAlleyName(String cargoAlleyName) {
        this.cargoAlleyName = cargoAlleyName;
    }
    /**
     * ���ܣ���òֿ�ID
     * @return
     */
    public String getWarehouseid() {
        return this.warehouseid;
    }
    /**
     * ���ܣ����òֿ�ID
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }
    /**
     * ���ܣ���ÿ���ID
     * @return
     */
    public String getWhAreaId() {
        return this.whAreaId;
    }
    /**
     * ���ܣ����ÿ���ID
     * @param whAreaId
     */
    public void setWhAreaId(String whAreaId) {
        this.whAreaId = whAreaId;
    }
    /**
     * ���ܣ��������� Y.�� N.��
     * @return
     */
    public String getInlock() {
        return this.inlock;
    }
    /**
     * ���ܣ���������� Y.�� N.��
     * @param inlock
     */
    public void setInlock(String inlock) {
        this.inlock = inlock;
    }
    /**
     * ���ܣ���ó����� Y.�� N.��
     * @return
     */
    public String getOutlock() {
        return this.outlock;
    }
    /**
     * ���ܣ����ó����� Y.�� N.��
     * @param outlock
     */
    public void setOutlock(String outlock) {
        this.outlock = outlock;
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
     * ���ܣ���ÿ�����
     * @return
     */
    public String getWhAreaName() {
        return this.whAreaName;
    }
    /**
     * ���ܣ����ÿ�����
     * @param whAreaName
     */
    public void setWhAreaName(String whAreaName) {
        this.whAreaName = whAreaName;
    }

	/**
	 * ���ܣ�����Ƿ�˫����λ���
	 */
	public String getIstwin() {
		return istwin;
	}

	/**
	 * ���ܣ������Ƿ�˫����λ���
	 * @param istwin
	 */
	public void setIstwin(String istwin) {
		this.istwin = istwin;
	}
}