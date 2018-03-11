package com.wiwj.maigcer.presenter.login;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.wiwj.maigcer.common.BaseConfig;
import com.wiwj.maigcer.model.LoginModel;
import com.wiwj.maigcer.model.bean.LoginResult;
import com.wiwj.maigcer.model.net.ApiService;
import com.wiwj.maigcer.net.HttpResult;
import com.wiwj.maigcer.net.RxObserver;
import com.wiwj.maigcer.net.RxRetrofit;
import com.wiwj.maigcer.presenter.base.BasePresenter;
import com.wiwj.maigcer.presenter.iview.ILoginView;
import com.wiwj.maigcer.view.activity.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by liuzhao on 2018/3/6.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {
    private LoginModel mLoginModel;
    private Context mContext;

    public LoginPresenter(ILoginView iLoginView, BaseActivity activity) {
        super(iLoginView, activity);
        mLoginModel = LoginModel.getInstance();
        this.mContext = activity;
    }

    public void getLoginUserInfo(String principal, String password, String validePassword) {

        Observer observer = new RxObserver<LoginResult>(mContext, true) {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mLoginModel.saveUserInfoLocal(loginResult.getData());
                Logger.d(loginResult.getData());
//                ((LoginActivity) mContext).startActivityForAnima(new Intent(mContext, MainActivity.class), (LoginActivity) mContext);
            }

            @Override
            public void onError(int whichRequest, String errMessage) {
                Logger.e(errMessage);
            }
        };
        Observable<HttpResult<LoginResult>> observable = RxRetrofit.getRetrofit(BaseConfig.API_HOST)
                .create(ApiService.class)
                .getLoginInfo(principal, password, validePassword);
        RxRetrofit.composeToSubscribe(observable, observer, mIView.getLifeSubject());
    }

}
