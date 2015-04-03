package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseSeqDao;
import com.wms3.bms.standard.entity.base.BaseSeq;

/**
 * ����: ���к�DAO��
 * @author fangjie 
 * 
 */
public class BaseSeqDaoImpl implements IBaseSeqDao {

	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    
	public BaseSeqDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * ����:����������ѯ���к�
	 * @param remark		����
	 * @return
	 * @throws Exception
	 */
	public List getSeqQuery(String remark) throws Exception {
		
		List ls = null;
		try{
			
			StringBuilder strBud = new StringBuilder();
			strBud.append(" from BaseSeq as seq where 1=1 ");
			
			if(remark != null && remark.trim().length() > 0){
				strBud.append(" and seq.seqRemark='").append(remark).append("'");
			}
			
			strBud.append(" order by seq.seqId ");
			ls = m_dao.searchEntities(strBud.toString());		
				
		}catch(Exception er)
		{
			throw new Exception("����������ѯ���кų���:" + er.getMessage());
		}
		return ls;
	}
	
	/**
	 * ����:�������к�ID������к�
	 * @param seqId	���к�ID
	 * @return
	 * @throws Exception
	 */
	public BaseSeq getSeqById(String seqId) throws Exception{
		
		if(seqId != null){
			
			String strSql = " from BaseSeq as t where t.seqId='" + seqId + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseSeq)ls.get(0);
			}
		}
		return null;
	}
	/**
	 * ����:�����������ͻ�ø�ϵ�ж���
	 * @param seqType	��������
	 * @return
	 * @throws Exception
	 */
	public BaseSeq getSeqByType(String seqType) throws Exception{
		
		if(seqType != null){
			
			String strSql = " from BaseSeq as t where t.seqType='" + seqType + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseSeq)ls.get(0);
			}
		}
		return null;
	}


	/**
	 * ����:�޸����к�
	 * @param seq	���к�
	 * @throws Exception
	 */
	public void updateSeq(BaseSeq seq) throws Exception {
		
		m_dao.update(seq);
	}
}
