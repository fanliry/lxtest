package com.wms3.bms.standard.entity.inbound;



/**
 * 收货记录表
 */
public class InboundReceiptTransaction  implements java.io.Serializable {
	 /**  */
    private static final long serialVersionUID = -1294713723821283384L;
    
    private String transreceiptid;		//收货记录ID
    private String transstatus;			//状态  (transstatus)    1:收货完成；2:已码盘; 3:部分上架; 4：完全上架 5：取消收货
    private String reinvoiceid;			//收货单据编号
    private String reinvoicedetailid;	//收货单详细ID
    private String warehouseid;			//仓库编号
    private String receipttime;			//收货时间
    private String receiptmanid;		//收货人
    private String receiptrf;			//收货设备RF
    private String traycode;			//托盘条码
    private String ownerid;				//货主
    private String productid;			//产品
    private String packid;				//包装
    private String eunit;				//单位
    private double recnum;				//收货数量
    private double reweight;			//收货重量
    private double renetweight;			//收货净重
    private double revolume;			//收货体积
    private double pucnum;				//上架数量
    private double puweight;			//上架重量
    private double punetweight;			//上架净重
    private double puvolume;			//上架体积
    private String lotid;				//批次类型ID
    public String lotatt1;				//批次属性1
    public String lotatt2;				//批次属性2
    public String lotatt3;				//批次属性3
    public String lotatt4;				//批次属性4
    public String lotatt5;				//批次属性5
    public String lotatt6;				//批次属性6
    public String lotatt7;				//批次属性7
    public String lotatt8;				//批次属性8
    public String lotatt9;				//批次属性9
    public String lotatt10;			//批次属性10
    public String lotatt11;			//批次属性11
    public String lotatt12;			//批次属性12
     
     
     //派生属性
     private String m_strProductName;    // 产品
     private String m_strPackName;       //包装名称
     private String m_strUnitName;       //单位名称
     private String ownername;           //货主
     private String receiptmanname;      //收货人
     
     private String strStatusName;      // 状态名

     //新增字段
     private String boxcode;            //箱条码
     private String productcode;        //产品条码

    // Constructors

    /** default constructor */
    public InboundReceiptTransaction() {
    }
   
    // Property accessors


    /**
	 * 功能：获得批次属性1
	 */
	public String getLotatt1() {
		return lotatt1;
	}

	/**
	 * 功能：设置批次属性1
	 * @param lotatt1
	 */
	public void setLotatt1(String lotatt1) {
		this.lotatt1 = lotatt1;
	}

	/**
	 * 功能：获得批次属性10
	 */
	public String getLotatt10() {
		return lotatt10;
	}

	/**
	 * 功能：设置批次属性10
	 * @param lotatt10
	 */
	public void setLotatt10(String lotatt10) {
		this.lotatt10 = lotatt10;
	}

	/**
	 * 功能：获得批次属性11
	 */
	public String getLotatt11() {
		return lotatt11;
	}

	/**
	 * 功能：设置批次属性11
	 * @param lotatt11
	 */
	public void setLotatt11(String lotatt11) {
		this.lotatt11 = lotatt11;
	}

	/**
	 * 功能：获得批次属性12
	 */
	public String getLotatt12() {
		return lotatt12;
	}

	/**
	 * 功能：设置批次属性12
	 * @param lotatt12
	 */
	public void setLotatt12(String lotatt12) {
		this.lotatt12 = lotatt12;
	}

	/**
	 * 功能：获得批次属性2
	 */
	public String getLotatt2() {
		return lotatt2;
	}

	/**
	 * 功能：设置批次属性2
	 * @param lotatt2
	 */
	public void setLotatt2(String lotatt2) {
		this.lotatt2 = lotatt2;
	}

	/**
	 * 功能：获得批次属性3
	 */
	public String getLotatt3() {
		return lotatt3;
	}

	/**
	 * 功能：设置批次属性3
	 * @param lotatt3
	 */
	public void setLotatt3(String lotatt3) {
		this.lotatt3 = lotatt3;
	}

	/**
	 * 功能：获得批次属性4
	 */
	public String getLotatt4() {
		return lotatt4;
	}

	/**
	 * 功能：设置批次属性4
	 * @param lotatt4
	 */
	public void setLotatt4(String lotatt4) {
		this.lotatt4 = lotatt4;
	}

	/**
	 * 功能：获得批次属性5
	 */
	public String getLotatt5() {
		return lotatt5;
	}

	/**
	 * 功能：设置批次属性5
	 * @param lotatt5
	 */
	public void setLotatt5(String lotatt5) {
		this.lotatt5 = lotatt5;
	}

	/**
	 * 功能：获得批次属性6
	 */
	public String getLotatt6() {
		return lotatt6;
	}

	/**
	 * 功能：设置批次属性6
	 * @param lotatt6
	 */
	public void setLotatt6(String lotatt6) {
		this.lotatt6 = lotatt6;
	}

