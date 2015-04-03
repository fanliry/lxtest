package com.wms3.bms.standard.entity.base;


import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * 说明：仓库管理员实体类
 * @author xiaotao
 *
 */
public class StoreHouseAdminster {
	
	private String m_storeHouseAdminsterId;  //主键
	private String m_storeHouseNumber;		//管理员编号
	private String m_storeHouseName;		//管理员姓名
	private String m_describe;				//备注
	
	public StoreHouseAdminster(){}
	
	public StoreHouseAdminster(String storeHouseAdminsterId,String storeHouseNumber,String storeHouseName)
	{
		this.m_storeHouseAdminsterId=storeHouseAdminsterId;
		this.m_storeHouseName=storeHouseName;
		this.m_storeHouseNumber=storeHouseNumber;
	}

	public String getM_describe() {
		return m_describe;
	}

	public void setM_describe(String m_describe) {
		this.m_describe = m_describe;
	}

	public String getM_storeHouseAdminsterId() {
		return m_storeHouseAdminsterId;
	}

	public void setM_storeHouseAdminsterId(String houseAdminsterId) {
		m_storeHouseAdminsterId = houseAdminsterId;
	}

	public String getM_storeHouseName() {
		return m_storeHouseName;
	}

	public void setM_storeHouseName(String houseName) {
		m_storeHouseName = houseName;
	}

	public String getM_storeHouseNumber() {
		return m_storeHouseNumber;
	}

	public void setM_storeHouseNumber(String houseNumber) {
		m_storeHouseNumber = houseNumber;
	}
	
	/**
	 * 功能：根据Id获取模块对象
	 * @param strCode
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public StoreHouseAdminster getSortInfoFromId(String strId, EntityDAO dao) throws Exception{
		if(strId != null){
			strId = strId.trim();
			
			String strSql = "From StoreHouseAdminster as m where m.m_storeHouseAdminsterId='"+ strId +"'";
			List ls = dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (StoreHouseAdminster)ls.get(0);
			}
		}
		
		return null;
	}
	
	
	/**
	 * 功能:增加
	 * @param dao
	 * @throws Exception
	 */
	public void addStoreInfo(EntityDAO dao) throws Exception
	{
		dao.save(this);
	}
	/**
	 * 功能:更新
	 * @param dao
	 * @throws Exception
	 */
	public void updateStoreInfo(EntityDAO dao) throws Exception
	{
		dao.update(this);
	}
	/**
	 * 功能:查询所有仓管员列表
	 * @param dao
	 * @return
	 * @throws Exception 
	 */
	public static List getSortHouseList(EntityDAO dao) throws Exception
	{
		List ls = null;
		try
		{
			String strHql = "From StoreHouseAdminster as m where 1=1";
			ls = dao.searchEntities(strHql);
		}catch(Exception er)
		{
				throw new Exception("查询所有仓管员列表出错:" + er.getMessage());
		}
		return ls;
	}
	/**
	 * 功能:获得查询的SQL语句
	 * @param storeHouseNumber
	 * @param storeHouseName
	 * @return strSql
	 */
	public static String getSortHouseSQL(String storeHouseNumber, String storeHouseName)
	{
		  String strSql="select m From StoreHouseAdminster as m where 1=1";
		  if(storeHouseNumber.equals("'" ) || storeHouseName.equals("'" ))
		  {
			  storeHouseNumber="";
			  storeHouseName="";
		  }
		  if (storeHouseNumber!=null && storeHouseNumber.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_storeHouseNumber like '%"+storeHouseNumber+"%'";	
		  }
		  if (storeHouseName!=null && storeHouseName.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_storeHouseName like '%"+storeHouseName+"%'";	
		  }
		  
		  strSql=strSql+" order by m.m_storeHouseNumber";
		  return strSql;
	}
	/**
	 * 功能:获得查询的SQL语句
	 * @param storeHouseNumber
	 * @param storeHouseName
	 * @return strSql
	 */
	public static String getSortHouseCountSQL(String storeHouseNumber, String storeHouseName)
	{
		  String strSql="select count(m) From StoreHouseAdminster as m where 1=1";
		  
		  if(storeHouseNumber.equals("'" ) || storeHouseName.equals("'" ))
		  {
			  storeHouseNumber="";
			  storeHouseName="";
		  }
		  if (storeHouseNumber!=null && storeHouseNumber.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_storeHouseNumber like '%"+storeHouseNumber+"%'";	
		  }
		  if (storeHouseName!=null && storeHouseName.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_storeHouseName like '%"+storeHouseName+"%'";	
		  }
		  return strSql;
	}
	
	/**
	 * 说明：根据ID查询仓库员名称 zhi
	 */
	public static String getWarehouseManName(EntityDAO dao, String warehousemanId) 
	{
		String sql = "select m_storeHouseName from StoreHouseAdminster where 1=1";
		if(warehousemanId!=null && warehousemanId.length()>0){
			sql = sql + "and m_storeHouseAdminsterId="+"'"+warehousemanId+"'";
		}
		String warehouseManName = "";
		List ls = null;
		try
		{
			if(warehousemanId!=null)
			{
				ls = dao.searchEntities(sql);
			}
			
			if(ls!=null && ls.size()!=0)
			{
				warehouseManName = (String)ls.get(0);
			}
		}
		catch(Exception er)
		{
			er.printStackTrace();
		}
		return warehouseManName;	
	}

}