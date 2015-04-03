package com.wms3.bms.standard.entity.inventory.lxyy;


import java.io.Serializable;

/**
 * 描述: 冻结、释放
 * @author hug 2012-10-15
 * @since WMS 3.0
 */
public class InventoryHold implements Serializable{
    
    /** serialVersionUID */
    private static final long serialVersionUID = 4775618188329586131L;
    
    private String inventoryholdid; //ID
    private String status;          //状态(默认1,直接冻结)
    private String holdflag;        //是否冻结 Y-是,N-否
    private String holdby;          //冻结方法 1.直接冻结 2.移至不合格品区
    private String holdcode;        //冻结原因 1.产品过期 2.产品损坏
    private String holdreason;      //原因描述
    private String inventoryid;     //库存ID
    private String ownerid;         //货主ID
    private String productid ;      //产品ID
    private String lotid;           //批次号
    private String spaceid;         //货位
    private double qtyonhold;       //冻结数量
    private double netweightonhold; //冻结重量
    private String dateon;          //冻结时间
    private String whoon;           //冻结操作人
    private String dateoff;         //释放时间
    private String whooff;          //释放操作人
    private String traycode;        //托盘条码
    
    //新增字段
    private String boxcode;            //箱条码
    private String productcode;        //产品条码
    
    //派生属性
	private String cargoSpaceName;		//货位名称
	private String productName;			//物品名称
	private String ownerName;			//货主名称
	private String holdbyName;			//冻结方法名称
	private String holdcodeName;		//冻结原因名称
	private String lotName;				//批次名称
	private String whoonName;			//冻结操作人名称
	
    
    /** default constructor */
    public InventoryHold() {
    }
    
    /**
     * 功能：获得箱条码
     * @author hug 2012-4-16 
     * @return
     */
    public String getBoxcode() {
        return boxcode;
    }
    /**
     * 功能：设置箱条码
     * @author hug 2012-4-16 
     * @param boxcode
     */
    public void setBoxcode(String boxcode) {
        this.boxcode = boxcode;
    }

    /**
     * 功能：获得产品条码
     * @author hug 2012-10-18 
     * @return
     */
    public String getProductcode() {
        return productcode;
    }

