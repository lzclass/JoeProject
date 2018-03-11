package com.wiwj.maigcer.model.bean.user;

import java.io.Serializable;

public class User implements Serializable {
	private String id;
	private String username;
	private String password;
	private String salt;
	private String nickname;
	private int gender;
	private String cardId;
	private String headPic;
	private int sourceType;
	private String mobile;
	private String email;
	private String description;
	private int orgId;
	private int locked;
	private String roles;
	private Org org;
	private Additional additional;
	/** 环信的信息 */
	private EasemobUsers easemobUsers;

	public EasemobUsers getEasemobUsers() {
		if (easemobUsers == null) {
			easemobUsers = new EasemobUsers();
		}
		return easemobUsers;
	}

	public void setEasemobUsers(EasemobUsers easemobUsers) {
		this.easemobUsers = easemobUsers;
	}

	public User() {
		org = new Org();
		additional = new Additional();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Org getOrg() {
		if (org == null) {
			org = new Org();
		}
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public Additional getAdditional() {
		return additional;
	}

	public void setAdditional(Additional additional) {
		this.additional = additional;
	}

}
