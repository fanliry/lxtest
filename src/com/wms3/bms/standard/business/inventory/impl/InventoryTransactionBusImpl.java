package com.wms3.bms.standard.business.inventory.impl;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.inventory.IInventoryTransactionBus;
import com.wms3.bms.standard.dao.inventory.IInventoryTransactionDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryTransactionDaoImpl;
/**
 * ��������潻�׵�ҵ��ʵ����
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
			throw new  Exception("��ÿ�潻�׼�¼ ����InventoryTransactionBusImpl.getTransactionsLs��"+e.getMessage());
		}
		
		return pt;
	}
	/**
	 * ���ܣ���ý��׼�¼	
	 * @param fmwarehouseid fm�ֿ�id
	 * @param fmwhAreaId    fm����id
	 * @param fmplatoon     fm��
	 * @param fmcolumn      fm��
	 * @param fmfloor       fm��
	 * @param fmCustomerId  fm�ͻ�
	 * @param fmPackageId   fm��Ʒid
	 * @param fmTrayCode    fm��������
	 * @param towhAreaId    to����id
	 * @param toplatoon     to��
	 * @param tocolumn      to��
	 * @param tofloor       to��
	 * @param toCustomerId  to�ͻ�
	 * @param toPackageId   to��Ʒid
	 * @param toTrayCode    to��������
	 * @param fmDate        ��ʼʱ��
	 * @param toDate        ����ʱ��
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
		if (fmwarehouseid != null && fmwarehouseid.trim().length() > 0) {//fm �ֿ�id
			strBud.append("and a.warehouseid='").append(fmwarehouseid).append("'");
		}
		if (fmwhAreaId != null && fmwhAreaId.trim().length() > 0) {//fm ����id
			strBud.append(" and a.fmwh_area_id='").append(fmwhAreaId).append("'");
		}
		strBud.append("and a.fmcargo_space_id in (select cargoSpaceId from BaseCargospace b where 1=1)");
		if (fmplatoon != null && fmplatoon.trim().length() > 0) {//fm ��
			strBud.insert(strBud.lastIndexOf(")"), " and b.csplatoon='"+fmplatoon+"'");
		}
		if (fmcolumn != null && fmcolumn.trim().length() > 0) {//fm ��
			strBud.insert(strBud.lastIndexOf(")"), " and b.cscolumn='"+fmcolumn+"'");
		}
		if (fmfloor != null && fmfloor.trim().length() > 0) {//fm ��
			strBud.insert(strBud.lastIndexOf(")"), " and b.csfloor='"+fmfloor+"'");
		}
		if (fmCustomerId != null && fmCustomerId.trim().length() > 0) {//fm �ͻ�id
			strBud.append(" and a.fmcustomerid='").append(fmCustomerId).append("'");
		}
		if (fmPackageId != null && fmPackageId.trim().length() > 0) {//fm ��Ʒid
			strBud.append("and a.fmproductid='").append(fmPackageId).append("'");
		}
		if (fmTrayCode != null && fmTrayCode.trim().length() > 0) {//fm ��������
			strBud.append("and a.fmtraycode='").append(fmTrayCode).append("'");
		}
		if (towhAreaId != null && towhAreaId.trim().length() > 0) {//to ����id
			strBud.append(" and a.towh_area_id='").append(towhAreaId).append("'");
		}
		strBud.append("and a.tocargo_space_id in (select cargoSpaceId from BaseCargospace b where 1=1)");
		if (toplatoon != null && toplatoon.trim().length() > 0) {//to ��
			strBud.insert(strBud.lastIndexOf(")"), " and b.csplatoon='"+toplatoon+"'");
		}
		if (tocolumn != null && tocolumn.trim().length() > 0) {//to ��
			strBud.insert(strBud.lastIndexOf(")"), " and b.cscolumn='"+tocolumn+"'");
		}
		if (tofloor != null && tofloor.trim().length() > 0) {//to ��
			strBud.insert(strBud.lastIndexOf(")"), " and b.csfloor='"+tofloor+"'");
		}
		if (toCustomerId != null && toCustomerId.trim().length() > 0) {//to �ͻ�id
			strBud.append(" and a.tocustomerid='").append(toCustomerId).append("'");
		}
		if (toPackageId != null && toPackageId.trim().length() > 0) {//to ��Ʒid
			strBud.append("and a.toproductid='").append(toPackageId).append("'");
		}
		if (toTrayCode != null && toTrayCode.trim().length() > 0) {//fm ��������
			strBud.append("and a.totraycode='").append(toTrayCode).append("'");
		}
		if (fmDate != null && fmDate.trim().length() > 0) {//fm ʱ��
			strBud.append("and a.transactiontime>='").append(fmDate).append("'");
		}
		if (toDate != null && toDate.trim().length() > 0) {//to ʱ��
			strBud.append("and a.transactiontime<='").append(toDate).append("'");
		}
		strHQL = strBud.toString();
		
		} catch (Exception e) {
			throw new  Exception("��ÿ�潻�׵�HQL������InventoryTransactionBusImpl.getQueryHql��"+e.getMessage());
		}
		return strHQL;   
   }
}
