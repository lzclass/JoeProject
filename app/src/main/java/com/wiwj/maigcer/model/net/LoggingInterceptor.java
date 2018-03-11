package com.wiwj.maigcer.model.net;

import com.orhanobut.logger.Logger;
import com.wiwj.maigcer.config.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liuzhao on 2018/3/5.
 * 接口请求拦截
 */

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //如果不是在正式包，添加拦截 打印响应json
        if (Constants.IS_DEBUG) {
            Logger.d(String.format("请求地址: %s on %s%n%s",
                    request.url(),chain.connection(),request.headers()));
        }

        return chain.proceed(request);
    }

}
