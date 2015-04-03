package com.wms3.bms.standard.dao.base.impl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseLotDetailDao;
import com.wms3.bms.standard.entity.base.BaseLotDetail;

/**
 * 
 * 描述: 批次属性详细信息DAO类
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseLotDetailDaoImpl implements IBaseLotDetailDao {
	
	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;

	public BaseLotDetailDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * 功能:根据批次ID获得批次详细信息列表
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public List getListByLotId(String lotid) throws Exception {
		
		List ls = null;
		
		if(lotid!=null && !lotid.equals("")){
			
			try{
				String strHql = "From BaseLotDetail as t where t.m_strLotid='" + lotid + "'";
				ls = m_dao.searchEntities(strHql);
				
			}catch(Exception er){
				
				throw new Exception("根据批次ID获得批次详细信息列表出错:" + er.getMessage());
			}
		}
		
		return ls;
	}
	/**
	 * 功能:根据批次ID获得批次详细信息列表
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public List getDetailListByLotId(String lotid) throws Exception {
		
		List ls = null;
		
		if(lotid!=null && !lotid.equals("")){
			
			try{
				String strHql = "From BaseLotDetail as t where t.m_strLotid='" + lotid + "' and t.m_strLotatt_flag!='2'";
				ls = m_dao.searchEntities(strHql);
				
			}catch(Exception er){
				
				throw new Exception("根据批次ID获得批次详细信息列表出错:" + er.getMessage());
			}
		}
		
		return ls;
	}
	/**
	 * 功能:根据批次明细ID获得批次明细
	 * @param id	批次明细ID
	 * @return 
	 * @throws Exception
	 */
	public BaseLotDetail getLotDetailByDetailId(String lotdetailid) throws Exception {
		
		String strSql = " from BaseLotDetail as t where t.m_strLotdetailid='" + lotdetailid + "'";
		List ls = m_dao.searchEntities(strSql);
		if(ls != null && ls.size() > 0){
			return (BaseLotDetail)ls.get(0);
		}
		return null;
	}	
	/**
	 * 功能:获得批次详细信息列表
	 * @return 
	 * @throws Exception
	 */
	public List getLotList() throws Exception {
		
		List ls = null;
		
		try{
			String strHql = "From BaseLot";
			ls = m_dao.searchEntities(strHql);
				
		}catch(Exception er){
				
			throw new Exception("根据批次ID获得批次详细信息列表出错:" + er.getMessage());
		}
		return ls;
	}
}