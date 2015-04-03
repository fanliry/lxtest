package com.wms3.bms.standard.entity.inbound.lxyy;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * 
 * 描述: 入库单明细表（收货单明细）
 * 
 * @author yao 2013-3-5
 */
public class InboundDetail implements java.io.Serializable {

	private String instockdetailid; // 入库单明细id
	private String instockid; // 入库单id
	private String bandrecordid; // 绑定记录id 无用
	private double innum; // 入库数量
	private double getnum; // 实收数量   无用
	private String traycode; // 托盘条码  无用
	private String lotid; // 批号类型  无用
	private String lotinfo; // 具体批号  无用
	private String status; // 1，新建 2，收货 3，正执行上架 4，上架完成 无用 4
	private String printdate; // 生产日期
	private String productid; // 品名id
	private String punit; // 单位
	private String obtainmanid; // RF收货人 无用
	private String obtaintime; // RF收货时间  无用
	private String finishtime; // 上架完成时间 无用
	private String productStatus; // 物品状态

	private String remark; // 备注
	private String Reserved1; // 预留字段1
	private String Reserved2; // 预留字段2

	// 派生属性
	private String statusname; // 1，新建 2，收货 3，上架  无用
	private String productName; // 产品名
	private String obtainmanname; // RF收货人名字  无用
	private String m_strUnitName; // 单位名
	private String productCode;// 产品编码

	private String productStatusName; // 物品状态名称

	public String getProductStatusName() {
		return productStatusName;
	}

	public void setProductStatusName(String productStatusName) {
		this.productStatusName = productStatusName;
	}

	public String getInstockdetailid() {
		return instockdetailid;
	}

	public void setInstockdetailid(String instockdetailid) {
		this.instockdetailid = instockdetailid;
	}

