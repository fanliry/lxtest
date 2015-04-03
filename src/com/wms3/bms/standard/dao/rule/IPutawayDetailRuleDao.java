package com.wms3.bms.standard.dao.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RulePutawayDetail;

/**
 * 
 * 描述: 上架规则详细表操作dao类接口
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public interface IPutawayDetailRuleDao {

	/**
	 * 功能:根据上架规则ID查询上架规则详细信息
	 * @param putawayid 	上架规则ID
	 * @return 
	 * @throws Exception
	 */
	List getPutawayDetailRuleById(String putawayid) throws Exception;

    /**
     * 功能：获得指定规则的优先顺位
     * @param ruleid 规则id
     * @return
     * @throws Exception 
     */
	int getRuleNextSort(String ruleid) throws Exception;

	/**
	 * 功能:增加上架规则详细
	 * @param putawayDetailRule	上架规则详细
	 * @throws Exception
	 */
	void addPutawayDetailRule(RulePutawayDetail putawayDetailRule) throws Exception;

	/**
	 * 功能:根据上架规则详细ID获得上架规则详细
	 * @param putawaydetailid	上架规则详细ID
	 * @return 
	 * @throws Exception
	 */
	RulePutawayDetail getPutDetailByDetailId(String putawaydetailid) throws Exception;
	
	/**
	 * 功能:修改上架规则详细
	 * @param putawayDetailRule	上架规则详细
	 * @param flg 
	 * @throws Exception
	 */
	void updatePutawayDetailRule(RulePutawayDetail putawayDetailRule) throws Exception;
	
	/**
	 * 功能:删除上架规则详细
	 * @param putawaydetailid	上架规则详细ID
	 * @throws Exception
	 */
	void deletePutawayDetailRule(String putawaydetailid) throws Exception;

	/**
	 * 功能:修改上架规则详细的优先顺位
	 * @param detailids	上架规则详细ID
	 * @param sorts		优先顺位
	 * @throws Exception
	 */
	void updatePutawayDetailRuleSorts(String detailids, String sorts) throws Exception;

}
