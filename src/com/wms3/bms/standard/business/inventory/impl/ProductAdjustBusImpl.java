package com.wms3.bms.standard.business.inventory.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.business.inventory.ProductAdjustBus;
import com.wms3.bms.standard.entity.inventory.InventoryTransferDetail;
import com.wms3.bms.standard.entity.inventory.InventoryTransferHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;


/**
 * 描述:库存调整管理
 * @author yao
 *
 */
public class ProductAdjustBusImpl implements ProductAdjustBus
{
	protected EntityDAO m_dao = null;
	
	public ProductAdjustBusImpl(EntityDAO dao)
	{
		m_dao = dao;
	}
	/**
	 * 功能:获得库存调整单查询的SQL语句
	 * @param status	状态
	 * @param customer	客户
	 * @return
	 */
	public String getProductAdjustHeaderSQL(String status, String customer)
	{
		String strSql = "from InventoryTransferHeader as ah where 1=1";
		if(status != null && status.trim().length() > 0)
		{
			strSql = strSql + " and ah.status like '" + status + "'";
		}
		if(customer != null && customer.trim().length() > 0)
		{
			strSql = strSql + " and ah.customerid like '%" + customer + "%'";
		}
		return strSql;
	}
	/**
	 * 功能:获得库存调整单总记录查询的SQL语句
	 * @param status	状态
	 * @param customer	客户
	 * @return
	 */
	public String getProductAdjustHeaderCountSQL(String status, String customer)
	{
		String strSql = "select count(ah) from InventoryTransferHeader as ah where 1=1";
		if(status != null && status.trim().length() > 0)
		{
			strSql = strSql + " and ah.status like '%" + status + "%'";
		}
		if(customer != null && customer.trim().length() > 0)
		{
			strSql = strSql + " and ah.customerid like '%" + customer + "%'";
		}
		return strSql;
	}
	/**
	 * 功能:获得物品调整单详细查询的SQL语句
	 * @param strId	物品调整单ID
	 * @return
	 */
	public static String getProductAdjustDetailSQL(String strId)
	{
		String strSql = "from InventoryTransferDetail as ad where 1=1";
		if(strId != null && strId.trim().length() > 0)
		{
			strSql = strSql + " and ad.transferid='" + strId + "'";
		}
		return strSql;
	}
	/**
	 * 功能:获得库存调整单详细查询总记录的SQL语句
	 * @param strId	库存调整单ID
	 * @return
	 */
	public static String getProductAdjustDetailCountSQL(String strId)
	{
		String strSql = "select count(ad) from InventoryTransferDetail as ad where 1=1";
		if(strId != null && strId.trim().length() > 0)
		{
			strSql = strSql + " and ad.transferid='" + strId + "'";
		}
		return strSql;
	}
	/**
	 * 功能:删除货品调整单
	 * @param id
	 */
	public void deleteProductAdjustHeader(String id)
	{
		Session session = m_dao.getSession();
    	try
		{
    		
    		String strSql0 = "delete from InventoryTransferHeader as ah where ah.transferid='"+id+"'";
    		Transaction tx= session.beginTransaction();
    		session.createQuery(strSql0).executeUpdate();
			tx.commit();
        }
    	catch(HibernateException he)
		{
    		Logger.error("删除货品调整单出错！"+he.getMessage());
		}
    	finally
		{
    		m_dao.closeSession(session);
    	}
	}
	/**
	 * 功能:根据调整单ID获得货品调整单
	 * @param strId
	 * @return
	 * @throws Exception
	 */
	public InventoryTransferHeader getProductAdjustHeaderToId(String strId) throws Exception
	{
		InventoryTransferHeader adjust = null;
		try
		{
			if(strId != null)
			{
				String strSql = "from InventoryTransferHeader as ah where ah.transferid='" + strId + "'";
				List ls = m_dao.searchEntities(strSql);
				if(ls != null && ls.size() > 0)
				{
					adjust = (InventoryTransferHeader)ls.get(0);
				}
			}
		}catch(Exception er)
		{
			throw new Exception("根据调整单ID获得货品调整单出错(ProductAdjustBusImpl.getProductAdjustHeaderToId):" + er.getMessage());
		}
		return adjust;
	}
	/**
	 * 功能:更新货品调整单
	 * @param adjust
	 */
	public void updateProductAdjustHeader(InventoryTransferHeader adjust)
	{
		m_dao.update(adjust);
	}
	
