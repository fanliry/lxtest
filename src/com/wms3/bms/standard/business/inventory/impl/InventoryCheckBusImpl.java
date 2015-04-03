package com.wms3.bms.standard.business.inventory.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.inventory.IInventoryCheckBus;
import com.wms3.bms.standard.business.inventory.IInventoryCheckResultBus;
import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.dao.inventory.IInventoryCheckDao;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryCheckDaoImpl;
import com.wms3.bms.standard.dao.inventory.impl.InventoryDaoImpl;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckRequest;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * ����: ����̵�ҵ����
 * @author fangjie
 * 
 */
public class InventoryCheckBusImpl implements IInventoryCheckBus {
	
	protected IInventoryCheckDao inventoryCheckDAO = null;
	protected IInventoryDao inventoryDAO = null;

	public InventoryCheckBusImpl(EntityDAO dao) {
		this.inventoryCheckDAO = new InventoryCheckDaoImpl(dao);
		this.inventoryDAO = new InventoryDaoImpl(dao);
	}

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
	public PagingTool getCheckRequests(String warehouseid, String whAreaId, String type, String status,String lotnumber,String productid, String strUrl, int maxLine,String fmDate,String toDate) throws Exception {
	
		PagingTool pt = null;
		
		try {
			StringBuilder strBud = new StringBuilder();
			strBud.append(" from InventoryCheckRequest as req where 1=1 ");
			
			if(warehouseid != null && warehouseid.trim().length() > 0){		//�ֿ�
				strBud.append(" and req.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whAreaId != null && whAreaId.trim().length() > 0){	//����
				strBud.append(" and req.wh_area_id='").append(whAreaId).append("'");
			}
			
			if(type != null && type.trim().length() > 0){			//����
				strBud.append(" and req.counttype='").append(type).append("'");
			}
			if(lotnumber != null && lotnumber.trim().length() > 0){			//����
				strBud.append(" and req.lotinfo='").append(lotnumber).append("'");
			}			
			if(productid != null && productid.trim().length() > 0){			//��Ʒid
				strBud.append(" and req.productid='").append(productid).append("'");
			}
			if(status != null && status.trim().length() > 0){		//״̬
				strBud.append(" and req.status='").append(status).append("'");
			}
			if(fmDate != null && fmDate.trim().length() > 0){		//��ʼʱ��
				strBud.append(" and req.requesttime>='").append(fmDate).append("'");
			}
			if(toDate != null && toDate.trim().length() > 0){		//����ʱ��
				strBud.append(" and req.requesttime<='").append(toDate).append(" 24:60:60'");
			}
			
			String strHQL = strBud.toString() + " order by req.priority, req.requesttime";
			
			String strCountHQL = "select count(*)" + strBud.toString();
			
			pt = CommonPagination.getPagingTool(inventoryCheckDAO.getEntityDAO(), strCountHQL ,strHQL, strUrl, 1, maxLine);
			
			
		} catch (Exception er) {
			throw new Exception("����������ѯ�̵����뵥�б����:" + er.getMessage());
		}
				
		return pt;	
	}

	/**
	 * ����:����̵����뵥
	 * @param checkReq	�̵����뵥
	 * @return 
	 * @throws Exception
	 */
	public void addCheckReq(InventoryCheckRequest checkReq) throws Exception {
		
		inventoryCheckDAO.addCheckReq(checkReq);
		Logger.info("�û�["+checkReq.getCreatemanid()+"]��������==>����̵�==>�������̵�����:" + checkReq.getRequestid());
	}

	@Override
	public List<InventoryCheckTask> getCheckTasks(String requestid,String tray) throws Exception {
		
		String strSQL = "";//��ѯre
		List list = null;
		
		try {
			strSQL = " from InventoryCheckTask as checktask where 1=1"; 
			if (requestid != null && requestid.length()>0) {
				strSQL += " and checktask.requestid='" + requestid + "'";
			}
			if (tray != null && tray.length()>0) {
				strSQL += " and checktask.traycode='" + tray + "'";	
			}
			list = inventoryCheckDAO.querySQL(strSQL);
			
		} catch(Exception er) {
			er.printStackTrace();
			throw new Exception("������==>����̵�==>��ѯ�̵������б�ʱ�����" + er.getMessage());
		}
			
		return list;
	}
	@Override
	public List<InventoryCheckTask> getCheckTasksbyTj(String requestid,String traycode,String platoon,String column,String floor) throws Exception {
		
		String strSQL = "";//��ѯre
		List list = null;
		
		try {
			strSQL = " from InventoryCheckTask as checktask where 1=1"; 
			if (requestid != null && requestid.length()>0) {
				strSQL += " and checktask.requestid='" + requestid + "'";
			}
			if (traycode != null && traycode.length()>0) {
				strSQL += " and checktask.traycode='" + traycode + "'";	
			}
			if (platoon != null && platoon.length()>0) {
				if(platoon.length()<2){
					strSQL += " and substring(checktask.cargo_space_id,7,2)='0"+platoon+"'";	
				}else{
					strSQL += " and substring(checktask.cargo_space_id,7,2)='"+platoon+"'";	
				}
			}
			if (column != null && column.length()>0) {
				if(column.length()<2){
					strSQL += " and substring(checktask.cargo_space_id,9,2)='0"+column+"'";	
				}else{
					strSQL += " and substring(checktask.cargo_space_id,9,2)='"+column+"'";	
				}	
			}
			if (floor != null && floor.length()>0) {
				if(floor.length()<2){
					strSQL += " and substring(checktask.cargo_space_id,11,2)='0"+floor+"'";	
				}else{
					strSQL += " and substring(checktask.cargo_space_id,11,2)='"+floor+"'";	
				}	
			}
			
			list = inventoryCheckDAO.querySQL(strSQL);
			
		} catch(Exception er) {
			er.printStackTrace();
			throw new Exception("������==>����̵�==>��ѯ�̵������б�ʱ�����" + er.getMessage());
		}
			
		return list;
	}

	/**
	 * ����:�����̵�����ID��ѯ�̵����뵥
	 * @param requestid		�̵�����ID
	 * @return 
	 * @throws Exception
	 */
	public InventoryCheckRequest getCheckReqById(String requestid) throws Exception {
		
		if(requestid != null){
			
			String strSql = " from InventoryCheckRequest as req where req.requestid='" + requestid + "'";
			List ls = inventoryCheckDAO.querySQL(strSql);
			
			if(ls != null && ls.size() > 0){
				return (InventoryCheckRequest)ls.get(0);
			}
		}
		return null;
	}

	/**
	 * ����:�޸��̵����뵥
	 * @param checkReq	�̵����뵥
	 * @return 
	 * @throws Exception
	 */
	public void updateCheckReq(InventoryCheckRequest checkReq) throws Exception {
		
		inventoryCheckDAO.updateCheckReq(checkReq);
	}
	
	/**
	 * ����:ɾ���̵����뵥
	 * @param requestid		�̵�����ID
	 * @return 
	 * @throws Exception
	 */
	public void deleteCheckReq(String requestid) throws Exception {
		
		if(requestid != null){
		
			String strSql  = " delete InventoryCheckRequest as req where req.requestid='" + requestid + "'";
			inventoryCheckDAO.excuteSQL(strSql);
		}	
	}
	/**
	 * ����:����̵����뵥
	 * @param requestid		�̵�����ID
	 * @return 
	 * @throws Exception
	 */
	public void finishCheckReq(String requestid) throws Exception {
		IInventoryCheckResultBus checkResultBus = new InventoryCheckResultBusImpl(inventoryDAO.getEntityDAO());
		InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(inventoryDAO.getEntityDAO());
		if(requestid != null){
			//�̵�����
			List<InventoryCheckResult> checkResults = checkResultBus.getCheckResultByRequestId(requestid);
			List<InventoryCheckTask> tasks = getCheckTasks(requestid,"");
			InventoryCheckRequest request =  getCheckReqById(requestid);
			List<InventoryStorage> inventorys = new ArrayList<InventoryStorage>();
			InventoryCheckResult result = null;
			InventoryStorage inventory = null;
			InventoryCheckTask task = null;
			if(checkResults!=null && checkResults.size()>0){
				for (int i = 0; i < checkResults.size(); i++) {
					result = (InventoryCheckResult)checkResults.get(i);
					result.setStatus("3");// 1.�½���2.������3.���
					inventory = inventoryadjustbus2.getInventoryInfoToId(result.getInventoryid());
					inventory.setPnum(result.getChecknum());
					inventory.setStatus("0");
					inventorys.add(inventory);
				}
			}
			if(tasks!=null && tasks.size()>0){
				for (int i = 0; i < tasks.size(); i++) {
					task = (InventoryCheckTask)tasks.get(i);
					task.setStatus("5");
				}
			}
			if(request!=null){
				request.setStatus("4");
			}
			
			Session session = null;
	        Transaction tx = null;
			try{
				session = inventoryCheckDAO.getEntityDAO().getSession();
	            tx = session.beginTransaction();
				for (int i = 0; i < checkResults.size(); i++) {
					 result = (InventoryCheckResult)checkResults.get(i);
					 session.update(result);
				}
				for (int i = 0; i < inventorys.size(); i++) {
					inventory = (InventoryStorage)inventorys.get(i);
					if(inventory.getPnum()<=0){
						 session.delete(inventory);
					}else{
						 session.update(inventory);
					}
					
				}
				for (int i = 0; i < tasks.size(); i++) {
					task = (InventoryCheckTask)tasks.get(i);
					session.update(inventory);
					
				}
				if(request!=null){
					session.update(request);
				}
				tx.commit();	
			}catch(Exception ex){
				if(tx != null){
	                tx.rollback();
	            }
				Logger.error("�޸ĳ��ⵥ��ϸ�����������"+ex.getMessage());	
			}finally{
				inventoryCheckDAO.getEntityDAO().closeSession(session);
	        }
		}	
	}

	/**
	 * ����:�����̵������ѯ���
	 * @param requestid		�̵�����ID
	 * @return 
	 * @throws Exception
	 */
	public List queryStorage(String requestid) throws Exception {
		
		List list = null;
		
		try {
			InventoryCheckRequest checkreq = getCheckReqById(requestid);
			String warehouseid = checkreq.getWarehouseid();		//�ֿ�ID
			String whareaid = checkreq.getWh_area_id();			//����ID
			String cargospaceid = checkreq.getCargo_space_id();	//��λID
			String lotnumber = checkreq.getLotinfo();		    //����
			String productid = checkreq.getProductid();			//��ƷID
			String traycode = checkreq.getTraycode();			//��������
			//String boxcode = checkreq.getBoxcode();            	//������
		    String productcode = checkreq.getProductcode();     //��Ʒ����
			
			StringBuilder strBud = new StringBuilder();
			strBud.append(" from InventoryStorage as storage where storage.status='0' ");	//״̬  0:δ���䣬1���ѷ��� 3:���� 4:��Ʒ������5���̵�
			
			if(warehouseid != null && warehouseid.length() > 0){		//�ֿ�
				strBud.append(" and storage.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whareaid != null && whareaid.length() > 0){				//����
				strBud.append(" and storage.whAreaId='").append(whareaid).append("'");
			}
			
			if(cargospaceid != null && cargospaceid.length() > 0){		//��λID
				strBud.append(" and storage.cargoSpaceId='").append(cargospaceid).append("'");
			}
			
			if(lotnumber != null && lotnumber.length() > 0){			//����
				strBud.append(" and storage.lotinfo='").append(lotnumber).append("'");
			}
			
			if(productid != null && productid.length() > 0){			//��ƷID
				strBud.append(" and storage.productid='").append(productid).append("'");
			}
			
			if(traycode != null && traycode.length() > 0){				//��������
				strBud.append(" and storage.traycode='").append(traycode).append("'");
			}
		
			/*if(productcode != null && productcode.length() > 0){		//��Ʒ����
				strBud.append(" and storage.productcode='").append(productcode).append("'");
			}*/
			
			strBud.append(" order by storage.cargoSpaceId");
			
			list = inventoryCheckDAO.querySQL(strBud.toString());
			
		} catch(Exception er) {
			er.printStackTrace();
			throw new Exception("������==>����̵�==>�����̵������ѯ����б�ʱ�����" + er.getMessage());
		}
			
		return list;
	}

	/**
	 * ����:�����̵����� ��ҵ�Լ���ҵ��ϸ
	 * @param requestid		�̵�����ID
	 * @param ids			���ID
	 * @return 
	 * @throws Exception
	 */
	public String addCheckTasks(String requestid, String ids) throws Exception {
		
		String strBackMsg = "Y";
		List<InventoryCheckTask> lstask = new ArrayList<InventoryCheckTask>();
		List<InventoryStorage> lsstorage = new ArrayList<InventoryStorage>();
		List<InoutJob> lsJobs = new ArrayList<InoutJob>();
		List<InoutJobdetail> lsJobdetails = new ArrayList<InoutJobdetail>();
		try{
			
			InventoryStorage storage = null;
			InventoryCheckTask task = null;
			InoutJob outJob = null;
			InoutJobdetail outJobdetail = null;
			String strTime =  StrTools.getCurrDateTime(2); 
			
			String[] id = ids.split(",");
			for(int i=0; i<id.length; i++){
				
				storage = inventoryDAO.getInventoryById(id[i]);
			/*************************����̵�����*******************************/	
				task = new InventoryCheckTask();
				task.setTaskid(getNo(i+1));			//ID
				task.setRequestid(requestid);		//�̵������ID
				task.setStatus("1");				//״̬ 1�½�
				task.setCargo_space_id(storage.getCargoSpaceId());	//��λID
				task.setWh_area_id(storage.getWhAreaId());	//����ID
				task.setLotid(storage.getLotid());//��������
				task.setLotinfo(storage.getLotinfo());  //����
				task.setProductid(storage.getProductid());	//��ƷID
				task.setCreatetime(strTime);				//����ʱ��
				task.setNum(storage.getPnum());				//�������
				task.setNetweight(storage.getPnetweight());	//�������
				task.setInventoryid(id[i]);					//���ID
				task.setWarehouseid(storage.getWarehouseid());	//�ֿ�ID
				task.setBoxcode(storage.getBoxcode());      //������
				task.setProductcode(storage.getProductcode());  //��Ʒ����
				task.setPunit(storage.getPunit());//��λ
				task.setTraycode(storage.getTraycode());	//��������
				task.setPrintdate(storage.getProductdate()); //��������
				lstask.add(task);//����̵�����
				
				//�жϿ��״̬�ǲ���δ����
				if(storage.getStatus().equals("0")){
					storage.setStatus("5");	 //ע����ʱ���޸Ŀ��״̬��״̬  0:δ���䣬1���ѷ��䣬2�����̵㣬 3:���� 4:��Ʒ������5���̵�
					lsstorage.add(storage);
				}else{
					strBackMsg = "���ڿ��״̬���ǡ�δ���䡿���������ڷ���ÿ�棬�����²�ѯ���������̵��¼��";
					break;
				}
			}
			//�ж��̵����뵥��״̬�ǲ����½�
			InventoryCheckRequest checkReq = getCheckReqById(requestid);
			if(checkReq.getStatus().equals("1")){	//1:�½���3:�����̵㣻4�����
				
			}else{
				strBackMsg = "�̵����뵥״̬���ǡ��½����������²�ѯ��";
				
			}
			if(strBackMsg.equals("Y")){
				inventoryCheckDAO.addCheckTasks(lstask, lsJobs,lsJobdetails,lsstorage, checkReq);
			}
			
		}catch(Exception er){
        	Logger.error("���̵����룺" + requestid + "�����̵�����ʱ����" + er.getMessage());
            throw new Exception("���̵����룺" + requestid + "�����̵�����ʱ����" + er.getMessage());
        }
		return strBackMsg; 
	}

	private String getNo(int i){
		String strNo = "CT-";
		strNo = strNo + StrTools.getCurrDateTime(0) + i;
		return strNo;
	}

	/**
	 * ����:�����̵������ѯ�̵���
	 * @param taskid		�̵�����ID
	 * @return 
	 * @throws Exception
	 */
	public List getCheckResults(String taskid) throws Exception {
			
		String strSql = " from InventoryCheckResult as result where result.taskid='" + taskid + "'";
		List ls = inventoryCheckDAO.querySQL(strSql);
		return ls;
	}
	/**
	 * ����:�����̵������ѯ�̵���
	 * @param taskid		�̵�����ID
	 * @return 
	 * @throws Exception
	 */
	public List getCheckResultsByRequestid(String requestid) throws Exception {
			
		String strSql = " from InventoryCheckResult as result where result.requestid='" + requestid + "'";
		List ls = inventoryCheckDAO.querySQL(strSql);
		return ls;
	}
	
	/**
	 * ����:�����̵�����ID��ѯ�̵�������Ϣ
	 * @param taskid		�̵�����ID
	 * @return 
	 * @throws Exception
	 */
	public InventoryCheckTask getCheckTaskById(String taskid) throws Exception {
		
		String strSql = " from InventoryCheckTask as checktask where checktask.taskid='" + taskid + "'";
		List ls = inventoryCheckDAO.querySQL(strSql);
		
		if(ls != null && ls.size() > 0){
			return (InventoryCheckTask)ls.get(0);
		}
		
		return null;
	}

	/**
	 * ����:�����̵���
	 * @param taskid			�̵�����ID
	 * @param checknum			�̵�����
	 * @param checknetweight	�̵�����
	 * @param strUserCode 		�û�ID
	 * @return 
	 * @throws Exception
	 */
	public String addCheckResult(String taskid, String checknum, String checknetweight, String strUserCode) throws Exception {
		
		String strBackMsg = "Y";
		
		try{
			
			InventoryCheckTask task = getCheckTaskById(taskid);		//�̵�����
			InventoryCheckRequest req = getCheckReqById(task.getRequestid());	//�̵�����
			
			//�ж��̵������״̬���رպ��ܲ���
			if(req.getStatus().equals("4")){	//״̬ 1:�½���2�������̵�����3:�����̵㣻4�����
				
				strBackMsg = "���̵������Ѿ��رգ��������޸��̵�����";
				
			}else{
					
				//�޸��̵������״̬��ֻ���½����̵����������̵���
				if(task.getStatus().equals("1")){ //״̬	1:�½���3.δ����4�����
					task.setStatus("4");
					
					//�޸��̵������״̬
					if(req.getStatus().equals("2")){//�̵�������2�������̵������ʱ���޸��̵������״̬
						req.setStatus("3");	
						
					}else{	//�̵�������3:�����̵��ʱ������̵�����ȫ�����Ҫ���̵�������Ϊ4���
						
						List lsTasks = getCheckTasks(task.getRequestid(),"");	//�̵������б�
						InventoryCheckTask temptask = null;
						boolean flag = true;
						for(int i=0; i<lsTasks.size(); i++){
							temptask = (InventoryCheckTask)lsTasks.get(i);
							if(!temptask.getTaskid().equals(task.getTaskid()) && temptask.getStatus().equals("1")){
								flag = false;
								break;
							}
						}
						if(flag){
							req.setStatus("4");	
						}
					}
					
					//�����̵���
					InventoryCheckResult result = new InventoryCheckResult();
					result.setTaskid(taskid);	//�̵�����ID
					//result.setCustomerid(task.getCustomerid());	//�ͻ�ID
					result.setProductid(task.getProductid());	//��ƷID
					result.setTraycode(task.getTraycode());		//��������
					result.setNum(task.getNum());				//�������
					result.setChecknum(Double.parseDouble(checknum));	//�̵�����
					result.setNetweight(task.getNetweight());	//�������
					result.setChecknetweight(Double.parseDouble(checknetweight));//�̵�����
					result.setChecktime(StrTools.getCurrDateTime(2));	//�̵�ʱ��
					result.setCreatemanid(strUserCode);		//������
					
					//�ָ�����״̬Ϊ0δ����
					InventoryStorage storage = inventoryDAO.getInventoryById(task.getInventoryid());	//���
					storage.setStatus("0");	//״̬  0:δ���䣬1���ѷ��� 3:���� 4:��Ʒ������5���̵�
					
					//�����̵������޸��̵�������̵������״̬���ָ�����״̬
					inventoryCheckDAO.addCheckResult(result, task, req, storage);
					
				}else{
					strBackMsg = "���̵������Ѿ���ɣ������²�ѯ��";
				}
			}
			
		}catch(Exception er){
        	Logger.error("���̵�����" + taskid + "�����̵���ʱ����" + er.getMessage());
            throw new Exception("���̵�����" + taskid + "�����̵���ʱ����" + er.getMessage());
        }
		
		return strBackMsg; 
	}

	/**
	 * ����:�����̵���ID��ѯ�̵���
	 * @param checkid		�̵���ID
	 * @return 
	 * @throws Exception
	 */
	public InventoryCheckResult getCheckResultById(String checkid) throws Exception {
		
		String strSql = " from InventoryCheckResult as checkresult where checkresult.checkid='" + checkid + "'";
		List ls = inventoryCheckDAO.querySQL(strSql);
		
		if(ls != null && ls.size() > 0){
			return (InventoryCheckResult)ls.get(0);
		}
		
		return null;
	}

	/**
	 * ����:�޸��̵���
	 * @param result		�̵���
	 * @return 
	 * @throws Exception
	 */
	public String updateCheckResult(InventoryCheckResult result) throws Exception {
		
		String strBackMsg = "Y";
		String taskid = result.getTaskid();	//�̵�����ID
		try{
			
			InventoryCheckTask task = getCheckTaskById(taskid);		//�̵�����
			InventoryCheckRequest req = getCheckReqById(task.getRequestid());	//�̵�����
			
			//�ж��̵������״̬���رպ��ܲ���
			if(req.getStatus().equals("5")){	//״̬ 1:�½���2�������̵�����3:�����̵㣻4��δ���� 5�����
				
				strBackMsg = "���̵������Ѿ���ɣ��������޸��̵�����";
			}else{
				inventoryCheckDAO.getEntityDAO().update(result);
			}
			
		}catch(Exception er){
        	Logger.error("���̵�����" + taskid + "�޸��̵���ʱ����" + er.getMessage());
            throw new Exception("���̵�����" + taskid + "�޸��̵���ʱ����" + er.getMessage());
        }
		return strBackMsg;
	}

	/**
	 * ����:�ر��̵�����
	 * @param requestid		�̵�����ID
	 * @return 
	 * @throws Exception
	 */
	public String closeCheckTasks(String requestid) throws Exception {
		
		String strBackMsg = "Y";
		try{
			
			InventoryCheckRequest req = getCheckReqById(requestid);	//�̵�����
			
			//�ж��̵������״̬���رպ��ܲ���
			if(req.getStatus().equals("3")){	//״̬ 1:�½���2�������̵�����3:�����̵㣻4�����

				List lsTasks = getCheckTasks(requestid,"");	//�̵������б�
				List lsStorages = new ArrayList();			//����б�
				InventoryCheckTask task = null;		//�̵�����
				InventoryStorage storage = null;	//���
				boolean flg = true;
				
				//�ж��̵������״̬�Ƿ�ȫ�����
				for(int i=0; i<lsTasks.size(); i++){
					
					task = (InventoryCheckTask)lsTasks.get(i);
					storage = inventoryDAO.getInventoryById(task.getInventoryid());
					
					if(task.getStatus().equals("4")){
						storage.setStatus("0");	//״̬  0:δ���䣬1���ѷ��� 3:���� 4:��Ʒ������5���̵�
						lsStorages.add(storage);
						
					}else{
						flg = false;
						break;
					}
				}
				
				if(flg){
					
					req.setStatus("4");
					
					//�ر��̵�����,�ָ����״̬Ϊδ����
					inventoryCheckDAO.closeReq(req, lsStorages);
					
				}else{
					strBackMsg = "�����̵�����δ��ɣ����ܹرգ���ȷ�ϣ�";
				}
				
			}else{
				
				strBackMsg = "���̵�����ǡ������̵㡿״̬�����ܹرգ���ȷ�ϣ�";
			}
			
		}catch(Exception er){
        	Logger.error("�ر��̵����뵥��" + requestid + "ʱ����" + er.getMessage());
            throw new Exception("�ر��̵����뵥��" + requestid + "ʱ����" + er.getMessage());
        }
		return strBackMsg;
	}

	@Override
	public CommonReturn addCheckResult(String taskid, String checknum,
			String strUserCode) throws Exception {
		
		CommonReturn cReturn = new CommonReturn();
		//String strBackMsg = "Y";	
		cReturn.setRetInfo("Y");
		try{
			
			InventoryCheckTask task = getCheckTaskById(taskid);		//�̵�����
			InventoryCheckRequest req = getCheckReqById(task.getRequestid());	//�̵�����
			
			//�ж��̵������״̬���رպ��ܲ���
			if(req.getStatus().equals("4")){	//״̬ 1:�½���2�������̵�����3:�����̵㣻4�����
				
				cReturn.setRetInfo("���̵������Ѿ���ɣ��������޸��̵�����");
				
			}else{
					
				//�޸��̵������״̬��ֻ���½����̵����������̵���
				if(!task.getStatus().equals("4")&&!task.getStatus().equals("5")){ //״̬1:�½���2�������̵����� 3:�����̵� 4�������̵���  5�����
					
					task.setStatus("4");//����״̬�������̵���
					req.setStatus("3");//���뵥״̬�������̵�
					
					//�����̵���
					InventoryCheckResult result = new InventoryCheckResult();
					result.setTaskid(taskid);	//�̵�����ID
					result.setLotnumber(task.getLotinfo());   //����
					result.setProductid(task.getProductid());	//��ƷID
					result.setTraycode(task.getTraycode());		//��������
					result.setNum(task.getNum());				//�������
					result.setChecknum(Double.parseDouble(checknum));	//�̵�����
					result.setChecktime(StrTools.getCurrDateTime(2));	//�̵�ʱ��
					result.setCreatemanid(strUserCode);		//������
					result.setPrintdate(task.getPrintdate());//��������
					result.setPunit(task.getPunit());//��λ
					result.setCargoSpaceId(task.getCargo_space_id());
					result.setInventoryid(task.getInventoryid());
					result.setRequestid(task.getRequestid());
					result.setStatus("1");//�½�
					
					
					InventoryStorage storage = inventoryDAO.getInventoryById(task.getInventoryid());
					//�жϿ��״̬�ǲ���δ����
					storage.setStatus("2");	 //1���ѷ��䣬2:������� 3:���� 4:��Ʒ������5���̵�       (�ݴ���״̬)
					
					
					//�����̵������޸��̵�������̵������״̬
					inventoryCheckDAO.addCheckResult(result, task, req,storage);
					
					List<InventoryCheckResult> lsResults = new ArrayList<InventoryCheckResult>();
					lsResults.add(result);
					cReturn.setLsReturn(lsResults);
			
				}else if (task.getStatus().equals("4")) {
					cReturn.setRetInfo("���̵��������������������½����");
				}
				else{
					cReturn.setRetInfo("���̵������Ѿ���ɣ������²�ѯ��");
				}
			}
			
		}catch(Exception er){
        	Logger.error("���̵�����" + taskid + "�����̵���ʱ����" + er.getMessage());
            throw new Exception("���̵�����" + taskid + "�����̵���ʱ����" + er.getMessage());
        }
		
		return cReturn; 
	}

	@Override
	public CommonReturn getCheckResultsByTimeAndType(String type,
			String fmtime, String totime) throws Exception {
		CommonReturn cReturn = new CommonReturn();
		StringBuilder strBuder = new StringBuilder();
		
		try {
	
			strBuder.append("from InventoryCheckResult as result,InventoryCheckTask as task where result.taskid=task.taskid ");
			if (type!=null&&type.trim().equals("1")) {
				strBuder.append(" and result.checknum < result.num");
			}
			if (type!=null&&type.trim().equals("0")) {
				strBuder.append(" and result.checknum > result.num");
			}
			if (fmtime!=null&&fmtime.length()>0) {
				strBuder.append(" and result.checktime >= '").append(fmtime).append("'");
			}
			if (totime!=null&&totime.length()>0) {
				strBuder.append(" and result.checktime <= '").append(totime).append(" 24:59:59'");
			}
			cReturn.setStrQueryHQL(strBuder.toString());
			cReturn.setStrCountHQL("select count(*) "+strBuder.toString());
			List ls = inventoryCheckDAO.querySQL(strBuder.toString());
			cReturn.setLsReturn(ls);
			
		} catch (Exception er) {
	     	Logger.error("����������ѯ�̵���ʱ����" + er.getMessage());
            throw new Exception("����������ѯ�̵���ʱ����" + er.getMessage());
     
		}
		return cReturn;
	}

	public CommonReturn getCheckResultsByTimeAndType1(String type,
			String fmtime, String totime) throws Exception {
		CommonReturn cReturn = new CommonReturn();
		StringBuilder strBuder = new StringBuilder();
		
		try {
	
			strBuder.append("from InventoryCheckResult as result,InventoryCheckTask as task where result.taskid=task.taskid and result.status='1'");
			if (type!=null&&type.trim().equals("1")) {
				strBuder.append(" and result.checknum < result.num");
			}
			if (type!=null&&type.trim().equals("0")) {
				strBuder.append(" and result.checknum > result.num");
			}
			if (fmtime!=null&&fmtime.length()>0) {
				strBuder.append(" and result.checktime >= '").append(fmtime).append("'");
			}
			if (totime!=null&&totime.length()>0) {
				strBuder.append(" and result.checktime <= '").append(totime).append(" 24:59:59'");
			}
			cReturn.setStrQueryHQL(strBuder.toString());
			cReturn.setStrCountHQL("select count(*) "+strBuder.toString());
			List ls = inventoryCheckDAO.querySQL(strBuder.toString());
			cReturn.setLsReturn(ls);
			
		} catch (Exception er) {
	     	Logger.error("����������ѯ�̵���ʱ����" + er.getMessage());
            throw new Exception("����������ѯ�̵���ʱ����" + er.getMessage());
     
		}
		return cReturn;
	}
	
}