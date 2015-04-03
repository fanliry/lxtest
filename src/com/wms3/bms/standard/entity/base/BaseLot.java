package com.wms3.bms.standard.entity.base;

/**
 * 批次
 * @author yangxi
 *
 */
public class BaseLot implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6995634298968536440L;
	
	protected String m_strLotid;		/*批次属性代码*/
	protected String m_strDescr;		/*描述*/
	protected String m_strIsdefault;	/*是否默认批次属性*/
	protected String m_strRemark;		/*备注*/


	public BaseLot() {}

	public BaseLot(String descr, String isdefault, String remark) {
		m_strDescr = descr;
		m_strIsdefault = isdefault;
		m_strRemark = remark;
	}

	/**
	 * 功能：获取描述
	 * @return
	 */
	public String getM_strDescr() {
		return m_strDescr;
	}

	/**
	 * 功能：设置描述
	 * @param descr
	 */
	public void setM_strDescr(String descr) {
		m_strDescr = descr;
	}

	/**
	 * 功能：获取批次属性代码
	 * @return
	 */
	public String getM_strLotid() {
		return m_strLotid;
	}

	/**
	 * 功能：设置批次属性代码
	 * @param lotid
	 */
	public void setM_strLotid(String lotid) {
		m_strLotid = lotid;
	}
	
	/**
	 * 功能：获得是否默认批次属性
	 */
	public String getM_strIsdefault() {
		return m_strIsdefault;
	}

	/**
	 * 功能：设置是否默认批次属性
	 * @param isdefault
	 */
	public void setM_strIsdefault(String isdefault) {
		m_strIsdefault = isdefault;
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
	
}
