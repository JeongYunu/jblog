package com.jblog.vo;

public class CategoryVo {

	private int cateNo;
	private String id;
	private String cateName;
	private String description;
	private String regDate;
	private int postCount;
	private int dbNo;
	
	public CategoryVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryVo(int cateNo, String id, String cateName, String description, String regDate, int postCount, int dbNo) {
		super();
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.regDate = regDate;
		this.postCount = postCount;
		this.dbNo = dbNo;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public int getDbNo() {
		return dbNo;
	}

	public void setDbNo(int dbNo) {
		this.dbNo = dbNo;
	}

	@Override
	public String toString() {
		return "CategoryVo [cateNo=" + cateNo + ", id=" + id + ", cateName=" + cateName + ", description=" + description + ", regDate=" + regDate + ", postCount=" + postCount + ", dbNo=" + dbNo + "]";
	}

	

	

}
