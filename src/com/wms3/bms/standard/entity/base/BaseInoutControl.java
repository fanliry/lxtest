package com.wms3.bms.standard.entity.base;


/**
 * ����������⿨��
 * @author fanll
 *
 */
public class BaseInoutControl  implements java.io.Serializable {
	
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1413801747304974139L;
	
	
	private String con_type;		//��������
	private String createtime;		//����ʱ��
	private String createmanid;		//����Ա
	private String note;			//��ע(������������)
	
	public String getCon_type() {
		return con_type;
	}
	public void setCon_type(String con_type) {
		this.con_type = con_type;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCreatemanid() {
		return createmanid;
	}
	public void setCreatemanid(String createmanid) {
		this.createmanid = createmanid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}