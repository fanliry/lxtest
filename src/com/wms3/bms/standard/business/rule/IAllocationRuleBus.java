package com.wms3.bms.standard.business.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RuleAllocation;
import com.wms3.bms.standard.entity.rule.RuleAllocationDetail;

/**
 * 
 * 描述: 分配规则业务接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IAllocationRuleBus {
	
	/**
	 * 功能:根据条件查询分配规则
	 * @param warehouseid 	所属仓库ID
	 * @param descr			分配规则描述
	 * @return 
	 * @throws Exception
	 */
	public List getAllocationRuleQuery(String warehouseid, String descr) throws Exception;

	/**
	 * 功能:根据分配规则ID查询分配规则详细信息
	 * @param allocationid 	分配规则ID
	 * @return 
	 * @throws Exception
	 */
	public List getAllocationDetailRuleById(String allocationid) throws Exception;
	
	/**
	 * 功能:增加分配规则
	 * @param allocationRule	分配规则
	 * @throws Exception
	 */
	public void addAllocationRule(RuleAllocation allocationRule) throws Exception;

	/**
	 * 功能:根据分配规则ID获得分配规则
	 * @param allocationid		分配规则ID
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
	 * @param id			分配规则id
	 * @throws Exception
	 */
	public void deleteAllocationRule(String id) throws Exception;

    /**
     * 功能：获得指定规则的优先顺位
     * @param ruleid 规则id
     * @return
     * @throws Exception 
     */
	public int getRuleNextSort(String ruleid) throws Exception;

	/**
	 * 功能:增加分配规则详细
	 * @param allocationDetailRule	分配规则详细
	 * @throws Exception
	 */
	public void addAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception;

	/**
	 * 功能:根据分配规则详细ID获得分配规则详细
	 * @param allocationdetailid	分配规则详细ID
	 * @return 
	 * @throws Exception
	 */
	public RuleAllocationDetail getPutDetailByDetailId(String allocationdetailid) throws Exception;

	/**
	 * 功能:修改分配规则详细
	 * @param allocationDetailRule	分配规则详细
	 * @throws Exception
	 */
	public void updateAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception;

	/**
	 * 功能:删除分配规则详细
	 * @param allocationdetailid	分配规则详细ID
	 * @throws Exception
	 */
	public void deleteAllocationDetailRule(String allocationdetailid) throws Exception;

	/**
	 * 功能:修改分配规则详细的优先顺位
	 * @param detailids	分配规则详细ID
	 * @param sorts		优先顺位
	 * @throws Exception
	 */
	public void updateAllocationDetailRuleSorts(String detailids, String sorts) throws Exception;
	
}