package com.wms3.bms.standard.entity.base;

/**
 * ����������
 * @author hug
 *
 */
public class BaseBatch  implements java.io.Serializable {

	private static final long serialVersionUID = -6436760181288458580L;
	private String batchid;			//����ID
    private String batchname;		//��������
    private Integer batchlength;	//����
    private String useflag;			//�Ƿ����� Y:�� N.��


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
     * ���ܣ��������ID
     */
    public String getBatchid() {
        return this.batchid;
    }
    /**
     * ���ܣ���������ID
     * @param batchid
     */
    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }
    /**
     * ���ܣ������������
     * @return
     */
    public String getBatchname() {
        return this.batchname;
    }
    /**
     * ���ܣ�������������
     * @param batchname
     */
    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }
    /**
     * ���ܣ���ó���
     * @return
     */
    public Integer getBatchlength() {
        return this.batchlength;
    }
    /**
     * ���ܣ����ó���
     * @param batchlength
     */
    public void setBatchlength(Integer batchlength) {
        this.batchlength = batchlength;
    }
    /**
     * ���ܣ�����Ƿ����� Y:�� N.��
     * @return
     */
    public String getUseflag() {
        return this.useflag;
    }
    /**
     * ���ܣ������Ƿ����� Y:�� N.��
     * @param useflag
     */
    public void setUseflag(String useflag) {
        this.useflag = useflag;
    }
}