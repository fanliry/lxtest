package com.wms3.bms.standard.entity.inbound.lxyy;

import com.ricosoft.common.dao.dataSource.EntityDAO;



/**
 * ����: ���뵥��ϸ
 * @author yao 2013-3-4
 */
public class InboundRequestInvoiceDetail  implements java.io.Serializable {

	 private String instockdetailid;	//���뵥��ϸID
	 private String instockid;      	//���뵥id
	 private String productid;			//Ʒ��id
	 private String printdate;			//��������
	 private double plannum;			//�ƻ�����
	 private double bindingnum;			//������
	 private String lotid;			    //��������
	 private String lotinfo;			//��������
	 private String remark;			    //��ע
	 private String punit;			    //��λ
	 private String productStatus;	//��Ʒ״̬
	 
	
	private String Reserved1;      	//Ԥ���ֶ�1
     private String Reserved2;      	//Ԥ���ֶ�2
     
     //��������
     protected String productName;	    // ��Ʒ��
     
     protected String m_strUnitName;	// ��λ��
     
     private String productCode;//��Ʒ����
     
    
	private String productStatusName;	//��Ʒ״̬����
     
     
     public String getProductStatusName() {
		return productStatusName;
	}
	public void setProductStatusName(String productStatusName) {
		this.productStatusName = productStatusName;
	}
	public EntityDAO  m_dao = null;
     

    /** default constructor */
    public InboundRequestInvoiceDetail(EntityDAO dao) {
    	m_dao = dao;
    }
    /** default constructor */
    public InboundRequestInvoiceDetail() {
    }

	public String getInstockdetailid() {
		return instockdetailid;
	}

	 public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
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


	public String getPrintdate() {
		return printdate;
	}
	
	 public String getProductStatus() {
			return productStatus;
		}
		public void setProductStatus(String productStatus) {
			this.productStatus = productStatus;
		}

	public void setPrintdate(String printdate) {
		this.printdate = printdate;
	}


	public double getPlannum() {
		return plannum;
	}


	public void setPlannum(double plannum) {
		this.plannum = plannum;
	}


	public double getBindingnum() {
		return bindingnum;
	}


	public void setBindingnum(double bindingnum) {
		this.bindingnum = bindingnum;
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


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getInstockid() {
		return instockid;
	}


	public void setInstockid(String instockid) {
		this.instockid = instockid;
	}
	/**
	 * ������뵥���� ���������뵥 ��ʾ ������뵥��ϸ
	 * @param instockid��������뵥id��
	 * @return
	 */
	public String getQueryHQLByid(String instockid)
	{
		String hql = "from InboundRequestInvoiceDetail where 1=1 ";
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and instockid='"+instockid+"'";
		}
		hql += " order by instockid";
		return hql;
	}
	/**
	 * ������뵥���� ���������뵥 ��ʾ ������뵥��ϸ
	 * @param instockid��������뵥id��
	 * @return
	 */
	public InboundRequestInvoiceDetail getDetailByid(String instockid,EntityDAO dao)
	{
		String hql = "from InboundRequestInvoiceDetail where 1=1 ";
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and instockid='"+instockid+"'";
		}
		hql += " order by instockdetailid";
		
		InboundRequestInvoiceDetail obj =null;
		java.util.List<InboundRequestInvoiceDetail> ls = dao.searchEntities(hql);
		if(ls!=null && ls.size()>0){
			obj = ls.get(0);
		}
		
		return obj;
	}
	/**
	 * RF���̰�(down)
	 * @param instockid��������뵥id��
	 * @return
	 */
	public InboundRequestInvoiceDetail getDetailByDownid(String instockid,String requestvoicedetailid,EntityDAO dao)
	{
		String hql = "from InboundRequestInvoiceDetail where 1=1 ";
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and instockid='"+instockid+"'";
		}
		if(requestvoicedetailid != null && requestvoicedetailid.trim().length() > 0){
			hql += " and instockdetailid > '"+requestvoicedetailid+"'";
		}
		hql += " order by instockdetailid";
		
		InboundRequestInvoiceDetail obj =null;
		java.util.List<InboundRequestInvoiceDetail> ls = dao.searchEntities(hql);
		if(ls!=null && ls.size()>0){
			obj = ls.get(0);
		}
		return obj;
	}
	/**
	 * RF���̰�(up)
	 * @param instockid��������뵥id��
	 * @return
	 */
	public InboundRequestInvoiceDetail getDetailByUpid(String instockid,String requestvoicedetailid,EntityDAO dao)
	{
		String hql = "from InboundRequestInvoiceDetail where 1=1 ";
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and instockid='"+instockid+"'";
		}
		if(requestvoicedetailid != null && requestvoicedetailid.trim().length() > 0){
			hql += " and instockdetailid < '"+requestvoicedetailid+"'";
		}
		hql += " order by instockdetailid desc";
		
		InboundRequestInvoiceDetail obj =null;
		java.util.List<InboundRequestInvoiceDetail> ls = dao.searchEntities(hql);
		if(ls!=null && ls.size()>0){
			obj = ls.get(0);
		}
		return obj;
	}
	/**
	 * RF��
	 * @param instockid��������뵥id��
	 * @return
	 */
	public InboundRequestInvoiceDetail getDetailByid(String instockid,String requestvoicedetailid,EntityDAO dao)
	{
		String hql = "from InboundRequestInvoiceDetail where 1=1 ";
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and instockid='"+instockid+"'";
		}
		if(requestvoicedetailid != null && requestvoicedetailid.trim().length() > 0){
			hql += " and instockdetailid = '"+requestvoicedetailid+"'";
		}
		hql += " order by instockdetailid desc";
		
		InboundRequestInvoiceDetail obj =null;
		java.util.List<InboundRequestInvoiceDetail> ls = dao.searchEntities(hql);
		if(ls!=null && ls.size()>0){
			obj = ls.get(0);
		}
		return obj;
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
    
}