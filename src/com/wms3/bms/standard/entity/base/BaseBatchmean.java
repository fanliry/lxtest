package com.wms3.bms.standard.entity.base;



/**
 * ��������������
 * @author hug
 *
 */
public class BaseBatchmean  implements java.io.Serializable {


	private static final long serialVersionUID = -8223498706084011604L;
    private String batchmeanid;	//��������ID
    private String batchid;		//����ID
    private String mean;		//����
    private Integer startpos;	//��ʼλ��
    private Integer endpos;		//����λ��


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
     * ���ܣ������������ID
     */
    public String getBatchmeanid() {
        return this.batchmeanid;
    }
    /**
     * ���ܣ�������������ID
     * @param batchmeanid
     */
    public void setBatchmeanid(String batchmeanid) {
        this.batchmeanid = batchmeanid;
    }
    /**
     * ���ܣ��������
     * @return
     */
    public String getMean() {
        return this.mean;
    }
    /**
     * ���ܣ���������
     * @param mean
     */
    public void setMean(String mean) {
        this.mean = mean;
    }
    /**
     * ���ܣ���ÿ�ʼλ��
     * @return
     */
    public Integer getStartpos() {
        return this.startpos;
    }
    /**
     * ���ܣ����ÿ�ʼλ��
     * @param startpos
     */
    public void setStartpos(Integer startpos) {
        this.startpos = startpos;
    }
    /**
     * ���ܣ���ý���λ��
     * @return
     */
    public Integer getEndpos() {
        return this.endpos;
    }
    /**
     * ���ܣ����ý���λ��
     * @param endpos
     */
    public void setEndpos(Integer endpos) {
        this.endpos = endpos;
    }

    /**
     * ���ܣ��������ID
     * @return
     */
	public String getBatchid() {
		return batchid;
	}

	/**
	 * ���ܣ���������ID
	 * @param batchid
	 */
	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}
}