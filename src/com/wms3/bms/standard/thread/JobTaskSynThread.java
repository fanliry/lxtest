package com.wms3.bms.standard.thread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.allot.IAllotBus;
import com.wms3.bms.standard.business.allot.impl.AllotBusImpl;
import com.wms3.bms.standard.business.outbound.IOutBoundJobBus;
import com.wms3.bms.standard.business.outbound.impl.OutBoundJobBusImpl;
import com.wms3.bms.standard.business.scheduletasl.IIdxAsrsSend;
import com.wms3.bms.standard.business.scheduletasl.IScheduleTask;
import com.wms3.bms.standard.business.scheduletasl.impl.IdxAsrsSendImpl;
import com.wms3.bms.standard.business.scheduletasl.impl.ScheduleTaskImpl;
import com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.InventoryNeedcheck;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.schedule.IdxAsrsSend;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;
import com.wms3.bms.standard.service.BMSService;

/**
 * 描述：作业-调度任务 同步线程
 * @author yao
 *
 */
public class JobTaskSynThread  extends Thread{
	static private int m_iSleepTime;		//时间
	static private EntityDAO dao;
	static private SimpleDateFormat RetDateFm1 = new SimpleDateFormat("yyyyMMdd");
	static private SimpleDateFormat RetDateFm2 = new SimpleDateFormat("yyyy-MM-dd");
	static private SimpleDateFormat RetDateFm3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static private IScheduleTask scheduleTaskBus;
	static private IJobDao jobdao ;
	static private IOutBoundJobBus outBoundJobBus;
	static private IIdxAsrsSend idxAsrsSendBus;
	static private IBaseCargoSpaceDao baseCargoSpaceDao;
	static private IAllotBus allotBus;
	
