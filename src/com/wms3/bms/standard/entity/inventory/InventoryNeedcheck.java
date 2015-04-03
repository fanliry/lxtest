package com.wms3.bms.standard.entity.inventory;

/**
 * 描述：需盘点
 * @author hug
 *
 */
public class InventoryNeedcheck  implements java.io.Serializable {
	 
	private static final long serialVersionUID = -7264087671584351975L;
	 private String needcheckid;	//ID
	 private String taskid;			//调度任务编号
	 private String jobid;			//作业号
     private String inouttype;		//入出类型1,入库类型 2，出库类型
     private String status;			//状态 0--新建  1--正在调整 2--调整完成

	 private String cargoSpaceId;	//作业货位
     private String createtime;		//生成时间
     private String handleflag;		//处理标识 Y,N
     private String handlecontent;	//处理内容
     private String traycode;		//托盘条码
     private String taskno;			//任务号
     private String handletime;			//处理时间
     private String handleman;			//处理人
     private String warehouseid;    //仓库ID
     
     
     /*派生属性*/
 	private String m_strCargoSpaceName;			/*货位名称*/
 	private String handlemanname;		//处理人名
    private String warehousename;    	    //收货仓库名
    private String statusname;	//状态名
 	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getM_strCargoSpaceName() {
 		return m_strCargoSpaceName;
 	}

 	public void setM_strCargoSpaceName(String cargoSpaceName) {
 		m_strCargoSpaceName = cargoSpaceName;
 	}

	/** default constructor */
    public InventoryNeedcheck() {
    }
    
	/** minimal constructor */
    public InventoryNeedcheck(String inouttype, String createtime, String handleflag) {
        this.inouttype = inouttype;
        this.createtime = createtime;
        this.handleflag = handleflag;
    }



	// Property accessors
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
     * 功能：获得ID
     */
    public String getNeedcheckid() {
        return this.needcheckid;
    }
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    /**
     * 功能：设置ID
     * @author hug 2012-3-6 
     * @param needcheckid
     */
    public void setNeedcheckid(String needcheckid) {
        this.needcheckid = needcheckid;
    }
    /**
     * 功能：获得作业号
     * @author hug 2012-3-6 
     * @return
     */
    public String getJobid() {
        return this.jobid;
    }
    /**
     * 功能：设置作业号
     * @author hug 2012-3-6 
     * @param jobid
     */
    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
    /**
     * 功能：获得入出类型
     * @author hug 2012-3-6 
     * @return
     */
    public String getInouttype() {
        return this.inouttype;
    }
    /**
     * 功能：设置入出类型
     * @author hug 2012-3-6 
     * @param inouttype
     */
    public void setInouttype(String inouttype) {
        this.inouttype = inouttype;
    }
    /**
     * 功能：获得作业货位
     * @author hug 2012-3-6 
     * @return
     */
    public String getCargoSpaceId() {
        return this.cargoSpaceId;
    }
    /**
     * 功能：设置作业货位
     * @author hug 2012-3-6 
     * @param cargoSpaceId
     */
    public void setCargoSpaceId(String cargoSpaceId) {
        this.cargoSpaceId = cargoSpaceId;
    }
    /**
     * 功能：获得生成时间
     * @author hug 2012-3-6 
     * @return
     */
    public String getCreatetime() {
        return this.createtime;
    }
    /**
     * 功能：设置生成时间
     * @author hug 2012-3-6 
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**
     * 功能：获得处理标识
     * @author hug 2012-3-6 
     * @return
     */
    public String getHandleflag() {
        return this.handleflag;
    }
    /**
     * 功能：设置处理标识
     * @author hug 2012-3-6 
     * @param handleflag
     */
    public void setHandleflag(String handleflag) {
        this.handleflag = handleflag;
    }
    /**
     * 功能：获得处理内容
     * @author hug 2012-3-6 
     * @return
     */
    public String getHandlecontent() {
        return this.handlecontent;
    }
    /**
     * 功能：设置处理内容
     * @author hug 2012-3-6 
     * @param handlecontent
     */
    public void setHandlecontent(String handlecontent) {
        this.handlecontent = handlecontent;
    }

	public String getHandletime() {
		return handletime;
	}

	public void setHandletime(String handletime) {
		this.handletime = handletime;
	}

	public String getHandleman() {
		return handleman;
	}

	public void setHandleman(String handleman) {
		this.handleman = handleman;
	}

	public String getHandlemanname() {
		return handlemanname;
	}

	public void setHandlemanname(String handlemanname) {
		this.handlemanname = handlemanname;
	}

	public String getWarehouseid() {
		return warehouseid;
	}

	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	/**
	 * 出入库异常查询
	 * @param warehouseid
	 * @param start_time
	 * @param end_time
	 * @param is_do
	 * @param inouttype
	 * @return
	 */
		public String getQueryHQL(String warehouseid,String start_time,String end_time,String is_do,String inouttype)
		{
			String hql = "from InventoryNeedcheck where 1=1 ";
			if(warehouseid != null && warehouseid.trim().length() > 0){
				hql += " and warehouseid='"+warehouseid+"'";
			}
			if(start_time != null && start_time.trim().length() > 0){
				hql += " and createtime>='"+start_time+"'";
			}
			if(end_time != null && end_time.trim().length() > 0){
				hql += " and createtime<='"+end_time+" 24:60:60'";
			}
			if(is_do != null && is_do.trim().length() > 0){
				hql += " and handleflag='"+is_do+"'";
			}
			if(inouttype != null && inouttype.trim().length() > 0){
				hql += " and inouttype='"+inouttype+"'";
			}
			hql += " order by createtime";
			return hql;
		}
		/**
		 * 出入库异常查询
		 * @param warehouseid
		 * @param start_time
		 * @param end_time
		 * @param is_do
		 * @param inouttype
		 * @return
		 */
			public String getQueryHQLCount(String warehouseid,String start_time,String end_time,String is_do,String inouttype)
			{
				String hql = "select count(*) from InventoryNeedcheck where 1=1 ";
				if(warehouseid != null && warehouseid.trim().length() > 0){
					hql += " and warehouseid='"+warehouseid+"'";
				}
				if(start_time != null && start_time.trim().length() > 0){
					hql += " and createtime>='"+start_time+"'";
				}
				if(end_time != null && end_time.trim().length() > 0){
					hql += " and createtime<='"+end_time+" 24:60:60'";
				}
				if(is_do != null && is_do.trim().length() > 0){
					hql += " and handleflag='"+is_do+"'";
				}
				if(inouttype != null && inouttype.trim().length() > 0){
					hql += " and inouttype='"+inouttype+"'";
				}
				return hql;
			}
   
			/**
			 * 查询盘点信息
			 * @param warehouseid
			 * @return
			 */
			public String getQueryHQLByid(String needcheckid)
			{
				String hql = "from InventoryNeedcheck where 1=1 ";
				if(needcheckid != null && needcheckid.trim().length() > 0){
					hql += " and needcheckid='"+needcheckid+"'";
				}
				return hql;
			}







}