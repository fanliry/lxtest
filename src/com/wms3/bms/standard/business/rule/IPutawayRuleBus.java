package com.wms3.bms.standard.business.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RulePutaway;
import com.wms3.bms.standard.entity.rule.RulePutawayDetail;

/**
 * 
 * ����: �ϼܹ���ҵ��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IPutawayRuleBus {
	
	/**
	 * ����:����������ѯ�ϼܹ���
	 * @param warehouseid 	�����ֿ�ID
	 * @param descr			�ϼܹ�������
	 * @return 
	 * @throws Exception
	 */
	public List getPutawayRuleQuery(String warehouseid, String descr) throws Exception;

	/**
	 * ����:�����ϼܹ���ID��ѯ�ϼܹ�����ϸ��Ϣ
	 * @param putawayid 	�ϼܹ���ID
	 * @return 
	 * @throws Exception
	 */
	public List getPutawayDetailRuleById(String putawayid) throws Exception;
	
	/**
	 * ����:�����ϼܹ���
	 * @param putawayRule	�ϼܹ���
	 * @throws Exception
	 */
	public void addPutawayRule(RulePutaway putawayRule) throws Exception;

	/**
	 * ����:�����ϼܹ���ID����ϼܹ���
	 * @param putawayid	�ϼܹ���ID
	 * @return 
	 * @throws Exception
	 */
	public RulePutaway getPutawayRuleById(String putawayid) throws Exception;

	/**
	 * ����:�޸��ϼܹ���
	 * @param putawayRule	�ϼܹ���
	 * @throws Exception
	 */
	public void updatePutawayRule(RulePutaway putawayRule) throws Exception;

	/**
	 * ����:ɾ���ϼܹ���
	 * @param id	�ϼܹ���id
	 * @throws Exception
	 */
	public void deletePutawayRule(String id) throws Exception;

    /**
     * ���ܣ����ָ�����������˳λ
     * @param ruleid ����id
     * @return
     * @throws Exception 
     */
	public int getRuleNextSort(String ruleid) throws Exception;

	/**
	 * ����:�����ϼܹ�����ϸ
	 * @param putawayDetailRule	�ϼܹ�����ϸ
	 * @throws Exception
	 */
	public void addPutawayDetailRule(RulePutawayDetail putawayDetailRule) throws Exception;

	/**
	 * ����:�����ϼܹ�����ϸID����ϼܹ�����ϸ
	 * @param putawaydetailid	�ϼܹ�����ϸID
	 * @return 
	 * @throws Exception
	 */
	public RulePutawayDetail getPutDetailByDetailId(String putawaydetailid) throws Exception;

	/**
	 * ����:�޸��ϼܹ�����ϸ
	 * @param putawayDetailRule	�ϼܹ�����ϸ
	 * @param flg
	 * @throws Exception
	 */
	public void updatePutawayDetailRule(RulePutawayDetail putawayDetailRule) throws Exception;

	/**
	 * ����:ɾ���ϼܹ�����ϸ
	 * @param putawaydetailid	�ϼܹ�����ϸID
	 * @throws Exception
	 */
	public void deletePutawayDetailRule(String putawaydetailid) throws Exception;

	/**
	 * ����:�޸��ϼܹ�����ϸ������˳λ
	 * @param detailids	�ϼܹ�����ϸID
	 * @param sorts		����˳λ
	 * @throws Exception
	 */
	public void updatePutawayDetailRuleSorts(String detailids, String sorts) throws Exception;
	
}