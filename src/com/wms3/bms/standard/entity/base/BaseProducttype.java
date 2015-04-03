package com.wms3.bms.standard.entity.base;



/**
 * ��������Ʒ����
 * @author hug
 */
public class BaseProducttype  implements java.io.Serializable {


    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5389324062640436811L;
	

    // Fields    
	private String ptid;		// ID
    private String parentid;	// ��ID
    private Integer ptorder;	// ˳��
    private Integer ptlevel;	// ����
    private String ptno;		// ���
    private String ptname;		// �����
    
    // ��������
    private String parentname;  // �������

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
     * ���ܣ����ID
     */
    public String getPtid() {
        return this.ptid;
    }
    
    /**
     * ���ܣ�����ID
     * @param ptid
     */
    public void setPtid(String ptid) {
        this.ptid = ptid;
    }

    /**
     * ���ܣ���ø�ID
     */
    public String getParentid() {
        return this.parentid;
    }
    
    /**
     * ���ܣ����ø�ID
     * @param parentid
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    /**
     * ���ܣ����˳��
     */
    public Integer getPtorder() {
        return this.ptorder;
    }
    
    /**
     * ���ܣ�����˳��
     * @param ptorder
     */
    public void setPtorder(Integer ptorder) {
        this.ptorder = ptorder;
    }

    /**
     * ���ܣ���ü���
     */
    public Integer getPtlevel() {
        return this.ptlevel;
    }
    
    /**
     * ���ܣ����ü���
     * @param ptlevel
     */
    public void setPtlevel(Integer ptlevel) {
        this.ptlevel = ptlevel;
    }

    /**
     * ���ܣ�������
     */
    public String getPtno() {
        return this.ptno;
    }
    
    /**
     * ���ܣ��������
     * @param ptno
     */
    public void setPtno(String ptno) {
        this.ptno = ptno;
    }

    /**
     * ���ܣ���������
     */
    public String getPtname() {
        return this.ptname;
    }
    
    /**
     * ���ܣ����������
     * @param ptname
     */
    public void setPtname(String ptname) {
        this.ptname = ptname;
    }
    
    /**
     * ���ܣ���ø������
     */
    public String getParentname() {
        return this.parentname;
    }
    
    /**
     * ���ܣ����ø������
     * @param parentname
     */
    public void setParentname(String parentname) {
        this.parentname = parentname;
    }
}