	/**
	 * 功能：获得批次属性7
	 */
	public String getLotatt7() {
		return lotatt7;
	}

	/**
	 * 功能：设置批次属性7
	 * @param lotatt7
	 */
	public void setLotatt7(String lotatt7) {
		this.lotatt7 = lotatt7;
	}

	/**
	 * 功能：获得批次属性8
	 */
	public String getLotatt8() {
		return lotatt8;
	}

	/**
	 * 功能：设置批次属性8
	 * @param lotatt8
	 */
	public void setLotatt8(String lotatt8) {
		this.lotatt8 = lotatt8;
	}

	/**
	 * 功能：获得批次属性9
	 */
	public String getLotatt9() {
		return lotatt9;
	}

	/**
	 * 功能：设置批次属性9
	 * @param lotatt9
	 */
	public void setLotatt9(String lotatt9) {
		this.lotatt9 = lotatt9;
	}

	/**
	 * 功能：获得批次类型ID
	 */
	public String getLotid() {
		return lotid;
	}

	/**
	 * 功能：设置批次类型ID
	 * @param lotid
	 */
	public void setLotid(String lotid) {
		this.lotid = lotid;
	}

	/**
	 * 功能：获得单位
	 */
	public String getEunit() {
		return eunit;
	}

	/**
	 * 功能：设置单位
	 * @param eunit
	 */
	public void setEunit(String eunit) {
		this.eunit = eunit;
	}

	/**
	 * 功能：获得产品
	 */
	public String getM_strProductName() {
		return m_strProductName;
	}

	/**
	 * 功能：设置产品
	 * @param productName
	 */
	public void setM_strProductName(String productName) {
		m_strProductName = productName;
	}

	/**
	 * 功能：获得货主
	 */
	public String getOwnerid() {
		return ownerid;
	}

	/**
	 * 功能：设置货主
	 * @param ownerid
	 */
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	/**
	 * 功能：获得包装
	 */
	public String getPackid() {
		return packid;
	}

	/**
	 * 功能：设置包装
	 * @param packid
	 */
	public void setPackid(String packid) {
		this.packid = packid;
	}

	/**
	 * 功能：获得产品
	 */
	public String getProductid() {
		return productid;
	}

	/**
	 * 功能：设置产品
	 * @param productid
	 */
	public void setProductid(String productid) {
		this.productid = productid;
	}

	/**
	 * 功能：获得上架数量
	 */
	public double getPucnum() {
		return pucnum;
	}

	/**
	 * 功能：设置上架数量
	 * @param pucnum
	 */
	public void setPucnum(double pucnum) {
		this.pucnum = pucnum;
	}

	/**
	 * 功能：获得上架净重
	 */
	public double getPunetweight() {
		return punetweight;
	}

	/**
	 * 功能：设置上架净重
	 * @param punetweight
	 */
	public void setPunetweight(double punetweight) {
		this.punetweight = punetweight;
	}

	/**
	 * 功能：获得上架体积
	 */
	public double getPuvolume() {
		return puvolume;
	}

	/**
	 * 功能：设置上架体积
	 * @param puvolume
	 */
	public void setPuvolume(double puvolume) {
		this.puvolume = puvolume;
	}

	/**
	 * 功能：获得上架重量
	 */
	public double getPuweight() {
		return puweight;
	}

	/**
	 * 功能：设置上架重量
	 * @param puweight
	 */
	public void setPuweight(double puweight) {
		this.puweight = puweight;
	}

	/**
	 * 功能：获得收货人
	 */
	public String getReceiptmanid() {
		return receiptmanid;
	}

	/**
	 * 功能：设置收货人
	 * @param receiptmanid
	 */
	public void setReceiptmanid(String receiptmanid) {
		this.receiptmanid = receiptmanid;
	}

	/**
	 * 功能：获得收货设备RF
	 */
	public String getReceiptrf() {
		return receiptrf;
	}

	/**
	 * 功能：设置收货设备RF
	 * @param receiptrf
	 */
	public void setReceiptrf(String receiptrf) {
		this.receiptrf = receiptrf;
	}

	/**
	 * 功能：获得收货时间
	 */
	public String getReceipttime() {
		return receipttime;
	}

	/**
	 * 功能：设置收货时间
	 * @param receipttime
	 */
	public void setReceipttime(String receipttime) {
		this.receipttime = receipttime;
	}

	/**
	 * 功能：获得收货数量
	 */
	public double getRecnum() {
		return recnum;
	}

	/**
	 * 功能：设置收货数量
	 * @param recnum
	 */
	public void setRecnum(double recnum) {
		this.recnum = recnum;
	}

	/**
	 * 功能：获得收货单详细ID
	 */
	public String getReinvoicedetailid() {
		return reinvoicedetailid;
	}

