package com.wms3.bms.standard.business.job.impl;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.allot.impl.AllotServiceBusImpl;
import com.wms3.bms.standard.business.allot.impl.InAllotRequestBean;
import com.wms3.bms.standard.business.allot.impl.InAllotResponseBean;
import com.wms3.bms.standard.business.base.ICargoSpaceBus;
import com.wms3.bms.standard.business.base.impl.CargoSpaceBusImpl;
import com.wms3.bms.standard.business.job.IJobBus;
import com.wms3.bms.standard.dao.inbound.IPutawayDao;
import com.wms3.bms.standard.dao.inbound.impl.PutawayDaoImpl;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * ����: ��ҵҵ�������
 * @author hug 2012-9-4
 * @since WMS 3.0
 */
public class JobBusImpl implements IJobBus {
    
    protected IJobDao jobDao; //��ҵDAO����
    protected EntityDAO m_dao = null;
    protected AllotServiceBusImpl allotBus;
    /** �ϼܲ���DAO�� */
    protected IPutawayDao putawayDao;
    public JobBusImpl(EntityDAO dao){
    	m_dao = dao;
        jobDao = new JobDaoImpl(dao);
        putawayDao = new PutawayDaoImpl(dao);
        this.allotBus = new AllotServiceBusImpl(dao);
    }
    /**
     * ������ҵ��ֻ��һ����ҵ��ϸ��
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.job.IJobBus#addOneJob(com.wms3.bms.standard.entity.job.InoutJob, com.wms3.bms.standard.entity.job.InoutJobdetail)
     */
    public String addOneJob(InoutJob job, InoutJobdetail jobDetail) throws Exception {
        String strMsg = "�����ɹ���";
        synchronized (job_lock) {
            //��ȡ��λ*************************************
            //�������������
            InAllotRequestBean inRequest = new InAllotRequestBean();
            inRequest.setWarehouseid(job.getWarehouseid());//�ֿ�ID
            inRequest.setWhAreaId(job.getTcargoWhareaId());//����ID
            inRequest.setPutmode("PL");              //�ϼܷ�ʽ PL-����, CS-��, EA-��  (Ϊ����ʱ�ͷ���һ����λ)
            inRequest.setITrays(1);                  //��������(����ʱʹ�ã�ƽ���ϼܾ�Ϊ1)
            inRequest.setTrayCode(job.getTraycode());//��������(Ϊ����ʱ����)
            
            //�ϼܵ���Ϣ     
            InAllotRequestBean.ProductBean productBean = inRequest.getNewProductBean();
            //productBean.setStrTransId(strTransId);//�ջ���¼ID�����ٺţ�
            productBean.setProductid(jobDetail.getProductid()); //��Ʒ
            productBean.setPackid(jobDetail.getPackid());       //��װ
            productBean.setEunit(jobDetail.getPunit());         //��λ
            productBean.setPutnum(jobDetail.getJobnum());             //�ϼ�����
            productBean.setPutweight(jobDetail.getWeight());       //�ϼ�����
            productBean.setPutnetweight(jobDetail.getNetweight()); //�ϼܾ���
            productBean.setPutvolume(jobDetail.getVolume());       //�ϼ���� 
            productBean.setLotid(jobDetail.getLotid());//��������ID
            productBean.setOwnerid(jobDetail.getOwnerId());//����  
            //productBean.setTransStatus(strStatus);//Ҫ�޸ĵ��ջ����׼�¼״̬  
            //productBean.setReinvoiceid(trans.getReinvoiceid());//�ջ����ݱ��
            //productBean.setReinvoicedetailid(trans.getReinvoicedetailid());//�ջ�����ϸID
            inRequest.addProductBean(productBean);
            
            //���ػ�λ****************************************
            List<InAllotResponseBean> lsResponse = allotBus.inAllotEntry(inRequest);
            
            if(lsResponse != null && lsResponse.size()>0){
                //��������Ӧ����
                InAllotResponseBean inResponseBean = lsResponse.get(0); 
                try
                {
                    job.setTcargoSpaceId(inResponseBean.getCargoSpaceId()); //Ŀ���λID
                    job.setTcargoAlleyId(inResponseBean.getCargoAlleyId()); //Ŀ�����ID
                    job.setTcargoWhareaId(inResponseBean.getWhAreaId());    //Ŀ�����ID
                    job.setOldspacestatus(inResponseBean.getOldstatus());   //����ǰ��λ״̬
                    
                    //���ݿ���ID�ж��Ƿ���Ҫ���ɵ�������
                    boolean bTask = putawayDao.isCreateTask(inResponseBean.getWhAreaId());
                    //��������
                    ScheduleTask task = null;
                    if(bTask){ //��Ҫ���ɵ�������
                        task = new ScheduleTask();
                        String strTaskId = SeqTool.getID("RW", "D", putawayDao.getEntityDAO()); //����ID
                        task.setTaskid(strTaskId);//taskid;          ����������
                        job.setTaskid(strTaskId); //��������ID
                        task.setTasktype("1");        //�������� 1-��� 2-���� 3-�ƿ�
                        task.setTplatoon(inResponseBean.getCsplatoon());       //Ŀ���λ��
                        task.setTcolumn(inResponseBean.getCscolumn());         //Ŀ���λ��
                        task.setTfloor(inResponseBean.getCsfloor());           //Ŀ���λ��   
                        task.setCargoSpaceId(inResponseBean.getCargoSpaceId());// ��λID
                        task.setCargoAlleyId(inResponseBean.getCargoAlleyId());// ���ID
                        task.setWarehouseid(inResponseBean.getWarehouseid());  // �ֿ�ID
                        task.setWhAreaId(inResponseBean.getWhAreaId());        // ����ID  
                        task.setStatus("1");          //����״̬
                        task.setPriority(10);         //���ȼ�
                        task.setTaskpos("1");         //������ 1.���� 2.�����
                        task.setCreatetime(StrTools.getCurrDateTime(2));      //ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
                        //���ͺ�
                        //�����
                        //ָ���ʽ
                        task.setTraycode(job.getTraycode()); //��������
                        task.setRoute("1");            //ִ��·�� 1-ֱ��/ֱ�� 2-����
                             
                    } 
                    jobDao.addOneJob(job, jobDetail, task);
                }catch(Exception er){
                    //������ҵʧ�ܣ�Ҫ��ԭ��λ����ǰ��״̬
                    String strSpaceSQL = "update BaseCargospace space set space.csstatus='" + inResponseBean.getOldstatus() + "', space.haspalletnum=space.haspalletnum-1 where  space.cargoSpaceId='" + inResponseBean.getCargoSpaceId() + "'";
                    putawayDao.excuteSQL(strSpaceSQL);    
                    throw new Exception("���������ҵʧ�ܣ�" + er.getMessage());
                } 
            }else{
                strMsg = "�����λʧ�ܣ�";
            }  
        }
        return strMsg;
    }
   
    
    public String getJobDetailByTraycode(String traycode,String warehouseID) throws Exception{
    	String strBackMsg = "";
    	try{
    		strBackMsg = jobDao.getJobDetailByTraycode(traycode,warehouseID);
    	}catch(Exception er){
    		throw new Exception("������������["+ traycode +"]���ֱ�ӳ�����Ϣ����" + er.getMessage());
    	}
    	
    	return strBackMsg;
    }
    
