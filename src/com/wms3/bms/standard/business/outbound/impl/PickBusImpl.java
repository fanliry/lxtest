package com.wms3.bms.standard.business.outbound.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.business.outbound.IPickBus;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.dao.outbound.IAssginDao;
import com.wms3.bms.standard.dao.outbound.IPickDao;
import com.wms3.bms.standard.dao.outbound.impl.AssginDaoImpl;
import com.wms3.bms.standard.dao.outbound.impl.PickDaoImpl;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * ����: ���ҵ����
 * @author hug 2012-9-19
 * @since WMS 3.0
 */
public class PickBusImpl implements IPickBus {
    /** ��ҵDAO�� */
    protected IJobDao jobDAO;
    /** ����DAO�� */
    protected IAssginDao assginDao;
    /** ���DAO�� */
    protected IPickDao pickDao;
    public PickBusImpl(EntityDAO dao){
        jobDAO = new JobDaoImpl(dao);
        assginDao = new AssginDaoImpl(dao);
        pickDao = new PickDaoImpl(dao);
    }
    /**
     * ���ݳ��ⵥID���δ�����ҵ�Ĳ�ѯSQL���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IPickBus#getPickJobQuerySQL(java.lang.String)
     */
    public String getPickJobQuerySQL(String strInvoiceId) {
        String strSql = "select outjob,jobdetail from InoutJob as outjob , InoutJobdetail as jobdetail where jobdetail.linestatus='0' and outjob.inOutType='2' and outjob.jobid=jobdetail.jobid and jobdetail.invoiceid='" + strInvoiceId + "' order by outjob.status";
        return strSql;
    }
    /**
     * ���ݳ��ⵥID���δ�����ҵ�Ĳ�ѯ�ܼ�¼��SQL���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IPickBus#getPickJobCountSQL(java.lang.String)
     */
    public String getPickJobCountSQL(String strInvoiceId) {
        String strSql = "select count(jobdetail.jobdetailid) from InoutJob as outjob , InoutJobdetail as jobdetail where jobdetail.linestatus='0' and  outjob.inOutType='2' and outjob.jobid=jobdetail.jobid and jobdetail.invoiceid='" + strInvoiceId + "'";
        return strSql;
    }
    public List<InoutJobdetail> getJobDetailsById(String strJobId) throws Exception {
        return jobDAO.getJobDetailByJobId(strJobId);
    }
    /**
     * ���ݳ��ⵥ�ͳ��ⵥ��ϸID��ѯ������ҵ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IAssginBus#getPickJob(java.lang.String, java.lang.String)
     */
    public List getPickJob(String invoiceid, String invoicedetailid) throws Exception {
        return jobDAO.getPickJob(invoiceid, invoicedetailid);
    }
    /**
     * ���ݳ��ⵥ�ͳ��ⵥ��ϸID��ѯ������ҵ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IAssginBus#getPickJobDetail(java.lang.String, java.lang.String)
     */
    public List getPickJobDetail(String invoiceid, String invoicedetailid) throws Exception {  
        return jobDAO.getPickJobDetail(invoiceid, invoicedetailid);
    }
    /**
     * ִ�м��
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IPickBus#executePick(java.lang.String, java.lang.String, java.lang.String)
     */
    public String executePick(String invoiceid, String jobdetailid, String strUserCode) throws Exception {
        String strMsg = "�����ɹ�!";
        synchronized (jobdetailid) {
            //��ҵ��ϸ
            InoutJobdetail jobDetail = jobDAO.getJobDetailByDetailId(jobdetailid);
            if(jobDetail != null && jobDetail.getLinestatus().equals("0")){     //״̬0:�½� 4.��� 5.����
                
                //1: �޸���ҵ��ϸ״̬
                String jobdetailSQL = "update InoutJobdetail set linestatus='4' where jobdetailid='" + jobdetailid + "'";
                //2: �޸ĳ��ⵥ��ϸ�ļ������  (��Ҫ�жϼ���Ƿ����)  �����������������
                StringBuilder invoiceBudSQL= new StringBuilder();   
                invoiceBudSQL.append("update OutboundInvoiceDetail set linestatus='3'")
                .append(" , picknum=picknum+").append(jobDetail.getAssignnum())
                .append(" , pickweight=pickweight+").append(jobDetail.getAssignweight())
                .append(" , picknetweight=picknetweight+").append(jobDetail.getAssignnetweight())
                .append(" , pickvolume=pickvolume+").append(jobDetail.getAssignvolume());
              //  .append(" where outstockdetailid='").append(jobDetail.getInvoicedetailid()).append("'");           
                //3: �޸Ŀ��ķ���������ʵ������( Ϊ0��ɾ�����)
                StringBuilder storageBudSQL= new StringBuilder();
                InventoryStorage storage = assginDao.getInventoryStorageById(jobDetail.getInventoryid());
                if(storage != null){
                    if(storage.getPnum() == jobDetail.getAssignnum() && storage.getHoldnum() == 0){
                        //ɾ�����
                        storageBudSQL.append("delete from InventoryStorage");
                    }else{
                        //�޸Ŀ��
                        storageBudSQL.append("update InventoryStorage set  ")
                        .append(" assignnum=assignnum-" + jobDetail.getAssignnum())
                        .append(" , assignweight=assignweight-" + jobDetail.getAssignweight())
                        .append(" , assignnetweight=assignnetweight-" + jobDetail.getAssignnetweight())
                        .append(" , assignvolume=assignvolume-" + jobDetail.getAssignvolume())
                        .append(" , pnum=pnum-" + jobDetail.getAssignnum())
                        .append(" , pweight=pweight-" + jobDetail.getAssignweight())
                        .append(" , pnetweight=pnetweight-" + jobDetail.getAssignnetweight())
                        .append(" , pvolume=pvolume-" + jobDetail.getAssignvolume());
                    }
                    storageBudSQL.append("  where inventoryid='").append(jobDetail.getInventoryid()).append("'");
                }else{
                    Logger.error("��ҵ��ϸ��" + jobdetailid + "������Ӧ�Ŀ�桾" + jobDetail.getInventoryid() + "�������ڣ�������޷�ɾ����棡") ;
                }
                //�޸ı��浽���ݿ�*****************************
                pickDao.executePick(jobdetailSQL, invoiceBudSQL.toString(), storageBudSQL.toString());
                //1:�����ⵥ��ϸ�Ƿ�����ȫ���
                /*if(pickDao.isPickInvoiceDetailFinish(jobDetail.getInvoicedetailid()))
                {
                    //�޸ĵ�����ϸ�ļ��״̬
                    String strSQL1 = "update OutboundInvoiceDetail set linestatus='4' where outstockdetailid='" + jobDetail.getInvoicedetailid() + "'";
                    pickDao.excuteSQL(strSQL1);
                }
                //2:�����ҵ��ϸ�Ƿ���ȫ��������
                if(pickDao.isPickJobFinish(jobDetail.getJobid())){
                    //�޸���ҵ�ļ��״̬
                    String strSQL2 = "update InoutJob set status='4' where jobid='" + jobDetail.getJobid() + "'";
                    pickDao.excuteSQL(strSQL2);
                }
                //3:�����ⵥ�Ƿ�����ȫ���
                if(pickDao.isPickInvoiceFinish(jobDetail.getInvoiceid())){
                    //�޸ĵ��ݵļ��״̬
                    String strSQL3 = "update OutboundInvoiceHeader set outstatus='4' where outstockid='" + jobDetail.getInvoiceid() + "'";
                    pickDao.excuteSQL(strSQL3);
                }*/
                
            }else{
                strMsg = "��ҵ��ϸ��" + jobdetailid + "����״̬��" + jobDetail.getLinestatus() + "����Ϊ�½�0,�޷������";
            }
        }
        return strMsg;
    }
}
