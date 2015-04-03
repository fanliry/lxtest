package com.wms3.bms.standard.business.inventory;

import com.ricosoft.common.pagination.PagingTool;
/**
 * ���������׼�¼
 * @author liuxh
 *
 */
public interface IInventoryTransactionBus {
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
public PagingTool getTransactionsLs(String fmwarehouseid,String fmwhAreaId,String fmplatoon,
		           String fmcolumn,String fmfloor,String fmCustomerId,String fmPackageId,String fmTrayCode,
		           String towhAreaId,String toplatoon,String tocolumn,String tofloor,String toCustomerId,
		           String toPackageId,String toTrayCode,String fmDate,String toDate,String url,int iCurrentPage,int iMaxRow)throws Exception;
}
