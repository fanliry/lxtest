package com.wms3.bms.standard.entity.inbound.lxyy;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;

/**
 * 
 * 描述: 绑定记录表
 * @author yao 2013-3-4
 */
public class BindingRecord implements java.io.Serializable {

     private String bandrecordid;      	//绑定id
     private String instockid;  	    //申请单id
     private String instockdetailid;  	//申请明细id
     private String productid;			//品名id
     private String punit;			    //单位
     private String traycode;			//托盘条码
     private String bandrecordtype;	    //绑定记录类型（1，成品入库；2销售退库）
     private String printdate;			//生产日期
     private String lotid;			    //批号类型
	 private String lotinfo;			//具体批号
	 private double num;			    //数量
	 private String status;			    //状态（1，未生成单据；2，已生成单据）
	 private String bandmanid;			//绑定人id
	 private String createtime;			//创建时间
	 private String productStatus;	//物品状态
	 private String productStatusName;	//物品状态名称
     public String getProductStatusName() {
		return productStatusName;
	}
	public void setProductStatusName(String productStatusName) {
		this.productStatusName = productStatusName;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	private String remark;      	    //备注
     private String Reserved1;      	//预留字段1
     private String Reserved2;      	//预留字段2
     
     //派生属性
     private String invoicetypename;  	//类型名(成品入库，销售退库)
     private String statusname;         //状态（1，已生成单据；2，未生成单据）
     private String bandmanname;        //绑定人名 
     private String m_strUnitName;	     // 单位名
     private String productCode;//产品编码
    
	private String productName;        //品名
	public String getBandrecordid() {
		return bandrecordid;
	}
	
	 public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public void setBandrecordid(String bandrecordid) {
		this.bandrecordid = bandrecordid;
	}
	public String getInstockid() {
		return instockid;
	}
	public void setInstockid(String instockid) {
		this.instockid = instockid;
	}
	public String getInstockdetailid() {
		return instockdetailid;
	}
	public void setInstockdetailid(String instockdetailid) {
		this.instockdetailid = instockdetailid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getTraycode() {
		return traycode;
	}
	public void setTraycode(String traycode) {
		this.traycode = traycode;
	}
	public String getBandrecordtype() {
		return bandrecordtype;
	}
	public void setBandrecordtype(String bandrecordtype) {
		this.bandrecordtype = bandrecordtype;
	}
	public String getPrintdate() {
		return printdate;
	}
	public void setPrintdate(String printdate) {
		this.printdate = printdate;
	}
	public String getLotid() {
		return lotid;
	}
	public void setLotid(String lotid) {
		this.lotid = lotid;
	}
	public String getLotinfo() {
		return lotinfo;
	}
	public void setLotinfo(String lotinfo) {
		this.lotinfo = lotinfo;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBandmanid() {
		return bandmanid;
	}
	public void setBandmanid(String bandmanid) {
		this.bandmanid = bandmanid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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
	public String getStatusname() {
		return statusname;
	}
	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
	public String getBandmanname() {
		return bandmanname;
	}
	public void setBandmanname(String bandmanname) {
		this.bandmanname = bandmanname;
	}
	/**
	 * 入库申请单管理 打印绑定记录
	 * @param instockid（入库申请单id）
	 * @return
	 */
	public String getQueryHQLByid(String instockid)
	{
		String hql = "from BindingRecord where 1=1 ";
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and instockid='"+instockid+"'";
		}
		hql += " order by bandrecordid";
		return hql;
	}
	/**
	 * 功能 根据绑定id获取绑定记录
	 * @param recordid
	 * @param dao
	 * @return
	 */
		public BindingRecord getQueryHQLByRecordid(String recordid,EntityDAO dao)
		{
			String hql = "from BindingRecord where 1=1 ";
			if(recordid != null && recordid.trim().length() > 0){
				hql += " and bandrecordid='"+recordid+"'";
			}
			BindingRecord obj =null;
			java.util.List<BindingRecord> ls = dao.searchEntities(hql);
			if(ls!=null && ls.size()>0){
				obj = ls.get(0);
			}
			return obj;
		}
	/**
	 * 入库申请单管理 取消绑定记录
	 * @param instockid（绑定记录id）
	 * @return
	 */
	public String getQueryHQLByRecordid(String recordid)
	{
		String hql = "from BindingRecord where 1=1 ";
		if(recordid != null && recordid.trim().length() > 0){
			hql += " and bandrecordid='"+recordid+"'";
		}
		return hql;
	}
/**
 * 新建入库单查询
 * @param warehouseid
 * @param instockid
 * @param isform(是否开单)
 * @param type（成品入库，销售退库等）
 * @param lotinfo
 * @param starttime
 * @param endtime
 * @return
 */
	public String getQueryHQL(String warehouseid, String instockid, String isform,
			String invoicetype, String lotinfo,String starttime,String endtime)
	{
		String hql = "select tb from InboundRequestInvoiceHeader ta,BindingRecord tb where 1=1 and ta.instockid=tb.instockid ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and ta.instockid like '%"+instockid+"%'";
		}
		if(starttime != null && starttime.trim().length() > 0){
			hql += " and tb.createtime>='"+starttime+"'";
		}
		if(endtime != null && endtime.trim().length() > 0){
			hql += " and tb.createtime<='"+endtime+" 24:59:59'";
		}
		if(isform != null && isform.trim().length() > 0){
			hql += " and tb.status='"+isform+"'";
		}
		if(invoicetype != null && invoicetype.trim().length() > 0){
			hql += " and tb.bandrecordtype ='"+invoicetype+"'";
		}
		if(lotinfo != null && lotinfo.trim().length() > 0){
			hql += " and tb.lotinfo like'%"+lotinfo+"%'";
		}
		hql += " order by ta.warehouseid,ta.instockid";
		return hql;
	}
	/**
	 * 新建入库单查询
	 * @param warehouseid
	 * @param instockid
	 * @param isform(是否开单)
	 * @param type（成品入库，销售退库等）
	 * @param lotinfo
	 * @param starttime
	 * @param endtime
	 * @return
	 */
		public String getQueryHQLCount(String warehouseid, String instockid, String isform,
				String invoicetype, String lotinfo,String starttime,String endtime)
		{
			String hql = "select count(tb) from InboundRequestInvoiceHeader ta,BindingRecord tb where 1=1 and ta.instockid=tb.instockid ";
			if(warehouseid != null && warehouseid.trim().length() > 0){
				hql += " and ta.warehouseid='"+warehouseid+"'";
			}
			if(instockid != null && instockid.trim().length() > 0){
				hql += " and ta.instockid like '%"+instockid+"%'";
			}
			if(starttime != null && starttime.trim().length() > 0){
				hql += " and tb.createtime>='"+starttime+"'";
			}
			if(endtime != null && endtime.trim().length() > 0){
				hql += " and tb.createtime<='"+endtime+" 24:59:59'";
			}
			if(isform != null && isform.trim().length() > 0){
				hql += " and tb.status='"+isform+"'";
			}
			if(invoicetype != null && invoicetype.trim().length() > 0){
				hql += " and tb.bandrecordtype ='"+invoicetype+"'";
			}else{
				hql += " and tb.bandrecordtype !='9'"; //成品入库
			}
			if(lotinfo != null && lotinfo.trim().length() > 0){
				hql += " and tb.lotinfo like'%"+lotinfo+"%'";
			}
			return hql;
	}
		/**
		 * 新建入库单查询
		 * @param warehouseid
		 * @param instockid
		 * @param isform(是否开单)
		 * @param type（成品入库，销售退库等）
		 * @param lotinfo
		 * @param starttime
		 * @param endtime
		 * @return
		 */
			public String getQueryHQLOther(String warehouseid, String instockid, String isform,
					String invoicetype, String lotinfo,String starttime,String endtime)
			{
				String hql = "select tb from InboundRequestInvoiceHeader ta,BindingRecord tb where 1=1 and ta.instockid=tb.instockid ";
				if(warehouseid != null && warehouseid.trim().length() > 0){
					hql += " and ta.warehouseid='"+warehouseid+"'";
				}
				if(instockid != null && instockid.trim().length() > 0){
					hql += " and ta.instockid like '%"+instockid+"%'";
				}
				if(starttime != null && starttime.trim().length() > 0){
					hql += " and tb.createtime>='"+starttime+"'";
				}
				if(endtime != null && endtime.trim().length() > 0){
					hql += " and tb.createtime<='"+endtime+" 24:59:59'";
				}
				if(isform != null && isform.trim().length() > 0){
					hql += " and tb.status='"+isform+"'";
				}
				if(invoicetype != null && invoicetype.trim().length() > 0){
					hql += " and tb.bandrecordtype ='"+invoicetype+"'";
				}else{
					hql += " and tb.bandrecordtype !='9'"; //成品入库
				}
				if(lotinfo != null && lotinfo.trim().length() > 0){
					hql += " and tb.lotinfo like'%"+lotinfo+"%'";
				}
				hql += " order by ta.warehouseid,ta.instockid";
				return hql;
			}
			/**
			 * 新建入库单查询
			 * @param warehouseid
			 * @param instockid
			 * @param isform(是否开单)
			 * @param type（成品入库，销售退库等）
			 * @param lotinfo
			 * @param starttime
			 * @param endtime
			 * @return
			 */
				public String getQueryHQLCountOther(String warehouseid, String instockid, String isform,
						String invoicetype, String lotinfo,String starttime,String endtime)
				{
					String hql = "select count(tb) from InboundRequestInvoiceHeader ta,BindingRecord tb where 1=1 and ta.instockid=tb.instockid ";
					if(warehouseid != null && warehouseid.trim().length() > 0){
						hql += " and ta.warehouseid='"+warehouseid+"'";
					}
					if(instockid != null && instockid.trim().length() > 0){
						hql += " and ta.instockid like '%"+instockid+"%'";
					}
					if(starttime != null && starttime.trim().length() > 0){
						hql += " and tb.createtime>='"+starttime+"'";
					}
					if(endtime != null && endtime.trim().length() > 0){
						hql += " and tb.createtime<='"+endtime+" 24:59:59'";
					}
					if(isform != null && isform.trim().length() > 0){
						hql += " and tb.status='"+isform+"'";
					}
					if(invoicetype != null && invoicetype.trim().length() > 0){
						hql += " and tb.bandrecordtype ='"+invoicetype+"'";
					}
					if(lotinfo != null && lotinfo.trim().length() > 0){
						hql += " and tb.lotinfo like'%"+lotinfo+"%'";
					}
					return hql;
			}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPunit() {
		return punit;
	}
	public void setPunit(String punit) {
		this.punit = punit;
	}
	public String getM_strUnitName() {
		return m_strUnitName;
	}
	public void setM_strUnitName(String unitName) {
		m_strUnitName = unitName;
	}
    /**
     * 取消绑定记录
     * @param RequestDetail
     * @param record
     * @param dao
     * @return
     * @throws Exception
     */
    public String UpdateRequestDInBound(InboundRequestInvoiceDetail RequestDetail, BindingRecord record,EntityDAO dao) throws Exception {
        String strMsg = "操作成功!";
        if(RequestDetail!=null){
        	//同步  入库申请单号
            synchronized (RequestDetail.getInstockdetailid()) {
            	Session session = dao.getSession();
        		try {
        			Transaction tx = session.beginTransaction();
        			session.update(RequestDetail); //更新申请单明细
        			session.delete(record); //删除记录
        			tx.commit();	
        		} catch (HibernateException he) {
        			Logger.error("增加入库申请单"+RequestDetail.getInstockid()+" 取消绑定错误 "+he.getMessage());
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
	 * 根据入库单 查询所有入库单明细
	 * @param recordid
	 * @param dao
	 * @return
	 */
	public java.util.List<BindingRecord> getQueryHQLByid(String instockid,EntityDAO dao)
	{
		String hql = "from BindingRecord where  bandrecordid in (select bandrecordid from InboundDetail where 1=1 ";
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and instockid='"+instockid+"')";
		}else{
			hql += " and 1!=1)";
		}
		java.util.List<BindingRecord> ls = dao.searchEntities(hql);
		return ls;
	}
     

}