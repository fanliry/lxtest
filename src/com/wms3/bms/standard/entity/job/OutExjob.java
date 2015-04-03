package com.wms3.bms.standard.entity.job;

/**
 * 
 * 描述: 异常作业表
 * @author hug 2012-3-6
 * @since WMS 3.0
 */
public class OutExjob  implements java.io.Serializable {

    private static final long serialVersionUID = 3825596874243444441L;
     private String exjobid;        //异常ID
     private String jobid;          //调度作业编号
     private String exstatus;       //执行任务状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
     private String createtime;     //执行任务生成时间 时间格式：yyyy-MM-dd hh:mm:ss
     private String readtime;       //执行任务读走时间 时间格式：yyyy-MM-dd hh:mm:ss
     private String cargoAlleyId;   //巷道ID
     private String cargoSpaceId;   //货位ID
     private String traycode;       //托盘条码
     private String messagecode;    //消息代码
     private String message;        //执行消息


    // Constructors

    /** default constructor */
    public OutExjob() {
    }

	/** minimal constructor */
    public OutExjob(String exstatus) {
        this.exstatus = exstatus;
    }
    
    /** full constructor */
    public OutExjob(String jobid, String exstatus, String createtime, String readtime, String cargoAlleyId, String cargoSpaceId, String traycode, String messagecode, String message) {
        this.jobid = jobid;
        this.exstatus = exstatus;
        this.createtime = createtime;
        this.readtime = readtime;
        this.cargoAlleyId = cargoAlleyId;
        this.cargoSpaceId = cargoSpaceId;
        this.traycode = traycode;
        this.messagecode = messagecode;
        this.message = message;
    }

   
    // Property accessors
    /**
     * 功能：获得异常ID
     */
    public String getExjobid() {
        return this.exjobid;
    }
    /**
     * 功能：设置异常ID
     * @author hug 2012-3-6 
     * @param exjobid
     */
    public void setExjobid(String exjobid) {
        this.exjobid = exjobid;
    }
    /**
     * 功能：获得调度作业编号
     * @author hug 2012-3-6 
     * @return
     */
    public String getJobid() {
        return this.jobid;
    }
    /**
     * 功能：设置调度作业编号
     * @author hug 2012-3-6 
     * @param jobid
     */
    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
    /**
     * 功能：获得执行任务状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
     * @author hug 2012-3-6 
     * @return
     */
    public String getExstatus() {
        return this.exstatus;
    }
    /**
     * 功能：设置执行任务状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
     * @author hug 2012-3-6 
     * @param exstatus
     */
    public void setExstatus(String exstatus) {
        this.exstatus = exstatus;
    }
    /**
     * 功能：获得执行任务生成时间 时间格式：yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @return
     */
    public String getCreatetime() {
        return this.createtime;
    }
    /**
     * 功能：设置执行任务生成时间 时间格式：yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**
     * 功能：获得执行任务读走时间 时间格式：yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @return
     */
    public String getReadtime() {
        return this.readtime;
    }
    /**
     * 功能：设置执行任务读走时间 时间格式：yyyy-MM-dd hh:mm:ss
     * @author hug 2012-3-6 
     * @param readtime
     */
    public void setReadtime(String readtime) {
        this.readtime = readtime;
    }
    /**
     * 功能：获得巷道ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getCargoAlleyId() {
        return this.cargoAlleyId;
    }
    /**
     * 功能：设置巷道ID
     * @author hug 2012-3-6 
     * @param cargoAlleyId
     */
    public void setCargoAlleyId(String cargoAlleyId) {
        this.cargoAlleyId = cargoAlleyId;
    }
    /**
     * 功能：获得货位ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getCargoSpaceId() {
        return this.cargoSpaceId;
    }
    /**
     * 功能：设置货位ID
     * @author hug 2012-3-6 
     * @param cargoSpaceId
     */
    public void setCargoSpaceId(String cargoSpaceId) {
        this.cargoSpaceId = cargoSpaceId;
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
     * 功能：获得消息代码
     * @author hug 2012-3-6 
     * @return
     */
    public String getMessagecode() {
        return this.messagecode;
    }
    /**
     * 功能：设置消息代码
     * @author hug 2012-3-6 
     * @param messagecode
     */
    public void setMessagecode(String messagecode) {
        this.messagecode = messagecode;
    }
    /**
     * 功能：获得执行消息
     * @author hug 2012-3-6 
     * @return
     */
    public String getMessage() {
        return this.message;
    }
    /**
     * 功能：设置执行消息
     * @author hug 2012-3-6 
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
   








}