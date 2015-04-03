package com.wms3.bms.standard.dao.rule.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.rule.IReplenishRuleDao;
import com.wms3.bms.standard.entity.rule.RuleReplenish;

/**
 * 
 * ����: �����������ݿ����dao��
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public class ReplenishRuleDaoImpl implements IReplenishRuleDao{

    /** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    public ReplenishRuleDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    public EntityDAO getEntityDAO() {
        return m_dao;
    }

    /**
	 * ����:���ݲֿ��ѯ��������
	 * @param warehouseid 	�����ֿ�ID
	 * @return 
	 * @throws Exception
	 */
	public List getReplenishRulesBywhid(String warehouseid) throws Exception {
		
		List ls = null;
		
		try {
			String sql = "From RuleReplenish as t where 1=1" ;
			
			if(warehouseid != null && warehouseid.trim().length() > 0){
				sql += " and t.warehouseid = '" + warehouseid + "'";
			}
			
			sql = sql + " order by t.replenishid";
			
			ls = m_dao.searchEntities(sql);
			
		} catch (Exception er) {
			throw new Exception("���ݲֿ��ѯ�����������:" + er.getMessage());
		}
				
		return ls;
	}

	/**
	 * ����:���Ӳ�������
	 * @param replenishRule	��������
	 * @throws Exception
	 */
	public void addReplenishRule(RuleReplenish replenishRule) throws Exception {
		
		m_dao.save(replenishRule);
	}
	
	/**
	 * ����:���ݲ�������ID��ò�������
	 * @param replenishid	��������ID
	 * @return 
	 * @throws Exception
	 */
	public RuleReplenish getReplenishRuleById(String replenishid) throws Exception {
		
		if(replenishid!=null && !replenishid.equals(""))
		{
			String strSql = " from RuleReplenish as t where t.replenishid='" + replenishid + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (RuleReplenish)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * ����:�޸Ĳ�������
	 * @param replenishRule	��������
	 * @throws Exception
	 */
	public void updateReplenishRule(RuleReplenish replenishRule) throws Exception {
		
		m_dao.update(replenishRule);
	}

	/**
	 * ����:ɾ����������
	 * @param id	��������id
	 * @throws Exception
	 */
	public void deleteReplenishRule(String id) throws Exception {
		
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete RuleReplenish as t where t.replenishid='" + id + "'";
			m_dao.deleteSql(strSql);
		}	
		
	}

}
