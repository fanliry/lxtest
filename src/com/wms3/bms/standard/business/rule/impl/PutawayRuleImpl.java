package com.wms3.bms.standard.business.rule.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.rule.IPutawayRuleBus;
import com.wms3.bms.standard.dao.rule.IPutawayDetailRuleDao;
import com.wms3.bms.standard.dao.rule.IPutawayRuleDao;
import com.wms3.bms.standard.dao.rule.impl.PutawayDetailRuleDaoImpl;
import com.wms3.bms.standard.dao.rule.impl.PutawayRuleDaoImpl;
import com.wms3.bms.standard.entity.rule.RulePutaway;
import com.wms3.bms.standard.entity.rule.RulePutawayDetail;

/**
 * 
 * 描述: 上架规则业务类
 * @author fangjie
 * @since WMS 3.0
 */
public class PutawayRuleImpl implements IPutawayRuleBus {
	
	protected IPutawayRuleDao putawayRuleDAO = null;
	protected IPutawayDetailRuleDao putawayDetailRuleDAO = null;

	/**
	 * @param dao
	 */
	public PutawayRuleImpl(EntityDAO dao) {
		this.putawayRuleDAO = new PutawayRuleDaoImpl(dao);
		this.putawayDetailRuleDAO = new PutawayDetailRuleDaoImpl(dao);
	}


	/**
	 * 功能:根据条件查询上架规则
	 * @param warehouseid 	所属仓库ID
	 * @param descr			上架规则描述
	 * @return 
	 * @throws Exception
	 */
	public List getPutawayRuleQuery(String warehouseid, String descr) throws Exception {
		
		return putawayRuleDAO.getPutawayRuleQuery(warehouseid, descr);
	}

	/**
	 * 功能:根据上架规则ID查询上架规则详细信息
	 * @param putawayid 	上架规则ID
	 * @return 
	 * @throws Exception
	 */
	public List getPutawayDetailRuleById(String putawayid) throws Exception {
		
		return putawayDetailRuleDAO.getPutawayDetailRuleById(putawayid);
	}
	
	/**
	 * 功能:增加上架规则
	 * @param putawayRule	上架规则
	 * @throws Exception
	 */
	public void addPutawayRule(RulePutaway putawayRule) throws Exception {
		
		putawayRuleDAO.addPutawayRule(putawayRule);
	}

	/**
	 * 功能:根据上架规则ID获得上架规则
	 * @param putawayid	上架规则ID
	 * @return 
	 * @throws Exception
	 */
	public RulePutaway getPutawayRuleById(String putawayid) throws Exception {
		
		return putawayRuleDAO.getPutawayRuleById(putawayid);
	}

	/**
	 * 功能:修改上架规则
	 * @param putawayRule	上架规则
	 * @throws Exception
	 */
	public void updatePutawayRule(RulePutaway putawayRule) throws Exception {

		putawayRuleDAO.updatePutawayRule(putawayRule);
	}

	/**
	 * 功能:删除上架规则
	 * @param id	上架规则id
	 * @throws Exception
	 */
	public void deletePutawayRule(String id) throws Exception {
		
		putawayRuleDAO.deletePutawayRule(id);
	}

    /**
     * 功能：获得指定规则的优先顺位
     * @param ruleid 规则id
     * @return
     * @throws Exception 
     */
	public int getRuleNextSort(String ruleid) throws Exception {
		
		return putawayDetailRuleDAO.getRuleNextSort(ruleid);
	}

	/**
	 * 功能:增加上架规则详细
	 * @param putawayDetailRule	上架规则详细
	 * @throws Exception
	 */
	public void addPutawayDetailRule(RulePutawayDetail putawayDetailRule) throws Exception {

		putawayDetailRuleDAO.addPutawayDetailRule(putawayDetailRule);
	}

	/**
	 * 功能:根据上架规则详细ID获得上架规则详细
	 * @param putawaydetailid	上架规则详细ID
	 * @return 
	 * @throws Exception
	 */
	public RulePutawayDetail getPutDetailByDetailId(String putawaydetailid) throws Exception {
		
		return putawayDetailRuleDAO.getPutDetailByDetailId(putawaydetailid);
	}

	/**
	 * 功能:修改上架规则详细
	 * @param putawayDetailRule	上架规则详细
	 * @param flg
	 * @throws Exception
	 */
	public void updatePutawayDetailRule(RulePutawayDetail putawayDetailRule) throws Exception {
		
		putawayDetailRuleDAO.updatePutawayDetailRule(putawayDetailRule);
	}

	/**
	 * 功能:删除上架规则详细
	 * @param putawaydetailid	上架规则详细ID
	 * @throws Exception
	 */
	public void deletePutawayDetailRule(String putawaydetailid) throws Exception {
		
		putawayDetailRuleDAO.deletePutawayDetailRule(putawaydetailid);
	}

	/**
	 * 功能:修改上架规则详细的优先顺位
	 * @param detailids	上架规则详细ID
	 * @param sorts		优先顺位
	 * @throws Exception
	 */
	public void updatePutawayDetailRuleSorts(String detailids, String sorts) throws Exception {
		
		putawayDetailRuleDAO.updatePutawayDetailRuleSorts(detailids, sorts);
	}

	
}