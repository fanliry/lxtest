package com.wms3.bms.standard.entity.base;


/**
 * 描述：出入库卡控
 * @author fanll
 *
 */
public class BaseInoutControl  implements java.io.Serializable {
	
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1413801747304974139L;
	
	
	private String con_type;		//卡控类型
	private String createtime;		//创建时间
	private String createmanid;		//操作员
	private String note;			//备注(卡控类型名称)
	
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