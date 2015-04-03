package com.wms3.bms.standard.business.quality.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.entity.inventory.InventoryAdjustDetail;
import com.wms3.bms.standard.entity.inventory.InventoryQualityResult;
import com.wms3.bms.standard.entity.inventory.InventoryQualityResultDetail;
import com.wms3.bms.standard.business.quality.InventoryQualityBus;
import com.wms3.bms.standard.entity.inventory.InventoryAdjustHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;


/**
 * ����:����������
 * @author yao
 *
 */
public class InventoryQualityBusImpl implements InventoryQualityBus
{
	protected EntityDAO m_dao = null;
	
	public InventoryQualityBusImpl(EntityDAO dao)
	{
		m_dao = dao;
	}
    
	public String getQualityHeaderSQL(String strLotNumber, String strFormDate, String strToDate)throws Exception
	{
		StringBuilder strBud = new StringBuilder();
		strBud.append("from InventoryQualityResult as ah where 1=1");
		if(strLotNumber != null && strLotNumber.trim().length() > 0)
		{
			strBud.append(" and ah.m_strLotNumber = '").append(strLotNumber).append("'");
		}
		if(strFormDate != null && strFormDate.trim().length() > 0)
		{
			strBud.append(" and ah.m_strCreateDate >= '").append(strFormDate).append("'");
		}
		if(strToDate != null && strToDate.trim().length() > 0)
		{
			strBud.append(" and ah.m_strCreateDate <= '").append(strToDate).append(" 24:60:59'");
		}
		return strBud.toString();
	}
   
	public String getQualityHeaderCountSQL(String strLotNumber, String strFormDate, String strToDate)throws Exception
	{
		StringBuilder strBud = new StringBuilder();
		strBud.append("select count(ah) from InventoryQualityResult as ah where 1=1");
		if(strLotNumber != null && strLotNumber.trim().length() > 0)
		{
			strBud.append(" and ah.m_strLotNumber = '").append(strLotNumber).append("'");
		}
		if(strFormDate != null && strFormDate.trim().length() > 0)
		{
			strBud.append(" and ah.m_strCreateDate >= '").append(strFormDate).append("'");
		}
		if(strToDate != null && strToDate.trim().length() > 0)
		{
			strBud.append(" and ah.m_strCreateDate <= '").append(strToDate).append(" 24:60:59'");
		}
		return strBud.toString();
	}
	/**
	 * ����:���״̬ת������ϸ��ѯ��SQL���
	 * @param strId	״̬ת����ID
	 * @return
	 */
	public static String getQualityDetailSQL(String strId)
	{
		String strSql = "from InventoryQualityResultDetail as ad where 1=1";
		if(strId != null && strId.trim().length() > 0)
		{
			strSql = strSql + " and ad.m_strCheckResultId='" + strId + "'";
		}
		return strSql;
	}
	/**
	 * ����:���״̬ת������ϸ��ѯ�ܼ�¼��SQL���
	 * @param strId	״̬ת����ID
	 * @return
	 */
	public static String getQualityDetailCountSQL(String strId)
	{
		String strSql = "select count(ad) from InventoryQualityResultDetail as ad where 1=1";
		if(strId != null && strId.trim().length() > 0)
		{
			strSql = strSql + " and ad.m_strCheckResultId='" + strId + "'";
		}
		return strSql;
	}
	/**
	 * ����:ɾ��״̬ת����
	 * @param id
	 */
	public void deleteQualityHeader(String id)
	{
		Session session = m_dao.getSession();
    	try
		{
    		
    		String strSql0 = "delete from InventoryQualityResult as ah where ah.m_strCheckResultId='"+id+"'";
    		Transaction tx= session.beginTransaction();
    		session.createQuery(strSql0).executeUpdate();
			tx.commit();
        }
    	catch(HibernateException he)
		{
    		Logger.error("ɾ��״̬ת��������"+he.getMessage());
		}
    	finally
		{
    		m_dao.closeSession(session);
    	}
	}
	/**
	 * ����:����״̬ת����ID��ÿ�������
	 * @param strId
	 * @return
	 * @throws Exception
	 */
	public InventoryQualityResult getQualityHeaderToId(String strId) throws Exception
	{
		InventoryQualityResult adjust = null;
		try
		{
			if(strId != null)
			{
				String strSql = "from InventoryQualityResult as ah where ah.m_strCheckResultId='" + strId + "'";
				List ls = m_dao.searchEntities(strSql);
				if(ls != null && ls.size() > 0)
				{
					adjust = (InventoryQualityResult)ls.get(0);
				}
			}
		}catch(Exception er)
		{
			throw new Exception("���ݵ�����ID��ÿ�����������(InventoryQualityBusImpl.getQualityHeaderToId):" + er.getMessage());
		}
		return adjust;
	}
	/**
	 * ����:����״̬ת����
	 * @param Quality
	 */
	public void updateAdjustHeader(InventoryQualityResult adjust)
	{
		m_dao.update(adjust);
	}
	/**
	 * ����:ɾ��״̬ת������ϸ
	 * @param id	״̬ת������ϸID
	 * @throws Exception
	 */
	public void deleteQualityDetail(String id) throws Exception
	{
		try
		{
			if(id != null)
			{
				String strSql = "delete from InventoryQualityResultDetail as ad where ad.m_strCheckResultDetailId='"+id+"'";
				m_dao.deleteSql(strSql);
			}		
		}catch(Exception er)
		{
			throw new Exception("ɾ��״̬ת������ϸ����(InventoryQualityBusImpl.deleteQualityDetail):" + er.getMessage());
		}	
	}
	/**
	 * ����:����״̬ת������ϸID���״̬ת������ϸ
	 * @param strId	״̬ת������ϸID
	 * @return
	 * @throws Exception
	 */
	public InventoryQualityResultDetail getAdjustDetailToId(String strId) throws Exception
	{
		InventoryQualityResultDetail detail = null;
		try
		{
			if(strId != null)
			{
				String strSql = "from InventoryQualityResultDetail as ad where ad.m_strCheckResultDetailId='" + strId + "'";
				List ls = m_dao.searchEntities(strSql);
				if(ls != null && ls.size() > 0)
				{
					detail = (InventoryQualityResultDetail)ls.get(0);
				}
			}
		}catch(Exception er)
		{
			throw new Exception("����״̬ת������ϸID���״̬ת������ϸ����(InventoryQualityBusImpl.getQualityDetailToId):" + er.getMessage());
		}
		return detail;
	}
	/**
	 * ����:����״̬ת����ID���״̬ת��������ϸ
	 * @param strId	״̬ת����ID
	 * @return
	 * @throws Exception
	 */
	public List getAdjustDetailListToId(String strId) throws Exception
	{
		List ls = null;
		try
		{
			if(strId != null)
			{
				String strSql = "from InventoryQualityResultDetail as ad where ad.m_strCheckResultId='" + strId + "'";
				ls = m_dao.searchEntities(strSql);
			}
		}catch(Exception er)
		{
			throw new Exception("����״̬ת����ID��ÿ��״̬ת������ϸ����(InventoryQualityBusImpl.getQualityDetailListToId):" + er.getMessage());
		}
		return ls;
	}
	
