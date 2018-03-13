package com.wiwj.maigcer.ui.base;

/***
 *
 * @param <T>
 */
public abstract class BasePresenter<T extends BaseView> implements IBasePresenter<T> {

    protected T mView;

    @Override
    public void onAttach(T mView) {
        this.mView = mView;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    @Override
    public void setUserAsLoggedOut() {

    }
}
