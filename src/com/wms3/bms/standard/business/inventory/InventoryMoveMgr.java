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
 * ����ƶ�����
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
	 * ���ӿ�浥�������ϸ�����¿��
	 * @param mHeader �ƶ�����
	 * @param mDetail �ƶ�����ϸ
	 * @param iStorage ���
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
    		Logger.error("�����ƶ������޸Ŀ�����"+he.getMessage());
		}
    	finally
		{
    		m_dao.closeSession(session);
    	} 
	}
	/**
	 * �����ƶ������ƶ���ϸ��������棬����ԭ���
	 * @param mHeader �ƶ���
	 * @param mDetail �ƶ���ϸ
	 * @param newInvent �������
	 * @param oldInvent  ԭ���
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
			Logger.error("�����ƶ������޸Ŀ�����"+he.getMessage());
		}
		finally
		{
    		m_dao.closeSession(session);
    		Logger.error("�����ƶ������޸Ŀ��ɹ����ƶ����ݺţ�"+mHeader.getMovementid()+"�ƶ���ϸ���ţ�"+mDetail.getMovemendetailid());
    	} 
	}
}
