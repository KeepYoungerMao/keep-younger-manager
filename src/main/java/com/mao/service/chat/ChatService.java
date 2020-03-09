package com.mao.service.chat;

import javax.websocket.Session;

/**
 * 会话处理
 * @author mao by 11:04 2020/3/9
 */
public interface ChatService {

    /**
     * 公共聊天
     * @param type 会话处理类型
     * @param user 用户名
     * @param session 用户socket Session
     * @param message 会话信息
     */
    void publicChat(ChatEnum type, String user, Session session, String message);

}