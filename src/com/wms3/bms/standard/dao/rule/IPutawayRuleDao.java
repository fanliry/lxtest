package com.wms3.bms.standard.dao.rule;

import java.util.List;

import com.wms3.bms.standard.entity.rule.RulePutaway;

/**
 * 
 * ����: �ϼܹ������dao��ӿ�
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public interface IPutawayRuleDao {

	/**
	 * ����:����������ѯ�ϼܹ���
	 * @param warehouseid 	�����ֿ�ID
	 * @param descr			�ϼܹ�������
	 * @return 
	 * @throws Exception
	 */
	public List getPutawayRuleQuery(String warehouseid, String descr) throws Exception;

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

}
