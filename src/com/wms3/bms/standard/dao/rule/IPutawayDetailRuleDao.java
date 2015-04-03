package com.wms3.bms.standard.dao.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RulePutawayDetail;

/**
 * 
 * ����: �ϼܹ�����ϸ�����dao��ӿ�
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public interface IPutawayDetailRuleDao {

	/**
	 * ����:�����ϼܹ���ID��ѯ�ϼܹ�����ϸ��Ϣ
	 * @param putawayid 	�ϼܹ���ID
	 * @return 
	 * @throws Exception
	 */
	List getPutawayDetailRuleById(String putawayid) throws Exception;

    /**
     * ���ܣ����ָ�����������˳λ
     * @param ruleid ����id
     * @return
     * @throws Exception 
     */
	int getRuleNextSort(String ruleid) throws Exception;

	/**
	 * ����:�����ϼܹ�����ϸ
	 * @param putawayDetailRule	�ϼܹ�����ϸ
	 * @throws Exception
	 */
	void addPutawayDetailRule(RulePutawayDetail putawayDetailRule) throws Exception;

	/**
	 * ����:�����ϼܹ�����ϸID����ϼܹ�����ϸ
	 * @param putawaydetailid	�ϼܹ�����ϸID
	 * @return 
	 * @throws Exception
	 */
	RulePutawayDetail getPutDetailByDetailId(String putawaydetailid) throws Exception;
	
	/**
	 * ����:�޸��ϼܹ�����ϸ
	 * @param putawayDetailRule	�ϼܹ�����ϸ
	 * @param flg 
	 * @throws Exception
	 */
	void updatePutawayDetailRule(RulePutawayDetail putawayDetailRule) throws Exception;
	
	/**
	 * ����:ɾ���ϼܹ�����ϸ
	 * @param putawaydetailid	�ϼܹ�����ϸID
	 * @throws Exception
	 */
	void deletePutawayDetailRule(String putawaydetailid) throws Exception;

	/**
	 * ����:�޸��ϼܹ�����ϸ������˳λ
	 * @param detailids	�ϼܹ�����ϸID
	 * @param sorts		����˳λ
	 * @throws Exception
	 */
	void updatePutawayDetailRuleSorts(String detailids, String sorts) throws Exception;

}
