package com.dj.common;

import lombok.Data;


//封装统一返回结果
@Data
public class Result<T> { //T表示任意类型泛型
    private String code;
    private T data;
    private String msg;


    //成功请求返回信息,有数据
    public static <T> Result<T> success(T data){
        Result<T> res = new Result<>();
        res.setCode("200");
        res.setData(data);
        return res;
    }

    //成功请求返回信息,没有数据
    public static <T> Result<T> success(){
        Result<T> res = new Result<>();
        res.setCode("200");
        return res;
    }

    //失败请求返回，有数据
    public static <T> Result<T> error(String code,String msg){
        Result<T> res = new Result<>();
        res.setCode("200");
        res.setMsg(msg);
        return res;
    }

    //失败请求返回
    public static <T> Result<T> error(String msg){
        Result<T> res = new Result<>();
        res.setCode("-1");
        res.setMsg(msg);
        return res;
    }

}
