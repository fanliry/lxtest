package com.wms3.bms.standard.entity.base;

/**
 * 
 * 描述: 序列表 生成ID
 * @author hug 2012-3-13
 * @since WMS 3.0
 */
public class BaseSeq  implements java.io.Serializable{

    /**  */
    private static final long serialVersionUID = 5815884595132040734L;
    
    private String seqId;   	//序列ID
    private String seqType; 	//序列类型
    private String seqValue;	//值
    private String seqRemark;	//描述
    private String seqPrefix;	//前缀
    private int icodelength;	//位数
    
    
    /**
	 * 功能：获得位数
	 */
	public int getIcodelength() {
		return icodelength;
	}
	/**
	 * 功能：设置位数
	 * @param icodelength
	 */
	public void setIcodelength(int icodelength) {
		this.icodelength = icodelength;
	}
	/**
	 * 功能：获得前缀
	 */
	public String getSeqPrefix() {
		return seqPrefix;
	}
	/**
	 * 功能：设置前缀
	 * @param seqPrefix
	 */
	public void setSeqPrefix(String seqPrefix) {
		this.seqPrefix = seqPrefix;
	}
	/**
	 * 功能：获得描述
	 */
	public String getSeqRemark() {
		return seqRemark;
	}
	/**
	 * 功能：设置描述
	 * @param seqRemark
	 */
	public void setSeqRemark(String seqRemark) {
		this.seqRemark = seqRemark;
	}
	public BaseSeq(){
        
    }
    /**
     * 功能：获得序列ID
     * @author hug 2012-3-13 
     * @return
     */
    public String getSeqId() {
        return seqId;
    }
    /**
     * 功能：设置序列ID
     * @author hug 2012-3-13 
     * @param seqId
     */
    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }
    /**
     * 功能：获得序列类型
     * @author hug 2012-3-13 
     * @return
     */
    public String getSeqType() {
        return seqType;
    }
    /**
     * 功能：设置序列类型
     * @author hug 2012-3-13 
     * @param seqType
     */
    public void setSeqType(String seqType) {
        this.seqType = seqType;
    }
    /**
     * 功能：获得值
     * @author hug 2012-3-13 
     * @return
     */
    public String getSeqValue() {
        return seqValue;
    }
    /**
     * 功能：设置值
     * @author hug 2012-3-13 
     * @param seqValue
     */
    public void setSeqValue(String seqValue) {
        this.seqValue = seqValue;
    }
    
    

}
