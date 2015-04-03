package com.wms3.bms.standard.entity.quality;
/**
 * 放行记录表
 * @author fanll
 *
 */
public class Release {
	
	private String Releaseid;       //放行ID
 	private String Productid;      	//物品ID
 	private String lotinfo;			//具体批号 (进厂编号)
 	private String Qualityid ;		//质检单号
 	private String createtime; 		//放行时间   时间格式：yyyy-MM-dd hh:mm:ss
 	private String opManId;    		//操作人
 	
	//派生属性
    private String Productcode;     //产品编码
    private String ProductName;   	//产品名称
    
	public String getReleaseid() {
		return Releaseid;
	}
	public void setReleaseid(String releaseid) {
		Releaseid = releaseid;
	}
	public String getProductid() {
		return Productid;
	}
	public void setProductid(String productid) {
		Productid = productid;
	}
	public String getLotinfo() {
		return lotinfo;
	}
	public void setLotinfo(String lotinfo) {
		this.lotinfo = lotinfo;
	}
	public String getQualityid() {
		return Qualityid;
	}
	public void setQualityid(String qualityid) {
		Qualityid = qualityid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getOpManId() {
		return opManId;
	}
	public void setOpManId(String opManId) {
		this.opManId = opManId;
	}
	public String getProductcode() {
		return Productcode;
	}
	public void setProductcode(String productcode) {
		Productcode = productcode;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}

}
