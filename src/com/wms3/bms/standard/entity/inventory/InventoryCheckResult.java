package com.wms3.bms.standard.entity.inventory;

/**
 * 
 * 描述: 盘点损益
 * @since WMS 3.0
 */
public class InventoryCheckResult  implements java.io.Serializable {


	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7708781566956400334L;

	private String checkid;			//ID
	private String taskid;			//盘点任务ID
	private String customerid;		//客户ID
	private String productid;		//产品ID
	private String traycode;		//托盘条码
	private double num;				//库存数量
	private double checknum;		//盘点数量
	private double netweight;		//库存重量
	private double checknetweight;	//盘点重量
	private String checktime;		//盘点时间
	private String createmanid;		//操作人

	//派生属性
	private String productName;			//物品名称
	private String ownerName;			//货主名称
	private String createman;			//创建人

	
	
	// Constructors
    /** default constructor */
    public InventoryCheckResult() {
    }


    
    // Property accessors

	/**
	 * 功能：获得创建人
	 */
	public String getCreatemanid() {
		return createmanid;
	}



	/**
	 * 功能：设置创建人
	 * @param createmanid
	 */
	public void setCreatemanid(String createmanid) {
		this.createmanid = createmanid;
	}



	/**
	 * 功能：获得客户ID
	 */
	public String getCustomerid() {
		return customerid;
	}



	/**
	 * 功能：设置客户ID
	 * @param customerid
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	
	/**
	 * 功能：获得产品ID
	 */
	public String getProductid() {
		return productid;
	}



	/**
	 * 功能：设置产品ID
	 * @param productid
	 */
	public void setProductid(String productid) {
		this.productid = productid;
	}



	/**
	 * 功能：获得托盘条码
	 */
	public String getTraycode() {
		return traycode;
	}



	/**
	 * 功能：设置托盘条码
	 * @param traycode
	 */
	public void setTraycode(String traycode) {
		this.traycode = traycode;
	}



	/**
	 * 功能：获得ID
	 */
	public String getCheckid() {
		return checkid;
	}



	/**
	 * 功能：设置ID
	 * @param checkid
	 */
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}



	/**
	 * 功能：获得盘点数量
	 */
	public double getChecknum() {
		return checknum;
	}



	/**
	 * 功能：设置盘点数量
	 * @param checknum
	 */
	public void setChecknum(double checknum) {
		this.checknum = checknum;
	}



	/**
	 * 功能：获得盘点时间
	 */
	public String getChecktime() {
		return checktime;
	}



	/**
	 * 功能：设置盘点时间
	 * @param checktime
	 */
	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}



	/**
	 * 功能：获得库存数量
	 */
	public double getNum() {
		return num;
	}



	/**
	 * 功能：设置库存数量
	 * @param num
	 */
	public void setNum(double num) {
		this.num = num;
	}



	/**
	 * 功能：获得盘点任务ID
	 */
	public String getTaskid() {
		return taskid;
	}



	/**
	 * 功能：设置盘点任务ID
	 * @param taskid
	 */
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
    
    


	/**
	 * 功能：获得盘点重量
	 */
	public double getChecknetweight() {
		return checknetweight;
	}



	/**
	 * 功能：设置盘点重量
	 * @param checknetweight
	 */
	public void setChecknetweight(double checknetweight) {
		this.checknetweight = checknetweight;
	}



	/**
	 * 功能：获得库存重量
	 */
	public double getNetweight() {
		return netweight;
	}



	/**
	 * 功能：设置库存重量
	 * @param netweight
	 */
	public void setNetweight(double netweight) {
		this.netweight = netweight;
	}


	/**
	 * 功能：获得创建人
	 */
	public String getCreateman() {
		return createman;
	}



	/**
	 * 功能：设置创建人
	 * @param createman
	 */
	public void setCreateman(String createman) {
		this.createman = createman;
	}



	/**
	 * 功能：获得货主名称
	 */
	public String getOwnerName() {
		return ownerName;
	}



	/**
	 * 功能：设置货主名称
	 * @param ownerName
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}



	/**
	 * 功能：获得物品名称
	 */
	public String getProductName() {
		return productName;
	}



	/**
	 * 功能：设置物品名称
	 * @param productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}



    
    
}