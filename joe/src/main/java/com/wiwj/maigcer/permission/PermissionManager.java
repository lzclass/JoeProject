package com.wiwj.maigcer.permission;

import android.app.Activity;
import android.os.Build;

/**
*@Author: Joe liuzhaojava@foxmail.com
*@Date: 2018/3/10 2:22
*@Description: 权限管理类
*/
public class PermissionManager {

    private static PermissionManager permissionManager;
    private Activity activity;

    private PermissionManager() {

    }

    public static PermissionManager instance() {
        if (permissionManager == null) {
            synchronized (PermissionManager.class) {
                if (permissionManager == null) {
                    permissionManager = new PermissionManager();
                }
            }
        }
        return permissionManager;
    }

    public PermissionManager with(Activity activity) {
        this.activity = activity;
        return this;
    }

    public void request(final OnPermissionCallback permissionCallback, final String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.activity != null && permissionCallback != null) {
            RxPermissions rxPermissions = new RxPermissions(this.activity);
            rxPermissions.requestEach(permissions).subscribe(new io.reactivex.functions.Consumer<Permission>() {
                @Override
                public void accept(Permission permission) {
                    if (permission.granted) {
                        permissionCallback.onRequestAllow(permission.name);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        permissionCallback.onRequestRefuse(permission.name);
                    } else {
                        permissionCallback.onRequestNoAsk(permission.name);
                    }
                }
            });
        }
    }
}