	public InventoryQualityResult getAdjustListToId(String strId) throws Exception{
		InventoryQualityResult result = null;
		try
		{
			if(strId != null)
			{
				String strSql = "from InventoryQualityResult as ar where ar.m_strCheckResultId='"+ strId + "'";
				List ls = m_dao.searchEntities(strSql);
				if(ls!=null){
					for(int i=0;i<ls.size();i++){
						result = (InventoryQualityResult)ls.get(0);
					}
				}
			}
		}catch(Exception er)
		{
			throw new Exception("����״̬ת����ID��ÿ��״̬ת��������(InventoryQualityBusImpl.getAdjustListToId):" + er.getMessage());
		}
		return result;
	}
	
    
	public void createqualityInvoice(List<InventoryQualityResultDetail> lsajustDetail, List<InventoryStorage> lsinventory, InventoryQualityResult adjust) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //�Կ��״̬ת��������ȷ��
            session.save(adjust);	       
            //�Կ��״̬ת������ϸ����ȷ��
            if(lsajustDetail != null){
            	InventoryQualityResultDetail Detail = null;	
                for(int i = 0; i < lsajustDetail.size(); i++){
                	Detail = lsajustDetail.get(i);
                    session.save(Detail);
                }
            	//session.saveOrUpdate(lsajustDetail);
            } 
            
            //�Կ����е���
            if(lsinventory != null){
           	InventoryStorage Inventory = null;	
                for(int i = 0; i < lsinventory.size(); i++){
                	Inventory = lsinventory.get(i);
                    session.update(Inventory);
                }
            	//session.saveOrUpdate(lsinventory);
            } 
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("���״̬ת��������ת��ȷ��ʱ����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
		
	}
	/**״̬ת����ɾ��
	 * @param lsajustDetail
	 * @param lsinventory
	 * @param adjust
	 * @throws Exception
	 */
		public void deletejustInvoice(List<InventoryAdjustDetail> lsajustDetail, List<InventoryStorage> lsinventory, InventoryAdjustHeader adjust) throws Exception {
			
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
	            throw new  Exception("״̬ת��������ת��ʱ����"+er.getMessage());
	        }finally{
	            m_dao.closeSession(session);
	        }     
			
		}
		
}
