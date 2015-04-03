package com.wms3.bms.standard.entity.inbound;



/**
 * 收货单详细
 */
public class InboundReceiptDetail  implements java.io.Serializable {
	 /**  */
    private static final long serialVersionUID = -1294713723821283384L;
    
    private String reincoicedetailid;  /* 收货单详细ID */
    private String linestatus;          /* 单据行状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架 */
    private String reinvoiceid;         /* 收货单据编号 */
    private String productid;           /* 品名 */
    private String packid;              /* 包装 */
    private String eunit;               /* 单位 */
    private Integer boxnum;             /* 箱数 */
    private String rejectcode;          /* 拒收原因代码 */
    private String rejectreason;        /* 拒收原因描述 */
    private String holdcode;            /* 冻结原因代码 */
    private String holdreason;          /* 冻结原因描述 */
    private String skustatus;           /* 产品状态代码 */
    private String skustatus_descr;     /* 产品状态描述 */
    private Integer plattenum;          /* 码盘数量 */
    private double invoicenum;          /* 预期数量（开单） */
    private double recnum;              /* 收货数量（收货） */
    private double rejectednum;         /* 拒收数量 */
    private double holdnum;             /* 冻结数量 */
    private String reclocation;         /* 收货库区 */
    private String receivedtime;        /* 收货时间 */
    private String lotid;            	/* 批次类型ID */
    private double volume;              /* 体积 */
    private double weight;              /* 重量 */
    private double netweight;           /* 净重 */
    private double reweight;            /* 收货重量 */
    private double renetweight;         /* 收货净重 */
    private double revolume;            /* 收货体积 */
    private double price;               /* 单价 */
    private String reserve1;            /* 预留字段1*/
    private String reserve2;            /* 预留字段2 */
    private String reserve3;            /* 预留字段3 */
    private String reserve4;            /* 预留字段4 */
    public String lotatt1;  			// 批次属性1
    public String lotatt2;  			// 批次属性2
    public String lotatt3;  			// 批次属性3
    public String lotatt4;  			// 批次属性4
    public String lotatt5;  			// 批次属性5
    public String lotatt6;  			// 批次属性6
    public String lotatt7;  			// 批次属性7
    public String lotatt8;  			// 批次属性8
    public String lotatt9;  			// 批次属性9
    public String lotatt10;  			// 批次属性10
    public String lotatt11;  			// 批次属性11
    public String lotatt12;  			// 批次属性12
     
     
     //派生属性
     private String m_strStatusName;           // 状态名
     private String m_strProductName;          // 产品
     private String m_strPackName;       //包装名称
     private String m_strUnitName;       //单位名称
     private String m_strRejectCodeText; //拒收编码显示内容
     private String m_strHoldCodeText;   //冻结编码显示内容
     private String m_strOverRcvFlag;    //SKU是否允许超量收货



    // Constructors

