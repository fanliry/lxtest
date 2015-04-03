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
 * 描述: 库存盘点业务类
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
	 * 功能:根据条件查询盘点申请单
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param type			类型
	 * @param status		状态
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
			
			if(warehouseid != null && warehouseid.trim().length() > 0){		//仓库
				strBud.append(" and req.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whAreaId != null && whAreaId.trim().length() > 0){	//库区
				strBud.append(" and req.wh_area_id='").append(whAreaId).append("'");
			}
			
			if(type != null && type.trim().length() > 0){			//类型
				strBud.append(" and req.counttype='").append(type).append("'");
			}
			if(lotnumber != null && lotnumber.trim().length() > 0){			//批号
				strBud.append(" and req.lotinfo='").append(lotnumber).append("'");
			}			
			if(productid != null && productid.trim().length() > 0){			//产品id
				strBud.append(" and req.productid='").append(productid).append("'");
			}
			if(status != null && status.trim().length() > 0){		//状态
				strBud.append(" and req.status='").append(status).append("'");
			}
			if(fmDate != null && fmDate.trim().length() > 0){		//开始时间
				strBud.append(" and req.requesttime>='").append(fmDate).append("'");
			}
			if(toDate != null && toDate.trim().length() > 0){		//结束时间
				strBud.append(" and req.requesttime<='").append(toDate).append(" 24:60:60'");
			}
			
			String strHQL = strBud.toString() + " order by req.priority, req.requesttime";
			
			String strCountHQL = "select count(*)" + strBud.toString();
			
			pt = CommonPagination.getPagingTool(inventoryCheckDAO.getEntityDAO(), strCountHQL ,strHQL, strUrl, 1, maxLine);
			
			
		} catch (Exception er) {
			throw new Exception("根据条件查询盘点申请单列表出错:" + er.getMessage());
		}
				
		return pt;	
	}

	/**
	 * 功能:添加盘点申请单
	 * @param checkReq	盘点申请单
	 * @return 
	 * @throws Exception
	 */
	public void addCheckReq(InventoryCheckRequest checkReq) throws Exception {
		
		inventoryCheckDAO.addCheckReq(checkReq);
		Logger.info("用户["+checkReq.getCreatemanid()+"]，库存管理==>库存盘点==>增加了盘点申请:" + checkReq.getRequestid());
	}

	@Override
	public List<InventoryCheckTask> getCheckTasks(String requestid,String tray) throws Exception {
		
		String strSQL = "";//查询re
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
			throw new Exception("库存管理==>库存盘点==>查询盘点任务列表时候出错：" + er.getMessage());
		}
			
		return list;
	}
	@Override
	public List<InventoryCheckTask> getCheckTasksbyTj(String requestid,String traycode,String platoon,String column,String floor) throws Exception {
		
		String strSQL = "";//查询re
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
			throw new Exception("库存管理==>库存盘点==>查询盘点任务列表时候出错：" + er.getMessage());
		}
			
		return list;
	}

	/**
	 * 功能:根据盘点申请ID查询盘点申请单
	 * @param requestid		盘点申请ID
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
	 * 功能:修改盘点申请单
	 * @param checkReq	盘点申请单
	 * @return 
	 * @throws Exception
	 */
	public void updateCheckReq(InventoryCheckRequest checkReq) throws Exception {
		
		inventoryCheckDAO.updateCheckReq(checkReq);
	}
	
	/**
	 * 功能:删除盘点申请单
	 * @param requestid		盘点申请ID
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
	 * 功能:完成盘点申请单
	 * @param requestid		盘点申请ID
	 * @return 
	 * @throws Exception
	 */
	public void finishCheckReq(String requestid) throws Exception {
		IInventoryCheckResultBus checkResultBus = new InventoryCheckResultBusImpl(inventoryDAO.getEntityDAO());
		InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(inventoryDAO.getEntityDAO());
		if(requestid != null){
			//盘点结果单
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
					result.setStatus("3");// 1.新建，2.调整，3.完成
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
				Logger.error("修改出库单详细完成数量错误："+ex.getMessage());	
			}finally{
				inventoryCheckDAO.getEntityDAO().closeSession(session);
	        }
		}	
	}

	/**
	 * 功能:根据盘点申请查询库存
	 * @param requestid		盘点申请ID
	 * @return 
	 * @throws Exception
	 */
	public List queryStorage(String requestid) throws Exception {
		
		List list = null;
		
		try {
			InventoryCheckRequest checkreq = getCheckReqById(requestid);
			String warehouseid = checkreq.getWarehouseid();		//仓库ID
			String whareaid = checkreq.getWh_area_id();			//库区ID
			String cargospaceid = checkreq.getCargo_space_id();	//货位ID
			String lotnumber = checkreq.getLotinfo();		    //批号
			String productid = checkreq.getProductid();			//产品ID
			String traycode = checkreq.getTraycode();			//托盘条码
			//String boxcode = checkreq.getBoxcode();            	//箱条码
		    String productcode = checkreq.getProductcode();     //产品条码
			
			StringBuilder strBud = new StringBuilder();
			strBud.append(" from InventoryStorage as storage where storage.status='0' ");	//状态  0:未分配，1：已分配 3:调整 4:货品调整；5：盘点
			
			if(warehouseid != null && warehouseid.length() > 0){		//仓库
				strBud.append(" and storage.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whareaid != null && whareaid.length() > 0){				//库区
				strBud.append(" and storage.whAreaId='").append(whareaid).append("'");
			}
			
			if(cargospaceid != null && cargospaceid.length() > 0){		//货位ID
				strBud.append(" and storage.cargoSpaceId='").append(cargospaceid).append("'");
			}
			
			if(lotnumber != null && lotnumber.length() > 0){			//批号
				strBud.append(" and storage.lotinfo='").append(lotnumber).append("'");
			}
			
			if(productid != null && productid.length() > 0){			//产品ID
				strBud.append(" and storage.productid='").append(productid).append("'");
			}
			
			if(traycode != null && traycode.length() > 0){				//托盘条码
				strBud.append(" and storage.traycode='").append(traycode).append("'");
			}
		
			/*if(productcode != null && productcode.length() > 0){		//产品条码
				strBud.append(" and storage.productcode='").append(productcode).append("'");
			}*/
			
			strBud.append(" order by storage.cargoSpaceId");
			
			list = inventoryCheckDAO.querySQL(strBud.toString());
			
		} catch(Exception er) {
			er.printStackTrace();
			throw new Exception("库存管理==>库存盘点==>根据盘点申请查询库存列表时候出错：" + er.getMessage());
		}
			
		return list;
	}

	/**
	 * 功能:生成盘点任务 作业以及作业明细
	 * @param requestid		盘点申请ID
	 * @param ids			库存ID
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
			/*************************添加盘点任务*******************************/	
				task = new InventoryCheckTask();
				task.setTaskid(getNo(i+1));			//ID
				task.setRequestid(requestid);		//盘点申请表ID
				task.setStatus("1");				//状态 1新建
				task.setCargo_space_id(storage.getCargoSpaceId());	//货位ID
				task.setWh_area_id(storage.getWhAreaId());	//库区ID
				task.setLotid(storage.getLotid());//批号类型
				task.setLotinfo(storage.getLotinfo());  //批号
				task.setProductid(storage.getProductid());	//产品ID
				task.setCreatetime(strTime);				//创建时间
				task.setNum(storage.getPnum());				//库存数量
				task.setNetweight(storage.getPnetweight());	//库存重量
				task.setInventoryid(id[i]);					//库存ID
				task.setWarehouseid(storage.getWarehouseid());	//仓库ID
				task.setBoxcode(storage.getBoxcode());      //箱条码
				task.setProductcode(storage.getProductcode());  //产品条码
				task.setPunit(storage.getPunit());//单位
				task.setTraycode(storage.getTraycode());	//托盘条码
				task.setPrintdate(storage.getProductdate()); //生产日期
				lstask.add(task);//添加盘点任务
				
				//判断库存状态是不是未分配
				if(storage.getStatus().equals("0")){
					storage.setStatus("5");	 //注：暂时不修改库存状态，状态  0:未分配，1：已分配，2：需盘点， 3:调整 4:货品调整；5：盘点
					lsstorage.add(storage);
				}else{
					strBackMsg = "存在库存状态不是【未分配】，可能正在分配该库存，请重新查询后再生成盘点记录！";
					break;
				}
			}
			//判断盘点申请单的状态是不是新建
			InventoryCheckRequest checkReq = getCheckReqById(requestid);
			if(checkReq.getStatus().equals("1")){	//1:新建；3:正在盘点；4：完成
				
			}else{
				strBackMsg = "盘点申请单状态不是【新建】，请重新查询！";
				
			}
			if(strBackMsg.equals("Y")){
				inventoryCheckDAO.addCheckTasks(lstask, lsJobs,lsJobdetails,lsstorage, checkReq);
			}
			
		}catch(Exception er){
        	Logger.error("对盘点申请：" + requestid + "生成盘点任务时出错！" + er.getMessage());
            throw new Exception("对盘点申请：" + requestid + "生成盘点任务时出错：" + er.getMessage());
        }
		return strBackMsg; 
	}

	private String getNo(int i){
		String strNo = "CT-";
		strNo = strNo + StrTools.getCurrDateTime(0) + i;
		return strNo;
	}

	/**
	 * 功能:根据盘点任务查询盘点结果
	 * @param taskid		盘点任务ID
	 * @return 
	 * @throws Exception
	 */
	public List getCheckResults(String taskid) throws Exception {
			
		String strSql = " from InventoryCheckResult as result where result.taskid='" + taskid + "'";
		List ls = inventoryCheckDAO.querySQL(strSql);
		return ls;
	}
	/**
	 * 功能:根据盘点任务查询盘点结果
	 * @param taskid		盘点任务ID
	 * @return 
	 * @throws Exception
	 */
	public List getCheckResultsByRequestid(String requestid) throws Exception {
			
		String strSql = " from InventoryCheckResult as result where result.requestid='" + requestid + "'";
		List ls = inventoryCheckDAO.querySQL(strSql);
		return ls;
	}
	
	/**
	 * 功能:根据盘点任务ID查询盘点任务信息
	 * @param taskid		盘点任务ID
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
	 * 功能:生成盘点结果
	 * @param taskid			盘点任务ID
	 * @param checknum			盘点数量
	 * @param checknetweight	盘点重量
	 * @param strUserCode 		用户ID
	 * @return 
	 * @throws Exception
	 */
	public String addCheckResult(String taskid, String checknum, String checknetweight, String strUserCode) throws Exception {
		
		String strBackMsg = "Y";
		
		try{
			
			InventoryCheckTask task = getCheckTaskById(taskid);		//盘点任务
			InventoryCheckRequest req = getCheckReqById(task.getRequestid());	//盘点申请
			
			//判断盘点申请的状态，关闭后不能操作
			if(req.getStatus().equals("4")){	//状态 1:新建；2：生成盘点任务；3:正在盘点；4：完成
				
				strBackMsg = "该盘点申请已经关闭，不能再修改盘点结果！";
				
			}else{
					
				//修改盘点任务的状态，只对新建的盘点任务生成盘点结果
				if(task.getStatus().equals("1")){ //状态	1:新建；3.未回流4：完成
					task.setStatus("4");
					
					//修改盘点申请的状态
					if(req.getStatus().equals("2")){//盘点申请是2：生成盘点任务的时候，修改盘点申请的状态
						req.setStatus("3");	
						
					}else{	//盘点申请是3:正在盘点的时候，如果盘点任务全部完成要将盘点申请设为4完成
						
						List lsTasks = getCheckTasks(task.getRequestid(),"");	//盘点任务列表
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
					
					//生成盘点结果
					InventoryCheckResult result = new InventoryCheckResult();
					result.setTaskid(taskid);	//盘点任务ID
					//result.setCustomerid(task.getCustomerid());	//客户ID
					result.setProductid(task.getProductid());	//产品ID
					result.setTraycode(task.getTraycode());		//托盘条码
					result.setNum(task.getNum());				//库存数量
					result.setChecknum(Double.parseDouble(checknum));	//盘点数量
					result.setNetweight(task.getNetweight());	//库存重量
					result.setChecknetweight(Double.parseDouble(checknetweight));//盘点重量
					result.setChecktime(StrTools.getCurrDateTime(2));	//盘点时间
					result.setCreatemanid(strUserCode);		//操作人
					
					//恢复库存的状态为0未分配
					InventoryStorage storage = inventoryDAO.getInventoryById(task.getInventoryid());	//库存
					storage.setStatus("0");	//状态  0:未分配，1：已分配 3:调整 4:货品调整；5：盘点
					
					//生成盘点结果，修改盘点任务和盘点申请的状态，恢复库存的状态
					inventoryCheckDAO.addCheckResult(result, task, req, storage);
					
				}else{
					strBackMsg = "该盘点任务已经完成，请重新查询！";
				}
			}
			
		}catch(Exception er){
        	Logger.error("对盘点任务：" + taskid + "生成盘点结果时出错！" + er.getMessage());
            throw new Exception("对盘点任务：" + taskid + "生成盘点结果时出错：" + er.getMessage());
        }
		
		return strBackMsg; 
	}

	/**
	 * 功能:根据盘点结果ID查询盘点结果
	 * @param checkid		盘点结果ID
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
	 * 功能:修改盘点结果
	 * @param result		盘点结果
	 * @return 
	 * @throws Exception
	 */
	public String updateCheckResult(InventoryCheckResult result) throws Exception {
		
		String strBackMsg = "Y";
		String taskid = result.getTaskid();	//盘点任务ID
		try{
			
			InventoryCheckTask task = getCheckTaskById(taskid);		//盘点任务
			InventoryCheckRequest req = getCheckReqById(task.getRequestid());	//盘点申请
			
			//判断盘点申请的状态，关闭后不能操作
			if(req.getStatus().equals("5")){	//状态 1:新建；2：生成盘点任务；3:正在盘点；4：未回流 5：完成
				
				strBackMsg = "该盘点申请已经完成，不能再修改盘点结果！";
			}else{
				inventoryCheckDAO.getEntityDAO().update(result);
			}
			
		}catch(Exception er){
        	Logger.error("对盘点任务：" + taskid + "修改盘点结果时出错！" + er.getMessage());
            throw new Exception("对盘点任务：" + taskid + "修改盘点结果时出错：" + er.getMessage());
        }
		return strBackMsg;
	}

	/**
	 * 功能:关闭盘点申请
	 * @param requestid		盘点申请ID
	 * @return 
	 * @throws Exception
	 */
	public String closeCheckTasks(String requestid) throws Exception {
		
		String strBackMsg = "Y";
		try{
			
			InventoryCheckRequest req = getCheckReqById(requestid);	//盘点申请
			
			//判断盘点申请的状态，关闭后不能操作
			if(req.getStatus().equals("3")){	//状态 1:新建；2：生成盘点任务；3:正在盘点；4：完成

				List lsTasks = getCheckTasks(requestid,"");	//盘点任务列表
				List lsStorages = new ArrayList();			//库存列表
				InventoryCheckTask task = null;		//盘点任务
				InventoryStorage storage = null;	//库存
				boolean flg = true;
				
				//判断盘点任务的状态是否全部完成
				for(int i=0; i<lsTasks.size(); i++){
					
					task = (InventoryCheckTask)lsTasks.get(i);
					storage = inventoryDAO.getInventoryById(task.getInventoryid());
					
					if(task.getStatus().equals("4")){
						storage.setStatus("0");	//状态  0:未分配，1：已分配 3:调整 4:货品调整；5：盘点
						lsStorages.add(storage);
						
					}else{
						flg = false;
						break;
					}
				}
				
				if(flg){
					
					req.setStatus("4");
					
					//关闭盘点申请,恢复库存状态为未分配
					inventoryCheckDAO.closeReq(req, lsStorages);
					
				}else{
					strBackMsg = "部分盘点任务未完成，不能关闭，请确认！";
				}
				
			}else{
				
				strBackMsg = "该盘点申请非【正在盘点】状态，不能关闭，请确认！";
			}
			
		}catch(Exception er){
        	Logger.error("关闭盘点申请单：" + requestid + "时出错！" + er.getMessage());
            throw new Exception("关闭盘点申请单：" + requestid + "时出错！" + er.getMessage());
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
			
			InventoryCheckTask task = getCheckTaskById(taskid);		//盘点任务
			InventoryCheckRequest req = getCheckReqById(task.getRequestid());	//盘点申请
			
			//判断盘点申请的状态，关闭后不能操作
			if(req.getStatus().equals("4")){	//状态 1:新建；2：生成盘点任务；3:正在盘点；4：完成
				
				cReturn.setRetInfo("该盘点申请已经完成，不能再修改盘点结果！");
				
			}else{
					
				//修改盘点任务的状态，只对新建的盘点任务生成盘点结果
				if(!task.getStatus().equals("4")&&!task.getStatus().equals("5")){ //状态1:新建；2：生成盘点任务 3:正在盘点 4：生成盘点结果  5：完成
					
					task.setStatus("4");//任务状态：生成盘点结果
					req.setStatus("3");//申请单状态：正在盘点
					
					//生成盘点结果
					InventoryCheckResult result = new InventoryCheckResult();
					result.setTaskid(taskid);	//盘点任务ID
					result.setLotnumber(task.getLotinfo());   //批号
					result.setProductid(task.getProductid());	//产品ID
					result.setTraycode(task.getTraycode());		//托盘条码
					result.setNum(task.getNum());				//库存数量
					result.setChecknum(Double.parseDouble(checknum));	//盘点数量
					result.setChecktime(StrTools.getCurrDateTime(2));	//盘点时间
					result.setCreatemanid(strUserCode);		//操作人
					result.setPrintdate(task.getPrintdate());//生产日期
					result.setPunit(task.getPunit());//单位
					result.setCargoSpaceId(task.getCargo_space_id());
					result.setInventoryid(task.getInventoryid());
					result.setRequestid(task.getRequestid());
					result.setStatus("1");//新建
					
					
					InventoryStorage storage = inventoryDAO.getInventoryById(task.getInventoryid());
					//判断库存状态是不是未分配
					storage.setStatus("2");	 //1：已分配，2:需调整， 3:调整 4:货品调整；5：盘点       (暂存区状态)
					
					
					//生成盘点结果，修改盘点任务和盘点申请的状态
					inventoryCheckDAO.addCheckResult(result, task, req,storage);
					
					List<InventoryCheckResult> lsResults = new ArrayList<InventoryCheckResult>();
					lsResults.add(result);
					cReturn.setLsReturn(lsResults);
			
				}else if (task.getStatus().equals("4")) {
					cReturn.setRetInfo("该盘点任务已新增结果，请更新结果！");
				}
				else{
					cReturn.setRetInfo("该盘点任务已经完成，请重新查询！");
				}
			}
			
		}catch(Exception er){
        	Logger.error("对盘点任务：" + taskid + "生成盘点结果时出错！" + er.getMessage());
            throw new Exception("对盘点任务：" + taskid + "生成盘点结果时出错：" + er.getMessage());
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
	     	Logger.error("根据条件查询盘点结果时出错！" + er.getMessage());
            throw new Exception("根据条件查询盘点结果时出错：" + er.getMessage());
     
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
	     	Logger.error("根据条件查询盘点结果时出错！" + er.getMessage());
            throw new Exception("根据条件查询盘点结果时出错：" + er.getMessage());
     
		}
		return cReturn;
	}
	
}