package com.wms3.bms.standard.entity.inbound;

/**
 * 
 * 描述: 收货单
 * @author hug 2012-6-26
 * @since WMS 3.0
 */
public class InboundReceiptHeader implements java.io.Serializable{

    /**  */
    private static final long serialVersionUID = -6358945903492412835L;
    
    private String reinvoiceid; /*申请单据编号*/
    private String warehouseid; /*仓库编号*/
    private String invoicedate; /*默认为生成时期*/
    private String createtime ; /*单据生成时间*/
    private String createmanid; /*单据生成人员编号*/
    private String updatetime ; /*单据最后修改时间*/
    private String updatemanid; /*单据最后修改人员编号*/
    private String instatus ;   /*单据状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架*/
    private String intype ;     /*类型*/
    private String ownerid ;    /*货主*/
    private String arrivestarttime; /*预期到货时间(from)*/
    private String arriveendtime;   /*预期到货时间(to)*/
    private String shipperid;   /*承运人信息*/
    private String supplierid ; /*供应商信息*/
    private String receiveloc;  /*收货月台*/
    private String customsno ;  /*海关备案号*/
    private String reserve1;    /*预留字段1*/
    private String reserve2;    /*预留字段2*/
    private String reserve3;    /*预留字段3*/
    private String reserve4;    /*预留字段4*/
    private String remark;      /*备注*/
    
    //派生属性
    private String m_strStatusName; // 状态名
    private String m_strTypeName;   // 类型名
    private String createman;       // 单据生成人员
    private String ownername;       //货主
    private String shippername;     //承运人信息
    private String suppliername ;   //供应商信息
    
    
    public InboundReceiptHeader()
    {}
    
    /**
     * 功能：获得预期到货时间(to)
     * @author hug 2012-6-26 
     * @return
     */
    public String getArriveendtime() {
        return arriveendtime;
    }
    /**
     * 功能：设置预期到货时间(to)
     * @author hug 2012-6-26 
     * @param arriveendtime
     */
    public void setArriveendtime(String arriveendtime) {
        this.arriveendtime = arriveendtime;
    }
    /**
     * 功能：获得预期到货时间(from)
     * @author hug 2012-6-26 
     * @return
     */
    public String getArrivestarttime() {
        return arrivestarttime;
    }
    /**
     * 功能：设置预期到货时间(from)
     * @author hug 2012-6-26 
     * @param arrivestarttime
     */
    public void setArrivestarttime(String arrivestarttime) {
        this.arrivestarttime = arrivestarttime;
    }
   
