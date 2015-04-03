package com.wms3.bms.standard.entity.inbound.lxyy;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * 
 * ����: ��ⵥ��ϸ���ջ�����ϸ��
 * 
 * @author yao 2013-3-5
 */
public class InboundDetail implements java.io.Serializable {

	private String instockdetailid; // ��ⵥ��ϸid
	private String instockid; // ��ⵥid
	private String bandrecordid; // �󶨼�¼id ����
	private double innum; // �������
	private double getnum; // ʵ������   ����
	private String traycode; // ��������  ����
	private String lotid; // ��������  ����
	private String lotinfo; // ��������  ����
	private String status; // 1���½� 2���ջ� 3����ִ���ϼ� 4���ϼ���� ���� 4
	private String printdate; // ��������
	private String productid; // Ʒ��id
	private String punit; // ��λ
	private String obtainmanid; // RF�ջ��� ����
	private String obtaintime; // RF�ջ�ʱ��  ����
	private String finishtime; // �ϼ����ʱ�� ����
	private String productStatus; // ��Ʒ״̬

	private String remark; // ��ע
	private String Reserved1; // Ԥ���ֶ�1
	private String Reserved2; // Ԥ���ֶ�2

	// ��������
	private String statusname; // 1���½� 2���ջ� 3���ϼ�  ����
	private String productName; // ��Ʒ��
	private String obtainmanname; // RF�ջ�������  ����
	private String m_strUnitName; // ��λ��
	private String productCode;// ��Ʒ����

	private String productStatusName; // ��Ʒ״̬����

	public String getProductStatusName() {
		return productStatusName;
	}

	public void setProductStatusName(String productStatusName) {
		this.productStatusName = productStatusName;
	}

	public String getInstockdetailid() {
		return instockdetailid;
	}

	public void setInstockdetailid(String instockdetailid) {
		this.instockdetailid = instockdetailid;
	}

