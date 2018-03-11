package com.wiwj.maigcer.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liuzhao on 2018/3/5.
 */

public class ResultData<T> {
    @SerializedName(value = "Status", alternate = {"status"})
    private boolean status;
    @SerializedName(value = "Data", alternate = {"data"})
    private T data;
    @SerializedName(value = "Count", alternate = {"count"})
    private Integer count;
    @SerializedName(value = "Error", alternate = {"error"})
    private ResultError error;

    public ResultData(boolean status, T data, Integer count, ResultError error) {
        this.status = status;
        this.data = data;
        this.count = count;
        this.error = error;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ResultError getError() {
        return error;
    }

    public void setError(ResultError error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "status=" + status +
                ", data=" + data +
                ", count=" + count +
                ", error=" + error +
                '}';
    }
}
