package com.xuecheng.framework.model.response;

import lombok.ToString;

@ToString//这个是干嘛的？
public enum  CmsCode implements ResultCode {
    //自定义异常信息
    CMS_ADD_EXISTS(false,24001,"页面已存在!");


    //操作是否成功,true为成功，false操作失败
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    CmsCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return false;
    }

    @Override
    public int code() {
        return 0;
    }

    @Override
    public String message() {
        return null;
    }
}
