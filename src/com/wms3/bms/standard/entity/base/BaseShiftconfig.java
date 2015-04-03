package com.wms3.bms.standard.entity.base;

import java.io.Serializable;
/**
 * ����:�������
 * @author 
 *
 */
public class BaseShiftconfig implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2247179769936475546L;
	
	protected String id;			//ID
	protected String timespace;		//���ʱ���� 12/24
	protected String type;			//������� 2������ 3�����ұ� 4�����ұ���
	protected String inout;			//�Ƿ�������/���� Y/N
	protected String opman1;		//�װฺ����
	protected String onduty1;		//�װ൱����Ա
	protected String opman2;		//�Ұฺ����
	protected String onduty2;		//�Ұ൱����Ա
	protected String opman3;		//���ฺ����
	protected String onduty3;		//���൱����Ա
	protected String opman4;		//���ฺ����
	protected String onduty4;		//���൱����Ա
	protected String opman5;		//Ԥ��
	protected String onduty5;		//Ԥ��
	
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
	 * ���ܣ���ȡID
	 * @return
	 */
	public String getId(){
		return this.id;
	}
	
	/**
	 * ���ܣ�����ID
	 * @param id
	 */
	public  void setId(String id){
		this.id = id;
	}
	
	/**
	 * ���ܣ���ȡ���ʱ����
	 * @return
	 */
	public String getTimespace(){
		return this.timespace;
	}
	
	/**
	 * ���ܣ����ð��ʱ����
	 * @param content
	 */
	public void setTimespace(String timespace){
		this.timespace = timespace;
	}
	
	/**
	 * ���ܣ���ȡ�������
	 * @return
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * ���ܣ����ð������
	 * @param type
	 */
	public  void setType(String type){
		this.type = type;
	}
	
	/**
	 * ���ܣ���ȡ��/��������
	 * @return
	 */
	public String getInout(){
		return this.inout;
	}
	/**
	 * ���ܣ�������/��������
	 * @param inout
	 */
	public  void setInout(String inout){
		this.inout = inout;
	}
	
	/**
	 * ���ܣ���ȡ�װฺ����
	 * @return
	 */
	public String getOpman1(){
		return this.opman1;
	}
	/**
	 * ���ܣ����üװฺ����
	 * @param opman1
	 */
	public  void setOpman1(String opman1){
		this.opman1 = opman1;
	}
	
	/**
	 * ���ܣ���ȡ�װ൱����Ա
	 * @return
	 */
	public String getOnduty1(){
		return this.onduty1;
	}
	/**
	 * ���ܣ����üװ൱����Ա
	 * @param onduty1
	 */
	public  void setOnduty1(String onduty1){
		this.onduty1 = onduty1;
	}
	
	/**
	 * ���ܣ���ȡ�Ұฺ����
	 * @return
	 */
	public String getOpman2(){
		return this.opman2;
	}
	/**
	 * ���ܣ������Ұฺ����
	 * @param opman2
	 */
	public  void setOpman2(String opman2){
		this.opman2 = opman2;
	}
	
	/**
	 * ���ܣ���ȡ�Ұ൱����Ա
	 * @return
	 */
	public String getOnduty2(){
		return this.onduty2;
	}
	/**
	 * ���ܣ������Ұ൱����Ա
	 * @param onduty2
	 */
	public  void setOnduty2(String onduty2){
		this.onduty2 = onduty2;
	}
	
	/**
	 * ���ܣ���ȡ���ฺ����
	 * @return
	 */
	public String getOpman3(){
		return this.opman3;
	}
	/**
	 * ���ܣ����ñ��ฺ����
	 * @param opman3
	 */
	public  void setOpman3(String opman3){
		this.opman3 = opman3;
	}
	
	/**
	 * ���ܣ���ȡ���൱����Ա
	 * @return
	 */
	public String getOnduty3(){
		return this.onduty3;
	}
	/**
	 * ���ܣ����ñ��൱����Ա
	 * @param onduty3
	 */
	public  void setOnduty3(String onduty3){
		this.onduty3 = onduty3;
	}
	
	/**
	 * ���ܣ���ȡ���ฺ����
	 * @return
	 */
	public String getOpman4(){
		return this.opman4;
	}
	/**
	 * ���ܣ����ö��ฺ����
	 * @param opman4
	 */
	public  void setOpman4(String opman4){
		this.opman4 = opman4;
	}
	
	/**
	 * ���ܣ���ȡ���൱����Ա
	 * @return
	 */
	public String getOnduty4(){
		return this.onduty4;
	}
	/**
	 * ���ܣ����ö��൱����Ա
	 * @param onduty4
	 */
	public  void setOnduty4(String onduty4){
		this.onduty4 = onduty4;
	}
	
	/**
	 * ���ܣ���ȡԤ��
	 * @return
	 */
	public String getOpman5(){
		return this.opman5;
	}
	/**
	 * ���ܣ�����Ԥ��
	 * @param opman5
	 */
	public  void setOpman5(String opman5){
		this.opman5 = opman5;
	}
	
	/**
	 * ���ܣ���ȡԤ��
	 * @return
	 */
	public String getOnduty5(){
		return this.onduty5;
	}
	/**
	 * ���ܣ�����Ԥ��
	 * @param onduty5
	 */
	public  void setOnduty5(String onduty5){
		this.onduty5 = onduty5;
	}
	
}
