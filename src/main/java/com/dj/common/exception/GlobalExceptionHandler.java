package com.dj.common.exception;

import com.dj.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//统一异常处理
@ControllerAdvice  //ControllerAdvice 定义全局异常处理器
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    //自定义异常处理
    @ExceptionHandler(CustomerException.class)
    @ResponseBody
    public Result customerException(CustomerException e){
        logger.error(e.getMessage(),e);
        return Result.error(e.getMessage());
    }


    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception e){
        logger.error("系统错误",e);
        return Result.error("系统错误");
    }
}
