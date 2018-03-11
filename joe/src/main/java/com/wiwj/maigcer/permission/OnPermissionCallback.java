package com.wiwj.maigcer.permission;

/**
*@Author: Joe liuzhaojava@foxmail.com
*@Date: 2018/3/10 2:24
*@Description: 申请权限回调
*/
public interface OnPermissionCallback {
    //允许
    void onRequestAllow(String permissionName);

    //拒绝
    void onRequestRefuse(String permissionName);

    //不在询问
    void onRequestNoAsk(String permissionName);
}
