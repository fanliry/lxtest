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
 * ����: ������������DAO��
 * @author hug 2012-3-12
 * @since WMS 3.0
 */
public class BaseLotSetDaoImpl implements IBaseLotSetDao{
    
    /** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    
    public BaseLotSetDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }

	/**
	 * ����:��ѯ�����������ò�ͬ����
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
	 * ����:����������������
	 * @param lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, 
	 * @param lotset7, lotset8, lotset9, lotset10, lotset11, lotset12
	 * @throws Exception
	 */
	public void addLotset(BaseLotSet lotset1, BaseLotSet lotset2, BaseLotSet lotset3, BaseLotSet lotset4, BaseLotSet lotset5, BaseLotSet lotset6, 
			BaseLotSet lotset7, BaseLotSet lotset8, BaseLotSet lotset9, BaseLotSet lotset10, BaseLotSet lotset11, BaseLotSet lotset12) throws Exception {
		
		Session session = m_dao.getSession();
		Transaction tx = null;
		
		try{
			//�������������
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
			
			//����ع�
			if(tx != null){
				tx.rollback();
			}
			Logger.error("����������������ʱ����"+he.getMessage());
			throw new Exception("����������������ʱ����"+he.getMessage());
			
		}finally{
			m_dao.closeSession(session);
		}
	}

	/**
	 * ����:�������Ͳ�ѯ�����������õ��б�
	 * @param lottype	����
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
	 * ����:ɾ��������������
	 * @param lottype	����
	 * @return 
	 * @throws Exception
	 */
	public void deleteLotset(String lottype) throws Exception {
		
        String strSql = " delete BaseLotSet as t where t.lottype='" + lottype+"'";
        m_dao.deleteSql(strSql);
	}
	
	/**
	 * ����:����Id��ѯ������������
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
	 * ����:�޸�������������
	 * @param lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, 
	 * @param lotset7, lotset8, lotset9, lotset10, lotset11, lotset12
	 * @throws Exception
	 */
	public void updateLotset(BaseLotSet lotset1, BaseLotSet lotset2, BaseLotSet lotset3, BaseLotSet lotset4, BaseLotSet lotset5, BaseLotSet lotset6, 
			BaseLotSet lotset7, BaseLotSet lotset8, BaseLotSet lotset9, BaseLotSet lotset10, BaseLotSet lotset11, BaseLotSet lotset12) throws Exception {
		
		Session session = m_dao.getSession();
		Transaction tx = null;
		
		try{
			//�������������
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
			
			//����ع�
			if(tx != null){
				tx.rollback();
			}
			Logger.error("����������������ʱ����"+he.getMessage());
			throw new Exception("����������������ʱ����"+he.getMessage());
			
		}finally{
			m_dao.closeSession(session);
		}
		
	}

	/**
	 * ����:�жϸ������Ƿ����
	 * @param lottype	����
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
     * �����������ͻ�ÿ���ʾ���������ԣ��������õ�˳������
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
            throw new Exception("�����������ͻ�ÿ���ʾ���������Գ���" + er.getMessage());
        }
    }
    /**
     * ͳ������Ҫ��ʾ����������
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
            throw new Exception("ͳ������Ҫ��ʾ���������ͳ���" + er.getMessage());
        }
    }
    
    
}
