package com.wms3.bms.standard.entity.outbound;


/**
 * 描述：出库单详细
 * @author hug
 *
 */
public class OutboundInvoiceDetail  implements java.io.Serializable {
     
   
	private static final long serialVersionUID = 4012223973119209218L;
	
	private String outstockdetailid;	//出库单详细ID
	private String outstockid;			//出库单据号       
	private String linestatus;          //单据行状态 0-开单;1-预配 2-分配 3-部分拣货 4-完全拣货   7-发货
	private String productid;			//物品ID
	private String lotid;				//批次类型ID
	private String cargoSpaceId;		//货位Id
	private String cargoAlleyId;		//巷道Id
	private String whAreaId;			//库区ID
	private String packid;				//包装ID
	private String pkgunit;				//包装单位
    private double invoicenum;          //开单数量
    private double preassignnum;        //预配数量
    private double picknum;             //拣货数量 对作业复核时 复核成功 进行 同时修改其拣货数量
    private double sendnum;             //发货数量
    private String customid;            //客户id
    private String fbFlag;              //来源标识 1-指定产品 2-指定货位 3-销售订单 4-外部调拨单
    private String traycode;            //托盘条码
    private double netweight;           //净重
    private double weight;              //重量
    private double volume;              //体积
    private double price;               /* 单价 */
    private double snetweight;          //发货净重
    private double sweight;             //发货重量
    private double svolume;             //发货体积
    private double preassignnetweight;  //预配净重
    private double preassignweight;     //预配重量
    private double preassignvolume;     //预配体积
    private double assignnetweight;     //分配净重
    private double assignweight;        //分配重量
    private double assignvolume;        //分配体积
    private double picknetweight;       //拣货净重
    private double pickweight;          //拣货重量
    private double pickvolume;          //拣货体积
    private String confirmanid;         //确认人Id
    private String confirmdate;         //确认时间
    private String reserve1;            //预留字段1
    private String reserve2;            //预留字段2
    private String reserve3;            //预留字段3
    private String reserve4;            //预留字段4
    public String lotatt1;             //批次属性1
    public String lotatt2;             //批次属性2
    public String lotatt3;             //批次属性3
    public String lotatt4;             //批次属性4
    public String lotatt5;             //批次属性5
    public String lotatt6;             //批次属性6
    public String lotatt7;             //批次属性7
    public String lotatt8;             //批次属性8
    public String lotatt9;             //批次属性9
    public String lotatt10;            //批次属性10
    public String lotatt11;            //批次属性11
    public String lotatt12;            //批次属性12
    
    public String lotinfo;             //批号信息
    public String printdate;          	//生产日期
    private String Virtualwhid;      //逻辑库区id

    
	//派生属性
    private double assignnum;          //分配数量
    private String Virtualwhname;     //逻辑库区名称
    private String m_strProductName;    //品名
    protected String m_strCustomerName; // 客户	
    private String m_strProductCode;//产品编码
    private String m_strSpec;//产品规格

	private String m_strStatusName;     // 状态名
    private String m_strPackName;       //包装名称
    private String m_strUnitName;       //单位名称

    //新增属性
    private String soid;             /* 关联SO号 */
    private String sodetailid;       /* 关联SO行号 */
    
    // Constructors

    /** default constructor */
    public OutboundInvoiceDetail() {
    }

	/** minimal constructor */
    public OutboundInvoiceDetail(String productid) {
        this.productid = productid;
    }
    public String getVirtualwhid() {
		return Virtualwhid;
	}

	public void setVirtualwhid(String virtualwhid) {
		Virtualwhid = virtualwhid;
	}

	public String getVirtualwhname() {
		return Virtualwhname;
	}

	public void setVirtualwhname(String virtualwhname) {
		Virtualwhname = virtualwhname;
	}
   
    // Property accessors
    /**
     * 功能：获得出库单详细ID
     */
    public String getOutstockdetailid() {
        return this.outstockdetailid;
    }
    /**
     * 功能：设置出库单详细ID
     * @param outstockdetailid
     */
    public void setOutstockdetailid(String outstockdetailid) {
        this.outstockdetailid = outstockdetailid;
    }
    /**
     * 功能：获取产品编码
     * @return
     */
    public String getM_strProductCode() {
		return m_strProductCode;
	}
    /**
     * 功能：设置产品编码
     * @param mStrProductCode
     */
	public void setM_strProductCode(String mStrProductCode) {
		m_strProductCode = mStrProductCode;
	}
	
	public String getM_strSpec() {
		return m_strSpec;
	}

