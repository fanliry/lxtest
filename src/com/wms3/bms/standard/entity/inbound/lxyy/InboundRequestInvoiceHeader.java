package com.wms3.bms.standard.entity.inbound.lxyy;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mortbay.html.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.StrTools;

/**
 * 
 * ����: ���뵥
 * @author yao 2013-3-4
 */
public class InboundRequestInvoiceHeader implements java.io.Serializable {

     private static final long serialVersionUID = -747802160323265188L;
     private String instockid;      	//���뵥id
     private String invoicetypeid;  	//���뵥����id(��Ʒ��⣬�����˿�)
     private String invoicedate;    	//����ʱ��
     private String createtime;     	//��������ʱ��
     private String instatus;       	//���뵥��״̬id 1-�½���2-��ˣ�3-�󶨣�4-�ر� 5-���� 
     private String createmanid;    	//����������Ա���
     private String departid;       	//��������
     private String auditmanid;     	//�����Id
     private String warehouseid;    	//�ջ��ֿ�
     private String auditdate;      	//���ʱ��
     private String confirmdate;      	//�ر�ȷ��ʱ��
     private String remark;      	    //��ע
     private String Reserved1;      	//Ԥ���ֶ�1
     private String Reserved2;      	//Ԥ���ֶ�2
     
     //��������
     private String invoicetypename;  	//������(��Ʒ��⣬�����˿�)
     private String instatusname;       //���뵥��״̬��  1-�½���2-��ˣ�3-���ְ󶨣�4-�ر� 5-���� 
     private String createmanname;    	//����������Ա��
     private String departname;       	//��������
     private String auditmanname;     	//�������
     private String warehousename;    	//�ջ��ֿ���
     
     
     public EntityDAO  m_dao = null;
     
     
     /** default constructor */
     public InboundRequestInvoiceHeader(EntityDAO dao) {
     	m_dao = dao;
     }
     /** default constructor */
     public InboundRequestInvoiceHeader() {
     }
     
