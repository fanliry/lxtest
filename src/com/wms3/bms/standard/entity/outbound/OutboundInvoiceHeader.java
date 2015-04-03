package com.wms3.bms.standard.entity.outbound;


/**
 * 出库单
 * @author hug
 *
 */
public class OutboundInvoiceHeader  implements java.io.Serializable { 
	
	private static final long serialVersionUID = 5578611054539531472L;
	
	private String outstockid;			//出库单据号
	private String outstatus;			//出库单状态 0:开单；1-预配 2-分配 4-完全拣货   7:发货确认 8:作废
	private String outtype;				//出库单据类型
	private String departid;			//部门id
  	private String warehouseid;			//仓库id
   	private String warehousemanid;		//仓库管理员id
  	private String createtime;			//出库单生成时间
  	private String createmanid;			//创建人Id
  	private String formdate;			//单据日期
	private String outpos;				//出库点
	private String vehicleno;			//车牌
  	private String vehiclepos;			//车位
  	private String auditmanid;			//审核人Id
 	private String auditdate;			//审核日期
 	private String confirmanid;			//确认人Id
 	private String confirmdate;			//确认日期
 	private String opmanid;				//操作员
 	private String shiftid;				//作业班次
 	private String saleno;				//外部单据id
 	private String onLineType;			//联机模式 1.联机 2.脱机
 	private String isupload;			//是否上传成功 Y.是 N.否 默认为否
 	private String ownerid;			    //货主
 	private String sendplatform;		//发货月台
 	private String setposition;			//集货位
 	private String shipmentstarttime;	//预期发货时间(from)
 	private String shipmentendtime;		//预期发货时间(to)
 	private String customerid;			//客户ID(收货人)
 	private String address;			    //客户ID(收货人)地址

 	//派生属性
 	private String m_strAffirmManName;		// 确认名
 	private String m_strOpManName;      	// 创建人名 
 	private String m_strOutStatusName;   	// 单据状态名
 	private String m_strOutTypeName;     	// 出库类型名
    private String customername;            // 客户
    private String ownername;               // 货主
    private String departName;			    //部门
    private String warehousename;			//仓库


    // Constructors
    /** default constructor */
    public OutboundInvoiceHeader() {
    }

	/** minimal constructor */
    public OutboundInvoiceHeader(String outstockid, String outtype, String createtime) {
        this.outstockid = outstockid;
        this.outtype = outtype;
        this.createtime = createtime;
    }
   
