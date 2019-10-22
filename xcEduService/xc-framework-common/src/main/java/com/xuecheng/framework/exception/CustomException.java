package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * 自定义异常类
 * 异常信息包含：错误代码和错误信息
 */
public class CustomException extends RuntimeException {

    private ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        super("错误代码："+resultCode+"错误信息："+resultCode.message());
        this.resultCode = resultCode;
    }


    public ResultCode getResultCode() {
        return this.resultCode;
    }
}
