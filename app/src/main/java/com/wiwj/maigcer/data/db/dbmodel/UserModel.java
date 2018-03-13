package com.wiwj.maigcer.data.db.dbmodel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author: Joe liuzhaojava@foxmail.com 2018/3/10 17:36
 * @Description: 登录用户信息实体类
 */
@Entity
public class UserModel{
    @Id(autoincrement = true)
    private Long id;
    private int user_id;
//    @Transient：表明这个字段不会被写入数据库，只是作为一个普通的java类字段，用来临时存储数据的，不会被持久化
    private String user_name;
    @Generated(hash = 566819554)
    public UserModel(Long id, int user_id, String user_name) {
        this.id = id;
        this.user_id = user_id;
        this.user_name = user_name;
    }
    @Generated(hash = 782181818)
    public UserModel() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getUser_id() {
        return this.user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getUser_name() {
        return this.user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


}
