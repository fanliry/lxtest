package com.wms3.bms.standard.dao.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RuleConfig;

/**
 * 
 * ����: �������ñ����dao��ӿ�
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public interface IRuleConfigDao {

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
