package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseShiftconfigDao;
import com.wms3.bms.standard.entity.base.BaseShiftconfig;

/**
 * 
 * ����: �������DAO��
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseShiftconfigDaoImpl implements IBaseShiftconfigDao {
	
	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;

	public BaseShiftconfigDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * ����:��ð������
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
	 * ����:�޸İ������
	 * @param shiftconfig	�������
	 * @throws Exception
	 */
	public void updateShiftconfig(BaseShiftconfig shiftconfig) throws Exception 
	{
		m_dao.update(shiftconfig);
	}
	
	/**
	 * ����:���Ӱ������
	 * @param shiftconfig	�������
	 * @throws Exception
	 */
	public void addShiftconfig(BaseShiftconfig shiftconfig) throws Exception {
		m_dao.save(shiftconfig);
	}

}