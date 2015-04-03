package com.wms3.bms.standard.entity.base;


import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * 说明：车间资料
 * @author yanhz
 *
 */
public class Workshop {
	
	private String m_workshopid;
	private String m_workshopCode;	//车间代码
	private String m_workshopName;	//车间名称
	private String m_remark;	//车间备注
	
	
	public Workshop(){}

	
	public Workshop(String workshopid, String workshopCode, String workshopName, String remark)
	{
		this.m_workshopid = workshopid;
		this.m_workshopCode = workshopCode;
		this.m_workshopName = workshopName;
		this.m_remark = remark;
	}


	
	public String getM_workshopid() {
		return m_workshopid;
	}


	public void setM_workshopid(String mWorkshopid) {
		m_workshopid = mWorkshopid;
	}


	public String getM_workshopCode() {
		return m_workshopCode;
	}


	public void setM_workshopCode(String mWorkshopCode) {
		m_workshopCode = mWorkshopCode;
	}


	public String getM_workshopName() {
		return m_workshopName;
	}


	public void setM_workshopName(String mWorkshopName) {
		m_workshopName = mWorkshopName;
	}


	public String getM_remark() {
		return m_remark;
	}


	public void setM_remark(String mRemark) {
		m_remark = mRemark;
	}


	/**
	 * 功能：根据Id获取模块对象
	 * @param strId
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public static Workshop getWorkshopInfoFromId(String strId, EntityDAO dao) throws Exception{
		if(strId != null){
			strId = strId.trim();
			
			String strSql = "From Workshop as m where m.m_workshopid='"+ strId +"'";
			List ls = dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (Workshop)ls.get(0);
			}
		}
		
		return null;
	}
	
	/**
	 * 功能:增加
	 * @param dao
	 * @throws Exception
	 */
	public void addWorkshopInfo(EntityDAO dao) throws Exception
	{
		dao.save(this);
	}
	/**
	 * 功能:更新
	 * @param dao
	 * @throws Exception
	 */
	public void updateWorkshopInfo(EntityDAO dao) throws Exception
	{
		dao.update(this);
	}
	/**
	 * 功能：获得查询车间的SQL语句
	 * @param carPlaceNumber
	 * @param carPlacetName
	 * @param linePlace
	 * @param rowPlace
	 * @param layerPlace
	 * @return strSql
	 */
	
	public static String getWorkshopSQL(String workshopCode,String workshopName)
	{
       String strSql="From Workshop as m where 1=1";
		  
       
        if(workshopCode.equals("'" ) || workshopName.equals("'" ))
		  {
        	  workshopName="";
        	  workshopCode="";
		  }
		  if (workshopCode!=null && workshopCode.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_workshopCode like '%"+workshopCode+"%'";	
		  }
		  if (workshopName!=null && workshopName.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_workshopName like '%"+workshopName+"%'";	
		  }
		  
		  strSql=strSql+" order by m.m_workshopCode";
		  return strSql;
	}
	
	
	
	
	/**
	 * 功能：获得查询车位的SQL语句
	 * @param carPlaceNumber
	 * @param carPlacetName
	 * @param linePlace
	 * @param rowPlace
	 * @param layerPlace
	 * @return strSql
	 */

	public static String getWorkshopCountSQL(String workshopCode,String workshopName)
	{
		 String strSql="Select count(m) From Workshop as m where 1=1";
       
         if(workshopCode.equals("'" ) || workshopName.equals("'" ))
		  {
        	 workshopCode="";
        	 workshopName="";
			  
		  }
		  if (workshopCode!=null && workshopCode.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_workshopCode like '%"+workshopCode+"%'";
		  }
		  if (workshopName!=null && workshopName.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_workshopName like '%"+workshopName+"%'";	
		  }
		  
		  return strSql;
	}
	
	
	

	/** 
	 * 功能：获取所有车间
	 * @param dao
	 * @return List
	 * @author yangxi
	 * @throws Exception
	 */
	public static List getWorkshopList(EntityDAO dao) throws Exception
	{
		List ls = null;
		try
		{
			String strHql = "From Workshop where 1=1 order by m_workshopCode";
			ls = dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("查询车间出错:" + er.getMessage());
		}
		return ls;
	}
	/**
	 * 功能：根据ID获取车间名称
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	
	public static String getWorkshopName(EntityDAO dao, String id) throws Exception
	{
		List ls = null;
		String name = "";
		try
		{
			String strHql = "From Workshop where m_workshopid=" + "'" + id + "'";
			ls = dao.searchEntities(strHql);
			Workshop tb = (Workshop)ls.get(0);
			name = tb.getM_workshopName();
			
		}catch(Exception er)
		{
			throw new Exception("查询车位出错:" + er.getMessage());
		}
		return name;
	}
}

