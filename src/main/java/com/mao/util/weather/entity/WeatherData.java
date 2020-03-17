package com.mao.util.weather.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 天气具体信息类
 * @author mao by 11:44 2019/8/30
 */
@Getter
@Setter
@ToString
public class WeatherData {

    private YesterdayWeather yesterday;         //昨日天气情况
    private String city;                        //城市
    private String aqi;                         //城市id
    private List<ForecastWeather> forecast;     //未来天气情况
    private String ganmao;                      //预防信息
    private String wendu;                       //平均温度

}