package com.wiwj.maigcer.presenter.base;

import com.wiwj.maigcer.presenter.iview.IBaseView;
import com.wiwj.maigcer.view.activity.BaseActivity;

import java.lang.ref.WeakReference;

/***
 *
 * @param <T>
 */
public abstract class BasePresenter<T extends IBaseView> {

    protected T mIView;
    protected WeakReference<BaseActivity> mBaseViewActivity;

    public BasePresenter(T t, BaseActivity activity) {
        mIView = t;
        mBaseViewActivity = new WeakReference<>(activity);
    }

    public BasePresenter(BaseActivity activity) {
        mBaseViewActivity = new WeakReference<>(activity);
    }

    public BasePresenter(T t) {
        mIView = t;
    }

    public BasePresenter() {
    }

    /**
     * 释放引用，防止内存泄露
     */
    public void destroy() {
        mIView = null;
    }
}
