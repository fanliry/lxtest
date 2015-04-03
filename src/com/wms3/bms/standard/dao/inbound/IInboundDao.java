package com.wms3.bms.standard.dao.inbound;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inbound.InboundInvoiceHeader;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;

/**
 * 
 * 描述: 入库单数据库操作DAO类接口
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IInboundDao extends IDao{

    /**
	 * 功能:生成入库单据
	 * @param inInvoice		入库单
	 * @param lsinDetail	入库单明细的集合
	 * @param ids			对应作业明细的id
	 * @return 
	 * @throws Exception
	 */
	public void createInvoice(InboundHeader inInvoice, List<InboundDetail> lsinDetail, String ids) throws Exception;

	/**
	 * 功能:根据入库单据编号查询入库单
	 * @param instockid	入库单据编号
	 * @return 
	 * @throws Exception
	 */
	public InboundHeader getInboundHeader(String instockid) throws Exception;

	/**
	 * 功能:更新入库单
	 * @param instockid	入库单据编号
	 * @return 
	 * @throws Exception
	 */
	public void updateInboundInvoice(InboundHeader inbound) throws Exception;

	/**
	 * 功能:删除入库单
	 * @param instockid	入库单据编号
	 * @return 
	 * @throws Exception
	 */
	public void deleteInboundInvoice(String instockid) throws Exception;

	/**
	 * 功能:根据入库单据编号查询作业的总数量
	 * @param instockdetailid	入库单据详细编号
	 * @return 
	 * @throws Exception
	 */
	public int getJobNumSum(String instockdetailid) throws Exception;

	/**
	 * 功能:作废单据
	 * @param instockid	入库单据编号
	 * @return 
	 * @throws Exception
	 */
	public void cancelInboundInvoice(String instockid) throws Exception;

	/**
	 * 功能:作废详细单据
	 * @param instockdetailid	入库单据详细编号
	 * @return 
	 * @throws Exception
	 */
	public void cancelInboundDetail(String instockdetailid) throws Exception;
    
	public String addHLRKJob(String inventoryId,String strUserCode,String getnum) throws Exception;
	/**
	 * rF回流入库(有指定货位)
	 * 
	 */
	public String addHLRKJobplus(String inventoryId,String Virtualwhid,String sjtraycode,String strUserCode,String getnum,String platoon,String column,String floor) throws Exception;
}
