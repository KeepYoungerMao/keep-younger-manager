package com.mao.util.weather.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 昨日天气信息类
 * @author mao by 11:44 2019/8/30
 */
@Getter
@Setter
@ToString
public class YesterdayWeather {

    private String date;            //日期
    private String high;            //最高温度
    private String fx;              //风向
    private String low;             //最低温度
    private String fl;              //风力
    private String type;            //天气情况

}