package com.wiwj.maigcer.model.bean;

import com.wiwj.maigcer.model.bean.user.UserInfo;

import java.io.Serializable;

/**
 * Created by liuzhao on 2018/3/6.
 */

public class LoginResult implements Serializable{
    private UserInfo data;

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "data=" + data +
                '}';
    }
}
