package com.wms3.bms.standard.entity.rule.copy;



/**
 * ���������������
 * @author hug
 */
public class RuleReplenish implements java.io.Serializable {


	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 123041264676420237L;

    // Fields   
	private String replenishid;			//�����������
	private String warehouseid;			//�����ֿ�ID
	private String descr;				//����
	private String ruleconfigid;		//��������ID
	private double ea_lowerlimit;		//��������
	private double ea_uplimit;			//��������
	private double ea_minreplenishqty;	//������С������
	private double cs_lowerlimit;		//�������
	private double cs_uplimit;			//�������
	private double cs_minreplenishqty;	//�����С������
	private double ot_lowerlimit;		//���������
	private double ot_uplimit;			//���������
	private double ot_minreplenishqty;	//�������С������
    private String remark;				//��ע
    
    // ��������
    private String warehousename;		//�����ֿ���
    private String ruleconfigname;		//������������

    // Constructors

    /** default constructor */
    public RuleReplenish() {
    }

   
    // Property accessors

	/**
	 * ���ܣ�����������
	 */
	public double getCs_lowerlimit() {
		return cs_lowerlimit;
	}


	/**
	 * ���ܣ������������
	 * @param cs_lowerlimit
	 */
	public void setCs_lowerlimit(double cs_lowerlimit) {
		this.cs_lowerlimit = cs_lowerlimit;
	}


	/**
	 * ���ܣ���������С������
	 */
	public double getCs_minreplenishqty() {
		return cs_minreplenishqty;
	}


	/**
	 * ���ܣ����������С������
	 * @param cs_minreplenishqty
	 */
	public void setCs_minreplenishqty(double cs_minreplenishqty) {
		this.cs_minreplenishqty = cs_minreplenishqty;
	}


	/**
	 * ���ܣ�����������
	 */
	public double getCs_uplimit() {
		return cs_uplimit;
	}


	/**
	 * ���ܣ������������
	 * @param cs_uplimit
	 */
	public void setCs_uplimit(double cs_uplimit) {
		this.cs_uplimit = cs_uplimit;
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
	 * ���ܣ���ü�������
	 */
	public double getEa_lowerlimit() {
		return ea_lowerlimit;
	}


	/**
	 * ���ܣ����ü�������
	 * @param ea_lowerlimit
	 */
	public void setEa_lowerlimit(double ea_lowerlimit) {
		this.ea_lowerlimit = ea_lowerlimit;
	}


	/**
	 * ���ܣ���ü�����С������
	 */
	public double getEa_minreplenishqty() {
		return ea_minreplenishqty;
	}


	/**
	 * ���ܣ����ü�����С������
	 * @param ea_minreplenishqty
	 */
	public void setEa_minreplenishqty(double ea_minreplenishqty) {
		this.ea_minreplenishqty = ea_minreplenishqty;
	}


	/**
	 * ���ܣ���ü�������
	 */
	public double getEa_uplimit() {
		return ea_uplimit;
	}


	/**
	 * ���ܣ����ü�������
	 * @param ea_uplimit
	 */
	public void setEa_uplimit(double ea_uplimit) {
		this.ea_uplimit = ea_uplimit;
	}


	/**
	 * ���ܣ�������������
	 */
	public double getOt_lowerlimit() {
		return ot_lowerlimit;
	}


	/**
	 * ���ܣ��������������
	 * @param ot_lowerlimit
	 */
	public void setOt_lowerlimit(double ot_lowerlimit) {
		this.ot_lowerlimit = ot_lowerlimit;
	}


	/**
	 * ���ܣ�����������С������
	 */
	public double getOt_minreplenishqty() {
		return ot_minreplenishqty;
	}


	/**
	 * ���ܣ������������С������
	 * @param ot_minreplenishqty
	 */
	public void setOt_minreplenishqty(double ot_minreplenishqty) {
		this.ot_minreplenishqty = ot_minreplenishqty;
	}


	/**
	 * ���ܣ�������������
	 */
	public double getOt_uplimit() {
		return ot_uplimit;
	}


	/**
	 * ���ܣ��������������
	 * @param ot_uplimit
	 */
	public void setOt_uplimit(double ot_uplimit) {
		this.ot_uplimit = ot_uplimit;
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
	 * ���ܣ���ò����������
	 */
	public String getReplenishid() {
		return replenishid;
	}


	/**
	 * ���ܣ����ò����������
	 * @param replenishid
	 */
	public void setReplenishid(String replenishid) {
		this.replenishid = replenishid;
	}


	/**
	 * ���ܣ���ù�������ID
	 */
	public String getRuleconfigid() {
		return ruleconfigid;
	}


	/**
	 * ���ܣ����ù�������ID
	 * @param ruleconfigid
	 */
	public void setRuleconfigid(String ruleconfigid) {
		this.ruleconfigid = ruleconfigid;
	}


	/**
	 * ���ܣ���ù�����������
	 */
	public String getRuleconfigname() {
		return ruleconfigname;
	}


	/**
	 * ���ܣ����ù�����������
	 * @param ruleconfigname
	 */
	public void setRuleconfigname(String ruleconfigname) {
		this.ruleconfigname = ruleconfigname;
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