package com.wms3.bms.standard.entity.base;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 描述：物品表
 * @author hug
 */
public class BaseProduct  implements java.io.Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -54472344660924613L;
	
    // Fields    
	private String productid;			// 产品ID
	private String productCode;			//产品编码
   

	private String productname;			// 产品标准名
    private String spec;				// 产品规格
    private String barcode;				// 产品批号
    private String pkspec;				// 产品商用名
    private String recunit;				// 计量单位
    private String sendunit;			// 缺省发货单位
    private String useflag;				// 可用标志
    private double length;				// 长
    private double width;				// 宽
    private double height;				// 高
    private double upperlimit;			// 库存上限
    private double lowerlimit;			// 库存下限
    private double weight;				// 重量
    private double netweight;			// 净重
    private double tareweight;			// 皮重
    private double volume;				// 体积
    private String pttypeid;			// 物品类别ID
    private String storageareaid;		// 存储环境ID
    private String storagespaceid;		// 存储货位ID
    private String putawayid;			// 上架规则ID
    private String allocationid;		// 分配规则ID
    private String replenishid;			// 补货规则ID
    private String pakageid;		    // 包装规则id
    private String lotid;               // 批次ID
    private String customerid;          // 客户ID
    private String producedate;			// 生产日期
    private double validityterm;		// 有效期
    private String validitytype;		// 有效期类型
    private String remark1;				// 备注
    private String remark2;				// 备注
    private String remark3;				// 备注
    private String remark4;				// 备注
    private String remark5;				// 备注
    private String remark6;				// 备注
    private String remark7;				// 备注
    
    
    private String isexcess;            // 是否超量收货
    private String isproductmixed;      // 允许产品混放
    private String isbatchmixed;        // 允许批次混放
    private String producttype;
    
    
    
    //  派生属性
    private String storageareaname;		// 存储库区名称
    private String storagespacename;	// 存储货位名称
    private String lotdesc;				// 批次属性描述
    private String pkgdesc;				// 包装描述
    private String customername;		// 客户名称
    private String validitytypename;	// 有效期类型名
    private String pttypename;			// 物品类别名
    private String putawayname;			// 上架规则名称
    private String allocationname;		// 分配规则名称
    private String replenishname;		// 补货规则名称
    private String producttypename;     // 物品类别名称
    

    // Constructors

    /** default constructor */
    public BaseProduct() {
    }
    
    public BaseProduct(String productid, String productname) {
        this.productid = productid;
        this.productname = productname;
    }

   
    // Property accessors
    /**
     * 功能：获得物品ID
     */
    public String getProductid() {
        return this.productid;
    }
    
    /**
     * 功能：设置物品ID
     * @param productid
     */ 
    public void setProductid(String productid) {
        this.productid = productid;
    }
    public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
    /**
     * 功能：获得产品标准名
     */
    public String getProductname() {
        return this.productname;
    }
    
    /**
     * 功能：设置产品标准名
     * @param productname
     */ 
    public void setProductname(String productname) {
        this.productname = productname;
    }

    /**
     * 功能：获得产品规格
     */
    public String getSpec() {
        return this.spec;
    }
    
    /**
     * 功能：设置产品规格
     * @param spec
     */ 
    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     * 功能：获得产品批号
     */
    public String getBarcode() {
        return this.barcode;
    }
    
    /**
     * 功能：设置产品批号
     * @param barcode
     */ 
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * 功能：获得产品商用名
     */
    public String getPkspec() {
        return this.pkspec;
    }
    
    /**
     * 功能：设置产品商用名
     * @param pkspec
     */ 
    public void setPkspec(String pkspec) {
        this.pkspec = pkspec;
    }



    /**
     * 功能：获得计量单位
     */
    public String getRecunit() {
        return this.recunit;
    }
    
    /**
     * 功能：设置计量单位
     * @param recunit
     */ 
    public void setRecunit(String recunit) {
        this.recunit = recunit;
    }

    /**
     * 功能：获得发货单位
     */
    public String getSendunit() {
        return this.sendunit;
    }
    
    /**
     * 功能：设置发货单位
     * @param sendunit
     */ 
    public void setSendunit(String sendunit) {
        this.sendunit = sendunit;
    }

    /**
     * 功能：获得可用标志
     */
    public String getUseflag() {
        return this.useflag;
    }
    
    /**
     * 功能：设置可用标志
     * @param useflag
     */ 
    public void setUseflag(String useflag) {
        this.useflag = useflag;
    }

    /**
     * 功能：获得长
     */
    public double getLength() {
        return this.length;
    }
    
    /**
     * 功能：设置长
     * @param length
     */ 
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * 功能：获得宽
     */
    public double getWidth() {
        return this.width;
    }
    
    /**
     * 功能：设置宽
     * @param width
     */ 
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * 功能：获得高
     */
    public double getHeight() {
        return this.height;
    }
    
    /**
     * 功能：设置高
     * @param height
     */ 
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * 功能：获得库存上限
     */
    public double getUpperlimit() {
        return this.upperlimit;
    }
    
    /**
     * 功能：设置库存上限
     * @param upperlimit
     */ 
    public void setUpperlimit(double upperlimit) {
        this.upperlimit = upperlimit;
    }

    /**
     * 功能：获得库存下限
     */
    public double getLowerlimit() {
        return this.lowerlimit;
    }
    
    /**
     * 功能：设置库存下限
     * @param lowerlimit
     */ 
    public void setLowerlimit(double lowerlimit) {
        this.lowerlimit = lowerlimit;
    }

    /**
     * 功能：获得重量
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
     * 功能：获得皮重
     */
    public double getTareweight() {
        return this.tareweight;
    }
    
    /**
     * 功能：设置皮重
     * @param tareweight
     */ 
    public void setTareweight(double tareweight) {
        this.tareweight = tareweight;
    }

    /**
     * 功能：获得体积
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
     * 功能：获得物品类别ID
     */
    public String getPttypeid() {
        return this.pttypeid;
    }
    
    /**
     * 功能：设置物品类别ID
     * @param pttypeid
     */ 
    public void setPttypeid(String pttypeid) {
        this.pttypeid = pttypeid;
    }

    /**
     * 功能：获得存储库区ID
     */
    public String getStorageareaid() {
        return this.storageareaid;
    }
    
    /**
     * 功能：设置存储库区ID
     * @param storageareaid
     */ 
    public void setStorageareaid(String storageareaid) {
        this.storageareaid = storageareaid;
    }
    
    /**
     * 功能：获得批次ID
     */
    public String getLotid() {
        return lotid;
    }

    /**
     * 功能：设置批次ID
     * @param lotid
     */ 
    public void setLotid(String lotid) {
        this.lotid = lotid;
    }

    /**
     * 功能：获得包装ID
     */
    public String getPakageid() {
        return this.pakageid;
    }
    
    /**
     * 功能：设置包装ID
     * @param pakageid
     */ 
    public void setPakageid(String pakageid) {
        this.pakageid = pakageid;
    }

    /**
     * 功能：获得生产日期
     */
    public String getProducedate() {
        return this.producedate;
    }
    
    /**
     * 功能：设置生产日期
     * @param producedate
     */ 
    public void setProducedate(String producedate) {
        this.producedate = producedate;
    }

    /**
     * 功能：获得有效期
     */
    public double getValidityterm() {
        return this.validityterm;
    }
    
    /**
     * 功能：设置有效期
     * @param validityterm
     */ 
    public void setValidityterm(double validityterm) {
        this.validityterm = validityterm;
    }

    /**
     * 功能：获得有效期类型
     */
    public String getValiditytype() {
        return this.validitytype;
    }
    
    /**
     * 功能：设置有效期类型
     * @param validitytype
     */ 
    public void setValiditytype(String validitytype) {
        this.validitytype = validitytype;
    }

    /**
     * 功能：获得备注
     */
    public String getRemark1() {
        return this.remark1;
    }
    
    /**
     * 功能：设置备注
     * @param remark1
     */ 
    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    /**
     * 功能：获得备注
     */
    public String getRemark2() {
        return this.remark2;
    }
    
    /**
     * 功能：设置备注
     * @param remark2
     */ 
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    /**
     * 功能：获得备注
     */
    public String getRemark3() {
        return this.remark3;
    }
    
    /**
     * 功能：设置备注
     * @param remark3
     */ 
    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    /**
     * 功能：获得备注
     */
    public String getRemark5() {
        return this.remark5;
    }
    
    /**
     * 功能：设置备注
     * @param remark5
     */ 
    public void setRemark5(String remark5) {
        this.remark5 = remark5;
    }

    /**
     * 功能：获得备注
     */
    public String getRemark4() {
        return this.remark4;
    }
    
    /**
     * 功能：设置备注
     * @param remark4
     */ 
    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

    /**
     * 功能：获得备注
     */
    public String getRemark6() {
        return this.remark6;
    }
    
    /**
     * 功能：设置备注
     * @param remark6
     */ 
    public void setRemark6(String remark6) {
        this.remark6 = remark6;
    }

    /**
     * 功能：获得备注
     */
    public String getRemark7() {
        return this.remark7;
    }
    
    /**
     * 功能：设置备注
     * @param remark7
     */ 
    public void setRemark7(String remark7) {
        this.remark7 = remark7;
    }
    
    /**
     * 功能：获得存储库区名称
     */
    public String getStorageareaname() {
        return this.storageareaname;
    }
    
    /**
     * 功能：设置存储库区名称
     * @param storageareaname
     */ 
    public void setStorageareaname(String storageareaname) {
        this.storageareaname = storageareaname;
    }
    
    /**
     * 功能：获得批次属性描述
     */
    public String getLotdesc() {
        return this.lotdesc;
    }
    
    /**
     * 功能：设置批次属性描述
     * @param lotdesc
     */ 
    public void setLotdesc(String lotdesc) {
        this.lotdesc = lotdesc;
    }
    
    /**
     * 功能：获得包装描述
     */
    public String getPkgdesc() {
        return this.pkgdesc;
    }
    
    /**
     * 功能：设置包装描述
     * @param pkgdesc
     */ 
    public void setPkgdesc(String pkgdesc) {
        this.pkgdesc = pkgdesc;
    }
    
    /**
     * 功能：获得有效期类型名
     */
    public String getValiditytypename() {
        return this.validitytypename;
    }
    
    /**
     * 功能：设置有效期类型名
     * @param validitytypename
     */ 
    public void setValiditytypename(String validitytypename) {
        this.validitytypename = validitytypename;
    }

    /**
     * 功能：获得物品类别名
     */
    public String getPttypename() {
        return this.pttypename;
    }
    
    /**
     * 功能：设置物品类别名
     * @param pttypename
     */ 
    public void setPttypename(String pttypename) {
        this.pttypename = pttypename;
    }

    /**
     * 功能：获得允许批次混放
     */
    public String getIsbatchmixed() {
        return isbatchmixed;
    }

    /**
     * 功能：设置允许批次混放
     * @param isbatchmixed
     */ 
    public void setIsbatchmixed(String isbatchmixed) {
        this.isbatchmixed = isbatchmixed;
    }

    /**
     * 功能：获得是否超量收货
     */
    public String getIsexcess() {
        return isexcess;
    }

    /**
     * 功能：设置是否超量收货
     * @param isexcess
     */ 
    public void setIsexcess(String isexcess) {
        this.isexcess = isexcess;
    }

    /**
     * 功能：获得允许产品混放
     */
    public String getIsproductmixed() {
        return isproductmixed;
    }

    /**
     * 功能：设置允许产品混放
     * @param isproductmixed
     */ 
    public void setIsproductmixed(String isproductmixed) {
        this.isproductmixed = isproductmixed;
    }

	/**
	 * 功能：获得分配规则ID
	 */
	public String getAllocationid() {
		return allocationid;
	}

	/**
	 * 功能：设置分配规则ID
	 * @param allocationid
	 */
	public void setAllocationid(String allocationid) {
		this.allocationid = allocationid;
	}

	/**
	 * 功能：获得上架规则ID
	 */
	public String getPutawayid() {
		return putawayid;
	}

	/**
	 * 功能：设置上架规则ID
	 * @param putawayid
	 */
	public void setPutawayid(String putawayid) {
		this.putawayid = putawayid;
	}

	/**
	 * 功能：获得补货规则ID
	 */
	public String getReplenishid() {
		return replenishid;
	}

	/**
	 * 功能：设置补货规则ID
	 * @param replenishid
	 */
	public void setReplenishid(String replenishid) {
		this.replenishid = replenishid;
	}

	/**
	 * 功能：获得分配规则名称
	 */
	public String getAllocationname() {
		return allocationname;
	}

	/**
	 * 功能：设置分配规则名称
	 * @param allocationname
	 */
	public void setAllocationname(String allocationname) {
		this.allocationname = allocationname;
	}

	/**
	 * 功能：获得上架规则名称
	 */
	public String getPutawayname() {
		return putawayname;
	}

	/**
	 * 功能：设置上架规则名称
	 * @param putawayname
	 */
	public void setPutawayname(String putawayname) {
		this.putawayname = putawayname;
	}

	/**
	 * 功能：获得补货规则名称
	 */
	public String getReplenishname() {
		return replenishname;
	}

	/**
	 * 功能：设置补货规则名称
	 * @param replenishname
	 */
	public void setReplenishname(String replenishname) {
		this.replenishname = replenishname;
	}

	/**
	 * 功能：获得存储货位ID
	 */
	public String getStoragespaceid() {
		return storagespaceid;
	}

	/**
	 * 功能：设置存储货位ID
	 * @param storagespaceid
	 */
	public void setStoragespaceid(String storagespaceid) {
		this.storagespaceid = storagespaceid;
	}

	/**
	 * 功能：获得存储货位名称
	 */
	public String getStoragespacename() {
		return storagespacename;
	}

	/**
	 * 功能：设置存储货位名称
	 * @param storagespacename
	 */
	public void setStoragespacename(String storagespacename) {
		this.storagespacename = storagespacename;
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
	 * 功能：获得客户名称
	 */
	public String getCustomername() {
		return customername;
	}

	/**
	 * 功能：设置客户名称
	 * @param customername
	 */
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	
	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}
	
	public String getProducttypename() {
		return producttypename;
	}

	public void setProducttypename(String producttypename) {
		this.producttypename = producttypename;
	}

	/**
	 * 功能：计算产品是否过期
	 * @param printdate
	 * @param losdate
	 * @return 
	 */
	public static long isWarningDate(String printdate , double losdate) {
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try
		{
			Date date = df.parse(printdate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, (int)losdate);
			long time= System.currentTimeMillis();
			Date d1=new Date(time);
		    Date d3 = df.parse(df.format(cal.getTime()));
		    long diff = d3.getTime() - d1.getTime();
		    long days = diff / (1000 * 60 * 60 * 24);
		    return days;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		return 0;
	}
}