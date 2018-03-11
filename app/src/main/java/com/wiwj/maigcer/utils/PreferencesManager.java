package com.wiwj.maigcer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.wiwj.maigcer.config.Constants;
import com.wiwj.maigcer.view.activity.BaseApplication;

/**
 * Created by liuzhao on 2018/3/8.
 * 数据持久化存储类
 */

public class PreferencesManager {
    private static PreferencesManager instance = null;
    private SharedPreferences mSharedPreferences;
    private static Context mContext = BaseApplication.ourInstance.getApplicationContext();
    private String share_name = "magicer_saveInfo";
    private static SharedPreferences.Editor editor;

    private PreferencesManager(Context context) {
        this.mContext = context;
        getSharedPreferences();
    }

    public  static PreferencesManager getInstance() {
        if (instance == null) {
            synchronized (PreferencesManager.class) {
                if (instance == null) {
                    instance = new PreferencesManager(mContext);
                }
            }
        }
        return instance;
    }

    private SharedPreferences getSharedPreferences() {
        mSharedPreferences = mContext.getSharedPreferences(share_name,
                Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
        return mSharedPreferences;
    }
    public void clear() {//清除内容
        editor.remove(Constants.USER_NAME);
        editor.commit();
    }
    public String getUserName() {
        return mSharedPreferences.getString(Constants.USER_NAME, null);
    }

    public void setUserName(String userName) {
        editor.putString(Constants.USER_NAME, userName);
        editor.commit();
    }


}
