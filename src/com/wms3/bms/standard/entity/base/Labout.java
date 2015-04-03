package com.wms3.bms.standard.entity.base;


import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * 说明：卸载工数据实体
 * @author xiaotao
 *
 */
public class Labout {

	private String m_laboutId;
	private String m_laboutNumber;
	private String m_laboutName;
	private String m_duty;
	private String m_marker;
	private String m_describe;
	
	public Labout(){}
	
	public Labout(String laboutId,String laboutNumber,String laboutName,String duty,String describe,String marker)
	{
		this.m_describe=describe;
		this.m_duty=duty;
		this.m_laboutId=laboutId;
		this.m_laboutName=laboutName;
		this.m_laboutNumber=laboutNumber;
		this.m_marker=marker;
	}


	public String getM_describe() {
		return m_describe;
	}


	public void setM_describe(String m_describe) {
		this.m_describe = m_describe;
	}


	public String getM_duty() {
		return m_duty;
	}


	public void setM_duty(String m_duty) {
		this.m_duty = m_duty;
	}


	public String getM_laboutId() {
		return m_laboutId;
	}


	public void setM_laboutId(String id) {
		m_laboutId = id;
	}


	public String getM_laboutName() {
		return m_laboutName;
	}


	public void setM_laboutName(String name) {
		m_laboutName = name;
	}


	public String getM_laboutNumber() {
		return m_laboutNumber;
	}


	public void setM_laboutNumber(String number) {
		m_laboutNumber = number;
	}
	
	/**
	 * 功能：根据Id获取模块对象
	 * @param strId
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public Labout getLabInfoFromId(String strId, EntityDAO dao) throws Exception{
		if(strId != null){
			strId = strId.trim();
			String strSql = "From Labout as m where m.m_laboutId='"+ strId +"'";
			List ls = dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (Labout)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 功能:增加
	 * @param dao
	 * @throws Exception
	 */
	public void addLabInfo(EntityDAO dao) throws Exception
	{
		dao.save(this);
	}
	/**
	 * 功能:更新
	 * @param dao
	 * @throws Exception
	 */
	public void updateLabInfo(EntityDAO dao) throws Exception
	{
		dao.update(this);
	}
	
	/**
	 * 功能:获得查询的SQL语句
	 * @param laboutNumber
	 * @param laboutName
	 * @param marker
	 * @param duty
	 * @return strSql
	 */
	public static String getLabSQL(String laboutNumber, String laboutName,String  marker,String duty)
	{
        String strSql="From Labout as m where 1=1";
		  
          if(laboutNumber.equals("'" ) || laboutName.equals("'" )|| marker.equals("'" )|| duty.equals("'" ))
		  {
        	  duty="";
        	  laboutNumber="";
        	  marker="";
        	  duty="";
		  }
		  if (laboutNumber!=null && laboutNumber.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_laboutNumber like '%"+laboutNumber+"%'";	
		  }
		  if (laboutName!=null && laboutName.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_laboutName like '%"+laboutName+"%'";	
		  }
		  if (marker!=null && marker.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_marker like '%"+marker+"%'";	
		  }
		  if (duty!=null && duty.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_duty like '%"+duty+"%'";	
		  }
		  
		  return strSql;
	}

	/**
	 * 功能:获得查询的SQL语句
	 * @param laboutNumber
	 * @param laboutName
	 * @param marker
	 * @param duty
	 * @return strSql
	 */
	public static String getLabCountSQL(String laboutNumber, String laboutName,String  marker,String duty)
	{
		  String strSql="select count(m) From Labout as m where 1=1";
		  
		  
		  if(laboutNumber.equals("'" ) || laboutName.equals("'" )|| marker.equals("'" )|| duty.equals("'" ))
		  {
        	  duty="";
        	  laboutNumber="";
        	  marker="";
        	  duty="";
		  }
		  if (laboutNumber!=null && laboutNumber.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_laboutNumber like '%"+laboutNumber+"%'";	
		  }
		  if (laboutName!=null && laboutName.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_laboutName like '%"+laboutName+"%'";	
		  }
		  if (marker!=null && marker.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_marker like '%"+marker+"%'";	
		  }
		  if (duty!=null && duty.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_duty like '%"+duty+"%'";	
		  }
		
		  return strSql;
	}
	/**
	 * 功能:获得查询可用的卸载工的SQL语句
	 * @param laboutNumber
	 * @param laboutName
	 * @param marker
	 * @param duty
	 * @return strSql
	 */
	public static String getSQL()
	{
        String strSql="From Labout as m where 1=1 and m.m_marker='1'";

		  return strSql;
	}


	public String getM_marker() {
		return m_marker;
	}


	public void setM_marker(String m_marker) {
		this.m_marker = m_marker;
	}

}
