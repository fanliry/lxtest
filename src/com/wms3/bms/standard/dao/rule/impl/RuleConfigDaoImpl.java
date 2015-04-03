package com.wms3.bms.standard.dao.rule.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.rule.IRuleConfigDao;
import com.wms3.bms.standard.entity.rule.RuleConfig;

/**
 * 
 * ����: �����������ݿ����dao��
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public class RuleConfigDaoImpl implements IRuleConfigDao{

    /** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    public RuleConfigDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    public EntityDAO getEntityDAO() {
        return m_dao;
    }

	/**
	 * ����:���ݹ������Ͳ�ѯ�������õ��б�
	 * @param flag		�������� 1���ϼܣ�2�����䣻3������
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
			throw new Exception("���ݹ������Ͳ�ѯ�������õ��б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:����id��ѯ�������õ��б�
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
		
			throw new Exception("����id��ѯ�������õ��б����:" + er.getMessage());
		}
		
		return null;
	}

}
