package com.wms3.bms.standard.entity.base;



/**
 * 描述：批次意义
 * @author hug
 *
 */
public class BaseBatchmean  implements java.io.Serializable {


	private static final long serialVersionUID = -8223498706084011604L;
    private String batchmeanid;	//批次意义ID
    private String batchid;		//批次ID
    private String mean;		//意义
    private Integer startpos;	//开始位置
    private Integer endpos;		//结束位置


    // Constructors

    /** default constructor */
    public BaseBatchmean() {
    }

    
    /** full constructor */
    public BaseBatchmean(String batchid, String mean, Integer startpos, Integer endpos) {
        this.batchid = batchid;
    	this.mean = mean;
        this.startpos = startpos;
        this.endpos = endpos;
    }

   
    // Property accessors
    /**
     * 功能：获得批次意义ID
     */
    public String getBatchmeanid() {
        return this.batchmeanid;
    }
    /**
     * 功能：设置批次意义ID
     * @param batchmeanid
     */
    public void setBatchmeanid(String batchmeanid) {
        this.batchmeanid = batchmeanid;
    }
    /**
     * 功能：获得意义
     * @return
     */
    public String getMean() {
        return this.mean;
    }
    /**
     * 功能：设置意义
     * @param mean
     */
    public void setMean(String mean) {
        this.mean = mean;
    }
    /**
     * 功能：获得开始位置
     * @return
     */
    public Integer getStartpos() {
        return this.startpos;
    }
    /**
     * 功能：设置开始位置
     * @param startpos
     */
    public void setStartpos(Integer startpos) {
        this.startpos = startpos;
    }
    /**
     * 功能：获得结束位置
     * @return
     */
    public Integer getEndpos() {
        return this.endpos;
    }
    /**
     * 功能：设置结束位置
     * @param endpos
     */
    public void setEndpos(Integer endpos) {
        this.endpos = endpos;
    }

    /**
     * 功能：获得批次ID
     * @return
     */
	public String getBatchid() {
		return batchid;
	}

	/**
	 * 功能：设置批次ID
	 * @param batchid
	 */
	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}
}