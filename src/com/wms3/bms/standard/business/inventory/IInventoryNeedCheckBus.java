package com.wms3.bms.standard.business.inventory;

import com.wms3.bms.standard.entity.inventory.InventoryNeedcheck;

/**
 * ���������̵�����ҵ��ӿ�
 * @author liuxh
 * @since 2012-11-23
 */
public interface IInventoryNeedCheckBus {
 /**
  * ���ܣ���ѯ���̵���Ϣ
  * @param needcheckid      ���̵�id
  * @param jobid            ��ҵ��
  * @param inouttype        �������
  * @param cargoSpaceId     ��ҵ��λ
  * @param createtimeform   ����ʱ��from
  * @param createtimeto     ����ʱ��to
  * @param handleflag       �����ʶ
  * @param traycode         ��������
  * @param taskno           �����
  * @return
  * @throws Exception
  */
 public String[] getInventNeedCheck(String needcheckid,String jobid,String inouttype,
			  String cargoSpaceId,String createtimeform,String createtimeto,String handleflag,String traycode,String taskno)throws Exception;
 /**
  * ���ܣ����¿�棬��λ״�壬���̵�״̬			                                                
  * @param ids
  * @param user
  * @param flag 1.����2.����
  * @return
  * @throws Exception
  */
 public String updateInventAndCargoSpace(String ids,String flag,String user)throws Exception;
 /**
  * ���ܣ�����ID��ȡ�쳣��
  * @param needCheckId
  * @return
  * @throws Exception
  */
 public InventoryNeedcheck getINeedcheckInfo(String needCheckId)
	throws Exception;
 
 /**
  * ���ܣ����ݿ�λID��ȡ�쳣��
  * @param needCheckId
  * @return
  * @throws Exception
  */
 public InventoryNeedcheck getINeedcheckInfoByCargospaceId(String cargospaceId)
	throws Exception;
 
}
