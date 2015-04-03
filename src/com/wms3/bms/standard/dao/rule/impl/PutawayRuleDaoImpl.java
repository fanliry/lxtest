package com.wms3.bms.standard.dao.rule.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.rule.IPutawayRuleDao;
import com.wms3.bms.standard.entity.rule.RulePutaway;

/**
 * 
 * ����: �ϼܹ������ݿ����dao��
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public class PutawayRuleDaoImpl implements IPutawayRuleDao{

    /** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    public PutawayRuleDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    public EntityDAO getEntityDAO() {
        return m_dao;
    }

	/**
	 * ����:����������ѯ�ϼܹ���
	 * @param warehouseid 	�����ֿ�ID
	 * @param descr		�ϼܹ�������
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
			throw new Exception("����������ѯ�ϼܹ������:" + er.getMessage());
		}
				
		return ls;
	}

	/**
	 * ����:�����ϼܹ���
	 * @param putawayRule	�ϼܹ���
	 * @throws Exception
	 */
	public void addPutawayRule(RulePutaway putawayRule) throws Exception {
		
		m_dao.save(putawayRule);
	}
	
	/**
	 * ����:�����ϼܹ���ID����ϼܹ���
	 * @param putawayid	�ϼܹ���ID
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
	 * ����:�޸��ϼܹ���
	 * @param putawayRule	�ϼܹ���
	 * @throws Exception
	 */
	public void updatePutawayRule(RulePutaway putawayRule) throws Exception {
		
		m_dao.update(putawayRule);
	}

	/**
	 * ����:ɾ���ϼܹ���
	 * @param id	�ϼܹ���id
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
        	Logger.error("ɾ���ϼܹ������ϸ��Ϣʱ����" + er.getMessage());
            throw new Exception("ɾ���ϼܹ������ϸ��Ϣʱ����" + er.getMessage());
        } 
		
	}

}
