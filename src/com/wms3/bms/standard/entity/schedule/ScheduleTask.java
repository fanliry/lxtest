package com.wms3.bms.standard.entity.schedule;
/**
 * 
 * 描述: 调度任务接口表
 * @author YAO
 * @since WMS 3.0
 */
public class ScheduleTask  implements java.io.Serializable {

	/**
	 * tasktype(调度类型)与 route(执行路线) 
	 * 入库 tasktype in('1') and route in('1')
	 * 回流 tasktype in('3') and route in('2') 从出库输送机回流
	 * 回流 tasktype in('3') and route in('1') 从入库输送机回流
	 * 直接出库 tasktype in('2') and route in('1')
	 * 部分出库 tasktype in('2') and route in('2')
	 */
	public String taskid;			//调度任务编号
	public String jobid;			//作业id
	public String tasktype;			//调度类型 1，入库  2，出库   3，回流
	public String floor;			//楼层           1，一楼  2，二楼
	public Integer splatoon;		//源货位排
	public Integer scolumn;			//源货位列
	public Integer sfloor;			//源货位层
	public Integer tplatoon;		//目标货位排
	public Integer tcolumn;		    //目标货位列
	public Integer tfloor;			//目标货位层
    
	public String cargoSpaceId;        // 货位ID
	public String cargoAlleyId;        // 巷道ID
	public String warehouseid;         // 仓库ID
	public String whAreaId;            // 库区ID
	
	public String TSJH;                  // 提升输送机号
    
	



	public String status;			//任务状态  2.待执行   3.正执行 4.完成 5.作废  6.同步完成  8.同步作废
	public Integer priority;		//优先级
	public String taskpos;			//任务方向 1.入库端 2.出库端
	public String createtime;		//时间 时间格式：yyyy-MM-dd hh:mm:ss
	public String starttime;		//开始时间 时间格式：yyyy-MM-dd hh:mm:ss
	public String asexectime;		//堆垛机 执行时间 时间格式：yyyy-MM-dd hh:mm:ss
	public String finishtime;		//完成时间 时间格式：yyyy-MM-dd hh:mm:ss
	public String snumber;			//输送号
	public String traycode;			//托盘条码
	public String route;			//执行路线 1-直入/直出  2-回流
	public String taskno;			//任务号
	public String commkind;			//指令格式
	public String cargoAreaId;      // 货区ID
	public String jobtype;         	//作业类型 
    public int phase ;         		//任务执行阶段（描述）1.下发阶段  2.堆垛机执行阶段  3.小车执行阶段 4.输送机执行阶段  7.完成
    
    public int jym ;         		//输送机上的校验码
    
    //派生
  	private String whareaName;
	private String cargoAlleyName;
  	private String cargoSpaceName;
  	private String statusName;
  	private String tasktypeName;

	// Constructors
	/** default constructor */
    public ScheduleTask() {
    }
    public String getJobid() {
		return jobid;
	}

	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
  	public String getWhareaName() {
		return whareaName;
	}

	public void setWhareaName(String whareaName) {
		this.whareaName = whareaName;
	}

	public String getCargoAlleyName() {
		return cargoAlleyName;
	}

	public void setCargoAlleyName(String cargoAlleyName) {
		this.cargoAlleyName = cargoAlleyName;
	}

	public String getCargoSpaceName() {
		return cargoSpaceName;
	}

	public void setCargoSpaceName(String cargoSpaceName) {
		this.cargoSpaceName = cargoSpaceName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getTasktypeName() {
		return tasktypeName;
	}

	public void setTasktypeName(String tasktypeName) {
		this.tasktypeName = tasktypeName;
	}
    // Property accessors
	/**
	 * 功能：获得堆垛机 执行时间
	 */
	public String getAsexectime() {
		return asexectime;
	}

	/**
	 * 功能：设置堆垛机 执行时间
	 * @param asexectime
	 */
	public void setAsexectime(String asexectime) {
		this.asexectime = asexectime;
	}

	/**
	 * 功能：获得指令格式
	 */
	public String getCommkind() {
		return commkind;
	}

	/**
	 * 功能：设置指令格式
	 * @param commkind
	 */
	public void setCommkind(String commkind) {
		this.commkind = commkind;
	}
	public String getTSJH() {
		return TSJH;
	}

	public void setTSJH(String tSJH) {
		TSJH = tSJH;
	}
	/**
	 * 功能：获得时间
	 */
	public String getCreatetime() {
		return createtime;
	}

	/**
	 * 功能：设置时间
	 * @param createtime
	 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	/**
	 * 功能：获得完成时间
	 */
	public String getFinishtime() {
		return finishtime;
	}

	/**
	 * 功能：设置完成时间
	 * @param finishtime
	 */
	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}

	/**
	 * 功能：获得优先级
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * 功能：设置优先级
	 * @param priority
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * 功能：获得执行路线 1-直入/直出 2-回流
	 */
	public String getRoute() {
		return route;
	}

	/**
	 * 功能：设置执行路线 1-直入/直出 2-回流
	 * @param route
	 */
	public void setRoute(String route) {
		this.route = route;
	}

