package com.wms3.bms.standard.entity.inbound.lxyy;

/**
 * 描述:采购入库订单
 * @author yao 2015-3-10
 * @since wmsBMS3.0
 */
public class InboundPoHeader implements java.io.Serializable {

	private static final long serialVersionUID = 6465928388683202095L;
	
	private String poid;// PO Id
	private String potype;// PO 类型  1采购入库  2退料入库  3其他入库 4销退入库
	private String postatus;//PO 状态0-开单 1-关闭
	private String createtime;//PO创建时间 时间格式：yyyy-MM-dd hh:mm:ss
	private String createdate;//PO创建日期 日期格式：yyyy-MM-dd 
	private String createmanid;//PO创建人
	private String warehouseid;//仓库编号 
	private String whAreaId;	/* 库区ID  */
	private String cargoAreaId;	/* 货区ID  */
	private String customerid;  //客户ID
	private String arrivestarttime;//预期到货时间(from) 时间格式：yyyy-MM-dd hh:mm:ss 
	private String arriveendtime;//预期到货时间(to) 时间格式：yyyy-MM-dd hh:mm:ss
	private String shipperid;//承运人
	private String supplierid;//供应商
	private String remark;//备注  
    private String poflag;  // 
	private String reserve1;//预留字段1   
	private String reserve2;//预留字段2    
	private String reserve3;//预留字段3    
	private String reserve4;//预留字段4 
	private String isup;//是否上传
	
	
	
	//派生属性
	private String supplierName;	// 供应商全称
	private String customerName;	// 客户全称
	private String potypeName;	    // 采购性质

	/** default constructor */
	public InboundPoHeader() {
	}
	public String getPoid() {
		return poid;
	}
	public void setPoid(String poid) {
		this.poid = poid;
	}
	public String getPotype() {
		return potype;
	}
	public void setPotype(String potype) {
		this.potype = potype;
	}
	public String getPostatus() {
		return postatus;
	}
	public void setPostatus(String postatus) {
		this.postatus = postatus;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getCreatemanid() {
		return createmanid;
	}
	public void setCreatemanid(String createmanid) {
		this.createmanid = createmanid;
	}
	public String getWarehouseid() {
		return warehouseid;
	}
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	public String getWhAreaId() {
		return whAreaId;
	}
	public void setWhAreaId(String whAreaId) {
		this.whAreaId = whAreaId;
	}
	public String getCargoAreaId() {
		return cargoAreaId;
	}
	public void setCargoAreaId(String cargoAreaId) {
		this.cargoAreaId = cargoAreaId;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getArrivestarttime() {
		return arrivestarttime;
	}
	public void setArrivestarttime(String arrivestarttime) {
		this.arrivestarttime = arrivestarttime;
	}
	public String getArriveendtime() {
		return arriveendtime;
	}
	public void setArriveendtime(String arriveendtime) {
		this.arriveendtime = arriveendtime;
	}
	public String getShipperid() {
		return shipperid;
	}
	public void setShipperid(String shipperid) {
		this.shipperid = shipperid;
	}
	public String getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPoflag() {
		return poflag;
	}
	public void setPoflag(String poflag) {
		this.poflag = poflag;
	}
	public String getReserve1() {
		return reserve1;
	}
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	public String getReserve2() {
		return reserve2;
	}
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	public String getReserve3() {
		return reserve3;
	}
	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}
	public String getReserve4() {
		return reserve4;
	}
	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPotypeName() {
		return potypeName;
	}
	public void setPotypeName(String potypeName) {
		this.potypeName = potypeName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getIsup() {
		return isup;
	}
	public void setIsup(String isup) {
		this.isup = isup;
	}
	

	

	
	
}