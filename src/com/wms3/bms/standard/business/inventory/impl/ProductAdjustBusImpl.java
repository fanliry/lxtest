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
 * ����:����������
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
	 * ����:��ÿ���������ѯ��SQL���
	 * @param status	״̬
	 * @param customer	�ͻ�
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
	 * ����:��ÿ��������ܼ�¼��ѯ��SQL���
	 * @param status	״̬
	 * @param customer	�ͻ�
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
	 * ����:�����Ʒ��������ϸ��ѯ��SQL���
	 * @param strId	��Ʒ������ID
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
	 * ����:��ÿ���������ϸ��ѯ�ܼ�¼��SQL���
	 * @param strId	��������ID
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
	 * ����:ɾ����Ʒ������
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
    		Logger.error("ɾ����Ʒ����������"+he.getMessage());
		}
    	finally
		{
    		m_dao.closeSession(session);
    	}
	}
	/**
	 * ����:���ݵ�����ID��û�Ʒ������
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
			throw new Exception("���ݵ�����ID��û�Ʒ����������(ProductAdjustBusImpl.getProductAdjustHeaderToId):" + er.getMessage());
		}
		return adjust;
	}
	/**
	 * ����:���»�Ʒ������
	 * @param adjust
	 */
	public void updateProductAdjustHeader(InventoryTransferHeader adjust)
	{
		m_dao.update(adjust);
	}
	
	//******����ѯ******************************************************
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
	//******����ѯ******************************************************
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
	 * ����:��ÿ���ѯ�ܼ�¼��SQL���
	 * @param customerid	�ͻ�
	 * @param skuid			��Ʒ
	 * @param lotid		��������
	 * @param traycode  ����id
	 * @param locid		��λ
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
	 * ����:���ݿ��ID��ÿ����Ϣ
	 * @param strId	���ID
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
			throw new Exception("���ݿ��ID��ÿ����Ϣ����(ProductAdjustBusImpl.getInventoryInfoToId):" + er.getMessage());
		}
		return invent;
	}
	/**
	 * ����:ɾ����Ʒ��������ϸ
	 * @param id	��Ʒ��������ϸID
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
			throw new Exception("ɾ������������ϸ����(ProductAdjustBusImpl.deleteAdjustDetail):" + er.getMessage());
		}	
	}
	/**
	 * ����:���ݵ�������ϸID��û�Ʒ��������ϸ
	 * @param strId	��������ϸID
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
			throw new Exception("���ݵ�������ϸID��û�Ʒ��������ϸ����(ProductAdjustBusImpl.getAdjustDetailToId):" + er.getMessage());
		}
		return detail;
	}
	/**
	 * ����:���ݵ�����ID�����Ʒ��������ϸ
	 * @param strId	������ID
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
			throw new Exception("���ݵ�����ID�����Ʒ��������ϸ����(ProductAdjustBusImpl.getProductAdjustDetailListToId):" + er.getMessage());
		}
		return ls;
	}
/**
 * ������������ȷ��
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
            
            //�Ի�Ʒ���Ե�����ȷ��
            session.update(adjust);	
            
            //�Ի�Ʒ���Ե���ϸ����ȷ��
            if(lsajustDetail != null){
            	InventoryTransferDetail Detail = null;	
                for(int i = 0; i < lsajustDetail.size(); i++){
                	Detail = lsajustDetail.get(i);
                    session.update(Detail);
                }
            }
            //�Կ����е���
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
            throw new  Exception("��Ʒ���Ե����е���ȷ��ʱ����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
		
	}
	/**
	 * ��Ʒ������ɾ��
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
	            
	            //�Կ����Ե�����ȷ��
	            session.delete(adjust);	
	            
	            //�Կ����Ե���ϸ����ȷ��
	            if(lsajustDetail != null){
	            	InventoryTransferDetail Detail = null;	
	                for(int i = 0; i < lsajustDetail.size(); i++){
	                	Detail = lsajustDetail.get(i);
	                    session.delete(Detail);
	                }
	            } 
	            
	            //�Կ����е���
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
	            throw new  Exception("�����Ե����е���ȷ��ʱ����"+er.getMessage());
	        }finally{
	            m_dao.closeSession(session);
	        }     
			
		}

}
