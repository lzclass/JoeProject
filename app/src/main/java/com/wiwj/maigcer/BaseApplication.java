package com.wiwj.maigcer;

import android.app.Application;

import com.wiwj.maigcer.utils.Constants;
import com.wiwj.maigcer.data.db.DbHelper;
import com.wiwj.maigcer.log.CollectLogManager;
import com.wiwj.maigcer.log.LogManager;
import com.wiwj.maigcer.net.RxRetrofit;
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
        CollectLogManager.getInstance().init(ourInstance);//初始化日志收集
    }

}
