package com.wms3.bms.standard.entity.base;


import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * 说明：计量单位数据实体
 * @author xiaotao
 *
 */

public class Meter {
	
	private String m_meterId;
	private String m_meterNumber;
	private String m_meterName;
	private String m_describe;
	
	public Meter(){}
	
	public Meter(String meterId,String meterNumber,String meterName,String describe)
	{
		this.m_meterNumber=meterNumber;
		this.m_meterName=meterName;
		this.m_meterId=meterId;
		this.m_describe=describe;
	}

	public String getM_describe() {
		return m_describe;
	}

	public void setM_describe(String m_describe) {
		this.m_describe = m_describe;
	}

	public String getM_meterId() {
		return m_meterId;
	}

	public void setM_meterId(String id) {
		m_meterId = id;
	}

	public String getM_meterName() {
		return m_meterName;
	}

	public void setM_meterName(String name) {
		m_meterName = name;
	}

	public String getM_meterNumber() {
		return m_meterNumber;
	}

	public void setM_meterNumber(String number) {
		m_meterNumber = number;
	}
	
	
	/**
	 * 功能:增加
	 * @param dao
	 * @throws Exception
	 */
	public void addMeterInfo(EntityDAO dao) throws Exception
	{
		dao.save(this);
	}
	/**
	 * 功能:更新
	 * @param dao
	 * @throws Exception
	 */
	public void updateMeterInfo(EntityDAO dao) throws Exception
	{
		dao.update(this);
	}
	
	
	/**
	 * 功能:获得查询的SQL语句
	 * @param meterNumber
	 * @param meterName
	 * @return strSql
	 */
	public static String getMeterSQL(String meterNumber,String meterName)
	{
       String strSql="select m From Meter as m where 1=1";
		  
       
         if(meterNumber.equals("'" ) || meterName.equals("'" ))
		  {
        	 meterNumber="";
        	 meterName="";
		  }
		  if (meterNumber!=null && meterNumber.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_meterNumber like '%"+meterNumber+"%'";	
		  }
		  if (meterName!=null && meterName.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_meterName like '%"+meterName+"%'";	
		  }
		  
		  strSql=strSql+" order by m.m_meterNumber";
		  return strSql;
	}
	
	
	/**
	 * 功能：根据Id获取模块对象
	 * @param strId
	 * @param dao
	 * @return strSql
	 * @throws Exception
	 */
	public static Meter getMeterInfoFromId(String strId, EntityDAO dao) throws Exception{
		if(strId != null){
			strId = strId.trim();
			
			String strSql = "From Meter as m where m.m_meterId='"+ strId +"'";
			List ls = dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (Meter)ls.get(0);
			}
		}
		
		return null;
	}
	

	/**
	 * 功能:获得总数的SQL语句
	 * @param meterNumber
	 * @param meterName
	 * @return strSql
	 */
	public static String getMeterCountSQL(String meterNumber,String meterName)
	{
       String strSql="select count(m) From Meter as m where 1=1";
		  
       	  if(meterNumber.equals("'" ) || meterName.equals("'" ))
		  {
       		meterNumber="";
       		meterName="";
		  }
		  if (meterNumber!=null && meterNumber.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_meterNumber ='"+meterNumber+"'";	
		  }
		  if (meterName!=null && meterName.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_meterName like '%"+meterName+"%'";	
		  }
		  
		  return strSql;
	}
	
	/**
	 * 说明： 检查规格名称是否唯一
	 * @param packName
	 * @return strSql
	 */
	public static String getSql(String meterName)
	{
		String strSql="";
		if(meterName!="")
		{
			strSql="from Meter as m where m.m_meterName='"+meterName+"'";
		}
		return strSql;
	}

}
