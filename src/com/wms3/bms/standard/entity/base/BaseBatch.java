package com.wms3.bms.standard.entity.base;

/**
 * 描述：批次
 * @author hug
 *
 */
public class BaseBatch  implements java.io.Serializable {

	private static final long serialVersionUID = -6436760181288458580L;
	private String batchid;			//批次ID
    private String batchname;		//批次名称
    private Integer batchlength;	//长度
    private String useflag;			//是否启用 Y:是 N.否


    // Constructors

    /** default constructor */
    public BaseBatch() {
    }

	/** minimal constructor */
    public BaseBatch(String batchname, String useflag) {
        this.batchname = batchname;
        this.useflag = useflag;
    }
    
    /** full constructor */
    public BaseBatch(String batchname, Integer batchlength, String useflag) {
        this.batchname = batchname;
        this.batchlength = batchlength;
        this.useflag = useflag;
    }
    
   
    // Property accessors
    /**
     * 功能：获得批次ID
     */
    public String getBatchid() {
        return this.batchid;
    }
    /**
     * 功能：设置批次ID
     * @param batchid
     */
    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }
    /**
     * 功能：获得批次名称
     * @return
     */
    public String getBatchname() {
        return this.batchname;
    }
    /**
     * 功能：设置批次名称
     * @param batchname
     */
    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }
    /**
     * 功能：获得长度
     * @return
     */
    public Integer getBatchlength() {
        return this.batchlength;
    }
    /**
     * 功能：设置长度
     * @param batchlength
     */
    public void setBatchlength(Integer batchlength) {
        this.batchlength = batchlength;
    }
    /**
     * 功能：获得是否启用 Y:是 N.否
     * @return
     */
    public String getUseflag() {
        return this.useflag;
    }
    /**
     * 功能：设置是否启用 Y:是 N.否
     * @param useflag
     */
    public void setUseflag(String useflag) {
        this.useflag = useflag;
    }
}