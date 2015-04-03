package com.wms3.bms.standard.entity.inventory;

/**
 * 
 * 描述: 库存调整单详细
 * @since WMS 3.0
 */
public class InventoryAdjustDetail  implements java.io.Serializable {
    
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6038598254721034915L;
	
	private String adjustdetailid;	//调整单明细ID
	private String adjustid;		//调整单ID
	private String status;			//状态
	private String lotinfo;		   //批号
	private String cargo_space_id;	//货位ID
	private String wh_area_id;		//库区ID
	private String packid;			//包装ID
	private String punit;			//计量单位
	private String productid;		//产品ID
	private String lotid;			//批次ID
	private String lotatt1;			//批次属性值1
	private String lotatt2;			//批次属性值2
	private String lotatt3;			//批次属性值3
	private String lotatt4;			//批次属性值4
	private String lotatt5;			//批次属性值5
	private String lotatt6;			//批次属性值6
	private String lotatt7;			//批次属性值7
	private String lotatt8;			//批次属性值8
	private String lotatt9;			//批次属性值9
	private String lotatt10;		//批次属性值10
	private String lotatt11;		//批次属性值11
	private String lotatt12;		//批次属性值12
	private String traycode;		//托盘条码
	private String injobid;			//作业ID
	private String injobetailid;	//作业详细ID
	private double pnum;			//库存数量
	private String producttime;		//生产日期
	private double pweight;			//库存重量
	private double pnetweight;		//库存净重
	private double tovolume;		//目标库存体积
	private double tonum;			//目标库存数量
	private double toweight;		//目标库存重量
	private double tonetweight;		//目标库存净重
	private String createtime;		//创建时间
	private String adjusttime;		//调整时间
	private String createmanid;		//创建人
	private String inventoryid;		//库存ID
	private String warehouseid;		//仓库ID
	
	//派生属性
	private String customername;	//客户名称
	private String productname;	//客户名称
	
	//新增字段
    private String boxcode;            //箱条码
    private String productcode;        //产品条码

	// Constructors
    /** default constructor */
    public InventoryAdjustDetail() {
    }

    
    // Property accessors
	/**
	 * 功能：获得调整单明细ID
	 */
	public String getAdjustdetailid() {
		return adjustdetailid;
	}

	/**
	 * 功能：设置调整单明细ID
	 * @param adjustdetailid
	 */
	public void setAdjustdetailid(String adjustdetailid) {
		this.adjustdetailid = adjustdetailid;
	}

	/**
	 * 功能：获得调整单ID
	 */
	public String getAdjustid() {
		return adjustid;
	}

	/**
	 * 功能：设置调整单ID
	 * @param adjustid
	 */
	public void setAdjustid(String adjustid) {
		this.adjustid = adjustid;
	}

	/**
	 * 功能：获得调整时间
	 */
	public String getAdjusttime() {
		return adjusttime;
	}

	/**
	 * 功能：设置调整时间
	 * @param adjusttime
	 */
	public void setAdjusttime(String adjusttime) {
		this.adjusttime = adjusttime;
	}

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
	 * 功能：获得创建时间
	 */
	public String getCreatetime() {
		return createtime;
	}

	/**
	 * 功能：设置创建时间
	 * @param createtime
	 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	/**
	 * 功能：获取批号
	 * @return
	 */
	public String getLotinfo() {
		return lotinfo;
	}
	/**
	 * 功能：设置批号
	 * @return
	 */
	public void setLotinfo(String lotinfo) {
		this.lotinfo = lotinfo;
	}


	/**
	 * 功能：获得作业详细ID
	 */
	public String getInjobetailid() {
		return injobetailid;
	}

	/**
	 * 功能：设置作业详细ID
	 * @param injobetailid
	 */
	public void setInjobetailid(String injobetailid) {
		this.injobetailid = injobetailid;
	}

	/**
	 * 功能：获得作业ID
	 */
	public String getInjobid() {
		return injobid;
	}

	/**
	 * 功能：设置作业ID
	 * @param injobid
	 */
	public void setInjobid(String injobid) {
		this.injobid = injobid;
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
	 * 功能：获得包装ID
	 */
	public String getPackid() {
		return packid;
	}

	/**
	 * 功能：设置包装ID
	 * @param packid
	 */
	public void setPackid(String packid) {
		this.packid = packid;
	}

	/**
	 * 功能：获得库存净重
	 */
	public double getPnetweight() {
		return pnetweight;
	}

	/**
	 * 功能：设置库存净重
	 * @param pnetweight
	 */
	public void setPnetweight(double pnetweight) {
		this.pnetweight = pnetweight;
	}

	/**
	 * 功能：获得库存数量
	 */
	public double getPnum() {
		return pnum;
	}

	/**
	 * 功能：设置库存数量
	 * @param pnum
	 */
	public void setPnum(double pnum) {
		this.pnum = pnum;
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
	 * 功能：获得计量单位
	 */
	public String getPunit() {
		return punit;
	}

	/**
	 * 功能：设置计量单位
	 * @param punit
	 */
	public void setPunit(String punit) {
		this.punit = punit;
	}

   //
	public String getProducttime() {
		return producttime;
	}


	public void setProducttime(String producttime) {
		this.producttime = producttime;
	}


	/**
	 * 功能：获得库存重量
	 */
	public double getPweight() {
		return pweight;
	}

	/**
	 * 功能：设置库存重量
	 * @param pweight
	 */
	public void setPweight(double pweight) {
		this.pweight = pweight;
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
	 * 功能：获得目标库存净重
	 */
	public double getTonetweight() {
		return tonetweight;
	}

	/**
	 * 功能：设置目标库存净重
	 * @param tonetweight
	 */
	public void setTonetweight(double tonetweight) {
		this.tonetweight = tonetweight;
	}

	/**
	 * 功能：获得目标库存数量
	 */
	public double getTonum() {
		return tonum;
	}

	/**
	 * 功能：设置目标库存数量
	 * @param tonum
	 */
	public void setTonum(double tonum) {
		this.tonum = tonum;
	}

	/**
	 * 功能：获得目标库存体积
	 */
	public double getTovolume() {
		return tovolume;
	}

	/**
	 * 功能：设置目标库存体积
	 * @param tovolume
	 */
	public void setTovolume(double tovolume) {
		this.tovolume = tovolume;
	}

	/**
	 * 功能：获得目标库存重量
	 */
	public double getToweight() {
		return toweight;
	}

	/**
	 * 功能：设置目标库存重量
	 * @param toweight
	 */
	public void setToweight(double toweight) {
		this.toweight = toweight;
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
	 * 功能：获得客户名称
	 */
	public String getCustomername() {
		return customername;
	}


	/**
	 * 功能：设置客户名称
	 * @param customername
	 */
	public void setCustomername(String customername) {
		this.customername = customername;
	}


	public String getProductname() {
		return productname;
	}


	public void setProductname(String productname) {
		this.productname = productname;
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
    
    
}