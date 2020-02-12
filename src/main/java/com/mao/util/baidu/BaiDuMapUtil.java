package com.mao.util.baidu;

import com.mao.util.HttpUtil;
import com.mao.util.JsonUtil;
import com.mao.util.baidu.entity.IpAddressResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 百度地图工具类
 * @author mao by 14:42 2020/2/12
 */
public class BaiDuMapUtil {

    private static final String AK = "Po86Y8fZwYv5fpcQIX7MVk1DaMOl3VwB";

    /**
     * 根据ip地址获取地理位置api
     */
    private static final String GET_LOCATION_URL = "http://api.map.baidu.com/location/ip";

    /**
     * 根据ip地址获取地址信息
     * 百度api链接
     * @param ip ip地址
     * @return 地址信息
     */
    private static IpAddressResult getAddressByIP(String ip){
        Map<String,String> map = new HashMap<>();
        map.put("ak",AK);
        map.put("ip",ip);
        String result = HttpUtil.get(GET_LOCATION_URL,map);
        try {
            return JsonUtil.json2obj(result,IpAddressResult.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 只返回详细地址
     * @param ip ip
     * @return 详细地址
     */
    public static String getOnlyAddressByIP(String ip){
        if (HttpUtil.BASIC_LOCAL_IP.equals(ip))
            return HttpUtil.BASIC_LOCAL_NAME;
        IpAddressResult result = getAddressByIP(ip);
        if (null != result && result.getStatus() == 0)
            return result.getContent().getAddress();
        return null;
    }

    public static void main(String[] args) {
        String result = getOnlyAddressByIP("118.212.75.141");
        System.out.println(result);
    }

}