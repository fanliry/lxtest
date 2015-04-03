package com.wms3.bms.standard.entity.inventory;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * 描述：质检结果详单
 * @author yao
 *
 */
public class InventoryQualityResultDetail 
{
	private String m_strCheckResultDetailId;		/*质检结果单明细ID*/
	private String m_strCheckResultId;				/*质检结果单ID*/
	private String m_strProductId;					/*产品代码*/
	private String m_strPrintDate;					/*打印日期*/
	private String m_strNewProductStatus;   		/*新物品状态（抽检结果）详见类型 0112*/
	private String m_strOldProductStatus;   		/*原物品状态（抽检结果）详见类型 0112*/
	private String	m_strLotNumber;					/*批号*/
	private String	m_strInventoryid;				/*库存id*/
	private String	m_strCarspaceid;				/*货位号*/
	private double	m_iProductNum;						/*数量*/
	
	
	
	/*派生属性*/
	private String m_strProductName;				/*品名规格*/
	private String m_strUnit;						/*单位*/
	
	private String m_strNewProductStatusName;	/*新物品状态（抽检结果）详见类型 0112*/
	
	private String m_strOldProductStatusName;	/*原物品状态（抽检结果）详见类型 0112*/
	
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
	 * 功能：根据信息获取信息
	 * @param dao
	 * @param id 车位ID
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
	 * 功能：根据ID获取信息
	 * @param dao
	 * @param id 车位ID
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
	 * 根据ID获取列表
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