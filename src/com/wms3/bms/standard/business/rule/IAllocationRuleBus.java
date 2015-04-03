package com.wms3.bms.standard.business.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RuleAllocation;
import com.wms3.bms.standard.entity.rule.RuleAllocationDetail;

/**
 * 
 * ����: �������ҵ��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IAllocationRuleBus {
	
	/**
	 * ����:����������ѯ�������
	 * @param warehouseid 	�����ֿ�ID
	 * @param descr			�����������
	 * @return 
	 * @throws Exception
	 */
	public List getAllocationRuleQuery(String warehouseid, String descr) throws Exception;

	/**
	 * ����:���ݷ������ID��ѯ���������ϸ��Ϣ
	 * @param allocationid 	�������ID
	 * @return 
	 * @throws Exception
	 */
	public List getAllocationDetailRuleById(String allocationid) throws Exception;
	
	/**
	 * ����:���ӷ������
	 * @param allocationRule	�������
	 * @throws Exception
	 */
	public void addAllocationRule(RuleAllocation allocationRule) throws Exception;

	/**
	 * ����:���ݷ������ID��÷������
	 * @param allocationid		�������ID
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
	 * @param id			�������id
	 * @throws Exception
	 */
	public void deleteAllocationRule(String id) throws Exception;

    /**
     * ���ܣ����ָ�����������˳λ
     * @param ruleid ����id
     * @return
     * @throws Exception 
     */
	public int getRuleNextSort(String ruleid) throws Exception;

	/**
	 * ����:���ӷ��������ϸ
	 * @param allocationDetailRule	���������ϸ
	 * @throws Exception
	 */
	public void addAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception;

	/**
	 * ����:���ݷ��������ϸID��÷��������ϸ
	 * @param allocationdetailid	���������ϸID
	 * @return 
	 * @throws Exception
	 */
	public RuleAllocationDetail getPutDetailByDetailId(String allocationdetailid) throws Exception;

	/**
	 * ����:�޸ķ��������ϸ
	 * @param allocationDetailRule	���������ϸ
	 * @throws Exception
	 */
	public void updateAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception;

	/**
	 * ����:ɾ�����������ϸ
	 * @param allocationdetailid	���������ϸID
	 * @throws Exception
	 */
	public void deleteAllocationDetailRule(String allocationdetailid) throws Exception;

	/**
	 * ����:�޸ķ��������ϸ������˳λ
	 * @param detailids	���������ϸID
	 * @param sorts		����˳λ
	 * @throws Exception
	 */
	public void updateAllocationDetailRuleSorts(String detailids, String sorts) throws Exception;
	
}