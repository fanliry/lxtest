package com.wms3.bms.standard.dao.rule.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.rule.IPutawayDetailRuleDao;
import com.wms3.bms.standard.entity.rule.RulePutawayDetail;

/**
 * 
 * ����: �ϼܹ�����ϸ�����dao��
 * @author hug 2012-5-7
 * @since WMS 3.0
 */
public class PutawayDetailRuleDaoImpl implements IPutawayDetailRuleDao{

    /** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    public PutawayDetailRuleDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    
    public EntityDAO getEntityDAO() {
        return m_dao;
    }

	/**
	 * ����:�����ϼܹ���ID��ѯ�ϼܹ�����ϸ��Ϣ
	 * @param putawayid 	�ϼܹ���ID
	 * @return 
	 * @throws Exception
	 */
	public List getPutawayDetailRuleById(String putawayid) throws Exception {
		
		List ls = null;
		
		try {
			String sql = "From RulePutawayDetail as t where t.putawayid='" + putawayid + "' order by t.sort";
			ls = m_dao.searchEntities(sql);	
			
		} catch (Exception er) {
			throw new Exception("�����ϼܹ���ID��ѯ�ϼܹ�����ϸ��Ϣ����:" + er.getMessage());
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
			String strSql = "select max(t.sort) from RulePutawayDetail as t where t.putawayid='" + ruleid + "'";
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
	 * ����:�����ϼܹ�����ϸ
	 * @param putawayDetailRule	�ϼܹ�����ϸ
	 * @throws Exception
	 */
	public void addPutawayDetailRule(RulePutawayDetail putawayDetailRule) throws Exception {
		
		m_dao.save(putawayDetailRule);
	}

	/**
	 * ����:�����ϼܹ�����ϸID����ϼܹ�����ϸ
	 * @param putawaydetailid	�ϼܹ�����ϸID
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
	 * ����:�޸��ϼܹ�����ϸ
	 * @param putawayDetailRule	�ϼܹ�����ϸ
	 * @param flg 
	 * @throws Exception
	 */
	public void updatePutawayDetailRule(RulePutawayDetail putawayDetailRule) throws Exception {
		
		m_dao.update(putawayDetailRule);
	}

	/**
	 * ����:ɾ���ϼܹ�����ϸ
	 * @param putawaydetailid	�ϼܹ�����ϸID
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
	 * ����:�޸��ϼܹ�����ϸ������˳λ
	 * @param detailids	�ϼܹ�����ϸID
	 * @param sorts		����˳λ
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
        	Logger.error("�޸��ϼܹ�����ϸ������˳λʱ����" + er.getMessage());
            throw new Exception("�޸��ϼܹ�����ϸ������˳λʱ����" + er.getMessage());
        } 
	}

}
