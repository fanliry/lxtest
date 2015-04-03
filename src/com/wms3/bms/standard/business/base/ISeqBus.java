package com.wms3.bms.standard.business.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseSeq;

/**
 * ����: ���к�ҵ��ӿ�
 * @author fangjie
 * 
 */
public interface ISeqBus {

	/**
	 * ����:����������ѯ���к�
	 * @param remark		����
	 * @return
	 * @throws Exception
	 */
	public List getSeqQuery(String remark) throws Exception;

	/**
	 * ����:�������к�ID������к�
	 * @param seqId	���к�ID
	 * @return
	 * @throws Exception
	 */
	public BaseSeq getSeqById(String seqId) throws Exception;
	

	/**
	 * ����:�޸����к�
	 * @param seq	���к�
	 * @throws Exception
	 */
	public void updateSeq(BaseSeq seq) throws Exception;

	
}
