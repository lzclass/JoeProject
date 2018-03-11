package com.wiwj.maigcer.model.net;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by liuzhao on 2018/3/5.
 * 封装请求回调
 * 在onError中进行统一的异常处理，得到更直接详细的异常信息
 * 在onNext中进行统一操作，如请求回来后，先判断token是否失效，如果失效则直接跳转登录页面
 * 在onNext中对返回的结果进行处理，得到更直接的数据信息
 * 可在onSubscribe中进行请求前的操作，注意，onSubscribe是执行在 subscribe() 被调用时的线程，所以如果在onSubscribe里进行UI操作，就要保证subscribe()也是调用在UI线程里。
 */

public abstract class RxObserver<T> implements Observer<HttpResult<T>> {

    private boolean isShowDialog;
    private Dialog mDialog;

    public RxObserver(Context mContext, boolean isShowDialog) {
        this.isShowDialog = isShowDialog;
        mDialog = new ProgressDialog(mContext);
        mDialog.setTitle("请稍后");
    }

    @Override
    public final void onSubscribe(Disposable d) {

        if (isShowDialog) {
            mDialog.show();
        }

    }

    @Override
    public final void onNext(HttpResult<T> httpResult) {
        if (httpResult != null) {
            onSuccess(httpResult.getData());
        }
        Logger.d("接口返回数据：", httpResult);
    }

    @Override
    public final void onError(Throwable e) {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
        if (e instanceof Exception) {
            //访问获得对应的Exception
            ExceptionHandler.ResponseThrowable responseThrowable = ExceptionHandler.handleException(e);
            onError(responseThrowable.code, responseThrowable.message);
        } else {
            //将Throwable 和 未知错误的status code返回
            ExceptionHandler.ResponseThrowable responseThrowable = new ExceptionHandler.ResponseThrowable(e, ExceptionHandler.ERROR.UNKNOWN);
            onError(responseThrowable.code, responseThrowable.message);
        }

    }

    @Override
    public final void onComplete() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public abstract void onSuccess(T t);

    public abstract void onError(int whichRequest, String errMessage);

}
