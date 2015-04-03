package com.wms3.bms.standard.entity.base;

import java.io.Serializable;
/**
 * ����:���
 * @author 
 *
 */
public class BaseShift implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2247179769936475546L;
	
	protected String m_strShiftId;		// ���ID
	protected String m_strShiftDate;	// ���ʱ��
	protected String m_strDayNight;		// �װࡢҹ��
	protected String m_strOndutyMen;	// ������Ա
	protected String m_strThreenum;		// �ס��ҡ�������
	protected String m_strShiftName;	// ���� ���ʱ�� + �װࡢҹ�� + �ס��ҡ�������
	protected String m_strIn_Out_Type;	// ������� 0-��/�� �� 1-��� 2-����
	protected String m_strOp_Man_Id;	// ������ ʵ�ʴ�����ֲ���id
	protected String m_strIscurrent;	// �Ƿ�ǰ��� Y.�� N.�� ������ֻ����һ��Y
	protected String m_strIsaccept;		// �Ƿ�Ӱ�
	protected String m_strAcceptTime;	// �Ӱ�ʱ�� ��ʽ��YYYY-MM-DD HH:MM:SS
	protected String m_strIsover;		// �Ƿ񽻰�
	protected String m_strOverTime;		// ����ʱ�� ��ʽ��YYYY-MM-DD HH:MM:SS
	protected String m_strIsaffirm;		// �Ƿ���
	protected String m_strAffirmtime;	// ���ʱ�� ��ʽ��YYYY-MM-DD HH:MM:SS
	protected String m_strIslast;		// �Ƿ������һ�����  Y.�� N.�� ������ֻ����һ��Y
	protected String m_strPre_Shift_Id;	// �ϰ��ID
	
	public BaseShift(){}
		
	public BaseShift(
					String shiftDate,
					String dayNight,
					String threenum,
					String shiftName,
					String in_Out_Type,
					String op_Man_Id,
					String iscurrent,
					String isaccept,
					String acceptTime,
					String isover,
					String overTime,
					String isaffirm,
					String affirmtime,
					String islast,
					String pre_Shift_Id,
					String ondutyMen) {
		m_strShiftDate = shiftDate;
		m_strDayNight = dayNight;
		m_strThreenum = threenum;
		m_strShiftName = shiftName;
		m_strIn_Out_Type = in_Out_Type;
		m_strOp_Man_Id = op_Man_Id;
		m_strIscurrent = iscurrent;
		m_strIsaccept = isaccept;
		m_strAcceptTime = acceptTime;
		m_strIsover = isover;
		m_strOverTime = overTime;
		m_strIsaffirm = isaffirm;
		m_strAffirmtime = affirmtime;
		m_strIslast = islast;
		m_strPre_Shift_Id = pre_Shift_Id;
		m_strOndutyMen = ondutyMen;
	}
	/**
	 * ���ܣ���ȡ���ID
	 * @return
	 */
	public String getM_strShiftId(){
		return m_strShiftId;
	}
	/**
	 * ���ܣ����ð��ID
	 * @param shiftId
	 */
	public  void setM_strShiftId(String shiftId){
		 m_strShiftId = shiftId;
	}
	/**
	 * ���ܣ���ȡ���ʱ��
	 * @return
	 */
	public String getM_strShiftDate(){
		return m_strShiftDate;
	}
	/**
	 * ���ܣ����ð��ʱ��
	 * @param shiftDate
	 */
	public void setM_strShiftDate(String shiftDate){
		m_strShiftDate = shiftDate;
	}
	
	/**
	 * ���ܣ���ȡ�װࡢҹ��
	 * @return
	 */
	public String getM_strDayNight(){
		return m_strDayNight;
	}
	/**
	 * ���ܣ����ðװࡢҹ��
	 * @param DayNight
	 */
	public  void setM_strDayNight(String dayNight){
		 m_strDayNight = dayNight;
	}
	/**
	 * ���ܣ���ȡ�ס��ҡ���
	 * @return
	 */
	public String getM_strThreenum(){
		return m_strThreenum;
	}
	/**
	 * ���ܣ����üס��ҡ���
	 * @param Threenum
	 */
	public  void setM_strThreenum(String threenum){
		 m_strThreenum = threenum;
	}
	/**
	 * ���ܣ���ȡ���� ���ʱ�� + �װࡢҹ�� + �ס��ҡ���
	 * @return
	 */
	public String getM_strShiftName(){
		return m_strShiftName;
	}
	/**
	 * ���ܣ��������� ���ʱ�� + �װࡢҹ�� + �ס��ҡ���
	 * @param ShiftName
	 */
	public  void setM_strShiftName(String shiftName){
		 m_strShiftName = shiftName;
	}
	/**
	 * ���ܣ���ȡ������� 0-��/�� ��1-��� 2-����
	 * @return
	 */
	public String getM_strIn_Out_Type(){
		return m_strIn_Out_Type;
	}
	/**
	 * ���ܣ������������ 0-��/�� ��1-��� 2-����
	 * @param In_Out_Type
	 */
	public  void setM_strIn_Out_Type(String in_Out_Type){
		 m_strIn_Out_Type = in_Out_Type;
	}
	/**
	 * ���ܣ���ȡ������
	 * @return
	 */
	public String getM_strOp_Man_Id(){
		return m_strOp_Man_Id;
	}
	/**
	 * ���ܣ����ø�����
	 * @param Op_Man_Id
	 */
	public  void setM_strOp_Man_Id(String op_Man_Id){
		 m_strOp_Man_Id = op_Man_Id;
	}
	/**
	 * ���ܣ���ȡ�Ƿ�ǰ��� Y.�� N.��
	 * @return
	 */
	public String getM_strIscurrent(){
		return m_strIscurrent;
	}
	/**
	 * ���ܣ������Ƿ�ǰ��� Y.�� N.��
	 * @param Iscurrent
	 */
	public  void setM_strIscurrent(String iscurrent){
		 m_strIscurrent = iscurrent;
	}
	/**
	 * ���ܣ���ȡ�Ƿ�Ӱ�
	 * @return
	 */
	public String getM_strIsaccept(){
		return m_strIsaccept;
	}
	/**
	 * ���ܣ������Ƿ�Ӱ�
	 * @param Isaccept
	 */
	public  void setM_strIsaccept(String isaccept){
		 m_strIsaccept = isaccept;
	}
	/**
	 * ���ܣ���ȡ�Ӱ�ʱ�� ��ʽ��YYYY-MM-DD HH:MM:SS
	 * @return
	 */
	public String getM_strAcceptTime(){
		return m_strAcceptTime;
	}
	/**
	 * ���ܣ����ýӰ�ʱ�� ��ʽ��YYYY-MM-DD HH:MM:SS
	 * @param AcceptTime
	 */
	public  void setM_strAcceptTime(String acceptTime){
		 m_strAcceptTime = acceptTime;
	}
	/**
	 * ���ܣ���ȡ�Ƿ񽻰�
	 * @return
	 */
	public String getM_strIsover(){
		return m_strIsover;
	}
	/**
	 * ���ܣ������Ƿ񽻰�
	 * @param Isover
	 */
	public  void setM_strIsover(String isover){
		 m_strIsover = isover;
	}
	/**
	 * ���ܣ���ȡ����ʱ�� ��ʽ��YYYY-MM-DD HH:MM:SS
	 * @return
	 */
	public String getM_strOverTime(){
		return m_strOverTime;
	}
	/**
	 * ���ܣ����ý���ʱ�� ��ʽ��YYYY-MM-DD HH:MM:SS
	 * @param OverTime
	 */
	public  void setM_strOverTime(String overTime){
		 m_strOverTime = overTime;
	}
	/**
	 * ���ܣ���ȡ�Ƿ���
	 * @return
	 */
	public String getM_strIsaffirm(){
		return m_strIsaffirm;
	}
	/**
	 * ���ܣ������Ƿ���
	 * @param Isaffirm
	 */
	public  void setM_strIsaffirm(String isaffirm){
		 m_strIsaffirm = isaffirm;
	}
	/**
	 * ���ܣ���ȡ���ʱ�� ��ʽ��YYYY-MM-DD HH:MM:SS
	 * @return
	 */
	public String getM_strAffirmtime(){
		return m_strAffirmtime;
	}
	/**
	 * ���ܣ����ð��ʱ�� ��ʽ��YYYY-MM-DD HH:MM:SS
	 * @param Affirmtime
	 */
	public  void setM_strAffirmtime(String affirmtime){
		 m_strAffirmtime = affirmtime;
	}
	/**
	 * ���ܣ���ȡ�Ƿ������һ�����  Y.�� N.�� ������ֻ����һ��Y
	 * @return
	 */
	public String getM_strIslast(){
		return m_strIslast;
	}
	/**
	 * ���ܣ������Ƿ������һ�����  Y.�� N.�� ������ֻ����һ��Y
	 * @param Islast
	 */
	public  void setM_strIslast(String islast){
		 m_strIslast = islast;
	}
	/**
	 * ���ܣ���ȡ�ϰ��ID
	 * @return
	 */
	public String getM_strPre_Shift_Id(){
		return m_strPre_Shift_Id;
	}
	/**
	 * ���ܣ������ϰ��ID
	 * @param Pre_Shift_Id
	 */
	public  void setM_strPre_Shift_Id(String pre_Shift_Id){
		 m_strPre_Shift_Id = pre_Shift_Id;
	}
	/**
	 * ���ܣ���ȡ������Ա
	 * @return
	 */
	public String getM_strOndutyMen() {
		return m_strOndutyMen;
	}
    
	/**
	 * ���ܣ����õ�����Ա
	 * @param ondutyMen
	 */
	public void setM_strOndutyMen(String ondutyMen) {
		m_strOndutyMen = ondutyMen;
	}

}