	/**
	 * 功能：设置收货单详细ID
	 * @param reincoicedetailid
	 */
	public void setReinvoicedetailid(String reinvoicedetailid) {
		this.reinvoicedetailid = reinvoicedetailid;
	}

	/**
	 * 功能：获得收货单据编号
	 */
	public String getReinvoiceid() {
		return reinvoiceid;
	}

	/**
	 * 功能：设置收货单据编号
	 * @param reinvoiceid
	 */
	public void setReinvoiceid(String reinvoiceid) {
		this.reinvoiceid = reinvoiceid;
	}

	/**
	 * 功能：获得收货净重
	 */
	public double getRenetweight() {
		return renetweight;
	}

	/**
	 * 功能：设置收货净重
	 * @param renetweight
	 */
	public void setRenetweight(double renetweight) {
		this.renetweight = renetweight;
	}

	/**
	 * 功能：获得收货体积
	 */
	public double getRevolume() {
		return revolume;
	}

	/**
	 * 功能：设置收货体积
	 * @param revolume
	 */
	public void setRevolume(double revolume) {
		this.revolume = revolume;
	}

	/**
	 * 功能：获得收货重量
	 */
	public double getReweight() {
		return reweight;
	}

	/**
	 * 功能：设置收货重量
	 * @param reweight
	 */
	public void setReweight(double reweight) {
		this.reweight = reweight;
	}

	/**
	 * 功能：获得收货记录ID
	 */
	public String getTransreceiptid() {
		return transreceiptid;
	}

	/**
	 * 功能：设置收货记录ID
	 * @param transreceiptid
	 */
	public void setTransreceiptid(String transreceiptid) {
		this.transreceiptid = transreceiptid;
	}

	/**
	 * 功能：获得状态(transstatus)1:收货完成；2:已码盘; 3:部分上架; 4：完全上架 5：取消收货
	 */
	public String getTransstatus() {
		return transstatus;
	}

	/**
	 * 功能：设置状态(transstatus)1:收货完成；2:已码盘; 3:部分上架; 4：完全上架 5：取消收货
	 * @param transstatus
	 */
	public void setTransstatus(String transstatus) {
		this.transstatus = transstatus;
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
	 * 功能：获得仓库编号
	 */
	public String getWarehouseid() {
		return warehouseid;
	}

	/**
	 * 功能：设置仓库编号
	 * @param warehouseid
	 */
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}

    /**
     * 
     * 功能:获得包装名称
     * @author hug 2012-8-23 
     * @return
     */
    public String getM_strPackName() {
        return m_strPackName;
    }

    /**
     * 
     * 功能:设置包装名称
     * @author hug 2012-8-23 
     * @param packName
     */
    public void setM_strPackName(String packName) {
        m_strPackName = packName;
    }

    /**
     * 
     * 功能:获得单位名称
     * @author hug 2012-8-23 
     * @return
     */
    public String getM_strUnitName() {
        return m_strUnitName;
    }

    /**
     * 
     * 功能:设置单位名称
     * @author hug 2012-8-23 
     * @param unitName
     */
    public void setM_strUnitName(String unitName) {
        m_strUnitName = unitName;
    }

    /**
     * 
     * 功能:获得货主
     * @author hug 2012-8-23 
     * @return
     */
    public String getOwnername() {
        return ownername;
    }

    /**
     * 
     * 功能:设置货主
     * @author hug 2012-8-23 
     * @param ownername
     */
    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    /**
     * 
     * 功能:获得收货人
     * @author hug 2012-8-23 
     * @return
     */
    public String getReceiptmanname() {
        return receiptmanname;
    }

    /**
     * 功能:设置收货人
     * @author hug 2012-8-23 
     * @param receiptmanname
     */
    public void setReceiptmanname(String receiptmanname) {
        this.receiptmanname = receiptmanname;
    }

    /**
     * 功能:获得状态名
     * @author hug 2012-8-31 
     * @return
     */
    public String getStrStatusName() {
        return strStatusName;
    }

    /**
     * 功能:设置状态名
     * @author hug 2012-8-31 
     * @param strStatusName
     */
    public void setStrStatusName(String strStatusName) {
        this.strStatusName = strStatusName;
    }
    /**
     * 功能：获得箱条码
     * @author hug 2012-4-16 
     * @return
     */
    public String getBoxcode() {
        return boxcode;
    }
    /**
     * 功能：设置箱条码
     * @author hug 2012-4-16 
     * @param boxcode
     */
    public void setBoxcode(String boxcode) {
        this.boxcode = boxcode;
    }

    /**
     * 功能：获得产品条码
     * @author hug 2012-10-18 
     * @return
     */
    public String getProductcode() {
        return productcode;
    }

    /**
     * 功能：设置产品条码
     * @author hug 2012-10-18 
     * @param productcode
     */
    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }
    
    
}