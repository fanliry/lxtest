package com.wms3.bms.standard.dao.outbound.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryDaoImpl;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.dao.job.impl.TaskDaoImpl;
import com.wms3.bms.standard.dao.outbound.IOutboundDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
import com.wms3.bms.standard.entity.outerInterface.SaleFormDetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * ����: �������DAO��
 * @author hug 2012-9-13
 * @since WMS 3.0
 */
public class OutboundDaoImpl extends AbstractDao implements IOutboundDao{
    
    public OutboundDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }  
    /**
     * ���ӳ��ⵥ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#addOutBound(com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader)
     */
    public void addOutBound(OutboundInvoiceHeader invoice) throws Exception {
        m_dao.save("OutboundInvoiceHeader", invoice);  
    }
    /**
     * ���³��ⵥ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#updateOutBound(com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader)
     */
    public void updateOutBound(OutboundInvoiceHeader invoice) throws Exception {
        m_dao.update("OutboundInvoiceHeader", invoice);
    }
    /**
     * ���ӳ��ⵥ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#addOutBoundDetail(com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail)
     */
    public void addOutBoundDetail(OutboundInvoiceDetail outBoundDetail) throws Exception {
        m_dao.save("OutboundInvoiceDetail", outBoundDetail);
    }
    /**
     * �޸ĳ��ⵥ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#updateOutBoundDetail(com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail)
     */
    public void updateOutBoundDetail(OutboundInvoiceDetail outBoundDetail) throws Exception {
        m_dao.update("OutboundInvoiceDetail", outBoundDetail);  
    }
    /**
     * ɾ�����ⵥ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#deleteOutBound(java.lang.String)
     */
    public void deleteOutBound(String strOutBoundId) throws Exception {
        try{
            if(strOutBoundId != null){
                List<String> lsSQL = new ArrayList<String>();
                String strSql = "delete from OutboundInvoiceHeader as outvo where outvo.outstockid='" + strOutBoundId + "'";
                lsSQL.add(strSql);
                String strSql1 = "delete from OutboundInvoiceDetail as outde where outde.outstockid='" + strOutBoundId + "'";
                lsSQL.add(strSql1);
                m_dao.deleteSqlList(lsSQL);
            }
        }catch(Exception er){
            throw new Exception("ɾ�����ⵥ����" + er.getMessage());
        }   
    }
    /**
     * ɾ�����ⵥ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#deleteOutBoundDetail(java.lang.String)
     */
    public void deleteOutBoundDetail(String strDetailId) throws Exception {
        try{
            if(strDetailId != null){
                String strSql = "delete from OutboundInvoiceDetail as outde where outde.outstockdetailid='" + strDetailId + "'";
                m_dao.deleteSql(strSql);
            }
        }catch(Exception er){
            throw new Exception("ɾ�����ⵥ��ϸ����" + er.getMessage());
        } 
    }
    /**
     * ���ݳ��ⵥID��ó��ⵥ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#getOutBoundById(java.lang.String)
     */
    public OutboundInvoiceHeader getOutBoundById(String strOutBoundId) throws Exception {
        try{
            if(strOutBoundId != null){
                String strSql = "from OutboundInvoiceHeader as invo where invo.outstockid='" + strOutBoundId + "'";
                List lsOutBound = m_dao.searchEntities(strSql, 0, 1);
                if(lsOutBound != null && lsOutBound.size() > 0){
                    return (OutboundInvoiceHeader)lsOutBound.get(0);
                }
            }
        }catch(Exception er){
            throw new Exception("���ݳ��ⵥID["+strOutBoundId+"]��ó��ⵥ����" + er.getMessage());
        }
        return null;
    }
    /**
     * ����:��ȡû����ȫ����ĳ����Ʒ��Ϣ
     * @author yao 2015-3-11
     * @return
     * @throws Exception
     */
    public List getOutBoundProudctInfo() throws Exception{
    	List lsList = null; 
    	try{
    		
             String strSql = "select ta.saleno,tb.outstockdetailid,ta.customername,tb.lotinfo,tb.m_strProductCode ,(tb.invoicenum- ISNULL(tb.assignnum,0)),tb.printdate,tb.productid,tb.Virtualwhid from OutboundInvoiceHeader ta ,OutboundInvoiceDetail tb where ta.outstockid=tb.outstockid and ta.outstatus not in('7','8') and tb.invoicenum>tb.assignnum order by tb.lotinfo,ta.saleno";
             lsList = m_dao.searchEntities(strSql);
        }catch(Exception er){
            throw new Exception("��ȡû����ȫ����ĳ����Ʒ��Ϣ����" + er.getMessage());
        }
        return lsList;
    }
    /**
     * ���ݳ��ⵥ��ϸID��ó��ⵥ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#getOutBoundDetailById(java.lang.String)
     */
    public OutboundInvoiceDetail getOutBoundDetailById(String strInvoiceDetailId) throws Exception {
        try{
            if(strInvoiceDetailId != null){
                String strSql = "from OutboundInvoiceDetail as invo where invo.outstockdetailid='" + strInvoiceDetailId + "'";
                List lsOutBound = m_dao.searchEntities(strSql, 0, 1);
                if(lsOutBound != null && lsOutBound.size() > 0){
                    return (OutboundInvoiceDetail)lsOutBound.get(0);
                }
            }
        }catch(Exception er){
            throw new Exception("���ݳ��ⵥ��ϸID["+strInvoiceDetailId+"]��ó��ⵥ��ϸ����" + er.getMessage());
        }
        return null;
    }
    /**
     * ���ݳ��ⵥID��ó��ⵥ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#getOutBoundDetailsById(java.lang.String)
     */
    public List<OutboundInvoiceDetail> getOutBoundDetailsById(String strInvoiceId) throws Exception {
        try{
            if(strInvoiceId != null){
                String strSql = "from OutboundInvoiceDetail as invo where invo.outstockid='" + strInvoiceId + "'";
                List<OutboundInvoiceDetail> lsOutBound = m_dao.searchEntities(strSql);
                return lsOutBound;
            }
        }catch(Exception er){
            throw new Exception("���ݳ��ⵥID["+strInvoiceId+"]��ó��ⵥ��ϸ����" + er.getMessage());
        }
        return null;
    }
    /**
     * ���ݲֿ�ID�����Ҫ����ȷ�ϵĳ��ⵥ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#getSendOutInvoice(java.lang.String)
     */
    public List<OutboundInvoiceHeader> getSendOutInvoice(String warehouseid) throws Exception {
        List<OutboundInvoiceHeader> lsResult = null;
        try{
            if(warehouseid != null){//���ⵥ״̬ 0:������1-Ԥ�� 2-���� 4-��ȫ���   7:����ȷ�� 8:����
                String strSql = "from OutboundInvoiceHeader as outinv  where outinv.outstatus not in('1','7','8') and outinv.warehouseid='" + warehouseid + "'";     
                lsResult = m_dao.searchEntities(strSql);
            }
        }catch(Exception er){
            throw new Exception("���ݲֿ�ID�����Ҫ����ȷ�ϵĳ��ⵥ����" + er.getMessage());
        }
        return lsResult;
    }
    /**
     * ���ݲֿ�ID�����Ҫ����ȷ�ϵ�B�ͻ��ĳ��ⵥ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IOutboundDao#getSendOutInvoice(java.lang.String, java.lang.String)
     */
    public List<OutboundInvoiceHeader> getSendOutInvoice(String warehouseid, String aoutid) throws Exception {
        List<OutboundInvoiceHeader> lsResult = null;
        try{
            if(warehouseid != null){//���ⵥ״̬ 0:������1-Ԥ�� 2-���� 4-��ȫ���   7:����ȷ�� 8:����
                String strSql = "from OutboundInvoiceHeader as outinv  where outinv.outstatus not in('1','7','8') and outinv.warehouseid='" + warehouseid + "' and outinv.outstockid!='" + aoutid + "'";     
                lsResult = m_dao.searchEntities(strSql);
            }
        }catch(Exception er){
            throw new Exception("���ݲֿ�ID�����Ҫ����ȷ�ϵ�B�ͻ��ĳ��ⵥ����" + er.getMessage());
        }
        return lsResult;
    }
    /**
     * ���ܣ����ݳ��ⵥ��id�ͻ�id��ó����Ѿ������ҵ
     * @return List<InoutJobdetail>
     * @throws Exception
     */
	public List<InoutJobdetail> getFinishJobByOutIdCid(String outId) throws Exception {
		List<InoutJobdetail> lsDel = null;
		try {
			String strHql = "from InoutJobdetail as o where o.invoiceid='"+outId+"'";
			lsDel = m_dao.searchEntities(strHql);
		} catch (Exception er) {
			throw new Exception("���ݳ��ⵥ��id�ͻ�id��ó����Ѿ������ҵ����" + er.getMessage());
		}
		return lsDel;
	}
	/**
     * ���ܣ����ݳ��ⵥ��id�ͻ�id��ó����Ѿ������ҵ
     * @return List<InoutJobdetail>
     * @throws Exception
     */
	public List<InoutJob> getFinishJobByIdCid(String outId) throws Exception {
		List<InoutJob> lsDel = null;
		try {
			String strHql = "from InoutJob as o where o.boundstockid='"+outId+"'";
			lsDel = m_dao.searchEntities(strHql);
		} catch (Exception er) {
			throw new Exception("���ݳ��ⵥ��id�ͻ�id��ó����Ѿ������ҵ����" + er.getMessage());
		}
		return lsDel;
	}
	 /**
	 * ���ܣ��ݴ�ֱ�ӳ���ʱ���޸ĵ�����
	 * @param job
	 * @param jobDetail
	 * @param inventory
	 * @throws Exception
	 */
    public String updateInventoryAndJob(String jobID, String jobDetailID,String inventoryID) throws Exception{
    	String strBackMsg = "Y";
    	IJobDao jobDao = new JobDaoImpl(m_dao);
    	IInventoryDao inventoryDao = new InventoryDaoImpl(m_dao);
    	InoutJob job = null;
    	InoutJobdetail jobDetail = null;
    	InventoryStorage inventory = null;
    	if(jobID!=null){
			job = jobDao.getJobById(jobID);
		}
		if(jobDetailID!=null){
			jobDetail = jobDao.getJobDetailByDetailId(jobDetailID);
		}
		if(inventoryID!=null){
			inventory = inventoryDao.getInventoryById(inventoryID); 
		}
    	Session session = m_dao.getSession();
    	Transaction tx = session.beginTransaction();
    	try{
    		if (job != null) {
    			String jobidsString = job.getJobid().intern();
    			synchronized (jobidsString) {
    				if (job.getJobcategory() != null && job.getJobcategory().equals("2")) { // �ݴ�����ҵ
        				// �ж���ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
        				if (job.getStatus().equals("4") || job.getStatus().equals("5")) {
        					if (strBackMsg.equals("Y")) {
        						strBackMsg = "���ܲ����Ѿ����ϻ����Ѿ����ϵ�����ҵ��";
        					} else {
        						strBackMsg += "\r���ܲ����Ѿ����ϻ����Ѿ����ϵ�����ҵ��";
        					}
        				} else {
        	        		job.setStatus("4");
        	        		job.setFinishtime(StrTools.getCurrDateTime(2));
        	        		jobDetail.setIsreview("Y");
        	        		jobDetail.setJobnum(jobDetail.getAssignnum());    //���˳ɹ����޸� ��ҵ����Ϊ��������
        	        		jobDetail.setReviewTime(StrTools.getCurrDateTime(2));
        	        		session.update(job);
        	        		session.update(jobDetail);
        	        		double pnumResult = inventory.getPnum()-jobDetail.getAssignnum();
        	        		double assignnumResult = inventory.getAssignnum() - jobDetail.getAssignnum();
        	        		if(pnumResult == 0){
        	        			session.delete(inventory);
        	        		}else if(pnumResult>0){
        	        			inventory.setPnum(pnumResult);
        	        			if(assignnumResult<=0){
        	        				inventory.setAssignnum(0);
        	        				inventory.setStatus("0"); //��������Ϊ0ʱ�����ÿ��Ϊδ����״̬
        	        			}else{
        	        				inventory.setAssignnum(assignnumResult);
        	        			}
        	        			session.update(inventory);
        	        		}else if(pnumResult<0){
        	        			strBackMsg = "�������С����ҵ����������";
        	        		}
        	        		tx.commit();
        				}
        			} else {
        				strBackMsg = "�����ݴ�����ҵ��";
        			}
    			}
    		} else {
    			if (strBackMsg.equals("Y")) {
    				strBackMsg = "��ҵ�����ڣ�";

    			} else {
    				strBackMsg += "\r��ҵ�����ڣ�";
    			}
    		}
    		

    	}catch(Exception he){
    		Logger.error("ֱ�ӳ������"+he.getMessage());
    		strBackMsg = "ֱ�ӳ������";
    	}finally
		{
    		m_dao.closeSession(session);
    	} 
    	return strBackMsg; //"1"��ʾ�ɹ�
    }
    
    /**
     * ���ܣ����ӳ��ⵥ
     * @author fanll 2015-3-8
     * @param invoice
     * @param outboundInvoiceDetails
     * @return
     * @throws Exception
     */
    public String addOutBound(OutboundInvoiceHeader outInvoice,List<OutboundInvoiceDetail> outboundInvoiceDetails,List<SaleFormDetail> SaleFormDetaills) throws Exception{
    	String msg = "�����ɹ�";
    	Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //���ӳ��ⵥ
            if(outInvoice!=null){
            	session.save("OutboundInvoiceHeader", outInvoice);	
            }
            //���ӳ��ⵥ��ϸ
            if(outboundInvoiceDetails != null){
            	OutboundInvoiceDetail outDetail = null;	
                for(int i = 0; i < outboundInvoiceDetails.size(); i++){
                	outDetail = outboundInvoiceDetails.get(i);
                    session.save("OutboundInvoiceDetail", outDetail);
                }
            }
            //�����������ⶩ��������
            if(SaleFormDetaills != null){
            	SaleFormDetail sodetail = null;	
                for(int i = 0; i < SaleFormDetaills.size(); i++){
                	sodetail = SaleFormDetaills.get(i);
                    session.update(sodetail);
                }
            }
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�����������ⶩ�����ɳ��ⵥ����ϸ����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
        return msg;
    }
    
    /**
     * ���ܣ�ȡ�����ⵥ
     * @author fanll 2015-3-8
     * @param outInvoice
     * @param SaleFormDetaills
     * @return
     * @throws Exception
     */
    public String CancelInvoice(OutboundInvoiceHeader outInvoice,List<SaleFormDetail> SaleFormDetaills) throws Exception{
    	String msg = "�����ɹ�";
    	Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //���ӳ��ⵥ
            if(outInvoice!=null){
            	session.update("OutboundInvoiceHeader", outInvoice);	
            }

            //�����������ⶩ��������
            if(SaleFormDetaills != null){
            	SaleFormDetail sodetail = null;	
                for(int i = 0; i < SaleFormDetaills.size(); i++){
                	sodetail = SaleFormDetaills.get(i);
                    session.update(sodetail);
                }
            }
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�����������ⶩ�����ɳ��ⵥ����ϸ����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
        return msg;
    }
    
}
