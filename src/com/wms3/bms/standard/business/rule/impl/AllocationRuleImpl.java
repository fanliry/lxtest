package com.wms3.bms.standard.business.rule.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.rule.IAllocationRuleBus;
import com.wms3.bms.standard.dao.rule.IAllocationDetailRuleDao;
import com.wms3.bms.standard.dao.rule.IAllocationRuleDao;
import com.wms3.bms.standard.dao.rule.impl.AllocationDetailRuleDaoImpl;
import com.wms3.bms.standard.dao.rule.impl.AllocationRuleDaoImpl;
import com.wms3.bms.standard.entity.rule.RuleAllocation;
import com.wms3.bms.standard.entity.rule.RuleAllocationDetail;

/**
 * 
 * ����: �������ҵ����
 * @author fangjie
 * @since WMS 3.0
 */
public class AllocationRuleImpl implements IAllocationRuleBus {
	
	protected IAllocationRuleDao allocationRuleDAO = null;
	protected IAllocationDetailRuleDao allocationDetailRuleDAO = null;

	/**
	 * @param dao
	 */
	public AllocationRuleImpl(EntityDAO dao) {
		this.allocationRuleDAO = new AllocationRuleDaoImpl(dao);
		this.allocationDetailRuleDAO = new AllocationDetailRuleDaoImpl(dao);
	}


	/**
	 * ����:����������ѯ�������
	 * @param warehouseid 	�����ֿ�ID
	 * @param descr			�����������
	 * @return 
	 * @throws Exception
	 */
	public List getAllocationRuleQuery(String warehouseid, String descr) throws Exception {
		
		return allocationRuleDAO.getAllocationRuleQuery(warehouseid, descr);
	}

	/**
	 * ����:���ݷ������ID��ѯ���������ϸ��Ϣ
	 * @param allocationid 	�������ID
	 * @return 
	 * @throws Exception
	 */
	public List getAllocationDetailRuleById(String allocationid) throws Exception {
		
		return allocationDetailRuleDAO.getAllocationDetailRuleById(allocationid);
	}
	
	/**
	 * ����:���ӷ������
	 * @param allocationRule	�������
	 * @throws Exception
	 */
	public void addAllocationRule(RuleAllocation allocationRule) throws Exception {
		
		allocationRuleDAO.addAllocationRule(allocationRule);
	}

	/**
	 * ����:���ݷ������ID��÷������
	 * @param allocationid	�������ID
	 * @return 
	 * @throws Exception
	 */
	public RuleAllocation getAllocationRuleById(String allocationid) throws Exception {
		
		return allocationRuleDAO.getAllocationRuleById(allocationid);
	}

	/**
	 * ����:�޸ķ������
	 * @param allocationRule	�������
	 * @throws Exception
	 */
	public void updateAllocationRule(RuleAllocation allocationRule) throws Exception {

		allocationRuleDAO.updateAllocationRule(allocationRule);
	}

	/**
	 * ����:ɾ���������
	 * @param id	�������id
	 * @throws Exception
	 */
	public void deleteAllocationRule(String id) throws Exception {
		
		allocationRuleDAO.deleteAllocationRule(id);
	}

    /**
     * ���ܣ����ָ�����������˳λ
     * @param ruleid ����id
     * @return
     * @throws Exception 
     */
	public int getRuleNextSort(String ruleid) throws Exception {
		
		return allocationDetailRuleDAO.getRuleNextSort(ruleid);
	}

	/**
	 * ����:���ӷ��������ϸ
	 * @param allocationDetailRule	���������ϸ
	 * @throws Exception
	 */
	public void addAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception {

		allocationDetailRuleDAO.addAllocationDetailRule(allocationDetailRule);
	}

	/**
	 * ����:���ݷ��������ϸID��÷��������ϸ
	 * @param allocationdetailid	���������ϸID
	 * @return 
	 * @throws Exception
	 */
	public RuleAllocationDetail getPutDetailByDetailId(String allocationdetailid) throws Exception {
		
		return allocationDetailRuleDAO.getPutDetailByDetailId(allocationdetailid);
	}

	/**
	 * ����:�޸ķ��������ϸ
	 * @param allocationDetailRule	���������ϸ
	 * @throws Exception
	 */
	public void updateAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception {
		
		allocationDetailRuleDAO.updateAllocationDetailRule(allocationDetailRule);
	}

	/**
	 * ����:ɾ�����������ϸ
	 * @param allocationdetailid	���������ϸID
	 * @throws Exception
	 */
	public void deleteAllocationDetailRule(String allocationdetailid) throws Exception {
		
		allocationDetailRuleDAO.deleteAllocationDetailRule(allocationdetailid);
	}

	/**
	 * ����:�޸ķ��������ϸ������˳λ
	 * @param detailids	���������ϸID
	 * @param sorts		����˳λ
	 * @throws Exception
	 */
	public void updateAllocationDetailRuleSorts(String detailids, String sorts) throws Exception {
		
		allocationDetailRuleDAO.updateAllocationDetailRuleSorts(detailids, sorts);
	}

	
}