    public String GetZCHLInventoryByTraycode(String traycode,String warehouseID) throws Exception{
    	String strBackMsg = "";
    	try{
    		strBackMsg = jobDao.GetZCHLInventoryByTraycode(traycode,warehouseID);
    	}catch(Exception er){
    		throw new Exception("������������["+ traycode +"]���ֱ�ӳ�����Ϣ����" + er.getMessage());
    	}
    	
    	return strBackMsg;
    }
    
    public String GetJobInfoByTraycode(String traycode) throws Exception{
    	String strBackMsg = "";
    	try{
    		strBackMsg = jobDao.GetJobInfoByTraycode(traycode);
    	}catch(Exception er){
    		throw new Exception("������������["+ traycode +"]�����Ӧ��ҵ��Ϣ����" + er.getMessage());
    	}
    	
    	return strBackMsg;
    }
    
    public List getJobDetailByInvoiceid(String invoiceid) throws Exception{
    	return jobDao.getJobDetailByInvoiceid(invoiceid);
    }
    
    
    public InoutJob createInoutJob(String strNo1,String strUserCode,String strTime,String strtoWhAreaId,String strtoCargospaceId,String warehouseid,InventoryStorage inStroStorage) throws Exception{
    	InoutJob inoutJob = new InoutJob();
		ICargoSpaceBus iCargo = new CargoSpaceBusImpl(m_dao);
		//���ݿ���ID�ж��Ƿ���Ҫ���ɵ�������
        boolean bTask = jobDao.isCreateTask(inStroStorage.getWhAreaId());
		try {
			inoutJob.setJobid(strNo1);
			inoutJob.setJobtype("13"); //��������--��13�����ƿ���⣬
			if(bTask){//���ɵ�����ҵ
				inoutJob.setOnLineType("1");//����ģʽ 1.���� 2.�ѻ�
			}else{
				inoutJob.setOnLineType("2");
			}
			
			inoutJob.setStatus("1");//��ҵ״̬
			
			inoutJob.setIsaccount("Y");//�Ƿ����
			
			inoutJob.setPriority(1); //���ȼ�---���δִ����ҵ��Ĭ��Ϊ��1��
			
			inoutJob.setCreatetime(strTime);//����ʱ��
			inoutJob.setJobpos("2");//��ҵ����1.����2.�����
			inoutJob.setTraycode(inStroStorage.getTraycode());//��������
			inoutJob.setOpMan(strUserCode);//������
			
			inoutJob.setInOutType("2");//�������1.�ϼ����2.����
			inoutJob.setInvoicetype("4");//����ҵ��Դ��1��������ջ��������ɵ���ҵ��2�������ⵥ���ɵ���ҵ��3�� ����ֱ�����ɵ���ҵ�����ݴ浽����⣩ ��4��������ƿ���ҵ������⵽�ݴ棩
			
			inoutJob.setRoute("1");//ִ��·��1-ֱ��/ֱ��2-�����������ջ������Ϊ ֱ�룬�����Ϊֱ�� ���ݴ浽ת���������ǻ�����
			
			if(bTask){//���ɵ�������
				inoutJob.setJobcategory("1");//��ҵ��� 1:�Զ����������ҵ 2:�ݴ�����ҵ
			}else{
				inoutJob.setJobcategory("2");
			}
			
			
			inoutJob.setWarehouseid(inStroStorage.getWarehouseid());
			inoutJob.setScargoWhareaId(inStroStorage.getWhAreaId());
			inoutJob.setScargoSpaceId(inStroStorage.getCargoSpaceId());
			inoutJob.setScargoAlleyId(inStroStorage.getCargoAlleyId());
			
			inoutJob.setTcargoWhareaId(strtoWhAreaId);
			inoutJob.setTcargoSpaceId(strtoCargospaceId);
			
		} catch (Exception er) {
			er.printStackTrace();
			Logger.error("������ҵʵ����ҵ����"+er.getMessage());
		}
		return inoutJob;
		
    }
    
