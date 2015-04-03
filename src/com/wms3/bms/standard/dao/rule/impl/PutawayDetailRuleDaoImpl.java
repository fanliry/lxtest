package com.wms3.bms.standard.dao.rule.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.rule.IPutawayDetailRuleDao;
import com.wms3.bms.standard.entity.rule.RulePutawayDetail;

/**
 * 
 * 描述: 上架规则详细表操作dao类
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public class PutawayDetailRuleDaoImpl implements IPutawayDetailRuleDao{

    /** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;
    public PutawayDetailRuleDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    public EntityDAO getEntityDAO() {
        return m_dao;
    }

	/**
	 * 功能:根据上架规则ID查询上架规则详细信息
	 * @param putawayid 	上架规则ID
	 * @return 
	 * @throws Exception
	 */
	public List getPutawayDetailRuleById(String putawayid) throws Exception {
		
		List ls = null;
		
		try {
			String sql = "From RulePutawayDetail as t where t.putawayid='" + putawayid + "' order by t.sort";
			ls = m_dao.searchEntities(sql);	
			
		} catch (Exception er) {
			throw new Exception("根据上架规则ID查询上架规则详细信息出错:" + er.getMessage());
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
			String strSql = "select max(t.sort) from RulePutawayDetail as t where t.putawayid='" + ruleid + "'";
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
	 * 功能:增加上架规则详细
	 * @param putawayDetailRule	上架规则详细
	 * @throws Exception
	 */
	public void addPutawayDetailRule(RulePutawayDetail putawayDetailRule) throws Exception {
		
		m_dao.save(putawayDetailRule);
	}

	/**
	 * 功能:根据上架规则详细ID获得上架规则详细
	 * @param putawaydetailid	上架规则详细ID
	 * @return 
	 * @throws Exception
	 */
	public RulePutawayDetail getPutDetailByDetailId(String putawaydetailid) throws Exception {
		
		String strSql = " from RulePutawayDetail as t where t.putawaydetailid='" + putawaydetailid + "'";
		List ls = m_dao.searchEntities(strSql);
		if(ls != null && ls.size() > 0){
			return (RulePutawayDetail)ls.get(0);
		}
		return null;
	}

	/**
	 * 功能:修改上架规则详细
	 * @param putawayDetailRule	上架规则详细
	 * @param flg 
	 * @throws Exception
	 */
	public void updatePutawayDetailRule(RulePutawayDetail putawayDetailRule) throws Exception {
		
		m_dao.update(putawayDetailRule);
	}

	/**
	 * 功能:删除上架规则详细
	 * @param putawaydetailid	上架规则详细ID
	 * @throws Exception
	 */
	public void deletePutawayDetailRule(String putawaydetailid) throws Exception {
		
		if(putawaydetailid != null)
		{
			String strSql = " delete RulePutawayDetail as t where t.putawaydetailid='" + putawaydetailid + "'";

			m_dao.deleteSql(strSql);
		}
	}

	/**
	 * 功能:修改上架规则详细的优先顺位
	 * @param detailids	上架规则详细ID
	 * @param sorts		优先顺位
	 * @throws Exception
	 */
	public void updatePutawayDetailRuleSorts(String detailids, String sorts) throws Exception {
		
		try{
			List<String> lsSQL = new ArrayList<String>();
			String strSql = "";
			
			String[] detailid = detailids.split(",");
			String[] sort = sorts.split(",");
			
			for(int i=0; i<detailid.length; i++){
				strSql = " update RulePutawayDetail as t set t.sort =" + Integer.parseInt(sort[i]) + " where t.putawaydetailid='" + detailid[i] + "'";
				lsSQL.add(strSql);
			}
			
            m_dao.deleteSqlList(lsSQL);
			
		}catch(Exception er){
        	Logger.error("修改上架规则详细的优先顺位时出错！" + er.getMessage());
            throw new Exception("修改上架规则详细的优先顺位时出错：" + er.getMessage());
        } 
	}

}
