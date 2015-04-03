package com.wms3.bms.standard.entity.inbound;

/**
 * 
 * 描述: 入库单
 * @author hug 2012-3-9
 * @since WMS 3.0
 */
public class InboundInvoiceHeader implements java.io.Serializable {

    private static final long serialVersionUID = -747802160323265188L;
    
     private String instockid;      	//入库单据编号
     private String warehouseid;    	//仓库编号
     private String invoicedate;    	//单据日期
     private String createtime;     	//单据生成时间
     private String createmanid;    	//单据生成人员编号
     private String instatus;       	//单据状态 0-新建，1-审核，2-确认 5-作废 
     private String intype;         	//入库类型
     private String opmode;         	//单据处理方式1-正常单据（默认）2-账目单据
     private String uploadflag;     	//上传标志0-已上传（默认）1-未上传
     private String auditmanid;     	//审核人Id
     private String auditdate;      	//审核日期
     private String confirmanid;    	//确认人Id
     private String confirmdate;    	//确认日期
     private String departid;       	//部门编号
     private String shiftid;        	//作业班次
     private String onLineType;     	//联机模式 1.联机（默认）2.脱机
     private String inpos;          	//入库点
     private String invoicesource;  	//单据来源(作业来源 1：直接采集生成的作业，2：按收货单来生成的作业，3：按入库单来生成的作业 4:按ERP其它单来生成的作业)
     private String customerid;  		//客户
     
     //派生属性
     protected String m_strStatusName;	// 状态名
     protected String m_strTypeName;	// 类型名
     protected String createman;		// 单据生成人员
     protected String customername;		// 客户名



    // Constructors

    /** default constructor */
    public InboundInvoiceHeader() {
    }

	/** minimal constructor */
    public InboundInvoiceHeader(String warehouseid, String createtime, String createmanid, String opmode) {
        this.warehouseid = warehouseid;
        this.createtime = createtime;
        this.createmanid = createmanid;
        this.opmode = opmode;
    }

   
    // Property accessors
    /**
     * 功能：获得入库单据编号
     */
    public String getInstockid() {
        return this.instockid;
    }
    /**
     * 功能：设置入库单据编号
     * @author hug 2012-3-9 
     * @param instockid
     */
    public void setInstockid(String instockid) {
        this.instockid = instockid;
    }
    /**
     * 功能：获得仓库编号
     * @author hug 2012-3-9 
     * @return
     */
    public String getWarehouseid() {
        return this.warehouseid;
    }
    /**
     * 功能：设置仓库编号
     * @author hug 2012-3-9 
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }

    /**
     * 功能：获得默认为生成时间
     * @author hug 2012-3-9 
     * @return
     */
    public String getInvoicedate() {
        return this.invoicedate;
    }
    /**
     * 功能：设置默认为生成时间
     * @author hug 2012-3-9 
     * @param invoicedate
     */
    public void setInvoicedate(String invoicedate) {
        this.invoicedate = invoicedate;
    }
    /**
     * 功能：获得单据生成时间
     * @author hug 2012-3-9 
     * @return
     */
    public String getCreatetime() {
        return this.createtime;
    }
    /**
     * 功能：设置单据生成时间
     * @author hug 2012-3-9 
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**
     * 功能：获得单据生成人员编号
     * @author hug 2012-3-9 
     * @return
     */
    public String getCreatemanid() {
        return this.createmanid;
    }
    /**
     * 功能：设置单据生成人员编号
     * @author hug 2012-3-9 
     * @param createmanid
     */
    public void setCreatemanid(String createmanid) {
        this.createmanid = createmanid;
    }
    
    
    /**
     * 功能：获得单据状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
     * @author hug 2012-3-9 
     * @return
     */
    public String getInstatus() {
        return this.instatus;
    }
    /**
     * 功能：设置单据状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
     * @author hug 2012-3-9 
     * @param instatus
     */
    public void setInstatus(String instatus) {
        this.instatus = instatus;
    }
    /**
     * 功能：获得入库类型
     * @author hug 2012-3-9 
     * @return
     */
    public String getIntype() {
        return this.intype;
    }
    /**
     * 功能：设置入库类型
     * @author hug 2012-3-9 
     * @param intype
     */
    public void setIntype(String intype) {
        this.intype = intype;
    }
    /**
     * 功能：获得单据来源
     * @author hug 2012-3-9 
     * @return
     */
    public String getInvoicesource() {
        return this.invoicesource;
    }
    /**
     * 功能：设置单据来源
     * @author hug 2012-3-9 
     * @param invoicesource
     */
    public void setInvoicesource(String invoicesource) {
        this.invoicesource = invoicesource;
    }
    /**
     * 功能：获得单据处理方式1-正常单据（默认）2-账目单据
     * @author hug 2012-3-9 
     * @return
     */
    public String getOpmode() {
        return this.opmode;
    }
    /**
     * 功能：设置单据处理方式1-正常单据（默认）2-账目单据
     * @author hug 2012-3-9 
     * @param opmode
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode;
    }
    /**
     * 功能：获得上传标志0-已上传（默认）1-未上传
     * @author hug 2012-3-9 
     * @return
     */
    public String getUploadflag() {
        return this.uploadflag;
    }
    /**
     * 功能：设置上传标志0-已上传（默认）1-未上传
     * @author hug 2012-3-9 
     * @param uploadflag
     */
    public void setUploadflag(String uploadflag) {
        this.uploadflag = uploadflag;
    }