    public InoutJobdetail createInoutJobDetail(String strNo1,String strUserCode,String strTime,String strtoWhAreaId,String strtoCargospaceId,String warehouseid,InventoryStorage inStroStorage) throws Exception{
    	InoutJobdetail inoutJobDetail = new InoutJobdetail();
		ICargoSpaceBus iCargo = new CargoSpaceBusImpl(m_dao);
		//���ݿ���ID�ж��Ƿ���Ҫ���ɵ�������
        boolean bTask = jobDao.isCreateTask(inStroStorage.getWhAreaId());
		try {
			//=============������ҵ��ϸ=============
			inoutJobDetail.setJobid(strNo1);//��ҵ���
			
			inoutJobDetail.setLinestatus("0");//״̬��//״̬0:�½� 4.��� 5.���ϣ���ʱ���ã�
			
			inoutJobDetail.setInventoryid(inStroStorage.getInventoryid());//���ID
			inoutJobDetail.setProductid(inStroStorage.getProductid());	//��ƷID
			inoutJobDetail.setPackid(inStroStorage.getPackid());//��װid
			inoutJobDetail.setLotid(inStroStorage.getLotid());//��������
			inoutJobDetail.setLotinfo(inStroStorage.getLotinfo());//��������
			inoutJobDetail.setPrintdate(inStroStorage.getProductdate());//��������
			inoutJobDetail.setProductStatus(inStroStorage.getProductstatus());//��Ʒ״̬
			inoutJobDetail.setProductcode(inStroStorage.getProductcode());//��Ʒ����
			
			if(bTask){//�����
				inoutJobDetail.setJobnum(inStroStorage.getPnum());
			}
//			else{//�ݴ���
//				inoutJobDetail.setJobnum(inStroStorage.getPnum()-inStroStorage.getAssignnum());
//			}
			
			inoutJobDetail.setNetweight(inStroStorage.getPnetweight());//����
			inoutJobDetail.setWeight(inStroStorage.getPweight());//ë��
			inoutJobDetail.setVolume(inStroStorage.getPvolume());//���
			inoutJobDetail.setAssignnum(inStroStorage.getPnum());//��������
			inoutJobDetail.setPunit(inStroStorage.getPunit());//��λ
			inoutJobDetail.setAssignnetweight(inStroStorage.getAssignnetweight()); //���侻��
			inoutJobDetail.setAssignweight(inStroStorage.getAssignweight());       //����ë��
			inoutJobDetail.setAssignvolume(inStroStorage.getAssignvolume());       //�������
			inoutJobDetail.setIsplit("1");//�Ƿ��֣�1������ 2���ֲ�������� ������� Ϊ��������assignnum
			inoutJobDetail.setIsinvoice("Y");//�Ƿ������ɵ��� Y��  N��
			inoutJobDetail.setInjobdetailid(inStroStorage.getInjobetailid());	//Դ��ҵ��ϸ�����ɳ�����ҵʱ����Ӹÿ��������ҵ��
//			inoutJobDetail.setTransreceiptid(iStorage.get)//�ջ���¼ID��
			
		} catch (Exception er) {
			er.printStackTrace();
			Logger.error("������ҵ��ϸʵ����ҵ����"+er.getMessage());
		}
		return inoutJobDetail;
		
    }
}