    /**
     * 功能：获得单据生成人员编号
     * @author hug 2012-6-26 
     * @return
     */
    public String getCreatemanid() {
        return createmanid;
    }
    /**
     * 功能：设置单据生成人员编号
     * @author hug 2012-6-26 
     * @param createmanid
     */
    public void setCreatemanid(String createmanid) {
        this.createmanid = createmanid;
    }
    /**
     * 功能：获得单据生成时间
     * @author hug 2012-6-26 
     * @return
     */
    public String getCreatetime() {
        return createtime;
    }
    /**
     * 功能：设置单据生成时间
     * @author hug 2012-6-26 
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    /**
     * 功能：获得海关备案号
     * @author hug 2012-6-26 
     * @return
     */
    public String getCustomsno() {
        return customsno;
    }
    /**
     * 功能：设置海关备案号
     * @author hug 2012-6-26 
     * @param customsno
     */
    public void setCustomsno(String customsno) {
        this.customsno = customsno;
    }
    /**
     * 功能：获得单据状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
     * @author hug 2012-6-26 
     * @return
     */
    public String getInstatus() {
        return instatus;
    }
    /**
     * 功能：设置单据状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
     * @author hug 2012-6-26 
     * @param instatus
     */
    public void setInstatus(String instatus) {
        this.instatus = instatus;
    }
    /**
     * 功能：获得类型
     * @author hug 2012-6-26 
     * @return
     */
    public String getIntype() {
        return intype;
    }
    /**
     * 功能：设置类型
     * @author hug 2012-6-26 
     * @param intype
     */
    public void setIntype(String intype) {
        this.intype = intype;
    }
    /**
     * 功能：获得默认为生成时期
     * @author hug 2012-6-26 
     * @return
     */
    public String getInvoicedate() {
        return invoicedate;
    }
    /**
     * 功能：设置默认为生成时期
     * @author hug 2012-6-26 
     * @param invoicedate
     */
    public void setInvoicedate(String invoicedate) {
        this.invoicedate = invoicedate;
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
     * 功能：获得收货月台
     * @author hug 2012-6-26 
     * @return
     */
    public String getReceiveloc() {
        return receiveloc;
    }
    /**
     * 功能：设置收货月台
     * @author hug 2012-6-26 
     * @param receiveloc
     */
    public void setReceiveloc(String receiveloc) {
        this.receiveloc = receiveloc;
    }
    /**
     * 功能：获得收货单据编号
     * @author hug 2012-6-26 
     * @return
     */
    public String getReinvoiceid() {
        return reinvoiceid;
    }
    /**
     * 功能：设置收货单据编号
     * @author hug 2012-6-26 
     * @param reinvoiceid
     */
    public void setReinvoiceid(String reinvoiceid) {
        this.reinvoiceid = reinvoiceid;
    }
    /**
     * 功能：获得备注
     * @author hug 2012-6-26 
     * @return
     */
    public String getRemark() {
        return remark;
    }
    /**
     * 功能：设置备注
     * @author hug 2012-6-26 
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * 功能：获得预留字段1
     * @author hug 2012-6-26 
     * @return
     */
    public String getReserve1() {
        return reserve1;
    }
    /**
     * 功能：设置预留字段1
     * @author hug 2012-6-26 
     * @param reserve1
     */
    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }
    /**
     * 功能：获得预留字段2
     * @author hug 2012-6-26 
     * @return
     */
    public String getReserve2() {
        return reserve2;
    }
    /**
     * 功能：设置预留字段2
     * @author hug 2012-6-26 
     * @param reserve2
     */
    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }
    /**
     * 功能：获得预留字段3
     * @author hug 2012-6-26 
     * @return
     */
    public String getReserve3() {
        return reserve3;
    }
    /**
     * 功能：设置预留字段3
     * @author hug 2012-6-26 
     * @param reserve3
     */
    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }
    /**
     * 功能：获得预留字段4
     * @author hug 2012-6-26 
     * @return
     */
    public String getReserve4() {
        return reserve4;
    }
    /**
     * 功能：设置预留字段4
     * @author hug 2012-6-26 
     * @param reserve4
     */
    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }
    /**
     * 功能：获得承运人信息
     * @author hug 2012-6-26 
     * @return
     */
    public String getShipperid() {
        return shipperid;
    }
    /**
     * 功能：设置承运人信息
     * @author hug 2012-6-26 
     * @param shipperid
     */
    public void setShipperid(String shipperid) {
        this.shipperid = shipperid;
    }
    
    
    /**
     * 功能：获得供应商信息
     * @author hug 2012-6-26 
     * @return
     */
    public String getSupplierid() {
        return supplierid;
    }
    /**
     * 功能：设置供应商信息
     * @author hug 2012-6-26 
     * @param supplierid
     */
    public void setSupplierid(String supplierid) {
        this.supplierid = supplierid;
    }
    
    
    /**
     * 功能：获得单据最后修改人员编号
     * @author hug 2012-6-26 
     * @return
     */
    public String getUpdatemanid() {
        return updatemanid;
    }
    /**
     * 功能：设置单据最后修改人员编号
     * @author hug 2012-6-26 
     * @param updatemanid
     */
    public void setUpdatemanid(String updatemanid) {
        this.updatemanid = updatemanid;
    }
    /**
     * 功能：获得单据最后修改时间
     * @author hug 2012-6-26 
     * @return
     */
    public String getUpdatetime() {
        return updatetime;
    }
    /**
     * 功能：设置单据最后修改时间
     * @author hug 2012-6-26 
     * @param updatetime
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    /**
     * 功能：获得仓库编号
     * @author hug 2012-6-26 
     * @return
     */
    public String getWarehouseid() {
        return warehouseid;
    }
    /**
     * 功能：设置仓库编号
     * @author hug 2012-6-26 
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }

    
    
    
    /**
     * 功能：获得单据生成人员
     * @author hug 2012-6-26 
     * @return
     */
    public String getCreateman() {
        return createman;
    }

    /**
     * 功能：设置单据生成人员
     * @author hug 2012-6-26 
     * @param createman
     */
    public void setCreateman(String createman) {
        this.createman = createman;
    }

    /**
     * 功能：获得状态名
     * @author hug 2012-6-26 
     * @return
     */
    public String getM_strStatusName() {
        return m_strStatusName;
    }

    /**
     * 功能：设置状态名
     * @author hug 2012-6-26 
     * @param statusName
     */
    public void setM_strStatusName(String statusName) {
        m_strStatusName = statusName;
    }

    /**
     * 功能：获得类型名
     * @author hug 2012-6-26 
     * @return
     */
    public String getM_strTypeName() {
        return m_strTypeName;
    }

    /**
     * 功能：设置类型名
     * @author hug 2012-6-26 
     * @param typeName
     */
    public void setM_strTypeName(String typeName) {
        m_strTypeName = typeName;
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

    /**
     * 功能：获得承运人信息
     * @author hug 2012-6-26 
     * @return
     */
    public String getShippername() {
        return shippername;
    }

    /**
     * 功能：设置承运人信息
     * @author hug 2012-6-26 
     * @param shippername
     */
    public void setShippername(String shippername) {
        this.shippername = shippername;
    }

    /**
     * 功能：获得供应商信息
     * @author hug 2012-6-26 
     * @return
     */
    public String getSuppliername() {
        return suppliername;
    }

    /**
     * 功能：设置供应商信息
     * @author hug 2012-6-26 
     * @param suppliername
     */
    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }
    
    
    
    
    
    

}
