package com.wms3.bms.standard.entity.rule;

/**
 * �������ϼܹ�����ϸ��
 * @author hug
 */
public class RulePutawayDetail implements java.io.Serializable{

    /**
	 * serialVersionUID
	 */
    private static final long serialVersionUID = 6677430147370893672L;
    
    //Fields    
    private String putawaydetailid;		//�ϼܹ�����ϸID
    private String putawayid;  			//�ϼܹ���ID
	private	int	sort;					//����˳λ
	private String ruleconfigid;		//��������ID
    private String enableflag;			//�Ƿ���Ч
    
    private String tozone;				//Ŀ�����
    private String tolocationid;		//Ŀ���λ
    
    private String loc_restrict;    	//��λ����
    
    private String loc_usage1;   		//��λʹ��1
    private String loc_usage2;   		//��λʹ��2
    private String loc_usage3;   		//��λʹ��3
    private String loc_usage4;   		//��λʹ��4
    private String loc_usage5;   		//��λʹ��5
    
    private String loc_handle1;   		//�洢����1
    private String loc_handle2;   		//�洢����2
    private String loc_handle3;   		//�洢����3
    private String loc_handle4;   		//�洢����4
    private String loc_handle5;   		//�洢����5
    
    private String lotid;    			//����ID
	private String lotatt1;  			//��������1
	private String lotatt2;  			//��������2
	private String lotatt3;  			//��������3
	private String lotatt4;  			//��������4
 	private String lotatt5;  			//��������5
	private String lotatt6;  			//��������6
 	private String lotatt7;  			//��������7
	private String lotatt8;  			//��������8
 	private String lotatt9;  			//��������9
 	private String lotatt10;  			//��������10
 	private String lotatt11;  			//��������11
 	private String lotatt12;  			//��������12
 	
    private String remark;    			//��ע
    
    // ��������
    private String ruleconfigname;		//������������
    private String tozonename;			//Ŀ���λ����
    private String tolocationname;		//Ŀ���λ����
    private String loc_restrictname;	//��λ��������
    private String loc_usagename1;		//��λʹ��1����
    private String loc_handlename1;		//�洢����1����
    

	//Constructors
    public RulePutawayDetail(){}


    //Property accessors

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
	 * ���ܣ���ô洢����1
	 */
	public String getLoc_handle1() {
		return loc_handle1;
	}


	/**
	 * ���ܣ����ô洢����1
	 * @param loc_handle1
	 */
	public void setLoc_handle1(String loc_handle1) {
		this.loc_handle1 = loc_handle1;
	}


	/**
	 * ���ܣ���ô洢����2
	 */
	public String getLoc_handle2() {
		return loc_handle2;
	}


	/**
	 * ���ܣ����ô洢����2
	 * @param loc_handle2
	 */
	public void setLoc_handle2(String loc_handle2) {
		this.loc_handle2 = loc_handle2;
	}


	/**
	 * ���ܣ���ô洢����3
	 */
	public String getLoc_handle3() {
		return loc_handle3;
	}


	/**
	 * ���ܣ����ô洢����3
	 * @param loc_handle3
	 */
	public void setLoc_handle3(String loc_handle3) {
		this.loc_handle3 = loc_handle3;
	}


	/**
	 * ���ܣ���ô洢����4
	 */
	public String getLoc_handle4() {
		return loc_handle4;
	}


	/**
	 * ���ܣ����ô洢����4
	 * @param loc_handle4
	 */
	public void setLoc_handle4(String loc_handle4) {
		this.loc_handle4 = loc_handle4;
	}


	/**
	 * ���ܣ���ô洢����5
	 */
	public String getLoc_handle5() {
		return loc_handle5;
	}


	/**
	 * ���ܣ����ô洢����5
	 * @param loc_handle5
	 */
	public void setLoc_handle5(String loc_handle5) {
		this.loc_handle5 = loc_handle5;
	}


