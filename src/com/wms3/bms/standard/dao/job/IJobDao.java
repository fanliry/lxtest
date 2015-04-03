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
 * ����: ��ҵDAO������ӿ�
 * @author hug 2012-9-4
 * @since WMS 3.0
 */
public interface IJobDao  extends IDao  {
      
    /**
     * ���ܣ�������ҵID�����ҵ
     * @author hug 2012-3-19 
     * @param strJobId  ��ҵID
     * @return
     * @throws Exception
     */
    public InoutJob getJobById(String strJobId) throws Exception;
    /**
     * ���ݵ�������ID�����ҵ
     * (non-Javadoc)
     */
    public InoutJob getJobByTaskId(String taskid) throws Exception;
    
    /**
     * ���ܣ�������ҵID�����ҵ��ϸ�б�
     * @author hug 2012-3-14 
     * @param strJobId      ��ҵID
     * @return
     * @throws Exception
     */
    public List<InoutJobdetail> getJobDetailByJobId(String strJobId) throws Exception;
    
    /**
     * ����:������ҵ��ϸID�����ҵ��ϸ
     * @author hug 2012-10-9 
     * @param strJobDetailId    ��ҵ��ϸID
     * @return
     * @throws Exception
     */
    public InoutJobdetail getJobDetailByDetailId(String strJobDetailId) throws Exception;
    /**
     * ������ҵ��ϸID�����ҵ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#getJobByDetailId(java.lang.String)
     */
    public InoutJob getJobByDetailId(String strJobDetailId) throws Exception;
    
    /**
     * ����:������ҵ��ֻ��һ����ҵ��ϸ��
     * @author hug 2012-9-4 
     * @param job           ��ҵ
     * @param jobDetail     ��ҵ��ϸ
     * @param task          ��������
     * @throws Exception
     */
    public void addOneJob(InoutJob job, InoutJobdetail jobDetail, ScheduleTask task) throws Exception;
    
    /**
     * ����: ���ݳ��ⵥ�ͳ��ⵥ��ϸID��ѯ������ҵ
     * @author hug 2012-9-27 
     * @param invoiceid         ���ⵥID 
     * @param invoicedetailid   ���ⵥ��ϸID
     * @return
     * @throws Exception
     */
    public List getPickJob(String invoiceid, String invoicedetailid) throws Exception;
    /**
     * ����: ��ó��ⵥû�����ϵĳ�����ҵ����
     * @author yao 2013-3-25 
     * @param outInvoiceID  ���ⵥID
     * @param outInvoiceDetailID ���ⵥ��ϸID
     * @return
     * @throws Exception
     */
    public int noCancelJob(String outInvoiceID, String outInvoiceDetailID) throws Exception;
    /**
     * ����: ���ݳ��ⵥ�ͳ��ⵥ��ϸID��ѯ������ҵ��ϸ
     * @author hug 2012-9-27 
     * @param invoiceid         ���ⵥID
     * @param invoicedetailid   ���ⵥ��ϸID
     * @return
     * @throws Exception
     */
    public List getPickJobDetail(String invoiceid, String invoicedetailid) throws Exception;
    
    /**
     * ���ܣ����ݵ���ID�򵥾���ϸID��ѯδ��ɵ���ҵ
     * @author hug 2012-5-23 
     * @param strInvoiceId
     * @param strInvoiceDetailId
     * @return
     * @throws Exception
     */
    public int getUnfinishedJob(String strInvoiceId, String strInvoiceDetailId) throws Exception;
    /**
     * ���ݵ���ID�򵥾���ϸID��ѯδ���˵���ҵ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#getUncheckUpJob(java.lang.String, java.lang.String)
     */
    public int getUncheckUpJob(String strInvoiceId, String strInvoiceDetailId) throws Exception;
    
    /**
     * ����:���ݳ��ⵥ��ѯ����ʣ�����ҵ��ϸ��Ϣ
     * @author hug 2012-5-24 
     * @param strInvoiceId          ����ID
     * @param strInvoiceDetailId    ������ϸID
     * @return
     * @throws Exception
     */
    public List getRemainJobDetail(String strInvoiceId, String strInvoiceDetailId) throws Exception;
    
    /**
     * ���ܣ�������ŵ�����ϸ����ɵ�����
     * @author hug 2012-5-24 
     * @param strInvoiceDetailId    ������ϸID
     * @return
     * @throws Exception
     */
    public Object[] getInvoiceDetailFinishNum(String strInvoiceDetailId) throws Exception;
    /**
     * ����:���ݵ���ID�͵�����ϸID�����ҵ����ҵ��ϸ
     * @author hug 2012-10-10 
     * @param invoiceid         ����ID
     * @param invoicedetailid   ������ϸID
     * @return
     * @throws Exception
     */
    public List getJobAndJobDetail(String invoiceid, String invoicedetailid) throws Exception;
    
    
    
    public String getJobDetailByTraycode(String traycode,String warehouseID) throws Exception;
    
    
    public String GetZCHLInventoryByTraycode(String traycode,String warehouseID) throws Exception;
    
    public String GetJobInfoByTraycode(String traycode) throws Exception;
    /**
     * ���ܣ����ݵ���Id��ȡ��ҵ����ϸ
     * @param invoiceid
     * @return
     * @throws Exception
     */
    public List getJobDetailByInvoiceid(String invoiceid) throws Exception;
    
    /**
     *  ���ܣ�RF������ҵ����ӵ���
     */
	public String addRFJobz(InoutJob job, InoutJobdetail jobdetail, ScheduleTask task, BaseCargospace space, EntityDAO dao);
	
    /**
     * ������ⵥ����ϸ���޸���ҵ��
     */
    public String addInBoundupdateJob( List<InboundHeader> lsInboundHeader, List<InboundDetail> lsInboundDetail, List<InoutJob> lsjob, List<InoutJobdetail> lsjobD, EntityDAO dao) throws Exception;
    
}
