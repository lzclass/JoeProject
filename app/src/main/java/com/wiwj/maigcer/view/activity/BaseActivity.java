package com.wiwj.maigcer.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.wiwj.maigcer.R;
import com.wiwj.maigcer.net.LifeCycleEvent;
import com.wiwj.maigcer.utils.ActivityManager;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by liuzhao on 2018/3/5.
 */

public class BaseActivity extends AppCompatActivity {
    protected Context mContext;

    //用于控制retrofit的生命周期，以便在destroy或其他状态时终止网络请求
    public PublishSubject<LifeCycleEvent> lifecycleSubject = PublishSubject.create();

    //该方法用于提供lifecycleSubject（相当于实现了IBaseView中的getLifeSubject抽象方法）。
    //方便Presenter中直接通过IBaseView获取lifecycleSubject，而不用每次都作为参数传递过去
    public PublishSubject<LifeCycleEvent> getLifeSubject() {
        return lifecycleSubject;
    }

    //一般的rxjava使用场景下，控制Observable的生命周期
    public <T> ObservableTransformer<T, T> controlLife(final LifeCycleEvent event) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<LifeCycleEvent> lifecycleObservable = lifecycleSubject.filter(new Predicate<LifeCycleEvent>() {
                    @Override
                    public boolean test(LifeCycleEvent lifeCycleEvent) throws Exception {
                        //当生命周期为event状态时，发射事件
                        return lifeCycleEvent.equals(event);
                    }
                }).take(1);

                return upstream.takeUntil(lifecycleObservable);
            }
        };
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected void onPause() {
        lifecycleSubject.onNext(LifeCycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(LifeCycleEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(LifeCycleEvent.DESTROY);
        ActivityManager.getInstance().removeActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (mOnKeyClickListener != null) {//如果没有设置返回事件的监听，则默认finish页面。
                    mOnKeyClickListener.clickBack();
                } else {
                    finish();
                }
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }

    OnKeyClickListener mOnKeyClickListener;

    public void setOnKeyListener(OnKeyClickListener onKeyClickListener) {
        this.mOnKeyClickListener = onKeyClickListener;
    }

    /**
     * 按键的监听，供页面设置自定义的按键行为
     */
    public interface OnKeyClickListener {
        /**
         * 点击了返回键
         */
        void clickBack();

        //可加入其它按键事件
    }

    /**
     * 启动activity带有动画切换
     *
     * @param intent
     * @param requestCode
     * @param parentActivity
     */
    public void startActivityForResultAndAnima(Intent intent,
                                               int requestCode, Activity parentActivity) {
        if (intent != null) {
            parentActivity = getParent();
            if (parentActivity != null) {
                parentActivity.startActivityForResult(intent, requestCode);
                parentActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
            } else {
                startActivityForResult(intent, requestCode);
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
            }
        }
    }

    /**
     * 启动activity带有动画切换
     *
     * @param intent
     * @param parentActivity
     */
    public void startActivityForAnima(Intent intent, Activity parentActivity) {
        if (intent != null) {
            parentActivity = getParent();
            if (parentActivity != null) {
                parentActivity.startActivity(intent);
                parentActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
            } else {
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
            }
        }
    }

}
