package com.wiwj.maigcer.model.net;

/**
 * Created by liuzhao on 2018/3/6.
 */

public class RxManager {

    private static class RxManagerHolder {
        public static RxManager instance = new RxManager();
    }

    public static RxManager getInstance() {
        return RxManagerHolder.instance;
    }


}
