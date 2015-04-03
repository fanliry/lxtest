package com.wms3.bms.standard.dao.inventory.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.inventory.IInventoryHoldDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryHold;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * 
 * 描述: 库存冻结/释放DAO类
 * @author hug 2012-4-25
 * @since WMS 3.0
 */
public class InventoryHoldDaoImpl extends AbstractDao implements IInventoryHoldDao {

    public InventoryHoldDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }

    /**
	 * 功能:生成冻结记录，修改库存冻结数量
	 * @param lshold		冻结列表
	 * @param lsstorage		库存列表
	 * @return 
	 * @throws Exception
	 */
	public void addHold(List<InventoryHold> lshold, List<InventoryStorage> lsstorage) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
        	InventoryStorage storage = null;
        	InventoryHold hold = null;
            
            for(int i = 0; i < lshold.size(); i++){
            	
                storage = (InventoryStorage)lsstorage.get(i);
                hold = (InventoryHold)lshold.get(i);
                
                //增加冻结记录
                session.save(hold);

                //修改库存冻结数量
                session.update(storage);	
            }
            tx.commit();
            
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("生成冻结记录，修改库存冻结数量时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}

	/**
	 * 功能:根据id获得冻结记录
	 * @param id		冻结id
	 * @return 
	 * @throws Exception
	 */
	public InventoryHold getHoldById(String id) throws Exception {
		
		InventoryHold hold = null;
        try{
            String strSql = "from InventoryHold as hold where hold.inventoryholdid='" + id + "'";
            List ls = m_dao.searchEntities(strSql);
            if(ls != null && ls.size() > 0){
            	hold = (InventoryHold)ls.get(0);
            }
        }catch(Exception er){
            throw new Exception("根据id获得冻结记录出错：" + er.getMessage());
        }
        return hold;
	}

	/**
	 * 功能:释放冻结记录，清空库存冻结数量
	 * @param lshold		冻结列表
	 * @param lsstorage		库存列表
	 * @return 
	 * @throws Exception
	 */
	public void updateHold(List<InventoryHold> lshold, List<InventoryStorage> lsstorage) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
        	InventoryStorage storage = null;
        	InventoryHold hold = null;
            
            for(int i = 0; i < lshold.size(); i++){
            	
                storage = (InventoryStorage)lsstorage.get(i);
                hold = (InventoryHold)lshold.get(i);
                
                //释放冻结记录
                session.update(hold);

                //清空库存冻结数量
                session.update(storage);	
            }
            tx.commit();
            
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("释放冻结记录，清空库存冻结数量时候出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}

    
	
}
