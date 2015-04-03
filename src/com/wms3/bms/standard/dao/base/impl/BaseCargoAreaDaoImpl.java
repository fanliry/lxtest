package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseCargoAreaDao;
import com.wms3.bms.standard.entity.base.BaseCargoarea;

/**
 * 描述: 货区DAO类
 * @author fangjie 
 * 
 */
public class BaseCargoAreaDaoImpl implements IBaseCargoAreaDao {

	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;
    
	public BaseCargoAreaDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * 功能:根据条件查询库区
	 * @param warehouseid		仓库ID
	 * @param cargoAreaName		货区名	
	 * @return
	 * @throws Exception
	 */
	public List getCargoAreaQuery(String warehouseid, String cargoAreaName) throws Exception
	{
		List ls = null;
		try
		{
			String strHql = "From BaseCargoarea as t where 1=1" ;
			
			if(warehouseid != null && warehouseid.trim().length() > 0){
				strHql += " and t.warehouseid='" + warehouseid + "'";
			}
			if(cargoAreaName != null && cargoAreaName.trim().length() > 0){
				strHql += " and t.cargoAreaName like '%" + cargoAreaName + "%'";
			}
			strHql = strHql + " order by t.cargoAreaId";
			ls = m_dao.searchEntities(strHql);		
				
		}catch(Exception er)
		{
			throw new Exception("根据条件查询货区出错:" + er.getMessage());
		}
		return ls;
	}
	
	/**
	 * 功能:根据货区ID获得货区
	 * @param cargoAreaId	货区ID
	 * @return
	 * @throws Exception
	 */
	public BaseCargoarea getCargoareaById(String cargoAreaId) throws Exception
	{
		if(cargoAreaId != null)
		{
			String strSql = " from BaseCargoarea as t where t.cargoAreaId='" + cargoAreaId + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseCargoarea)ls.get(0);
			}
		}
		return null;
	}

	/**
	 * 功能:获得该仓库的最大的一个货区编码
	 * @param warehouseid 
	 * @return
	 * @throws Exception
	 */
	public String getMaxCargoAreaId(String warehouseid) throws Exception
	{
		String maxCargoareaNo = warehouseid + "000";
		try
		{
			String strSql = "from BaseCargoarea as t where t.warehouseid='" + warehouseid + "' order by t.cargoAreaId desc";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && !ls.isEmpty())
			{
				BaseCargoarea cargoArea = (BaseCargoarea)ls.get(0);
				maxCargoareaNo = cargoArea.getCargoAreaId();
			}
		}catch(Exception er)
		{
			throw new Exception("获得最大的一个货区编码失败:"+er.getMessage());
		}
		return maxCargoareaNo;
	}

	/**
	 * 功能:增加货区
	 * @param cargoArea	货区
	 * @throws Exception
	 */
	public void addCargoArea(BaseCargoarea cargoarea) throws Exception{
		
		m_dao.save(cargoarea);
	}

	/**
	 * 功能:获取所有货区列表
	 * @return
	 * @throws Exception
	 */
	public List getCargoAreaList() throws Exception
	{
		List ls = null;
		try
		{
			String strHql = "From BaseCargoarea as t where 1=1 order by t.cargoAreaId";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("查询货区列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:修改货区
	 * @param cargoArea	货区
	 * @throws Exception
	 */
	public void updateCargoArea(BaseCargoarea cargoarea) throws Exception{
		
		m_dao.update(cargoarea);
	}

	/**
	 * 功能:删除货区
	 * @param cargoAreaId	货区ID
	 * @throws Exception
	 */
	public void deleteCargoArea(String id) throws Exception
	{
		if(id != null)
		{
			String strSql  = " delete BaseCargoarea as t where t.cargoAreaId='" + id + "'";

			m_dao.deleteSql(strSql);
		}	
	}
}
