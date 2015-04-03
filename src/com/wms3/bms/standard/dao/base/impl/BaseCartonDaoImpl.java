package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseCartonDao;
import com.wms3.bms.standard.entity.base.BaseCarton;

/**
 * 
 * 描述: 周转箱DAO类
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseCartonDaoImpl implements IBaseCartonDao {
	
	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;

	public BaseCartonDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * 功能:根据周转箱ID获得周转箱
	 * @param id	周转箱ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCarton getCartonById(String id) throws Exception 
	{
		if(id!=null && !id.equals(""))
		{
			String strSql = " from BaseCarton as t where t.cartonid='" + id + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseCarton)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 功能:获得最大的一个周转箱编码
	 * @return
	 * @throws Exception
	 */
	public String getMaxCartonNo() throws Exception 
	{
		String maxNo = "0000";
		try
		{
			String strSql = "from BaseCarton as t where 1=1 order by t.cartonid desc";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && !ls.isEmpty())
			{
				BaseCarton carton = (BaseCarton)ls.get(0);
				maxNo = carton.getCartonid();
			}
		}catch(Exception er)
		{
			throw new Exception("获得最大的一个周转箱编码失败:"+er.getMessage());
		}
		return maxNo;
	}

	/**
	 * 功能:增加周转箱
	 * @param carton	周转箱
	 * @throws Exception
	 */
	public void addCarton(BaseCarton carton) throws Exception 
	{
		m_dao.save(carton);
	}

	/**
	 * 功能:获取所有周转箱列表
	 * @return 
	 * @throws Exception
	 */
	public List getCartonList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseCarton as t where 1=1 order by t.cartonid";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("查询周转箱列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:修改周转箱
	 * @param carton	周转箱
	 * @throws Exception
	 */
	public void updateCarton(BaseCarton carton) throws Exception 
	{
		m_dao.update(carton);
	}

	/**
	 * 功能:删除周转箱
	 * @param id	周转箱ID
	 * @throws Exception
	 */
	public void deleteCarton(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete BaseCarton as t where t.cartonid='" + id + "'";
			m_dao.deleteSql(strSql);
		}	
	}
}