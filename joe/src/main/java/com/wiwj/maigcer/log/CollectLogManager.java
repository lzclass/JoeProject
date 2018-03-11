package com.wiwj.maigcer.log;

import android.content.Context;

import java.io.File;

/**
 * @Author: Joe liuzhaojava@foxmail.com 2018/3/11 16:35
 * @Description:
 */
public class CollectLogManager implements CrashHandler.CrashListener {
    private static class collectLogManagerHolder {
        public static CollectLogManager instance = new CollectLogManager();
    }

    public static CollectLogManager getInstance() {
        return CollectLogManager.collectLogManagerHolder.instance;
    }

    public void init(Context context) {
        CrashHandler.getInstance().init(context, this);
    }

    @Override
    public void uploadExceptionToServer(File file) {

    }

    @Override
    public void crashAction() {

    }
}
