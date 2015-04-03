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
 * ����: ��涳��/�ͷ�DAO��
 * @author hug 2012-4-25
 * @since WMS 3.0
 */
public class InventoryHoldDaoImpl extends AbstractDao implements IInventoryHoldDao {

    public InventoryHoldDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }

    /**
	 * ����:���ɶ����¼���޸Ŀ�涳������
	 * @param lshold		�����б�
	 * @param lsstorage		����б�
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
                
                //���Ӷ����¼
                session.save(hold);

                //�޸Ŀ�涳������
                session.update(storage);	
            }
            tx.commit();
            
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("���ɶ����¼���޸Ŀ�涳������ʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}

	/**
	 * ����:����id��ö����¼
	 * @param id		����id
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
            throw new Exception("����id��ö����¼����" + er.getMessage());
        }
        return hold;
	}

	/**
	 * ����:�ͷŶ����¼����տ�涳������
	 * @param lshold		�����б�
	 * @param lsstorage		����б�
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
                
                //�ͷŶ����¼
                session.update(hold);

                //��տ�涳������
                session.update(storage);	
            }
            tx.commit();
            
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�ͷŶ����¼����տ�涳������ʱ�����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
	}

    
	
}
