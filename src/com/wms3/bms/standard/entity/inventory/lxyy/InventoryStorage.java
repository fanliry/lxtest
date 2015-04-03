package com.wms3.bms.standard.entity.inventory.lxyy;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;

/**
 * 
 * 描述: 库存表(一个货位有多个产品、批次、货主)
 * @author hug 2012-3-6
 * @since WMS 3.0
 */
public class InventoryStorage  implements java.io.Serializable {

     private static final long serialVersionUID = -4103033685800670300L;
     private String inventoryid;    	//库存ID
     private String cargoSpaceId;   	//货位ID
    

	 private String cargoAlleyId;   	    //巷道ID
     private String cargoAreaId;    	//货区ID
     private String whAreaId;       	//库区ID
     private String warehouseid;    	//仓库ID
     private String ownerId;            //货主ID（暂时不用）
     private String productid;          //货品ID
     private String productdate;		//产品生产日期
     private String indate;             //入库时间
     private String lotid;          	//批号ID
     private String lotinfo;            //批号信息(进厂编号)
     private String packid;             //包装ID
     private String intype;             //入库方式 1.联机 2.脱机
     private String punit;              //计量单位
     private String isplit;             //1：整托 2：分拆（零包）
     private String status;             //状态  0:未分配，1：已分配，2:需调整， 3:调整 4:货品调整；5：盘点       (暂存区状态)
     
     
     private double pnum;               //库存数量
     private double assignnum;          //分配数量（暂时不用）
     private double holdnum;            //冻结数量（暂时不用）
     private double pvolume;            //库存体积（暂时不用）
     private double pweight;            //库存重量（暂时不用）
     private double pnetweight;         //库存净重（暂时不用）
     private double assignvolume;       //分配体积（暂时不用）
     private double assignweight;       //分配重量（暂时不用）
     private double assignnetweight;    //分配净重（暂时不用）
     private double holdvolume;         //冻结体积（暂时不用）
     private double holdweight;         //冻结重量（暂时不用）
     private double holdnetweight;      //冻结净重（暂时不用）

	 private String injobid;            //作业ID
     private String injobetailid;       //作业详细ID
     private String traycode;           //托盘条码

     private String requestid;          //申请单id
     private String instockid;          //入库单id
     private String reserve3;           //预留3
     private String reserve4;           //预留4
     
     public String lotatt1;  			//批次属性1
     public String lotatt2;  			//批次属性2
     public String lotatt3;  			//批次属性3------------------------------------
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
     private String warehouseName;		//仓库名称
	 private String whAreaName;			//库区名称
 	 private String cargoSpaceName;		//货位名称
 	 private String productName;		//物品名称
 	 private String ownerName;			//货主名称
     private String punitname;          //单位名称
     private String packagename;        //包装名称
     private String productStatusName;	//物品状态名称
     private String statusName;         //状态  0:未分配，1：已分配 3:调整 4:货品调整；5：盘点       (暂存区状态)
     private String Virtualwhname;    	//逻辑库区名称
     private String supplierName;       //供应商名称(包材入库、原辅料入库  使用)

 	//新增字段
     private String boxcode;            //箱条码
     private String productcode;        //产品条码
     private String productstatus;      //物品状态 合格,不合格,待检；（待检状态是不能出库的）
     private String Virtualwhid;		//逻辑库区id
     private String supplier;           //供应商id(包材入库、原辅料入库  使用)
     private String lotinfo2;		    //原厂编号
     private String producttype;		    //产品类别
     
     
     public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getProductStatusName() {
		return productStatusName;
	}

	public void setProductStatusName(String productStatusName) {
		this.productStatusName = productStatusName;
	}

	// Constructors
    /** default constructor */
    public InventoryStorage() {
    }
    
