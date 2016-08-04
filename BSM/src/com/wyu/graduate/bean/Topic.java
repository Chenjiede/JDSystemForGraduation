package com.wyu.graduate.bean;

import java.util.Arrays;

import javafx.scene.chart.PieChart.Data;

import com.wyu.graduate.db.anonation.FieldType;

public class Topic {
	private int id;
	@FieldType(notNUll=true,unique=true) //唯一包含不为空
	private String user;
	private String describe;
	private String difficult;
	private String title;
	private String teacherUser;
	
	public Topic() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Topic(String user, String describe, String difficult, String title, String teacherUser) {
		super();
		this.user = user;
		this.describe = describe;
		this.difficult = difficult;
		this.title = title;
		this.teacherUser = teacherUser;
	}


	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getDifficult() {
		return difficult;
	}

	public void setDifficult(String difficult) {
		this.difficult = difficult;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTeacherUser() {
		return teacherUser;
	}

	public void setTeacherUser(String teacherUser) {
		this.teacherUser = teacherUser;
	}

	@Override
	public String toString() {
		return "Topic [user=" + user + ", describe=" + describe
				+ ", difficult=" + difficult + ", title=" + title
				+ ", teacherUser=" + teacherUser + "]";
	}
	
}
