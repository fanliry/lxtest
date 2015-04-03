package com.wms3.bms.standard.entity.inventory.lxyy;

/**
 * 
 * 描述: 盘点申请
 * @since WMS 3.0
 */
public class InventoryCheckRequest  implements java.io.Serializable {

    
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4378900787650441282L;
	
	private String requestid;		//ID
	private String counttype;		//类型
	private String status;			//状态	1:新建；2：生成盘点任务；3:正在盘点；4：完成
	private int priority;			//优先级	1:高；2：较高；3:中；4：较低；5：低
	private String cargo_space_id;	//货位ID
	private String wh_area_id;		//库区ID
	private String lotinfo;		//批号
	private String productid;		//产品ID
	private String lotid;			//批次ID
	public String lotatt1;			//批次属性值1
	public String lotatt2;			//批次属性值2
	public String lotatt3;			//批次属性值3
	public String lotatt4;			//批次属性值4
	public String lotatt5;			//批次属性值5
	public String lotatt6;			//批次属性值6
	public String lotatt7;			//批次属性值7
	public String lotatt8;			//批次属性值8
	public String lotatt9;			//批次属性值9
	public String lotatt10;			//批次属性值10
	public String lotatt11;			//批次属性值11
	public String lotatt12;			//批次属性值12
	private String traycode;		//托盘条码
	private String requesttime;		//申请时间
	private String starttime;		//开始时间
	private String endtime;			//结束时间
	private String createmanid;		//创建人
	private String warehouseid;		//仓库ID

	//新增字段
    private String boxcode;            //箱条码
    private String productcode;        //产品条码
	
	//派生属性
    private String whAreaName;			//库区名称
	private String cargoSpaceName;		//货位名称
	private String productName;			//物品名称
	private String typeName;			//类型名称
	private String statusName;			//状态名称
	private String createman;			//创建人
	private String lotName;				//批次名称

	// Constructors
    /** default constructor */
    public InventoryCheckRequest() {
    }


    
    // Property accessors
    
	/**
	 * 功能：获得货位ID
	 */
	public String getCargo_space_id() {
		return cargo_space_id;
	}



	/**
	 * 功能：设置货位ID
	 * @param cargo_space_id
	 */
	public void setCargo_space_id(String cargo_space_id) {
		this.cargo_space_id = cargo_space_id;
	}



	/**
	 * 功能：获得类型
	 */
	public String getCounttype() {
		return counttype;
	}



	/**
	 * 功能：设置类型
	 * @param counttype
	 */
	public void setCounttype(String counttype) {
		this.counttype = counttype;
	}



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
	 * 功能：获得结束时间
	 */
	public String getEndtime() {
		return endtime;
	}



	/**
	 * 功能：设置结束时间
	 * @param endtime
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}



	/**
	 * 功能：获得批次属性值1
	 */
	public String getLotatt1() {
		return lotatt1;
	}



	/**
	 * 功能：设置批次属性值1
	 * @param lotatt1
	 */
	public void setLotatt1(String lotatt1) {
		this.lotatt1 = lotatt1;
	}



	/**
	 * 功能：获得批次属性值10
	 */
	public String getLotatt10() {
		return lotatt10;
	}



	/**
	 * 功能：设置批次属性值10
	 * @param lotatt10
	 */
	public void setLotatt10(String lotatt10) {
		this.lotatt10 = lotatt10;
	}



	/**
	 * 功能：获得批次属性值11
	 */
	public String getLotatt11() {
		return lotatt11;
	}



	/**
	 * 功能：设置批次属性值11
	 * @param lotatt11
	 */
	public void setLotatt11(String lotatt11) {
		this.lotatt11 = lotatt11;
	}



	/**
	 * 功能：获得批次属性值12
	 */
	public String getLotatt12() {
		return lotatt12;
	}



	/**
	 * 功能：设置批次属性值12
	 * @param lotatt12
	 */
	public void setLotatt12(String lotatt12) {
		this.lotatt12 = lotatt12;
	}



	/**
	 * 功能：获得批次属性值2
	 */
	public String getLotatt2() {
		return lotatt2;
	}



	/**
	 * 功能：设置批次属性值2
	 * @param lotatt2
	 */
	public void setLotatt2(String lotatt2) {
		this.lotatt2 = lotatt2;
	}



	/**
	 * 功能：获得批次属性值3
	 */
	public String getLotatt3() {
		return lotatt3;
	}



