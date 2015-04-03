package com.wms3.bms.standard.business.inbound;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 
 * 描述: 入库
 * 作业管理接口类
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IInBoundJobBus {

	/**
	 * 功能:根据条件查询入库作业
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param alleyId		巷道
	 * @param indate		作业日期
	 * @param jobid			作业号
	 * @param invoicetype	作业来源
	 * @param status		作业状态
	 * @param shiftid		班次
	 * @param productid		品名
	 * @param ownerid		货主
	 * @param tray_code		托盘条码
	 * @param isback		是否回流
	 * @param sortflg		排序标志
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getInboundJobs(String warehouseid, String whAreaId, String alleyId, String indate, String jobid, String invoicetype, 
			String status, String shiftid, String productid, String ownerid, String traycode, String isback, String sortflg,  
			String strUrl, int maxLine) throws Exception;

	/**
	 * 功能:根据作业id查询作业明细
	 * @param jobid			作业号
	 * @return 
	 * @throws Exception
	 */
	public List getJobDetails(String jobid) throws Exception;

	/**
	 * 功能:生成作业和作业详细
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param alleyId		巷道
	 * @param cargospaceid	货位ID
	 * @param intype		入库类型
	 * @param tray_code		托盘条码
	 * @param indate		作业日期
	 * @param shiftid		班次
	 * @param jobdetails	作业明细（多条，用分隔符分隔开）
	 * @param strUserCode	用户id
	 * @return 
	 * @throws Exception
	 */
	public String addRKWHJob(String warehouseid, String whAreaId, String alleyId, String cargospaceid, String intype, String traycode,  
			String indate, String shiftid, String jobdetails, String strUserCode) throws Exception;

	/**
	 * 功能:根据作业id查询作业
	 * @param jobid			作业号
	 * @return 
	 * @throws Exception
	 */
	public InoutJob getJobByJobid(String jobid) throws Exception;

	/**
	 * 功能:修改作业（入库维护）
	 * @param jobid			作业ID
	 * @param jobtype		作业类型
	 * @param tray_code		托盘条码
	 * @return 
	 * @throws Exception
	 */
	public String updateRKWHJob(String jobid, String jobtype, String traycode) throws Exception;

	/**
	 * 功能:根据作业明细号查询作业明细
	 * @param jobdetailid			作业明细号
	 * @return 
	 * @throws Exception
	 */
	public InoutJobdetail getJobDetailBydetailid(String jobdetailid) throws Exception;

	/**
	 * 功能:修改作业明细（入库维护）
	 * @param jobdetailid	作业明细号
	 * @param key			作业明细内容
	 * @return 
	 * @throws Exception
	 */
	public String updateRKWHJobDetail(String jobdetailid, String key) throws Exception;

	/**
	 * 功能:作废作业（入库维护）
	 * @param jobid			作业ID
	 * @return 
	 * @throws Exception
	 */
	public String cancelRKWHJob(String jobid) throws Exception;

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
	 * @param isback		是否回流
	 * @param invoicetype	作业来源
	 * @param productid		品名
	 * @param ownerid		货主
	 * @param tray_code		托盘条码
	 * @param lotid			批次id
     * @param lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, lotatt6      批次属性
     * @param lotatt7, lotatt8, lotatt9, lotatt10, lotatt11, lotatt12   批次属性
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getInboundJobDetails(String warehouseid, String whAreaId, String alleyId, String cargospaceid,  
			String onlinetype, String indate_from, String indate_to, String shiftid, String isback, String invoicetype, 
			String productid, String ownerid, String traycode, String lotid, String lotatt1, String lotatt2, String lotatt3,  
			String lotatt4, String lotatt5, String lotatt6, String lotatt7, String lotatt8, String lotatt9, String lotatt10, 
			String lotatt11, String lotatt12, String strUrl, int maxLine) throws Exception;

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
	 * 功能：获取分组后的入库全部作业
	 * @param warehouseid
	 * @param whAreaId
	 * @param alleyId
	 * @param indate_from
	 * @param indate_to
	 * @param invoicetype
	 * @param productid
	 * @param ownerid
	 * @param traycode
	 * @param lotid
	 * @param strUrl
	 * @param boundstockid
	 * @param groupinfo
	 * @param customer_id
	 * @param intype
	 * @return
	 * @throws Exception
	 */
	public List getInboundJobDetailsGroupListByIn(String warehouseid, String whAreaId, String alleyId,String indate_from, String indate_to,
			String invoicetype, String productid, String ownerid, String traycode, String lotid, String strUrl,String boundstockid,String groupinfo,String customer_id,String intype) throws Exception; 
	
	/**
	 *  功能：获取分组后的出库全部作业
	 * @param warehouseid
	 * @param whAreaId
	 * @param alleyId
	 * @param indate_from
	 * @param indate_to
	 * @param invoicetype
	 * @param productid
	 * @param ownerid
	 * @param traycode
	 * @param lotid
	 * @param strUrl
	 * @param boundstockid
	 * @param groupinfo
	 * @param customer_id
	 * @param outtype
	 * @return
	 * @throws Exception
	 */
	public List getInboundJobDetailsGroupListByOut(String warehouseid, String whAreaId, String alleyId,String indate_from, String indate_to,
			String invoicetype, String productid, String ownerid, String traycode, String lotid, String strUrl, String boundstockid,String groupinfo,String customer_id,String outtype) throws Exception ;
}
