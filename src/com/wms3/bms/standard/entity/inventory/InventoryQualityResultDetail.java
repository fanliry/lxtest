package com.wms3.bms.standard.entity.inventory;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * �������ʼ����굥
 * @author yao
 *
 */
public class InventoryQualityResultDetail 
{
	private String m_strCheckResultDetailId;		/*�ʼ�������ϸID*/
	private String m_strCheckResultId;				/*�ʼ�����ID*/
	private String m_strProductId;					/*��Ʒ����*/
	private String m_strPrintDate;					/*��ӡ����*/
	private String m_strNewProductStatus;   		/*����Ʒ״̬���������������� 0112*/
	private String m_strOldProductStatus;   		/*ԭ��Ʒ״̬���������������� 0112*/
	private String	m_strLotNumber;					/*����*/
	private String	m_strInventoryid;				/*���id*/
	private String	m_strCarspaceid;				/*��λ��*/
	private double	m_iProductNum;						/*����*/
	
	
	
	/*��������*/
	private String m_strProductName;				/*Ʒ�����*/
	private String m_strUnit;						/*��λ*/
	
	private String m_strNewProductStatusName;	/*����Ʒ״̬���������������� 0112*/
	
	private String m_strOldProductStatusName;	/*ԭ��Ʒ״̬���������������� 0112*/
	
	public InventoryQualityResultDetail() {}

	public String getM_strNewProductStatusName() {
		return m_strNewProductStatusName;
	}

	public void setM_strNewProductStatusName(String mStrNewProductStatusName) {
		m_strNewProductStatusName = mStrNewProductStatusName;
	}

	public String getM_strOldProductStatusName() {
		return m_strOldProductStatusName;
	}

	public void setM_strOldProductStatusName(String mStrOldProductStatusName) {
		m_strOldProductStatusName = mStrOldProductStatusName;
	}

	
	public double getM_iProductNum() {
		return m_iProductNum;
	}

	public String getM_strLotNumber() {
		return m_strLotNumber;
	}

	public void setM_strLotNumber(String mStrLotNumber) {
		m_strLotNumber = mStrLotNumber;
	}

	public void setM_iProductNum(double productNum) {
		m_iProductNum = productNum;
	}

	public String getM_strUnit() {
		return m_strUnit;
	}

	public void setM_strUnit(String unit) {
		m_strUnit = unit;
	}


	public String getM_strCheckResultDetailId() {
		return m_strCheckResultDetailId;
	}

	public void setM_strCheckResultDetailId(String checkResultDetailId) {
		m_strCheckResultDetailId = checkResultDetailId;
	}

	public String getM_strCheckResultId() {
		return m_strCheckResultId;
	}

	public void setM_strCheckResultId(String checkResultId) {
		m_strCheckResultId = checkResultId;
	}

	public String getM_strNewProductStatus() {
		return m_strNewProductStatus;
	}

	public void setM_strNewProductStatus(String newProductStatus) {
		m_strNewProductStatus = newProductStatus;
	}

	public String getM_strOldProductStatus() {
		return m_strOldProductStatus;
	}

	public void setM_strOldProductStatus(String oldProductStatus) {
		m_strOldProductStatus = oldProductStatus;
	}

	public String getM_strPrintDate() {
		return m_strPrintDate;
	}

	public void setM_strPrintDate(String printDate) {
		m_strPrintDate = printDate;
	}
	/**
	 * ���ܣ�������Ϣ��ȡ��Ϣ
	 * @param dao
	 * @param id ��λID
	 * @return
	 */
	public InventoryQualityResultDetail getInfoByInfo(EntityDAO dao, String check_result_id, String package_id, String print_date, String new_sample_category,
			String new_product_status, String old_sample_category, String old_product_status){
		InventoryQualityResultDetail ta = null;
		
		String hql = "From InventoryQualityResultDetail where 1=1" +
				" and m_strCheckResultId='" + check_result_id + "'" +
				" and m_strProductId='"+ package_id + "'" +
				" and m_strPrintDate='" + print_date + "'" +
				" and m_strNewSampleCategory='" + new_sample_category + "'" +
				" and m_strNewProductStatus='" + new_product_status + "'" + 
				" and m_strOldSampleCategory='" + old_sample_category + "'" + 
				" and m_strOldProductStatus='" + old_product_status + "'";
		List ls = dao.searchEntities(hql);
		if(ls != null && ls.size() > 0){
			ta = (InventoryQualityResultDetail)ls.get(0);
		}
		return ta;
	}
	/**
	 * ���ܣ�����ID��ȡ��Ϣ
	 * @param dao
	 * @param id ��λID
	 * @return
	 */
	public InventoryQualityResultDetail getInfoById(EntityDAO dao, String id)
	{
		InventoryQualityResultDetail ta = null;
		
		String hql = "From InventoryCheckResultDetail where m_strCheckResultDetailId='"+ id + "'";
		List ls = dao.searchEntities(hql);
		if(ls != null && ls.size() == 1)
		{
			ta = (InventoryQualityResultDetail)ls.get(0);
		}
		return ta;
	}

	/**
	 * ����ID��ȡ�б�
	 * @param dao
	 * @param id
	 * @return
	 */
	public List getListByRId(EntityDAO dao, String id){
		String hql = "from InventoryQualityResultDetail where m_strCheckResultId='" + id + "'";
		List ls = dao.searchEntities(hql);
		return ls;
	}

	public String getM_strProductName() {
		return m_strProductName;
	}

	public void setM_strProductName(String productName) {
		m_strProductName = productName;
	}

	public String getM_strProductId() {
		return m_strProductId;
	}

	public void setM_strProductId(String productId) {
		m_strProductId = productId;
	}


	public String getM_strInventoryid() {
		return m_strInventoryid;
	}

	public void setM_strInventoryid(String inventoryid) {
		m_strInventoryid = inventoryid;
	}

	public String getM_strCarspaceid() {
		return m_strCarspaceid;
	}

	public void setM_strCarspaceid(String carspaceid) {
		m_strCarspaceid = carspaceid;
	}

	
}