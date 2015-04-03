package com.wms3.bms.standard.entity.base;

/**
 * 
 * ����: ���б� ����ID
 * @author hug 2012-3-13
 * @since WMS 3.0
 */
public class BaseSeq  implements java.io.Serializable{

    /**  */
    private static final long serialVersionUID = 5815884595132040734L;
    
    private String seqId;   	//����ID
    private String seqType; 	//��������
    private String seqValue;	//ֵ
    private String seqRemark;	//����
    private String seqPrefix;	//ǰ׺
    private int icodelength;	//λ��
    
    
    /**
	 * ���ܣ����λ��
	 */
	public int getIcodelength() {
		return icodelength;
	}
	/**
	 * ���ܣ�����λ��
	 * @param icodelength
	 */
	public void setIcodelength(int icodelength) {
		this.icodelength = icodelength;
	}
	/**
	 * ���ܣ����ǰ׺
	 */
	public String getSeqPrefix() {
		return seqPrefix;
	}
	/**
	 * ���ܣ�����ǰ׺
	 * @param seqPrefix
	 */
	public void setSeqPrefix(String seqPrefix) {
		this.seqPrefix = seqPrefix;
	}
	/**
	 * ���ܣ��������
	 */
	public String getSeqRemark() {
		return seqRemark;
	}
	/**
	 * ���ܣ���������
	 * @param seqRemark
	 */
	public void setSeqRemark(String seqRemark) {
		this.seqRemark = seqRemark;
	}
	public BaseSeq(){
        
    }
    /**
     * ���ܣ��������ID
     * @author hug 2012-3-13 
     * @return
     */
    public String getSeqId() {
        return seqId;
    }
    /**
     * ���ܣ���������ID
     * @author hug 2012-3-13 
     * @param seqId
     */
    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }
    /**
     * ���ܣ������������
     * @author hug 2012-3-13 
     * @return
     */
    public String getSeqType() {
        return seqType;
    }
    /**
     * ���ܣ�������������
     * @author hug 2012-3-13 
     * @param seqType
     */
    public void setSeqType(String seqType) {
        this.seqType = seqType;
    }
    /**
     * ���ܣ����ֵ
     * @author hug 2012-3-13 
     * @return
     */
    public String getSeqValue() {
        return seqValue;
    }
    /**
     * ���ܣ�����ֵ
     * @author hug 2012-3-13 
     * @param seqValue
     */
    public void setSeqValue(String seqValue) {
        this.seqValue = seqValue;
    }
    
    

}
