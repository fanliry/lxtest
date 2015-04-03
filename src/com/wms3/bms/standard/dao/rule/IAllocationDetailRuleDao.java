package com.wms3.bms.standard.dao.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RuleAllocationDetail;

/**
 * 
 * 描述: 分配规则详细表操作dao类接口
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public interface IAllocationDetailRuleDao {

	/**
	 * 功能:根据分配规则ID查询分配规则详细信息
	 * @param allocationid 	分配规则ID
	 * @return 
	 * @throws Exception
	 */
	List getAllocationDetailRuleById(String allocationid) throws Exception;

    /**
     * 功能：获得指定规则的优先顺位
     * @param ruleid 规则id
     * @return
     * @throws Exception 
     */
	int getRuleNextSort(String ruleid) throws Exception;

	/**
	 * 功能:增加分配规则详细
	 * @param allocationDetailRule	分配规则详细
	 * @throws Exception
	 */
	void addAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception;

	/**
	 * 功能:根据分配规则详细ID获得分配规则详细
	 * @param allocationdetailid	分配规则详细ID
	 * @return 
	 * @throws Exception
	 */
	RuleAllocationDetail getPutDetailByDetailId(String allocationdetailid) throws Exception;
	
	/**
	 * 功能:修改分配规则详细
	 * @param allocationDetailRule	分配规则详细
	 * @throws Exception
	 */
	void updateAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception;
	
	/**
	 * 功能:删除分配规则详细
	 * @param allocationdetailid	分配规则详细ID
	 * @throws Exception
	 */
	void deleteAllocationDetailRule(String allocationdetailid) throws Exception;

	/**
	 * 功能:修改分配规则详细的优先顺位
	 * @param detailids	分配规则详细ID
	 * @param sorts		优先顺位
	 * @throws Exception
	 */
	void updateAllocationDetailRuleSorts(String detailids, String sorts) throws Exception;

}
