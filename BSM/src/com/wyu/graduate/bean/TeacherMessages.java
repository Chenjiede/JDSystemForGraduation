package com.wyu.graduate.bean;

import com.wyu.graduate.db.anonation.FieldType;

public class TeacherMessages {
	private int id;
	@FieldType(notNUll=true,unique=true) //唯一包含不为空
	private String user;
	private String teacherUser;
	private String name;
	private String imageUrl;
	private String topic;
	private String profession;
	
	public TeacherMessages() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TeacherMessages(String user, String teacherUser, String name, String imageUrl, String topic, String profession) {
		super();
		this.user = user;
		this.teacherUser = teacherUser;
		this.name = name;
		this.imageUrl = imageUrl;
		this.topic = topic;
		this.profession = profession;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTeacherUser() {
		return teacherUser;
	}
	public void setTeacherUser(String teacherUser) {
		this.teacherUser = teacherUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getId() {
		return id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	@Override
	public String toString() {
		return "TeacherMessages [user=" + user + ", teacherUser=" + teacherUser
				+ ", name=" + name + ", imageUrl=" + imageUrl + ", topic="
				+ topic + ", profession=" + profession + "]";
	}
	
}
