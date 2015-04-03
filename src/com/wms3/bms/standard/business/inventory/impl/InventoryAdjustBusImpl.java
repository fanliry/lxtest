package com.wms3.bms.standard.business.inventory.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.StrTools;
import com.ricosoft.entity.competenceMgr.SystemLogInfo;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.ICargoSpaceBus;
import com.wms3.bms.standard.business.base.impl.CargoSpaceBusImpl;
import com.wms3.bms.standard.business.inventory.InventoryAdjustBus;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.InventoryNeedcheck;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;


/**
 * 描述:库存调整管理
 * @author yao
 *
 */
public class InventoryAdjustBusImpl implements InventoryAdjustBus
{
	protected EntityDAO m_dao = null;
	
	public InventoryAdjustBusImpl(EntityDAO dao)
	{
		m_dao = dao;
	}
	/**
	 * 功能:获得库存调整单查询的SQL语句
	 * @param status	状态
	 * @param customer	客户
	 * @return
	 */
	public String getAdjustHeaderSQL(String warehouseid, String status,String adjusttype,String reasoncode)
	{     
		StringBuilder strBur = new StringBuilder();
		strBur.append("from InventoryAdjustHeader as ah where 1=1");
		if(warehouseid != null && warehouseid.trim().length() > 0)
		{
			strBur.append(" and ah.warehouseid ='").append(warehouseid).append("'");
		}
		if(status != null && status.trim().length() > 0)
		{
			strBur.append(" and ah.status = '").append(status).append("'");
		}
		if(adjusttype != null && adjusttype.trim().length() > 0)
		{
			strBur.append(" and ah.adjusttype = '").append(adjusttype).append("'");
		}
		if(reasoncode != null && reasoncode.trim().length() > 0)
		{
			strBur.append(" and ah.reasoncode  = '").append(reasoncode).append("'");
		}
		return strBur.toString();
	}
	/**
	 * 功能:获得库存调整单总记录查询的SQL语句
	 * @param status	状态
	 * @param customer	客户
	 * @return
	 */
	public String getAdjustHeaderCountSQL(String sql)
	{
		String strSql = "select count(ah) " + sql;

		return strSql;
	}
	/**
	 * 功能:获得库存调整单详细查询的SQL语句
	 * @param strId	库存调整单ID
	 * @return
	 */
	public static String getAdjustDetailSQL(String strId)
	{
		String strSql = "from InventoryAdjustDetail as ad where 1=1";
		if(strId != null && strId.trim().length() > 0)
		{
			strSql = strSql + " and ad.adjustid='" + strId + "'";
		}
		return strSql;
	}
	/**
	 * 功能:获得库存调整单详细查询总记录的SQL语句
	 * @param strId	库存调整单ID
	 * @return
	 */
	public static String getAdjustDetailCountSQL(String strId)
	{
		String strSql = "select count(ad) from InventoryAdjustDetail as ad where 1=1";
		if(strId != null && strId.trim().length() > 0)
		{
			strSql = strSql + " and ad.adjustid='" + strId + "'";
		}
		return strSql;
	}
	/**
	 * 功能:删除库存调整单
	 * @param id
	 */
	public void deleteAdjustHeader(String id)
	{
		Session session = m_dao.getSession();
    	try
		{
    		
    		String strSql0 = "delete from InventoryAdjustHeader as ah where ah.adjustid='"+id+"'";
    		Transaction tx= session.beginTransaction();
    		session.createQuery(strSql0).executeUpdate();
			tx.commit();
        }
    	catch(HibernateException he)
		{
    		Logger.error("删除库存调整单出错！"+he.getMessage());
		}
    	finally
		{
    		m_dao.closeSession(session);
    	}
	}
	/**
	 * 功能:根据调整单ID获得库存调整单
	 * @param strId
	 * @return
	 * @throws Exception
	 */
	public InventoryAdjustHeader getAdjustHeaderToId(String strId) throws Exception
	{
		InventoryAdjustHeader adjust = null;
		try
		{
			if(strId != null)
			{
				String strSql = "from InventoryAdjustHeader as ah where ah.adjustid='" + strId + "'";
				List ls = m_dao.searchEntities(strSql);
				if(ls != null && ls.size() > 0)
				{
					adjust = (InventoryAdjustHeader)ls.get(0);
				}
			}
		}catch(Exception er)
		{
			throw new Exception("根据调整单ID获得库存调整单出错(InventoryAdjustBusImpl.getAdjustHeaderToId):" + er.getMessage());
		}
		return adjust;
	}
	/**
	 * 功能:更新库存调整单
	 * @param adjust
	 */
	public void updateAdjustHeader(InventoryAdjustHeader adjust)
	{
		m_dao.update(adjust);
	}
	
