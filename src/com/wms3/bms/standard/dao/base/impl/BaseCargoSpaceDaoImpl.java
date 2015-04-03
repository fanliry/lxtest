package com.wms3.bms.standard.dao.base.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.service.BMSService;

/**
 * 描述: 货位DAO类
 * @author fangjie 
 * 
 */
public class BaseCargoSpaceDaoImpl implements IBaseCargoSpaceDao {

	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;
    
	public BaseCargoSpaceDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * 功能:根据货位ID获得货位
	 * @param cargoSpaceId	货位ID
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getCargoSpaceById(String cargoSpaceId) throws Exception {
		
		if(cargoSpaceId != null)
		{
			String strSql = " from BaseCargospace as t where t.cargoSpaceId='" + cargoSpaceId + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseCargospace)ls.get(0);
			}
		}
		return null;
	}

	/**
	 * 功能:增加货位
	 * @param cargoSpace	货位
	 * @throws Exception
	 */
	public void addCargospace(BaseCargospace cargoSpace) throws Exception {
		
		m_dao.save(cargoSpace);
	}

	/**
	 * 功能:修改货位
	 * @param cargoSpace	货位
	 * @throws Exception
	 */
	public void updateCargospace(BaseCargospace cargoSpace) throws Exception {
		
		m_dao.update(cargoSpace);
	}
	
	/**
	 * 功能:删除货位
	 * @param id	货位ID
	 * @throws Exception
	 */
	public void deletCargospaceById(String id) throws Exception {
		
		if(id != null && !id.equals(""))
        {
            String strSql = " delete BaseCargospace as t where t.cargoSpaceId='" + id + "'";
            m_dao.deleteSql(strSql);
        }
	}
	
	/**
	 * 功能:删除巷道下所有货位
	 * @param id	巷道ID
	 * @throws Exception
	 */
	public void deletCargospaceByAlleyId(String id) throws Exception {
		
		if(id != null && !id.equals(""))
        {
            String strSql = " delete BaseCargospace as t where t.cargoAlleyId='" + id + "'";
            m_dao.deleteSql(strSql);
        }
	}
	
	/**
	 * 功能:根据条件查询货位
	 * @param warehouseid		仓库ID
	 * @param cargoAreaId		货区ID
	 * @param cargoAlleyId		巷道ID
	 * @param whAreaId			库区ID
	 * @return
	 * @throws Exception
	 */
	public List getCargoSpaceQuery(String warehouseid, String cargoAreaId, String cargoAlleyId, String whAreaId) throws Exception {
		
		List ls = null;
		try
		{
			String strHql = "From BaseCargospace as t where 1=1" ;
			
			if(warehouseid != null && warehouseid.trim().length() > 0){
				strHql += " and t.warehouseid = '" + warehouseid + "'";
			}
			if(cargoAreaId != null && cargoAreaId.trim().length() > 0){
				strHql += " and t.cargoAreaId = '" + cargoAreaId + "'";
			}
			if(cargoAlleyId != null && cargoAlleyId.trim().length() > 0){
				strHql += " and t.cargoAlleyId  = '" + cargoAlleyId + "'";
			}
			if(whAreaId != null && whAreaId.trim().length() > 0){
				strHql += " and t.whAreaId  = '" + whAreaId + "'";
			}
			
			strHql = strHql + " order by t.cargoSpaceId";
			ls = m_dao.searchEntities(strHql);		
				
		}catch(Exception er)
		{
			throw new Exception("根据条件查询巷道出错:" + er.getMessage());
		}
		return ls;
	}
	
    /**
     * 功能:修改货位状态
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao#updateCargospaceStatus(java.lang.String, java.lang.String)
     */
    public void updateCargospaceStatus(String strSpaceId, String strStatus) throws Exception {
        try
        {
            String strHql = "update BaseCargospace set csstatus='" + strStatus + "'  where cargoSpaceId='" + strSpaceId + "'" ;
            m_dao.updateSql(strHql);      
                
        }catch(Exception er)
        {
            throw new Exception("修改货位[" + strSpaceId + "]状态[" + strStatus + "]出错:" + er.getMessage());
        }
    }
    
	/**
	 * 功能:清空一个货位的库存
	 * @param strCargoSpaceId 货位ID
	 * @throws Exception
	 */
	public void clearOneCS(String cargoSpaceId) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            if(cargoSpaceId != null && !cargoSpaceId.equals("")){
            	
            	//清空库存
    			String strSql1 = "delete InventoryStorage as s where s.cargoSpaceId='" + cargoSpaceId + "'";
    			//更改货位状态为空货位
    			String strSql2 = "update BaseCargospace as cs set cs.csstatus='1' where cs.cargoSpaceId='" + cargoSpaceId + "'";
    			
    			session.createQuery(strSql1).executeUpdate();
    			session.createQuery(strSql2).executeUpdate();
    			tx.commit();  
    		}
            
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("清空一个货位的库存出错！"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }  
	}

	/**
	 * 功能:清空所有货位的库存
	 * @throws Exception
	 */
	public void clearAllCS() throws Exception {

		Session session = null;
        Transaction tx = null;
        
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
        	//清空所有货位的库存
			String strSql1 = "delete InventoryStorage as s";
			//更改所有货位状态为空货位
			String strSql2 = "update BaseCargospace as cs set cs.csstatus='1'";
			
			session.createQuery(strSql1).executeUpdate();
			session.createQuery(strSql2).executeUpdate();
			tx.commit();  
            
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("清空所有货位的库存出错！"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }  
	}
	
	/**
	 * 功能:修改货位的入出库锁
	 * @param strIds		货位ID
	 * @param strFlag		1:入库加锁;2:出库加锁;3:入库解锁;4:出库解锁
	 * @param strFlagValue
	 * @throws Exception 
	 */
	public void lockCS(String strIds, String flag, String strFlag) throws Exception {
		
		try{
			
            if(strIds != null && flag != null && strFlag != null){
            	
    			String strSql="update BaseCargospace as cs set ";
    			if(flag.trim().equals("1") || flag.trim().equals("3")){
    				strSql = strSql + " cs.inlock='" + strFlag + "'";
    			}else{
    				strSql = strSql + " cs.outlock='" + strFlag + "'";
    			}
    			strSql = strSql + " where cs.cargoSpaceId in(" + strIds + ")";
    			m_dao.updateSql(strSql);
    			//1:入库加锁;2:出库加锁;3:入库解锁;4:出库解锁
    			Date date = new Date();
    			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			String time = format.format(date);
    			
    			if(flag.trim().equals("1")){
    				Logger.info("入库冻结操作成功    "+time);
    				
    			}
    			if(flag.trim().equals("2")){
    				Logger.info("出库冻结操作成功    "+time);
    			}
    			if(flag.trim().equals("3")){
    				Logger.info("入库释放操作成功    "+time);
    			}
    			if(flag.trim().equals("4")){
    				Logger.info("出库释放操作成功   "+time);
    			}
    			
    		}
                
        }catch(Exception er)
        {
            throw new Exception("修改货位的入出库锁[" + strIds + "]出错:" + er.getMessage());
        }
	}

	/**
	 * 功能:清空货位,保存货位
	 * @param strIds		货位ID
	 * @param strFlag		1:清空货位;2:保存货位
	 * @throws Exception
	 */
	public void saveCS(String ids, String flag) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            String strSql1 = "";
            String strSql2 = "";
            
            if(flag.equals("1")){	//清空货位
            	
            	//清空库存
    			strSql1 = "delete InventoryStorage as s where s.cargoSpaceId in(" + ids + ")";
    			//更改货位状态为空货位1
    			strSql2 = "update BaseCargospace as cs set cs.csstatus='1' where cs.cargoSpaceId in(" + ids + ")";
    			
    		}else if(flag.equals("2")){//保存货位
    			//修改库存状态为未分配0
    			strSql1 = "update InventoryStorage as s set s.status='0' where s.cargoSpaceId in(" + ids + ")";
    			//修改货位为满货位2
    			strSql2 = "update BaseCargospace as cs set cs.csstatus='2' where cs.cargoSpaceId in(" + ids + ")";
    		}
            
			session.createQuery(strSql1).executeUpdate();
			session.createQuery(strSql2).executeUpdate();
			tx.commit(); 
            
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("清空货位或者保存货位出错！"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }  
		
	}
	/**
	 * 入库均匀分析
	 * @param warehouseid
	 * @return
	 */
	public List getListSum(String warehouseid){
		String hql = "select whAreaName,cargoAlleyName, count(*), sum(case when csstatus='2' then 1 else 0 end)," +
		             " sum(case when csstatus='1' then 1 else 0 end)," +
					 " sum(case when csstatus!='1' and csstatus!='2' then 1 else 0 end)" +
					 " from BaseCargospace where 1=1";
		if(warehouseid != null && warehouseid.length() > 1){
			hql += " and warehouseid='" + warehouseid + "'";
		}
		hql += " group by whAreaId, cargoAlleyId order by whAreaId,cargoAlleyId";
		List ls = m_dao.searchEntities(hql);
		return ls;
	}
	/**
	 * 入库均匀分析
	 * @param warehouseid
	 * @return
	 */
	public List getListSum(String warehouseid,String whAreaId){
		String hql = "select whAreaName,cargoAlleyName, count(*), sum(case when csstatus='2' then 1 else 0 end)," +
		             " sum(case when csstatus='1' then 1 else 0 end)," +
					 " sum(case when csstatus!='1' and csstatus!='2' then 1 else 0 end)" +
					 " from BaseCargospace where 1=1";
		if(warehouseid != null && warehouseid.length() > 1){
			hql += " and warehouseid='" + warehouseid + "'";
		}
		if(whAreaId != null && whAreaId.length() > 1){
			hql += " and whAreaId='" + whAreaId + "'";
		}
		hql += " group by whAreaId, cargoAlleyId order by whAreaId,cargoAlleyId";
		List ls = m_dao.searchEntities(hql);
		return ls;
	}
	/**
	 * 功能:根据仓库 货位层 查询库存货位
	 * @param iFloor	层
	 * @param strWarehouseId
	 * @return
	 * @throws Exception 
	 */
	public  String getSpaceStatus(String iFloor,String strWarehouseId) throws Exception
	{
		try
		{
			String strSql = "select csplatoon ,cscolumn ,csstatus from BaseCargospace where 1=1 ";
			if(iFloor != null && iFloor.trim().length() > 0)
			{
				int floor = Integer.parseInt(iFloor.trim());
				strSql = strSql + " and csfloor ="+floor+"";
			}
			if(strWarehouseId != null && strWarehouseId.trim().length() > 0)
			{
				strSql = strSql + " and warehouseid ='"+strWarehouseId+"'";
			}
			strSql =strSql+" order by csplatoon";
			return strSql;
			
		}catch(Exception er)
		{
			throw new Exception("根据条件查询出错:" + er.getMessage());
		}
	}
	/**
	 * 功能:根据仓库 货位层 查询列
	 * @param iFloor	层
	 * @param strWarehouseId
	 * @return
	 * @throws Exception 
	
	 */
	public  String getiColumn(String iFloor,String strWarehouseId) throws Exception
	{
		try
		{
			String strSql = "select max(cs.cscolumn) from BaseCargospace as cs where 1=1";
			
			if(iFloor != null && iFloor.trim().length() > 0)
			{
				int floor = Integer.parseInt(iFloor.trim());
				strSql = strSql + " and cs.csfloor ="+floor;
			}
			if(strWarehouseId != null && strWarehouseId.trim().length() > 0)
			{
				strSql = strSql + " and cs.warehouseid ='"+strWarehouseId+"'";
			}
			return strSql;
			
		}catch(Exception er)
		{
			throw new Exception("根据条件查询出错:" + er.getMessage());
		}
	}
	/**
	 * 功能:根据仓库 货位层 查询排
	 * @param iFloor	层
	 * @param strWarehouseId
	 * @return
	 * @throws Exception 
	
	 */
	public String getiPlatoon(String iFloor,String strWarehouseId) throws Exception
	{
		try
		{
			String strSql = "select max(cs.csplatoon)  from BaseCargospace as cs where 1=1";
			
			if(iFloor != null && iFloor.trim().length() > 0)
			{
				int floor = Integer.parseInt(iFloor.trim());
				strSql = strSql + " and cs.csfloor ="+floor;
			}
			if(strWarehouseId != null && strWarehouseId.trim().length() > 0)
			{
				strSql = strSql + " and cs.warehouseid ='"+strWarehouseId+"'";
			}
			return strSql;
			
		}catch(Exception er)
		{
			throw new Exception("根据条件查询出错:" + er.getMessage());
		}
	}

	/**
	 * 功能:获得指定货位相邻的货位
	 * @param cargoSpaceId	货位ID
	 * @param Up down left right 
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getNearCargospace(String cargoSpaceId, String position) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
     * 功能:根据仓库id和库位id获得唯一的库区id
     * @param warehouseid
     * @return
     * @throws Exception
     */
    public String getWhAreaIdByWarehouseidAndCargospaceId(String warehouseid,String cargospaceId)throws Exception{
    	String whareaid = null;
    	try
		{
    		
    		if(warehouseid!=null&&cargospaceId!=null){
    			String strSql = "select cs.whAreaId  from BaseCargospace as cs where cs.cargoSpaceId='"+cargospaceId+"' and cs.warehouseid='"+warehouseid+"'";
    			List ls = m_dao.searchEntities(strSql);
    			if(ls!=null&&ls.size()>0){
    				whareaid = (String)ls.get(0);
    			}
    		}
		}catch(Exception er)
		{
			throw new Exception("根据仓库id和库位id获得唯一的库区id出错:" + er.getMessage());
		}
		return whareaid;
    }
    
    /**
     * 功能:根据仓库id和库位id获得唯一的巷道id
     * @param warehouseid
     * @return
     * @throws Exception
     */
    public String getAlleyIdByWarehouseidAndCargospaceId(String warehouseid,String cargospaceId)throws Exception{
    	String alleyid = null;
    	try
		{
    		
    		if(warehouseid!=null&&cargospaceId!=null){
    			String strSql = "select cs.cargoAlleyId  from BaseCargospace as cs where cs.cargoSpaceId='"+cargospaceId+"' and cs.warehouseid='"+warehouseid+"'";
    			List ls = m_dao.searchEntities(strSql);
    			if(ls!=null&&ls.size()>0){
    				alleyid = (String)ls.get(0);
    			}
    		}
		}catch(Exception er)
		{
			throw new Exception("根据仓库id和库位id获得唯一的巷道id出错:" + er.getMessage());
		}
		return alleyid;
    }
    
    /**
     * 功能：根据货位获取货位信息
     * @return
     * @throws Exception 
     */
    public BaseCargospace GetCargospace(String platoon, String column,String floor,String warehouseid)
    {
    	BaseCargospace space = null;
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
            String strSql0 = " from BaseCargospace bc where bc.csplatoon='"+platoon+"' and bc.cscolumn='"+column+"' and bc.csfloor='"+floor+"'"+" and bc.warehouseid='"+warehouseid+"'";
            List lsList = dao.searchEntities(strSql0);
            if(lsList!=null && lsList.size()>0){
            	if(lsList.get(0)!=null){
            		space=(BaseCargospace)lsList.get(0);
            		return space;
            	}
            	
            }
            
        }catch(Exception er)
        {
            Logger.error("根据货位获取货位信息失败:" + er.getMessage());
        }
        return space;
    }
    
    /**
     * 功能：根据货位获取货位信息
     * @return
     * @throws Exception 
     */
    public BaseCargospace GetWhCargospace(String platoon, String column,String floor,String warehouseid,String whAreaId)
    {
    	BaseCargospace space = null;
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
            String strSql0 = " from BaseCargospace bc where bc.csplatoon='"+platoon+"' and bc.cscolumn='"+column+"' and bc.csfloor='"+floor+"'"+" and bc.whAreaId='"+whAreaId+"' and bc.warehouseid='"+warehouseid+"'";
            List lsList = dao.searchEntities(strSql0);
            if(lsList!=null && lsList.size()>0){
            	if(lsList.get(0)!=null){
            		space=(BaseCargospace)lsList.get(0);
            		return space;
            	}
            	
            }
            
        }catch(Exception er)
        {
            Logger.error("根据货位获取货位信息失败:" + er.getMessage());
        }
        return space;
    }
    
	/**
	 * 功能：获取一个空货位
	 * @param warehouseid
	 * @param WhAreaId
	 * @return
	 */
    public BaseCargospace getNullSpaceId(String warehouseid, String WhAreaId)
    {
    	BaseCargospace space = null;
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
            String strSql0 = " from BaseCargospace bc where bc.csstatus='1' and bc.whAreaId='"+WhAreaId+"'"+" and bc.warehouseid='"+warehouseid+"'";
            List lsList = dao.searchEntities(strSql0);
            if(lsList!=null && lsList.size()>0){
            	if(lsList.get(0)!=null){
            		space=(BaseCargospace)lsList.get(0);
            		return space;
            	}
            	
            }
            
        }catch(Exception er)
        {
            Logger.error("根据货位获取货位信息失败:" + er.getMessage());
        }
        return null;
    }
    
	/**
	 * 功能:根据whAreaId获得货位
	 * @param whAreaId
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getCargoSpaceBywhAreaId(String whAreaId) throws Exception {
		
		if(whAreaId != null)
		{
			String strSql = " from BaseCargospace as t where t.whAreaId='" + whAreaId + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseCargospace)ls.get(0);
			}
		}
		return null;
	}
	
}