	public void setM_strSpec(String mStrSpec) {
		m_strSpec = mStrSpec;
	}
    /**
     * 功能：获得出库单据号
     * @return
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
     * 功能：获得物品规格
     * @return
     */
    public String getProductid() {
        return this.productid;
    }
    /**
     * 功能：设置物品规格
     * @param productid
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }
    /**
     * 功能：获得货位Id
     * @return
     */
    public String getCargoSpaceId() {
        return this.cargoSpaceId;
    }
    /**
     * 功能：设置货位Id
     * @param cargoSpaceId
     */
    public void setCargoSpaceId(String cargoSpaceId) {
        this.cargoSpaceId = cargoSpaceId;
    }
    /**
     * 功能：获得巷道Id
     * @return
     */
    public String getCargoAlleyId() {
        return this.cargoAlleyId;
    }
    /**
     * 功能：设置巷道Id
     * @param cargoAlleyId
     */
    public void setCargoAlleyId(String cargoAlleyId) {
        this.cargoAlleyId = cargoAlleyId;
    }
    /**
     * 功能：获得库区ID
     * @return
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
     * 功能：获得包装单位
     * @return
     */
    public String getPkgunit() {
        return this.pkgunit;
    }
    /**
     * 功能：设置包装单位
     * @param pkgunit
     */
    public void setPkgunit(String pkgunit) {
        this.pkgunit = pkgunit;
    }
    /**
     * 功能：获得开单数量
     * @return
     */
    public double getInvoicenum() {
        return this.invoicenum;
    }
    /**
     * 功能：设置开单数量
     * @param invoicenum
     */
    public void setInvoicenum(double invoicenum) {
        this.invoicenum = invoicenum;
    }
    /**
     * 功能：获得预配数量
     * @return
     */
    public double getPreassignnum() {
        return this.preassignnum;
    }
    /**
     * 功能：设置预配数量
     * @param preassignnum
     */
    public void setPreassignnum(double preassignnum) {
        this.preassignnum = preassignnum;
    }
    /**
     * 功能：获得分配数量
     * @return
     */
    public double getAssignnum() {
        return this.assignnum;
    }
    /**
     * 功能：设置分配数量
     * @param assignnum
     */
    public void setAssignnum(double assignnum) {
        this.assignnum = assignnum;
    }
    /**
     * 功能：获得拣货数量
     * @return
     */
    public double getPicknum() {
        return this.picknum;
    }
    /**
     * 功能：设置拣货数量
     * @param picknum
     */
    public void setPicknum(double picknum) {
        this.picknum = picknum;
    }
    /**
     * 功能：获得发货数量
     * @return
     */
    public double getSendnum() {
        return this.sendnum;
    }
    /**
     * 功能：设置发货数量
     * @param sendnum
     */
    public void setSendnum(double sendnum) {
        this.sendnum = sendnum;
    }
    /**
     * 功能：获得客户id
     * @return
     */
    public String getCustomid() {
        return this.customid;
    }
    /**
     * 功能：设置客户id
     * @param customid
     */
    public void setCustomid(String customid) {
        this.customid = customid;
    }
    /**
     * 功能：获得来源标识 1-指定产品 2-指定货位 3-销售订单 4-外部调拨单
     * @return
     */
    public String getFbFlag() {
        return this.fbFlag;
    }
    /**
     * 功能：设置来源标识 1-指定产品 2-指定货位 3-销售订单 4-外部调拨单
     * @param fbFlag
     */
    public void setFbFlag(String fbFlag) {
        this.fbFlag = fbFlag;
    }
    /**
     * 功能：获得托盘条码
     * @return
     */
    public String getTraycode() {
        return this.traycode;
    }
    /**
     * 功能：设置托盘条码
     * @param traycode
     */
    public void setTraycode(String traycode) {
        this.traycode = traycode;
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
     * 功能：获得净重
     * @return
     */
    public double getSnetweight() {
        return this.snetweight;
    }
    /**
     * 功能：设置净重
     * @param snetweight
     */
    public void setSnetweight(double snetweight) {
        this.snetweight = snetweight;
    }
    /**
     * 功能：获得重量
     * @return
     */
    public double getSweight() {
        return this.sweight;
    }
    /**
     * 功能：设置重量
     * @param sweight
     */
    public void setSweight(double sweight) {
        this.sweight = sweight;
    }
    /**
     * 功能：获得体积
     * @return
     */
    public double getSvolume() {
        return this.svolume;
    }
    /**
     * 功能：设置体积
     * @param svolume
     */
    public void setSvolume(double svolume) {
        this.svolume = svolume;
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
     * 功能:获得分配净重
     * @author hug 2012-5-21 
     * @return
     */
    public double getAssignnetweight() {
        return assignnetweight;
    }

    /**
     * 功能：设置分配净重
     * @author hug 2012-5-21 
     * @param assignnetweight
     */
    public void setAssignnetweight(double assignnetweight) {
        this.assignnetweight = assignnetweight;
    }

    /**
     * 功能:获得分配体积
     * @author hug 2012-5-21 
     * @return
     */
    public double getAssignvolume() {
        return assignvolume;
    }

    /**
     * 功能：设置分配体积
     * @author hug 2012-5-21 
     * @param assignvolume
     */
    public void setAssignvolume(double assignvolume) {
        this.assignvolume = assignvolume;
    }

    /**
     * 功能:获得分配重量
     * @author hug 2012-5-21 
     * @return
     */
    public double getAssignweight() {
        return assignweight;
    }

    /**
     * 功能：设置分配重量
     * @author hug 2012-5-21 
     * @param assignweight
     */
    public void setAssignweight(double assignweight) {
        this.assignweight = assignweight;
    }

    /**
     * 功能:获得拣货净重
     * @author hug 2012-5-21 
     * @return
     */
    public double getPicknetweight() {
        return picknetweight;
    }

    /**
     * 功能：设置拣货净重
     * @author hug 2012-5-21 
     * @param picknetweight
     */
    public void setPicknetweight(double picknetweight) {
        this.picknetweight = picknetweight;
    }

    /**
     * 功能:获得拣货体积
     * @author hug 2012-5-21 
     * @return
     */
    public double getPickvolume() {
        return pickvolume;
    }

    /**
     * 功能：设置拣货体积
     * @author hug 2012-5-21 
     * @param pickvolume
     */
    public void setPickvolume(double pickvolume) {
        this.pickvolume = pickvolume;
    }

    /**
     * 功能:获得拣货重量
     * @author hug 2012-5-21 
     * @return
     */
    public double getPickweight() {
        return pickweight;
    }

    /**
     * 功能：设置拣货重量
     * @author hug 2012-5-21 
     * @param pickweight
     */
    public void setPickweight(double pickweight) {
        this.pickweight = pickweight;
    }

    /**
     * 功能:获得预配净重
     * @author hug 2012-5-21 
     * @return
     */
    public double getPreassignnetweight() {
        return preassignnetweight;
    }

    /**
     * 功能：设置预配净重
     * @author hug 2012-5-21 
     * @param preassignnetweight
     */
    public void setPreassignnetweight(double preassignnetweight) {
        this.preassignnetweight = preassignnetweight;
    }

    /**
     * 功能:获得预配体积
     * @author hug 2012-5-21 
     * @return
     */
    public double getPreassignvolume() {
        return preassignvolume;
    }

    /**
     * 功能：设置预配体积
     * @author hug 2012-5-21 
     * @param preassignvolume
     */
    public void setPreassignvolume(double preassignvolume) {
        this.preassignvolume = preassignvolume;
    }

    /**
     * 功能:获得预配重量
     * @author hug 2012-5-21 
     * @return
     */
    public double getPreassignweight() {
        return preassignweight;
    }

    /**
     * 功能：设置预配重量
     * @author hug 2012-5-21 
     * @param preassignweight
     */
    public void setPreassignweight(double preassignweight) {
        this.preassignweight = preassignweight;
    }

    
    public String getM_strProductName() {
        return m_strProductName;
    }

    
    public void setM_strProductName(String productName) {
        m_strProductName = productName;
    }
    /**
     * 功能：获得客户
     * @author hug 2012-6-20 
     * @return
     */
    public String getM_strCustomerName() {
        return m_strCustomerName;
    }


    /**
     * 功能：设置客户
     * @author hug 2012-6-20 
     * @param customerName
     */
    public void setM_strCustomerName(String customerName) {
        m_strCustomerName = customerName;
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
	 * 功能：设置
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
	 * 功能：获得包装ID
	 */
	public String getPackid() {
		return packid;
	}

	/**
	 * 功能：设置包装ID
	 * @param packid
	 */
	public void setPackid(String packid) {
		this.packid = packid;
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
	 * 功能：获得单据行状态 0-开单;1-预配 2-分配 3-部分拣货 4-完全拣货   7-发货
	 */
	public String getLinestatus() {
		return linestatus;
	}

	/**
	 * 功能：设置单据行状态 0-开单;1-预配 2-分配 3-部分拣货 4-完全拣货   7-发货
	 * @param linestatus
	 */
	public void setLinestatus(String linestatus) {
		this.linestatus = linestatus;
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

	public String getLotinfo() {
		return lotinfo;
	}

	public void setLotinfo(String lotinfo) {
		this.lotinfo = lotinfo;
	}

	public String getPrintdate() {
		return printdate;
	}

	public void setPrintdate(String printdate) {
		this.printdate = printdate;
	}

	public String getSoid() {
		return soid;
	}

	public void setSoid(String soid) {
		this.soid = soid;
	}

	public String getSodetailid() {
		return sodetailid;
	}

	public void setSodetailid(String sodetailid) {
		this.sodetailid = sodetailid;
	}
    
    

}