package com.wms3.bms.standard.business.outbound;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;

/**
 * 
 * 描述: 出库作业管理接口类
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IOutBoundJobBus {

	/**
	 * 功能:根据条件查询出库作业
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param productid		品名
	 * @param customerid	客户
	 * @param indate		作业日期
	 * @param shiftid		班次
	 * @param jobid			作业号
	 * @param status		作业状态
	 * @param tray_code		托盘条码
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getOutboundJobs(String warehouseid, String Virtualwhid, String whAreaId, String productid, String customerid, String indate, String shiftid, 
			String jobid, String status, String taskid, String traycode,  String strUrl, int maxLine) throws Exception;

	/**
	 * 功能:根据作业id查询作业明细
	 * @param jobid			作业号
	 * @return 
	 * @throws Exception
	 */
	public List getJobDetails(String jobid) throws Exception;
	
	/**
	 * 功能:作废作业（作业管理）
	 * @param jobids		作业ID(可复数个)
	 * @param strUserCode	用户
	 * @return 
	 * @throws Exception
	 */
	public String cancelJobs(String jobids, String strUserCode) throws Exception;

	/**
	 * 功能:手动完成作业（作业管理）
	 * @param jobids		作业ID(可复数个)
	 * @param strUserCode	用户
	 * @return 
	 * @throws Exception
	 */
	public String finishJobs(String jobids, String strUserCode) throws Exception;

	/**
	 * 功能:设定优先级别
	 * @param jobids		作业ID(可复数个)
	 * @param priority		优先级别
	 * @param strUserCode	用户
	 * @return 
	 * @throws Exception
	 */
	public String updJobStatus(String jobids, String priority, String strUserCode) throws Exception;

	/**
	 * 功能:根据条件查询入库作业明细
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param alleyId		巷道
	 * @param cargospaceid	货位
	 * @param onlinetype	入库模式
	 * @param indate_from	作业日期
	 * @param indate_to		作业日期
	 * @param shiftid		班次
	 * @param productid		品名
	 * @param customerid	客户
	 * @param tray_code		托盘条码
	 * @param lotid			批次id
     * @param lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, lotatt6      批次属性
     * @param lotatt7, lotatt8, lotatt9, lotatt10, lotatt11, lotatt12   批次属性
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getOutboundJobDetails(String warehouseid, String whAreaId, String alleyId, String cargospaceid, String onlinetype, 
			String indate_from, String indate_to, String shiftid, String productid, String customerid, String traycode, 
			String lotid, String lotatt1, String lotatt2, String lotatt3, String lotatt4, String lotatt5, String lotatt6, String lotatt7, 
			String lotatt8, String lotatt9, String lotatt10, String lotatt11, String lotatt12, String strUrl, int maxLine) throws Exception;
	
	/**
	 * 功能:根据单据明细id查询作业明细
	 * @param outstockdetailid			单据明细id
	 * @return 
	 * @throws Exception
	 */
	public List getJobDetailsAndJobByOutboundDid(String outstockdetailid) throws Exception;
	
}
