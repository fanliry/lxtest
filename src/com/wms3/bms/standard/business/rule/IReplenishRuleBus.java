package com.wms3.bms.standard.business.rule;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.rule.RuleReplenish;

/**
 * 
 * 描述: 补货规则业务接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IReplenishRuleBus {
	
	/**
	 * 功能:根据条件查询补货规则
	 * @param warehouseid 	所属仓库ID
	 * @param descr			补货规则描述
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getReplenishRuleQuery(String warehouseid, String descr, String strUrl, int maxLine) throws Exception;

	/**
	 * 功能:增加补货规则
	 * @param replenishRule	补货规则
	 * @throws Exception
	 */
	public void addReplenishRule(RuleReplenish replenishRule) throws Exception;

	/**
	 * 功能:根据补货规则ID获得补货规则
	 * @param replenishid	补货规则ID
	 * @return 
	 * @throws Exception
	 */
	public RuleReplenish getReplenishRuleById(String replenishid) throws Exception;

	/**
	 * 功能:修改补货规则
	 * @param replenishRule	补货规则
	 * @throws Exception
	 */
	public void updateReplenishRule(RuleReplenish replenishRule) throws Exception;

	/**
	 * 功能:删除补货规则
	 * @param id	补货规则id
	 * @throws Exception
	 */
	public void deleteReplenishRule(String id) throws Exception;
	
	/**
	 * 功能:根据仓库查询补货规则
	 * @param warehouseid 	所属仓库ID
	 * @return 
	 * @throws Exception
	 */
	public List getReplenishRulesBywhid(String warehouseid) throws Exception;
	
}