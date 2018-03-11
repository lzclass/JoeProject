package com.wiwj.maigcer.model.net;

import com.wiwj.maigcer.model.bean.LoginResult;
import com.wiwj.maigcer.net.LifeCycleEvent;
import com.wiwj.maigcer.net.RxObserver;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by liuzhao on 2018/3/6.
 */

public class RxManager {

    private static class RxManagerHolder {
        public static RxManager instance = new RxManager();
    }

    public static RxManager getInstance() {
        return RxManagerHolder.instance;
    }

    public void getApiService(RxObserver<LoginResult> observer, PublishSubject<LifeCycleEvent> lifecycleSubject) {

//        RxRetrofit.getRetrofit(BaseConfig.API_HOST).create(ApiService.class).g;
//        RxRetrofit.composeToSubscribe(observable, observer, lifecycleSubject);
    }

}