    /**
     * 功能：获得部门编号
     * @author hug 2012-3-9 
     * @return
     */
    public String getDepartid() {
        return this.departid;
    }
    /**
     * 功能：设置部门编号
     * @author hug 2012-3-9 
     * @param departid
     */
    public void setDepartid(String departid) {
        this.departid = departid;
    }
    /**
     * 功能：获得作业班次
     * @author hug 2012-3-9 
     * @return
     */
    public String getShiftid() {
        return this.shiftid;
    }
    /**
     * 功能：设置作业班次
     * @author hug 2012-3-9 
     * @param shiftid
     */
    public void setShiftid(String shiftid) {
        this.shiftid = shiftid;
    }
    /**
     * 功能：获得联机模式 1.联机（默认）2.脱机
     * @author hug 2012-3-9 
     * @return
     */
    public String getOnLineType() {
        return this.onLineType;
    }
    /**
     * 功能：设置联机模式 1.联机（默认）2.脱机
     * @author hug 2012-3-9 
     * @param onLineType
     */
    public void setOnLineType(String onLineType) {
        this.onLineType = onLineType;
    }
    /**
     * 功能：获得入库点
     * @author hug 2012-3-9 
     * @return
     */
    public String getInpos() {
        return this.inpos;
    }
    /**
     * 功能：设置入库点
     * @author hug 2012-3-9 
     * @param inpos
     */
    public void setInpos(String inpos) {
        this.inpos = inpos;
    }
    
    /**
     * 功能:获得审核日期
     * @author hug 2012-3-22 
     * @return
     */
    public String getAuditdate() {
        return auditdate;
    }

    /**
     * 功能：设置审核日期
     * @author hug 2012-3-22 
     * @param auditdate
     */
    public void setAuditdate(String auditdate) {
        this.auditdate = auditdate;
    }

    /**
     * 功能:获得审核人Id
     * @author hug 2012-3-22 
     * @return
     */
    public String getAuditmanid() {
        return auditmanid;
    }

    /**
     * 功能:设置审核人Id
     * @author hug 2012-3-22 
     * @param auditmanid
     */
    public void setAuditmanid(String auditmanid) {
        this.auditmanid = auditmanid;
    }

    /**
     * 功能：获得确认人Id
     * @author hug 2012-3-22 
     * @return
     */
    public String getConfirmanid() {
        return confirmanid;
    }

    /**
     * 功能:设置确认人Id
     * @author hug 2012-3-22 
     * @param confirmanid
     */
    public void setConfirmanid(String confirmanid) {
        this.confirmanid = confirmanid;
    }

    /**
     * 功能：获得确认日期
     * @author hug 2012-3-22 
     * @return
     */
    public String getConfirmdate() {
        return confirmdate;
    }

    /**
     * 功能:设置确认日期
     * @author hug 2012-3-22 
     * @param confirmdate
     */
    public void setConfirmdate(String confirmdate) {
        this.confirmdate = confirmdate;
    }


    /**
     * 功能：获得单据生成人员
     * @author hug 2012-6-25 
     * @return
     */
    public String getCreateman() {
        return createman;
    }

    /**
     * 功能：设置单据生成人员
     * @author hug 2012-6-25 
     * @param createman
     */
    public void setCreateman(String createman) {
        this.createman = createman;
    }

    /**
     * 功能：获得状态名
     * @author hug 2012-6-25 
     * @return
     */
    public String getM_strStatusName() {
        return m_strStatusName;
    }

    /**
     * 功能：设置状态名
     * @author hug 2012-6-25 
     * @param statusName
     */
    public void setM_strStatusName(String statusName) {
        m_strStatusName = statusName;
    }

    /**
     * 功能：获得类型名
     * @author hug 2012-6-25 
     * @return
     */
    public String getM_strTypeName() {
        return m_strTypeName;
    }

    /**
     * 功能：设置类型名
     * @author hug 2012-6-25 
     * @param typeName
     */
    public void setM_strTypeName(String typeName) {
        m_strTypeName = typeName;
    }

	/**
	 * 功能：获得客户
	 */
	public String getCustomerid() {
		return customerid;
	}

	/**
	 * 功能：设置客户
	 * @param customerid
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	/**
	 * 功能：获得客户名
	 */
	public String getCustomername() {
		return customername;
	}

	/**
	 * 功能：设置客户名
	 * @param customername
	 */
	public void setCustomername(String customername) {
		this.customername = customername;
	}
    
    

}