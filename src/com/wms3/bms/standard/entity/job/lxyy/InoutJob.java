package com.wms3.bms.standard.entity.job.lxyy;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.base.IAlleyBus;
import com.wms3.bms.standard.business.base.impl.AlleyBusImpl;
import com.wms3.bms.standard.constant.StandardConstant;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.job.ITaskDao;
import com.wms3.bms.standard.dao.job.impl.TaskDaoImpl;
import com.wms3.bms.standard.entity.base.BaseAlley;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;



/**
 * 
 * 描述: 作业
 * @author yao 
 * @since WMS 3.0
 */
public class InoutJob  implements java.io.Serializable {

    private static final long serialVersionUID = 4020743324182458002L;
    
     private String jobid;      //作业编号
     private String jobtype;    //作业类型 （回流的作业类型为 入库类型的hl值）
     private String onLineType; //联机模式 1.联机 2.脱机
     private String isaccount;  //是否记账 Y.是 N.否（回流的不记账）
     private String status;     //作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
     private Integer priority;  //优先级（入库没有优先级）
     private String createtime; //时间 时间格式：yyyy-MM-dd hh:mm:ss
     private String readtime;   //读走时间 时间格式：yyyy-MM-dd hh:mm:ss
     private String finishtime; //完成时间 时间格式：yyyy-MM-dd hh:mm:ss
     private String jobpos;     //作业方向 1.入库端 2.出库端（暂时没有用）
     private String inOutType;  //入出类型 1上架入库 2.出库
     private String shiftid;    //作业班次（暂时没有用）
     private String snumber;    //输送号（入库输送号 为输送机的编码号 一般与巷道号相同）
     private String traycode;   //托盘条码
     private String route;      //执行路线1-直入/直出2-回流（根据收货单入的为 直入，出库的为直出 ，暂存到转向立体库就是回流）
     private String opManId;    //操作人
     private String taskid;     //调度任务ID
     private String invoicetype;//1.先有作业后有单 2.先有单据后有作业

     private String warehouseid;    //仓库ID
     private String scargoWhareaId; //源库区ID
     private String scargoSpaceId;  //源货位ID
     private String scargoAlleyId;  //源巷道ID
     private String tcargoWhareaId; //目标库区ID
     private String tcargoSpaceId;  //目标货位ID
     private String tcargoAlleyId;  //目标巷道ID
     
     private String oldspacestatus; // 分配前货位状态
     private String isinvoice;      	//是否已生成单据 Y是  N否
     
     

	private String boundstockid; // 单据id
     private String boundstockdetailid; // 单据明细id
     private String boundrequeststockid; // 入库申请单id
     
     
     //派生属性
     protected String scargoWhareaName;     // 源库区
     protected String scargoAlleyName;      // 源巷道
     protected String scargoSpaceName;      // 源货位
     protected String tcargoWhareaName;     // 目标库区
     protected String tcargoAlleyName;      // 目标巷道
     protected String tcargoSpaceName;      // 目标货位
     protected String statusName;           // 作业状态名称
     protected String jobtypeName;          // 作业类型名称
     protected String opMan;                // 操作人
     private String warehousename;    	    //收货仓库名
     private String invoicetypename;    	//1：按入库收货单来生成的作业，2：按出库单生成的作业，3： 回流直接生成的作业（从暂存到立体库）
     private String jobcategoryname;      //作业类别 1:自动化立体库作业 2:暂存区作业
     //private double invoicenumz;          //开单数量
     protected String Virtualwhname;      //逻辑库区名称
     
     //新增字段
     protected String jobcategory;      //作业类别 1:自动化立体库作业 2:暂存区作业

     protected String Virtualwhid;      //逻辑库区id
     
     

    // Constructors

    /** default constructor */
    public InoutJob() {
    }

	/** minimal constructor */
    public InoutJob(String jobid, String onLineType, String status, String jobpos, String taskid) {
        this.jobid = jobid;
        this.onLineType = onLineType;
        this.status = status;
        this.jobpos = jobpos;
        this.taskid = taskid;
    }
    
