package com.wms3.bms.standard.entity.base;



/**
 * 描述：物品类别表
 * @author hug
 */
public class BaseProducttype  implements java.io.Serializable {


    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5389324062640436811L;
	

    // Fields    
	private String ptid;		// ID
    private String parentid;	// 父ID
    private Integer ptorder;	// 顺序
    private Integer ptlevel;	// 级别
    private String ptno;		// 类别
    private String ptname;		// 类别名
    
    // 派生属性
    private String parentname;  // 父类别名

    // Constructors

    /** default constructor */
    public BaseProducttype() {
    }

	/** minimal constructor */
    public BaseProducttype(String ptno, String ptname) {
        this.ptno = ptno;
        this.ptname = ptname;
    }
    
    /** full constructor */
    public BaseProducttype(String parentid, Integer ptorder, Integer ptlevel, String ptname) {
        this.parentid = parentid;
        this.ptorder = ptorder;
        this.ptlevel = ptlevel;
        this.ptname = ptname;
    }

   
    // Property accessors
    /**
     * 功能：获得ID
     */
    public String getPtid() {
        return this.ptid;
    }
    
    /**
     * 功能：设置ID
     * @param ptid
     */
    public void setPtid(String ptid) {
        this.ptid = ptid;
    }

    /**
     * 功能：获得父ID
     */
    public String getParentid() {
        return this.parentid;
    }
    
    /**
     * 功能：设置父ID
     * @param parentid
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    /**
     * 功能：获得顺序
     */
    public Integer getPtorder() {
        return this.ptorder;
    }
    
    /**
     * 功能：设置顺序
     * @param ptorder
     */
    public void setPtorder(Integer ptorder) {
        this.ptorder = ptorder;
    }

    /**
     * 功能：获得级别
     */
    public Integer getPtlevel() {
        return this.ptlevel;
    }
    
    /**
     * 功能：设置级别
     * @param ptlevel
     */
    public void setPtlevel(Integer ptlevel) {
        this.ptlevel = ptlevel;
    }

    /**
     * 功能：获得类别
     */
    public String getPtno() {
        return this.ptno;
    }
    
    /**
     * 功能：设置类别
     * @param ptno
     */
    public void setPtno(String ptno) {
        this.ptno = ptno;
    }

    /**
     * 功能：获得类别名
     */
    public String getPtname() {
        return this.ptname;
    }
    
    /**
     * 功能：设置类别名
     * @param ptname
     */
    public void setPtname(String ptname) {
        this.ptname = ptname;
    }
    
    /**
     * 功能：获得父类别名
     */
    public String getParentname() {
        return this.parentname;
    }
    
    /**
     * 功能：设置父类别名
     * @param parentname
     */
    public void setParentname(String parentname) {
        this.parentname = parentname;
    }
}