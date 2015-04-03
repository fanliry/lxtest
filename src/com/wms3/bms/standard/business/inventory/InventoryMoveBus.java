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
 * 描述：库存移动的业务接口类
 * @author liuxh
 *
 */
public interface InventoryMoveBus{
	/**
	 * 功能：根据库存ID获取库存
	 * @param id 库存ID
	 * @return  库存信息
	 * @throws Exception
	 */
	public InventoryStorage getInventoryStorage(String id) throws Exception;
	
	/**
	 * 功能：增加移动单据，移动明细单据，更新原库存
	 * @param mHeader 移动单据
	 * @param mDetail  移动明细
	 * @param iStorage  原库存
	 * @throws Exception
	 */
	public void addMoveHeaderAddMoveDetailUpdateInvent(InventoryMovementHeader iMoveHeader,List<InventoryMovementDetail> lsInMoverDetail,List<InoutJob> lsInoutJob,List<InoutJobdetail> lsInoutJobDetail,List<InventoryStorage> lsInStroStorage,List<BaseCargospace> lsCargospace)throws Exception ;
	
	/**
	 * 功能：增加移动单据，移动明细单，增加新库存，更新原库存
	 * @param mHeader     移动单据
	 * @param mDetail     移动明细单
	 * @param newInvent   新库存
	 * @param oldInvent   原库存
	 * @throws Exception
	 */
	public void addMoveHeaderAddMoveDetailAddNewInventUpdateInvent(InventoryMovementHeader mHeader,InventoryMovementDetail mDetail,
			InventoryStorage newInvent,InventoryStorage oldInvent)throws Exception;
    
	/**
     * 功能：创建移动单据
     * @param createmanId  创建人
     * @param movementId   移动ID
     * @param customerId   客户ID
     * @param createTime   创建时间
     * @param moveTime     移动时间
     * @param reasonCode   移动原因
     * @param reasonDiscr  移动描述
     * @return  移动单据
     * @throws Exception
     */
	public InventoryMovementHeader creatMoeveHeader(String movementId,String warehouseId,String createManid,String createTime)throws Exception;
	
	/**
	 * 功能：创建移动单明细
	 * @param iHeader 移动单
	 * @param iStorage 原库存
	 * @param toNum    移动数量
	 * @param cargoryId  移动的货位ID
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
