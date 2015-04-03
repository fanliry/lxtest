package com.wms3.bms.standard.dao.job.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inbound.lxyy.BindingRecord;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * ����: ��ҵDAO�����
 * @author hug 2012-9-4
 * @since WMS 3.0
 */
public class JobDaoImpl extends AbstractDao implements IJobDao{
    public JobDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    /**
     * ������ҵID�����ҵ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#getJobById(java.lang.String)
     */
    public InoutJob getJobById(String strJobId) throws Exception {
        try{
            if(strJobId != null){
                String strSql = "from InoutJob as job where job.jobid='" + strJobId + "'";
                List ls = m_dao.searchEntities(strSql, 0, 1);
                if(ls != null && ls.size() > 0){
                    return (InoutJob)ls.get(0);
                }
            }  
        }catch(Exception er){
            throw new Exception("������ҵID["+ strJobId +"]�����ҵ����" + er.getMessage());
        }
        return null;
    }
    /**
     * ���ݵ�������ID�����ҵ
     * (non-Javadoc)     */
    public InoutJob getJobByTaskId(String taskid) throws Exception {
        try{
            if(taskid != null){
                String strSql = "from InoutJob as job where job.taskid='" + taskid + "'";
                List ls = m_dao.searchEntities(strSql, 0, 1);
                if(ls != null && ls.size() > 0){
                    return (InoutJob)ls.get(0);
                }
            }  
        }catch(Exception er){
            throw new Exception("���ݵ�������ID["+ taskid +"]�����ҵ����" + er.getMessage());
        }
        return null;
    }
    /**
     *  ������ҵID�����ҵ��ϸ�б�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#getJobDetailByJobId(java.lang.String)
     */
    public List<InoutJobdetail> getJobDetailByJobId(String strJobId) throws Exception {
        try{
            if(strJobId != null){
                String strSql = "from InoutJobdetail as jobdetail where jobdetail.jobid='" + strJobId + "'";
                List<InoutJobdetail> ls = m_dao.searchEntities(strSql);
                return ls;
            }  
        }catch(Exception er){
            throw new Exception("������ҵID["+ strJobId +"]�����ҵ��ϸ�б����" + er.getMessage());
        }
        return null;
    }
    /**
     * ������ҵ��ϸID�����ҵ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#getJobDetailByDetailId(java.lang.String)
     */
    public InoutJobdetail getJobDetailByDetailId(String strJobDetailId) throws Exception {
        try{
            if(strJobDetailId != null){
                String strSql = "select jobd from InoutJobdetail as jobd,InoutJob as job where jobd.jobdetailid='" + strJobDetailId + "' and job.jobid=jobd.jobid ";
                List ls = m_dao.searchEntities(strSql);
                if(ls != null && ls.size() > 0){
                    return (InoutJobdetail)ls.get(0);
                }
            }  
        }catch(Exception er){
            throw new Exception("������ҵ��ϸID["+ strJobDetailId +"]�����ҵ��ϸ����" + er.getMessage());
        }
        return null;
    }
    /**
     * ��ó��ⵥû�����ϵĳ�����ҵ����
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#noCancelJob(java.lang.String, java.lang.String)
     */
    public int noCancelJob(String outInvoiceID, String outInvoiceDetailID) throws Exception {
        int iCount = 0;
        try{
            String strSQL = "select count(distinct injob.jobid) from InoutJob as injob, InoutJobdetail as jobdetail where injob.jobid=jobdetail.jobid and   injob.status!='5' and injob.inOutType='2' and injob.boundstockid='" + outInvoiceID + "'";
            if(outInvoiceDetailID != null && outInvoiceDetailID.trim().length() > 0){
                strSQL = strSQL + " and injob.boundstockdetailid='" + outInvoiceDetailID + "'";
            }
            iCount = m_dao.searchEntitieCount(strSQL);
        }catch(Exception er){
            throw new Exception("[JobDaoImpl.noCancelJob]���ݳ��ⵥ["+ outInvoiceID +"]��ó��ⵥû�����ϵĳ�����ҵ��������" + er.getMessage());
        }
        return iCount;
    }
    /**
     * ������ҵ��ϸID�����ҵ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#getJobByDetailId(java.lang.String)
     */
    public InoutJob getJobByDetailId(String strJobDetailId) throws Exception {
        try{
            if(strJobDetailId != null){
                String strSql = "select job from InoutJobdetail as jobd,InoutJob as job where jobd.jobdetailid='" + strJobDetailId + "' and job.jobid=jobd.jobid ";
                List ls = m_dao.searchEntities(strSql);
                if(ls != null && ls.size() > 0){
                    return (InoutJob)ls.get(0);
                }
            }  
        }catch(Exception er){
            throw new Exception("������ҵ��ϸID["+ strJobDetailId +"]�����ҵ����" + er.getMessage());
        }
        return null;
    }
    
