package com.wms3.bms.standard.entity.inventory;

/**
 * 
 * ����: �̵�����
 * @since WMS 3.0
 */
public class InventoryCheckResult  implements java.io.Serializable {


	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7708781566956400334L;

	private String checkid;			//ID
	private String taskid;			//�̵�����ID
	private String customerid;		//�ͻ�ID
	private String productid;		//��ƷID
	private String traycode;		//��������
	private double num;				//�������
	private double checknum;		//�̵�����
	private double netweight;		//�������
	private double checknetweight;	//�̵�����
	private String checktime;		//�̵�ʱ��
	private String createmanid;		//������

	//��������
	private String productName;			//��Ʒ����
	private String ownerName;			//��������
	private String createman;			//������

	
	
	// Constructors
    /** default constructor */
    public InventoryCheckResult() {
    }


    
    // Property accessors

	/**
	 * ���ܣ���ô�����
	 */
	public String getCreatemanid() {
		return createmanid;
	}



	/**
	 * ���ܣ����ô�����
	 * @param createmanid
	 */
	public void setCreatemanid(String createmanid) {
		this.createmanid = createmanid;
	}



	/**
	 * ���ܣ���ÿͻ�ID
	 */
	public String getCustomerid() {
		return customerid;
	}



	/**
	 * ���ܣ����ÿͻ�ID
	 * @param customerid
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	
	/**
	 * ���ܣ���ò�ƷID
	 */
	public String getProductid() {
		return productid;
	}



	/**
	 * ���ܣ����ò�ƷID
	 * @param productid
	 */
	public void setProductid(String productid) {
		this.productid = productid;
	}



	/**
	 * ���ܣ������������
	 */
	public String getTraycode() {
		return traycode;
	}



	/**
	 * ���ܣ�������������
	 * @param traycode
	 */
	public void setTraycode(String traycode) {
		this.traycode = traycode;
	}



	/**
	 * ���ܣ����ID
	 */
	public String getCheckid() {
		return checkid;
	}



	/**
	 * ���ܣ�����ID
	 * @param checkid
	 */
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}



	/**
	 * ���ܣ�����̵�����
	 */
	public double getChecknum() {
		return checknum;
	}



	/**
	 * ���ܣ������̵�����
	 * @param checknum
	 */
	public void setChecknum(double checknum) {
		this.checknum = checknum;
	}



	/**
	 * ���ܣ�����̵�ʱ��
	 */
	public String getChecktime() {
		return checktime;
	}



	/**
	 * ���ܣ������̵�ʱ��
	 * @param checktime
	 */
	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}



	/**
	 * ���ܣ���ÿ������
	 */
	public double getNum() {
		return num;
	}



	/**
	 * ���ܣ����ÿ������
	 * @param num
	 */
	public void setNum(double num) {
		this.num = num;
	}



	/**
	 * ���ܣ�����̵�����ID
	 */
	public String getTaskid() {
		return taskid;
	}



	/**
	 * ���ܣ������̵�����ID
	 * @param taskid
	 */
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
    
    


	/**
	 * ���ܣ�����̵�����
	 */
	public double getChecknetweight() {
		return checknetweight;
	}



	/**
	 * ���ܣ������̵�����
	 * @param checknetweight
	 */
	public void setChecknetweight(double checknetweight) {
		this.checknetweight = checknetweight;
	}



	/**
	 * ���ܣ���ÿ������
	 */
	public double getNetweight() {
		return netweight;
	}



	/**
	 * ���ܣ����ÿ������
	 * @param netweight
	 */
	public void setNetweight(double netweight) {
		this.netweight = netweight;
	}


	/**
	 * ���ܣ���ô�����
	 */
	public String getCreateman() {
		return createman;
	}



	/**
	 * ���ܣ����ô�����
	 * @param createman
	 */
	public void setCreateman(String createman) {
		this.createman = createman;
	}



	/**
	 * ���ܣ���û�������
	 */
	public String getOwnerName() {
		return ownerName;
	}



	/**
	 * ���ܣ����û�������
	 * @param ownerName
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}



	/**
	 * ���ܣ������Ʒ����
	 */
	public String getProductName() {
		return productName;
	}



	/**
	 * ���ܣ�������Ʒ����
	 * @param productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}



    
    
}