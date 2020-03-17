package com.mao.service;

import com.mao.entity.Page;
import com.mao.entity.response.ResponseData;
import com.mao.entity.response.ResponseEnum;
import org.springframework.stereotype.Service;

/**
 * 基础service
 * @author mao by 11:16 2020/2/13
 */
@Service
public class BaseService {

    private static final String OK = "ok";

    protected <T> ResponseData ok(T data){
        return o(ResponseEnum.SUCCESS,OK,data);
    }

    protected ResponseData bad(String msg){
        return o(ResponseEnum.BAD_REQUEST,msg,null);
    }

    protected ResponseData permission(String msg){
        return o(ResponseEnum.PERMISSION,msg,null);
    }

    public ResponseData error(String msg){
        return o(ResponseEnum.ERROR,msg,null);
    }

    private <T> ResponseData o(ResponseEnum code,String msg, T data){
        return new ResponseData<>(code.getType(),msg,data);
    }

    /**
     * 设置页码参数
     * @param param 页码参数
     */
    protected  <T extends Page> void  transPageParam(T param){
        if (null == param.getLimit() || param.getLimit() <= 0)
            param.setLimit(20);
        Integer page = param.getPage();
        page = null == page || page <= 1 ? 0 : (page - 1)*param.getLimit();
        param.setPage(page);
    }

}