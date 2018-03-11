package com.wiwj.maigcer.view.activity;

import android.app.Application;

import com.wiwj.maigcer.config.Constants;
import com.wiwj.maigcer.db.DbHelper;
import com.wiwj.maigcer.log.LogManager;
import com.wiwj.maigcer.model.net.RxRetrofit;
import com.wiwj.maigcer.utils.ToastUtils;

/**
 * Created by liuzhao on 2018/3/5.
 */

public class BaseApplication extends Application {

    public static BaseApplication ourInstance;

    public BaseApplication() {
        ourInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        ToastUtils.builder.init(ourInstance);//初始化Toast
        LogManager.init(Constants.IS_DEBUG);//初始化日志
        RxRetrofit.init(ourInstance);//初始化retrofit网络框架
        DbHelper.getInstance().init(ourInstance);//初始化GreenDao数据库
    }

}
