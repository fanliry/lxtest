package com.wms3.bms.standard.dao.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RuleReplenish;

/**
 * 
 * ����: �����������ݿ����dao��ӿ�
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public interface IReplenishRuleDao {

	/**
	 * ����:���ݲֿ��ѯ��������
	 * @param warehouseid 	�����ֿ�ID
	 * @return 
	 * @throws Exception
	 */
	public List getReplenishRulesBywhid(String warehouseid) throws Exception;

	/**
	 * ����:���Ӳ�������
	 * @param replenishRule	��������
	 * @throws Exception
	 */
	public void addReplenishRule(RuleReplenish replenishRule) throws Exception;

	/**
	 * ����:���ݲ�������ID��ò�������
	 * @param replenishid	��������ID
	 * @return 
	 * @throws Exception
	 */
	public RuleReplenish getReplenishRuleById(String replenishid) throws Exception;

	/**
	 * ����:�޸Ĳ�������
	 * @param replenishRule	��������
	 * @throws Exception
	 */
	public void updateReplenishRule(RuleReplenish replenishRule) throws Exception;

	/**
	 * ����:ɾ����������
	 * @param id	��������id
	 * @throws Exception
	 */
	public void deleteReplenishRule(String id) throws Exception;

}
