package com.mao.service.chat;

import com.mao.config.Cache;
import com.mao.entity.chat.ChatDataEnum;
import com.mao.entity.chat.ChatRequest;
import com.mao.entity.chat.ChatResponse;
import com.mao.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;

/**
 * @author mao by 11:04 2020/3/9
 */
@Service
public class DefaultChatService implements ChatService {

    private static final String SYSTEM_CHAT_NAME = "system";

    private static final Logger log = LoggerFactory.getLogger(DefaultChatService.class);

    /**
     * 公共聊天
     * @param type 会话处理类型
     * @param user 用户名
     * @param session 用户socket Session
     * @param message 会话信息
     */
    @Override
    public void publicChat(ChatEnum type, String user, Session session, String message) {
        switch (type){
            case OPEN:
                startChat(user,session);
                break;
            case MESSAGE:
                sendAll(user,message);
                break;
            case CLOSE:
                endChat(user);
                break;
        }
    }

    /**
     * 用户开启会话
     * @param user 用户名
     * @param session 用户socket Session
     */
    private void startChat(String user, Session session){
        String forSelfMessage = "您好，"+user+"！欢迎来到聊天室！";
        String forOtherMessage = user + "已加入聊天室";
        send(session,forSelfMessage);
        sendAll(forOtherMessage);
        addCache(user,session);
    }

    /**
     * 用户关闭会话
     * @param user 用户名
     */
    private void endChat(String user){
        String forOtherMessage = user + "已离开聊天室";
        removeCache(user);
        sendAll(forOtherMessage);
    }

    /**
     * 加入会话缓存
     * @param user 用户名
     * @param session 用户socket Session
     */
    private void addCache(String user, Session session){
        Cache.CHAT.put(user,session);
    }

    /**
     * 删除会话缓存
     * @param user 用户名
     */
    private void removeCache(String user){
        Cache.CHAT.remove(user);
    }

    /**
     * 群发
     * @param message 消息内容
     */
    private void sendAll(String user,String message){
        Cache.CHAT.forEach((_user, session) -> send(user,session,message));
    }

    /**
     * 群发
     * @param message 消息内容
     */
    private void sendAll(String message){
        Cache.CHAT.forEach((user, session) -> send(session,message));
    }

    /**
     * 消息发送
     * @param user 用户名
     * @param session 用户socket Session
     * @param message 消息
     */
    private void send(String user, Session session, String message){
        try {
            session.getBasicRemote().sendText(makeMessage(user,message));
        } catch (IOException e) {
            log.warn("[ chat ] warn: "+e.getMessage());
        }
    }

    /**
     * 消息发送
     * @param session 用户socket Session
     * @param message 消息
     */
    private void send(Session session, String message){
        try {
            session.getBasicRemote().sendText(makeMessage(message));
        } catch (IOException e) {
            log.warn("[ chat ] warn: "+e.getMessage());
        }
    }

    /**
     * 组合返回消息体
     * @param user 用户名
     * @param message 消息内容
     * @return 消息json
     */
    private String makeMessage(String user, String message){
        ChatRequest chatRequest = makeChatRequest(message);
        if (null == chatRequest)
            return errorMessage();
        return JsonUtil.obj2json(new ChatResponse(chatRequest.getType(),user,chatRequest.getMessage()));
    }

    /**
     * 组合返回消息体
     * 系统数据
     * @param message 消息内容
     * @return 消息json
     */
    private String makeMessage(String message){
        return JsonUtil.obj2json(new ChatResponse(ChatDataEnum.TEXT,SYSTEM_CHAT_NAME,message));
    }

    /**
     * 消息错误返回体
     * @return 消息错误返回体
     */
    private String errorMessage(){
        return JsonUtil.obj2json(new ChatResponse(ChatDataEnum.ERROR,null,null));
    }

    /**
     * 拆解消息请求体
     * @param message 消息
     * @return 消息请求体
     */
    private ChatRequest makeChatRequest(String message){
        return JsonUtil.json2obj(message,ChatRequest.class);
    }

}