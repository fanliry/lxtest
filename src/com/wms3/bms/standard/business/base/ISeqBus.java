package com.wms3.bms.standard.business.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseSeq;

/**
 * 描述: 序列号业务接口
 * @author fangjie
 * 
 */
public interface ISeqBus {

	/**
	 * 功能:根据条件查询序列号
	 * @param remark		描述
	 * @return
	 * @throws Exception
	 */
	public List getSeqQuery(String remark) throws Exception;

	/**
	 * 功能:根据序列号ID获得序列号
	 * @param seqId	序列号ID
	 * @return
	 * @throws Exception
	 */
	public BaseSeq getSeqById(String seqId) throws Exception;
	

	/**
	 * 功能:修改序列号
	 * @param seq	序列号
	 * @throws Exception
	 */
	public void updateSeq(BaseSeq seq) throws Exception;

	
}
