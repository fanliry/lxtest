package com.wms3.bms.standard.entity.job.lxyy;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;


/**
 * 
 * 描述: 作业详细
 * @author hug 2012-3-6
 * @since WMS 3.0
 */
public class InoutJobdetail  implements java.io.Serializable {

    private static final long serialVersionUID = -1749256160040811905L;
    private String jobdetailid;		//作业详细ID（作业详细与作业一一对应）
    private String jobid;          	//作业编号
    private String linestatus;     	//状态0:新建 4.完成 5.作废（暂时不用）
    private String inventoryid;    	//库存ID
 	private String productid;      	//物品ID
 	private String printdate;      	//生产日期
 	private String packid;      	//包装ID （暂时不用）
 	private String lotid;        	//批号类型ID
 	private String lotinfo;			//具体批号 (进厂编号)
 	private String punit;          	//单位 （与类型管理的 产品单位一一对应）
 	private double jobnum;        	//作业数量
 	private double assignnum;     	//分配数量（入库作业 分配数量与作业数量一致）
 	private String isplit;     	    //是否拆分（1：整托 2：分拆（零包）） 拆分数量 为分配数量assignnum
 	private double volume;         	//体积（暂时不用）
 	private double weight;         	//重量（暂时不用）
 	private double netweight;      	//净重（暂时不用）
 	private double assignvolume;   	//分配体积（暂时不用）
 	private double assignweight;   	//分配重量（暂时不用）
 	private double assignnetweight;	//分配净重（暂时不用）
 	private String customerid;     	//客户ID（暂时不用）
 	private String ownerId;        	//货主ID（暂时不用）
 	private String isreview;        //是否复核 复核成功 写Y 
 	private String reviewid;        //复核人ID
 	private String boxcode;        	//箱条码（暂时不用）
 	private String isinvoice;      	//是否已生成单据 Y是  N否
 	private String injobdetailid;  	//源作业详细（生成出库作业时的添加该库存的入库作业）
 	private String reserve1;       	//预留1   -（记录作废时的作废原因）---2014-8-28 09:11:29改
 	private String reserve2;       	//预留2
 	private String reserve3;       	//预留3
 	private String reserve4;       	//预留4

 	
 	private String transreceiptid;	//收货记录ID）
 	private String productStatus;	//物品状态
 	
     
     


	//派生属性
    protected String m_strCustomerName;		// 客户（暂时不用）
    protected String m_strOwnerName; 		// 货主（暂时不用）
    protected String m_strProductName;   	// 产品
    private String productStatusName;		//物品状态名称

	private String m_strPackName;       	//包装名称（暂时不用）
    private String m_strUnitName;      		//单位名称
    private String reviewMan;         		//复核人
    
    private String supplierName;           //供应商名称(包材入库、原辅料入库  使用)
    private String producttype;		       //产品类别
    
    //新增字段
    private String productcode;        //产品编码
    private double reviewnum;          //复核数量
    private String reviewTime;         //复核时间
    private String supplier;           //供应商id(包材入库、原辅料入库  使用)
    private String lotinfo2;		   //原厂编号 (进厂之前的编号，可以为空)

