package com.wms3.bms.standard.dao.outbound;


import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;


/**
 * 
 * 描述: 出库作业数据库操作DAO类接口
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IOutboundJobDao extends IDao {

	/**
	 * 功能:根据作业id查询作业
	 * @param jobid			作业号
	 * @return 
	 * @throws Exception
	 */
	public InoutJob getJobByJobid(String jobid) throws Exception;
	
	/**
	 * 功能:修改作业明细
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsStorage		库存列表
	 * @return 
	 * @throws Exception
	 */
	public void cancelJob(InoutJob job, BaseCargospace cargospace, List lsStorage) throws Exception;
	/**
	 * 功能:作废作业
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsStorage		库存列表
	 * @return 
	 * @throws Exception
	 */
	public void ZFJob(InoutJob job, BaseCargospace cargospace, List lsStorage,StringBuilder detailBuilder) throws Exception;
	/**
	 * 功能:作废暂存作业
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsStorage		库存列表
	 * @return 
	 * @throws Exception
	 */
	public void ZCJob(InoutJob job, BaseCargospace cargospace, InventoryStorage lsStorage,StringBuilder detailBuilder) throws Exception;

	/**
	 * 功能:手动完成作业，添加库存,修改货位状态满空货位
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsJobDetail	作业详细的列表
	 * @param lsStorage		库存的列表
	 * @return 
	 * @throws Exception
	 */
	public void finishJob(InoutJob job,ScheduleTask task, BaseCargospace cargospace, List lsjobdetail, List lsStorage,List<OutboundInvoiceDetail> outBoundls) throws Exception;
	/**
	 * 功能:手动完成作业，添加库存,修改货位状态满空货位
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsJobDetail	作业详细的列表
	 * @param lsStorage		库存的列表
	 * @return 
	 * @throws Exception
	 */
	public void finishJobupdate(InoutJob job, BaseCargospace cargospace, List lsjobdetail, List lsStorage,List<OutboundInvoiceDetail> outBoundls) throws Exception;
	/**
	 * 功能:手动完成作业，添加库存,修改货位状态满空货位
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsJobDetail	作业详细的列表
	 * @param lsStorage		库存的列表
	 * @return 
	 * @throws Exception
	 */
	public void finishJobZC(InoutJob job, BaseCargospace cargospace, List lsjobdetail, List  lsStorage1,List lsStorage2,List<OutboundInvoiceDetail> outBoundls) throws Exception;
	/**
	 * 功能:手动完成作业，更新库存,修改货位状态为空货位,修改调度状态
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsJobDetail	作业详细的列表
	 * @param lsStorage		库存的列表
	 * @param lstask		调度的列表
	 * @return 
	 * @throws Exception
	 */
	public void finishJobupdatez(InoutJob job, BaseCargospace cargospace, List lsjobdetail,ScheduleTask task, List lsStorage,List<OutboundInvoiceDetail> outBoundls) throws Exception;

}