    /**
     * 功能：设置产品条码
     * @author hug 2012-10-18 
     * @param productcode
     */
    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }
	/**
	 * 功能：获得释放时间
	 */
	public String getDateoff() {
		return dateoff;
	}
	/**
	 * 功能：设置释放时间
	 * @param dateoff
	 */
	public void setDateoff(String dateoff) {
		this.dateoff = dateoff;
	}
	/**
	 * 功能：获得冻结时间
	 */
	public String getDateon() {
		return dateon;
	}
	/**
	 * 功能：设置冻结时间
	 * @param dateon
	 */
	public void setDateon(String dateon) {
		this.dateon = dateon;
	}
	/**
	 * 功能：获得冻结方法
	 */
	public String getHoldby() {
		return holdby;
	}
	/**
	 * 功能：设置冻结方法
	 * @param holdby
	 */
	public void setHoldby(String holdby) {
		this.holdby = holdby;
	}
	/**
	 * 功能：获得冻结原因
	 */
	public String getHoldcode() {
		return holdcode;
	}
	/**
	 * 功能：设置冻结原因
	 * @param holdcode
	 */
	public void setHoldcode(String holdcode) {
		this.holdcode = holdcode;
	}
	/**
	 * 功能：获得是否冻结 Y-是,N-否
	 */
	public String getHoldflag() {
		return holdflag;
	}
	/**
	 * 功能：设置是否冻结 Y-是,N-否
	 * @param holdflag
	 */
	public void setHoldflag(String holdflag) {
		this.holdflag = holdflag;
	}
	/**
	 * 功能：获得原因描述
	 */
	public String getHoldreason() {
		return holdreason;
	}
	/**
	 * 功能：设置原因描述
	 * @param holdreason
	 */
	public void setHoldreason(String holdreason) {
		this.holdreason = holdreason;
	}
	/**
	 * 功能：获得ID
	 */
	public String getInventoryholdid() {
		return inventoryholdid;
	}
	/**
	 * 功能：设置ID
	 * @param inventoryholdid
	 */
	public void setInventoryholdid(String inventoryholdid) {
		this.inventoryholdid = inventoryholdid;
	}
	/**
	 * 功能：获得库存ID
	 */
	public String getInventoryid() {
		return inventoryid;
	}
	/**
	 * 功能：设置库存ID
	 * @param inventoryid
	 */
	public void setInventoryid(String inventoryid) {
		this.inventoryid = inventoryid;
	}
	/**
	 * 功能：获得批次号
	 */
	public String getLotid() {
		return lotid;
	}
	/**
	 * 功能：设置批次号
	 * @param lotid
	 */
	public void setLotid(String lotid) {
		this.lotid = lotid;
	}
	/**
	 * 功能：获得货主ID
	 */
	public String getOwnerid() {
		return ownerid;
	}
	/**
	 * 功能：设置货主ID
	 * @param ownerid
	 */
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
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
	 * 功能：获得冻结数量
	 */
	public double getQtyonhold() {
		return qtyonhold;
	}
	/**
	 * 功能：设置冻结数量
	 * @param qtyonhold
	 */
	public void setQtyonhold(double qtyonhold) {
		this.qtyonhold = qtyonhold;
	}
	/**
	 * 功能：获得货位
	 */
	public String getSpaceid() {
		return spaceid;
	}
	/**
	 * 功能：设置货位
	 * @param spaceid
	 */
	public void setSpaceid(String spaceid) {
		this.spaceid = spaceid;
	}
	/**
	 * 功能：获得状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 功能：设置状态
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * 功能：获得释放操作人
	 */
	public String getWhooff() {
		return whooff;
	}
	/**
	 * 功能：设置释放操作人
	 * @param whooff
	 */
	public void setWhooff(String whooff) {
		this.whooff = whooff;
	}
	/**
	 * 功能：获得冻结操作人
	 */
	public String getWhoon() {
		return whoon;
	}
	/**
	 * 功能：设置冻结操作人
	 * @param whoon
	 */
	public void setWhoon(String whoon) {
		this.whoon = whoon;
	}

	/**
	 * 功能：获得冻结重量
	 */
	public double getNetweightonhold() {
		return netweightonhold;
	}

	/**
	 * 功能：设置冻结重量
	 * @param netweightonhold
	 */
	public void setNetweightonhold(double netweightonhold) {
		this.netweightonhold = netweightonhold;
	}

	/**
	 * 功能：获得货位名称
	 */
	public String getCargoSpaceName() {
		return cargoSpaceName;
	}

	/**
	 * 功能：设置货位名称
	 * @param cargoSpaceName
	 */
	public void setCargoSpaceName(String cargoSpaceName) {
		this.cargoSpaceName = cargoSpaceName;
	}

	/**
	 * 功能：获得冻结方法名称
	 */
	public String getHoldbyName() {
		return holdbyName;
	}

	/**
	 * 功能：设置冻结方法名称
	 * @param holdbyName
	 */
	public void setHoldbyName(String holdbyName) {
		this.holdbyName = holdbyName;
	}

	/**
	 * 功能：获得冻结原因名称
	 */
	public String getHoldcodeName() {
		return holdcodeName;
	}

	/**
	 * 功能：设置冻结原因名称
	 * @param holdcodeName
	 */
	public void setHoldcodeName(String holdcodeName) {
		this.holdcodeName = holdcodeName;
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

	/**
	 * 功能：获得批次名称
	 */
	public String getLotName() {
		return lotName;
	}

	/**
	 * 功能：设置批次名称
	 * @param lotName
	 */
	public void setLotName(String lotName) {
		this.lotName = lotName;
	}

	/**
	 * 功能：获得冻结操作人名称
	 */
	public String getWhoonName() {
		return whoonName;
	}

	/**
	 * 功能：设置冻结操作人名称
	 * @param whoonName
	 */
	public void setWhoonName(String whoonName) {
		this.whoonName = whoonName;
	}


}
