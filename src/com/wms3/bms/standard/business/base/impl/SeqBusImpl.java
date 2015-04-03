package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.ISeqBus;
import com.wms3.bms.standard.dao.base.IBaseSeqDao;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.entity.base.BaseSeq;

/**
 * 描述: 序列号业务类
 * @author fangjie
 * 
 */
public class SeqBusImpl implements ISeqBus {
	
	protected IBaseSeqDao baseSeqDAO = null;

	public SeqBusImpl(EntityDAO dao) {
		this.baseSeqDAO = new BaseSeqDaoImpl(dao);
	}
	
	/**
	 * 功能:根据条件查询序列号
	 * @param remark		描述
	 * @return
	 * @throws Exception
	 */
	public List getSeqQuery(String remark) throws Exception {
		
		return baseSeqDAO.getSeqQuery(remark);
	}

	/**
	 * 功能:根据序列号ID获得序列号
	 * @param seqId	序列号ID
	 * @return
	 * @throws Exception
	 */
	public BaseSeq getSeqById(String seqId) throws Exception {
		
		return baseSeqDAO.getSeqById(seqId);
	}

	/**
	 * 功能:修改序列号
	 * @param seq	序列号
	 * @throws Exception
	 */
	public void updateSeq(BaseSeq seq) throws Exception {
		
		baseSeqDAO.updateSeq(seq);
	}


}