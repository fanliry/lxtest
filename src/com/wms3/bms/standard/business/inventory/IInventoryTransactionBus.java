package com.wms3.bms.standard.business.inventory;

import com.ricosoft.common.pagination.PagingTool;
/**
 * 描述：交易记录
 * @author liuxh
 *
 */
public interface IInventoryTransactionBus {
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
public PagingTool getTransactionsLs(String fmwarehouseid,String fmwhAreaId,String fmplatoon,
		           String fmcolumn,String fmfloor,String fmCustomerId,String fmPackageId,String fmTrayCode,
		           String towhAreaId,String toplatoon,String tocolumn,String tofloor,String toCustomerId,
		           String toPackageId,String toTrayCode,String fmDate,String toDate,String url,int iCurrentPage,int iMaxRow)throws Exception;
}
