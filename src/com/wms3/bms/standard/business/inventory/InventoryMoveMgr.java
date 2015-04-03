package com.wms3.bms.standard.business.inventory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
/**
 * 库存移动管理
 * @author liuxh
 *
 */
public class InventoryMoveMgr {
    
	protected EntityDAO m_dao = null;
	public InventoryMoveMgr(EntityDAO dao)
	{
		m_dao = dao;
	}
	/**
	 * 增加库存单，库存明细，更新库存
	 * @param mHeader 移动单据
	 * @param mDetail 移动单明细
	 * @param iStorage 库存
	 */
	public void addMoveHeaderAddMoveDetailUpdateInvent(InventoryMovementHeader mHeader,InventoryMovementDetail mDetail,
			InventoryStorage iStorage) {
		Session session = m_dao.getSession();
		try
		{
    		Transaction tx= session.beginTransaction();
			session.save(mHeader);
			session.save(mDetail);
			if (iStorage.getPnum()==0) {
				session.delete(iStorage);
			} else {
				session.update(iStorage);
			}		
			tx.commit();
        }
    	catch(HibernateException he)
		{
    		Logger.error("增加移动单并修改库存出错！"+he.getMessage());
		}
    	finally
		{
    		m_dao.closeSession(session);
    	} 
	}
	/**
	 * 增加移动单，移动明细，新增库存，更新原库存
	 * @param mHeader 移动单
	 * @param mDetail 移动明细
	 * @param newInvent 新增库存
	 * @param oldInvent  原库存
	 */
	public void addMoveHeaderAddMoveDetailAddNewInventUpdateInvent(InventoryMovementHeader mHeader,InventoryMovementDetail mDetail,
			InventoryStorage newInvent,InventoryStorage oldInvent) {
		Session session = m_dao.getSession();
		try {
			Transaction tx = session.beginTransaction();
			session.save(mHeader);
			session.save(mDetail);
	        session.save(newInvent);
			if (oldInvent.getPnum()==0.0) {
				session.delete(oldInvent);
			} else {
               session.update(oldInvent);
			}
			tx.commit();	
		} catch (HibernateException he) {
			Logger.error("增加移动单并修改库存出错！"+he.getMessage());
		}
		finally
		{
    		m_dao.closeSession(session);
    		Logger.error("增加移动单并修改库存成功！移动单据号："+mHeader.getMovementid()+"移动明细单号："+mDetail.getMovemendetailid());
    	} 
	}
}
