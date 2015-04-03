package com.wms3.bms.standard.dao.rule.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.rule.IAllocationDetailRuleDao;
import com.wms3.bms.standard.entity.rule.RuleAllocationDetail;

/**
 * 
 * ����: ���������ϸ�����dao��
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public class AllocationDetailRuleDaoImpl implements IAllocationDetailRuleDao{

    /** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    public AllocationDetailRuleDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    public EntityDAO getEntityDAO() {
        return m_dao;
    }

	/**
	 * ����:���ݷ������ID��ѯ���������ϸ��Ϣ
	 * @param allocationid 	�������ID
	 * @return 
	 * @throws Exception
	 */
	public List getAllocationDetailRuleById(String allocationid) throws Exception {
		
		List ls = null;
		
		try {
			String sql = "From RuleAllocationDetail as t where t.allocationid='" + allocationid + "' order by t.sort";
			ls = m_dao.searchEntities(sql);	
			
		} catch (Exception er) {
			throw new Exception("���ݷ������ID��ѯ���������ϸ��Ϣ����:" + er.getMessage());
		}
				
		return ls;
	}
	
    /**
     * ���ܣ����ָ��������������˳λ
     * @param ruleid ����id
     * @return
     * @throws Exception 
     */
	public int getRuleNextSort(String ruleid) throws Exception {
		
		List ls = null;
		int value = 0;
		
		try {
			String strSql = "select max(t.sort) from RuleAllocationDetail as t where t.allocationid='" + ruleid + "'";
			ls = m_dao.searchEntities(strSql);
			
		} catch (Exception er) {
			throw new Exception("���ָ��������������˳λ����:" + er.getMessage());
		}
		
		if(ls != null && ls.size() > 0 && ls.get(0) != null){
			value = (Integer)ls.get(0);
		}
		return (value + 1);
	}

	/**
	 * ����:���ӷ��������ϸ
	 * @param allocationDetailRule	���������ϸ
	 * @throws Exception
	 */
	public void addAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception {
		
		m_dao.save(allocationDetailRule);
	}

	/**
	 * ����:���ݷ��������ϸID��÷��������ϸ
	 * @param allocationdetailid	���������ϸID
	 * @return 
	 * @throws Exception
	 */
	public RuleAllocationDetail getPutDetailByDetailId(String allocationdetailid) throws Exception {
		
		String strSql = " from RuleAllocationDetail as t where t.allocationdetailid='" + allocationdetailid + "'";
		List ls = m_dao.searchEntities(strSql);
		if(ls != null && ls.size() > 0){
			return (RuleAllocationDetail)ls.get(0);
		}
		return null;
	}

	/**
	 * ����:�޸ķ��������ϸ
	 * @param allocationDetailRule	���������ϸ
	 * @throws Exception
	 */
	public void updateAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception {
		
		m_dao.update(allocationDetailRule);
	}

	/**
	 * ����:ɾ�����������ϸ
	 * @param allocationdetailid	���������ϸID
	 * @throws Exception
	 */
	public void deleteAllocationDetailRule(String allocationdetailid) throws Exception {
		
		if(allocationdetailid != null)
		{
			String strSql  = " delete RuleAllocationDetail as t where t.allocationdetailid='" + allocationdetailid + "'";

			m_dao.deleteSql(strSql);
		}
	}

	/**
	 * ����:�޸ķ��������ϸ������˳λ
	 * @param detailids	���������ϸID
	 * @param sorts		����˳λ
	 * @throws Exception
	 */
	public void updateAllocationDetailRuleSorts(String detailids, String sorts) throws Exception {
		
		try{
			List<String> lsSQL = new ArrayList<String>();
			String strSql = "";
			
			String[] detailid = detailids.split(",");
			String[] sort = sorts.split(",");
			
			for(int i=0; i<detailid.length; i++){
				strSql = " update RuleAllocationDetail as t set t.sort =" + Integer.parseInt(sort[i]) + " where t.allocationdetailid='" + detailid[i] + "'";
				lsSQL.add(strSql);
			}
			
            m_dao.deleteSqlList(lsSQL);
			
		}catch(Exception er){
        	Logger.error("�޸ķ��������ϸ������˳λʱ����" + er.getMessage());
            throw new Exception("�޸ķ��������ϸ������˳λʱ����" + er.getMessage());
        } 
	}

}
