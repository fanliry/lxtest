package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseShiftconfigDao;
import com.wms3.bms.standard.entity.base.BaseShiftconfig;

/**
 * 
 * 描述: 班次设置DAO类
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseShiftconfigDaoImpl implements IBaseShiftconfigDao {
	
	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;

	public BaseShiftconfigDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * 功能:获得班次设置
	 * @return 
	 * @throws Exception
	 */
	public BaseShiftconfig getShiftconfig()
	{
		String strSql = " from BaseShiftconfig as t where 1=1";
		List ls = m_dao.searchEntities(strSql);
		if(ls != null && ls.size() == 1){
			return (BaseShiftconfig)ls.get(0);
		}
		return null;
	}

	/**
	 * 功能:修改班次设置
	 * @param shiftconfig	班次设置
	 * @throws Exception
	 */
	public void updateShiftconfig(BaseShiftconfig shiftconfig) throws Exception 
	{
		m_dao.update(shiftconfig);
	}
	
	/**
	 * 功能:增加班次设置
	 * @param shiftconfig	班次设置
	 * @throws Exception
	 */
	public void addShiftconfig(BaseShiftconfig shiftconfig) throws Exception {
		m_dao.save(shiftconfig);
	}

}