	/**
	 * ���ܣ���ÿ�λ����
	 */
	public String getLoc_restrict() {
		return loc_restrict;
	}


	/**
	 * ���ܣ����ÿ�λ����
	 * @param loc_restrict
	 */
	public void setLoc_restrict(String loc_restrict) {
		this.loc_restrict = loc_restrict;
	}


	/**
	 * ���ܣ���ÿ�λʹ��1
	 */
	public String getLoc_usage1() {
		return loc_usage1;
	}


	/**
	 * ���ܣ����ÿ�λʹ��1
	 * @param loc_usage1
	 */
	public void setLoc_usage1(String loc_usage1) {
		this.loc_usage1 = loc_usage1;
	}


	/**
	 * ���ܣ���ÿ�λʹ��2
	 */
	public String getLoc_usage2() {
		return loc_usage2;
	}


	/**
	 * ���ܣ����ÿ�λʹ��2
	 * @param loc_usage2
	 */
	public void setLoc_usage2(String loc_usage2) {
		this.loc_usage2 = loc_usage2;
	}


	/**
	 * ���ܣ���ÿ�λʹ��3
	 */
	public String getLoc_usage3() {
		return loc_usage3;
	}


	/**
	 * ���ܣ����ÿ�λʹ��3
	 * @param loc_usage3
	 */
	public void setLoc_usage3(String loc_usage3) {
		this.loc_usage3 = loc_usage3;
	}


	/**
	 * ���ܣ���ÿ�λʹ��4
	 */
	public String getLoc_usage4() {
		return loc_usage4;
	}


	/**
	 * ���ܣ����ÿ�λʹ��4
	 * @param loc_usage4
	 */
	public void setLoc_usage4(String loc_usage4) {
		this.loc_usage4 = loc_usage4;
	}


	/**
	 * ���ܣ���ÿ�λʹ��5
	 */
	public String getLoc_usage5() {
		return loc_usage5;
	}


	/**
	 * ���ܣ����ÿ�λʹ��5
	 * @param loc_usage5
	 */
	public void setLoc_usage5(String loc_usage5) {
		this.loc_usage5 = loc_usage5;
	}


	/**
	 * ���ܣ��������ID
	 */
	public String getLotid() {
		return lotid;
	}


	/**
	 * ���ܣ���������ID
	 * @param lotid
	 */
	public void setLotid(String lotid) {
		this.lotid = lotid;
	}


	/**
	 * ���ܣ�����ϼܹ�����ϸID
	 */
	public String getPutawaydetailid() {
		return putawaydetailid;
	}


