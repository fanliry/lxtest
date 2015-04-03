package com.wms3.bms.standard.business.rule.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.rule.IReplenishRuleBus;
import com.wms3.bms.standard.dao.rule.IReplenishRuleDao;
import com.wms3.bms.standard.dao.rule.impl.ReplenishRuleDaoImpl;
import com.wms3.bms.standard.entity.rule.RuleReplenish;

/**
 * 
 * ����: ��������ҵ����
 * @author fangjie
 * @since WMS 3.0
 */
public class ReplenishRuleImpl implements IReplenishRuleBus {
	
	protected IReplenishRuleDao replenishRuleDAO = null;
	protected EntityDAO m_dao = null;

	/**
	 * @param dao
	 */
	public ReplenishRuleImpl(EntityDAO dao) {
		this.replenishRuleDAO = new ReplenishRuleDaoImpl(dao);
		this.m_dao = dao;
	}


	/**
	 * ����:����������ѯ��������
	 * @param warehouseid 	�����ֿ�ID
	 * @param descr			������������
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getReplenishRuleQuery(String warehouseid, String descr, String strUrl, int maxLine) throws Exception {
		
		PagingTool pt = null;
		
		try {
			String sql = "From RuleReplenish as t where 1=1" ;
			
			if(warehouseid != null && warehouseid.trim().length() > 0){
				sql += " and t.warehouseid = '" + warehouseid + "'";
			}
			
			if(descr != null && descr.trim().length() > 0){
				sql += " and t.descr like '%" + descr + "%'";
			}
			
			String strHQL = sql + " order by t.replenishid";
			String strCountHQL = "select count(*)" + sql ;
			
			pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("����������ѯ�����������:" + er.getMessage());
		}
				
		return pt;
	}

	/**
	 * ����:���Ӳ�������
	 * @param replenishRule	��������
	 * @throws Exception
	 */
	public void addReplenishRule(RuleReplenish replenishRule) throws Exception {
		
		replenishRuleDAO.addReplenishRule(replenishRule);
	}

	/**
	 * ����:���ݲ�������ID��ò�������
	 * @param replenishid	��������ID
	 * @return 
	 * @throws Exception
	 */
	public RuleReplenish getReplenishRuleById(String replenishid) throws Exception {
		
		return replenishRuleDAO.getReplenishRuleById(replenishid);
	}

	/**
	 * ����:�޸Ĳ�������
	 * @param replenishRule	��������
	 * @throws Exception
	 */
	public void updateReplenishRule(RuleReplenish replenishRule) throws Exception {

		replenishRuleDAO.updateReplenishRule(replenishRule);
	}

	/**
	 * ����:ɾ����������
	 * @param id	��������id
	 * @throws Exception
	 */
	public void deleteReplenishRule(String id) throws Exception {
		
		replenishRuleDAO.deleteReplenishRule(id);
	}

	/**
	 * ����:���ݲֿ��ѯ��������
	 * @param warehouseid 	�����ֿ�ID
	 * @return 
	 * @throws Exception
	 */
	public List getReplenishRulesBywhid(String warehouseid) throws Exception {
		
		return replenishRuleDAO.getReplenishRulesBywhid(warehouseid);
	}
	
}