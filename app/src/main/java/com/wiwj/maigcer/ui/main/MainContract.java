package com.wiwj.maigcer.ui.main;

import com.wiwj.maigcer.ui.base.BaseView;
import com.wiwj.maigcer.ui.base.IBasePresenter;

/**
 * @Author: Joe liuzhaojava@foxmail.com 2018/3/14 1:06
 * @Description:
 */
public class MainContract {
    interface view extends BaseView {

    }

    interface presenter<V extends view> extends IBasePresenter<V> {

    }
}
