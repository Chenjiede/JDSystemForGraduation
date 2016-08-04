package com.wyu.graduate.bean;

import com.wyu.graduate.db.anonation.FieldType;

public class People {
	private int id;
	@FieldType(notNUll=true,unique=true) //唯一包含不为空
	private String user;
	private String pwd;
	private String name;
	private String email;
	private String phone;
	private String type;
	private String imageUrl;
	private String profession;
	
	public People() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public People(String user, String pwd, String name, String email,
			String phone, String type, String imageUrl, String profession) {
		super();
		this.user = user;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.type = type;
		this.imageUrl = imageUrl;
		this.profession = profession;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
		return "People [user=" + user + ", pwd=" + pwd + ", name=" + name
				+ ", email=" + email + ", phone=" + phone + ", type=" + type
				+ ", imageUrl=" + imageUrl + ", profession=" + profession + "]";
	}
	
}
