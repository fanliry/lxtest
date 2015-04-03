package com.wms3.bms.standard.dao.inventory;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckRequest;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
/**
 * 
 * ����: ���DAO��ӿ�
 * @author hug 2012-4-25
 * @since WMS 3.0
 */
public interface IInventoryCheckDao  extends IDao{

	/**
	 * ����:����̵����뵥
	 * @param checkReq	�̵����뵥
	 * @return 
	 * @throws Exception
	 */
	void addCheckReq(InventoryCheckRequest checkReq) throws Exception;

	/**
	 * ����:�޸��̵����뵥
	 * @param checkReq	�̵����뵥
	 * @return 
	 * @throws Exception
	 */
	void updateCheckReq(InventoryCheckRequest checkReq) throws Exception;

	/**
	 * ����:�����̵������޸Ŀ��״̬
	 * @param lstask	�����б�
	 * @param lsstorage	����б�
	 * @param checkReq  �̵����뵥
	 * @return 
	 * @throws Exception
	 */
	void addCheckTasks(List<InventoryCheckTask> lstask, List<InoutJob> lsJobs,List<InoutJobdetail> lsjobdels,List<InventoryStorage> lsstorage, InventoryCheckRequest checkReq) throws Exception;

	/**
	 * ����:�����̵������޸��̵�������̵������״̬
	 * @param result	�̵���
	 * @param task		�̵�����
	 * @param req  		�̵����뵥
	 * @param storage 	���
	 * @return 
	 * @throws Exception
	 */
	void addCheckResult(InventoryCheckResult result, InventoryCheckTask task, InventoryCheckRequest req, InventoryStorage storage) throws Exception;
	/**
	 * ����:�����̵������޸��̵�������̵������״̬��������
	 * @param result	�̵���
	 * @param task		�̵�����
	 * @param req  		�̵����뵥
	 * @return 
	 * @throws Exception
	 */
	void addCheckResult(InventoryCheckResult result, InventoryCheckTask task, InventoryCheckRequest req) throws Exception;
	/**
	 * ����:�ر��̵�����,�ָ����״̬Ϊδ����
	 * @param req  			�̵����뵥
	 * @param lsStorages  	����б�
	 * @return 
	 * @throws Exception
	 */
	void closeReq(InventoryCheckRequest req, List lsStorages) throws Exception;
  
}
