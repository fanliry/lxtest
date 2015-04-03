package com.wms3.bms.standard.business.quality;

import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * �������ʼ�����ҵ��ӿ���
 * @author liuxh
 * @since 2012-11-18
 *
 */
public interface IQualityMgrBus {
 /**
  * ���ܣ����¿���쵥״̬�Ϳ��
  * @param ids
  * @param userCode
  * @return
  * @throws Exception
  */
 public String updateCheckBoundUpdateInvent(String ids,String userCode)throws Exception;
 /**
  * ���ܣ����ݿ��ID��ÿ��
  * @param id ���ID
  * @return
  * @throws Exception
  */
 public InventoryStorage getInventById(String id)throws Exception;
 /**
  * ���ܣ����ϳ�쵥
  * @param ids
  * @param userCode
  * @return
  * @throws Exception
  */
public String deleteCheckBoundUpdateInvent(String ids, String strUserCode)throws Exception;
}
