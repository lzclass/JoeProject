package com.wiwj.maigcer.model.bean.user;

import java.io.Serializable;

/**
 * @description 环信的信息
 * @author liuzhao
 * @date 2018-3-7
 */
public class EasemobUsers implements Serializable {
	private String id;
	private String userId;
	/** 环信登录名 */
	private String username;
	/** 环信的密码 */
	private String password;
	/** 环信的别名，也是我们后台的别名 */
	private String nickname;
	/** 用户类型 */
	private String userType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		if (username == null) {
			username = "";
		}
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		if (password == null) {
			password = "";
		}
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		if (nickname == null) {
			nickname = "";
		}
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
