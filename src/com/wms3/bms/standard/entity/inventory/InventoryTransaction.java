package com.wms3.bms.standard.entity.inventory;


/**
 * 描述：库存交易表
 * @author hug
 *
 */
public class InventoryTransaction  implements java.io.Serializable {
	 
	
	 /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6058652395729659760L;
	
	private String transactionid;		//交易ID
	private String transactiontype;		//交易类型
	private String transstatus;			//交易状态
	private String doctype;				//单证类型
	private String docid;				//单证ID
	private String doclineno;			//单证行号
	private String fmcargo_space_id;	//FM货位ID
	private String fmwh_area_id;		//FM库区ID
	private String fmcustomerid;		//FM客户ID
	private String fmpackid;			//FM包装ID
	private String fmpunit;				//FM计量单位
	private String fmproductid;			//FM产品ID
	private String fmlotid;			//FM批次ID
	public  String fmlotatt1;		//FM批次属性值1
	public  String fmlotatt2;		//FM批次属性值2
	public  String fmlotatt3;		//FM批次属性值3
	public  String fmlotatt4;		//FM批次属性值4
	public  String fmlotatt5;		//FM批次属性值5
	public  String fmlotatt6;		//FM批次属性值6
	public  String fmlotatt7;		//FM批次属性值7
	public  String fmlotatt8;		//FM批次属性值8
	public  String fmlotatt9;		//FM批次属性值9
	public  String fmlotatt10;		//FM批次属性值10
	public  String fmlotatt11;		//FM批次属性值11
	public  String fmlotatt12;		//FM批次属性值12
	private String tocargo_space_id;	//TO货位ID
	private String towh_area_id;		//TO库区ID
	private String tocustomerid;		//TO客户ID
	private String topackid;		//TO包装ID
	private String topunit;			//TO计量单位
	private String toproductid;		//TO产品ID
	private String tolotid;			//TO批次ID
	public  String tolotatt1;		//TO批次属性值1
	public  String tolotatt2;		//TO批次属性值2
	public  String tolotatt3;		//TO批次属性值3
	public  String tolotatt4;		//TO批次属性值4
	public  String tolotatt5;		//TO批次属性值5
	public  String tolotatt6;		//TO批次属性值6
	public  String tolotatt7;		//TO批次属性值7
	public  String tolotatt8;		//TO批次属性值8
	public  String tolotatt9;		//TO批次属性值9
	public  String tolotatt10;		//TO批次属性值10
	public  String tolotatt11;		//TO批次属性值11
	public  String tolotatt12;		//TO批次属性值12
	private String totraycode;		//TO托盘条码
	private String injobid;			//作业ID
	private String injobetailid;		//作业详细ID
	private String transactiontime;		//操作时间
	private String createmanid;		//操作人
	private String inventoryid;		//库存ID
	private double fmnum;			//FM库存数量
	private double fmvolume;		//FM库存体积
	private double fmweight;		//FM库存重量
	private double fmnetweight;		//FM库存净重
	private double tonetweight;		//TO库存净重
	private double toweight;		//TO库存重量
	private double tonum;			//TO库存数量
	private String reasoncode;		//原因代码
	private String reason;			//原因
	private double tovolume;		//TO库存体积
	private String fmtraycode;		//FM托盘条码
	private String warehouseid;		//仓库ID
    
    //新增字段
    private String boxcode;            //箱条码
    private String productcode;        //产品条码

    private String fmcargo_space_name;	//FM货位名
    private String fmwh_area_name;		//FM库区名
    private String tocargo_space_name;	//FM货位名
    private String towh_area_name;		//FM库区名
    private String tocustomername;		//TO客户	名
    private String fmcustomername;		//FM客户名
    private String fmproductname;	    //FM产品名
    private String toproductname;	    //TO产品名
	/** default constructor */
    public InventoryTransaction() {
    }



	// Property accessors

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
	 * 功能：获得单证ID
	 */
	public String getDocid() {
		return docid;
	}


	/**
	 * 功能：设置单证ID
	 * @param docid
	 */
	public void setDocid(String docid) {
		this.docid = docid;
	}


	/**
	 * 功能：获得单证行号
	 */
	public String getDoclineno() {
		return doclineno;
	}


