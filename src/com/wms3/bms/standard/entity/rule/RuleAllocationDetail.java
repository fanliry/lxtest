package com.wms3.bms.standard.entity.rule;

/**
 * ���������������ϸ��
 * @author hug
 */
public class RuleAllocationDetail implements java.io.Serializable{

    /**
	 * serialVersionUID
	 */
    private static final long serialVersionUID = 6677430147370893672L;
    
    //Fields    
    private String allocationdetailid;	//���������ϸID
    private String allocationid;  		//�������ID
	private	int	sort;					//����˳λ
	private String ruleconfigid;		//��������ID
    private String enableflag;			//�Ƿ���Ч
    private String clearloc_flag;      	//�Ƿ���� N-��,Y-��
    private String apart_flag;			//�Ƿ���� N-��,Y-��
    
    protected String over_flag;    		//�Ƿ���λ�������� N-��,Y-��
    protected String auto_flag;    		//�Ƿ��Զ������������� N-��,Y-��
    protected String bulkpick_flag;    	//�Ƿ�洢λ��ѡ N-��,Y-��
    protected String part_flag;    		//�Ƿ��� N-��,Y-��
    protected String remark;    		//��ע
    
    private String tozone;				//Ŀ�����
    private String tolocationid;		//Ŀ���λ
    private String toalleys;			//Ŀ��������ɶ���
    private String part_allocation_flag;//�Ƿ������ַ��� N-��,Y-��
    private String unit_flag;			//������λ 1.���� 2.ë�� 3.���� 4.���
    
    //��������
    private String ruleconfigname;		//������������
    private String tozonename;			//Ŀ���������
    private String tolocationname;		//Ŀ���λ����
    private String unitflagname;		//������λ 1.���� 2.ë�� 3.���� 4.���
    
    //Constructors
    public RuleAllocationDetail(){}


    //Property accessors
    
	/**
	 * ���ܣ���÷��������ϸID
	 */
	public String getAllocationdetailid() {
		return allocationdetailid;
	}


	/**
	 * ���ܣ����÷��������ϸID
	 * @param allocationdetailid
	 */
	public void setAllocationdetailid(String allocationdetailid) {
		this.allocationdetailid = allocationdetailid;
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
	 * ���ܣ�����Ƿ����
	 */
	public String getApart_flag() {
		return apart_flag;
	}


	/**
	 * ���ܣ������Ƿ����
	 * @param apart_flag
	 */
	public void setApart_flag(String apart_flag) {
		this.apart_flag = apart_flag;
	}


	/**
	 * ���ܣ�����Ƿ��Զ�������������
	 */
	public String getAuto_flag() {
		return auto_flag;
	}


	/**
	 * ���ܣ������Ƿ��Զ�������������
	 * @param auto_flag
	 */
	public void setAuto_flag(String auto_flag) {
		this.auto_flag = auto_flag;
	}


	/**
	 * ���ܣ�����Ƿ�洢λ��ѡ
	 */
	public String getBulkpick_flag() {
		return bulkpick_flag;
	}


	/**
	 * ���ܣ������Ƿ�洢λ��ѡ
	 * @param bulkpick_flag
	 */
	public void setBulkpick_flag(String bulkpick_flag) {
		this.bulkpick_flag = bulkpick_flag;
	}


	/**
	 * ���ܣ�����Ƿ����
	 */
	public String getClearloc_flag() {
		return clearloc_flag;
	}


	/**
	 * ���ܣ������Ƿ����
	 * @param clearloc_flag
	 */
	public void setClearloc_flag(String clearloc_flag) {
		this.clearloc_flag = clearloc_flag;
	}


	/**
	 * ���ܣ�����Ƿ���Ч
	 */
	public String getEnableflag() {
		return enableflag;
	}


	/**
	 * ���ܣ������Ƿ���Ч
	 * @param enableflag
	 */
	public void setEnableflag(String enableflag) {
		this.enableflag = enableflag;
	}


	/**
	 * ���ܣ�����Ƿ���λ��������
	 */
	public String getOver_flag() {
		return over_flag;
	}


	/**
	 * ���ܣ������Ƿ���λ��������
	 * @param over_flag
	 */
	public void setOver_flag(String over_flag) {
		this.over_flag = over_flag;
	}


	/**
	 * ���ܣ�����Ƿ���
	 */
	public String getPart_flag() {
		return part_flag;
	}


	/**
	 * ���ܣ������Ƿ���
	 * @param part_flag
	 */
	public void setPart_flag(String part_flag) {
		this.part_flag = part_flag;
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
	 * ���ܣ��������˳λ
	 */
	public int getSort() {
		return sort;
	}


	/**
	 * ���ܣ���������˳λ
	 * @param sort
	 */
	public void setSort(int sort) {
		this.sort = sort;
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
	 * ���ܣ�����Ƿ������ַ��� N-��,Y-��
	 */
	public String getPart_allocation_flag() {
		return part_allocation_flag;
	}


	/**
	 * ���ܣ������Ƿ������ַ��� N-��,Y-��
	 * @param part_allocation_flag
	 */
	public void setPart_allocation_flag(String part_allocation_flag) {
		this.part_allocation_flag = part_allocation_flag;
	}


	/**
	 * ���ܣ����Ŀ��������ɶ���
	 */
	public String getToalleys() {
		return toalleys;
	}


	/**
	 * ���ܣ�����Ŀ��������ɶ���
	 * @param toalleys
	 */
	public void setToalleys(String toalleys) {
		this.toalleys = toalleys;
	}


	/**
	 * ���ܣ����Ŀ���λ
	 */
	public String getTolocationid() {
		return tolocationid;
	}


	/**
	 * ���ܣ�����Ŀ���λ
	 * @param tolocationid
	 */
	public void setTolocationid(String tolocationid) {
		this.tolocationid = tolocationid;
	}


	/**
	 * ���ܣ����Ŀ���λ����
	 */
	public String getTolocationname() {
		return tolocationname;
	}


	/**
	 * ���ܣ�����Ŀ���λ����
	 * @param tolocationname
	 */
	public void setTolocationname(String tolocationname) {
		this.tolocationname = tolocationname;
	}


	/**
	 * ���ܣ����Ŀ�����
	 */
	public String getTozone() {
		return tozone;
	}


	/**
	 * ���ܣ�����Ŀ�����
	 * @param tozone
	 */
	public void setTozone(String tozone) {
		this.tozone = tozone;
	}


	/**
	 * ���ܣ����Ŀ���������
	 */
	public String getTozonename() {
		return tozonename;
	}


	/**
	 * ���ܣ�����Ŀ���������
	 * @param tozonename
	 */
	public void setTozonename(String tozonename) {
		this.tozonename = tozonename;
	}


	/**
	 * ���ܣ���ü�����λ 1.���� 2.ë�� 3.���� 4.���
	 */
	public String getUnit_flag() {
		return unit_flag;
	}


	/**
	 * ���ܣ����ü�����λ 1.���� 2.ë�� 3.���� 4.���
	 * @param unit_flag
	 */
	public void setUnit_flag(String unit_flag) {
		this.unit_flag = unit_flag;
	}


	/**
	 * ���ܣ���ü�����λ 1.���� 2.ë�� 3.���� 4.���
	 */
	public String getUnitflagname() {
		return unitflagname;
	}


	/**
	 * ���ܣ����ü�����λ 1.���� 2.ë�� 3.���� 4.���
	 * @param unitflagname
	 */
	public void setUnitflagname(String unitflagname) {
		this.unitflagname = unitflagname;
	}
}
