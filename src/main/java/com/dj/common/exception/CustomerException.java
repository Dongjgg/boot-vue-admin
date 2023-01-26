package com.dj.common.exception;

//自定义异常
public class CustomerException extends RuntimeException {

    public CustomerException(String message) {
        super(message);
    }
}
