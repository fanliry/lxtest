package com.wms3.bms.standard.entity.outerInterface;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * 说明：销售单详细
 * @author yao
 */
public class SaleFormDetail 
{
	private String m_strSaleFormDetailId;
	private String m_strSaleFormId; 		//销售单id
	private String m_strPackageId;  		//物品代码
	private String m_strPrintDate;  		//打印日期
	private String m_strBatch;  			//批号
	private String m_strProductStatus;  	//状态
	private double m_iSaleNum;				//数量
	private double m_iOutNum;				//发出数量
	
	/*派生属性*/
	private String m_strPackageName;		//产品名称
	private String m_strUnit;				//产品单位
	private String m_strProductStatusName;	//物品状态
	private String spec;					//产品规格
	
	/**
	 * 功能:默认构造器
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