	//******库存查询******************************************************
	/**
	 * 功能:获得库存查询的SQL语句
	 * @param customerid	客户
	 * @param skuid			产品
	 * @param lotid		批次属性
	 * @param traycode  托盘id
	 * @param locid		库位
	 * @return
	 */
	public static String getInventoryToSQL(String customerid, String skuid, String lotid, String traycode, String locid)
	{
		String strSql = "from InventoryStorage as invent where 1=1";
		if(customerid != null && customerid.trim().length() > 0)
		{
			strSql = strSql + " and invent.ownerId like '%" + customerid + "%'";
		}
		if(skuid != null && skuid.trim().length() > 0)
		{
			strSql = strSql + " and invent.productid like '%" + skuid + "%'";
		}
		if(lotid != null && lotid.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotid like '%" + lotid + "%'";
		}
		if(traycode != null && traycode.trim().length() > 0)
		{
			strSql = strSql + " and invent.traycode like '%" + traycode + "%'";
		}
		if(locid != null && locid.trim().length() > 0)
		{
			strSql = strSql + " and invent.cargoSpaceId like '%" + locid + "%'";
		}
		return strSql;
	}
	/**
	 * 功能:获得库存查询总记录的SQL语句
	 * @param customerid	客户
	 * @param skuid			产品
	 * @param lotid		批次属性
	 * @param traycode  托盘id
	 * @param locid		库位
	 * @return
	 */
	public static String getInventoryToCountSQL(String customerid, String skuid, String lotid, String traycode, String locid)
	{
		String strSql = "select count(invent) from InventoryStorage as invent where 1=1";
		if(customerid != null && customerid.trim().length() > 0)
		{
			strSql = strSql + " and invent.ownerId like '%" + customerid + "%'";
		}
		if(skuid != null && skuid.trim().length() > 0)
		{
			strSql = strSql + " and invent.productid like '%" + skuid + "%'";
		}
		if(lotid != null && lotid.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotid like '%" + lotid + "%'";
		}
		if(traycode != null && traycode.trim().length() > 0)
		{
			strSql = strSql + " and invent.traycode like '%" + traycode + "%'";
		}
		if(locid != null && locid.trim().length() > 0)
		{
			strSql = strSql + " and invent.cargoSpaceId like '%" + locid + "%'";
		}
		return strSql;
	}
	/**
	 * 功能:根据库存ID获得库存信息
	 * @param strId	库存ID
	 * @return
	 * @throws Exception
	 */
	public InventoryStorage getInventoryInfoToId(String strId) throws Exception
	{
		InventoryStorage invent = null;
		try
		{
			if(strId != null)
			{
				String strSql = "from InventoryStorage as invent where invent.inventoryid='" + strId + "'";
				List ls = m_dao.searchEntities(strSql);
				if(ls != null && ls.size() > 0)
				{
					invent = (InventoryStorage)ls.get(0);
				}
			}
		}catch(Exception er)
		{
			throw new Exception("根据库存ID获得库存信息出错(InventoryAdjustBusImpl.getInventoryInfoToId):" + er.getMessage());
		}
		return invent;
	}
	
	/**
	 * 功能:根据出库异常ID获得出库异常信息
	 * @param strId	库存ID
	 * @return
	 * @throws Exception
	 */
	public InventoryNeedcheck getInventoryNeedcheckInfoById(String strId) throws Exception
	{
		InventoryNeedcheck inventNC = null;
		try
		{
			if(strId != null)
			{
				String strSql = "from InventoryNeedcheck as inventNC where inventNC.needcheckid='" + strId + "'";
				List ls = m_dao.searchEntities(strSql);
				if(ls != null && ls.size() > 0)
				{
					inventNC = (InventoryNeedcheck)ls.get(0);
				}
			}
		}catch(Exception er)
		{
			throw new Exception("根据出库异常ID获得出库异常信息出错(InventoryAdjustBusImpl.getInventoryNeedcheckInfoById):" + er.getMessage());
		}
		return inventNC;
	}
	
