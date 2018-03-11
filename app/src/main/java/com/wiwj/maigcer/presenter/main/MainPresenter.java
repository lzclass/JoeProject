package com.wiwj.maigcer.presenter.main;

import com.wiwj.maigcer.model.MainModel;
import com.wiwj.maigcer.presenter.base.BasePresenter;
import com.wiwj.maigcer.presenter.iview.IMainView;
import com.wiwj.maigcer.view.activity.BaseActivity;

/**
 * Created by liuzhao on 2018/3/8.
 * 主界面
 */

public class MainPresenter extends BasePresenter<IMainView> {
    private MainModel mainModel;

    public MainPresenter(IMainView iMainView, BaseActivity activity) {
        super(iMainView, activity);

    }
    void saveLoginUserInfo(){
    }

}
