package com.mbgo.searchmgr.core.bean;

import java.util.Date;


public class MgrRole{
   
	private Long id;
    private String rolename;
    private Long addTime;
    private Date lastUpdate;
	public MgrRole() {
		super();
	}
	public MgrRole(Long id, String rolename) {
		super();
		this.id = id;
		this.rolename = rolename;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public Long getAddTime() {
		return addTime;
	}
	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
    
    
}