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
 * ����:����������
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
	 * ����:��ÿ���������ѯ��SQL���
	 * @param status	״̬
	 * @param customer	�ͻ�
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
	 * ����:��ÿ��������ܼ�¼��ѯ��SQL���
	 * @param status	״̬
	 * @param customer	�ͻ�
	 * @return
	 */
	public String getAdjustHeaderCountSQL(String sql)
	{
		String strSql = "select count(ah) " + sql;

		return strSql;
	}
	/**
	 * ����:��ÿ���������ϸ��ѯ��SQL���
	 * @param strId	��������ID
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
	 * ����:��ÿ���������ϸ��ѯ�ܼ�¼��SQL���
	 * @param strId	��������ID
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
	 * ����:ɾ����������
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
    		Logger.error("ɾ��������������"+he.getMessage());
		}
    	finally
		{
    		m_dao.closeSession(session);
    	}
	}
	/**
	 * ����:���ݵ�����ID��ÿ�������
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
			throw new Exception("���ݵ�����ID��ÿ�����������(InventoryAdjustBusImpl.getAdjustHeaderToId):" + er.getMessage());
		}
		return adjust;
	}
	/**
	 * ����:���¿�������
	 * @param adjust
	 */
	public void updateAdjustHeader(InventoryAdjustHeader adjust)
	{
		m_dao.update(adjust);
	}
	
	//******����ѯ******************************************************
	/**
	 * ����:��ÿ���ѯ��SQL���
	 * @param customerid	�ͻ�
	 * @param skuid			��Ʒ
	 * @param lotid		��������
	 * @param traycode  ����id
	 * @param locid		��λ
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
			throw new Exception("���ݿ��ID��ÿ����Ϣ����(InventoryAdjustBusImpl.getInventoryInfoToId):" + er.getMessage());
		}
		return invent;
	}
	
	/**
	 * ����:���ݳ����쳣ID��ó����쳣��Ϣ
	 * @param strId	���ID
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
			throw new Exception("���ݳ����쳣ID��ó����쳣��Ϣ����(InventoryAdjustBusImpl.getInventoryNeedcheckInfoById):" + er.getMessage());
		}
		return inventNC;
	}
	
	/**
	 * ����:���ݳ����쳣�Ļ�λ��ÿ����Ϣ
	 * @param strId	���ID
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
			throw new Exception("���ݳ����쳣�Ļ�λ��ÿ����Ϣ����(InventoryAdjustBusImpl.getInventoryStorageInfoByCargoSpaceId):" + er.getMessage());
		}
		return invent;
	}
	
	
	/**
	 * ����:ɾ������������ϸ
	 * @param id	����������ϸID
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
			throw new Exception("ɾ������������ϸ����(InventoryAdjustBusImpl.deleteAdjustDetail):" + er.getMessage());
		}	
	}
	/**
	 * ����:���ݵ�������ϸID��ÿ���������ϸ
	 * @param strId	��������ϸID
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
			throw new Exception("���ݵ�������ϸID��ÿ���������ϸ����(InventoryAdjustBusImpl.getAdjustDetailToId):" + er.getMessage());
		}
		return detail;
	}
	/**
	 * ����:���ݵ�����ID��ÿ���������ϸ
	 * @param strId	������ID
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
			throw new Exception("���ݵ�����ID��ÿ���������ϸ����(InventoryAdjustBusImpl.getAdjustDetailListToId):" + er.getMessage());
		}
		return ls;
	}
    /**
     * ���ܣ�������ȷ��
     * @param lsadjustheader  ������
     * @param lsajustDetail   ��������ϸ����
     * @param updatelsinventory  Ҫ���µĿ�漯��
     * @param addkcls         Ҫ���ӵĿ�漯��
     * @param jobls           ��ҵ����
     * @param jobdells        ��ҵ��ϸ����
     * @param cargospacesls   ��λ����
     * @throws Exception
     */
	public void createjustInvoice(InventoryAdjustHeader adjustheader,List<InventoryAdjustDetail> lsajustDetail, List<InventoryStorage> updatelsinventory,List<InventoryStorage> addkcls,List<InoutJob> jobls,List<InoutJobdetail> jobdells,List<BaseCargospace> cargospacesls,SystemLogInfo sysLog) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //�Կ����Ե�����ȷ��
            session.update(adjustheader);	
            
            //�Կ����Ե���ϸ����ȷ��
            if(lsajustDetail != null){
            	InventoryAdjustDetail Detail = null;	
                for(int i = 0; i < lsajustDetail.size(); i++){
                	Detail = lsajustDetail.get(i);
                    session.update(Detail);
                }
            } 
            
            //�Կ����е���
            if(updatelsinventory != null){
            	InventoryStorage Inventory = null;	
                for(int i = 0; i < updatelsinventory.size(); i++){
                	Inventory = updatelsinventory.get(i);
                	if (Inventory.getPnum()==0.0) {//����Ϊ0��ɾ����档
						session.delete(Inventory);
					}else {
						session.update(Inventory);
					}     
                }
            } 
            
            //�Կ����е���
            if(addkcls != null){
            	InventoryStorage addStorage = null;	
                for(int i = 0; i < addkcls.size(); i++){
                	addStorage = addkcls.get(i);
                    session.save(addStorage);
                }
            } 
            
            //������ҵ
            if(jobls != null){
            	InoutJob job = null;	
                for(int i = 0; i < jobls.size(); i++){
                	job = jobls.get(i);
                    session.save(job);
                }
            }
            
            //������ҵ��ϸ
            
            if (jobdells !=null) {
				InoutJobdetail jobdel = null;
				for (int i = 0; i < jobdells.size(); i++) {
					jobdel = jobdells.get(i);
					session.save(jobdel);
				}
			}
            
            //���»�λ״̬
            
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
            throw new  Exception("�����Ե����е���ȷ��ʱ����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
		
	}
	/**
     * ���ܣ�������ȷ��
     * @param lsadjustheader  ������
     * @param lsajustDetail   ��������ϸ����
     * @param updatelsinventory  Ҫ���µĿ�漯��
     * @param addkcls         Ҫ���ӵĿ�漯��
     * @param jobls           ��ҵ����
     * @param jobdells        ��ҵ��ϸ����
     * @param cargospacesls   ��λ����
     * @throws Exception
     */
	public void createjustInvoice(InventoryAdjustHeader adjustheader,List<InventoryAdjustDetail> lsajustDetail, List<InventoryStorage> updatelsinventory,List<BaseCargospace> cargospacesls,List<InventoryNeedcheck> lsinventoryNeedcheck,SystemLogInfo sysLog) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //�Կ����Ե�����ȷ��
            session.update(adjustheader);	
            
            //�Կ����Ե���ϸ����ȷ��
            if(lsajustDetail != null){
            	InventoryAdjustDetail Detail = null;	
                for(int i = 0; i < lsajustDetail.size(); i++){
                	Detail = lsajustDetail.get(i);
                    session.update(Detail);
                }
            } 
            
            //�Կ����е���
            if(updatelsinventory != null){
            	InventoryStorage Inventory = null;	
                for(int i = 0; i < updatelsinventory.size(); i++){
                	Inventory = updatelsinventory.get(i);
						session.delete(Inventory);
                }
            } 
            
            //���»�λ״̬
            
            if (cargospacesls!=null) {
				BaseCargospace cargospace = null;
				for (int i = 0; i < cargospacesls.size(); i++) {
					cargospace = cargospacesls.get(i);
					session.update(cargospace);
				}
			}
            //���³����쳣��״̬
            if(lsinventoryNeedcheck!=null){
            	InventoryNeedcheck inventoryNeedcheck = null;
            	for(int i =0; i<lsinventoryNeedcheck.size();i++){
            		inventoryNeedcheck = lsinventoryNeedcheck.get(i);
            		session.update(inventoryNeedcheck);
            	}
            }
            
            
            //������־
            if (sysLog!=null) {
				session.save(sysLog);
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
	
	/**
     * ���ܣ�������ȷ��
     * @param lsadjustheader  ������
     * @param lsajustDetail   ��������ϸ����
     * @param updatelsinventory  Ҫ���µĿ�漯��
     * @param addkcls         Ҫ���ӵĿ�漯��
     * @param jobls           ��ҵ����
     * @param jobdells        ��ҵ��ϸ����
     * @param cargospacesls   ��λ����
     * @throws Exception
     */
	public void createjustInvoice1(InventoryAdjustHeader adjustheader,List<InventoryAdjustDetail> lsajustDetail, List<InventoryStorage> updatelsinventory,List<BaseCargospace> cargospacesls,List<InventoryNeedcheck> lsinventoryNeedcheck,SystemLogInfo sysLog) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //�Կ����Ե�����ȷ��
            session.update(adjustheader);	
            
            //�Կ����Ե���ϸ����ȷ��
            if(lsajustDetail != null){
            	InventoryAdjustDetail Detail = null;	
                for(int i = 0; i < lsajustDetail.size(); i++){
                	Detail = lsajustDetail.get(i);
                    session.update(Detail);
                }
            } 
            
            //�Կ����е���
            if(updatelsinventory != null){
            	InventoryStorage Inventory = null;	
                for(int i = 0; i < updatelsinventory.size(); i++){
                	Inventory = updatelsinventory.get(i);
						session.save(Inventory);
                }
            } 
            
            //���»�λ״̬
            
            if (cargospacesls!=null) {
				BaseCargospace cargospace = null;
				for (int i = 0; i < cargospacesls.size(); i++) {
					cargospace = cargospacesls.get(i);
					session.update(cargospace);
				}
			}
            //���³����쳣��״̬
            if(lsinventoryNeedcheck!=null){
            	InventoryNeedcheck inventoryNeedcheck = null;
            	for(int i =0; i<lsinventoryNeedcheck.size();i++){
            		inventoryNeedcheck = lsinventoryNeedcheck.get(i);
            		session.update(inventoryNeedcheck);
            	}
            }
            
            
            //������־
            if (sysLog!=null) {
				session.save(sysLog);
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
	
	
	
	/**
     * ���ܣ��̵����ȷ��
     * @param lsadjustheader  ������
     * @param lsajustDetail   ��������ϸ����
     * @param updatelsinventory  Ҫ���µĿ�漯��
     * @param addkcls         Ҫ���ӵĿ�漯��
     * @param jobls           ��ҵ����
     * @param jobdells        ��ҵ��ϸ����
     * @param cargospacesls   ��λ����
     * @throws Exception
     */
	public void createjustInvoice2(InventoryAdjustHeader adjustheader,List<InventoryAdjustDetail> lsajustDetail, List<InventoryStorage> updatelsinventory,List<BaseCargospace> cargospacesls,List<InventoryCheckResult> lscheckResult,SystemLogInfo sysLog) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //�Կ����Ե�����ȷ��
            session.update(adjustheader);	
            
            //�Կ����Ե���ϸ����ȷ��
            if(lsajustDetail != null){
            	InventoryAdjustDetail Detail = null;	
                for(int i = 0; i < lsajustDetail.size(); i++){
                	Detail = lsajustDetail.get(i);
                    session.update(Detail);
                }
            } 
            
            //�Կ����е���
            if(updatelsinventory != null){
            	InventoryStorage Inventory = null;	
                for(int i = 0; i < updatelsinventory.size(); i++){
                	Inventory = updatelsinventory.get(i);
						session.saveOrUpdate(Inventory);
                }
            } 
            
            //���̵��������е���
            if(lscheckResult != null){
            	InventoryCheckResult checkResult = null;	
                for(int i = 0; i < lscheckResult.size(); i++){
                	checkResult = lscheckResult.get(i);
						session.saveOrUpdate(checkResult);
                }
            } 
            
            //���»�λ״̬
            
            if (cargospacesls!=null) {
				BaseCargospace cargospace = null;
				for (int i = 0; i < cargospacesls.size(); i++) {
					cargospace = cargospacesls.get(i);
					session.update(cargospace);
				}
			}
            //���³����쳣��״̬
//            if(lsinventoryNeedcheck!=null){
//            	InventoryNeedcheck inventoryNeedcheck = null;
//            	for(int i =0; i<lsinventoryNeedcheck.size();i++){
//            		inventoryNeedcheck = lsinventoryNeedcheck.get(i);
//            		session.update(inventoryNeedcheck);
//            	}
//            }
            
            
            //������־
            if (sysLog!=null) {
				session.save(sysLog);
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
	
	
	/**
     * ���ܣ����������Ϣȷ��
     * @param lsadjustheader  ������
     * @param lsajustDetail   ��������ϸ����
     * @param updatelsinventory  Ҫ���µĿ�漯��
     * @param addkcls         Ҫ���ӵĿ�漯��
     * @param jobls           ��ҵ����
     * @param jobdells        ��ҵ��ϸ����
     * @param cargospacesls   ��λ����
     * @throws Exception
     */
	public void createjustInvoice3(InventoryAdjustHeader adjustheader,List<InventoryAdjustDetail> lsajustDetail, List<InventoryStorage> updatelsinventory,List<BaseCargospace> cargospacesls,SystemLogInfo sysLog) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //�Կ����Ե�����ȷ��
            session.update(adjustheader);	
            
            //�Կ����Ե���ϸ����ȷ��
            if(lsajustDetail != null){
            	InventoryAdjustDetail Detail = null;	
                for(int i = 0; i < lsajustDetail.size(); i++){
                	Detail = lsajustDetail.get(i);
                    session.update(Detail);
                }
            } 
            
            //�Կ����е���
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
            
            //���»�λ״̬
            
            if (cargospacesls!=null) {
				BaseCargospace cargospace = null;
				for (int i = 0; i < cargospacesls.size(); i++) {
					cargospace = cargospacesls.get(i);
					session.update(cargospace);
				}
			}
            //���³����쳣��״̬
//            if(lsinventoryNeedcheck!=null){
//            	InventoryNeedcheck inventoryNeedcheck = null;
//            	for(int i =0; i<lsinventoryNeedcheck.size();i++){
//            		inventoryNeedcheck = lsinventoryNeedcheck.get(i);
//            		session.update(inventoryNeedcheck);
//            	}
//            }
            
            
            //������־
            if (sysLog!=null) {
				session.save(sysLog);
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
	
	
	
	/**
	 * ��������ɾ��
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
	            
	            //�Կ����Ե�����ȷ��
	            session.delete(adjust);	
	            
	            //�Կ����Ե���ϸ����ȷ��
	            if(lsajustDetail != null){
	            	InventoryAdjustDetail Detail = null;	
	                for(int i = 0; i < lsajustDetail.size(); i++){
	                	Detail = lsajustDetail.get(i);
	                    session.delete(Detail);
	                }
	            } 
	            //�Ի�λ���е���
	            if(lscargospace != null){
	            	BaseCargospace cargospace = null;	
	                for(int i = 0; i < lscargospace.size(); i++){
	                	cargospace = lscargospace.get(i);
	                    session.update(cargospace);
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
		/**
		 ����:�����̵��������ϸ
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
				session.update(iStorage);//���¿��
			}
            if (cs!=null) {
				session.update(cs);//���»�λ״̬
			}
            if (adjustDetail!=null) {
				session.save(adjustDetail);//���������ϸ
			}
            if(checkResult!=null){
            	session.update(checkResult);//�޸��̵�����
            }
            if (sysLog!=null) {
				session.save(sysLog);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�����Ե����ӿ����Ϣʱ���"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
		
	}
	
	
	
	/**
	 ����:���ӵ�������ϸ
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
				session.update(iStorage);//���¿��
			}
            if (cs!=null) {
				session.update(cs);//���»�λ״̬
			}
            if (adjustDetail!=null) {
				session.save(adjustDetail);//���������ϸ
			}
            if (sysLog!=null) {
				session.save(sysLog);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�����Ե����ӿ����Ϣʱ���"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
		
	}
	
	/**
	 * ���ܣ���������쳣��������ϸ
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
				session.save(adjustDetail);//���������ϸ
			}
            if (sysLog!=null) {
				session.save(sysLog);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("���ӡ������쳣����������ϸ����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
		
	}
	
	/**
	 * ���ܣ����ӳ����쳣��������ϸ
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
				session.save(adjustDetail);//���������ϸ
			}
            if (sysLog!=null) {
				session.save(sysLog);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("���ӡ������쳣����������ϸ����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }   
		
	}
	
	
	@Override
	public Object[] addinvenForAdjust(InventoryAdjustDetail adjustDetail)
			throws Exception {
		Object[] obj = new Object[4];
		if (adjustDetail!=null) {
			/**************��������ɵ���ҵ����ϸ*************************/
			InoutJob job = new InoutJob();
			job.setJobid(SeqTool.getID("TZRK", m_dao));
			job.setBoundstockid(adjustDetail.getAdjustid());//���ݺ�
			job.setBoundstockdetailid(adjustDetail.getAdjustdetailid());//������ϸ
			job.setWarehouseid(adjustDetail.getWarehouseid());//�ֿ�
			job.setTcargoWhareaId(adjustDetail.getWh_area_id());//����
			job.setTcargoSpaceId(adjustDetail.getCargo_space_id());//��λ
			job.setCreatetime(StrTools.getCurrDateTime(5));//����ʱ��
			job.setTraycode(adjustDetail.getTtraycode());//��������
			job.setStatus("4");//״̬��δִ��
			job.setSnumber(String.valueOf(adjustDetail.getRealitypnum()));//��ҵ����
			job.setOnLineType("2");//������ʽ��1.������2.�ѻ�
			job.setInOutType("1");//��������ͣ�1
			job.setInvoicetype("2");//������⡣��������������ҵ��
			job.setIsaccount("Y");//�Ƿ���ˣ�����
			job.setJobtype("22");//�������
			
			InoutJobdetail jobdel = new InoutJobdetail();
			jobdel.setJobdetailid(SeqTool.getDetailId(job.getJobid(), "1"));//��ϸid
			jobdel.setJobid(job.getJobid());//��ҵid
			jobdel.setPackid(adjustDetail.getTproductid());//���ò�Ʒ
			jobdel.setPrintdate(adjustDetail.getTprintdate());
			jobdel.setLotid(adjustDetail.getTlotid());//��������
			jobdel.setLotinfo(adjustDetail.getTlotinfo());	//������Ϣ	
			/**************���ӿ��**********************/
			//���ӿ��
			InventoryStorage iStorage = new InventoryStorage();
			iStorage.setIndate(StrTools.getCurrDateTime(5));
			iStorage.setInjobetailid(jobdel.getJobdetailid());
			iStorage.setInjobid(job.getJobid());//��ҵid
			iStorage.setLotid(adjustDetail.getTlotid());//��������
			iStorage.setLotinfo(adjustDetail.getTlotinfo());//������Ϣ
			iStorage.setProductid(adjustDetail.getTproductid());//��Ʒid
			iStorage.setPnum(adjustDetail.getRealitypnum());//�������
			iStorage.setProductdate(adjustDetail.getTprintdate());//��������
			iStorage.setPunit(adjustDetail.getTpunit());//��λ
			iStorage.setStatus("0");//���״̬
			iStorage.setTraycode(adjustDetail.getTtraycode());//��������
			iStorage.setWarehouseid(adjustDetail.getWarehouseid());//�ֿ�
			iStorage.setWhAreaId(adjustDetail.getWh_area_id());//����
			iStorage.setCargoSpaceId(adjustDetail.getCargo_space_id());//��λ
			/************���¿�λ************/
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
			if (adjustDetail.getRealitypnum()>adjustDetail.getSyspnum()) {//���ڿ�棬��������
				/**************��������ɵ���ҵ����ϸ*************************/
				InoutJob job = new InoutJob();
				job.setJobid(SeqTool.getID("TZRK", m_dao));
				job.setBoundstockid(adjustDetail.getAdjustid());//���ݺ�
				job.setBoundstockdetailid(adjustDetail.getAdjustdetailid());//������ϸ
				job.setWarehouseid(adjustDetail.getWarehouseid());//�ֿ�
				job.setTcargoWhareaId(adjustDetail.getWh_area_id());//����
				job.setTcargoSpaceId(adjustDetail.getCargo_space_id());//��λ
				job.setCreatetime(StrTools.getCurrDateTime(5));//����ʱ��
				job.setTraycode(adjustDetail.getStraycode());//��������
				job.setStatus("4");//״̬�����
				job.setSnumber(String.valueOf(adjustDetail.getRealitypnum()-adjustDetail.getSyspnum()));//��ҵ����
				job.setOnLineType("2");//������ʽ��1.������2.�ѻ�
				job.setInOutType("1");//��������ͣ�1
				job.setInvoicetype("2");//������⡣��������������ҵ��
				job.setIsaccount("Y");//�Ƿ���ˣ�����
				job.setJobtype("22");//�������
				
				InoutJobdetail jobdel = new InoutJobdetail();
				jobdel.setJobdetailid(SeqTool.getDetailId(job.getJobid(), "1"));//��ϸid
				jobdel.setJobid(job.getJobid());//��ҵid
				jobdel.setPackid(adjustDetail.getSproductid());//���ò�Ʒ
				jobdel.setPrintdate(adjustDetail.getSprintdate());
				jobdel.setLotid(adjustDetail.getSlotid());//��������
				jobdel.setLotinfo(adjustDetail.getSlotinfo());	//������Ϣ
				
				InventoryStorage inventory = getInventoryInfoToId(adjustDetail.getInventoryid());	
				inventory.setStatus("0");//����Ϊδ����
				inventory.setPnum(adjustDetail.getRealitypnum());//���ø�������
				
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
				/**************��������ɵ���ҵ����ϸ*************************/
				InoutJob job = new InoutJob();
				job.setJobid(SeqTool.getID("TZCK", m_dao));
				job.setBoundstockid(adjustDetail.getAdjustid());//���ݺ�
				job.setBoundstockdetailid(adjustDetail.getAdjustdetailid());//������ϸ
				job.setWarehouseid(adjustDetail.getWarehouseid());//�ֿ�
				job.setTcargoWhareaId(adjustDetail.getWh_area_id());//����
				job.setTcargoSpaceId(adjustDetail.getCargo_space_id());//��λ
				job.setCreatetime(StrTools.getCurrDateTime(5));//����ʱ��
				job.setTraycode(adjustDetail.getStraycode());//��������
				job.setStatus("4");//״̬�����
				job.setSnumber(String.valueOf(adjustDetail.getSyspnum()-adjustDetail.getRealitypnum()));//��ҵ����
				job.setOnLineType("2");//������ʽ��1.������2.�ѻ�
				job.setInOutType("2");//��������ͣ�1
				job.setInvoicetype("2");//������⡣��������������ҵ��
				job.setIsaccount("Y");//�Ƿ���ˣ�����
				job.setJobtype("21");//��������
				
				InoutJobdetail jobdel = new InoutJobdetail();
				jobdel.setJobdetailid(SeqTool.getDetailId(job.getJobid(), "1"));//��ϸid
				jobdel.setJobid(job.getJobid());//��ҵid
				jobdel.setPackid(adjustDetail.getSproductid());//���ò�Ʒ
				jobdel.setPrintdate(adjustDetail.getSprintdate());
				jobdel.setLotid(adjustDetail.getSlotid());//��������
				jobdel.setLotinfo(adjustDetail.getSlotinfo());	//������Ϣ
				
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