	/**
	 * 功能：获得源货位列
	 */
	public Integer getScolumn() {
		return scolumn;
	}

	/**
	 * 功能：设置源货位列
	 * @param scolumn
	 */
	public void setScolumn(Integer scolumn) {
		this.scolumn = scolumn;
	}

	/**
	 * 功能：获得源货位层
	 */
	public Integer getSfloor() {
		return sfloor;
	}

	/**
	 * 功能：设置源货位层
	 * @param sfloor
	 */
	public void setSfloor(Integer sfloor) {
		this.sfloor = sfloor;
	}

	/**
	 * 功能：获得输送号
	 */
	public String getSnumber() {
		return snumber;
	}

	/**
	 * 功能：设置输送号
	 * @param snumber
	 */
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	/**
	 * 功能：获得源货位排
	 */
	public Integer getSplatoon() {
		return splatoon;
	}

	/**
	 * 功能：设置源货位排
	 * @param splatoon
	 */
	public void setSplatoon(Integer splatoon) {
		this.splatoon = splatoon;
	}

	/**
	 * 功能：获得开始时间
	 */
	public String getStarttime() {
		return starttime;
	}

	/**
	 * 功能：设置开始时间
	 * @param starttime
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/**
	 * 功能：获得任务状态
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 功能：设置任务状态
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 功能：获得调度任务编号
	 */
	public String getTaskid() {
		return taskid;
	}

	/**
	 * 功能：设置调度任务编号
	 * @param taskid
	 */
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	/**
	 * 功能：获得任务号
	 */
	public String getTaskno() {
		return taskno;
	}

	/**
	 * 功能：设置任务号
	 * @param taskno
	 */
	public void setTaskno(String taskno) {
		this.taskno = taskno;
	}

	/**
	 * 功能：获得任务方向 1.入库端 2.出库端
	 */
	public String getTaskpos() {
		return taskpos;
	}

	/**
	 * 功能：设置任务方向 1.入库端 2.出库端
	 * @param taskpos
	 */
	public void setTaskpos(String taskpos) {
		this.taskpos = taskpos;
	}
	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}
	/**
	 * 功能：获得调度类型 1-入库 2-出库 3-回流
	 */
	public String getTasktype() {
		return tasktype;
	}

	/**
	 * 功能：设置调度类型 1-入库 2-出库 3-回流
	 * @param tasktype
	 */
	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	/**
	 * 功能：获得目标货位列
	 */
	public Integer getTcolumn() {
		return tcolumn;
	}

	/**
	 * 功能：设置目标货位列
	 * @param tcolumn
	 */
	public void setTcolumn(Integer tcolumn) {
		this.tcolumn = tcolumn;
	}

	/**
	 * 功能：获得目标货位层
	 */
	public Integer getTfloor() {
		return tfloor;
	}

	/**
	 * 功能：设置目标货位层
	 * @param tfloor
	 */
	public void setTfloor(Integer tfloor) {
		this.tfloor = tfloor;
	}

	/**
	 * 功能：获得目标货位排
	 */
	public Integer getTplatoon() {
		return tplatoon;
	}

	/**
	 * 功能：设置目标货位排
	 * @param tplatoon
	 */
	public void setTplatoon(Integer tplatoon) {
		this.tplatoon = tplatoon;
	}

	/**
	 * 功能：获得托盘条码
	 */
	public String getTraycode() {
		return traycode;
	}

	/**
	 * 功能：设置托盘条码
	 * @param traycode
	 */
	public void setTraycode(String traycode) {
		this.traycode = traycode;
	}
   
    /**
     * 功能：获得巷道ID
     */
    public String getCargoAlleyId() {
        return this.cargoAlleyId;
    }
    
    /**
     * 功能：设置巷道ID
     * @param cargoAlleyId
     */     
    public void setCargoAlleyId(String cargoAlleyId) {
        this.cargoAlleyId = cargoAlleyId;
    }
    /**
     * 功能：获得仓库ID
     */
    public String getWarehouseid() {
        return this.warehouseid;
    }
    
    /**
     * 功能：设置仓库ID
     * @param warehouseid
     */     
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }
    
    /**
     * 功能：获得库区ID
     */
    public String getWhAreaId() {
        return this.whAreaId;
    }
    
    /**
     * 功能：设置库区ID
     * @param whAreaId
     */     
    public void setWhAreaId(String whAreaId) {
        this.whAreaId = whAreaId;
    }
    /**
     * 功能：获得货位ID
     */
    public String getCargoSpaceId() {
        return this.cargoSpaceId;
    }
    
    /**
     * 功能：设置货位ID
     * @param cargoSpaceId
     */
    public void setCargoSpaceId(String cargoSpaceId) {
        this.cargoSpaceId = cargoSpaceId;
    }
    public String getCargoAreaId() {
		return cargoAreaId;
	}

	public void setCargoAreaId(String cargoAreaId) {
		this.cargoAreaId = cargoAreaId;
	}

	public String getJobtype() {
		return jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public int getJym() {
		return jym;
	}

	public void setJym(int jym) {
		this.jym = jym;
	}
	
	
}