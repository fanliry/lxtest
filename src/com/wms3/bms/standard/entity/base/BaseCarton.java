package com.wms3.bms.standard.entity.base;


/**
 * 描述：周转箱表
 * @author hug
 *
 */
public class BaseCarton  implements java.io.Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4810462564443658707L;
	
	private String cartonid;		//装箱代码
	private String cartontype;		//装箱类型
	private String descr;			//装箱描述
	private double boxleng;			//长
	private double boxwidth;		//宽
	private double boxheight;		//高
	private double maxcube;			//最大体积
	private double maxweight;		//最大重量
	private double maxcount;		//最大数量
	private double selfweight;		//自重
	private double cartonpercent;	//装箱百分比
	private String remark;			//备注

    
    //派生属性


    // Constructors

    /** default constructor */
    public BaseCarton() {
    }

    
    // Property accessors
	/**
	 * 功能：获得高
	 */
	public double getBoxheight() {
		return boxheight;
	}


	/**
	 * 功能：设置高
	 * @param boxheight
	 */
	public void setBoxheight(double boxheight) {
		this.boxheight = boxheight;
	}


	/**
	 * 功能：获得长
	 */
	public double getBoxleng() {
		return boxleng;
	}


	/**
	 * 功能：设置长
	 * @param boxleng
	 */
	public void setBoxleng(double boxleng) {
		this.boxleng = boxleng;
	}


	/**
	 * 功能：获得宽
	 */
	public double getBoxwidth() {
		return boxwidth;
	}


	/**
	 * 功能：设置宽
	 * @param boxwidth
	 */
	public void setBoxwidth(double boxwidth) {
		this.boxwidth = boxwidth;
	}


	/**
	 * 功能：获得装箱代码
	 */
	public String getCartonid() {
		return cartonid;
	}


	/**
	 * 功能：设置装箱代码
	 * @param cartonid
	 */
	public void setCartonid(String cartonid) {
		this.cartonid = cartonid;
	}


	/**
	 * 功能：获得装箱百分比
	 */
	public double getCartonpercent() {
		return cartonpercent;
	}


	/**
	 * 功能：设置装箱百分比
	 * @param cartonpercent
	 */
	public void setCartonpercent(double cartonpercent) {
		this.cartonpercent = cartonpercent;
	}


	/**
	 * 功能：获得装箱类型
	 */
	public String getCartontype() {
		return cartontype;
	}


	/**
	 * 功能：设置装箱类型
	 * @param cartontype
	 */
	public void setCartontype(String cartontype) {
		this.cartontype = cartontype;
	}


	/**
	 * 功能：获得装箱描述
	 */
	public String getDescr() {
		return descr;
	}


	/**
	 * 功能：设置装箱描述
	 * @param descr
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}


	/**
	 * 功能：获得最大数量
	 */
	public double getMaxcount() {
		return maxcount;
	}


	/**
	 * 功能：设置最大数量
	 * @param maxcount
	 */
	public void setMaxcount(double maxcount) {
		this.maxcount = maxcount;
	}


	/**
	 * 功能：获得最大体积
	 */
	public double getMaxcube() {
		return maxcube;
	}


	/**
	 * 功能：设置最大体积
	 * @param maxcube
	 */
	public void setMaxcube(double maxcube) {
		this.maxcube = maxcube;
	}


	/**
	 * 功能：获得最大重量
	 */
	public double getMaxweight() {
		return maxweight;
	}


	/**
	 * 功能：设置最大重量
	 * @param maxweight
	 */
	public void setMaxweight(double maxweight) {
		this.maxweight = maxweight;
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
	 * 功能：获得自重
	 */
	public double getSelfweight() {
		return selfweight;
	}


	/**
	 * 功能：设置自重
	 * @param selfweight
	 */
	public void setSelfweight(double selfweight) {
		this.selfweight = selfweight;
	}
    
}