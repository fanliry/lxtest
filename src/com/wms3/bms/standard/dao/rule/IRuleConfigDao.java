package com.wms3.bms.standard.dao.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RuleConfig;

/**
 * 
 * 描述: 规则配置表操作dao类接口
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public interface IRuleConfigDao {

	/**
	 * 功能:根据条件查询规则配置的列表
	 * @param flag		规则类型 1：上架；2：分配；3：补货
	 * @return 
	 * @throws Exception
	 */
	public List getRuleConfigByRuletype(String flag) throws Exception;

	/**
	 * 功能:根据id查询规则配置的列表
	 * @param id
	 * @return 
	 * @throws Exception
	 */
	public RuleConfig getRuleConfigById(String id) throws Exception;

}