	public JobTaskSynThread(){
		dao = BMSService.getm_EntityDAO();
		m_iSleepTime = 1000*5*1;		  //5秒
		scheduleTaskBus  = new ScheduleTaskImpl(dao);
		jobdao = new JobDaoImpl(dao);
		outBoundJobBus = new OutBoundJobBusImpl(dao);
		idxAsrsSendBus = new IdxAsrsSendImpl(dao);
		baseCargoSpaceDao = new BaseCargoSpaceDaoImpl(dao);
		allotBus = new AllotBusImpl(dao);
	}
	@SuppressWarnings("unchecked")
	public void run(){
		List<ScheduleTask> lsList = null,lsListS = null;
		List<IdxAsrsSend> lsSendList = null;
		ScheduleTask taskEntity = null,taskSEntity = null;
		IdxAsrsSend sendEntity = null;
		InoutJob job = null;
		String strBackMsg="";
		boolean disable = true;
		while(disable){
			try{
				lsList = scheduleTaskBus.getScheduleTaskByStatus("", "", "", "", "", "", "", "", "", "", "'4','5'", ""); //获取完成,作废 但没有同步到作业的调度任务
				if(lsList!=null && lsList.size()>0){
					for (int i = 0; i < lsList.size(); i++) {
						try {
							taskEntity = lsList.get(i);
							job = jobdao.getJobByTaskId(taskEntity.getTaskid());
							if(job!=null &&!job.getStatus().equals("4")&&!job.getStatus().equals("5")){//作业没有完成或者作废
								if(taskEntity.getStatus().equals("4")){ //任务完成同步
									if(job.getInOutType().equals("2")){//出库作业
										 strBackMsg = outBoundJobBus.finishJobs(job.getJobid(), "系统线程");
									}if(job.getInOutType().equals("1")){//入库或回流作业 job.getJobid()
										 strBackMsg = job.finishJobs(job.getJobid(), "系统线程", dao);
									}
									if(strBackMsg.equals("Y")){
										taskEntity.setStatus("6");
										dao.update("ScheduleTask",taskEntity);
										Logger.info("作业"+job.getJobid()+"完成同步,已经上下账," + strBackMsg);
									}else{
										Logger.error("作业"+job.getJobid()+"完成失败," + strBackMsg);
									}
								}else{ //任务作废同步
									if(job.getInOutType().equals("2")){//出库作业
										 strBackMsg = outBoundJobBus.cancelJobs(job.getJobid(), "系统线程");
									}if(job.getInOutType().equals("1")){//入库或回流作业
										 strBackMsg = job.cancelJobs(job.getJobid(), "系统线程", dao);
									}
									if(strBackMsg.equals("Y")){
										taskEntity.setStatus("8");
										dao.update("ScheduleTask",taskEntity);
										Logger.info("作业"+job.getJobid()+"作废同步," + strBackMsg);
									}else{
										Logger.error("作业"+job.getJobid()+"作废失败," + strBackMsg);
									}
								}
							 }else{
								 if(taskEntity.getStatus().equals("4")){
									 taskEntity.setStatus("6"); //同步完成
									 dao.update("ScheduleTask",taskEntity);
								 }else if(taskEntity.getStatus().equals("5")){
									 taskEntity.setStatus("8"); //同步作废
									 dao.update("ScheduleTask",taskEntity);
								 }
							 }
						} catch (Exception ex) {
							Logger.error("作业"+job.getJobid()+"同步异常!" + ex.getMessage());
						}
					}
				}
                
				lsSendList = idxAsrsSendBus.getIdxAsrsSends("", "", "", "'10','11','12','13'", "1", "", "", ""); //获取入库目标有货异常信息
				if(lsSendList!=null && lsSendList.size()>0){
					for (int i = 0; i < lsSendList.size(); i++) {
						try {
							sendEntity = lsSendList.get(i);
							if(sendEntity.getMessageCode()!=null && sendEntity.getMessageCode().equals("10")){ //入库目标有货
								lsListS = scheduleTaskBus.getScheduleTask(sendEntity.getTaskId(), "", "", "", "", "", "", "", "", "", "", "");
								if(lsListS!=null && lsListS.size()>0){
									taskSEntity = lsListS.get(0);
									BaseCargospace newSpace = allotBus.getNullSpaceId(taskSEntity.getWarehouseid(), taskSEntity.getWhAreaId(), sendEntity.getAsrsid());
									if(newSpace!=null){
										 //生成入库需盘点记录
                                        InventoryNeedcheck nc = new InventoryNeedcheck();
                                        nc.setJobid(taskSEntity.getJobid()); 
                                        nc.setTaskid(taskSEntity.getTaskid());//调度任务id
                                        nc.setStatus("0");
                                        nc.setWarehouseid(taskSEntity.getWarehouseid());
                                        nc.setInouttype("1");       //入出类型
                                        nc.setCargoSpaceId(taskSEntity.getCargoSpaceId());//货位
                                        nc.setCreatetime(StrTools.getCurrDateTime(14));  //生成时间
                                        nc.setHandleflag("N");      //处理标识
                                        nc.setHandlecontent("");    //处理内容
                                        nc.setTraycode(taskSEntity.getTraycode());//托盘条码
                                        nc.setTaskno(taskSEntity.getTaskno());    //任务号
                                        
                                        //入库需盘点
										BaseCargospace space = baseCargoSpaceDao.getCargoSpaceById(taskSEntity.getCargoSpaceId());
										space.setCsstatus("5");   
										
										//入库分配
										newSpace.setCsstatus("3");
										
										//修改异常状态
										sendEntity.setLocation(newSpace.getCargoSpaceId());
										sendEntity.setTaskStatus("2");
										
										addSendEx(sendEntity,nc,space, newSpace);
									}
								}
							}else if(sendEntity.getMessageCode()!=null && sendEntity.getMessageCode().equals("11")){ //11：升降重分巷道货位
								lsListS = scheduleTaskBus.getScheduleTask(sendEntity.getTaskId(), "", "", "", "", "", "", "", "", "", "", "");
								if(lsListS!=null && lsListS.size()>0){
									taskSEntity = lsListS.get(0);
									BaseCargospace newSpace = allotBus.getNullSpaceId(taskSEntity.getWarehouseid(), taskSEntity.getWhAreaId(), sendEntity.getAsrsid());
									if(newSpace!=null){
                                        //之前的货位设置为空货位
										BaseCargospace space = baseCargoSpaceDao.getCargoSpaceById(taskSEntity.getCargoSpaceId());
										space.setCsstatus("1");   
										//入库分配
										newSpace.setCsstatus("3");
										//修改异常状态
										sendEntity.setLocation(newSpace.getCargoSpaceId());
										sendEntity.setTaskStatus("2");
										
										addSendEx(sendEntity,null,space, newSpace);
									}
								}
							}else if(sendEntity.getMessageCode()!=null && sendEntity.getMessageCode().equals("12")){ //12：升降重分巷道货位
								lsListS = scheduleTaskBus.getScheduleTask(sendEntity.getTaskId(), "", "", "", "", "", "", "", "", "", "", "");
								if(lsListS!=null && lsListS.size()>0){
									taskSEntity = lsListS.get(0);
									BaseCargospace newSpace = allotBus.getNullSpaceId(taskSEntity.getWarehouseid(), taskSEntity.getWhAreaId(), sendEntity.getAsrsid());
									if(newSpace!=null){
                                        //之前的货位设置为空货位
										BaseCargospace space = baseCargoSpaceDao.getCargoSpaceById(taskSEntity.getCargoSpaceId());
										space.setCsstatus("1");   
										//入库分配
										newSpace.setCsstatus("3");
										//修改异常状态
										sendEntity.setLocation(newSpace.getCargoSpaceId());
										sendEntity.setTaskStatus("2");
										
										addSendEx(sendEntity,null,space, newSpace);
									}
								}
							}else if(sendEntity.getMessageCode()!=null && sendEntity.getMessageCode().equals("13")){ //13：巷道里输送机堆垛机入库重分货位
								lsListS = scheduleTaskBus.getScheduleTask(sendEntity.getTaskId(), "", "", "", "", "", "", "", "", "", "", "");
								if(lsListS!=null && lsListS.size()>0){
									taskSEntity = lsListS.get(0);
									BaseCargospace newSpace = allotBus.getNullSpaceId(taskSEntity.getWarehouseid(), taskSEntity.getWhAreaId(), sendEntity.getAsrsid());
									if(newSpace!=null){
                                        //之前的货位设置为空货位
										BaseCargospace space = baseCargoSpaceDao.getCargoSpaceById(taskSEntity.getCargoSpaceId());
										space.setCsstatus("1");   
										//入库分配
										newSpace.setCsstatus("3");
										//修改异常状态
										sendEntity.setLocation(newSpace.getCargoSpaceId());
										sendEntity.setTaskStatus("2");
										
										addSendEx(sendEntity,null,space, newSpace);
									}
								}
							}
						} catch (Exception ex) {
							Logger.error("堆垛机异常!" + ex.getMessage());
						}
					}
				}
				
			}
			catch(Exception er){
				Logger.error("作业-调度任务-入库占货处理 异常："+er.getMessage());
			}
			finally{
				try {sleep(m_iSleepTime);} catch (InterruptedException e) {}
			}
		}
	}
	/**
	 * 入库目标有货处理
	 * @param send
	 * @param nc
	 * @param space
	 * @param newspace
	 * @param dao
	 * @throws Exception
	 */
	public void addSendEx(IdxAsrsSend send,InventoryNeedcheck nc,BaseCargospace space, BaseCargospace newspace) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = dao.getSession();
            tx = session.beginTransaction();
            if(send!=null){
                session.update(send);	
            }
            if(nc!=null){
            	session.save(nc);
            }
            if(space!=null){
            	session.update(space);
            }
            if(newspace!=null){
            	session.update(newspace);
            }
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("入库目标有货处理时候出错："+er.getMessage());
        }finally{
        	dao.closeSession(session);
        }
	}
}