    // Constructors
    /** default constructor */
    public InoutJobdetail() {
    }

    
    // Property accessors
    /**
     * 功能：获得作业详细ID
     */
    public String getJobdetailid() {
        return this.jobdetailid;
    }
    /**
     * 功能：设置作业详细ID
     * @author hug 2012-3-6 
     * @param jobdetailid
     */
    public void setJobdetailid(String jobdetailid) {
        this.jobdetailid = jobdetailid;
    }
    /**
     * 功能：获得作业编号
     * @author hug 2012-3-6 
     * @return
     */
    public String getJobid() {
        return this.jobid;
    }
    /**
     * 功能：设置作业编号
     * @author hug 2012-3-6 
     * @param jobid
     */
    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
    public String getProductStatus() {
		return productStatus;
	}


	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
    /**
     * 功能：获得物品规格
     * @author hug 2012-3-6 
     * @return
     */
    public String getProductid() {
        return this.productid;
    }
    /**
     * 功能：设置物品规格
     * @author hug 2012-3-6 
     * @param productid
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }
    /**
     * 功能：获得单位
     * @author hug 2012-3-6 
     * @return
     */
    public String getPunit() {
        return this.punit;
    }
    /**
     * 功能：设置单位
     * @author hug 2012-3-6 
     * @param punit
     */
    public void setPunit(String punit) {
        this.punit = punit;
    }
    /**
     * 功能：获得作业数量
     * @author hug 2012-3-6 
     * @return
     */
    public double getJobnum() {
        return this.jobnum;
    }
    /**
     * 功能：设置作业数量
     * @author hug 2012-3-6 
     * @param jobnum
     */
    public void setJobnum(double jobnum) {
        this.jobnum = jobnum;
    }
    /**
     * 功能：获得分配数量
     * @author hug 2012-3-6 
     * @return
     */
    public double getAssignnum() {
        return this.assignnum;
    }
    /**
     * 功能：设置分配数量
     * @author hug 2012-3-6 
     * @param assignnum
     */
    public void setAssignnum(double assignnum) {
        this.assignnum = assignnum;
    }
    /**
     * 功能：获得体积
     * @author hug 2012-3-6 
     * @return
     */
    public double getVolume() {
        return this.volume;
    }
    /**
     * 功能：设置体积
     * @author hug 2012-3-6 
     * @param volume
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }
    /**
     * 功能：获得重量
     * @author hug 2012-3-6 
     * @return
     */
    public double getWeight() {
        return this.weight;
    }
    /**
     * 功能：设置重量
     * @author hug 2012-3-6 
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
    /**
     * 功能：获得净重
     * @author hug 2012-3-6 
     * @return
     */
    public double getNetweight() {
        return this.netweight;
    }
    /**
     * 功能：设置净重
     * @author hug 2012-3-6 
     * @param netweight
     */
    public void setNetweight(double netweight) {
        this.netweight = netweight;
    }
    /**
     * 功能：获得客户ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getCustomerid() {
        return this.customerid;
    }
    /**
     * 功能：设置客户ID
     * @author hug 2012-3-6 
     * @param customerid
     */
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }
    /**
     * 功能：获得分配净重
     * @author hug 2012-4-16 
     * @return
     */
    public double getAssignnetweight() {
        return assignnetweight;
    }
    /**
     * 功能：设置分配净重
     * @author hug 2012-4-16 
     * @param assignnetweight
     */
    public void setAssignnetweight(double assignnetweight) {
        this.assignnetweight = assignnetweight;
    }
    /**
     * 功能：获得分配体积
     * @author hug 2012-4-16 
     * @return
     */
    public double getAssignvolume() {
        return assignvolume;
    }
    /**
     * 功能：设置分配体积
     * @author hug 2012-4-16 
     * @param assignvolume
     */
    public void setAssignvolume(double assignvolume) {
        this.assignvolume = assignvolume;
    }
    /**
     * 功能：获得分配重量
     * @author hug 2012-4-16 
     * @return
     */
    public double getAssignweight() {
        return assignweight;
    }
    /**
     * 功能：设置分配重量
     * @author hug 2012-4-16 
     * @param assignweight
     */
    public void setAssignweight(double assignweight) {
        this.assignweight = assignweight;
    }
    /**
     * 功能：获得条码
     * @author hug 2012-4-16 
     * @return
     */
    public String getBoxcode() {
        return boxcode;
    }
    /**
     * 功能：设置条码
     * @author hug 2012-4-16 
     * @param boxcode
     */
    public void setBoxcode(String boxcode) {
        this.boxcode = boxcode;
    }
    /**
     * 功能：获得源作业详细（生成出库作业时的添加该库存的入库作业）
     * @author hug 2012-4-16 
     * @return
     */
    public String getInjobdetailid() {
        return injobdetailid;
    }
    /**
     * 功能：设置源作业详细（生成出库作业时的添加该库存的入库作业）
     * @author hug 2012-4-16 
     * @param injobdetailid
     */
    public void setInjobdetailid(String injobdetailid) {
        this.injobdetailid = injobdetailid;
    }
    /**
     * 功能：获得库存ID
     * @author hug 2012-4-16 
     * @return
     */
    public String getInventoryid() {
        return inventoryid;
    }
    /**
     * 功能：设置库存ID
     * @author hug 2012-4-16 
     * @param inventoryid
     */
    public void setInventoryid(String inventoryid) {
        this.inventoryid = inventoryid;
    }
    /**
     * 功能：获得是否已生成单据 Y是  N否
     * @author hug 2012-4-16 
     * @return
     */
    public String getIsinvoice() {
        return isinvoice;
    }
    /**
     * 功能：设置是否已生成单据 Y是  N否
     * @author hug 2012-4-16 
     * @param isinvoice
     */
    public void setIsinvoice(String isinvoice) {
        this.isinvoice = isinvoice;
    }
    /**
     * 功能：获得预留1 //预留1   -（记录作废时的作废原因）---2014-8-28 09:11:29改
     * @author hug 2012-4-16 
     * @return
     */
    public String getReserve1() {
        return reserve1;
    }
    /**
     * 功能：设置预留1 //预留1   -（记录作废时的作废原因）---2014-8-28 09:11:29改
     * @author hug 2012-4-16 
     * @param reserve1
     */
    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }
    /**
     * 功能：获得预留2
     * @author hug 2012-4-16 
     * @return
     */
    public String getReserve2() {
        return reserve2;
    }
    /**
     * 功能：设置预留2
     * @author hug 2012-4-16 
     * @param reserve2
     */
    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }
    /**
     * 功能：获得预留3
     * @author hug 2012-4-16 
     * @return
     */
    public String getReserve3() {
        return reserve3;
    }
    /**
     * 功能：设置预留3
     * @author hug 2012-4-16 
     * @param reserve3
     */
    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }
    /**
     * 功能：获得预留4
     * @author hug 2012-4-16 
     * @return
     */
    public String getReserve4() {
        return reserve4;
    }
    /**
     * 功能：设置预留4
     * @author hug 2012-4-16 
     * @param reserve4
     */
    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }
    /**
     * 功能：获得货主ID
     * @author hug 2012-5-21 
     * @return
     */
    public String getOwnerId() {
        return ownerId;
    }
    /**
     * 功能：设置货主ID
     * @author hug 2012-5-21 
     * @param ownerId
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    /**
     * 功能：获得状态0:新建 4.完成 5.作废
     * @author hug 2012-6-5 
     * @return
     */
    public String getLinestatus() {
        return linestatus;
    }
    /**
     * 功能：设置状态0:新建 4.完成 5.作废
     * @author hug 2012-6-5 
     * @param linestatus
     */
    public void setLinestatus(String linestatus) {
        this.linestatus = linestatus;
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
     * 功能：获得货主
     * @author hug 2012-6-20 
     * @return
     */
    public String getM_strOwnerName() {
        return m_strOwnerName;
    }
    /**
     * 功能：设置货主
     * @author hug 2012-6-20 
     * @param ownerName
     */
    public void setM_strOwnerName(String ownerName) {
        m_strOwnerName = ownerName;
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
    
    /**
     * 功能：获得复核数量
     * @author hug 2012-9-19 
     * @return
     */
    public double getReviewnum() {
        return reviewnum;
    }

    /**
     * 功能：设置复核数量
     * @author hug 2012-9-19 
     * @param reviewnum
     */
    public void setReviewnum(double reviewnum) {
        this.reviewnum = reviewnum;
    }


	public String getLotinfo() {
		return lotinfo;
	}


	public void setLotinfo(String lotinfo) {
		this.lotinfo = lotinfo;
	}


	public String getReviewid() {
		return reviewid;
	}


	public void setReviewid(String reviewid) {
		this.reviewid = reviewid;
	}


	public String getReviewMan() {
		return reviewMan;
	}


	public void setReviewMan(String reviewMan) {
		this.reviewMan = reviewMan;
	}


	public String getIsplit() {
		return isplit;
	}


	public void setIsplit(String isplit) {
		this.isplit = isplit;
	}


	public String getPrintdate() {
		return printdate;
	}


	public void setPrintdate(String printdate) {
		this.printdate = printdate;
	}
	
		
	public String getSupplier() {
		return supplier;
	}


	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * 获得：原厂编号 (进厂之前的编号，可以为空)
	 * @return
	 */
	public String getLotinfo2() {
		return lotinfo2;
	}

	/**
	 * 设置：原厂编号 (进厂之前的编号，可以为空)
	 * @param lotinfo2
	 */
	public void setLotinfo2(String lotinfo2) {
		this.lotinfo2 = lotinfo2;
	}

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}


	/**
	 * 功能:根据作业id查询作业明细
	 * @param jobid			作业号
	 * @return 
	 * @throws Exception
	 */
	public InoutJobdetail getJobDetailByJobid(String jobid,EntityDAO dao) throws Exception {
		
		if(jobid != null){
		
			String strSql = " from InoutJobdetail as t where t.jobid='" + jobid + "'";
			List ls = dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (InoutJobdetail)ls.get(0);
			}
		}
		return null;
	}

	
	/**
	 * 功能:
	 * @param productid			
	 * @param lotinfo			
	 * @return 
	 * @throws Exception
	 */
	public static int getJobDLotsum(String productid,String lotinfo,EntityDAO dao) throws Exception {
		
		if(productid != null && lotinfo != null){
		
			String strSql = " select COUNT(*)  from InoutJobdetail as t where t.productid='" + productid + "' and t.lotinfo='" + lotinfo + "'";
			return dao.searchEntitieCount(strSql);

		}
		return 0;
	}

	public String getReviewTime() {
		return reviewTime;
	}


	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}


	public String getIsreview() {
		return isreview;
	}


	public void setIsreview(String isreview) {
		this.isreview = isreview;
	}
	
    public String getProductStatusName() {
		return productStatusName;
	}

	public void setProductStatusName(String productStatusName) {
		this.productStatusName = productStatusName;
	}
    
}