package com.mao.util.weather.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 接收API天气信息结果类
 * @author mao by 10:09 2018/8/31
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Result {

    private WeatherData data;       //天气信息
    private int status;             //接收状态码
    private String desc;            //接收情况：OK

    public Result(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

}