    // Property accessors
    /**
     * 功能：获得出库单据号
     */
    public String getOutstockid() {
        return this.outstockid;
    }
    /**
     * 功能：设置出库单据号
     * @param outstockid
     */
    public void setOutstockid(String outstockid) {
        this.outstockid = outstockid;
    }
    /**
     * 功能：获得出库单状态 0:开单；1-预配 2-分配  5-部分复核 6-复核 7:发货确认 8:作废
     * @return
     */
    public String getOutstatus() {
        return this.outstatus;
    }
    /**
     * 功能：设置出库单状态 0:开单；1-预配 2-分配  5-部分复核 6-复核 7:发货确认 8:作废
     * @param outstatus
     */
    public void setOutstatus(String outstatus) {
        this.outstatus = outstatus;
    }
    /**
     * 功能：获得出库单据类型
     * @return
     */
    public String getOuttype() {
        return this.outtype;
    }
    /**
     * 功能：设置出库单据类型
     * @param outtype
     */
    public void setOuttype(String outtype) {
        this.outtype = outtype;
    }
    /**
     * 功能：获得部门id
     * @return
     */
    public String getDepartid() {
        return this.departid;
    }
    /**
     * 功能：设置部门id
     * @param departid
     */
    public void setDepartid(String departid) {
        this.departid = departid;
    }
    /**
     * 功能：获得仓库id
     * @return
     */
    public String getWarehouseid() {
        return this.warehouseid;
    }
    /**
     * 功能：设置仓库id
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }
    /**
     * 功能：获得仓库管理员id
     * @return
     */
    public String getWarehousemanid() {
        return this.warehousemanid;
    }
    /**
     * 功能：设置仓库管理员id
     * @param warehousemanid
     */
    public void setWarehousemanid(String warehousemanid) {
        this.warehousemanid = warehousemanid;
    }
    /**
     * 功能：获得出库单生成时间
     * @return
     */
    public String getCreatetime() {
        return this.createtime;
    }
    /**
     * 功能：设置出库单生成时间
     * @param createdate
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**
     * 功能：获得创建人Id
     * @return
     */
    public String getCreatemanid() {
        return this.createmanid;
    }
    /**
     * 功能：设置创建人Id
     * @param createmanid
     */
    public void setCreatemanid(String createmanid) {
        this.createmanid = createmanid;
    }
    /**
     * 功能：获得单据日期
     * @return
     */
    public String getFormdate() {
        return this.formdate;
    }
    /**
     * 功能：设置单据日期
     * @param formdate
     */
    public void setFormdate(String formdate) {
        this.formdate = formdate;
    }
    /**
     * 功能：获得出库点
     * @return
     */
    public String getOutpos() {
        return this.outpos;
    }
    /**
     * 功能：设置出库点
     * @param outpos
     */
    public void setOutpos(String outpos) {
        this.outpos = outpos;
    }
    /**
     * 功能：获得车牌
     * @return
     */
    public String getVehicleno() {
        return this.vehicleno;
    }
    /**
     * 功能：设置车牌
     * @param vehicleno
     */
    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }
    /**
     * 功能：获得车位
     * @return
     */
    public String getVehiclepos() {
        return this.vehiclepos;
    }
    /**
     * 功能：设置车位
     * @param vehiclepos
     */
    public void setVehiclepos(String vehiclepos) {
        this.vehiclepos = vehiclepos;
    }
    /**
     * 功能：获得审核人Id
     * @return
     */
    public String getAuditmanid() {
        return this.auditmanid;
    }
    /**
     * 功能：设置审核人Id
     * @param auditmanid
     */
    public void setAuditmanid(String auditmanid) {
        this.auditmanid = auditmanid;
    }
    /**
     * 功能：获得审核日期
     * @return
     */
    public String getAuditdate() {
        return this.auditdate;
    }
    /**
     * 功能：设置审核日期
     * @param auditdate
     */
    public void setAuditdate(String auditdate) {
        this.auditdate = auditdate;
    }
    /**
     * 功能：获得确认人Id
     * @return
     */
    public String getConfirmanid() {
        return this.confirmanid;
    }
    /**
     * 功能：设置确认人Id
     * @param confirmanid
     */
    public void setConfirmanid(String confirmanid) {
        this.confirmanid = confirmanid;
    }
    /**
     * 功能：获得确认日期
     * @return
     */
    public String getConfirmdate() {
        return this.confirmdate;
    }
    /**
     * 功能：设置确认日期
     * @param confirmdate
     */
    public void setConfirmdate(String confirmdate) {
        this.confirmdate = confirmdate;
    }
    /**
     * 功能：获得操作员
     * @return
     */
    public String getOpmanid() {
        return this.opmanid;
    }
    /**
     * 功能：设置操作员
     * @param opmanid
     */
    public void setOpmanid(String opmanid) {
        this.opmanid = opmanid;
    }
    /**
     * 功能：获得作业班次
     * @return
     */
    public String getShiftid() {
        return this.shiftid;
    }
    /**
     * 功能：设置作业班次
     * @param shiftid
     */
    public void setShiftid(String shiftid) {
        this.shiftid = shiftid;
    }
    /**
     * 功能：获得外部据号
     * @return
     */
    public String getSaleno() {
        return this.saleno;
    }
    /**
     * 功能：设置外部据号
     * @param saleno
     */
    public void setSaleno(String saleno) {
        this.saleno = saleno;
    }
    /**
     * 功能：获得联机模式 1.联机 2.脱机
     * @return
     */
    public String getOnLineType() {
        return this.onLineType;
    }
    /**
     * 功能：设置联机模式 1.联机 2.脱机
     * @param onLineType
     */
    public void setOnLineType(String onLineType) {
        this.onLineType = onLineType;
    }
    /**
     * 功能：获得是否上传成功 Y.是 N.否 默认为否
     * @return
     */
    public String getIsupload() {
        return this.isupload;
    }
    /**
     * 功能：设置是否上传成功 Y.是 N.否 默认为否
     * @param isupload
     */
    public void setIsupload(String isupload) {
        this.isupload = isupload;
    }

    /**
     * 功能：获得发货月台
     * @return
     */
    public String getSendplatform() {
        return this.sendplatform;
    }
    /**
     * 功能：设置发货月台
     * @param sendplatform
     */
    public void setSendplatform(String sendplatform) {
        this.sendplatform = sendplatform;
    }
    /**
     * 功能：获得集货位
     * @return
     */
    public String getSetposition() {
        return this.setposition;
    }
    /**
     * 功能：设置集货位
     * @param setposition
     */
    public void setSetposition(String setposition) {
        this.setposition = setposition;
    }

    /**
     * 功能：获得确认名
     * @author hug 2012-6-21 
     * @return
     */
    public String getM_strAffirmManName() {
        return m_strAffirmManName;
    }

    /**
     * 功能：设置确认名
     * @author hug 2012-6-21 
     * @param affirmManName
     */
    public void setM_strAffirmManName(String affirmManName) {
        m_strAffirmManName = affirmManName;
    }

    /**
     * 功能：获得操作员名
     * @author hug 2012-6-21 
     * @return
     */
    public String getM_strOpManName() {
        return m_strOpManName;
    }

    /**
     * 功能：设置操作员名
     * @author hug 2012-6-21 
     * @param opManName
     */
    public void setM_strOpManName(String opManName) {
        m_strOpManName = opManName;
    }

    /**
     * 功能：获得单据状态名
     * @author hug 2012-6-21 
     * @return
     */
    public String getM_strOutStatusName() {
        return m_strOutStatusName;
    }

    /**
     * 功能：设置单据状态名
     * @author hug 2012-6-21 
     * @param outStatusName
     */
    public void setM_strOutStatusName(String outStatusName) {
        m_strOutStatusName = outStatusName;
    }

    /**
     * 功能：获得出库类型名
     * @author hug 2012-6-21 
     * @return
     */
    public String getM_strOutTypeName() {
        return m_strOutTypeName;
    }

    /**
     * 功能：设置出库类型名
     * @author hug 2012-6-21 
     * @param outTypeName
     */
    public void setM_strOutTypeName(String outTypeName) {
        m_strOutTypeName = outTypeName;
    }

	/**
	 * 功能：获得客户ID
	 */
	public String getCustomerid() {
		return customerid;
	}

	/**
	 * 功能：设置客户ID
	 * @param customerid
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	/**
	 * 功能：获得预期发货时间(to)
	 */
	public String getShipmentendtime() {
		return shipmentendtime;
	}

	/**
	 * 功能：设置预期发货时间(to)
	 * @param shipmentendtime
	 */
	public void setShipmentendtime(String shipmentendtime) {
		this.shipmentendtime = shipmentendtime;
	}

	/**
	 * 功能：获得预期发货时间(from)
	 */
	public String getShipmentstarttime() {
		return shipmentstarttime;
	}

	/**
	 * 功能：设置预期发货时间(from)
	 * @param shipmentstarttime
	 */
	public void setShipmentstarttime(String shipmentstarttime) {
		this.shipmentstarttime = shipmentstarttime;
	}

    /**
     * 功能:获得客户名
     * @author hug 2012-9-14 
     * @return
     */
    public String getCustomername() {
        return customername;
    }

    /**
     * 功能:设置客户名
     * @author hug 2012-9-14 
     * @param customername
     */
    public void setCustomername(String customername) {
        this.customername = customername;
    }
    /**
     * 功能：获得货主
     * @author hug 2012-6-26 
     * @return
     */
    public String getOwnerid() {
        return ownerid;
    }
    /**
     * 功能：设置货主
     * @author hug 2012-6-26 
     * @param ownerid
     */
    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }
    /**
     * 功能：获得货主
     * @author hug 2012-6-26 
     * @return
     */
    public String getOwnername() {
        return ownername;
    }

    /**
     * 功能：设置货主
     * @author hug 2012-6-26 
     * @param ownername
     */
    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
    
}