package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseWarehouseDao;
import com.wms3.bms.standard.entity.base.BaseWarehouse;
/**
 * 
 * 描述: 仓库DAO类
 * @author hug 2012-3-12
 * @since WMS 3.0
 */
public class BaseWarehouseDaoImpl implements IBaseWarehouseDao{
    
    /** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;
    
    public BaseWarehouseDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    /**
     * 功能:增加仓库
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseWarehouseDao#addWarehouse(com.ricosoft.common.dao.dataSource.EntityDAO, com.wms3.bms.standard.entity.base.BaseWarehouse)
     */
    public void addWarehouse(BaseWarehouse warehouse) throws Exception
    {
        m_dao.save(warehouse);
    }

    /**
     * 功能:获得最大的一个仓库编码
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseWarehouseDao#getMaxWarehouseNo(com.ricosoft.common.dao.dataSource.EntityDAO)
     */
    public String getMaxWarehouseNo() throws Exception {
        String maxWarehouseNo = "000";
        try
        {
            String strSql = "from BaseWarehouse as m where 1=1 order by m.warehouseid desc";
            List ls = m_dao.searchEntities(strSql);
            if(ls != null && !ls.isEmpty())
            {
                BaseWarehouse wh = (BaseWarehouse)ls.get(0);
                maxWarehouseNo = wh.getWarehouseid();
            }
        }catch(Exception er)
        {
            throw new Exception("获得最大的一个仓库编码失败:"+er.getMessage());
        }
        return maxWarehouseNo;
    }
    
    /**
     * 功能:获得所有仓库信息
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseWarehouseDao#getWarehouseList()
     */
    public List getWarehouseList() throws Exception {
    	
        String hql = "from BaseWarehouse order by warehouseid";
        List ls = m_dao.searchEntities(hql);
        return ls;
    }
    
    /**
     * 功能:根据仓库ID获得仓库
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseWarehouseDao#getWarehouseById(java.lang.String)
     */
    public BaseWarehouse getWarehouseById(String strWarehouseId) throws Exception {
        if(strWarehouseId != null)
        {
            String strSql = " from BaseWarehouse as wh where wh.warehouseid='" + strWarehouseId+"'";
            List ls = m_dao.searchEntities(strSql);
            if(ls != null && ls.size() > 0){
                return (BaseWarehouse)ls.get(0);
            }
        }
       return null;    
    }
    /**
     * 功能:删除仓库
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseWarehouseDao#deleteWarehouse(java.lang.String)
     */
    public void deleteWarehouse(String strWarehouseId) throws Exception {
        if(strWarehouseId != null)
        {
            String strSql = " delete BaseWarehouse as wh where wh.warehouseid='" + strWarehouseId+"'";
            m_dao.deleteSql(strSql);
        }
    }
    /**
     * 功能：修改仓库
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseWarehouseDao#updateWarehouse(com.wms3.bms.standard.entity.base.BaseWarehouse)
     */
    public void updateWarehouse(BaseWarehouse warehouse) throws Exception {
        
        m_dao.update(warehouse);
    }

	/**
	 * 功能:根据条件查询仓库信息
	 * @param warehousename		仓库名
	 * @param warehousetype		仓库类型
	 * @return 
	 * @throws Exception
	 */
	public List getWarehouseQuery(String warehousename, String warehousetype) throws Exception {
		
		List ls = null;
		String strHql = "From BaseWarehouse as t where 1=1";

		if(warehousename != null && warehousename.trim().length() > 0){
			strHql += " and t.warehousename like '%" + warehousename + "%'";
		}
		if(warehousetype != null && warehousetype.trim().length() > 0){
			strHql += " and t.warehousetype='" + warehousetype + "'";
		}
		
		strHql = strHql + " order by t.warehouseid";
		ls = m_dao.searchEntities(strHql);		
				
		return ls;
		
	}

	/**
     * 功能:获取数据表中是否存在当前数据库
     * @return
     * @throws Exception
     */
	public List getWarehouseCurrent() throws Exception {
		
		String strSql="from BaseWarehouse as t where t.iscurrent='Y'";
		List ls = m_dao.searchEntities(strSql);
		if(ls.size()>0)
		{
			return ls;
		}
		return null;
	}

	/**
	 * 功能:设置仓库不是当前仓库
	 * @throws Exception
	 */
	public void updateIsCurrentWh() throws Exception {

		String strSql="update BaseWarehouse as t set t.iscurrent='N'";
		m_dao.updateSql(strSql);
	}
}
