package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseProducttypeDao;
import com.wms3.bms.standard.entity.base.BaseProducttype;

/**
 * 
 * 描述: 物品类别DAO类
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseProducttypeDaoImpl implements IBaseProducttypeDao {
	
	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;

	public BaseProducttypeDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * 功能:根据条件查询库区
	 * @param ptname	类别名
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeQuery(String ptname) throws Exception 
	{
		List ls = null;
		
		String strHql = "From BaseProducttype as t where 1=1" ;
		
		if(ptname != null && ptname.trim().length() > 0){
			strHql += " and t.ptname like '%" + ptname + "%'";
		}
		strHql = strHql + " order by t.ptorder";
		ls = m_dao.searchEntities(strHql);		
				
		return ls;
	}

	/**
	 * 功能:根据物品类别ID获得物品类别
	 * @param id	物品类别ID
	 * @return 
	 * @throws Exception
	 */
	public BaseProducttype getProducttypeById(String id) throws Exception 
	{
		if(id!=null && !id.equals(""))
		{
			String strSql = " from BaseProducttype as t where t.ptid='" + id + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseProducttype)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 功能:获得最大的一个物品类别编码
	 * @param parentid	父类别ID
	 * @return
	 * @throws Exception
	 */
	public String getMaxProducttypeNo(String parentid) throws Exception 
	{
		String maxNo = "000";
		try
		{
			String strSql = "from BaseProducttype as t where t.parentid='" + parentid +"' order by t.ptid desc";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && !ls.isEmpty())
			{
				BaseProducttype producttype = (BaseProducttype)ls.get(0);
				maxNo = producttype.getPtid();
			}
		}catch(Exception er)
		{
			throw new Exception("获得最大的一个物品类别编码失败:"+er.getMessage());
		}
		return maxNo;
	}

	/**
	 * 功能:增加物品类别
	 * @param producttype	物品类别
	 * @throws Exception
	 */
	public void addProducttype(BaseProducttype producttype) throws Exception 
	{
		m_dao.save(producttype);
	}

	/**
	 * 功能:获取所有物品类别列表
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseProducttype as t where 1=1 order by t.ptorder";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("查询物品类别列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:修改物品类别
	 * @param producttype	物品类别
	 * @throws Exception
	 */
	public void updateProducttype(BaseProducttype producttype) throws Exception 
	{
		m_dao.update(producttype);
	}

	/**
	 * 功能:删除物品类别
	 * @param id	物品类别ID
	 * @throws Exception
	 */
	public void deleteProducttype(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete BaseProducttype as t where t.ptid='" + id + "'";
			m_dao.deleteSql(strSql);
		}	
	}
}