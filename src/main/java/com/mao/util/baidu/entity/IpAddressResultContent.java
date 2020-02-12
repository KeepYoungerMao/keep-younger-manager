package com.mao.util.baidu.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 根据ip地址获取详细地址详细内容封装类
 * @author mao by 14:59 2020/2/12
 */
@Getter
@Setter
@ToString
public class IpAddressResultContent {

    private AddressDetail address_detail;   //地址详细信息
    private String address;                 //地址描述
    private AddressPoint point;             //地址经纬度

}