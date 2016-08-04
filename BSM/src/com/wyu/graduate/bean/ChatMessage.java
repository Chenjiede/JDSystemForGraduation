package com.wyu.graduate.bean;

public class ChatMessage {
	private int id;
	private String sendUser;
	private String receiveUser;
	private String imageUrl;
	private String time;
	private String text;
	private String userType;
	
	public ChatMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChatMessage(String sendUser, String receiveUser, String imageUrl, String time, String text, String userType) {
		super();
		this.sendUser = sendUser;
		this.receiveUser = receiveUser;
		this.imageUrl = imageUrl;
		this.time = time;
		this.text = text;
		this.userType = userType;
	}

	public String getSendUser() {
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ChatMessage [sendUser=" + sendUser + ", receiveUser="
				+ receiveUser + ", imageUrl=" + imageUrl + ", time=" + time
				+ ", text=" + text + ", userType=" + userType + "]";
	}
	
	
}
