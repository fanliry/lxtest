package com.wms3.bms.standard.dao.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RuleReplenish;

/**
 * 
 * 描述: 补货规则数据库操作dao类接口
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public interface IReplenishRuleDao {

	/**
	 * 功能:根据仓库查询补货规则
	 * @param warehouseid 	所属仓库ID
	 * @return 
	 * @throws Exception
	 */
	public List getReplenishRulesBywhid(String warehouseid) throws Exception;

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

}
