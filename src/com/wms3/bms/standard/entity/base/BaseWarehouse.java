package com.wms3.bms.standard.entity.base;



/**
 * �������ֿ��
 * @author hug
 */
public class BaseWarehouse  implements java.io.Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8039395923359479863L;

    // Fields    
	private String warehouseid;		// �ֿ�ID
    private String warehousename;	// �ֿ���
    private String whaddress;		// �ֿ��ַ
    private String whmgrman;		// �ֿ����Ա
    private String whlinkman;		// ��ϵ��
    private String whlinktel;		// ��ϵ�绰
    private String iscurrent;		// �Ƿ�ǰ�ֿ� Y���ǣ�N������
    private String erpcode;			// ��ӦERP�Ĵ���
    private String createtime;		// ����ʱ��
    private String createmanid;		// ������
    private String updatetime;		// ����޸�ʱ��
    private String updatemanid;		// ����޸���
    
    //��������
    private String warehousetypename;	// �ֿ���������

    // Constructors

    /** default constructor */
    public BaseWarehouse() {
    }

    
    // Property accessors
    /**
     * ���ܣ���òֿ�ID
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
     * ���ܣ���òֿ���
     */
    public String getWarehousename() {
        return this.warehousename;
    }
    
    /**
     * ���ܣ����òֿ���
     * @param warehousename
     */
    public void setWarehousename(String warehousename) {
        this.warehousename = warehousename;
    }

    /**
     * ���ܣ���òֿ��ַ
     */
    public String getWhaddress() {
        return this.whaddress;
    }
    
    /**
     * ���ܣ����òֿ��ַ
     * @param whaddress
     */
    public void setWhaddress(String whaddress) {
        this.whaddress = whaddress;
    }

    /**
     * ���ܣ���òֿ����Ա
     */
    public String getWhmgrman() {
        return this.whmgrman;
    }
    
    /**
     * ���ܣ����òֿ����Ա
     * @param whmgrman
     */
    public void setWhmgrman(String whmgrman) {
        this.whmgrman = whmgrman;
    }

    /**
     * ���ܣ������ϵ��
     */
    public String getWhlinkman() {
        return this.whlinkman;
    }
    
    /**
     * ���ܣ�������ϵ��
     * @param whlinkman
     */
    public void setWhlinkman(String whlinkman) {
        this.whlinkman = whlinkman;
    }

    /**
     * ���ܣ������ϵ�绰
     */
    public String getWhlinktel() {
        return this.whlinktel;
    }
    
    /**
     * ���ܣ�������ϵ�绰
     * @param whlinktel
     */
    public void setWhlinktel(String whlinktel) {
        this.whlinktel = whlinktel;
    }
    
    /**
     * ���ܣ�����Ƿ�ǰ�ֿ�
     */
    public String getIscurrent() {
        return this.iscurrent;
    }
    
    /**
     * ���ܣ������Ƿ�ǰ�ֿ�
     * @param iscurrent
     */
    public void setIscurrent(String iscurrent) {
        this.iscurrent = iscurrent;
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
     * ���ܣ���òֿ���������
     */
    public String getWarehousetypename() {
        return this.warehousetypename;
    }
    
    /**
     * ���ܣ����òֿ���������
     * @param warehousetypename
     */
    public void setWarehousetypename(String warehousetypename) {
        this.warehousetypename = warehousetypename;
    }


	/**
	 * ���ܣ���ö�ӦERP�Ĵ���
	 */
	public String getErpcode() {
		return erpcode;
	}


	/**
	 * ���ܣ����ö�ӦERP�Ĵ���
	 * @param erpcode
	 */
	public void setErpcode(String erpcode) {
		this.erpcode = erpcode;
	}
}