	public String getInstockid() {
		return instockid;
	}
	public void setInstockid(String instockid) {
		this.instockid = instockid;
	}
	public String getInvoicetypeid() {
		return invoicetypeid;
	}
	public void setInvoicetypeid(String invoicetypeid) {
		this.invoicetypeid = invoicetypeid;
	}
	public String getInvoicetypename() {
		return invoicetypename;
	}
	public void setInvoicetypename(String invoicetypename) {
		this.invoicetypename = invoicetypename;
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
	public String getInstatus() {
		return instatus;
	}
	public void setInstatus(String instatus) {
		this.instatus = instatus;
	}
	public String getInstatusname() {
		return instatusname;
	}
	public void setInstatusname(String instatusname) {
		this.instatusname = instatusname;
	}
	public String getCreatemanid() {
		return createmanid;
	}
	public void setCreatemanid(String createmanid) {
		this.createmanid = createmanid;
	}
	public String getCreatemanname() {
		return createmanname;
	}
	public void setCreatemanname(String createmanname) {
		this.createmanname = createmanname;
	}
	public String getDepartid() {
		return departid;
	}
	public void setDepartid(String departid) {
		this.departid = departid;
	}
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
	}
	public String getAuditmanid() {
		return auditmanid;
	}
	public void setAuditmanid(String auditmanid) {
		this.auditmanid = auditmanid;
	}
	public String getAuditmanname() {
		return auditmanname;
	}
	public void setAuditmanname(String auditmanname) {
		this.auditmanname = auditmanname;
	}
	public String getWarehouseid() {
		return warehouseid;
	}
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	public String getWarehousename() {
		return warehousename;
	}
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	public String getAuditdate() {
		return auditdate;
	}
	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}
	public String getConfirmdate() {
		return confirmdate;
	}
	public void setConfirmdate(String confirmdate) {
		this.confirmdate = confirmdate;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
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
	/**
	 * ������뵥���� ��ѯ
	 * @param warehouseid
	 * @param instockid
	 * @param invoicedate
	 * @param instatus
	 * @param productName
	 * @param lotinfo
	 * @return
	 */
	public String getQueryHQL(String warehouseid, String instockid, String instatus,
			String productName, String lotinfo,String starttime,String endtime,String invoicetype)
	{
		String hql = "select distinct ta from InboundRequestInvoiceHeader ta,InboundRequestInvoiceDetail tb where 1=1 and ta.instockid=tb.instockid ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and ta.instockid='"+instockid+"'";
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
	 * ������뵥���� ��ѯ
	 * @param warehouseid
	 * @param instockid
	 * @param invoicedate
	 * @param instatus
	 * @param productName
	 * @param lotinfo
	 * @return
	 */
	public String getQueryHQLCount(String warehouseid, String instockid, String instatus,
			String productName, String lotinfo,String starttime,String endtime,String invoicetype)
	{
		String hql = "select count(distinct ta) from InboundRequestInvoiceHeader ta,InboundRequestInvoiceDetail tb where 1=1 and ta.instockid=tb.instockid ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and ta.instockid='"+instockid+"'";
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
	 * ������뵥���� ��ѯ
	 * @param warehouseid
	 * @param instockid
	 * @param invoicedate
	 * @param instatus
	 * @param productName
	 * @param lotinfo
	 * @return
	 */
	public String getQueryHQLOtherHQL(String warehouseid, String instockid, String instatus,
			String productName, String lotinfo,String starttime,String endtime,String invoicetype)
	{
		String hql = "select distinct ta from InboundRequestInvoiceHeader ta,InboundRequestInvoiceDetail tb where 1=1 and ta.instockid=tb.instockid ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and ta.instockid='"+instockid+"'";
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
	 * ������뵥���� ��ѯ
	 * @param warehouseid
	 * @param instockid
	 * @param invoicedate
	 * @param instatus
	 * @param productName
	 * @param lotinfo
	 * @return
	 */
	public String getQueryHQLOtherCount(String warehouseid, String instockid, String instatus,
			String productName, String lotinfo,String starttime,String endtime,String invoicetype)
	{
		String hql = "select count(distinct ta) from InboundRequestInvoiceHeader ta,InboundRequestInvoiceDetail tb where 1=1 and ta.instockid=tb.instockid ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and ta.instockid='"+instockid+"'";
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
	/**
	 * ������뵥���� �����֤ ��ѯ
	 * @param instockid
	 * @return
	 */
	public InboundRequestInvoiceHeader getDaoQueryHql(String instockid,EntityDAO dao)
	{
		String hql = "from InboundRequestInvoiceHeader where 1=1  ";
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and instockid='"+instockid+"'";
		}
		InboundRequestInvoiceHeader obj =null;
		java.util.List<InboundRequestInvoiceHeader> ls = dao.searchEntities(hql);
		if(ls!=null && ls.size()>0){
			obj = ls.get(0);
		}
		
		return obj;
	}
	/**
	 * ������뵥���� ���
	 * @param instockid
	 * @return
	 */
	public String getUpdateAuditsql(String instockid,String auditmanid)
	{
		String time = StrTools.getCurrDateTime(2);
		String hql = "update InboundRequestInvoiceHeader set  instatus='2',auditmanid='"+auditmanid+"',auditdate='"+time+"' where 1=1 ";
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and instockid='"+instockid+"'";
		}
		return hql;
	}
	/**
	 * ������뵥���� ȡ�����
	 * @param instockid
	 * @return
	 */
	public String getUpdateCancelAuditsql(String instockid)
	{
		String hql = "update InboundRequestInvoiceHeader set  instatus='1',auditmanid='',auditdate='' where 1=1 ";
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and instockid='"+instockid+"'";
		}
		return hql;
	}
	/**
	 * ������뵥���� �������뵥
	 * @param instockid
	 * @return
	 */
	public String getUpdateDeleteAuditsql(String instockid)
	{
		String hql = "update InboundRequestInvoiceHeader set instatus='5' where 1=1 ";
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and instockid='"+instockid+"'";
		}
		return hql;
	}
	/**
	 * ������뵥���� �������뵥
	 * @param instockid
	 * @return
	 */
	public String getUpdateCloseAuditsql(String instockid)
	{
		String time = StrTools.getCurrDateTime(2);
		String hql = "update InboundRequestInvoiceHeader set  instatus='4',confirmdate='"+time+"' where 1=1 ";
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and instockid='"+instockid+"'";
		}
		return hql;
	}
	/**
	 * ֻ����˼��󶨵ĵ���״̬ �ſ��԰󶨣�RF -> �󶨣� 
	 * @param warehouseid �ֿ��
	 * @return
	 */
	public java.util.List<InboundRequestInvoiceHeader> getInboundRIHList(String warehouseid,EntityDAO dao)
	{
		String hql = "from InboundRequestInvoiceHeader where ((instatus in('2','3') and invoicetypeid='9') or(instatus in('1','3') and invoicetypeid!='9'))"; 
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and warehouseid='"+warehouseid+"'";
		}
		InboundRequestInvoiceHeader obj =null;
		java.util.List<InboundRequestInvoiceHeader> ls = dao.searchEntities(hql);
		return ls;
	}
    public String addRequestInBound(java.util.List<InboundRequestInvoiceDetail> lsRequestDetail, InboundRequestInvoiceHeader Header,EntityDAO dao) throws Exception {
        String strMsg = "�����ɹ�!";
        if(Header!=null){
        	//ͬ��  ������뵥��
            synchronized (Header.getInstockid()) {
            	Session session = dao.getSession();
        		try {
        			Transaction tx = session.beginTransaction();
        			session.save(Header); //������ⵥͷ
        			if(lsRequestDetail!=null && lsRequestDetail.size()>0){
        				for(int i=0;i<lsRequestDetail.size();i++){
        					session.save(lsRequestDetail.get(i));
            			}
        			}
        			tx.commit();	
        		} catch (HibernateException he) {
        			Logger.error("����������뵥"+Header.getInstockid()+"��������ʧ��"+he.getMessage());
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