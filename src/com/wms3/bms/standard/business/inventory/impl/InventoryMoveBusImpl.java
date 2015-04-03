package com.wms3.bms.standard.business.inventory.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.ICargoSpaceBus;
import com.wms3.bms.standard.business.base.impl.CargoSpaceBusImpl;
import com.wms3.bms.standard.business.inventory.InventoryMoveBus;
import com.wms3.bms.standard.business.job.IJobBus;
import com.wms3.bms.standard.business.job.impl.JobBusImpl;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

public class InventoryMoveBusImpl implements InventoryMoveBus {
    
    protected IInventoryDao iInventoryDao  = null;
    protected EntityDAO m_dao = null;
    protected IJobBus jobBus = null;
	public InventoryMoveBusImpl(EntityDAO dao)
	{
		iInventoryDao = new InventoryDaoImpl(dao);
		jobBus = new JobBusImpl(dao);
		m_dao = dao;
		
	}
	/**
	 * 功能：根据库存id获取库存
	 */
	public InventoryStorage getInventoryStorage(String id) throws Exception {
		
		InventoryStorage iStorage = new InventoryStorage();
		
		try {
			 iStorage = iInventoryDao.getInventoryById(id);	
		} catch (Exception er) {
			er.printStackTrace();
			throw new Exception("库存移动->根据ID查询库存出错：" + er.getMessage());
		}
		return iStorage;
	}
	/**
	 * 增加库存单，库存明细，更新库存
	 * @param mHeader 移动单据
	 * @param mDetail 移动单明细
	 * @param iStorage 库存
	 */
	public void addMoveHeaderAddMoveDetailUpdateInvent(InventoryMovementHeader iMoveHeader,List<InventoryMovementDetail> lsInMoverDetail,List<InoutJob> lsInoutJob,List<InoutJobdetail> lsInoutJobDetail,List<InventoryStorage> lsInStroStorage,List<BaseCargospace> lsCargospace)throws Exception {
		BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(m_dao);
		ICargoSpaceBus iCargo = new CargoSpaceBusImpl(m_dao);
		if(iMoveHeader!=null){
			//同步移库单
			synchronized(iMoveHeader.getMovementid()){
				Session session = m_dao.getSession();
				try{
					Transaction tx= session.beginTransaction();
					session.save(iMoveHeader);//保存移库单头
					//保存移库单详细
					if(lsInMoverDetail!=null&&lsInMoverDetail.size()>0){
						for(int i=0;i<lsInMoverDetail.size();i++){
							session.save(lsInMoverDetail.get(i));
						}
					}
					
					//保存移库作业
					if(lsInoutJob!=null&&lsInoutJob.size()>0){
						for(int i=0;i<lsInoutJob.size();i++){
							session.save(lsInoutJob.get(i));
						}
					}
					//保存移库详细
					if(lsInoutJobDetail!=null&&lsInoutJobDetail.size()>0){
						for(int i=0;i<lsInoutJobDetail.size();i++){
							InoutJobdetail ijobdetail = lsInoutJobDetail.get(i);
							session.save(ijobdetail);
						}
					}
					
					//修改库位状态
					
					if(lsCargospace!=null&&lsCargospace.size()>0){
						for(int i=0;i<lsCargospace.size();i++){
							BaseCargospace iCargospace = lsCargospace.get(i);
							iCargospace.setCsstatus("4");//出库分配状态
							session.update(iCargospace);
						}
					}
					
					//删除库存
//					if(lsInStroStorage!=null&&lsInStroStorage.size()>0){
//						for(int i=0;i<lsInStroStorage.size();i++){
//							InventoryStorage iStorage = lsInStroStorage.get(i);
//							session.delete(iStorage);
//						}
//					}
					
					tx.commit();
				
				}catch(Exception he){
					Logger.error("增加移动单操作出错！"+he.getMessage());
				}
				finally
				{
		    		m_dao.closeSession(session);
		    	} 
			}
		}
	}
	/**
	 * 增加移动单，移动明细，新增库存，更新原库存
	 * @param mHeader 移动单
	 * @param mDetail 移动明细
	 * @param newInvent 新增库存
	 * @param oldInvent  原库存
	 */
	public void addMoveHeaderAddMoveDetailAddNewInventUpdateInvent(InventoryMovementHeader mHeader,InventoryMovementDetail mDetail,
			InventoryStorage newInvent,InventoryStorage oldInvent) {
		Session session = m_dao.getSession();
		try {
			Transaction tx = session.beginTransaction();
			session.save(mHeader);
			session.save(mDetail);
	        session.save(newInvent);
			if (oldInvent.getPnum()==0.0) {
				session.delete(oldInvent);
			} else {
               session.update(oldInvent);
			}
			tx.commit();	
		} catch (HibernateException he) {
			Logger.error("增加移动单并修改库存出错！"+he.getMessage());
		}
		finally
		{
    		m_dao.closeSession(session);
    		Logger.error("增加移动单并修改库存成功！移动单据号："+mHeader.getMovementid()+"移动明细单号："+mDetail.getMovemendetailid());
    	} 
	}
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
	public InventoryMovementHeader creatMoeveHeader(String movementId,String warehouseId,String createManid,String createTime) throws Exception {
		InventoryMovementHeader iHeader = new InventoryMovementHeader();
		try {	
			iHeader.setMovementid(movementId);
			iHeader.setWarehouseid(warehouseId);
			iHeader.setCreateManid(createManid);
			iHeader.setCreateTime(createTime);
			iHeader.setMoveType("13");
		} catch (Exception er) {
			er.printStackTrace();
			Logger.error("创建移动单出错！"+er.getMessage());
		}
		return iHeader;
	}
	
