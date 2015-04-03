package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseWarehouseDao;
import com.wms3.bms.standard.entity.base.BaseWarehouse;
/**
 * 
 * ����: �ֿ�DAO��
 * @author hug 2012-3-12
 * @since WMS 3.0
 */
public class BaseWarehouseDaoImpl implements IBaseWarehouseDao{
    
    /** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    
    public BaseWarehouseDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    /**
     * ����:���Ӳֿ�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseWarehouseDao#addWarehouse(com.ricosoft.common.dao.dataSource.EntityDAO, com.wms3.bms.standard.entity.base.BaseWarehouse)
     */
    public void addWarehouse(BaseWarehouse warehouse) throws Exception
    {
        m_dao.save(warehouse);
    }

    /**
     * ����:�������һ���ֿ����
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
            throw new Exception("�������һ���ֿ����ʧ��:"+er.getMessage());
        }
        return maxWarehouseNo;
    }
    
    /**
     * ����:������вֿ���Ϣ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseWarehouseDao#getWarehouseList()
     */
    public List getWarehouseList() throws Exception {
    	
        String hql = "from BaseWarehouse order by warehouseid";
        List ls = m_dao.searchEntities(hql);
        return ls;
    }
    
    /**
     * ����:���ݲֿ�ID��òֿ�
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
     * ����:ɾ���ֿ�
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
     * ���ܣ��޸Ĳֿ�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseWarehouseDao#updateWarehouse(com.wms3.bms.standard.entity.base.BaseWarehouse)
     */
    public void updateWarehouse(BaseWarehouse warehouse) throws Exception {
        
        m_dao.update(warehouse);
    }

	/**
	 * ����:����������ѯ�ֿ���Ϣ
	 * @param warehousename		�ֿ���
	 * @param warehousetype		�ֿ�����
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
     * ����:��ȡ���ݱ����Ƿ���ڵ�ǰ���ݿ�
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
	 * ����:���òֿⲻ�ǵ�ǰ�ֿ�
	 * @throws Exception
	 */
	public void updateIsCurrentWh() throws Exception {

		String strSql="update BaseWarehouse as t set t.iscurrent='N'";
		m_dao.updateSql(strSql);
	}
}