	/**
	 * 功能：设置单证行号
	 * @param doclineno
	 */
	public void setDoclineno(String doclineno) {
		this.doclineno = doclineno;
	}


	/**
	 * 功能：获得单证类型
	 */
	public String getDoctype() {
		return doctype;
	}


	/**
	 * 功能：设置单证类型
	 * @param doctype
	 */
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}


	/**
	 * 功能：获得FM货位ID
	 */
	public String getFmcargo_space_id() {
		return fmcargo_space_id;
	}


	/**
	 * 功能：设置FM货位ID
	 * @param fmcargo_space_id
	 */
	public void setFmcargo_space_id(String fmcargo_space_id) {
		this.fmcargo_space_id = fmcargo_space_id;
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
	 * 功能：获得M库存净重
	 */
	public double getFmnetweight() {
		return fmnetweight;
	}


	/**
	 * 功能：设置M库存净重
	 * @param fmnetweight
	 */
	public void setFmnetweight(double fmnetweight) {
		this.fmnetweight = fmnetweight;
	}


	/**
	 * 功能：获得FM库存数量
	 */
	public double getFmnum() {
		return fmnum;
	}


	/**
	 * 功能：设置FM库存数量
	 * @param fmnum
	 */
	public void setFmnum(double fmnum) {
		this.fmnum = fmnum;
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
	 * 功能：获得FM库存体积
	 */
	public double getFmvolume() {
		return fmvolume;
	}


	/**
	 * 功能：设置FM库存体积
	 * @param fmvolume
	 */
	public void setFmvolume(double fmvolume) {
		this.fmvolume = fmvolume;
	}


	/**
	 * 功能：获得FM库存重量
	 */
	public double getFmweight() {
		return fmweight;
	}


	/**
	 * 功能：设置FM库存重量
	 * @param fmweight
	 */
	public void setFmweight(double fmweight) {
		this.fmweight = fmweight;
	}


	/**
	 * 功能：获得FM库区ID
	 */
	public String getFmwh_area_id() {
		return fmwh_area_id;
	}


	/**
	 * 功能：设置FM库区ID
	 * @param fmwh_area_id
	 */
	public void setFmwh_area_id(String fmwh_area_id) {
		this.fmwh_area_id = fmwh_area_id;
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
	 * 功能：获得原因
	 */
	public String getReason() {
		return reason;
	}


	/**
	 * 功能：设置原因
	 * @param reason
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}


	/**
	 * 功能：获得原因代码
	 */
	public String getReasoncode() {
		return reasoncode;
	}


	/**
	 * 功能：设置原因代码
	 * @param reasoncode
	 */
	public void setReasoncode(String reasoncode) {
		this.reasoncode = reasoncode;
	}


	/**
	 * 功能：获得TO货位ID
	 */
	public String getTocargo_space_id() {
		return tocargo_space_id;
	}


	/**
	 * 功能：设置TO货位ID
	 * @param tocargo_space_id
	 */
	public void setTocargo_space_id(String tocargo_space_id) {
		this.tocargo_space_id = tocargo_space_id;
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
	 * 功能：获得TO库存净重
	 */
	public double getTonetweight() {
		return tonetweight;
	}


	/**
	 * 功能：设置TO库存净重
	 * @param tonetweight
	 */
	public void setTonetweight(double tonetweight) {
		this.tonetweight = tonetweight;
	}


	/**
	 * 功能：获得TO库存数量
	 */
	public double getTonum() {
		return tonum;
	}


	/**
	 * 功能：设置TO库存数量
	 * @param tonum
	 */
	public void setTonum(double tonum) {
		this.tonum = tonum;
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
	 * 功能：获得TO库存体积
	 */
	public double getTovolume() {
		return tovolume;
	}


	/**
	 * 功能：设置TO库存体积
	 * @param tovolume
	 */
	public void setTovolume(double tovolume) {
		this.tovolume = tovolume;
	}


	/**
	 * 功能：获得TO库存重量
	 */
	public double getToweight() {
		return toweight;
	}


	/**
	 * 功能：设置TO库存重量
	 * @param toweight
	 */
	public void setToweight(double toweight) {
		this.toweight = toweight;
	}


	/**
	 * 功能：获得TO库区ID
	 */
	public String getTowh_area_id() {
		return towh_area_id;
	}


	/**
	 * 功能：设置TO库区ID
	 * @param towh_area_id
	 */
	public void setTowh_area_id(String towh_area_id) {
		this.towh_area_id = towh_area_id;
	}


	/**
	 * 功能：获得交易ID
	 */
	public String getTransactionid() {
		return transactionid;
	}


	/**
	 * 功能：设置交易ID
	 * @param transactionid
	 */
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}


	/**
	 * 功能：获得操作时间
	 */
	public String getTransactiontime() {
		return transactiontime;
	}


	/**
	 * 功能：设置操作时间
	 * @param transactiontime
	 */
	public void setTransactiontime(String transactiontime) {
		this.transactiontime = transactiontime;
	}


	/**
	 * 功能：获得交易类型
	 */
	public String getTransactiontype() {
		return transactiontype;
	}


	/**
	 * 功能：设置交易类型
	 * @param transactiontype
	 */
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}


	/**
	 * 功能：获得交易状态
	 */
	public String getTransstatus() {
		return transstatus;
	}


	/**
	 * 功能：设置交易状态
	 * @param transstatus
	 */
	public void setTransstatus(String transstatus) {
		this.transstatus = transstatus;
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
     * 功能：获得fm货位名
     * @author liuxh 2012-12-25 
     * @return
     */
	public String getFmcargo_space_name() {
		return fmcargo_space_name;
	}


	/**
     * 功能：获得fm库区
     * @author liuxh 2012-12-25 
     * @return
     */
	public String getFmwh_area_name() {
		return fmwh_area_name;
	}

	/**
     * 功能：获得to货位名
     * @author liuxh 2012-12-25 
     * @return
     */

	public String getTocargo_space_name() {
		return tocargo_space_name;
	}


	/**
     * 功能：获得to库区名
     * @author liuxh 2012-12-25 
     * @return
     */
	public String getTowh_area_name() {
		return towh_area_name;
	}

	/**
     * 功能：设置fm库区名
     * @author liuxh 2012-12-25 
     * @return
     */

	public void setFmcargo_space_name(String fmcargoSpaceName) {
		fmcargo_space_name = fmcargoSpaceName;
	}


	/**
     * 功能：获得fm库区名
     * @author liuxh 2012-12-25 
     * @return
     */
	public void setFmwh_area_name(String fmwhAreaName) {
		fmwh_area_name = fmwhAreaName;
	}
	/**
     * 功能：设置to货位名
     * @author liuxh 2012-12-25 
     * @return
     */


	public void setTocargo_space_name(String tocargoSpaceName) {
		tocargo_space_name = tocargoSpaceName;
	}

	/**
     * 功能：设置to库区名
     * @author liuxh 2012-12-25 
     * @return
     */

	public void setTowh_area_name(String towhAreaName) {
		towh_area_name = towhAreaName;
	}

	/**
     * 功能：获得to客户名
     * @author liuxh 2012-12-25 
     * @return
     */

	public String getTocustomername() {
		return tocustomername;
	}

	/**
     * 功能：设置to客户名
     * @author liuxh 2012-12-25 
     * @return
     */

	public void setTocustomername(String tocustomername) {
		this.tocustomername = tocustomername;
	}


	/**
     * 功能：获得fm客户名
     * @author liuxh 2012-12-25 
     * @return
     */
	public String getFmcustomername() {
		return fmcustomername;
	}

	/**
     * 功能：设置fm客户名
     * @author liuxh 2012-12-25 
     * @return
     */

	public void setFmcustomername(String fmcustomername) {
		this.fmcustomername = fmcustomername;
	}


	/**
     * 功能：获得fm产品名
     * @author liuxh 2012-12-25 
     * @return
     */
	public String getFmproductname() {
		return fmproductname;
	}


	/**
     * 功能：设置fm产品名
     * @author liuxh 2012-12-25 
     * @return
     */
	public void setFmproductname(String fmproductname) {
		this.fmproductname = fmproductname;
	}


	/**
     * 功能：获得to产品名
     * @author liuxh 2012-12-25 
     * @return
     */
	public String getToproductname() {
		return toproductname;
	}


	/**
     * 功能：设置to产品名
     * @author liuxh 2012-12-25 
     * @return
     */

	public void setToproductname(String toproductname) {
		this.toproductname = toproductname;
	}

}