package com.wms3.bms.standard.dao.rule.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.rule.IAllocationDetailRuleDao;
import com.wms3.bms.standard.entity.rule.RuleAllocationDetail;

/**
 * 
 * 描述: 分配规则详细表操作dao类
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public class AllocationDetailRuleDaoImpl implements IAllocationDetailRuleDao{

    /** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;
    public AllocationDetailRuleDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    public EntityDAO getEntityDAO() {
        return m_dao;
    }

	/**
	 * 功能:根据分配规则ID查询分配规则详细信息
	 * @param allocationid 	分配规则ID
	 * @return 
	 * @throws Exception
	 */
	public List getAllocationDetailRuleById(String allocationid) throws Exception {
		
		List ls = null;
		
		try {
			String sql = "From RuleAllocationDetail as t where t.allocationid='" + allocationid + "' order by t.sort";
			ls = m_dao.searchEntities(sql);	
			
		} catch (Exception er) {
			throw new Exception("根据分配规则ID查询分配规则详细信息出错:" + er.getMessage());
		}
				
		return ls;
	}
	
    /**
     * 功能：获得指定规则的最大优先顺位
     * @param ruleid 规则id
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
			throw new Exception("获得指定规则的最大优先顺位出错:" + er.getMessage());
		}
		
		if(ls != null && ls.size() > 0 && ls.get(0) != null){
			value = (Integer)ls.get(0);
		}
		return (value + 1);
	}

	/**
	 * 功能:增加分配规则详细
	 * @param allocationDetailRule	分配规则详细
	 * @throws Exception
	 */
	public void addAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception {
		
		m_dao.save(allocationDetailRule);
	}

	/**
	 * 功能:根据分配规则详细ID获得分配规则详细
	 * @param allocationdetailid	分配规则详细ID
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
	 * 功能:修改分配规则详细
	 * @param allocationDetailRule	分配规则详细
	 * @throws Exception
	 */
	public void updateAllocationDetailRule(RuleAllocationDetail allocationDetailRule) throws Exception {
		
		m_dao.update(allocationDetailRule);
	}

	/**
	 * 功能:删除分配规则详细
	 * @param allocationdetailid	分配规则详细ID
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
	 * 功能:修改分配规则详细的优先顺位
	 * @param detailids	分配规则详细ID
	 * @param sorts		优先顺位
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
        	Logger.error("修改分配规则详细的优先顺位时出错！" + er.getMessage());
            throw new Exception("修改分配规则详细的优先顺位时出错：" + er.getMessage());
        } 
	}

}
