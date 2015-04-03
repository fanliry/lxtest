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
 * 描述: 拣货业务类
 * @author hug 2012-9-19
 * @since WMS 3.0
 */
public class PickBusImpl implements IPickBus {
    /** 作业DAO类 */
    protected IJobDao jobDAO;
    /** 分配DAO类 */
    protected IAssginDao assginDao;
    /** 拣货DAO类 */
    protected IPickDao pickDao;
    public PickBusImpl(EntityDAO dao){
        jobDAO = new JobDaoImpl(dao);
        assginDao = new AssginDaoImpl(dao);
        pickDao = new PickDaoImpl(dao);
    }
    /**
     * 根据出库单ID获得未拣货作业的查询SQL语句
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IPickBus#getPickJobQuerySQL(java.lang.String)
     */
    public String getPickJobQuerySQL(String strInvoiceId) {
        String strSql = "select outjob,jobdetail from InoutJob as outjob , InoutJobdetail as jobdetail where jobdetail.linestatus='0' and outjob.inOutType='2' and outjob.jobid=jobdetail.jobid and jobdetail.invoiceid='" + strInvoiceId + "' order by outjob.status";
        return strSql;
    }
    /**
     * 根据出库单ID获得未拣货作业的查询总记录的SQL语句
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
     * 根据出库单和出库单详细ID查询出库作业
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IAssginBus#getPickJob(java.lang.String, java.lang.String)
     */
    public List getPickJob(String invoiceid, String invoicedetailid) throws Exception {
        return jobDAO.getPickJob(invoiceid, invoicedetailid);
    }
    /**
     * 根据出库单和出库单详细ID查询出库作业详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IAssginBus#getPickJobDetail(java.lang.String, java.lang.String)
     */
    public List getPickJobDetail(String invoiceid, String invoicedetailid) throws Exception {  
        return jobDAO.getPickJobDetail(invoiceid, invoicedetailid);
    }
    /**
     * 执行拣货
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IPickBus#executePick(java.lang.String, java.lang.String, java.lang.String)
     */
    public String executePick(String invoiceid, String jobdetailid, String strUserCode) throws Exception {
        String strMsg = "操作成功!";
        synchronized (jobdetailid) {
            //作业详细
            InoutJobdetail jobDetail = jobDAO.getJobDetailByDetailId(jobdetailid);
            if(jobDetail != null && jobDetail.getLinestatus().equals("0")){     //状态0:新建 4.完成 5.作废
                
                //1: 修改作业详细状态
                String jobdetailSQL = "update InoutJobdetail set linestatus='4' where jobdetailid='" + jobdetailid + "'";
                //2: 修改出库单详细的拣货数量  (还要判断拣货是否完成)  分配的数量与拣货数量
                StringBuilder invoiceBudSQL= new StringBuilder();   
                invoiceBudSQL.append("update OutboundInvoiceDetail set linestatus='3'")
                .append(" , picknum=picknum+").append(jobDetail.getAssignnum())
                .append(" , pickweight=pickweight+").append(jobDetail.getAssignweight())
                .append(" , picknetweight=picknetweight+").append(jobDetail.getAssignnetweight())
                .append(" , pickvolume=pickvolume+").append(jobDetail.getAssignvolume());
              //  .append(" where outstockdetailid='").append(jobDetail.getInvoicedetailid()).append("'");           
                //3: 修改库存的分配数量和实际数量( 为0则删除库存)
                StringBuilder storageBudSQL= new StringBuilder();
                InventoryStorage storage = assginDao.getInventoryStorageById(jobDetail.getInventoryid());
                if(storage != null){
                    if(storage.getPnum() == jobDetail.getAssignnum() && storage.getHoldnum() == 0){
                        //删除库存
                        storageBudSQL.append("delete from InventoryStorage");
                    }else{
                        //修改库存
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
                    Logger.error("作业详细【" + jobdetailid + "】所对应的库存【" + jobDetail.getInventoryid() + "】不存在！拣货了无法删除库存！") ;
                }
                //修改保存到数据库*****************************
                pickDao.executePick(jobdetailSQL, invoiceBudSQL.toString(), storageBudSQL.toString());
                //1:检测出库单详细是否已完全拣货
                /*if(pickDao.isPickInvoiceDetailFinish(jobDetail.getInvoicedetailid()))
                {
                    //修改单据详细的拣货状态
                    String strSQL1 = "update OutboundInvoiceDetail set linestatus='4' where outstockdetailid='" + jobDetail.getInvoicedetailid() + "'";
                    pickDao.excuteSQL(strSQL1);
                }
                //2:检测作业详细是否已全部拣货完成
                if(pickDao.isPickJobFinish(jobDetail.getJobid())){
                    //修改作业的拣货状态
                    String strSQL2 = "update InoutJob set status='4' where jobid='" + jobDetail.getJobid() + "'";
                    pickDao.excuteSQL(strSQL2);
                }
                //3:检测出库单是否已完全拣货
                if(pickDao.isPickInvoiceFinish(jobDetail.getInvoiceid())){
                    //修改单据的拣货状态
                    String strSQL3 = "update OutboundInvoiceHeader set outstatus='4' where outstockid='" + jobDetail.getInvoiceid() + "'";
                    pickDao.excuteSQL(strSQL3);
                }*/
                
            }else{
                strMsg = "作业详细【" + jobdetailid + "】的状态【" + jobDetail.getLinestatus() + "】不为新建0,无法拣货！";
            }
        }
        return strMsg;
    }
}
