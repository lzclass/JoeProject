package com.wiwj.maigcer.view.activity;

import android.Manifest;
import android.os.Bundle;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.wiwj.maigcer.R;
import com.wiwj.maigcer.db.DbHelper;
import com.wiwj.maigcer.db.dbmodel.UserModel;
import com.wiwj.maigcer.glide.GlideManager;
import com.wiwj.maigcer.model.bean.LoginResult;
import com.wiwj.maigcer.permission.OnPermissionCallback;
import com.wiwj.maigcer.permission.PermissionManager;
import com.wiwj.maigcer.presenter.iview.ILoginView;
import com.wiwj.maigcer.presenter.login.LoginPresenter;
import com.wiwj.maigcer.utils.Utils;

import butterknife.BindView;

/**
 * @Author: Joe liuzhaojava@foxmail.com
 * @Date: 2018/3/10 0:11
 * @Description: 测试框架类，开发完删掉
 */
public class TestActivity extends ToolbarBaseActivity implements ILoginView {
    @BindView(R.id.iv_test)
    ImageView iv_test;
    LoginPresenter presenter;

    @Override
    public void setLoginResult(LoginResult data) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        LogTest();
//        LoaderImageTest();
//        PermissionTest();
        DBTest();
        getNetData();
    }

    private void getNetData() {
        presenter = new LoginPresenter(this, this);
        presenter.getLoginUserInfo("13388888888", "cs1234", "1");
    }

    private void DBTest() {
        for (int i = 0; i < 10; i++) {
            UserModel userModel = new UserModel();
            userModel.setId(Long.valueOf(i + 100));
            userModel.setUser_id(1004);
            userModel.setUser_name("Joe4444");
//            DbHelper.getInstance().getUserModelDBManager().insert(userModel);
            DbHelper.getInstance().getUserModelDBManager().insertOrReplace(userModel);
        }

    }

    private void PermissionTest() {
        PermissionManager.instance().with(this).request(new OnPermissionCallback() {
            @Override
            public void onRequestAllow(String permissionName) {
//                DialogUtil.showTips(TestActivity.this, getString(R.string.permission_control),
//                        getString(R.string.permission_allow) + "\n" + permissionName);
                Logger.d(permissionName);
            }

            @Override
            public void onRequestRefuse(String permissionName) {
//                DialogUtil.showTips(TestActivity.this, getString(R.string.permission_control),
//                        getString(R.string.permission_refuse) + "\n" + permissionName);
                Logger.d(permissionName);
            }

            @Override
            public void onRequestNoAsk(String permissionName) {
//                DialogUtil.showTips(TestActivity.this, getString(R.string.permission_control),
//                        getString(R.string.permission_noAsk) + "\n" + permissionName);
                Logger.d(permissionName);
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
    }


    private void LoaderImageTest() {
//        GlideManager.getInstance().loadNet(iv_test,"http://img1.imgtn.bdimg.com/it/u=4128497002,1012426327&fm=11&gp=0.jpg",null);
        GlideManager.getInstance().loadRoundCornersImage(iv_test, "http://img1.imgtn.bdimg.com/it/u=4128497002,1012426327&fm=11&gp=0.jpg", null);
        Utils.showToast("测试消息弹出");
    }

    //测试logger框架
    private void LogTest() {
//        Logger.d("debug");
//        Logger.e("error");
//        Logger.w("warning");
//        Logger.v("verbose");
//        Logger.i("information");
//        Logger.wtf("wtf!!!!");
    }

}

