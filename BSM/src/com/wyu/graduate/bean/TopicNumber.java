package com.wyu.graduate.bean;

import com.wyu.graduate.db.anonation.FieldType;

public class TopicNumber {
	private int id;
	private String totalNum; // 可带名额
	private String havedNum; // 已带名额
	@FieldType(notNUll=true,unique=true) //唯一包含不为空
	private String user; // 用户账号
	private String investigation; // 研究领域
	private String name;
	private String imageUrl;
	
	public TopicNumber() {
		super();
	}

	public TopicNumber(String totalNum, String havedNum, String user, String investigation, String name, String imageUrl) {
		super();
		this.totalNum = totalNum;
		this.havedNum = havedNum;
		this.user = user;
		this.investigation = investigation;
		this.name = name;
		this.imageUrl = imageUrl;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	public String getHavedNum() {
		return havedNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHavedNum(String havedNum) {
		this.havedNum = havedNum;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getInvestigation() {
		return investigation;
	}

	public void setInvestigation(String investigation) {
		this.investigation = investigation;
	}
		
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "TopicNumber [totalNum=" + totalNum + ", havedNum=" + havedNum
				+ ", user=" + user + ", investigation=" + investigation
				+ ", name=" + name + ", imageUrl=" + imageUrl + "]";
	}

	
}