    /** full constructor */
    public InoutJob(String jobid, String jobtype, String onLineType, String isaccount, String status, Integer priority, String createtime, String readtime, String finishtime, String jobpos, String shiftid, String warehouseid, String snumber, String traycode, String route, String opManId, String taskid) {
        this.jobid = jobid;
        this.jobtype = jobtype;
        this.onLineType = onLineType;
        this.isaccount = isaccount;
        this.status = status;
        this.priority = priority;
        this.createtime = createtime;
        this.readtime = readtime;
        this.finishtime = finishtime;
        this.jobpos = jobpos;
        this.shiftid = shiftid;
        this.warehouseid = warehouseid;
        this.snumber = snumber;
        this.traycode = traycode;
        this.route = route;
        this.opManId = opManId;
        this.taskid = taskid;
    }
    public String getIsinvoice() {
		return isinvoice;
	}
	public void setIsinvoice(String isinvoice) {
		this.isinvoice = isinvoice;
	}
    // Property accessors
    /**
     * 功能：获得作业编号
     */
    public String getJobid() {
        return this.jobid;
    }
    /**
     * 功能：设置作业编号
     * @author hug 2012-3-6 
     * @param jobid
     */
    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
    /**
     * 功能：获得作业类型1-入库2-出库3-移库
     * @author hug 2012-3-6 
     * @return
     */
    public String getJobtype() {
        return this.jobtype;
    }
    /**
     * 功能：设置作业类型1-入库2-出库3-移库
     * @author hug 2012-3-6 
     * @param jobtype
     */
    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }
    /**
     * 功能：获得联机模式 1.联机 2.脱机
     * @author hug 2012-3-6 
     * @return
     */
    public String getOnLineType() {
        return this.onLineType;
    }
    /**
     * 功能：设置联机模式 1.联机 2.脱机
     * @author hug 2012-3-6 
     * @param onLineType
     */
    public void setOnLineType(String onLineType) {
        this.onLineType = onLineType;
    }
    /**
     * 功能：获得是否记账 Y.是 N.否
     * @author hug 2012-3-6 
     * @return
     */
    public String getIsaccount() {
        return this.isaccount;
    }
    /**
     * 功能：设置是否记账 Y.是 N.否
     * @author hug 2012-3-6 
     * @param isaccount
     */
    public void setIsaccount(String isaccount) {
        this.isaccount = isaccount;
    }
    /**
     * 功能：获得作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
     * @author hug 2012-3-6 
     * @return
     */
    public String getStatus() {
        return this.status;
    }
    /**
     * 功能：设置作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
     * @author hug 2012-3-6 
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * 功能：获得优先级
     * @author hug 2012-3-6 
     * @return
     */
    public Integer getPriority() {
        return this.priority;
    }
    /**
     * 功能：设置优先级
     * @author hug 2012-3-6 
     * @param priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    /**
     * 功能：获得时间 时间格式：yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @return
     */
    public String getCreatetime() {
        return this.createtime;
    }
    /**
     * 功能：设置时间 时间格式：yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**
     * 功能：获得读走时间 时间格式：yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @return
     */
    public String getReadtime() {
        return this.readtime;
    }
    /**
     * 功能：设置读走时间 时间格式：yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @param readtime
     */
    public void setReadtime(String readtime) {
        this.readtime = readtime;
    }
    /**
     * 功能：获得完成时间 时间格式：yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @return
     */
    public String getFinishtime() {
        return this.finishtime;
    }
    /**
     * 功能：设置完成时间 时间格式：yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @param finishtime
     */
    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }
    /**
     * 功能：获得作业方向 1.入库端 2.出库端
     * @author hug 2012-3-6 
     * @return
     */
    public String getJobpos() {
        return this.jobpos;
    }
    /**
     * 功能：设置作业方向 1.入库端 2.出库端
     * @author hug 2012-3-6 
     * @param jobpos
     */
    public void setJobpos(String jobpos) {
        this.jobpos = jobpos;
    }
    /**
     * 功能：获得作业班次
     * @author hug 2012-3-6 
     * @return
     */
    public String getShiftid() {
        return this.shiftid;
    }
    /**
     * 功能：设置作业班次
     * @author hug 2012-3-6 
     * @param shiftid
     */
    public void setShiftid(String shiftid) {
        this.shiftid = shiftid;
    }
    /**
     * 功能：获得仓库ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getWarehouseid() {
        return this.warehouseid;
    }
    /**
     * 功能：设置仓库ID
     * @author hug 2012-3-6 
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }
    /**
     * 功能：获得输送号
     * @author hug 2012-3-6 
     * @return
     */
    public String getSnumber() {
        return this.snumber;
    }
    /**
     * 功能：设置输送号
     * @author hug 2012-3-6 
     * @param snumber
     */
    public void setSnumber(String snumber) {
        this.snumber = snumber;
    }
    /**
     * 功能：获得托盘条码
     * @author hug 2012-3-6 
     * @return
     */
    public String getTraycode() {
        return this.traycode;
    }
    /**
     * 功能：设置托盘条码
     * @author hug 2012-3-6 
     * @param traycode
     */
    public void setTraycode(String traycode) {
        this.traycode = traycode;
    }
    /**
     * 功能：获得执行路线1-直入/直出2-回流
     * @author hug 2012-3-6 
     * @return
     */
    public String getRoute() {
        return this.route;
    }
    /**
     * 功能：设置执行路线1-直入/直出2-回流
     * @author hug 2012-3-6 
     * @param route
     */
    public void setRoute(String route) {
        this.route = route;
    }
    /**
     * 功能：获得操作人
     * @author hug 2012-3-6 
     * @return
     */
    public String getOpManId() {
        return this.opManId;
    }
    /**
     * 功能：设置操作人
     * @author hug 2012-3-6 
     * @param opManId
     */
    public void setOpManId(String opManId) {
        this.opManId = opManId;
    }
    /**
     * 功能：获得调度任务号
     * @author hug 2012-3-6 
     * @return
     */
    public String getTaskid() {
        return this.taskid;
    }
    /**
     * 功能：设置调度任务号
     * @author hug 2012-3-6 
     * @param taskid
     */
    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    /**
     * 功能：
     * @author hug 2012-4-16 
     * @return
     */
    public String getInvoicetype() {
        return invoicetype;
    }

    /**
     * 功能：设置1:先有作业后生成单据;2:先有单据后生成作业
     * @author hug 2012-4-16 
     * @param invoicetype
     */
    public void setInvoicetype(String invoicetype) {
        this.invoicetype = invoicetype;
    }
    
    /**
     * 功能：获得源货位ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getScargoSpaceId() {
        return this.scargoSpaceId;
    }
    /**
     * 功能：设置源货位ID
     * @author hug 2012-3-6 
     * @param scargoSpaceId
     */
    public void setScargoSpaceId(String scargoSpaceId) {
        this.scargoSpaceId = scargoSpaceId;
    }
    /**
     * 功能：获得源巷道ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getScargoAlleyId() {
        return this.scargoAlleyId;
    }
    /**
     * 功能：设置源巷道ID
     * @author hug 2012-3-6 
     * @param scargoAlleyId
     */
    public void setScargoAlleyId(String scargoAlleyId) {
        this.scargoAlleyId = scargoAlleyId;
    }
   
    /**
     * 功能：获得目标货位ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getTcargoSpaceId() {
        return this.tcargoSpaceId;
    }
    /**
     * 功能：设置目标货位ID
     * @author hug 2012-3-6 
     * @param tcargoSpaceId
     */
    public void setTcargoSpaceId(String tcargoSpaceId) {
        this.tcargoSpaceId = tcargoSpaceId;
    }
    /**
     * 功能：获得目标巷道ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getTcargoAlleyId() {
        return this.tcargoAlleyId;
    }
    /**
     * 功能：设置目标巷道ID
     * @author hug 2012-3-6 
     * @param tcargoAlleyId
     */
    public void setTcargoAlleyId(String tcargoAlleyId) {
        this.tcargoAlleyId = tcargoAlleyId;
    }
    
    /**
     * 功能：获得目标库区ID
     * @author hug 2012-4-16 
     * @return
     */
    public String getTcargoWhareaId() {
        return tcargoWhareaId;
    }


    /**
     * 功能：设置目标库区ID
     * @author hug 2012-4-16 
     * @param tcargoWhareaId
     */
    public void setTcargoWhareaId(String tcargoWhareaId) {
        this.tcargoWhareaId = tcargoWhareaId;
    }
    /**
     * 功能：获得源库区ID
     * @author hug 2012-4-16 
     * @return
     */
    public String getScargoWhareaId() {
        return scargoWhareaId;
    }


    /**
     * 功能：设置源库区ID
     * @author hug 2012-4-16 
     * @param scargoWhareaId
     */
    public void setScargoWhareaId(String scargoWhareaId) {
        this.scargoWhareaId = scargoWhareaId;
    }

    /**
     * 功能：获得入出类型 0:收货1上架入库 2.出库
     * @author hug 2012-4-18 
     * @return
     */
    public String getInOutType() {
        return inOutType;
    }

    /**
     * 功能：设置入出类型 0:收货1上架入库 2.出库
     * @author hug 2012-4-18 
     * @param inOutType
     */
    public void setInOutType(String inOutType) {
        this.inOutType = inOutType;
    }

    /**
     * 功能：获得作业类型名称
     * @author hug 2012-6-20 
     * @return
     */
    public String getJobtypeName() {
        return jobtypeName;
    }

    /**
     * 功能：设置作业类型名称
     * @author hug 2012-6-20 
     * @param jobtypeName
     */
    public void setJobtypeName(String jobtypeName) {
        this.jobtypeName = jobtypeName;
    }

    /**
     * 功能：获得操作人
     * @author hug 2012-6-20 
     * @return
     */
    public String getOpMan() {
        return opMan;
    }

    /**
     * 功能：设置操作人
     * @author hug 2012-6-20 
     * @param opMan
     */
    public void setOpMan(String opMan) {
        this.opMan = opMan;
    }

    /**
     * 功能：获得源巷道
     * @author hug 2012-6-20 
     * @return
     */
    public String getScargoAlleyName() {
        return scargoAlleyName;
    }

    /**
     * 功能：设置源巷道
     * @author hug 2012-6-20 
     * @param scargoAlleyName
     */
    public void setScargoAlleyName(String scargoAlleyName) {
        this.scargoAlleyName = scargoAlleyName;
    }

    /**
     * 功能：获得源货位
     * @author hug 2012-6-20 
     * @return
     */
    public String getScargoSpaceName() {
        return scargoSpaceName;
    }

    /**
     * 功能：设置源货位
     * @author hug 2012-6-20 
     * @param scargoSpaceName
     */
    public void setScargoSpaceName(String scargoSpaceName) {
        this.scargoSpaceName = scargoSpaceName;
    }

    /**
     * 功能：获得源库区
     * @author hug 2012-6-20 
     * @return
     */
    public String getScargoWhareaName() {
        return scargoWhareaName;
    }

    /**
     * 功能：设置源库区
     * @author hug 2012-6-20 
     * @param scargoWhareaName
     */
    public void setScargoWhareaName(String scargoWhareaName) {
        this.scargoWhareaName = scargoWhareaName;
    }

    /**
     * 功能：获得作业状态名称
     * @author hug 2012-6-20 
     * @return
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * 功能：设置作业状态名称
     * @author hug 2012-6-20 
     * @param statusName
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * 功能：获得目标巷道
     * @author hug 2012-6-20 
     * @return
     */
    public String getTcargoAlleyName() {
        return tcargoAlleyName;
    }

    /**
     * 功能：设置目标巷道
     * @author hug 2012-6-20 
     * @param tcargoAlleyName
     */
    public void setTcargoAlleyName(String tcargoAlleyName) {
        this.tcargoAlleyName = tcargoAlleyName;
    }

    /**
     * 功能：获得目标货位
     * @author hug 2012-6-20 
     * @return
     */
    public String getTcargoSpaceName() {
        return tcargoSpaceName;
    }

    /**
     * 功能：设置目标货位
     * @author hug 2012-6-20 
     * @param tcargoSpaceName
     */
    public void setTcargoSpaceName(String tcargoSpaceName) {
        this.tcargoSpaceName = tcargoSpaceName;
    }

    /**
     * 功能：获得目标库区
     * @author hug 2012-6-20 
     * @return
     */
    public String getTcargoWhareaName() {
        return tcargoWhareaName;
    }

    /**
     * 功能：设置目标库区
     * @author hug 2012-6-20 
     * @param tcargoWhareaName
     */
    public void setTcargoWhareaName(String tcargoWhareaName) {
        this.tcargoWhareaName = tcargoWhareaName;
    }

    /**
     * 功能:获得分配前货位状态
     * @author hug 2012-9-6 
     * @return
     */
    public String getOldspacestatus() {
        return oldspacestatus;
    }

    /**
     * 功能:设置分配前货位状态
     * @author hug 2012-9-6 
     * @param oldspacestatus
     */
    public void setOldspacestatus(String oldspacestatus) {
        this.oldspacestatus = oldspacestatus;
    }

    /**
     * 功能: 获得作业类别 1:自动化立体库作业
     * @author hug 2012-10-24 
     * @return
     */
    public String getJobcategory() {
        return jobcategory;
    }

    /**
     * 功能: 设置作业类别 1:自动化立体库作业
     * @author hug 2012-10-24 
     * @param jobcategory
     */
    public void setJobcategory(String jobcategory) {
        this.jobcategory = jobcategory;
    }

	public String getBoundstockid() {
		return boundstockid;
	}

	public void setBoundstockid(String boundstockid) {
		this.boundstockid = boundstockid;
	}

	public String getBoundstockdetailid() {
		return boundstockdetailid;
	}

	public void setBoundstockdetailid(String boundstockdetailid) {
		this.boundstockdetailid = boundstockdetailid;
	}
	
	public String getVirtualwhname() {
		return Virtualwhname;
	}

	public void setVirtualwhname(String virtualwhname) {
		Virtualwhname = virtualwhname;
	}

	public String getVirtualwhid() {
		return Virtualwhid;
	}

	public void setVirtualwhid(String virtualwhid) {
		Virtualwhid = virtualwhid;
	}

