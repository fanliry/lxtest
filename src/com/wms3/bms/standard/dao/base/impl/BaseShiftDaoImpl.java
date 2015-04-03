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
 * ����: ���DAO��
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseShiftDaoImpl implements IBaseShiftDao {
	
	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;

	public BaseShiftDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * ����:���ݰ��ID��ð��
	 * @param id	���ID
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
	 * ����:���Ӱ��
	 * @param shift	���
	 * @param lastshift ����Σ������������ͬ��
	 * @throws Exception
	 */
	public void addShift(BaseShift shift, BaseShift lastshift) throws Exception 
	{
		Session session = m_dao.getSession();
		Transaction tx = null;
		try
		{
			//�������������
			tx= session.beginTransaction();
			if(lastshift != null)
			{	
				//������һ������ε�����־Ϊ��
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
			//����ع�
			if(tx != null){
				tx.rollback();
			}
			Logger.error("�޸���һ������ε�����־������°��ʱ����"+he.getMessage());
			throw new Exception("�޸���һ������ε�����־������°��ʱ����"+he.getMessage());
		}
		finally
		{
			m_dao.closeSession(session);
		} 	
	}

	/**
	 * ����:��ȡ���а���б�
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
			throw new Exception("��ѯ����б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:�޸İ��
	 * @param shift	���
	 * @throws Exception
	 */
	public void updateShift(BaseShift shift) throws Exception 
	{
		m_dao.update(shift);
	}

	/**
	 * ����:ɾ�����
	 * @param id	���ID
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
	 * ����:������һ�����
	 * @param id	���ID
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
				throw new Exception("������һ�����ʱ�����������������:" + strIn_Out_Type);
			}
		}
		return null;
	}

	/**
	 * ����:���ݰ������ѯ����Ƿ����
	 * @param shiftname	�����
	 * @param inouttype �������
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