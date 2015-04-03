package com.wms3.bms.standard.dao.rule.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.rule.IRuleConfigDao;
import com.wms3.bms.standard.entity.rule.RuleConfig;

/**
 * 
 * 描述: 补货规则数据库操作dao类
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public class RuleConfigDaoImpl implements IRuleConfigDao{

    /** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;
    public RuleConfigDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    public EntityDAO getEntityDAO() {
        return m_dao;
    }

	/**
	 * 功能:根据规则类型查询规则配置的列表
	 * @param flag		规则类型 1：上架；2：分配；3：补货
	 * @return 
	 * @throws Exception
	 */
	public List getRuleConfigByRuletype(String flag) throws Exception {
		
		List ls = null;
		try
		{
			String strHql = "From RuleConfig as t where 1=1 and t.ruletype ='" + flag + "'";
			ls = m_dao.searchEntities(strHql);		
				
		}catch(Exception er)
		{
			throw new Exception("根据规则类型查询规则配置的列表出错:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * 功能:根据id查询规则配置的列表
	 * @param id
	 * @return 
	 * @throws Exception
	 */
	public RuleConfig getRuleConfigById(String id) throws Exception {
		
		try{
		
			String strHql = "From RuleConfig as t where 1=1 and t.ruleconfigid ='" + id + "'";
			List ls = m_dao.searchEntities(strHql);	
			
			if(ls != null && ls.size() > 0){
				return (RuleConfig)ls.get(0);
			}
				
		}catch(Exception er){
		
			throw new Exception("根据id查询规则配置的列表出错:" + er.getMessage());
		}
		
		return null;
	}

}
