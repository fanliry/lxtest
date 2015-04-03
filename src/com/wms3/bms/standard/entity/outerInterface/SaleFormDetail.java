package com.wms3.bms.standard.entity.outerInterface;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * ˵�������۵���ϸ
 * @author yao
 */
public class SaleFormDetail 
{
	private String m_strSaleFormDetailId;
	private String m_strSaleFormId; 		//���۵�id
	private String m_strPackageId;  		//��Ʒ����
	private String m_strPrintDate;  		//��ӡ����
	private String m_strBatch;  			//����
	private String m_strProductStatus;  	//״̬
	private double m_iSaleNum;				//����
	private double m_iOutNum;				//��������
	
	/*��������*/
	private String m_strPackageName;		//��Ʒ����
	private String m_strUnit;				//��Ʒ��λ
	private String m_strProductStatusName;	//��Ʒ״̬
	private String spec;					//��Ʒ���
	
	/**
	 * ����:Ĭ�Ϲ�����
	 */
	public SaleFormDetail() {}

	public double getM_iSaleNum() {
		return m_iSaleNum;
	}

	public void setM_iSaleNum(double m_iSaleNum) {
		this.m_iSaleNum = m_iSaleNum;
	}

	public double getM_iOutNum() {
		return m_iOutNum;
	}

	public void setM_iOutNum(double m_iOutNum) {
		this.m_iOutNum = m_iOutNum;
	}

	public String getM_strPackageId() {
		return m_strPackageId;
	}
	
	public void setM_strPackageId(String packageId) {
		m_strPackageId = packageId;
	}
	
	public String getM_strPackageName() {
		return m_strPackageName;
	}
	
	public void setM_strPackageName(String packageName) {
		m_strPackageName = packageName;
	}
	
	public String getM_strPrintDate() {
		return m_strPrintDate;
	}
	
	public void setM_strPrintDate(String printDate) {
		m_strPrintDate = printDate;
	}
	
	public String getM_strSaleFormDetailId() {
		return m_strSaleFormDetailId;
	}
	
	public void setM_strSaleFormDetailId(String saleFormDetailId) {
		m_strSaleFormDetailId = saleFormDetailId;
	}
	
	public String getM_strSaleFormId() {
		return m_strSaleFormId;
	}
	
	public void setM_strSaleFormId(String saleFormId) {
		m_strSaleFormId = saleFormId;
	}

	public String getM_strUnit() {
		return m_strUnit;
	}
	
	public void setM_strUnit(String unit) {
		m_strUnit = unit;
	}
	
	public List getListByRId(EntityDAO dao, String id)
	{
		String hql = "from SaleFormDetail where m_strSaleFormId='" + id + "' order by m_strPackageId, m_strPrintDate";
		List ls = dao.searchEntities(hql);
		return ls;
	}


	public String getM_strBatch() {
		return m_strBatch;
	}


	public void setM_strBatch(String batch) {
		m_strBatch = batch;
	}


	public String getM_strProductStatus() {
		return m_strProductStatus;
	}


	public void setM_strProductStatus(String productStatus) {
		m_strProductStatus = productStatus;
	}


	public String getM_strProductStatusName() {
		return m_strProductStatusName;
	}


	public void setM_strProductStatusName(String productStatusName) {
		m_strProductStatusName = productStatusName;
	}


	public String getSpec() {
		return spec;
	}


	public void setSpec(String spec) {
		this.spec = spec;
	}
	
}