	public String getInstockid() {
		return instockid;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public void setInstockid(String instockid) {
		this.instockid = instockid;
	}

	public String getBandrecordid() {
		return bandrecordid;
	}

	public void setBandrecordid(String bandrecordid) {
		this.bandrecordid = bandrecordid;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public double getInnum() {
		return innum;
	}

	public void setInnum(double innum) {
		this.innum = innum;
	}

	public double getGetnum() {
		return getnum;
	}

	public void setGetnum(double getnum) {
		this.getnum = getnum;
	}

	public String getTraycode() {
		return traycode;
	}

	public void setTraycode(String traycode) {
		this.traycode = traycode;
	}

	public String getLotid() {
		return lotid;
	}

	public void setLotid(String lotid) {
		this.lotid = lotid;
	}

	public String getLotinfo() {
		return lotinfo;
	}

	public void setLotinfo(String lotinfo) {
		this.lotinfo = lotinfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrintdate() {
		return printdate;
	}

	public void setPrintdate(String printdate) {
		this.printdate = printdate;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getObtainmanid() {
		return obtainmanid;
	}

	public void setObtainmanid(String obtainmanid) {
		this.obtainmanid = obtainmanid;
	}

	public String getObtaintime() {
		return obtaintime;
	}

	public void setObtaintime(String obtaintime) {
		this.obtaintime = obtaintime;
	}

	public String getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReserved1() {
		return Reserved1;
	}

	public void setReserved1(String reserved1) {
		Reserved1 = reserved1;
	}

	public String getReserved2() {
		return Reserved2;
	}

	public void setReserved2(String reserved2) {
		Reserved2 = reserved2;
	}

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getObtainmanname() {
		return obtainmanname;
	}

	public void setObtainmanname(String obtainmanname) {
		this.obtainmanname = obtainmanname;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 入库单管理 点击入库单 显示 入库单明细
	 * 
	 * @param instockid
	 *            （入库单id）
	 * @return
	 */
	public String getQueryHQLByid(String instockid) {
		String hql = "from InboundDetail where 1=1 ";
		if (instockid != null && instockid.trim().length() > 0) {
			hql += " and instockid='" + instockid + "'";
		}
		hql += " order by instockid";
		return hql;
	}

	/**
	 * 入库单管理 打印收货信息
	 * 
	 * @param instockid
	 *            （入库单id）
	 * @return
	 */
	public String getQuerySHByid(String instockid) {
		String hql = "from InboundDetail where 1=1 and status!='1' ";
		if (instockid != null && instockid.trim().length() > 0) {
			hql += " and instockid='" + instockid + "'";
		}
		hql += " order by bandrecordid";
		return hql;
	}

	/**
	 * 入库单管理 取消收货
	 * 
	 * @param recordid
	 *            （收货记录id）
	 * @return
	 */
	public String getQueryHQLByRecordid(String recordid) {
		String hql = "from InboundDetail where 1=1 ";
		if (recordid != null && recordid.trim().length() > 0) {
			hql += " and instockdetailid='" + recordid + "'";
		}
		return hql;
	}

	/**
	 * (RF收货)
	 * 
	 * @param instockid
	 *            （入库申请单id）
	 * @param traycode
	 *            （托盘条码）
	 * @return
	 */
	public InboundDetail getDetailByidtray(String instockid, String traycode,
			EntityDAO dao) {
		String hql = "from InboundDetail where 1=1 ";
		if (instockid != null && instockid.trim().length() > 0) {
			hql += " and instockid='" + instockid + "'";
		}
		if (traycode != null && traycode.trim().length() > 0) {
			hql += " and traycode='" + traycode + "'";
		}
		hql += " order by instockdetailid";

		InboundDetail obj = null;
		java.util.List<InboundDetail> ls = dao.searchEntities(hql);
		if (ls != null && ls.size() > 0) {
			obj = ls.get(0);
		}

		return obj;
	}
	
	public double getSurplusnumByinvoiceid(String instockid,
			EntityDAO dao) {
		double surplusnum = 0;
		double sum = 0;
		double assignnum =0;
		String hql = "from InboundDetail where 1=1 ";
		if (instockid != null && instockid.trim().length() > 0) {
			hql += " and instockid='" + instockid + "'";
		}
		hql += " order by instockdetailid";

		InboundDetail obj = null;
		java.util.List<InboundDetail> ls = dao.searchEntities(hql);
		
		if (ls != null && ls.size() > 0) {
			for(int i=0;i<ls.size();i++){
				obj = ls.get(i);
				sum = sum+obj.getInnum();
				assignnum = assignnum+obj.getGetnum();
				
			}
			
		}
		surplusnum=sum-assignnum;
		return surplusnum;
	}
	
	/**
	 * 
	 * @param recordid
	 * @param dao
	 * @return
	 */
	public InboundDetail getQueryHQLByRecordid(String recordid, EntityDAO dao) {
		String hql = "from InboundDetail where 1=1 ";
		if (recordid != null && recordid.trim().length() > 0) {
			hql += " and instockdetailid='" + recordid + "'";
		}
		InboundDetail obj = null;
		java.util.List<InboundDetail> ls = dao.searchEntities(hql);
		if (ls != null && ls.size() > 0) {
			obj = ls.get(0);
		}

		return obj;
	}

	/**
	 * 根据入库单 查询所有入库单明细
	 * 
	 * @param recordid
	 * @param dao
	 * @return
	 */
	public java.util.List<InboundDetail> getQueryHQLByid(String instockid,
			EntityDAO dao) {
		String hql = "from InboundDetail where 1=1 ";
		if (instockid != null && instockid.trim().length() > 0) {
			hql += " and instockid='" + instockid + "'";
		}
		java.util.List<InboundDetail> ls = dao.searchEntities(hql);
		return ls;
	}

	/**
	 * 根据入库单 查询没有上架的入库单明细
	 * 
	 * @param recordid
	 * @param dao
	 * @return
	 */
	public java.util.List<InboundDetail> getQueryNotSJHQLByid(String instockid,
			EntityDAO dao) {
		String hql = "from InboundDetail where 1=1 and status!=4 ";
		if (instockid != null && instockid.trim().length() > 0) {
			hql += " and instockid='" + instockid + "'";
		}
		java.util.List<InboundDetail> ls = dao.searchEntities(hql);
		return ls;
	}

	public String getPunit() {
		return punit;
	}

	public void setPunit(String punit) {
		this.punit = punit;
	}

	public String getM_strUnitName() {
		return m_strUnitName;
	}

	public void setM_strUnitName(String unitName) {
		m_strUnitName = unitName;
	}
}