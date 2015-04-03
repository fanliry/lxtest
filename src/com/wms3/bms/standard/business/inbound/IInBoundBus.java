package com.wms3.bms.standard.business.inbound;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;

/**
 * 
 * 描述: 入库单管理接口类
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IInBoundBus {
	
	//入库单生成任务锁(不能同时生成入库单据，每次只能一个进程生成入库单据)
    public static byte[] newin_lock = new byte[0]; 
    
	/**
	 * 功能:查询新建入库单的作业集
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param indate		日期
	 * @param shiftid		班次
	 * @param owner_id		货主
	 * @param isinvoice		是否开单
	 * @param lsLot 		新建入库单对应的批次属性设置集
	 * @return 
	 * @throws Exception
	 */
	public List getInboundJobs(String warehouseid, String whAreaId, String indate, String shiftid, String owner_id, String isinvoice, 
			List<BaseLotSet> lsLot) throws Exception;
	/**
	 * 功能：根据订单详细id获得订单
	 * @author yao 2015-3-10
	 * @param poid
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> getJobInfoByPodetailid(String instockdetailid) throws Exception;

	/**
	 * 功能:新建入库单->查询明细
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param indate		日期
	 * @param shiftid		班次
	 * @param isinvoice		是否开单
	 * @param strKey		明细key
	 * @param lsLot 		新建入库单对应的批次属性设置集 
	 * @return 
	 * @throws Exception
	 */
	public List getInboundJobDetails(String warehouseid, String whAreaId, String indate, String shiftid, String isinvoice, String strKey, 
			List<BaseLotSet> lsLot) throws Exception;

	/**
	 * 功能:新建入库单
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param indate		日期
	 * @param shiftid		班次
	 * @param strKey		明细key
	 * @param lsLot 		新建入库单对应的批次属性设置集 
	 * @param strUserCode 	用户id  
	 * @return 
	 * @throws Exception
	 */
	public String addInboundInvoice(String warehouseid, String whAreaId, String indate, String shiftid, String strKey, 
			List<BaseLotSet> lsLot, String strUserCode) throws Exception;

	/**
	 * 功能:入库单管理->查询入库单列表
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param indate_from	日期
	 * @param indate_to		日期
	 * @param shiftid		班次
	 * @param owner_id		货主
	 * @param instatus		单据状态
	 * @param intype		入库类型
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getInbounds(String warehouseid, String whAreaId, String indate_from, String indate_to, String shiftid, String owner_id, 
			String instatus, String intype, String strUrl, int maxLine) throws Exception;

	/**
	 * 功能:根据入库单据编号查询入库单详细列表
	 * @param instockid	入库单据编号
	 * @return 
	 * @throws Exception
	 */
	public List getInboundDetails(String instockid) throws Exception;
    
	/**
	 * 功能:根据入库单据编号查询入库单
	 * @param instockid	入库单据编号
	 * @return 
	 * @throws Exception
	 */
	public InboundHeader getInboundInvoice(String instockid) throws Exception;

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
	
	/**
	 * 生成回流入库作业
	 * @param inventoryId
	 * @throws Exception
	 */
	public String addHLRKJob(String inventoryId,String userCode,String getnum) throws Exception;
	/**
	 * 生成回流入库作业(指定货位，生成调度)
	 * @param inventoryId
	 * @throws Exception
	 */
	public String addHLRKJobplus(String inventoryId,String Virtualwhid,String sjtraycode,String userCode,String getnum,String platoon,String column,String floor) throws Exception;
}
