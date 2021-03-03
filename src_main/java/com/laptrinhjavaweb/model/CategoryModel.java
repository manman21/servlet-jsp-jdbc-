package com.laptrinhjavaweb.model;

public class CategoryModel extends AbstractModel<CategoryModel>{
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
	public CategoryModel(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	public CategoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