    /**
     * ������ҵ��ֻ��һ����ҵ��ϸ��
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#addOneJob(com.wms3.bms.standard.entity.job.InoutJob, com.wms3.bms.standard.entity.job.InoutJobdetail, com.wms3.bms.standard.entity.schedule.ScheduleTask)
     */
    public void addOneJob(InoutJob job, InoutJobdetail jobDetail, ScheduleTask task) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //������ҵ��ϸ
            session.save("InoutJobdetail", jobDetail);
        
            //������ҵ
            session.save("InoutJob", job);
            
            //���ӵ�������
            if(task != null){
                session.save(task); 
            } 
            tx.commit();     
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("������ҵ��ֻ��һ����ҵ��ϸ������"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
    }
    
    /**
     * ���ݳ��ⵥ�ͳ��ⵥ��ϸID��ѯ������ҵ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#getPickJob(java.lang.String, java.lang.String)
     */
    public List getPickJob(String invoiceid, String invoicedetailid) throws Exception {
        try{
            if((invoiceid != null && invoiceid.trim().length() > 0) || (invoicedetailid != null && invoicedetailid.trim().length() > 0)){
                StringBuilder strBud = new StringBuilder(); 
                strBud.append("select distinct(job)  from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid ");
                if(invoiceid != null && invoiceid.trim().length() > 0){
                    strBud.append(" and jobdetail.invoiceid='" + invoiceid + "'");
                }
                if(invoicedetailid != null && invoicedetailid.trim().length() > 0){
                    strBud.append(" and jobdetail.invoicedetailid='" + invoicedetailid + "'");
                }
                strBud.append(" order by job.jobid");
                return m_dao.searchEntities(strBud.toString());
            }
        }catch(Exception er){
            throw new Exception("���ݳ��ⵥ�ͳ��ⵥ��ϸID��ѯ������ҵ����:" + er.getMessage());
        }
        return null;
    }
    /**
     * ���ݳ��ⵥ�ͳ��ⵥ��ϸID��ѯ������ҵ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#getPickJobDetail(java.lang.String, java.lang.String)
     */
    public List getPickJobDetail(String invoiceid, String invoicedetailid) throws Exception {
        try{
            if((invoiceid != null && invoiceid.trim().length() > 0) || (invoicedetailid != null && invoicedetailid.trim().length() > 0))
            {
                if((invoiceid != null && invoiceid.trim().length() > 0) || (invoicedetailid != null && invoicedetailid.trim().length() > 0)){
                    StringBuilder strBud = new StringBuilder(); 
                    strBud.append("select jobdetail  from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid ");
                    if(invoiceid != null && invoiceid.trim().length() > 0){
                        strBud.append(" and jobdetail.invoiceid='" + invoiceid + "'");
                    }
                    if(invoicedetailid != null && invoicedetailid.trim().length() > 0){
                        strBud.append(" and jobdetail.invoicedetailid='" + invoicedetailid + "'");
                    }
                    strBud.append(" order by job.jobid");
                    return m_dao.searchEntities(strBud.toString());
                }
            }
        }catch(Exception er){
            throw new Exception("���ݳ��ⵥ�ͳ��ⵥ��ϸID��ѯ������ҵ��ϸ����:" + er.getMessage());
        }
        return null;
    }
    /**
     * ���ݵ���ID�򵥾���ϸID��ѯδ��ɵ���ҵ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#getUnfinishedJob(java.lang.String, java.lang.String)
     */
    public int getUnfinishedJob(String strInvoiceId, String strInvoiceDetailId) throws Exception {
        int iRow = 0;
        try{
            String strSql = "select count(distinct job.jobid) from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.status not in('4','5')";
            if(strInvoiceId != null && strInvoiceId.trim().length() > 0){
                strSql = strSql + " and job.boundstockid='" + strInvoiceId + "'";
            }
            if(strInvoiceDetailId != null && strInvoiceDetailId.trim().length() > 0){
                strSql = strSql + " and job.boundstockdetailid='" + strInvoiceDetailId + "'";
            }     
            //strSql = strSql + " group by job.jobid";
            iRow = m_dao.searchEntitieCount(strSql);
        }catch(Exception er){
            throw new Exception("���ݵ���ID�򵥾���ϸID��ѯδ��ɵ���ҵ����" + er.getMessage());
        }
        return iRow;
    }
    /**
     * ���ݵ���ID�򵥾���ϸID��ѯδ���˵���ҵ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#getUncheckUpJob(java.lang.String, java.lang.String)
     */
    public int getUncheckUpJob(String strInvoiceId, String strInvoiceDetailId) throws Exception {
        int iRow = 0;
        try{
            String strSql = "select count(distinct job.jobid) from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and (jobdetail.isreview!='Y' or jobdetail.isreview is null) and job.status!='5' ";
            if(strInvoiceId != null && strInvoiceId.trim().length() > 0){
                strSql = strSql + " and job.boundstockid='" + strInvoiceId + "'";
            }
            if(strInvoiceDetailId != null && strInvoiceDetailId.trim().length() > 0){
                strSql = strSql + " and job.boundstockdetailid='" + strInvoiceDetailId + "'";
            }     
            //strSql = strSql + " group by job.jobid";
            iRow = m_dao.searchEntitieCount(strSql);
        }catch(Exception er){
            throw new Exception("���ݵ���ID�򵥾���ϸID��ѯδ��ɵ���ҵ����" + er.getMessage());
        }
        return iRow;
    }
    /**
     * ���ݳ��ⵥ��ѯ����ʣ�����ҵ��ϸ��Ϣ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#getRemainJobDetail(java.lang.String, java.lang.String)
     */
    public List getRemainJobDetail(String strInvoiceId, String strInvoiceDetailId) throws Exception
    {
        if(strInvoiceId != null || strInvoiceDetailId != null)
        {
            try
            {
                String strSql = "select jobdetail.jobdetailid, (jobdetail.jobnum-jobdetail.assignnum), (jobdetail.volume-jobdetail.assignvolume), (jobdetail.weight-jobdetail.assignweight), (jobdetail.netweight-jobdetail.assignnetweight)  from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.status='4' and jobdetail.assignnum!=jobdetail.jobnum";
                if(strInvoiceId != null && strInvoiceId.trim().length() > 0){
                    strSql = strSql + " and job.boundstockdetailid='" + strInvoiceId + "'";
                }
                if(strInvoiceDetailId != null && strInvoiceDetailId.trim().length() > 0){
                    strSql = strSql + " and job.boundstockdetailid='" + strInvoiceDetailId + "'";
                }  
                List ls = m_dao.searchEntities(strSql);
                return ls;
            }catch(Exception er)
            {
                throw new Exception("���ݳ��ⵥ��ѯ����ʣ�����ҵ��ϸ��Ϣ����" + er.getMessage());
            }
        }
        return null;
    }
    /**
     * ������ŵ�����ϸ����ɵ�����
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#getInvoiceDetailFinishNum(java.lang.String)
     */
    public Object[] getInvoiceDetailFinishNum(String strInvoiceDetailId) throws Exception
    {
        if(strInvoiceDetailId != null)
        {
            try
            {
                String strSql = "select sum(jobDetail.assignnum),sum(jobDetail.assignweight),sum(jobDetail.assignnetweight),sum(jobDetail.assignvolume) from InoutJob as job, InoutJobdetail as jobDetail where job.jobid=jobDetail.jobid and job.boundstockdetailid='" + strInvoiceDetailId + "' and job.status='4'";
                List ls = m_dao.searchEntities(strSql);
                if(ls != null && ls.size()>0)
                {  
                    return (Object[])ls.get(0);
                }            
            }catch(Exception er)
            {
                throw new Exception("���ݳ��ⵥ����ϸ����������ҵ�ķ�����������:" + er.getMessage());
            }
        }
        return null;
    }
    /**
     * ���ݳ��ⵥ�ͳ��ⵥ��ϸID��ѯ������ҵ����ҵ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.job.IJobDao#getJobAndJobDetail(java.lang.String, java.lang.String)
     */
    public List getJobAndJobDetail(String invoiceid, String invoicedetailid) throws Exception {
        try{
            if((invoiceid != null && invoiceid.trim().length() > 0) || (invoicedetailid != null && invoicedetailid.trim().length() > 0))
            {
                if((invoiceid != null && invoiceid.trim().length() > 0) || (invoicedetailid != null && invoicedetailid.trim().length() > 0)){
                    StringBuilder strBud = new StringBuilder(); 
                    strBud.append("from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.status!='5' ");
                    if(invoiceid != null && invoiceid.trim().length() > 0){
                        strBud.append(" and job.boundstockid='" + invoiceid + "'");
                    }
                    if(invoicedetailid != null && invoicedetailid.trim().length() > 0){
                        strBud.append(" and job.boundstockdetailid='" + invoicedetailid + "'");
                    }
                    strBud.append(" order by job.jobid");
                    return m_dao.searchEntities(strBud.toString());
                }
            }
        }catch(Exception er){
            throw new Exception("���ݳ��ⵥ�ͳ��ⵥ��ϸID��ѯ������ҵ����ҵ��ϸ����:" + er.getMessage());
        }
        return null;
    }
    
   
    
