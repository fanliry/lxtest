package com.wms3.bms.standard.entity.base;


/**
 * ��������ת���
 * @author hug
 *
 */
public class BaseCarton  implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4810462564443658707L;
	
	private String cartonid;		//װ�����
	private String cartontype;		//װ������
	private String descr;			//װ������
	private double boxleng;			//��
	private double boxwidth;		//��
	private double boxheight;		//��
	private double maxcube;			//������
	private double maxweight;		//�������
	private double maxcount;		//�������
	private double selfweight;		//����
	private double cartonpercent;	//װ��ٷֱ�
	private String remark;			//��ע

    
    //��������


    // Constructors

    /** default constructor */
    public BaseCarton() {
    }

    
    // Property accessors
	/**
	 * ���ܣ���ø�
	 */
	public double getBoxheight() {
		return boxheight;
	}


	/**
	 * ���ܣ����ø�
	 * @param boxheight
	 */
	public void setBoxheight(double boxheight) {
		this.boxheight = boxheight;
	}


	/**
	 * ���ܣ���ó�
	 */
	public double getBoxleng() {
		return boxleng;
	}


	/**
	 * ���ܣ����ó�
	 * @param boxleng
	 */
	public void setBoxleng(double boxleng) {
		this.boxleng = boxleng;
	}


	/**
	 * ���ܣ���ÿ�
	 */
	public double getBoxwidth() {
		return boxwidth;
	}


	/**
	 * ���ܣ����ÿ�
	 * @param boxwidth
	 */
	public void setBoxwidth(double boxwidth) {
		this.boxwidth = boxwidth;
	}


	/**
	 * ���ܣ����װ�����
	 */
	public String getCartonid() {
		return cartonid;
	}


	/**
	 * ���ܣ�����װ�����
	 * @param cartonid
	 */
	public void setCartonid(String cartonid) {
		this.cartonid = cartonid;
	}


	/**
	 * ���ܣ����װ��ٷֱ�
	 */
	public double getCartonpercent() {
		return cartonpercent;
	}


	/**
	 * ���ܣ�����װ��ٷֱ�
	 * @param cartonpercent
	 */
	public void setCartonpercent(double cartonpercent) {
		this.cartonpercent = cartonpercent;
	}


	/**
	 * ���ܣ����װ������
	 */
	public String getCartontype() {
		return cartontype;
	}


	/**
	 * ���ܣ�����װ������
	 * @param cartontype
	 */
	public void setCartontype(String cartontype) {
		this.cartontype = cartontype;
	}


	/**
	 * ���ܣ����װ������
	 */
	public String getDescr() {
		return descr;
	}


	/**
	 * ���ܣ�����װ������
	 * @param descr
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}


	/**
	 * ���ܣ�����������
	 */
	public double getMaxcount() {
		return maxcount;
	}


	/**
	 * ���ܣ������������
	 * @param maxcount
	 */
	public void setMaxcount(double maxcount) {
		this.maxcount = maxcount;
	}


	/**
	 * ���ܣ����������
	 */
	public double getMaxcube() {
		return maxcube;
	}


	/**
	 * ���ܣ�����������
	 * @param maxcube
	 */
	public void setMaxcube(double maxcube) {
		this.maxcube = maxcube;
	}


	/**
	 * ���ܣ�����������
	 */
	public double getMaxweight() {
		return maxweight;
	}


	/**
	 * ���ܣ������������
	 * @param maxweight
	 */
	public void setMaxweight(double maxweight) {
		this.maxweight = maxweight;
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
	 * ���ܣ��������
	 */
	public double getSelfweight() {
		return selfweight;
	}


	/**
	 * ���ܣ���������
	 * @param selfweight
	 */
	public void setSelfweight(double selfweight) {
		this.selfweight = selfweight;
	}
    
}