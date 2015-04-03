package com.wms3.bms.standard.dao.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RulePutaway;

/**
 * 
 * 描述: 上架规则操作dao类接口
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public interface IPutawayRuleDao {

	/**
	 * 功能:根据条件查询上架规则
	 * @param warehouseid 	所属仓库ID
	 * @param descr			上架规则描述
	 * @return 
	 * @throws Exception
	 */
	public List getPutawayRuleQuery(String warehouseid, String descr) throws Exception;

	/**
	 * 功能:增加上架规则
	 * @param putawayRule	上架规则
	 * @throws Exception
	 */
	public void addPutawayRule(RulePutaway putawayRule) throws Exception;

	/**
	 * 功能:根据上架规则ID获得上架规则
	 * @param putawayid	上架规则ID
	 * @return 
	 * @throws Exception
	 */
	public RulePutaway getPutawayRuleById(String putawayid) throws Exception;

	/**
	 * 功能:修改上架规则
	 * @param putawayRule	上架规则
	 * @throws Exception
	 */
	public void updatePutawayRule(RulePutaway putawayRule) throws Exception;

	/**
	 * 功能:删除上架规则
	 * @param id	上架规则id
	 * @throws Exception
	 */
	public void deletePutawayRule(String id) throws Exception;

}
