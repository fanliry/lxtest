package com.wms3.bms.standard.entity.base;



/**
 * ���������ͱ�
 * @author hug
 */
public class BaseType  implements java.io.Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3453945866379329479L;

    // Fields    
	private String typeid;			// ����ID
    private String typename;		// ������
    private String typevalue;		// ����ֵ
    private String selecttext;		// �����б���ʾ����
    private String selectvalue;		// �����б�ֵ
    private String typelevel;		// ���͵ļ���ϵͳ������ǲ��ܸ��µģ�

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
     * ���ܣ��������ID
     */
    public String getTypeid() {
        return this.typeid;
    }
    
    /**
     * ���ܣ���������ID
     * @param typeid
     */ 
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    /**
     * ���ܣ����������
     */
    public String getTypename() {
        return this.typename;
    }
    
    /**
     * ���ܣ�����������
     * @param typename
     */ 
    public void setTypename(String typename) {
        this.typename = typename;
    }

    /**
     * ���ܣ��������ֵ
     */
    public String getTypevalue() {
        return this.typevalue;
    }
    
    /**
     * ���ܣ���������ֵ
     * @param typevalue
     */ 
    public void setTypevalue(String typevalue) {
        this.typevalue = typevalue;
    }

    /**
     * ���ܣ���������б���ʾ����
     */
    public String getSelecttext() {
        return this.selecttext;
    }
    
    /**
     * ���ܣ����������б���ʾ����
     * @param selecttext
     */ 
    public void setSelecttext(String selecttext) {
        this.selecttext = selecttext;
    }

    /**
     * ���ܣ���������б�ֵ
     */
    public String getSelectvalue() {
        return this.selectvalue;
    }
    
    /**
     * ���ܣ����������б�ֵ
     * @param selectvalue
     */ 
    public void setSelectvalue(String selectvalue) {
        this.selectvalue = selectvalue;
    }
    
    /**
	 * ���ܣ�������͵ļ���ϵͳ������ǲ���ɾ���ģ�
	 */
	public String getTypelevel() {
		return typelevel;
	}

	/**
	 * ���ܣ��������͵ļ���ϵͳ������ǲ���ɾ���ģ�
	 * @param typelevel
	 */
	public void setTypelevel(String typelevel) {
		this.typelevel = typelevel;
	}
}
