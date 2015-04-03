package com.wms3.bms.standard.entity.base;

import java.io.Serializable;

/**
 * 描述:批次设置
 * @author gui
 *
 */
public class BaseLotSet implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 166368831580855944L;
	
	protected String lotattid;			//ID
	protected String lotid;				//批次属性ID
	protected String lotname;			//批次属性名称
	protected String islot;				//是否显示或者是否分组统计 Y N
	protected int lotseq;				//显示顺序或者分组统计的顺序
	protected String remark;			//备注
	protected String lottype;			//类型(系统显示，新建出库的)
	
	//派生属性
    private String lottypename;			//类型名
	
	public BaseLotSet(){}

	/**
	 * 功能：获得是否显示或者是否分组统计
	 */
	public String getIslot() {
		return islot;
	}

	/**
	 * 功能：设置是否显示或者是否分组统计
	 * @param islot
	 */
	public void setIslot(String islot) {
		this.islot = islot;
	}

	/**
	 * 功能：获得ID
	 */
	public String getLotattid() {
		return lotattid;
	}

	/**
	 * 功能：设置ID
	 * @param lotattid
	 */
	public void setLotattid(String lotattid) {
		this.lotattid = lotattid;
	}

	/**
	 * 功能：获得批次属性ID
	 */
	public String getLotid() {
		return lotid;
	}

	/**
	 * 功能：设置批次属性ID
	 * @param lotid
	 */
	public void setLotid(String lotid) {
		this.lotid = lotid;
	}

	/**
	 * 功能：获得批次属性名称
	 */
	public String getLotname() {
		return lotname;
	}

	/**
	 * 功能：设置批次属性名称
	 * @param lotname
	 */
	public void setLotname(String lotname) {
		this.lotname = lotname;
	}

	/**
	 * 功能：获得显示顺序或者分组统计的顺序
	 */
	public int getLotseq() {
		return lotseq;
	}

	/**
	 * 功能：设置显示顺序或者分组统计的顺序
	 * @param lotseq
	 */
	public void setLotseq(int lotseq) {
		this.lotseq = lotseq;
	}

	/**
	 * 功能：获得类型(系统显示，新建出库的)
	 */
	public String getLottype() {
		return lottype;
	}

	/**
	 * 功能：设置类型(系统显示，新建出库的)
	 * @param lottype
	 */
	public void setLottype(String lottype) {
		this.lottype = lottype;
	}

	/**
	 * 功能：获得备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 功能：设置备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 功能：获得类型名
	 */
	public String getLottypename() {
		return lottypename;
	}

	/**
	 * 功能：设置类型名
	 * @param lottypename
	 */
	public void setLottypename(String lottypename) {
		this.lottypename = lottypename;
	}

}
