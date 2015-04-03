package com.wms3.bms.standard.entity.base;

/**
 * ����
 * @author yangxi
 *
 */
public class BaseLot implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6995634298968536440L;
	
	protected String m_strLotid;		/*�������Դ���*/
	protected String m_strDescr;		/*����*/
	protected String m_strIsdefault;	/*�Ƿ�Ĭ����������*/
	protected String m_strRemark;		/*��ע*/


	public BaseLot() {}

	public BaseLot(String descr, String isdefault, String remark) {
		m_strDescr = descr;
		m_strIsdefault = isdefault;
		m_strRemark = remark;
	}

	/**
	 * ���ܣ���ȡ����
	 * @return
	 */
	public String getM_strDescr() {
		return m_strDescr;
	}

	/**
	 * ���ܣ���������
	 * @param descr
	 */
	public void setM_strDescr(String descr) {
		m_strDescr = descr;
	}

	/**
	 * ���ܣ���ȡ�������Դ���
	 * @return
	 */
	public String getM_strLotid() {
		return m_strLotid;
	}

	/**
	 * ���ܣ������������Դ���
	 * @param lotid
	 */
	public void setM_strLotid(String lotid) {
		m_strLotid = lotid;
	}
	
	/**
	 * ���ܣ�����Ƿ�Ĭ����������
	 */
	public String getM_strIsdefault() {
		return m_strIsdefault;
	}

	/**
	 * ���ܣ������Ƿ�Ĭ����������
	 * @param isdefault
	 */
	public void setM_strIsdefault(String isdefault) {
		m_strIsdefault = isdefault;
	}
	
	/**
	 * ���ܣ���ȡ��ע
	 * @return
	 */
	public String getM_strRemark() {
		return m_strRemark;
	}

	/**
	 * ���ܣ����ñ�ע
	 * @param remark
	 */
	public void setM_strRemark(String remark) {
		m_strRemark = remark;
	}
	
}
