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
 * ����: ��λDAO��
 * @author fangjie 
 * 
 */
public class BaseCargoSpaceDaoImpl implements IBaseCargoSpaceDao {

	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    
	public BaseCargoSpaceDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * ����:���ݻ�λID��û�λ
	 * @param cargoSpaceId	��λID
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
	 * ����:���ӻ�λ
	 * @param cargoSpace	��λ
	 * @throws Exception
	 */
	public void addCargospace(BaseCargospace cargoSpace) throws Exception {
		
		m_dao.save(cargoSpace);
	}

	/**
	 * ����:�޸Ļ�λ
	 * @param cargoSpace	��λ
	 * @throws Exception
	 */
	public void updateCargospace(BaseCargospace cargoSpace) throws Exception {
		
		m_dao.update(cargoSpace);
	}
	
	/**
	 * ����:ɾ����λ
	 * @param id	��λID
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
	 * ����:ɾ����������л�λ
	 * @param id	���ID
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
	 * ����:����������ѯ��λ
	 * @param warehouseid		�ֿ�ID
	 * @param cargoAreaId		����ID
	 * @param cargoAlleyId		���ID
	 * @param whAreaId			����ID
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
			throw new Exception("����������ѯ�������:" + er.getMessage());
		}
		return ls;
	}
	
    /**
     * ����:�޸Ļ�λ״̬
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
            throw new Exception("�޸Ļ�λ[" + strSpaceId + "]״̬[" + strStatus + "]����:" + er.getMessage());
        }
    }
    
	/**
	 * ����:���һ����λ�Ŀ��
	 * @param strCargoSpaceId ��λID
	 * @throws Exception
	 */
	public void clearOneCS(String cargoSpaceId) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            if(cargoSpaceId != null && !cargoSpaceId.equals("")){
            	
            	//��տ��
    			String strSql1 = "delete InventoryStorage as s where s.cargoSpaceId='" + cargoSpaceId + "'";
    			//���Ļ�λ״̬Ϊ�ջ�λ
    			String strSql2 = "update BaseCargospace as cs set cs.csstatus='1' where cs.cargoSpaceId='" + cargoSpaceId + "'";
    			
    			session.createQuery(strSql1).executeUpdate();
    			session.createQuery(strSql2).executeUpdate();
    			tx.commit();  
    		}
            
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("���һ����λ�Ŀ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }  
	}

	/**
	 * ����:������л�λ�Ŀ��
	 * @throws Exception
	 */
	public void clearAllCS() throws Exception {

		Session session = null;
        Transaction tx = null;
        
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
        	//������л�λ�Ŀ��
			String strSql1 = "delete InventoryStorage as s";
			//�������л�λ״̬Ϊ�ջ�λ
			String strSql2 = "update BaseCargospace as cs set cs.csstatus='1'";
			
			session.createQuery(strSql1).executeUpdate();
			session.createQuery(strSql2).executeUpdate();
			tx.commit();  
            
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("������л�λ�Ŀ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }  
	}
	
	/**
	 * ����:�޸Ļ�λ���������
	 * @param strIds		��λID
	 * @param strFlag		1:������;2:�������;3:������;4:�������
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
    			//1:������;2:�������;3:������;4:�������
    			Date date = new Date();
    			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			String time = format.format(date);
    			
    			if(flag.trim().equals("1")){
    				Logger.info("��ⶳ������ɹ�    "+time);
    				
    			}
    			if(flag.trim().equals("2")){
    				Logger.info("���ⶳ������ɹ�    "+time);
    			}
    			if(flag.trim().equals("3")){
    				Logger.info("����ͷŲ����ɹ�    "+time);
    			}
    			if(flag.trim().equals("4")){
    				Logger.info("�����ͷŲ����ɹ�   "+time);
    			}
    			
    		}
                
        }catch(Exception er)
        {
            throw new Exception("�޸Ļ�λ���������[" + strIds + "]����:" + er.getMessage());
        }
	}

	/**
	 * ����:��ջ�λ,�����λ
	 * @param strIds		��λID
	 * @param strFlag		1:��ջ�λ;2:�����λ
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
            
            if(flag.equals("1")){	//��ջ�λ
            	
            	//��տ��
    			strSql1 = "delete InventoryStorage as s where s.cargoSpaceId in(" + ids + ")";
    			//���Ļ�λ״̬Ϊ�ջ�λ1
    			strSql2 = "update BaseCargospace as cs set cs.csstatus='1' where cs.cargoSpaceId in(" + ids + ")";
    			
    		}else if(flag.equals("2")){//�����λ
    			//�޸Ŀ��״̬Ϊδ����0
    			strSql1 = "update InventoryStorage as s set s.status='0' where s.cargoSpaceId in(" + ids + ")";
    			//�޸Ļ�λΪ����λ2
    			strSql2 = "update BaseCargospace as cs set cs.csstatus='2' where cs.cargoSpaceId in(" + ids + ")";
    		}
            
			session.createQuery(strSql1).executeUpdate();
			session.createQuery(strSql2).executeUpdate();
			tx.commit(); 
            
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("��ջ�λ���߱����λ����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }  
		
	}
	/**
	 * �����ȷ���
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
	 * �����ȷ���
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
	 * ����:���ݲֿ� ��λ�� ��ѯ����λ
	 * @param iFloor	��
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
			throw new Exception("����������ѯ����:" + er.getMessage());
		}
	}
	/**
	 * ����:���ݲֿ� ��λ�� ��ѯ��
	 * @param iFloor	��
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
			throw new Exception("����������ѯ����:" + er.getMessage());
		}
	}
	/**
	 * ����:���ݲֿ� ��λ�� ��ѯ��
	 * @param iFloor	��
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
			throw new Exception("����������ѯ����:" + er.getMessage());
		}
	}

	/**
	 * ����:���ָ����λ���ڵĻ�λ
	 * @param cargoSpaceId	��λID
	 * @param Up down left right 
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getNearCargospace(String cargoSpaceId, String position) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
     * ����:���ݲֿ�id�Ϳ�λid���Ψһ�Ŀ���id
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
			throw new Exception("���ݲֿ�id�Ϳ�λid���Ψһ�Ŀ���id����:" + er.getMessage());
		}
		return whareaid;
    }
    
    /**
     * ����:���ݲֿ�id�Ϳ�λid���Ψһ�����id
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
			throw new Exception("���ݲֿ�id�Ϳ�λid���Ψһ�����id����:" + er.getMessage());
		}
		return alleyid;
    }
    
    /**
     * ���ܣ����ݻ�λ��ȡ��λ��Ϣ
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
            Logger.error("���ݻ�λ��ȡ��λ��Ϣʧ��:" + er.getMessage());
        }
        return space;
    }
    
    /**
     * ���ܣ����ݻ�λ��ȡ��λ��Ϣ
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
            Logger.error("���ݻ�λ��ȡ��λ��Ϣʧ��:" + er.getMessage());
        }
        return space;
    }
    
	/**
	 * ���ܣ���ȡһ���ջ�λ
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
            Logger.error("���ݻ�λ��ȡ��λ��Ϣʧ��:" + er.getMessage());
        }
        return null;
    }
    
	/**
	 * ����:����whAreaId��û�λ
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
