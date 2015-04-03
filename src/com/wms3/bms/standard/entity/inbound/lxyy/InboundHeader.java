package com.wms3.bms.standard.entity.inbound.lxyy;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * 
 * ����: ��ⵥ��
 * @author yao 2013-3-4
 */
public class InboundHeader implements java.io.Serializable {

     private String instockid;  	    //��ⵥid
     private String inrequestid;      	//���뵥id
     private String invoicetypeid;  	//��ⵥ����id(��Ʒ��⣬�����˿�)
     private String uploadflag;     	//�ϴ���־0-���ϴ���Ĭ�ϣ�1-δ�ϴ�
     private String invoicedate;    	//��������
     private String createtime;     	//��������ʱ��
     private String finishtime;     	//����ȷ��ʱ��
     private String createmanid;    	//����������Ա���
     private String warehouseid;    	//�ջ��ֿ�
     private String instatus;       	//��ⵥ��״̬id 1-�½��� 3-ȷ�ϣ�5-����
     private String deliveryunits;    	//������λ
     
     private String remark;      	    //��ע
     private String Reserved1;      	//Ԥ���ֶ�1
     private String Reserved2;      	//Ԥ���ֶ�2
     
     //��������
     private String invoicetypename;  	//������(��Ʒ��⣬�����˿�)
     private String createmanname;      //�Ƶ�����
     private String instatusname;       //��ⵥ��״̬id id 1-�½���3-ȷ�ϣ�4-����
     private String warehousename;    	//�ջ��ֿ���
	public String getInstockid() {
		return instockid;
	}
	public void setInstockid(String instockid) {
		this.instockid = instockid;
	}
	public String getInrequestid() {
		return inrequestid;
	}
	public void setInrequestid(String inrequestid) {
		this.inrequestid = inrequestid;
	}
	public String getInvoicetypeid() {
		return invoicetypeid;
	}
	public void setInvoicetypeid(String invoicetypeid) {
		this.invoicetypeid = invoicetypeid;
	}
	
