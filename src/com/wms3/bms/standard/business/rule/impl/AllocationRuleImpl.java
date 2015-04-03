package com.wms3.bms.standard.business.rule.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.rule.IAllocationRuleBus;
import com.wms3.bms.standard.dao.rule.IAllocationDetailRuleDao;
import com.wms3.bms.standard.dao.rule.IAllocationRuleDao;
import com.wms3.bms.standard.dao.rule.impl.AllocationDetailRuleDaoImpl;
import com.wms3.bms.standard.dao.rule.impl.AllocationRuleDaoImpl;
import com.wms3.bms.standard.entity.rule.RuleAllocation;
import com.wms3.bms.standard.entity.rule.RuleAllocationDetail;

/**
 * 
 * 描述: 分配规则业务类
 * @author fangjie
 * @since WMS 3.0
 */
public class AllocationRuleImpl implements IAllocationRuleBus {
	
	protected IAllocationRuleDao allocationRuleDAO = null;
	protected IAllocationDetailRuleDao allocationDetailRuleDAO = null;

	/**
	 * @param dao
	 */
	public AllocationRuleImpl(EntityDAO dao) {
		this.allocationRuleDAO = new AllocationRuleDaoImpl(dao);
		this.allocationDetailRuleDAO = new AllocationDetailRuleDaoImpl(dao);
	}


	/**
	 * 功能:根据条件查询分配规则
	 * @param warehouseid 	所属仓库ID
	 * @param descr			分配规则描述
	 * @return 
	 * @throws Exception
	 */
	public List getAllocationRuleQuery(String warehouseid, String descr) throws Exception {
		
		return allocationRuleDAO.getAllocationRuleQuery(warehouseid, descr);
	}

	/**
	 * 功能:根据分配规则ID查询分配规则详细信息
	 * @param allocationid 	分配规则ID
	 * @return 
	 * @throws Exception
	 */
	public List getAllocationDetailRuleById(String allocationid) throws Exception {
		
		return allocationDetailRuleDAO.getAllocationDetailRuleById(allocationid);
	}
	
	/**
	 * 功能:增加分配规则
	 * @param allocationRule	分配规则
	 * @throws Exception
	 */
	public void addAllocationRule(RuleAllocation allocationRule) throws Exception {
		
		allocationRuleDAO.addAllocationRule(allocationRule);
	}

	/**
	 * 功能:根据分配规则ID获得分配规则
	 * @param allocationid	分配规则ID
	 * @return 
	 * @throws Exception
	 */
	public RuleAllocation getAllocationRuleById(String allocationid) throws Exception {
		
		return allocationRuleDAO.getAllocationRuleById(allocationid);
	}

	/**
	 * 功能:修改分配规则
	 * @param allocationRule	分配规则
	 * @throws Exception
	 */
	public void updateAllocationRule(RuleAllocation allocationRule) throws Exception {

		allocationRuleDAO.updateAllocationRule(allocationRule);
	}

	/**
	 * 功能:删除分配规则
	 * @param id	分配规则id
	 * @throws Exception
	 */
	public void deleteAllocationRule(String id) throws Exception {
		
		allocationRuleDAO.deleteAllocationRule(id);
	}

    /**
     * 功能：获得指定规则的优先顺位
     * @param ruleid 规则id
     * @return
     * @throws Exception 
     */
	public int getRuleNextSort(String ruleid) throws Exception {
		
		return allocationDetailRuleDAO.getRuleNextSort(ruleid);
	}

	/**
	 * 功能:增加分配规则详细
	 * @param allocationDetailRule	分配规则详细
	 * @throws Exception
	 */
	public void addAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception {

		allocationDetailRuleDAO.addAllocationDetailRule(allocationDetailRule);
	}

	/**
	 * 功能:根据分配规则详细ID获得分配规则详细
	 * @param allocationdetailid	分配规则详细ID
	 * @return 
	 * @throws Exception
	 */
	public RuleAllocationDetail getPutDetailByDetailId(String allocationdetailid) throws Exception {
		
		return allocationDetailRuleDAO.getPutDetailByDetailId(allocationdetailid);
	}

	/**
	 * 功能:修改分配规则详细
	 * @param allocationDetailRule	分配规则详细
	 * @throws Exception
	 */
	public void updateAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception {
		
		allocationDetailRuleDAO.updateAllocationDetailRule(allocationDetailRule);
	}

	/**
	 * 功能:删除分配规则详细
	 * @param allocationdetailid	分配规则详细ID
	 * @throws Exception
	 */
	public void deleteAllocationDetailRule(String allocationdetailid) throws Exception {
		
		allocationDetailRuleDAO.deleteAllocationDetailRule(allocationdetailid);
	}

	/**
	 * 功能:修改分配规则详细的优先顺位
	 * @param detailids	分配规则详细ID
	 * @param sorts		优先顺位
	 * @throws Exception
	 */
	public void updateAllocationDetailRuleSorts(String detailids, String sorts) throws Exception {
		
		allocationDetailRuleDAO.updateAllocationDetailRuleSorts(detailids, sorts);
	}

	
}