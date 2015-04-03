package com.wms3.bms.standard.business.inventory;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckRequest;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask;

/**
 * ����: ����̵�ҵ��ӿ�
 * @author fangjie
 * 
 */
public interface IInventoryCheckBus {

	/**
	 * ����:����������ѯ�̵����뵥
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param type			����
	 * @param status		״̬
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	PagingTool getCheckRequests(String warehouseid, String whAreaId, String type, String status,String lotnumber,String productid, String strUrl, int maxLine,String fmDate,String toDate) throws Exception;

	/**
	 * ����:����̵����뵥
	 * @param checkReq	�̵����뵥
	 * @return 
	 * @throws Exception
	 */
	void addCheckReq(InventoryCheckRequest checkReq) throws Exception;

	/**
	 * ����:�����̵����뵥��ѯ�̵�������Ϣ
	 * @param requestid		�̵�����ID
	 * @param tray		          ��������
	 * @return 
	 * @throws Exception
	 */
	List<InventoryCheckTask> getCheckTasks(String requestid,String tray) throws Exception;

	/**
	 * ����:�����̵�����ID��ѯ�̵����뵥
	 * @param requestid		�̵�����ID
	 * @return 
	 * @throws Exception
	 */
	InventoryCheckRequest getCheckReqById(String requestid) throws Exception;

	/**
	 * ����:�޸��̵����뵥
	 * @param checkReq	�̵����뵥
	 * @return 
	 * @throws Exception
	 */
	void updateCheckReq(InventoryCheckRequest checkReq) throws Exception;

	/**
	 * ����:ɾ���̵����뵥
	 * @param requestid		�̵�����ID
	 * @return 
	 * @throws Exception
	 */
	void deleteCheckReq(String requestid) throws Exception;
	/**
	 * ����:����̵����뵥
	 * @param requestid		�̵�����ID
	 * @return 
	 * @throws Exception
	 */
	void finishCheckReq(String requestid) throws Exception;

	/**
	 * ����:�����̵������ѯ���
	 * @param requestid		�̵�����ID
	 * @return 
	 * @throws Exception
	 */
	List queryStorage(String requestid) throws Exception;

	/**
	 * ����:�����̵�����
	 * @param requestid		�̵�����ID
	 * @param ids			���ID
	 * @return 
	 * @throws Exception
	 */
	String addCheckTasks(String requestid, String ids) throws Exception;

	/**
	 * ����:�����̵������ѯ�̵���
	 * @param taskid		�̵�����ID
	 * @return 
	 * @throws Exception
	 */
	List getCheckResults(String taskid) throws Exception;
	/**
	 * ����:�����̵������ѯ�̵���
	 * @param taskid		�̵�����ID
	 * @return 
	 * @throws Exception
	 */
	List getCheckResultsByRequestid(String requestid) throws Exception;

	/**
	 * ����:�����̵���
	 * @param taskid			�̵�����ID
	 * @param checknum			�̵�����
	 * @param checknetweight	�̵�����
	 * @param strUserCode 		�û�ID
	 * @return 
	 * @throws Exception
	 */
	String addCheckResult(String taskid, String checknum, String checknetweight, String strUserCode) throws Exception;
	/**
	 * ����:�����̵�����������
	 * @param taskid			�̵�����ID
	 * @param checknum			�̵�����
	 * @param strUserCode 		�û�ID
	 * @return 
	 * @throws Exception
	 */
	CommonReturn addCheckResult(String taskid, String checknum, String strUserCode) throws Exception;

	/**
	 * ����:�����̵���ID��ѯ�̵���
	 * @param checkid		�̵���ID
	 * @return 
	 * @throws Exception
	 */
	InventoryCheckResult getCheckResultById(String checkid) throws Exception;
    /**
     * ���ܣ�������ӯ�̿���ʱ�����̵���
     * @param type  0.��ӯ���̵������ڿ�棩1.�̿����̵���С�ڿ�棩
     * @param fmtime ��ʼʱ��
     * @param totime ����ʱ��
     * @return
     * @throws Exception
     */
	CommonReturn getCheckResultsByTimeAndType(String type,String fmtime,String totime) throws Exception;
	/**
	 * ����:�޸��̵���
	 * @param result		�̵���
	 * @return 
	 * @throws Exception
	 */
	String updateCheckResult(InventoryCheckResult result) throws Exception;

	/**
	 * ����:�ر��̵�����
	 * @param requestid		�̵�����ID
	 * @return 
	 * @throws Exception
	 */
	String closeCheckTasks(String requestid) throws Exception;
    /**
     * ��ѯ�̵�����
     * @param requestid
     * @param traycode
     * @param platoon
     * @param column
     * @param floor
     * @return
     */
	List getCheckTasksbyTj(String requestid, String traycode, String platoon,
			String column, String floor) throws Exception;
    
	CommonReturn getCheckResultsByTimeAndType1(String type,String fmtime,String totime) throws Exception;

}
