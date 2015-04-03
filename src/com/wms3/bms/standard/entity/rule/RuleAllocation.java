package com.wms3.bms.standard.entity.rule;



/**
 * ��������������
 * @author hug
 */
public class RuleAllocation  implements java.io.Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5104908860464197383L;
	
	
	// Fields    
	private String allocationid;		// �������ID
    private String warehouseid;			// �����ֿ�ID
    private String descr;				// ����
    private String remark;				// ��ע
    
    //��������
    private String warehousename;		// �����ֿ���

    // Constructors

    /** default constructor */
    public RuleAllocation() {
    }

    
    // Property accessors
    
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