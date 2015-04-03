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
 * 描述: 入库单表
 * @author yao 2013-3-4
 */
public class InboundHeader implements java.io.Serializable {

     private String instockid;  	    //入库单id
     private String inrequestid;      	//申请单id
     private String invoicetypeid;  	//入库单类型id(成品入库，销售退库)
     private String uploadflag;     	//上传标志0-已上传（默认）1-未上传
     private String invoicedate;    	//建单日期
     private String createtime;     	//单据生成时间
     private String finishtime;     	//单据确认时间
     private String createmanid;    	//单据生成人员编号
     private String warehouseid;    	//收货仓库
     private String instatus;       	//入库单据状态id 1-新建， 3-确认，5-作废
     private String deliveryunits;    	//交货单位
     
     private String remark;      	    //备注
     private String Reserved1;      	//预留字段1
     private String Reserved2;      	//预留字段2
     
     //派生属性
     private String invoicetypename;  	//类型名(成品入库，销售退库)
     private String createmanname;      //制单人名
     private String instatusname;       //入库单据状态id id 1-新建，3-确认，4-作废
     private String warehousename;    	//收货仓库名
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
	 * 入库单管理 查询
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
	 * 入库单管理 查询
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
	 * 入库单管理 查询
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
	 * 入库单管理 查询
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
	 * 入库申请单管理 作废验证 查询
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
	 * 入库单 查询
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
	 * 入库单管理 作废入库单
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
	 * 获取没有确定及没有作废的入库单（RF -> 收货）
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
     * 新建入库单及明细详细
     */
    public String addInBound(List<InboundDetail> lsInboundDetail, List<BindingRecord> lsBindingRecord,InboundHeader Header,EntityDAO dao) throws Exception {
        String strMsg = "操作成功!";
        if(Header!=null){
        	//同步  入库单号
            synchronized (Header.getInstockid()) {
            	Session session = dao.getSession();
        		try {
        			Transaction tx = session.beginTransaction();
        			session.save(Header); //保存入库单头
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
        			Logger.error("增加入库单"+Header.getInstockid()+"出错！保存失败"+he.getMessage());
        		}
        		finally
        		{
            		dao.closeSession(session);
            	}    
            }
        }else{
        	strMsg = "操作失败!";
        }
        return strMsg;
    }
    /**
     * 添加收货信息及作业信息
     */
    public String addInBoundJob(InboundHeader invoice, InboundDetail invoicedetail,InoutJob job,InoutJobdetail jobdetail,EntityDAO dao) throws Exception {
        String strMsg = "操作成功!";
        if(invoice!=null){
        	//同步  入库单号
            synchronized (invoice.getInstockid()) {
            	Session session = dao.getSession();
            	Transaction tx =null;
        		try {
        			tx = session.beginTransaction();
        			session.update(invoice); //更新入库单据
        			session.update(invoicedetail); //更新入库单明细
        			session.save(job); //保存作业
        			session.save(jobdetail); //保存作业明细
        			tx.commit();	
        			Logger.info("添加收货信息及作业信息成功");
        		}catch (HibernateException he) {
        			if(tx != null){
                        tx.rollback();
                    }
        			Logger.error("增加入库单"+invoice.getInstockid()+"出错！保存失败"+he.getMessage());
        		}
        		finally
        		{
            		dao.closeSession(session);
            	}    
            }
        }else{
        	strMsg = "操作失败!";
        }
        return strMsg;
    }
    /**
     * 添加收货信息及作业信息
     */
    public String updateInboundABR(InboundHeader invoice,List<BindingRecord> lsBindingRecord,EntityDAO dao) throws Exception {
        String strMsg = "操作成功!";
        if(invoice!=null){
        	//同步  入库单号
            synchronized (invoice.getInstockid()) {
            	Session session = dao.getSession();
        		try {
        			Transaction tx = session.beginTransaction();
        			session.update(invoice); //更新入库单据
        			
        			if(lsBindingRecord!=null && lsBindingRecord.size()>0){
        				for(int i=0;i<lsBindingRecord.size();i++){
        					session.update(lsBindingRecord.get(i));
            			}
        			}
        			tx.commit();	
        		}catch (HibernateException he) {
        		
        			Logger.error("增加入库单"+invoice.getInstockid()+"出错！保存失败"+he.getMessage());
        		}
        		finally
        		{
            		dao.closeSession(session);
            	}    
            }
        }else{
        	strMsg = "操作失败!";
        }
        return strMsg;
    }
    
    /**
     *  功能：RF收货，生成作业，添加调度
     */
	public String addInBoundJobz(InboundHeader invoice,
			InboundDetail invoicedetail, InoutJob job,
			InoutJobdetail jobdetail, ScheduleTask task, EntityDAO dao) {
		String strMsg = "操作成功!";
        if(invoice!=null){
        	//同步  入库单号
            synchronized (invoice.getInstockid()) {
            	Session session = dao.getSession();
            	Transaction tx =null;
        		try {
        			tx = session.beginTransaction();
        			session.update(invoice); //更新入库单据
        			session.update(invoicedetail); //更新入库单明细
        			session.save(job); //保存作业
        			session.save(jobdetail); //保存作业明细
        			session.save(task); //添加调度作业
        			tx.commit();	
        			Logger.info("RF收货，生成作业，添加调度---> 成功");
        		}catch (HibernateException he) {
        			if(tx != null){
                        tx.rollback();
                    }
        			Logger.error("增加入库单"+invoice.getInstockid()+"出错！保存失败"+he.getMessage());
        		}
        		finally
        		{
            		dao.closeSession(session);
            	}    
            }
        }else{
        	strMsg = "操作失败!";
        }
        return strMsg;
		
	}
}