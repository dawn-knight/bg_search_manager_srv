package com.mbgo.searchmgr.core.util;


public class DtoSelect {

	private Long id;
	private String menuName;
	
	public DtoSelect(Long id, String menuName) {
		super();
		this.id = id;
		this.menuName = menuName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	
    

}
