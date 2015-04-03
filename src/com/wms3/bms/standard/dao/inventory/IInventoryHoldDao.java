package com.wms3.bms.standard.dao.inventory;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryHold;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * 
 * ����: ��涳��/�ͷ�DAO��ӿ�
 * @author hug 2012-4-25
 * @since WMS 3.0
 */
public interface IInventoryHoldDao  extends IDao{

	/**
	 * ����:���ɶ����¼���޸Ŀ�涳������
	 * @param lshold		�����б�
	 * @param lsstorage		����б�
	 * @return 
	 * @throws Exception
	 */
	void addHold(List<InventoryHold> lshold, List<InventoryStorage> lsstorage) throws Exception;

	/**
	 * ����:����id��ö����¼
	 * @param id		����id
	 * @return 
	 * @throws Exception
	 */
	InventoryHold getHoldById(String id) throws Exception;

	/**
	 * ����:�ͷŶ����¼����տ�涳������
	 * @param lshold		�����б�
	 * @param lsstorage		����б�
	 * @return 
	 * @throws Exception
	 */
	void updateHold(List<InventoryHold> lshold, List<InventoryStorage> lsstorage) throws Exception;

	
  
}