	public String getUploadflag() {
		return uploadflag;
	}
	public void setUploadflag(String uploadflag) {
		this.uploadflag = uploadflag;
	}
	public String getInvoicedate() {
		return invoicedate;
	}
	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCreatemanid() {
		return createmanid;
	}
	public void setCreatemanid(String createmanid) {
		this.createmanid = createmanid;
	}
	public String getWarehouseid() {
		return warehouseid;
	}
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	public String getInstatus() {
		return instatus;
	}
	public void setInstatus(String instatus) {
		this.instatus = instatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReserved1() {
		return Reserved1;
	}
	public void setReserved1(String reserved1) {
		Reserved1 = reserved1;
	}
	public String getReserved2() {
		return Reserved2;
	}
	public void setReserved2(String reserved2) {
		Reserved2 = reserved2;
	}
	public String getInvoicetypename() {
		return invoicetypename;
	}
	public void setInvoicetypename(String invoicetypename) {
		this.invoicetypename = invoicetypename;
	}
	public String getCreatemanname() {
		return createmanname;
	}
	public void setCreatemanname(String createmanname) {
		this.createmanname = createmanname;
	}
	public String getInstatusname() {
		return instatusname;
	}
	public void setInstatusname(String instatusname) {
		this.instatusname = instatusname;
	}
	/**
	 * ��ⵥ���� ��ѯ
	 * @param warehouseid
	 * @param instockid
	 * @param invoicedate
	 * @param instatus
	 * @param productName
	 * @param lotinfo
	 * @return
	 */
	public String getQueryHQL(String warehouseid, String inrequeststockid, String instatus,
			String productName, String lotinfo,String starttime,String endtime,String invoicetype)
	{
		String hql = "select distinct ta from InboundHeader ta,InboundDetail tb where 1=1 and ta.instockid=tb.instockid ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(inrequeststockid != null && inrequeststockid.trim().length() > 0){
			hql += " and ta.inrequestid='"+inrequeststockid+"'";
		}
		if(starttime != null && starttime.trim().length() > 0){
			hql += " and ta.invoicedate>='"+starttime+"'";
		}
		if(endtime != null && endtime.trim().length() > 0){
			hql += " and ta.invoicedate<='"+endtime+"'";
		}
		if(instatus != null && instatus.trim().length() > 0){
			hql += " and ta.instatus='"+instatus+"'";
		}
		if(productName != null && productName.trim().length() > 0){
			hql += " and tb.productName like'%"+productName+"%'";
		}
		if(lotinfo != null && lotinfo.trim().length() > 0){
			hql += " and tb.lotinfo like'%"+lotinfo+"%'";
		}
		if(invoicetype != null && invoicetype.trim().length() > 0){
			hql += " and ta.invoicetypeid ='"+invoicetype+"'";
		}
		hql += " order by ta.warehouseid,ta.instockid";
		return hql;
	}
	/**
	 * ��ⵥ���� ��ѯ
	 * @param warehouseid
	 * @param instockid
	 * @param invoicedate
	 * @param instatus
	 * @param productName
	 * @param lotinfo
	 * @return
	 */
	public String getQueryHQLCount(String warehouseid, String inrequeststockid, String instatus,
			String productName, String lotinfo,String starttime,String endtime,String invoicetype)
	{
		String hql = "select count(distinct ta) from InboundHeader ta,InboundDetail tb where 1=1 and ta.instockid=tb.instockid ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(inrequeststockid != null && inrequeststockid.trim().length() > 0){
			hql += " and ta.inrequestid='"+inrequeststockid+"'";
		}
		if(starttime != null && starttime.trim().length() > 0){
			hql += " and ta.invoicedate>='"+starttime+"'";
		}
		if(endtime != null && endtime.trim().length() > 0){
			hql += " and ta.invoicedate<='"+endtime+"'";
		}
		if(instatus != null && instatus.trim().length() > 0){
			hql += " and ta.instatus='"+instatus+"'";
		}
		if(productName != null && productName.trim().length() > 0){
			hql += " and tb.productName like'%"+productName+"%'";
		}
		if(lotinfo != null && lotinfo.trim().length() > 0){
			hql += " and tb.lotinfo like'%"+lotinfo+"%'";
		}
		if(invoicetype != null && invoicetype.trim().length() > 0){
			hql += " and ta.invoicetypeid ='"+invoicetype+"'";
		}
		return hql;
	}
	/**
	 * ��ⵥ���� ��ѯ
	 * @param warehouseid
	 * @param instockid
	 * @param invoicedate
	 * @param instatus
	 * @param productName
	 * @param lotinfo
	 * @return
	 */
	public String getQueryHQLOther(String warehouseid, String inrequeststockid, String instatus,
			String productName, String lotinfo,String starttime,String endtime,String invoicetype)
	{
		String hql = "select distinct ta from InboundHeader ta,InboundDetail tb where 1=1 and ta.instockid=tb.instockid ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(inrequeststockid != null && inrequeststockid.trim().length() > 0){
			hql += " and ta.inrequestid='"+inrequeststockid+"'";
		}
		if(starttime != null && starttime.trim().length() > 0){
			hql += " and ta.invoicedate>='"+starttime+"'";
		}
		if(endtime != null && endtime.trim().length() > 0){
			hql += " and ta.invoicedate<='"+endtime+"'";
		}
		if(instatus != null && instatus.trim().length() > 0){
			hql += " and ta.instatus='"+instatus+"'";
		}
		if(productName != null && productName.trim().length() > 0){
			hql += " and tb.productName like'%"+productName+"%'";
		}
		if(lotinfo != null && lotinfo.trim().length() > 0){
			hql += " and tb.lotinfo like'%"+lotinfo+"%'";
		}
		if(invoicetype != null && invoicetype.trim().length() > 0){
			hql += " and ta.invoicetypeid ='"+invoicetype+"'";
		}else{
			hql += " and ta.invoicetypeid !='9'";
		}
		hql += " order by ta.warehouseid,ta.instockid";
		return hql;
	}
	/**
	 * ��ⵥ���� ��ѯ
	 * @param warehouseid
	 * @param instockid
	 * @param invoicedate
	 * @param instatus
	 * @param productName
	 * @param lotinfo
	 * @return
	 */
	public String getQueryHQLCountOther(String warehouseid, String inrequeststockid, String instatus,
			String productName, String lotinfo,String starttime,String endtime,String invoicetype)
	{
		String hql = "select count(distinct ta) from InboundHeader ta,InboundDetail tb where 1=1 and ta.instockid=tb.instockid ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(inrequeststockid != null && inrequeststockid.trim().length() > 0){
			hql += " and ta.inrequestid='"+inrequeststockid+"'";
		}
		if(starttime != null && starttime.trim().length() > 0){
			hql += " and ta.invoicedate>='"+starttime+"'";
		}
		if(endtime != null && endtime.trim().length() > 0){
			hql += " and ta.invoicedate<='"+endtime+"'";
		}
		if(instatus != null && instatus.trim().length() > 0){
			hql += " and ta.instatus='"+instatus+"'";
		}
		if(productName != null && productName.trim().length() > 0){
			hql += " and tb.productName like'%"+productName+"%'";
		}
		if(lotinfo != null && lotinfo.trim().length() > 0){
			hql += " and tb.lotinfo like'%"+lotinfo+"%'";
		}
		if(invoicetype != null && invoicetype.trim().length() > 0){
			hql += " and ta.invoicetypeid ='"+invoicetype+"'";
		}else{
			hql += " and ta.invoicetypeid !='9'";
		}
		return hql;
	}
	public String getFinishtime() {
		return finishtime;
	}
	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}
	/**
	 * ������뵥���� ������֤ ��ѯ
	 * @param instockid
	 * @return
	 */
	public InboundHeader getDaoQueryHql(String instockid,EntityDAO dao)
	{
		String hql = "from InboundHeader where 1=1  ";
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and instockid='"+instockid+"'";
		}
		InboundHeader obj =null;
		java.util.List<InboundHeader> ls = dao.searchEntities(hql);
		if(ls!=null && ls.size()>0){
			obj = ls.get(0);
		}
		
		return obj;
	}
	/**
	 * ��ⵥ ��ѯ
	 * @param instockid
	 * @return
	 */
	public String getQueryHql(String instockid)
	{
		String hql = "from InboundHeader where 1=1  ";
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and instockid='"+instockid+"'";
		}
		return hql;
	}
	/**
	 * ��ⵥ���� ������ⵥ
	 * @param instockid
	 * @return
	 */
	public String getUpdateDeletesql(String instockid)
	{
		String hql = "update InboundHeader set instatus='4' where 1=1 ";
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and instockid='"+instockid+"'";
		}
		return hql;
	}
	public String getDeliveryunits() {
		return deliveryunits;
	}
	public void setDeliveryunits(String deliveryunits) {
		this.deliveryunits = deliveryunits;
	}
	public String getWarehousename() {
		return warehousename;
	}
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	/**
	 * ��ȡû��ȷ����û�����ϵ���ⵥ��RF -> �ջ���
	 * @param instockid
	 * @return
	 */
	public java.util.List<InboundHeader> getInboundRIHList(String warehouseid,EntityDAO dao)
	{
		String hql = "from InboundHeader where 1=1 and instatus not in('3','4')";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and warehouseid='"+warehouseid+"'";
		}
		hql += " order by instockid desc";
		InboundRequestInvoiceHeader obj =null;
		java.util.List<InboundHeader> ls = dao.searchEntities(hql);
		return ls;
	}
    /**
     * �½���ⵥ����ϸ��ϸ
     */
    public String addInBound(List<InboundDetail> lsInboundDetail, List<BindingRecord> lsBindingRecord,InboundHeader Header,EntityDAO dao) throws Exception {
        String strMsg = "�����ɹ�!";
        if(Header!=null){
        	//ͬ��  ��ⵥ��
            synchronized (Header.getInstockid()) {
            	Session session = dao.getSession();
        		try {
        			Transaction tx = session.beginTransaction();
        			session.save(Header); //������ⵥͷ
        			if(lsInboundDetail!=null && lsInboundDetail.size()>0){
        				for(int i=0;i<lsInboundDetail.size();i++){
        					session.save(lsInboundDetail.get(i));
            			}
        			}
        			if(lsBindingRecord!=null && lsBindingRecord.size()>0){
        				for(int i=0;i<lsBindingRecord.size();i++){
        					session.update(lsBindingRecord.get(i));
            			}
        			}
        			tx.commit();	
        		} catch (HibernateException he) {
        			Logger.error("������ⵥ"+Header.getInstockid()+"��������ʧ��"+he.getMessage());
        		}
        		finally
        		{
            		dao.closeSession(session);
            	}    
            }
        }else{
        	strMsg = "����ʧ��!";
        }
        return strMsg;
    }
    /**
     * ����ջ���Ϣ����ҵ��Ϣ
     */
    public String addInBoundJob(InboundHeader invoice, InboundDetail invoicedetail,InoutJob job,InoutJobdetail jobdetail,EntityDAO dao) throws Exception {
        String strMsg = "�����ɹ�!";
        if(invoice!=null){
        	//ͬ��  ��ⵥ��
            synchronized (invoice.getInstockid()) {
            	Session session = dao.getSession();
            	Transaction tx =null;
        		try {
        			tx = session.beginTransaction();
        			session.update(invoice); //������ⵥ��
        			session.update(invoicedetail); //������ⵥ��ϸ
        			session.save(job); //������ҵ
        			session.save(jobdetail); //������ҵ��ϸ
        			tx.commit();	
        			Logger.info("����ջ���Ϣ����ҵ��Ϣ�ɹ�");
        		}catch (HibernateException he) {
        			if(tx != null){
                        tx.rollback();
                    }
        			Logger.error("������ⵥ"+invoice.getInstockid()+"��������ʧ��"+he.getMessage());
        		}
        		finally
        		{
            		dao.closeSession(session);
            	}    
            }
        }else{
        	strMsg = "����ʧ��!";
        }
        return strMsg;
    }
    /**
     * ����ջ���Ϣ����ҵ��Ϣ
     */
    public String updateInboundABR(InboundHeader invoice,List<BindingRecord> lsBindingRecord,EntityDAO dao) throws Exception {
        String strMsg = "�����ɹ�!";
        if(invoice!=null){
        	//ͬ��  ��ⵥ��
            synchronized (invoice.getInstockid()) {
            	Session session = dao.getSession();
        		try {
        			Transaction tx = session.beginTransaction();
        			session.update(invoice); //������ⵥ��
        			
        			if(lsBindingRecord!=null && lsBindingRecord.size()>0){
        				for(int i=0;i<lsBindingRecord.size();i++){
        					session.update(lsBindingRecord.get(i));
            			}
        			}
        			tx.commit();	
        		}catch (HibernateException he) {
        		
        			Logger.error("������ⵥ"+invoice.getInstockid()+"��������ʧ��"+he.getMessage());
        		}
        		finally
        		{
            		dao.closeSession(session);
            	}    
            }
        }else{
        	strMsg = "����ʧ��!";
        }
        return strMsg;
    }
    
    /**
     *  ���ܣ�RF�ջ���������ҵ����ӵ���
     */
	public String addInBoundJobz(InboundHeader invoice,
			InboundDetail invoicedetail, InoutJob job,
			InoutJobdetail jobdetail, ScheduleTask task, EntityDAO dao) {
		String strMsg = "�����ɹ�!";
        if(invoice!=null){
        	//ͬ��  ��ⵥ��
            synchronized (invoice.getInstockid()) {
            	Session session = dao.getSession();
            	Transaction tx =null;
        		try {
        			tx = session.beginTransaction();
        			session.update(invoice); //������ⵥ��
        			session.update(invoicedetail); //������ⵥ��ϸ
        			session.save(job); //������ҵ
        			session.save(jobdetail); //������ҵ��ϸ
        			session.save(task); //��ӵ�����ҵ
        			tx.commit();	
        			Logger.info("RF�ջ���������ҵ����ӵ���---> �ɹ�");
        		}catch (HibernateException he) {
        			if(tx != null){
                        tx.rollback();
                    }
        			Logger.error("������ⵥ"+invoice.getInstockid()+"��������ʧ��"+he.getMessage());
        		}
        		finally
        		{
            		dao.closeSession(session);
            	}    
            }
        }else{
        	strMsg = "����ʧ��!";
        }
        return strMsg;
		
	}
}