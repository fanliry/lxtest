package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseBarcodeDao;
import com.wms3.bms.standard.entity.base.BaseBarcode;

/**
 * 描述: 条形码DAO类
 * @author fangjie
 * 
 */
public class BaseBarcodeDaoImpl implements IBaseBarcodeDao {

	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;
    
	public BaseBarcodeDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * 功能:根据条形码ID获得条形码
	 * @param id	条形码ID
	 * @return
	 * @throws Exception
	 */
	public BaseBarcode getBarcodeById(String id) throws Exception
	{
		if(id != null || !id.equals(""))
		{
			String strSql = " from BaseBarcode as t where t.barcode='" + id + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseBarcode)ls.get(0);
			}
		}
		return null;
	}

	/**
	 * 功能:增加条形码
	 * @param barcode	条形码
	 * @throws Exception
	 */
	public void addBarcode(BaseBarcode barcode) throws Exception{
		
		m_dao.save(barcode);
	}

	/**
	 * 功能:获取所有条形码列表
	 * @return
	 * @throws Exception
	 */
	public List getBarcodeList() throws Exception
	{
		List ls = null;
		try
		{
			String strHql = "From BaseBarcode as t where 1=1 order by t.barcode";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("查询条形码列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:修改条形码
	 * @param barcode	条形码
	 * @throws Exception
	 */
	public void updateBarcode(BaseBarcode barcode) throws Exception{
		
		m_dao.update(barcode);
	}

	/**
	 * 功能:删除条形码
	 * @param id	条形码ID
	 * @throws Exception
	 */
	public void deleteBarcode(String id) throws Exception
	{
		if(id != null)
		{
			String strSql  = " delete BaseBarcode as t where t.barcode='" + id + "'";

			m_dao.deleteSql(strSql);
		}	
	}
	
}
