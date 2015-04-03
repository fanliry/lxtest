package com.wms3.bms.standard.dao.rule.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.rule.IReplenishRuleDao;
import com.wms3.bms.standard.entity.rule.RuleReplenish;

/**
 * 
 * 描述: 补货规则数据库操作dao类
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public class ReplenishRuleDaoImpl implements IReplenishRuleDao{

    /** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;
    public ReplenishRuleDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    public EntityDAO getEntityDAO() {
        return m_dao;
    }

    /**
	 * 功能:根据仓库查询补货规则
	 * @param warehouseid 	所属仓库ID
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
			throw new Exception("根据仓库查询补货规则出错:" + er.getMessage());
		}
				
		return ls;
	}

	/**
	 * 功能:增加补货规则
	 * @param replenishRule	补货规则
	 * @throws Exception
	 */
	public void addReplenishRule(RuleReplenish replenishRule) throws Exception {
		
		m_dao.save(replenishRule);
	}
	
	/**
	 * 功能:根据补货规则ID获得补货规则
	 * @param replenishid	补货规则ID
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
	 * 功能:修改补货规则
	 * @param replenishRule	补货规则
	 * @throws Exception
	 */
	public void updateReplenishRule(RuleReplenish replenishRule) throws Exception {
		
		m_dao.update(replenishRule);
	}

	/**
	 * 功能:删除补货规则
	 * @param id	补货规则id
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
