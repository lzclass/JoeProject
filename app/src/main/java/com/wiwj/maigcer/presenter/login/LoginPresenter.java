package com.wiwj.maigcer.presenter.login;

import android.content.Context;
import android.content.Intent;

import com.wiwj.maigcer.model.LoginModel;
import com.wiwj.maigcer.model.bean.LoginResult;
import com.wiwj.maigcer.model.net.RxObserver;
import com.wiwj.maigcer.presenter.base.BasePresenter;
import com.wiwj.maigcer.presenter.iview.ILoginView;
import com.wiwj.maigcer.view.activity.BaseActivity;
import com.wiwj.maigcer.view.activity.LoginActivity;
import com.wiwj.maigcer.view.activity.MainActivity;

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
        mLoginModel.getLoginUserInfo(principal, password, validePassword, new RxObserver<LoginResult>(mContext, true) {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mLoginModel.saveUserInfoLocal(loginResult.getData());
                ((LoginActivity) mContext).startActivityForAnima(new Intent(mContext, MainActivity.class), (LoginActivity) mContext);
            }

            @Override
            public void onError(int whichRequest, String errMessage) {

            }
        }, mIView.getLifeSubject());
    }
}
