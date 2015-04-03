package com.wms3.bms.standard.entity.base;

import java.io.Serializable;
/**
 * ������ϸ
 * @author yangxi
 *
 */
public class BaseLotDetail implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4305015157750949956L;
	
	protected String m_strLotdetailid;	  /*������ϸID*/
	protected String m_strLotid;	      /*����ID*/
	protected String m_strAttname;		  /*��������*/
	protected String m_strAttcode;		  /*���Դ���*/
	protected String m_strLotatt_flag;	  /*������� 1-����,2-����,3-��ѡ*/
	protected String m_strLottype;		  /*���Ը�ʽ 1-����,2-�ı�,3-����,4-*/
	protected String m_strLottypevalue;	  /*���Ը�ʽֵ (���������õ����ʹ����)*/
	protected String m_strLotsearch;	  /*ҳ���ѯʱ���ģʽ 1-��ȷ��ѯ,2-ģ����ѯ(�ı���ʽ),3-��Χ��ѯ(���ڸ�ʽ),*/
	protected String m_strRemark;		  /*��ע*/
	

	/**
	 * ������Ĭ�Ϲ��캯��
	 */
	public BaseLotDetail(){}
	
	
	/**
	 * ���ܣ���ȡ���Դ���
	 * @return
	 */
	public String getM_strAttcode() {
		return m_strAttcode;
	}
	
	/**
	 * ���ܣ��������Դ���
	 * @param attcode
	 */
	public void setM_strAttcode(String attcode) {
		m_strAttcode = attcode;
	}
	
	/**
	 * ���ܣ���ȡ��������
	 * @return
	 */
	public String getM_strAttname() {
		return m_strAttname;
	}
	
	/**
	 * ���ܣ�������������
	 * @param attname
	 */
	public void setM_strAttname(String attname) {
		m_strAttname = attname;
	}
	
	/**
	 * ���ܣ���ȡ�������
	 * @return
	 */
	public String getM_strLotatt_flag() {
		return m_strLotatt_flag;
	}
	/**
	 * ���ܣ������������
	 * @param lotatt_flag
	 */
	public void setM_strLotatt_flag(String lotatt_flag) {
		m_strLotatt_flag = lotatt_flag;
	}
	
	/**
	 * ���ܣ���ȡ������ϸID
	 * @return
	 */
	public String getM_strLotdetailid() {
		return m_strLotdetailid;
	}
	
	/**
	 * ���ܣ�����������ϸID
	 * @param lotdetailid
	 */
	public void setM_strLotdetailid(String lotdetailid) {
		m_strLotdetailid = lotdetailid;
	}
	
	/**
	 * ���ܣ���ȡ����ID
	 * @return
	 */
	public String getM_strLotid() {
		return m_strLotid;
	}
	
	/**
	 * ���ܣ���������ID
	 * @param lotid
	 */
	public void setM_strLotid(String lotid) {
		m_strLotid = lotid;
	}
	
	/**
	 * ���ܣ���ȡ���Ը�ʽ
	 * @return
	 */
	public String getM_strLottype() {
		return m_strLottype;
	}
	
	/**
	 * ���ܣ��������Ը�ʽ
	 * @param lottype
	 */
	public void setM_strLottype(String lottype) {
		m_strLottype = lottype;
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

	/**
	 * ���ܣ���������õ����ʹ���
	 */
	public String getM_strLottypevalue() {
		return m_strLottypevalue;
	}

	/**
	 * ���ܣ����������õ����ʹ���
	 * @param lottypevalue
	 */
	public void setM_strLottypevalue(String lottypevalue) {
		m_strLottypevalue = lottypevalue;
	}


	/**
	 * ���ܣ���ò�ѯģʽ
	 */
	public String getM_strLotsearch() {
		return m_strLotsearch;
	}


	/**
	 * ���ܣ����ò�ѯģʽ
	 * @param lotsearch
	 */
	public void setM_strLotsearch(String lotsearch) {
		m_strLotsearch = lotsearch;
	}


	

}
