package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.ISeqBus;
import com.wms3.bms.standard.dao.base.IBaseSeqDao;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.entity.base.BaseSeq;

/**
 * ����: ���к�ҵ����
 * @author fangjie
 * 
 */
public class SeqBusImpl implements ISeqBus {
	
	protected IBaseSeqDao baseSeqDAO = null;

	public SeqBusImpl(EntityDAO dao) {
		this.baseSeqDAO = new BaseSeqDaoImpl(dao);
	}
	
	/**
	 * ����:����������ѯ���к�
	 * @param remark		����
	 * @return
	 * @throws Exception
	 */
	public List getSeqQuery(String remark) throws Exception {
		
		return baseSeqDAO.getSeqQuery(remark);
	}

	/**
	 * ����:�������к�ID������к�
	 * @param seqId	���к�ID
	 * @return
	 * @throws Exception
	 */
	public BaseSeq getSeqById(String seqId) throws Exception {
		
		return baseSeqDAO.getSeqById(seqId);
	}

	/**
	 * ����:�޸����к�
	 * @param seq	���к�
	 * @throws Exception
	 */
	public void updateSeq(BaseSeq seq) throws Exception {
		
		baseSeqDAO.updateSeq(seq);
	}


}