package com.laptrinhjavaweb.model;

public class RoleModel extends AbstractModel<RoleModel>{
	
	private String code;
	private String name;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RoleModel(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	public RoleModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
