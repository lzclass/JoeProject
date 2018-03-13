package com.wiwj.maigcer.ui.login;

import com.wiwj.maigcer.ui.base.BaseView;
import com.wiwj.maigcer.ui.base.IBasePresenter;

/**
 * @Author: Joe liuzhaojava@foxmail.com 2018/3/14 0:46
 * @Description:
 */
public class LoginContract {
    interface view extends BaseView {
        void openMainActivity();
    }

    interface presenter<V extends view> extends IBasePresenter<V> {
        void login(String telephone, String password);
    }

}
