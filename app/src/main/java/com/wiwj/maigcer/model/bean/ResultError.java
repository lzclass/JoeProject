package com.wiwj.maigcer.model.bean;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by liuzhao on 2018/3/5.
 */

public class ResultError {
    @SerializedName(value = "Code", alternate = {"code"})
    private Integer code;
    @SerializedName(value = "Message", alternate = {"message"})
    private String message;
    @SerializedName(value = "MessageDetail", alternate = {"messageDetail"})
    private String messageDetail;
    @SerializedName(value = "ModelState", alternate = {"modelState"})
    private List<JsonObject> modelState;

    public ResultError(Integer code, String message, String messageDetail, List<JsonObject> modelState) {
        this.code = code;
        this.message = message;
        this.messageDetail = messageDetail;
        this.modelState = modelState;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(String messageDetail) {
        this.messageDetail = messageDetail;
    }

    public List<JsonObject> getModelState() {
        return modelState;
    }

    public void setModelState(List<JsonObject> modelState) {
        this.modelState = modelState;
    }

    @Override
    public String toString() {
        return "ResultError{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", messageDetail='" + messageDetail + '\'' +
                ", modelState=" + modelState +
                '}';
    }
}
