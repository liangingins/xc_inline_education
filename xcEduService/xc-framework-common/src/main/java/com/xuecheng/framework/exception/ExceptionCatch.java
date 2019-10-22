package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 异常捕获类
 */
@ControllerAdvice//增强
public class ExceptionCatch {

    //这个错误日志整的不是很明白
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);

    private static  ImmutableMap<Class<?extends Throwable>,ResultCode> EXCEPTIONS;

    private static  ImmutableMap.Builder<Class<?extends Throwable>,ResultCode> builder = ImmutableMap.builder();

    /**
     *
     * 捕获exception
     */
    public ResponseResult exception(Exception e){
            LOGGER.error("catch exception : {} \r\n exception :",e.getMessage(),e);
            if(EXCEPTIONS==null){
                EXCEPTIONS = builder.build();
            }
            final ResultCode resultCode= EXCEPTIONS.get(e.getClass());
            final ResponseResult responseResult;
            if(resultCode!=null){
                responseResult = new ResponseResult(resultCode);
            }else{
                responseResult = new ResponseResult(CommonCode.SERVER_ERROR);
            }
            return responseResult;


    }
    /**
     * 捕获CustomException
     */
    @ExceptionHandler//异常处理器
    @ResponseBody
    public ResponseResult customException(CustomException e){
        LOGGER.error("catch exception : {} \r\n exception :",e.getMessage(),e);
        ResultCode resultCode = e.getResultCode();
        ResponseResult responseResult = new ResponseResult(resultCode);
        return responseResult;
    }

}
