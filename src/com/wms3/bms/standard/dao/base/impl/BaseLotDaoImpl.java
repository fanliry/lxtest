package com.wms3.bms.standard.dao.base.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.base.IBaseLotDao;
import com.wms3.bms.standard.entity.base.BaseLot;
import com.wms3.bms.standard.entity.base.BaseLotDetail;

/**
 * 
 * 描述: 批次属性DAO类
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseLotDaoImpl implements IBaseLotDao {
	
	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;

	public BaseLotDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * 功能:增加批次，批次详细信息
	 * @param lot	批次
	 * @param lotDetail1, lotDetail2, lotDetail3, lotDetail4, lotDetail5, lotDetail6, 
	 * @param lotDetail7, lotDetail8, lotDetail9, lotDetail10, lotDetail11, lotDetail12 批次详细信息
	 * @throws Exception
	 */
	public void addLot(BaseLot lot, BaseLotDetail lotDetail1, BaseLotDetail lotDetail2, BaseLotDetail lotDetail3, BaseLotDetail lotDetail4, 
			BaseLotDetail lotDetail5, BaseLotDetail lotDetail6, BaseLotDetail lotDetail7, BaseLotDetail lotDetail8, BaseLotDetail lotDetail9, 
			BaseLotDetail lotDetail10, BaseLotDetail lotDetail11, BaseLotDetail lotDetail12) throws Exception {
		
		Session session = m_dao.getSession();
		Transaction tx = null;
		
		try{
			//关联表事务操作
			tx= session.beginTransaction();
			
			//批次
			session.save(lot);
			
			//批次详细信息
			lotDetail1.setM_strLotid(lot.getM_strLotid());
			lotDetail2.setM_strLotid(lot.getM_strLotid());
			lotDetail3.setM_strLotid(lot.getM_strLotid());
			lotDetail4.setM_strLotid(lot.getM_strLotid());
			lotDetail5.setM_strLotid(lot.getM_strLotid());
			lotDetail6.setM_strLotid(lot.getM_strLotid());
			lotDetail7.setM_strLotid(lot.getM_strLotid());
			lotDetail8.setM_strLotid(lot.getM_strLotid());
			lotDetail9.setM_strLotid(lot.getM_strLotid());
			lotDetail10.setM_strLotid(lot.getM_strLotid());
			lotDetail11.setM_strLotid(lot.getM_strLotid());
			lotDetail12.setM_strLotid(lot.getM_strLotid());
			
			session.save(lotDetail1);
			session.save(lotDetail2);
			session.save(lotDetail3);
			session.save(lotDetail4);
			session.save(lotDetail5);
			session.save(lotDetail6);
			session.save(lotDetail7);
			session.save(lotDetail8);
			session.save(lotDetail9);
			session.save(lotDetail10);
			session.save(lotDetail11);
			session.save(lotDetail12);

			tx.commit();
			
		}catch(HibernateException he){
			
			//事务回滚
			if(tx != null){
				tx.rollback();
			}
			Logger.error("增加批次，批次详细信息时出错！"+he.getMessage());
			throw new Exception("增加批次，批次详细信息时出错！"+he.getMessage());
			
		}finally{
			m_dao.closeSession(session);
		}
	}
	
	/**
	 * 功能:根据批次ID获得批次
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public BaseLot getLotById(String lotid) throws Exception {
		
		if(lotid!=null && !lotid.equals(""))
		{
			String strSql = " from BaseLot as t where t.m_strLotid='" + lotid + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseLot)ls.get(0);
			}
		}
		return null;
	}

	/**
	 * 功能:修改批次，批次明细信息
	 * @param defaultLot, lot	批次
	 * @param lotDetail1, lotDetail2, lotDetail3, lotDetail4, lotDetail5, lotDetail6, 
	 * @param lotDetail7, lotDetail8, lotDetail9, lotDetail10, lotDetail11, lotDetail12 批次明细信息
	 * @throws Exception
	 */
	public void updateLot(BaseLot defaultLot, BaseLot lot, BaseLotDetail lotDetail1, BaseLotDetail lotDetail2, BaseLotDetail lotDetail3, BaseLotDetail lotDetail4, 
			BaseLotDetail lotDetail5, BaseLotDetail lotDetail6, BaseLotDetail lotDetail7, BaseLotDetail lotDetail8, BaseLotDetail lotDetail9, 
			BaseLotDetail lotDetail10, BaseLotDetail lotDetail11, BaseLotDetail lotDetail12) throws Exception {
		
		Session session = m_dao.getSession();
		Transaction tx = null;
		
		try{
			//关联表事务操作
			tx= session.beginTransaction();
			
			//原来的默认批次
			if(defaultLot != null){
				session.update(defaultLot);
			}
			
			//批次
			session.update(lot);
			
			//批次明细信息
			session.update(lotDetail1);
			session.update(lotDetail2);
			session.update(lotDetail3);
			session.update(lotDetail4);
			session.update(lotDetail5);
			session.update(lotDetail6);
			session.update(lotDetail7);
			session.update(lotDetail8);
			session.update(lotDetail9);
			session.update(lotDetail10);
			session.update(lotDetail11);
			session.update(lotDetail12);

			tx.commit();
			
		}catch(HibernateException he){
			
			//事务回滚
			if(tx != null){
				tx.rollback();
			}
			Logger.error("修改批次，批次详细信息时出错！"+he.getMessage());
			throw new Exception("修改批次，批次详细信息时出错！"+he.getMessage());
			
		}finally{
			m_dao.closeSession(session);
		}
		
	}

	/**
	 * 功能:删除批次，批次明细信息
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public void deleteLot(String id) throws Exception {
		
		try{
			if(id != null && !id.equals(""))
			{
				List<String> lsSQL = new ArrayList<String>();
				
				String strSql1 = " delete BaseLot as t where t.m_strLotid='" + id + "'";
				lsSQL.add(strSql1);
				
				String strSql2 = " delete BaseLotDetail as t where t.m_strLotid='" + id + "'";
				lsSQL.add(strSql2);
				
	            m_dao.deleteSqlList(lsSQL);
			}
		}catch(Exception er){
        	Logger.error("删除批次，批次明细信息时出错！" + er.getMessage());
            throw new Exception("删除批次，批次明细信息出错：" + er.getMessage());
        } 
		
	}

	/**
	 * 功能:获得默认批次属性
	 * @return 
	 * @throws Exception
	 */
	public BaseLot getDefaultLot() throws Exception {
		
		String strSql = " from BaseLot as t where t.m_strIsdefault='Y'";
		List ls = m_dao.searchEntities(strSql);
		if(ls != null && ls.size() > 0){
			return (BaseLot)ls.get(0);
		}
		return null;
	}

	/**
	 * 功能:获得所有批次属性
	 * @return 
	 * @throws Exception
	 */
	public List getAllLots() throws Exception {
		
		String strSql = " from BaseLot as t order by t.m_strLotid";
		List ls = m_dao.searchEntities(strSql);
		
		return ls;
	}

	
}