    /** default constructor */
    public InboundReceiptDetail() {
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
     * 功能：获得品名规格
     * @return
     */
    public String getProductid() {
        return this.productid;
    }
    /**
     * 功能：设置品名规格
     * @param productid
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }
    /**
     * 功能：获得最小单位
     * @return
     */
    public String getEunit() {
        return this.eunit;
    }
    /**
     * 功能：设置最小单位
     * @param eunit
     */
    public void setEunit(String eunit) {
        this.eunit = eunit;
    }
    /**
     * 功能：获得箱数（开单）
     * @return
     */
    public Integer getBoxnum() {
        return this.boxnum;
    }
    /**
     * 功能：设置箱数（开单）
     * @param boxnum
     */
    public void setBoxnum(Integer boxnum) {
        this.boxnum = boxnum;
    }
    /**
     * 功能：获得托盘数（开单）
     * @return
     */
    public Integer getPlattenum() {
        return this.plattenum;
    }
    /**
     * 功能：设置托盘数（开单）
     * @param plattenum
     */
    public void setPlattenum(Integer plattenum) {
        this.plattenum = plattenum;
    }
    /**
     * 功能：获得最小单位数量（开单）
     * @return
     */
    public double getInvoicenum() {
        return this.invoicenum;
    }
    /**
     * 功能：设置最小单位数量（开单）
     * @param invoicenum
     */
    public void setInvoicenum(double invoicenum) {
        this.invoicenum = invoicenum;
    }

    /**
     * 功能：获得最小单位数量（收货）
     * @return
     */
    public double getRecnum() {
        return this.recnum;
    }
    /**
     * 功能：设置最小单位数量（收货）
     * @param recnum
     */
    public void setRecnum(double recnum) {
        this.recnum = recnum;
    }
    /**
     * 功能：获得体积
     * @return
     */
    public double getVolume() {
        return this.volume;
    }
    /**
     * 功能：设置体积
     * @param volume
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }
    /**
     * 功能：获得重量
     * @return
     */
    public double getWeight() {
        return this.weight;
    }
    /**
     * 功能：设置重量
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
    /**
     * 功能：获得净重
     * @return
     */
    public double getNetweight() {
        return this.netweight;
    }
    /**
     * 功能：设置净重
     * @param netweight
     */
    public void setNetweight(double netweight) {
        this.netweight = netweight;
    }
    /**
     * 功能：获得收货重量
     * @return
     */
    public double getReweight() {
        return this.reweight;
    }
    /**
     * 功能：设置收货重量
     * @param reweight
     */
    public void setReweight(double reweight) {
        this.reweight = reweight;
    }
    /**
     * 功能：获得收货净重
     * @return
     */
    public double getRenetweight() {
        return this.renetweight;
    }
    /**
     * 功能：设置收货净重
     * @param renetweight
     */
    public void setRenetweight(double renetweight) {
        this.renetweight = renetweight;
    }
    /**
     * 功能：获得收货库区
     * @return
     */
    public String getReclocation() {
        return this.reclocation;
    }
    /**
     * 功能：设置收货库区
     * @param reclocation
     */
    public void setReclocation(String reclocation) {
        this.reclocation = reclocation;
    }
    /**
     * 功能：获得收货体积
     * @return
     */
    public double getRevolume() {
        return this.revolume;
    }
    /**
     * 功能：设置收货体积
     * @param revolume
     */
    public void setRevolume(double revolume) {
        this.revolume = revolume;
    }
    /**
     * 功能：获得单价
     * @return
     */
    public double getPrice() {
        return this.price;
    }
    /**
     * 功能：设置单价
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * 功能：获得单据行状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
     * @author hug 2012-3-22 
     * @return
     */
    public String getLinestatus() {
        return linestatus;
    }

    /**
     * 功能：设置单据行状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
     * @author hug 2012-3-22 
     * @param linestatus
     */
    public void setLinestatus(String linestatus) {
        this.linestatus = linestatus;
    }

    /**
     * 功能：获得冻结原因代码
     * @author hug 2012-6-26 
     * @return
     */
    public String getHoldcode() {
        return holdcode;
    }
    /**
     * 功能：设置冻结原因代码
     * @author hug 2012-6-26 
     * @param holdcode
     */
    public void setHoldcode(String holdcode) {
        this.holdcode = holdcode;
    }
    /**
     * 功能：获得冻结数量
     * @author hug 2012-6-26 
     * @return
     */
    public double getHoldnum() {
        return holdnum;
    }


    /**
     * 功能：设置冻结数量
     * @author hug 2012-6-26 
     * @param holdnum
     */
    public void setHoldnum(double holdnum) {
        this.holdnum = holdnum;
    }

    /**
     * 功能：获得冻结原因描述
     * @author hug 2012-6-26 
     * @return
     */
    public String getHoldreason() {
        return holdreason;
    }
    /**
     * 功能：设置冻结原因描述
     * @author hug 2012-6-26 
     * @param holdreason
     */
    public void setHoldreason(String holdreason) {
        this.holdreason = holdreason;
    }


    /**
     * 功能：获得包装
     * @author hug 2012-6-26 
     * @return
     */
    public String getPackid() {
        return packid;
    }


    /**
     * 功能：设置包装
     * @author hug 2012-6-26 
     * @param packid
     */
    public void setPackid(String packid) {
        this.packid = packid;
    }
    /**
     * 功能：获得收货时间
     * @author hug 2012-6-26 
     * @return
     */
    public String getReceivedtime() {
        return receivedtime;
    }

    /**
     * 功能：设置收货时间
     * @author hug 2012-6-26 
     * @param receivedtime
     */
    public void setReceivedtime(String receivedtime) {
        this.receivedtime = receivedtime;
    }

    /**
     * 功能：获得收货单详细ID
     * @author hug 2012-6-26 
     * @return
     */
    public String getReincoicedetailid() {
        return reincoicedetailid;
    }

    /**
     * 功能：设置收货单详细ID
     * @author hug 2012-6-26 
     * @param reincoicedetailid
     */
    public void setReincoicedetailid(String reincoicedetailid) {
        this.reincoicedetailid = reincoicedetailid;
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
     * 功能：获得拒收原因代码
     * @author hug 2012-6-26 
     * @return
     */
    public String getRejectcode() {
        return rejectcode;
    }
    /**
     * 功能：设置拒收原因代码
     * @author hug 2012-6-26 
     * @param rejectcode
     */
    public void setRejectcode(String rejectcode) {
        this.rejectcode = rejectcode;
    }
    /**
     * 功能：获得拒收数量
     * @author hug 2012-6-26 
     * @return
     */
    public double getRejectednum() {
        return rejectednum;
    }
    /**
     * 功能：设置拒收数量
     * @author hug 2012-6-26 
     * @param rejectednum
     */
    public void setRejectednum(double rejectednum) {
        this.rejectednum = rejectednum;
    }
    /**
     * 功能：获得拒收原因描述
     * @author hug 2012-6-26 
     * @return
     */
    public String getRejectreason() {
        return rejectreason;
    }
    /**
     * 功能：设置拒收原因描述
     * @author hug 2012-6-26 
     * @param rejectreason
     */
    public void setRejectreason(String rejectreason) {
        this.rejectreason = rejectreason;
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
     * 功能：获得产品状态代码
     * @author hug 2012-6-26 
     * @return
     */
    public String getSkustatus() {
        return skustatus;
    }
    /**
     * 功能：设置产品状态代码
     * @author hug 2012-6-26 
     * @param skustatus
     */
    public void setSkustatus(String skustatus) {
        this.skustatus = skustatus;
    }
    /**
     * 功能：获得产品状态描述
     * @author hug 2012-6-26 
     * @return
     */
    public String getSkustatus_descr() {
        return skustatus_descr;
    }
    /**
     * 功能：设置产品状态描述
     * @author hug 2012-6-26 
     * @param skustatus_descr
     */
    public void setSkustatus_descr(String skustatus_descr) {
        this.skustatus_descr = skustatus_descr;
    }

    /**
     * 功能：获得产品
     * @author hug 2012-6-20 
     * @return
     */
    public String getM_strProductName() {
        return m_strProductName;
    }


    /**
     * 功能：设置产品
     * @author hug 2012-6-20 
     * @param productName
     */
    public void setM_strProductName(String productName) {
        m_strProductName = productName;
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
     * 功能：获得冻结编码显示内容
     * @author hug 2012-6-26 
     * @return
     */
    public String getM_strHoldCodeText() {
        return m_strHoldCodeText;
    }
    /**
     * 功能：设置冻结编码显示内容
     * @author hug 2012-6-26 
     * @param holdCodeText
     */
    public void setM_strHoldCodeText(String holdCodeText) {
        m_strHoldCodeText = holdCodeText;
    }
    /**
     * 功能：获得SKU是否允许超量收货
     * @author hug 2012-6-26 
     * @return
     */
    public String getM_strOverRcvFlag() {
        return m_strOverRcvFlag;
    }
    /**
     * 功能：设置SKU是否允许超量收货
     * @author hug 2012-6-26 
     * @param overRcvFlag
     */
    public void setM_strOverRcvFlag(String overRcvFlag) {
        m_strOverRcvFlag = overRcvFlag;
    }

    /**
     * 功能：获得拒收编码显示内容
     * @author hug 2012-6-26 
     * @return
     */
    public String getM_strRejectCodeText() {
        return m_strRejectCodeText;
    }
    /**
     * 功能：设置拒收编码显示内容
     * @author hug 2012-6-26 
     * @param rejectCodeText
     */
    public void setM_strRejectCodeText(String rejectCodeText) {
        m_strRejectCodeText = rejectCodeText;
    }

    /**
     * 功能：获得包装名称
     * @author hug 2012-8-22 
     * @return
     */
    public String getM_strPackName() {
        return m_strPackName;
    }

    /**
     * 功能：设置包装名称
     * @author hug 2012-8-22 
     * @param packName
     */
    public void setM_strPackName(String packName) {
        m_strPackName = packName;
    }

    /**
     * 功能：获得单位名称
     * @author hug 2012-8-22 
     * @return
     */
    public String getM_strUnitName() {
        return m_strUnitName;
    }

    /**
     * 功能：设置单位名称
     * @author hug 2012-8-22 
     * @param unitName
     */
    public void setM_strUnitName(String unitName) {
        m_strUnitName = unitName;
    }
    
    
    
}