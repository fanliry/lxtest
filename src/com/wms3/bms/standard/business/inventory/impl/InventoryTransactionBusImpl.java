package com.wms3.bms.standard.business.inventory.impl;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.inventory.IInventoryTransactionBus;
import com.wms3.bms.standard.dao.inventory.IInventoryTransactionDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryTransactionDaoImpl;
/**
 * 描述：库存交易的业务实现类
 * @author liuxh
 *
 */
public class InventoryTransactionBusImpl implements IInventoryTransactionBus {
    protected EntityDAO m_dao = null;
	protected IInventoryTransactionDao iTransactionDao = null;
	public InventoryTransactionBusImpl(EntityDAO dao)
	{
		this.m_dao = dao;
		this.iTransactionDao = new InventoryTransactionDaoImpl(dao);
	}
	
	public PagingTool getTransactionsLs(String fmwarehouseid,String fmwhAreaId, 
			String fmplatoon, String fmcolumn,String fmfloor, String fmCustomerId, String fmPackageId,
			String fmTrayCode, String towhAreaId,String toplatoon, String tocolumn, 
			String tofloor,String toCustomerId, String toPackageId, String toTrayCode,String fmDate, 
			String toDate,String url,int iCurrentPage,int iMaxRow) throws Exception {
		PagingTool pt = null;
		String strHQL = "";
		try {
			strHQL = getQueryHql(fmwarehouseid, fmwhAreaId, fmplatoon, fmcolumn, fmfloor, fmCustomerId, fmPackageId, fmTrayCode, 
					towhAreaId, toplatoon, tocolumn, tofloor, toCustomerId, toPackageId, toTrayCode, fmDate, toDate);
		    pt = CommonPagination.getPagingTool(m_dao, "select count(*)"+strHQL, strHQL, url, iCurrentPage, iMaxRow);
		} catch (Exception e) {
			throw new  Exception("获得库存交易记录 出错，InventoryTransactionBusImpl.getTransactionsLs："+e.getMessage());
		}
		
		return pt;
	}
	/**
	 * 功能：获得交易记录	
	 * @param fmwarehouseid fm仓库id
	 * @param fmwhAreaId    fm库区id
	 * @param fmplatoon     fm排
	 * @param fmcolumn      fm列
	 * @param fmfloor       fm层
	 * @param fmCustomerId  fm客户
	 * @param fmPackageId   fm产品id
	 * @param fmTrayCode    fm托盘条码
	 * @param towhAreaId    to库区id
	 * @param toplatoon     to排
	 * @param tocolumn      to列
	 * @param tofloor       to层
	 * @param toCustomerId  to客户
	 * @param toPackageId   to产品id
	 * @param toTrayCode    to托盘条码
	 * @param fmDate        开始时间
	 * @param toDate        结束时间
	 * @return
	 * @throws Exception
	 */
	String getQueryHql(String fmwarehouseid,String fmwhAreaId, 
			String fmplatoon, String fmcolumn,String fmfloor, String fmCustomerId, String fmPackageId,
			String fmTrayCode, String towhAreaId,String toplatoon, String tocolumn, 
			String tofloor,String toCustomerId, String toPackageId, String toTrayCode,String fmDate, 
			String toDate) throws Exception{
	    String strHQL = "";
	    try {
		
		StringBuilder strBud = new StringBuilder();  
		strBud.append("from InventoryTransaction a where 1=1");
		if (fmwarehouseid != null && fmwarehouseid.trim().length() > 0) {//fm 仓库id
			strBud.append("and a.warehouseid='").append(fmwarehouseid).append("'");
		}
		if (fmwhAreaId != null && fmwhAreaId.trim().length() > 0) {//fm 库区id
			strBud.append(" and a.fmwh_area_id='").append(fmwhAreaId).append("'");
		}
		strBud.append("and a.fmcargo_space_id in (select cargoSpaceId from BaseCargospace b where 1=1)");
		if (fmplatoon != null && fmplatoon.trim().length() > 0) {//fm 排
			strBud.insert(strBud.lastIndexOf(")"), " and b.csplatoon='"+fmplatoon+"'");
		}
		if (fmcolumn != null && fmcolumn.trim().length() > 0) {//fm 列
			strBud.insert(strBud.lastIndexOf(")"), " and b.cscolumn='"+fmcolumn+"'");
		}
		if (fmfloor != null && fmfloor.trim().length() > 0) {//fm 层
			strBud.insert(strBud.lastIndexOf(")"), " and b.csfloor='"+fmfloor+"'");
		}
		if (fmCustomerId != null && fmCustomerId.trim().length() > 0) {//fm 客户id
			strBud.append(" and a.fmcustomerid='").append(fmCustomerId).append("'");
		}
		if (fmPackageId != null && fmPackageId.trim().length() > 0) {//fm 产品id
			strBud.append("and a.fmproductid='").append(fmPackageId).append("'");
		}
		if (fmTrayCode != null && fmTrayCode.trim().length() > 0) {//fm 托盘条码
			strBud.append("and a.fmtraycode='").append(fmTrayCode).append("'");
		}
		if (towhAreaId != null && towhAreaId.trim().length() > 0) {//to 库区id
			strBud.append(" and a.towh_area_id='").append(towhAreaId).append("'");
		}
		strBud.append("and a.tocargo_space_id in (select cargoSpaceId from BaseCargospace b where 1=1)");
		if (toplatoon != null && toplatoon.trim().length() > 0) {//to 排
			strBud.insert(strBud.lastIndexOf(")"), " and b.csplatoon='"+toplatoon+"'");
		}
		if (tocolumn != null && tocolumn.trim().length() > 0) {//to 列
			strBud.insert(strBud.lastIndexOf(")"), " and b.cscolumn='"+tocolumn+"'");
		}
		if (tofloor != null && tofloor.trim().length() > 0) {//to 层
			strBud.insert(strBud.lastIndexOf(")"), " and b.csfloor='"+tofloor+"'");
		}
		if (toCustomerId != null && toCustomerId.trim().length() > 0) {//to 客户id
			strBud.append(" and a.tocustomerid='").append(toCustomerId).append("'");
		}
		if (toPackageId != null && toPackageId.trim().length() > 0) {//to 产品id
			strBud.append("and a.toproductid='").append(toPackageId).append("'");
		}
		if (toTrayCode != null && toTrayCode.trim().length() > 0) {//fm 托盘条码
			strBud.append("and a.totraycode='").append(toTrayCode).append("'");
		}
		if (fmDate != null && fmDate.trim().length() > 0) {//fm 时间
			strBud.append("and a.transactiontime>='").append(fmDate).append("'");
		}
		if (toDate != null && toDate.trim().length() > 0) {//to 时间
			strBud.append("and a.transactiontime<='").append(toDate).append("'");
		}
		strHQL = strBud.toString();
		
		} catch (Exception e) {
			throw new  Exception("获得库存交易的HQL语句出错，InventoryTransactionBusImpl.getQueryHql："+e.getMessage());
		}
		return strHQL;   
   }
}
