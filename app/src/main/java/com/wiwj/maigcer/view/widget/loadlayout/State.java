package com.wiwj.maigcer.view.widget.loadlayout;

/**
 *
 */
public interface State {
    /**
     * 加载中
     */
    int LOADING = 1;

    /**
     * 加载成功
     */
    int SUCCESS = 2;

    /**
     * 加载失败
     */
    int FAILED = 3;

    /**
     * 加载成功且返回无数据
     */
    int NO_DATA = 4;

}
