package com.wms3.bms.standard.action.base.ajaxTree;

/**
 * 功能：封装节点信息的bean
 * @author hug
 */
public class BlueTreeData {
	
	private String id;
	private String pid;
	private String name;
	private String url;
	private String state;
	private boolean ischild;
	private String openimg;
	private String closeimg;
	
	public BlueTreeData() {
	}
	
	public BlueTreeData(String id, String pid, String name, String url, String state, 
			boolean ischild, String openimg, String closeimg) {
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.url = url;
		this.state = state;
		this.ischild = ischild;
		this.openimg = openimg;
		this.closeimg = closeimg;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public boolean isIschild() {
		return ischild;
	}
	public void setIschild(boolean ischild) {
		this.ischild = ischild;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOpenimg() {
		return openimg;
	}
	public void setOpenimg(String openimg) {
		this.openimg = openimg;
	}
	public String getCloseimg() {
		return closeimg;
	}
	public void setCloseimg(String closeimg) {
		this.closeimg = closeimg;
	}
	
}