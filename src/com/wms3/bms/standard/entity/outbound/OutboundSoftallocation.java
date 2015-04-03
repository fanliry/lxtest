package com.wms3.bms.standard.entity.outbound;


/**
 * 描述：预配
 * @author hug
 *
 */
public class OutboundSoftallocation  implements java.io.Serializable {
     
    
	private static final long serialVersionUID = 4012223973119209218L;
	
	private String softallocationid;	//预分配ID
	private String status;				//状态 0:新建 1:完成 2：作废
	private String invoiceid;			//单据编号
	private String invoicedetailid;		//单据详细ID
	private String edittime;			//预配时间
	private String editwho;				//预配人
	private String starttime;			//预配开始时间
	private String endtime;				//预配结束时间
    private String warehouseid;         //仓库ID
    private String whAreaId;            //库区ID
	private String customerid;			//客户ID
    private String ownerId;            //货主ID
	private String productid;			//产品ID
	private String lotid;				//批次ID
	private String packid;				//包装代码
	private String pkgunit;				//单位
	private double assignnum;			//数量
	private double netweight;			//净重
	private double weight;				//重量
	private double volume;				//体积
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
    private String m_strProductName;    //品名
    protected String m_strCustomerName; //客户	

    //新增字段
    private String boxcode;            //箱条码
    private String productcode;        //产品条码

    // Constructors

    /** default constructor */
    public OutboundSoftallocation() {
    }

	/** minimal constructor */
    public OutboundSoftallocation(String productid) {
        this.productid = productid;
    }
    
   
    // Property accessors

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
	 * 功能：获得数量
	 */
	public double getAssignnum() {
		return assignnum;
	}

	/**
	 * 功能：设置数量
	 * @param assignnum
	 */
	public void setAssignnum(double assignnum) {
		this.assignnum = assignnum;
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
	 * 功能：获得预配时间
	 */
	public String getEdittime() {
		return edittime;
	}

	/**
	 * 功能：设置预配时间
	 * @param edittime
	 */
	public void setEdittime(String edittime) {
		this.edittime = edittime;
	}

	/**
	 * 功能：获得预配人
	 */
	public String getEditwho() {
		return editwho;
	}

	/**
	 * 功能：设置预配人
	 * @param editwho
	 */
	public void setEditwho(String editwho) {
		this.editwho = editwho;
	}

	/**
	 * 功能：获得预配结束时间
	 */
	public String getEndtime() {
		return endtime;
	}

	/**
	 * 功能：设置预配结束时间
	 * @param endtime
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/**
	 * 功能：获得单据详细ID
	 */
	public String getInvoicedetailid() {
		return invoicedetailid;
	}

	/**
	 * 功能：设置单据详细ID
	 * @param invoicedetailid
	 */
	public void setInvoicedetailid(String invoicedetailid) {
		this.invoicedetailid = invoicedetailid;
	}

	/**
	 * 功能：获得单据编号
	 */
	public String getInvoiceid() {
		return invoiceid;
	}

	/**
	 * 功能：设置单据编号
	 * @param invoiceid
	 */
	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}

	/**
	 * 功能：获得净重
	 */
	public double getNetweight() {
		return netweight;
	}

	/**
	 * 功能：设置净重
	 * @param netweight
	 */
	public void setNetweight(double netweight) {
		this.netweight = netweight;
	}

	/**
	 * 功能：获得单位
	 */
	public String getPkgunit() {
		return pkgunit;
	}

	/**
	 * 功能：设置单位
	 * @param pkgunit
	 */
	public void setPkgunit(String pkgunit) {
		this.pkgunit = pkgunit;
	}

	/**
	 * 功能：获得产品ID
	 */
	public String getProductid() {
		return productid;
	}

	/**
	 * 功能：设置产品ID
	 * @param productid
	 */
	public void setProductid(String productid) {
		this.productid = productid;
	}

	/**
	 * 功能：获得预分配ID
	 */
	public String getSoftallocationid() {
		return softallocationid;
	}

	/**
	 * 功能：设置预分配ID
	 * @param softallocationid
	 */
	public void setSoftallocationid(String softallocationid) {
		this.softallocationid = softallocationid;
	}

	/**
	 * 功能：获得预配开始时间
	 */
	public String getStarttime() {
		return starttime;
	}

	/**
	 * 功能：设置预配开始时间
	 * @param starttime
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/**
	 * 功能：获得状态
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 功能：设置状态
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 功能：获得体积
	 */
	public double getVolume() {
		return volume;
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
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * 功能：设置重量
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
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
     * 功能：获得库区ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getWhAreaId() {
        return this.whAreaId;
    }
    /**
     * 功能：设置库区ID
     * @author hug 2012-3-6 
     * @param whAreaId
     */
    public void setWhAreaId(String whAreaId) {
        this.whAreaId = whAreaId;
    }
    /**
     * 功能：获得仓库ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getWarehouseid() {
        return this.warehouseid;
    }
    /**
     * 功能：设置仓库ID
     * @author hug 2012-3-6 
     * @param warehouseid
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }
    /**
     * 功能：获得货主ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getOwnerId() {
        return this.ownerId;
    }
    /**
     * 功能：设置货主ID
     * @author hug 2012-3-6 
     * @param ownerId
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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