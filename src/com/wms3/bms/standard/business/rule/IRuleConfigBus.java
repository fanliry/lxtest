package com.wms3.bms.standard.business.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RuleConfig;

/**
 * 
 * ����: �������ñ�ҵ��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IRuleConfigBus {
	
	/**
	 * ����:����������ѯ�������õ��б�
	 * @param flag		�������� 1���ϼܣ�2�����䣻3������
	 * @return 
	 * @throws Exception
	 */
	public List getRuleConfigByRuletype(String flag) throws Exception;
	
	/**
	 * ����:����id��ѯ�������õ��б�
	 * @param id
	 * @return 
	 * @throws Exception
	 */
	public RuleConfig getRuleConfigById(String id) throws Exception;
	
}