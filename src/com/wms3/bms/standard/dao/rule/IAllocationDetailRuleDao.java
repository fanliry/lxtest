package com.wms3.bms.standard.dao.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RuleAllocationDetail;

/**
 * 
 * ����: ���������ϸ�����dao��ӿ�
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public interface IAllocationDetailRuleDao {

	/**
	 * ����:���ݷ������ID��ѯ���������ϸ��Ϣ
	 * @param allocationid 	�������ID
	 * @return 
	 * @throws Exception
	 */
	List getAllocationDetailRuleById(String allocationid) throws Exception;

    /**
     * ���ܣ����ָ�����������˳λ
     * @param ruleid ����id
     * @return
     * @throws Exception 
     */
	int getRuleNextSort(String ruleid) throws Exception;

	/**
	 * ����:���ӷ��������ϸ
	 * @param allocationDetailRule	���������ϸ
	 * @throws Exception
	 */
	void addAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception;

	/**
	 * ����:���ݷ��������ϸID��÷��������ϸ
	 * @param allocationdetailid	���������ϸID
	 * @return 
	 * @throws Exception
	 */
	RuleAllocationDetail getPutDetailByDetailId(String allocationdetailid) throws Exception;
	
	/**
	 * ����:�޸ķ��������ϸ
	 * @param allocationDetailRule	���������ϸ
	 * @throws Exception
	 */
	void updateAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception;
	
	/**
	 * ����:ɾ�����������ϸ
	 * @param allocationdetailid	���������ϸID
	 * @throws Exception
	 */
	void deleteAllocationDetailRule(String allocationdetailid) throws Exception;

	/**
	 * ����:�޸ķ��������ϸ������˳λ
	 * @param detailids	���������ϸID
	 * @param sorts		����˳λ
	 * @throws Exception
	 */
	void updateAllocationDetailRuleSorts(String detailids, String sorts) throws Exception;

}