    // Property accessors
    /**
     * 功能：获得库存ID
     */
    public String getInventoryid() {
        return this.inventoryid;
    }
    /**
     * 功能：设置库存ID
     * @author hug 2012-3-6 
     * @param inventoryid
     */
    public void setInventoryid(String inventoryid) {
        this.inventoryid = inventoryid;
    }
    /**
     * 功能：获得货位ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getCargoSpaceId() {
        return this.cargoSpaceId;
    }
    
    public void setCargoSpaceId(String cargoSpaceId) {
		this.cargoSpaceId = cargoSpaceId;
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
     * 功能：获得货区ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getCargoAreaId() {
        return this.cargoAreaId;
    }
    /**
     * 功能：设置货区ID
     * @author hug 2012-3-6 
     * @param cargoAreaId
     */
    public void setCargoAreaId(String cargoAreaId) {
        this.cargoAreaId = cargoAreaId;
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
     * 功能：获得货品ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getProductid() {
        return this.productid;
    }
    /**
     * 功能：设置货品ID
     * @author hug 2012-3-6 
     * @param productid
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }
    /**
     * 功能：获得入库时间
     * @author hug 2012-3-6 
     * @return
     */
    public String getIndate() {
        return this.indate;
    }
    /**
     * 功能：设置入库时间
     * @author hug 2012-3-6 
     * @param indate
     */
    public void setIndate(String indate) {
        this.indate = indate;
    }
    /**
     * 功能：获得批次属性ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getLotid() {
        return this.lotid;
    }
    /**
     * 功能：设置批次属性ID
     * @author hug 2012-3-6 
     * @param lotnum
     */
    public void setLotid(String lotid) {
        this.lotid = lotid;
    }
    /**
     * 功能：获得包装ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getPackid() {
        return this.packid;
    }
    /**
     * 功能：设置包装ID
     * @author hug 2012-3-6 
     * @param packid
     */
    public void setPackid(String packid) {
        this.packid = packid;
    }
    /**
     * 功能：获得入库方式 1.联机 2.脱机
     * @author hug 2012-3-6 
     * @return
     */
    public String getIntype() {
        return this.intype;
    }
    /**
     * 功能：设置入库方式 1.联机 2.脱机
     * @author hug 2012-3-6 
     * @param intype
     */
    public void setIntype(String intype) {
        this.intype = intype;
    }
    /**
     * 功能：获得计量单位
     * @author hug 2012-3-6 
     * @return
     */
    public String getPunit() {
        return this.punit;
    }
    /**
     * 功能：设置计量单位
     * @author hug 2012-3-6 
     * @param punit
     */
    public void setPunit(String punit) {
        this.punit = punit;
    }
    /**
     * 功能：获得库存数量
     * @author hug 2012-3-6 
     * @return
     */
    public double getPnum() {
        return this.pnum;
    }
    /**
     * 功能：设置库存数量
     * @author hug 2012-3-6 
     * @param pnum
     */
    public void setPnum(double pnum) {
        this.pnum = pnum;
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
     * 功能：获得冻结数量
     * @author hug 2012-3-6 
     * @return
     */
    public double getHoldnum() {
        return this.holdnum;
    }
    /**
     * 功能：设置冻结数量
     * @author hug 2012-3-6 
     * @param availablenum
     */
    public void setHoldnum(double holdnum) {
        this.holdnum = holdnum;
    }
    /**
     * 功能：获得库存体积
     * @author hug 2012-3-6 
     * @return
     */
    public double getPvolume() {
        return this.pvolume;
    }
    /**
     * 功能：设置库存体积
     * @author hug 2012-3-6 
     * @param pvolume
     */
    public void setPvolume(double pvolume) {
        this.pvolume = pvolume;
    }
    /**
     * 功能：获得库存重量
     * @author hug 2012-3-6 
     * @return
     */
    public double getPweight() {
        return this.pweight;
    }
    /**
     * 功能：设置库存重量
     * @author hug 2012-3-6 
     * @param pweight
     */
    public void setPweight(double pweight) {
        this.pweight = pweight;
    }
    /**
     * 功能：获得库存净重
     * @author hug 2012-3-6 
     * @return
     */
    public double getPnetweight() {
        return this.pnetweight;
    }
    /**
     * 功能：设置库存净重
     * @author hug 2012-3-6 
     * @param pnetweight
     */
    public void setPnetweight(double pnetweight) {
        this.pnetweight = pnetweight;
    }
    /**
     * 功能：获得分配体积
     * @author hug 2012-3-6 
     * @return
     */
    public double getAssignvolume() {
        return this.assignvolume;
    }
    /**
     * 功能：设置分配体积
     * @author hug 2012-3-6 
     * @param assignvolume
     */
    public void setAssignvolume(double assignvolume) {
        this.assignvolume = assignvolume;
    }
    /**
     * 功能：获得分配重量
     * @author hug 2012-3-6 
     * @return
     */
    public double getAssignweight() {
        return this.assignweight;
    }
    /**
     * 功能：设置分配重量
     * @author hug 2012-3-6 
     * @param assignweight
     */
    public void setAssignweight(double assignweight) {
        this.assignweight = assignweight;
    }
    /**
     * 功能：获得分配净重
     * @author hug 2012-3-6 
     * @return
     */
    public double getAssignnetweight() {
        return this.assignnetweight;
    }
    /**
     * 功能：设置分配净重
     * @author hug 2012-3-6 
     * @param assignnetweight
     */
    public void setAssignnetweight(double assignnetweight) {
        this.assignnetweight = assignnetweight;
    }
    /**
     * 功能：获得冻结体积
     * @author hug 2012-3-6 
     * @return
     */
    public double getHoldvolume() {
        return this.holdvolume;
    }
    /**
     * 功能：设置冻结体积
     * @author hug 2012-3-6 
     * @param availablevolume
     */
    public void setHoldvolume(double availablevolume) {
        this.holdvolume = availablevolume;
    }
    /**
     * 功能：获得冻结重量
     * @author hug 2012-3-6 
     * @return
     */
    public double getHoldweight() {
        return this.holdweight;
    }
    /**
     * 功能：设置冻结重量
     * @author hug 2012-3-6 
     * @param availableweight
     */
    public void setHoldweight(double holdweight) {
        this.holdweight = holdweight;
    }
    /**
     * 功能：获得冻结净重
     * @author hug 2012-3-6 
     * @return
     */
    public double getHoldnetweight() {
        return this.holdnetweight;
    }
    /**
     * 功能：设置冻结净重
     * @author hug 2012-3-6 
     * @param availablenetweight
     */
    public void setHoldnetweight(double holdnetweight) {
        this.holdnetweight = holdnetweight;
    }
    /**
     * 功能：获得作业ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getInjobid() {
        return this.injobid;
    }
    /**
     * 功能：设置作业ID
     * @author hug 2012-3-6 
     * @param injobid
     */
    public void setInjobid(String injobid) {
        this.injobid = injobid;
    }
    /**
     * 功能：获得作业详细ID
     * @author hug 2012-3-6 
     * @return
     */
    public String getInjobetailid() {
        return this.injobetailid;
    }
    /**
     * 功能：设置作业详细ID
     * @author hug 2012-3-6 
     * @param injobetailid
     */
    public void setInjobetailid(String injobetailid) {
        this.injobetailid = injobetailid;
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
     * 功能：获得预留1
     * @author hug 2012-4-23 
     * @return
     */
    public String getRequestid() {
        return requestid;
    }
    /**
     * 功能：设置预留1
     * @author hug 2012-4-23 
     * @param reserve1
     */
    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }
    /**
     * 功能：获得预留2
     * @author hug 2012-4-23 
     * @return
     */
    public String getInstockid() {
        return instockid;
    }
    /**
     * 功能：设置预留2
     * @author hug 2012-4-23 
     * @param reserve2
     */
    public void setInstockid(String instockid) {
        this.instockid = instockid;
    }
    /**
     * 功能：获得预留3
     * @author hug 2012-4-23 
     * @return
     */
    public String getReserve3() {
        return reserve3;
    }
    /**
     * 功能：设置预留3
     * @author hug 2012-4-23 
     * @param reserve3
     */
    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    /**
     * 功能：获得预留4
     * @author hug 2012-4-23 
     * @return
     */
    public String getReserve4() {
        return reserve4;
    }

    /**
     * 功能：设置预留4
     * @author hug 2012-4-23 
     * @param reserve4
     */
    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }
    /**
     * 功能：获得货位名称
     * @author hug 2012-4-23 
     * @return
     */
    public String getCargoSpaceName() {
        return cargoSpaceName;
    }
    /**
     * 功能：设置货位名称
     * @author hug 2012-4-23 
     * @param cargoSpaceName
     */
    public void setCargoSpaceName(String cargoSpaceName) {
        this.cargoSpaceName = cargoSpaceName;
    }
    /**
     * 功能：获得物品名称
     * @author hug 2012-4-23 
     * @return
     */
    public String getProductName() {
        return productName;
    }
    /**
     * 功能：设置物品名称
     * @author hug 2012-4-23 
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**
     * 功能：获得货主名称
     * @author hug 2012-4-23 
     * @return
     */
    public String getOwnerName() {
        return ownerName;
    }
    /**
     * 功能：设置货主名称
     * @author hug 2012-4-23 
     * @param ownerName
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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
	 * 功能：获得库区名称
	 */
	public String getWhAreaName() {
		return whAreaName;
	}

	/**
	 * 功能：设置库区名称
	 * @param whAreaName
	 */
	public void setWhAreaName(String whAreaName) {
		this.whAreaName = whAreaName;
	}

    /**
     * 功能:获得单位名称
     * @author hug 2012-9-20 
     * @return
     */
    public String getPunitname() {
        return punitname;
    }

    /**
     * 功能:设置单位名称
     * @author hug 2012-9-20 
     * @param punitname
     */
    public void setPunitname(String punitname) {
        this.punitname = punitname;
    }

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
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

    /**
     * 功能:获得物品状态 合格,不合格,待检；（待检状态是不能出库的）
     * @author hug 2012-11-6 
     * @return
     */
    public String getProductstatus() {
        return productstatus;
    }

    /**
     * 功能:设置物品状态 合格,不合格,待检；（待检状态是不能出库的）
     * @author hug 2012-11-6 
     * @param productstatus
     */
    public void setProductstatus(String productstatus) {
        this.productstatus = productstatus;
    }
	 public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getLotinfo() {
		return lotinfo;
	}

	public void setLotinfo(String lotinfo) {
		this.lotinfo = lotinfo;
	}

	public String getProductdate() {
		return productdate;
	}

	public void setProductdate(String productdate) {
		this.productdate = productdate;
	}

	public String getIsplit() {
		return isplit;
	}

	public void setIsplit(String isplit) {
		this.isplit = isplit;
	}
	
	public String getVirtualwhname() {
		return Virtualwhname;
	}

	public void setVirtualwhname(String virtualwhname) {
		Virtualwhname = virtualwhname;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getVirtualwhid() {
		return Virtualwhid;
	}

	public void setVirtualwhid(String virtualwhid) {
		Virtualwhid = virtualwhid;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getLotinfo2() {
		return lotinfo2;
	}

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
	 * 根据货位查询库存
	 * @param cargoSpaceId
	 * @return
	 */
	public String getQueryHQLByspaceid(String cargoSpaceId)
	{
		String hql = "from InventoryNeedcheck where 1=1 ";
		if(cargoSpaceId != null && cargoSpaceId.trim().length() > 0){
			hql += " and cargoSpaceId='"+cargoSpaceId+"'";
		}
		return hql;
	}
	/**
	 * 根据托盘条码及库区
	 * @param traycode
	 * @return
	 */
	public static InventoryStorage getQueryHQLBytraycode(String traycode,String whAreaid,EntityDAO dao)
	{
		String hql = "from InventoryStorage where 1=1 ";
		if(traycode != null && traycode.trim().length() > 0){
			hql += " and traycode='"+traycode+"'";
		}
		if(whAreaid != null && whAreaid.trim().length() > 0){
			hql += " and whAreaId='"+whAreaid+"'";
		}
		List ls = dao.searchEntities(hql);
		if(ls != null && ls.size() > 0){
			return (InventoryStorage)ls.get(0);
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
		
			String strSql = " select COUNT(*)  from InventoryStorage as t where t.productid='" + productid + "' and t.lotinfo='" + lotinfo + "'";
			return dao.searchEntitieCount(strSql);

		}
		return 0;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}