	/**
	 * 功能：创建移动单明细
	 * @param iHeader 移动单
	 * @param iStorage 原库存
	 * @param toNum    移动数量
	 * @param cargoryId  移动的货位ID
	 * @return
	 * @throws Exception
	 */
	public InventoryMovementDetail createMoveDetail(String strNo,String strUserCode, String strTime,String strtoWhAreaId, String strtoCargospaceId,String strtoCargospaceName,String strtoWarehouseid, String strreasoncode, String strRemark, InventoryStorage iStorage) throws Exception {
		
		InventoryMovementDetail iDetail = new InventoryMovementDetail();
		ICargoSpaceBus iCargo = new CargoSpaceBusImpl(m_dao);
		try {

			iDetail.setMovementid(strNo);
			iDetail.setProductid(iStorage.getProductid());
			iDetail.setProductName(iStorage.getProductName());
			iDetail.setProductDate(iStorage.getProductdate());
			iDetail.setLotid(iStorage.getLotid());
			iDetail.setLotNum(iStorage.getLotinfo());
			iDetail.setTrayCode(iStorage.getTraycode());
			iDetail.setMeter(iStorage.getPunitname());
			iDetail.setFromWarehouseId(iStorage.getWarehouseid());
			iDetail.setFromAreId(iStorage.getWhAreaId());
			iDetail.setFromCargospaceId(iStorage.getCargoSpaceId());
			iDetail.setFromCargospace(iStorage.getCargoSpaceName());
			iDetail.setToWarehouseId(strtoWarehouseid);
			iDetail.setToAreId(strtoWhAreaId);
			iDetail.setToCargospaceId(strtoCargospaceId);
			iDetail.setToCargospace(strtoCargospaceName);
			iDetail.setMoveReason(strreasoncode);
			iDetail.setRemark(strRemark);
			
		} catch (Exception er) {
			er.printStackTrace();
			Logger.error("创建移动单明细出错！"+er.getMessage());
		}
		
		return iDetail;
	}
	
	
	
