package com.wiwj.maigcer.model.bean.user;

/**
 * Created by liuzhao on 2018/3/7.
 */

import java.io.Serializable;

/**
 * 用户登录信息
 */

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String username;
    private String password;
    private String plainPassowrd;
    private String salt;
    private String source;
    private String lastLoginTime;
    private String lastLoginIp;
    private String lastActiveTime;
    private String isAllowUpdateUsername;
    private String role;
    private User user;
    private String token;
    /**
     * 离线消息数量
     */
    private int offlineMsgCount;
    /**
     * 目前只用于显示密码过于简单的消息,设计的真蛋疼
     */
    private String msg;
    /**
     * false密码过于简单
     */
    private boolean passwordLegal = true;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getPlainPassowrd() {
        return plainPassowrd;
    }

    public void setPlainPassowrd(String plainPassowrd) {
        this.plainPassowrd = plainPassowrd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(String lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public String getIsAllowUpdateUsername() {
        return isAllowUpdateUsername;
    }

    public void setIsAllowUpdateUsername(String isAllowUpdateUsername) {
        this.isAllowUpdateUsername = isAllowUpdateUsername;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getOfflineMsgCount() {
        return offlineMsgCount;
    }

    public void setOfflineMsgCount(int offlineMsgCount) {
        this.offlineMsgCount = offlineMsgCount;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isPasswordLegal() {
        return passwordLegal;
    }

    public void setPasswordLegal(boolean passwordLegal) {
        this.passwordLegal = passwordLegal;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", plainPassowrd='" + plainPassowrd + '\'' +
                ", salt='" + salt + '\'' +
                ", source='" + source + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", lastActiveTime='" + lastActiveTime + '\'' +
                ", isAllowUpdateUsername='" + isAllowUpdateUsername + '\'' +
                ", role='" + role + '\'' +
                ", user=" + user +
                ", token='" + token + '\'' +
                ", offlineMsgCount=" + offlineMsgCount +
                ", msg='" + msg + '\'' +
                ", passwordLegal=" + passwordLegal +
                '}';
    }
}

