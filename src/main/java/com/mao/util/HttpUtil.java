package com.mao.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Http方面工具类
 * @author mao by 14:58 2020/2/11
 */
public class HttpUtil {

    private static final String LOCAL_IP = "0:0:0:0:0:0:0:1";
    private static final String BASIC_LOCAL_IP = "127.0.0.1";

    /**
     * 获取访问者IP
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     * @param request request
     * @return ip
     */
    public static String getAccountIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (SU.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (SU.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                ip = ip.substring(0, index);
            }
        } else {
            ip = request.getRemoteAddr();
        }
        return LOCAL_IP.equals(ip) ? BASIC_LOCAL_IP : ip;
    }

}