    /**
     * ������ҵ��ȡ��ҵ��ϸ
     */
    public String getJobDetailByTraycode(String traycode,String warehouseID) throws Exception{
    	String strBackMsg = "";
    	try{
    		if(traycode!=null&&traycode.trim().length()>0){
        		String hql = "select job.boundstockid, jobDetail.m_strCustomerName, jobDetail.m_strProductName, " +
        				"jobDetail.assignnum, jobDetail.printdate, jobDetail.lotinfo, jobDetail.customerid, jobDetail.productid, jobDetail.jobid, jobDetail.jobdetailid, jobDetail.inventoryid from InoutJob as job, InoutJobdetail as jobDetail " +
        				"where jobDetail.jobid = job.jobid and job.inOutType='2' and job.traycode = '" + traycode + "' and job.warehouseid ='"+warehouseID+"' and job.status in ('1','2','3') and job.jobcategory = '2'";
        		List ls = m_dao.searchEntities(hql);
        		if(ls!=null&&ls.size()>0){
        			Object[] ob = null;
        			for(int i=0; i<ls.size(); i++ ){
        				ob = (Object[])ls.get(i);
        				if(ob!=null&&ob.length>0){
            				String boundstockid = ob[0]==null?"":ob[0].toString();
            				String customerName = ob[1]==null?"":ob[1].toString();
            				String productName = ob[2]==null?"":ob[2].toString();
            				String assignnum = ob[3]==null?"":ob[3].toString();
            				String printdate = ob[4]==null?"":ob[4].toString();
            				String lotinfo = ob[5]==null?"":ob[5].toString();
            				String customerId = ob[6]==null?"":ob[6].toString();
            				String productId = ob[7]==null?"":ob[7].toString();
            				String jobId = ob[8]==null?"":ob[8].toString();
            				String jobDetailId = ob[9]==null?"":ob[9].toString();
            				String inventoryId = ob[10]==null?"":ob[10].toString();
            				if(i == ls.size()-1){
            					strBackMsg = strBackMsg + boundstockid+","+customerName+","+productName+","+assignnum+","+printdate+","+lotinfo+","+customerId+","+productId+","+jobId+","+jobDetailId+","+inventoryId;
            				}else{
            					strBackMsg = strBackMsg + boundstockid+","+customerName+","+productName+","+assignnum+","+printdate+","+lotinfo+","+customerId+","+productId+","+jobId+","+jobDetailId+","+inventoryId+" | ";
            				}
            				
            			}
        			}
        		}
        	}
    	}catch(Exception er){
    		throw new Exception("������������["+ traycode +"]���ֱ�ӳ�����Ϣ����" + er.getMessage());
    	}
    	return strBackMsg;
    }
    
