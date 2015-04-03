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
 * ��������ҵ-�������� ͬ���߳�
 * @author yao
 *
 */
public class JobTaskSynThread  extends Thread{
	static private int m_iSleepTime;		//ʱ��
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
		m_iSleepTime = 1000*5*1;		  //5��
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
				lsList = scheduleTaskBus.getScheduleTaskByStatus("", "", "", "", "", "", "", "", "", "", "'4','5'", ""); //��ȡ���,���� ��û��ͬ������ҵ�ĵ�������
				if(lsList!=null && lsList.size()>0){
					for (int i = 0; i < lsList.size(); i++) {
						try {
							taskEntity = lsList.get(i);
							job = jobdao.getJobByTaskId(taskEntity.getTaskid());
							if(job!=null &&!job.getStatus().equals("4")&&!job.getStatus().equals("5")){//��ҵû����ɻ�������
								if(taskEntity.getStatus().equals("4")){ //�������ͬ��
									if(job.getInOutType().equals("2")){//������ҵ
										 strBackMsg = outBoundJobBus.finishJobs(job.getJobid(), "ϵͳ�߳�");
									}if(job.getInOutType().equals("1")){//���������ҵ job.getJobid()
										 strBackMsg = job.finishJobs(job.getJobid(), "ϵͳ�߳�", dao);
									}
									if(strBackMsg.equals("Y")){
										taskEntity.setStatus("6");
										dao.update("ScheduleTask",taskEntity);
										Logger.info("��ҵ"+job.getJobid()+"���ͬ��,�Ѿ�������," + strBackMsg);
									}else{
										Logger.error("��ҵ"+job.getJobid()+"���ʧ��," + strBackMsg);
									}
								}else{ //��������ͬ��
									if(job.getInOutType().equals("2")){//������ҵ
										 strBackMsg = outBoundJobBus.cancelJobs(job.getJobid(), "ϵͳ�߳�");
									}if(job.getInOutType().equals("1")){//���������ҵ
										 strBackMsg = job.cancelJobs(job.getJobid(), "ϵͳ�߳�", dao);
									}
									if(strBackMsg.equals("Y")){
										taskEntity.setStatus("8");
										dao.update("ScheduleTask",taskEntity);
										Logger.info("��ҵ"+job.getJobid()+"����ͬ��," + strBackMsg);
									}else{
										Logger.error("��ҵ"+job.getJobid()+"����ʧ��," + strBackMsg);
									}
								}
							 }else{
								 if(taskEntity.getStatus().equals("4")){
									 taskEntity.setStatus("6"); //ͬ�����
									 dao.update("ScheduleTask",taskEntity);
								 }else if(taskEntity.getStatus().equals("5")){
									 taskEntity.setStatus("8"); //ͬ������
									 dao.update("ScheduleTask",taskEntity);
								 }
							 }
						} catch (Exception ex) {
							Logger.error("��ҵ"+job.getJobid()+"ͬ���쳣!" + ex.getMessage());
						}
					}
				}
                
