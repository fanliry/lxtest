package com.wms3.bms.standard.entity.inbound.lxyy;

/**
 * 描述:采购入库订单详细
 * @author yao 2015-3-10
 * @since wmsBMS3.0
 */
public class InboundPoDetail  implements java.io.Serializable{

	private static final long serialVersionUID = -6088034943537391803L;
	
	private String podetailid;//PO行号
	private String poid;             // PO Id 
	private String polinestatus;     //PO行状态 0-开单
	private String warehouseid; 	 /* 仓库编号 */
	private String whAreaId;		 /* 库区ID  */
	private String cargoAreaId;		 /* 货区ID  */
	private String productid;// 产品ID
	private String productSort;//产品类别
	private String eunit;// 单位
	private double ponum;//数量（开单）
	private double weight;//重量 
	private double netweight;//净重
	private double volume;//体积
	private double price;	// 原币含税单价
	private String reserve1;//预留字段1
	private String reserve2;//预留字段2
	private String lotinfo; //进厂编号
	private String lotinfo2; //原厂编号
	private String checkid;  //检验单号
	
	//派生属性
    private String m_strProductName;    // 产品
    private String productCode;        	//ERP物料编码(产品编码)
	private String m_strSortName;       //产品类别
	private String m_strUnitName;       //单位名称
	private String polinestatusName;    //状态名
	private String m_spec;              //规格
	private double useponum;    //已使用数量
	public String getPodetailid() {
		return podetailid;
	}
	public void setPodetailid(String podetailid) {
		this.podetailid = podetailid;
	}
	public String getPoid() {
		return poid;
	}
	public void setPoid(String poid) {
		this.poid = poid;
	}
	public String getPolinestatus() {
		return polinestatus;
	}
	public void setPolinestatus(String polinestatus) {
		this.polinestatus = polinestatus;
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
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProductSort() {
		return productSort;
	}
	public void setProductSort(String productSort) {
		this.productSort = productSort;
	}
	public String getEunit() {
		return eunit;
	}
	public void setEunit(String eunit) {
		this.eunit = eunit;
	}
	public double getPonum() {
		return ponum;
	}
	public void setPonum(double ponum) {
		this.ponum = ponum;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getNetweight() {
		return netweight;
	}
	public void setNetweight(double netweight) {
		this.netweight = netweight;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getUseponum() {
		return useponum;
	}
	public void setUseponum(double useponum) {
		this.useponum = useponum;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public String getLotinfo() {
		return lotinfo;
	}
	public void setLotinfo(String lotinfo) {
		this.lotinfo = lotinfo;
	}
	public String getLotinfo2() {
		return lotinfo2;
	}
	public void setLotinfo2(String lotinfo2) {
		this.lotinfo2 = lotinfo2;
	}
	public String getCheckid() {
		return checkid;
	}
	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}
	public String getM_strProductName() {
		return m_strProductName;
	}
	public void setM_strProductName(String m_strProductName) {
		this.m_strProductName = m_strProductName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getM_strSortName() {
		return m_strSortName;
	}
	public void setM_strSortName(String m_strSortName) {
		this.m_strSortName = m_strSortName;
	}
	public String getM_strUnitName() {
		return m_strUnitName;
	}
	public void setM_strUnitName(String m_strUnitName) {
		this.m_strUnitName = m_strUnitName;
	}
	public String getPolinestatusName() {
		return polinestatusName;
	}
	public void setPolinestatusName(String polinestatusName) {
		this.polinestatusName = polinestatusName;
	}
	public String getM_spec() {
		return m_spec;
	}
	public void setM_spec(String m_spec) {
		this.m_spec = m_spec;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}