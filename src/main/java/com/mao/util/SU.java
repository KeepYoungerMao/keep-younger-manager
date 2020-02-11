package com.mao.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

/**
 * 关于字符串的一些操作
 * 可能含有别的判断
 * @author mao by 15:34 2020/1/6
 */
public class SU {

    /**
     * 判断字符串是否为空
     * 仿写commons-lang3
     * @param str str
     * @return true/false
     */
    public static boolean isEmpty(String str){
        // " " is false
        return null == str || str.length() == 0;
    }

    /**
     * 判断字符串是否不为空
     * 仿写commons-lang3
     * @param str str
     * @return true/false
     */
    public static boolean isNotEmpty(String str){
        // " " is true
        return null != str && str.length() > 0;
    }

    /**
     * 判断集合不为空
     * @param collection 集合
     * @return true / false
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return null != collection && collection.size() > 0;
    }

    /**
     * 获取long型时间戳
     * @return 时间戳
     */
    public static Long getLongDate(){
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
        System.out.println(encoder.encode("123456"));
    }

}