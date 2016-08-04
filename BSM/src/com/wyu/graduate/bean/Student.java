package com.wyu.graduate.bean;

import com.wyu.graduate.db.anonation.FieldType;

public class Student {

	private int id;
	private String name;
	@FieldType(notNUll=true,unique=true) //唯一包含不为空
	private String studentUser;
	private String imageUrl;
	private String teacherUser;
	private String topic;
	private String profession;

	public Student() {
	}

	public Student(String name, String studentUser, String teacherUser, String topic, String imageUrl, String profession) {
		super();
		this.name = name;
		this.studentUser = studentUser;
		this.teacherUser = teacherUser;
		this.topic = topic;
		this.imageUrl = imageUrl;
		this.profession = profession;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentUser() {
		return studentUser;
	}

	public void setStudentUser(String studentUser) {
		this.studentUser = studentUser;
	}

	public String getTeacherUser() {
		return teacherUser;
	}

	public void setTeacherUser(String teacherUser) {
		this.teacherUser = teacherUser;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getId() {
		return id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", studentUser=" + studentUser
				+ ", imageUrl=" + imageUrl + ", teacherUser=" + teacherUser
				+ ", topic=" + topic + ", profession=" + profession + "]";
	}
}
