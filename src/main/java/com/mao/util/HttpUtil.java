package com.mao.util;

import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Http方面工具类
 * @author mao by 14:58 2020/2/11
 */
public class HttpUtil {

    /**
     * 本机识别ip地址
     */
    private static final String LOCAL_IP = "0:0:0:0:0:0:0:1";

    /**
     * 标准本机ip地址
     */
    public static final String BASIC_LOCAL_IP = "127.0.0.1";

    /**
     * 本机名称
     */
    public static final String BASIC_LOCAL_NAME = "本机";

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

    /**
     * spring boot自带http请求方法
     * http请求
     * @param url 请求地址
     * @param method 请求方法类型
     * @param params 参数
     * @return 字符串类型返回结果
     */
    public static String http(String url, HttpMethod method,
                              MultiValueMap<String, String> params) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);
        RestTemplate template = new RestTemplate(requestFactory);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params,httpHeaders);
        ResponseEntity<String> entity = template.exchange(url, method, httpEntity, String.class);
        return entity.getBody();
    }

}