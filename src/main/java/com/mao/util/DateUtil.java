package com.mao.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间转换工具类
 * @author mao by 10:16 2020/2/13
 */
public class DateUtil {

    /**
     * 标准日期格式
     */
    private static final String FORMATTER = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取long型时间戳
     * @return 时间戳
     */
    public static Long getLongDate(){
        return System.currentTimeMillis();
    }

    /**
     * 根据时间戳转化成字符串类型时间
     * @param mills 时间戳
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDate(long mills){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER);
        Date date = new Date(mills);
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(formatter);
    }

    public static void main(String[] args) {
        System.out.println(getDate(1581564502541L));
    }

}