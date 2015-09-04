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
 * 描述: 作业业务类操作
 * @author hug 2012-9-4
 * @since WMS 3.0
 */
public class JobBusImpl implements IJobBus {
    
    protected IJobDao jobDao; //作业DAO类型
    protected EntityDAO m_dao = null;
    protected AllotServiceBusImpl allotBus;
    /** 上架操作DAO类 */
    protected IPutawayDao putawayDao;
    public JobBusImpl(EntityDAO dao){
    	m_dao = dao;
        jobDao = new JobDaoImpl(dao);
        putawayDao = new PutawayDaoImpl(dao);
        this.allotBus = new AllotServiceBusImpl(dao);
    }
    /**
     * 增加作业（只有一条作业详细）
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.job.IJobBus#addOneJob(com.wms3.bms.standard.entity.job.InoutJob, com.wms3.bms.standard.entity.job.InoutJobdetail)
     */
    public String addOneJob(InoutJob job, InoutJobdetail jobDetail) throws Exception {
        String strMsg = "操作成功！";
        synchronized (job_lock) {
            //获取货位*************************************
            //入库分配请求对象
            InAllotRequestBean inRequest = new InAllotRequestBean();
            inRequest.setWarehouseid(job.getWarehouseid());//仓库ID
            inRequest.setWhAreaId(job.getTcargoWhareaId());//库区ID
            inRequest.setPutmode("PL");              //上架方式 PL-托盘, CS-箱, EA-件  (为托盘时就返回一个货位)
            inRequest.setITrays(1);                  //托盘数量(码盘时使用，平常上架就为1)
            inRequest.setTrayCode(job.getTraycode());//托盘条码(为托盘时才能)
            
            //上架的信息     
            InAllotRequestBean.ProductBean productBean = inRequest.getNewProductBean();
            //productBean.setStrTransId(strTransId);//收货记录ID（跟踪号）
            productBean.setProductid(jobDetail.getProductid()); //产品
            productBean.setPackid(jobDetail.getPackid());       //包装
            productBean.setEunit(jobDetail.getPunit());         //单位
            productBean.setPutnum(jobDetail.getJobnum());             //上架数量
            productBean.setPutweight(jobDetail.getWeight());       //上架重量
            productBean.setPutnetweight(jobDetail.getNetweight()); //上架净重
            productBean.setPutvolume(jobDetail.getVolume());       //上架体积 
            productBean.setLotid(jobDetail.getLotid());//批次类型ID
            productBean.setOwnerid(jobDetail.getOwnerId());//货主  
            //productBean.setTransStatus(strStatus);//要修改的收货交易记录状态  
            //productBean.setReinvoiceid(trans.getReinvoiceid());//收货单据编号
            //productBean.setReinvoicedetailid(trans.getReinvoicedetailid());//收货单详细ID
            inRequest.addProductBean(productBean);
            
            //返回货位****************************************
            List<InAllotResponseBean> lsResponse = allotBus.inAllotEntry(inRequest);
            
            if(lsResponse != null && lsResponse.size()>0){
                //入库分配响应对象
                InAllotResponseBean inResponseBean = lsResponse.get(0); 
                try
                {
                    job.setTcargoSpaceId(inResponseBean.getCargoSpaceId()); //目标货位ID
                    job.setTcargoAlleyId(inResponseBean.getCargoAlleyId()); //目标巷道ID
                    job.setTcargoWhareaId(inResponseBean.getWhAreaId());    //目标库区ID
                    job.setOldspacestatus(inResponseBean.getOldstatus());   //分配前货位状态
                    
                    //根据库区ID判断是否需要生成调度任务
                    boolean bTask = putawayDao.isCreateTask(inResponseBean.getWhAreaId());
                    //调度任务
                    ScheduleTask task = null;
                    if(bTask){ //需要生成调度任务
                        task = new ScheduleTask();
                        String strTaskId = SeqTool.getID("RW", "D", putawayDao.getEntityDAO()); //任务ID
                        task.setTaskid(strTaskId);//taskid;          调度任务编号
                        job.setTaskid(strTaskId); //调度任务ID
                        task.setTasktype("1");        //调度类型 1-入库 2-出库 3-移库
                        task.setTplatoon(inResponseBean.getCsplatoon());       //目标货位排
                        task.setTcolumn(inResponseBean.getCscolumn());         //目标货位列
                        task.setTfloor(inResponseBean.getCsfloor());           //目标货位层   
                        task.setCargoSpaceId(inResponseBean.getCargoSpaceId());// 货位ID
                        task.setCargoAlleyId(inResponseBean.getCargoAlleyId());// 巷道ID
                        task.setWarehouseid(inResponseBean.getWarehouseid());  // 仓库ID
                        task.setWhAreaId(inResponseBean.getWhAreaId());        // 库区ID  
                        task.setStatus("1");          //任务状态
                        task.setPriority(10);         //优先级
                        task.setTaskpos("1");         //任务方向 1.入库端 2.出库端
                        task.setCreatetime(StrTools.getCurrDateTime(2));      //时间 时间格式：yyyy-MM-dd hh:mm:ss
                        //输送号
                        //任务号
                        //指令格式
                        task.setTraycode(job.getTraycode()); //托盘条码
                        task.setRoute("1");            //执行路线 1-直入/直出 2-回流
                             
                    } 
                    jobDao.addOneJob(job, jobDetail, task);
                }catch(Exception er){
                    //生成作业失败，要还原货位分配前的状态
                    String strSpaceSQL = "update BaseCargospace space set space.csstatus='" + inResponseBean.getOldstatus() + "', space.haspalletnum=space.haspalletnum-1 where  space.cargoSpaceId='" + inResponseBean.getCargoSpaceId() + "'";
                    putawayDao.excuteSQL(strSpaceSQL);    
                    throw new Exception("生成入库作业失败：" + er.getMessage());
                } 
            }else{
                strMsg = "分配货位失败！";
            }  
        }
        return strMsg;
    }
   
    
    public String getJobDetailByTraycode(String traycode,String warehouseID) throws Exception{
    	String strBackMsg = "";
    	try{
    		strBackMsg = jobDao.getJobDetailByTraycode(traycode,warehouseID);
    	}catch(Exception er){
    		throw new Exception("根据托盘条码["+ traycode +"]获得直接出库信息出错：" + er.getMessage());
    	}
    	
    	return strBackMsg;
    }
    