	//******库存查询******************************************************
	/**
	 * 
	 */
	public static String getInventoryToSQL(String strwarehouseid,String status,String strCustomerId,String strwhAreaId,String strcargoAreaId,String strfmpackid,String strfmpunit,String strproductid,String strLotid,String strfmtraycode,String lt1,String lt2,String lt3,String lt4,String lt5,String lt6,String lt7,String lt8,String lt9,String lt10,String lt11,String lt12)
	{
		String strSql = "from InventoryStorage as invent where 1=1";
		if(strwarehouseid != null && strwarehouseid.trim().length() > 0)
		{
			strSql = strSql + " and invent.warehouseid like '%" + strwarehouseid + "%'";
		}
		if(status != null && status.trim().length() > 0)
		{
			strSql = strSql + " and invent.status = '" + status + "'";
		}
		if(strCustomerId != null && strCustomerId.trim().length() > 0)
		{
			strSql = strSql + " and invent.ownerId like '%" + strCustomerId + "%'";
		}
		if(strwhAreaId != null && strwhAreaId.trim().length() > 0)
		{
			strSql = strSql + " and invent.whAreaId like '%" + strwhAreaId + "%'";
		}
		if(strcargoAreaId != null && strcargoAreaId.trim().length() > 0)
		{
			strSql = strSql + " and invent.cargoAreaId like '%" + strcargoAreaId + "%'";
		}
		if(strfmpackid != null && strfmpackid.trim().length() > 0)
		{
			strSql = strSql + " and invent.packid like '%" + strfmpackid + "%'";
		}
		
		if(strfmpunit != null && strfmpunit.trim().length() > 0)
		{
			strSql = strSql + " and invent.punit like '%" + strfmpunit + "%'";
		}
		if(strproductid != null && strproductid.trim().length() > 0)
		{
			strSql = strSql + " and invent.productid like '%" + strproductid + "%'";
		}
		if(strLotid != null && strLotid.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotid like '%" + strLotid + "%'";
		}
		if(strfmtraycode != null && strfmtraycode.trim().length() > 0)
		{
			strSql = strSql + " and invent.traycode like '%" + strfmtraycode + "%'";
		}
		if(lt1 != null && lt1.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt1 like '%" + lt1 + "%'";
		}
		if(lt2 != null && lt2.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt2 like '%" + lt2 + "%'";
		}
		if(lt3 != null && lt3.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt3 like '%" + lt3 + "%'";
		}
		if(lt4 != null && lt4.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt4 like '%" + lt4 + "%'";
		}
		if(lt5 != null && lt5.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt5 like '%" + lt5 + "%'";
		}
		if(lt6 != null && lt6.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt6 like '%" + lt6 + "%'";
		}
		if(lt7 != null && lt7.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt7 like '%" + lt7 + "%'";
		}
		if(lt8 != null && lt8.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt8 like '%" + lt8 + "%'";
		}
		if(lt9 != null && lt9.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt9 like '%" + lt9 + "%'";
		}
		if(lt10 != null && lt10.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt10 like '%" + lt10 + "%'";
		}
		if(lt11 != null && lt11.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt11 like '%" + lt11 + "%'";
		}
		if(lt12 != null && lt12.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt12 like '%" + lt12 + "%'";
		}
		return strSql;
	}
	//******库存查询******************************************************
	/**
	 * 
	 */
	public static String getInventoryToCountSQL(String strwarehouseid,String status,String strCustomerId,String strwhAreaId,String strcargoAreaId,String strfmpackid,String strfmpunit,String strproductid,String strLotid,String strfmtraycode,String lt1,String lt2,String lt3,String lt4,String lt5,String lt6,String lt7,String lt8,String lt9,String lt10,String lt11,String lt12)
	{
		String strSql = "select count(invent) from InventoryStorage as invent where 1=1";
		if(strwarehouseid != null && strwarehouseid.trim().length() > 0)
		{
			strSql = strSql + " and invent.warehouseid like '%" + strwarehouseid + "%'";
		}
		if(status != null && status.trim().length() > 0)
		{
			strSql = strSql + " and invent.status = '" + status + "'";
		}
		if(strCustomerId != null && strCustomerId.trim().length() > 0)
		{
			strSql = strSql + " and invent.ownerId like '%" + strCustomerId + "%'";
		}
		if(strwhAreaId != null && strwhAreaId.trim().length() > 0)
		{
			strSql = strSql + " and invent.whAreaId like '%" + strwhAreaId + "%'";
		}
		if(strcargoAreaId != null && strcargoAreaId.trim().length() > 0)
		{
			strSql = strSql + " and invent.cargoAreaId like '%" + strcargoAreaId + "%'";
		}
		if(strfmpackid != null && strfmpackid.trim().length() > 0)
		{
			strSql = strSql + " and invent.packid like '%" + strfmpackid + "%'";
		}
		
		if(strfmpunit != null && strfmpunit.trim().length() > 0)
		{
			strSql = strSql + " and invent.punit like '%" + strfmpunit + "%'";
		}
		if(strproductid != null && strproductid.trim().length() > 0)
		{
			strSql = strSql + " and invent.productid like '%" + strproductid + "%'";
		}
		if(strLotid != null && strLotid.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotid like '%" + strLotid + "%'";
		}
		if(strfmtraycode != null && strfmtraycode.trim().length() > 0)
		{
			strSql = strSql + " and invent.traycode like '%" + strfmtraycode + "%'";
		}
		if(lt1 != null && lt1.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt1 like '%" + lt1 + "%'";
		}
		if(lt2 != null && lt2.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt2 like '%" + lt2 + "%'";
		}
		if(lt3 != null && lt3.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt3 like '%" + lt3 + "%'";
		}
		if(lt4 != null && lt4.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt4 like '%" + lt4 + "%'";
		}
		if(lt5 != null && lt5.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt5 like '%" + lt5 + "%'";
		}
		if(lt6 != null && lt6.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt6 like '%" + lt6 + "%'";
		}
		if(lt7 != null && lt7.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt7 like '%" + lt7 + "%'";
		}
		if(lt8 != null && lt8.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt8 like '%" + lt8 + "%'";
		}
		if(lt9 != null && lt9.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt9 like '%" + lt9 + "%'";
		}
		if(lt10 != null && lt10.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt10 like '%" + lt10 + "%'";
		}
		if(lt11 != null && lt11.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt11 like '%" + lt11 + "%'";
		}
		if(lt12 != null && lt12.trim().length() > 0)
		{
			strSql = strSql + " and invent.lotatt12 like '%" + lt12 + "%'";
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
			throw new Exception("根据库存ID获得库存信息出错(ProductAdjustBusImpl.getInventoryInfoToId):" + er.getMessage());
		}
		return invent;
	}
	/**
	 * 功能:删除货品调整单详细
	 * @param id	货品调整单详细ID
	 * @throws Exception
	 */
	public void deleteProductAdjustDetail(String id) throws Exception
	{
		try
		{
			if(id != null)
			{
				String strSql = "delete from InventoryTransferDetail as ad where ad.transferdetailid='"+id+"'";
				m_dao.deleteSql(strSql);
			}		
		}catch(Exception er)
		{
			throw new Exception("删除库存调整单详细出错(ProductAdjustBusImpl.deleteAdjustDetail):" + er.getMessage());
		}	
	}
	/**
	 * 功能:根据调整单详细ID获得货品调整单详细
	 * @param strId	调整单详细ID
	 * @return
	 * @throws Exception
	 */
	public InventoryTransferDetail getProductAdjustDetailToId(String strId) throws Exception
	{
		InventoryTransferDetail detail = null;
		try
		{
			if(strId != null)
			{
				String strSql = "from InventoryTransferDetail as ad where ad.transferdetailid='" + strId + "'";
				List ls = m_dao.searchEntities(strSql);
				if(ls != null && ls.size() > 0)
				{
					detail = (InventoryTransferDetail)ls.get(0);
				}
			}
		}catch(Exception er)
		{
			throw new Exception("根据调整单详细ID获得货品调整单详细出错(ProductAdjustBusImpl.getAdjustDetailToId):" + er.getMessage());
		}
		return detail;
	}
	/**
	 * 功能:根据调整单ID获得物品调整单详细
	 * @param strId	调整单ID
	 * @return
	 * @throws Exception
	 */
	public List getProductAdjustDetailListToId(String strId) throws Exception
	{
		List ls = null;
		try
		{
			if(strId != null)
			{
				String strSql = "from InventoryTransferDetail as ad where ad.transferid='" + strId + "'";
				ls = m_dao.searchEntities(strSql);
			}
		}catch(Exception er)
		{
			throw new Exception("根据调整单ID获得物品调整单详细出错(ProductAdjustBusImpl.getProductAdjustDetailListToId):" + er.getMessage());
		}
		return ls;
	}
/**
 * 库存调整单调整确定
 * @param lsajustDetail
 * @param lsinventory
 * @param adjust
 * @throws Exception
 */
	public void createjustInvoice(List<InventoryTransferDetail> lsajustDetail, List<InventoryStorage> lsinventory, InventoryTransferHeader adjust) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //对货品调试单进行确定
            session.update(adjust);	
            
            //对货品调试单明细进行确定
            if(lsajustDetail != null){
            	InventoryTransferDetail Detail = null;	
                for(int i = 0; i < lsajustDetail.size(); i++){
                	Detail = lsajustDetail.get(i);
                    session.update(Detail);
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
            throw new  Exception("货品调试单进行调试确定时出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
		
	}
	/**
	 * 货品调整单删除
	 * @param lsajustDetail
	 * @param lsinventory
	 * @param adjust
	 * @throws Exception
	 */
		public void deletejustInvoice(List<InventoryTransferDetail> lsajustDetail, List<InventoryStorage> lsinventory, InventoryTransferHeader adjust) throws Exception {
			
			Session session = null;
	        Transaction tx = null;
	        try{
	            session = m_dao.getSession();
	            tx = session.beginTransaction();
	            
	            //对库存调试单进行确定
	            session.delete(adjust);	
	            
	            //对库存调试单明细进行确定
	            if(lsajustDetail != null){
	            	InventoryTransferDetail Detail = null;	
	                for(int i = 0; i < lsajustDetail.size(); i++){
	                	Detail = lsajustDetail.get(i);
	                    session.delete(Detail);
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

}
