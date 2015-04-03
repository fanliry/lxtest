package com.wms3.bms.standard.business.inventory;

import com.ricosoft.entity.competenceMgr.SystemLogInfo;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * ����:����������
 * @author yao
 *
 */
public interface InventoryAdjustBus 
{
	/**
	 * ����:��ÿ���������ѯ��SQL���
	 * @param status	״̬
	 * @param customer	�ͻ�
	 * @return
	 */
	public String getAdjustHeaderSQL(String warehouseid, String status,String adjusttype,String reasoncode) throws Exception;
	/**
	 * ����:��ÿ��������ܼ�¼��ѯ��SQL���
	 * @param sql	��ѯ���
	 * @return
	 */
	public String getAdjustHeaderCountSQL(String sql) throws Exception;
    /**
	 * ���ܣ���ӿ����Ϣ�����»�λ״̬�����ӵ�����ϸ,������־(����������ӿ����Ϣ)
	 * @param inven
	 * @param cs
	 * @param adjustDetail
	 * @throws Exception
	 */
	public void addinvenUpdatecarsoAddAdjustDel(InventoryStorage iStorage,BaseCargospace cs,InventoryAdjustDetail adjustDetail,InventoryCheckResult checkResult,SystemLogInfo sysLog) throws Exception;
    /**
     * ���ܣ���������ִ�е����������������ҵ����棬���»�λ״̬��
     * @param adjustDetail
     * @return
     * @throws Exception
     */
	public Object[] addinvenForAdjust(InventoryAdjustDetail adjustDetail) throws Exception;
	public Object[] updateinvenForAdjust(InventoryAdjustDetail adjustDetail) throws Exception;
}
