package com.wms3.bms.standard.entity.rule;



/**
 * �������ϼܹ����
 * @author hug
 */
public class RulePutaway  implements java.io.Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5104908860464197383L;
	
	
	// Fields    
	private String putawayid;			// �ϼܹ���ID
    private String warehouseid;			// �����ֿ�ID
    private String descr;				// ����
    private String remark;				// ��ע
    
    //��������
    private String warehousename;		// �����ֿ���

    // Constructors

    /** default constructor */
    public RulePutaway() {
    }

    
    // Property accessors
    
	/**
	 * ���ܣ�����ϼܹ���ID
	 */
	public String getPutawayid() {
		return putawayid;
	}

	/**
	 * ���ܣ������ϼܹ���ID
	 * @param allocationid
	 */
	public void setPutawayid(String putawayid) {
		this.putawayid = putawayid;
	}

	/**
	 * ���ܣ��������
	 */
	public String getDescr() {
		return descr;
	}

	/**
	 * ���ܣ���������
	 * @param descr
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}

	/**
	 * ���ܣ���ñ�ע
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * ���ܣ����ñ�ע
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * ���ܣ���������ֿ�ID
	 */
	public String getWarehouseid() {
		return warehouseid;
	}

	/**
	 * ���ܣ����������ֿ�ID
	 * @param warehouseid
	 */
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	
	/**
     * ���ܣ���������ֿ���
     * @return
     */
	public String getWarehousename() {
		return warehousename;
	}
	
	/**
	 * ���ܣ����������ֿ���
	 * @param warehousename
	 */
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
}