/**
  * 作业管理 查询
  * @param warehouseid
  * @param whAreaId
  * @param indate
  * @param jobid
  * @param instockid
  * @param status
  * @param traycode
  * @param isback
  * @param productid
  * @param lotinfo
  * @return
  */
	public String getQueryHQL(String warehouseid,String Virtualwhid,String whAreaId,String indate,String jobid,String instockid,String status,String traycode,String isback,String productid,String lotinfo,String taskid,String inOutType)
	{
		String hql = "select distinct ta from InoutJob ta,InoutJobdetail tb where 1=1 and ta.jobid=tb.jobid ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(Virtualwhid != null && Virtualwhid.trim().length() > 0){
			hql += " and ta.Virtualwhid='"+Virtualwhid+"'";
		}
		if(whAreaId != null && whAreaId.trim().length() > 0){
			hql += " and ta.scargoWhareaId='"+whAreaId+"'";
		}
		if(indate != null && indate.trim().length() > 0){
			hql += " and ta.createtime like '%"+indate+"%'";
		}
		if(jobid != null && jobid.trim().length() > 0){
			hql += " and ta.jobid like '%"+jobid+"%'";
		}
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and ta.boundstockid='"+instockid+"'";
		}
		if(status != null && status.trim().length() > 0){
			hql += " and ta.status ='"+status+"'";
		}
		if(isback != null && isback.trim().length() > 0 && isback.equals("Y")){
			hql += " and ta.jobtype ='hl'";
		}else if(isback != null && isback.trim().length() > 0 && isback.equals("N")){
			hql += " and ta.jobtype !='hl'";
		}
		if(traycode != null && traycode.trim().length() > 0){
			hql += " and ta.traycode ='"+traycode+"'";
		}
		if(productid != null && productid.trim().length() > 0){
			hql += " and tb.productid ='"+productid+"'";
		}
		if(lotinfo != null && lotinfo.trim().length() > 0){
			hql += " and tb.lotinfo  ='"+lotinfo+"'";
		}
		if(taskid != null && taskid.trim().length() > 0){
			hql += " and ta.taskid  ='"+taskid+"'";
		}
		if(inOutType != null && inOutType.trim().length() > 0){
			hql += " and ta.inOutType  ='"+inOutType+"'";
		}
		hql += " order by ta.warehouseid,ta.boundstockid";
		return hql;
	}
	 /**
	  * 作业管理 查询
	  * @param warehouseid
	  * @param whAreaId
	  * @param indate
	  * @param jobid
	  * @param instockid
	  * @param status
	  * @param traycode
	  * @param isback
	  * @param productid
	  * @param lotinfo
	  * @return
	  */
	public String getQueryHQLCount(String warehouseid,String Virtualwhid,String whAreaId,String indate,String jobid,String instockid,String status,String traycode,String isback,String productid,String lotinfo,String taskid,String inOutType)
	{
		String hql = "select count(distinct ta) from InoutJob ta,InoutJobdetail tb where 1=1 and ta.jobid=tb.jobid ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(Virtualwhid != null && Virtualwhid.trim().length() > 0){
			hql += " and ta.Virtualwhid='"+Virtualwhid+"'";
		}
		if(whAreaId != null && whAreaId.trim().length() > 0){
			hql += " and ta.scargoWhareaId='"+whAreaId+"'";
		}
		if(indate != null && indate.trim().length() > 0){
			hql += " and ta.createtime like '%"+indate+"%'";
		}
		if(jobid != null && jobid.trim().length() > 0){
			hql += " and ta.jobid like '%"+jobid+"%'";
		}
		if(instockid != null && instockid.trim().length() > 0){
			hql += " and ta.boundstockid='"+instockid+"'";
		}
		if(status != null && status.trim().length() > 0){
			hql += " and ta.status ='"+status+"'";
		}
		if(isback != null && isback.trim().length() > 0 && isback.equals("Y")){
			hql += " and ta.jobtype ='hl'";
		}else if(isback != null && isback.trim().length() > 0 && isback.equals("N")){
			hql += " and ta.jobtype !='hl'";
		}
		if(traycode != null && traycode.trim().length() > 0){
			hql += " and ta.traycode ='"+traycode+"'";
		}
		if(productid != null && productid.trim().length() > 0){
			hql += " and tb.productid ='"+productid+"'";
		}
		if(lotinfo != null && lotinfo.trim().length() > 0){
			hql += " and tb.lotinfo  ='"+lotinfo+"'";
		}
		if(taskid != null && taskid.trim().length() > 0){
			hql += " and ta.taskid  ='"+taskid+"'";
		}
		if(inOutType != null && inOutType.trim().length() > 0){
			hql += " and ta.inOutType  ='"+inOutType+"'";
		}
		return hql;
	}
	/**
	 * 作业及作业明细查询
	 * @param warehouseid
	 * @param whAreaId
	 * @param alleyId
	 * @param cargospaceid
	 * @param onlinetype
	 * @param indate_from
	 * @param indate_to
	 * @param isback
	 * @param invoicetype
	 * @param instockid
	 * @param productid
	 * @param lotinfo
	 * @param traycode
	 * @param inOutType
	 * @return
	 */
		public String getQueryJobADetailHQL(String warehouseid,String whAreaId,String alleyId,String cargospaceid,String onlinetype,String indate_from,String indate_to,String isback,String invoicetype,String instockid,String productid,String lotinfo,String traycode,String inOutType)
		{
			String hql = " from InoutJob ta,InoutJobdetail tb where 1=1 and ta.jobid=tb.jobid ";
			if(warehouseid != null && warehouseid.trim().length() > 0){
				hql += " and ta.warehouseid='"+warehouseid+"'";
			}
			if(whAreaId != null && whAreaId.trim().length() > 0){
				hql += " and ta.scargoWhareaId='"+whAreaId+"'";
			}
			if(alleyId != null && alleyId.trim().length() > 0){
				hql += " and ta.alleyId = '"+alleyId+"'";
			}
			if(cargospaceid != null && cargospaceid.trim().length() > 0){
				hql += " and ta.cargospaceid = '"+cargospaceid+"'";
			}
			if(onlinetype != null && onlinetype.trim().length() > 0){
				hql += " and ta.onlinetype = '"+onlinetype+"'";
			}
			if(jobid != null && jobid.trim().length() > 0){
				hql += " and ta.jobid like '%"+jobid+"%'";
			}
			if(indate_from != null && indate_from.trim().length() > 0){
				hql += " and ta.createtime >= '"+indate_from+"'";
			}
			if(indate_to != null && indate_to.trim().length() > 0){
				hql += " and ta.createtime <= '"+indate_to+" 24:60:60'";
			}
			if(isback != null && isback.trim().length() > 0 && isback.equals("Y")){
				hql += " and ta.jobtype ='hl'";
			}else if(isback != null && isback.trim().length() > 0 && isback.equals("N")){
				hql += " and ta.jobtype !='hl'";
			}
			if(invoicetype != null && invoicetype.trim().length() > 0){
				hql += " and ta.invoicetype='"+invoicetype+"'";
			}
			if(instockid != null && instockid.trim().length() > 0){
				hql += " and ta.boundstockid='"+instockid+"'";
			}
			if(traycode != null && traycode.trim().length() > 0){
				hql += " and ta.traycode ='"+traycode+"'";
			}
			if(productid != null && productid.trim().length() > 0){
				hql += " and tb.productid ='"+productid+"'";
			}
			if(lotinfo != null && lotinfo.trim().length() > 0){
				hql += " and tb.lotinfo  like '%"+lotinfo+"%'";
			}
			if(inOutType != null && inOutType.trim().length() > 0){
				hql += " and ta.inOutType  ='"+inOutType+"'";
			}
			
			hql += " order by ta.warehouseid,ta.boundstockid";
			return hql;
		}
		/**
		 * 作业及作业明细查询
		 * @param warehouseid
		 * @param whAreaId
		 * @param alleyId
		 * @param cargospaceid
		 * @param onlinetype
		 * @param indate_from
		 * @param indate_to
		 * @param isback
		 * @param invoicetype
		 * @param instockid
		 * @param productid
		 * @param lotinfo
		 * @param traycode
		 * @param inOutType
		 * @return
		 */
			public String getQueryJobADetailHQLCount(String warehouseid,String whAreaId,String alleyId,String cargospaceid,String onlinetype,String indate_from,String indate_to,String isback,String invoicetype,String instockid,String productid,String lotinfo,String traycode,String inOutType)
			{
				String hql = " select count(*) from InoutJob ta,InoutJobdetail tb where 1=1 and ta.jobid=tb.jobid ";
				if(warehouseid != null && warehouseid.trim().length() > 0){
					hql += " and ta.warehouseid='"+warehouseid+"'";
				}
				if(whAreaId != null && whAreaId.trim().length() > 0){
					hql += " and ta.scargoWhareaId='"+whAreaId+"'";
				}
				if(alleyId != null && alleyId.trim().length() > 0){
					hql += " and ta.alleyId = '"+alleyId+"'";
				}
				if(cargospaceid != null && cargospaceid.trim().length() > 0){
					hql += " and ta.cargospaceid = '"+cargospaceid+"'";
				}
				if(onlinetype != null && onlinetype.trim().length() > 0){
					hql += " and ta.onlinetype = '"+onlinetype+"'";
				}
				if(jobid != null && jobid.trim().length() > 0){
					hql += " and ta.jobid like '%"+jobid+"%'";
				}
				if(indate_from != null && indate_from.trim().length() > 0){
					hql += " and ta.createtime >= '"+indate_from+"'";
				}
				if(indate_to != null && indate_to.trim().length() > 0){
					hql += " and ta.createtime <= '"+indate_to+" 24:60:60'";
				}
				if(isback != null && isback.trim().length() > 0 && isback.equals("Y")){
					hql += " and ta.jobtype ='hl'";
				}else if(isback != null && isback.trim().length() > 0 && isback.equals("N")){
					hql += " and ta.jobtype !='hl'";
				}
				if(invoicetype != null && invoicetype.trim().length() > 0){
					hql += " and ta.invoicetype='"+instockid+"'";
				}
				if(instockid != null && instockid.trim().length() > 0){
					hql += " and ta.boundstockid='"+instockid+"'";
				}
				if(traycode != null && traycode.trim().length() > 0){
					hql += " and ta.traycode ='"+traycode+"'";
				}
				if(productid != null && productid.trim().length() > 0){
					hql += " and tb.productid ='"+productid+"'";
				}
				if(lotinfo != null && lotinfo.trim().length() > 0){
					hql += " and tb.lotinfo  like '%"+lotinfo+"%'";
				}
				if(inOutType != null && inOutType.trim().length() > 0){
					hql += " and ta.inOutType  ='"+inOutType+"'";
				}
				return hql;
			}
	public String getBoundrequeststockid() {
		return boundrequeststockid;
	}

	public void setBoundrequeststockid(String boundrequeststockid) {
		this.boundrequeststockid = boundrequeststockid;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}

	public String getInvoicetypename() {
		return invoicetypename;
	}

	public void setInvoicetypename(String invoicetypename) {
		this.invoicetypename = invoicetypename;
	}   
