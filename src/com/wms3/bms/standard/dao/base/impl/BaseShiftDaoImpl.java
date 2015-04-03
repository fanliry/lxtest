package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.base.IBaseShiftDao;
import com.wms3.bms.standard.entity.base.BaseShift;

/**
 * 
 * 描述: 班次DAO类
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseShiftDaoImpl implements IBaseShiftDao {
	
	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;

	public BaseShiftDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * 功能:根据班次ID获得班次
	 * @param id	班次ID
	 * @return 
	 * @throws Exception
	 */
	public BaseShift getShiftById(String id) throws Exception 
	{
		if(id!=null && !id.equals(""))
		{
			String strSql = " from BaseShift as t where t.m_strShiftId='" + id + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseShift)ls.get(0);
			}
		}
		return null;
	}

	/**
	 * 功能:增加班次
	 * @param shift	班次
	 * @param lastshift 最后班次（出入库类型相同）
	 * @throws Exception
	 */
	public void addShift(BaseShift shift, BaseShift lastshift) throws Exception 
	{
		Session session = m_dao.getSession();
		Transaction tx = null;
		try
		{
			//关联表事务操作
			tx= session.beginTransaction();
			if(lastshift != null)
			{	
				//更新上一个最后班次的最后标志为否
				lastshift.setM_strIslast("N");
				session.update(lastshift);		
			}
			if(shift != null)
			{
				session.save(shift);		
			}
			tx.commit();
		}
		catch(HibernateException he)
		{
			//事务回滚
			if(tx != null){
				tx.rollback();
			}
			Logger.error("修改上一个最后班次的最后标志和添加新班次时出错！"+he.getMessage());
			throw new Exception("修改上一个最后班次的最后标志和添加新班次时出错！"+he.getMessage());
		}
		finally
		{
			m_dao.closeSession(session);
		} 	
	}

	/**
	 * 功能:获取所有班次列表
	 * @return 
	 * @throws Exception
	 */
	public List getShiftList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseShift as t where 1=1 order by t.m_strShiftId";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("查询班次列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:修改班次
	 * @param shift	班次
	 * @throws Exception
	 */
	public void updateShift(BaseShift shift) throws Exception 
	{
		m_dao.update(shift);
	}

	/**
	 * 功能:删除班次
	 * @param id	班次ID
	 * @throws Exception
	 */
	public void deleteShift(String id) throws Exception 
	{
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete BaseShift as t where t.m_strShiftId='" + id + "'";
			m_dao.deleteSql(strSql);
		}	
	}
	
	/**
	 * 功能:获的最后一个班次
	 * @param id	班次ID
	 * @return 
	 * @throws Exception
	 */
	public BaseShift getLastShift(String strIn_Out_Type) throws Exception {
		
		if(strIn_Out_Type!=null && !strIn_Out_Type.equals(""))
		{
			String strSql = " from BaseShift as t where t.m_strIslast='Y' and t.m_strIn_Out_Type='" + strIn_Out_Type + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls == null || ls.size() == 0){
				return null;
			}else if(ls.size() == 1){
				return (BaseShift)ls.get(0);
			}else{
				throw new Exception("获的最后一个班次时候出错，班次入出类型是:" + strIn_Out_Type);
			}
		}
		return null;
	}

	/**
	 * 功能:根据班次名查询班次是否存在
	 * @param shiftname	班次名
	 * @param inouttype 入出类型
	 * @return 
	 */
	public boolean isShiftExist(String shiftname, String inouttype) {
		
		String strSql = " from BaseShift as t where t.m_strShiftName='" + shiftname + "' and t.m_strIn_Out_Type='" + inouttype + "'";
		List ls = m_dao.searchEntities(strSql);
		if(ls != null && ls.size() > 0){
			return true;
		}else{
			return false;
		}
	}
}