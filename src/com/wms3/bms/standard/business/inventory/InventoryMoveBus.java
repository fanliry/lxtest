package com.wms3.bms.standard.business.inventory;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;


/**
 * ����������ƶ���ҵ��ӿ���
 * @author liuxh
 *
 */
public interface InventoryMoveBus{
	/**
	 * ���ܣ����ݿ��ID��ȡ���
	 * @param id ���ID
	 * @return  �����Ϣ
	 * @throws Exception
	 */
	public InventoryStorage getInventoryStorage(String id) throws Exception;
	
	/**
	 * ���ܣ������ƶ����ݣ��ƶ���ϸ���ݣ�����ԭ���
	 * @param mHeader �ƶ�����
	 * @param mDetail  �ƶ���ϸ
	 * @param iStorage  ԭ���
	 * @throws Exception
	 */
	public void addMoveHeaderAddMoveDetailUpdateInvent(InventoryMovementHeader iMoveHeader,List<InventoryMovementDetail> lsInMoverDetail,List<InoutJob> lsInoutJob,List<InoutJobdetail> lsInoutJobDetail,List<InventoryStorage> lsInStroStorage,List<BaseCargospace> lsCargospace)throws Exception ;
	
	/**
	 * ���ܣ������ƶ����ݣ��ƶ���ϸ���������¿�棬����ԭ���
	 * @param mHeader     �ƶ�����
	 * @param mDetail     �ƶ���ϸ��
	 * @param newInvent   �¿��
	 * @param oldInvent   ԭ���
	 * @throws Exception
	 */
	public void addMoveHeaderAddMoveDetailAddNewInventUpdateInvent(InventoryMovementHeader mHeader,InventoryMovementDetail mDetail,
			InventoryStorage newInvent,InventoryStorage oldInvent)throws Exception;
    
	/**
     * ���ܣ������ƶ�����
     * @param createmanId  ������
     * @param movementId   �ƶ�ID
     * @param customerId   �ͻ�ID
     * @param createTime   ����ʱ��
     * @param moveTime     �ƶ�ʱ��
     * @param reasonCode   �ƶ�ԭ��
     * @param reasonDiscr  �ƶ�����
     * @return  �ƶ�����
     * @throws Exception
     */
	public InventoryMovementHeader creatMoeveHeader(String movementId,String warehouseId,String createManid,String createTime)throws Exception;
	
	/**
	 * ���ܣ������ƶ�����ϸ
	 * @param iHeader �ƶ���
	 * @param iStorage ԭ���
	 * @param toNum    �ƶ�����
	 * @param cargoryId  �ƶ��Ļ�λID
	 * @return
	 * @throws Exception
	 */
	public InventoryMovementDetail createMoveDetail(String strNo,String strUserCode, String strTime,String strtoWhAreaId, String strtoCargospaceId,String strtoCargospaceName,String strtoWarehouseid, String strreasoncode, String strRemark, InventoryStorage iStorage) throws Exception;
	
	public PagingTool getMovesLs(String fmwarehouseid,String fmwhAreaId, 
			String fmplatoon, String fmcolumn,String fmfloor, String fmCustomerId, String fmPackageId,
			String fmTrayCode, String towhAreaId,String toplatoon, String tocolumn, 
			String tofloor,String toCustomerId, String toPackageId, String toTrayCode,String fmDate, 
			String toDate,String url,int iCurrentPage,int iMaxRow) throws Exception; 
	
	public PagingTool getMovesLs0(String fmwarehouseid,String fmwhAreaId, 
			String fmplatoon, String fmcolumn,String fmfloor, String fmCustomerId, String fmPackageId,
			String fmTrayCode, String towhAreaId,String toplatoon, String tocolumn, 
			String tofloor,String toCustomerId, String toPackageId, String toTrayCode,String fmDate, 
			String toDate,String url,int iCurrentPage,int iMaxRow) throws Exception; 
	
	public PagingTool getMovesLs1(String movementid,String url,int iCurrentPage,int iMaxRow) throws Exception; 
	
	public InventoryMovementHeader getMoveHeaderById(String movementId);
		
	public InventoryMovementDetail getMoveDetailByHeaderId(InventoryMovementHeader iHeader);
	
	public String getQueryHql(String fmwarehouseid,String fmwhAreaId, 
			String fmplatoon, String fmcolumn,String fmfloor, String fmCustomerId, String fmPackageId,
			String fmTrayCode, String towhAreaId,String toplatoon, String tocolumn, 
			String tofloor,String toCustomerId, String toPackageId, String toTrayCode,String fmDate, 
			String toDate) throws Exception;
}
