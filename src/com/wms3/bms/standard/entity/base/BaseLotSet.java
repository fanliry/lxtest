package com.wms3.bms.standard.entity.base;

import java.io.Serializable;

/**
 * ����:��������
 * @author gui
 *
 */
public class BaseLotSet implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 166368831580855944L;
	
	protected String lotattid;			//ID
	protected String lotid;				//��������ID
	protected String lotname;			//������������
	protected String islot;				//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
	protected int lotseq;				//��ʾ˳����߷���ͳ�Ƶ�˳��
	protected String remark;			//��ע
	protected String lottype;			//����(ϵͳ��ʾ���½������)
	
	//��������
    private String lottypename;			//������
	
	public BaseLotSet(){}

	/**
	 * ���ܣ�����Ƿ���ʾ�����Ƿ����ͳ��
	 */
	public String getIslot() {
		return islot;
	}

	/**
	 * ���ܣ������Ƿ���ʾ�����Ƿ����ͳ��
	 * @param islot
	 */
	public void setIslot(String islot) {
		this.islot = islot;
	}

	/**
	 * ���ܣ����ID
	 */
	public String getLotattid() {
		return lotattid;
	}

	/**
	 * ���ܣ�����ID
	 * @param lotattid
	 */
	public void setLotattid(String lotattid) {
		this.lotattid = lotattid;
	}

	/**
	 * ���ܣ������������ID
	 */
	public String getLotid() {
		return lotid;
	}

	/**
	 * ���ܣ�������������ID
	 * @param lotid
	 */
	public void setLotid(String lotid) {
		this.lotid = lotid;
	}

	/**
	 * ���ܣ����������������
	 */
	public String getLotname() {
		return lotname;
	}

	/**
	 * ���ܣ�����������������
	 * @param lotname
	 */
	public void setLotname(String lotname) {
		this.lotname = lotname;
	}

	/**
	 * ���ܣ������ʾ˳����߷���ͳ�Ƶ�˳��
	 */
	public int getLotseq() {
		return lotseq;
	}

	/**
	 * ���ܣ�������ʾ˳����߷���ͳ�Ƶ�˳��
	 * @param lotseq
	 */
	public void setLotseq(int lotseq) {
		this.lotseq = lotseq;
	}

	/**
	 * ���ܣ��������(ϵͳ��ʾ���½������)
	 */
	public String getLottype() {
		return lottype;
	}

	/**
	 * ���ܣ���������(ϵͳ��ʾ���½������)
	 * @param lottype
	 */
	public void setLottype(String lottype) {
		this.lottype = lottype;
	}

	/**
	 * ���ܣ���ñ�ע
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * ���ܣ����ñ�ע
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * ���ܣ����������
	 */
	public String getLottypename() {
		return lottypename;
	}

	/**
	 * ���ܣ�����������
	 * @param lottypename
	 */
	public void setLottypename(String lottypename) {
		this.lottypename = lottypename;
	}

}
