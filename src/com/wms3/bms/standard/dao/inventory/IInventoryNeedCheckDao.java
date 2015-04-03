package com.wms3.bms.standard.dao.inventory;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.InventoryNeedcheck;

public interface IInventoryNeedCheckDao extends IDao {
	/**
	 * ���ܣ�����id������̵��¼
	 * @param needCheckId ���̵�ID
	 * @return
	 * @throws Exception
	 */
public InventoryNeedcheck getINeedcheckInfo(String needCheckId)throws Exception;

/**
 * ���ܣ����ݿ�λID��ȡ�쳣��
 * @param needCheckId
 * @return
 * @throws Exception
 */
public InventoryNeedcheck getINeedcheckInfoByCargospaceId(String cargospaceId)
	throws Exception;
}
