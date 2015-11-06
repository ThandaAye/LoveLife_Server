package com.lovelife.model;

import java.io.Serializable;

public class UserDTO implements Serializable{
	
	private static final long serialVersionUID = -7413422304207287954L;
	private int userID;
	private String username;
	private String password;
	private String fullName;
	private String nickName;
	private String dateOfBirth;
	private String contactNo;
	private String email;
	private String imgUrl;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public UserDTO() {
		super();
	}
	public UserDTO(int userID, String username, String password, String fullName, String nickName, String dateOfBirth,
			String contactNo, String email, String imgUrl) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.nickName = nickName;
		this.dateOfBirth = dateOfBirth;
		this.contactNo = contactNo;
		this.email = email;
		this.imgUrl = imgUrl;
	}
	@Override
	public String toString() {
		return "UserDTO [userID=" + userID + ", username=" + username + ", password=" + password + ", fullName="
				+ fullName + ", nickName=" + nickName + ", dateOfBirth=" + dateOfBirth + ", contactNo=" + contactNo
				+ ", email=" + email + ", imgUrl=" + imgUrl + "]";
	}
	
}