	public String getInstockid() {
		return instockid;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public void setInstockid(String instockid) {
		this.instockid = instockid;
	}

	public String getBandrecordid() {
		return bandrecordid;
	}

	public void setBandrecordid(String bandrecordid) {
		this.bandrecordid = bandrecordid;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public double getInnum() {
		return innum;
	}

	public void setInnum(double innum) {
		this.innum = innum;
	}

	public double getGetnum() {
		return getnum;
	}

	public void setGetnum(double getnum) {
		this.getnum = getnum;
	}

	public String getTraycode() {
		return traycode;
	}

	public void setTraycode(String traycode) {
		this.traycode = traycode;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrintdate() {
		return printdate;
	}

	public void setPrintdate(String printdate) {
		this.printdate = printdate;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getObtainmanid() {
		return obtainmanid;
	}

	public void setObtainmanid(String obtainmanid) {
		this.obtainmanid = obtainmanid;
	}

	public String getObtaintime() {
		return obtaintime;
	}

	public void setObtaintime(String obtaintime) {
		this.obtaintime = obtaintime;
	}

	public String getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
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

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getObtainmanname() {
		return obtainmanname;
	}

	public void setObtainmanname(String obtainmanname) {
		this.obtainmanname = obtainmanname;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * ��ⵥ���� �����ⵥ ��ʾ ��ⵥ��ϸ
	 * 
	 * @param instockid
	 *            ����ⵥid��
	 * @return
	 */
	public String getQueryHQLByid(String instockid) {
		String hql = "from InboundDetail where 1=1 ";
		if (instockid != null && instockid.trim().length() > 0) {
			hql += " and instockid='" + instockid + "'";
		}
		hql += " order by instockid";
		return hql;
	}

	/**
	 * ��ⵥ���� ��ӡ�ջ���Ϣ
	 * 
	 * @param instockid
	 *            ����ⵥid��
	 * @return
	 */
	public String getQuerySHByid(String instockid) {
		String hql = "from InboundDetail where 1=1 and status!='1' ";
		if (instockid != null && instockid.trim().length() > 0) {
			hql += " and instockid='" + instockid + "'";
		}
		hql += " order by bandrecordid";
		return hql;
	}

	/**
	 * ��ⵥ���� ȡ���ջ�
	 * 
	 * @param recordid
	 *            ���ջ���¼id��
	 * @return
	 */
	public String getQueryHQLByRecordid(String recordid) {
		String hql = "from InboundDetail where 1=1 ";
		if (recordid != null && recordid.trim().length() > 0) {
			hql += " and instockdetailid='" + recordid + "'";
		}
		return hql;
	}

	/**
	 * (RF�ջ�)
	 * 
	 * @param instockid
	 *            ��������뵥id��
	 * @param traycode
	 *            ���������룩
	 * @return
	 */
	public InboundDetail getDetailByidtray(String instockid, String traycode,
			EntityDAO dao) {
		String hql = "from InboundDetail where 1=1 ";
		if (instockid != null && instockid.trim().length() > 0) {
			hql += " and instockid='" + instockid + "'";
		}
		if (traycode != null && traycode.trim().length() > 0) {
			hql += " and traycode='" + traycode + "'";
		}
		hql += " order by instockdetailid";

		InboundDetail obj = null;
		java.util.List<InboundDetail> ls = dao.searchEntities(hql);
		if (ls != null && ls.size() > 0) {
			obj = ls.get(0);
		}

		return obj;
	}
	
	public double getSurplusnumByinvoiceid(String instockid,
			EntityDAO dao) {
		double surplusnum = 0;
		double sum = 0;
		double assignnum =0;
		String hql = "from InboundDetail where 1=1 ";
		if (instockid != null && instockid.trim().length() > 0) {
			hql += " and instockid='" + instockid + "'";
		}
		hql += " order by instockdetailid";

		InboundDetail obj = null;
		java.util.List<InboundDetail> ls = dao.searchEntities(hql);
		
		if (ls != null && ls.size() > 0) {
			for(int i=0;i<ls.size();i++){
				obj = ls.get(i);
				sum = sum+obj.getInnum();
				assignnum = assignnum+obj.getGetnum();
				
			}
			
		}
		surplusnum=sum-assignnum;
		return surplusnum;
	}
	
	/**
	 * 
	 * @param recordid
	 * @param dao
	 * @return
	 */
	public InboundDetail getQueryHQLByRecordid(String recordid, EntityDAO dao) {
		String hql = "from InboundDetail where 1=1 ";
		if (recordid != null && recordid.trim().length() > 0) {
			hql += " and instockdetailid='" + recordid + "'";
		}
		InboundDetail obj = null;
		java.util.List<InboundDetail> ls = dao.searchEntities(hql);
		if (ls != null && ls.size() > 0) {
			obj = ls.get(0);
		}

		return obj;
	}

	/**
	 * ������ⵥ ��ѯ������ⵥ��ϸ
	 * 
	 * @param recordid
	 * @param dao
	 * @return
	 */
	public java.util.List<InboundDetail> getQueryHQLByid(String instockid,
			EntityDAO dao) {
		String hql = "from InboundDetail where 1=1 ";
		if (instockid != null && instockid.trim().length() > 0) {
			hql += " and instockid='" + instockid + "'";
		}
		java.util.List<InboundDetail> ls = dao.searchEntities(hql);
		return ls;
	}

	/**
	 * ������ⵥ ��ѯû���ϼܵ���ⵥ��ϸ
	 * 
	 * @param recordid
	 * @param dao
	 * @return
	 */
	public java.util.List<InboundDetail> getQueryNotSJHQLByid(String instockid,
			EntityDAO dao) {
		String hql = "from InboundDetail where 1=1 and status!=4 ";
		if (instockid != null && instockid.trim().length() > 0) {
			hql += " and instockid='" + instockid + "'";
		}
		java.util.List<InboundDetail> ls = dao.searchEntities(hql);
		return ls;
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