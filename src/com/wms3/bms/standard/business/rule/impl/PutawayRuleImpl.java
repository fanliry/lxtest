package com.wms3.bms.standard.business.rule.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.rule.IPutawayRuleBus;
import com.wms3.bms.standard.dao.rule.IPutawayDetailRuleDao;
import com.wms3.bms.standard.dao.rule.IPutawayRuleDao;
import com.wms3.bms.standard.dao.rule.impl.PutawayDetailRuleDaoImpl;
import com.wms3.bms.standard.dao.rule.impl.PutawayRuleDaoImpl;
import com.wms3.bms.standard.entity.rule.RulePutaway;
import com.wms3.bms.standard.entity.rule.RulePutawayDetail;

/**
 * 
 * ����: �ϼܹ���ҵ����
 * @author fangjie
 * @since WMS 3.0
 */
public class PutawayRuleImpl implements IPutawayRuleBus {
	
	protected IPutawayRuleDao putawayRuleDAO = null;
	protected IPutawayDetailRuleDao putawayDetailRuleDAO = null;

	/**
	 * @param dao
	 */
	public PutawayRuleImpl(EntityDAO dao) {
		this.putawayRuleDAO = new PutawayRuleDaoImpl(dao);
		this.putawayDetailRuleDAO = new PutawayDetailRuleDaoImpl(dao);
	}


	/**
	 * ����:����������ѯ�ϼܹ���
	 * @param warehouseid 	�����ֿ�ID
	 * @param descr			�ϼܹ�������
	 * @return 
	 * @throws Exception
	 */
	public List getPutawayRuleQuery(String warehouseid, String descr) throws Exception {
		
		return putawayRuleDAO.getPutawayRuleQuery(warehouseid, descr);
	}

	/**
	 * ����:�����ϼܹ���ID��ѯ�ϼܹ�����ϸ��Ϣ
	 * @param putawayid 	�ϼܹ���ID
	 * @return 
	 * @throws Exception
	 */
	public List getPutawayDetailRuleById(String putawayid) throws Exception {
		
		return putawayDetailRuleDAO.getPutawayDetailRuleById(putawayid);
	}
	
	/**
	 * ����:�����ϼܹ���
	 * @param putawayRule	�ϼܹ���
	 * @throws Exception
	 */
	public void addPutawayRule(RulePutaway putawayRule) throws Exception {
		
		putawayRuleDAO.addPutawayRule(putawayRule);
	}

	/**
	 * ����:�����ϼܹ���ID����ϼܹ���
	 * @param putawayid	�ϼܹ���ID
	 * @return 
	 * @throws Exception
	 */
	public RulePutaway getPutawayRuleById(String putawayid) throws Exception {
		
		return putawayRuleDAO.getPutawayRuleById(putawayid);
	}

	/**
	 * ����:�޸��ϼܹ���
	 * @param putawayRule	�ϼܹ���
	 * @throws Exception
	 */
	public void updatePutawayRule(RulePutaway putawayRule) throws Exception {

		putawayRuleDAO.updatePutawayRule(putawayRule);
	}

	/**
	 * ����:ɾ���ϼܹ���
	 * @param id	�ϼܹ���id
	 * @throws Exception
	 */
	public void deletePutawayRule(String id) throws Exception {
		
		putawayRuleDAO.deletePutawayRule(id);
	}

    /**
     * ���ܣ����ָ�����������˳λ
     * @param ruleid ����id
     * @return
     * @throws Exception 
     */
	public int getRuleNextSort(String ruleid) throws Exception {
		
		return putawayDetailRuleDAO.getRuleNextSort(ruleid);
	}

	/**
	 * ����:�����ϼܹ�����ϸ
	 * @param putawayDetailRule	�ϼܹ�����ϸ
	 * @throws Exception
	 */
	public void addPutawayDetailRule(RulePutawayDetail putawayDetailRule) throws Exception {

		putawayDetailRuleDAO.addPutawayDetailRule(putawayDetailRule);
	}

	/**
	 * ����:�����ϼܹ�����ϸID����ϼܹ�����ϸ
	 * @param putawaydetailid	�ϼܹ�����ϸID
	 * @return 
	 * @throws Exception
	 */
	public RulePutawayDetail getPutDetailByDetailId(String putawaydetailid) throws Exception {
		
		return putawayDetailRuleDAO.getPutDetailByDetailId(putawaydetailid);
	}

	/**
	 * ����:�޸��ϼܹ�����ϸ
	 * @param putawayDetailRule	�ϼܹ�����ϸ
	 * @param flg
	 * @throws Exception
	 */
	public void updatePutawayDetailRule(RulePutawayDetail putawayDetailRule) throws Exception {
		
		putawayDetailRuleDAO.updatePutawayDetailRule(putawayDetailRule);
	}

	/**
	 * ����:ɾ���ϼܹ�����ϸ
	 * @param putawaydetailid	�ϼܹ�����ϸID
	 * @throws Exception
	 */
	public void deletePutawayDetailRule(String putawaydetailid) throws Exception {
		
		putawayDetailRuleDAO.deletePutawayDetailRule(putawaydetailid);
	}

	/**
	 * ����:�޸��ϼܹ�����ϸ������˳λ
	 * @param detailids	�ϼܹ�����ϸID
	 * @param sorts		����˳λ
	 * @throws Exception
	 */
	public void updatePutawayDetailRuleSorts(String detailids, String sorts) throws Exception {
		
		putawayDetailRuleDAO.updatePutawayDetailRuleSorts(detailids, sorts);
	}

	
}