	/**
	 * 功能:根据出库异常的货位获得库存信息
	 * @param strId	库存ID
	 * @return
	 * @throws Exception
	 */
	public InventoryStorage getInventoryStorageInfoByCargoSpaceId(String cargoSpaceId) throws Exception
	{
		InventoryStorage invent = null;
		try
		{
			if(cargoSpaceId != null)
			{
				String strSql = "from InventoryStorage as invent where invent.cargoSpaceId='" + cargoSpaceId + "'";
				List ls = m_dao.searchEntities(strSql);
				if(ls != null && ls.size() > 0)
				{
					invent = (InventoryStorage)ls.get(0);
				}
			}
		}catch(Exception er)
		{
			throw new Exception("根据出库异常的货位获得库存信息出错(InventoryAdjustBusImpl.getInventoryStorageInfoByCargoSpaceId):" + er.getMessage());
		}
		return invent;
	}
	
	
	/**
	 * 功能:删除库存调整单详细
	 * @param id	库存调整单详细ID
	 * @throws Exception
	 */
	public void deleteAdjustDetail(String id) throws Exception
	{
		try
		{
			if(id != null)
			{
				String strSql = "delete from InventoryAdjustDetail as ad where ad.adjustdetailid='"+id+"'";
				m_dao.deleteSql(strSql);
			}		
		}catch(Exception er)
		{
			throw new Exception("删除库存调整单详细出错(InventoryAdjustBusImpl.deleteAdjustDetail):" + er.getMessage());
		}	
	}
	/**
	 * 功能:根据调整单详细ID获得库存调整单详细
	 * @param strId	调整单详细ID
	 * @return
	 * @throws Exception
	 */
	public InventoryAdjustDetail getAdjustDetailToId(String strId) throws Exception
	{
		InventoryAdjustDetail detail = null;
		try
		{
			if(strId != null)
			{
				String strSql = "from InventoryAdjustDetail as ad where ad.adjustdetailid='" + strId + "'";
				List ls = m_dao.searchEntities(strSql);
				if(ls != null && ls.size() > 0)
				{
					detail = (InventoryAdjustDetail)ls.get(0);
				}
			}
		}catch(Exception er)
		{
			throw new Exception("根据调整单详细ID获得库存调整单详细出错(InventoryAdjustBusImpl.getAdjustDetailToId):" + er.getMessage());
		}
		return detail;
	}
	/**
	 * 功能:根据调整单ID获得库存调整单详细
	 * @param strId	调整单ID
	 * @return
	 * @throws Exception
	 */
	public List<InventoryAdjustDetail> getAdjustDetailListToId(String strId) throws Exception
	{
		List<InventoryAdjustDetail> ls = null;
		try
		{
			if(strId != null)
			{
				String strSql = "from InventoryAdjustDetail as ad where ad.adjustid='" + strId + "'";
				ls = m_dao.searchEntities(strSql);
			}
		}catch(Exception er)
		{
			throw new Exception("根据调整单ID获得库存调整单详细出错(InventoryAdjustBusImpl.getAdjustDetailListToId):" + er.getMessage());
		}
		return ls;
	}
    /**
     * 功能：库存调整确认
     * @param lsadjustheader  调整单
     * @param lsajustDetail   调整单明细集合
     * @param updatelsinventory  要更新的库存集合
     * @param addkcls         要增加的库存集合
     * @param jobls           作业集合
     * @param jobdells        作业明细集合
     * @param cargospacesls   货位集合
     * @throws Exception
     */
	public void createjustInvoice(InventoryAdjustHeader adjustheader,List<InventoryAdjustDetail> lsajustDetail, List<InventoryStorage> updatelsinventory,List<InventoryStorage> addkcls,List<InoutJob> jobls,List<InoutJobdetail> jobdells,List<BaseCargospace> cargospacesls,SystemLogInfo sysLog) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //对库存调试单进行确定
            session.update(adjustheader);	
            
            //对库存调试单明细进行确定
            if(lsajustDetail != null){
            	InventoryAdjustDetail Detail = null;	
                for(int i = 0; i < lsajustDetail.size(); i++){
                	Detail = lsajustDetail.get(i);
                    session.update(Detail);
                }
            } 
            
            //对库存进行调整
            if(updatelsinventory != null){
            	InventoryStorage Inventory = null;	
                for(int i = 0; i < updatelsinventory.size(); i++){
                	Inventory = updatelsinventory.get(i);
                	if (Inventory.getPnum()==0.0) {//数量为0，删除库存。
						session.delete(Inventory);
					}else {
						session.update(Inventory);
					}     
                }
            } 
            
            //对库存进行调整
            if(addkcls != null){
            	InventoryStorage addStorage = null;	
                for(int i = 0; i < addkcls.size(); i++){
                	addStorage = addkcls.get(i);
                    session.save(addStorage);
                }
            } 
            
            //增加作业
            if(jobls != null){
            	InoutJob job = null;	
                for(int i = 0; i < jobls.size(); i++){
                	job = jobls.get(i);
                    session.save(job);
                }
            }
            
