package com.wms3.bms.standard.dao.rule.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.rule.IAllocationRuleDao;
import com.wms3.bms.standard.entity.rule.RuleAllocation;

/**
 * 
 * ����: ����������ݿ����dao��
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public class AllocationRuleDaoImpl implements IAllocationRuleDao{

    /** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    public AllocationRuleDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    public EntityDAO getEntityDAO() {
        return m_dao;
    }

	/**
	 * ����:����������ѯ�������
	 * @param warehouseid 	�����ֿ�ID
	 * @param descr		�����������
	 * @return 
	 * @throws Exception
	 */
	public List getAllocationRuleQuery(String warehouseid, String descr) throws Exception {
		
		List ls = null;
		
		try {
			String sql = "From RuleAllocation as t where 1=1" ;
			
			if(warehouseid != null && warehouseid.trim().length() > 0){
				sql += " and t.warehouseid = '" + warehouseid + "'";
			}
			
			if(descr != null && descr.trim().length() > 0){
				sql += " and t.descr like '%" + descr + "%'";
			}
			
			String strHQL = sql + " order by t.allocationid";
			ls = m_dao.searchEntities(strHQL);	
			
		} catch (Exception er) {
			throw new Exception("����������ѯ����������:" + er.getMessage());
		}
				
		return ls;
	}

	/**
	 * ����:���ӷ������
	 * @param allocationRule	�������
	 * @throws Exception
	 */
	public void addAllocationRule(RuleAllocation allocationRule) throws Exception {
		
		m_dao.save(allocationRule);
	}
	
	/**
	 * ����:���ݷ������ID��÷������
	 * @param allocationid	�������ID
	 * @return 
	 * @throws Exception
	 */
	public RuleAllocation getAllocationRuleById(String allocationid) throws Exception {
		
		if(allocationid!=null && !allocationid.equals(""))
		{
			String strSql = " from RuleAllocation as t where t.allocationid='" + allocationid + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (RuleAllocation)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * ����:�޸ķ������
	 * @param allocationRule	�������
	 * @throws Exception
	 */
	public void updateAllocationRule(RuleAllocation allocationRule) throws Exception {
		
		m_dao.update(allocationRule);
	}

	/**
	 * ����:ɾ���������
	 * @param id	�������id
	 * @throws Exception
	 */
	public void deleteAllocationRule(String id) throws Exception {	
		
		try{
			List<String> lsSQL = new ArrayList<String>();
			
			String strSql1 = " delete RuleAllocationDetail as t where t.allocationid='" + id + "'";
			lsSQL.add(strSql1);
			
			String strSql2 = " delete RuleAllocation as t where t.allocationid='" + id + "'";
			lsSQL.add(strSql2);
			
			m_dao.deleteSqlList(lsSQL);
			
		}catch(Exception er){
        	Logger.error("ɾ�����������ϸ����ϸ��Ϣʱ����" + er.getMessage());
            throw new Exception("ɾ�����������ϸ����ϸ��Ϣʱ����" + er.getMessage());
        } 
		
	}

}
