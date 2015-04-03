package com.wms3.bms.standard.entity.schedule;

import java.util.Date;
import java.util.List;
import com.ricosoft.common.dao.dataSource.EntityDAO;
/**
 * @author yao
 * ˵�����Ѷ������ִ��״̬��
 * 
 */
public  class IdxAsrsSend implements java.io.Serializable {

	private static final long serialVersionUID = -8022145369349278100L;
	private String Sendid;					//���� �Զ�����
	private String taskId;					//���������
	private String asrsid;					//�����
	private String palletId;				//���̺�
	private String messageCode;				//�쳣���� 10��Ϊ ���ռλ 11�������ط������λ  12��С����ʱ�ط������λ  13����������ͻ��Ѷ������طֻ�λ  20��Ϊȡ������ 
	private String taskStatus;				//����״̬ 1:�·�   2:WMS�Ѿ�����  3���Ѷ�ȡ
	private String location;				//��λ
	private Date creationTime;				//����ʱ��
	private Date readTime;				    //��ȡʱ��
	private String notes;					//��ע

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
		String hql = "from IdxAsrsSend as a where a.taskId="+"'"+id+"'"; //0 WCS�·� 3 ��������ִ�� 4 ������� 5 �������
		List ls = dao.searchEntities(hql);
		if(ls != null && ls.size() == 1)
		{
			ta = (IdxAsrsSend)ls.get(0);
		}
		return ta;
	}
	
}