/**
 * 入库管理 取消收货
 * @param boundstockid
 * @param boundstockdetailid
 * @return
 */
	public String getQueryHQLByboundid(String boundstockid,String boundstockdetailid)
	{
		String hql = "from InoutJob where 1=1 and status!='5'";
		if(boundstockid != null && boundstockid.trim().length() > 0){
			hql += " and boundstockid='"+boundstockid+"'";
		}
		if(boundstockdetailid != null && boundstockdetailid.trim().length() > 0){
			hql += " and boundstockdetailid='"+boundstockdetailid+"'";
		}
		return hql;
	}
	/**
	 * 功能:作废入库作业（作业管理）
	 * @param jobids		作业ID(可复数个)
	 * @param strUserCode	
	 * @return 
	 * @throws Exception 
	 * @throws Exception
	 */
	public String cancelJobs(String jobids, String strUserCode,EntityDAO dao) throws Exception{
		String strBackMsg = "Y";
		try {
			    BaseCargoSpaceDaoImpl spaceIml  = new BaseCargoSpaceDaoImpl(dao);
			    ScheduleTask task = null;
				ITaskDao iTaskDao  = new TaskDaoImpl(dao);
				if(jobids!=null && !jobids.equals("")){
					String[] jobid = jobids.split(",");
					for(int i=0; i<jobid.length; i++){
						   String jobidsString = jobid[i].intern();
							synchronized (jobidsString) {
								InoutJob job = getJobByJobid(jobid[i],dao);
								InoutJobdetail jobdetail = new InoutJobdetail().getJobDetailByJobid(jobid[i],dao);
								if(job != null){
									if(job.getStatus().equals("1") || job.getStatus().equals("2")){
										if(job.getJobtype().equals("hl")){ //回流作业作废的处理方式
											job.setStatus("5"); //作废回流作业
											BaseCargospace space = null;
											//还原到暂存货位库存中
											InventoryStorage inventory = null ;
											inventory = InventoryStorage.getQueryHQLBytraycode(job.getTraycode(), job.getScargoWhareaId(), dao);
											if(job.getTcargoSpaceId()!=null && !job.getTcargoSpaceId().trim().equals("")){
												space = spaceIml.getCargoSpaceById(job.getTcargoSpaceId());
												space.setCsstatus("1");
											}
											if(inventory == null){
												inventory = new InventoryStorage();
												inventory.setCargoSpaceId(job.getWarehouseid()+StandardConstant.zcidkzl+"010101");
												inventory.setWhAreaId(job.getWarehouseid()+StandardConstant.zcidkzl);
												inventory.setWarehouseid(job.getWarehouseid());
												inventory.setProductid(jobdetail.getProductid());
												inventory.setProductdate(jobdetail.getPrintdate());
												inventory.setIndate(StrTools.getCurrDateTime(2));
												inventory.setLotid(jobdetail.getLotid());
												inventory.setLotinfo(jobdetail.getLotinfo());
												inventory.setPackid(jobdetail.getPackid());
												inventory.setIntype("2"); //脱机
												inventory.setPunit(jobdetail.getPunit());
												inventory.setIsplit("1"); //整托
												inventory.setPnum(jobdetail.getJobnum());
												inventory.setInjobid(job.getJobid());
												inventory.setTraycode(job.getTraycode());
												inventory.setProductcode(jobdetail.getProductcode());
												inventory.setProductstatus(jobdetail.getProductStatus());
												inventory.setStatus("0");
												
												task = iTaskDao.getScheduleTaskByTaskid(job.getTaskid());
												if(task!=null){
													task.setStatus("5");          //任务状态  作废
												}
												ZFJob(job,space,null,inventory,task,dao);
											}else{
												task = iTaskDao.getScheduleTaskByTaskid(job.getTaskid());
												if(task!=null){
													task.setStatus("5");          //任务状态  作废
												}
												inventory.setPnum(inventory.getPnum()+jobdetail.getJobnum());
												ZFJobupdate(job,space,null,inventory,task,dao);
											}	
										}else{//其他类型入库作业
											job.setStatus("5"); //作废回流作业
											BaseCargospace space = null;
											if(job.getTcargoSpaceId()!=null && !job.getTcargoSpaceId().trim().equals("")){
												space = spaceIml.getCargoSpaceById(job.getTcargoSpaceId());
												space.setCsstatus("1");
											}
											ZFJob(job,space,null,null,task,dao);
										}
									}else{
										strBackMsg = "只有未执行或待执行作业可以作废！";
									}
								}
							}
					}
				}
		}catch (Exception e) {
			e.printStackTrace();
			strBackMsg = "作废作业（作业管理）时候发生错误！";
			return strBackMsg;
		}
		return strBackMsg;
	}
	/**
	 * 功能:初始化作业（作业管理）
	 * @param jobids		作业ID(可复数个)
	 * @param strUserCode	
	 * @return 
	 * @throws Exception 
	 * @throws Exception
	 */
	public String initializeJobs(String jobids, String strUserCode,EntityDAO dao) throws Exception{
		String strBackMsg = "初始化成功";
		try {
			    BaseCargoSpaceDaoImpl spaceIml  = new BaseCargoSpaceDaoImpl(dao);
			    IAlleyBus alleyBus = new AlleyBusImpl(dao);
				if(jobids!=null && !jobids.equals("")){
					String[] jobid = jobids.split(",");
					for(int i=0; i<jobid.length; i++){
						synchronized (jobid[i]) {
							InoutJob job = getJobByJobid(jobid[i],dao);
							InoutJobdetail jobdetail = new InoutJobdetail().getJobDetailByJobid(jobid[i],dao);
							if(job != null){
								if(job.getInvoicetype().equals("1") && job.getInOutType().equals("1") ){ //入库作业作废的处理方式 1：按入库收货单来生成的作业，2：按出库单生成的作业，3： 回流直接生成的作业（从暂存到立体库）
									if(job.getStatus().equals("2")){ //待执行状态
										strBackMsg = "待执行作业无需初始化";
									}else if(job.getStatus().equals("3")){ //正执行状态
										job.setStatus("1"); //初始化作业
										job.setTcargoAlleyId("");//初始化巷道
										job.setTcargoWhareaId("");
										job.setTaskid("");
										job.setReadtime("");
										//初始化货位
										if(job.getTcargoSpaceId()!=null && !job.getTcargoSpaceId().equals("")){
											BaseCargospace space = spaceIml.getCargoSpaceById(job.getTcargoSpaceId());
											if(space!=null){
												job.setTcargoSpaceId("");
												space.setCsstatus("1"); //改成空货位状态
												cancelJob(job,space,dao);
											}else{
												strBackMsg = "作业号为"+job.getJobid()+"的作业初始化失败";
											}
										}
									}
								}
							}
						}		
					}
				}
		}catch (Exception e) {
			e.printStackTrace();
			strBackMsg = "作废作业（作业管理）时候发生错误！";
			return strBackMsg;
		}
		return strBackMsg;
	}
