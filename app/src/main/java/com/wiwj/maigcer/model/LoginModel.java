package com.wiwj.maigcer.model;

import com.wiwj.maigcer.model.bean.LoginResult;
import com.wiwj.maigcer.model.bean.user.UserInfo;
import com.wiwj.maigcer.model.net.HttpResult;
import com.wiwj.maigcer.model.net.LifeCycleEvent;
import com.wiwj.maigcer.model.net.RxObserver;
import com.wiwj.maigcer.model.net.RxRetrofit;
import com.wiwj.maigcer.utils.PreferencesManager;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by liuzhao on 2018/3/6.
 * 包含相关的网络请求、数据库操作、sharePreference等操作
 */

public class LoginModel {
    public static LoginModel mInstance;

    public synchronized static LoginModel getInstance() {
        if (mInstance == null) {
            synchronized (LoginModel.class) {
                if (mInstance == null)
                    mInstance = new LoginModel();
            }

        }
        return mInstance;
    }

    private LoginModel() {
    }

    public void getLoginUserInfo(String principal, String password, String validePassword, RxObserver<LoginResult> observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {
        Observable<HttpResult<LoginResult>> observable = RxRetrofit.getDefaultService()
                .getLoginInfo(principal, password, validePassword);
        RxRetrofit.composeToSubscribe(observable, observer, lifecycleSubject);
    }

    /**
     * 保存用户信息到本地
     *
     * @param result
     */
    public void saveUserInfoLocal(UserInfo result) {
        PreferencesManager.getInstance().setUserName(result.getUsername());
    }

    /**
     * 从本地获取登录用户环信信息
     * <p>
     * 是否要添加token，用于登录后的保存
     */
    public UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        return userInfo;
    }
}
