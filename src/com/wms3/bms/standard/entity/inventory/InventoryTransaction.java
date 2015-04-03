package com.wms3.bms.standard.entity.inventory;


/**
 * ��������潻�ױ�
 * @author hug
 *
 */
public class InventoryTransaction  implements java.io.Serializable {
	 
	
	 /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6058652395729659760L;
	
	private String transactionid;		//����ID
	private String transactiontype;		//��������
	private String transstatus;			//����״̬
	private String doctype;				//��֤����
	private String docid;				//��֤ID
	private String doclineno;			//��֤�к�
	private String fmcargo_space_id;	//FM��λID
	private String fmwh_area_id;		//FM����ID
	private String fmcustomerid;		//FM�ͻ�ID
	private String fmpackid;			//FM��װID
	private String fmpunit;				//FM������λ
	private String fmproductid;			//FM��ƷID
	private String fmlotid;			//FM����ID
	public  String fmlotatt1;		//FM��������ֵ1
	public  String fmlotatt2;		//FM��������ֵ2
	public  String fmlotatt3;		//FM��������ֵ3
	public  String fmlotatt4;		//FM��������ֵ4
	public  String fmlotatt5;		//FM��������ֵ5
	public  String fmlotatt6;		//FM��������ֵ6
	public  String fmlotatt7;		//FM��������ֵ7
	public  String fmlotatt8;		//FM��������ֵ8
	public  String fmlotatt9;		//FM��������ֵ9
	public  String fmlotatt10;		//FM��������ֵ10
	public  String fmlotatt11;		//FM��������ֵ11
	public  String fmlotatt12;		//FM��������ֵ12
	private String tocargo_space_id;	//TO��λID
	private String towh_area_id;		//TO����ID
	private String tocustomerid;		//TO�ͻ�ID
	private String topackid;		//TO��װID
	private String topunit;			//TO������λ
	private String toproductid;		//TO��ƷID
	private String tolotid;			//TO����ID
	public  String tolotatt1;		//TO��������ֵ1
	public  String tolotatt2;		//TO��������ֵ2
	public  String tolotatt3;		//TO��������ֵ3
	public  String tolotatt4;		//TO��������ֵ4
	public  String tolotatt5;		//TO��������ֵ5
	public  String tolotatt6;		//TO��������ֵ6
	public  String tolotatt7;		//TO��������ֵ7
	public  String tolotatt8;		//TO��������ֵ8
	public  String tolotatt9;		//TO��������ֵ9
	public  String tolotatt10;		//TO��������ֵ10
	public  String tolotatt11;		//TO��������ֵ11
	public  String tolotatt12;		//TO��������ֵ12
	private String totraycode;		//TO��������
	private String injobid;			//��ҵID
	private String injobetailid;		//��ҵ��ϸID
	private String transactiontime;		//����ʱ��
	private String createmanid;		//������
	private String inventoryid;		//���ID
	private double fmnum;			//FM�������
	private double fmvolume;		//FM������
	private double fmweight;		//FM�������
	private double fmnetweight;		//FM��澻��
	private double tonetweight;		//TO��澻��
	private double toweight;		//TO�������
	private double tonum;			//TO�������
	private String reasoncode;		//ԭ�����
	private String reason;			//ԭ��
	private double tovolume;		//TO������
	private String fmtraycode;		//FM��������
	private String warehouseid;		//�ֿ�ID
    
    //�����ֶ�
    private String boxcode;            //������
    private String productcode;        //��Ʒ����

    private String fmcargo_space_name;	//FM��λ��
    private String fmwh_area_name;		//FM������
    private String tocargo_space_name;	//FM��λ��
    private String towh_area_name;		//FM������
    private String tocustomername;		//TO�ͻ�	��
    private String fmcustomername;		//FM�ͻ���
    private String fmproductname;	    //FM��Ʒ��
    private String toproductname;	    //TO��Ʒ��
	/** default constructor */
    public InventoryTransaction() {
    }