	/**
	 * ���ܣ������ϼܹ�����ϸID
	 * @param putawaydetailid
	 */
	public void setPutawaydetailid(String putawaydetailid) {
		this.putawaydetailid = putawaydetailid;
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
	 * ���ܣ���ÿ�λ��������
	 */
	public String getLoc_restrictname() {
		return loc_restrictname;
	}


	/**
	 * ���ܣ����ÿ�λ��������
	 * @param loc_restrictname
	 */
	public void setLoc_restrictname(String loc_restrictname) {
		this.loc_restrictname = loc_restrictname;
	}


	/**
	 * ���ܣ����Ŀ���λ����
	 */
	public String getTozonename() {
		return tozonename;
	}


	/**
	 * ���ܣ�����Ŀ���λ����
	 * @param tozonename
	 */
	public void setTozonename(String tozonename) {
		this.tozonename = tozonename;
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
	 * ���ܣ������������1
	 */
	public String getLotatt1() {
		return lotatt1;
	}


	/**
	 * ���ܣ�������������1
	 * @param lotatt1
	 */
	public void setLotatt1(String lotatt1) {
		this.lotatt1 = lotatt1;
	}


	/**
	 * ���ܣ������������10
	 */
	public String getLotatt10() {
		return lotatt10;
	}


	/**
	 * ���ܣ�������������10
	 * @param lotatt10
	 */
	public void setLotatt10(String lotatt10) {
		this.lotatt10 = lotatt10;
	}


	/**
	 * ���ܣ������������11
	 */
	public String getLotatt11() {
		return lotatt11;
	}


	/**
	 * ���ܣ�������������11
	 * @param lotatt11
	 */
	public void setLotatt11(String lotatt11) {
		this.lotatt11 = lotatt11;
	}


	/**
	 * ���ܣ������������12
	 */
	public String getLotatt12() {
		return lotatt12;
	}


	/**
	 * ���ܣ�������������12
	 * @param lotatt12
	 */
	public void setLotatt12(String lotatt12) {
		this.lotatt12 = lotatt12;
	}


	/**
	 * ���ܣ������������2
	 */
	public String getLotatt2() {
		return lotatt2;
	}


	/**
	 * ���ܣ�������������2
	 * @param lotatt2
	 */
	public void setLotatt2(String lotatt2) {
		this.lotatt2 = lotatt2;
	}


	/**
	 * ���ܣ������������3
	 */
	public String getLotatt3() {
		return lotatt3;
	}


	/**
	 * ���ܣ�������������3
	 * @param lotatt3
	 */
	public void setLotatt3(String lotatt3) {
		this.lotatt3 = lotatt3;
	}


	/**
	 * ���ܣ������������4
	 */
	public String getLotatt4() {
		return lotatt4;
	}


	/**
	 * ���ܣ�������������4
	 * @param lotatt4
	 */
	public void setLotatt4(String lotatt4) {
		this.lotatt4 = lotatt4;
	}


	/**
	 * ���ܣ������������5
	 */
	public String getLotatt5() {
		return lotatt5;
	}


	/**
	 * ���ܣ�������������5
	 * @param lotatt5
	 */
	public void setLotatt5(String lotatt5) {
		this.lotatt5 = lotatt5;
	}


	/**
	 * ���ܣ������������6
	 */
	public String getLotatt6() {
		return lotatt6;
	}


	/**
	 * ���ܣ�������������6
	 * @param lotatt6
	 */
	public void setLotatt6(String lotatt6) {
		this.lotatt6 = lotatt6;
	}


	/**
	 * ���ܣ������������7
	 */
	public String getLotatt7() {
		return lotatt7;
	}


	/**
	 * ���ܣ�������������7
	 * @param lotatt7
	 */
	public void setLotatt7(String lotatt7) {
		this.lotatt7 = lotatt7;
	}


	/**
	 * ���ܣ������������8
	 */
	public String getLotatt8() {
		return lotatt8;
	}


	/**
	 * ���ܣ�������������8
	 * @param lotatt8
	 */
	public void setLotatt8(String lotatt8) {
		this.lotatt8 = lotatt8;
	}


	/**
	 * ���ܣ������������9
	 */
	public String getLotatt9() {
		return lotatt9;
	}


	/**
	 * ���ܣ�������������9
	 * @param lotatt9
	 */
	public void setLotatt9(String lotatt9) {
		this.lotatt9 = lotatt9;
	}


	/**
	 * ���ܣ���ô洢����1����
	 */
	public String getLoc_handlename1() {
		return loc_handlename1;
	}


	/**
	 * ���ܣ����ô洢����1����
	 * @param loc_handlename1
	 */
	public void setLoc_handlename1(String loc_handlename1) {
		this.loc_handlename1 = loc_handlename1;
	}


	/**
	 * ���ܣ���ÿ�λʹ��1����
	 */
	public String getLoc_usagename1() {
		return loc_usagename1;
	}


	/**
	 * ���ܣ����ÿ�λʹ��1����
	 * @param loc_usagename1
	 */
	public void setLoc_usagename1(String loc_usagename1) {
		this.loc_usagename1 = loc_usagename1;
	}

}