	public PagingTool getMovesLs(String fmwarehouseid,String fmwhAreaId, 
			String fmplatoon, String fmcolumn,String fmfloor, String fmCustomerId, String fmPackageId,
			String fmTrayCode, String towhAreaId,String toplatoon, String tocolumn, 
			String tofloor,String toCustomerId, String toPackageId, String toTrayCode,String fmDate, 
			String toDate,String url,int iCurrentPage,int iMaxRow) throws Exception {
		PagingTool pt = null;
		String strHQL = "";
		try {
			strHQL = getQueryHql(fmwarehouseid, fmwhAreaId, fmplatoon, fmcolumn, fmfloor, fmCustomerId, fmPackageId, fmTrayCode, 
					towhAreaId, toplatoon, tocolumn, tofloor, toCustomerId, toPackageId, toTrayCode, fmDate, toDate);
		    pt = CommonPagination.getPagingTool(m_dao,"select count(*)"+strHQL, strHQL, url, iCurrentPage, iMaxRow);
		} catch (Exception e) {
			throw new  Exception("获得库存交易记录 出错，InventoryMoveBusImpl.getMovesLs："+e.getMessage());
		}
		
		return pt;
	}
	public PagingTool getMovesLs0(String fmwarehouseid,String fmwhAreaId, 
			String fmplatoon, String fmcolumn,String fmfloor, String fmCustomerId, String fmPackageId,
			String fmTrayCode, String towhAreaId,String toplatoon, String tocolumn, 
			String tofloor,String toCustomerId, String toPackageId, String toTrayCode,String fmDate, 
			String toDate,String url,int iCurrentPage,int iMaxRow) throws Exception {
		PagingTool pt = null;
		String strHQL = "";
		try {
			strHQL = getQueryHql0(fmwarehouseid, fmwhAreaId, fmplatoon, fmcolumn, fmfloor, fmCustomerId, fmPackageId, fmTrayCode, 
					towhAreaId, toplatoon, tocolumn, tofloor, toCustomerId, toPackageId, toTrayCode, fmDate, toDate);
		    pt = CommonPagination.getPagingTool(m_dao,"select count(*)"+strHQL, "select distinct a "+strHQL, url, iCurrentPage, iMaxRow);
		} catch (Exception e) {
			throw new  Exception("获得库存交易记录 出错，InventoryMoveBusImpl.getMovesLs："+e.getMessage());
		}
		
		return pt;
	}
	public PagingTool getMovesLs1(String movementid,String url,int iCurrentPage,int iMaxRow) throws Exception{
		PagingTool pt = null;
		String strHQL = "";
		try {
			strHQL = getQueryHql1(movementid);
		    pt = CommonPagination.getPagingTool(m_dao,"select count(*)"+strHQL, strHQL, url, iCurrentPage, iMaxRow);
		} catch (Exception e) {
			throw new  Exception("获得库存交易记录 出错，InventoryMoveBusImpl.getMovesLs："+e.getMessage());
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
	public String getQueryHql(String fmwarehouseid,String fmwhAreaId, 
			String fmplatoon, String fmcolumn,String fmfloor, String fmCustomerId, String fmPackageId,
			String fmTrayCode, String towhAreaId,String toplatoon, String tocolumn, 
			String tofloor,String toCustomerId, String toPackageId, String toTrayCode,String fmDate, 
			String toDate) throws Exception{
	    String strHQL = "";
	    try {
		//select a.warehouseName, b.productid, b.productName, b.productDate, b.lotNum, b.trayCode, b.meter, a.createTime, b.fromAreName, b.fromCargospace, b.toAreName, b.toCargospace, a.createManid 
		StringBuilder strBud = new StringBuilder();  
		strBud.append("from InventoryMovementHeader a ,InventoryMovementDetail b where 1=1 and a.movementid=b.movementid");
		if (fmwarehouseid != null && fmwarehouseid.trim().length() > 0) {//fm 仓库id
			strBud.append(" and b.fromWarehouseId='").append(fmwarehouseid).append("'");
		}
		if (fmwhAreaId != null && fmwhAreaId.trim().length() > 0) {//fm 库区id
			strBud.append(" and b.fromAreId='").append(fmwhAreaId).append("'");
		}
		strBud.append(" and b.fromCargospaceId in (select cargoSpaceId from BaseCargospace c where 1=1)");
		if (fmplatoon != null && fmplatoon.trim().length() > 0) {//fm 排
			strBud.insert(strBud.lastIndexOf(")"), " and c.csplatoon='"+fmplatoon+"'");
		}
		if (fmcolumn != null && fmcolumn.trim().length() > 0) {//fm 列
			strBud.insert(strBud.lastIndexOf(")"), " and c.cscolumn='"+fmcolumn+"'");
		}
		if (fmfloor != null && fmfloor.trim().length() > 0) {//fm 层
			strBud.insert(strBud.lastIndexOf(")"), " and c.csfloor='"+fmfloor+"'");
		}
		if (fmCustomerId != null && fmCustomerId.trim().length() > 0) {//fm 客户id
			strBud.append(" and a.fmcustomerid='").append(fmCustomerId).append("'");
		}
		if (fmPackageId != null && fmPackageId.trim().length() > 0) {//fm 产品id
			strBud.append(" and a.fmproductid='").append(fmPackageId).append("'");
		}
		if (fmTrayCode != null && fmTrayCode.trim().length() > 0) {//fm 托盘条码
			strBud.append(" and a.fmtraycode='").append(fmTrayCode).append("'");
		}
		if (towhAreaId != null && towhAreaId.trim().length() > 0) {//to 库区id
			strBud.append(" and b.toAreId='").append(towhAreaId).append("'");
		}
		strBud.append(" and b.toCargospaceId in (select cargoSpaceId from BaseCargospace c where 1=1)");
		if (toplatoon != null && toplatoon.trim().length() > 0) {//to 排
			strBud.insert(strBud.lastIndexOf(")"), " and c.csplatoon='"+toplatoon+"'");
		}
		if (tocolumn != null && tocolumn.trim().length() > 0) {//to 列
			strBud.insert(strBud.lastIndexOf(")"), " and c.cscolumn='"+tocolumn+"'");
		}
		if (tofloor != null && tofloor.trim().length() > 0) {//to 层
			strBud.insert(strBud.lastIndexOf(")"), " and c.csfloor='"+tofloor+"'");
		}
		if (toCustomerId != null && toCustomerId.trim().length() > 0) {//to 客户id
			strBud.append(" and a.tocustomerid='").append(toCustomerId).append("'");
		}
		if (toPackageId != null && toPackageId.trim().length() > 0) {//to 产品id
			strBud.append(" and a.toproductid='").append(toPackageId).append("'");
		}
		if (toTrayCode != null && toTrayCode.trim().length() > 0) {//fm 托盘条码
			strBud.append(" and a.totraycode='").append(toTrayCode).append("'");
		}
		if (fmDate != null && fmDate.trim().length() > 0) {//fm 时间
			strBud.append(" and a.createTime>='").append(fmDate).append("'");
		}
		if (toDate != null && toDate.trim().length() > 0) {//to 时间
			strBud.append(" and a.createTime<='").append(toDate).append("'");
		}
		strHQL = strBud.toString();
		
		} catch (Exception e) {
			throw new  Exception("获得库存交易的HQL语句出错，InventoryMoveBusImpl.getQueryHql："+e.getMessage());
		}
		return strHQL;   
   }
	public String getQueryHql0(String fmwarehouseid,String fmwhAreaId, 
			String fmplatoon, String fmcolumn,String fmfloor, String fmCustomerId, String fmPackageId,
			String fmTrayCode, String towhAreaId,String toplatoon, String tocolumn, 
			String tofloor,String toCustomerId, String toPackageId, String toTrayCode,String fmDate, 
			String toDate) throws Exception{
	    String strHQL = "";
	    try {
		//select a.warehouseName, b.productid, b.productName, b.productDate, b.lotNum, b.trayCode, b.meter, a.createTime, b.fromAreName, b.fromCargospace, b.toAreName, b.toCargospace, a.createManid 
		StringBuilder strBud = new StringBuilder();  
		strBud.append("from InventoryMovementHeader a ,InventoryMovementDetail b where 1=1 and a.movementid=b.movementid");
		if (fmwarehouseid != null && fmwarehouseid.trim().length() > 0) {//fm 仓库id
			strBud.append(" and b.fromWarehouseId='").append(fmwarehouseid).append("'");
		}
		if (fmwhAreaId != null && fmwhAreaId.trim().length() > 0) {//fm 库区id
			strBud.append(" and b.fromAreId='").append(fmwhAreaId).append("'");
		}
		strBud.append(" and b.fromCargospaceId in (select cargoSpaceId from BaseCargospace c where 1=1)");
		if (fmplatoon != null && fmplatoon.trim().length() > 0) {//fm 排
			strBud.insert(strBud.lastIndexOf(")"), " and c.csplatoon='"+fmplatoon+"'");
		}
		if (fmcolumn != null && fmcolumn.trim().length() > 0) {//fm 列
			strBud.insert(strBud.lastIndexOf(")"), " and c.cscolumn='"+fmcolumn+"'");
		}
		if (fmfloor != null && fmfloor.trim().length() > 0) {//fm 层
			strBud.insert(strBud.lastIndexOf(")"), " and c.csfloor='"+fmfloor+"'");
		}
		if (fmCustomerId != null && fmCustomerId.trim().length() > 0) {//fm 客户id
			strBud.append(" and a.fmcustomerid='").append(fmCustomerId).append("'");
		}
		if (fmPackageId != null && fmPackageId.trim().length() > 0) {//fm 产品id
			strBud.append(" and a.fmproductid='").append(fmPackageId).append("'");
		}
		if (fmTrayCode != null && fmTrayCode.trim().length() > 0) {//fm 托盘条码
			strBud.append(" and a.fmtraycode='").append(fmTrayCode).append("'");
		}
		if (towhAreaId != null && towhAreaId.trim().length() > 0) {//to 库区id
			strBud.append(" and b.toAreId='").append(towhAreaId).append("'");
		}
		strBud.append(" and b.toCargospaceId in (select cargoSpaceId from BaseCargospace c where 1=1)");
		if (toplatoon != null && toplatoon.trim().length() > 0) {//to 排
			strBud.insert(strBud.lastIndexOf(")"), " and c.csplatoon='"+toplatoon+"'");
		}
		if (tocolumn != null && tocolumn.trim().length() > 0) {//to 列
			strBud.insert(strBud.lastIndexOf(")"), " and c.cscolumn='"+tocolumn+"'");
		}
		if (tofloor != null && tofloor.trim().length() > 0) {//to 层
			strBud.insert(strBud.lastIndexOf(")"), " and c.csfloor='"+tofloor+"'");
		}
		if (toCustomerId != null && toCustomerId.trim().length() > 0) {//to 客户id
			strBud.append(" and a.tocustomerid='").append(toCustomerId).append("'");
		}
		if (toPackageId != null && toPackageId.trim().length() > 0) {//to 产品id
			strBud.append(" and a.toproductid='").append(toPackageId).append("'");
		}
		if (toTrayCode != null && toTrayCode.trim().length() > 0) {//fm 托盘条码
			strBud.append(" and a.totraycode='").append(toTrayCode).append("'");
		}
		if (fmDate != null && fmDate.trim().length() > 0) {//fm 时间
			strBud.append(" and a.createTime>='").append(fmDate).append("'");
		}
		if (toDate != null && toDate.trim().length() > 0) {//to 时间
			strBud.append(" and a.createTime<='").append(toDate).append("'");
		}
		strHQL = strBud.toString();
		
		} catch (Exception e) {
			throw new  Exception("获得库存交易的HQL语句出错，InventoryMoveBusImpl.getQueryHql："+e.getMessage());
		}
		return strHQL;   
   }
	public String getQueryHql1(String movementid) throws Exception{
	    String strHQL = "";
	    try {
		//select a.warehouseName, b.productid, b.productName, b.productDate, b.lotNum, b.trayCode, b.meter, a.createTime, b.fromAreName, b.fromCargospace, b.toAreName, b.toCargospace, a.createManid 
		StringBuilder strBud = new StringBuilder();  
		strBud.append("from InventoryMovementDetail b where 1=1 and b.movementid='"+movementid+"'");
		
		strHQL = strBud.toString();
		
		} catch (Exception e) {
			throw new  Exception("获得库存交易的HQL语句出错，InventoryMoveBusImpl.getQueryHql："+e.getMessage());
		}
		return strHQL;   
   }
	
	
	public InventoryMovementHeader getMoveHeaderById(String movementId){
		InventoryMovementHeader iHeader = null;
		
		
		return iHeader;
	}
	
	public InventoryMovementDetail getMoveDetailByHeaderId(InventoryMovementHeader iHeader){
		InventoryMovementDetail iDetail = null;
		
	
		return iDetail;
	}
	
}
