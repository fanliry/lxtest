package com.wms3.bms.standard.entity.base;

import java.io.Serializable;
/**
 * 描述:班次设置
 * @author 
 *
 */
public class BaseShiftconfig implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2247179769936475546L;
	
	protected String id;			//ID
	protected String timespace;		//班次时间间隔 12/24
	protected String type;			//班次区分 2：甲乙 3：甲乙丙 4：甲乙丙丁
	protected String inout;			//是否区分入/出库 Y/N
	protected String opman1;		//甲班负责人
	protected String onduty1;		//甲班当班人员
	protected String opman2;		//乙班负责人
	protected String onduty2;		//乙班当班人员
	protected String opman3;		//丙班负责人
	protected String onduty3;		//丙班当班人员
	protected String opman4;		//丁班负责人
	protected String onduty4;		//丁班当班人员
	protected String opman5;		//预留
	protected String onduty5;		//预留
	
	/** default constructor */
    public BaseShiftconfig() {
    }
    
    /** full constructor */
    public BaseShiftconfig(String timespace, String type, String inout, String opman1, String onduty1,
    		String opman2, String onduty2, String opman3, String onduty3, String opman4, String onduty4, String opman5, String onduty5) {
 
    	this.timespace = timespace;
    	this.type = type;
    	this.inout = inout;
    	this.opman1 = opman1;
    	this.onduty1 = onduty1;
    	this.opman2 = opman2;
    	this.onduty2 = onduty2;
    	this.opman3 = opman3;
    	this.onduty3 = onduty3;
    	this.opman4 = opman4;
    	this.onduty4 = onduty4;
    	this.opman5 = opman5;
    	this.onduty5 = onduty5;
    }
	
	/**
	 * 功能：获取ID
	 * @return
	 */
	public String getId(){
		return this.id;
	}
	
	/**
	 * 功能：设置ID
	 * @param id
	 */
	public  void setId(String id){
		this.id = id;
	}
	
	/**
	 * 功能：获取班次时间间隔
	 * @return
	 */
	public String getTimespace(){
		return this.timespace;
	}
	
	/**
	 * 功能：设置班次时间间隔
	 * @param content
	 */
	public void setTimespace(String timespace){
		this.timespace = timespace;
	}
	
	/**
	 * 功能：获取班次区分
	 * @return
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * 功能：设置班次区分
	 * @param type
	 */
	public  void setType(String type){
		this.type = type;
	}
	
	/**
	 * 功能：获取入/出库区分
	 * @return
	 */
	public String getInout(){
		return this.inout;
	}
	/**
	 * 功能：设置入/出库区分
	 * @param inout
	 */
	public  void setInout(String inout){
		this.inout = inout;
	}
	
	/**
	 * 功能：获取甲班负责人
	 * @return
	 */
	public String getOpman1(){
		return this.opman1;
	}
	/**
	 * 功能：设置甲班负责人
	 * @param opman1
	 */
	public  void setOpman1(String opman1){
		this.opman1 = opman1;
	}
	
	/**
	 * 功能：获取甲班当班人员
	 * @return
	 */
	public String getOnduty1(){
		return this.onduty1;
	}
	/**
	 * 功能：设置甲班当班人员
	 * @param onduty1
	 */
	public  void setOnduty1(String onduty1){
		this.onduty1 = onduty1;
	}
	
	/**
	 * 功能：获取乙班负责人
	 * @return
	 */
	public String getOpman2(){
		return this.opman2;
	}
	/**
	 * 功能：设置乙班负责人
	 * @param opman2
	 */
	public  void setOpman2(String opman2){
		this.opman2 = opman2;
	}
	
	/**
	 * 功能：获取乙班当班人员
	 * @return
	 */
	public String getOnduty2(){
		return this.onduty2;
	}
	/**
	 * 功能：设置乙班当班人员
	 * @param onduty2
	 */
	public  void setOnduty2(String onduty2){
		this.onduty2 = onduty2;
	}
	
	/**
	 * 功能：获取丙班负责人
	 * @return
	 */
	public String getOpman3(){
		return this.opman3;
	}
	/**
	 * 功能：设置丙班负责人
	 * @param opman3
	 */
	public  void setOpman3(String opman3){
		this.opman3 = opman3;
	}
	
	/**
	 * 功能：获取丙班当班人员
	 * @return
	 */
	public String getOnduty3(){
		return this.onduty3;
	}
	/**
	 * 功能：设置丙班当班人员
	 * @param onduty3
	 */
	public  void setOnduty3(String onduty3){
		this.onduty3 = onduty3;
	}
	
	/**
	 * 功能：获取丁班负责人
	 * @return
	 */
	public String getOpman4(){
		return this.opman4;
	}
	/**
	 * 功能：设置丁班负责人
	 * @param opman4
	 */
	public  void setOpman4(String opman4){
		this.opman4 = opman4;
	}
	
	/**
	 * 功能：获取丁班当班人员
	 * @return
	 */
	public String getOnduty4(){
		return this.onduty4;
	}
	/**
	 * 功能：设置丁班当班人员
	 * @param onduty4
	 */
	public  void setOnduty4(String onduty4){
		this.onduty4 = onduty4;
	}
	
	/**
	 * 功能：获取预留
	 * @return
	 */
	public String getOpman5(){
		return this.opman5;
	}
	/**
	 * 功能：设置预留
	 * @param opman5
	 */
	public  void setOpman5(String opman5){
		this.opman5 = opman5;
	}
	
	/**
	 * 功能：获取预留
	 * @return
	 */
	public String getOnduty5(){
		return this.onduty5;
	}
	/**
	 * 功能：设置预留
	 * @param onduty5
	 */
	public  void setOnduty5(String onduty5){
		this.onduty5 = onduty5;
	}
	
}
