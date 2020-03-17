package com.mao.entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author mao by 16:54 2019/11/26
 */
@Getter
@Setter
@AllArgsConstructor
public class ResponseData<T> {
    private int code;
    private String msg;
    private T data;
}