	// Property accessors

	/**
	 * ���ܣ���ò�����
	 */
	public String getCreatemanid() {
		return createmanid;
	}


	/**
	 * ���ܣ����ò�����
	 * @param createmanid
	 */
	public void setCreatemanid(String createmanid) {
		this.createmanid = createmanid;
	}


	/**
	 * ���ܣ���õ�֤ID
	 */
	public String getDocid() {
		return docid;
	}


	/**
	 * ���ܣ����õ�֤ID
	 * @param docid
	 */
	public void setDocid(String docid) {
		this.docid = docid;
	}


	/**
	 * ���ܣ���õ�֤�к�
	 */
	public String getDoclineno() {
		return doclineno;
	}


	/**
	 * ���ܣ����õ�֤�к�
	 * @param doclineno
	 */
	public void setDoclineno(String doclineno) {
		this.doclineno = doclineno;
	}


	/**
	 * ���ܣ���õ�֤����
	 */
	public String getDoctype() {
		return doctype;
	}


	/**
	 * ���ܣ����õ�֤����
	 * @param doctype
	 */
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}


	/**
	 * ���ܣ����FM��λID
	 */
	public String getFmcargo_space_id() {
		return fmcargo_space_id;
	}


	/**
	 * ���ܣ�����FM��λID
	 * @param fmcargo_space_id
	 */
	public void setFmcargo_space_id(String fmcargo_space_id) {
		this.fmcargo_space_id = fmcargo_space_id;
	}


	/**
	 * ���ܣ����FM�ͻ�ID
	 */
	public String getFmcustomerid() {
		return fmcustomerid;
	}


	/**
	 * ���ܣ�����FM�ͻ�ID
	 * @param fmcustomerid
	 */
	public void setFmcustomerid(String fmcustomerid) {
		this.fmcustomerid = fmcustomerid;
	}


	/**
	 * ���ܣ����FM��������ֵ1
	 */
	public String getFmlotatt1() {
		return fmlotatt1;
	}


	/**
	 * ���ܣ�����FM��������ֵ1
	 * @param fmlotatt1
	 */
	public void setFmlotatt1(String fmlotatt1) {
		this.fmlotatt1 = fmlotatt1;
	}


	/**
	 * ���ܣ����FM��������ֵ10
	 */
	public String getFmlotatt10() {
		return fmlotatt10;
	}


	/**
	 * ���ܣ�����FM��������ֵ10
	 * @param fmlotatt10
	 */
	public void setFmlotatt10(String fmlotatt10) {
		this.fmlotatt10 = fmlotatt10;
	}


	/**
	 * ���ܣ����FM��������ֵ11
	 */
	public String getFmlotatt11() {
		return fmlotatt11;
	}


	/**
	 * ���ܣ�����FM��������ֵ11
	 * @param fmlotatt11
	 */
	public void setFmlotatt11(String fmlotatt11) {
		this.fmlotatt11 = fmlotatt11;
	}


	/**
	 * ���ܣ����FM��������ֵ12
	 */
	public String getFmlotatt12() {
		return fmlotatt12;
	}


	/**
	 * ���ܣ�����FM��������ֵ12
	 * @param fmlotatt12
	 */
	public void setFmlotatt12(String fmlotatt12) {
		this.fmlotatt12 = fmlotatt12;
	}


	/**
	 * ���ܣ����FM��������ֵ2
	 */
	public String getFmlotatt2() {
		return fmlotatt2;
	}


	/**
	 * ���ܣ�����FM��������ֵ2
	 * @param fmlotatt2
	 */
	public void setFmlotatt2(String fmlotatt2) {
		this.fmlotatt2 = fmlotatt2;
	}


	/**
	 * ���ܣ����FM��������ֵ3
	 */
	public String getFmlotatt3() {
		return fmlotatt3;
	}


	/**
	 * ���ܣ�����FM��������ֵ3
	 * @param fmlotatt3
	 */
	public void setFmlotatt3(String fmlotatt3) {
		this.fmlotatt3 = fmlotatt3;
	}


	/**
	 * ���ܣ����FM��������ֵ4
	 */
	public String getFmlotatt4() {
		return fmlotatt4;
	}


	/**
	 * ���ܣ�����FM��������ֵ4
	 * @param fmlotatt4
	 */
	public void setFmlotatt4(String fmlotatt4) {
		this.fmlotatt4 = fmlotatt4;
	}


	/**
	 * ���ܣ����FM��������ֵ5
	 */
	public String getFmlotatt5() {
		return fmlotatt5;
	}


	/**
	 * ���ܣ�����FM��������ֵ5
	 * @param fmlotatt5
	 */
	public void setFmlotatt5(String fmlotatt5) {
		this.fmlotatt5 = fmlotatt5;
	}


	/**
	 * ���ܣ����FM��������ֵ6
	 */
	public String getFmlotatt6() {
		return fmlotatt6;
	}


	/**
	 * ���ܣ�����FM��������ֵ6
	 * @param fmlotatt6
	 */
	public void setFmlotatt6(String fmlotatt6) {
		this.fmlotatt6 = fmlotatt6;
	}


	/**
	 * ���ܣ����FM��������ֵ7
	 */
	public String getFmlotatt7() {
		return fmlotatt7;
	}


	/**
	 * ���ܣ�����FM��������ֵ7
	 * @param fmlotatt7
	 */
	public void setFmlotatt7(String fmlotatt7) {
		this.fmlotatt7 = fmlotatt7;
	}


	/**
	 * ���ܣ����FM��������ֵ8
	 */
	public String getFmlotatt8() {
		return fmlotatt8;
	}


	/**
	 * ���ܣ�����FM��������ֵ8
	 * @param fmlotatt8
	 */
	public void setFmlotatt8(String fmlotatt8) {
		this.fmlotatt8 = fmlotatt8;
	}


	/**
	 * ���ܣ����FM��������ֵ9
	 */
	public String getFmlotatt9() {
		return fmlotatt9;
	}


	/**
	 * ���ܣ�����FM��������ֵ9
	 * @param fmlotatt9
	 */
	public void setFmlotatt9(String fmlotatt9) {
		this.fmlotatt9 = fmlotatt9;
	}


	/**
	 * ���ܣ����FM����ID
	 */
	public String getFmlotid() {
		return fmlotid;
	}


	/**
	 * ���ܣ�����FM����ID
	 * @param fmlotid
	 */
	public void setFmlotid(String fmlotid) {
		this.fmlotid = fmlotid;
	}


	/**
	 * ���ܣ����M��澻��
	 */
	public double getFmnetweight() {
		return fmnetweight;
	}


	/**
	 * ���ܣ�����M��澻��
	 * @param fmnetweight
	 */
	public void setFmnetweight(double fmnetweight) {
		this.fmnetweight = fmnetweight;
	}


	/**
	 * ���ܣ����FM�������
	 */
	public double getFmnum() {
		return fmnum;
	}


	/**
	 * ���ܣ�����FM�������
	 * @param fmnum
	 */
	public void setFmnum(double fmnum) {
		this.fmnum = fmnum;
	}


	/**
	 * ���ܣ����FM��װID
	 */
	public String getFmpackid() {
		return fmpackid;
	}


	/**
	 * ���ܣ�����FM��װID
	 * @param fmpackid
	 */
	public void setFmpackid(String fmpackid) {
		this.fmpackid = fmpackid;
	}


	/**
	 * ���ܣ����FM��ƷID
	 */
	public String getFmproductid() {
		return fmproductid;
	}


	/**
	 * ���ܣ�����FM��ƷID
	 * @param fmproductid
	 */
	public void setFmproductid(String fmproductid) {
		this.fmproductid = fmproductid;
	}


	/**
	 * ���ܣ����FM������λ
	 */
	public String getFmpunit() {
		return fmpunit;
	}


	/**
	 * ���ܣ�����FM������λ
	 * @param fmpunit
	 */
	public void setFmpunit(String fmpunit) {
		this.fmpunit = fmpunit;
	}


	/**
	 * ���ܣ����FM��������
	 */
	public String getFmtraycode() {
		return fmtraycode;
	}


	/**
	 * ���ܣ�����FM��������
	 * @param fmtraycode
	 */
	public void setFmtraycode(String fmtraycode) {
		this.fmtraycode = fmtraycode;
	}


	/**
	 * ���ܣ����FM������
	 */
	public double getFmvolume() {
		return fmvolume;
	}


	/**
	 * ���ܣ�����FM������
	 * @param fmvolume
	 */
	public void setFmvolume(double fmvolume) {
		this.fmvolume = fmvolume;
	}


	/**
	 * ���ܣ����FM�������
	 */
	public double getFmweight() {
		return fmweight;
	}


	/**
	 * ���ܣ�����FM�������
	 * @param fmweight
	 */
	public void setFmweight(double fmweight) {
		this.fmweight = fmweight;
	}


	/**
	 * ���ܣ����FM����ID
	 */
	public String getFmwh_area_id() {
		return fmwh_area_id;
	}


	/**
	 * ���ܣ�����FM����ID
	 * @param fmwh_area_id
	 */
	public void setFmwh_area_id(String fmwh_area_id) {
		this.fmwh_area_id = fmwh_area_id;
	}


	/**
	 * ���ܣ������ҵ��ϸID
	 */
	public String getInjobetailid() {
		return injobetailid;
	}


	/**
	 * ���ܣ�������ҵ��ϸID
	 * @param injobetailid
	 */
	public void setInjobetailid(String injobetailid) {
		this.injobetailid = injobetailid;
	}


	/**
	 * ���ܣ������ҵID
	 */
	public String getInjobid() {
		return injobid;
	}


	/**
	 * ���ܣ�������ҵID
	 * @param injobid
	 */
	public void setInjobid(String injobid) {
		this.injobid = injobid;
	}


	/**
	 * ���ܣ���ÿ��ID
	 */
	public String getInventoryid() {
		return inventoryid;
	}


	/**
	 * ���ܣ����ÿ��ID
	 * @param inventoryid
	 */
	public void setInventoryid(String inventoryid) {
		this.inventoryid = inventoryid;
	}


	/**
	 * ���ܣ����ԭ��
	 */
	public String getReason() {
		return reason;
	}


	/**
	 * ���ܣ�����ԭ��
	 * @param reason
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}


	/**
	 * ���ܣ����ԭ�����
	 */
	public String getReasoncode() {
		return reasoncode;
	}


	/**
	 * ���ܣ�����ԭ�����
	 * @param reasoncode
	 */
	public void setReasoncode(String reasoncode) {
		this.reasoncode = reasoncode;
	}


	/**
	 * ���ܣ����TO��λID
	 */
	public String getTocargo_space_id() {
		return tocargo_space_id;
	}


	/**
	 * ���ܣ�����TO��λID
	 * @param tocargo_space_id
	 */
	public void setTocargo_space_id(String tocargo_space_id) {
		this.tocargo_space_id = tocargo_space_id;
	}


	/**
	 * ���ܣ����TO�ͻ�ID
	 */
	public String getTocustomerid() {
		return tocustomerid;
	}


	/**
	 * ���ܣ�����TO�ͻ�ID
	 * @param tocustomerid
	 */
	public void setTocustomerid(String tocustomerid) {
		this.tocustomerid = tocustomerid;
	}


	/**
	 * ���ܣ����TO��������ֵ1
	 */
	public String getTolotatt1() {
		return tolotatt1;
	}


	/**
	 * ���ܣ�����TO��������ֵ1
	 * @param tolotatt1
	 */
	public void setTolotatt1(String tolotatt1) {
		this.tolotatt1 = tolotatt1;
	}


	/**
	 * ���ܣ����TO��������ֵ10
	 */
	public String getTolotatt10() {
		return tolotatt10;
	}


	/**
	 * ���ܣ�����TO��������ֵ10
	 * @param tolotatt10
	 */
	public void setTolotatt10(String tolotatt10) {
		this.tolotatt10 = tolotatt10;
	}


	/**
	 * ���ܣ����TO��������ֵ11
	 */
	public String getTolotatt11() {
		return tolotatt11;
	}


	/**
	 * ���ܣ�����TO��������ֵ11
	 * @param tolotatt11
	 */
	public void setTolotatt11(String tolotatt11) {
		this.tolotatt11 = tolotatt11;
	}


	/**
	 * ���ܣ����TO��������ֵ12
	 */
	public String getTolotatt12() {
		return tolotatt12;
	}


	/**
	 * ���ܣ�����TO��������ֵ12
	 * @param tolotatt12
	 */
	public void setTolotatt12(String tolotatt12) {
		this.tolotatt12 = tolotatt12;
	}


	/**
	 * ���ܣ����TO��������ֵ2
	 */
	public String getTolotatt2() {
		return tolotatt2;
	}


	/**
	 * ���ܣ�����TO��������ֵ2
	 * @param tolotatt2
	 */
	public void setTolotatt2(String tolotatt2) {
		this.tolotatt2 = tolotatt2;
	}


	/**
	 * ���ܣ����TO��������ֵ3
	 */
	public String getTolotatt3() {
		return tolotatt3;
	}


	/**
	 * ���ܣ�����TO��������ֵ3
	 * @param tolotatt3
	 */
	public void setTolotatt3(String tolotatt3) {
		this.tolotatt3 = tolotatt3;
	}


	/**
	 * ���ܣ����TO��������ֵ4
	 */
	public String getTolotatt4() {
		return tolotatt4;
	}


	/**
	 * ���ܣ�����TO��������ֵ4
	 * @param tolotatt4
	 */
	public void setTolotatt4(String tolotatt4) {
		this.tolotatt4 = tolotatt4;
	}


	/**
	 * ���ܣ����TO��������ֵ5
	 */
	public String getTolotatt5() {
		return tolotatt5;
	}


	/**
	 * ���ܣ�����TO��������ֵ5
	 * @param tolotatt5
	 */
	public void setTolotatt5(String tolotatt5) {
		this.tolotatt5 = tolotatt5;
	}


	/**
	 * ���ܣ����TO��������ֵ6
	 */
	public String getTolotatt6() {
		return tolotatt6;
	}


	/**
	 * ���ܣ�����TO��������ֵ6
	 * @param tolotatt6
	 */
	public void setTolotatt6(String tolotatt6) {
		this.tolotatt6 = tolotatt6;
	}


	/**
	 * ���ܣ����TO��������ֵ7
	 */
	public String getTolotatt7() {
		return tolotatt7;
	}


	/**
	 * ���ܣ�����TO��������ֵ7
	 * @param tolotatt7
	 */
	public void setTolotatt7(String tolotatt7) {
		this.tolotatt7 = tolotatt7;
	}


	/**
	 * ���ܣ����TO��������ֵ8
	 */
	public String getTolotatt8() {
		return tolotatt8;
	}


	/**
	 * ���ܣ�����TO��������ֵ8
	 * @param tolotatt8
	 */
	public void setTolotatt8(String tolotatt8) {
		this.tolotatt8 = tolotatt8;
	}


	/**
	 * ���ܣ����TO��������ֵ9
	 */
	public String getTolotatt9() {
		return tolotatt9;
	}


	/**
	 * ���ܣ�����TO��������ֵ9
	 * @param tolotatt9
	 */
	public void setTolotatt9(String tolotatt9) {
		this.tolotatt9 = tolotatt9;
	}


	/**
	 * ���ܣ����TO����ID
	 */
	public String getTolotid() {
		return tolotid;
	}


	/**
	 * ���ܣ�����TO����ID
	 * @param tolotid
	 */
	public void setTolotid(String tolotid) {
		this.tolotid = tolotid;
	}


	/**
	 * ���ܣ����TO��澻��
	 */
	public double getTonetweight() {
		return tonetweight;
	}


	/**
	 * ���ܣ�����TO��澻��
	 * @param tonetweight
	 */
	public void setTonetweight(double tonetweight) {
		this.tonetweight = tonetweight;
	}


	/**
	 * ���ܣ����TO�������
	 */
	public double getTonum() {
		return tonum;
	}


	/**
	 * ���ܣ�����TO�������
	 * @param tonum
	 */
	public void setTonum(double tonum) {
		this.tonum = tonum;
	}


	/**
	 * ���ܣ����TO��װID
	 */
	public String getTopackid() {
		return topackid;
	}


	/**
	 * ���ܣ�����TO��װID
	 * @param topackid
	 */
	public void setTopackid(String topackid) {
		this.topackid = topackid;
	}


	/**
	 * ���ܣ����TO��ƷID
	 */
	public String getToproductid() {
		return toproductid;
	}


	/**
	 * ���ܣ�����TO��ƷID
	 * @param toproductid
	 */
	public void setToproductid(String toproductid) {
		this.toproductid = toproductid;
	}


	/**
	 * ���ܣ����TO������λ
	 */
	public String getTopunit() {
		return topunit;
	}


	/**
	 * ���ܣ�����TO������λ
	 * @param topunit
	 */
	public void setTopunit(String topunit) {
		this.topunit = topunit;
	}


	/**
	 * ���ܣ����TO��������
	 */
	public String getTotraycode() {
		return totraycode;
	}


	/**
	 * ���ܣ�����TO��������
	 * @param totraycode
	 */
	public void setTotraycode(String totraycode) {
		this.totraycode = totraycode;
	}


	/**
	 * ���ܣ����TO������
	 */
	public double getTovolume() {
		return tovolume;
	}


	/**
	 * ���ܣ�����TO������
	 * @param tovolume
	 */
	public void setTovolume(double tovolume) {
		this.tovolume = tovolume;
	}


	/**
	 * ���ܣ����TO�������
	 */
	public double getToweight() {
		return toweight;
	}


	/**
	 * ���ܣ�����TO�������
	 * @param toweight
	 */
	public void setToweight(double toweight) {
		this.toweight = toweight;
	}


	/**
	 * ���ܣ����TO����ID
	 */
	public String getTowh_area_id() {
		return towh_area_id;
	}


	/**
	 * ���ܣ�����TO����ID
	 * @param towh_area_id
	 */
	public void setTowh_area_id(String towh_area_id) {
		this.towh_area_id = towh_area_id;
	}


	/**
	 * ���ܣ���ý���ID
	 */
	public String getTransactionid() {
		return transactionid;
	}


	/**
	 * ���ܣ����ý���ID
	 * @param transactionid
	 */
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}


	/**
	 * ���ܣ���ò���ʱ��
	 */
	public String getTransactiontime() {
		return transactiontime;
	}


	/**
	 * ���ܣ����ò���ʱ��
	 * @param transactiontime
	 */
	public void setTransactiontime(String transactiontime) {
		this.transactiontime = transactiontime;
	}


	/**
	 * ���ܣ���ý�������
	 */
	public String getTransactiontype() {
		return transactiontype;
	}


	/**
	 * ���ܣ����ý�������
	 * @param transactiontype
	 */
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}


	/**
	 * ���ܣ���ý���״̬
	 */
	public String getTransstatus() {
		return transstatus;
	}


	/**
	 * ���ܣ����ý���״̬
	 * @param transstatus
	 */
	public void setTransstatus(String transstatus) {
		this.transstatus = transstatus;
	}


	/**
	 * ���ܣ���òֿ�ID
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
     * ���ܣ����������
     * @author hug 2012-4-16 
     * @return
     */
    public String getBoxcode() {
        return boxcode;
    }
    /**
     * ���ܣ�����������
     * @author hug 2012-4-16 
     * @param boxcode
     */
    public void setBoxcode(String boxcode) {
        this.boxcode = boxcode;
    }

    /**
     * ���ܣ���ò�Ʒ����
     * @author hug 2012-10-18 
     * @return
     */
    public String getProductcode() {
        return productcode;
    }

    /**
     * ���ܣ����ò�Ʒ����
     * @author hug 2012-10-18 
     * @param productcode
     */
    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }


    /**
     * ���ܣ����fm��λ��
     * @author liuxh 2012-12-25 
     * @return
     */
	public String getFmcargo_space_name() {
		return fmcargo_space_name;
	}


	/**
     * ���ܣ����fm����
     * @author liuxh 2012-12-25 
     * @return
     */
	public String getFmwh_area_name() {
		return fmwh_area_name;
	}

	/**
     * ���ܣ����to��λ��
     * @author liuxh 2012-12-25 
     * @return
     */

	public String getTocargo_space_name() {
		return tocargo_space_name;
	}


	/**
     * ���ܣ����to������
     * @author liuxh 2012-12-25 
     * @return
     */
	public String getTowh_area_name() {
		return towh_area_name;
	}

	/**
     * ���ܣ�����fm������
     * @author liuxh 2012-12-25 
     * @return
     */

	public void setFmcargo_space_name(String fmcargoSpaceName) {
		fmcargo_space_name = fmcargoSpaceName;
	}


	/**
     * ���ܣ����fm������
     * @author liuxh 2012-12-25 
     * @return
     */
	public void setFmwh_area_name(String fmwhAreaName) {
		fmwh_area_name = fmwhAreaName;
	}
	/**
     * ���ܣ�����to��λ��
     * @author liuxh 2012-12-25 
     * @return
     */


	public void setTocargo_space_name(String tocargoSpaceName) {
		tocargo_space_name = tocargoSpaceName;
	}

	/**
     * ���ܣ�����to������
     * @author liuxh 2012-12-25 
     * @return
     */

	public void setTowh_area_name(String towhAreaName) {
		towh_area_name = towhAreaName;
	}

	/**
     * ���ܣ����to�ͻ���
     * @author liuxh 2012-12-25 
     * @return
     */

	public String getTocustomername() {
		return tocustomername;
	}

	/**
     * ���ܣ�����to�ͻ���
     * @author liuxh 2012-12-25 
     * @return
     */

	public void setTocustomername(String tocustomername) {
		this.tocustomername = tocustomername;
	}


	/**
     * ���ܣ����fm�ͻ���
     * @author liuxh 2012-12-25 
     * @return
     */
	public String getFmcustomername() {
		return fmcustomername;
	}

	/**
     * ���ܣ�����fm�ͻ���
     * @author liuxh 2012-12-25 
     * @return
     */

	public void setFmcustomername(String fmcustomername) {
		this.fmcustomername = fmcustomername;
	}


	/**
     * ���ܣ����fm��Ʒ��
     * @author liuxh 2012-12-25 
     * @return
     */
	public String getFmproductname() {
		return fmproductname;
	}


	/**
     * ���ܣ�����fm��Ʒ��
     * @author liuxh 2012-12-25 
     * @return
     */
	public void setFmproductname(String fmproductname) {
		this.fmproductname = fmproductname;
	}


	/**
     * ���ܣ����to��Ʒ��
     * @author liuxh 2012-12-25 
     * @return
     */
	public String getToproductname() {
		return toproductname;
	}


	/**
     * ���ܣ�����to��Ʒ��
     * @author liuxh 2012-12-25 
     * @return
     */

	public void setToproductname(String toproductname) {
		this.toproductname = toproductname;
	}

}