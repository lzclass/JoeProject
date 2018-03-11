package com.wiwj.maigcer.net;

import java.io.Serializable;

/**
 * RESTFul 返回值封装类
 * @param <T>
 */
public class HttpResult<T> implements Serializable{

    private String msg;
    private int code;
    private int page;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", page=" + page +
                ", data=" + data +
                '}';
    }
}
