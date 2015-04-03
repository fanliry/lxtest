package com.wms3.bms.standard.entity.base;



/**
 * 描述：类型表
 * @author hug
 */
public class BaseType  implements java.io.Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3453945866379329479L;

    // Fields    
	private String typeid;			// 类型ID
    private String typename;		// 类型名
    private String typevalue;		// 类型值
    private String selecttext;		// 下拉列表显示内容
    private String selectvalue;		// 下拉列表值
    private String typelevel;		// 类型的级别（系统级别的是不能更新的）

    // Constructors

	/** default constructor */
    public BaseType() {
    }

	/** minimal constructor */
    public BaseType(String typeid, String typename, String typevalue) {
    	this.typeid = typeid;
        this.typename = typename;
        this.typevalue = typevalue;
    }
    
    /** full constructor */
    public BaseType(String typeid, String typename, String typevalue, String selecttext, String selectvalue) {
    	this.typeid = typeid;
        this.typename = typename;
        this.typevalue = typevalue;
        this.selecttext = selecttext;
        this.selectvalue = selectvalue;
    }

    // Property accessors
    /**
     * 功能：获得类型ID
     */
    public String getTypeid() {
        return this.typeid;
    }
    
    /**
     * 功能：设置类型ID
     * @param typeid
     */ 
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    /**
     * 功能：获得类型名
     */
    public String getTypename() {
        return this.typename;
    }
    
    /**
     * 功能：设置类型名
     * @param typename
     */ 
    public void setTypename(String typename) {
        this.typename = typename;
    }

    /**
     * 功能：获得类型值
     */
    public String getTypevalue() {
        return this.typevalue;
    }
    
    /**
     * 功能：设置类型值
     * @param typevalue
     */ 
    public void setTypevalue(String typevalue) {
        this.typevalue = typevalue;
    }

    /**
     * 功能：获得下拉列表显示内容
     */
    public String getSelecttext() {
        return this.selecttext;
    }
    
    /**
     * 功能：设置下拉列表显示内容
     * @param selecttext
     */ 
    public void setSelecttext(String selecttext) {
        this.selecttext = selecttext;
    }

    /**
     * 功能：获得下拉列表值
     */
    public String getSelectvalue() {
        return this.selectvalue;
    }
    
    /**
     * 功能：设置下拉列表值
     * @param selectvalue
     */ 
    public void setSelectvalue(String selectvalue) {
        this.selectvalue = selectvalue;
    }
    
    /**
	 * 功能：获得类型的级别（系统级别的是不能删除的）
	 */
	public String getTypelevel() {
		return typelevel;
	}

	/**
	 * 功能：设置类型的级别（系统级别的是不能删除的）
	 * @param typelevel
	 */
	public void setTypelevel(String typelevel) {
		this.typelevel = typelevel;
	}
}
