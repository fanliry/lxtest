package com.wms3.bms.standard.business.rule.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.rule.IRuleConfigBus;
import com.wms3.bms.standard.dao.rule.IRuleConfigDao;
import com.wms3.bms.standard.dao.rule.impl.RuleConfigDaoImpl;
import com.wms3.bms.standard.entity.rule.RuleConfig;

/**
 * 
 * ����: �������ñ�ҵ����
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
	 * ����:����������ѯ�������õ��б�
	 * @param flag		�������� 1���ϼܣ�2�����䣻3������
	 * @return 
	 * @throws Exception
	 */
	public List getRuleConfigByRuletype(String flag) throws Exception {
		
		return ruleConfigDAO.getRuleConfigByRuletype(flag);
	}

	/**
	 * ����:����id��ѯ�������õ��б�
	 * @param id
	 * @return 
	 * @throws Exception
	 */
	public RuleConfig getRuleConfigById(String id) throws Exception {
		
		return ruleConfigDAO.getRuleConfigById(id);
	}
	
}