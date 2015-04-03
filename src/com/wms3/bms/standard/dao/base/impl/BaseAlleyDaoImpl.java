package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseAlleyDao;
import com.wms3.bms.standard.entity.base.BaseAlley;

/**
 * ����: ���DAO��
 * @author fangjie 
 * 
 */
public class BaseAlleyDaoImpl implements IBaseAlleyDao {

	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    
	public BaseAlleyDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * ����:����������ѯ���
	 * @param warehouseid		�ֿ�ID
	 * @param cargoAlleyId		���ID	
	 * @param whAreaId 			����ID
	 * @return
	 * @throws Exception
	 */
	public List getAlleyQuery(String warehouseid, String cargoAlleyId, String whAreaId) throws Exception
	{
		List ls = null;
		try
		{
			String strHql = "From BaseAlley as t where 1=1" ;
			
			if(warehouseid != null && warehouseid.trim().length() > 0){
				strHql += " and t.warehouseid = '" + warehouseid + "'";
			}
			if(cargoAlleyId != null && cargoAlleyId.trim().length() > 0){
				strHql += " and t.cargoAlleyId = '" + cargoAlleyId + "'";
			}
			if(whAreaId != null && whAreaId.trim().length() > 0){
				strHql += " and t.whAreaId = '" + whAreaId + "'";
			}
			strHql = strHql + " order by t.cargoAlleyId";
			ls = m_dao.searchEntities(strHql);		
				
		}catch(Exception er)
		{
			throw new Exception("����������ѯ�������:" + er.getMessage());
		}
		return ls;
	}
	
	/**
	 * ����:�������ID������
	 * @param cargoAlleyId	���ID
	 * @return
	 * @throws Exception
	 */
	public BaseAlley getAlleyById(String cargoAlleyId) throws Exception
	{
		if(cargoAlleyId != null)
		{
			String strSql = " from BaseAlley as t where t.cargoAlleyId='" + cargoAlleyId + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseAlley)ls.get(0);
			}
		}
		return null;
	}

	/**
	 * ����:���ͬ����������һ���������
	 * @param whAreaId  
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public String getMaxAlleyId(String whAreaId) throws Exception
	{
		String maxAlleyId = whAreaId + "000";
		try
		{
			String strSql = "from BaseAlley as t where t.whAreaId='" + whAreaId + "' order by t.cargoAlleyId desc";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && !ls.isEmpty())
			{
				BaseAlley alley = (BaseAlley)ls.get(0);
				maxAlleyId = alley.getCargoAlleyId();
			}
		}catch(Exception er)
		{
			throw new Exception("�������һ���������ʧ��:"+er.getMessage());
		}
		return maxAlleyId;
	}

	/**
	 * ����:�������
	 * @param alley	���
	 * @throws Exception
	 */
	public void addAlley(BaseAlley alley) throws Exception{
		
		m_dao.save(alley);
	}

	/**
	 * ����:��ȡ��������б�
	 * @return
	 * @throws Exception
	 */
	public List getAlleyList() throws Exception
	{
		List ls = null;
		try
		{
			String strHql = "From BaseAlley as t where 1=1 order by t.cargoAlleyId";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("��ѯ����б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:�޸����
	 * @param alley	���
	 * @throws Exception
	 */
	public void updateAlley(BaseAlley alley) throws Exception{
		
		m_dao.update(alley);
	}

	/**
	 * ����:ɾ�����
	 * @param id	���ID
	 * @throws Exception
	 */
	public void deleteAlley(String id) throws Exception
	{
		if(id != null)
		{
			String strSql  = " delete BaseAlley as t where t.cargoAlleyId='" + id + "'";

			m_dao.deleteSql(strSql);
		}	
	}
	
	/**
	 * ����:�жϿ������Ƿ������
	 * @param id	����ID
	 * @throws Exception
	 */
	public boolean isWhAreaHasChildNode(String id) throws Exception {
		
		if(id != null && !id.equals(""))
		{
			String strSql = " from BaseAlley as t where t.whAreaId='" + id+"'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return true;
			}
		}
		return false;
	}
}
