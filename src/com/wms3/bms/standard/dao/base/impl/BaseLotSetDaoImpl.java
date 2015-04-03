package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.base.IBaseLotSetDao;
import com.wms3.bms.standard.entity.base.BaseLotSet;

/**
 * 
 * 描述: 批次属性设置DAO类
 * @author hug 2012-3-12
 * @since WMS 3.0
 */
public class BaseLotSetDaoImpl implements IBaseLotSetDao{
    
    /** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;
    
    public BaseLotSetDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }

	/**
	 * 功能:查询批次属性设置不同类型
	 * @return 
	 * @throws Exception
	 */
	public List getLotsetType() throws Exception {
		
		List ls = null;
		String strHql = "select distinct t.lottype, t.lottypename, t.remark From BaseLotSet as t where 1=1";
		
		strHql = strHql + " order by t.lottype";
		
		ls = m_dao.searchEntities(strHql);		
		return ls;
	}

	/**
	 * 功能:增加批次属性设置
	 * @param lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, 
	 * @param lotset7, lotset8, lotset9, lotset10, lotset11, lotset12
	 * @throws Exception
	 */
	public void addLotset(BaseLotSet lotset1, BaseLotSet lotset2, BaseLotSet lotset3, BaseLotSet lotset4, BaseLotSet lotset5, BaseLotSet lotset6, 
			BaseLotSet lotset7, BaseLotSet lotset8, BaseLotSet lotset9, BaseLotSet lotset10, BaseLotSet lotset11, BaseLotSet lotset12) throws Exception {
		
		Session session = m_dao.getSession();
		Transaction tx = null;
		
		try{
			//关联表事务操作
			tx= session.beginTransaction();
			
			session.save(lotset1);
			session.save(lotset2);
			session.save(lotset3);
			session.save(lotset4);
			session.save(lotset5);
			session.save(lotset6);
			session.save(lotset7);
			session.save(lotset8);
			session.save(lotset9);
			session.save(lotset10);
			session.save(lotset11);
			session.save(lotset12);

			tx.commit();
			
		}catch(HibernateException he){
			
			//事务回滚
			if(tx != null){
				tx.rollback();
			}
			Logger.error("增加批次属性设置时出错！"+he.getMessage());
			throw new Exception("增加批次属性设置时出错！"+he.getMessage());
			
		}finally{
			m_dao.closeSession(session);
		}
	}

	/**
	 * 功能:根据类型查询批次属性设置的列表
	 * @param lottype	类型
	 * @return 
	 * @throws Exception
	 */
	public List getLotsetByType(String lottype) throws Exception {
		
		List ls = null;
		String strHql = "From BaseLotSet as t where 1=1";
		
		if(lottype != null && lottype.trim().length() > 0){
			strHql += " and t.lottype='" + lottype + "'";
		}
		
		ls = m_dao.searchEntities(strHql);		
				
		return ls;
	}

	/**
	 * 功能:删除批次属性设置
	 * @param lottype	类型
	 * @return 
	 * @throws Exception
	 */
	public void deleteLotset(String lottype) throws Exception {
		
        String strSql = " delete BaseLotSet as t where t.lottype='" + lottype+"'";
        m_dao.deleteSql(strSql);
	}
	
	/**
	 * 功能:根据Id查询批次属性设置
	 * @param lotattid	Id
	 * @return 
	 * @throws Exception
	 */
	public BaseLotSet getLotsetById(String lotattid) throws Exception {
		
		if(lotattid != null)
		{
			String strSql = " from BaseLotSet as t where t.lotattid='" + lotattid + "'";
			List ls = m_dao.searchEntities(strSql);
			
			if(ls != null && ls.size() > 0){
				return (BaseLotSet)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 功能:修改批次属性设置
	 * @param lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, 
	 * @param lotset7, lotset8, lotset9, lotset10, lotset11, lotset12
	 * @throws Exception
	 */
	public void updateLotset(BaseLotSet lotset1, BaseLotSet lotset2, BaseLotSet lotset3, BaseLotSet lotset4, BaseLotSet lotset5, BaseLotSet lotset6, 
			BaseLotSet lotset7, BaseLotSet lotset8, BaseLotSet lotset9, BaseLotSet lotset10, BaseLotSet lotset11, BaseLotSet lotset12) throws Exception {
		
		Session session = m_dao.getSession();
		Transaction tx = null;
		
		try{
			//关联表事务操作
			tx= session.beginTransaction();
			
			session.update(lotset1);
			session.update(lotset2);
			session.update(lotset3);
			session.update(lotset4);
			session.update(lotset5);
			session.update(lotset6);
			session.update(lotset7);
			session.update(lotset8);
			session.update(lotset9);
			session.update(lotset10);
			session.update(lotset11);
			session.update(lotset12);

			tx.commit();
			
		}catch(HibernateException he){
			
			//事务回滚
			if(tx != null){
				tx.rollback();
			}
			Logger.error("增加批次属性设置时出错！"+he.getMessage());
			throw new Exception("增加批次属性设置时出错！"+he.getMessage());
			
		}finally{
			m_dao.closeSession(session);
		}
		
	}

	/**
	 * 功能:判断该类型是否存在
	 * @param lottype	类型
	 * @throws Exception
	 */
	public boolean isLotsetExist(String lottype) throws Exception {
		
		String strSql = "select count(*) from BaseLotSet as t where t.lottype='" + lottype + "'";
		List ls = m_dao.searchEntities(strSql);
		
		if((Long)ls.get(0) > 0){
			return true;
		}

		return false;
	}
    
    /**
     * 根据批次类型获得可显示的批次属性，并按设置的顺序排序
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseLotSetDao#getViewLot(java.lang.String)
     */
    public List<BaseLotSet> getViewLot(String strLotType) throws Exception {
        try
        {
            String strSql = "from BaseLotSet as t where t.lottype='" + strLotType + "' and t.islot='Y' order by t.lotseq";
            List ls = m_dao.searchEntities(strSql);
            return ls;
        }catch(Exception er){
            throw new Exception("根据批次类型获得可显示的批次属性出错：" + er.getMessage());
        }
    }
    /**
     * 统计所有要显示的批次类型
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseLotSetDao#getGroupLotType()
     */
    public List<String> getGroupLotType() throws Exception {
        try
        {
            String strSql = "select t.lottype from BaseLotSet as t group by t.lottype";
            List ls = m_dao.searchEntities(strSql);
            return ls;
        }catch(Exception er){
            throw new Exception("统计所有要显示的批次类型出错：" + er.getMessage());
        }
    }
    
    
}
