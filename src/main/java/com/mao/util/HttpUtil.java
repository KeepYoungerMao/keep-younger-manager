package com.mao.util;

import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

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

    //请求设置
    private static final int CONNECTION_TIMEOUT = 5000;
    private static final int READ_TIMEOUT = 5000;
    private static final String CHARSET = "UTF-8";

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
     * http请求
     * @param url url
     * @param params 参数
     * @param headers 头部
     * @param method 请求方法
     * @return 返回数据
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static String invokeUrl(String url, Map params, Map<String, String> headers, HttpMethod method) {
        //构造请求参数字符串
        StringBuilder paramsStr = null;
        if (params != null) {
            paramsStr = new StringBuilder();
            Set<Map.Entry> entries = params.entrySet();
            for (Map.Entry entry : entries) {
                String value = (entry.getValue() != null) ? (String.valueOf(entry.getValue())) : "";
                paramsStr.append(entry.getKey()).append("=").append(value).append("&");
            }
            //只有POST方法才能通过OutputStream(即form的形式)提交参数
            if (method != HttpMethod.POST) {
                url += "?" + paramsStr.toString();
            }
        }

        URL uUrl;
        HttpURLConnection conn = null;
        BufferedWriter out = null;
        BufferedReader in = null;
        try {
            //创建和初始化连接
            uUrl = new URL(url);
            conn = (HttpURLConnection) uUrl.openConnection();
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setRequestMethod(method.toString());
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //设置连接超时时间
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            //设置读取超时时间
            conn.setReadTimeout(READ_TIMEOUT);
            //指定请求header参数
            if (headers != null && headers.size() > 0) {
                Set<String> headerSet = headers.keySet();
                for (String key : headerSet) {
                    conn.setRequestProperty(key, headers.get(key));
                }
            }

            if (paramsStr != null && method == HttpMethod.POST) {
                //发送请求参数
                out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),CHARSET));
                out.write(paramsStr.toString());
                out.flush();
            }

            //接收返回结果
            StringBuilder result = new StringBuilder();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),CHARSET));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (Exception e) {
            //处理错误流，提高http连接被重用的几率
            try {
                if (null != conn){
                    InputStream es = conn.getErrorStream();
                    if (null != es) {
                        es.close();
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //关闭连接
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }

    /**
     * POST方法提交Http请求
     */
    public static String post(String url, Map params) {
        return invokeUrl(url,params,null,HttpMethod.POST);
    }

    /**
     * POST方法提交Http请求
     */
    public static String post(String url, Map params, Map<String, String> headers) {
        return invokeUrl(url,params,headers,HttpMethod.POST);
    }

    /**
     * GET方法提交Http请求
     */
    public static String get(String url, Map params) {
        return invokeUrl(url,params,null,HttpMethod.GET);
    }

    /**
     * GET方法提交Http请求
     */
    public static String get(String url, Map params, Map<String, String> headers) {
        return invokeUrl(url,params,headers,HttpMethod.GET);
    }

    /**
     * PUT方法提交Http请求
     */
    public static String put(String url, Map params) {
        return invokeUrl(url,params,null,HttpMethod.PUT);
    }

    /**
     * PUT方法提交Http请求
     */
    public static String put(String url, Map params, Map<String, String> headers) {
        return invokeUrl(url,params,headers,HttpMethod.PUT);
    }

    /**
     * DELETE方法提交Http请求
     */
    public static String delete(String url, Map params) {
        return invokeUrl(url,params,null,HttpMethod.DELETE);
    }

    /**
     * DELETE方法提交Http请求
     */
    public static String delete(String url, Map params, Map<String, String> headers) {
        return invokeUrl(url,params,headers,HttpMethod.DELETE);
    }

    /**
     * HEAD方法提交Http请求
     */
    public static String head(String url, Map params) {
        return invokeUrl(url,params,null,HttpMethod.HEAD);
    }

    /**
     * HEAD方法提交Http请求
     */
    public static String head(String url, Map params, Map<String, String> headers) {
        return invokeUrl(url,params,headers,HttpMethod.HEAD);
    }

}