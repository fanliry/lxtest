package com.wms3.bms.standard.entity.base;


import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

public class Extractor {
	
	private String m_extractorId;
	private String m_extractorNumber;
	private String m_extractorName;
	private String m_describe;
	
	public Extractor(){}
	
	public Extractor(String extractorId,String extractorName,String extractorNumber,String describe)
	{
		this.m_extractorNumber=extractorNumber;
		this.m_extractorId=extractorId;
		this.m_describe=describe;
		this.m_extractorName=extractorName;
	}

	
	public String getM_describe() {
		return m_describe;
	}


	public void setM_describe(String m_describe) {
		this.m_describe = m_describe;
	}


	public String getM_extractorId() {
		return m_extractorId;
	}


	public void setM_extractorId(String id) {
		m_extractorId = id;
	}


	public String getM_extractorName() {
		return m_extractorName;
	}


	public void setM_extractorName(String name) {
		m_extractorName = name;
	}


	public String getM_extractorNumber() {
		return m_extractorNumber;
	}


	public void setM_extractorNumber(String number) {
		m_extractorNumber = number;
	}
	
	/**
	 * 功能:增加
	 * @param dao
	 * @throws Exception
	 */
	public void addExtInfo(EntityDAO dao) throws Exception
	{
		dao.save(this);
	}
	/**
	 * 功能：根据Id获取模块对象
	 * @param strId
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public Extractor getExtInfoFromId(String strId, EntityDAO dao) throws Exception{
		if(strId != null){
			strId = strId.trim();
			
			String strSql = "From Extractor as m where m.m_extractorId='"+ strId +"'";
			List ls = dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (Extractor)ls.get(0);
			}
		}
		
		return null;
	}
	/**
	 * 功能:更新
	 * @param dao
	 * @throws Exception
	 */
	public void updateExtInfo(EntityDAO dao) throws Exception
	{
		dao.update(this);
	}


	/**
	 *  功能:获得查询的SQL语句
	 * @param extractorNumber
	 * @param extractorName
	 * @return strSql
	 */
	
	public static String getExtSQL(String extractorNumber, String extractorName)
	{
		  String strSql="From Extractor as m where 1=1";
		  
		  if(extractorNumber.equals("'" ) || extractorName.equals("'" ))
		  {
			  extractorName="";
			  extractorNumber="";

		  }
		  if (extractorNumber!=null && extractorNumber.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_extractorNumber like '%"+extractorNumber+"%'";	
		  }
		  if (extractorName!=null && extractorName.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_extractorName like '%"+extractorName+"%'";	
		  }
		  
		  strSql=strSql+" order by m.m_extractorNumber";
		  return strSql;
	}
	/**
	 * 功能:获得总数的SQL语句
	 * @param extractorNumber
	 * @param extractorName
	 * @return strSql
	 */
	public static String getExtCountSQL(String extractorNumber, String extractorName)
	{
		  String strSql="select count(m) From Extractor as m where 1=1";
		  
		  if(extractorNumber.equals("'" ) || extractorName.equals("'" ))
		  {
			  extractorName="";
			  extractorNumber="";

		  }
		  if (extractorNumber!=null && extractorNumber.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_extractorNumber like '%"+extractorNumber+"%'";	
		  }
		  if (extractorName!=null && extractorName.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_extractorName like '%"+extractorName+"%'";	
		  }
		  
		  return strSql;
	}
}
