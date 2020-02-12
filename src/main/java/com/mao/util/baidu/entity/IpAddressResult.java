package com.mao.util.baidu.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 根据ip地址获取详细地址封装类
 * @author mao by 14:58 2020/2/12
 */
@Getter
@Setter
@ToString
public class IpAddressResult {

    private String address;                     //地址拼写
    private IpAddressResultContent content;     //详细内容
    private int status;                         //请求状态码

}