    /**
     * ������ҵ��ȡ������ҵ��ϸ
     */
    public String GetZCHLInventoryByTraycode(String traycode,String warehouseID) throws Exception{
    	String strBackMsg = "";
    	try{
    		if(traycode!=null&&traycode.trim().length()>0){
    			
        		String hql = " from InventoryStorage as inventory, BaseWharea as baseWharea " +
        				"where inventory.traycode = '" + traycode + "' and inventory.warehouseid ='"+warehouseID+"' and inventory.whAreaId = baseWharea.whAreaId and baseWharea.whAreaType = '9' and baseWharea.warehouseid ='"+warehouseID+"'";
        		List ls = m_dao.searchEntities(hql);
        		if(ls!=null&&ls.size()>0){
        			Object[] ob = null;
        			for(int i=0; i<ls.size(); i++ ){
        				ob = (Object[])ls.get(i);
        				if(ob!=null&&ob.length>0){
        					InventoryStorage inventory = (InventoryStorage)ob[0];
        					String inventoryId = inventory.getInventoryid();
        					String productId = inventory.getProductid();
        					String productName = inventory.getProductName();
        					String productDate = inventory.getProductdate();
        					String lotinfo = inventory.getLotinfo();
        					double pnum = inventory.getPnum();
        					double assignnum = inventory.getAssignnum();
        					double oriNum = pnum-assignnum;
            				if(i == ls.size()-1){
            					strBackMsg = strBackMsg + inventoryId+","+productId+","+productName+","+productDate+","+lotinfo+","+oriNum+","+assignnum;
            				}else{
            					strBackMsg = strBackMsg + inventoryId+","+productId+","+productName+","+productDate+","+lotinfo+","+oriNum+","+assignnum+" | ";
            				}
            				
            			}
        			}
        		}
        	}
    	}catch(Exception er){
    		throw new Exception("������������["+ traycode +"]���ֱ�ӳ�����Ϣ����" + er.getMessage());
    	}
    	return strBackMsg;
    }
    /**
     * �������̻�ȡ��Ӧ��ҵ��Ϣ
     */
    public String GetJobInfoByTraycode(String traycode) throws Exception{
    	String strBackMsg = "";
    	try{
    		if(traycode!=null&&traycode.trim().length()>0){
    			
        		String hql = " from InoutJob as job , InoutJobdetail as jobDetail " +
        				"where job.traycode = '" + traycode+"' and jobDetail.jobid = job.jobid and job.status in ('1','2','3') and job.invoicetype in ('1','3')";
        		List ls = m_dao.searchEntities(hql);
        		if(ls!=null&&ls.size()>0){
        			Object[] ob = null;
        			for(int i=0; i<ls.size(); i++ ){
        				ob = (Object[])ls.get(i);
        				if(ob!=null&&ob.length>0){
//        					InventoryStorage inventory = (InventoryStorage)ob[0];
//        					String inventoryId = inventory.getInventoryid();
//        					String productId = inventory.getProductid();
//        					String productName = inventory.getProductName();
//        					String productDate = inventory.getProductdate();
//        					String lotinfo = inventory.getLotinfo();
//        					double pnum = inventory.getPnum();
//        					double assignnum = inventory.getAssignnum();
//        					double oriNum = pnum-assignnum;
//            				if(i == ls.size()-1){
//            					strBackMsg = strBackMsg + inventoryId+","+productId+","+productName+","+productDate+","+lotinfo+","+oriNum;
//            				}else{
//            					strBackMsg = strBackMsg + inventoryId+","+productId+","+productName+","+productDate+","+lotinfo+","+oriNum+" | ";
//            				}
        					InoutJob job = (InoutJob)ob[0];
        					InoutJobdetail jobDetail = (InoutJobdetail)ob[1];
        					String productName = jobDetail.getM_strProductName();
        					String lotInfo = jobDetail.getLotinfo();
        					String productCode = jobDetail.getProductcode();
        					double num = jobDetail.getJobnum();
        					String warehouseName = job.getWarehousename();
        					String scargoWhareaName = job.getTcargoWhareaName();
        					if(i == ls.size()-1){
        						strBackMsg = strBackMsg + productName + ","+lotInfo+ ","+productCode+ ","+num+ ","+warehouseName+ ","+scargoWhareaName;
        					}else{
        						strBackMsg = strBackMsg + productName + ","+lotInfo+ ","+productCode+ ","+num+ ","+warehouseName+ ","+scargoWhareaName+"|";
        					}
            				
            			}
        			}
        		}
        	}
    	}catch(Exception er){
    		throw new Exception("������������["+ traycode +"]���ֱ�ӳ�����Ϣ����" + er.getMessage());
    	}
    	return strBackMsg;
    }
    /**
     * ���ܣ����ݵ���Id��ȡ��ҵ����ϸ
     * @param invoiceid
     * @return
     * @throws Exception
     */
    public List getJobDetailByInvoiceid(String invoiceid) throws Exception{
    	try{
            if(invoiceid != null && invoiceid.trim().length() > 0)
            {
                if(invoiceid != null && invoiceid.trim().length() > 0 ){
                    StringBuilder strBud = new StringBuilder(); 
                    strBud.append("from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.status!='5'");
                    if(invoiceid != null && invoiceid.trim().length() > 0){
                        strBud.append(" and job.boundstockid='" + invoiceid + "'");
                    }
                    strBud.append(" order by job.jobid");
                    return m_dao.searchEntities(strBud.toString());
                }
            }
        }catch(Exception er){
            throw new Exception("���ݳ��ⵥID��ѯ������ҵ��ϸ����:" + er.getMessage());
        }
        return null;
    }
    
