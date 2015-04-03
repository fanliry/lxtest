package com.wms3.bms.standard.entity.inventory.lxyy;

/**
 * 
 * 描述: 库存调整单详细
 * @since WMS 3.0
 */
public class InventoryAdjustDetail  implements java.io.Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String adjustdetailid;	//调整单明细ID
	private String adjustid;		//调整单ID
	private String type;		    //1.调整数量，2.新增库存（入库异常时用）【暂不用】
	private String status;			//状态（1，未调整 2，已调整）
	private String cargo_space_id;	//货位ID
	private String wh_area_id;		//库区ID
	private String warehouseid;		//仓库ID
	private String spunit;			//源计量单位
	private String tpunit;			//现计量单位
	private String sproductid;		//源产品ID
	private String tproductid;		//现产品ID
	private String sprintdate;		//源产品生产日期
	private String tprintdate;		//现产品生产日期
	private String slotid;			//源批号类型
	private String tlotid;			//现批号类型
	private String slotinfo;		//源批号信息
	private String tlotinfo;		//现批号信息
	private String straycode;		//源托盘条码
	private String ttraycode;		//现托盘条码
	private double syspnum;			//系统数量
	private double realitypnum;		//实际数量
	private String jobid;			//作业ID
	private String createtime;		//创建时间
	private String adjusttime;		//调整时间
	private String inventoryid;		//调整类型单ID  分四类：出库异常调整， 入库异常调整，  盘点调整， 库存信息调整
	
	//派生属性
	 private String statusname;	    //状态（1，未调整 2，已调整）
	 private String warehousename;	//仓库名
	 private String sproductname;	//源产品名
	 private String tproductname;	//现产品名
	 private String sproductcode;//原产品编码
	 private String tproductcode;//现产品编码
	 
	 
	private String spunitName;	    //源单位
	 private String tpunitName;	    //现单位
	 private String slotName;	    //源批号类型
	 private String tlotName;	    //现批号类型
   
	 
	 
	 public String getSproductcode() {
			return sproductcode;
		}
		public void setSproductcode(String sproductcode) {
			this.sproductcode = sproductcode;
		}
		public String getTproductcode() {
			return tproductcode;
		}
		public void setTproductcode(String tproductcode) {
			this.tproductcode = tproductcode;
		}
   public String getType() {
		return type;
   }
   public void setType(String type) {
		this.type = type;
	}
	public String getAdjustdetailid() {
		return adjustdetailid;
	}
	public void setAdjustdetailid(String adjustdetailid) {
		this.adjustdetailid = adjustdetailid;
	}
	public String getAdjustid() {
		return adjustid;
	}
	public void setAdjustid(String adjustid) {
		this.adjustid = adjustid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCargo_space_id() {
		return cargo_space_id;
	}
	public void setCargo_space_id(String cargo_space_id) {
		this.cargo_space_id = cargo_space_id;
	}
	public String getWh_area_id() {
		return wh_area_id;
	}
	public void setWh_area_id(String wh_area_id) {
		this.wh_area_id = wh_area_id;
	}
	public String getWarehouseid() {
		return warehouseid;
	}
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	public String getSpunit() {
		return spunit;
	}
	public void setSpunit(String spunit) {
		this.spunit = spunit;
	}
	public String getTpunit() {
		return tpunit;
	}
	public void setTpunit(String tpunit) {
		this.tpunit = tpunit;
	}
	public String getSproductid() {
		return sproductid;
	}
	public void setSproductid(String sproductid) {
		this.sproductid = sproductid;
	}
	public String getTproductid() {
		return tproductid;
	}
	public void setTproductid(String tproductid) {
		this.tproductid = tproductid;
	}
	public String getSprintdate() {
		return sprintdate;
	}
	public void setSprintdate(String sprintdate) {
		this.sprintdate = sprintdate;
	}
	public String getTprintdate() {
		return tprintdate;
	}
	public void setTprintdate(String tprintdate) {
		this.tprintdate = tprintdate;
	}
	public String getSlotid() {
		return slotid;
	}
	public void setSlotid(String slotid) {
		this.slotid = slotid;
	}
	public String getTlotid() {
		return tlotid;
	}
	public void setTlotid(String tlotid) {
		this.tlotid = tlotid;
	}
	public String getSlotinfo() {
		return slotinfo;
	}
	public void setSlotinfo(String slotinfo) {
		this.slotinfo = slotinfo;
	}
	public String getTlotinfo() {
		return tlotinfo;
	}
	public void setTlotinfo(String tlotinfo) {
		this.tlotinfo = tlotinfo;
	}
	public String getStraycode() {
		return straycode;
	}
	public void setStraycode(String straycode) {
		this.straycode = straycode;
	}
	public String getTtraycode() {
		return ttraycode;
	}
	public void setTtraycode(String ttraycode) {
		this.ttraycode = ttraycode;
	}
	public double getSyspnum() {
		return syspnum;
	}
	public void setSyspnum(double syspnum) {
		this.syspnum = syspnum;
	}
	public double getRealitypnum() {
		return realitypnum;
	}
	public void setRealitypnum(double realitypnum) {
		this.realitypnum = realitypnum;
	}
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getAdjusttime() {
		return adjusttime;
	}
	public void setAdjusttime(String adjusttime) {
		this.adjusttime = adjusttime;
	}
	public String getInventoryid() {
		return inventoryid;
	}
	public void setInventoryid(String inventoryid) {
		this.inventoryid = inventoryid;
	}
	public String getStatusname() {
		return statusname;
	}
	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
	public String getWarehousename() {
		return warehousename;
	}
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	public String getSproductname() {
		return sproductname;
	}
	public void setSproductname(String sproductname) {
		this.sproductname = sproductname;
	}
	public String getTproductname() {
		return tproductname;
	}
	public void setTproductname(String tproductname) {
		this.tproductname = tproductname;
	}
	public String getSpunitName() {
		return spunitName;
	}
	public void setSpunitName(String spunitName) {
		this.spunitName = spunitName;
	}
	public String getTpunitName() {
		return tpunitName;
	}
	public void setTpunitName(String tpunitName) {
		this.tpunitName = tpunitName;
	}
	public String getSlotName() {
		return slotName;
	}
	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}
	public String getTlotName() {
		return tlotName;
	}
	public void setTlotName(String tlotName) {
		this.tlotName = tlotName;
	}
    
}