package com.wms3.bms.standard.entity.inbound;



/**
 * 入库单详细
 */
public class InboundInvoiceDetail  implements java.io.Serializable {

	private static final long serialVersionUID = 92095308929677463L;
	
	 private String instockdetailid;	//入库单详细ID
     private String instockid;			//入库单据编号
     private String productid;			//品名规格
     private String packid;				//包装
     private String eunit;				//单位
     private Integer boxnum;			//箱数（开单）
     private Integer plattenum;			//托盘数（开单）
     private double invoicenum;			//数量（开单）
     private Integer puboxnum;			//箱数（上架）
     private Integer puplattenum;		//托盘数（上架）
     private double pucnum;				//数量（上架）
     private String lotid;				//批次类型ID
     private double volume;				//体积
     private double weight;				//重量
     private double netweight;			//净重
     private double puvolume;			//上架体积
     private double puweight;			//上架重量
     private double punetweight;		//上架净重
     private double price;				//单价
     private String reserve1;			//预留字段1
     private String reserve2;			//预留字段2
     private String reserve3;			//预留字段3
     private String reserve4;			//预留字段4
     private String providerid;			//供应商编号
     private String linestatus;         //单据行状态0-新建 5-作废
     private String reincoiceid;        //收货单号
     private String reinvoicedetailid;  //收货单详细ID
     public String lotatt1;  			//批次属性1
     public String lotatt2;  			//批次属性2
     public String lotatt3;  			//批次属性3
     public String lotatt4;  			//批次属性4
     public String lotatt5;  			//批次属性5
     public String lotatt6;  			//批次属性6
     public String lotatt7;  			//批次属性7
     public String lotatt8;  			//批次属性8
     public String lotatt9;  			//批次属性9
     public String lotatt10;  			//批次属性10
     public String lotatt11;  			//批次属性11
     public String lotatt12;  			//批次属性12
     
     //派生属性
     protected String m_strProviderName;	// 供应商
     protected String m_strStatusName;		// 状态名
     protected String m_strProductName;		// 产品
     protected String pkgdesc;  			// 包装描述
     

    // Constructors

    /** default constructor */
    public InboundInvoiceDetail() {
    }

	/** minimal constructor */
    public InboundInvoiceDetail(String productid, String eunit, double invoicenum) {
        this.productid = productid;
        this.eunit = eunit;
        this.invoicenum = invoicenum;
    }

   
    // Property accessors
    /**
     * 功能：获得入库单详细ID
     */
    public String getInstockdetailid() {
        return this.instockdetailid;
    }
    /**
     * 功能：设置入库单详细ID
     * @param instockdetailid
     */
    public void setInstockdetailid(String instockdetailid) {
        this.instockdetailid = instockdetailid;
    }
    /**
     * 功能：获得入库单据编号
     * @return
     */
    public String getInstockid() {
        return this.instockid;
    }
    /**
     * 功能：设置入库单据编号
     * @param instockid
     */
    public void setInstockid(String instockid) {
        this.instockid = instockid;
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
     * 功能：获得数量（开单）
     * @return
     */
    public double getInvoicenum() {
        return this.invoicenum;
    }
    /**
     * 功能：设置数量（开单）
     * @param invoicenum
     */
    public void setInvoicenum(double invoicenum) {
        this.invoicenum = invoicenum;
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
     * 功能：获得供应商编号
     * @author hug 2012-3-22 
     * @return
     */
    public String getProviderid() {
        return providerid;
    }

    /**
     * 功能：设置供应商编号
     * @author hug 2012-3-22 
     * @param providerid
     */
    public void setProviderid(String providerid) {
        this.providerid = providerid;
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
     * 功能：获得供应商
     * @author hug 2012-6-25 
     * @return
     */
    public String getM_strProviderName() {
        return m_strProviderName;
    }

    /**
     * 功能：设置供应商
     * @author hug 2012-6-25 
     * @param providerName
     */
    public void setM_strProviderName(String providerName) {
        m_strProviderName = providerName;
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
	 * 功能：获得箱数（上架）
	 */
	public Integer getPuboxnum() {
		return puboxnum;
	}

	/**
	 * 功能：设置箱数（上架）
	 * @param puboxnum
	 */
	public void setPuboxnum(Integer puboxnum) {
		this.puboxnum = puboxnum;
	}

	/**
	 * 功能：获得数量（上架）
	 */
	public double getPucnum() {
		return pucnum;
	}

	/**
	 * 功能：设置数量（上架）
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
	 * 功能：获得托盘数（上架）
	 */
	public Integer getPuplattenum() {
		return puplattenum;
	}

	/**
	 * 功能：设置托盘数（上架）
	 * @param puplattenum
	 */
	public void setPuplattenum(Integer puplattenum) {
		this.puplattenum = puplattenum;
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
	 * 功能：获得收货单号
	 */
	public String getReincoiceid() {
		return reincoiceid;
	}

	/**
	 * 功能：设置收货单号
	 * @param reincoiceid
	 */
	public void setReincoiceid(String reincoiceid) {
		this.reincoiceid = reincoiceid;
	}

	/**
	 * 功能：获得收货单详细ID
	 */
	public String getReinvoicedetailid() {
		return reinvoicedetailid;
	}

	/**
	 * 功能：设置收货单详细ID
	 * @param reinvoicedetailid
	 */
	public void setReinvoicedetailid(String reinvoicedetailid) {
		this.reinvoicedetailid = reinvoicedetailid;
	}

	/**
	 * 功能：获得预留字段1
	 */
	public String getReserve1() {
		return reserve1;
	}

	/**
	 * 功能：设置预留字段1
	 * @param reserve1
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	/**
	 * 功能：获得预留字段2
	 */
	public String getReserve2() {
		return reserve2;
	}

	/**
	 * 功能：设置预留字段2
	 * @param reserve2
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	/**
	 * 功能：获得预留字段3
	 */
	public String getReserve3() {
		return reserve3;
	}

	/**
	 * 功能：设置预留字段3
	 * @param reserve3
	 */
	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	/**
	 * 功能：获得预留字段4
	 */
	public String getReserve4() {
		return reserve4;
	}

	/**
	 * 功能：设置预留字段4
	 * @param reserve4
	 */
	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	/**
	 * 功能：获得包装描述
	 */
	public String getPkgdesc() {
		return pkgdesc;
	}

	/**
	 * 功能：设置包装描述
	 * @param pkgdesc
	 */
	public void setPkgdesc(String pkgdesc) {
		this.pkgdesc = pkgdesc;
	}
    
}