    /**
     *  ���ܣ�RF������ҵ����ӵ���
     */
	public String addRFJobz(InoutJob job, InoutJobdetail jobdetail, ScheduleTask task, BaseCargospace space, EntityDAO dao) {
		String strMsg = "�����ɹ�!";
        if(job!=null){
        	Session session = dao.getSession();
        	Transaction tx =null;
    		try {
    			tx = session.beginTransaction();
    			session.save(job); //������ҵ
    			session.save(jobdetail); //������ҵ��ϸ
    			if(task!=null){
    				session.save("ScheduleTask",task); //��ӵ�����ҵ
    			}
    			session.update(space);	//���»�λ
    			tx.commit();	
    			Logger.info("RF�ջ���������ҵ����ӵ���---> �ɹ�");
    		}catch (HibernateException he) {
    			if(tx != null){
                    tx.rollback();
                }
    			Logger.error("������ⵥ"+job.getJobid()+"��������ʧ��"+he.getMessage());
    		}
    		finally
    		{
        		dao.closeSession(session);
        	}    
        }else{
        	strMsg = "����ʧ��!";
        }
        return strMsg;
	}
	
    /**
     * ������ⵥ����ϸ���޸���ҵ��
     */
    public String addInBoundupdateJob( List<InboundHeader> lsInboundHeader, List<InboundDetail> lsInboundDetail, List<InoutJob> lsjob, List<InoutJobdetail> lsjobD, EntityDAO dao) throws Exception {
        String strMsg = "�����ɹ�!";
    	//ͬ��  ��ⵥ��
    	Session session = dao.getSession();
		try {
			Transaction tx = session.beginTransaction();
			if(lsInboundHeader!=null && lsInboundHeader.size()>0){
				for(int i=0;i<lsInboundHeader.size();i++){
					session.save(lsInboundHeader.get(i));
    			}
			}
			if(lsInboundDetail!=null && lsInboundDetail.size()>0){
				for(int i=0;i<lsInboundDetail.size();i++){
					session.save(lsInboundDetail.get(i));
    			}
			}
			if(lsjob!=null && lsjob.size()>0){
				for(int i=0;i<lsjob.size();i++){
					session.update(lsjob.get(i));
    			}
			}
			if(lsjobD!=null && lsjobD.size()>0){
				for(int i=0;i<lsjobD.size();i++){
					session.update(lsjobD.get(i));
    			}
			}
			tx.commit();	
		} catch (HibernateException he) {
			System.out.println("������ⵥ����ϸ���޸���ҵ��_��������ʧ��"+he.getMessage());
			strMsg = "����ʧ��!";
			Logger.error("������ⵥ����ϸ���޸���ҵ��_��������ʧ��"+he.getMessage());
		}
		finally
		{
    		dao.closeSession(session);
    	}    
        return strMsg;
    }
    
}
