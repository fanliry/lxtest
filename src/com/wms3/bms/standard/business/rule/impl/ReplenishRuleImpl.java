package com.wms3.bms.standard.business.rule.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.rule.IReplenishRuleBus;
import com.wms3.bms.standard.dao.rule.IReplenishRuleDao;
import com.wms3.bms.standard.dao.rule.impl.ReplenishRuleDaoImpl;
import com.wms3.bms.standard.entity.rule.RuleReplenish;

/**
 * 
 * 描述: 补货规则业务类
 * @author fangjie
 * @since WMS 3.0
 */
public class ReplenishRuleImpl implements IReplenishRuleBus {
	
	protected IReplenishRuleDao replenishRuleDAO = null;
	protected EntityDAO m_dao = null;

	/**
	 * @param dao
	 */
	public ReplenishRuleImpl(EntityDAO dao) {
		this.replenishRuleDAO = new ReplenishRuleDaoImpl(dao);
		this.m_dao = dao;
	}


	/**
	 * 功能:根据条件查询补货规则
	 * @param warehouseid 	所属仓库ID
	 * @param descr			补货规则描述
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getReplenishRuleQuery(String warehouseid, String descr, String strUrl, int maxLine) throws Exception {
		
		PagingTool pt = null;
		
		try {
			String sql = "From RuleReplenish as t where 1=1" ;
			
			if(warehouseid != null && warehouseid.trim().length() > 0){
				sql += " and t.warehouseid = '" + warehouseid + "'";
			}
			
			if(descr != null && descr.trim().length() > 0){
				sql += " and t.descr like '%" + descr + "%'";
			}
			
			String strHQL = sql + " order by t.replenishid";
			String strCountHQL = "select count(*)" + sql ;
			
			pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("根据条件查询补货规则出错:" + er.getMessage());
		}
				
		return pt;
	}

	/**
	 * 功能:增加补货规则
	 * @param replenishRule	补货规则
	 * @throws Exception
	 */
	public void addReplenishRule(RuleReplenish replenishRule) throws Exception {
		
		replenishRuleDAO.addReplenishRule(replenishRule);
	}

	/**
	 * 功能:根据补货规则ID获得补货规则
	 * @param replenishid	补货规则ID
	 * @return 
	 * @throws Exception
	 */
	public RuleReplenish getReplenishRuleById(String replenishid) throws Exception {
		
		return replenishRuleDAO.getReplenishRuleById(replenishid);
	}

	/**
	 * 功能:修改补货规则
	 * @param replenishRule	补货规则
	 * @throws Exception
	 */
	public void updateReplenishRule(RuleReplenish replenishRule) throws Exception {

		replenishRuleDAO.updateReplenishRule(replenishRule);
	}

	/**
	 * 功能:删除补货规则
	 * @param id	补货规则id
	 * @throws Exception
	 */
	public void deleteReplenishRule(String id) throws Exception {
		
		replenishRuleDAO.deleteReplenishRule(id);
	}

	/**
	 * 功能:根据仓库查询补货规则
	 * @param warehouseid 	所属仓库ID
	 * @return 
	 * @throws Exception
	 */
	public List getReplenishRulesBywhid(String warehouseid) throws Exception {
		
		return replenishRuleDAO.getReplenishRulesBywhid(warehouseid);
	}
	
}