package com.mao.service;

import com.mao.mapper.response.ResponseData;
import com.mao.mapper.response.ResponseEnum;
import org.springframework.stereotype.Service;

/**
 * 基础service
 * @author mao by 11:16 2020/2/13
 */
@Service
public class BaseService {

    private static final String OK = "ok";

    protected static <T> ResponseData ok(T data){
        return o(ResponseEnum.SUCCESS,OK,data);
    }

    protected static ResponseData bad(String msg){
        return o(ResponseEnum.BAD_REQUEST,msg,null);
    }

    protected static ResponseData permission(String msg){
        return o(ResponseEnum.PERMISSION,msg,null);
    }

    public static ResponseData error(String msg){
        return o(ResponseEnum.ERROR,msg,null);
    }

    private static  <T> ResponseData o(ResponseEnum code,String msg, T data){
        return new ResponseData<>(code.getType(),msg,data);
    }

}