				lsSendList = idxAsrsSendBus.getIdxAsrsSends("", "", "", "'10','11','12','13'", "1", "", "", ""); //��ȡ���Ŀ���л��쳣��Ϣ
				if(lsSendList!=null && lsSendList.size()>0){
					for (int i = 0; i < lsSendList.size(); i++) {
						try {
							sendEntity = lsSendList.get(i);
							if(sendEntity.getMessageCode()!=null && sendEntity.getMessageCode().equals("10")){ //���Ŀ���л�
								lsListS = scheduleTaskBus.getScheduleTask(sendEntity.getTaskId(), "", "", "", "", "", "", "", "", "", "", "");
								if(lsListS!=null && lsListS.size()>0){
									taskSEntity = lsListS.get(0);
									BaseCargospace newSpace = allotBus.getNullSpaceId(taskSEntity.getWarehouseid(), taskSEntity.getWhAreaId(), sendEntity.getAsrsid());
									if(newSpace!=null){
										 //����������̵��¼
                                        InventoryNeedcheck nc = new InventoryNeedcheck();
                                        nc.setJobid(taskSEntity.getJobid()); 
                                        nc.setTaskid(taskSEntity.getTaskid());//��������id
                                        nc.setStatus("0");
                                        nc.setWarehouseid(taskSEntity.getWarehouseid());
                                        nc.setInouttype("1");       //�������
                                        nc.setCargoSpaceId(taskSEntity.getCargoSpaceId());//��λ
                                        nc.setCreatetime(StrTools.getCurrDateTime(14));  //����ʱ��
                                        nc.setHandleflag("N");      //�����ʶ
                                        nc.setHandlecontent("");    //��������
                                        nc.setTraycode(taskSEntity.getTraycode());//��������
                                        nc.setTaskno(taskSEntity.getTaskno());    //�����
                                        
                                        //������̵�
										BaseCargospace space = baseCargoSpaceDao.getCargoSpaceById(taskSEntity.getCargoSpaceId());
										space.setCsstatus("5");   
										
										//������
										newSpace.setCsstatus("3");
										
										//�޸��쳣״̬
										sendEntity.setLocation(newSpace.getCargoSpaceId());
										sendEntity.setTaskStatus("2");
										
										addSendEx(sendEntity,nc,space, newSpace);
									}
								}
							}else if(sendEntity.getMessageCode()!=null && sendEntity.getMessageCode().equals("11")){ //11�������ط������λ
								lsListS = scheduleTaskBus.getScheduleTask(sendEntity.getTaskId(), "", "", "", "", "", "", "", "", "", "", "");
								if(lsListS!=null && lsListS.size()>0){
									taskSEntity = lsListS.get(0);
									BaseCargospace newSpace = allotBus.getNullSpaceId(taskSEntity.getWarehouseid(), taskSEntity.getWhAreaId(), sendEntity.getAsrsid());
									if(newSpace!=null){
                                        //֮ǰ�Ļ�λ����Ϊ�ջ�λ
										BaseCargospace space = baseCargoSpaceDao.getCargoSpaceById(taskSEntity.getCargoSpaceId());
										space.setCsstatus("1");   
										//������
										newSpace.setCsstatus("3");
										//�޸��쳣״̬
										sendEntity.setLocation(newSpace.getCargoSpaceId());
										sendEntity.setTaskStatus("2");
										
										addSendEx(sendEntity,null,space, newSpace);
									}
								}
							}else if(sendEntity.getMessageCode()!=null && sendEntity.getMessageCode().equals("12")){ //12�������ط������λ
								lsListS = scheduleTaskBus.getScheduleTask(sendEntity.getTaskId(), "", "", "", "", "", "", "", "", "", "", "");
								if(lsListS!=null && lsListS.size()>0){
									taskSEntity = lsListS.get(0);
									BaseCargospace newSpace = allotBus.getNullSpaceId(taskSEntity.getWarehouseid(), taskSEntity.getWhAreaId(), sendEntity.getAsrsid());
									if(newSpace!=null){
                                        //֮ǰ�Ļ�λ����Ϊ�ջ�λ
										BaseCargospace space = baseCargoSpaceDao.getCargoSpaceById(taskSEntity.getCargoSpaceId());
										space.setCsstatus("1");   
										//������
										newSpace.setCsstatus("3");
										//�޸��쳣״̬
										sendEntity.setLocation(newSpace.getCargoSpaceId());
										sendEntity.setTaskStatus("2");
										
										addSendEx(sendEntity,null,space, newSpace);
									}
								}
							}else if(sendEntity.getMessageCode()!=null && sendEntity.getMessageCode().equals("13")){ //13����������ͻ��Ѷ������طֻ�λ
								lsListS = scheduleTaskBus.getScheduleTask(sendEntity.getTaskId(), "", "", "", "", "", "", "", "", "", "", "");
								if(lsListS!=null && lsListS.size()>0){
									taskSEntity = lsListS.get(0);
									BaseCargospace newSpace = allotBus.getNullSpaceId(taskSEntity.getWarehouseid(), taskSEntity.getWhAreaId(), sendEntity.getAsrsid());
									if(newSpace!=null){
                                        //֮ǰ�Ļ�λ����Ϊ�ջ�λ
										BaseCargospace space = baseCargoSpaceDao.getCargoSpaceById(taskSEntity.getCargoSpaceId());
										space.setCsstatus("1");   
										//������
										newSpace.setCsstatus("3");
										//�޸��쳣״̬
										sendEntity.setLocation(newSpace.getCargoSpaceId());
										sendEntity.setTaskStatus("2");
										
										addSendEx(sendEntity,null,space, newSpace);
									}
								}
							}
						} catch (Exception ex) {
							Logger.error("�Ѷ���쳣!" + ex.getMessage());
						}
					}
				}
				
			}
			catch(Exception er){
				Logger.error("��ҵ-��������-���ռ������ �쳣��"+er.getMessage());
			}
			finally{
				try {sleep(m_iSleepTime);} catch (InterruptedException e) {}
			}
		}
	}
	/**
	 * ���Ŀ���л�����
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
            throw new  Exception("���Ŀ���л�����ʱ�����"+er.getMessage());
        }finally{
        	dao.closeSession(session);
        }
	}
}