/**
 * 完成作业
 * @param jobids
 * @param strUserCode
 * @param dao
 * @return
 * @throws Exception
 */
	public String finishJobs(String jobids, String strUserCode,EntityDAO dao) throws Exception{
		String strBackMsg = "Y";
		try {
			    BaseCargoSpaceDaoImpl spaceIml  = new BaseCargoSpaceDaoImpl(dao);
				if(jobids!=null && !jobids.equals("")){
					String[] jobid = jobids.split(",");
					for(int i=0; i<jobid.length; i++){
								String jobidsString = jobid[i].intern();
								synchronized (jobidsString) {
									InoutJob job = getJobByJobid(jobid[i],dao);
									InoutJobdetail jobdetail = new InoutJobdetail().getJobDetailByJobid(jobid[i],dao);
									if(job!=null){
										if(job.status.equals("1") || job.status.equals("2") || job.status.equals("3")){
											if(job.getTcargoSpaceId()!=null && job.getTcargoSpaceId().length()>0){
												if(!job.getJobtype().equals("hl") && job.getInOutType().equals("1") ){
													job.setStatus("4"); //完成作业
														BaseCargospace space = spaceIml.getCargoSpaceById(job.getTcargoSpaceId());
														if(space!=null){
															space.setCsstatus("2"); //改成满货位状态
															InventoryStorage inventory = new InventoryStorage();
															inventory.setCargoSpaceId(job.getTcargoSpaceId());
															inventory.setCargoAlleyId(job.getTcargoAlleyId());
															inventory.setWhAreaId(job.getTcargoWhareaId());
															inventory.setWarehouseid(job.getWarehouseid());
															inventory.setVirtualwhid(job.getVirtualwhid());
															inventory.setProductid(jobdetail.getProductid());
															inventory.setProductdate(jobdetail.getPrintdate());
															inventory.setIndate(StrTools.getCurrDateTime(2));
															inventory.setLotid(jobdetail.getLotid());
															inventory.setLotinfo(jobdetail.getLotinfo());
															inventory.setLotinfo2(jobdetail.getLotinfo2());
															inventory.setSupplier(jobdetail.getSupplier());
															inventory.setPackid(jobdetail.getPackid());
															inventory.setIntype("2"); //脱机
															inventory.setPunit(jobdetail.getPunit());
															inventory.setProductstatus(jobdetail.getProductStatus());//物品状态
															inventory.setPnum(jobdetail.getJobnum());
															inventory.setInjobid(job.getJobid());
															inventory.setInjobetailid(jobdetail.getJobdetailid());
															inventory.setTraycode(job.getTraycode());
															inventory.setProductcode(jobdetail.getProductcode());
															inventory.setStatus("0"); //未分配状态
															
															String finishtime = StrTools.getCurrDateTime(2);
															job.setFinishtime(finishtime);//完成时间
															
															ScheduleTask task = null;
															ITaskDao iTaskDao  = new TaskDaoImpl(dao);
															task = iTaskDao.getScheduleTaskByTaskid(job.getTaskid());
															if(task!=null){
																task.setStatus("4");          //任务状态  4.完成
															}
															
															finishJobz(job,space,null,inventory,task,dao);
														}else{
															strBackMsg = "作业号为"+job.getJobid()+"的作业手动完成失败";
														}
												}else if(job.getJobtype().equals("hl") && job.getInOutType().equals("1")){ //回流作业完成处理方式
													job.setStatus("4"); //完成作业
													//手动完成 目标货位为空
													if(job.getTcargoSpaceId() == null || job.getTcargoSpaceId().length() <= 0){
														
														strBackMsg = "作业:"+job.getJobid()+"目标货位为空的,手动完成失败";
														//令源货位ID = 源货位ID
														//job.setTcargoSpaceId(job.getScargoSpaceId());  
														
													}
													if(job.getTcargoSpaceId() != null || job.getTcargoSpaceId().length() > 0){
														BaseCargospace space = spaceIml.getCargoSpaceById(job.getTcargoSpaceId());
														if(space!=null){
															space.setCsstatus("2"); //改成满货位状态
															InventoryStorage inventory = new InventoryStorage();
															inventory.setCargoSpaceId(job.getTcargoSpaceId());
															inventory.setCargoAlleyId(job.getTcargoAlleyId());
															inventory.setWhAreaId(job.getTcargoWhareaId());
															inventory.setWarehouseid(job.getWarehouseid());
															inventory.setVirtualwhid(job.getVirtualwhid());
															inventory.setProductid(jobdetail.getProductid());
															inventory.setProductdate(jobdetail.getPrintdate());
															inventory.setIndate(StrTools.getCurrDateTime(2));
															inventory.setLotid(jobdetail.getLotid());
															inventory.setLotinfo(jobdetail.getLotinfo());
															inventory.setLotinfo2(jobdetail.getLotinfo2());
															inventory.setSupplier(jobdetail.getSupplier());
															inventory.setPackid(jobdetail.getPackid());
															inventory.setProductstatus(jobdetail.getProductStatus());//物品状态
															inventory.setIntype("2"); //脱机
															inventory.setPunit(jobdetail.getPunit());
															inventory.setPnum(jobdetail.getJobnum());
															inventory.setInjobid(job.getJobid());
															inventory.setInjobetailid(jobdetail.getJobdetailid());
															inventory.setTraycode(job.getTraycode());
															inventory.setProductcode(jobdetail.getProductcode());
															inventory.setStatus("0");
															
															String finishtime = StrTools.getCurrDateTime(2);
															job.setFinishtime(finishtime);//完成时间
															
															//finishJob(job,space,null,inventory,dao);
															ScheduleTask task = null;
															ITaskDao iTaskDao  = new TaskDaoImpl(dao);
															task = iTaskDao.getScheduleTaskByTaskid(job.getTaskid());
															if(task!=null){
																task.setStatus("4");          //任务状态  4.完成
															}
															finishJobz(job, space, null, inventory, task, dao);
														}
														/*else{
															strBackMsg = "作业号为"+job.getJobid()+"的作业手动完成失败";
														}*/
													}
												}
											}else{
												strBackMsg = "只有分配货位的作业才能手动完成！";
												break;
											}
										}else{
											strBackMsg = "只有未完成作业才能手动完成！";
											break;
										}
									}
								}

					}
				}
		}catch (Exception e) {
			e.printStackTrace();
			strBackMsg = "手动完成作业（作业管理）时候发生错误！";
			return strBackMsg;
		}
		return strBackMsg;
	}
	/**
	 * 完成作业 移库到暂存区
	 * @param jobid
	 * @param dao
	 * @return
	 * @throws Exception
	 */
		public String finishJobsToTem(String jobids, String strUserCode,EntityDAO dao) throws Exception{
			String strBackMsg = "成功移库到暂存区";
			try {
				    BaseCargoSpaceDaoImpl spaceIml  = new BaseCargoSpaceDaoImpl(dao);
					if(jobids!=null && !jobids.equals("")){
						String[] jobid = jobids.split(",");
						for(int i=0; i<jobid.length; i++){
							synchronized (jobid[i]) {
								InoutJob job = getJobByJobid(jobid[i],dao);
								InoutJobdetail jobdetail = new InoutJobdetail().getJobDetailByJobid(jobid[i],dao);
								if(job != null){
									InboundDetail detail = new InboundDetail();
									detail =  new InboundDetail().getDetailByidtray(job.getBoundstockid(), job.getTraycode(), dao);
									if(job.getInvoicetype().equals("1") && job.getInOutType().equals("1") ){ //入库作业作废的处理方式 1：按入库收货单来生成的作业，2：按出库单生成的作业，3： 回流直接生成的作业（从暂存到立体库）
										if(job.getStatus().equals("1") && detail!=null){ //未执行作业
											job.setStatus("4"); //完成作业
											detail.setStatus("4"); //上架状态
											//if(job.getTcargoSpaceId()==null || job.getTcargoSpaceId().equals("")){
											if(job.getTcargoSpaceId()==null || job.getTcargoSpaceId().equals("")|| !job.getTcargoSpaceId().equals("")){
												BaseCargospace space = spaceIml.getCargoSpaceById(job.getWarehouseid()+StandardConstant.zcidkzl+"010101"); //移库到暂存区
												if(space!=null){
													space.setCsstatus("2"); //改成满货位状态
													InventoryStorage inventory = new InventoryStorage();
													inventory.setCargoSpaceId(job.getWarehouseid()+StandardConstant.zcidkzl+"010101");
													inventory.setWhAreaId(job.getWarehouseid()+StandardConstant.zcidkzl);
													inventory.setWarehouseid(job.getWarehouseid());
													inventory.setProductid(jobdetail.getProductid());
													inventory.setProductdate(jobdetail.getPrintdate());
													inventory.setIndate(StrTools.getCurrDateTime(2));
													inventory.setLotid(jobdetail.getLotid());
													inventory.setLotinfo(jobdetail.getLotinfo());
													inventory.setPackid(jobdetail.getPackid());
													inventory.setIntype("2"); //脱机
													inventory.setPunit(jobdetail.getPunit());
													inventory.setIsplit("1"); //整托
													inventory.setPnum(jobdetail.getJobnum());
													inventory.setInjobid(job.getJobid());
													inventory.setTraycode(job.getTraycode());
													inventory.setRequestid(job.getBoundrequeststockid()); //申请单
													inventory.setInstockid(job.getBoundstockid());
													inventory.setProductcode(jobdetail.getProductcode());
													inventory.setProductstatus(jobdetail.getProductStatus());
													inventory.setStatus("0"); //未分配状态（库存状态）
													finishJob(job,space,detail,inventory,dao);
												}else{
													strBackMsg = "作业号为"+job.getJobid()+"的作业移库到暂存区失败，没有找到相应的暂存区";
												}
											}
										}else{
											strBackMsg = "作业号为"+job.getJobid()+"的作业不是未执行作业，不能移到暂存区";
										}
									}
								}
							}
								
						}
					}
			}catch (Exception e) {
				e.printStackTrace();
				strBackMsg = "移库到暂存区失败！";
				return strBackMsg;
			}
			return strBackMsg;
		}
	/**
	 * 功能:作废作业,删除库存,修改货位状态
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsStorage		库存列表
	 * @return 
	 * @throws Exception
	 */
	public void cancelJob(InoutJob job, BaseCargospace cargospace,EntityDAO dao) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = dao.getSession();
            tx = session.beginTransaction();
            //修改作业
            if(job!=null){
            	session.update(job);
            }
            
            //修改货位状态
            if(cargospace!=null){
            	session.update(cargospace);
            }
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("作废作业,删除库存,修改货位状态时候出错："+er.getMessage());
        }finally{
        	dao.closeSession(session);
        }
	}
    /**
     * 完成作业
     * @param InoutJob
     * @param BaseCargospace
     * @param InboundDetail
     * @param InventoryStorage
     * @param dao
     * @throws Exception
     */
	public void finishJob(InoutJob job,BaseCargospace cargospace,InboundDetail detail, InventoryStorage inventory,EntityDAO dao) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = dao.getSession();
            tx = session.beginTransaction();
            //修改作业
            if(job!=null){
                session.update(job);	
            }

            //修改货位状态
            if(cargospace!=null){
            	session.update(cargospace);
            }
            
            //修改作业
            if(detail!=null){
            	session.update(detail);
            }
            
            //保存库存
            if(inventory!=null){
                session.save(inventory);
            }

            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("手动完成作业,添加库存,修改货位状态,修改入库单明细状态时候出错："+er.getMessage());
        }finally{
        	dao.closeSession(session);
        }
	}
	/**
     * 作废入库作业(回流作业，普通入库作业)
     * @param InoutJob
     * @param BaseCargospace
     * @param InboundDetail
     * @param InventoryStorage
     * @param dao
     * @throws Exception
     */
	public void ZFJob(InoutJob job,BaseCargospace cargospace,InboundDetail detail, InventoryStorage inventory,ScheduleTask task,EntityDAO dao) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = dao.getSession();
            tx = session.beginTransaction();
            //修改作业
            if(job!=null){
                session.update(job);	
            }

            //修改货位状态
            if(cargospace!=null){
            	session.update(cargospace);
            }
            
            //修改作业
            if(detail!=null){
            	session.update(detail);
            }
            //修改调度指令
            if(detail!=null){
            	session.update("ScheduleTask",task);
            }
            //保存库存
            if(inventory!=null){
                session.save(inventory);
            }

            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("手动完成作业,添加库存,修改货位状态,修改入库单明细状态时候出错："+er.getMessage());
        }finally{
        	dao.closeSession(session);
        }
	}
    /**
     * 完成作业
     * @param InoutJob
     * @param BaseCargospace
     * @param InboundDetail
     * @param InventoryStorage
     * @param ScheduleTask
     * @param dao
     * @throws Exception
     */
	public void finishJobz(InoutJob job,BaseCargospace cargospace,InboundDetail detail, InventoryStorage inventory,ScheduleTask task,EntityDAO dao) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = dao.getSession();
            tx = session.beginTransaction();
            //修改作业
            if(job!=null){
                session.update(job);	
            }

            //修改货位状态
            if(cargospace!=null){
            	session.update(cargospace);
            }
            
            //修改作业
            if(detail!=null){
            	session.update(detail);
            }
            
            //保存库存
            if(inventory!=null){
                session.save(inventory);
            }
			// 修改调度
			if (task != null) {
				session.update("ScheduleTask",task);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("手动完成作业,添加库存,修改货位状态,修改入库单明细状态时候出错："+er.getMessage());
        }finally{
        	dao.closeSession(session);
        }
	}
    /**
     * 入库增加
     * @param InoutJob
     * @param BaseCargospace
     * @param InboundDetail
     * @param InventoryStorage
     * @param ScheduleTask
     * @param dao
     * @throws Exception
     */
	public void rkadd(BaseCargospace cargospace,InoutJob job,InoutJobdetail jobdetail, InventoryStorage inventory,ScheduleTask task,EntityDAO dao) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = dao.getSession();
            tx = session.beginTransaction();

            //修改货位状态
            if(cargospace!=null){
            	session.update(cargospace);
            }
            
            //保存作业
            if(job!=null){
                session.save(job);	
            }

            //保存作业详细
            if(jobdetail!=null){
            	session.save(jobdetail);
            }
            
            //保存库存
            if(inventory!=null){
                session.save(inventory);
            }
			//保存调度
			if (task != null) {
				session.save("ScheduleTask",task);
			}
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("入库增加,修改货位状态,添加作业和作业详细、库存、调度,出错："+er.getMessage());
        }finally{
        	dao.closeSession(session);
        }
	}
    /**
     * 作业作废
     * @param job
     * @param cargospace
     * @param inventory
     * @param dao
     * @throws Exception
     */
	public void ZFJobupdate(InoutJob job,BaseCargospace cargospace,InboundDetail detail, InventoryStorage inventory,ScheduleTask task,EntityDAO dao) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
        	
            session = dao.getSession();
            tx = session.beginTransaction();
            //修改作业
            if(job!=null){
                session.update(job);	
            }

            //修改货位状态
            if(cargospace!=null){
            	session.update(cargospace);
            }
            
            //修改作业
            if(detail!=null){
            	session.update(detail);
            }
          //修改调度指令
            if(detail!=null){
            	session.update("ScheduleTask",task);
            }
            //保存库存
            if(inventory!=null){
                session.update(inventory);
            }

            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("手动完成作业,添加库存,修改货位状态,修改入库单明细状态时候出错："+er.getMessage());
        }finally{
        	dao.closeSession(session);
        }
	}
	/**
	 * Rf 复核作业
	 * @param jobdetail
	 * @param inventory
	 * @param dao
	 * @throws Exception
	 */
		public void fhJob(InoutJobdetail jobdetail,InventoryStorage inventory,EntityDAO dao) throws Exception {
			
			Session session = null;
	        Transaction tx = null;
	        try{
	        	
	            session = dao.getSession();
	            tx = session.beginTransaction();
	            //修改作业详细
	            if(jobdetail!=null){
	                session.update(jobdetail);	
	            }
	            
	            //保存库存
	            if(inventory!=null){
	                session.save(inventory);
	            }

	            tx.commit();    
	        }catch(Exception er){
	            if(tx != null){
	                tx.rollback();
	            }
	            throw new  Exception("RF复核保存时出错：作业号为"+jobdetail.getJobid()+"   "+er.getMessage());
	        }finally{
	        	dao.closeSession(session);
	        }
		}

	/**
	 * Rf 复核作业(调度)
	 * 
	 * @param jobdetail
	 * @param inventory
	 * @param dao
	 * @throws Exception
	 */
	public void fhJobz(InoutJobdetail jobdetail, InventoryStorage inventory,ScheduleTask task,EntityDAO dao) throws Exception {

		Session session = null;
		Transaction tx = null;
		try {

			session = dao.getSession();
			tx = session.beginTransaction();
			// 修改作业详细
			if (jobdetail != null) {
				session.update(jobdetail);
			}

			// 保存库存
			if (inventory != null) {
				session.save(inventory);
			}
			
			// 删除调度
			if (task != null) {
				session.delete(task);
			}

			tx.commit();
		} catch (Exception er) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception("RF复核保存时出错：作业号为" + jobdetail.getJobid() + "   " + er.getMessage());
		} finally {
			dao.closeSession(session);
		}
	}
	/**
	 * Rf 复核作业
	 * @param jobdetail
	 * @param inventory
	 * @param dao
	 * @throws Exception
	 */
		public void fhupdateJob(InoutJobdetail jobdetail,InventoryStorage inventory,EntityDAO dao) throws Exception {
			
			Session session = null;
	        Transaction tx = null;
	        try{
	        	
	            session = dao.getSession();
	            tx = session.beginTransaction();
	            //修改作业详细
	            if(jobdetail!=null){
	                session.update(jobdetail);	
	            }
	            
	            //保存库存
	            if(inventory!=null){
	                session.update(inventory);
	            }

	            tx.commit();    
	        }catch(Exception er){
	            if(tx != null){
	                tx.rollback();
	            }
	            throw new  Exception("RF复核保存时出错：作业号为"+jobdetail.getJobid()+"   "+er.getMessage());
	        }finally{
	        	dao.closeSession(session);
	        }
		}
	/**
	 * 功能:根据作业id查询作业
	 * @param jobid			作业号
	 * @return 
	 * @throws Exception
	 */
	public InoutJob getJobByJobid(String jobid,EntityDAO dao) throws Exception {
		
		if(jobid != null){
		
			String strSql = " from InoutJob as t where t.jobid='" + jobid + "'";
			List ls = dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (InoutJob)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * 功能:根据托盘条码查询作业 立体库的作业 非暂存作业
	 * @param traycode			托盘条码id
	 * @return 
	 * @throws Exception
	 */
	public Object[] getJobByJobTraycode(String traycode,String warehouseid, EntityDAO dao) throws Exception {
		
		if(traycode != null && !traycode.equals("")){
		
			String strSql = "select out.customername,de.m_strProductName,de.printdate,de.lotinfo,de.jobnum,de.assignnum,t.jobid  from OutboundInvoiceHeader as out,InoutJobdetail as de,InoutJob as t where t.jobid=de.jobid and t.boundstockid=out.outstockid and t.inOutType='2' and out.outstatus not in('7','8') and t.traycode='"+traycode+"' and t.jobcategory ='1' order by t.createtime desc ";
			if(warehouseid!=null ){
				strSql += " and t.warehouseid = '"+warehouseid+"'";
			}
			List ls = dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (Object[])ls.get(0);
			}
		}
		return null;
	}

	
	
	
	public String getJobcategoryname() {
		return jobcategoryname;
	}

	public void setJobcategoryname(String jobcategoryname) {
		this.jobcategoryname = jobcategoryname;
	}

	/**
	 * 作业及作业明细部分建单查询
	 * @param warehouseid
	 * @param indate_from
	 * @param indate_to
	 * @param productid
	 * @param jobtype
	 * @return
	 */
	public String getQueryJobADetailHQLPart(String warehouseid,String indate_from,String indate_to,String productid,String jobtype)
	{
		String hql = " from InoutJob ta,InoutJobdetail tb where 1=1 and ta.jobid=tb.jobid and ta.status='4' and tb.isinvoice='N' ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(indate_from != null && indate_from.trim().length() > 0){
			hql += " and ta.createtime >= '"+indate_from+"'";
		}
		if(indate_to != null && indate_to.trim().length() > 0){
			hql += " and ta.createtime <= '"+indate_to+" 24:60:60'";
		}
		if(jobtype != null && jobtype.trim().length() > 0){
			hql += " and ta.jobtype='"+jobtype+"'";
		}else{
			hql += " and ta.jobtype<>'hl'";
		}
		if(productid != null && productid.trim().length() > 0){
			hql += " and tb.productid ='"+productid+"'";
		}
		
		hql += " order by ta.warehouseid,ta.createtime";
		return hql;
	}
	
	/**
	 * 作业及作业明细部分建单查询
	 * @param warehouseid
	 * @param indate_from
	 * @param indate_to
	 * @param productid
	 * @param jobtype
	 * @return
	 */
	public String getQueryJobADetailHQLCountPart(String warehouseid,String indate_from,String indate_to,String productid,String jobtype)
	{
		String hql = " select count(*) from InoutJob ta,InoutJobdetail tb where 1=1 and ta.jobid=tb.jobid and ta.status='4' and tb.isinvoice='N' ";
		if(warehouseid != null && warehouseid.trim().length() > 0){
			hql += " and ta.warehouseid='"+warehouseid+"'";
		}
		if(indate_from != null && indate_from.trim().length() > 0){
			hql += " and ta.createtime >= '"+indate_from+"'";
		}
		if(indate_to != null && indate_to.trim().length() > 0){
			hql += " and ta.createtime <= '"+indate_to+" 24:60:60'";
		}
		if(jobtype != null && jobtype.trim().length() > 0){
			hql += " and ta.jobtype='"+jobtype+"'";
		}else{
			hql += " and ta.jobtype<>'hl'";
		}
		if(productid != null && productid.trim().length() > 0){
			hql += " and tb.productid ='"+productid+"'";
		}
		return hql;
	}
	
}