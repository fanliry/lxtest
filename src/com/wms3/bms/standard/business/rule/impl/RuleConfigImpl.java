package com.wms3.bms.standard.business.rule.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.rule.IRuleConfigBus;
import com.wms3.bms.standard.dao.rule.IRuleConfigDao;
import com.wms3.bms.standard.dao.rule.impl.RuleConfigDaoImpl;
import com.wms3.bms.standard.entity.rule.RuleConfig;

/**
 * 
 * 描述: 规则配置表业务类
 * @author fangjie
 * @since WMS 3.0
 */
public class RuleConfigImpl implements IRuleConfigBus {
	
	protected IRuleConfigDao ruleConfigDAO = null;

	/**
	 * @param dao
	 */
	public RuleConfigImpl(EntityDAO dao) {
		this.ruleConfigDAO = new RuleConfigDaoImpl(dao);
	}


	/**
	 * 功能:根据条件查询规则配置的列表
	 * @param flag		规则类型 1：上架；2：分配；3：补货
	 * @return 
	 * @throws Exception
	 */
	public List getRuleConfigByRuletype(String flag) throws Exception {
		
		return ruleConfigDAO.getRuleConfigByRuletype(flag);
	}

	/**
	 * 功能:根据id查询规则配置的列表
	 * @param id
	 * @return 
	 * @throws Exception
	 */
	public RuleConfig getRuleConfigById(String id) throws Exception {
		
		return ruleConfigDAO.getRuleConfigById(id);
	}
	
}