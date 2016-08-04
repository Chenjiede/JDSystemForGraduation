package com.wyu.graduate.bean;

import com.wyu.graduate.db.anonation.FieldType;

public class ChatPeople {
	private int id;
	
	private String sendUser;
	private String receiveUser;
	private String imageUrl;
	private String name;
	private String time;
	private String text;
	private String num;
	
	public ChatPeople() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChatPeople(String sendUser, String receiveUser, String imageUrl, String name, String time, String text, String num) {
		super();
		this.sendUser = sendUser;
		this.receiveUser = receiveUser;
		this.imageUrl = imageUrl;
		this.name = name;
		this.time = time;
		this.text = text;
		this.num = num;
	}

	public String getSenduser() {
		return sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public String getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ChatPeople [sendUser=" + sendUser + ", receiveUser=" + receiveUser
				+ ", imageUrl=" + imageUrl + ", name=" + name + ", time="
				+ time + ", text=" + text + ", num=" + num + "]";
	}
	
	
}
