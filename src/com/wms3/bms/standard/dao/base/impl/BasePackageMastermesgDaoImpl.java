package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBasePackageMastermesgDao;
import com.wms3.bms.standard.entity.base.BasePackageMastermesg;

/**
 * 
 * 描述: 包装主信息DAO类
 * @author fangjie 
 * @since WMS 3.0
 */
public class BasePackageMastermesgDaoImpl implements IBasePackageMastermesgDao {
	
	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;

	public BasePackageMastermesgDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * 功能:根据包装ID获得包装主信息
	 * @param packageid	包装ID
	 * @param type
	 * @return 
	 * @throws Exception
	 */
	public BasePackageMastermesg getPackageMastermesg(String packageid, String type) throws Exception {
		
		String strSql = " from BasePackageMastermesg as t where t.packid='" + packageid + "' and t.pkgunit='" + type + "'";
		List ls = m_dao.searchEntities(strSql);
		if(ls != null && ls.size() > 0){
			return (BasePackageMastermesg)ls.get(0);
		}
		return null;
	}

	/**
	 * 功能:根据包装主信息ID获得包装主信息
	 * @param packmasterid	包装主信息ID
	 * @return 
	 * @throws Exception
	 */
	public BasePackageMastermesg getPackageMastermesgById(String packmasterid) throws Exception {
		
		String strSql = " from BasePackageMastermesg as t where t.packmasterid='" + packmasterid + "'";
		List ls = m_dao.searchEntities(strSql);
		if(ls != null && ls.size() > 0){
			return (BasePackageMastermesg)ls.get(0);
		}
		return null;
	}
    
    public List<BasePackageMastermesg> getPackageMastermesgList(String strPackageId, String inoutType) throws Exception {
        String strSql = " from BasePackageMastermesg as t where t.packid='" + strPackageId + "'";
        if(inoutType != null && inoutType.equals("1")){//入库使用
            strSql = strSql + " and t.inused='Y'";
        }else if(inoutType != null && inoutType.equals("2")){//出库使用
            strSql = strSql + " and t.outused='Y'";
        }
        
        List ls = m_dao.searchEntities(strSql);
        return ls;
    }

	
}