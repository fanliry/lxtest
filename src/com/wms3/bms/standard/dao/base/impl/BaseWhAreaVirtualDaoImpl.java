package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseWhAreaVirtualDao;
import com.wms3.bms.standard.entity.base.BaseWhareaVirtual;

/**
 * 
 * 描述: 库区DAO类
 * @author fanll 
 * @since WMS 3.0
 */
public class BaseWhAreaVirtualDaoImpl implements IBaseWhAreaVirtualDao {
	
	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;

	public BaseWhAreaVirtualDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * 功能:根据条件查询库区
	 * @param warehouseid		仓库ID
	 * @param whAreaName		库区名
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaQuery(String warehouseid, String whAreaName) throws Exception 
	{
		List ls = null;
		
		String strHql = "From BaseWhareaVirtual as t where 1=1" ;
		
		if(warehouseid != null && warehouseid.trim().length() > 0){
			strHql += " and t.warehouseid='" + warehouseid + "'";
		}
		if(whAreaName != null && whAreaName.trim().length() > 0){
			strHql += " and t.whAreaName like '%" + whAreaName + "%'";
		}
		strHql = strHql + " order by t.whAreaId";
		ls = m_dao.searchEntities(strHql);		
				
		return ls;
	}

	/**
	 * 功能:根据库区ID获得库区
	 * @param whAreaId	库区ID
	 * @return 
	 * @throws Exception
	 */
	public BaseWhareaVirtual getWhareaById(String whAreaId) throws Exception 
	{
		if(whAreaId != null)
		{
			String strSql = " from BaseWhareaVirtual as t where t.whAreaId='" + whAreaId+"'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseWhareaVirtual)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 功能:获得最大的一个库区编码
	 * @param warehouseid 
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public String getMaxWhareaNo(String warehouseid) throws Exception 
	{
		String maxWhareaNo = warehouseid + "0000";
		try
		{
			if(warehouseid != null && !warehouseid.equals("")) {
				String strSql = "from BaseWhareaVirtual as t where t.warehouseid='" + warehouseid + "' order by t.whAreaId desc";
				List ls = m_dao.searchEntities(strSql);
				if(ls != null && !ls.isEmpty())
				{
					BaseWhareaVirtual wharea = (BaseWhareaVirtual)ls.get(0);
					maxWhareaNo = wharea.getwhAreaId();
				}
			}
		}catch(Exception er)
		{
			throw new Exception("获得最大的一个库区编码失败:"+er.getMessage());
		}
		return maxWhareaNo;
	}

	/**
	 * 功能:增加库区
	 * @param dao
	 * @param wharea	库区
	 * @throws Exception
	 */
	public void addWhArea(BaseWhareaVirtual wharea) throws Exception 
	{
		m_dao.save(wharea);
	}

	/**
	 * 功能:获取所有库区列表
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public List getWhAreaList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseWhareaVirtual as t where 1=1 order by t.whAreaId";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("查询库区列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:修改库区
	 * @param dao
	 * @param wharea	库区
	 * @throws Exception
	 */
	public void updateWhArea(BaseWhareaVirtual wharea) throws Exception 
	{
		m_dao.update(wharea);
	}

	/**
	 * 功能:删除库区
	 * @param whAreaId	库区ID
	 * @throws Exception
	 */
	public void deleteWhArea(String whAreaId) throws Exception 
	{
		if(whAreaId != null && !whAreaId.equals(""))
		{
			String strSql  = " delete BaseWhareaVirtual as t where t.whAreaId='" + whAreaId+"'";

			m_dao.deleteSql(strSql);
		}	
	}
	
	/**
	 * 功能:判断仓库下是否有库区
	 * @param id	仓库ID
	 * @throws Exception
	 */
	public boolean isWhHasChildNode(String id) throws Exception {
		
		if(id != null && !id.equals(""))
		{
			String strSql = " from BaseWhareaVirtual as t where t.warehouseid='" + id + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return true;
			}
		}
		return false;
	}

	/**
	 * 功能:取得指定仓库下的库区
	 * @param warehouseid	仓库ID
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaByWhid(String warehouseid) throws Exception {
		
		List ls = null;
		String strHql = "";
		if(warehouseid!=null && warehouseid.length()>0){
			 strHql = "From BaseWhareaVirtual as t where 1=1 and t.warehouseid='" + warehouseid + "' order by t.whAreaId";
		}else{
			 strHql = "From BaseWhareaVirtual as t where 1=1 order by t.whAreaId";
		}
		
		ls = m_dao.searchEntities(strHql);
		
		return ls;
	}
	/**
	 * 功能:取得指定仓库下的库区（立体库）
	 * @param warehouseid	仓库ID
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaByWhidNotTem(String warehouseid) throws Exception {
		
		List ls = null;
		
		String strHql = "From BaseWhareaVirtual as t where 1=1 and t.warehouseid='" + warehouseid + "' and t.istask='Y' order by t.whAreaId";
		ls = m_dao.searchEntities(strHql);
		
		return ls;
	}
    /**
     * 根据仓库获得暂存区
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseWhAreaDao#getZCgetWhAreaByWhid(java.lang.String)
     */
    public BaseWhareaVirtual getZCgetWhAreaByWhid(String warehouseid) throws Exception {
        if(warehouseid != null)
        {
            String strSql = " from BaseWhareaVirtual as t where t.warehouseid='" + warehouseid+"' and t.whAreaType='9'";
            List ls = m_dao.searchEntities(strSql);
            if(ls != null && ls.size() > 0){
                return (BaseWhareaVirtual)ls.get(0);
            }
        }
        return null;
    }
}