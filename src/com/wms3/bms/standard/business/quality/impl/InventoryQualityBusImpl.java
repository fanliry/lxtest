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
 * 描述:库存调整管理
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
	 * 功能:获得状态转换单详细查询的SQL语句
	 * @param strId	状态转换单ID
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
	 * 功能:获得状态转换单详细查询总记录的SQL语句
	 * @param strId	状态转换单ID
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
	 * 功能:删除状态转换单
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
    		Logger.error("删除状态转换单出错！"+he.getMessage());
		}
    	finally
		{
    		m_dao.closeSession(session);
    	}
	}
	/**
	 * 功能:根据状态转换单ID获得库存调整单
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
			throw new Exception("根据调整单ID获得库存调整单出错(InventoryQualityBusImpl.getQualityHeaderToId):" + er.getMessage());
		}
		return adjust;
	}
	/**
	 * 功能:更新状态转换单
	 * @param Quality
	 */
	public void updateAdjustHeader(InventoryQualityResult adjust)
	{
		m_dao.update(adjust);
	}
	/**
	 * 功能:删除状态转换单详细
	 * @param id	状态转换单详细ID
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
			throw new Exception("删除状态转换单详细出错(InventoryQualityBusImpl.deleteQualityDetail):" + er.getMessage());
		}	
	}
	/**
	 * 功能:根据状态转换单详细ID获得状态转换单详细
	 * @param strId	状态转换单详细ID
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
			throw new Exception("根据状态转换单详细ID获得状态转换单详细出错(InventoryQualityBusImpl.getQualityDetailToId):" + er.getMessage());
		}
		return detail;
	}
	/**
	 * 功能:根据状态转换单ID获得状态转换单单详细
	 * @param strId	状态转换单ID
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
			throw new Exception("根据状态转换单ID获得库存状态转换单详细出错(InventoryQualityBusImpl.getQualityDetailListToId):" + er.getMessage());
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
			throw new Exception("根据状态转换单ID获得库存状态转换单出错(InventoryQualityBusImpl.getAdjustListToId):" + er.getMessage());
		}
		return result;
	}
	
    
	public void createqualityInvoice(List<InventoryQualityResultDetail> lsajustDetail, List<InventoryStorage> lsinventory, InventoryQualityResult adjust) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            //对库存状态转换单进行确定
            session.save(adjust);	       
            //对库存状态转换单明细进行确定
            if(lsajustDetail != null){
            	InventoryQualityResultDetail Detail = null;	
                for(int i = 0; i < lsajustDetail.size(); i++){
                	Detail = lsajustDetail.get(i);
                    session.save(Detail);
                }
            	//session.saveOrUpdate(lsajustDetail);
            } 
            
            //对库存进行调整
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
            throw new  Exception("库存状态转换单进行转换确定时出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
		
	}
	/**状态转换单删除
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
	            throw new  Exception("状态转换单进行转换时出错："+er.getMessage());
	        }finally{
	            m_dao.closeSession(session);
	        }     
			
		}
		
}
