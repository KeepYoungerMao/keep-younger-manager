package com.mao.entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 相应类型
 * @author mao by 16:54 2019/10/22
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(200),           //成功
    NOT_FOUND(404),         //404
    BAD_REQUEST(405),       //请求错误
    PERMISSION(406),        //权限错误
    ERROR(500);             //系统错误

    private int type;

}