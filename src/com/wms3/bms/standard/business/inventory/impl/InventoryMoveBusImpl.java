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
	 * ���ܣ����ݿ��id��ȡ���
	 */
	public InventoryStorage getInventoryStorage(String id) throws Exception {
		
		InventoryStorage iStorage = new InventoryStorage();
		
		try {
			 iStorage = iInventoryDao.getInventoryById(id);	
		} catch (Exception er) {
			er.printStackTrace();
			throw new Exception("����ƶ�->����ID��ѯ������" + er.getMessage());
		}
		return iStorage;
	}
	/**
	 * ���ӿ�浥�������ϸ�����¿��
	 * @param mHeader �ƶ�����
	 * @param mDetail �ƶ�����ϸ
	 * @param iStorage ���
	 */
	public void addMoveHeaderAddMoveDetailUpdateInvent(InventoryMovementHeader iMoveHeader,List<InventoryMovementDetail> lsInMoverDetail,List<InoutJob> lsInoutJob,List<InoutJobdetail> lsInoutJobDetail,List<InventoryStorage> lsInStroStorage,List<BaseCargospace> lsCargospace)throws Exception {
		BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(m_dao);
		ICargoSpaceBus iCargo = new CargoSpaceBusImpl(m_dao);
		if(iMoveHeader!=null){
			//ͬ���ƿⵥ
			synchronized(iMoveHeader.getMovementid()){
				Session session = m_dao.getSession();
				try{
					Transaction tx= session.beginTransaction();
					session.save(iMoveHeader);//�����ƿⵥͷ
					//�����ƿⵥ��ϸ
					if(lsInMoverDetail!=null&&lsInMoverDetail.size()>0){
						for(int i=0;i<lsInMoverDetail.size();i++){
							session.save(lsInMoverDetail.get(i));
						}
					}
					
					//�����ƿ���ҵ
					if(lsInoutJob!=null&&lsInoutJob.size()>0){
						for(int i=0;i<lsInoutJob.size();i++){
							session.save(lsInoutJob.get(i));
						}
					}
					//�����ƿ���ϸ
					if(lsInoutJobDetail!=null&&lsInoutJobDetail.size()>0){
						for(int i=0;i<lsInoutJobDetail.size();i++){
							InoutJobdetail ijobdetail = lsInoutJobDetail.get(i);
							session.save(ijobdetail);
						}
					}
					
					//�޸Ŀ�λ״̬
					
					if(lsCargospace!=null&&lsCargospace.size()>0){
						for(int i=0;i<lsCargospace.size();i++){
							BaseCargospace iCargospace = lsCargospace.get(i);
							iCargospace.setCsstatus("4");//�������״̬
							session.update(iCargospace);
						}
					}
					
					//ɾ�����
//					if(lsInStroStorage!=null&&lsInStroStorage.size()>0){
//						for(int i=0;i<lsInStroStorage.size();i++){
//							InventoryStorage iStorage = lsInStroStorage.get(i);
//							session.delete(iStorage);
//						}
//					}
					
					tx.commit();
				
				}catch(Exception he){
					Logger.error("�����ƶ�����������"+he.getMessage());
				}
				finally
				{
		    		m_dao.closeSession(session);
		    	} 
			}
		}
	}
	/**
	 * �����ƶ������ƶ���ϸ��������棬����ԭ���
	 * @param mHeader �ƶ���
	 * @param mDetail �ƶ���ϸ
	 * @param newInvent �������
	 * @param oldInvent  ԭ���
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
			Logger.error("�����ƶ������޸Ŀ�����"+he.getMessage());
		}
		finally
		{
    		m_dao.closeSession(session);
    		Logger.error("�����ƶ������޸Ŀ��ɹ����ƶ����ݺţ�"+mHeader.getMovementid()+"�ƶ���ϸ���ţ�"+mDetail.getMovemendetailid());
    	} 
	}
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
			Logger.error("�����ƶ�������"+er.getMessage());
		}
		return iHeader;
	}
	
	/**
	 * ���ܣ������ƶ�����ϸ
	 * @param iHeader �ƶ���
	 * @param iStorage ԭ���
	 * @param toNum    �ƶ�����
	 * @param cargoryId  �ƶ��Ļ�λID
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
			Logger.error("�����ƶ�����ϸ����"+er.getMessage());
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
			throw new  Exception("��ÿ�潻�׼�¼ ����InventoryMoveBusImpl.getMovesLs��"+e.getMessage());
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
			throw new  Exception("��ÿ�潻�׼�¼ ����InventoryMoveBusImpl.getMovesLs��"+e.getMessage());
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
			throw new  Exception("��ÿ�潻�׼�¼ ����InventoryMoveBusImpl.getMovesLs��"+e.getMessage());
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
		if (fmwarehouseid != null && fmwarehouseid.trim().length() > 0) {//fm �ֿ�id
			strBud.append(" and b.fromWarehouseId='").append(fmwarehouseid).append("'");
		}
		if (fmwhAreaId != null && fmwhAreaId.trim().length() > 0) {//fm ����id
			strBud.append(" and b.fromAreId='").append(fmwhAreaId).append("'");
		}
		strBud.append(" and b.fromCargospaceId in (select cargoSpaceId from BaseCargospace c where 1=1)");
		if (fmplatoon != null && fmplatoon.trim().length() > 0) {//fm ��
			strBud.insert(strBud.lastIndexOf(")"), " and c.csplatoon='"+fmplatoon+"'");
		}
		if (fmcolumn != null && fmcolumn.trim().length() > 0) {//fm ��
			strBud.insert(strBud.lastIndexOf(")"), " and c.cscolumn='"+fmcolumn+"'");
		}
		if (fmfloor != null && fmfloor.trim().length() > 0) {//fm ��
			strBud.insert(strBud.lastIndexOf(")"), " and c.csfloor='"+fmfloor+"'");
		}
		if (fmCustomerId != null && fmCustomerId.trim().length() > 0) {//fm �ͻ�id
			strBud.append(" and a.fmcustomerid='").append(fmCustomerId).append("'");
		}
		if (fmPackageId != null && fmPackageId.trim().length() > 0) {//fm ��Ʒid
			strBud.append(" and a.fmproductid='").append(fmPackageId).append("'");
		}
		if (fmTrayCode != null && fmTrayCode.trim().length() > 0) {//fm ��������
			strBud.append(" and a.fmtraycode='").append(fmTrayCode).append("'");
		}
		if (towhAreaId != null && towhAreaId.trim().length() > 0) {//to ����id
			strBud.append(" and b.toAreId='").append(towhAreaId).append("'");
		}
		strBud.append(" and b.toCargospaceId in (select cargoSpaceId from BaseCargospace c where 1=1)");
		if (toplatoon != null && toplatoon.trim().length() > 0) {//to ��
			strBud.insert(strBud.lastIndexOf(")"), " and c.csplatoon='"+toplatoon+"'");
		}
		if (tocolumn != null && tocolumn.trim().length() > 0) {//to ��
			strBud.insert(strBud.lastIndexOf(")"), " and c.cscolumn='"+tocolumn+"'");
		}
		if (tofloor != null && tofloor.trim().length() > 0) {//to ��
			strBud.insert(strBud.lastIndexOf(")"), " and c.csfloor='"+tofloor+"'");
		}
		if (toCustomerId != null && toCustomerId.trim().length() > 0) {//to �ͻ�id
			strBud.append(" and a.tocustomerid='").append(toCustomerId).append("'");
		}
		if (toPackageId != null && toPackageId.trim().length() > 0) {//to ��Ʒid
			strBud.append(" and a.toproductid='").append(toPackageId).append("'");
		}
		if (toTrayCode != null && toTrayCode.trim().length() > 0) {//fm ��������
			strBud.append(" and a.totraycode='").append(toTrayCode).append("'");
		}
		if (fmDate != null && fmDate.trim().length() > 0) {//fm ʱ��
			strBud.append(" and a.createTime>='").append(fmDate).append("'");
		}
		if (toDate != null && toDate.trim().length() > 0) {//to ʱ��
			strBud.append(" and a.createTime<='").append(toDate).append("'");
		}
		strHQL = strBud.toString();
		
		} catch (Exception e) {
			throw new  Exception("��ÿ�潻�׵�HQL������InventoryMoveBusImpl.getQueryHql��"+e.getMessage());
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
		if (fmwarehouseid != null && fmwarehouseid.trim().length() > 0) {//fm �ֿ�id
			strBud.append(" and b.fromWarehouseId='").append(fmwarehouseid).append("'");
		}
		if (fmwhAreaId != null && fmwhAreaId.trim().length() > 0) {//fm ����id
			strBud.append(" and b.fromAreId='").append(fmwhAreaId).append("'");
		}
		strBud.append(" and b.fromCargospaceId in (select cargoSpaceId from BaseCargospace c where 1=1)");
		if (fmplatoon != null && fmplatoon.trim().length() > 0) {//fm ��
			strBud.insert(strBud.lastIndexOf(")"), " and c.csplatoon='"+fmplatoon+"'");
		}
		if (fmcolumn != null && fmcolumn.trim().length() > 0) {//fm ��
			strBud.insert(strBud.lastIndexOf(")"), " and c.cscolumn='"+fmcolumn+"'");
		}
		if (fmfloor != null && fmfloor.trim().length() > 0) {//fm ��
			strBud.insert(strBud.lastIndexOf(")"), " and c.csfloor='"+fmfloor+"'");
		}
		if (fmCustomerId != null && fmCustomerId.trim().length() > 0) {//fm �ͻ�id
			strBud.append(" and a.fmcustomerid='").append(fmCustomerId).append("'");
		}
		if (fmPackageId != null && fmPackageId.trim().length() > 0) {//fm ��Ʒid
			strBud.append(" and a.fmproductid='").append(fmPackageId).append("'");
		}
		if (fmTrayCode != null && fmTrayCode.trim().length() > 0) {//fm ��������
			strBud.append(" and a.fmtraycode='").append(fmTrayCode).append("'");
		}
		if (towhAreaId != null && towhAreaId.trim().length() > 0) {//to ����id
			strBud.append(" and b.toAreId='").append(towhAreaId).append("'");
		}
		strBud.append(" and b.toCargospaceId in (select cargoSpaceId from BaseCargospace c where 1=1)");
		if (toplatoon != null && toplatoon.trim().length() > 0) {//to ��
			strBud.insert(strBud.lastIndexOf(")"), " and c.csplatoon='"+toplatoon+"'");
		}
		if (tocolumn != null && tocolumn.trim().length() > 0) {//to ��
			strBud.insert(strBud.lastIndexOf(")"), " and c.cscolumn='"+tocolumn+"'");
		}
		if (tofloor != null && tofloor.trim().length() > 0) {//to ��
			strBud.insert(strBud.lastIndexOf(")"), " and c.csfloor='"+tofloor+"'");
		}
		if (toCustomerId != null && toCustomerId.trim().length() > 0) {//to �ͻ�id
			strBud.append(" and a.tocustomerid='").append(toCustomerId).append("'");
		}
		if (toPackageId != null && toPackageId.trim().length() > 0) {//to ��Ʒid
			strBud.append(" and a.toproductid='").append(toPackageId).append("'");
		}
		if (toTrayCode != null && toTrayCode.trim().length() > 0) {//fm ��������
			strBud.append(" and a.totraycode='").append(toTrayCode).append("'");
		}
		if (fmDate != null && fmDate.trim().length() > 0) {//fm ʱ��
			strBud.append(" and a.createTime>='").append(fmDate).append("'");
		}
		if (toDate != null && toDate.trim().length() > 0) {//to ʱ��
			strBud.append(" and a.createTime<='").append(toDate).append("'");
		}
		strHQL = strBud.toString();
		
		} catch (Exception e) {
			throw new  Exception("��ÿ�潻�׵�HQL������InventoryMoveBusImpl.getQueryHql��"+e.getMessage());
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
			throw new  Exception("��ÿ�潻�׵�HQL������InventoryMoveBusImpl.getQueryHql��"+e.getMessage());
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