	/**
	 * 功能：设置批次属性值3
	 * @param lotatt3
	 */
	public void setLotatt3(String lotatt3) {
		this.lotatt3 = lotatt3;
	}



	/**
	 * 功能：获得批次属性值4
	 */
	public String getLotatt4() {
		return lotatt4;
	}



	/**
	 * 功能：设置批次属性值4
	 * @param lotatt4
	 */
	public void setLotatt4(String lotatt4) {
		this.lotatt4 = lotatt4;
	}



	/**
	 * 功能：获得批次属性值5
	 */
	public String getLotatt5() {
		return lotatt5;
	}



	/**
	 * 功能：设置批次属性值5
	 * @param lotatt5
	 */
	public void setLotatt5(String lotatt5) {
		this.lotatt5 = lotatt5;
	}



	/**
	 * 功能：获得批次属性值6
	 */
	public String getLotatt6() {
		return lotatt6;
	}



	/**
	 * 功能：设置批次属性值6
	 * @param lotatt6
	 */
	public void setLotatt6(String lotatt6) {
		this.lotatt6 = lotatt6;
	}



	/**
	 * 功能：获得批次属性值7
	 */
	public String getLotatt7() {
		return lotatt7;
	}



	/**
	 * 功能：设置批次属性值7
	 * @param lotatt7
	 */
	public void setLotatt7(String lotatt7) {
		this.lotatt7 = lotatt7;
	}



	/**
	 * 功能：获得批次属性值8
	 */
	public String getLotatt8() {
		return lotatt8;
	}



	/**
	 * 功能：设置批次属性值8
	 * @param lotatt8
	 */
	public void setLotatt8(String lotatt8) {
		this.lotatt8 = lotatt8;
	}



	/**
	 * 功能：获得批次属性值9
	 */
	public String getLotatt9() {
		return lotatt9;
	}



	/**
	 * 功能：设置批次属性值9
	 * @param lotatt9
	 */
	public void setLotatt9(String lotatt9) {
		this.lotatt9 = lotatt9;
	}



	/**
	 * 功能：获得批次ID
	 */
	public String getLotid() {
		return lotid;
	}



	/**
	 * 功能：设置批次ID
	 * @param lotid
	 */
	public void setLotid(String lotid) {
		this.lotid = lotid;
	}



	/**
	 * 功能：获得优先级
	 */
	public int getPriority() {
		return priority;
	}



	/**
	 * 功能：设置优先级
	 * @param priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
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
	 * 功能：获得ID
	 */
	public String getRequestid() {
		return requestid;
	}



	/**
	 * 功能：设置ID
	 * @param requestid
	 */
	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}



	/**
	 * 功能：获得申请时间
	 */
	public String getRequesttime() {
		return requesttime;
	}



	/**
	 * 功能：设置申请时间
	 * @param requesttime
	 */
	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}



	/**
	 * 功能：获得开始时间
	 */
	public String getStarttime() {
		return starttime;
	}



	/**
	 * 功能：设置开始时间
	 * @param starttime
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
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
	 * 功能：获得仓库ID
	 */
	public String getWarehouseid() {
		return warehouseid;
	}



	/**
	 * 功能：设置仓库ID
	 * @param warehouseid
	 */
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}



	/**
	 * 功能：获得库区ID
	 */
	public String getWh_area_id() {
		return wh_area_id;
	}



	/**
	 * 功能：设置库区ID
	 * @param wh_area_id
	 */
	public void setWh_area_id(String wh_area_id) {
		this.wh_area_id = wh_area_id;
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
	 * 功能：获得状态名称
	 */
	public String getStatusName() {
		return statusName;
	}



	/**
	 * 功能：设置状态名称
	 * @param statusName
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}



	/**
	 * 功能：获得类型名称
	 */
	public String getTypeName() {
		return typeName;
	}



	/**
	 * 功能：设置类型名称
	 * @param typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}



	/**
	 * 功能：获得库区名称
	 */
	public String getWhAreaName() {
		return whAreaName;
	}



	/**
	 * 功能：设置库区名称
	 * @param whAreaName
	 */
	public void setWhAreaName(String whAreaName) {
		this.whAreaName = whAreaName;
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
	 * 功能：设置批号
	 *@author liuxh 2013-3-11
	 *@param lotnumber
	 */
	public void setLotinfo(String lotnumber) {
		this.lotinfo = lotnumber;
	}


	/**
	 * 功能：获取批号
	 *@author liuxh 2013-3-11
	 *@param lotnumber
	 */
	public String getLotinfo() {
		return lotinfo;
	}
    
    
}