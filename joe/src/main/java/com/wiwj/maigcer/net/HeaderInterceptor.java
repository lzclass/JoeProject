package com.wiwj.maigcer.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liuzhao on 2018/3/6.
 * 统一添加头部
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = null;
//        String token = PreferencesManager.getInstance().getString(Constants.USER_TOKEN, Constants.VALUE_NO_LOGIN);
        Request request = chain.request().newBuilder()
                .addHeader("token", token)
                .addHeader("loginEntry", "2")//登录标识
                .addHeader("APPNAME", "moqueke").build();
        return chain.proceed(request);
    }
}
