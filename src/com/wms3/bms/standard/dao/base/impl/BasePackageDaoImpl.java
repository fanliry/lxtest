package com.wms3.bms.standard.dao.base.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.base.IBasePackageDao;
import com.wms3.bms.standard.entity.base.BasePackage;
import com.wms3.bms.standard.entity.base.BasePackageMastermesg;

/**
 * 
 * 描述: 包装DAO类
 * @author fangjie 
 * @since WMS 3.0
 */
public class BasePackageDaoImpl implements IBasePackageDao {
	
	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;

	public BasePackageDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * 功能:根据包装ID获得包装
	 * @param id	包装ID
	 * @return 
	 * @throws Exception
	 */
	public BasePackage getPackageById(String id) throws Exception 
	{
		if(id!=null && !id.equals(""))
		{
			String strSql = " from BasePackage as t where t.packageid='" + id + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BasePackage)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 功能:增加包装
	 * @param pk	包装
	 * @throws Exception
	 */
	public void addPackage(BasePackage pk) throws Exception 
	{
		m_dao.save(pk);
	}

	/**
	 * 功能:获取所有包装列表
	 * @return 
	 * @throws Exception
	 */
	public List getPackageList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BasePackage as t where 1=1 order by t.packageid";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("查询包装列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:修改包装
	 * @param pk	包装
	 * @throws Exception
	 */
	public void updatePackage(BasePackage pk) throws Exception 
	{
		m_dao.update(pk);
	}

	/**
	 * 功能:删除包装,包装附加信息,包装主信息
	 * @param id	包装ID
	 * @throws Exception
	 */
	public void deletePackage(String id) throws Exception 
	{
		try{
			if(id != null && !id.equals(""))
			{
				List<String> lsSQL = new ArrayList<String>();
				
				String strSql1  = " delete BasePackage as t where t.packageid='" + id + "'";
				lsSQL.add(strSql1);
				
				String strSql2  = " delete BasePackageMastermesg as t where t.packid='" + id + "'";
				lsSQL.add(strSql2);
				
	            m_dao.deleteSqlList(lsSQL);
			}
		}catch(Exception er){
        	Logger.error("删除包装,包装主信息,包装附加信息时出错！" + er.getMessage());
            throw new Exception("删除包装,包装附加信息,包装主信息出错：" + er.getMessage());
        } 
	}
	
	/**
	 * 功能:增加包装,包装附加信息,包装主信息
	 * @param pk	包装
	 * @param pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5 包装主信息
	 * @throws Exception
	 */
	public void addPackage(BasePackage pk, BasePackageMastermesg pkmaster1, BasePackageMastermesg pkmaster2, BasePackageMastermesg pkmaster3, 
			BasePackageMastermesg pkmaster4, BasePackageMastermesg pkmaster5) throws Exception {
		
		Session session = m_dao.getSession();
		Transaction tx = null;
		
		try{
			//关联表事务操作
			tx= session.beginTransaction();
			
			//包装
			session.save(pk);
			
			//包装主信息
			pkmaster1.setPackid(pk.getPackageid());
			pkmaster2.setPackid(pk.getPackageid());
			pkmaster3.setPackid(pk.getPackageid());
			pkmaster4.setPackid(pk.getPackageid());
			pkmaster5.setPackid(pk.getPackageid());
			session.save(pkmaster1);
			session.save(pkmaster2);
			session.save(pkmaster3);
			session.save(pkmaster4);
			session.save(pkmaster5);
			
			tx.commit();
			
		}catch(HibernateException he){
			
			//事务回滚
			if(tx != null){
				tx.rollback();
			}
			Logger.error("增加包装,包装主信息,包装附加信息时出错！"+he.getMessage());
			throw new Exception("增加包装,包装主信息,包装附加信息时出错！"+he.getMessage());
			
		}finally{
			m_dao.closeSession(session);
		} 	
		
	}

	/**
	 * 功能:修改包装,包装附加信息,包装主信息
	 * @param pk	包装
	 * @param pkextra1, pkextra2, pkextra3, pkextra4, pkextra5 包装附加信息
	 * @param pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5 包装主信息
	 * @throws Exception
	 */
	public void updatePackage(BasePackage pk, BasePackageMastermesg pkmaster1, BasePackageMastermesg pkmaster2, BasePackageMastermesg pkmaster3, 
			BasePackageMastermesg pkmaster4, BasePackageMastermesg pkmaster5) throws Exception {
		
		Session session = m_dao.getSession();
		Transaction tx = null;
		
		try{
			//关联表事务操作
			tx= session.beginTransaction();
			
			//包装
			session.update(pk);
			
			//包装主信息
			session.update(pkmaster1);
			session.update(pkmaster2);
			session.update(pkmaster3);
			session.update(pkmaster4);
			session.update(pkmaster5);
			
			tx.commit();
			
		}catch(HibernateException he){
			
			//事务回滚
			if(tx != null){
				tx.rollback();
			}
			Logger.error("修改包装,包装主信息,包装附加信息时出错！"+he.getMessage());
			throw new Exception("修改包装,包装主信息,包装附加信息时出错！"+he.getMessage());
			
		}finally{
			m_dao.closeSession(session);
		} 
	}
}