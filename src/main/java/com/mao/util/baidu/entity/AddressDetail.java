package com.mao.util.baidu.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 地址详细信息
 * @author mao by 15:03 2020/2/12
 */
@Getter
@Setter
@ToString
public class AddressDetail {

    private String province;            //省
    private String city;                //市
    private String district;            //区
    private String street;              //街道
    private String street_number;       //街道编码
    private Integer city_code;          //市编码

}