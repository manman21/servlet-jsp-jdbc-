package com.laptrinhjavaweb.model;

public class CommentModel extends AbstractModel<CommentModel>{

	private String content;
	private Long userId;
	private Long newId;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getNewId() {
		return newId;
	}
	public void setNewId(Long newId) {
		this.newId = newId;
	}
	public CommentModel(String content, Long userId, Long newId) {
		super();
		this.content = content;
		this.userId = userId;
		this.newId = newId;
	}
	public CommentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
