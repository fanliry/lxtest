package com.wms3.bms.standard.dao.base.impl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseLotDetailDao;
import com.wms3.bms.standard.entity.base.BaseLotDetail;

/**
 * 
 * ����: ����������ϸ��ϢDAO��
 * @author fangjie 
 * @since WMS 3.0
 */
public class BaseLotDetailDaoImpl implements IBaseLotDetailDao {
	
	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;

	public BaseLotDetailDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * ����:��������ID���������ϸ��Ϣ�б�
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public List getListByLotId(String lotid) throws Exception {
		
		List ls = null;
		
		if(lotid!=null && !lotid.equals("")){
			
			try{
				String strHql = "From BaseLotDetail as t where t.m_strLotid='" + lotid + "'";
				ls = m_dao.searchEntities(strHql);
				
			}catch(Exception er){
				
				throw new Exception("��������ID���������ϸ��Ϣ�б����:" + er.getMessage());
			}
		}
		
		return ls;
	}
	/**
	 * ����:��������ID���������ϸ��Ϣ�б�
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public List getDetailListByLotId(String lotid) throws Exception {
		
		List ls = null;
		
		if(lotid!=null && !lotid.equals("")){
			
			try{
				String strHql = "From BaseLotDetail as t where t.m_strLotid='" + lotid + "' and t.m_strLotatt_flag!='2'";
				ls = m_dao.searchEntities(strHql);
				
			}catch(Exception er){
				
				throw new Exception("��������ID���������ϸ��Ϣ�б����:" + er.getMessage());
			}
		}
		
		return ls;
	}
	/**
	 * ����:����������ϸID���������ϸ
	 * @param id	������ϸID
	 * @return 
	 * @throws Exception
	 */
	public BaseLotDetail getLotDetailByDetailId(String lotdetailid) throws Exception {
		
		String strSql = " from BaseLotDetail as t where t.m_strLotdetailid='" + lotdetailid + "'";
		List ls = m_dao.searchEntities(strSql);
		if(ls != null && ls.size() > 0){
			return (BaseLotDetail)ls.get(0);
		}
		return null;
	}	
	/**
	 * ����:���������ϸ��Ϣ�б�
	 * @return 
	 * @throws Exception
	 */
	public List getLotList() throws Exception {
		
		List ls = null;
		
		try{
			String strHql = "From BaseLot";
			ls = m_dao.searchEntities(strHql);
				
		}catch(Exception er){
				
			throw new Exception("��������ID���������ϸ��Ϣ�б����:" + er.getMessage());
		}
		return ls;
	}
}