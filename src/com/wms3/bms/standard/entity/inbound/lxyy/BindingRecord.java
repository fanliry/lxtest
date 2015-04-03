package com.wms3.bms.standard.entity.inbound.lxyy;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;

/**
 * 
 * ����: �󶨼�¼��
 * @author yao 2013-3-4
 */
public class BindingRecord implements java.io.Serializable {

     private String bandrecordid;      	//��id
     private String instockid;  	    //���뵥id
     private String instockdetailid;  	//������ϸid
     private String productid;			//Ʒ��id
     private String punit;			    //��λ
     private String traycode;			//��������
     private String bandrecordtype;	    //�󶨼�¼���ͣ�1����Ʒ��⣻2�����˿⣩
     private String printdate;			//��������
     private String lotid;			    //��������
	 private String lotinfo;			//��������
	 private double num;			    //����
	 private String status;			    //״̬��1��δ���ɵ��ݣ�2�������ɵ��ݣ�
	 private String bandmanid;			//����id
	 private String createtime;			//����ʱ��
	 private String productStatus;	//��Ʒ״̬
	 private String productStatusName;	//��Ʒ״̬����
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
	private String remark;      	    //��ע
     private String Reserved1;      	//Ԥ���ֶ�1
     private String Reserved2;      	//Ԥ���ֶ�2
     
     //��������
     private String invoicetypename;  	//������(��Ʒ��⣬�����˿�)
     private String statusname;         //״̬��1�������ɵ��ݣ�2��δ���ɵ��ݣ�
     private String bandmanname;        //������ 
     private String m_strUnitName;	     // ��λ��
     private String productCode;//��Ʒ����
    
	private String productName;        //Ʒ��
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
	 * ������뵥���� ��ӡ�󶨼�¼
	 * @param instockid��������뵥id��
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
	 * ���� ���ݰ�id��ȡ�󶨼�¼
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
	 * ������뵥���� ȡ���󶨼�¼
	 * @param instockid���󶨼�¼id��
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
 * �½���ⵥ��ѯ
 * @param warehouseid
 * @param instockid
 * @param isform(�Ƿ񿪵�)
 * @param type����Ʒ��⣬�����˿�ȣ�
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
	 * �½���ⵥ��ѯ
	 * @param warehouseid
	 * @param instockid
	 * @param isform(�Ƿ񿪵�)
	 * @param type����Ʒ��⣬�����˿�ȣ�
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
				hql += " and tb.bandrecordtype !='9'"; //��Ʒ���
			}
			if(lotinfo != null && lotinfo.trim().length() > 0){
				hql += " and tb.lotinfo like'%"+lotinfo+"%'";
			}
			return hql;
	}
		/**
		 * �½���ⵥ��ѯ
		 * @param warehouseid
		 * @param instockid
		 * @param isform(�Ƿ񿪵�)
		 * @param type����Ʒ��⣬�����˿�ȣ�
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
					hql += " and tb.bandrecordtype !='9'"; //��Ʒ���
				}
				if(lotinfo != null && lotinfo.trim().length() > 0){
					hql += " and tb.lotinfo like'%"+lotinfo+"%'";
				}
				hql += " order by ta.warehouseid,ta.instockid";
				return hql;
			}
			/**
			 * �½���ⵥ��ѯ
			 * @param warehouseid
			 * @param instockid
			 * @param isform(�Ƿ񿪵�)
			 * @param type����Ʒ��⣬�����˿�ȣ�
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
     * ȡ���󶨼�¼
     * @param RequestDetail
     * @param record
     * @param dao
     * @return
     * @throws Exception
     */
    public String UpdateRequestDInBound(InboundRequestInvoiceDetail RequestDetail, BindingRecord record,EntityDAO dao) throws Exception {
        String strMsg = "�����ɹ�!";
        if(RequestDetail!=null){
        	//ͬ��  ������뵥��
            synchronized (RequestDetail.getInstockdetailid()) {
            	Session session = dao.getSession();
        		try {
        			Transaction tx = session.beginTransaction();
        			session.update(RequestDetail); //�������뵥��ϸ
        			session.delete(record); //ɾ����¼
        			tx.commit();	
        		} catch (HibernateException he) {
        			Logger.error("����������뵥"+RequestDetail.getInstockid()+" ȡ���󶨴��� "+he.getMessage());
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
	 * ������ⵥ ��ѯ������ⵥ��ϸ
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