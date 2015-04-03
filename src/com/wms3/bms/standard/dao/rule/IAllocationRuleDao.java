package com.wms3.bms.standard.dao.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RuleAllocation;

/**
 * 
 * ����: ����������dao��ӿ�
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public interface IAllocationRuleDao {

	/**
	 * ����:����������ѯ�������
	 * @param warehouseid 	�����ֿ�ID
	 * @param descr			�����������
	 * @return 
	 * @throws Exception
	 */
	public List getAllocationRuleQuery(String warehouseid, String descr) throws Exception;

	/**
	 * ����:���ӷ������
	 * @param allocationRule	�������
	 * @throws Exception
	 */
	public void addAllocationRule(RuleAllocation allocationRule) throws Exception;

	/**
	 * ����:���ݷ������ID��÷������
	 * @param allocationid	�������ID
	 * @return 
	 * @throws Exception
	 */
	public RuleAllocation getAllocationRuleById(String allocationid) throws Exception;

	/**
	 * ����:�޸ķ������
	 * @param allocationRule	�������
	 * @throws Exception
	 */
	public void updateAllocationRule(RuleAllocation allocationRule) throws Exception;

	/**
	 * ����:ɾ���������
	 * @param id	�������id
	 * @throws Exception
	 */
	public void deleteAllocationRule(String id) throws Exception;

}
