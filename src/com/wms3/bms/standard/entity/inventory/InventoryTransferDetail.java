package com.wms3.bms.standard.entity.inventory;

/**
 * 
 * 描述: 库存转移单详细
 * @since WMS 3.0
 */
public class InventoryTransferDetail  implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5523017946735375971L;
	
	private String transferdetailid;//库存转移单详细ID
	private String transferid;		//库存转移单编号
	private String status;			//状态 0:创建 1:完成
	private String cargo_space_id;	//货位ID
	private String wh_area_id;		//库区ID
	private String fmcustomerid;	//FM客户ID
	private String fmpackid;		//FM包装ID
	private String fmpunit;			//FM计量单位
	private String fmproductid;		//FM产品ID
	private String fmlotid;			//FM批次ID
	private String fmlotatt1;		//FM批次属性值1
	private String fmlotatt2;		//FM批次属性值2
	private String fmlotatt3;		//FM批次属性值3
	private String fmlotatt4;		//FM批次属性值4
	private String fmlotatt5;		//FM批次属性值5
	private String fmlotatt6;		//FM批次属性值6
	private String fmlotatt7;		//FM批次属性值7
	private String fmlotatt8;		//FM批次属性值8
	private String fmlotatt9;		//FM批次属性值9
	private String fmlotatt10;		//FM批次属性值10
	private String fmlotatt11;		//FM批次属性值11
	private String fmlotatt12;		//FM批次属性值12
	private String tocustomerid;	//TO客户ID
	private String topackid;		//TO包装ID
	private String topunit;			//TO计量单位
	private String toproductid;		//TO产品ID
	private String tolotid;			//TO批次ID
	private String tolotatt1;		//TO批次属性值1
	private String tolotatt2;		//TO批次属性值2
	private String tolotatt3;		//TO批次属性值3
	private String tolotatt4;		//TO批次属性值4
	private String tolotatt5;		//TO批次属性值5
	private String tolotatt6;		//TO批次属性值6
	private String tolotatt7;		//TO批次属性值7
	private String tolotatt8;		//TO批次属性值8
	private String tolotatt9;		//TO批次属性值9
	private String tolotatt10;		//TO批次属性值10
	private String tolotatt11;		//TO批次属性值11
	private String tolotatt12;		//TO批次属性值12
	private String totraycode;		//TO托盘条码
	private String fmtraycode;		//FM托盘条码
	private String injobid;			//作业ID
	private String injobetailid;	//作业详细ID
	private double pnum;			//库存数量
	private double pvolume;			//库存体积
	private double pweight;			//库存重量
	private double pnetweight;		//库存净重
	private String createtime;		//创建时间
	private String transfertime;	//转移时间
	private String createmanid;		//创建人
	private String inventoryid;		//库存ID
	private String warehouseid;		//仓库ID
	
	//派生属性
	private String fmcustomername;	//FM客户名称
	private String tocustomername;	//TO客户名称
	
	private String fmlotname;	//FM批次名称
	private String tolotname;	//TO批次名称
	
	private String fmproductname;	//FM产品名称
	private String toproductname;	//TO产品名称
	
	//新增字段
    private String boxcode;            //箱条码
    private String productcode;        //产品条码

	// Constructors
    /** default constructor */
    public InventoryTransferDetail() {
    }

    
    //Property accessors

	/**
	 * 功能：获得操作人
	 */
	public String getCreatemanid() {
		return createmanid;
	}


	/**
	 * 功能：设置操作人
	 * @param createmanid
	 */
	public void setCreatemanid(String createmanid) {
		this.createmanid = createmanid;
	}


	/**
	 * 功能：获得FM客户ID
	 */
	public String getFmcustomerid() {
		return fmcustomerid;
	}


	/**
	 * 功能：设置FM客户ID
	 * @param fmcustomerid
	 */
	public void setFmcustomerid(String fmcustomerid) {
		this.fmcustomerid = fmcustomerid;
	}


	/**
	 * 功能：获得FM批次属性值1
	 */
	public String getFmlotatt1() {
		return fmlotatt1;
	}


	/**
	 * 功能：设置FM批次属性值1
	 * @param fmlotatt1
	 */
	public void setFmlotatt1(String fmlotatt1) {
		this.fmlotatt1 = fmlotatt1;
	}


	/**
	 * 功能：获得FM批次属性值10
	 */
	public String getFmlotatt10() {
		return fmlotatt10;
	}


	/**
	 * 功能：设置FM批次属性值10
	 * @param fmlotatt10
	 */
	public void setFmlotatt10(String fmlotatt10) {
		this.fmlotatt10 = fmlotatt10;
	}


	/**
	 * 功能：获得FM批次属性值11
	 */
	public String getFmlotatt11() {
		return fmlotatt11;
	}


	/**
	 * 功能：设置FM批次属性值11
	 * @param fmlotatt11
	 */
	public void setFmlotatt11(String fmlotatt11) {
		this.fmlotatt11 = fmlotatt11;
	}


	/**
	 * 功能：获得FM批次属性值12
	 */
	public String getFmlotatt12() {
		return fmlotatt12;
	}


	/**
	 * 功能：设置FM批次属性值12
	 * @param fmlotatt12
	 */
	public void setFmlotatt12(String fmlotatt12) {
		this.fmlotatt12 = fmlotatt12;
	}


	/**
	 * 功能：获得FM批次属性值2
	 */
	public String getFmlotatt2() {
		return fmlotatt2;
	}


	/**
	 * 功能：设置FM批次属性值2
	 * @param fmlotatt2
	 */
	public void setFmlotatt2(String fmlotatt2) {
		this.fmlotatt2 = fmlotatt2;
	}


	/**
	 * 功能：获得FM批次属性值3
	 */
	public String getFmlotatt3() {
		return fmlotatt3;
	}


	/**
	 * 功能：设置FM批次属性值3
	 * @param fmlotatt3
	 */
	public void setFmlotatt3(String fmlotatt3) {
		this.fmlotatt3 = fmlotatt3;
	}


	/**
	 * 功能：获得FM批次属性值4
	 */
	public String getFmlotatt4() {
		return fmlotatt4;
	}


	/**
	 * 功能：设置FM批次属性值4
	 * @param fmlotatt4
	 */
	public void setFmlotatt4(String fmlotatt4) {
		this.fmlotatt4 = fmlotatt4;
	}


	/**
	 * 功能：获得FM批次属性值5
	 */
	public String getFmlotatt5() {
		return fmlotatt5;
	}


	/**
	 * 功能：设置FM批次属性值5
	 * @param fmlotatt5
	 */
	public void setFmlotatt5(String fmlotatt5) {
		this.fmlotatt5 = fmlotatt5;
	}


	/**
	 * 功能：获得FM批次属性值6
	 */
	public String getFmlotatt6() {
		return fmlotatt6;
	}


	/**
	 * 功能：设置FM批次属性值6
	 * @param fmlotatt6
	 */
	public void setFmlotatt6(String fmlotatt6) {
		this.fmlotatt6 = fmlotatt6;
	}


	/**
	 * 功能：获得FM批次属性值7
	 */
	public String getFmlotatt7() {
		return fmlotatt7;
	}


	/**
	 * 功能：设置FM批次属性值7
	 * @param fmlotatt7
	 */
	public void setFmlotatt7(String fmlotatt7) {
		this.fmlotatt7 = fmlotatt7;
	}


	/**
	 * 功能：获得FM批次属性值8
	 */
	public String getFmlotatt8() {
		return fmlotatt8;
	}


	/**
	 * 功能：设置FM批次属性值8
	 * @param fmlotatt8
	 */
	public void setFmlotatt8(String fmlotatt8) {
		this.fmlotatt8 = fmlotatt8;
	}


	/**
	 * 功能：获得FM批次属性值9
	 */
	public String getFmlotatt9() {
		return fmlotatt9;
	}


	/**
	 * 功能：设置FM批次属性值9
	 * @param fmlotatt9
	 */
	public void setFmlotatt9(String fmlotatt9) {
		this.fmlotatt9 = fmlotatt9;
	}


	/**
	 * 功能：获得FM批次ID
	 */
	public String getFmlotid() {
		return fmlotid;
	}


	/**
	 * 功能：设置FM批次ID
	 * @param fmlotid
	 */
	public void setFmlotid(String fmlotid) {
		this.fmlotid = fmlotid;
	}


	/**
	 * 功能：获得FM包装ID
	 */
	public String getFmpackid() {
		return fmpackid;
	}


	/**
	 * 功能：设置FM包装ID
	 * @param fmpackid
	 */
	public void setFmpackid(String fmpackid) {
		this.fmpackid = fmpackid;
	}


	/**
	 * 功能：获得FM产品ID
	 */
	public String getFmproductid() {
		return fmproductid;
	}


	/**
	 * 功能：设置FM产品ID
	 * @param fmproductid
	 */
	public void setFmproductid(String fmproductid) {
		this.fmproductid = fmproductid;
	}


	/**
	 * 功能：获得FM计量单位
	 */
	public String getFmpunit() {
		return fmpunit;
	}


	/**
	 * 功能：设置FM计量单位
	 * @param fmpunit
	 */
	public void setFmpunit(String fmpunit) {
		this.fmpunit = fmpunit;
	}


	/**
	 * 功能：获得FM托盘条码
	 */
	public String getFmtraycode() {
		return fmtraycode;
	}


	/**
	 * 功能：设置FM托盘条码
	 * @param fmtraycode
	 */
	public void setFmtraycode(String fmtraycode) {
		this.fmtraycode = fmtraycode;
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
	 * 功能：获得TO客户ID
	 */
	public String getTocustomerid() {
		return tocustomerid;
	}


	/**
	 * 功能：设置TO客户ID
	 * @param tocustomerid
	 */
	public void setTocustomerid(String tocustomerid) {
		this.tocustomerid = tocustomerid;
	}


	/**
	 * 功能：获得TO批次属性值1
	 */
	public String getTolotatt1() {
		return tolotatt1;
	}


	/**
	 * 功能：设置TO批次属性值1
	 * @param tolotatt1
	 */
	public void setTolotatt1(String tolotatt1) {
		this.tolotatt1 = tolotatt1;
	}


	/**
	 * 功能：获得TO批次属性值10
	 */
	public String getTolotatt10() {
		return tolotatt10;
	}


	/**
	 * 功能：设置TO批次属性值10
	 * @param tolotatt10
	 */
	public void setTolotatt10(String tolotatt10) {
		this.tolotatt10 = tolotatt10;
	}


	/**
	 * 功能：获得TO批次属性值11
	 */
	public String getTolotatt11() {
		return tolotatt11;
	}


	/**
	 * 功能：设置TO批次属性值11
	 * @param tolotatt11
	 */
	public void setTolotatt11(String tolotatt11) {
		this.tolotatt11 = tolotatt11;
	}


	/**
	 * 功能：获得TO批次属性值12
	 */
	public String getTolotatt12() {
		return tolotatt12;
	}


	/**
	 * 功能：设置TO批次属性值12
	 * @param tolotatt12
	 */
	public void setTolotatt12(String tolotatt12) {
		this.tolotatt12 = tolotatt12;
	}


	/**
	 * 功能：获得TO批次属性值2
	 */
	public String getTolotatt2() {
		return tolotatt2;
	}


	/**
	 * 功能：设置TO批次属性值2
	 * @param tolotatt2
	 */
	public void setTolotatt2(String tolotatt2) {
		this.tolotatt2 = tolotatt2;
	}


	/**
	 * 功能：获得TO批次属性值3
	 */
	public String getTolotatt3() {
		return tolotatt3;
	}


	/**
	 * 功能：设置TO批次属性值3
	 * @param tolotatt3
	 */
	public void setTolotatt3(String tolotatt3) {
		this.tolotatt3 = tolotatt3;
	}


	/**
	 * 功能：获得TO批次属性值4
	 */
	public String getTolotatt4() {
		return tolotatt4;
	}


	/**
	 * 功能：设置TO批次属性值4
	 * @param tolotatt4
	 */
	public void setTolotatt4(String tolotatt4) {
		this.tolotatt4 = tolotatt4;
	}


	/**
	 * 功能：获得TO批次属性值5
	 */
	public String getTolotatt5() {
		return tolotatt5;
	}


	/**
	 * 功能：设置TO批次属性值5
	 * @param tolotatt5
	 */
	public void setTolotatt5(String tolotatt5) {
		this.tolotatt5 = tolotatt5;
	}


	/**
	 * 功能：获得TO批次属性值6
	 */
	public String getTolotatt6() {
		return tolotatt6;
	}


	/**
	 * 功能：设置TO批次属性值6
	 * @param tolotatt6
	 */
	public void setTolotatt6(String tolotatt6) {
		this.tolotatt6 = tolotatt6;
	}


	/**
	 * 功能：获得TO批次属性值7
	 */
	public String getTolotatt7() {
		return tolotatt7;
	}


	/**
	 * 功能：设置TO批次属性值7
	 * @param tolotatt7
	 */
	public void setTolotatt7(String tolotatt7) {
		this.tolotatt7 = tolotatt7;
	}


	/**
	 * 功能：获得TO批次属性值8
	 */
	public String getTolotatt8() {
		return tolotatt8;
	}


	/**
	 * 功能：设置TO批次属性值8
	 * @param tolotatt8
	 */
	public void setTolotatt8(String tolotatt8) {
		this.tolotatt8 = tolotatt8;
	}


	/**
	 * 功能：获得TO批次属性值9
	 */
	public String getTolotatt9() {
		return tolotatt9;
	}


	/**
	 * 功能：设置TO批次属性值9
	 * @param tolotatt9
	 */
	public void setTolotatt9(String tolotatt9) {
		this.tolotatt9 = tolotatt9;
	}


	/**
	 * 功能：获得TO批次ID
	 */
	public String getTolotid() {
		return tolotid;
	}


	/**
	 * 功能：设置TO批次ID
	 * @param tolotid
	 */
	public void setTolotid(String tolotid) {
		this.tolotid = tolotid;
	}


	/**
	 * 功能：获得TO包装ID
	 */
	public String getTopackid() {
		return topackid;
	}


	/**
	 * 功能：设置TO包装ID
	 * @param topackid
	 */
	public void setTopackid(String topackid) {
		this.topackid = topackid;
	}


	/**
	 * 功能：获得TO产品ID
	 */
	public String getToproductid() {
		return toproductid;
	}


	/**
	 * 功能：设置TO产品ID
	 * @param toproductid
	 */
	public void setToproductid(String toproductid) {
		this.toproductid = toproductid;
	}


	/**
	 * 功能：获得TO计量单位
	 */
	public String getTopunit() {
		return topunit;
	}


	/**
	 * 功能：设置TO计量单位
	 * @param topunit
	 */
	public void setTopunit(String topunit) {
		this.topunit = topunit;
	}


	/**
	 * 功能：获得TO托盘条码
	 */
	public String getTotraycode() {
		return totraycode;
	}


	/**
	 * 功能：设置TO托盘条码
	 * @param totraycode
	 */
	public void setTotraycode(String totraycode) {
		this.totraycode = totraycode;
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
	 * 功能：获得库存体积
	 */
	public double getPvolume() {
		return pvolume;
	}


	/**
	 * 功能：设置库存体积
	 * @param pvolume
	 */
	public void setPvolume(double pvolume) {
		this.pvolume = pvolume;
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
	 * 功能：获得库存转移单详细ID
	 */
	public String getTransferdetailid() {
		return transferdetailid;
	}


	/**
	 * 功能：设置库存转移单详细ID
	 * @param transferdetailid
	 */
	public void setTransferdetailid(String transferdetailid) {
		this.transferdetailid = transferdetailid;
	}


	/**
	 * 功能：获得库存转移单编号
	 */
	public String getTransferid() {
		return transferid;
	}


	/**
	 * 功能：设置库存转移单编号
	 * @param transferid
	 */
	public void setTransferid(String transferid) {
		this.transferid = transferid;
	}


	/**
	 * 功能：获得转移时间
	 */
	public String getTransfertime() {
		return transfertime;
	}


	/**
	 * 功能：设置转移时间
	 * @param transfertime
	 */
	public void setTransfertime(String transfertime) {
		this.transfertime = transfertime;
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


	public String getFmcustomername() {
		return fmcustomername;
	}


	public void setFmcustomername(String fmcustomername) {
		this.fmcustomername = fmcustomername;
	}


	public String getTocustomername() {
		return tocustomername;
	}


	public void setTocustomername(String tocustomername) {
		this.tocustomername = tocustomername;
	}


	public String getFmlotname() {
		return fmlotname;
	}


	public void setFmlotname(String fmlotname) {
		this.fmlotname = fmlotname;
	}


	public String getFmproductname() {
		return fmproductname;
	}


	public void setFmproductname(String fmproductname) {
		this.fmproductname = fmproductname;
	}


	public String getTolotname() {
		return tolotname;
	}


	public void setTolotname(String tolotname) {
		this.tolotname = tolotname;
	}


	public String getToproductname() {
		return toproductname;
	}


	public void setToproductname(String toproductname) {
		this.toproductname = toproductname;
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