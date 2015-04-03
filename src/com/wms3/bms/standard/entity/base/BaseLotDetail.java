package com.wms3.bms.standard.entity.base;

import java.io.Serializable;
/**
 * 批次明细
 * @author yangxi
 *
 */
public class BaseLotDetail implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4305015157750949956L;
	
	protected String m_strLotdetailid;	  /*批次详细ID*/
	protected String m_strLotid;	      /*批次ID*/
	protected String m_strAttname;		  /*属性名称*/
	protected String m_strAttcode;		  /*属性代码*/
	protected String m_strLotatt_flag;	  /*输入控制 1-必输,2-禁用,3-可选*/
	protected String m_strLottype;		  /*属性格式 1-日期,2-文本,3-下拉,4-*/
	protected String m_strLottypevalue;	  /*属性格式值 (保存下拉用的类型代码等)*/
	protected String m_strLotsearch;	  /*页面查询时候的模式 1-精确查询,2-模糊查询(文本格式),3-范围查询(日期格式),*/
	protected String m_strRemark;		  /*备注*/
	

	/**
	 * 描述：默认构造函数
	 */
	public BaseLotDetail(){}
	
	
	/**
	 * 功能：获取属性代码
	 * @return
	 */
	public String getM_strAttcode() {
		return m_strAttcode;
	}
	
	/**
	 * 功能：设置属性代码
	 * @param attcode
	 */
	public void setM_strAttcode(String attcode) {
		m_strAttcode = attcode;
	}
	
	/**
	 * 功能：获取属性名称
	 * @return
	 */
	public String getM_strAttname() {
		return m_strAttname;
	}
	
	/**
	 * 功能：设置属性名称
	 * @param attname
	 */
	public void setM_strAttname(String attname) {
		m_strAttname = attname;
	}
	
	/**
	 * 功能：获取输入控制
	 * @return
	 */
	public String getM_strLotatt_flag() {
		return m_strLotatt_flag;
	}
	/**
	 * 功能：设置输入控制
	 * @param lotatt_flag
	 */
	public void setM_strLotatt_flag(String lotatt_flag) {
		m_strLotatt_flag = lotatt_flag;
	}
	
	/**
	 * 功能：获取批次详细ID
	 * @return
	 */
	public String getM_strLotdetailid() {
		return m_strLotdetailid;
	}
	
	/**
	 * 功能：设置批次详细ID
	 * @param lotdetailid
	 */
	public void setM_strLotdetailid(String lotdetailid) {
		m_strLotdetailid = lotdetailid;
	}
	
	/**
	 * 功能：获取批次ID
	 * @return
	 */
	public String getM_strLotid() {
		return m_strLotid;
	}
	
	/**
	 * 功能：设置批次ID
	 * @param lotid
	 */
	public void setM_strLotid(String lotid) {
		m_strLotid = lotid;
	}
	
	/**
	 * 功能：获取属性格式
	 * @return
	 */
	public String getM_strLottype() {
		return m_strLottype;
	}
	
	/**
	 * 功能：设置属性格式
	 * @param lottype
	 */
	public void setM_strLottype(String lottype) {
		m_strLottype = lottype;
	}
	
	/**
	 * 功能：获取备注
	 * @return
	 */
	public String getM_strRemark() {
		return m_strRemark;
	}
	
	/**
	 * 功能：设置备注
	 * @param remark
	 */
	public void setM_strRemark(String remark) {
		m_strRemark = remark;
	}

	/**
	 * 功能：获得下拉用的类型代码
	 */
	public String getM_strLottypevalue() {
		return m_strLottypevalue;
	}

	/**
	 * 功能：设置下拉用的类型代码
	 * @param lottypevalue
	 */
	public void setM_strLottypevalue(String lottypevalue) {
		m_strLottypevalue = lottypevalue;
	}


	/**
	 * 功能：获得查询模式
	 */
	public String getM_strLotsearch() {
		return m_strLotsearch;
	}


	/**
	 * 功能：设置查询模式
	 * @param lotsearch
	 */
	public void setM_strLotsearch(String lotsearch) {
		m_strLotsearch = lotsearch;
	}


	

}
