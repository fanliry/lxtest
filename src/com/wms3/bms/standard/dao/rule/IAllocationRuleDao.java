package com.wms3.bms.standard.dao.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RuleAllocation;

/**
 * 
 * 描述: 分配规则操作dao类接口
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public interface IAllocationRuleDao {

	/**
	 * 功能:根据条件查询分配规则
	 * @param warehouseid 	所属仓库ID
	 * @param descr			分配规则描述
	 * @return 
	 * @throws Exception
	 */
	public List getAllocationRuleQuery(String warehouseid, String descr) throws Exception;

	/**
	 * 功能:增加分配规则
	 * @param allocationRule	分配规则
	 * @throws Exception
	 */
	public void addAllocationRule(RuleAllocation allocationRule) throws Exception;

	/**
	 * 功能:根据分配规则ID获得分配规则
	 * @param allocationid	分配规则ID
	 * @return 
	 * @throws Exception
	 */
	public RuleAllocation getAllocationRuleById(String allocationid) throws Exception;

	/**
	 * 功能:修改分配规则
	 * @param allocationRule	分配规则
	 * @throws Exception
	 */
	public void updateAllocationRule(RuleAllocation allocationRule) throws Exception;

	/**
	 * 功能:删除分配规则
	 * @param id	分配规则id
	 * @throws Exception
	 */
	public void deleteAllocationRule(String id) throws Exception;

}
