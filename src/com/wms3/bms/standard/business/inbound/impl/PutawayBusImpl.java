/*package com.wms3.bms.standard.business.inbound.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.allot.impl.AllotServiceBusImpl;
import com.wms3.bms.standard.business.allot.impl.InAllotRequestBean;
import com.wms3.bms.standard.business.allot.impl.InAllotResponseBean;
import com.wms3.bms.standard.business.inbound.IPutawayBus;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.inbound.IInboundReceiptDao;
import com.wms3.bms.standard.dao.inbound.IPutawayDao;
import com.wms3.bms.standard.dao.inbound.impl.InboundReceiptDaoImpl;
import com.wms3.bms.standard.dao.inbound.impl.PutawayDaoImpl;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inbound.InboundReceiptDetail;
import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;
import com.wms3.bms.standard.entity.inventory.fyhz.InventoryStorage;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

*//**
 * 描述: 上架业务类
 * @author hug 2012-8-29
 * @since WMS 3.0
 *//*
public class PutawayBusImpl implements IPutawayBus {
     
    
    *//** 上架操作DAO类 *//*
    protected IPutawayDao putawayDao;
    *//** 作业DAO类 *//*
    protected IJobDao jobDAO;
    *//** 收货单据操作DAO类  *//*
    protected IInboundReceiptDao inReceiptDao;
    *//** 入库上架请求DAO类 *//*
    protected AllotServiceBusImpl allot;
    *//** DAO类 *//*
    protected EntityDAO daoE;
    *//**
     *  该包装一托盘可放多少数量
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IPutawayBus#getOneTrayNumber(java.lang.String)
     *//*
    public int getOneTrayNumber(String packid) throws Exception{
        return putawayDao.getPackageTrayNum(packid);
    }
    *//**
     * 取消上架
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IPutawayBus#cancelPutaway(java.lang.String)
     *//*
    public String cancelPutaway(String strJobId) throws Exception {
        String strMsg = "取消上架成功！";
        synchronized (strJobId) {
            //获得作业
            InoutJob job = jobDAO.getJobById(strJobId);
            if(job != null){
                if(job.getStatus().equals("1")){//作业状态为未执行状态 
                    List<String> lsTrasnsSQL = new ArrayList<String>(); //修改收货记录的SQL
                    List<String> lsInvoiceSQL = new ArrayList<String>();//修改收货单的状态的SQL
                    List<String> lsDetailSQL = new ArrayList<String>(); //修改收货单详细的状态的SQL
                    //1:修改作业状态
                    String strJobSql = "update InoutJob set status='5' where jobid='" + strJobId + "'";
                    //获得作业详细
                    List<InoutJobdetail> lsJobDetail = jobDAO.getJobDetailByJobId(strJobId);
                    if(lsJobDetail != null && lsJobDetail.size()>0){
                        InoutJobdetail jobdetail = null; //作业详细  
                        for(int i = 0; i < lsJobDetail.size(); i++){
                            jobdetail = lsJobDetail.get(i); 
                            
                            //2：修改收货记录的状态，及上架数量
                            String strTransSql = "update InboundReceiptTransaction set transstatus='3', pucnum=pucnum-" + jobdetail.getJobnum() + ", " +
                                                    " puweight=puweight-" + jobdetail.getWeight() + "," +
                                                    " punetweight=punetweight-" + jobdetail.getNetweight() + "," +
                                                    " puvolume=puvolume-" + jobdetail.getVolume() + "  where transreceiptid='" + jobdetail.getTransreceiptid() + "'";
                            lsTrasnsSQL.add(strTransSql);
                            //3：修改收货单的状态
                            String strInvoiceSql = "update InboundReceiptHeader set instatus='1' where reinvoiceid='" + jobdetail.getSinvoiceid() + "'";
                            lsInvoiceSQL.add(strInvoiceSql);
                            //4：修改收货单详细的状态
                            String strDetailSql = "update InboundReceiptDetail set linestatus='1' where InboundReceiptDetail='" + jobdetail.getSinvoicedetailid() + "'";
                            lsDetailSQL.add(strDetailSql);    
                        }
                    }
                    //4: 修改货位状态
                    String strSpaceSQL = "update BaseCargospace space set space.csstatus='" + job.getOldspacestatus() + "', space.haspalletnum=space.haspalletnum-1 where  space.cargoSpaceId='" + job.getTcargoSpaceId() + "'";
                    //取消上架
                    putawayDao.cancelPutaway(strJobSql, lsTrasnsSQL, lsInvoiceSQL, lsDetailSQL, strSpaceSQL);
                }else{
                    strMsg =  "作业[" + strJobId  + "]状态不为未执行状态,无法上架操作！";
                }
            }else{
                strMsg = "作业[" + strJobId  + "]不存在！";
            } 
        }
        return strMsg;
    }

}*/