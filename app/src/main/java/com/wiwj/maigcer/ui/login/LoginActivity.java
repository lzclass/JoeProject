package com.wiwj.maigcer.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wiwj.maigcer.R;
import com.wiwj.maigcer.ui.base.BaseActivity;
import com.wiwj.maigcer.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/***
 * 登录页面
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener,LoginContract.view{
    private LoginPresenter presenter;
    @BindView(R.id.et_telephone)
    EditText et_telephone;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.btn_login)
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            String telephone = et_telephone.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            if (TextUtils.isEmpty(telephone)) {
                Utils.showToast("帐号不能为空");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Utils.showToast("密码不能为空");
                return;
            }
            presenter.login(telephone, password);
        }
    }

    @Override
    public void openMainActivity() {

    }
}