            //增加作业明细
            
            if (jobdells !=null) {
				InoutJobdetail jobdel = null;
				for (int i = 0; i < jobdells.size(); i++) {
					jobdel = jobdells.get(i);
					session.save(jobdel);
				}
			}
            
            //更新货位状态
            
            if (cargospacesls!=null) {
				BaseCargospace cargospace = null;
				for (int i = 0; i < cargospacesls.size(); i++) {
					cargospace = cargospacesls.get(i);
					session.update(cargospace);
				}
			}
            
            if (sysLog!=null) {
				session.save(sysLog);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("库存调试单进行调试确定时出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
		
	}
	/**
     * 功能：库存调整确认
     * @param lsadjustheader  调整单
     * @param lsajustDetail   调整单明细集合
     * @param updatelsinventory  要更新的库存集合
     * @param addkcls         要增加的库存集合
     * @param jobls           作业集合
     * @param jobdells        作业明细集合
     * @param cargospacesls   货位集合
     * @throws Exception
     */
	public void createjustInvoice(InventoryAdjustHeader adjustheader,List<InventoryAdjustDetail> lsajustDetail, List<InventoryStorage> updatelsinventory,List<BaseCargospace> cargospacesls,List<InventoryNeedcheck> lsinventoryNeedcheck,SystemLogInfo sysLog) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //对库存调试单进行确定
            session.update(adjustheader);	
            
            //对库存调试单明细进行确定
            if(lsajustDetail != null){
            	InventoryAdjustDetail Detail = null;	
                for(int i = 0; i < lsajustDetail.size(); i++){
                	Detail = lsajustDetail.get(i);
                    session.update(Detail);
                }
            } 
            
            //对库存进行调整
            if(updatelsinventory != null){
            	InventoryStorage Inventory = null;	
                for(int i = 0; i < updatelsinventory.size(); i++){
                	Inventory = updatelsinventory.get(i);
						session.delete(Inventory);
                }
            } 
            
            //更新货位状态
            
            if (cargospacesls!=null) {
				BaseCargospace cargospace = null;
				for (int i = 0; i < cargospacesls.size(); i++) {
					cargospace = cargospacesls.get(i);
					session.update(cargospace);
				}
			}
            //更新出库异常单状态
            if(lsinventoryNeedcheck!=null){
            	InventoryNeedcheck inventoryNeedcheck = null;
            	for(int i =0; i<lsinventoryNeedcheck.size();i++){
            		inventoryNeedcheck = lsinventoryNeedcheck.get(i);
            		session.update(inventoryNeedcheck);
            	}
            }
            
            
            //保存日志
            if (sysLog!=null) {
				session.save(sysLog);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("库存调试单进行调试确定时出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
		
	}
	
	/**
     * 功能：库存调整确认
     * @param lsadjustheader  调整单
     * @param lsajustDetail   调整单明细集合
     * @param updatelsinventory  要更新的库存集合
     * @param addkcls         要增加的库存集合
     * @param jobls           作业集合
     * @param jobdells        作业明细集合
     * @param cargospacesls   货位集合
     * @throws Exception
     */
	public void createjustInvoice1(InventoryAdjustHeader adjustheader,List<InventoryAdjustDetail> lsajustDetail, List<InventoryStorage> updatelsinventory,List<BaseCargospace> cargospacesls,List<InventoryNeedcheck> lsinventoryNeedcheck,SystemLogInfo sysLog) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //对库存调试单进行确定
            session.update(adjustheader);	
            
            //对库存调试单明细进行确定
            if(lsajustDetail != null){
            	InventoryAdjustDetail Detail = null;	
                for(int i = 0; i < lsajustDetail.size(); i++){
                	Detail = lsajustDetail.get(i);
                    session.update(Detail);
                }
            } 
            
            //对库存进行调整
            if(updatelsinventory != null){
            	InventoryStorage Inventory = null;	
                for(int i = 0; i < updatelsinventory.size(); i++){
                	Inventory = updatelsinventory.get(i);
						session.save(Inventory);
                }
            } 
            
            //更新货位状态
            
            if (cargospacesls!=null) {
				BaseCargospace cargospace = null;
				for (int i = 0; i < cargospacesls.size(); i++) {
					cargospace = cargospacesls.get(i);
					session.update(cargospace);
				}
			}
            //更新出库异常单状态
            if(lsinventoryNeedcheck!=null){
            	InventoryNeedcheck inventoryNeedcheck = null;
            	for(int i =0; i<lsinventoryNeedcheck.size();i++){
            		inventoryNeedcheck = lsinventoryNeedcheck.get(i);
            		session.update(inventoryNeedcheck);
            	}
            }
            
            
            //保存日志
            if (sysLog!=null) {
				session.save(sysLog);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("库存调试单进行调试确定时出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
		
	}
	
	
	
	/**
     * 功能：盘点调整确认
     * @param lsadjustheader  调整单
     * @param lsajustDetail   调整单明细集合
     * @param updatelsinventory  要更新的库存集合
     * @param addkcls         要增加的库存集合
     * @param jobls           作业集合
     * @param jobdells        作业明细集合
     * @param cargospacesls   货位集合
     * @throws Exception
     */
	public void createjustInvoice2(InventoryAdjustHeader adjustheader,List<InventoryAdjustDetail> lsajustDetail, List<InventoryStorage> updatelsinventory,List<BaseCargospace> cargospacesls,List<InventoryCheckResult> lscheckResult,SystemLogInfo sysLog) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //对库存调试单进行确定
            session.update(adjustheader);	
            
            //对库存调试单明细进行确定
            if(lsajustDetail != null){
            	InventoryAdjustDetail Detail = null;	
                for(int i = 0; i < lsajustDetail.size(); i++){
                	Detail = lsajustDetail.get(i);
                    session.update(Detail);
                }
            } 
            
            //对库存进行调整
            if(updatelsinventory != null){
            	InventoryStorage Inventory = null;	
                for(int i = 0; i < updatelsinventory.size(); i++){
                	Inventory = updatelsinventory.get(i);
						session.saveOrUpdate(Inventory);
                }
            } 
            
            //对盘点结果单进行调整
            if(lscheckResult != null){
            	InventoryCheckResult checkResult = null;	
                for(int i = 0; i < lscheckResult.size(); i++){
                	checkResult = lscheckResult.get(i);
						session.saveOrUpdate(checkResult);
                }
            } 
            
            //更新货位状态
            
            if (cargospacesls!=null) {
				BaseCargospace cargospace = null;
				for (int i = 0; i < cargospacesls.size(); i++) {
					cargospace = cargospacesls.get(i);
					session.update(cargospace);
				}
			}
            //更新出库异常单状态
//            if(lsinventoryNeedcheck!=null){
//            	InventoryNeedcheck inventoryNeedcheck = null;
//            	for(int i =0; i<lsinventoryNeedcheck.size();i++){
//            		inventoryNeedcheck = lsinventoryNeedcheck.get(i);
//            		session.update(inventoryNeedcheck);
//            	}
//            }
            
            
            //保存日志
            if (sysLog!=null) {
				session.save(sysLog);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("库存调试单进行调试确定时出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
		
	}
	
	
	/**
     * 功能：调整库存信息确认
     * @param lsadjustheader  调整单
     * @param lsajustDetail   调整单明细集合
     * @param updatelsinventory  要更新的库存集合
     * @param addkcls         要增加的库存集合
     * @param jobls           作业集合
     * @param jobdells        作业明细集合
     * @param cargospacesls   货位集合
     * @throws Exception
     */
	public void createjustInvoice3(InventoryAdjustHeader adjustheader,List<InventoryAdjustDetail> lsajustDetail, List<InventoryStorage> updatelsinventory,List<BaseCargospace> cargospacesls,SystemLogInfo sysLog) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //对库存调试单进行确定
            session.update(adjustheader);	
            
            //对库存调试单明细进行确定
            if(lsajustDetail != null){
            	InventoryAdjustDetail Detail = null;	
                for(int i = 0; i < lsajustDetail.size(); i++){
                	Detail = lsajustDetail.get(i);
                    session.update(Detail);
                }
            } 
            
            //对库存进行调整
            if(updatelsinventory != null){
            	InventoryStorage Inventory = null;	
                for(int i = 0; i < updatelsinventory.size(); i++){
                	Inventory = updatelsinventory.get(i);
                	if(Inventory.getPnum()<=0){
                		session.delete(Inventory);
                	}else{
                		session.saveOrUpdate(Inventory);
                	}
						
                }
            } 
            
            //更新货位状态
            
            if (cargospacesls!=null) {
				BaseCargospace cargospace = null;
				for (int i = 0; i < cargospacesls.size(); i++) {
					cargospace = cargospacesls.get(i);
					session.update(cargospace);
				}
			}
            //更新出库异常单状态
//            if(lsinventoryNeedcheck!=null){
//            	InventoryNeedcheck inventoryNeedcheck = null;
//            	for(int i =0; i<lsinventoryNeedcheck.size();i++){
//            		inventoryNeedcheck = lsinventoryNeedcheck.get(i);
//            		session.update(inventoryNeedcheck);
//            	}
//            }
            
            
            //保存日志
            if (sysLog!=null) {
				session.save(sysLog);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("库存调试单进行调试确定时出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
		
	}
	
	
	
	/**
	 * 库存调整单删除
	 * @param lsajustDetail
	 * @param lsinventory
	 * @param adjust
	 * @throws Exception
	 */
		public void deletejustInvoice(List<BaseCargospace> lscargospace,List<InventoryAdjustDetail> lsajustDetail, List<InventoryStorage> lsinventory, InventoryAdjustHeader adjust) throws Exception {
			
			Session session = null;
	        Transaction tx = null;
	        try{
	            session = m_dao.getSession();
	            tx = session.beginTransaction();
	            
	            //对库存调试单进行确定
	            session.delete(adjust);	
	            
	            //对库存调试单明细进行确定
	            if(lsajustDetail != null){
	            	InventoryAdjustDetail Detail = null;	
	                for(int i = 0; i < lsajustDetail.size(); i++){
	                	Detail = lsajustDetail.get(i);
	                    session.delete(Detail);
	                }
	            } 
	            //对货位进行调整
	            if(lscargospace != null){
	            	BaseCargospace cargospace = null;	
	                for(int i = 0; i < lscargospace.size(); i++){
	                	cargospace = lscargospace.get(i);
	                    session.update(cargospace);
	                }
	            } 
	            //对库存进行调整
	            if(lsinventory != null){
	            	InventoryStorage Inventory = null;	
	                for(int i = 0; i < lsinventory.size(); i++){
	                	Inventory = lsinventory.get(i);
	                    session.update(Inventory);
	                }
	            } 
	            tx.commit();    
	        }catch(Exception er){
	            if(tx != null){
	                tx.rollback();
	            }
	            throw new  Exception("库存调试单进行调试确定时出错："+er.getMessage());
	        }finally{
	            m_dao.closeSession(session);
	        }     
			
		}
		/**
		 功能:增加盘点调整单详细
		 * @param hsSysParam
		 * @param hsCurrentParam
		 * @return
		 * @throws Exception
		 */
	public void addinvenUpdatecarsoAddAdjustDel(InventoryStorage iStorage,
			BaseCargospace cs, InventoryAdjustDetail adjustDetail,InventoryCheckResult checkResult,SystemLogInfo sysLog)
			throws Exception {
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            if (iStorage!=null) {
				session.update(iStorage);//更新库存
			}
            if (cs!=null) {
				session.update(cs);//更新货位状态
			}
            if (adjustDetail!=null) {
				session.save(adjustDetail);//保存调整明细
			}
            if(checkResult!=null){
            	session.update(checkResult);//修改盘点结果单
            }
            if (sysLog!=null) {
				session.save(sysLog);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("库存调试单增加库存信息时出差："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
		
	}
	
	
	
	/**
	 功能:增加调整单详细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public void addinvenUpdatecarsoAddAdjustDel1(InventoryStorage iStorage,
			BaseCargospace cs, InventoryAdjustDetail adjustDetail,SystemLogInfo sysLog)
			throws Exception {
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            if (iStorage!=null) {
				session.update(iStorage);//更新库存
			}
            if (cs!=null) {
				session.update(cs);//更新货位状态
			}
            if (adjustDetail!=null) {
				session.save(adjustDetail);//保存调整明细
			}
            if (sysLog!=null) {
				session.save(sysLog);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("库存调试单增加库存信息时出差："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
		
	}
	
	/**
	 * 功能：增加入库异常调整单详细
	 * @param adjustDetail
	 * @param sysLog
	 * @throws Exception
	 */
	public void addinvenUpdatecarsoAddAdjustDeloutboundExce(InventoryNeedcheck inventoryNC,InventoryAdjustDetail adjustDetail,SystemLogInfo sysLog)
			throws Exception {
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            if(inventoryNC!=null){
            	session.update(inventoryNC);
            }
            if (adjustDetail!=null) {
				session.save(adjustDetail);//保存调整明细
			}
            if (sysLog!=null) {
				session.save(sysLog);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("增加【出库异常】调整单详细出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
		
	}
	
	/**
	 * 功能：增加出库异常调整单详细
	 * @param adjustDetail
	 * @param sysLog
	 * @throws Exception
	 */
	public void addinvenUpdatecarsoAddAdjustDeloutboundExce(InventoryStorage inventory,InventoryNeedcheck inventoryNC,InventoryAdjustDetail adjustDetail,SystemLogInfo sysLog)
			throws Exception {
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            if(inventory!=null){
            	session.update(inventory);
            }
            if(inventoryNC!=null){
            	session.update(inventoryNC);
            }
            if (adjustDetail!=null) {
				session.save(adjustDetail);//保存调整明细
			}
            if (sysLog!=null) {
				session.save(sysLog);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("增加【出库异常】调整单详细出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
		
	}
	
	
	@Override
	public Object[] addinvenForAdjust(InventoryAdjustDetail adjustDetail)
			throws Exception {
		Object[] obj = new Object[4];
		if (adjustDetail!=null) {
			/**************增加已完成的作业和明细*************************/
			InoutJob job = new InoutJob();
			job.setJobid(SeqTool.getID("TZRK", m_dao));
			job.setBoundstockid(adjustDetail.getAdjustid());//单据号
			job.setBoundstockdetailid(adjustDetail.getAdjustdetailid());//单据详细
			job.setWarehouseid(adjustDetail.getWarehouseid());//仓库
			job.setTcargoWhareaId(adjustDetail.getWh_area_id());//库区
			job.setTcargoSpaceId(adjustDetail.getCargo_space_id());//库位
			job.setCreatetime(StrTools.getCurrDateTime(5));//创建时间
			job.setTraycode(adjustDetail.getTtraycode());//托盘条码
			job.setStatus("4");//状态：未执行
			job.setSnumber(String.valueOf(adjustDetail.getRealitypnum()));//作业数量
			job.setOnLineType("2");//联机方式：1.联机，2.脱机
			job.setInOutType("1");//出入库类型：1
			job.setInvoicetype("2");//调整入库。调整单生产的作业。
			job.setIsaccount("Y");//是否记账，记账
			job.setJobtype("22");//调整入库
			
			InoutJobdetail jobdel = new InoutJobdetail();
			jobdel.setJobdetailid(SeqTool.getDetailId(job.getJobid(), "1"));//明细id
			jobdel.setJobid(job.getJobid());//作业id
			jobdel.setPackid(adjustDetail.getTproductid());//设置产品
			jobdel.setPrintdate(adjustDetail.getTprintdate());
			jobdel.setLotid(adjustDetail.getTlotid());//批次类型
			jobdel.setLotinfo(adjustDetail.getTlotinfo());	//批号信息	
			/**************增加库存**********************/
			//增加库存
			InventoryStorage iStorage = new InventoryStorage();
			iStorage.setIndate(StrTools.getCurrDateTime(5));
			iStorage.setInjobetailid(jobdel.getJobdetailid());
			iStorage.setInjobid(job.getJobid());//作业id
			iStorage.setLotid(adjustDetail.getTlotid());//批号类型
			iStorage.setLotinfo(adjustDetail.getTlotinfo());//批号信息
			iStorage.setProductid(adjustDetail.getTproductid());//产品id
			iStorage.setPnum(adjustDetail.getRealitypnum());//库存数量
			iStorage.setProductdate(adjustDetail.getTprintdate());//生产日期
			iStorage.setPunit(adjustDetail.getTpunit());//单位
			iStorage.setStatus("0");//库存状态
			iStorage.setTraycode(adjustDetail.getTtraycode());//托盘条码
			iStorage.setWarehouseid(adjustDetail.getWarehouseid());//仓库
			iStorage.setWhAreaId(adjustDetail.getWh_area_id());//库区
			iStorage.setCargoSpaceId(adjustDetail.getCargo_space_id());//库位
			/************更新库位************/
			ICargoSpaceBus cBus = new CargoSpaceBusImpl(m_dao);
			BaseCargospace cargospace = null;
			if (adjustDetail.getCargo_space_id()!=null) {
				 cargospace = cBus.getCargoSpaceById(adjustDetail.getCargo_space_id());
				 cargospace.setCsstatus("2");
			}
			obj[0]= job;
			obj[1]=jobdel;
			obj[2]= iStorage;
			obj[3]= cargospace;
		}
		
		return obj;
	}
	@Override
	public Object[] updateinvenForAdjust(InventoryAdjustDetail adjustDetail)
			throws Exception {
		Object[] obj = new Object[4];
		if (adjustDetail!=null) {
			if (adjustDetail.getRealitypnum()>adjustDetail.getSyspnum()) {//大于库存，做入库操作
				/**************增加已完成的作业和明细*************************/
				InoutJob job = new InoutJob();
				job.setJobid(SeqTool.getID("TZRK", m_dao));
				job.setBoundstockid(adjustDetail.getAdjustid());//单据号
				job.setBoundstockdetailid(adjustDetail.getAdjustdetailid());//单据详细
				job.setWarehouseid(adjustDetail.getWarehouseid());//仓库
				job.setTcargoWhareaId(adjustDetail.getWh_area_id());//库区
				job.setTcargoSpaceId(adjustDetail.getCargo_space_id());//库位
				job.setCreatetime(StrTools.getCurrDateTime(5));//创建时间
				job.setTraycode(adjustDetail.getStraycode());//托盘条码
				job.setStatus("4");//状态：完成
				job.setSnumber(String.valueOf(adjustDetail.getRealitypnum()-adjustDetail.getSyspnum()));//作业数量
				job.setOnLineType("2");//联机方式：1.联机，2.脱机
				job.setInOutType("1");//出入库类型：1
				job.setInvoicetype("2");//调整入库。调整单生产的作业。
				job.setIsaccount("Y");//是否记账，记账
				job.setJobtype("22");//调整入库
				
				InoutJobdetail jobdel = new InoutJobdetail();
				jobdel.setJobdetailid(SeqTool.getDetailId(job.getJobid(), "1"));//明细id
				jobdel.setJobid(job.getJobid());//作业id
				jobdel.setPackid(adjustDetail.getSproductid());//设置产品
				jobdel.setPrintdate(adjustDetail.getSprintdate());
				jobdel.setLotid(adjustDetail.getSlotid());//批次类型
				jobdel.setLotinfo(adjustDetail.getSlotinfo());	//批号信息
				
				InventoryStorage inventory = getInventoryInfoToId(adjustDetail.getInventoryid());	
				inventory.setStatus("0");//设置为未分配
				inventory.setPnum(adjustDetail.getRealitypnum());//设置更新数量
				
				ICargoSpaceBus cBus = new CargoSpaceBusImpl(m_dao);
				BaseCargospace cargospace = null;
				if (adjustDetail.getCargo_space_id()!=null) {
					 cargospace = cBus.getCargoSpaceById(adjustDetail.getCargo_space_id());
					 cargospace.setCsstatus("2");
				}
				obj[0] = job;
				obj[1] = jobdel;
				obj[2] = inventory;
				obj[3] = cargospace;
			}else {
				/**************增加已完成的作业和明细*************************/
				InoutJob job = new InoutJob();
				job.setJobid(SeqTool.getID("TZCK", m_dao));
				job.setBoundstockid(adjustDetail.getAdjustid());//单据号
				job.setBoundstockdetailid(adjustDetail.getAdjustdetailid());//单据详细
				job.setWarehouseid(adjustDetail.getWarehouseid());//仓库
				job.setTcargoWhareaId(adjustDetail.getWh_area_id());//库区
				job.setTcargoSpaceId(adjustDetail.getCargo_space_id());//库位
				job.setCreatetime(StrTools.getCurrDateTime(5));//创建时间
				job.setTraycode(adjustDetail.getStraycode());//托盘条码
				job.setStatus("4");//状态：完成
				job.setSnumber(String.valueOf(adjustDetail.getSyspnum()-adjustDetail.getRealitypnum()));//作业数量
				job.setOnLineType("2");//联机方式：1.联机，2.脱机
				job.setInOutType("2");//出入库类型：1
				job.setInvoicetype("2");//调整入库。调整单生产的作业。
				job.setIsaccount("Y");//是否记账，记账
				job.setJobtype("21");//调整出库
				
				InoutJobdetail jobdel = new InoutJobdetail();
				jobdel.setJobdetailid(SeqTool.getDetailId(job.getJobid(), "1"));//明细id
				jobdel.setJobid(job.getJobid());//作业id
				jobdel.setPackid(adjustDetail.getSproductid());//设置产品
				jobdel.setPrintdate(adjustDetail.getSprintdate());
				jobdel.setLotid(adjustDetail.getSlotid());//批次类型
				jobdel.setLotinfo(adjustDetail.getSlotinfo());	//批号信息
				
				InventoryStorage inventory = getInventoryInfoToId(adjustDetail.getInventoryid());	
				inventory.setStatus("0");
				inventory.setPnum(adjustDetail.getRealitypnum());
				
				ICargoSpaceBus cBus = new CargoSpaceBusImpl(m_dao);
				BaseCargospace cargospace = null;
				if (adjustDetail.getCargo_space_id()!=null) {
					 cargospace = cBus.getCargoSpaceById(adjustDetail.getCargo_space_id());
					 cargospace.setCsstatus("2");
				}
				obj[0] = job;
				obj[1] = jobdel;
				obj[2] = inventory;	
				obj[3] = cargospace;	
			}
		}
		return obj;
	}


}
