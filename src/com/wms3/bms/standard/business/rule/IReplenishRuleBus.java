package com.wms3.bms.standard.business.rule;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.rule.RuleReplenish;

/**
 * 
 * ����: ��������ҵ��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IReplenishRuleBus {
	
	/**
	 * ����:����������ѯ��������
	 * @param warehouseid 	�����ֿ�ID
	 * @param descr			������������
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getReplenishRuleQuery(String warehouseid, String descr, String strUrl, int maxLine) throws Exception;

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
	
	/**
	 * ����:���ݲֿ��ѯ��������
	 * @param warehouseid 	�����ֿ�ID
	 * @return 
	 * @throws Exception
	 */
	public List getReplenishRulesBywhid(String warehouseid) throws Exception;
	
}