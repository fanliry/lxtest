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
 * ����: ��װDAO��
 * @author fangjie 
 * @since WMS 3.0
 */
public class BasePackageDaoImpl implements IBasePackageDao {
	
	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;

	public BasePackageDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * ����:���ݰ�װID��ð�װ
	 * @param id	��װID
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
	 * ����:���Ӱ�װ
	 * @param pk	��װ
	 * @throws Exception
	 */
	public void addPackage(BasePackage pk) throws Exception 
	{
		m_dao.save(pk);
	}

	/**
	 * ����:��ȡ���а�װ�б�
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
			throw new Exception("��ѯ��װ�б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:�޸İ�װ
	 * @param pk	��װ
	 * @throws Exception
	 */
	public void updatePackage(BasePackage pk) throws Exception 
	{
		m_dao.update(pk);
	}

	/**
	 * ����:ɾ����װ,��װ������Ϣ,��װ����Ϣ
	 * @param id	��װID
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
        	Logger.error("ɾ����װ,��װ����Ϣ,��װ������Ϣʱ����" + er.getMessage());
            throw new Exception("ɾ����װ,��װ������Ϣ,��װ����Ϣ����" + er.getMessage());
        } 
	}
	
	/**
	 * ����:���Ӱ�װ,��װ������Ϣ,��װ����Ϣ
	 * @param pk	��װ
	 * @param pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5 ��װ����Ϣ
	 * @throws Exception
	 */
	public void addPackage(BasePackage pk, BasePackageMastermesg pkmaster1, BasePackageMastermesg pkmaster2, BasePackageMastermesg pkmaster3, 
			BasePackageMastermesg pkmaster4, BasePackageMastermesg pkmaster5) throws Exception {
		
		Session session = m_dao.getSession();
		Transaction tx = null;
		
		try{
			//�������������
			tx= session.beginTransaction();
			
			//��װ
			session.save(pk);
			
			//��װ����Ϣ
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
			
			//����ع�
			if(tx != null){
				tx.rollback();
			}
			Logger.error("���Ӱ�װ,��װ����Ϣ,��װ������Ϣʱ����"+he.getMessage());
			throw new Exception("���Ӱ�װ,��װ����Ϣ,��װ������Ϣʱ����"+he.getMessage());
			
		}finally{
			m_dao.closeSession(session);
		} 	
		
	}

	/**
	 * ����:�޸İ�װ,��װ������Ϣ,��װ����Ϣ
	 * @param pk	��װ
	 * @param pkextra1, pkextra2, pkextra3, pkextra4, pkextra5 ��װ������Ϣ
	 * @param pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5 ��װ����Ϣ
	 * @throws Exception
	 */
	public void updatePackage(BasePackage pk, BasePackageMastermesg pkmaster1, BasePackageMastermesg pkmaster2, BasePackageMastermesg pkmaster3, 
			BasePackageMastermesg pkmaster4, BasePackageMastermesg pkmaster5) throws Exception {
		
		Session session = m_dao.getSession();
		Transaction tx = null;
		
		try{
			//�������������
			tx= session.beginTransaction();
			
			//��װ
			session.update(pk);
			
			//��װ����Ϣ
			session.update(pkmaster1);
			session.update(pkmaster2);
			session.update(pkmaster3);
			session.update(pkmaster4);
			session.update(pkmaster5);
			
			tx.commit();
			
		}catch(HibernateException he){
			
			//����ع�
			if(tx != null){
				tx.rollback();
			}
			Logger.error("�޸İ�װ,��װ����Ϣ,��װ������Ϣʱ����"+he.getMessage());
			throw new Exception("�޸İ�װ,��װ����Ϣ,��װ������Ϣʱ����"+he.getMessage());
			
		}finally{
			m_dao.closeSession(session);
		} 
	}
}