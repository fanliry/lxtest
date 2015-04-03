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
 * ����: �ϼ�ҵ����
 * @author hug 2012-8-29
 * @since WMS 3.0
 *//*
public class PutawayBusImpl implements IPutawayBus {
     
    
    *//** �ϼܲ���DAO�� *//*
    protected IPutawayDao putawayDao;
    *//** ��ҵDAO�� *//*
    protected IJobDao jobDAO;
    *//** �ջ����ݲ���DAO��  *//*
    protected IInboundReceiptDao inReceiptDao;
    *//** ����ϼ�����DAO�� *//*
    protected AllotServiceBusImpl allot;
    *//** DAO�� *//*
    protected EntityDAO daoE;
    *//**
     *  �ð�װһ���̿ɷŶ�������
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IPutawayBus#getOneTrayNumber(java.lang.String)
     *//*
    public int getOneTrayNumber(String packid) throws Exception{
        return putawayDao.getPackageTrayNum(packid);
    }
    *//**
     * ȡ���ϼ�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IPutawayBus#cancelPutaway(java.lang.String)
     *//*
    public String cancelPutaway(String strJobId) throws Exception {
        String strMsg = "ȡ���ϼܳɹ���";
        synchronized (strJobId) {
            //�����ҵ
            InoutJob job = jobDAO.getJobById(strJobId);
            if(job != null){
                if(job.getStatus().equals("1")){//��ҵ״̬Ϊδִ��״̬ 
                    List<String> lsTrasnsSQL = new ArrayList<String>(); //�޸��ջ���¼��SQL
                    List<String> lsInvoiceSQL = new ArrayList<String>();//�޸��ջ�����״̬��SQL
                    List<String> lsDetailSQL = new ArrayList<String>(); //�޸��ջ�����ϸ��״̬��SQL
                    //1:�޸���ҵ״̬
                    String strJobSql = "update InoutJob set status='5' where jobid='" + strJobId + "'";
                    //�����ҵ��ϸ
                    List<InoutJobdetail> lsJobDetail = jobDAO.getJobDetailByJobId(strJobId);
                    if(lsJobDetail != null && lsJobDetail.size()>0){
                        InoutJobdetail jobdetail = null; //��ҵ��ϸ  
                        for(int i = 0; i < lsJobDetail.size(); i++){
                            jobdetail = lsJobDetail.get(i); 
                            
                            //2���޸��ջ���¼��״̬�����ϼ�����
                            String strTransSql = "update InboundReceiptTransaction set transstatus='3', pucnum=pucnum-" + jobdetail.getJobnum() + ", " +
                                                    " puweight=puweight-" + jobdetail.getWeight() + "," +
                                                    " punetweight=punetweight-" + jobdetail.getNetweight() + "," +
                                                    " puvolume=puvolume-" + jobdetail.getVolume() + "  where transreceiptid='" + jobdetail.getTransreceiptid() + "'";
                            lsTrasnsSQL.add(strTransSql);
                            //3���޸��ջ�����״̬
                            String strInvoiceSql = "update InboundReceiptHeader set instatus='1' where reinvoiceid='" + jobdetail.getSinvoiceid() + "'";
                            lsInvoiceSQL.add(strInvoiceSql);
                            //4���޸��ջ�����ϸ��״̬
                            String strDetailSql = "update InboundReceiptDetail set linestatus='1' where InboundReceiptDetail='" + jobdetail.getSinvoicedetailid() + "'";
                            lsDetailSQL.add(strDetailSql);    
                        }
                    }
                    //4: �޸Ļ�λ״̬
                    String strSpaceSQL = "update BaseCargospace space set space.csstatus='" + job.getOldspacestatus() + "', space.haspalletnum=space.haspalletnum-1 where  space.cargoSpaceId='" + job.getTcargoSpaceId() + "'";
                    //ȡ���ϼ�
                    putawayDao.cancelPutaway(strJobSql, lsTrasnsSQL, lsInvoiceSQL, lsDetailSQL, strSpaceSQL);
                }else{
                    strMsg =  "��ҵ[" + strJobId  + "]״̬��Ϊδִ��״̬,�޷��ϼܲ�����";
                }
            }else{
                strMsg = "��ҵ[" + strJobId  + "]�����ڣ�";
            } 
        }
        return strMsg;
    }

}*/