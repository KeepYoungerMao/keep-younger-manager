package com.mao.config;

import com.mao.entity.sys.Menu;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据缓存类
 * @author zongx at 2020/1/9 21:14
 */
public class Cache {

    /**
     * 菜单数据
     */
    public static List<Menu> MENU = new ArrayList<>();

    /**
     * 聊天缓存
     */
    public static Map<String, Session> CHAT = new ConcurrentHashMap<>();

}
