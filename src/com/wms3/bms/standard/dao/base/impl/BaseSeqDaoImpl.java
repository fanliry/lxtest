package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseSeqDao;
import com.wms3.bms.standard.entity.base.BaseSeq;

/**
 * 描述: 序列号DAO类
 * @author fangjie 
 * 
 */
public class BaseSeqDaoImpl implements IBaseSeqDao {

	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;
    
	public BaseSeqDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * 功能:根据条件查询序列号
	 * @param remark		描述
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
			throw new Exception("根据条件查询序列号出错:" + er.getMessage());
		}
		return ls;
	}
	
	/**
	 * 功能:根据序列号ID获得序列号
	 * @param seqId	序列号ID
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
	 * 功能:根据序列类型获得该系列对象
	 * @param seqType	序列类型
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
	 * 功能:修改序列号
	 * @param seq	序列号
	 * @throws Exception
	 */
	public void updateSeq(BaseSeq seq) throws Exception {
		
		m_dao.update(seq);
	}
}
