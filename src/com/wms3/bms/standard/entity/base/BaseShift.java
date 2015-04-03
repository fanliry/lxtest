package com.wms3.bms.standard.entity.base;

import java.io.Serializable;
/**
 * 描述:班次
 * @author 
 *
 */
public class BaseShift implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2247179769936475546L;
	
	protected String m_strShiftId;		// 班次ID
	protected String m_strShiftDate;	// 班次时间
	protected String m_strDayNight;		// 白班、夜班
	protected String m_strOndutyMen;	// 当班人员
	protected String m_strThreenum;		// 甲、乙、丙、丁
	protected String m_strShiftName;	// 名称 班次时间 + 白班、夜班 + 甲、乙、丙、丁
	protected String m_strIn_Out_Type;	// 入出类型 0-入/出 库 1-入库 2-出库
	protected String m_strOp_Man_Id;	// 负责人 实际存放名字不是id
	protected String m_strIscurrent;	// 是否当前班次 Y.是 N.否 入出库各只能有一个Y
	protected String m_strIsaccept;		// 是否接班
	protected String m_strAcceptTime;	// 接班时间 格式：YYYY-MM-DD HH:MM:SS
	protected String m_strIsover;		// 是否交班
	protected String m_strOverTime;		// 交班时间 格式：YYYY-MM-DD HH:MM:SS
	protected String m_strIsaffirm;		// 是否班结
	protected String m_strAffirmtime;	// 班结时间 格式：YYYY-MM-DD HH:MM:SS
	protected String m_strIslast;		// 是否是最后一个班次  Y.是 N.否 入出库各只能有一个Y
	protected String m_strPre_Shift_Id;	// 上班次ID
	
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
	 * 功能：获取班次ID
	 * @return
	 */
	public String getM_strShiftId(){
		return m_strShiftId;
	}
	/**
	 * 功能：设置班次ID
	 * @param shiftId
	 */
	public  void setM_strShiftId(String shiftId){
		 m_strShiftId = shiftId;
	}
	/**
	 * 功能：获取班次时间
	 * @return
	 */
	public String getM_strShiftDate(){
		return m_strShiftDate;
	}
	/**
	 * 功能：设置班次时间
	 * @param shiftDate
	 */
	public void setM_strShiftDate(String shiftDate){
		m_strShiftDate = shiftDate;
	}
	
	/**
	 * 功能：获取白班、夜班
	 * @return
	 */
	public String getM_strDayNight(){
		return m_strDayNight;
	}
	/**
	 * 功能：设置白班、夜班
	 * @param DayNight
	 */
	public  void setM_strDayNight(String dayNight){
		 m_strDayNight = dayNight;
	}
	/**
	 * 功能：获取甲、乙、丙
	 * @return
	 */
	public String getM_strThreenum(){
		return m_strThreenum;
	}
	/**
	 * 功能：设置甲、乙、丙
	 * @param Threenum
	 */
	public  void setM_strThreenum(String threenum){
		 m_strThreenum = threenum;
	}
	/**
	 * 功能：获取名称 班次时间 + 白班、夜班 + 甲、乙、丙
	 * @return
	 */
	public String getM_strShiftName(){
		return m_strShiftName;
	}
	/**
	 * 功能：设置名称 班次时间 + 白班、夜班 + 甲、乙、丙
	 * @param ShiftName
	 */
	public  void setM_strShiftName(String shiftName){
		 m_strShiftName = shiftName;
	}
	/**
	 * 功能：获取入出类型 0-入/出 库1-入库 2-出库
	 * @return
	 */
	public String getM_strIn_Out_Type(){
		return m_strIn_Out_Type;
	}
	/**
	 * 功能：设置入出类型 0-入/出 库1-入库 2-出库
	 * @param In_Out_Type
	 */
	public  void setM_strIn_Out_Type(String in_Out_Type){
		 m_strIn_Out_Type = in_Out_Type;
	}
	/**
	 * 功能：获取负责人
	 * @return
	 */
	public String getM_strOp_Man_Id(){
		return m_strOp_Man_Id;
	}
	/**
	 * 功能：设置负责人
	 * @param Op_Man_Id
	 */
	public  void setM_strOp_Man_Id(String op_Man_Id){
		 m_strOp_Man_Id = op_Man_Id;
	}
	/**
	 * 功能：获取是否当前班次 Y.是 N.否
	 * @return
	 */
	public String getM_strIscurrent(){
		return m_strIscurrent;
	}
	/**
	 * 功能：设置是否当前班次 Y.是 N.否
	 * @param Iscurrent
	 */
	public  void setM_strIscurrent(String iscurrent){
		 m_strIscurrent = iscurrent;
	}
	/**
	 * 功能：获取是否接班
	 * @return
	 */
	public String getM_strIsaccept(){
		return m_strIsaccept;
	}
	/**
	 * 功能：设置是否接班
	 * @param Isaccept
	 */
	public  void setM_strIsaccept(String isaccept){
		 m_strIsaccept = isaccept;
	}
	/**
	 * 功能：获取接班时间 格式：YYYY-MM-DD HH:MM:SS
	 * @return
	 */
	public String getM_strAcceptTime(){
		return m_strAcceptTime;
	}
	/**
	 * 功能：设置接班时间 格式：YYYY-MM-DD HH:MM:SS
	 * @param AcceptTime
	 */
	public  void setM_strAcceptTime(String acceptTime){
		 m_strAcceptTime = acceptTime;
	}
	/**
	 * 功能：获取是否交班
	 * @return
	 */
	public String getM_strIsover(){
		return m_strIsover;
	}
	/**
	 * 功能：设置是否交班
	 * @param Isover
	 */
	public  void setM_strIsover(String isover){
		 m_strIsover = isover;
	}
	/**
	 * 功能：获取交班时间 格式：YYYY-MM-DD HH:MM:SS
	 * @return
	 */
	public String getM_strOverTime(){
		return m_strOverTime;
	}
	/**
	 * 功能：设置交班时间 格式：YYYY-MM-DD HH:MM:SS
	 * @param OverTime
	 */
	public  void setM_strOverTime(String overTime){
		 m_strOverTime = overTime;
	}
	/**
	 * 功能：获取是否班结
	 * @return
	 */
	public String getM_strIsaffirm(){
		return m_strIsaffirm;
	}
	/**
	 * 功能：设置是否班结
	 * @param Isaffirm
	 */
	public  void setM_strIsaffirm(String isaffirm){
		 m_strIsaffirm = isaffirm;
	}
	/**
	 * 功能：获取班结时间 格式：YYYY-MM-DD HH:MM:SS
	 * @return
	 */
	public String getM_strAffirmtime(){
		return m_strAffirmtime;
	}
	/**
	 * 功能：设置班结时间 格式：YYYY-MM-DD HH:MM:SS
	 * @param Affirmtime
	 */
	public  void setM_strAffirmtime(String affirmtime){
		 m_strAffirmtime = affirmtime;
	}
	/**
	 * 功能：获取是否是最后一个班次  Y.是 N.否 入出库各只能有一个Y
	 * @return
	 */
	public String getM_strIslast(){
		return m_strIslast;
	}
	/**
	 * 功能：设置是否是最后一个班次  Y.是 N.否 入出库各只能有一个Y
	 * @param Islast
	 */
	public  void setM_strIslast(String islast){
		 m_strIslast = islast;
	}
	/**
	 * 功能：获取上班次ID
	 * @return
	 */
	public String getM_strPre_Shift_Id(){
		return m_strPre_Shift_Id;
	}
	/**
	 * 功能：设置上班次ID
	 * @param Pre_Shift_Id
	 */
	public  void setM_strPre_Shift_Id(String pre_Shift_Id){
		 m_strPre_Shift_Id = pre_Shift_Id;
	}
	/**
	 * 功能：获取当班人员
	 * @return
	 */
	public String getM_strOndutyMen() {
		return m_strOndutyMen;
	}
    
	/**
	 * 功能：设置当班人员
	 * @param ondutyMen
	 */
	public void setM_strOndutyMen(String ondutyMen) {
		m_strOndutyMen = ondutyMen;
	}

}
