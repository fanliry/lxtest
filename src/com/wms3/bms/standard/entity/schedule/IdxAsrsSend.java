package com.wms3.bms.standard.entity.schedule;

import java.util.Date;
import java.util.List;
import com.ricosoft.common.dao.dataSource.EntityDAO;
/**
 * @author yao
 * 说明：堆垛机命令执行状态表
 * 
 */
public  class IdxAsrsSend implements java.io.Serializable {

	private static final long serialVersionUID = -8022145369349278100L;
	private String Sendid;					//主键 自动生成
	private String taskId;					//调度任务号
	private String asrsid;					//巷道号
	private String palletId;				//托盘号
	private String messageCode;				//异常代码 10：为 存货占位 11：升降重分巷道货位  12：小车超时重分巷道货位  13：巷道里输送机堆垛机入库重分货位  20：为取货无箱 
	private String taskStatus;				//任务状态 1:下发   2:WMS已经处理  3：已读取
	private String location;				//库位
	private Date creationTime;				//更新时间
	private Date readTime;				    //读取时间
	private String notes;					//备注

	public String getSendid() {
		return Sendid;
	}

	public void setSendid(String sendid) {
		Sendid = sendid;
	}
	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getAsrsid() {
		return this.asrsid;
	}

	public void setAsrsid(String asrsid) {
		this.asrsid = asrsid;
	}

	public String getPalletId() {
		return this.palletId;
	}

	public void setPalletId(String palletId) {
		this.palletId = palletId;
	}

	public String getMessageCode() {
		return this.messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getTaskStatus() {
		return this.taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	public  static IdxAsrsSend getInfoById(EntityDAO dao, String id)
	{
		IdxAsrsSend ta = null;
		String hql = "from IdxAsrsSend as a where a.taskId="+"'"+id+"'"; //0 WCS下发 3 任务正在执行 4 任务完成 5 任务废弃
		List ls = dao.searchEntities(hql);
		if(ls != null && ls.size() == 1)
		{
			ta = (IdxAsrsSend)ls.get(0);
		}
		return ta;
	}
	
}