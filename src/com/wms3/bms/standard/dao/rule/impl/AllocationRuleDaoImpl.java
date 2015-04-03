package com.wms3.bms.standard.dao.rule.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.rule.IAllocationRuleDao;
import com.wms3.bms.standard.entity.rule.RuleAllocation;

/**
 * 
 * 描述: 分配规则数据库操作dao类
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public class AllocationRuleDaoImpl implements IAllocationRuleDao{

    /** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;
    public AllocationRuleDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    public EntityDAO getEntityDAO() {
        return m_dao;
    }

	/**
	 * 功能:根据条件查询分配规则
	 * @param warehouseid 	所属仓库ID
	 * @param descr		分配规则描述
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
			throw new Exception("根据条件查询分配规则出错:" + er.getMessage());
		}
				
		return ls;
	}

	/**
	 * 功能:增加分配规则
	 * @param allocationRule	分配规则
	 * @throws Exception
	 */
	public void addAllocationRule(RuleAllocation allocationRule) throws Exception {
		
		m_dao.save(allocationRule);
	}
	
	/**
	 * 功能:根据分配规则ID获得分配规则
	 * @param allocationid	分配规则ID
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
	 * 功能:修改分配规则
	 * @param allocationRule	分配规则
	 * @throws Exception
	 */
	public void updateAllocationRule(RuleAllocation allocationRule) throws Exception {
		
		m_dao.update(allocationRule);
	}

	/**
	 * 功能:删除分配规则
	 * @param id	分配规则id
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
        	Logger.error("删除分配规则详细和详细信息时出错！" + er.getMessage());
            throw new Exception("删除分配规则详细和详细信息时出错：" + er.getMessage());
        } 
		
	}

}
