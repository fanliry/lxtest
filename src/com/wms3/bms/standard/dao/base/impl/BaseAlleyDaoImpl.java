package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseAlleyDao;
import com.wms3.bms.standard.entity.base.BaseAlley;

/**
 * 描述: 巷道DAO类
 * @author fangjie 
 * 
 */
public class BaseAlleyDaoImpl implements IBaseAlleyDao {

	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;
    
	public BaseAlleyDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * 功能:根据条件查询巷道
	 * @param warehouseid		仓库ID
	 * @param cargoAlleyId		巷道ID	
	 * @param whAreaId 			库区ID
	 * @return
	 * @throws Exception
	 */
	public List getAlleyQuery(String warehouseid, String cargoAlleyId, String whAreaId) throws Exception
	{
		List ls = null;
		try
		{
			String strHql = "From BaseAlley as t where 1=1" ;
			
			if(warehouseid != null && warehouseid.trim().length() > 0){
				strHql += " and t.warehouseid = '" + warehouseid + "'";
			}
			if(cargoAlleyId != null && cargoAlleyId.trim().length() > 0){
				strHql += " and t.cargoAlleyId = '" + cargoAlleyId + "'";
			}
			if(whAreaId != null && whAreaId.trim().length() > 0){
				strHql += " and t.whAreaId = '" + whAreaId + "'";
			}
			strHql = strHql + " order by t.cargoAlleyId";
			ls = m_dao.searchEntities(strHql);		
				
		}catch(Exception er)
		{
			throw new Exception("根据条件查询巷道出错:" + er.getMessage());
		}
		return ls;
	}
	
	/**
	 * 功能:根据巷道ID获得巷道
	 * @param cargoAlleyId	巷道ID
	 * @return
	 * @throws Exception
	 */
	public BaseAlley getAlleyById(String cargoAlleyId) throws Exception
	{
		if(cargoAlleyId != null)
		{
			String strSql = " from BaseAlley as t where t.cargoAlleyId='" + cargoAlleyId + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseAlley)ls.get(0);
			}
		}
		return null;
	}

	/**
	 * 功能:获得同库区下最大的一个巷道编码
	 * @param whAreaId  
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public String getMaxAlleyId(String whAreaId) throws Exception
	{
		String maxAlleyId = whAreaId + "000";
		try
		{
			String strSql = "from BaseAlley as t where t.whAreaId='" + whAreaId + "' order by t.cargoAlleyId desc";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && !ls.isEmpty())
			{
				BaseAlley alley = (BaseAlley)ls.get(0);
				maxAlleyId = alley.getCargoAlleyId();
			}
		}catch(Exception er)
		{
			throw new Exception("获得最大的一个巷道编码失败:"+er.getMessage());
		}
		return maxAlleyId;
	}

	/**
	 * 功能:增加巷道
	 * @param alley	巷道
	 * @throws Exception
	 */
	public void addAlley(BaseAlley alley) throws Exception{
		
		m_dao.save(alley);
	}

	/**
	 * 功能:获取所有巷道列表
	 * @return
	 * @throws Exception
	 */
	public List getAlleyList() throws Exception
	{
		List ls = null;
		try
		{
			String strHql = "From BaseAlley as t where 1=1 order by t.cargoAlleyId";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("查询巷道列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:修改巷道
	 * @param alley	巷道
	 * @throws Exception
	 */
	public void updateAlley(BaseAlley alley) throws Exception{
		
		m_dao.update(alley);
	}

	/**
	 * 功能:删除巷道
	 * @param id	巷道ID
	 * @throws Exception
	 */
	public void deleteAlley(String id) throws Exception
	{
		if(id != null)
		{
			String strSql  = " delete BaseAlley as t where t.cargoAlleyId='" + id + "'";

			m_dao.deleteSql(strSql);
		}	
	}
	
	/**
	 * 功能:判断库区下是否有巷道
	 * @param id	库区ID
	 * @throws Exception
	 */
	public boolean isWhAreaHasChildNode(String id) throws Exception {
		
		if(id != null && !id.equals(""))
		{
			String strSql = " from BaseAlley as t where t.whAreaId='" + id+"'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return true;
			}
		}
		return false;
	}
}