    public String GetZCHLInventoryByTraycode(String traycode,String warehouseID) throws Exception{
    	String strBackMsg = "";
    	try{
    		strBackMsg = jobDao.GetZCHLInventoryByTraycode(traycode,warehouseID);
    	}catch(Exception er){
    		throw new Exception("根据托盘条码["+ traycode +"]获得直接出库信息出错：" + er.getMessage());
    	}
    	
    	return strBackMsg;
    }
    
    public String GetJobInfoByTraycode(String traycode) throws Exception{
    	String strBackMsg = "";
    	try{
    		strBackMsg = jobDao.GetJobInfoByTraycode(traycode);
    	}catch(Exception er){
    		throw new Exception("根据托盘条码["+ traycode +"]获得相应作业信息出错：" + er.getMessage());
    	}
    	
    	return strBackMsg;
    }
    
    public List getJobDetailByInvoiceid(String invoiceid) throws Exception{
    	return jobDao.getJobDetailByInvoiceid(invoiceid);
    }
    
    
    public InoutJob createInoutJob(String strNo1,String strUserCode,String strTime,String strtoWhAreaId,String strtoCargospaceId,String warehouseid,InventoryStorage inStroStorage) throws Exception{
    	InoutJob inoutJob = new InoutJob();
		ICargoSpaceBus iCargo = new CargoSpaceBusImpl(m_dao);
		//根据库区ID判断是否需要生成调度任务
        boolean bTask = jobDao.isCreateTask(inStroStorage.getWhAreaId());
		try {
			inoutJob.setJobid(strNo1);
			inoutJob.setJobtype("13"); //出库类型--“13”：移库出库，
			if(bTask){//生成调度作业
				inoutJob.setOnLineType("1");//联机模式 1.联机 2.脱机
			}else{
				inoutJob.setOnLineType("2");
			}
			
			inoutJob.setStatus("1");//作业状态
			
			inoutJob.setIsaccount("Y");//是否记账
			
			inoutJob.setPriority(1); //优先级---针对未执行作业，默认为“1”
			
			inoutJob.setCreatetime(strTime);//生成时间
			inoutJob.setJobpos("2");//作业方向1.入库端2.出库端
			inoutJob.setTraycode(inStroStorage.getTraycode());//托盘条码
			inoutJob.setOpMan(strUserCode);//操作人
			
			inoutJob.setInOutType("2");//入出类型1.上架入库2.出库
			inoutJob.setInvoicetype("4");//【作业来源】1：按入库收货单来生成的作业，2：按出库单生成的作业，3： 回流直接生成的作业（从暂存到立体库） ，4：立体库移库作业（立体库到暂存）
			
			inoutJob.setRoute("1");//执行路线1-直入/直出2-回流（根据收货单入的为 直入，出库的为直出 ，暂存到转向立体库就是回流）
			
			if(bTask){//生成调度任务
				inoutJob.setJobcategory("1");//作业类别 1:自动化立体库作业 2:暂存区作业
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
			Logger.error("创建作业实例作业出错！"+er.getMessage());
		}
		return inoutJob;
		
    }
    
    public InoutJobdetail createInoutJobDetail(String strNo1,String strUserCode,String strTime,String strtoWhAreaId,String strtoCargospaceId,String warehouseid,InventoryStorage inStroStorage) throws Exception{
    	InoutJobdetail inoutJobDetail = new InoutJobdetail();
		ICargoSpaceBus iCargo = new CargoSpaceBusImpl(m_dao);
		//根据库区ID判断是否需要生成调度任务
        boolean bTask = jobDao.isCreateTask(inStroStorage.getWhAreaId());
		try {
			//=============增加作业明细=============
			inoutJobDetail.setJobid(strNo1);//作业编号
			
			inoutJobDetail.setLinestatus("0");//状态：//状态0:新建 4.完成 5.作废（暂时不用）
			
			inoutJobDetail.setInventoryid(inStroStorage.getInventoryid());//库存ID
			inoutJobDetail.setProductid(inStroStorage.getProductid());	//物品ID
			inoutJobDetail.setPackid(inStroStorage.getPackid());//包装id
			inoutJobDetail.setLotid(inStroStorage.getLotid());//批号类型
			inoutJobDetail.setLotinfo(inStroStorage.getLotinfo());//具体批号
			inoutJobDetail.setPrintdate(inStroStorage.getProductdate());//生产日期
			inoutJobDetail.setProductStatus(inStroStorage.getProductstatus());//物品状态
			inoutJobDetail.setProductcode(inStroStorage.getProductcode());//产品编码
			
			if(bTask){//立体库
				inoutJobDetail.setJobnum(inStroStorage.getPnum());
			}
//			else{//暂存区
//				inoutJobDetail.setJobnum(inStroStorage.getPnum()-inStroStorage.getAssignnum());
//			}
			
			inoutJobDetail.setNetweight(inStroStorage.getPnetweight());//净重
			inoutJobDetail.setWeight(inStroStorage.getPweight());//毛重
			inoutJobDetail.setVolume(inStroStorage.getPvolume());//体积
			inoutJobDetail.setAssignnum(inStroStorage.getPnum());//分配数量
			inoutJobDetail.setPunit(inStroStorage.getPunit());//单位
			inoutJobDetail.setAssignnetweight(inStroStorage.getAssignnetweight()); //分配净重
			inoutJobDetail.setAssignweight(inStroStorage.getAssignweight());       //分配毛量
			inoutJobDetail.setAssignvolume(inStroStorage.getAssignvolume());       //分配体积
			inoutJobDetail.setIsplit("1");//是否拆分（1：整托 2：分拆（零包）） 拆分数量 为分配数量assignnum
			inoutJobDetail.setIsinvoice("Y");//是否已生成单据 Y是  N否
			inoutJobDetail.setInjobdetailid(inStroStorage.getInjobetailid());	//源作业详细（生成出库作业时的添加该库存的入库作业）
//			inoutJobDetail.setTransreceiptid(iStorage.get)//收货记录ID）
			
		} catch (Exception er) {
			er.printStackTrace();
			Logger.error("创建作业详细实例作业出错！"+er.getMessage());
		}
		return inoutJobDetail;
		
    }
}
