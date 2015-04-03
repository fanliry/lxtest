package com.wms3.bms.standard.dao.rule.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.rule.IPutawayRuleDao;
import com.wms3.bms.standard.entity.rule.RulePutaway;

/**
 * 
 * 描述: 上架规则数据库操作dao类
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public class PutawayRuleDaoImpl implements IPutawayRuleDao{

    /** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;
    public PutawayRuleDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    public EntityDAO getEntityDAO() {
        return m_dao;
    }

	/**
	 * 功能:根据条件查询上架规则
	 * @param warehouseid 	所属仓库ID
	 * @param descr		上架规则描述
	 * @return 
	 * @throws Exception
	 */
	public List getPutawayRuleQuery(String warehouseid, String descr) throws Exception {
		
		List ls = null;
		
		try {
			String sql = "From RulePutaway as t where 1=1" ;
			
			if(warehouseid != null && warehouseid.trim().length() > 0){
				sql += " and t.warehouseid = '" + warehouseid + "'";
			}
			
			if(descr != null && descr.trim().length() > 0){
				sql += " and t.descr like '%" + descr + "%'";
			}
			
			String strHQL = sql + " order by t.putawayid";
			ls = m_dao.searchEntities(strHQL);	
			
		} catch (Exception er) {
			throw new Exception("根据条件查询上架规则出错:" + er.getMessage());
		}
				
		return ls;
	}

	/**
	 * 功能:增加上架规则
	 * @param putawayRule	上架规则
	 * @throws Exception
	 */
	public void addPutawayRule(RulePutaway putawayRule) throws Exception {
		
		m_dao.save(putawayRule);
	}
	
	/**
	 * 功能:根据上架规则ID获得上架规则
	 * @param putawayid	上架规则ID
	 * @return 
	 * @throws Exception
	 */
	public RulePutaway getPutawayRuleById(String putawayid) throws Exception {
		
		if(putawayid!=null && !putawayid.equals(""))
		{
			String strSql = " from RulePutaway as t where t.putawayid='" + putawayid + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (RulePutaway)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 功能:修改上架规则
	 * @param putawayRule	上架规则
	 * @throws Exception
	 */
	public void updatePutawayRule(RulePutaway putawayRule) throws Exception {
		
		m_dao.update(putawayRule);
	}

	/**
	 * 功能:删除上架规则
	 * @param id	上架规则id
	 * @throws Exception
	 */
	public void deletePutawayRule(String id) throws Exception {	
		
		try{
			List<String> lsSQL = new ArrayList<String>();
			
			String strSql1 = " delete RulePutawayDetail as t where t.putawayid='" + id + "'";
			lsSQL.add(strSql1);
			
			String strSql2 = " delete RulePutaway as t where t.putawayid='" + id + "'";
			lsSQL.add(strSql2);
			
			m_dao.deleteSqlList(lsSQL);
			
		}catch(Exception er){
        	Logger.error("删除上架规则和详细信息时出错！" + er.getMessage());
            throw new Exception("删除上架规则和详细信息时出错：" + er.getMessage());
        } 
		
	}

}
