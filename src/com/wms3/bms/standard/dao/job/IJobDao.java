package com.wms3.bms.standard.dao.job;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * 描述: 作业DAO类操作接口
 * @author hug 2012-9-4
 * @since WMS 3.0
 */
public interface IJobDao  extends IDao  {
      
    /**
     * 功能：根据作业ID获得作业
     * @author hug 2012-3-19 
     * @param strJobId  作业ID
     * @return
     * @throws Exception
     */
    public InoutJob getJobById(String strJobId) throws Exception;
    /**
     * 根据调度任务ID获得作业
     * (non-Javadoc)
     */
    public InoutJob getJobByTaskId(String taskid) throws Exception;
    
    /**
     * 功能：根据作业ID获得作业详细列表
     * @author hug 2012-3-14 
     * @param strJobId      作业ID
     * @return
     * @throws Exception
     */
    public List<InoutJobdetail> getJobDetailByJobId(String strJobId) throws Exception;
    
    /**
     * 功能:根据作业详细ID获得作业详细
     * @author hug 2012-10-9 
     * @param strJobDetailId    作业详细ID
     * @return
     * @throws Exception
     */
    public InoutJobdetail getJobDetailByDetailId(String strJobDetailId) throws Exception;
    /**
     * 根据作业详细ID获得作业
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#getJobByDetailId(java.lang.String)
     */
    public InoutJob getJobByDetailId(String strJobDetailId) throws Exception;
    
    /**
     * 功能:增加作业（只有一条作业详细）
     * @author hug 2012-9-4 
     * @param job           作业
     * @param jobDetail     作业详细
     * @param task          调度任务
     * @throws Exception
     */
    public void addOneJob(InoutJob job, InoutJobdetail jobDetail, ScheduleTask task) throws Exception;
    
    /**
     * 功能: 根据出库单和出库单详细ID查询出库作业
     * @author hug 2012-9-27 
     * @param invoiceid         出库单ID 
     * @param invoicedetailid   出库单详细ID
     * @return
     * @throws Exception
     */
    public List getPickJob(String invoiceid, String invoicedetailid) throws Exception;
    /**
     * 功能: 获得出库单没有作废的出库作业数量
     * @author yao 2013-3-25 
     * @param outInvoiceID  出库单ID
     * @param outInvoiceDetailID 出库单详细ID
     * @return
     * @throws Exception
     */
    public int noCancelJob(String outInvoiceID, String outInvoiceDetailID) throws Exception;
    /**
     * 功能: 根据出库单和出库单详细ID查询出库作业详细
     * @author hug 2012-9-27 
     * @param invoiceid         出库单ID
     * @param invoicedetailid   出库单详细ID
     * @return
     * @throws Exception
     */
    public List getPickJobDetail(String invoiceid, String invoicedetailid) throws Exception;
    
    /**
     * 功能：根据单据ID或单据详细ID查询未完成的作业
     * @author hug 2012-5-23 
     * @param strInvoiceId
     * @param strInvoiceDetailId
     * @return
     * @throws Exception
     */
    public int getUnfinishedJob(String strInvoiceId, String strInvoiceDetailId) throws Exception;
    /**
     * 根据单据ID或单据详细ID查询未复核的作业
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#getUncheckUpJob(java.lang.String, java.lang.String)
     */
    public int getUncheckUpJob(String strInvoiceId, String strInvoiceDetailId) throws Exception;
    
    /**
     * 功能:根据出库单查询发货剩余的作业详细信息
     * @author hug 2012-5-24 
     * @param strInvoiceId          单据ID
     * @param strInvoiceDetailId    单据详细ID
     * @return
     * @throws Exception
     */
    public List getRemainJobDetail(String strInvoiceId, String strInvoiceDetailId) throws Exception;
    
    /**
     * 功能：获得这张单据详细所完成的数量
     * @author hug 2012-5-24 
     * @param strInvoiceDetailId    单据详细ID
     * @return
     * @throws Exception
     */
    public Object[] getInvoiceDetailFinishNum(String strInvoiceDetailId) throws Exception;
    /**
     * 功能:根据单据ID和单据详细ID获得作业及作业详细
     * @author hug 2012-10-10 
     * @param invoiceid         单据ID
     * @param invoicedetailid   单据详细ID
     * @return
     * @throws Exception
     */
    public List getJobAndJobDetail(String invoiceid, String invoicedetailid) throws Exception;
    
    
    
    public String getJobDetailByTraycode(String traycode,String warehouseID) throws Exception;
    
    
    public String GetZCHLInventoryByTraycode(String traycode,String warehouseID) throws Exception;
    
    public String GetJobInfoByTraycode(String traycode) throws Exception;
    /**
     * 功能：根据单据Id获取作业及详细
     * @param invoiceid
     * @return
     * @throws Exception
     */
    public List getJobDetailByInvoiceid(String invoiceid) throws Exception;
    
    /**
     *  功能：RF生成作业，添加调度
     */
	public String addRFJobz(InoutJob job, InoutJobdetail jobdetail, ScheduleTask task, BaseCargospace space, EntityDAO dao);
	
    /**
     * 生成入库单和详细，修改作业表
     */
    public String addInBoundupdateJob( List<InboundHeader> lsInboundHeader, List<InboundDetail> lsInboundDetail, List<InoutJob> lsjob, List<InoutJobdetail> lsjobD, EntityDAO dao) throws Exception;
    
}
