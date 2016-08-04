package com.wyu.graduate.bean;

public class ReplyMessage {
	private int id;
	private String user;
	private String teacher;
	private String reason;
	private String imageUrl;
	private String green;
	private String name;
	private String topic;
	
	public ReplyMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReplyMessage(String user, String teacher, String reason, String imageUrl, String green, String name, String topic) {
		super();
		this.user = user;
		this.teacher = teacher;
		this.reason = reason;
		this.imageUrl = imageUrl;
		this.green = green;
		this.name = name;
		this.topic = topic;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getGreen() {
		return green;
	}

	public void setGreen(String green) {
		this.green = green;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "ReplyMessage [user=" + user + ", teacher=" + teacher
				+ ", reason=" + reason + ", image=" + imageUrl + ", green="
				+ green + ", name=" + name + ", topic=" + topic + "]";
	}
	
	
}
